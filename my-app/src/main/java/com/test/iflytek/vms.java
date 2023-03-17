package com.test.iflytek;

import com.alibaba.fastjson.JSONObject;
import com.test.iflytek.task.IReqConstruct;
import com.test.iflytek.task.RecordRtmpStream;
import com.test.iflytek.task.VmsAudioTask;
import com.test.iflytek.task.VmsTask;


import java.util.HashMap;

/*
主运行类
 */
public class vms implements IReqConstruct {
    // 开放平台获取APP_ID ，APP_KEY ，API_SECRET 填入以下字段
    private static final String API_SECRET = "OTJkNjM3ZTRmZGRjZGYzYTZjMDI5ZTJj";
    private static final String APP_ID = "f8ecbbaa";
    private static final String API_KEY = "5b4e5257bdd10cb88d2d0e2b459372b7";


    private static final String OPEN_URL_PREFIX = "http://vms.cn-huadong-1.xf-yun.com"; // 创建、查询任务的地址开头
    private static final String WS_AudioDriver_Url = "ws://vms.cn-east-1.xf-yun.com/v1/private/vms2d_audio_ctrl"; // 创建、查询任务的地址开头

    public static void main(String[] args) throws InterruptedException {
        vms vms = new vms();

        VmsTask vmsTask = new VmsTask(OPEN_URL_PREFIX, API_KEY, API_SECRET);

        // 1. 开启 2d虚拟人 交互
        JSONObject startResp = vmsTask.Start(vms.ConstructStartRequest());
        System.out.println("start ==> " + startResp);
        if (startResp.get("header") == null || startResp.getJSONObject("header").getInteger("code") != 0) {
            return;
        }

        // 2. 获取重要的返回参数 拉流地址 ，session字段
        String livePullUrl = startResp.getJSONObject("header").getString("stream_url");
        String session = startResp.getJSONObject("header").getString("session");
        System.out.println("\nlive-pull-url : " + livePullUrl);
        System.out.println("session : " + session);

        // 开启java—cv 录制视频流(仅支持rtmp)
        RecordRtmpStream recordRtmpStream = new RecordRtmpStream();
        recordRtmpStream.setStreamURL(livePullUrl);
        recordRtmpStream.setFilePath("/Users/a123/code/2022/my-app/src/main/resources/tts/test-demo.flv");
//        recordRtmpStream.setFilePath("/Users/a123/code/2022/my-app/src/main/resources/tts/test-demo.mp4");
        recordRtmpStream.start();
        System.out.println("start record video !!!");

        Thread.sleep(10000); // 模拟操作时间间隔
        System.out.println("----------------------------go on------------------------------------");

        // 3.1 进行文本驱动
        // 科大讯飞，人工智能让世界更美好！ base64 后的加密字符
//        String text = "56eR5aSn6K6v6aOe77yM5Lq65bel5pm66IO96K6p5LiW55WM5pu0576O5aW977yB";
        String text = "6aOO6JCn6JCn5YWu5piT5rC05a+SLOWQm+WtkOS4gOWOu+WFruS4jeWkjei/mC7lhbXmnaXlsIbmjKHmsLTmnaXlnJ/mjqnjgIIK";
        // json 格式的动作信息 base64 后的加密字符
//        String ctrlW = "eyJhdmF0YXIiOlt7InR5cGUiOiJhY3Rpb24iLCJ2YWx1ZSI6ImExXzRfMl9MIiwid2IiOjMsIndlIjo1fV19";
        String ctrlW = "eyJhdmF0YXIiOlt7InR5cGUiOiJhY3Rpb24iLCJ2YWx1ZSI6IkFfUkxIX2ludHJvZHVjZWRfTyIsIndiIjozLCJ3ZSI6NX1dfQ==";
        //{"avatar":[{"type":"action","value":"a1_4_2_L","wb":3,"we":5}]}
        //{"avatar":[{"type":"action","value":"A_RLH_introduced_O","wb":3,"we":5}]}
        JSONObject driveResp = vmsTask.Drive(vms.ConstructDriveRequest(session, text, ctrlW));
        // 打印出驱动返回结果
        System.out.println("\ntext drive ==> " + driveResp);

        // 3.2 进行音频驱动 ，可去除注释使用
        Thread.sleep(10000*3); // 模拟操作时间间隔
//        VmsAudioTask vmsAudioTask = new VmsAudioTask(WS_AudioDriver_Url,API_KEY,API_SECRET,APP_ID);
//        vmsAudioTask.StartAudioDriver(session);

        // 4.进行ping操作
        JSONObject pingResp = vmsTask.Ping(vms.ConstructPingRequest(session));
        System.out.println("\nping ==> " + pingResp);
//        Thread.sleep(10000); // 模拟操作时间间隔

        // 5. 进行任务 停止
        JSONObject stopResp = vmsTask.Stop(vms.ConstructStopRequest(session));
        // 打印出任务停止返回结果
        System.out.println("\nstop ==> " + stopResp);
        System.out.println("task end !!!");
    }

    public String ConstructStartRequest() {
        HashMap<String,Object> request = new HashMap<>();
        request.put("header", new HashMap<String, Object>(){{
            put("app_id",APP_ID);
            put("uid","12345678");
        }});
        request.put("parameter",new HashMap<String, Object>(){{
            put("vmr",new HashMap<String, Object>(){{
                put("stream",new HashMap<String, Object>(){{
                    put("protocol","rtmp");
                }});
                put("avatar_id","110005018");
                put("width",1920);
                put("height",1080);
            }});
        }});
        return new JSONObject(request).toString();
    }

    public String ConstructDriveRequest(String session, String text, String ctrlW) {
        HashMap<String,Object> request = new HashMap<>();
        request.put("header", new HashMap<String, Object>(){{
            put("app_id",APP_ID);
            put("uid","12345678");
            put("session",session);
        }});
        request.put("parameter",new HashMap<String, Object>(){{
            put("tts",new HashMap<String, Object>(){{
//                put("vcn","x2_yilin");
                put("vcn","x3_qianxue");
//                put("vcn","x3_chaoge");
            }});
        }});
        request.put("payload",new HashMap<String, Object>(){{
            put("text",new HashMap<String, Object>(){{
                put("encoding","utf8");
                put("compress","raw");
                put("format","plain");
                put("text",text);
            }});
            put("ctrl_w",new HashMap<String, Object>(){{
                put("encoding","utf8");
                put("compress","raw");
                put("format","plain");
                put("text",ctrlW);
            }});
        }});
        return new JSONObject(request).toString();
    }

    public String ConstructPingRequest(String session) {
        HashMap<String,Object> request = new HashMap<>();
        request.put("header", new HashMap<String, Object>(){{
            put("app_id",APP_ID);
            put("uid","12345678");
            put("session",session);
        }});
        return new JSONObject(request).toString();
    }


    public String ConstructStopRequest(String session) {
        HashMap<String,Object> request = new HashMap<>();
        request.put("header", new HashMap<String, Object>(){{
            put("app_id",APP_ID);
            put("uid","12345678");
            put("session",session);
        }});
        return new JSONObject(request).toString();
    }
}
