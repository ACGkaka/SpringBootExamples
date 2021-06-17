package com.demo.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * <p> @Title RedisObjectSerializer
 * <p> @Description Redis对象序列化
 *
 * <p> 此处定义的序列化操作表示可以序列化所有类的对象，当然，这个对象所在的类一定要实现序列化接口
 *
 * @author ACGkaka
 * @date 2021/6/17 11:44
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {
    // 为了方便进行对象与字节数组的转换，所以应该首先准备出两个转换器
    private Converter<Object, byte[]> serializingConverter = new SerializingConverter();
    private Converter<byte[], Object> deserializingConverter = new DeserializingConverter();

    /**
     * 做一个空数组，不是null
     */
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        // 这个时候没有要序列化的对象出现，所以返回的字节数组应该就是一个空数组
        if (obj == null) {
            return EMPTY_BYTE_ARRAY;
        }
        // 将对象变为字节数组
        return this.serializingConverter.convert(obj);
    }

    @Override
    public Object deserialize(byte[] data) throws SerializationException {
        // 此时没有对象的内容信息
        if (data == null || data.length == 0) {
            return null;
        }
        return this.deserializingConverter.convert(data);
    }

}