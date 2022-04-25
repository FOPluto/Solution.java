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

/**
 * 用户注册的接口
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //POST请求方式 设置字符输入流编码
        //接收用户名、密码、邮箱、验证码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String checkcode = request.getParameter("checkcode");

        //从session获取生成的验证码
        HttpSession session = request.getSession();
        String checkcodegen = (String) session.getAttribute("checkcodegen");

        //比对验证码
        if (!checkcodegen.equalsIgnoreCase(checkcode)) {
            //验证码错误
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("验证码错误");
            return;
        }

        //封装User对象userTemp
        User userTemp = new User();
        userTemp.setUsername(username);
        userTemp.setPassword(password);
        userTemp.setEmail(email);

        //调用UserService查询用户名
        UserService userService = new UserService();
        User user = userService.selectByUsername(username);

        //判断用户是否存在
        if (user == null) {
            //用户名存在，可以注册
            userService.addUser(userTemp);
            //获取字符输出流，并设置content type
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('注册成功');history.back();</script>");
        } else {
            //用户名存在，不可以注册
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户名已存在");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}