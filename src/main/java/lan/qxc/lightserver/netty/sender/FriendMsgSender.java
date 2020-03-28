package lan.qxc.lightserver.netty.sender;


import io.netty.channel.Channel;
import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.netty.protocol.packet.friend_msg_packet.FriendMsgPacket;
import lan.qxc.lightserver.netty.sender.message_sender.MsgSender;
import lan.qxc.lightserver.netty.util.SessionUtil;
import lan.qxc.lightserver.netty.sender.message.FriendMsgVO;

import java.util.HashMap;
import java.util.Map;

public class FriendMsgSender extends MsgSender {

    private static class FriendMsgSenderHolder{
        private static FriendMsgSender instance = new FriendMsgSender();
    }

    public static FriendMsgSender getInstance(){
        return FriendMsgSenderHolder.instance;
    }


    public void sendMsg(Long userid, FriendMsgVO friendMsgVO){

        FriendMsgPacket packet = new FriendMsgPacket();
        packet.setVersion(new Byte("1"));
        packet.setVersion(new Byte("1"));
        packet.setFriendMsgVO(friendMsgVO);

        send(userid,packet);

    }


}
