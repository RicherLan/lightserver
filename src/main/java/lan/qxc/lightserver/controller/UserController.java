package lan.qxc.lightserver.controller;


import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @PostMapping("/user/getinfo")
    public Result getUserInfo(){

        return ResultGenerator.genSuccessResult();

    }


}
