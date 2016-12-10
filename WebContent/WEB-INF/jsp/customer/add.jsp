<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>客户新增</title>
		<link href="${pageContext.request.contextPath}/static/css/application.css" rel="stylesheet">
	</head>
	
	<body>
		<form action="<%=path%>/customer/add" method="post">
			<table width="100%" border="1" cellpadding="0" cellspacing="1" class="tableLine">
				<tr>
					<td colspan=4 align=center class="tableLineBg">客户新增</td>
				</tr>
				<tr>
					<td>客户编号</td>
					<td><input type="text" name="customerId" class="input"></td>
					<td>客户密码</td>
					<td><input type="text" name="pwd" class="input"></td>
				</tr>
				<tr>
					<td>显示名称</td>
					<td><input type="text" name="showName" class="input"></td>			
					<td>真实姓名</td>
					<td><input type="text" name="trueName" class="input"></td>
				</tr>
				<tr>
					<td colspan=4 align=center><input type="submit" value="新增" class="button"></td>
				</tr>
			</table>
		</form>
	</body>
</html>