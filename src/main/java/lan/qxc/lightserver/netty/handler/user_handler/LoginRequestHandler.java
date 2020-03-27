package lan.qxc.lightserver.netty.handler.user_handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lan.qxc.lightserver.common.ServiceResultEnum;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.netty.protocol.request.user_request.LoginRequestPacket;
import lan.qxc.lightserver.netty.protocol.response.user_response.LoginResponsePacket;
import lan.qxc.lightserver.netty.session.Session;
import lan.qxc.lightserver.netty.util.SessionUtil;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.util.FixedThreadPool;
import lan.qxc.lightserver.util.MD5Util;
import lan.qxc.lightserver.util.ResultGenerator;
import lan.qxc.lightserver.vo.PersonalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@ChannelHandler.Sharable
@Component
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static LoginRequestHandler INSTANCE;

    @Autowired
    UserServiceImpl userService;

    @PostConstruct
    public void init(){
        INSTANCE = this;
    }



    protected LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {

        System.out.println("LoginRequestHandler.....");
        FixedThreadPool.threadPool.submit(new Runnable() {
            @Override
            public void run() {
                LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
                loginResponsePacket.setVersion(loginRequestPacket.getVersion());


                String phone = loginRequestPacket.getPhone();
                String password = loginRequestPacket.getPassword();

                String rs = ServiceResultEnum.SUCCESS.getResult();

                if(StringUtils.isEmpty(phone)){
                    rs = (ServiceResultEnum.LOGIN_PHONE_NULL.getResult());
                }
                if(StringUtils.isEmpty(password)){
                    rs = (ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
                }

                PersonalInfo personalInfo = userService.login(phone,password);


                if(personalInfo!=null){
                    personalInfo.setPassword(password);
                    SessionUtil.bindSession(new Session(personalInfo.getUserid()), ctx.channel());
                }else{
                    rs = ServiceResultEnum.LOGIN_FAIL.getResult();
                }

                loginResponsePacket.setRs(rs);
                loginResponsePacket.setPersonalInfo(personalInfo);

                System.out.println(loginResponsePacket.toString());
                // 登录响应
                ctx.writeAndFlush(loginResponsePacket);
            }
        });

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("断开Login");
        SessionUtil.unBindSession(ctx.channel());
    }
}
