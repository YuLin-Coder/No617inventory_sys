package com.inventory.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    private String errorPage="index.jsp";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();

        Object flag = session.getAttribute("flag");
        PrintWriter out = response.getWriter();
        if(flag != null){

            chain.doFilter(request,response);

        } else{

            out.println("您还未登陆，三秒钟后跳转至登录页面");

            //out.println("<script language='javascript'>alert('你还未登录');");

            response.setHeader("refresh","3;/WEB-INF/view/login.jsp");

            //response.sendRedirect("/pages/users/login.jsp");

            //request.getRequestDispatcher("/pages/users/login.jsp").forward(request,response);

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
