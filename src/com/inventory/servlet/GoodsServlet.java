package com.inventory.servlet;

import com.inventory.entity.*;
import com.inventory.service.CategoryService;
import com.inventory.service.GoodsService;
import com.inventory.service.SupplierService;
import com.inventory.service.impl.CategoryServiceImpl;
import com.inventory.service.impl.GoodsServiceImpl;
import com.inventory.service.impl.SupplierServiceImpl;
import com.inventory.utils.WebUtils;
import org.json.JSONArray;

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


@WebServlet(name = "GoodsServlet")

public class GoodsServlet extends BaseServlet {
	
	
	GoodsService GoodsService = new GoodsServiceImpl();

	CategoryService CategoryService = new CategoryServiceImpl();

	SupplierService SupplierService = new SupplierServiceImpl();

	Map<String,Object> param = new HashMap<>();

	/**
	 * 去修改商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Goods Goods = GoodsService.findGoodsById(id);
		request.setAttribute("data", Goods);
		List<Category> categoryList = CategoryService.selectCategoryByMap(param);
		List<Supplier> supplierList = SupplierService.selectSupplierByMap(param);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("supplierList", supplierList);
		request.getRequestDispatcher("/WEB-INF/view/goods/update.jsp").forward(request, response);
	}

	/**
	 * 删除商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		GoodsService.deleteGoods(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		Goods Goods = WebUtils.toBean(request.getParameterMap(), Goods.class);
		Goods.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		GoodsService.addGoods(Goods);
		message = "yes";
		response.getWriter().print(message);

	}
	
	/***
	 * 去新增商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categoryList = CategoryService.selectCategoryByMap(param);
		List<Supplier> supplierList = SupplierService.selectSupplierByMap(param);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("supplierList", supplierList);
		request.getRequestDispatcher("/WEB-INF/view/goods/add.jsp").forward(request, response);
	}

	/**
	 * 商品列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void GoodsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		Goods Goods = (Goods)request.getSession().getAttribute("Goods");
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<>();
		map.put("key", key);
		map.put("uid", Goods!=null?Goods.getId():null);
		// 调用分页查询
		List<Goods> list = GoodsService.getGoodsPage(pageNum, pageSize, map);
		for(Goods goods : list){
			goods.setSupplier(SupplierService.findSupplierById(goods.getSid().toString()));
			goods.setCategory(CategoryService.findCategoryById(goods.getCid().toString()));
		}
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = GoodsService.queryGoodsCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.getSession().setAttribute("cp", pageNum); // 当前页
		request.getSession().setAttribute("tp", totalPage); // 总页数
		request.getSession().setAttribute("key", key); // 总页数
		request.getSession().setAttribute("url", "GoodsServlet?action=GoodsList"); 

		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/goods/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Goods Goods = WebUtils.toBean(request.getParameterMap(), Goods.class);
		GoodsService.updateGoods(Goods);
		message = "yes";
		response.getWriter().print(message);

	}



	/**
	 * 商品统计
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectGoodsStatic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		List<Statics> list = GoodsService.selectGoodsStatic();
		JSONArray jsonArray = new JSONArray();
		for(Statics ss : list){
			Map map = new HashMap();
			map.put("message", ss.getMessage());
			map.put("counts", ss.getCounts());
			jsonArray.put(map);
		}
		response.setContentType("text/html;charset=utf-8");//改编码
		response.getWriter().print(jsonArray);//把json字符串返回的页面
	}



	/**
	 * 出库统计
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectCrkCkStatic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		List<Statics> list = GoodsService.selectCrkCkStatic();
		JSONArray jsonArray = new JSONArray();
		for(Statics ss : list){
			Map map = new HashMap();
			map.put("message", ss.getMessage());
			map.put("counts", ss.getCounts());
			jsonArray.put(map);
		}
		response.setContentType("text/html;charset=utf-8");//改编码
		response.getWriter().print(jsonArray);//把json字符串返回的页面
	}


	/**
	 * 入库统计
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectCrkRkStatic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		List<Statics> list = GoodsService.selectCrkRkStatic();
		JSONArray jsonArray = new JSONArray();
		for(Statics ss : list){
			Map map = new HashMap();
			map.put("message", ss.getMessage());
			map.put("counts", ss.getCounts());
			jsonArray.put(map);
		}
		response.setContentType("text/html;charset=utf-8");//改编码
		response.getWriter().print(jsonArray);//把json字符串返回的页面
	}
}
