package lan.qxc.lightserver.controller;


import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.common.api.GuanzhuAPI;
import lan.qxc.lightserver.service.GuanzhuService;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import lan.qxc.lightserver.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuanzhuController {

    @Autowired
    GuanzhuService guanzhuService;

    /**
     * 关注
     * @param userid
     * @param gzuid
     * @return
     */
    @PostMapping(GuanzhuAPI.GUANZHU)
    public Result guanzhu(@RequestParam("userid") Long userid,@RequestParam("gzuid") Long gzuid){

        System.out.println("guanzhu......");
        String res = guanzhuService.guanzhu(userid,gzuid);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(res);
    }

    /**
     * 取消关注
     * @param userid
     * @param gzuid
     * @return
     */
    @PostMapping(GuanzhuAPI.DEL_GUANZHU)
    public Result del_guanzhu(@RequestParam("userid") Long userid,@RequestParam("gzuid") Long gzuid){

        System.out.println("del_guanzhu......");
        String res = guanzhuService.deleteGuanzhu(userid,gzuid);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(res);
    }


    @PostMapping(GuanzhuAPI.UPDATE_REMARK_NAME)
    public Result updateRemarkName(@RequestParam("userid") Long userid,@RequestParam("gzuid") Long gzuid,
                                   @RequestParam("remark") String remark){

        System.out.println("updateRemarkName......");
        String res = guanzhuService.updateRemarkName(userid,gzuid,remark);
        if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(res);
    }


    @PostMapping(GuanzhuAPI.GET_MY_GUANZHU)
    public Result getMyGuanzhu(@RequestParam("userid") Long userid){

        System.out.println("getMyGuanzhu......");

        List<UserVO> userVOS = guanzhuService.getMyGuanzhu(userid);


        return ResultGenerator.genSuccessResult(userVOS);

    }

    @PostMapping(GuanzhuAPI.GET_USER_GUANZHU_ME)
    public Result getUsersGuanzhuMe(@RequestParam("userid") Long userid){

        System.out.println("getUsersGuanzhuMe......");

        List<UserVO> userVOS = guanzhuService.getUsersGuanzhuMe(userid);


        return ResultGenerator.genSuccessResult(userVOS);

    }


    @PostMapping(GuanzhuAPI.GET_FRIEND_LIST)
    public Result getFriendsByUserid(@RequestParam("userid") Long userid){

        System.out.println("getFriendsByUserid......");

        List<UserVO> userVOS = guanzhuService.getFriendsByUserid(userid);

        return ResultGenerator.genSuccessResult(userVOS);

    }

    @PostMapping(GuanzhuAPI.GET_MY_GUANZHU_NUM)
    public Result getMyGuanzhuNum(@RequestParam("userid") Long userid){

        System.out.println("getMyGuanzhuNum......");
        Integer num = guanzhuService.getMyGuanzhuNum(userid);
        return ResultGenerator.genSuccessResult(num+"");
    }

    @PostMapping(GuanzhuAPI.GET_GUANZHU_ME_NUM)
    public Result getGuanzhuMeNum(@RequestParam("userid") Long userid){

        System.out.println("getGuanzhuMeNum......");
        Integer num = guanzhuService.getGuanzhuMeNum(userid);
        return ResultGenerator.genSuccessResult(num+"");
    }

    @PostMapping(GuanzhuAPI.GET_MY_FRIEND_NUM)
    public Result getMyFriendNum(@RequestParam("userid") Long userid){

        System.out.println("getMyFriendNum......");
        Integer num = guanzhuService.getMyFriendNum(userid);
        return ResultGenerator.genSuccessResult(num+"");
    }


}
