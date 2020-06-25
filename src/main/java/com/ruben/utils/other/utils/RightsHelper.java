package com.ruben.utils.other.utils;

import java.math.BigInteger;

/**
 * 权限计算帮助类
 * 这个类为BigInteger封装类,基层使用BigInteger实现
 * 主要方法:1>得到2的权的和2>测试是否具有指定编码的权限
 * 使用方法:调用方法1传入menu_id数组得到2的权的和,然后转换为字符串保存到数据库。在验证权限时调用方法2进行权限验证。
 */
public class RightsHelper {
    /**
     * 得到2的权的和
     *
     * @param rights int型权限编码数组
     * @return 2的权的和(可转换为字符保存到数据库)
     */
    public static BigInteger sumRights(int[] rights) {
        BigInteger num = new BigInteger("0");
        for (int i = 0; i < rights.length; i++) {
            num = num.setBit(rights[i]);
        }
        return num;
    }

    /**
     * 得到2的权的和
     *
     * @param rights String型权限编码数组
     * @return 2的权的和(可转换为字符保存到数据库)
     */
    public static BigInteger sumRights(String[] rights) {
        BigInteger num = new BigInteger("0");
        for (int i = 0; i < rights.length; i++) {
            num = num.setBit(Integer.parseInt(rights[i]));
        }
        return num;
    }

    /**
     * 验证权限
     *
     * @param sum          2的权的和
     * @param targetRights 需要验证的数字(权限Id)
     * @return 有权限true, 无权限false
     */
    public static boolean testRights(String sum, int targetRights) {
        if (isEmpty(sum))
            return false;
        return testRights(new BigInteger(sum), targetRights);
    }

    /**
     * 验证权限
     *
     * @param sum          2的权的和
     * @param targetRights 需要验证的数字(权限Id)
     * @return 有权限true, 无权限false
     */
    public static boolean testRights(String sum, String targetRights) {
        if (isEmpty(sum))
            return false;
        return testRights(new BigInteger(sum), Integer.parseInt(targetRights));
        //return testRights(new BigInteger(sum),targetRights); 或
    }

    /**
     * 验证权限(基层调用)
     *
     * @param sum          2的权的和
     * @param targetRights 需要验证的数字(权限Id)
     * @return
     */
    public static boolean testRights(BigInteger sum, int targetRights) {
        return sum.testBit(targetRights);
    }

    /**
     * 验证权限(指定编码)
     *
     * @param sum
     * @param targetRights
     * @return
     */
    public static boolean testRights(BigInteger sum, String targetRights) {
        return testRights(sum, Integer.parseInt(targetRights));
    }

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s) || "null".equals(s);
    }

    //test
    public static void main(String[] args) {
        //得到2的权的和
        BigInteger num = new BigInteger("0");
        num = num.setBit(2);
        num = num.setBit(1);
        System.out.println(num);            // 6
        System.out.println(num.testBit(2)); // true
        System.out.println(num.testBit(1)); // true
        System.out.println(num.testBit(3));    // false
        //工具类
        System.out.println(RightsHelper.testRights("6", 1));    //验证*******
        System.out.println(RightsHelper.testRights("6", 2));    //验证*******
        System.out.println(RightsHelper.testRights("6", 3));    //验证*******
        System.out.println(RightsHelper.testRights("1152921504606846980", 60));    //验证*******
    }

}


