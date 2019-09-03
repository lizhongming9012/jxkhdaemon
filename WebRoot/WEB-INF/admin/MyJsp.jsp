<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${catagoryName}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${baseUrlPath}/res/ext-4.2.1/resources/css/ext-all.css" />
<script type="text/javascript" language="javascript" src="js/jquery-1.7.1.min.js"></script>
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
<style>
.gridInner
{ font-family: "宋体";
  font-size: 12px;
   text-decoration: none;color: black;
   overflow:hidden;text-overflow:ellipsis;white-space:nowrap;
}
</style>
<script type="text/javascript" src="${baseUrlPath}/res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript" src="${baseUrlPath}/res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">	
	var vCatagoryId='${catagoryId}';//栏目ID
	var vTotalCount=${totalCount};  //该栏目下文章总记录数
	var vStoreUrl='${storeUrl}';    //Ajax后台获取数据的URL
	var vPageSize='${pageSize}';  
</script>
<script type="text/javascript" src="${baseUrlPath}/res/js/portal/templates/catagory.js"></script>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<title>${catagoryName}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/backgroud.css"/>
<script language="javascript">
function hiddenThis(){
	var vCatagoryId=${catagoryId};
	var vDiv="div"+vCatagoryId;
	var vUl="ul"+vCatagoryId;
	document.getElementById(vDiv).style.cssText="display:none;";
	document.getElementById(vUl).style.cssText="display:none;";
}
 </script>

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
    	<div id="${catagoryId}" name="${catagoryId}"></div>
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
