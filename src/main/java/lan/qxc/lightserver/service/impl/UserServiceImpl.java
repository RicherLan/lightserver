package lan.qxc.lightserver.service.impl;

import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.service.UserService;

import lan.qxc.lightserver.util.MD5Util;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String phone, String password) {

        User user = userMapper.selectByPhone(phone);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        if(user!=null&&user.getPhone().equals(phone)&&user.getPassword().equals(passwordMD5)){
            return user;
        }

        return null;
    }

    @Override
    public String isPhoneHasRegisted(String phone) {

        User user = userMapper.selectByPhone(phone);
        if(user!=null){
            return ServiceResultEnum.PHONE_HAS_EXIST.getResult();
        }
        return ServiceResultEnum.SUCCESS.getResult();
    }

    @Override
    public String register(String phone, String nickname, String password) {

        if(userMapper.selectByPhone(phone)!=null){
            return ServiceResultEnum.PHONE_HAS_EXIST.getResult();
        }

        User user = new User();
        user.setPhone(phone);
        user.setUsername(nickname);

        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        user.setPassword(passwordMD5);

        if(userMapper.insertSelective(user)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public User updateUserInfo(User user) {
        if(userMapper.updateByUseridSelective(user)>0){
            return userMapper.selectByUserid(user.getUserid());
        }
        return null;
    }
}
