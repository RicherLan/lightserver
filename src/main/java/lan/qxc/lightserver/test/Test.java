package lan.qxc.lightserver.test;

import lan.qxc.lightserver.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class Test {

    @Autowired
    UserServiceImpl userService;

    public void test(){
        String res = userService.register("15054190193","蓝","123456");
        System.out.println(res);



    }


}
