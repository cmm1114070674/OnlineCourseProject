<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.*" %>
<%@ page import="Service.*" %><%--
  Created by IntelliJ IDEA.
  User: 曹铭明
  Date: 2018/8/1
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript"
        src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<%
    int courseId = 1;
    if (request.getParameter("courseId") != null) {
        courseId = Integer.parseInt(request.getParameter("courseId"));
    }
    int homeworkId = 1;
    if (request.getParameter("homeworkId") != null){
        homeworkId = Integer.parseInt(request.getParameter("homeworkId"));
    }
    HomeworkBean homeworkBean = HomeworkGet.getHomeworkById(homeworkId);
    String username = "ccc";
    if (session.getAttribute("username") != null) {
        username = session.getAttribute("username").toString();
    }
    CourseBean course = CourseGet.getCourse(courseId);
    UserBean user = UserGet.getUserByUsername(username);

    int permission = 3;
    if (request.getParameter("permission") != null) {
        permission = Integer.parseInt(request.getParameter("permission"));
    }
%>

<%
    int isLogin = 1;
    if (session.getAttribute("username") == null) {
        isLogin = 0;
    }
%>

<html>
<head>
    <title>修改作业</title>
    <style>
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
    
    
        #header {
            background-color:white;
            height:200px;
            padding:10px 100px 10px;
        }
        #work{
            width: 600px;
            padding:20px;
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
				<form class="form-inline my-2 my-lg-0"  action="getSearchContentServlet" method="post">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search"  name="searchContent"> 
						
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>

<div id = header>
    <div class="page-header">
        <h1><%= course.getName()%> <small><%= CourseGet.getTeacherByCourse(course).getName()%></small></h1>
    </div>
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

<div id = work style="margin: 0 auto; width: 600px">
    <div class="panel panel-primary">
        <form action="UpdateHomeworkServlet" method="get">
            <div class="panel-footer">题目：<input type="text" name = "topic" placeholder="<%= homeworkBean.getHomeworkname()%>"></div>
            <div class="panel-footer">
                <textarea cols="30" rows="10" style="border-radius: 10px; width: 400px" name = "content" placeholder="<%= homeworkBean.getQuestion()%>">
                </textarea>
            </div>
            <div class="panel-footer">
                <input type="hidden" name = "courseId" value="<%= courseId %>">
                <input type="hidden" name = "permission" value="<%= permission %>">
                <input type="hidden" name = "homeworkId" value="<%= homeworkId %>">
                <input type="submit" value="修改作业" class="btn btn-info">
            </div>
        </form>
    </div>
</div>

<% } %>


</body>
</html>
