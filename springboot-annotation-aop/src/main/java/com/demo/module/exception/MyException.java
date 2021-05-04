package com.demo.module.exception;

import java.io.Serializable;

/**
 * <p> @Title MyException
 * <p> @Description 全局自定义异常
 *
 * @author ACGkaka
 * @date 2021/4/30 16:56
 */
public class MyException extends RuntimeException implements Serializable {
    private int errorCode;

    public MyException(){
        this.errorCode = 10099;
    }

    public MyException(String message){
        super(message);
        this.errorCode = 10099;
    }

    public MyException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MyException(int errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
