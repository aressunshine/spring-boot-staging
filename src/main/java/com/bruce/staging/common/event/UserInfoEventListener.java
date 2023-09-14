package com.bruce.staging.common.event;


import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserInfoEventListener {

    @EventListener
    @Async("threadPoolTaskExecutor")
    public void subscribeEvent(UserInfoEvent userInfoEvent) {
        log.info("接收到消息了，内容为：{}", JSON.toJSONString(userInfoEvent));
    }
}
