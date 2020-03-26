package lan.qxc.lightserver.dao;


import lan.qxc.lightserver.entity.Guanzhu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuanzhuMapper {


    /**
     * 插入  即关注某人
     * @param guanzhu
     * @return
     */
    int insert(Guanzhu guanzhu);

    int insertSelective(Guanzhu guanzhu);


    /**
     * 修改  根据关注id
     * @param guanzhu
     * @return
     */
    int updateByGzidSelective(Guanzhu guanzhu);

    /**
     * 修改  根据两个用户的id
     * @param guanzhu
     * @return
     */
    int updateByUseridSelective(Guanzhu guanzhu);


    /**
     * 删除  根据关注id
     * @param gzid
     * @return
     */
    int deleteByGzid(Long gzid);

    /**
     * 删除  根据两个用户的id
     * @param userid
     * @param gzuid
     * @return
     */
    int deleteByUserid(@Param("userid")Long userid,@Param("gzuid") Long gzuid);

    /**
     * 获得我关注的人
     * @param userid
     * @return
     */
    List<Guanzhu> getMyGuanzhu(Long userid);

    /**
     * 获得谁关注了我   即获得我的粉丝
     * @param userid
     * @return
     */
    List<Long> getUsersGuanzhuMe(Long userid);


}
