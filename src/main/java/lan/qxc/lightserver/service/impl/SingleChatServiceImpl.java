package lan.qxc.lightserver.service.impl;

import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.dao.SingleChatMapper;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.netty.sender.message.SingleChatMsg;
import lan.qxc.lightserver.service.SingleChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SingleChatServiceImpl implements SingleChatService {

    @Resource
    SingleChatMapper singleChatMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public Long insert(SingleChatMsg msg) {

        if(singleChatMapper.insertSelective(msg)>0){
            return msg.getMsgid();
        }

        return new Long(-1);
    }

    @Override
    public String updateByMsgId(Long msgid, SingleChatMsg msg) {

        msg.setMsgid(msgid);
        if(singleChatMapper.updateByMsgIdSelective(msg)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String setMsgHadReadByMsgid(Long msgid) {
        if(singleChatMapper.setMsgHadReadByMsgid(msgid)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String setOldMsgHadRead(Long sendUid, Long receiveUid) {
        if(singleChatMapper.setOldMsgHadRead(sendUid,receiveUid)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String deleteMsgByMsgid(Long msgid) {
        if(singleChatMapper.deleteMsgByMsgid(msgid)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public SingleChatMsg getMsgByMsgid(Long msgid) {
        SingleChatMsg singleChatMsg = singleChatMapper.getMsgByMsgid(msgid);
        return singleChatMsg;
    }

    @Override
    public List<SingleChatMsg> getMsgNotReadOfUid(Long receiveUid) {
        List<SingleChatMsg> singleChatMsgs = singleChatMapper.getMsgNotReadOfUid(receiveUid);

        Set<Long> userids = new HashSet<>();
        for(SingleChatMsg singleChatMsg : singleChatMsgs){
            userids.add(singleChatMsg.getSendUid());
        }

        for(Long userid : userids){
            User user = userMapper.selectByUserid(userid);

            for(SingleChatMsg singleChatMsg : singleChatMsgs){
                if(singleChatMsg.getSendUid().equals(userid)){
                    singleChatMsg.setSendUername(user.getUsername());
                    singleChatMsg.setSendUicon(user.getIcon());
                }
            }
        }

        return singleChatMsgs;
    }

    @Override
    public List<SingleChatMsg> getMsgNotReadOfSendUid(Long sendUid, Long receiveUid) {
        List<SingleChatMsg> singleChatMsgs = singleChatMapper.getMsgNotReadOfSendUid(sendUid,receiveUid);
        return singleChatMsgs;
    }
}
