package com.ysucode.service;

import com.ysucode.mapper.UserMapper;
import com.ysucode.pojo.User;
import com.ysucode.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 用户登录---查询所有
     *
     * @param username
     * @param password
     * @return
     */
    public User select(String username, String password) {
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = userMapper.select(username, password);
        //释放资源
        sqlSession.close();
        //返回user
        return user;
    }

    /**
     * 用户注册---查询用户名是否存在
     *
     * @param username
     * @return
     */
    public User selectByUsername(String username) {
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = userMapper.selectByUsername(username);
        //释放资源
        sqlSession.close();
        //返回user
        return user;
    }

    /**
     * 用户注册---添加用户
     *
     * @param user
     */
    public void addUser(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //用户名不存在，可以注册
        userMapper.addUser(user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
