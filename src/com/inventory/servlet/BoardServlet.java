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
import com.inventory.entity.Board;
import com.inventory.service.BoardService;
import com.inventory.service.impl.BoardServiceImpl;
import com.inventory.utils.WebUtils;


@WebServlet(name = "BoardServlet")

public class BoardServlet extends BaseServlet {
	
	
	BoardService BoardService = new BoardServiceImpl();

	/**
	 * 去修改公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Board Board = BoardService.findBoardById(id);
		request.setAttribute("data", Board);
		request.getRequestDispatcher("/WEB-INF/view/board/update.jsp").forward(request, response);
	}

	/**
	 * 删除公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		BoardService.deleteBoard(id);
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
	protected void addBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		Board Board = WebUtils.toBean(request.getParameterMap(), Board.class);
		Board.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		BoardService.addBoard(Board);
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
	protected void toAddBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/add.jsp").forward(request, response);
	}

	/**
	 * 公告列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void BoardList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		Board Board = (Board)request.getSession().getAttribute("Board");
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<>();
		map.put("key", key);
		map.put("uid", Board!=null?Board.getId():null);
		// 调用分页查询
		List<Board> list = BoardService.getBoardPage(pageNum, pageSize, map);
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = BoardService.queryBoardCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.getSession().setAttribute("cp", pageNum); // 当前页
		request.getSession().setAttribute("tp", totalPage); // 总页数
		request.getSession().setAttribute("key", key); // 总页数
		request.getSession().setAttribute("url", "BoardServlet?action=BoardList"); 
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/board/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Board Board = WebUtils.toBean(request.getParameterMap(), Board.class);
		BoardService.updateBoard(Board);
		message = "yes";
		response.getWriter().print(message);

	}

}
