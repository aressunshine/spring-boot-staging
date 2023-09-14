package com.bruce.staging;

import java.time.LocalDateTime;

import com.bruce.staging.domain.UserInfo;
import com.bruce.staging.service.UserInfoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserInfoTest {

    @Resource
    private UserInfoService userInfoService;

    @Test
    void saveOneUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("BruceZou");
        userInfo.setNickname("bruce");
        userInfo.setAge((byte) 25);
        userInfo.setTelephone("13800000000");
        userInfo.setEmail("123@bruce.com");
        userInfo.setSex((byte) 0);
        userInfoService.save(userInfo);
    }

    @Test
    void batchSaveUser() {

    }

    @Test
    void updateUser() {

    }

    @Test
    void batchUpdateUser() {

    }
}
