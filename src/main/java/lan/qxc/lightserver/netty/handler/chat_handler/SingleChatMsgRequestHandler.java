package lan.qxc.lightserver.netty.handler.chat_handler;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lan.qxc.lightserver.netty.protocol.packet.chat_msg.SingleChatMsgPacket;
import lan.qxc.lightserver.netty.protocol.request.chat_request.SingleChatRequestPacket;
import lan.qxc.lightserver.netty.util.SessionUtil;
import lan.qxc.lightserver.service.impl.SingleChatServiceImpl;
import lan.qxc.lightserver.service.impl.UserServiceImpl;
import lan.qxc.lightserver.util.FixedThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * 单人聊天消息
 */
@ChannelHandler.Sharable
@Component
public class SingleChatMsgRequestHandler extends SimpleChannelInboundHandler<SingleChatRequestPacket> {

    public static SingleChatMsgRequestHandler INSTANCE;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    SingleChatServiceImpl singleChatService;

    @PostConstruct
    public void init(){
        INSTANCE = this;
    }

    protected SingleChatMsgRequestHandler() {
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleChatRequestPacket singleChatRequestPacket) throws Exception {

        System.out.println("SingleChatRequestHandler.....");
        FixedThreadPool.threadPool.submit(new Runnable() {
            @Override
            public void run() {

                String res = singleChatService.insert(singleChatRequestPacket.getSingleChatMsg());


                Channel channel = SessionUtil.getChannel(singleChatRequestPacket.getSingleChatMsg().getReceiveUid());
                if(SessionUtil.hasLogin(channel)){
                    SingleChatMsgPacket singleChatMsgPacket = new SingleChatMsgPacket();
                    channel.writeAndFlush(singleChatMsgPacket);
                }

            }
        });

    }




}
