package lan.qxc.lightserver.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.dao.GuanzhuMapper;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.FriendMsg;
import lan.qxc.lightserver.entity.Guanzhu;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.netty.sender.FriendMsgSender;
import lan.qxc.lightserver.netty.sender.message.FriendMsgVO;
import lan.qxc.lightserver.service.GuanzhuService;
import lan.qxc.lightserver.util.BeanUtil;
import lan.qxc.lightserver.vo.FriendVO;
import lan.qxc.lightserver.vo.GuanzhuVO;
import lan.qxc.lightserver.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    FriendMsgServiceImpl friendMsgService;
    @Resource
    GuanzhuService guanzhuService;

    @Override
    @Transactional
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

                    FriendMsg friendMsg = new FriendMsg();
                    friendMsg.setUserid(userid);
                    friendMsg.setTouserid(gzuid);
                    friendMsg.setMsgtype(new Byte("1"));

                    User user = userMapper.selectByUserid(userid);
                    FriendMsgVO friendMsgVO = new FriendMsgVO();
                    BeanUtil.copyProperties(friendMsg,friendMsgVO);
                    BeanUtil.copyProperties(user,friendMsgVO);

                    FriendMsgSender.getInstance().sendMsg(gzuid,friendMsgVO);

                    return friendMsgService.insert(friendMsg);

                }
                return ServiceResultEnum.ERROR.getResult();
            }
        }

        Guanzhu gz = new Guanzhu();
        gz.setUserid(userid);
        gz.setGzuid(gzuid);
        if(guanzhuMapper.insertSelective(gz)>0){

            FriendMsg friendMsg = new FriendMsg();
            friendMsg.setUserid(userid);
            friendMsg.setTouserid(gzuid);
            friendMsg.setMsgtype(new Byte("1"));

            User user = userMapper.selectByUserid(userid);
            FriendMsgVO friendMsgVO = new FriendMsgVO();
            BeanUtil.copyProperties(friendMsg,friendMsgVO);
            BeanUtil.copyProperties(user,friendMsgVO);

            FriendMsgSender.getInstance().sendMsg(gzuid,friendMsgVO);

            return friendMsgService.insert(friendMsg);
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
    @Transactional
    public String deleteGuanzhu(Long userid, Long gzuid) {

        Byte is_deleted = guanzhuMapper.isGuanzhuByUid(userid,gzuid);

        if(is_deleted!=null){
            if(is_deleted==0){
                if(guanzhuMapper.deleteByUserid(userid,gzuid)>0){

                    FriendMsg friendMsg = new FriendMsg();
                    friendMsg.setUserid(userid);
                    friendMsg.setTouserid(gzuid);
                    friendMsg.setMsgtype(new Byte("2"));


                    User user = userMapper.selectByUserid(userid);
                    FriendMsgVO friendMsgVO = new FriendMsgVO();
                    BeanUtil.copyProperties(friendMsg,friendMsgVO);
                    BeanUtil.copyProperties(user,friendMsgVO);
                    FriendMsgSender.getInstance().sendMsg(gzuid,friendMsgVO);

                    return friendMsgService.insert(friendMsg);

                }
                return ServiceResultEnum.ERROR.getResult();

            }else{
                return ServiceResultEnum.SUCCESS.getResult();
            }
        }

        return ServiceResultEnum.ERROR.getResult();
    }


    @Override
    public List<FriendVO> getMyGuanzhu(Long userid) {

        List<Guanzhu> guanzhus = guanzhuMapper.getMyGuanzhu(userid);
        List<FriendVO> userVOS = new ArrayList<>();
        if(guanzhus==null||guanzhus.size()==0){
            return userVOS;
        }

        for(Guanzhu guanzhu : guanzhus){
            Long uid = guanzhu.getGzuid();
            User user = userMapper.selectByUserid(uid);

            FriendVO userVO = new FriendVO();
            BeanUtil.copyProperties(user,userVO);
            userVO.setRemark(guanzhu.getRemarkname());
            userVO.setIs_blacked(guanzhu.getIs_blacked());
            userVO.setGuanzhu_type(guanzhuService.getUToURelation(userid,uid));
            userVOS.add(userVO);
        }

        return userVOS;
    }

    @Override
    public List<FriendVO> getUsersGuanzhuMe(Long userid) {
        List<Long> myguanzhuUserids = guanzhuMapper.getMyGuanzhuUserids(userid);

        List<Long> guanzhus = guanzhuMapper.getUsersGuanzhuMe(userid);
        List<FriendVO> userVOS = new ArrayList<>();
        if(guanzhus==null||guanzhus.size()==0){
            return userVOS;
        }

        for(Long uid : guanzhus){
            User user = userMapper.selectByUserid(uid);
            FriendVO userVO = new FriendVO();
            BeanUtil.copyProperties(user,userVO);
            userVO.setGuanzhu_type(guanzhuService.getUToURelation(userid,uid));
            userVOS.add(userVO);
        }

        return userVOS;

    }

    //我关注的人少  但是关注我的可能数量特别大   所以先拿到我关注的用户  然后判断该用户是否关注我了
    @Override
    public List<FriendVO> getFriendsByUserid(Long userid) {

        List<FriendVO> userVOS = getMyGuanzhu(userid);
        List<FriendVO> del = new ArrayList<>();
        for(FriendVO userVO : userVOS){
            Long uid = userVO.getUserid();
            Byte b = guanzhuMapper.isGuanzhuByUid(uid,userid);
            if(b==null||b!=0){
                del.add(userVO);
            }
        }
        for(FriendVO userVO : del){
            userVOS.remove(userVO);
        }
        del = null;

        for(FriendVO userVO : userVOS){
            userVO.setGuanzhu_type(0);
        }
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
        List<FriendVO> list = getFriendsByUserid(userid);
        if(list==null){
            return 0;
        }
        return list.size();
    }

    @Override
    public Integer getUToURelation(Long uid1, Long uid2) {

        Byte b1 = guanzhuMapper.isGuanzhuByUid(uid1,uid2);
        Byte b2 = guanzhuMapper.isGuanzhuByUid(uid2,uid1);

        if(b1==null&&b2==null){
            return 4;
        }
        if(b1!=null&&b2!=null){
            return 0;
        }
        if(b1!=null&&b2==null){
            return 1;
        }

        return 2;
    }
}
