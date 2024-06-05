package com.zbh.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request.Text;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request.Msg;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.taobao.api.ApiException;
import com.zbh.common.RespConstants;
import com.zbh.config.DingTalkConfig;
import com.zbh.config.MyException;
import com.zbh.entity.po.CarFocusRecord;
import com.zbh.entity.vo.CarInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class DingTalkUtils {

    private static final HashMap<String, String> pusherMap = new HashMap<>();
    static {
        pusherMap.put("郑斌豪", "482141070136468975");
        pusherMap.put("张三", "482141070136468976");
        pusherMap.put("李四", "482141070136468977");
    }

    public static String sendToDing(CarInfo carInfo, CarFocusRecord record) {
        try {
            String accessToken = DingTalkConfig.getValidAccessToken();
            DingTalkClient client = new DefaultDingTalkClient(DingTalkConfig.MESSAGE_SEND_URL + accessToken);
            String pushers = record.getPusherName();
            String pusherIds = Arrays.stream(pushers.split(","))
                    .map(pusherMap::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining(","));

            if (pusherIds.isEmpty()) {
                throw new MyException(RespConstants.NO_VALID_PUSHER);
            }
            OapiMessageCorpconversationAsyncsendV2Request request = buildMessageRequest(accessToken, pusherIds, carInfo);
            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, accessToken);
            return response.getBody();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static OapiMessageCorpconversationAsyncsendV2Request buildMessageRequest(String accessToken, String pusherIds, CarInfo carInfo) {
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setAgentId(DingTalkConfig.AGENT_ID);
        request.setUseridList(pusherIds);

        Msg msg = new Msg();
        msg.setMsgtype("text");
        Text textContent = new Text();
        textContent.setContent(formatCarInfoMessage(carInfo));
        msg.setText(textContent);
        request.setMsg(msg);
        return request;
    }

    private static String formatCarInfoMessage(CarInfo carInfo) {
        return String.format("重点车辆:%s, 车牌颜色:%s, 状态:%s, 时间:%s, 停车场名称:%s, 通道名称:%s",
                carInfo.getLicensePlateNumber(), carInfo.getPlateColor(), carInfo.getStatus(),
                carInfo.getStatusTime(), carInfo.getParkName(), carInfo.getChannelName());
    }


}
