<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <!-- meta -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta property="qc:admins" content="23635710066417756375" />
    <meta name="baidu-site-verification" content="QIQ6KC1oZ6" />
    <meta charset="UTF-8">

    <meta content="拉勾，自由职业，soho，Freelancer,威客，兼职，外包，开发项目，设计项目，市场项目，运营项目，产品设计项目，找开发，找设计，找市场/运营，平面设计,UI设计,插画,logo设计,VI设计,图标设计, 海报,网页设计,app设计,包装设计，前端开发，微信开发，H5开发，后端开发，APP开发，Web网站开发，活动策划，市场推广，品牌建设，SEO，新媒体运营，活动运营，内容运营，内容编辑 ，文案策划，记者，产品经理" name="keywords">

    <meta content="拉勾自由职业者平台，为你的项目精准对接最棒的最有契约精神的行业专家，目前开放的领域有开发、设计、市场/运营、产品。我们会为你全程提供专业化、个性化的服务和监管，以让优秀专家和优质项目顺利合作为己任。我们致力于打造一个自由职业者的自由联盟平台，让专业的人都有勇气改变他们的工作和生活，轻松享受自由的工作方式。" name="description">
    <meta name="viewport" content="width=device-width"/>

    <title>登录</title>
    <link rel="stylesheet"
          href="<%=path%>/resource/static/bootstrap/css/bootstrap.min.css">
    <script src="<%=path%>/resource/static/js/jquery-3.3.1.min.js"></script>
    <script src="<%=path%>/resource/static/bootstrap/js/bootstrap.bundle.js"></script>

    <link rel="stylesheet" type="text/css" href="<%=path%>/resource/login/css/dmaku2.css" />
    <script src="<%=path%>/resource/static/js/jquery-3.3.1.min.js"></script>
    <script src="<%=path%>/resource/static/bootstrap/js/bootstrap.bundle.js"></script>
</head>
<body>

<header id="header">
    <a href="javascript:;" class="logo"></a>
    <i class="icons">beta</i>
</header><!-- /header -->

<!-- 页面主体START -->
<section id="main" style="width: 480px">
    <h1>超市库存管理系统登录</h1>
    <form id="saveForm" accept-charset="utf-8" data-view="loginView">

        <div class="clearfix" data-propertyname="username" data-controltype="Phone">
            <input type="text" placeholder="输入帐号" id="username" name="username" placeholder="" data-required="required" autocomplete="off" >
        </div>

        <div class="clearfix" data-propertyname="password" data-controltype="Password">
            <input type="password" id="password" name="password" placeholder="输入密码" data-required="required" autocomplete="off" >
        </div>

        <div class="clearfix" style="width: 150px;">
            <select name="type" class="form-control">
                <option value="0">管理员</option>
                <option value="1">入库员工</option>
                <option value="2">出库员工</option>
                <option value="3">普通员工</option>
            </select>
        </div>
  <%--      <div class="form-group form-check">
            <input type="radio" class="form-check-input" name="type" value="0" id="exampleCheck1" checked>
            <label class="form-check-label" for="exampleCheck1">管理员</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" class="form-check-input" name="type" value="1" id="exampleCheck2" >
            <label class="form-check-label" for="exampleCheck2">药房管理员</label>
            <input type="radio" class="form-check-input" name="type" value="2" id="exampleCheck3" >
            <label class="form-check-label" for="exampleCheck3">药库管理员 </label>
            <input type="radio" class="form-check-input" name="type" value="3" id="exampleCheck4" >
            <label class="form-check-label" for="exampleCheck4">医生</label>
        </div>--%>

        <div class="clearfix btn_login" data-propertyname="submit" data-controltype="Botton">
            <input type="button" style="border:0;height:46px;width:100%;color:#fff;font-size:16px;text-align:center;background:#3dca99;-webkit-border-radius:23px;-moz-border-radius:23px;border-radius:23px;margin:18px 0"  id="save" value="登录">
        </div>

     <%--   <div class="clearfix goregister">
            <span class="reg_now">没有帐号？</span>
            <a href="javascript:;"  data-target="#myModal" data-toggle="modal" >注册</a>
        </div>--%>

        <input type="hidden" value = "" id = "isVisiable_request_form_verifyCode" />

    </form>
</section>
<!-- 页面主体END -->


<div class="modal fade" id="myModal" aria-labelledby="myModallabel"
     aria-hidden="true" tabindex="-1" >
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header">
                <h5 class="modal-title" id="myModallabel">预约房间</h5>
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
            </div>
            <form id="registerForm" class="form-horizontal" enctype="multipart/form-data">
                <div class="modal-body">

                    <div class="form-group">
                        <label class="control-label col-lg-4">姓名：</label>
                        <div class="col-lg-9">
                            <input type="text" name="realname"
                                 class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-4">学号：</label>
                        <div class="col-lg-9">
                            <input type="text" name="cno"
                                    class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-4">手机号：</label>
                        <div class="col-lg-9">
                            <input type="text" name="phone"
                                    class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-4">密码：</label>
                        <div class="col-lg-9">
                            <input type="password" name="pwd"
                                    class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-4">性别：</label>
                        <div class="col-lg-9">
                            <select name="sex" class="form-control">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-4">图片：</label>
                        <div class="col-lg-9">
                            <input type="file" name="photo"  class="form-control">
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                    <a class="btn btn-success" type="button" id="register" >提交</a>
                </div>
            </form>
        </div>
    </div>
</div>





<script>


    $("#register").click(function() {
        $.ajax({
            cache:true,
            type : "post",
            url : "UserServlet?action=addUser",
            data : new FormData($('#registerForm')[0]),
            processData: false,
            contentType: false,
            async:false,
            success : function(e) {
                if (e == "yes") {
                    alert("注册成功！");
                    window.parent.location.href = "LoginServlet?action=loginOut";
                } else if (e == "isExist") {
                    alert("注册失败，学号重复");
                }else{
                    alert("注册失败");
                }
            }
        })
    });

    $("#save").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        if(username == null || username == ""){
            alert("请填写账号");
            return false;
        }if(password == null || password == ""){
            alert("请填写密码");
            return false;
        }
        //执行添加的操作ajax
        $.ajax({
            cache:true,
            type:"post",
            url:"LoginServlet?action=login",
            data:$("#saveForm").serialize(),
            async:false,
            success:function(e){
                if(e == 'ok'){
                    alert("登录成功");
                    window.parent.location.href="LoginServlet?action=toMain";
                }else if(e == "user"){
                    alert("登录成功");
                    window.parent.location.href="IndexServlet?action=toIndex";
                }else{
                    alert("登录失败，账号或密码错误");
                }
            }
        })
    });

</script>

</body>
</html>
