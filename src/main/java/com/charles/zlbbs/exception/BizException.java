package com.charles.zlbbs.exception;


public class BizException extends RuntimeException {
    protected Integer errorCode;
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(BaseErrorInfoInterface errorInfo) {
        super(String.valueOf(errorInfo.getCode()));
        this.errorCode = errorInfo.getCode();
        this.errorMsg = errorInfo.getMessage();
    }

    public BizException(BaseErrorInfoInterface errorInfo, Throwable cause) {
        super(String.valueOf(errorInfo.getCode()), cause);
        this.errorCode = errorInfo.getCode();
        this.errorMsg = errorInfo.getMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(String.valueOf(errorCode));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
