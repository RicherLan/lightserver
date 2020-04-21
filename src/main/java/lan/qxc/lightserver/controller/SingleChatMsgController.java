package lan.qxc.lightserver.controller;


import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.common.api.SingleChatMsgAPI;
import lan.qxc.lightserver.netty.sender.message.SingleChatMsg;
import lan.qxc.lightserver.service.impl.SingleChatServiceImpl;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SingleChatMsgController {

    @Autowired
    SingleChatServiceImpl singleChatService;


    @PostMapping(SingleChatMsgAPI.DELETE_MSG_BY_MSGID)
    public Result deleteMsgByMsgid(@RequestParam("msgid") Long msgid){
        System.out.println("deleteMsgByMsgid...");
        String res = singleChatService.deleteMsgByMsgid(msgid);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(res);
    }


    @PostMapping(SingleChatMsgAPI.GET_ALL_MSG_NOT_READ_BY_UID)
    public Result getMsgNotReadOfUid(@RequestParam("userid") Long userid){
        System.out.println("getMsgNotReadOfUid.....");
        List<SingleChatMsg> singleChatMsgs = singleChatService.getMsgNotReadOfUid(userid);
        if(singleChatMsgs!=null){

            for(SingleChatMsg singleChatMsg : singleChatMsgs){
                System.out.println(singleChatMsg.getMsgid()+"   11111111111111");
            }

            return ResultGenerator.genSuccessResult(singleChatMsgs);
        }
        return ResultGenerator.genFailResult("error");
    }


    @PostMapping(SingleChatMsgAPI.GET_MSG_BY_MSGID)
    public Result getMsgByMsgid(@RequestParam("msgid") Long msgid){
        System.out.println("getMsgByMsgid...");
        SingleChatMsg singleChatMsg = singleChatService.getMsgByMsgid(msgid);
        if(singleChatMsg!=null){
            return ResultGenerator.genSuccessResult(singleChatMsg);
        }
        return ResultGenerator.genFailResult("error");
    }

    @PostMapping(SingleChatMsgAPI.SET_ALL_MSG_HAD_READ_BY_UID)
    public Result setAllMsgHadReadByuid(@RequestParam("senduid") Long senduid,@RequestParam("receiveuid") Long receiveid){

        String res = singleChatService.setOldMsgHadRead(senduid,receiveid);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(res);
    }

}
