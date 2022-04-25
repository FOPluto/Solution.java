package com.ysucode.web.servlet;


import com.ysucode.pojo.User;
import com.ysucode.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户登录的接口
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //POST请求方式 设置字符输入流编码
        HttpSession session = request.getSession(); //获取session
        //接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用UserService查询
        UserService userService = new UserService();
        User user = userService.select(username, password);

        //获取字符输出流，并设置content type
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        //3. 判断user释放为null
        if (user != null) {
            //登陆成功
            //writer.write("登陆成功，跳转到首页");

            //把用户存储到session
            session.setAttribute("user", user);

            //登录转发
            //request.setAttribute("login", "登录成功！");
            //request.getRequestDispatcher("/home.html").forward(request, response);

            //重定向
            response.sendRedirect("/YsuCode-demo/ojHome.html");
        } else {
            // 登陆失败
            //writer.write("登陆失败");
            response.getWriter().write("<script>alert('登录失败');history.back();</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}