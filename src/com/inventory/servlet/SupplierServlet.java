package com.inventory.servlet;

import com.inventory.entity.Supplier;
import com.inventory.service.SupplierService;
import com.inventory.service.impl.SupplierServiceImpl;
import com.inventory.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "SupplierServlet")

public class SupplierServlet extends BaseServlet {
	
	
	SupplierService SupplierService = new SupplierServiceImpl();

	/**
	 * 去修改公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Supplier Supplier = SupplierService.findSupplierById(id);
		request.setAttribute("data", Supplier);
		request.getRequestDispatcher("/WEB-INF/view/supplier/update.jsp").forward(request, response);
	}

	/**
	 * 删除公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		SupplierService.deleteSupplier(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		Supplier Supplier = WebUtils.toBean(request.getParameterMap(), Supplier.class);
		SupplierService.addSupplier(Supplier);
		message = "yes";
		response.getWriter().print(message);

	}
	
	/***
	 * 去新增公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/supplier/add.jsp").forward(request, response);
	}

	/**
	 * 公告列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void SupplierList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		Supplier Supplier = (Supplier)request.getSession().getAttribute("Supplier");
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<>();
		map.put("key", key);
		map.put("uid", Supplier!=null?Supplier.getId():null);
		// 调用分页查询
		List<Supplier> list = SupplierService.getSupplierPage(pageNum, pageSize, map);
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = SupplierService.querySupplierCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.getSession().setAttribute("cp", pageNum); // 当前页
		request.getSession().setAttribute("tp", totalPage); // 总页数
		request.getSession().setAttribute("key", key); // 总页数
		request.getSession().setAttribute("url", "SupplierServlet?action=SupplierList"); 
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/supplier/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Supplier Supplier = WebUtils.toBean(request.getParameterMap(), Supplier.class);
		SupplierService.updateSupplier(Supplier);
		message = "yes";
		response.getWriter().print(message);

	}

}
