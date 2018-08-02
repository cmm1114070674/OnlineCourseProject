<%--
  Created by IntelliJ IDEA.
  User: 曹铭明
  Date: 2018/8/1
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int courseId = 1;
    if (request.getParameter("courseId") != null) {
        courseId = Integer.parseInt(request.getParameter("courseId"));
    }
%>

<html>
<head>
    <title>退课确认</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        html,body{
            width:100%;
            height:100%;
        }
        .loginForm{
            width:30%;
            height:30%;
            margin:auto ;
            margin-top:150px;
        }
        #submit{
            width:100%;
            margin-top:15px;
        }
    </style>
</head>
<body>
<div>
    <%--导航栏--%>
</div>

<div class="loginForm">
    <form action="dropCourseServlet" method="get">
        <div class="form-group">
            <label>请输入密码确认</label>
            <input type="password" class="form-control" name="cheak">
            <input type="hidden" name="courseId" value="<%=courseId%>">
        </div>
        <input type="submit" value="提交" id="submit" class="btn btn-primary"></td>
    </form>
</div>

</body>
</html>