package com.service;

import com.bean.User;

import java.util.List;

// 业务层约束接口
public interface UserService {

    // 登录方法
    List<User> login(User user);

}
