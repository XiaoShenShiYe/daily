package com.example.businessManager.impl;

import com.example.businessManager.AuthorBusinessManager;
import com.example.mapper.TAuthorMapper;
import com.example.model.TAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorBusinessManagerImpl implements AuthorBusinessManager {

    @Autowired
    private TAuthorMapper authorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateData(TAuthor author) throws Exception{
        authorMapper.updateByPrimaryKey(author);
        throw new Exception("LLLL");
    }
}
