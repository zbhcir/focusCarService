package com.zbh.common;

public enum RespConstants {
    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    INSERT_FOCUS_ERROR("0003", "插入重点车辆错误,"),
    DELETE_FOCUS_ERROR("0004", "删除重点车辆错误"),
    UPDATE_FOCUS_ERROR("0005", "修改重点车辆错误"),
    SELECT_FOCUS_ERROR("0006", "查询重点车辆错误"),
    SELECT_TRACK_ERROR("0006", "查询重点车辆行驶轨迹错误"),
    CLOUD_SEND_FAIL("0007", "云平台发送消息失败"),
    PARK_SEND_FAIL("0008", "停车场发送消息失败"),
    NO_VALID_PUSHER("0009", "没有有效发送人"),
    INVALID_TIME("0010", "结束时间早于开始时间"),
    INVALID_KAFKA_INFO("0011", "kafka消息不存在"),
    SEND_DING_FILE("0012", "向钉钉发送消息失败");

    private String code;
    private String msg;

    RespConstants(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
