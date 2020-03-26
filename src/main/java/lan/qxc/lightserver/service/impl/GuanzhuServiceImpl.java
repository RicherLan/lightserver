package lan.qxc.lightserver.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.dao.GuanzhuMapper;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.Guanzhu;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.service.GuanzhuService;
import lan.qxc.lightserver.util.BeanUtil;
import lan.qxc.lightserver.vo.GuanzhuVO;
import lan.qxc.lightserver.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GuanzhuServiceImpl implements GuanzhuService {

    @Resource
    GuanzhuMapper guanzhuMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public String guanzhu(Long userid, Long gzuid) {

        Byte is_deleted = guanzhuMapper.isGuanzhuByUid(userid,gzuid);

        if(is_deleted!=null){
            if(is_deleted==0){
                return ServiceResultEnum.SUCCESS.getResult();
            }else{
                Guanzhu gz = new Guanzhu();
                gz.setUserid(userid);
                gz.setGzuid(gzuid);
                gz.setIs_deleted(new Byte("0"));

                if(guanzhuMapper.updateByUseridSelective(gz)>0){
                    return ServiceResultEnum.SUCCESS.getResult();
                }
                return ServiceResultEnum.ERROR.getResult();
            }
        }

        Guanzhu gz = new Guanzhu();
        gz.setUserid(userid);
        gz.setGzuid(gzuid);
        if(guanzhuMapper.insertSelective(gz)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String updateRemarkName(Long userid, Long gzuid, String remarkname) {
        Guanzhu gz = new Guanzhu();
        gz.setUserid(userid);
        gz.setGzuid(gzuid);
        gz.setRemarkname(remarkname);
        if(guanzhuMapper.updateByUseridSelective(gz)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String deleteGuanzhu(Long userid, Long gzuid) {

        Byte is_deleted = guanzhuMapper.isGuanzhuByUid(userid,gzuid);

        if(is_deleted!=null){
            if(is_deleted==0){
                if(guanzhuMapper.deleteByUserid(userid,gzuid)>0){
                    return ServiceResultEnum.SUCCESS.getResult();
                }
                return ServiceResultEnum.ERROR.getResult();

            }else{
                return ServiceResultEnum.SUCCESS.getResult();
            }
        }

        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public List<UserVO> getMyGuanzhu(Long userid) {

        List<Guanzhu> guanzhus = guanzhuMapper.getMyGuanzhu(userid);
        List<UserVO> userVOS = new ArrayList<>();
        if(guanzhus==null||guanzhus.size()==0){
            return userVOS;
        }

        for(Guanzhu guanzhu : guanzhus){
            Long uid = guanzhu.getGzuid();
            User user = userMapper.selectByUserid(uid);

            UserVO userVO = new UserVO();
            BeanUtil.copyProperties(user,userVO);
            userVO.setRemark(guanzhu.getRemarkname());
            userVO.setIs_blacked(guanzhu.getIs_blacked());
            userVOS.add(userVO);
        }

        return userVOS;
    }

    @Override
    public List<UserVO> getUsersGuanzhuMe(Long userid) {
        List<Long> guanzhus = guanzhuMapper.getUsersGuanzhuMe(userid);
        List<UserVO> userVOS = new ArrayList<>();
        if(guanzhus==null||guanzhus.size()==0){
            return userVOS;
        }

        for(Long uid : guanzhus){
            User user = userMapper.selectByUserid(uid);
            UserVO userVO = new UserVO();
            BeanUtil.copyProperties(user,userVO);
            userVOS.add(userVO);
        }

        return userVOS;

    }

    //我关注的人少  但是关注我的可能数量特别大   所以先拿到我关注的用户  然后判断该用户是否关注我了
    @Override
    public List<UserVO> getFriendsByUserid(Long userid) {

        List<UserVO> userVOS = getMyGuanzhu(userid);
        List<UserVO> del = new ArrayList<>();
        for(UserVO userVO : userVOS){
            Long uid = userVO.getUserid();
            Byte b = guanzhuMapper.isGuanzhuByUid(uid,userid);
            if(b==null||b!=0){
                del.add(userVO);
            }
        }
        for(UserVO userVO : del){
            userVOS.remove(userVO);
        }
        del = null;
        return userVOS;
    }

    @Override
    public Integer getMyGuanzhuNum(Long userid) {
        return guanzhuMapper.getMyGuanzhuNum(userid);
    }

    @Override
    public Integer getGuanzhuMeNum(Long userid) {
        return guanzhuMapper.getGuanzhuMeNum(userid);
    }

    @Override
    public Integer getMyFriendNum(Long userid) {
        List<UserVO> list = getFriendsByUserid(userid);
        if(list==null){
            return 0;
        }
        return list.size();
    }
}
