
<%@page import="Bean.FileUploadBean"%>
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
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
        #header {
            background-color:white;
            height:200px;
            padding:10px 100px 10px;
        }
</style>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(function() {
		var i = 2;
		$("#addFile")
				.click(
						function() {
							$(this).parent().parent().before(
							"<tr class='file'><td class='text-info text-center'>File"
							+ i+ ":</td><td>"
							+"<div class='input-group mb-3'><div class='custom-file'>"
							+"<input type='file' class='custom-file-input' name='file"
				            +i+"'><label class='custom-file-label'>Choose file</label></div></div></td></tr>"
						    + "<tr class='dsc added'><td class='text-info text-center'>dsc"
							+ i+ ":</td><td><div class='form-group'><input type='text' class='form-control' name='dsc"
				            +i+"'></div><button type='button' class='btn btn-light' id='delete"
				            +i+"'>删除</button></td></tr>");

							i++;

							//获取新添加的删除按钮
							$("#delete" + (i - 1))
									.click(
											function() {
												var $tr = $(this).parent().parent();
												$tr.prev("tr").remove();
												$tr.remove();

												//对i重新排序
												$(".file").each(function(index) {
																	var n = index + 1;
																	$(this)
																			.find(
																					"td:first")
																			.text(
																					"File"
																							+ n);
																	$(this)
																			.find(
																					"td:last input")
																			.attr(
																					"name",
																					"file"
																							+ n);
																});

												$(".dsc")
														.each(
																function(index) {
																	var n = index + 1;
																	$(this)
																			.find(
																					"td:first")
																			.text(
																					"dsc"
																							+ n);
																	$(this)
																			.find(
																					"td:last input")
																			.attr(
																					"name",
																					"dsc"
																							+ n);
																});

												$(".added")
														.each(
																function(index) {
																	var n = index + 2;
																	$(this)
																			.find(
																					"button")
																			.attr(
																					"id",
																					"delete"
																							+ n);
																	s
																});
												i--;
											});
						});
	});
</script>
</head>
<body>
<% if(isLogin == 0){ %>
<div>
    <button class="btn btn-info"><a href="login.jsp?page=homepage.jsp">登录</a></button>
</div>
<% } %>

<% if(isLogin == 1){ %>
	<%
		int courseId = 2;
		int permission = 99;
		//courseId和permission必须成对出现
		if (request.getParameter("courseId") != null) {
			courseId = Integer.parseInt(request.getParameter("courseId"));
			permission = Integer.parseInt(request.getParameter("permission"));
			System.out.println(courseId);
			System.out.println(permission);
		}
	%>
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
					<%--登陆时导航栏：获取username session 和logout服务--%>
					<%
						if (session.getAttribute("username") != null) {
					%>
					<li class="nav-item"><a class="nav-link  text-success"
						href="mycourse.jsp"><%=session.getAttribute("username")%></a></li>
					<li class="nav-item"><a class="nav-link text-success"
						href="logoutServlet?page=course.jsp&courseId=<%=courseId%>">登出</a></li>
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

	<%--课程的次级导航栏 --%>
		<div id = header>
		
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

	<%--前往课程页面 --%>
	<a href="course.jsp?courseId=<%=courseId%>">课程详情</a>
	<%--前往知识点页面 --%>
	<a
		href="courseKnowledgePoint.jsp?courseId=<%=courseId%>&permission=<%=permission%>"
		id="knowledgePoint">单元和知识点</a>
	<br>

	<%--展示课程资源 --%>
	<%
		if (courseId != 0 && permission == 3) {
	%>
	<%--资源上传 --%>
	<h2>资源上传</h2>
	<font color="red">${message}</font>
	<form
		action="resourceUploadServlet?courseId=<%=courseId%>&permission=<%=permission%>"
		method="post" enctype="multipart/form-data">
		<table class="table table-striped">

			<tr class="file">
				<td class="text-info text-center">File1:</td>
				<td>
					<div class="input-group mb-3">
						<div class="custom-file">
							<input type="file" class="custom-file-input" name="file1">
							<label class="custom-file-label">Choose file</label>
						</div>
					</div>
				</td>
			</tr>


			<tr class="dsc">
				<td class="text-info text-center">dsc1:</td>
				<td>
					<div class="form-group">
						<input type="text" class="form-control" name="dsc1">
					</div>
				</td>
			</tr>


			<tr>
				<td class="text-center"><input type="submit" value="submit"
					class="btn btn-primary"></td>
				<td class="text-center"><button id="addFile" type="button"
						class="btn btn-primary">新增一个附件</button></td>
			</tr>


		</table>
	</form>
	<%
		}
	%>

	<%--资源下载,先获取对应courseId的所有资源 --%>
	<h2>资源下載</h2>
	<%
		if (request.getAttribute("allResources") == null && request.getParameter("courseId") != null) {
			request.getRequestDispatcher("allResourceServlet?courseId=" + courseId + "&permission=" + permission)
					.forward(request, response);
		}
	%>

	<%
		if (request.getAttribute("allResources") != null) {
	%>
	<table class="table table-striped text-center">
		<tr>
			<th >资源名字</th>
			<th>资源路径（下载）</th>
			<th>资源描述</th>
		</tr>
		<%
			List<FileUploadBean> beans = (List<FileUploadBean>) request.getAttribute("allResources");
				for (FileUploadBean file : beans) {
		%>
		<tr>
			<td><button type="button" class="delete btn btn-outline-primary">
					<a
						href="resourceDeleteServlet?resourceId=<%=file.getResourceId()%>&courseId=<%=courseId%>&permission=<%=permission%>">删除</a>
				</button> <%=file.getResourceName()%></td>
			<td><a
				href="resourceDownloadServlet?resourceId=<%=file.getResourceId()%>"><%=file.getResourceName()%></a></td>
			<td><%=file.getResourceDesc()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>


	<script type="text/javascript">
		$(function() {
	<%if (permission != 3) {%>
		$(".delete").hide();
	<%}%>
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