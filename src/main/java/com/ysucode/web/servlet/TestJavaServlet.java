package com.ysucode.web.servlet;


import com.alibaba.fastjson.JSON;
import com.ysucode.pojo.Problem;
import com.ysucode.service.ProblemService;
import com.ysucode.service.TaskJava;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 提交java代码运行的接口
 */
@WebServlet("/testJavaServlet")
public class TestJavaServlet extends HttpServlet {
    private ProblemService problemService = new ProblemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service
        //1.1查询id指定的单个题目
        int id = Integer.parseInt(request.getParameter("id"));
        Problem problem = problemService.selectById(id);

        //1.2获取用户输入的代码
        String code = request.getParameter("code");
        //System.out.println(code);

        //2.将用户输入的代码和测试代码拼起来进行编译运行 result为0代表代码正确
        int result;
        try {
            result = TaskJava.TestJava(id, code);
        } catch (InterruptedException e) {
            result = -1;
            e.printStackTrace();
        }
        System.out.println(result);

        //3.响应数据
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}