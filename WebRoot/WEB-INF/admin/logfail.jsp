<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="res/ext-3.0.0/resources/css/ext-all.css" />
<script type="text/javascript"
	src="res/ext-3.0.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="res/ext-3.0.0/ext-all.js"></script>

</head>

<body>
	
	<br>
	
	<table width="20%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align=left valign="top">
				登录失败，没有权限！<a href="admin/login">重新登录</a>
			</td>
		</tr>
	</table>
</body>
</html>
