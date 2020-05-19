package lan.qxc.lightserver.service;

import lan.qxc.lightserver.vo.DongtaiMsgVO;

import java.util.List;

public interface DongtaiMsgService {


    /**
     * 点赞
     * @param userid
     * @param dtid
     * @return
     */
    int likeDongtai(Long userid,Long dtid);

    /**
     * 评论
     * @param userid
     * @param dtidw
     * @param body
     * @return
     */
    int commonDongtai(Long userid,Long dtidw,String body);

    /**
     * 转发
     * @param userid
     * @param dtid
     * @return
     */
    int transmitDongtai(Long userid,Long dtid);


    /**
     * 获得某用户的最新的几条动态消息
     * @param userid
     * @return
     */
    List<DongtaiMsgVO> getUserDtMsgsNewList(Long userid);


    /**
     * 从msgid开始向以前找某用户自己的动态几条消息
     * @param userid
     * @param msgid
     * @return
     */
    List<DongtaiMsgVO> getUserDtMsgsBackList(Long userid,Long msgid);

}
