package com.service.impl;

import com.bean.Student;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.StudentMapper;
import com.service.StudentService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class StudentServiceImpl implements StudentService {

    @Override
    public Page selectByPage(Integer currentPage, Integer pageSize) {
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        Page page = null;
        try {
            // 1. 加载Mybatis配置文件
            inputStream = Resources.getResourceAsStream("conf/MyBatisConfig.xml");
            // 2. 获取SqlSession工厂对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 3. 通过SqlSession工厂对象获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. 获取StudentMapper接口实现类对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            // 5.设置分页参数
            page = PageHelper.startPage(currentPage, pageSize);
            // 6. 调用实现类对象查询全部数据
            mapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
            if (sqlSession!=null) {
                sqlSession.close();
            }
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 8. 返回结果给控制层
        return page;
    }

    // 添加学生数据
    @Override
    public void addStu(Student stu) {
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            // 1. 加载Mybatis配置文件
            inputStream = Resources.getResourceAsStream("conf/MyBatisConfig.xml");
            // 2. 获取SqlSession工厂对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 3. 通过SqlSession工厂对象获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. 获取StudentMapper接口实现类对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            // 5. 调用实现类对象添加方法
            mapper.addStu(stu);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
            if (sqlSession!=null) {
                sqlSession.close();
            }
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 修改学生数据
    @Override
    public void updateStu(Student stu) {
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            // 1. 加载Mybatis配置文件
            inputStream = Resources.getResourceAsStream("conf/MyBatisConfig.xml");
            // 2. 获取SqlSession工厂对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 3. 通过SqlSession工厂对象获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. 获取StudentMapper接口实现类对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            // 5. 调用实现类对象添加方法
            mapper.updateStu(stu);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
            if (sqlSession!=null) {
                sqlSession.close();
            }
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 删除学生数据
    @Override
    public void deleteStu(String number) {
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            // 1. 加载Mybatis配置文件
            inputStream = Resources.getResourceAsStream("conf/MyBatisConfig.xml");
            // 2. 获取SqlSession工厂对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 3. 通过SqlSession工厂对象获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. 获取StudentMapper接口实现类对象
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            // 5. 调用实现类对象添加方法
            mapper.deleteStu(number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
            if (sqlSession!=null) {
                sqlSession.close();
            }
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
