package com.pentair.utils;

import java.util.Random;

/**
 * 数学函数工具
 */
public class MathUtil {

    private static final char[] zeroArray = "0000000000000000000000000000000000000000000000000000000000000000"
            .toCharArray();
    private static final Random random = new Random();


    public static final String zeroPadString(String string, int length) {
        if (string == null || string.length() > length) {
            return string;
        } else {
            StringBuffer buf = new StringBuffer(length);
            if (string.startsWith("-")) {
                buf.append('-').append(zeroArray, 0, length - string.length())
                        .append(string.substring(1));
            } else {
                buf.append(zeroArray, 0, length - string.length()).append(
                        string);
            }
            return buf.toString();
        }
    }

    /**
     * 获取指定长度的随机字符串
     *
     * @param num
     * @return
     */
    public static String getRandomString(int num) {
        int rand = random.nextInt(999999);
        return zeroPadString("" + rand, num);
    }


    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int getRandomInt(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * double类型取小数点后面几位
     *
     * @param val       指定double型数字
     * @param precision 取前几位
     * @return 转换后的double数字
     */
    public static Double roundDouble(double val, int precision) {
        Double ret = null;
        try {
            double factor = Math.pow(10, precision);
            ret = Math.floor(val * factor + 0.5) / factor;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
}
