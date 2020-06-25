package com.ruben.utils.other.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 高效判断数组中是否包含某个元素--- 收藏夹 商品id
 *
 * 显然，使用一个简单的循环方法比使用任何集合都更加高效。
 * 许多开发人员为了方便，都使用第一种方法，但是他的效率也相对较低。
 * 因为将数组压入Collection类型中，首先要将数组元素遍历一遍，然后再使用集合类做其他操作。
 */
public class ArrayUtil {
    // 使用List
    public static boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    // 使用Set
    public static boolean useSet(String[] arr, String targetValue) {
        Set<String> set = new HashSet<String>(Arrays.asList(arr));
        return set.contains(targetValue);
    }

    // 使用循环判断
    public static boolean useLoop(String[] arr, String targetValue) {
        for (String s : arr) {
            if (s.equals(targetValue))
                return true;
        }
        return false;
    }

    // 查找有序数组中是否包含某个值的用法
    public static boolean useArraysBinarySearch(String[] arr, String targetValue) {
        int a = Arrays.binarySearch(arr, targetValue);
        return a > 0;
    }

    // 使用ArrayUtils
    public static boolean useArrayUtils(String[] arr, String targetValue) {
        return ArrayUtils.contains(arr, targetValue);
    }

    //Test测试
    public static void main(String[] args) {
        String[] arr = new String[]{"CD", "BC", "EF", "DE", "AB", "JK"};

        // use list
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            useList(arr, "A");
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("useList:" + duration / 100);

        // use set
        long startTime2 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            useSet(arr, "A");
        }
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;
        System.out.println("useSet:" + duration / 100);

        // use loop
        long startTime3 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            useLoop(arr, "A");
        }
        long endTime3 = System.nanoTime();
        long duration3 = endTime3 - startTime3;
        System.out.println("useLoop:" + duration / 100);

        // use Arrays.binarySearch()
        long startTime4 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            useArraysBinarySearch(arr, "A");
        }
        long endTime4 = System.nanoTime();
        long duration4 = endTime4 - startTime4;
        System.out.println("useArraysBinarySearch:" + duration / 100);
    }

}
