package lan.qxc.lightserver.service.impl;


import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.dao.DongtaiMapper;
import lan.qxc.lightserver.dao.GuanzhuMapper;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.service.DongtaiService;
import lan.qxc.lightserver.service.GuanzhuService;
import lan.qxc.lightserver.util.BeanUtil;
import lan.qxc.lightserver.vo.DongtailVO;
import lan.qxc.lightserver.vo.FriendVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DongtaiServiceImpl implements DongtaiService {

    @Resource
    DongtaiMapper dongtaiMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    GuanzhuMapper guanzhuMapper;

    @Resource
    GuanzhuService guanzhuService;


    @Override
    public String addDongtai(Dongtai dongtai) {

        if(dongtaiMapper.insertSelective(dongtai)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String deleteDongtai(Long dtid) {
        if(dongtaiMapper.deleteByDtid(dtid)>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public DongtailVO updateDongtai(Dongtai dongtai) {
        DongtailVO dongtailVO = null;
        if(dongtaiMapper.updateByDtidSelective(dongtai)>0){
            Dongtai dongtai1 = dongtaiMapper.getDongtaiByDtid(dongtai.getDtid());
            dongtailVO = new DongtailVO();
            BeanUtil.copyProperties(dongtai1,dongtailVO);

            User user = userMapper.selectByUserid(dongtai.getUserid());
            BeanUtil.copyProperties(user,dongtailVO);
        }

        return dongtailVO;
    }

    //一下子刷新12条数据
    @Override
    public List<DongtailVO> getDongtaiBackList(Long userid,Long dtid) {
        int count = dongtaiMapper.getBiggerCountOfDtid(dtid);
        int begin = count+1;
        List<Dongtai> dongtais = dongtaiMapper.findDongtaiBackList(begin,10);
        List<DongtailVO> dongtailVOS = new ArrayList<>();

        if(dongtais!=null&&dongtais.size()!=0){

            for(Dongtai dongtai : dongtais){
                User user = userMapper.selectByUserid(dongtai.getUserid());
                DongtailVO dongtailVO = new DongtailVO();
                BeanUtil.copyProperties(dongtai,dongtailVO);
                BeanUtil.copyProperties(user,dongtailVO);


                dongtailVO.setGuanzhu_type(guanzhuService.getUToURelation(userid,dongtai.getUserid()));


                dongtailVOS.add(dongtailVO);
            }
        }

        return dongtailVOS;
    }

    @Override
    public List<DongtailVO> getDongtaiNewList(Long userid) {

        List<Dongtai> dongtais = dongtaiMapper.findDongtaiNewList(10);
        List<DongtailVO> dongtailVOS = new ArrayList<>();

        if(dongtais!=null&&dongtais.size()!=0){

            for(Dongtai dongtai : dongtais){
                User user = userMapper.selectByUserid(dongtai.getUserid());
                DongtailVO dongtailVO = new DongtailVO();
                BeanUtil.copyProperties(dongtai,dongtailVO);
                BeanUtil.copyProperties(user,dongtailVO);
                dongtailVO.setGuanzhu_type(guanzhuService.getUToURelation(userid,dongtai.getUserid()));
                dongtailVOS.add(dongtailVO);
            }
        }

        return dongtailVOS;
    }


    //指定用户
    //一下子刷新12条数据
    @Override
    public List<DongtailVO> getUserDongtaiBackList(Long uid,Long userid,Long dtid) {
        int count = dongtaiMapper.getUserBiggerCountOfDtid(userid,dtid);
        int begin = count+1;
        List<Dongtai> dongtais = dongtaiMapper.findUserDongtaiBackList(userid,begin,10);
        List<DongtailVO> dongtailVOS = new ArrayList<>();

        if(dongtais!=null&&dongtais.size()!=0){

            for(Dongtai dongtai : dongtais){
                User user = userMapper.selectByUserid(dongtai.getUserid());
                DongtailVO dongtailVO = new DongtailVO();
                BeanUtil.copyProperties(dongtai,dongtailVO);
                BeanUtil.copyProperties(user,dongtailVO);
                dongtailVO.setGuanzhu_type(guanzhuService.getUToURelation(uid,userid));
                dongtailVOS.add(dongtailVO);
            }
        }

        return dongtailVOS;
    }

    @Override
    public List<DongtailVO> getUserDongtaiNewList(Long uid,Long userid) {

        List<Dongtai> dongtais = dongtaiMapper.findUserDongtaiNewList(userid,10);
        List<DongtailVO> dongtailVOS = new ArrayList<>();

        if(dongtais!=null&&dongtais.size()!=0){
            for(Dongtai dongtai : dongtais){
                System.out.println(dongtai.getUserid());
                User user = userMapper.selectByUserid(dongtai.getUserid());
                DongtailVO dongtailVO = new DongtailVO();
                BeanUtil.copyProperties(dongtai,dongtailVO);
                BeanUtil.copyProperties(user,dongtailVO);
                dongtailVO.setGuanzhu_type(guanzhuService.getUToURelation(uid,userid));
                dongtailVOS.add(dongtailVO);
            }
        }

        return dongtailVOS;
    }



}
