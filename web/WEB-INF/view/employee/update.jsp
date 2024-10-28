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
	content="width=device-width, data-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>添加员工</title>
<link rel="icon" href="<%=path%>/resource/static/favicon.ico">
<link rel="stylesheet"
	href="<%=path%>/resource/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=path%>/resource/static/admin/css/index.css">
<link rel="stylesheet"
	href="<%=path%>/resource/static/admin/css/main.css">
<link rel="stylesheet"
	href="<%=path%>/layui/css/layui.css">
<link rel="stylesheet"
	href="<%=path%>/resource/static/admin/css/html.css">
<script src="<%=path%>/resource/static/js/vue.min.js"></script>
<script src="<%=path%>/resource/static/js/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/resource/static/bootstrap/js/bootstrap.bundle.js"></script>
<script src="<%=path%>/resource/static/admin/js/config.js"></script>
<script src="<%=path%>/resource/static/admin/js/script.js"></script>
<script src="<%=path%>/layui/layui.js"></script>
<script>
	$(function() {
		const hrefArr = window.location.href.split('/');
		const name = hrefArr[hrefArr.length - 1];
		$('.menu a[href=\'' + name + '\']').addClass('active');
	});
</script>
</head>
<body>

	<div id="app" class="d-flex">
		<jsp:include page="/WEB-INF/common/line.jsp" />

		<main>
			<jsp:include page="/WEB-INF/common/head.jsp" />

			<div id="body">


				<main>
					<div class="main">

						<div class="title-box">
							<h5>修改员工</h5>
						</div>
						<div >
							<div class="row">
								<div class="col-5">
									<form id="saveForm" >
									<input type="hidden" name="id" value="${data.id }">

										<div class="form-group">
											<label>工号</label> <input type="text" name="eno" value="${data.eno }" readonly
																	 class="form-control"> <small
												class="form-text text-muted"></small>
										</div>


										<div class="form-group">
											<label>姓名</label> <input type="text" name="realname" value="${data.id }"
																	 class="form-control"> <small
												class="form-text text-muted"></small>
										</div>


										<div class="form-group">
											<label>密码</label> <input type="pwd" name="pwd" value="${data.pwd }"
																	 class="form-control"> <small
												class="form-text text-muted"></small>
										</div>

										<div class="form-group">
											<label>手机号</label> <input type="text" name="phone" value="${data.phone }"
																	  class="form-control"> <small
												class="form-text text-muted"></small>
										</div>


										<div class="form-group">
											<label>性别</label> <select name="sex" class="form-control">
											<option value="男" <c:if test="${data.sex == '男'}">selected</c:if> >男</option>
											<option value="女" <c:if test="${data.sex == '女'}">selected</c:if> >女</option>
										</select> <small class="form-text text-muted"></small>
										</div>

										<div class="form-group">
											<label>工作内容</label> <input type="text" name="work" value="${data.work }"
																	   class="form-control"> <small
												class="form-text text-muted"></small>
										</div>

									</form>
								</div>
							</div>
						</div>

						<div class="submit-box">
							<button type="button" id="save" class="btn btn-primary">确定提交</button>
							<a type="button" href="EmployeeServlet?action=EmployeeList" class="btn btn-outline-secondary">返回</a>
						</div>
					</div>
				</main>
			</div>
		</main>
	</div>
	<script>
		$("#save").click(function() {
			$.ajax({
				cache : true,
				type : "post",
				url : "EmployeeServlet?action=updateEmployee",
				// data : new FormData($('#saveForm')[0]),
				// processData: false,
				// contentType: false,
				data : $("#saveForm").serialize(),
				async : false,
				success : function(e) {
					if (e == 'yes') {
						alert("修改成功！");
						window.parent.location.href = "EmployeeServlet?action=EmployeeList";
					} else {
						alert("修改失败！");
					}
				}
			})
		});
	</script>
	
		
<script>


	$("#tx").change(function () {
		//创建blob对象，浏览器将文件放入内存中，并生成标识
		var img_src = URL.createObjectURL($(this)[0].files[0]);
		//给img标检的src赋值
		document.getElementById("demo1").src=img_src;
		//URL.revokeObjectURL(img_src);// 手动 回收，
	});
</script>
</body>
</html>
