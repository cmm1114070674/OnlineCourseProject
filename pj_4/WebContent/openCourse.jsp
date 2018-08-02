<%@ page import="Service.UserGet" %>
<%@ page import="Bean.UserBean" %>
<%@ page import="Bean.CourseBean" %>
<%@ page import="Service.CourseGet" %>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: 曹铭明--%>
  <%--Date: 2018/7/31--%>
  <%--Time: 12:58--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript"
        src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(function() {
        /*原理是把本地图片路径："D(盘符):/image/..."转为"http://..."格式路径来进行显示图片*/
        $("#fileupload").change(function() {
            var $file = $(this);
            var objUrl = $file[0].files[0];
            //获得一个http格式的url路径:mozilla(firefox)||webkit or chrome
            var windowURL = window.URL || window.webkitURL;
            //createObjectURL创建一个指向该参数对象(图片)的URL
            var dataURL;
            dataURL = windowURL.createObjectURL(objUrl);
            $("#imageview").attr("src",dataURL);
        });
    });
</script>
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<%
    int isExisted = 0;
    if (request.getParameter("isExisted") != null) {
        isExisted = Integer.parseInt(request.getParameter("isExisted"));
    }
    String username = null;
    if (session.getAttribute("username") == null) {
        username = request.getParameter("username");
    }
    UserBean user = UserGet.getUserByUsername(username);
    int courseId = 1;
    if (request.getParameter("courseId") != null) {
        courseId = Integer.parseInt(request.getParameter("courseId"));
    }
    String coursename = null;
    if(request.getParameter("coursename") != null){
        coursename = request.getParameter("coursename");
    }
    String introduction = null;
    if(request.getParameter("introduction") != null){
        introduction = request.getParameter("introduction");
    }
    String path = null;
    if(request.getParameter("path") != null){
        path = request.getParameter("path");
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
    <title>开设课程</title>
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
        #information{
            height: 400px;
        }
        #blank{
            height:400px;
            width:300px;
            float:left;
        }
        #blank_center{
            height:400px;
            width:150px;
            float:left;
        }
        #left_picture{
            height:400px;
            width:300px;
            float:left;
        }
        #photo{
            height: 380px;

        }
        #photo_choose{
            height: 20px;
            position: absolute;
            bottom: 80px;
        }
        #right1{
            height:400px;
            width:400px;
            float:left
        }
        #blank_footer{
            height: 50px;
        }
        #footer {
            background-color:white;
            clear:both;
            text-align:center;
            padding:5px;
        }
        #header2 {
            background-color:white;
            height:150px;
            padding:10px 10px 10px;
            text-align:center;
        }
        #information2{
            height: 400px;
        }
        #blank2{
            height:400px;
            width:300px;
            float:left;
        }
        #blank_center2{
            height:400px;
            width:150px;
            float:left;
        }
        #left_picture2{
            height:400px;
            width:300px;
            float:left;
        }
        #photo2{
            height: 380px;

        }
        #photo_choose2{
            height: 20px;
            position: absolute;
            bottom: 80px;
        }
        #right12{
            height:400px;
            width:400px;
            float:left
        }
        #blank_footer2{
            height: 50px;
        }
        #footer2 {
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
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search"> 
						
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>


<div id = "header">
    <h1>开设课程</h1>
</div>
<% if(isExisted == 0){ %>
<form action="openCourseToShow" method="post"  enctype="multipart/form-data">
<div id = information>
    <div id = blank></div>
    <div id = left_picture>
        <div id = photo_choose>
            <input id="fileupload" type="file" name="photo" value="选择图片" style="border-radius: 3px; color: #1b6d85">
        </div>
        <div id = photo>
            <img id="imageview" style="border-radius: 20px; width: 100%; height: 100%">
        </div>
    </div>
    <div id = blank_center></div>
    <div id = right1>
        <br>
        <div class="input-group input-group-lg">
            <span class="input-group-addon" id="sizing-addon1">课程名称</span>
            <input type="text" class="form-control" placeholder="Coursename" aria-describedby="sizing-addon1" name = "coursename">
        </div>
        <br><br><br>
        <div class="input-group input-group-lg" style="width: 100px">
            <span class="input-group-addon">课程简介</span>
        </div>
        <div>
            <textarea cols="30" rows="10" style="border-radius: 10px; width: 400px" placeholder="Type something..." name = "introduction"></textarea>
        </div>
    </div>
</div>

<div id = blank_footer>
</div>

<div id = footer>
    <input type="submit" value="确认" class="btn btn-info">
</div>
</form>
<% } %>

<% if(isExisted == 1){ %>
<form action="CourseChangeServlet" method="post" enctype="multipart/form-data">
    <div id = information2>
        <div id = blank2></div>
        <div id = left_picture2>
            <div id = photo_choose2>
                <input id="fileupload" type="file" name="photo" value="选择图片" 
                style="border-radius: 3px; color: #1b6d85" src="<%=path %>">
            </div>
            <div id = photo2>
                <img id="imageview2" style="border-radius: 20px; width: 100%; height: 100%">
            </div>
        </div>
        <div id = blank_center2></div>
        <div id = right12>
            <br>
            <div class="input-group input-group-lg">
                <span class="input-group-addon" id="sizing-addon12">课程名称</span>
                <input type="text" class="form-control" placeholder="<%= coursename%>" aria-describedby="sizing-addon1" name = "coursename">
            </div>
            <br><br><br>
            <div class="input-group input-group-lg" style="width: 100px">
                <span class="input-group-addon">课程简介</span>
            </div>
            <div>
                <textarea cols="30" rows="10" style="border-radius: 10px; width: 400px" placeholder="<%= introduction%>" name = "introduction" ></textarea>
            </div>
        </div>
    </div>

    <div id = blank_footer2>
    </div>

    <input type="hidden" name="courseId" value="<%= courseId%>">

    <div id = footer2>
        <input type="submit" value="修改" class="btn btn-info">
    </div>
</form>
<% } %>
<% } %>



</body>
</html>
