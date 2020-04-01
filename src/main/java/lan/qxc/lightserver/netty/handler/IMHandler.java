package lan.qxc.lightserver.netty.handler;



import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lan.qxc.lightserver.netty.handler.chat_handler.SingleChatMsgRequestHandler;
import lan.qxc.lightserver.netty.protocol.Packet;

import java.util.HashMap;
import java.util.Map;

import static lan.qxc.lightserver.netty.protocol.command.Command.SINGLE_CHAT_MSG_REQUEST;


@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {
    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Integer, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();
        
        handlerMap.put(SINGLE_CHAT_MSG_REQUEST, SingleChatMsgRequestHandler.INSTANCE);
        
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand())
        			.channelRead(ctx, packet);
        
    }
}
