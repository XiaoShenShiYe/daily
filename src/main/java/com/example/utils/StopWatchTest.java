package com.example.utils;

import org.springframework.util.StopWatch;

import java.util.HashMap;

public class StopWatchTest {
    public static final HashMap<String,String> hashMap = new HashMap<>(1000000);

    public static void test(){
        for (int i = 0;i<1000000;i++){
            hashMap.put(String.valueOf(i),String.valueOf(i));
        }
    }


    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        test();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
