package lan.qxc.lightserver.service.impl;

import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.dao.GuanzhuMapper;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.Guanzhu;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.service.GuanzhuService;
import lan.qxc.lightserver.service.UserService;

import lan.qxc.lightserver.util.BeanUtil;
import lan.qxc.lightserver.util.MD5Util;
import lan.qxc.lightserver.vo.FriendVO;
import lan.qxc.lightserver.vo.PersonalInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private GuanzhuMapper guanzhuMapper;
    @Resource
    private GuanzhuService guanzhuService;

    @Override
    public PersonalInfo login(String phone, String password) {

        User user = userMapper.selectByPhone(phone);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        if(user!=null&&user.getPhone().equals(phone)&&user.getPassword().equals(passwordMD5)){
            PersonalInfo personalInfo = new PersonalInfo();
            BeanUtil.copyProperties(user,personalInfo);
            Long userid = user.getUserid();

            int my_guanzhu_num = guanzhuMapper.getMyGuanzhuNum(userid);
            int guanzhu_me_num = guanzhuMapper.getGuanzhuMeNum(userid);

            int my_friend_num = guanzhuService.getMyFriendNum(userid);

            personalInfo.setMy_guanzhu_num(my_guanzhu_num);
            personalInfo.setGuanzhu_me_num(guanzhu_me_num);
            personalInfo.setMy_friend_num(my_friend_num);

            return personalInfo;
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

    @Override
    public String updateHeadIcon(Long userid, String path) {

        if(userMapper.updateHeadIcByUserid(userid,path)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String updatePassword(Long userid, String oldpassword, String newpassword) {

        String newpasswordMD5 = MD5Util.MD5Encode(newpassword, "UTF-8");
        String oldpasswordMD5 = MD5Util.MD5Encode(oldpassword, "UTF-8");


        User user = userMapper.selectByUserid(userid);
        if(user==null){
            return ServiceResultEnum.ERROR.getResult();
        }
        if(!user.getPassword().equals(oldpasswordMD5)){
            return ServiceResultEnum.OLD_PASSWORD_ERROR.getResult();
        }

        if(userMapper.updatePassByUserid(userid,oldpasswordMD5,newpasswordMD5)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();
    }


    @Override
    public FriendVO getUserDetailInifO(Long userid, Long uid) {
        Guanzhu guanzhu = guanzhuMapper.getGuanzhuInfo(userid,uid);
        System.out.println(guanzhu);
            User user = userMapper.selectByUserid(uid);
            FriendVO userVO = new FriendVO();
            BeanUtil.copyProperties(user,userVO);
            if(guanzhu!=null){
                userVO.setRemark(guanzhu.getRemarkname());
                userVO.setIs_blacked(guanzhu.getIs_blacked());
            }

            userVO.setGuanzhu_type(guanzhuService.getUToURelation(userid,uid));

        return userVO;
    }
}
