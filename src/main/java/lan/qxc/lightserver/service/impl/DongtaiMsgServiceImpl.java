package lan.qxc.lightserver.service.impl;

import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.dao.DongtaiMapper;
import lan.qxc.lightserver.dao.DongtaiMsgMapper;
import lan.qxc.lightserver.dao.UserMapper;
import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.entity.DongtaiMsg;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.netty.sender.DongtaiMsgSender;
import lan.qxc.lightserver.netty.sender.FriendMsgSender;
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
    DongtaiMapper dongtaiMapper;

    @Resource
    UserMapper userMapper;


    @Override
    public String likeDongtai(Long userid, Long dtid) {
        DongtaiMsg dongtaiMsg = new DongtaiMsg();
        dongtaiMsg.setUserid(userid);
        dongtaiMsg.setDtid(dtid);
        dongtaiMsg.setMsgtype(new Byte("1"));

        Dongtai dongtai = dongtaiMapper.getDongtaiByDtid(dtid);
        Long touid = dongtai.getUserid();

        DongtaiMsgSender.getInstance().sendMsg(touid,dongtai,dongtaiMsg);

        //是否之前点赞过
        DongtaiMsg msg = dongtaiMsgMapper.getDTMsgByDtidAUidAMsgtype(userid,new Byte("1"),dtid);
        int res = 1;

        //之前点过赞
        if(msg!=null){
            //删除
            res = dongtaiMsgMapper.deleteMsg(msg.getMsgid());
            //删除失败
            if(res<1){
                return ServiceResultEnum.ERROR.getResult();
            }else{
                return ServiceResultEnum.SUCCESS.getResult();
            }
        }

        res = dongtaiMsgMapper.insertSelective(dongtaiMsg);

        if(res>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String commonDongtai(Long userid, Long dtid, String body) {

        DongtaiMsg dongtaiMsg = new DongtaiMsg();
        dongtaiMsg.setUserid(userid);
        dongtaiMsg.setDtid(dtid);
        dongtaiMsg.setBody(body);
        dongtaiMsg.setMsgtype(new Byte("2"));

        int res = dongtaiMsgMapper.insertSelective(dongtaiMsg);

        if(res>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();

    }

    @Override
    public String transmitDongtai(Long userid, Long dtid) {

        DongtaiMsg dongtaiMsg = new DongtaiMsg();
        dongtaiMsg.setUserid(userid);
        dongtaiMsg.setDtid(dtid);
        dongtaiMsg.setMsgtype(new Byte("3"));

        int res = dongtaiMsgMapper.insertSelective(dongtaiMsg);

        if(res>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.ERROR.getResult();

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
