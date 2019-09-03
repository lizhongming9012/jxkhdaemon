<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int userChoose=request.getParameter("user")==null?1:0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>00</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="res/ext-4.2.1/resources/css/ext-all.css"/>    
<script type="text/javascript" src="res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript" src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>

<script type="text/javascript">
var mailUserList='<%=userChoose%>';
var userName='${userName}';
var userDeptId='${userDeptId}';
var userAccount='${userAccount}';
var userDeptName='${userDeptName}';
var sysDate='${sysDate}';
var basePath='<%=basePath%>';
var authorityDeptId='${authorityDeptId}';
var authorityDeptName='${authorityDeptName}';
var depId = authorityDeptId.slice(0,7)+'0000';
</script>
<script src="res/jquery/jquery-1.7.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="res/js/sys/manageUser/manageUser.js" defer="defer"></script>
  </head>
  <body>
  </body>
</html>
