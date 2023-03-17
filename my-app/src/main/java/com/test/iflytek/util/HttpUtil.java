package com.test.iflytek.util;

import okhttp3.*;

public class HttpUtil {
    static OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    public static String NetworkRequest(String reqUrl, String content){
        try {

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, content);
            Request request = new Request.Builder()
                    .url(reqUrl)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() != 0) {
                System.out.println("http request error :" + response);
            }
            return response.body().string();
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
