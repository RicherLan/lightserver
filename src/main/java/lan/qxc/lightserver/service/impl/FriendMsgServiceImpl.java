package lan.qxc.lightserver.service.impl;

import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.dao.FriendMsgMapper;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.FriendMsg;
import lan.qxc.lightserver.service.FriendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendMsgServiceImpl implements FriendMsgService {

    @Resource
    FriendMsgMapper friendMsgMapper;

    @Resource
    UserMapper userMapper;

    @Override
    @Transactional
    public String insert(FriendMsg friendMsg) {

        Long userid = friendMsg.getUserid();
        Long touserid = friendMsg.getTouserid();
        Byte msgtype = friendMsg.getMsgtype();

        friendMsgMapper.setOldOfUseridandType(userid,touserid);

        if(friendMsgMapper.insertSelective(friendMsg)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public List<FriendMsg> getUserNotReadMsg(Long userid) {

        return friendMsgMapper.getUserNotReadMsg(userid);
    }

    @Override
    public String setMsgHasRead(Long msgid) {
        FriendMsg friendMsg = new FriendMsg();
        friendMsg.setId(msgid);
        friendMsg.setReadstate(new Byte("1"));
        if(friendMsgMapper.updateByIdSelective(friendMsg)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String updateMsgByid(Long msgid, FriendMsg friendMsg) {
        friendMsg.setId(msgid);
        if(friendMsgMapper.updateByIdSelective(friendMsg)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }
}
