package com.example.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class WelfareLottery {

    private static final String[] red_number = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18",
            "19","20","21","22","23","24","25","26","27","28","29","30","31","32"};
    private static final String[] blue_number = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"};

    public static void main(String[] args) {
        Map<String,Integer> map = Maps.newHashMap();
        int n = 1000000;
        System.out.println("total count is "+n);
        System.out.println("total possible is "+ 32*31*30*29*28*27*16);

        for(int i = 0 ;i < n ; i++ ){
            String key = JSON.toJSONString(getNumber(red_number,blue_number));
            if(map.containsKey(key)){
                map.put(key,map.get(key) + 1);
            }else{
                map.put(key,0);
            }
        }
        System.out.println("map size is "+map.keySet().size());

        if(map.containsKey("[\"01\",\"06\",\"12\",\"13\",\"24\",\"32\",\"13\"]")){
            System.out.println("中奖啦  500 万");
        }
//        Map<String, Integer> sortMap = sortMapByValue(map);
//
//        Map<String, Integer> firstElement = getFirstElement(sortMap);
//        System.out.println(firstElement);

    }
//    01061213243213
//    ["01","06","12","13","24","32","13"]

    private static Map<String,Integer> getFirstElement(Map<String,Integer> sortMap) {
        Map<String,Integer> firstElement = Maps.newHashMap();
        String key=null;
        for (Map.Entry<String, Integer> entry : sortMap.entrySet()) {
            key = entry.getKey();
            if (key != null) {
                Integer value = entry.getValue();
                firstElement.put(key,value);
                break;
            }
        }
        return firstElement;

    }


    /**
     * 使用 Map按value进行排序
     * @param map
     * @return
     */
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
                map.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    private static List<String> getNumber(String[] arr1,String[] arr2){

        List<String> resultList = Lists.newArrayList();

        List<String> l1 = Lists.newArrayList(arr1);
        int r1 = new Random().nextInt(32);
        String s1 = l1.get(r1);
        resultList.add(s1);
        l1.remove(s1);

        List<String> l2 = Lists.newArrayList(l1);
        int r2 = new Random().nextInt(31);
        String s2 = l1.get(r2);
        resultList.add(s2);
        l2.remove(s2);

        List<String> l3 = Lists.newArrayList(l2);
        int r3 = new Random().nextInt(30);
        String s3 = l1.get(r3);
        resultList.add(s3);
        l3.remove(s3);

        List<String> l4 = Lists.newArrayList(l3);
        int r4 = new Random().nextInt(29);
        String s4 = l1.get(r4);
        resultList.add(s4);
        l4.remove(s4);

        List<String> l5 = Lists.newArrayList(l4);
        int r5 = new Random().nextInt(28);
        String s5 = l1.get(r5);
        resultList.add(s5);
        l5.remove(s5);

        List<String> l6 = Lists.newArrayList(l5);
        int r6 = new Random().nextInt(27);
        String s6 = l6.get(r6);
        resultList.add(s6);
        l6.remove(s6);

        List<String> sortList = Lists.newArrayList();
        sortList = resultList.stream().sorted((ss1,ss2) -> ss1.compareTo(ss2)).collect(Collectors.toList());

        List<String> l7 = Lists.newArrayList(arr2);

        int r7 = new Random().nextInt(16);
        sortList.add(l7.get(r7));


        return sortList;
    }

    private static class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

            return me2.getValue().compareTo(me1.getValue());
        }
    }
}
