package lan.qxc.lightserver.service;

import lan.qxc.lightserver.entity.User;

public interface UserService {


    /**
     *
     * 登录
     * @param phone
     *
     * @param password
     * @return
     */
    User login(String phone,String password);


    /**
     * 某手机号是否已经注册
     * @param phone
     * @return
     */
    String isPhoneHasRegisted(String phone);

    /**
     * 注册   手机号加密码加昵称
     * @param phone
     * @param nickname
     * @param password
     * @return
     */
    String register(String phone,String nickname,String password);


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    User updateUserInfo(User user);


    /**
     * 更改头像
     * @param userid
     * @param path
     * @return
     */
    String updateHeadIcon(Long userid,String path);



}
