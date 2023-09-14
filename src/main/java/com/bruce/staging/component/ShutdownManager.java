package com.bruce.staging.component;

import com.bruce.staging.common.utils.AsyncUtils;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShutdownManager {

    @PreDestroy
    public void destroy() {
        shutdownAsyncManager();
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAsyncManager() {
        try {
            log.info("*******************关闭后台任务任务线程池*******************");
            AsyncUtils.getInstance().shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
