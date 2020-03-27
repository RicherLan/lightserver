package lan.qxc.lightserver.netty.handler.net_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lan.qxc.lightserver.netty.util.SessionUtil;

import java.util.concurrent.TimeUnit;

/*
    死亡处理器
 */
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 35;

    public IMIdleStateHandler(){
        super(READER_IDLE_TIME,0,0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        SessionUtil.unBindSession(ctx.channel());
        ctx.channel().close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开Idle");
        SessionUtil.unBindSession(ctx.channel());
    }
}
