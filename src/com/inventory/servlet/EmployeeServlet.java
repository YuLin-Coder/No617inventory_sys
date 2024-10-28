package com.inventory.servlet;

import com.inventory.entity.Employee;
import com.inventory.service.EmployeeService;
import com.inventory.service.impl.EmployeeServiceImpl;
import com.inventory.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "EmployeeServlet")
public class EmployeeServlet extends BaseServlet{

    EmployeeService employeeService = new EmployeeServiceImpl();


    /**
     * 去修改员工页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toUpdateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        Employee employee = employeeService.findEmployeeById(id);
        request.setAttribute("data" ,employee);
        request.getRequestDispatcher("/WEB-INF/view/employee/update.jsp").forward(request,response);
    }


    /**
     * 删除员工
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "no";
        String id=request.getParameter("id");
        employeeService.deleteEmployee(id);
        message = "yes";
        response.getWriter().print(message);

    }

    /**
     * 添加员工
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
        String message = "no";
       // Employee employee = fileUpload(request);
        Employee employee = WebUtils.toBean(request.getParameterMap(), Employee.class);
        Employee employees = employeeService.selectEmployeeByKey(employee);
        if(employees == null){
            employeeService.addEmployee(employee);
            message = "yes";
        }else{
            message = "is";
        }
        response.getWriter().print(message);

    }

    /**
     * 上传文件工具
     * @param request
     * @return
     */
//    private Employee fileUpload(HttpServletRequest request) {
//        Employee employee=new Employee();
//        try {
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            List<FileItem> list = upload.parseRequest(request);
//            for(FileItem item : list){
//                if(item.isFormField()){
//                    String name = item.getFieldName();
//                    String value = item.getString("UTF-8");
//                    BeanUtils.setProperty(employee, name, value);
//                }else{
//                    String filename = item.getName();
//                    if(filename != null && !filename.equals("")){
//                        String savefilename = makeFileName(filename);
//                        String savepath="D:\\upload\\";
//                        InputStream in = item.getInputStream();
//                        FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
//                        int len = 0;
//                        byte buffer[] = new byte[1024];
//                        while((len = in.read(buffer)) > 0){
//                            out.write(buffer, 0, len);
//                        }
//                        in.close();
//                        out.close();
//                        item.delete();
//                        employee.setPhoto(savefilename);
//                    }
//                }
//            }
//            return employee;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    private String makeFileName(String filename) {
//        String ext = filename.substring(filename.lastIndexOf(".") + 1);
//        return UUID.randomUUID().toString() + "." + ext;
//    }

    /**
     * 去新增员工页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toAddEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/employee/add.jsp").forward(request,response);
    }

    /**
     * 分页查询员工
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void EmployeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//跳转到社长列表界面
        String p=request.getParameter("p");//接收页码
        String key=request.getParameter("key");//接收页码
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        System.out.print(p);
        int pageSize=6;//每页显示5条
        int pageNum=1; //默认第一页
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        Map map = new HashMap<>();
        map.put("key",key);
        map.put("uid",employee!=null?employee.getId():null);
        //调用分页查询
        List<Employee> list=employeeService.getEmployeePage(pageNum,pageSize,map);
        //携带参数到页面
        request.setAttribute("list",list); //绑定参数
        int nums=employeeService.queryEmployeeCount(map); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.getSession().setAttribute("cp",pageNum); //当前页
        request.getSession().setAttribute("tp",totalPage); //总页数
        request.getSession().setAttribute("key",key); //总页数
        request.getSession().setAttribute("url","EmployeeServlet?action=EmployeeList");
        //条件 值1：值2
        request.getRequestDispatcher("/WEB-INF/view/employee/list.jsp").forward(request,response); //页面转发
    }

    /**
     * 修改员工
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询个人信息
        String message = "no";
       // Employee employee = fileUpload(request);
        Employee employee = WebUtils.toBean(request.getParameterMap(), Employee.class);
        employeeService.updateEmployee(employee);
        message = "yes";
        response.getWriter().print(message);

    }

}
