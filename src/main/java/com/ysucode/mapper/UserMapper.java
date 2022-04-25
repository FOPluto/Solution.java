package com.ysucode.mapper;

import com.ysucode.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /*
     * 用户登录：检查用户名和密码是否正确
     */
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User select(@Param("username") String username, @Param("password") String password);

    /*
     * 用户注册：查询用户名是否存在
     */
    @Select("select * from tb_user where username = #{username}")
    User selectByUsername(@Param("username") String username);

    /*
     * 用户注册：添加用户
     */
    @Insert("insert into tb_user values(null,#{username},#{password},#{email})")
    void addUser(User user);
}
