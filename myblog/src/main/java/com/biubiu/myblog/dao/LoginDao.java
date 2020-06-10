package com.biubiu.myblog.dao;

import com.biubiu.myblog.entity.Login;

public interface LoginDao {


    /**
     * 根据用户id删除登录记录
     * @param id
     */
    void deleteLoginByUserId(Integer id);


    /**
     * 保存登录信息
     */
    void saveLogin(Login login);


    /**
     * 根据用户id修改登录操作表
     * @param login
     */
    void updateLogin(Login login);
}
