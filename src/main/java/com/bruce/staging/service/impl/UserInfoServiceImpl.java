package com.bruce.staging.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bruce.staging.common.event.UserInfoEvent;
import com.bruce.staging.domain.UserInfo;
import com.bruce.staging.service.UserInfoService;
import com.bruce.staging.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author zoubaolu
 * @description 针对表【user_info】的数据库操作Service实现
 * @createDate 2023-09-13 23:41:24
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    int a = 0;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Retryable(recover = "recoverTest", retryFor = {RuntimeException.class}, maxAttempts = 5,
            backoff = @Backoff(delay = 1000, multiplier = 2))
    public String test() {
        a++;
        System.out.println(a + " - " + System.currentTimeMillis());
        if (a < 10) {
            throw new RuntimeException("未满足条件");
        }
        return "执行成功";
    }

    @Recover
    public String recoverTest(RuntimeException e) {
        return "回调方法-" + e.getMessage();
    }

    @Override
    public void publicEvent(UserInfo userInfo) {
        log.info("发送消息，内容为：{}", JSON.toJSONString(new UserInfoEvent(userInfo)));
        applicationEventPublisher.publishEvent(new UserInfoEvent(userInfo));
    }
}




