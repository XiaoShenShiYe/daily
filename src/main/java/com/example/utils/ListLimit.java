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
        Integer totalPage = originList.size()/count + 1;

        while(totalPage > 0){
            for (int i = 0;i<totalPage;i++){
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
        list.add(new Person());
        list.add(new Person());
        list.add(new Person());
        list.add(new Person());
        IntStream.range(0,list.size()).forEach(i->list.get(i).setAge(i+1));
        list.stream().forEach(i->System.out.println(i));
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.toString());
//        list.add("1");
//
//        getLimitList(list,10);


//        IntStreamTest();
        System.out.println(2/2);
        System.out.println(2%2);
        System.out.println(2%3);
        System.out.println(2%1);

    }



}
