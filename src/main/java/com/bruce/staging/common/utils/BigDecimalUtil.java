package com.bruce.staging.common.utils;

import java.math.BigDecimal;

/**
 * @description: 小数计算工具类
 * @author: zoubaolu
 * @date: 2022年06月06日 9:09
 */
public class BigDecimalUtil {

    public static void main(String[] args) {
        System.out.println(roundHalfUp(1.23456,2));
    }

    public static BigDecimal roundHalfUp(float v1, int scale) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        //保留两位小数且四舍五入
        b1 = b1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return b1;
    }

    public static BigDecimal roundHalfUp(double v1, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        //保留两位小数且四舍五入
        b1 = b1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return b1;
    }

    public static BigDecimal doubleAdd(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal doubleSub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal doubleMul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal doubleDiv(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        // 保留小数点后两位 ROUND_HALF_UP = 四舍五入
        return b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP);
    }

    public static int doubleCompareTo(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.compareTo(b2);
    }

    public static BigDecimal floatAdd(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal floatSub(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal floatMul(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal floatDiv(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        // 保留小数点后两位 ROUND_HALF_UP = 四舍五入
        return b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP);
    }

    public static int floatCompareTo(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.compareTo(b2);
    }
}
