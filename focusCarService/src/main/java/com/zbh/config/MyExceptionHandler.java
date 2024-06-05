package com.zbh.config;

import com.zbh.common.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice(basePackages="com.zbh.controller")
public class MyExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Resp exceptionHandler(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
//            log.error(e.getMessage(), e);
            return Resp.errorResp(myException.getCode(), myException.getMsg());
        }
        return Resp.errorResp("500", "服务端异常");
    }

}
