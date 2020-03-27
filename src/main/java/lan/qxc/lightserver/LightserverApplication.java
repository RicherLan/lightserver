package lan.qxc.lightserver;

import lan.qxc.lightserver.netty.netty_server.NettyServer;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.test.Test;
import lan.qxc.lightserver.util.FixedThreadPool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("lan.qxc.lightserver.dao")
@SpringBootApplication
public class LightserverApplication implements CommandLineRunner {

    @Value("${netty.port}")
    private int port;

    @Autowired
    private NettyServer nettyServer;


    public static void main(String[] args) {

        SpringApplication.run(LightserverApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        FixedThreadPool.startThreadPool();
        nettyServer.start(port);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                nettyServer.shutdown();
                FixedThreadPool.shutdownThreadPool();
            }
        });

    }
}
