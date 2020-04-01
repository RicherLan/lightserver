package lan.qxc.lightserver.netty.protocol;

import io.netty.buffer.ByteBuf;
import lan.qxc.lightserver.netty.protocol.packet.chat_msg.SingleChatMsgPacket;
import lan.qxc.lightserver.netty.protocol.packet.friend_msg_packet.FriendMsgPacket;
import lan.qxc.lightserver.netty.protocol.request.chat_request.SingleChatRequestPacket;
import lan.qxc.lightserver.netty.protocol.request.netRequest.HeartBeatRequestPacket;
import lan.qxc.lightserver.netty.protocol.request.user_request.LoginRequestPacket;
import lan.qxc.lightserver.netty.protocol.response.netResponse.HeartBeatResponsePacket;
import lan.qxc.lightserver.netty.protocol.response.user_response.LoginResponsePacket;
import lan.qxc.lightserver.netty.serialize.Serializer;
import lan.qxc.lightserver.netty.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

import static lan.qxc.lightserver.netty.protocol.command.Command.*;


/*
 * 一个数据包的格式为
 * 魔数(4字节)   版本号(1字节)  序列化算法(1字节)  指令(4字节)  数据长度(4字节)  数据(n字节)
 *
 */
public class PacketCodec {

    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Integer,Class<? extends Packet>> packetTypeMap;

    private final Map<Byte, Serializer> serializerMap;


    private PacketCodec(){
        packetTypeMap = new HashMap<>();

        packetTypeMap.put(HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);
        packetTypeMap.put(HEARTBEAT_RESPONSE, HeartBeatResponsePacket.class);

        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);

        packetTypeMap.put(Friend_GUANZHU_MSG, FriendMsgPacket.class);

        packetTypeMap.put(SINGLE_CHAT_MSG_REQUEST, SingleChatRequestPacket.class);

        packetTypeMap.put(SINGLE_CHAT_MSG_PACKET, SingleChatMsgPacket.class);




        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    private Serializer getSerializer(Byte serializeAlgorithm){
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(int command) {

        return packetTypeMap.get(command);
    }


    public void encode(ByteBuf byteBuf,Packet packet){
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeInt(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        int command = byteBuf.readInt();


        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {

            return serializer.deserialize(requestType, bytes);
        }

        return null;

    }

}
