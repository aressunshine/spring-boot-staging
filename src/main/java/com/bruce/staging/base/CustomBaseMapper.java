package com.bruce.staging.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义BaseMapper接口
 *
 * @author Brucezou
 */
public interface CustomBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入
     *
     * @param insertList 插入列表
     */
    void insertBatchSomeColumn(@Param("list") List<T> insertList);
}
