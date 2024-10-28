package com.inventory.servlet;

import com.inventory.entity.Admin;
import com.inventory.entity.Employee;
import com.inventory.entity.Supplier;
import com.inventory.service.AdminService;
import com.inventory.service.EmployeeService;
import com.inventory.service.SupplierService;
import com.inventory.service.impl.AdminServiceImpl;
import com.inventory.service.impl.EmployeeServiceImpl;
import com.inventory.service.impl.SupplierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "LoginServlet")
public class LoginServlet extends BaseServlet {

    AdminService adminService = new AdminServiceImpl();

    EmployeeService employeeService= new EmployeeServiceImpl();

    /**
     * 退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request,response);
    }

    /**
     * 默认页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void console(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/console.jsp").forward(request,response);
    }


    /**
     * 登录页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request,response);
    }

    /**
     * 主页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/echarts.jsp").forward(request,response);
    }

    /**
     * 去注册页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request,response);
    }

    /**
     * 去注册页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toEcharts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/echarts.jsp").forward(request,response);
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理登录
        //清空登录记录
        request.getSession().invalidate();
        //进行登录操作
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        Admin admin = new Admin();
        if(type != null && type.equals("0")){
            Admin admin1 = adminService.selectAdmin(username,password);
            if(admin1 != null){
                response.getWriter().print("ok");
                request.getSession().setAttribute("admin",admin1);
                request.getSession().setAttribute("flag",type);
            }else{
                response.getWriter().print("error");
            }
        }else if(type != null && !type.equals("0")){//doctorService
            Employee employee = employeeService.selectEmployee(username,password,type);
            if(employee != null){
                response.getWriter().print("ok");
                request.getSession().setAttribute("employee",employee);
                request.getSession().setAttribute("flag",type);
            }else{
                response.getWriter().print("error");
            }
        }else{
            response.getWriter().print("error");
        }

    }

}
