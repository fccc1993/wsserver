package com.websocket.server;


/**
 * @author halochen
 * @since 2017-03-16
 */
public class ResultBean {
    private Integer code;
    private String message;
    private Object data;

    private static class BackResultHolder{
        private static final ResultBean INSTANCE = new ResultBean();
    }

    public static final ResultBean successOf(Object data){
        return successOf(ResultType.SUCCESS.getMessage(),data);
    }

    public static final ResultBean successOf(String message){
        return successOf(message,null);
    }

    public static final ResultBean successOf(String message, Object data){
        ResultBean resultBean = BackResultHolder.INSTANCE;
        resultBean.setCode(ResultType.SUCCESS.getCode());
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }


    public static final ResultBean failureOf(String message){
        return failureOf(message,null);
    }


    public static final ResultBean failureOf(Object data){
        return failureOf(ResultType.FAILURE.getMessage(),data);
    }

    public static final ResultBean failureOf(String message, Object data){
        ResultBean resultBean = BackResultHolder.INSTANCE;
        resultBean.setCode(ResultType.FAILURE.getCode());
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }

    public static final ResultBean valueOf(Integer code, String message, Object data){
        ResultBean resultBean = BackResultHolder.INSTANCE;
        resultBean.setCode(code);
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
