package lan.qxc.lightserver.service;

import lan.qxc.lightserver.entity.Guanzhu;
import lan.qxc.lightserver.vo.FriendVO;
import lan.qxc.lightserver.vo.UserVO;

import java.util.List;

public interface GuanzhuService {

    /**
     * 关注某人
     * @param userid
     * @param gzuid
     * @return
     */
    String guanzhu(Long userid,Long gzuid);

    /**
     * 修改备注
     * @param userid
     * @param gzuid
     * @param remarkname
     * @return
     */
    String updateRemarkName(Long userid,Long gzuid,String remarkname);

    /**
     * 取消关注
     * @param userid
     * @param gzuid
     * @return
     */
    String deleteGuanzhu(Long userid,Long gzuid);

    /**
     * 获得我关注的
     * @param userid
     * @return
     */
    List<FriendVO> getMyGuanzhu(Long userid);

    /**
     * 获得关注我的用户信息
     * @param userid
     * @return
     */
    List<FriendVO> getUsersGuanzhuMe(Long userid);


    /**
     * 获得我的好友列表
     * @param userid
     * @return
     */
    List<FriendVO> getFriendsByUserid(Long userid);


    /**
     * 获得我的关注的数量
     * @param userid
     * @return
     */
    Integer getMyGuanzhuNum(Long userid);

    /**
     * 获得关注我的人的数量
     * @param userid
     * @return
     */
    Integer getGuanzhuMeNum(Long userid);

    /**
     * 获得我的好友的数量
     * @param userid
     * @return
     */
    Integer getMyFriendNum(Long userid);

}
