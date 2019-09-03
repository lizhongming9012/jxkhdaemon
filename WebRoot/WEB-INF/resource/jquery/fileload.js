function createHtml(obj) {
    var htmstr = [];
    htmstr.push(  "<form id='_fileForm' enctype='multipart/form-data'>");
    htmstr.push(  "<table width='100%' cellspacing=\"0\" cellpadding=\"3\" style=\"margin:0 auto; margin-top:20px;\">");
    htmstr.push(  "<tr>");
    htmstr.push( "<input type=\"hidden\" name=\"id\" id=\"xiechaid\" />");
    htmstr.push(  "<td class=\"tdt tdl\"><input  type=\"file\" name=\"imageFile\"  id=\"imageFile\"/></td>");
    htmstr.push(  "<td class=\"tdt tdl tdr\"><input type=\"button\" onclick=\"fileloadon()\" value=\"上传\"/></td>");
    //htmstr.push(  " <td  style=\"border:0px\" class=\"tdt tdl tdr\" colspan='3'style='text-align:center;'><div id=\"msg\">&nbsp;</div></td> ");
    htmstr.push(  "</tr>");
    htmstr.push(  "</table>")
    htmstr.push(  "</form>");
    obj.html(htmstr.join(""));
}

function fileloadon() {
   if(!$("#imageFile").val()){
   	alert("请选择上传文件");
   	return ;
   }
    $("#displayMessage").html("");    
    $("#_fileForm").submit(function () {   
        $("#_fileForm").ajaxSubmit({
            type: "post",
            url: "internet/imageUpload",
            success: function (data1) {
		            var remsg = data1.split("|");
		            if (remsg[0] == "1") {
		            	$("#beizhu").val(remsg[1]); //传递文件名给页面beizhu
			            $("#uploadMsg").text( remsg[2]);
		            }
		            else {
		                $("#displayMessage").html("文件上传失败：" + remsg[2]);
		            }
            },
            error: function (msg) {
                alert("文件上传失败");    
            }
        });
        return false;
    });
    $("#_fileForm").submit();
}
