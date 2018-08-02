
<%@page import="Bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

.latestCourse{
margin-bottom:100px;
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
						placeholder="Search" aria-label="Search" name="searchContent"> 
						
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>

	<%--首次请求，要转发 --%>
	<%
		if (request.getAttribute("latestCourses") == null) {
			request.getRequestDispatcher("homepageServlet").forward(request, response);
		}
	%>

	<%--已经转发过一次，获取request的特性，展示最热课程 --%>
	<%
		if (request.getAttribute("hottestCourses") != null) {
			List<CourseBean> courses = (List<CourseBean>) request.getAttribute("hottestCourses");
	%>
	<%--bootstrap最热课程图片轮播 --%>

	<%--最热课程图片轮播 ，container容器第一行 --%>
	<div class="container">
		<div class="row">
			<div class="col">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner">
						<%
							for (int i = 0; i < 3; i++) {
									CourseBean course = courses.get(i);
									if (i == 0) {
						%>
						<div class="carousel-item active">
							<a href="course.jsp?courseId=<%=course.getId()%>"> <img
								class="d-block w-100"
								src="<%=course.getPictureRelativePath()%>"
								alt="First slide" height="500">
							</a>
							<div class="carousel-caption d-none d-md-block">
								<h5>
									name:<%=course.getName()%></h5>
								<p>
									introduction:<%=course.getIntroduction()%></p>
							</div>
						</div>
						<%
							} else {
						%>
						<div class="carousel-item">
							<a href="course.jsp?courseId=<%=course.getId()%>"> <img
								class="d-block w-100"
								src="<%=course.getPictureRelativePath()%>"
								alt="Second slide" height="500">
							</a>
							<div class="carousel-caption d-none d-md-block">
								<h5>
									name:<%=course.getName()%></h5>
								<p>
									introduction:<%=course.getIntroduction()%></p>
							</div>
						</div>
						<%
							}
								}
						%>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
				<%
					}
				%>
			</div>
			<!-- 圖片輪播結束 -->
		</div>
		<!-- container容器第一行結束 -->
		</div><!-- container容器結束 -->

		<br><br>
		<%-- container容器第二，顯示最新課程--%>
		<div class="container">
		<div class="row justify-content-center">
			<%--已经转发过一次，获取request的特性，展示最新课程 --%>
			<%
				if (request.getAttribute("latestCourses") != null) {
					List<CourseBean> courses = (List<CourseBean>) request.getAttribute("latestCourses");
					for (int i = 0; i < 3; i++) {
						CourseBean course = courses.get(i);
			%>
			<div class="col-4 latestCourse">
				<a href="course.jsp?courseId=<%=course.getId()%>"><img
					src="<%=course.getPictureRelativePath()%>"
					width="350" height="400"></a>
				<h2><%=course.getName()%></h2>
				<p><%=course.getIntroduction()%></p>
				<button type="button" class="btn btn-primary"><a href="course.jsp?courseId=<%=course.getId()%>" class="text-white">查看</a></button>
			</div>
			<%
				}
			%>
		</div>
		<!-- 第二行row結束 -->
	</div>
	<!-- container容器第二結束 -->
	<%
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
</body>
</html>