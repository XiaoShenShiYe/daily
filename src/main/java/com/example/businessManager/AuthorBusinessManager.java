package com.example.businessManager;

import com.example.model.TAuthor;

public interface AuthorBusinessManager {

    /**
     * 模拟带事务，更新数据
     * @return
     */
    Boolean updateData(TAuthor author) throws Exception;
}
