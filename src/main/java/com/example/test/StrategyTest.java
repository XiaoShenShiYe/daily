package com.example.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class StrategyTest {

    public static final Map<String,String> map1 = Maps.newHashMap();

    static {
        map1.put("1","2");
    }

    public static final List<String> list1 = Lists.newArrayList("1","2","3");




    public static Object getResult1(Integer num){
        if(num == null){
            return null;
        }
        if(num == 1){
            return 1;
        }
        return null;
    }
}
