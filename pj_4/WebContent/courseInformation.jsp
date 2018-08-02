<%@page import="Dao.DaoImpl.CourseDaoImpl"%>
<%@ page import="Bean.CourseBean" %>
<%@ page import="Service.CourseGet" %>
<%@ page import="Bean.UserBean" %>
<%@ page import="Service.UserGet" %><%--
  Created by IntelliJ IDEA.
  User: 曹铭明
  Date: 2018/7/30
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int courseId = 1;
    if (request.getParameter("courseId") != null) {
        courseId = Integer.parseInt(request.getParameter("courseId"));
    }
    String username = null;
    if (session.getAttribute("username") != null) {
        username = session.getAttribute("username").toString();
    }
    CourseBean course = CourseGet.getCourse(courseId);
    UserBean user = UserGet.getUserByUsername(username);
 
    CourseDaoImpl courseDao = new CourseDaoImpl();
    int permission;

	if(session.getAttribute("username") == null) {
		permission = 0;
	}
	else {
		username = session.getAttribute("username").toString();
		permission = courseDao.getPermissoin(courseId, username);
	}
    if (request.getParameter("permission") != null) {
        permission = Integer.parseInt(request.getParameter("permission"));
    }
%>

<%
    int isLogin = 1;
    if (request.getParameter("courseId") == null) {
        isLogin = 0;
    }
%>



<html>
<head>
    <title>课程信息</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

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
        #information{
            height: 400px;
        }
        #left_information {
            height:400px;
            width:430px;
            float:left;
        }
        #right_picture{
            height:400px;
            width:300px;
            float:right;
        }
        #right_picture img{
            height:100%;
            width:100%;
        }
        #blank{
            height:400px;
            width:130px;
            float:left;
        }
        #section1 {
            width:350px;
            float:left;
            padding:10px;
        }
        #section2 {
            width:350px;
            float:left;
            padding:10px;
            white-space:normal;
            word-break:break-all;
            word-wrap:break-word;
        }
        #blank2{
            height: 50px;
        }
        #footer {
            background-color:white;
            clear:both;
            text-align:center;
            padding:5px;
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
						href="login.jsp?page=courseInformation.jsp&courseId=<%=courseId%>">登陆</a></li>
					<li class="nav-item"><a class="nav-link text-primary"
						href="register.jsp">注册</a></li>
					<%
						}
					%>
					<%--登陆时导航栏：获取username session 和logout服务--%>
					<%
						if (session.getAttribute("username") != null) {
					%>
					<li class="nav-item"><a class="nav-link text-success" href="mycourse.jsp?"><%=session.getAttribute("username")%></a>
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

<div id = header>
    <div class="page-header">
        <h1><%= course.getName()%> <small><%= CourseGet.getTeacherByCourse(course).getName()%></small></h1>
    </div>
    <ul class="nav nav-pills">
        <li role="presentation" class="active"><a href="courseInformation.jsp?courseId=<%=courseId%>&permission=<%=permission%>">详情</a></li>
        <% if (permission == 2 || permission == 3) { %>
        <li role="presentation"><a href="courseResource.jsp?courseId=<%=courseId%>&permission=<%=permission%>">资料</a></li>
        <li role="presentation"><a href="courseKnowledgePoint.jsp?courseId=<%=courseId%>&permission=<%=permission%>">知识点</a></li>
        <li role="presentation"><a href="homework.jsp?courseId=<%=courseId%>&permission=<%=permission%>">作业</a></li>
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

<div class="media" id = information>
    <div class="media-left" id = left_information>
        <div id = right_picture>
            <img src="<%=course.getPictureRelativePath()%>">
        </div>
    </div>
    <div id = blank>
    </div>
    <div class="media-body" id = section1>
        <h4 class="media-heading" style="color: #2aabd2">课程名称</h4>
        <%= course.getName()%><br><br><br>
        <h4 class="media-heading" style="color: #2aabd2">老师</h4>
        <%= CourseGet.getTeacherByCourse(course).getName()%><br><br><br>
        <h4 class="media-heading" style="color: #2aabd2">开课时间</h4>
        <%= course.getDate()%><br><br><br>
        <h4 class="media-heading" style="color: #2aabd2">选课人数</h4>
        <%= course.getStudentNumber()%><br><br><br>
    </div>
    <div class="media-body" id = section2>
        <h4 class="media-heading" style="color: #2aabd2">课程介绍</h4>
        <%= course.getIntroduction()%>
    </div>
</div>

<div id = blank2>
</div>

<div id = footer>
    <% if (permission == 3) { %>
    <form action="UpdateCourseServlet" method="get">
        <input type="hidden" name="courseId" value="<%= courseId%>">
        <input type="hidden" name="coursename" value="<%= course.getName()%>">
        <input type="hidden" name="introduction" value="<%= course.getIntroduction()%>">
        <input type="hidden" name="path" value="<%= course.getPictureRelativePath()%>">
        <input type="submit" value="修改" class="btn btn-info">
    </form>
    <% } %>
    <% if (permission == 2) { %>
    <form action="DropCourseConfirmServlet" method="get">
        <input type="hidden" name="courseId" value="<%= courseId%>">
        <input type="submit" value="退课" class="btn btn-info">
    </form>
    <% } %>
    <% if (permission == 1) { %>
    <form action="chooseCourseServlet" method="get">
        <input type="hidden" name="courseId" value="<%= courseId%>">
        <input type="submit" value="选课" class="btn btn-info">
    </form>
    <% } %>
    <% if (permission == 0) { %>
    <button class="btn btn-info"><a href="login.jsp?page=homepage.jsp">选课</a></button>
    <% } %>
</div>
<% } %>


</body>
</html>
