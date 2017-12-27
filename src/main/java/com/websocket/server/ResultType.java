package com.websocket.server;

/**
 * @author halochen
 * @since 2017-03-16
 */
public enum ResultType {
    SUCCESS(1,"请求成功"),
    FAILURE(-1,"请求失败")
    ;

    int code;
    String message;

    ResultType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public static ResultType getResultByCode(int code)  {
        ResultType target = null;
        for(ResultType resultType : ResultType.values()) {
            if(resultType.getCode() == code) {
                target = resultType;
                break;
            }
        }
        return target;
    }
}
