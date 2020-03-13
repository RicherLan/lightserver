package lan.qxc.lightserver;

import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.test.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("lan.qxc.lightserver.dao")
@SpringBootApplication
public class LightserverApplication {



    public static void main(String[] args) {

        SpringApplication.run(LightserverApplication.class, args);

    }



}
