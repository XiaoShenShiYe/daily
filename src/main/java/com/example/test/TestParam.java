package com.example.test;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestParam {

    static final Integer i = 1000;
    public static void main(String[] args) throws Exception {
//        Long id = 1L;
//        id = getMethod(id);
//        List<String>  list = new ArrayList<>();
//
//        setList(list);
//        System.out.println(list.toString());
//
//        System.out.println(id);
//
//        System.out.println(1001 > i);
//        System.out.println(String.valueOf(true));
//        Integer a = 1;
//        Integer b = 0;
//        Integer c = a = b;
//        System.out.println(c);
//        System.out.println(a);

        System.out.println(File.separator);


    }

    public static void setList(List<String> list) throws Exception {

        list.add("134");
        throw new Exception("adfasfa");
    }

    private static Long getMethod(Long id) {

        id = 2L;
        return id;
    }
}
