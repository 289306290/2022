package com.test.iflytek.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.iflytek.util.AuthUtils;
import okhttp3.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;


/*
    websocket协议 进行语音驱动
 */
public class VmsAudioTask extends WebSocketListener {
    private String hostUrl;
    private String apiKey;
    private String apiSecret;
    private String appid;

    public VmsAudioTask() {
    }

    public VmsAudioTask(String hostUrl, String apiKey, String apiSecret, String appid) {
        this.hostUrl = hostUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.appid = appid;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    private static final String audioFilePath = "/Users/a123/code/2022/my-app/src/main/resources/2min.pcm";

    public static final int StatusFirstFrame = 0;
    public static final int StatusContinueFrame = 1;
    public static final int StatusLastFrame = 2;
    private final  String encoding = "raw";
    private final  String ctrlT = "eyJhdmF0YXIiOlt7InR5cGUiOiJhY3Rpb24iLCJ2YWx1ZSI6IkFfTEhfcGxlYXNlX08iLCJ3YiI6MSwid2UiOjN9LHsidHlwZSI6ImFjdGlvbiIsInZhbHVlIjoiQV9SSF9saWtlX08iLCJ3YiI6NCwid2UiOjV9LHsidHlwZSI6ImFjdGlvbiIsInZhbHVlIjoiQV9STEhfZW1waGFzaXplX08iLCJ3YiI6NSwid2UiOjd9XX0=";
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        System.out.println("open connection");
        // new Thread(()->{
        //连接成功，开始发送数据

        // }).start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        ResponseData resp = JSON.parseObject(text, ResponseData.class);
        if (resp != null) {
            if (resp.getHeader().getCode() != 0) {
                System.out.println("error=>" + resp.getHeader().getMessage() + " sid=" + resp.getHeader().getSid());
                return;
            }
            if (resp.getHeader() != null) {
                if (resp.getHeader().getCode() == 0) {
                    System.out.println(text);
                }

                if (resp.getHeader().getStatus() == 2) {
                    System.out.println("session end ");
                    webSocket.close(1000,"data finished");
                    System.exit(0);
                } else {
                    // todo 根据返回的数据处理
                    System.out.println("返回内容:"+resp.getPayload().toString());
                }
            }
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        System.out.println(t.getMessage());
        System.out.println("error:"+response);
        if (response == null){
            return;
        }
        System.out.println("error:"+response.code());
    }


    public void StartAudioDriver(String session){
        // 构建鉴权url
        String authUrl = AuthUtils.AssembleRequestUrl(hostUrl, apiKey, apiSecret,"GET");
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request req = new Request.Builder().url(authUrl).build();

        System.out.println("ws url === >" + authUrl);
        WebSocket webSocket = client.newWebSocket(req, new VmsAudioTask());
        File file = new File(audioFilePath);
        try {
            //打开音频文件
            int frameSize = 1280; //1280 // 482 //每一帧音频的大小
            int intervel = 40;
            int status = StatusFirstFrame;  // 音频的状态
            int count = 0;
            try (FileInputStream fs = new FileInputStream(file)) {
                byte[] buffer = new byte[frameSize];
                // 发送音频
                end:
                while (true) {
                    int len = fs.read(buffer);
                    if (len < frameSize) {
                        status = StatusLastFrame;  //文件读完，改变status 为 2
                    }

                    switch (status) {
                        case StatusFirstFrame:   // 第一帧音频status = 0
                            byte[] audioData = Arrays.copyOf(buffer, len);
                            HashMap<String, Object> request = SendFirstFrameData(audioData,session,ctrlT);

                            status = StatusContinueFrame;  // 发送完第一帧改变status 为 1
                            JSONObject json = new JSONObject(request);
                            webSocket.send(json.toString());
                            break;

                        case StatusContinueFrame:  //中间帧status = 1
                            byte[] audioData1 = Arrays.copyOf(buffer, len);
                            HashMap<String, Object> request1 = SendContinueFrameData(audioData1,session,ctrlT);
                            // 发送完第一帧改变status 为 1
                            JSONObject json1 = new JSONObject(request1);
                            webSocket.send(json1.toString());
                            break;

                        case StatusLastFrame:    // 最后一帧音频status = 2 ，标志音频发送结束
                            byte[] audioData2 = Arrays.copyOf(buffer, len);
                            HashMap<String, Object> request2 = SendLastFrameData(audioData2,session,ctrlT);
                            JSONObject json2 = new JSONObject(request2);
                            webSocket.send(json2.toString());
                            break end;
                    }
                    count++;
                    Thread.sleep(intervel); //模拟音频采样延时
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private  HashMap<String, Object> SendFirstFrameData(byte[] audio, String session,String ctrlT) {
        HashMap<String,Object> request = new HashMap<>();

        request.put("header", new HashMap<String, Object>(){{
            put("app_id",appid);
            put("status",StatusFirstFrame);
            put("session",session);
            put("uid","77607d78ccc5424986cdbe4039879b14");
        }});

        request.put("parameter",new HashMap<String, Object>(){{
            put("vmr",new HashMap<String, Object>(){{
                put("audio",new HashMap<String, Object>(){{
                    put("encoding",encoding);
                    put("sample_rate",16000);
                }});
            }});
        }});

        request.put("payload",new HashMap<String, Object>(){{
            put("audio",new HashMap<String, Object>(){{
                put("audio",Base64.getEncoder().encodeToString(audio));
                put("encoding","raw");
                put("sample_rate",16000);
                put("status",StatusFirstFrame);
            }});

            put("ctrl_t",new HashMap<String, Object>(){{
                put("text",ctrlT);
                put("compress","raw");
                put("format","plain");
                put("encoding","utf8");
                put("status",StatusFirstFrame);
            }});
        }});
        return request;
    }

    private  HashMap<String, Object> SendContinueFrameData(byte[] audio, String session,String ctrlT) {
        HashMap<String,Object> request = new HashMap<>();

        request.put("header", new HashMap<String, Object>(){{
            put("app_id",appid);
            put("status",StatusContinueFrame);
            put("session",session);
            put("uid","77607d78ccc5424986cdbe4039879b14");
        }});

        request.put("payload",new HashMap<String, Object>(){{
            put("audio",new HashMap<String, Object>(){{
                put("audio",Base64.getEncoder().encodeToString(audio));
                put("encoding","raw");
                put("sample_rate",16000);
                put("status",StatusContinueFrame);
            }});
            put("ctrl_t",new HashMap<String, Object>(){{
                put("text",ctrlT);
                put("compress","raw");
                put("format","plain");
                put("encoding","utf8");
                put("status",StatusContinueFrame);
            }});
        }});
        return request;
    }

    private  HashMap<String, Object> SendLastFrameData(byte[] audio, String session,String ctrlT) {
        HashMap<String,Object> request = new HashMap<>();

        request.put("header", new HashMap<String, Object>(){{
            put("app_id",appid);
            put("status",StatusLastFrame);
            put("session",session);
            put("uid","77607d78ccc5424986cdbe4039879b14");
        }});

        request.put("payload",new HashMap<String, Object>(){{
            put("audio",new HashMap<String, Object>(){{
                put("audio",Base64.getEncoder().encodeToString(audio));
                put("encoding","raw");
                put("sample_rate",16000);
                put("status",StatusLastFrame);
            }});
            put("ctrl_t",new HashMap<String, Object>(){{
                put("text",ctrlT);
                put("compress","raw");
                put("format","plain");
                put("encoding","utf8");
                put("status",StatusLastFrame);
            }});
        }});
        return request;
    }

    private static class ResponseData{
        Header header;
        Object payload;

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public Object getPayload() {
            return payload;
        }

        public void setPayload(Object payload) {
            this.payload = payload;
        }
    }


    private static class Header{
        int code;
        String message;
        String sid;
        int status;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}