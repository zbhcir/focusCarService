package com.zbh.config;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;

import java.util.Date;

public class DingTalkConfig {
    public static final Long AGENT_ID = 3096311167L;
    public static final String MESSAGE_SEND_URL = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2?";
    private static final String TOKEN_URL = "https://oapi.dingtalk.com/gettoken";
    private static final String APP_KEY = "dingfvrcwmndwslzbu03";
    private static final String APP_SECRET = "RcINVHy_D04YVUffLko5P-02hSUIPHszHVNfqcVANHlS9o3Yz-cKBbbx7mEWd9aD";

    private static String accessToken;
    private static Date expirationTime;

    // 获取访问令牌的方法，模拟实际逻辑，简单起见直接返回固定字符串
    private static String fetchNewAccessToken() {
        // 实际应用中这里会调用API获取新的accessToken
        return getAccessToken();
    }

    // 保证每次获取的accessToken有效
    public static String getValidAccessToken() {
        if (accessToken == null || new Date().after(expirationTime)) {
            // 如果令牌为空或已过期，则重新获取
            accessToken = fetchNewAccessToken();
            // 假设新获取的令牌有效期为2小时，这里简化处理，未考虑实际API响应中的有效期字段
            expirationTime = new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000); // 2小时后过期
        }
        return accessToken;
    }

    public static String getAccessToken() {
        DingTalkClient client = new DefaultDingTalkClient(TOKEN_URL);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(APP_KEY);
        request.setAppsecret(APP_SECRET);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = null;
        try {
            response = client.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response.getBody());
        return response.getAccessToken();
    }
}
