package com.example.mapper;

import com.example.model.TAuthor;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TAuthorMapper extends Mapper<TAuthor>, MySqlMapper<TAuthor> {
}