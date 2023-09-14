package com.bruce.staging.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.bruce.staging.common.interceptor.DataChangeInterceptor;
import com.bruce.staging.component.InsertBatchSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: MyBatisPlus配置类
 * @author: zoubaolu
 * @date: 2022年08月31日 14:26
 */
@Configuration
@MapperScan("com.bruce.staging.mapper")
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new DataChangeInterceptor());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public InsertBatchSqlInjector easySqlInjector() {
        return new InsertBatchSqlInjector();
    }
}
