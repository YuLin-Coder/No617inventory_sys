package com.inventory.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.entity.Admin;
import com.inventory.entity.Category;
import com.inventory.service.CategoryService;
import com.inventory.service.impl.CategoryServiceImpl;
import com.inventory.utils.WebUtils;


@WebServlet(name = "CategoryServlet")

public class CategoryServlet extends BaseServlet {
	
	
	CategoryService CategoryService = new CategoryServiceImpl();

	/**
	 * 去修改商品类别
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Category Category = CategoryService.findCategoryById(id);
		request.setAttribute("data", Category);
		request.getRequestDispatcher("/WEB-INF/view/category/update.jsp").forward(request, response);
	}

	/**
	 * 删除商品类别
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		CategoryService.deleteCategory(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加商品类别
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		Category Category = WebUtils.toBean(request.getParameterMap(), Category.class);
		CategoryService.addCategory(Category);
		message = "yes";
		response.getWriter().print(message);

	}
	
	/***
	 * 去新增商品类别
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/category/add.jsp").forward(request, response);
	}

	/**
	 * 商品类别列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void CategoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		Category Category = (Category)request.getSession().getAttribute("Category");
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<>();
		map.put("key", key);
		map.put("uid", Category!=null?Category.getId():null);
		// 调用分页查询
		List<Category> list = CategoryService.getCategoryPage(pageNum, pageSize, map);
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = CategoryService.queryCategoryCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.getSession().setAttribute("cp", pageNum); // 当前页
		request.getSession().setAttribute("tp", totalPage); // 总页数
		request.getSession().setAttribute("key", key); // 总页数
		request.getSession().setAttribute("url", "CategoryServlet?action=CategoryList"); 

		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/category/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改商品类别
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Category Category = WebUtils.toBean(request.getParameterMap(), Category.class);
		CategoryService.updateCategory(Category);
		message = "yes";
		response.getWriter().print(message);

	}

}
