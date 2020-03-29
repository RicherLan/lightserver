package lan.qxc.lightserver.dao;

import lan.qxc.lightserver.entity.FriendMsg;
import lan.qxc.lightserver.entity.Guanzhu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMsgMapper {


    /**
     * @param friendMsg
     * @return
     */
    int insert(FriendMsg friendMsg);

    int insertSelective(FriendMsg friendMsg);


    int updateByIdSelective(FriendMsg friendMsg);

    /**
     * 把某用户对另一个用户的以往的所有操作 设置过期
     * 一般在插入新的消息之前调用一次
     * @param userid
     * @param touserid
     * @return
     */
    int setOldOfUseridandType(@Param("userid") Long userid,@Param("touserid") Long touserid);

    /**
     * 获得某人的未读的消息
     * @param userid
     * @return
     */
    List<FriendMsg> getUserNotReadFriendMsg(Long userid);




}
