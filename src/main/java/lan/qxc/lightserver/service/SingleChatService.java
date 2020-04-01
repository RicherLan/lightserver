package lan.qxc.lightserver.service;

import lan.qxc.lightserver.netty.sender.message.SingleChatMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SingleChatService {

    String insert(SingleChatMsg msg);

    String updateByMsgId(Long msgid,SingleChatMsg msg);

    /**
     * 设置消息已读
     * @param msgid
     * @return
     */
    String setMsgHadReadByMsgid(Long msgid);

    /**
     * 所有消息已读
     * @param sendUid
     * @param receiveUid
     * @return
     */
    String setOldMsgHadRead(Long sendUid,Long receiveUid);

    /**
     * 删除消息
     * @param msgid
     * @return
     */
    String deleteMsgByMsgid(Long msgid);

    /**
     * 获得消息
     * @param msgid
     * @return
     */
    SingleChatMsg getMsgByMsgid(Long msgid);

    /**
     * 获得某用户所有未读消息
     * @param receiveUid
     * @return
     */
    List<SingleChatMsg> getMsgNotReadOfUid(Long receiveUid);


    /**
     * 获得某用户的所有另一个指定用户发送的未读消息
     * @param sendUid
     * @param receiveUid
     * @return
     */
    List<SingleChatMsg> getMsgNotReadOfSendUid(Long sendUid,Long receiveUid);

}
