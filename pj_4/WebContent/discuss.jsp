<%@page import="Bean.PostBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    int isLogin = 1;
    if (session.getAttribute("username") == null) {
        isLogin = 0;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Insert title here</title>
<style type="text/css">
html,body{
width:100%;
height:100%;
}
header {
	position: relative;
	margin-bottom: 50px;
	z-index: 100;
}
.fixedNavbar {
	position: fixed;
	top: 0;
	width: 100%;
}
.createNewPost{
width:60%;
height:30%;
margin:auto;
margin-top:80px;
margin-bottom:100px;
}
.postBuilding{
width:60%;
margin:auto;
margin-top:20px;
}
</style>

</head>
<body>

<% if(isLogin == 0){ %>
<div>
    <button class="btn btn-info"><a href="login.jsp?page=homepage.jsp">登录</a></button>
</div>
<% } %>

<% if(isLogin == 1){ %>
<%--获取courseId --%>
<%
int courseId = Integer.parseInt(request.getParameter("courseId"));
int permission = Integer.parseInt(request.getParameter("permission"));
%>
<%--测试时，设定courseId=2 --%>
<%--
int courseId = 2;
--%>
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
					<li class="nav-item"><a class="nav-link text-success" href="mycourse.jsp"><%=session.getAttribute("username")%></a>
					</li>
					<li class="nav-item"><a class="nav-link text-success"
						href="logoutServlet?page=homepage.jsp">登出</a></li>
					<%
						}
					%>
				</ul>
				<form class="form-inline my-2 my-lg-0" action="getSearchContentServlet" method="post">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search"  name="searchContent"> 
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>

<div id = header>
    <ul class="nav nav-pills">
        <li role="presentation"><a href="courseInformation.jsp?courseId=<%=courseId%>&permission=<%=permission%>">详情</a></li>
        <% if (permission == 2 || permission == 3) { %>
        <li role="presentation"><a href="courseResource.jsp?courseId=<%=courseId%>&permission=<%=permission%>">资料</a></li>
        <li role="presentation"><a href="courseKnowledgePoint.jsp?courseId=<%=courseId%>&permission=<%=permission%>">知识点</a></li>
        <li role="presentation" class="active"><a href="homework.jsp?courseId=<%=courseId%>&permission=<%=permission%>">作业</a></li>
        <li role="presentation"><a href="discuss.jsp?courseId=<%=courseId%>&permission=<%=permission%>">讨论</a></li>
        <% } %>
        <% if (permission == 0 || permission == 1) { %>
        <li role="presentation"><a href="#"><p style="color: #8c8c8c">资料</p></a></li>
        <li role="presentation"><a href="#"><p style="color: #8c8c8c">知识点</p></a></li>
        <li role="presentation"><a href="#"><p style="color: #8c8c8c">作业</p></a></li>
        <li role="presentation"><a href="#"><p style="color: #8c8c8c">讨论</p></a></li>
        <% } %>
    </ul>
</div>

<%--讨论区：只有改课程的老师或学生才有权限进来 --%>
<%--开贴功能,只有选了该课的学生以及开了该课的老师有权限,大前提是都登陆了 --%>
<div class="createNewPost">
<form action="createPost?courseId=<%=courseId %>&permission=<%=permission %>" method="post">
  <div class="form-group">
    <label>主题</label>
    <input type="text" class="form-control"  placeholder="新建主题讨论"  name="subject" id="subject">
  </div>
  <div class="form-group">
    <label>内容</label>
    <textarea class="form-control"  rows="3"  name="content" id="content" placeholder="说点什么"></textarea>
  </div>
<input type="submit" value="submit" class="btn btn-primary">
</form>
</div>

<%--第一次来到此页面，请求转发，获取课程所有帖子 --%>
<%--展示课程的所有帖子 ，按照帖子的最近更改时间来排序--%>
<%
if(request.getAttribute("posts") == null){
	request.getRequestDispatcher("returnAllPostServlet?courseId="+courseId+"&permission="+permission).forward(request, response);
}
%>
<%
if(request.getAttribute("posts") != null){
	List<PostBean> posts = (List<PostBean>)request.getAttribute("posts");
	for(PostBean post : posts){
		%>
		<div class="card postBuilding" >
  <div class="card-body">
    <h3 class="card-title"><a href="discussPost.jsp?courseId=<%=courseId %>&postId=<%=post.getPostId() %>&permission=<%=permission %>"><%=post.getSubject() %></a></h3>
    <h6 class="card-subtitle mb-2 text-muted">楼主：<%=post.getPosterName() %></h6>
    <p class="card-text"><%=post.getContent() %></p>
    <small class="card-subtitle mb-2 text-muted">最后回复时间：<%=post.getLatestReplyTime() %></small><br>
    <small class="card-subtitle mb-2 text-muted">开贴时间：<%=post.getTime() %></small>
  </div>
</div>
		<% 
	}
}
%>


	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
		<% } %>
</body>
</html>