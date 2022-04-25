package com.ysucode.mapper;

import com.ysucode.pojo.Problem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProblemMapper {
    /*
     * 查询Oj表：根据题目id进行查询
     */
    @Select("select * from tb_oj where id = #{id}")
    Problem selectById(@Param("id") int id);

    /*
     * 查询OjC表：根据题目id进行查询
     */
    @Select("select * from tb_ojC where id = #{id}")
    Problem selectByIdC(@Param("id") int id);

    /*
     * 查询Oj表：查询所有题目
     */
    @Select("select * from tb_oj")
    List<Problem> selectAllOj();

    /*
     * Oj表：插入题目
     */
    @Insert("insert into tb_oj values(null,#{title},#{level},#{description},#{templateCode},#{testCode})")
    void addOj(Problem problem);
}
