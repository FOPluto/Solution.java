package com.ysucode.web.filter;

import com.ysucode.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证是否登录的过滤器
 */

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //和登录注册相关资源路径
        String[] urls = {"/index.html", "/imgs/", "/loginServlet", "/registerServlet", "/checkCodeServlet"};
        //获取当前访问路径
        String url = req.getRequestURL().toString();
        //循环判断
        for (String u : urls) {
            if (url.contains(u)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        //判断session是否有user
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        //判断user是否为空
        if (user != null) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
            //System.out.println("123");
        } else {
            //没有登陆，存储登录信息，跳转到登录页面
            //req.setAttribute("login_msg", "您尚未登录！");
            req.getRequestDispatcher("/index.html").forward(req, servletResponse);
        }

        //放行后，对servletResponse数据处理
    }

    public LoginFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
