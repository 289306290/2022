package com.test.iflytek.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.iflytek.util.AuthUtils;
import com.test.iflytek.util.HttpUtil;


public class VmsTask {

    private String ulrPrefix;
    private String apiKey;
    private String apiSecret;

    public VmsTask(String ulrPrefix, String apiKey, String apiSecret) {
        this.ulrPrefix = ulrPrefix;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public String getUlrPrefix() {
        return ulrPrefix;
    }

    public void setUlrPrefix(String ulrPrefix) {
        this.ulrPrefix = ulrPrefix;
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


    private final String Method = "POST";

    public JSONObject Start(String reqParam) {
        try {
            String reqUrl = ulrPrefix + "/v1/private/vms2d_start";
            return JSON.parseObject(HttpUtil.NetworkRequest(AuthUtils.AssembleRequestUrl(reqUrl,apiKey,apiSecret,Method),reqParam));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public JSONObject Drive(String reqParam) {
        try {
            String reqUrl = ulrPrefix + "/v1/private/vms2d_ctrl";
            return JSON.parseObject(HttpUtil.NetworkRequest(AuthUtils.AssembleRequestUrl(reqUrl,apiKey,apiSecret,Method),reqParam));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public JSONObject Stop(String reqParam) {
        try {
            String reqUrl = ulrPrefix + "/v1/private/vms2d_stop";
            return JSON.parseObject(HttpUtil.NetworkRequest(AuthUtils.AssembleRequestUrl(reqUrl,apiKey,apiSecret,Method),reqParam));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public JSONObject Ping(String reqParam) {
        try {
            String reqUrl = ulrPrefix + "/v1/private/vms2d_ping";
            return JSON.parseObject(HttpUtil.NetworkRequest(AuthUtils.AssembleRequestUrl(reqUrl,apiKey,apiSecret,Method),reqParam));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
