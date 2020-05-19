package lan.qxc.lightserver.service.impl;

import lan.qxc.lightserver.dao.DongtaiMsgMapper;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.entity.DongtaiMsg;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.service.DongtaiMsgService;
import lan.qxc.lightserver.util.BeanUtil;
import lan.qxc.lightserver.vo.DongtaiMsgVO;
import lan.qxc.lightserver.vo.DongtailVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DongtaiMsgServiceImpl implements DongtaiMsgService {

    @Resource
    DongtaiMsgMapper dongtaiMsgMapper;

    @Resource
    UserMapper userMapper;


    @Override
    public int likeDongtai(Long userid, Long dtid) {
        DongtaiMsg dongtaiMsg = new DongtaiMsg();
        dongtaiMsg.setUserid(userid);
        dongtaiMsg.setDtid(dtid);
        dongtaiMsg.setMsgtype(new Byte("1"));

        int res = dongtaiMsgMapper.insertSelective(dongtaiMsg);

        return res;
    }

    @Override
    public int commonDongtai(Long userid, Long dtid, String body) {

        DongtaiMsg dongtaiMsg = new DongtaiMsg();
        dongtaiMsg.setUserid(userid);
        dongtaiMsg.setDtid(dtid);
        dongtaiMsg.setBody(body);
        dongtaiMsg.setMsgtype(new Byte("2"));

        int res = dongtaiMsgMapper.insertSelective(dongtaiMsg);

        return res;
    }

    @Override
    public int transmitDongtai(Long userid, Long dtid) {

        DongtaiMsg dongtaiMsg = new DongtaiMsg();
        dongtaiMsg.setUserid(userid);
        dongtaiMsg.setDtid(dtid);
        dongtaiMsg.setMsgtype(new Byte("3"));

        int res = dongtaiMsgMapper.insertSelective(dongtaiMsg);

        return res;
    }

    @Override
    public List<DongtaiMsgVO> getUserDtMsgsNewList(Long userid) {

        List<DongtaiMsg> dongtaiMsgs = dongtaiMsgMapper.getUserDtMsgsNewList(userid,10);
        List<DongtaiMsgVO> dongtaiMsgVOS = new ArrayList<>();

        if(dongtaiMsgs!=null&&dongtaiMsgs.size()!=0){
            for( DongtaiMsg dongtaiMsg : dongtaiMsgs){

                User user = userMapper.selectByUserid(dongtaiMsg.getUserid());
                DongtaiMsgVO dongtaiMsgVO = new DongtaiMsgVO();
                BeanUtil.copyProperties(dongtaiMsg,dongtaiMsgVO);
                BeanUtil.copyProperties(user,dongtaiMsgVO);
                dongtaiMsgVOS.add(dongtaiMsgVO);
            }
        }

        return dongtaiMsgVOS;

    }

    @Override
    public List<DongtaiMsgVO> getUserDtMsgsBackList(Long userid, Long msgid) {
        int count = dongtaiMsgMapper.getBiggerCountOfmsgid(msgid);
        int begin = count+1;

        List<DongtaiMsg> dongtaiMsgs = dongtaiMsgMapper.getUserDtMsgsNewList(userid,10);
        List<DongtaiMsgVO> dongtaiMsgVOS = new ArrayList<>();

        if(dongtaiMsgs!=null&&dongtaiMsgs.size()!=0){
            for( DongtaiMsg dongtaiMsg : dongtaiMsgs){

                User user = userMapper.selectByUserid(dongtaiMsg.getUserid());
                DongtaiMsgVO dongtaiMsgVO = new DongtaiMsgVO();
                BeanUtil.copyProperties(dongtaiMsg,dongtaiMsgVO);
                BeanUtil.copyProperties(user,dongtaiMsgVO);
                dongtaiMsgVOS.add(dongtaiMsgVO);
            }
        }

        return dongtaiMsgVOS;
    }
}
