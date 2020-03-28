package lan.qxc.lightserver.netty.sender.message_sender;

import io.netty.channel.Channel;
import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.netty.protocol.packet.friend_msg_packet.FriendMsgPacket;
import lan.qxc.lightserver.netty.util.SessionUtil;

public class MsgSender {

    public void send(Long userid , Packet packet){
        Channel channel = SessionUtil.getChannel(userid);
        if(channel!=null&&SessionUtil.hasLogin(channel)){
            channel.writeAndFlush(packet);
        }
    }

}
