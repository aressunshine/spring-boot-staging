package com.bruce.staging.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @description: 反射工具类
 * @author: zoubaolu
 * @date: 2022年05月20日 8:58
 */
@Slf4j
public class ReflectionUtils {

    /**
     * 反射获取所有的字段
     *
     * @param t
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    public static <T> List<Field> getAllFields(T t) {
        List<Field> fieldList = new ArrayList<>(200);

        //获取当前对象的所有属性字段，包括private修饰的字段
        Class<?> currentClass = t.getClass();
        Field[] declaredFields = currentClass.getDeclaredFields();
        fieldList.addAll(Arrays.asList(declaredFields));

        // 获取所有父类的字段， 父类中的字段需要逐级获取
        Class clazzSuper = currentClass.getSuperclass();
        // 如果父类不是object，表明其继承的有其他类。 逐级获取所有父类的字段
        while (clazzSuper != Object.class) {
            fieldList.addAll(Arrays.asList(clazzSuper.getDeclaredFields()));
            clazzSuper = clazzSuper.getSuperclass();
        }
        return fieldList;
    }

    /**
     * 反射获取所有的字段属性和值
     *
     * @param t
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    public static <T> Map<String, Object> getAllFieldAndValue(T t) {
        List<Field> fieldList = getAllFields(t);
        Map<String, Object> map = new LinkedHashMap<>();
        for (Field field : fieldList) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (fieldName.equals("serialVersionUID")) {
                    continue;
                }
                Object fieldValue = field.get(t);
                map.put(fieldName, fieldValue);
            } catch (IllegalAccessException e) {
                log.error("获取字段属性失败", e);
            }
        }
        return map;
    }
}
