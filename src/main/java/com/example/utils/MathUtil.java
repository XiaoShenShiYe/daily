package com.example.utils;

import java.math.BigDecimal;

public class MathUtil {

    /**
     * 四舍五入
     * @param size
     * @param number
     * @return
     */
    public static Double roundDouble(int size,Double number){

        if(number == null){
            return null;
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(number);
        return bigDecimal.setScale(size,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println("0.00");
        System.out.println(Double.valueOf("0.00"));
        System.out.println(Double.valueOf("0") == 0d);
    }
}
