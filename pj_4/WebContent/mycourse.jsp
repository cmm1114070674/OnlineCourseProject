<%@ page import="Bean.CourseBean" %>
<%@ page import="Service.CourseGet" %>
<%@ page import="Bean.UserBean" %>
<%@ page import="Service.UserGet" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 曹铭明
  Date: 2018/7/31
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int userId = 1;
    if (request.getParameter("userId") != null) {
        userId = Integer.parseInt(request.getParameter("userId"));
    }
    String username = "ccc";
    if (session.getAttribute("username") != null) {
        username = session.getAttribute("username").toString();
    }
    UserBean user = UserGet.getUserByUsername(username);
    userId = user.getId();
    ArrayList<CourseBean> chooseCourse = CourseGet.getChooseCourseList(userId);
    ArrayList<CourseBean> openCourse = CourseGet.getOpenCourseList(userId);
    int choose = 1;
    if(request.getParameter("choose") != null && Integer.parseInt(request.getParameter("choose")) == 2){
        choose = 2;
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
    <title>我的课程</title>
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
            height:150px;
            padding:10px 10px 10px;
            text-align:center;
        }
        #total{
            height: 1000px;
            width: 450px;
            float:left;
        }
        #left_blank{
            height: 1000px;
            width: 150px;
            float:left;
        }
        #information{
            height: 1000px;
            width: 300px;
            float:right;
        }
        #right_blank{
            height: 1000px;
            width: 100px;
            float:right;
        }
        #right_information{
            height: 1000px;
            width: 700px;
            float:right;
        }
        #courseInformation{
            height: 600px;
            width: 600px;
            background-color: white;
            position: absolute;
            right: 200px;
            top:200px;
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


<div>
    <%--导航栏 --%>
</div>

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
						href="login.jsp?page=mycourse.jsp">登陆</a></li>
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


<div id = total>
    <div id = left_blank>
    </div>
    <div id = information>
    <div class="panel panel-primary">
        <div class="panel-body" style="color:white; background-color: #2aabd2">
            <b><%= user.getName()%></b>
        </div>
        <div class="panel-footer"><b>Email：<i><%= user.getEmail()%></i></b></div>
        <div class="panel-footer"><b>电话：<i><%= user.getPhone()%></i></b></div>
        <div class="panel-footer"><b>注册时间：<i><%= user.getRegisterTime()%></i></b></div>
    </div>
    </div>
</div>

<div id = right_blank>
</div>

<div id = right_information>
    <div>
        <ul class="nav nav-pills">
            <% if(choose == 1){ %>
            <li role="presentation" class="active"><a href="mycourse.jsp?userId=<%=userId%>&choose=1">我选的课</a></li>
            <li role="presentation"><a href="mycourse.jsp?userId=<%=userId%>&choose=2">我开的课</a></li>
            <% } %>
            <% if(choose == 2){ %>
            <li role="presentation"><a href="mycourse.jsp?userId=<%=userId%>&choose=1">我选的课</a></li>
            <li role="presentation" class="active"><a href="mycourse.jsp?userId=<%=userId%>&choose=2">我开的课</a></li>
            <% } %>
        </ul>
    </div>
</div>

<div id = courseInformation>
    <% if(choose == 1){
        for(CourseBean i:chooseCourse){ %>
    <div class="col">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<%= i.getPictureRelativePath()%>" alt="..." width="200" height="200">
                <div class="caption">
                    <h4><b><%= i.getName()%></b></h4>
                    <h5 style="color: #3c3c3c"><%= CourseGet.getTeacherByCourse(i).getName() %></h5>
                    <p>
                        <form action="MyCourseServlet" method="get">
                            <input type="hidden" name="courseId" value="<%= i.getId()%>">
                            <input type="hidden" name="choose" value="<%= choose%>">
                            <input type="submit" class="btn btn-primary" value="查看">
                        </form>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <% }%>
        <div class="col">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    
                    <div class="caption">
                        <h4><b>课程名称</b></h4>
                        <h5 style="color: #3c3c3c">授课老师</h5>
                        <button class="btn btn-info"><a href="searchpage.jsp?type=1&sort=1&order=1&number=1">选课</a></button>
                    </div>
                </div>
            </div>
    <% } %>


    <% if(choose == 2){
        for(CourseBean i:openCourse){ %>
    <div class="col">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<%= i.getPictureRelativePath()%>" alt="..." width="200" height="200">
                <div class="caption">
                    <h4><b><%= i.getName()%></b></h4>
                    <h5 style="color: #3c3c3c"><%= CourseGet.getTeacherByCourse(i).getName() %></h5>
                    <%--<h5 style="color: #3c3c3c"><%= CourseGet.getTeacherByCourse(i).getName() %></h5>--%>
                    <p>
                    <form action="MyCourseServlet" method="get">
                        <input type="hidden" name="courseId" value="<%= i.getId()%>">
                        <input type="hidden" name="choose" value="<%= choose%>">
                        <input type="submit" class="btn btn-primary" value="查看">
                    </form>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <% } %>
            <div class="col">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <div class="caption">
                            <h4><b>课程名称</b></h4>
                            <h5 style="color: #3c3c3c">授课老师</h5>
                            <button class="btn btn-info"><a href="openCourse.jsp?isExisted=0">开课</a></button>
                        </div>
                    </div>
                </div>
    <% } %>

</div>

<% } %>



</body>
</html>