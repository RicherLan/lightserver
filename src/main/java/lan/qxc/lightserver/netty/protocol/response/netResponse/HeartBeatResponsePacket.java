package lan.qxc.lightserver.netty.protocol.response.netResponse;

import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.netty.protocol.command.Command;

/*
    心跳回复包
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public int getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
