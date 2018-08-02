
<%@page import="Bean.KnowledgePointResourceSwfBean"%>
<%@page import="Bean.KnowledgePointResource"%>
<%@page import="Bean.KnowledgePointBean"%>
<%@page import="java.util.List"%>
<%@page import="Bean.CourseUnit"%>
<%@page import="java.util.Map"%>
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


<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/flexpaper_flash.js"></script>
<script type="text/javascript" src="js/flexpaper_flash_debug.js"></script>
<style type="text/css" media="screen">
html, body {
	height: 100%;
}
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
.unitText{
font-size:20px;
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
<%--class="teacher" 表示老师权限的功能 --%>
<!-- 通过第一次访问此页面的url,获得变量courseId -->
<%  
int courseId = 2;
int permission = 99;
//courseId和permission必须成对出现
if(request.getParameter("courseId") != null){
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
					<li class="nav-item"><a class="nav-link text-success" href="mycourse.jsp"><%=session.getAttribute("username")%></a>
					</li>
					<li class="nav-item"><a class="nav-link text-success"
						href="logoutServlet?page=course.jsp&courseId=<%=courseId %>">登出</a></li>
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



<%--展示课程知识点 --%>
<!--获取变量 unitBegin ，表明新增章节从第几章开始-->
<% 
int unitBegin = 1;
if(request.getAttribute("unitBegin") != null)
unitBegin = (int)request.getAttribute("unitBegin");
%>

<%--展示现有单元及其知识点的列表,并提供对单元名和知识点名的修改功能,以及单元和知识点的新增功能--%>
<!-- 第一次到此页面，请求转发，请求 课程的单元和知识点信息 -->
<%
if(request.getAttribute("allUnits") == null)
request.getRequestDispatcher("returnAllUnitServlet?courseId="+courseId+"&permission="+permission).forward(request, response);
%>
<!-- returnAllUnitServlet转发回来此页面，并且携带特性：allUnits，knowledgePoints-->
<%
if(request.getAttribute("allUnits") != null){
	%>
	<div  style="float:left;margin-left:20px;width:30%;">
	<table class="table table-striped" >
	<% 
	Map<Integer,CourseUnit> allUnits = (Map<Integer,CourseUnit>)request.getAttribute("allUnits");
	Map<Integer,List<KnowledgePointBean>> knowledgePoints = 
			(Map<Integer,List<KnowledgePointBean>>)request.getAttribute("allKnowledgePoints");
	//变量i表示单元索引
			for(int i = 1; i < allUnits.size()+1;i++){
				//变量unit表示获取的单元bean，
				CourseUnit unit = allUnits.get(i);
				//变量unitId表示用于关联知识点List的单元Id
				int unitId = unit.getCourseUnitId();
				//通过单元id获取关联的知识点List，变量points表示单元i所有 的知识点列表
				List<KnowledgePointBean> points = knowledgePoints.get(unitId);
				//单元的现有知识点个数+1，变量count表示单元i所有 的知识点列数量
				int count = points.size()+1;
				%>
				<!-- 展示单元索引与单元名称 -->
	<tr><td class="text-success font-weight-bold text-center unitText">Unit <%= unit.getCourseUnitIndex() %>  <%= unit.getCourseUnitName()%></td></tr>
	<tr><td class="text-center">
	    <div class="btn-group" role="group" aria-label="Basic example">
	    <button type="button" id="alter<%=i %>" class="btn btn-light teacher text-info">修改Unit<%= i %></button>
	    <button type="button" id="addPointUnit<%=i %>" class="btn btn-light teacher text-info">新增Unit<%= i %>知识点</button>
	    </div>
	    </td></tr>

	<!-- 修改单元名称的表单 -->
	<tr class="alter alter<%=i%>"><td>
	<form action='alterCourseUnitServlet?courseId=<%= courseId%>&courseUnitIndex=<%=i %>&permission=<%=permission %>' method='post'>
	<div class="input-group mb-3">
  <input type="text" class="form-control" aria-describedby="basic-addon2" name='alteredUnitName'>
  <div class="input-group-append">
 	<input type='submit' value='submit' class="btn btn-outline-secondary">
  </div>
</div>
	</form></td></tr>
	<!-- 在每个单元下，展示现有知识点,并且提供修改功能 ，现有知识点名称是一个超链接，
	连接到一个servlet，它从服务器获取知识点相关的资源，然后jsp页面展示-->
     <% 
     int d = 1;
        for(KnowledgePointBean point : points){
        	%>
        	<tr>
        	    <td class="text-primary text-center"><%= point.getKnowledgePointIndex() %>.<a href="knowledgePointResourceGetServlet?courseId=<%= courseId%>&knowledgePointId=<%= point.getKnowledgePointId()%>&permission=<%= permission%>"><%= point.getKnowledgePoint() %></a>
        	    <button type="button" id="unit<%=i%>point<%=d%>" class="teacher btn btn-light text-info">修改unit<%=i%>point<%=d%></button>
        	    </td></tr>
        	<tr><td>
        	<form action="alterKnowledgePointServlet?knowledgePointId=<%=point.getKnowledgePointId()%>&courseId=<%=courseId %>&permission=<%=permission %>" method="post">
        	<div class="input-group mb-3">
  <input type="text" class="form-control" aria-describedby="basic-addon2" name="alteredPoint">
  <div class="input-group-append">
    <input class="input-group-text btn btn-outline-secondary" id="basic-addon2" type="submit" value="submit">
  </div>
</div></form></td></tr>
        	<% 
        	d++;
        }
     %>
	
	<!-- 在每个单元内，在现有知识点基础上，新增知识点的表单 -->
	<tr class="addPointUnit<%=i%>">
	<td>
	<form action="addPointServlet?courseUnitId=<%=unit.getCourseUnitId()%>&courseId=<%=courseId %>&permission=<%=permission %>" method="post" enctype="multipart/form-data">
	<span id="span<%=i %>"></span>
	<input type='submit' value='submit' id='deletePointUnit<%=i%>' class="btn btn-primary">
	</form></td></tr>
				<% 
			}
			%>
	</table>
	
		<%--在现有单元基础上，添加单元的表单 --%>	
<form action="addCourseUnitServlet?courseId=<%= courseId%>&permission=<%=permission %>"  
method="post" enctype="multipart/form-data" class="teacher">
<table id="1" class="table table-striped">

<tr class="unit">
<td class="text-success font-weight-bold text-center unitText">Unit<%= unitBegin %></td>
<td>
<input type="text" class="form-control" aria-describedby="basic-addon2" name="unit<%= unitBegin%>" placeholder="unit<%= unitBegin%>">
</td>
</tr>

<tr>
<td class="text-center"><button type="button" id="addUnit" class="btn btn-primary">新增章节</button></td>
<td class="text-center"><input type="submit" value="submit" class="btn btn-primary"></td>
</tr>
</table>
</form>
</div>
<% 
			}
%>




<div class="resource" style="float:right;margin-right:20px;width:60%;" >

<!-- 知识点相关的资源（ppt/视频等）必须有knowledgePointId,videos,ppts请求特性参数 -->
<% if(request.getParameter("knowledgePointId") != null){
	int knowledgePointId = Integer.parseInt(request.getParameter("knowledgePointId"));
	List<KnowledgePointResource> videos = (List<KnowledgePointResource>)request.getAttribute("videos");
	List<KnowledgePointResourceSwfBean> ppts = (List<KnowledgePointResourceSwfBean>)request.getAttribute("ppts");
	System.out.println("以下在courseKnowledgePoint页面");
	System.out.println(videos);
	System.out.println(ppts);
	System.out.println("知识点ID："+knowledgePointId);
	System.out.println("courseKnowledgePoint页面输出结束");
	%>
	
	<!-- 上传知识点的资源-->
	<div>
<font color="red">${message}</font>
<form action="knowledgePointResourceUpload?courseId=<%= courseId%>&knowledgePointId=<%=knowledgePointId %>&permission=<%=permission %>" 
method="post" enctype="multipart/form-data" class="teacher">
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
<td><input type="submit" value="submit" class="btn btn-primary"></td>
<td><button id="addFile" type="button" class="btn btn-primary">新增一个附件</button></td>
</tr>
</table>
</form></div>

<!-- 展示知识点的所有视频 -->
<%
if(videos != null){
for(KnowledgePointResource video : videos){
	int resourceId = video.getResourceId();
	System.out.println(video.getResourceRelativePath());
	%>
	<div style="box-sizing:content-box;padding-left:20px;padding-bottom:20px;" class="border-left border-bottom border-info">
<button type="button"  class="btn btn-secondary"  style="margin-top:10px;">
<a href="deleteKnowledgePointVideoServlet?resourceId=<%=resourceId %>&courseId=<%=courseId%>&knowledgePointId=<%=knowledgePointId%>&permission=<%= permission%>" class="teacher text-white">删除</a>
</button><div>
<video src="<%=video.getResourceRelativePath()%>" width="750" height="500" controls="controls" >
Your browser does not support the video tag.
</video></div>
	</div>
	<% 
}
}
//展示知识点的所有ppt
if(ppts != null){
	//变量r表示ppt个数索引
	int r = 1;
for(KnowledgePointResourceSwfBean swf : ppts){
	String swfRelativePath = swf.getResourceRelativePath();
	int resourceId = swf.getResourceId();
	%>
	
	<div style="box-sizing:content-box;padding-left:20px;padding-bottom:20px;" class="border-left border-bottom border-info">
	<button type="button" class="btn btn-secondary" style="margin-top:10px;margin-bottom:10px;">
	<a href="deleteKnowledgePointSwfServlet?resourceId=<%=resourceId %>&courseId=<%=courseId%>&knowledgePointId=<%=knowledgePointId%>&permission=<%=permission %>" class="teacher text-white">删除</a>
	</button>
		<a id="viewerPlaceHolder<%=r %>"
			style="width: 750px; height: 650px; display: block"></a>
		<script type="text/javascript">   
                var fp = new FlexPaperViewer(     
                         'FlexPaperViewer',  
                         'viewerPlaceHolder<%=r%>', { config : {  
                         SwfFile : escape('<%=swfRelativePath%>'), 
			//编码设置  
							Scale : 0.6,
							ZoomTransition : 'easeOut',//变焦过渡  
							ZoomTime : 0.5,
							ZoomInterval : 0.2,//缩放滑块-移动的缩放基础[工具栏]  
							FitPageOnLoad : true,//自适应页面  
							FitWidthOnLoad : true,//自适应宽度  
							FullScreenAsMaxWindow : false,//全屏按钮-新页面全屏[工具栏]  
							ProgressiveLoading : false,//分割加载  
							MinZoomSize : 0.2,//最小缩放  
							MaxZoomSize : 3,//最大缩放  
							SearchMatchAll : true,
							InitViewMode : 'Portrait',//初始显示模式(SinglePage,TwoPage,Portrait)  

							ViewModeToolsVisible : true,//显示模式工具栏是否显示  
							ZoomToolsVisible : true,//缩放工具栏是否显示  
							NavToolsVisible : true,//跳页工具栏  
							CursorToolsVisible : false,
							SearchToolsVisible : true,
							PrintPaperAsBitmap : false,
							localeChain : 'en_US'
						}
					});
		</script>
	</div>
<% 
r++;
}
}
}
%>
</div>


<script type="text/javascript">
$(function(){
	//在现有单元基础上，增加单元
	var i = <%= unitBegin%> + 1;
	$("#addUnit").click(function(){
		$(this).parent().parent().before("<tr class='unit added'><td class='text-success font-weight-bold text-center unitText'>Unit"
				+i+"</td><td>"
				+"<div class='input-group mb-3'><input type='text' class='form-control' aria-describedby='basic-addon2' name='unit"
				+i+"' placeholder='unit"
				+i+"'><button type='button' class='close' aria-label='Close' id='delete"
				+i+"'><span aria-hidden='true'>&times;</span></button></div></td></tr>");
		i++;
		$("#delete"+(i-1)).click(function(){
		     $(this).parent().parent().parent().remove();
		     
		     $(".unit").each(function(index){
		    	var n = index + <%= unitBegin%>;
		    	$(this).find("td:first").text("Unit"+n);
		    	$(this).find("td:last input").attr("name","unit"+n);
		    	$(this).find("td:last input").attr("placeholder","unit"+n);
		     });
		     $(".added").each(function(index){
		    	 var n = index + <%= unitBegin%> + 1;
		    	 $(this).find("button").attr("id","delete"+n);
		     });
		     i--;
		});
	});
	
	//循环绑定事件（修改单元名称的表单）
	$(".alter").hide();
for(var q = 1; q< <%= unitBegin%> ; q++){
	$("#alter"+q).bind("click",{index:q},alterClickHandler);
}
function alterClickHandler(event){
	var q = event.data.index;
	$(".alter"+q).toggle();
}

//循环绑定事件（新增  单元的知识点）
//数组内存储对应单元的新增知识点按钮被按下的次数，一开始均为0
//p表示单元索引，500表示课程最多有500个单元
var time = new Array(500);
for(var p = 1; p < <%= unitBegin%> ; p++){
	time[p-1] = 0 ;
}

//在获取所有单元和知识点之后，循环绑定所有单元的新增知识点按钮的点击事件，k表示单元索引,t表示对应单元的新增知识点数量
<%
if(request.getAttribute("allUnits") != null){
	 Map<Integer,Long> knowledgePointsCount = 
			 (Map<Integer,Long>)request.getAttribute("knowledgePointsCount");
for(int k = 1 ; k < unitBegin ; k++){
	%>
	$("#deletePointUnit"+<%= k %>).hide();
	
	$("#addPointUnit"+<%= k %>).bind("click",{index:<%= k %>},function(event){
		var k = event.data.index;
		var t = time[k-1];
		t = t + 1;
		time[k-1] = t;
		
		$("#addPointUnit"+k).parent().parent().parent().parent().find("#span"+k)
		.before("<span class='input-group mb-3 addedPoint"
				+k+"'><input type='text' class='form-control' aria-describedby='basic-addon2' name='addedPoint"
				+(t+<%= knowledgePointsCount.get(k)%>)+"' placeholder='addedPoint"
				+(t+<%= knowledgePointsCount.get(k)%>)+"'><button type='button' aria-label='Close' class='close unit"
				+k+"' id='point"
				+(t+<%= knowledgePointsCount.get(k)%>)
				+"'><span aria-hidden='true'>&times;</span></button></span>");	
		//alert(<%= knowledgePointsCount.get(k)%>);
		$(".unit"+k+"#point"+(t+<%= knowledgePointsCount.get(k)%>)).click(function(){
			$(this).parent().remove();
			//删除之后要对新增知识点输入框重新排序
			t =  time[k-1];
			t = t -1;
			time[k-1] = t;
			$(".addedPoint"+k).each(function(index){
				var g = index + 1 + <%= knowledgePointsCount.get(k)%>;
				$(this).find("input").attr("name","addedPoint"+g);
				$(this).find("input").attr("placeholder","addedPoint"+g);
				$(this).find("button").attr("id","point"+g);
			})
			//删除后要减少新增知识点数量
			//alert("delete：新增知识点数"+t);
		   // alert("delete：单元索引"+k);
		  //  alert(time[k-1]);
			if(time[k-1] == 0){
				$("#deletePointUnit"+k).hide();	
			}
		});
		if(time[k-1] >= 0){
			$("#deletePointUnit"+k).show();	
		}

		//alert("add:新增知识点数"+t);
	   // alert("add：单元索引"+k);
		
	});
	<%
}
}
%>

//修改单元的知识点(循环绑定)
<%
if(request.getAttribute("allUnits") != null){
	 Map<Integer,Long> knowledgePointsCount2 = 
			 (Map<Integer,Long>)request.getAttribute("knowledgePointsCount");
	 //双重循环绑定知识点的修改事件
for(int u = 1; u < unitBegin;u++){
	for(int poi = 1 ; poi <= knowledgePointsCount2.get(u);poi++){
		%>
		//一开始总是隐藏修改按钮
		$("#unit"+<%=u%>+"point"+<%=poi%>).parent().parent().next().hide();
		$("#unit"+<%=u%>+"point"+<%=poi%>).bind("click",{unit:<%=u%>,point:<%=poi%>},function(event){
			var u = event.data.unit;
			var poi = event.data.point;
			$(this).parent().parent().next().toggle();
		});
		
		
		
		<%
	}
}
}
%>

//对应知识点资源的上传

	//y表示一共有多少个上传input
	var y = 1;
	$("#addFile").click(function(){
		y++;
		$(this).parent().parent().before("<tr class='file'><td class='text-info text-center'>File"
				+y+":</td><td>"
				+"<div class='input-group mb-3'><div class='custom-file'>"
				+"<input type='file' class='custom-file-input' name='file"
				+y+"'><label class='custom-file-label'>Choose file</label></div></div>"
				+"</td></tr>"
				+ "<tr class='dsc addedFile'><td class='text-info text-center'>dsc"
				+y+":</td><td><div class='form-group'><input type='text' class='form-control' name='dsc"
				+y+"'></div><button type='button' class='btn btn-light' id='deleteFile"
				+y+"'>刪除</button></td></tr>");
		//alert("add"+y);
		
		
		
		//获取新添加的删除按钮
		$("#deleteFile"+y).click(function(){
			var $tr = $(this).parent().parent();
			$tr.prev("tr").remove();
			$tr.remove();
			y--;
			//alert("1delete"+y);
			//对y重新排序
			$(".file").each(function(index){ 
				var h = index + 1;
				$(this).find("td:first").text("File"+h);
				$(this).find("td:last input").attr("name","file"+h);
			});
			
			$(".dsc").each(function(index){ 
				var h = index + 1;
				$(this).find("td:first").text("dsc"+h);
				$(this).find("td:last input").attr("name","dsc"+h);
			});
			
			$(".addedFile").each(function(index){
				var h = index + 2;
				$(this).find("button").attr("id","deleteFile"+h);
			});
			//alert("2delete"+y);
			
		});
	});

});
</script>
<script type="text/javascript">
<%
if(permission != 3){
	%>
	$(".teacher").hide();
	<%
}
%>
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