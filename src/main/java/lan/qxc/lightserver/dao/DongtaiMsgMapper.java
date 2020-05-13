package lan.qxc.lightserver.dao;

import lan.qxc.lightserver.entity.DongtaiMsg;

public interface DongtaiMsgMapper {

    int insertSelective(DongtaiMsg dongtaiMsg);

    /**
     * 删除
     * @param msgid
     * @return
     */
    int deleteMsg(Long msgid);


    int updateSelectiveByMsgId(DongtaiMsg dongtaiMsg);


    /**
     * 根据动态消息id获得动态消息
     * @param msgid
     * @return
     */
    DongtaiMsg getDTMsgBymsgId(Long msgid);


}
