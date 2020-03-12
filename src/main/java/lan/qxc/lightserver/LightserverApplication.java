package lan.qxc.lightserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("lan.qxc.lightserver.dao")
@SpringBootApplication
public class LightserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(LightserverApplication.class, args);
    }

}
