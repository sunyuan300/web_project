<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
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
    <table>
        <thead>
            <caption>查询结果</caption>
                <tr>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>学号</th>
                    <th>专业</th>
                </tr> 
        </thead>
    <% 
        ArrayList al = (ArrayList) request.getAttribute("arr");
        for (int i=0;i<al.size();i+=4)
		{
	%>
			<tr>
				<td><%out.println(al.get(i));%></td>
				<td><%out.println(al.get(i+1));%></td>
				<td><%out.println(al.get(i+2));%></td>
				<td><%out.println(al.get(i+3));%></td>
			</tr>
	<%		
		}
	%>
        <tbody>
		</tbody>
    </table>
	<br/>
	<div class="return">
		<a href="index.html">返回</a>
	</div>	
</body>
</html>