package lan.qxc.lightserver.netty.handler.net_handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lan.qxc.lightserver.netty.protocol.request.netRequest.HeartBeatRequestPacket;
import lan.qxc.lightserver.netty.protocol.response.netResponse.HeartBeatResponsePacket;

/*
    心跳
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        System.out.println("ping");
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
