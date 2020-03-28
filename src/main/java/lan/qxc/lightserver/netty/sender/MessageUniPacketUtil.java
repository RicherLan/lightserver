package lan.qxc.lightserver.netty.sender;

import lan.qxc.lightserver.entity.FriendMsg;
import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.netty.protocol.packet.friend_msg_packet.FriendMsgPacket;
import lan.qxc.lightserver.netty.sender.message.FriendMsgVO;
import lan.qxc.lightserver.netty.sender.message.Message;

import java.util.HashMap;
import java.util.Map;

public class MessageUniPacketUtil {

    private static class MessageUniPacketUtilHolder{
        private static MessageUniPacketUtil instance = new MessageUniPacketUtil();
    }

    private final Map<Class<? extends Message>,Class<? extends Packet>> messagePacketTypeMap;

    private MessageUniPacketUtil(){
        messagePacketTypeMap = new HashMap<>();
        messagePacketTypeMap.put(FriendMsgVO.class, FriendMsgPacket.class);
    }

    public Class<? extends Packet> getPacketCByMsgC(Class<? extends Message> messageClazz){
        return messagePacketTypeMap.get(messageClazz);
    }


}
