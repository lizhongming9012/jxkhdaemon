<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>绩效管理平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="res/js/sys/desktop/desktop/css/desktop.css" />
<!--  <link href="res/wallpaper/css/base.css" rel="stylesheet">-->
<!--[if lt IE 7]>
<link href="res/wallpaper/css/ie6base.css" rel="stylesheet">
<![endif]-->
<!--[if gt IE 7]>
<link href="res/wallpaper/css/base.css" rel="stylesheet">
<![endif]-->
<!--[if IE 6]>
	<script src="res/wallpaper/haiyi/js/iepng.js" type="text/javascript"></script>
	<script>
	if(!window.XMLHttpRequest) 
       EvPNG.fix('div,img');
 EvPNG.fix('.fyj,.sx,.tongzhi,.daiban,.youjian,.lianjie,.zt,.ts');
	</script>
	<![endif]-->

<link href="res/wallpaper/css/index.css" rel="stylesheet">
<link type="text/css" href="res/wallpaper/css/datePicker.css"
	rel="stylesheet" />
<link type="text/css" rel="stylesheet"
	href="res/wallpaper/haiyi/css/style.css" />
<style type="text/css">
body {
	height: 100%;
	font-family: "微软雅黑", "宋体" !important;
	min-width: 900px;
	font-size: 12px
}

#winpop {
	width: 200px;
	height: 0px;
	position: absolute;
	right: 0;
	bottom: 28px;
	border: 0px solid #999999;
	margin: 0;
	padding: 1px;
	overflow: hidden;
	display: none;
	background: #FFFFFF
}

#winpop .title {
	width: 100%;
	height: 20px;
	line-height: 20px;
	background: #4169E1;
	font-weight: bold;
	text-align: center;
	font-size: 12px;
}

#winpop .con {
	width: 100%;
	height: 80px;
	line-height: 80px;
	font-weight: bold;
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
	text-align: center;
	cursor: pointer
}

.close {
	position: absolute;
	right: 4px;
	top: -1px;
	color: #FFFFFF;
	cursor: pointer
}
</style>
<!-- GC -->

<!-- <x-compile> -->
<!-- <x-bootstrap>-->
<script type="text/javascript" defer="defer">
var userName='${userName}';
var userAccount='${userAccount}';
var deptName='${deptName}';
var basePath='<%=basePath%>';
var ols='${onlineUsers}';
var sysDate='${sysDate}';

var store = [];
</script>
<!--[if IE ]>
<script type="text/javascript">
window.onload=function(){
		window.opener.close();
	}
</script>
<![endif]-->
<script type="text/javascript"
	src="res/js/sys/desktop/shared/include-ext.js"></script>

<script type="text/javascript"
	src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>


<script src="res/wallpaper/js/jquery-1.10.2.js" type="text/javascript"></script>
<script type="text/javascript"
	src="res/wallpaper/js/jquery.datePicker-min.js"></script>


<script type="text/javascript" src="res/wallpaper/js/html5.js"></script>


<!-- </x-bootstrap> -->
<script type="text/javascript" defer="defer">
	window.onload = function(){
		//publicBusi();
	}
	
	function publicBusi(){
//		setTimeout(publicBusi,1000*10*60);
        var alartDate = Ext.util.Format.date(new Date(), 'Y-m-d');
		var alartTime = Ext.util.Format.date(new Date(), 'H:i:s');
        Ext.Ajax.request({
             url : 'admin/xzjx/ksrc/getRcRemindContent',
             method : 'post',
			 params : {
			 	date:alartDate,
			 	dayTime:alartTime,
			 	userName:userAccount
			 },
             success : function(response, opts) {
             		var text = Ext.decode(response.responseText);
             		if(text.total != 0){
             			var divTitle = document.getElementById("titleId");
             			 divTitle.innerHTML = "今日日程提醒";
             			 var span = document.createElement("span");
             			 span.setAttribute("class","close");
             			 span.setAttribute("onclick","tips_pop()");
             			 span.innerHTML = "X";
             			 divTitle.appendChild(span);
             			 
             			 var divTitle = document.getElementById("conId");
             			 divTitle.innerHTML = "未读日程(" + text.total + ")";
             			 
             			 store = text.content;
             			 document.getElementById('winpop').style.height='0px';//我不知道为什么要初始化这个高度,CSS里不是已经初始化了吗,知道的告诉我一下
        				 tips_pop();     //3秒后调用tips_pop()这个函数 
             			 
             		}
/*              	 	 var html="";
                     var obj = Ext.decode(response.responseText);
                     for( i=0;i<obj.length;i++){
                    	 html+='<li><a title="'+obj[i].subject+'" href="admin/mail/mailInfo?messageNumber='+obj[i].messageNumber+'&active='+obj[i].active+'" target="_blank">'+obj[i].subject+'</a><span class="nameFrom">'+obj[i].nameFrom+'</span><span class="time">'+obj[i].sentDate+'</span></li>';
                     }
                     $(".email-item").html(html);
                     Ext.get("mail-ul").el.unmask();  */
             }
     	});
	}
	
	function lookRcgl(){
/* 		var gridRcgl = new Ext.create('Ext.grid.Panel',{
			store:store,
			viewConfig:{
				forceFit:true
			},
			columns:[
				{header:'日程内容',dataIndex:'ksrcContent',width : 590}
			]
		}); */
		Ext.define('RcCont',{
			extend:'Ext.data.Model',
			fields:[
				{name:'ksrcContent',type:'string'}
			
			]
		});
		
		var myStoreRc = Ext.create('Ext.data.Store',{
			model:'RcCont'
		});
		
		myStoreRc.loadData(store);
		
		var winRcgl = new Ext.create('Ext.window.Window', {
			title:'日程内容',
			width:600,
			height:400,
			layout:'fit',
			items:{
				xtype:"grid",
				store:myStoreRc,
				columns:[
					{text : '今日日程内容',dataIndex:'ksrcContent',flex:1,align:'center'}
				]
				
			}
		});
		
		winRcgl.show();
	}
	
	function tips_pop(){
        var MsgPop=document.getElementById("winpop");//获取窗口这个对象,即ID为winpop的对象
        var popH=parseInt(MsgPop.style.height);//用parseInt将对象的高度转化为数字,以方便下面比较
        if (popH==0){         //如果窗口的高度是0
            MsgPop.style.display="block";//那么将隐藏的窗口显示出来
            show=setInterval("changeH('up')",20);//开始以每0.002秒调用函数changeH("up"),即每0.002秒向上移动一次
        }
        else {         //否则
            hide=setInterval("changeH('down')",20);//开始以每0.002秒调用函数changeH("down"),即每0.002秒向下移动一次
        }
    }//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
    function changeH(str) {
        var MsgPop=document.getElementById("winpop");
        var popH=parseInt(MsgPop.style.height);
        if(str=="up"){     //如果这个参数是UP
            if (popH<=100){    //如果转化为数值的高度小于等于100
                MsgPop.style.height=(popH+4).toString()+"px";//高度增加4个象素
            }
            else{
                clearInterval(show);//否则就取消这个函数调用,意思就是如果高度超过100象度了,就不再增长了
            }
        }//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
        if(str=="down"){
            if (popH>=4){       //如果这个参数是down
                MsgPop.style.height=(popH-4).toString()+"px";//那么窗口的高度减少4个象素
            }
            else{        //否则
                clearInterval(hide);    //否则就取消这个函数调用,意思就是如果高度小于4个象度的时候,就不再减了
                MsgPop.style.display="none";  //因为窗口有边框,所以还是可以看见1~2象素没缩进去,这时候就把DIV隐藏掉
            }
        }
    }
    
    
	
	Ext.Loader.setPath({
            'Ext.ux.desktop': 'res/js/sys/desktop/desktop/js',          
    		'Sys.store.SysTree':'res/js/sys/store/SysTree.js',
    		'Sys.model.TreeNode':'res/js/sys/model/TreeNode.js',
             MyDesktop: 'res/js/sys/desktop/desktop'
        });

     Ext.require('MyDesktop.App');
     Ext.require('Sys.store.SysTree');
     Ext.require('Sys.model.TreeNode');
    
     var myDesktopApp;
     var dateInterval=setInterval(function(icnt){ 
   	   //if(window.screen.width<1050){
   		 //$("#leftbottom").css("display","none");
   	   //}
 	   if(!$(".datepicker").html()){
    	$(".datepicker").datePicker({
		inline : true,
		selectMultiple : false
	    });
 	   }else{
 		   clearInterval(dateInterval);
 	   }
 	   },150);
     Ext.onReady(function () {
    	 
    	 
     	 treeStore = new Sys.store.SysTree();
     	 var func=function(){
     	 myDesktopApp = new MyDesktop.App();
     	
     	 };
     	 var me=this;
     	 treeStore.on('load', func,me, {
              buffer: 1
          });        	
         
     });
     
     function force_logout(){
     	Ext.Ajax.request({
	url : basePath + 'j_spring_security_logout'
});
     }
     //邮件刷新
     function refreshMail(){   
    	 Ext.get("mail-ul").el.mask('加载中...');
    	 Ext.Ajax.request({
             url : 'admin/mail/unreadQuery',
             success : function(response, opts) {
            	 var html="";
                     var obj = Ext.decode(response.responseText);
                     for( i=0;i<obj.length;i++){
                    	 html+='<li><a title="'+obj[i].subject+'" href="admin/mail/mailInfo?messageNumber='+obj[i].messageNumber+'&active='+obj[i].active+'" target="_blank">'+obj[i].subject+'</a><span class="nameFrom">'+obj[i].nameFrom+'</span><span class="time">'+obj[i].sentDate+'</span></li>';
                     }
                     $(".email-item").html(html);
                     Ext.get("mail-ul").el.unmask();
             }
     });
     }
     //通知刷新
     function refreshNotice(){ 
    	 Ext.get("notice-ul").el.mask('加载中...');
    	 Ext.Ajax.request({
             //url : 'admin/xzjx/gztz/gztzquery',
               url : 'admin/xzjx/gztz/gztzviewquery', 
             success : function(response, opts) {
            	 var html="";
                     var obj = Ext.decode(response.responseText).records;
                     for( i=0;i<Math.min(obj.length,8);i++){
                    	 var temp=(i%2===0)?"even":"liodd";
                    	 if(obj[i].fbsjquery==sysDate){
                    	 	html+=  '<li class='+temp+'><span class="xxbt"><a style="color:red;" title="'+obj[i].xxbt+'" href="javascript:noticePage(\''+obj[i].id+'\')">'+obj[i].xxbt+'</a></span><span class="fbrDeptMc">'+obj[i].fbrDeptMc+'</span></li> '  ;
                    	 }else{
                     	 	html+=  '<li class='+temp+'><span class="xxbt"><a title="'+obj[i].xxbt+'" href="javascript:noticePage(\''+obj[i].id+'\')">'+obj[i].xxbt+'</a></span><span class="fbrDeptMc">'+obj[i].fbrDeptMc+'</span></li> '  ;
                    	 }
                     }
                     $(".notice-item").html(html);
                     Ext.get("notice-ul").el.unmask();
             }
     });
     }
     //待办事项刷新
     function refreshTodo(){   
    	 Ext.get("todo-ul").el.mask('加载中...');
    	 Ext.Ajax.request({
             url : 'admin/workflow/getTodoList',
             params:{page:1,limit:5,userAccount:userAccount},
             success : function(response, opts) {
            	 var html="";
                     var obj = Ext.decode(response.responseText).records;
                     for( i=0;i<obj.length;i++){
                    	 html+=  '<li><span class="todo"><a title="'+obj[i].title+'" href="javascript:todoPage(\''+obj[i].uri+'\',\''+obj[i].baseId+'\',\''+obj[i].registerId+'\',\''+obj[i].currentNode+'\',\''+obj[i].currentNodeMc+'\',\''+obj[i].senderNodeDm+'\',\''+obj[i].senderNodeMc+'\',\''+obj[i].forwardOrBack+'\')">'+obj[i].title+'</a></span></li>  ' ;
                     }
                     $(".todo-item").html(html);
                     Ext.get("todo-ul").el.unmask();
             }
     });
     }
    //通知浏览 -old
      function noticePage(id){
 		var url=basePath+"admin/desktop/noticePage?id="+id;
 		var prop="height="+(screen.height)*0.92+",width="+(screen.width)*0.99+",toolbar=no,resizable=yes,top=0,left=0";
 		window.open(url,"_blank",prop);
 		/* var imenu = new MyDesktop.BogusModule();
		imenu.app = appme;
		imenu.wndId = child.get('id');
		imenu.text = child.get('text');
		imenu.id = 'bogus' + child.get('id');
		imenu.customHtml = '<iframe  id="frame'
				+ child.get("id")
				+ '" src="'
				+ child.get("href")
				+ '" width=100% height="100%" frameBorder="0"></iframe>'; */
 		
 	}  
    //通知浏览 
    /* function noticePage(id){
 		var url=basePath+"admin/desktop/noticePage?id="+id;
 		window.open(url,"_blank");
 	} */
    //待办事宜浏览 
     function todoPage(uri,baseId,registerId,currentNode,currentNodeMc,senderNodeDm,senderNodeMc,forwardOrBack){
    	 var url=basePath+uri+"?baseId="+baseId+"&registerId="+registerId+
    			 "&currentNode="+currentNode+"&currentNodeMc="+currentNodeMc+
    			 "&senderNodeDm="+senderNodeDm+"&senderNodeMc="+senderNodeMc+
    			 "&forwardOrBack="+forwardOrBack;
 		window.open(encodeURI(url),"_blank");
 	} 
    //通知搜索
     function noticeSearch(){
    	var searchContent=document.getElementById('searchContent').value;
 		//var url=basePath+"admin/xzjx/gztz/gztzquery?xxbt="+searchContent;
 		var url=basePath+"admin/desktop/noticeQuery?searchContent="+searchContent;
 		window.open(url,"_blank");
 	} 
    //待办事宜more
     function todoMore(){
    	
 		var url=basePath+"admin/desktop/todoMore";
 		window.open(url,"_blank");
 	} 
    //通知more
     function noticeMore(){
    	
 		var url=basePath+"admin/desktop/noticeMore";
 		window.open(url,"_blank");
 	} 
 	//新邮件
 	function newMail(){
 	var url=basePath+"admin/mail/newMail";
 	//var prop="height="+(screen.height)/2*1.2 +",width="+(screen.width)/ 2*1.5+",titlebar=no,menubar=no,status=no,location =no,toolbar=no,resizable=no,top=200,left=200";
 	var prop="height=520,width=650,titlebar=no,menubar=no,status=no,location =no,toolbar=no,resizable=no,top=100,left=200";
 		window.open(url,"desktopnewmail",prop);
 	
 	}
 	
    //日程通知
    function rcglNotice(id){
		var url=basePath+"admin/desktop/rcglPage?id="+id;
		var prop="height="+(screen.height)*0.92+",width="+(screen.width)*0.99+",toolbar=no,resizable=yes,top=0,left=0";
		window.open(url,"_blank",prop);		
	}  
    
 	//修改密码
 	function changePwd(){
 	var dlg = new Ext.create('Ext.window.Window', {
			title : '修改密码',
			layout : 'fit',
			width : 380,
			height : 160,
			items : {
				xtype : 'form',
				id : 'pwdForm',

				layout : 'vbox',
				items : [{
							xtype : 'textfield',

							name : 'newpass1',
							id : 'newpass1',
							width : 260,
							inputType : 'password',
							labelWidth : 95,
							labelAlign : 'right',
							allowBlank : false,
							msgTarget : 'side',
							fieldLabel : '新密码'
						}, {
							xtype : 'textfield',
							name : 'newpass2',
							id : 'newpass2',
							width : 260,
							inputType : 'password',
							labelWidth : 95,
							labelAlign : 'right',
							allowBlank : false,
							msgTarget : 'side',
							fieldLabel : '确认新密码'
						}],
				buttons : [{
					text : '确认',
					name : 'subbutton',
					action : 'modifyPwd',
					handler : function(btn) {
						var vpwd1 = Ext.getCmp('newpass1').value;
						var vpwd2 = Ext.getCmp('newpass2').value;
						var vForm = Ext.getCmp('pwdForm');
						if (vForm.getForm().isValid()) {
							if (vpwd1 == vpwd2) {
								vForm.el.mask('保存中...');
								vForm.getForm().submit({
									clientValidation : true,
									url : 'admin/sys/desktop/modifyPwd',
									success : function(form, action) {
									if(action.result.success){
													Ext.Msg.alert('提示', "修改成功");
										          dlg.close();
												}else{
													Ext.Msg.alert('提示', "修改失败");
													vForm.el.unmask();
												}					
										

											
									},
									failure : function(form, action) {
										switch (action.failureType) {
											case Ext.form.action.Action.CLIENT_INVALID :
												Ext.Msg
														.alert('Failure',
																'Form fields may not be submitted with invalid values');
												break;
											case Ext.form.action.Action.CONNECT_FAILURE :
												Ext.Msg
														.alert('Failure',
																'Ajax communication failed');
												break;
											case Ext.form.action.Action.SERVER_INVALID :
												Ext.Msg.alert('Failure',
														action.result.msg);
														
										}
										vForm.el.unmask();
									}
								});

							} else {
								Ext.Msg.alert('两次输入不一致，请检查!');
							}
						}
					}
				}]
			}
		});
		dlg.show();
 	}
 	//注销
 	function logout(){
 		Ext.Msg.confirm('登出', '您确定退出系统吗？', function(btn, text) {
					if (btn == 'yes') {
						top.close();
						//location.href = basePath + '/sys/logout';
					}
				});
 	}
 	function onswitch(id){
 	var ids=["tz","dbsx","lj"];
 	 var div=document.getElementById(id);
 	 div.style.visibility="visible";
 	 for(var i=0;i<ids.length;i++){
	 	 if (id!=ids[i]){
	 	 var div=document.getElementById(ids[i]);
	 	   div.style.visibility="hidden";
	 	 }	 	 
 	 }
 	}
</script>
<!-- </x-compile> -->

</head>

<body>
	<div id="winpop">
		<div class="title" id="titleId"></div>
		<div class="con" id="conId" onClick="lookRcgl()"></div>
	</div>
</body>
</html>
