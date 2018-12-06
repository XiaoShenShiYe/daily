package com.example.controller;

import com.example.businessService.AuthorService;
import com.example.model.TAuthor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author harvey
 * @Email harvey.shen@jollycorp.com
 * @Date 11:30 2018/11/20
 */
@RestController
@EnableAutoConfiguration
public class ControllerTest {

    @Resource
    private AuthorService authorService;

    @RequestMapping("/")
    String home() {
        System.out.println("aa");
        return "Hello World!";
    }



    /**
     * 查询用户信息
     */
    @RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.GET)
    public TAuthor getAuthor(@PathVariable Long userId, HttpServletRequest request) {
        TAuthor author = this.authorService.findAuthor(userId);
        if(author == null){
            throw new RuntimeException("查询错误");
        }
        return author;
    }

    @RequestMapping(value = "realName", method = RequestMethod.GET)
    @ResponseBody
    public void update(String realName, HttpServletRequest request) {
        TAuthor author = new TAuthor();
        author.setRealName(realName);
        author.setNickName("bb");
        author.setId(1L);
        Boolean aBoolean = authorService.updateAuthor(author);
        if(!aBoolean){
            throw new RuntimeException("更新错误");
        }
    }
}
