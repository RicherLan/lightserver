package lan.qxc.lightserver.controller;


import lan.qxc.lightserver.common.Constants;
import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.common.api.UserAPI;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(UserAPI.LOGIN)
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

    @PostMapping(UserAPI.REGISTER_IS_PHONE_HAS_EXIST)
    public Result isPhoneHasExist(@RequestParam("phone") String phone){

        System.out.println("isPhoneHasExist......");
        String resStr = userService.isPhoneHasRegisted(phone);
        System.out.println(resStr);
        if(resStr.equals(ServiceResultEnum.PHONE_HAS_EXIST.getResult())){
            return ResultGenerator.genFailResult(ServiceResultEnum.PHONE_HAS_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping(UserAPI.REGISTER_REGIST_USER_URL)
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


    @PostMapping(UserAPI.UPDATE_USER_INFO)
    public Result updateUserIngo(@RequestBody User user){

        System.out.println("updateUserIngo....");
//        System.out.println(user.toString());
        User newUser = userService.updateUserInfo(user);
        if(newUser!=null){
            return ResultGenerator.genSuccessResult(newUser);
        }

        return ResultGenerator.genFailResult("修改失败");
    }


    @RequestMapping(UserAPI.UPLOAD_USER_ICON)
    public Result uploadUserHeadIcon(@RequestParam("headic")MultipartFile file,@RequestParam("userid") Long userid){

        System.out.println("uploadUserHeadIcon......");
        if(file.isEmpty()){
            return ResultGenerator.genFailResult("error");
        }

        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random random = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(format.format(new Date())).append(random.nextInt(100)).append(suffix);
        String newFileName = tempName.toString();

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(Constants.HEADIC_FILE_UPLOAD_PATH+newFileName);
            Files.write(path,bytes);

            String res = userService.updateHeadIcon(userid,Constants.HEADIC_FILE_ACCESS_PATH+newFileName);
            if(res.equals(ServiceResultEnum.SUCCESS.getResult())){
                return ResultGenerator.genSuccessResult(Constants.HEADIC_FILE_ACCESS_PATH+newFileName);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("error");
        }

        return ResultGenerator.genFailResult("error");
    }


}
