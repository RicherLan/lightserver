package lan.qxc.lightserver.dao;

import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DongtaiMapper {

    /**
     * 获得某动态基础信息
     * @param dtid
     * @return
     */
    Dongtai getDongtaiByDtid(Long dtid);


    /**
     * 删除某动态
     * @param dtid
     *
     * @return
     */
    int deleteByDtid(Long dtid);


    /**
     * 插入
     * @param dongtai
     * @return
     */
    int insert(Dongtai dongtai);

    int insertSelective(Dongtai dongtai);


    /**
     * 更新动态
     * @param dongtai
     * @return
     */
    int updateByDtidSelective(Dongtai dongtai);

    int updateByDtid(Dongtai dongtai);


    /**
     * 获得比dtid大的动态的数量   即dongtai表中dtid下面的数据的数量
     * @param dtid
     * @return
     */
    int getBiggerCountOfDtid(Long dtid);

    /**
     * 从当前动态所在的位置的下一个位置  pos开始向前找limit条返回
     *
     * @param pos
     * @param limit
     * @return
     */
    List<Dongtai> findDongtaiBackList(@Param("pos") int pos, @Param("limit") int limit);

    /**
     * 获得最新的num条数据
     * @param num
     * @return
     */
    List<Dongtai> findDongtaiNewList(@Param("num") int num);


}
