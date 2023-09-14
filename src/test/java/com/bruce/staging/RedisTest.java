package com.bruce.staging;

import com.bruce.staging.component.RedisTools;
import com.bruce.staging.domain.UserInfo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

    public static final String KEY = "user_info";

    @Resource
    private RedisTools redisTools;

    @Test
    void setValue() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("BruceZou");
        userInfo.setNickname("bruce");
        userInfo.setAge((byte) 25);
        userInfo.setTelephone("13800000000");
        userInfo.setEmail("123@bruce.com");
        userInfo.setSex((byte) 0);
        redisTools.set(KEY, userInfo);
    }

    @Test
    void getValue() {
        UserInfo userInfo = redisTools.get(KEY);
        System.out.println(userInfo);
    }
}
