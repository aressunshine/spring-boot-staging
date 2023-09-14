package com.bruce.staging.component;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@DependsOn("redisConfig")
@Component
public class RedisTools {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 查询prefix开头的所有keys
     */
    public <T> Set<T> keys(String prefix) {
        return redisTemplate.keys(prefix.concat("*"));
    }

    /**
     * 查询prefix开头的所有values
     */
    public <T> List<T> values(String prefix) {
        Set<T> keys = redisTemplate.keys(prefix.concat("*"));
        List<T> list = redisTemplate.opsForValue().multiGet(keys);
        return list;
    }

    /**
     * 保存属性
     */
    public <T> void set(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存属性，时间粒度：秒
     */
    public <T> void set(final String key, final T value, final long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }


    /**
     * 获取属性
     */
    public <T> T get(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除属性
     */
    public Boolean del(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 批量删除属性
     */
    public Long del(final List<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 设置过期时间
     */
    public Boolean expire(final String key, final long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 获取过期时间，时间粒度：秒
     */
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断是否有该属性
     */
    public Boolean hasKey(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 按delta递增
     */
    public Long incr(final String key, final long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 按delta递减
     */
    public Long decr(final String key, final long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 向Hash结构中放入一个属性
     */
    public <T> void hSet(final String key, final String hashKey, final T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 向Hash结构中放入一个属性
     */
    public <T> Boolean hSet(final String key, final String hashKey, final T value, final long seconds) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, seconds);
    }

    /**
     * 直接设置整个Hash结构
     */
    public <T> void hSetAll(final String key, final Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 直接设置整个Hash结构
     */
    public <T> Boolean hSetAll(final String key, final Map<String, T> map, final long seconds) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, seconds);
    }

    /**
     * 获取Hash结构中的属性
     */
    public <T> T hGet(final String key, final String hashKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hashKey);
    }

    /**
     * 直接获取整个Hash结构
     */
    public <T> Map<String, T> hGetAll(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除Hash结构中的属性
     */
    public void hDel(final String key, final Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 判断Hash结构中是否有该属性
     */
    public Boolean hHasKey(final String key, final String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * Hash结构中属性递增
     */
    public Long hIncr(final String key, final String hashKey, final Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * Hash结构中属性递减
     */
    public Long hDecr(final String key, final String hashKey, final Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    /**
     * 获取Set结构
     */
    public <T> Set<T> sMembers(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 向Set结构中添加属性
     */
    public <T> Long sAdd(final String key, final T... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 向Set结构中添加属性
     */
    public <T> Long sAdd(final long seconds, final String key, final T... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, seconds);
        return count;
    }

    /**
     * 是否为Set中的属性
     */
    public Boolean sIsMember(final String key, final Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取Set结构的长度
     */
    public Long sSize(final String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 删除Set结构中的属性
     */
    public Long sRemove(final String key, final Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 获取List结构中的属性
     */
    public List<Object> lRange(final String key, final long start, final long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取List结构的长度
     */
    public Long lSize(final String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 根据索引获取List中的属性
     */
    public Object lIndex(final String key, final long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 向List结构中添加属性
     */
    public <T> Long lPush(final String key, final T value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 向List结构中添加属性
     */
    public <T> Long lPush(final String key, final T value, final long seconds) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, seconds);
        return index;
    }

    /**
     * 向List结构中批量添加属性
     */
    public <T> Long lPushAll(final String key, final T... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 向List结构中批量添加属性
     */
    public <T> Long lPushAll(final String key, final long seconds, final T... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, seconds);
        return count;
    }

    /**
     * 从List结构中移除属性
     */
    public Long lRemove(final String key, final long count, final Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 获取锁
     */
    public Boolean getLock(final String key, final String value, final long seconds) {
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(seconds));
        return lock;
    }

    /**
     * 释放锁
     *
     * @param key
     * @param value
     * @return java.lang.Long
     */
    public Long releaseLock(final String key, final String value) {
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        Long releaseStatus = (Long) redisTemplate.execute(redisScript, Collections.singletonList(key), value);
        return releaseStatus;
    }
}
