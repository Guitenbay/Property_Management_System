package com.example.pms.dao;

import com.example.pms.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
// 该注释是解决 IDEA mapper 注入时显示 could not autowire, no beans type of 'userMapper' found
@Component(value = "userMapper")
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User findUserById(String id);

    @Select("select * from user")
    List<User> findUsers();
}
