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
<base href="<%=basePath%>">

<title>工作通知</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="res/ext-4.2.1/resources/css/ext-all.css" />
<script type="text/javascript" src="res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript"
	src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>
<style type="text/css">

body{ margin:0px; padding:0px; font-size:16px; font-family:"宋体";background-color:#ffffff;text-decoration:none}
.main{margin:0 auto;width:900px;}
.tittle{text-align:center; margin:30px 0 10px 0; font-family:"楷体"; color:#00629C; line-height:30px; text-decoration:none; width:100%;font-weight:bold; font-size:34px;}
.xgxx{text-align:center;width:100%; float:left;font-size:12px;color:#00629C;}
.content{height:500px; width:100%;overflow: auto;}
.fujianImg{ background-color:#e0ecf9;height:32px; width:100%; float:left;}
.fujian{ height:50px; width:100%; float:left;font-size:14px;}

a:link {
	text-decoration: none;
	color: #000;
}
a:visited {
	text-decoration: none;
	color: #000;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}

</style>
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
<body>
	<div class="main">
		<div id="tittle" class="tittle">${xzjxGztz.xxbt}</div>
		<div id="xgxx" class="xgxx">
				发布部门：${xzjxGztz.fbrDeptMc}&nbsp;&nbsp;发布人：${xzjxGztz.fbrMc}&nbsp;&nbsp;
				发布时间：${xzjxGztz.fbsj}
				<br><hr>
		</div>
		<div class="content">${xzjxGztz.content}</div>
		<div class="fujianImg">
			<img src="res/wallpaper/images/appendix.jpg" />
		</div>
		<div class="fujian">
			<script type="text/javascript">
				document.write(url);
			</script>
		</div>
	</div>
</body>
<script type="text/javascript" defer="defer">
	
</script>
</html>
