package com.bruce.staging.common.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 通用翻页响应结果类
 * @author: zoubaolu
 * @date: 2022年08月31日 14:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage<T> {

    private String code;

    private String msg;

    private Page<T> page;

    public static <T> ResponsePage<T> page(Page<T> page) {
        return new ResponsePage<>("200", "success", page);
    }
}
