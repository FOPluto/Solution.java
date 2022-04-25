package com.ysucode.service;

import com.ysucode.mapper.ProblemMapper;
import com.ysucode.pojo.Problem;
import com.ysucode.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ProblemService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询Java题目--根据id查询
     *
     * @param id 题目序号
     * @return 题目
     */
    public Problem selectById(int id) {
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ProblemMapper problemMapper = sqlSession.getMapper(ProblemMapper.class);
        //调用方法
        Problem problem = problemMapper.selectById(id);
        //释放资源
        sqlSession.close();
        //返回user
        return problem;
    }

    /**
     * 查询C++题目--根据id查询
     *
     * @param id 题目序号
     * @return 题目
     */
    public Problem selectByIdC(int id) {
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ProblemMapper problemMapper = sqlSession.getMapper(ProblemMapper.class);
        //调用方法
        Problem problem = problemMapper.selectByIdC(id);
        //释放资源
        sqlSession.close();
        //返回user
        return problem;
    }

    /**
     * 查询Java题目--查询所有
     *
     * @return java题目集合
     */
    public List<Problem> selectAllOj() {
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ProblemMapper problemMapper = sqlSession.getMapper(ProblemMapper.class);
        //调用方法
        List<Problem> problem = problemMapper.selectAllOj();
        //释放资源
        sqlSession.close();
        //返回Problem集合
        return problem;
    }

    /**
     * 添加Java题目
     *
     * @param problem java题目对象
     */
    public void addOj(Problem problem) {
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ProblemMapper problemMapper = sqlSession.getMapper(ProblemMapper.class);
        //调用方法
        problemMapper.addOj(problem);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

}
