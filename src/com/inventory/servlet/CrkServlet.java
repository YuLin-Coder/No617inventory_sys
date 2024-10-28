package com.inventory.servlet;

import com.inventory.entity.Crk;
import com.inventory.entity.Goods;
import com.inventory.service.CrkService;
import com.inventory.service.GoodsService;
import com.inventory.service.impl.CrkServiceImpl;
import com.inventory.service.impl.GoodsServiceImpl;
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


@WebServlet(name = "CrkServlet")

public class CrkServlet extends BaseServlet {
	
	
	CrkService CrkService = new CrkServiceImpl();

	GoodsService GoodsService = new GoodsServiceImpl();

	Map<String,Object> param = new HashMap<>();

	/**
	 * 去修改出入库
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateCrk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Crk Crk = CrkService.findCrkById(id);
		request.setAttribute("data", Crk);
		List<Goods> goodsList = GoodsService.selectGoodsByMap(param);
		request.setAttribute("goodsList", goodsList);
		request.getRequestDispatcher("/WEB-INF/view/crk/update.jsp").forward(request, response);
	}

	/**
	 * 删除出入库
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteCrk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		CrkService.deleteCrk(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加出入库
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addCrk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		Crk Crk = WebUtils.toBean(request.getParameterMap(), Crk.class);
		Crk.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		Goods goods = GoodsService.findGoodsById(Crk.getGid().toString());
		if(Crk.getType().equals("1")){
			goods.setAmount(goods.getAmount()+Crk.getNums());
			CrkService.addCrk(Crk);
			GoodsService.updateGoodsAmout(goods);
			message = "yes";
		}else if(Crk.getType().equals("2")){
			if(Crk.getNums() > goods.getAmount()){
				message = "null";
			}else{
				goods.setAmount(goods.getAmount()-Crk.getNums());
				CrkService.addCrk(Crk);
				GoodsService.updateGoodsAmout(goods);
				message = "yes";
			}
		}
		response.getWriter().print(message);

	}
	
	/***
	 * 去新增出入库
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddCrkRk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Goods> goodsList = GoodsService.selectGoodsByMap(param);
		request.setAttribute("goodsList", goodsList);
		request.getRequestDispatcher("/WEB-INF/view/crk/add_rk.jsp").forward(request, response);
	}

	/***
	 * 去新增出出库
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddCrkCk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Goods> goodsList = GoodsService.selectGoodsByMap(param);
		request.setAttribute("goodsList", goodsList);
		request.getRequestDispatcher("/WEB-INF/view/crk/add_ck.jsp").forward(request, response);
	}

	/**
	 * 出入库列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void CrkList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		Crk Crk = (Crk)request.getSession().getAttribute("Crk");
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<>();
		map.put("key", key);
		map.put("uid", Crk!=null?Crk.getId():null);
		// 调用分页查询
		List<Crk> list = CrkService.getCrkPage(pageNum, pageSize, map);
		for(Crk crk :list){
			crk.setGoods(GoodsService.findGoodsById(crk.getGid().toString()));
		}
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = CrkService.queryCrkCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.getSession().setAttribute("cp", pageNum); // 当前页
		request.getSession().setAttribute("tp", totalPage); // 总页数
		request.getSession().setAttribute("key", key); // 总页数
		request.getSession().setAttribute("url", "CrkServlet?action=CrkList");
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/crk/list.jsp").forward(request, response); // 页面转发
	}


	/**
	 * 出入库列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void CrkCkList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		Crk Crk = (Crk)request.getSession().getAttribute("Crk");
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<>();
		map.put("key", key);
		map.put("type", "2");
		map.put("uid", Crk!=null?Crk.getId():null);
		// 调用分页查询
		List<Crk> list = CrkService.getCrkPage(pageNum, pageSize, map);
		for(Crk crk :list){
			crk.setGoods(GoodsService.findGoodsById(crk.getGid().toString()));
		}
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = CrkService.queryCrkCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.getSession().setAttribute("cp", pageNum); // 当前页
		request.getSession().setAttribute("tp", totalPage); // 总页数
		request.getSession().setAttribute("key", key); // 总页数
		request.getSession().setAttribute("url", "CrkServlet?action=CrkCkList");
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/crk/list.jsp").forward(request, response); // 页面转发
	}


	/**
	 * 入库库列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void CrkRkList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		Crk Crk = (Crk)request.getSession().getAttribute("Crk");
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<>();
		map.put("key", key);
		map.put("type", "1");
		map.put("uid", Crk!=null?Crk.getId():null);
		// 调用分页查询
		List<Crk> list = CrkService.getCrkPage(pageNum, pageSize, map);
		for(Crk crk :list){
			crk.setGoods(GoodsService.findGoodsById(crk.getGid().toString()));
		}
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = CrkService.queryCrkCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.getSession().setAttribute("cp", pageNum); // 当前页
		request.getSession().setAttribute("tp", totalPage); // 总页数
		request.getSession().setAttribute("key", key); // 总页数
		request.getSession().setAttribute("url", "CrkServlet?action=CrkRkList");
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/crk/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改出入库
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCrk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Crk Crk = WebUtils.toBean(request.getParameterMap(), Crk.class);
		CrkService.updateCrk(Crk);
		message = "yes";
		response.getWriter().print(message);

	}

}
