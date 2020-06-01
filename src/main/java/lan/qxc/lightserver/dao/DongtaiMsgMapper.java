package lan.qxc.lightserver.dao;

import lan.qxc.lightserver.entity.DongtaiMsg;
import lan.qxc.lightserver.vo.DongtaiMsgVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 某用户是否对某动态进行过某种操作
     * @param userid
     * @param msgtype
     * @param dtid
     * @return
     */
    DongtaiMsg getDTMsgByDtidAUidAMsgtype(@Param("userid") Long userid, @Param("msgtype") Byte msgtype,@Param("dtid") long dtid);


    /**
     * 根据动态消息id获得动态消息
     * @param msgid
     * @return
     */
    DongtaiMsg getDTMsgBymsgId(Long msgid);



    /**
     * 获得比msgid大的动态的数量   即dongtaimsg表中msgid下面的数据的数量
     * @param msgid
     * @return
     */
    int getBiggerCountOfmsgid(Long msgid);


    /**
     * 获得某用户的最新的几条动态消息
     * @param userid
     * @param num
     * @return
     */
    List<DongtaiMsg> getUserDtMsgsNewList(@Param("userid") Long userid, @Param("num") int num);


    /**
     * 从msgid开始向以前找某用户自己的动态几条消息
     * @param userid
     * @return
     */
    List<DongtaiMsg> getUserDtMsgsBackList(@Param("userid") Long userid,@Param("pos") int pos,@Param("limit") int limit);


    /**
     * 获得某动态的点赞数
     * @param dtid
     * @return
     */
    int getLikeNumByDtid(Long dtid);

    /**
     * 获得某用户未读的动态消息数量
     * @param userid
     * @return
     */
    int getMsgNotReadNumByUserid(Long userid);

    /**
     * 设置动态消息已读
     * @param userid
     * @return
     */
    int setDongtaiMsgHadRead(Long userid);


}
