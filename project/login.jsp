<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session ="false" %>
<html>
<head>
	<meta charset="utf-8">
	<title>系统登录</title>
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
		<a href="login.html">返回登陆界面</a>
	</div>
	
</body>
</html>