<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>工作通知</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>res/wallpaper/haiyi/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>res/ext-4.2.1/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=basePath%>res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript"
	src="<%=basePath%>res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	var userName='${userName}';
	var userAccount='${userAccount}';
	var deptName='${deptName}';
	var basePath='<%=basePath%>';
	var ols='${onlineUsers}';
	//处理附件
	var attachname='${xzjxGztz.attachname}';
	var dir='${xzjxGztz.fbrDm}';//传入附件下载目录名
	var url="";
	if (attachname!='null')
	{
	var attachements= attachname.split(',');
	Ext.Array.each(attachements,function(attachement,index){			
	     if (attachement){	 
	     	url+='<a href="'+basePath+'mainPageNotice/xzjx/gztz/downloadAttachmentFile/'+dir+'/'+attachement+'" target=_blank>【'+attachement+'】</a>'+" ";}	       
		}                
	);
	}
</script>

</head>
<body style="background:#fff">
	<div class="tc">

		<div class="neirong">
			<div class="title">
				<h3 class="bt">${xzjxGztz.xxbt}</h3>
				<div class="xxy">
					<span>发布部门：${xzjxGztz.fbrDeptMc}</span> <span>发布人：${xzjxGztz.fbrMc}</span>
					<span>发布时间：${xzjxGztz.fbsj}</span> 
				</div>
			</div>
			<div class="box">${xzjxGztz.content}</div>
			<div class="fj">
				<div class="fj_tit">
					<a name="fujian" id="fujian">附件</a>
				</div>
				<div class="fj_box"><script type="text/javascript">
				document.write(url);
			</script></div>
			</div>

		</div>
	</div>

</body>
</html>
