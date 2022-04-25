package com.ysucode.web.servlet;


import com.alibaba.fastjson.JSON;
import com.ysucode.pojo.Problem;
import com.ysucode.service.ProblemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询全部题目的接口
 */
@WebServlet("/selectAllOjServlet")
public class SelectAllOjServlet extends HttpServlet {
    private ProblemService problemService = new ProblemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询所有题目
        List<Problem> problems = problemService.selectAllOj();

        //2.将集合转换为json数据(序列化)
        String jsonString = JSON.toJSONString(problems);

        //3.响应数据
        response.setContentType("text/html;charset=utf-8"); //把html改成json浏览器会接受json文件???
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}