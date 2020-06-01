package lan.qxc.lightserver.service;

import lan.qxc.lightserver.netty.protocol.packet.dongtai_msg_packet.DongtaiMsgPacket;
import lan.qxc.lightserver.vo.DongtaiMsgVO;

import java.util.List;

public interface DongtaiMsgService {


    /**
     * 点赞
     * @param userid
     * @param dtid
     * @return
     */
    String likeDongtai(Long userid,Long dtid);

    /**
     * 评论
     * @param userid
     * @param dtidw
     * @param body
     * @return
     */
    String commonDongtai(Long userid,Long dtidw,String body);

    /**
     * 转发
     * @param userid
     * @param dtid
     * @return
     */
    String transmitDongtai(Long userid,Long dtid);


    /**
     * 获得某用户的最新的几条动态消息
     * @param userid
     * @return
     */
    List<DongtaiMsgPacket> getUserDtMsgsNewList(Long userid);


    /**
     * 从msgid开始向以前找某用户自己的动态几条消息
     * @param userid
     * @param msgid
     * @return
     */
    List<DongtaiMsgPacket> getUserDtMsgsBackList(Long userid,Long msgid);

    /**
     * 获得某用户未读的动态消息数量
     * @param userid
     * @return
     */
    int getMsgNotReadNumByUserid(Long userid);

    /**
     * 设置某用户动态消息已读
     * @param userid
     * @return
     */
    int setDongtaiMsgHadRead(Long userid);


}
