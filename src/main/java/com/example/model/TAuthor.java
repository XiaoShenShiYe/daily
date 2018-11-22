package com.example.model;

import javax.persistence.*;

@Table(name = "t_author")
public class TAuthor {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名称
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 用户匿名
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 获取用户ID
     *
     * @return id - 用户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名称
     *
     * @return real_name - 用户名称
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置用户名称
     *
     * @param realName 用户名称
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取用户匿名
     *
     * @return nick_name - 用户匿名
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户匿名
     *
     * @param nickName 用户匿名
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}