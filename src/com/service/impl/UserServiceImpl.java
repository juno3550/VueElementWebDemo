package com.service.impl;

import com.bean.User;
import com.mapper.UserMapper;
import com.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> login(User user) {
        InputStream in = null;
        SqlSession sqlSession = null;
        List<User> list = null;
        try {
            // 1. 加载MyBatis全局配置文件
            in = Resources.getResourceAsStream("conf/MyBatisConfig.xml");
            // 2. 获取SqlSession工厂对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            // 3. 通过SqlSession工厂对象获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. 获取UserMapper接口的实现类对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 5. 调用实现类对象的登录方法
            list = mapper.login(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            if (sqlSession != null) {
                sqlSession.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 7. 返回结果给控制层（controller）
        return list;
    }
}
