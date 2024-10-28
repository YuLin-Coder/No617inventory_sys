<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>OA管理系统</title>
<link rel="icon" href="favicon.ico" type="image/ico">
<meta name="keywords" content="后台管理系统HTML模板">
<meta name="description" content="基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
<meta name="author" content="yinqi">
<link href="<%=path%>/resource/boot/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="icon" href="<%=path%>/resource/static/favicon.ico">
<link rel="stylesheet"
	href="<%=path%>/resource/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=path%>/resource/static/admin/css/index.css">
<link rel="stylesheet"
	href="<%=path%>/resource/static/admin/css/main.css">
<link rel="stylesheet"
	href="<%=path%>/resource/static/admin/css/html.css">
<script src="<%=path%>/resource/static/js/vue.min.js"></script>
<script src="<%=path%>/resource/static/js/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/resource/static/bootstrap/js/bootstrap.bundle.js"></script>
<script src="<%=path%>/resource/static/admin/js/config.js"></script>
<script src="<%=path%>/resource/static/admin/js/script.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/My97DatePicker/WdatePicker.js"></script>
<script>
	$(function() {
		const hrefArr = window.location.href.split('/');
		const name = hrefArr[hrefArr.length - 1];
		$('.menu a[href=\'' + name + '\']').addClass('active');
	});
</script>
<style>
.main {
	overflow-y: auto;
}

.info .card {
	min-height: 330px;
}

.user .card {
	min-height: 500px;
}

.card h5 {
	margin-bottom: 20px;
}

.main ul li {
	margin-bottom: 3px;
}
</style>
<!-- <style>
td {
	text-align: center;
}

th {
	text-align: center;
}
</style> -->
</head>
<nav>
	<div class="logo">
		<h5>超市库存管理系统</h5>
	</div>
	<ul class="menu">
		<li><a href="LoginServlet?action=toMain"><i class="iconfont mr-1">&#xe6a2;</i>首页</a></li>
		<c:if test="${flag == '0' }">

		   <li><a class="top-menu" href="javascript:;"><i
				class="iconfont mr-1">&#xe6e0;</i>员工管理<i
				class="iconfont arrow float-right">&#xe66c;</i></a>
			<ul class="sub-menu">
				<li><a href="EmployeeServlet?action=EmployeeList">员工列表</a></li>
				<li><a href="EmployeeServlet?action=toAddEmployee">添加员工</a></li>
			</ul></li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>公告管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="BoardServlet?action=BoardList">公告列表</a></li>
					<li><a href="BoardServlet?action=toAddBoard">添加公告</a></li>
				</ul>
			</li>


			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>商品类别管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="CategoryServlet?action=CategoryList">商品类别列表</a></li>
					<li><a href="CategoryServlet?action=toAddCategory">添加商品类别</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>供应商管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="SupplierServlet?action=SupplierList">供应商列表</a></li>
					<li><a href="SupplierServlet?action=toAddSupplier">添加供应商</a></li>
				</ul>
			</li>
			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>商品管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="GoodsServlet?action=GoodsList">商品列表</a></li>
					<li><a href="GoodsServlet?action=toAddGoods">添加商品</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>过期商品管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="GqspServlet?action=GqspList">过期商品列表</a></li>
				</ul>
			</li>


			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>出库入库管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="CrkServlet?action=CrkList">出库入库列表</a></li>
				</ul>
			</li>


			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe6e0;</i>个人信息<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="AdminServlet?action=toUpdateAdminPass">修改密码</a></li>
				</ul></li>

		</c:if>

		<c:if test="${flag == '1' }">

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe6e0;</i>员工管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="EmployeeServlet?action=EmployeeList">员工列表</a></li>
				</ul></li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>公告管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="BoardServlet?action=BoardList">查看公告</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>出库入库管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="CrkServlet?action=CrkRkList">入库列表</a></li>
					<li><a href="CrkServlet?action=toAddCrkRk">入库</a></li>
				</ul>
			</li>

		</c:if>



		<c:if test="${flag == '2' }">

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe6e0;</i>员工管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="EmployeeServlet?action=EmployeeList">员工列表</a></li>
				</ul></li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>公告管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="BoardServlet?action=BoardList">查看公告</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>出库入库管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="CrkServlet?action=CrkCkList">出库列表</a></li>
					<li><a href="CrkServlet?action=toAddCrkCk">出库</a></li>
				</ul>
			</li>



		</c:if>


		<c:if test="${flag == '3' }">

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe6e0;</i>员工管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="EmployeeServlet?action=EmployeeList">员工列表</a></li>
				</ul></li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>公告管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="BoardServlet?action=BoardList">查看公告</a></li>
				</ul>
			</li>


			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>商品管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="GoodsServlet?action=GoodsList">商品列表</a></li>
					<li><a href="GoodsServlet?action=toAddGoods">添加商品</a></li>
				</ul>
			</li>

			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>过期商品管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="GqspServlet?action=GqspList">过期商品列表</a></li>
					<li><a href="GqspServlet?action=toAddGqsp">添加过期商品</a></li>
				</ul>
			</li>


			<li><a class="top-menu" href="javascript:;"><i
					class="iconfont mr-1">&#xe69d;</i>出库入库管理<i
					class="iconfont arrow float-right">&#xe66c;</i></a>
				<ul class="sub-menu">
					<li><a href="CrkServlet?action=CrkList">出库入库列表</a></li>
				</ul>
			</li>

		</c:if>

		<li><a href="LoginServlet?action=loginOut"><i class="iconfont mr-1">&#xe68c;</i>退出登录</a>
		</li> 
	</ul>
</nav>