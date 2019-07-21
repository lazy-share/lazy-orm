package com.lazy.orm.util;

/**
 * <p>
 * 字符串工具类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class StringUtil {


    public static String toCame(String sourceStr, String split) {
        if (!hasText(sourceStr)) {
            return sourceStr;
        }
        if (!hasText(split)) {
            split = "_";
        }
        String[] strArr = sourceStr.split(split);
        int notSplit = 2;
        if (strArr.length < notSplit) {
            return strArr[0];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (i == 0) {
                builder.append(strArr[i]);
            } else {
                builder.append(toUpperCaseFirstOne(strArr[i]));
            }
        }
        return builder.toString();
    }

    public static String toUpperCaseFirstOne(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    public static boolean hasText(String cc) {

        return !"".equals(cc) && cc != null;
    }


}
