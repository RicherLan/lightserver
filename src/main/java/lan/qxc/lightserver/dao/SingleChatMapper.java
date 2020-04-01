package lan.qxc.lightserver.dao;

import lan.qxc.lightserver.netty.sender.message.SingleChatMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SingleChatMapper {

    int insert(SingleChatMsg msg);

    int insertSelective(SingleChatMsg msg);


    int updateByMsgIdSelective(SingleChatMsg msg);


    /**
     * 设置某消息已读
     * @param msgid
     * @return
     */
    int setMsgHadReadByMsgid(Long msgid);

    /**
     * 设置sendUid给receiveUid以往发的所有消息设置已读
     * @param sendUid
     * @param receiveUid
     * @return
     */
    int setOldMsgHadRead(@Param("sendUid") Long sendUid, @Param("receiveUid")Long receiveUid);


    /**
     * 删除某消息
     * @param msgid
     * @return
     */
    int deleteMsgByMsgid(Long msgid);


    /**
     * 获得某消息
     * @param msgid
     * @return
     */
    SingleChatMsg getMsgByMsgid(Long msgid);

    /**
     * 获得某用户所有的未读消息
     * @param receiveUid
     * @return
     */
    List<SingleChatMsg> getMsgNotReadOfUid(Long receiveUid);

    /**
     * 获得receiveUid用户所有的sendUid发的未读消息
     * @return
     */
    List<SingleChatMsg> getMsgNotReadOfSendUid(@Param("sendUid") Long sendUid,@Param("receiveUid")Long receiveUid);



}
