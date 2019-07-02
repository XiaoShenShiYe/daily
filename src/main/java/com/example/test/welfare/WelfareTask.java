package com.example.test.welfare;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WelfareTask implements Runnable {
    private static final String[] red_number = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18",
            "19","20","21","22","23","24","25","26","27","28","29","30","31","32"};
    private static final String[] blue_number = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"};

    private static final String TARGET_SOURCE= "[\"03\",\"06\",\"07\",\"15\",\"16\",\"21\",\"08\"]";

    @Override
    public void run() {
        Map<String,String> map = Maps.newHashMap();
        Long count = 0L;
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        try{
            for(;;){
                count ++;
                String currentNum = JSON.toJSONString(getNumber(red_number,blue_number));
                map.put(currentNum,currentNum);
                if(map.containsKey(TARGET_SOURCE)){
                    break;
                }
            }
        }catch (Exception e){

        }

        stopwatch.stop();
        System.out.println("随机次数："+count + "耗时："+stopwatch.elapsedTime(TimeUnit.SECONDS)+" s");
    }



    private static List<String> getNumber(String[] arr1, String[] arr2){

        List<String> resultList = Lists.newCopyOnWriteArrayList();
        Random random = new Random();


        List<String> l1 = Lists.newCopyOnWriteArrayList(Arrays.asList(arr1));
        int r1 = random.nextInt(31);
        String s1 = l1.get(r1);
        resultList.add(s1);
        l1.remove(s1);

        List<String> l2 = Lists.newCopyOnWriteArrayList(l1);
        int r2 = random.nextInt(30);
        String s2 = l2.get(r2);
        resultList.add(s2);
        l2.remove(s2);

        List<String> l3 = Lists.newCopyOnWriteArrayList(l2);
        int r3 = random.nextInt(29);
        String s3 = l3.get(r3);
        resultList.add(s3);
        l3.remove(s3);

        List<String> l4 = Lists.newCopyOnWriteArrayList(l3);
        int r4 = random.nextInt(28);
        String s4 = l4.get(r4);
        resultList.add(s4);
        l4.remove(s4);

        List<String> l5 = Lists.newCopyOnWriteArrayList(l4);
        int r5 = random.nextInt(27);
        String s5 = l5.get(r5);
        resultList.add(s5);
        l5.remove(s5);

        List<String> l6 = Lists.newCopyOnWriteArrayList(l5);
        int r6 = random.nextInt(26);
        String s6 = l6.get(r6);
        resultList.add(s6);
        l6.remove(s6);

        List<String> sortList = Lists.newCopyOnWriteArrayList();
        sortList = resultList.stream().sorted((ss1,ss2) -> ss1.compareTo(ss2)).collect(Collectors.toList());

        List<String> l7 = Lists.newCopyOnWriteArrayList(Arrays.asList(arr2));

        int r7 = random.nextInt(16);
        sortList.add(l7.get(r7));

//        System.out.println(JSON.toJSONString(sortList)+"-"+Thread.currentThread().getName());

        return sortList;
    }
}
