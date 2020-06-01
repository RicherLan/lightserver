package lan.qxc.lightserver.controller;


import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.common.api.DongtaiAPI;
import lan.qxc.lightserver.netty.protocol.packet.dongtai_msg_packet.DongtaiMsgPacket;
import lan.qxc.lightserver.service.impl.DongtaiMsgServiceImpl;
import lan.qxc.lightserver.service.impl.DongtaiServiceImpl;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import lan.qxc.lightserver.vo.DongtailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DongtaiMsgController {

    @Autowired
    DongtaiServiceImpl dongtaiService;
    @Autowired
    DongtaiMsgServiceImpl dongtaiMsgService;
    @Autowired
    UserServiceImpl userService;


    //点赞
    @PostMapping(DongtaiAPI.LIKE_DONGTAI)
    private Result likeDongtai(@RequestParam("dtid") Long dtid,@RequestParam("userid") Long userid){
        System.out.println("likeDongtai...");

        String res = dongtaiMsgService.likeDongtai(userid,dtid);

        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(res);
    }


    @PostMapping(DongtaiAPI.GET_DONGTAI_MSG_BACK_LIST)
    private Result getDongtai_Msg_Back_List(@RequestParam("userid") Long userid,@RequestParam("dtid") Long dtid){
        System.out.println("getDongtai_Msg_Back_List...");
        List<DongtaiMsgPacket> dongtaiMsgPackets = dongtaiMsgService.getUserDtMsgsBackList(userid,dtid);
        if(dongtaiMsgPackets!=null){
            return ResultGenerator.genSuccessResult(dongtaiMsgPackets);
        }

        return ResultGenerator.genFailResult(ServiceResultEnum.ERROR.getResult());
    }

    @PostMapping(DongtaiAPI.GET_DONGTAI_MSG_NEW_LIST)
    private Result getDongtai_Msg_new_List(@RequestParam("userid") Long userid){
        System.out.println("getDongtai_Msg_new_List...");
        List<DongtaiMsgPacket> dongtaiMsgPackets = dongtaiMsgService.getUserDtMsgsNewList(userid);

        if(dongtaiMsgPackets!=null){
            return ResultGenerator.genSuccessResult(dongtaiMsgPackets);
        }

        return ResultGenerator.genFailResult(ServiceResultEnum.ERROR.getResult());
    }

    @PostMapping(DongtaiAPI.GET_DONGTAI_MSG_NOT_READ_NUM)
    private Result getDongtai_Msg_Not_Read_Num(@RequestParam("userid") Long userid){
        System.out.println("getDongtai_Msg_Not_Read_Num...");
       int num = dongtaiMsgService.getMsgNotReadNumByUserid(userid);

       return ResultGenerator.genSuccessResult(num+"");

    }

    @PostMapping(DongtaiAPI.SET_DONGTAI_MSG_AHD_READ)
    private Result set_Dongtai_Msg_Had_Read(@RequestParam("userid") Long userid){
        System.out.println("set_Dongtai_Msg_Had_Read...");
        int num = dongtaiMsgService.setDongtaiMsgHadRead(userid);

        return ResultGenerator.genSuccessResult(num+"");

    }

}
