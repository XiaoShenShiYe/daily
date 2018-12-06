package com.example.businessService.impl;

import com.example.businessManager.AuthorBusinessManager;
import com.example.businessService.AuthorService;
import com.example.mapper.TAuthorMapper;
import com.example.model.TAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author harvey
 * @Email harvey.shen@jollycorp.com
 * @Date 15:25 2018/11/20
 */
@Service
public class AuthorSeriveImpl implements AuthorService {

    @Autowired
    private TAuthorMapper authorMapper;

    @Resource
    private AuthorBusinessManager authorBusinessManager;

    @Override
    public TAuthor findAuthor(Long id){
        return authorMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateAuthor(TAuthor author) {
        try{
            authorBusinessManager.updateData(author);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public Boolean updateByAuthor(TAuthor to, TAuthor query) {


        int i = authorMapper.updateByExampleSelective(to, query);
        if(i > 0){
            return true;
        }
        return false;
    }


}
