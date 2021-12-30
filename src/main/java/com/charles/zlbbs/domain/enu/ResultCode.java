package com.charles.zlbbs.domain.enu;

import com.charles.zlbbs.exception.BaseErrorInfoInterface;

public enum ResultCode implements BaseErrorInfoInterface {
    /* 成功 */
    SUCCESS(200, "操作成功"),

    /* 默认失败 */
    COMMON_FAIL(500, "操作失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "账号或密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    INCONSISTENT_PASSWORD(2010, "两次密码输入不一致"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有操作权限"),

    EMAIL_EXPIRED(4001, "邮箱验证码过期"),
    EMAIL_ERROR(4002, "邮箱验证码错误"),
    EMAIL_SEND_ERROR(4003, "邮箱远程连接失败，请稍后再试"),

    GRAPH_CAPTCHA_EXPIRED(4003, "图形验证码过期"),
    GRAPH_CAPTCHA_ERROR(4004, "图形验证码错误"),

    /* 系统错误 */
    UNKNOWN_ERROR(5000, "接口未知异常，请稍后再试"),
    SERVER_BUSY(50001, "服务器正忙，请稍后再试！");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}
