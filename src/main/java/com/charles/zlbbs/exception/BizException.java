package com.charles.zlbbs.exception;


public class BizException extends RuntimeException {
    protected Integer errorCode;
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(BaseErrorInfoInterface errorInfo) {
        super(errorInfo.getMessage());
        this.errorCode = errorInfo.getCode();
        this.errorMsg = errorInfo.getMessage();
    }

    public BizException(BaseErrorInfoInterface errorInfo, Throwable cause) {
        super(errorInfo.getMessage(), cause);
        this.errorCode = errorInfo.getCode();
        this.errorMsg = errorInfo.getMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(errorMsg);
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

}
