
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

<title>通知发布</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--[if lt IE 9]>
<script src="res/js/util/html5.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="res/ext-4.2.1/resources/css/ext-all.css" />
<script type="text/javascript" src="res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript"
	src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="res/exportToExcel/exportExcel.js"></script>
<script type="text/javascript">
	var userName='${userName}';
var vUserDeptId='${userDeptStandId}';
var vUserDeptName='${userDeptStandName}';
var basePath='<%=basePath%>';
</script>
<script type="text/javascript" defer="defer">
	Ext.onReady(function(){
	var store=Ext.create( 'Ext.data.Store',{
		
		fields : [{
			name : 'id'
		},{
			name : 'xxbt'
		},{
			name : 'fbrDm'
		}, {
			name : 'fbrMc'
		}, {
			name : 'fbsj'
		}, {
			name : 'fbbmDm'
		}, {
			name : 'fbbmMc'
		}, {
			name : 'fbjgDm'
		}, {
			name : 'fbjgMc'
		}, {
			name : 'fbfw'
		}, {
			name : 'content'
		}, {
			name : 'savepath'
		}, {
			name : 'attachname'
		}, {
			name : 'attachnameupload'
		}, {
			name : 'fbsjquery'
		}, {
			name : 'fbrDeptMc'
		}, {
			name : 'fbrDeptDm'
		}, {
			name : 'page'
		}, {
			name : 'limit'
		}, {
			name : 'displayAttachName'
		}],
		autoSync : false,
		autoLoad:true,
		proxy : {
			type : 'ajax',
			url:'admin/desktop/noticeListQuery',
			extraParams:{
				searchContent:'${param.searchContent}'
			},
			reader : {
				type : 'json',
				root : 'records',
				totalProperty : 'totalCount'//后台需返回，记录总数，分页用
			},
			writer : {
				type : 'json',
				idProperty : 'id'
			}
		}
	});

var grid=	Ext.create('Ext.grid.Panel', {
		
		store : store,
		viewConfig : {
			enableTextSelection : true
		},// 可选择
       renderTo:Ext.getBody(),
		border : 0,
		iconCls : 'icon-grid',
		margin : '0 0 20 0',
		title : '通知列表',
		frame : true,
		dockedItems : [{
			xtype : 'pagingtoolbar',
			store : store,
			dock : 'bottom',
			displayInfo : true
		}],
		columns : {
			items : [{
						text : '通知标题',
						dataIndex : 'xxbt',
						width : 400,
						autoScroll : true,
						locked : true,
						renderer:function(value,metaData ,record){
							return '<a href="'+basePath+'admin/desktop/noticePage?id='+record.get('id')+'" target="_blank">'+value+'</a>';
						}
					}, {
						text : '发布部门',
						dataIndex : 'fbbmMc',
						width : 200
					}, {
						text : '发布人',
						dataIndex : 'fbrMc',
						width : 100
					}, {
						text : '发布时间',
						dataIndex : 'fbsj',
						width : 200
					}]
		},
		columnLines : true
	})
})
</script>

</head>
<body>
</body>
</html>
