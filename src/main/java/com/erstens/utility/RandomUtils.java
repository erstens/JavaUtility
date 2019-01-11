package com.erstens.utility;

import java.util.Random;

public class RandomUtils {
    /**
     * 随机的n位数
     *
     * @param n
     * @return
     */
    public static String getNumberByBit(int n) {
        if (n == 0) {
            throw new IllegalArgumentException("n is error");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int r = new Random().nextInt(9);
            if(n > 1 && 0 == i) {
                while(r == 0) {
                    r = new Random().nextInt(9) ;
                }
            }
            sb.append(r);
        }
        return sb.toString();
    }
}
