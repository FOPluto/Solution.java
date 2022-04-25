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

/**
 * 添加java题目的接口
 */
@WebServlet("/addJavaOjServlet")
public class AddJavaOjServlet extends HttpServlet {
    private ProblemService problemService = new ProblemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收数据
        request.setCharacterEncoding("UTF-8"); //设置编码格式 防止中文乱码
        String title = request.getParameter("title");
        String level = request.getParameter("level");
        String description = request.getParameter("description");
        String template = request.getParameter("template");
        String test = request.getParameter("test");

        //2.封装成对象
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setLevel(level);
        problem.setDescription(description);
        problem.setTemplateCode(template);
        problem.setTestCode(test);

        //3.调用service存储到数据库
        System.out.println("存储数据库");
        System.out.println(problem);
        problemService.addOj(problem);

        //4.做出响应
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(JSON.toJSONString("666"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}