package com.zbh.common;


import lombok.Data;

import java.io.Serializable;

@Data
public class Resp implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;
    
    private String code;
    private String msg;
    private Object data;
    private Long totalRows;
    private Integer currentPage;
    

    public static Resp buildResp(RespConstants resp) {
        return new Resp(resp.getCode(), resp.getMsg(), "");
    }

    public static Resp buildResp(String code, String msg, Object data) {
        return new Resp(code, msg, data);
    }

    public static Resp successResp(String code, String msg, Object data) {
        return new Resp(code, msg, data);
    }

    public static Resp successResp(String code, String msg, Object data, Long totalRows, Integer currentPage) {
        return new Resp(code, msg, data, totalRows, currentPage);
    }

    public static Resp errorResp(RespConstants resp) {
        return new Resp(resp.getCode(), resp.getMsg(), "{}");
    }

    public static Resp errorResp(String code, String msg) {
        Resp resp = new Resp(code, msg, "");
        System.out.println(resp);
        return resp;
    }

    public static Resp errorResp(String code, String msg, Object data) {
        return new Resp(code, msg, data);
    }



    public Resp(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp(String code, String msg, Object data, Long totalRows, Integer currentPage) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.totalRows = totalRows;
        this.currentPage = currentPage;
    }



}
