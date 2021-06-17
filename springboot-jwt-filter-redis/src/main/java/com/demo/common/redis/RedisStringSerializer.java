package com.demo.common.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <p> @Title RedisStringSerializer
 * <p> @Description Redis字符串序列化
 *
 * @author ACGkaka
 * @date 2021/6/17 11:44
 */
public class RedisStringSerializer implements RedisSerializer<String> {

    private final Charset charset = StandardCharsets.UTF_8;

    private String prefix;

    public RedisStringSerializer(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public byte[] serialize(String key) throws SerializationException {
        key = prefix + key;
        return key == null ? null : key.getBytes(charset);
    }

    @Override
    public String deserialize(byte[] key) throws SerializationException {
        String saveKey = new String(key, charset);
        int indexOf = saveKey.indexOf(prefix);
        if (indexOf >= 0) {
            if (prefix.equals(saveKey.substring(0, prefix.length()))) {
                saveKey = saveKey.substring(prefix.length());
            }
        }
        return saveKey;
    }
}
