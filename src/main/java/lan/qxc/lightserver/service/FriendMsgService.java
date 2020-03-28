package lan.qxc.lightserver.service;

import lan.qxc.lightserver.entity.FriendMsg;
import lan.qxc.lightserver.netty.sender.message.FriendMsgVO;

import java.util.List;

public interface FriendMsgService {

    /**
     * 插入消息
     * @param friendMsg
     * @return
     */
    String insert(FriendMsg friendMsg);

    /**
     * 获得某人未读消息
     * @param userid
     * @return
     */
    List<FriendMsgVO> getUserNotReadMsg(Long userid);

    /**
     * 设置某消息以读
     * @param msgid
     * @return
     */
    String setMsgHasRead(Long msgid);

    /**
     * 根据消息id修改消息
     * @param msgid
     * @param friendMsg
     * @return
     */
    String updateMsgByid(Long msgid,FriendMsg friendMsg);



}
