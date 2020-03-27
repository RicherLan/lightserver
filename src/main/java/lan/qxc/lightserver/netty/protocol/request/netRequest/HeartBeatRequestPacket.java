package lan.qxc.lightserver.netty.protocol.request.netRequest;

import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.netty.protocol.command.Command;

/*
    心跳数据包
 */
public class HeartBeatRequestPacket extends Packet {

    @Override
    public int getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }

}
