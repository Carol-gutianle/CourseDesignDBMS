package com.hnu.dbserver.mapper;

import com.hnu.dbserver.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface DataBase {
    @Select("select * from 学生 where 学号=#{sno} and 密码=#{pwd}")
    List<Student>stuLogin(String sno,String pwd);
    @Select("select * from 教师 where 工资号=#{tno} and 密码=#{tpwd}")
    List<Student>teaLogin(String tno,String tpwd);
    @Update("update 学生 set 密码=#{nspwd} where 学号=#{sno}")
    int stuUpdatePwd(String sno,String nspwd);
    @Update("update 教师 set 密码=#{nspwd} where 工资号=#{sno}")
    int teaUpdatePwd(String sno,String nspwd);
    @Select("select 姓名,题目 from 学生,题库 where 学生.学号=#{sno} and 题库.题号=学生.题号")
    List<Map<String,Object>> seleQueCond(String sno);
    @Select("select 分数 from 学生,课程学生用 where 学生.题号=课程学生用.题号 and 学生.学号=#{sno}")
    Map<String,Object> seleGrade(String sno);
    @Update("update 课程学生用 set 进度情况=#{progress} where 题号=#{qno}")
    int setProgress(String qno,String progress);
    @Update("update 课程学生用 set 分数=#{grade} where 题号=#{qno}")
    boolean setGrade(String qno,String grade);
    @Select("select * from 学生,题库 where 学生.题号=题库.题号")
    List<Map<String,Object>> seleQue();
    @Select("select * from 题库")
    List<Map<String,Object>> seleQueName();
    /*判断是否已经选题*/
    @Select(("select 题号 from 学生 where 学号=#{sno}"))
    String isChosen(String sno);
    /*确定个人选题*/
    @Update("update 学生 set 题号=#{qno} where 学号=#{sno}")
    boolean confirmQue(String qno,String sno);
    /*添加选题记录*/
    @Insert("insert into 课程学生用(题号,小组长) values (#{qno},#{leader})")
    boolean addQueRecord(String qno,String leader);
    /*判断是否已经有小组长*/
    @Select("select 小组长 from 课程学生用 where 题号=#{qno}")
    String seleLeader(String qno);
    /*获取名字*/
    @Select("select 姓名 from 学生 where 学号=#{sno}")
    String seleName(String sno);
    /*学生上传文件*/
    @Update("update 课程学生用 set 工作报告=#{screport} where 题号=#{qno}")
    boolean stuSetfile(String qno,String screport);
    /*获取题号*/
    @Select("select 题号 from 学生 where 学号=#{sno}")
    String getQno(String sno);
}
