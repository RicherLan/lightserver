package lan.qxc.lightserver.common;

public enum ServiceResultEnum {

    ERROR("error"),
    SUCCESS("success"),
    LOGIN_FAIL("用户名或密码错误"),
    PHONE_HAS_EXIST("该手机号已注册"),
    USER_NICKNAME_HAS_EXIST("还昵称已存在"),
    LOGIN_PHONE_NULL("请输入手机号码"),
    LOGIN_NAME_NULL("请输入登录名！"),
    LOGIN_PASSWORD_NULL("请输入密码！"),
    LOGIN_VERIFY_CODE_NULL("请输入验证码！"),
    VERIFY_CODE_ERROR("验证码错误"),
    OLD_PASSWORD_ERROR("旧密码错误"),
    DB_ERROR("database error");


    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



}
