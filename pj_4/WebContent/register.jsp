<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style type="text/css">
html,body{
width:100%;
height:100%;
}
.registerForm{
width:30%;
margin:auto ;
margin-top:50px;
}
#submit{
width:100%;
margin-top:15px;
}
</style>

</head>
<body>
	<header>
		<%--导航栏 --%>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixedNavbar">
			<%--首頁導航欄 --%>
			<a class="navbar-brand text-info" href="homepage.jsp">课程网</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">

					<%--未登录时 导航栏 --%>
					<%
						if (session.getAttribute("username") == null) {
					%>
					<li class="nav-item"><a class="nav-link text-primary"
						href="login.jsp?page=homepage.jsp">登陆</a></li>
					<li class="nav-item"><a class="nav-link text-primary"
						href="register.jsp">注册</a></li>
					<%
						}
					%>
					<%--登陆时导航栏：获取username session 和logout服务--%>
					<%
						if (session.getAttribute("username") != null) {
					%>
					<li class="nav-item"><a class="nav-link text-success" href="#"><%=session.getAttribute("username")%></a>
					</li>
					<li class="nav-item"><a class="nav-link text-success"
						href="logoutServlet?page=homepage.jsp">登出</a></li>
					<%
						}
					%>
				</ul>
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search"> 
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>

<div class="registerForm">
	<form action="registerServlet" method="post" name="register">
		<div class="form-group">
			<label>用戶名</label> <input type="text" class="form-control"
				 name="username" id="username">
		</div>
		<div class="form-group">
			<label>密碼</label> <input type="password" class="form-control"
			 name="password" id="password"> <small class="form-text text-muted"
				id="passwordError">密码不能为纯数字，长度大于等于6位</small>
		</div>
		<div class="form-group">
			<label>重複密碼</label> <input type="password" 
				class="form-control" name="passwordRepeat" id="passwordRepeat">
		</div>
		<div class="form-group">
			<label>郵箱</label> <input type="email" class="form-control"
				aria-describedby="emailHelp"  name="email" id="email">
			<small class="form-text text-muted">我們不會泄露你的郵箱信息</small>
		</div>
		<div class="form-group">
			<label>電話</label> <input type="number" class="form-control"
				name="phone" id="phone">
		</div>
		<input type="submit" value="submit" id="submit" class="btn btn-primary"> 
		<small class="form-text text-danger" id="emptyError">所有输入项不能为空</small>  
		<small class="form-text text-danger" id="passwordRepeatError">重复密码与密码不一致</small>
	</form>
<hr>
<p id="register"></p>
	</div>

<script type="text/javascript">
$(function(){
	$("#emptyError").hide();
	$("#passwordRepeatError").hide();
	function checkEmpty(){
		if($("#username").val() == ""){
			return true;
		}
		if($("#password").val() == ""){
			return true;
		}
		if($("#passwordRepeat").val() == ""){
			return true;
		}
		if($("#email").val() == ""){
			return true;
		}
		if($("#phone").val() == ""){
			return true;
		}
		return false;
	}
	
	function checkPassword(){
		var pattern = /^\d+$/;
		var password = $("#password").val();
		var result = password.match(pattern);
		if(result != null){
			//是纯数字
			return false;
		}
		else{
			//不是纯数字
			pattern = /^.{6,}$/;
			result = password.match(pattern);
			if(result == null){
				//小于六位
				return false;
			}
			else{
				//大于等于六位
				return true;
			}
		}
	}
	
	function checkPasswordRepeat(){
		var password = $("#password").val();
		var passwordRepeat = $("#passwordRepeat").val();
		if(password == passwordRepeat){
			return false;
		}
		else{
			return true;
		}
	}
	
	$("#submit").click(function(e){
	if(checkEmpty()){
		$("#passwordError").attr("class","form-text text-muted");
		$("#emptyError").show();
		e.preventDefault();
		return;
	}
	else{
		$("#emptyError").hide();
		if(!checkPassword()){
			$("#passwordError").attr("class","form-text text-danger");
			e.preventDefault();
			return;
		}
		else{
			$("#passwordError").attr("class","form-text text-muted");
			if(checkPasswordRepeat()){
				$("#passwordRepeatError").show();
				e.preventDefault();
			}
		}
	}
	});
	
	
	//注冊成功，請激活郵件
	<%
	if(request.getParameter("register") != null && request.getParameter("register").equals("1")){
		%>
		$("#register").html("注冊成功，請前往郵箱激活賬號");
		$("#register").css("color","green");
		<%
	}
	%>
	//注冊失敗，請另選用戶名
	<%
	if(request.getParameter("register") != null && request.getParameter("register").equals("0")){
		%>
		$("#register").html("該用戶名已被注冊，請另選用戶名");
		$("#register").css("color","red");
		<%
	}
	%>
});

</script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>