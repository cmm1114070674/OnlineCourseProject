<%@page import="Bean.PostBean"%>
<%@page import="Bean.FloorBean"%>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
	
<style type="text/css">
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
.postHead{
width:60%;

margin:auto;
margin-top:80px;
}

.replyFloor{
width:62.5%;
margin:auto;
margin-top:30px;
}
.replyHead{
width:100%;
}
.replyBody{
width:100%;
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

<%--从请求的 url 中，获取courseId,postId --%>
<%
int courseId = Integer.parseInt(request.getParameter("courseId"));
int postId = Integer.parseInt(request.getParameter("postId"));
int permission = Integer.parseInt(request.getParameter("permission"));
int replyCount = 1;
System.out.print("courseId:"+courseId+"postId="+postId);
%>
<%--测试时，设定courseId=2，postId=1 ，posterId=36 --%>
<%--
int courseId = 2;
int postId = 1;
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
						placeholder="Search" aria-label="Search" name="searchContent"> 
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

<%--导航栏 --%>
<a href="discuss.jsp?courseId=<%=courseId %>&permission=<%=permission%>"> 回到课程讨论版 </a> <br>

<%--根据postId，获取所有的回复,按时间排序 --%>
<%--第一次来到此页面，请求转发,获取所有的回复 --%>
<%
if(request.getAttribute("post") == null){
	request.getRequestDispatcher("returnAllReply?postId="+postId+"&courseId="+courseId+"&permission="+permission).forward(request, response);
}
%>
<%--请求转发后获取到了所有的回复，以及开贴信息, --%>
<%--给每一条信息都增加一个回复功能 --%>
<%
if(request.getAttribute("post") != null){
	List<FloorBean> floors = (List<FloorBean>)request.getAttribute("floors");
	PostBean post = (PostBean)request.getAttribute("post");
	String posterName = request.getAttribute("posterName").toString();
	System.out.println(posterName);
	replyCount = floors.size();
	%>
	
	<%--展示post,开贴信息 --%>

	
	
	<div class="card text-center postHead">
  <div class="card-header">
 <h1><%=post.getSubject() %></h1> 
  </div>
  <div class="card-body">
    <h5 class="card-title">开贴人：<%=posterName %></h5>
    <p class="card-text">内容：<%=post.getContent() %></p>
   <button type="button" name="reply" id="reply0"  class="btn btn-info">回复</button>
  </div>
  <div class="card-footer text-muted">
   最后更改时间：<%=post.getLatestReplyTime() %>     开贴时间：<%=post.getTime() %>
  </div>
    <div class="card-footer text-muted">
 回复数：<%=floors.size() %>
  </div>
  
  	<form action="addReplyServlet?postId=<%= postId%>&replyTo=<%=posterName %>&courseId=<%=courseId %>&permission=<%=permission %>" 
	method="post" class="reply0 reply">
  <div class="form-group">
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="说点什么" name="replyContent"></textarea>
  </div>
	<input type="submit" value="submit"  class="btn btn-info"><br><br>
	</form>
</div>
	
	
	
	
	
	
	
	<div class="container replyFloor">
	<%--展示回复信息，按照时间顺序 --%>
	<% int i = 1;
	for(FloorBean floor:floors){
		%>
<div class="row no-gutters">

	<div class="col-3 border">	
 <div class="card replyHead">
  <div class="card-body">
    <h5 class="card-title"><%=floor.getReplier() %></h5>
    <p class="card-subtitle mb-2 text-muted">To <%=floor.getReplyTo() %></p>
    <small class="text-muted"><%=floor.getTime() %></small><br>
    <button type="button" name="reply" id="reply<%=i%>" class="btn btn-info">回复</button>
  </div>
</div></div>

<div class="col-9 border">
  <div class="replyBody" style="margin:20px 20px;">
      <p class="card-text" ><%=floor.getContent() %></p>
      
        	<form action="addReplyServlet?postId=<%= postId%>&replyTo=<%=floor.getReplier()%>&courseId=<%=courseId %>&permission=<%=permission %>" 
	method="post" class="reply<%= i%> reply" style="width:80%;">
		  <div class="form-group">
    <textarea class="form-control" rows="3" placeholder="说点什么" name="replyContent"></textarea>
  </div>
	<input type="submit" value="submit" class="btn btn-info">
	</form>
	
  </div></div>
  
</div>

	
	
	
		<% 
	i++;
	}
}
%>
</div>



<script type="text/javascript">
$(function(){
		$(".reply").hide();
	//给回复按钮循环绑定点击事件
	for(var q = 0;q < <%= replyCount%> + 1 ; q++){
	$("#reply"+q).bind("click",{index:q},reply);
	}
	function reply(event){
		var q = event.data.index;
		$(".reply"+q).toggle();
	}
	
});





</script>



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