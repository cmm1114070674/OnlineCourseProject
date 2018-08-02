<%@page import="Bean.CourseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>

</head>
<body>
	<%
		int courseId = 2;
		if (request.getParameter("courseId") != null) {
			courseId = Integer.parseInt(request.getParameter("courseId"));
		}
	%>
	<%--未登录时 导航栏 --%>
	<%
		if (session.getAttribute("username") == null) {
	%>
	<a href="login.jsp?page=course.jsp&courseId=<%=courseId%>">登陆</a>
	<br>
	<a href="register.jsp">注册</a>
	<br>
	<a href="homepage.jsp">首页</a>
	<%
		}
	%>
	<%--登陆时导航栏：获取username session 和logout服务--%>
	<%
		if (session.getAttribute("username") != null) {
	%>
	username:<%=session.getAttribute("username")%>
	<a href="logoutServlet?page=course.jsp&courseId=<%=courseId%>">logout</a>
	<a href="homepage.jsp">首页</a>
	<%
		}
	%>
	<%--展示课程详情 --%>

	<%--首次请求，请求转发到courseServlet，获取与courseId对应的courseBean,并且获得用户权限：0——未登录；1——登陆，不是老师或学生；2——登陆，是学生；3——登陆，是老师 --%>
	<%
		if (request.getAttribute("course") == null) {
			request.getRequestDispatcher("courseServlet?courseId=" + courseId).forward(request, response);
		}
	%>
	<%
		if (request.getAttribute("course") != null) {
			CourseBean course = (CourseBean) request.getAttribute("course");
			int permission = Integer.parseInt(request.getAttribute("permission").toString());
			System.out.println("get permission:" + permission);
	%>
	<%--展示courseBean ，并提供修改功能--%>
	id:<%=course.getId()%><br> name:<%=course.getName()%><br>
	<button type="button" id="alterName" class="alter">修改</button>
	<br>
	<form action="alterCourseNameServlet?courseId=<%=courseId%>"
		method="post" name="alterCourseName" id="alterCourseName">
		name:<input type="text" name="courseName"><input type="submit"
			value="submit" id="submitName">
	</form>

	introduction:<%=course.getIntroduction()%><br>
	<button type="button" id="alterIntro" class="alter">修改</button>
	<br>
	<form action="alterCourseIntroServlet?courseId=<%=courseId%>"
		method="post" name="alterCourseIntro" id="alterCourseIntro">
		introduction:<input type="text" name="courseIntro"><input
			type="submit" value="submit" id="submitIntro">
	</form>

	picture:
	<img src="<%=course.getPictureRelativePath()%>" width="200" height="200">
	<br>
	<button type="button" id="alterPicture" class="alter">修改</button>
	<br>
	<form action="alterCoursePictureServlet?courseId=<%=courseId%>"
		method="post" name="alterCoursePicture" id="alterCoursePicture"
		enctype="multipart/form-data">
		picture:<input type="file" name="coursePicture"><input
			type="submit" value="submit" id="submitPicture">
	</form>


	date:<%=course.getDate()%><br> studentNumber:<%=course.getStudentNumber()%><br>

	<%
		if (permission == 0) {
	%>
	<%--登陆操作 --%>
	<a href="login.jsp?page=course.jsp&courseId=<%=courseId%>">选课</a>
	<br>
	<%
		}
			if (permission == 1) {
	%>
	<%--选课操作 --%>
	<a href="chooseCourseServlet?courseId=<%=courseId%>">选课</a>
	<br>
	<%
		}
	%>

	<%
		if (permission == 2) {
	%>
	<%--退课操作 --%>
	<a href="dropCourseServlet?courseId=<%=courseId%>">退课</a>
	<br>
	<%
		}
	%>

	<%--前往资源页面 --%>
	<a href="courseResource.jsp?courseId=<%=courseId%>&permission=<%=permission %>" 
	id="resource">资源</a>
	
	<%--前往知识点页面 --%>
	<a href="courseKnowledgePoint.jsp?courseId=<%=courseId%>&permission=<%=permission %>"
		id="knowledgePoint">单元和知识点</a><br>

    <%--前往讨论区 --%>
    <a href="discuss.jsp?courseId=<%=courseId %>&permission=<%=permission %>" 
    id="discuss">课程讨论区</a>
	<%
		}
	%>


	<script type="text/javascript">
		$(function() {
			$("#alterCourseName").hide();
			$("#alterCourseIntro").hide();
			$("#alterCoursePicture").hide();
			$("#alterName").click(function() {
				$("#alterCourseName").toggle();
			});
			$("#alterIntro").click(function() {
				$("#alterCourseIntro").toggle();
			});
			$("#alterPicture").click(function() {
				$("#alterCoursePicture").toggle();
			});

			//如果是身份permission:0,1,禁用资源，知识点超链接，以及讨论区超链接
	<%if (request.getAttribute("course") != null) {
				int permission = Integer.parseInt(request.getAttribute("permission").toString());
				if (permission == 0 || permission == 1) {%>
		$("#resource").removeAttr("href");
			$("#knowledgePoint").removeAttr("href");
			$("#discuss").removeAttr("href");
			
	<%}
			}%>
		//如果不是permission:3,（老师），隐藏修改按钮
	<%if (request.getAttribute("course") != null) {
				int permission = Integer.parseInt(request.getAttribute("permission").toString());
				if (permission != 3) {%>
		$(".alter").hide();
	<%}
			}%>
		});
	</script>
</body>
</html>