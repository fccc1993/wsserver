package com.websocket.server.controller;

import com.websocket.server.result.ResultBean;
import com.websocket.server.result.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Created by fccc on 2018/1/2.
 */
@Controller
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 统一返回异常信息,在被此异常处理拦截之前会先匹配粒度更细的异常处理
     * @param e 异常对象
     * @return backresult 返回结果
     */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBean handleException(Exception e){
        return ResultBean.failureOf(ResultType.FAILURE.getMessage(),e.getMessage());
    }





}
