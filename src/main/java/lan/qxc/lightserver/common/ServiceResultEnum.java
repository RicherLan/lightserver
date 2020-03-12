package lan.qxc.lightserver.common;

public enum ServiceResultEnum {

    ERROR("error"),
    SUCCESS("success"),
    LOGIN_FAIL("用户名或密码错误"),
    PHONE_HAS_EXIST("该手机号已注册"),
    USER_NICKNAME_HAS_EXIST("还昵称已存在"),
    VERIFY_CODE_ERROR("验证码错误"),
    DB_ERROR("database error");;


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
