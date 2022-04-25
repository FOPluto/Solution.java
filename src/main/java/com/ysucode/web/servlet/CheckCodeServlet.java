package com.ysucode.web.servlet;


import com.ysucode.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 生成验证码的接口
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取response字节输出流os
        ServletOutputStream os = response.getOutputStream();

        //获取session对象 这步要放在产生验证码之前 要不然图片显示不出来
        HttpSession session = request.getSession();

        //调用util工具类产生验证码
        String checkCode = CheckCodeUtil.outputVerifyImage(97, 40, os, 4);

        //设置session
        session.setAttribute("checkcodegen", checkCode);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}