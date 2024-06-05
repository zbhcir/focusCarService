package com.zbh.config;

import com.zbh.common.RespConstants;

public class MyException extends RuntimeException{
    private String code;
    private String msg;

    public MyException(RespConstants resp) {
        super();
        this.code = resp.getCode();
        this.msg = resp.getMsg();
    }

    public MyException(String code, String msg) {
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
