package lan.qxc.lightserver.controller;


import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.common.api.DongtaiAPI;
import lan.qxc.lightserver.service.impl.DongtaiMsgServiceImpl;
import lan.qxc.lightserver.service.impl.DongtaiServiceImpl;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
