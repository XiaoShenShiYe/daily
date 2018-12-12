package com.example.controller;

import com.example.businessService.AuthorService;
import com.example.businessService.impl.AuthorSeriveImpl;
import com.example.utils.spring.ApplicationContextHelper;
import org.springframework.stereotype.Component;

/**
 * @Author harvey
 * @Email harvey.shen@jollycorp.com
 * @Date 9:10 2018/12/12
 */
@Component
public class BaseController {
    static {
        System.out.println("i am baseController");
        AuthorService authorService = ApplicationContextHelper.getBean("authorService",AuthorSeriveImpl.class);
        System.out.println(String.valueOf(authorService));
    }


    public void print(){
        System.out.println("i am baseController");
    }
}
