package com.feizi.dubbo.dao;

import com.feizi.dubbo.dao.model.User;

/**
 * Created by feizi on 2018/3/8.
 */
public interface UserDao {
    User getUser(Integer id);
}
