<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>${catagoryName}</title>
	<link rel="stylesheet" type="text/css" href="${baseUrlPath}/res/repository/portal/col/${colPath}/css/style.css"/>
	<script type="text/javascript" language="javascript" src="${baseUrlPath}/res/repository/portal/col/${colPath}/js/jquery-1.7.1.min.js"></script>
    <script language="javascript" type="text/javascript">
        $(function($) {
            $('.navli .li').hover(
                    function(){
                        $('.subnav').addClass('hidden');
                        $(this).children('.subnav').removeClass('hidden');
                    },
                    function(){
                        $('.subnav').addClass('hidden');
                    }
            );

        });
    </script>
	<script language="javascript">
	function hiddenThis(){
		var vCatagoryId=${catagoryId};
		var vDiv="div"+vCatagoryId;
		var vUl="ul"+vCatagoryId;
		document.getElementById(vDiv).style.cssText="visibility:hidden;display:none;";
		document.getElementById(vUl).style.cssText="visibility:hidden;display:none;";
	}
	 </script>
    <link rel="stylesheet" type="text/css" href="${baseUrlPath}/res/repository/portal/col/${colPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${baseUrlPath}/res/repository/portal/col/${colPath}/css/backgroud.css"/>

  </head>
  
  <body>
<div class="divcenter header background">
    <div class="headerl">
        <div class="logo"></div>
    </div>
    <div class="headerr">
        <div class="biaoyu"></div>
        <div class="searchbar">
            <script type="text/javascript" src="${baseUrlPath}/res/repository/portal/common/weather.inc"></script>
            <form name="searForm" id="searchForm" method="post" action="" class="search">
                <input type="text" name="keyword" id="keyword" class="text" />
                <input type="button" name="btn" id="btn" class="btn" />
            </form>
        </div>
    </div>

</div>
<div class="divcenter nav">
    <script type="text/javascript" src="${baseUrlPath}/res/repository/portal/common/indexHead.inc"></script>
</div>
<div class="divcenter breadbackground bread fontcolor2" style="height:35px;">
	<span>
    <div class="navl">${navigate}</div>
    </span>
</div>

<div class="divcenter clist" style="">
	<div class="cleft cbackground" >
    	<ul class="content" >
        	<li style="height:50px;"><h1>${content.title}</h1></li>
            <li style="height:50px;text-align:center"><span>发布时间:</span><span>${content.createTime}</span> <span>发布部门:</span><span>${content.source}</span></span><span>发布人:</span><span>${content.author}</span></span></li>
            <li style="">${content.content}</li>
        </ul>
    </div>
    <div class="cright cbackground">
    	<script type="text/javascript" src="${baseUrlPath}/res/repository/portal/common/${topicId}.inc"></script>
    </div>
</div>
<div style="height: 100px; clear: both;"></div>
<div class="divall footer background">
    <div class="divcenter" style="position: relative;">
    <a href="#" class="gotop"></a>
    </div>
</div>
<div style="height: 500px; clear: both;"></div>
</body>
</html>
