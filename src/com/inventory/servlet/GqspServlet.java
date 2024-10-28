package com.inventory.servlet;

import com.inventory.entity.Category;
import com.inventory.entity.Gqsp;
import com.inventory.entity.Supplier;
import com.inventory.service.CategoryService;
import com.inventory.service.GqspService;
import com.inventory.service.SupplierService;
import com.inventory.service.impl.CategoryServiceImpl;
import com.inventory.service.impl.GqspServiceImpl;
import com.inventory.service.impl.SupplierServiceImpl;
import com.inventory.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "GqspServlet")

public class GqspServlet extends BaseServlet {
	
	
	GqspService GqspService = new GqspServiceImpl();

	CategoryService CategoryService = new CategoryServiceImpl();

	SupplierService SupplierService = new SupplierServiceImpl();

	Map<String,Object> param = new HashMap<>();

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 去修改过期商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateGqsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Gqsp Gqsp = GqspService.findGqspById(id);
		request.setAttribute("data", Gqsp);
		List<Category> categoryList = CategoryService.selectCategoryByMap(param);
		List<Supplier> supplierList = SupplierService.selectSupplierByMap(param);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("supplierList", supplierList);
		request.getRequestDispatcher("/WEB-INF/view/gqsp/update.jsp").forward(request, response);
	}

	/**
	 * 删除过期商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteGqsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		GqspService.deleteGqsp(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加过期商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addGqsp(HttpServletRequest request, HttpServletResponse response)
			throws Exception {// 处理注册
		String message = "no";
		Gqsp Gqsp = WebUtils.toBean(request.getParameterMap(), Gqsp.class);
		Gqsp.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		Gqsp.setOver_date(format.format(addDate(format.parse(Gqsp.getCreate_date()),Gqsp.getBzq()*30L)));
		GqspService.addGqsp(Gqsp);
		message = "yes";
		response.getWriter().print(message);
	}

	//注意day 必须是long类型 否者会超精度影响结果
	public static Date addDate(Date date,long day) {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time+=day; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}
	
	/***
	 * 去新增过期商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddGqsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categoryList = CategoryService.selectCategoryByMap(param);
		List<Supplier> supplierList = SupplierService.selectSupplierByMap(param);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("supplierList", supplierList);
		request.getRequestDispatcher("/WEB-INF/view/gqsp/add.jsp").forward(request, response);
	}

	/**
	 * 过期商品列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void GqspList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		Gqsp Gqsp = (Gqsp)request.getSession().getAttribute("Gqsp");
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<>();
		map.put("key", key);
		map.put("uid", Gqsp!=null?Gqsp.getId():null);
		// 调用分页查询
		List<Gqsp> list = GqspService.getGqspPage(pageNum, pageSize, map);
		for(Gqsp gqsp : list){
			gqsp.setSupplier(SupplierService.findSupplierById(gqsp.getSid().toString()));
			gqsp.setCategory(CategoryService.findCategoryById(gqsp.getCid().toString()));
		}
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = GqspService.queryGqspCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.getSession().setAttribute("cp", pageNum); // 当前页
		request.getSession().setAttribute("tp", totalPage); // 总页数
		request.getSession().setAttribute("key", key); // 总页数
		request.getSession().setAttribute("url", "GqspServlet?action=GqspList"); 

		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/gqsp/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改过期商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateGqsp(HttpServletRequest request, HttpServletResponse response)
			throws Exception {// 查询个人信息
		String message = "no";
		Gqsp Gqsp = WebUtils.toBean(request.getParameterMap(), Gqsp.class);
		Gqsp.setOver_date(format.format(addDate(format.parse(Gqsp.getCreate_date()),Gqsp.getBzq()*30L)));
		GqspService.updateGqsp(Gqsp);
		message = "yes";
		response.getWriter().print(message);

	}

}
