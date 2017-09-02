<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session ="false" %>
<html>
<head>
	<meta charset="utf-8">
	<title>服务器平台后台考核</title>
	<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	.return{
		width: 400px;
		height: 40px;
		text-align: right;
	}
</style>
</head>
<body>
	<% 
		String infor=(String)request.getAttribute("styles");
		out.println(infor);
	%>
	<br/>
	<div class="return">
		<a href="index.html">返回</a>
	</div>
	
</body>
</html>