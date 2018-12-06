package com.example.utils;

import com.example.domain.Person;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 排序
 */
public class SortUtils {

    /**
     * case one :按照名称升序，如果名称相同，则按照电话号码升序
     * default:按照电话号码升序
     * @param list
     * @param colume
     * @return
     */
    public static List<Person> sortList(List<Person> list,Colume colume){

        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        return list.stream().sorted(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                switch (colume){
                    case ONE:
                        int compare = org.apache.commons.lang3.StringUtils.compare(o1.getName(), o2.getName());
                        if(compare == 0){
                            return  org.apache.commons.lang3.StringUtils.compare(o1.getPhoneNum(),o2.getPhoneNum());
                        }
                        return org.apache.commons.lang3.StringUtils.compare(o1.getName(),o2.getName());
                    default:return  org.apache.commons.lang3.StringUtils.compare(o1.getPhoneNum(),o2.getPhoneNum());
                }
            }
        }).collect(Collectors.toList());
    }

    public enum Colume{
        ONE,
        TWO,
        THREE;
        private Colume(){}
    }


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aba");
        list.add("aac");

        List<String> collect = list.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return org.apache.commons.lang3.StringUtils.compare(o1, o2);
            }
        }).collect(Collectors.toList());
        System.out.println(collect);


        //System.out.println(org.apache.commons.lang3.StringUtils.compare("abc","aac"));

//        List<Person> list = new ArrayList<>();
//        list.add(new Person(1,"a","123"));
//        list.add(new Person(2,"c","124"));
//        list.add(new Person(3,"a","122"));
//        list.add(new Person(4,"a","156"));
//
//        list.add(new Person(5,"a","111"));
//
//        list.add(new Person(6,"a","178"));
//
//        List<Person> people = sortList(list, Colume.ONE);
//
//        System.out.println(people.toString());

    }


    public static void gather(List<Person> list){


    }

}
