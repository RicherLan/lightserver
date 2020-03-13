package lan.qxc.lightserver.controller;


import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public Result login(@RequestParam("phone")String phone,@RequestParam("password") String password){
        System.out.println("login......");
        if(StringUtils.isEmpty(phone)){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PHONE_NULL.getResult());
        }
        if(StringUtils.isEmpty(password)){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }

        User user = userService.login(phone,password);
        if(user!=null){
            return ResultGenerator.genSuccessResult(user);
        }

        return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_FAIL.getResult());

    }

    @PostMapping("/register/is_phone_exist")
    public Result isPhoneHasExist(@RequestParam("phone") String phone){

        System.out.println("isPhoneHasExist......");
        String resStr = userService.isPhoneHasRegisted(phone);
        System.out.println(resStr);
        if(resStr.equals(ServiceResultEnum.PHONE_HAS_EXIST.getResult())){
            return ResultGenerator.genFailResult(ServiceResultEnum.PHONE_HAS_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/register/regist_user")
    public Result register(@RequestParam("phone") String phone,
                           @RequestParam("username") String username,
                           @RequestParam("password")String password){

        System.out.println("registeruser.......");
        System.out.println(phone+"  "+username+"  "+password);

        if(StringUtils.isEmpty(phone)){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PHONE_NULL.getResult());
        }
        if(StringUtils.isEmpty(username)){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }
        if(StringUtils.isEmpty(password)){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }


        String result_str = userService.register(phone,username,password);
        System.out.println(result_str);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result_str)){
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(result_str);
    }


}
