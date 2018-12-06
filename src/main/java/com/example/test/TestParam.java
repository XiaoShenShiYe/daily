package com.example.test;


import java.util.ArrayList;
import java.util.List;

public class TestParam {

    static final Integer i = 1000;
    public static void main(String[] args) {
//        Long id = 1L;
//        id = getMethod(id);
//        List<String>  list = new ArrayList<>();
//
//        setList(list);
//        System.out.println(list.toString());

//        System.out.println(id);

        //System.out.println(1001 > i);
        //System.out.println(String.valueOf(true));
    }

    private static void setList(List<String> list) {
        list.add("134");
    }

    private static Long getMethod(Long id) {

        id = 2L;
        return id;
    }
}
