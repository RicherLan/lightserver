package lan.qxc.lightserver.controller;

import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.common.api.FriendMsgAPI;
import lan.qxc.lightserver.netty.sender.message.FriendMsgVO;
import lan.qxc.lightserver.service.impl.FriendMsgServiceImpl;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendMsgController {

    @Autowired
    FriendMsgServiceImpl friendMsgService;


    @PostMapping(FriendMsgAPI.SET_MSG_HAD_READ)
    public Result setMsgHadRead(@RequestParam("msgid") Long msgid){

        System.out.println("setMsgHadRead......");
        String res = friendMsgService.setMsgHasRead(msgid);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(res);
    }


    @PostMapping(FriendMsgAPI.GET_USER_FRIEND_MSG_NOT_READ_MSG)
    public Result getFriendMsgNotReadMsg(@RequestParam("userid") Long userid){
        System.out.println("getFriendMsgNotReadMsg......");

        List<FriendMsgVO> friendMsgVOS = friendMsgService.getUserNotReadFriendMsg(userid);
        System.out.println(friendMsgVOS.toString());
        return ResultGenerator.genSuccessResult(friendMsgVOS);

    }

}
