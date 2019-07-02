package com.example.test;

import com.example.test.welfare.WelfareTask;

import java.util.concurrent.*;

public class WelfareLotteryThreadPool {

    public static void main(String[] args) {
        BlockingQueue<Runnable> queueList = new ArrayBlockingQueue<Runnable>(16);

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(4,4,1000,TimeUnit.SECONDS,queueList);
        threadPoolExecutor.execute(new WelfareTask());
        System.out.println(threadPoolExecutor.getActiveCount());
        System.out.println(threadPoolExecutor.getPoolSize());
    }


}
