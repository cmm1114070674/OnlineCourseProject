<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style type="text/css">
html,body{
width:100%;
height:100%;
}
.loginForm{
width:30%;
height:30%;
margin:auto ;
margin-top:150px;
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
						href="login.jsp?page=login.jsp">登陆</a></li>
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
						href="logoutServlet?page=login.jsp">登出</a></li>
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


<%--获取登陆前页面 --%>
<%
String pageName = "";
if(request.getParameter("page") != null){
pageName = request.getParameter("page");
}

int courseId = 0;
if(request.getParameter("courseId") != null){
	courseId = Integer.parseInt(request.getParameter("courseId"));
}
//获取js跳转页面
String pageBefore = pageName;
if(courseId != 0){
	pageBefore = pageBefore + "?courseId="+courseId;
}
%>

<div class="loginForm">
<form action="loginServlet?page=<%=pageName %>&courseId=<%=courseId %>" method="post">
  <div class="form-group">
    <label>用戶名</label>
    <input type="text" class="form-control" name="username" id="username">
        <small id="usernameError" class="text-danger">
  用户名不能为空
    </small>
  </div>
  <div class="form-group">
    <label>密码</label>
    <input type="password" class="form-control" name="password" id="password" >
    <small  id="passwordError" class="text-danger">
      密码不能为空
    </small>
  </div>
<input type="submit" value="submit" id="submit" class="btn btn-primary"></td>
</form>
<hr>
<p id="login"></p>
</div>
<script type="text/javascript">
$(function(){
	
	$("#usernameError").hide();
	$("#passwordError").hide();
	$("#submit").click(function(e){
		if(checkUserName()){
			$("#usernameError").hide();
		}
		else{
			$("#usernameError").show();
		}
		if(checkPassword()){
			$("#passwordError").hide();
		}
		else{
			$("#passwordError").show();
		}
		
		if((!checkUserName()) || (!checkPassword())){
			e.preventDefault();
		}
		function checkUserName(){
			if($("#username").val() == ""){
				return false;
			}
			else
				return true;
		}
		function checkPassword(){
			if($("#password").val() == ""){
				return false;
			}
			else
				return true;
		}
	});

	function loginSuccess(){
		setTimeout(function(){ 
			alert("点此立即跳转!");
			$(window).attr('location',"http://localhost:8080/pj_4/<%= pageBefore%>");
			}, 3000);
		}
	//登陆成功，将在3秒后跳转
	<%
	if(request.getAttribute("login") != null && request.getAttribute("login").toString().equals("1")){
		System.out.println(pageBefore);
		%>
		$("#login").html("登陆成功，将在3秒后跳转");
		$("#login").css("color","green");
		loginSuccess();
		<%
	}
	%>
	//登陆失敗
	<%
	if(request.getAttribute("login") != null && request.getAttribute("login").toString().equals("0")){
		System.out.println(pageBefore);
		%>
		$("#login").html("用戶名或密碼錯誤");
		$("#login").css("color","red");
		<%
	}
	%>

	
});
</script>
</body>
</html>