package com.charles.zlbbs.utils;

import java.util.Random;

public class RandomCaptcha {

    /**
     * 生成随机数+字母组合 当作getItemID
     * n ： 需要的长度
     *
     * @return
     */
    public static String getItemID(int n) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val.append((char) (nextInt + random.nextInt(26)));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }

    /**
     * 生产字母 ItemName随机函数
     *
     * @param length
     * @return
     */
    public static String getItemName(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
