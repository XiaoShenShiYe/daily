package com.example.test;

import org.springframework.util.StopWatch;

/**
 * @Author harvey
 * @Email harvey.shen@jollycorp.com
 * @Date 10:20 2018/12/10
 */
public class TestStopWatch {

    private static void test(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        //stopWatch.start("test");
        stopWatch.start();
        for (int i = 0;i<100;i++){
            test();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
