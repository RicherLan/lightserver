package lan.qxc.lightserver.netty.protocol;

import io.netty.buffer.ByteBuf;
import lan.qxc.lightserver.netty.serialize.Serializer;
import lan.qxc.lightserver.netty.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

public class PacketCodec {

    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Integer,Class<? extends Packet>> packetTypeMap;

    private final Map<Byte, Serializer> serializerMap;


    private PacketCodec(){
        packetTypeMap = new HashMap<>();
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
