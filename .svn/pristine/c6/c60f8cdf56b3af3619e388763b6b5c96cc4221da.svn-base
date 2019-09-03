<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String error_msg = request.getParameter("error_msg");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css"
	href="res/ext-4.2.1/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="res/ext-4.2.1/example.css" />
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<script type="text/javascript" src="res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript"
	src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="res/ext-4.2.1/examples.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var error_msg = '${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}';
		document.forms[0].j_username.focus();
		if (error_msg.length > 2)
			Ext.example
					.msg('警告:',
							'${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}');
	}
	function submitForm() {
		document.getElementById('iform').submit();
	}
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.caller.arguments[0];
		if (e && e.keyCode == 13) {
			submitForm();
		}
	}
</script>
<title>绩效管理平台</title>
</head>
<body>
	<div class="htmleaf-container">
		<div class="wrapper">
			<div class="container">
				<h1>绩效管理平台</h1>
				<form name="loginform" action="j_spring_security_check"
					method="POST" id="iform">
					<input id="username" type='text' name="j_username"
						placeholder="Username"> <input id="pwd" type='password'
						name='j_password' placeholder="Password">
					<button type="submit" id="login-button" alt=""
						onclick="submitForm();" style="cursor:pointer;">Login</button>
				</form>
			</div>
		</div>
	</div>
	<div
		style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
	</div>
</body>
</html>
