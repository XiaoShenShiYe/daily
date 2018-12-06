package com.example.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 数据处理工具类。
 * 
 * @author bl00817
 *
 */
public class MathUtil {

    /**
     * 两数相乘
     * 
     * @param value1
     * @param value2
     * @return
     */
    public static Double multiply(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return Double.valueOf(0);
        }
        BigDecimal val1 = new BigDecimal(value1.toString());
        BigDecimal val2 = new BigDecimal(value2.toString());

        return val1.multiply(val2).doubleValue();
    }

    /**
     * 两数相除
     * 
     * @param value
     * @param divisor
     * @param len
     *            保留的小数位
     * @return
     */
    public static Double divide(Object value, Object divisor, int len) {
        if (value == null || divisor == null) {
            return Double.valueOf(0);
        }

        BigDecimal val1 = new BigDecimal(value.toString());
        BigDecimal val2 = new BigDecimal(divisor.toString());

        if (val2.doubleValue() == 0D) {
            return Double.valueOf(0);
//            throw new IllegalArgumentException("divisor is null or zero");
        }

        return val1.divide(val2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double divide(Object value, Object divisor) {
        return divide(value, divisor, 4);
    }

    /**
     * 根据factor因子结合两个Double数字，factor为 1 则两数相加，为 -1 则两数相减
     * 
     * @param first
     * @param second
     * @param factor
     *            1 或 -1；
     * @return
     */
    public static Double mix(Double first, Double second, int factor) {
        if (Math.abs(factor) != 1) {
            throw new IllegalArgumentException("错误的因子！factor只能为1或-1");
        }

        first = first == null ? 0D : first;
        second = second == null ? 0D : second;
        // return first + second * factor;
        return add(first, second * factor);
    }

    /**
     * {@link #nullToZero(Double)}
     * 
     * @param a
     * @return
     */
    public static Long abs( Long a) {
        a = nullToZero(a);
        return Math.abs(a);
    }
    /**
     * Double型加法运算。
     * 
     * @param first
     * @param second
     * @return
     */
    public static Double add(Double first, Double second) {
        BigDecimal sum = BigDecimal.ZERO; // new BigDecimal(0)
        if (first != null) {
            sum = BigDecimal.valueOf(first);
        }
        if (second != null) {
            sum = sum.add(BigDecimal.valueOf(second));
        }
        return sum.doubleValue();
    }

    /**
     * 多数相乘
     */
    public static Double multiAll(List<Double> datas) {

        BigDecimal result = null;
        for(Double d:datas){
            if(d==null||d.equals(0d)){
                return Double.valueOf(0d);
            }
            if(result==null){
                result=BigDecimal.valueOf(d);
            }else {
                result = result.multiply(BigDecimal.valueOf(d));
            }
        }
        return result.doubleValue();
    }

    public static Double addAll(List<Double> datas){
        BigDecimal sum = BigDecimal.ZERO;
        for(Double d:datas){
            if(d!=null){
                sum = sum.add(BigDecimal.valueOf(d));
            }
        }
        return sum.doubleValue();
    }

    public static Double addAll(Object... datas){
        BigDecimal sum = BigDecimal.ZERO;
        for(Object d:datas){
            if(d!=null){
                sum = sum.add(BigDecimal.valueOf((Double) d));
            }
        }
        return sum.doubleValue();
    }

    public static Double add(Double first, Double second, Double third) {
        return add(add(first, second), third);
    }

    /**
     * Double型减法运算。
     * 
     * @param first
     * @param second
     * @return
     */
    public static Double sub(Double first, Double second) {
        if (first == null) {
            first = 0D;
        }
        if (second == null) {
            second = 0D;
        }
        return add(first, second * -1);
    }

    public static Double sub(Double first, Double second, Double third) {
        return sub(sub(first, second), third);
    }

    public static Double reverse(Double value) {
        if (value == null) {
            return 0d;
        }
        return -value;
    }

    public static Double abs(Double value) {
        if (value == null) {
            return 0D;
        }
        return Math.abs(value);
    }

    // 四舍五入。size:截取指定长度
    public static Double roundUp(int size, Double number) {
        StringBuffer formatString = new StringBuffer("0");
        if (size > 0) {
            formatString.append(".");
        }
        String numberStr = number.toString();
        int indexDot = numberStr.indexOf(".");
        int numberDot = numberStr.substring(indexDot).length() - 1;
        size = size > numberDot ? numberDot : size;
        for (int i = 0; i < size; i++) {
            formatString.append("#");
        }
        DecimalFormat df = new DecimalFormat(formatString.toString());
        return Double.valueOf(df.format(number));
    }

    /**
     * 四舍五入
     * 
     * @param value
     * @param size
     *            保留小数位数
     * @return
     */
//    public static Double round(Double value, int size) {
//        String pattern = "#0.0";
//        if (size > 1) {
//            for (int i = 0; i < size - 1; i++) {
//                pattern += "0";
//            }
//        }
//
//        DecimalFormat df = new DecimalFormat(pattern);
//        return Double.valueOf(df.format(value));
//    }


    /**
     * 四舍五入
     *
     * @param value
*            保留小数位数
     * @return
     */
    public static Double round2(Double value) {
       BigDecimal b = BigDecimal.valueOf(value);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 判断String是否可以转换为整数
     * 
     * @param str
     *            传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean canParseToInteger(String str) {
        Pattern pattern = Pattern.compile("^[-]?[\\d]+$");
        return str == null ? false : pattern.matcher(str).matches();
    }

    /**
     * 判断是否为整数
     * 
     * @param str
     *            传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为整数
     * 
     * @param str
     *            传入的字符串
     * @return 是整数返回true,否则返回false 上面的方法貌似无法判断类似1.0的“整数”，所以又写了这个方法 add by zxl
     *         2011-05-12
     */
    public static boolean isInteger2(String str) {
        Pattern pattern = Pattern.compile("(^[0-9]+[.][0]+$)||(^[0-9]+$)");
        return pattern.matcher(str).matches();

    }

    /**
     * 当字符串的长度超过maxLength时，截取掉前面的benIndex个字符
     */
    public static String subStr4Char(String str, int maxLength, int benIndex) {
        if (str == null || "".equals(str)) {
            return "";
        }
        char[] charArray = str.toCharArray();
        if (charArray.length >= maxLength) {
            return str.substring(benIndex);
        }
        return str;
    }

    public static Integer getIntFromString(String src) {
        if (isInteger(src)) {
            return Integer.parseInt(src);
        }
        return 0;
    }

    public static Long add(Long long1, Long long2) {
        long1 = nullToZero(long1);
        long2 = nullToZero(long2);
        
        return long1 + long2;
    }

    public static Long nullToZero(Long long1) {
        if (long1 == null) {
            return 0l;
        }
        
        return long1;
    }
    
    public static Double nullToZero(Double long1) {
        if (long1 == null) {
            return 0d;
        }
        
        return long1;
    }

    /**
     * 对Double四舍五入
     * @param size
     * @param number
     * @return
     */
    public static Double roundDouble(int size, Double number) {
        if(number == null){
            return null;
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(number);
        return bigDecimal.setScale(size, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
