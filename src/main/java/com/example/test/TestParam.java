package com.example.test;


import com.alibaba.com.caucho.hessian.io.HessianSerializerInput;
import com.alibaba.com.caucho.hessian.io.HessianSerializerOutput;
import com.alibaba.fastjson.JSON;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestParam {

    static final Integer i = 1000;
    public static void main(String[] args) throws Exception {
//        Long id = 1L;
//        id = getMethod(id);
//        List<String>  list = new ArrayList<>();
//
//        setList(list);
//        System.out.println(list.toString());
//
//        System.out.println(id);
//
//        System.out.println(1001 > i);
//        System.out.println(String.valueOf(true));
//        Integer a = 1;
//        Integer b = 0;
//        Integer c = a = b;
//        System.out.println(c);
//        System.out.println(a);

//        System.out.println(File.separator);
//        System.out.println(2^3);

        //JSON.parseObject("a",Object.class);


        Student student = new Student();
        student.setAge(10);
        student.setName("123");
        student.setName("深圳");

        byte[] results = null;
        ByteArrayOutputStream os = null;
        HessianSerializerOutput hessianOut = null;

        ByteArrayInputStream is = null;
        Student2 serStu = null;
        try{
            //进行序列化操作
            os = new ByteArrayOutputStream();
            hessianOut = new HessianSerializerOutput(os);
            hessianOut.writeObject(student);
            os.close();
            results = os.toByteArray();
            System.out.println(results);

            //反序列化操作
            is = new ByteArrayInputStream(results);
            HessianSerializerInput hessianInput = new HessianSerializerInput(is);
            serStu = (Student2)hessianInput.readObject();

            System.out.println("-------反序列化数据----"+serStu);
        }catch(Exception e){
            //hassian序列化类型缺少字段的话会抛异常
            e.printStackTrace();
        }


    }

    public static void setList(List<String> list) throws Exception {

        list.add("134");
        throw new Exception("adfasfa");
    }

    private static Long getMethod(Long id) {

        id = 2L;
        return id;
    }
    public static class Student implements Serializable {
        private String name;
        private int age;
        private String address;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        @Override
        public String toString() {
            return "Student [name=" + name + ", age=" + age + ", address="
                    + address + "]";
        }
    }

    public static class Student2 implements Serializable {
        private String name;
//        private int age;
        private String address;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
//        public int getAge() {
//            return age;
//        }
//        public void setAge(int age) {
//            this.age = age;
//        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        @Override
        public String toString() {
            return "Student [name=" + name + ",  address="
                    + address + "]";
        }
    }
}
