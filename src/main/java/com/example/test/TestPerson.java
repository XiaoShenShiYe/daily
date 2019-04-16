package com.example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.Person;
import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.*;

/**
 * @Author harvey
 * @Email harvey.shen@jollycorp.com
 * @Date 15:52 2018/12/10
 */
public class TestPerson {

    public static void main(String[] args) {
        Person p1 = new Person(1,"a","a");
        Person p2 = new Person(1,"b","b");


//        Set<Person> set = new HashSet<>();
        ArrayList<Person> personArrayList = Lists.newArrayList();
        personArrayList.add(p1);
        personArrayList.add(p2);

//        a(personArrayList);

//        System.out.println(JSONObject.toJSONString(personArrayList));

        Object parse = JSON.parse(JSONObject.toJSONString(personArrayList));

//        set.add(p1);
//        set.add(p2);
//        System.out.println(set);


//        String[] s = {"a","b"};
//        String[] clone = s.clone();
//
//        System.out.println(s.toString());
//        System.out.println(clone);
//        Set<Person> sets = null;
//        for (Person p: sets
//             ) {
//            System.out.println("aa");

//        }

//        IdentityHashMap iMap = new IdentityHashMap();
//        HashMap map = new HashMap();
//        iMap.put(null,1);
//        iMap.put(null,2);
//        map.put(null,3);
//        map.put(null,4);
//        System.out.println("Imap"+iMap.toString());
//        System.out.println("map"+map.toString());
    }

    private static void a(ArrayList<Person> personArrayList){
//        personArrayList.stream().forEach(a->a.setAge(5));
        personArrayList.stream().forEach(a->{int i = 1/0;});
    }
}
