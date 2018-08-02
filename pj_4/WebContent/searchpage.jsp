<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.*" %>
<%@ page import="Service.*" %>
<%@ page import="Dao.DaoImpl.CourseDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: 曹铭明
  Date: 2018/8/1
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript"
        src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<%

request.setCharacterEncoding("utf-8");
    String username = "ccc";
    if (session.getAttribute("username") != null) {
        username = session.getAttribute("username").toString();
    }


    int courseId = 0;
    CourseDaoImpl courseDao = new CourseDaoImpl();
    int permission = 0;

    String word = null;
    if(request.getParameter("word") != null){
        word = request.getParameter("word");
    	System.out.println("searchPage:"+word);
    }

    int type = 1;
    if(request.getParameter("type") != null){
        type = Integer.parseInt(request.getParameter("type"));
    	System.out.println("searchPage:"+word+"type:"+type);
    }

    int sort = 1;
    if(request.getParameter("sort") != null)
        sort = Integer.parseInt(request.getParameter("sort"));

    int order = 1;
    if(request.getParameter("order") != null)
        order = Integer.parseInt(request.getParameter("order"));

    int number = 4;
    if(request.getParameter("number") != null)
        number = Integer.parseInt(request.getParameter("number"));

    ArrayList<CourseBean> courseBeans = CourseGet.getCourseListAll();

    if(word == null || word.isEmpty()){
        if(sort == 1)
            courseBeans = SearchUtil.getCoursesAllByDate();
        if(sort == 2)
            courseBeans = SearchUtil.getCoursesAllByStudentNumber();
    }

    if(word != null && !word.isEmpty()){
        if(sort == 1 && type == 1)
            courseBeans = SearchUtil.searchCourseNameByDate(word);
        if(sort == 1 && type == 2)
            courseBeans = SearchUtil.searchCourseTeacherByDate(word);
        if(sort == 1 && type == 3)
            courseBeans = SearchUtil.searchCourseContentByDate(word);
        if(sort == 2 && type == 1)
            courseBeans = SearchUtil.searchCourseNameByStudentNumber(word);
        if(sort == 2 && type == 2)
            courseBeans = SearchUtil.searchCourseTeacherByStudentNumber(word);
        if(sort == 2 && type == 3)
            courseBeans = SearchUtil.searchCourseContentByStudentNumber(word);
    }

    if(order == 2)
        courseBeans = SearchUtil.getReverseList(courseBeans);

    int course_number = courseBeans.size();

    int page_number = course_number / 6 + 1;

%>





<html>
<head>
    <title>搜索结果</title>
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
            padding:10px 300px 10px;
        }
        #blank_button{
            width: 50px;
            height: 50px;
            position:absolute;
            right: 300px;
            top:75px;
        }
        #work{
            width: 600px;
            padding:20px;
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
						href="login.jsp?page=searchpage.jsp">登陆</a></li>
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
        <% if(type == 1){ %>
        <li role="presentation" class="active"><a href="searchpage.jsp?word=<%=word %>&type=1&sort=<%=sort%>&order=1&number=1">按课程名称搜索</a></li>
        <li role="presentation"><a href="searchpage.jsp?word=<%=word %>&type=2&sort=<%=sort%>&order=1&number=1">按课程老师搜索</a></li>
        <li role="presentation"><a href="searchpage.jsp?word=<%=word %>&type=3&sort=<%=sort%>&order=1&number=1">按课程介绍搜索</a></li>
        <% } %>
        <% if(type == 2){ %>
        <li role="presentation"><a href="searchpage.jsp?word=<%=word %>&type=1&sort=<%=sort%>&order=1&number=1">按课程名称搜索</a></li>
        <li role="presentation" class="active"><a href="searchpage.jsp?word=<%=word %>&type=2&sort=<%=sort%>&order=1&number=1">按课程老师搜索</a></li>
        <li role="presentation"><a href="searchpage.jsp?word=<%=word %>&type=3&sort=<%=sort%>&order=1&number=1">按课程介绍搜索</a></li>
        <% } %>
        <% if(type == 3){ %>
        <li role="presentation"><a href="searchpage.jsp?word=<%=word %>&type=1&sort=<%=sort%>&order=1&number=1">按课程名称搜索</a></li>
        <li role="presentation"><a href="searchpage.jsp?word=<%=word %>&type=2&sort=<%=sort%>&order=1&number=1">按课程老师搜索</a></li>
        <li role="presentation" class="active"><a href="searchpage.jsp?word=<%=word %>&type=2&sort=<%=sort%>&order=1&number=1">按课程介绍搜索</a></li>
        <% } %>
    </ul>
    <br>
    <ul class="nav nav-pills">
        <% if(sort == 1){ %>
        <li role="presentation" class="active"><a href="searchpage.jsp?word=<%=word %>&type=<%=type%>&sort=1&order=1&number=1">按开课时间排序</a></li>
        <li role="presentation"><a href="searchpage.jsp?word=<%=word %>&type=<%=type%>&sort=2&order=1&number=1">按选课人数排序</a></li>
        <% } %>
        <% if(sort == 2){ %>
        <li role="presentation"><a href="searchpage.jsp?word=<%=word %>&type=<%=type%>&sort=1&order=1&number=1">按开课时间排序</a></li>
        <li role="presentation" class="active"><a href="searchpage.jsp?word=<%=word %>&type=<%=type%>&sort=2&order=1&number=1">按选课人数排序</a></li>
        <% } %>
    </ul>
</div>

<% if(order==1){ %>
<div id = blank_button>
    <button class="btn btn-info">
        <a href="searchpage.jsp?word=<%=word %>&type=<%=type%>&sort=<%=sort%>&order=2&number=1">倒序</a>
    </button>
</div>
<% } %>
<% if(order==2){ %>
<div id = blank_button>
    <button class="btn btn-info">
        <a href="searchpage.jsp?word=<%=word %>&type=<%=type%>&sort=<%=sort%>&order=1&number=1">倒序</a>
    </button>
</div>
<% } %>

<div id = work style="margin: 0 auto; width: 600px">
    <% for(int i = 6 * (number -1); i < 6 * number && i < course_number; i++){ %>
    <div class="col">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<%= courseBeans.get(i).getPictureRelativePath()%>" alt="...">
                <div class="caption">
                    <h4><b><%= courseBeans.get(i).getName()%></b></h4>
                    <h5 style="color: #3c3c3c"><%= CourseGet.getTeacherByCourse(courseBeans.get(i)).getName() %></h5>
                    <p>
                    <%
                        courseId = courseBeans.get(i).getId();
                        if(session.getAttribute("username") == null) {
			                permission = 0;
		                }
		                else {
			                username = session.getAttribute("username").toString();
			                permission = courseDao.getPermissoin(courseId, username);
		                }
                    %>
                    <form action="CourseGetServlet" method="get">
                        <input type="hidden" name="courseId" value="<%= courseId%>">
                        <input type="hidden" name="permission" value="<%= permission%>">
                        <input type="submit" class="btn btn-primary" value="查看">
                    </form>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>

<div id = footer>
    <div align="center">
        <nav aria-label="Page navigation">
            <ul class="pagination" style="">
                <% for(int i = 0; i < page_number; i++){ %>
                <li><a href="searchpage.jsp?word=<%=word %>&type=<%=type%>&sort=<%=sort%>&order=<%=order%>&number=<%=i+1%>"><%= i+1%></a></li>
                <% } %>
            </ul>
        </nav>
    </div>
</div>




</body>
</html>
