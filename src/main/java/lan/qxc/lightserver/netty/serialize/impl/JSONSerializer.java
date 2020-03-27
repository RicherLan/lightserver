package lan.qxc.lightserver.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import lan.qxc.lightserver.netty.serialize.Serializer;
import lan.qxc.lightserver.netty.serialize.SerializerAlgorithm;

public class JSONSerializer implements Serializer {


    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
