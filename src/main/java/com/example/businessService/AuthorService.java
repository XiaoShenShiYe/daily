package com.example.businessService;

import com.example.model.TAuthor;

/**
 * @Author harvey
 * @Email harvey.shen@jollycorp.com
 * @Date 14:04 2018/11/20
 */
public interface AuthorService {
    public TAuthor findAuthor(Long id);


    Boolean updateAuthor(TAuthor author);

    Boolean updateByAuthor(TAuthor to,TAuthor query);

}
