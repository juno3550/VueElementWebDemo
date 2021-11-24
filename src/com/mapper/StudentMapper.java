package com.mapper;

import com.bean.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {

    // 查询全部
    @Select("select * from student")
    public abstract List<Student> selectAll();

    // 添加学生
    @Insert("insert into student values(#{number}, #{name}, #{birthday}, #{address})")
    public abstract void addStu(Student student);

    // 修改学生数据
    @Update("update student set number=#{number}, name=#{name}, birthday=#{birthday}, address=#{address} where number=#{number}")
    public abstract void updateStu(Student student);

    // 删除学生数据
    @Delete("delete from student where number=#{number}")
    public abstract void deleteStu(String number);


}
