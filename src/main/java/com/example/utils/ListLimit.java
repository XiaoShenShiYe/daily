package com.example.utils;

import com.example.domain.Person;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListLimit {

    /**
     * 截取固定长度的list数据
     * @param originList
     * @param count
     */
    public static void getLimitList(List<String> originList,int count){

        if(CollectionUtils.isEmpty(originList)) {
            return;
        }
        List<String> list = new ArrayList();
        Integer totalPage = originList.size()/count + 1;

        Integer co = totalPage;
        while(totalPage > 0){
            for (int i = 0;i<co;i++){
                List<String> collect = originList.stream().skip(i * count).limit(count).collect(Collectors.toList());
                System.out.println(collect.toString());
            }
            totalPage --;
        }
    }

    /**
     * intStreamTest
     */
    public static void IntStreamTest(){

        List<Person> list = new ArrayList<>();
        IntStream.range(0,list.size()).forEach(i->list.get(i).setAge(i+1));
        list.stream().forEach(i->System.out.println(i));
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");

        System.out.println(list.toString());

        //getLimitList(list,10);

        List<String> collect = list.stream().skip(0).limit(1).collect(Collectors.toList());

        System.out.println(collect.toString());

    }



}
