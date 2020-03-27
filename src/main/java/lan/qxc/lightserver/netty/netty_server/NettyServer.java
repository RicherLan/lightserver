package lan.qxc.lightserver.netty.netty_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lan.qxc.lightserver.netty.codec.PacketCodecHandler;
import lan.qxc.lightserver.netty.codec.Spliter;
import lan.qxc.lightserver.netty.handler.net_handler.AuthHandler;
import lan.qxc.lightserver.netty.handler.net_handler.HeartBeatRequestHandler;
import lan.qxc.lightserver.netty.handler.net_handler.IMIdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
    netty 配置类
 */
@Slf4j
@Component
public class NettyServer {

    private static class NettyServerHolder{
        private static NettyServer instance = new NettyServer();
    }

    public static NettyServer getInstance(){
        return NettyServerHolder.instance;
    }

    private NettyServer() {
    }

    NioEventLoopGroup bossGroup;
    NioEventLoopGroup workerGroup;


    public void start(int port){
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)                //tcp请求连接存放   队列大小   即可存放三次握手中的连接数
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("连接到来......");

                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);

                        //注册

                        //登录

                        ch.pipeline().addLast(AuthHandler.INSTANCE);

                        //其他业务处理器
                    }
                });

        bind(serverBootstrap, port);
    }


    private static void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": netty server   端口[" + port + "]绑定成功!");
            } else {
                System.err.println("netty server   端口[" + port + "]绑定失败!");
            }
        });
    }


    public void shutdown(){
        log.info("Shutdown Netty Server...");
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        log.info("Shutdown Netty Server Success");
    }

}
