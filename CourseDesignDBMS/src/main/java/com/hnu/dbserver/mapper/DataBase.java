package com.hnu.dbserver.mapper;

import com.hnu.dbserver.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataBase {
    @Select("select * from 学生 where 学号=#{sno} and 密码=#{pwd}")
    List<Student>stuLogin(String sno,String pwd);
    @Select("select * from 教师 where 工资号=#{tno} and 密码=#{tpwd}")
    List<Student>teaLogin(String tno,String tpwd);
}
