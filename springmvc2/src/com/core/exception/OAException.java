package com.core.exception;

/**
 * 办公管理系统基础异常类
 *
 * @author LEE.SIU.WAH
 * @version 1.0
 * @email lixiaohua7@163.com
 * @date 2014年10月7日 下午4:18:16
 */
public class OAException extends RuntimeException {

    private static final long serialVersionUID = 537866350045313959L;

    public OAException() {
    }

    public OAException(String message) {
        super(message);
    }

    public OAException(Throwable cause) {
        super(cause);
    }

    public OAException(String message, Throwable cause) {
        super(message, cause);
    }

    public OAException(String message, Throwable cause,
                       boolean enableSuppression, boolean writableStackTrace) {
        //super(message, cause, enableSuppression, writableStackTrace);
    }
}