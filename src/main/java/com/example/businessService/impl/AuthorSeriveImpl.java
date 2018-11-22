package com.example.businessService.impl;

import com.example.businessService.AuthorService;
import com.example.mapper.TAuthorMapper;
import com.example.model.TAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author harvey
 * @Email harvey.shen@jollycorp.com
 * @Date 15:25 2018/11/20
 */
@Service
public class AuthorSeriveImpl implements AuthorService {

    @Autowired
    private TAuthorMapper authorMapper;

    @Override
    public TAuthor findAuthor(Long id){
        return authorMapper.selectByPrimaryKey(id);
    }
}
