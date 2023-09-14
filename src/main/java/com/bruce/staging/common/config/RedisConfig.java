package com.bruce.staging.common.config;

import com.bruce.staging.common.utils.FastJson2RedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description: Redis配置类
 * @author: zoubaolu
 * @date: 2022年05月13日 15:10
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * writer RedisTemplate Bean
     *
     * @param connectionFactory
     * @return template
     */
    @Bean
    public RedisTemplate<Object, ?> redisTemplate(final RedisConnectionFactory connectionFactory) {
        return createTemplate(connectionFactory);
    }

    /**
     * 创建redisTemplate.
     *
     * @param connectionFactory 连接工厂
     * @return redisTemplate
     */
    public static RedisTemplate<Object, ?> createTemplate(final RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        FastJson2RedisSerializer serializer = new FastJson2RedisSerializer(Object.class);
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.setDefaultSerializer(serializer);
        template.setStringSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }
}