package com.bruce.staging.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter.Feature;

import java.nio.charset.Charset;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @description: Redis使用FastJson序列化
 * @author: zoubaolu
 * @date: 2022年05月13日 15:10
 */
public class FastJson2RedisSerializer<T> implements RedisSerializer<T> {

    /**
     * clazz.
     */
    private final Class<T> clazz;

    /**
     * 构造器.
     *
     * @param clazz cls
     */
    public FastJson2RedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * 序列化
     *
     * @param t object to serialize.
     * @return byte[]
     * @throws SerializationException e
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        Map.Entry<String, T> entity = new SimpleEntry<>(t.getClass().getName(), t);
        return JSON.toJSONString(entity, Feature.WriteClassName).getBytes(Charset.defaultCharset());
    }

    /**
     * 反序列化.
     *
     * @param bytes object binary representation
     * @return es
     * @throws SerializationException e
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (Objects.isNull(bytes)) {
            return null;
        }
        String str = new String(bytes, Charset.defaultCharset());
        int index = str.indexOf(":");
        String cls = str.substring(2, index - 1);
        String obj = str.substring(index + 1, str.length() - 1);
        return JSON.parseObject(obj, clazz, JSONReader.autoTypeFilter(cls), JSONReader.Feature.SupportClassForName);
    }


}
