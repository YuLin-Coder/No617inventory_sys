<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>商品类别列表</title>
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
<script>
	$(function() {
		const hrefArr = window.location.href.split('/');
		const name = hrefArr[hrefArr.length - 1];
		$('.menu a[href=\'' + name + '\']').addClass('active');
	});
</script>
<style>
	th,tr,td{    border: 1px solid #ced4da;}
</style>
</head>
<body>

	<div id="app" class="d-flex">
		<jsp:include page="/WEB-INF/common/line.jsp" />
		<main>
			<jsp:include page="/WEB-INF/common/head.jsp" />
			<div id="body">
				<main>
					<div class="main">
						<div class="search">
							<form class="form-inline float-left" action="CategoryServlet?action=CategoryList"
								method="post">
								<div class="form-group">
									<input type="text" name="key" value="${key}"
										class="form-control" placeholder="商品类别标题">
								</div>
								<button type="submit" class="btn btn-primary">
									<i class="iconfont"></i>搜索
								</button>
							</form>
							<!-- <a class="btn btn-primary float-right" href="user_create.html"><i
								class="iconfont"></i>新增</a> -->
						</div>

						<div >
							<table class="table">
								<thead>
									<tr>
										<th scope="col">商品类别</th>
										<th scope="col">备注</th>
										<th scope="col">操作</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${list}" var="data">
										<tr>
											<td>${data.name }</td>
											<td>${data.note }</td>
											<td>
											<input id ="${data.id}" value="${data.id}" type="hidden" class="weui-input"/>
											<a class="btn btn-primary btn-sm"
												href="CategoryServlet?action=toUpdateCategory&id=${data.id }">修改</a>
												<a  class="btn btn-danger btn-sm delete" href="javascript:;">删除</a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
						
						<jsp:include page="/WEB-INF/common/page.jsp" />
					</div>
				</main>
			</div>

		</main>
	</div>
	<script>
	
	   $('a.delete').click(function(e) 
   		        {
   		            var id = $(e.currentTarget).parent().find('.weui-input').attr('id');
			   		if(confirm("确定删除吗?")){
			 		 　 $.ajax({
						    cache:true,
							url:"CategoryServlet?action=deleteCategory",
						  	data:{"id": id},
							type:"post",
						    async:false,
						  	success:function (data) {
								console.log(data)
							 	if (data == 'yes') {
									alert("删除成功！");
									document.location.reload();//当前页面
							  	}else{
							  		alert("删除失败！");
							  	}
						 	}  
					 	}); 
			 		}

   		        })
	
	</script>
</body>
</html>
