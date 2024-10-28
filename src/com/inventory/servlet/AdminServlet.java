package com.inventory.servlet;

import com.inventory.entity.Admin;
import com.inventory.service.AdminService;
import com.inventory.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet")
public class AdminServlet extends BaseServlet{

    AdminService adminService = new AdminServiceImpl();


    /**
     * 管理员修改密码页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toUpdateAdminPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/admin/updatePassword.jsp").forward(request,response);
    }


    /**
     * 修改密码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateAdminPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询个人信息
        String message = "no";
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        String nowpass=request.getParameter("nowpass");
        String newpass=request.getParameter("newpass");
        if(admin !=null && admin.getPwd().equals(nowpass)){
            adminService.updateAdminPassword(newpass,admin.getId());
            message = "yes";
        }
        response.getWriter().print(message);
    }



}
