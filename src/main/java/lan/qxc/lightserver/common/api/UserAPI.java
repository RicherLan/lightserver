package lan.qxc.lightserver.common.api;

public interface UserAPI {

    String LOGIN = "/login";

    String REGISTER_IS_PHONE_HAS_EXIST = "/register/is_phone_exist";
    String REGISTER_REGIST_USER_URL = "/register/regist_user";

    String UPDATE_USER_INFO = "/user/update/info";
    String UPLOAD_USER_ICON = "/user/update/upload_headic_file";

    String UPDATE_USER_PASSWORD = "/user/update/password";

}
