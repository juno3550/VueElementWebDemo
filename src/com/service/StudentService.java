package com.service;

import com.bean.Student;
import com.github.pagehelper.Page;

public interface StudentService {

    // 分页查询方法
    public abstract Page selectByPage(Integer currentPage, Integer pageSize);

    // 添加学生
    public abstract void addStu(Student stu);

    // 修改学生数据
    public abstract void updateStu(Student stu);

    // 删除学生数据
    public abstract void deleteStu(String number);
}
