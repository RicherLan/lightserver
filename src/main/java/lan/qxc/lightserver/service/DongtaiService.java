package lan.qxc.lightserver.service;

import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.vo.DongtailVO;

import java.util.List;

public interface DongtaiService {


    /**
     * 发表动态
     * @param dongtai
     * @return
     */
    String addDongtai(Dongtai dongtai);

    /**
     * 删除动态
     * @param dtid
     * @return
     */
    String deleteDongtai(Long dtid);


    /**
     * 修改动态
     * @param dongtai
     * @return
     */
    DongtailVO updateDongtai(Dongtai dongtai);


    /**
     * 从dtid的动态向前找
     * @param dtid
     * @return
     */
    List<DongtailVO> getDongtaiBackList(Long dtid);


    /**
     * 获得最新的几条数据
     * @return
     */
    List<DongtailVO> getDongtaiNewList();


}
