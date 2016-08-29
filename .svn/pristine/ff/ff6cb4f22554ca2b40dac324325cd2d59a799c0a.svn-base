package com.boxiang.share.utils;

import java.util.Random;

public class RandomUtil {

    private static final char[] charArray = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String rand(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int index = random.nextInt(charArray.length);
            sb.append(charArray[index]);
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(rand(11));
//    }

}
