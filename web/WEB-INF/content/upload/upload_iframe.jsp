<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/upload.css">
<style type="text/css">
<!--
#uploading1 {
	position:absolute;
	left:64px;
	top:1px;
	width:353px;
	z-index:1;
	font-size:large;
}
-->
</style>
</head>
<body topmargin=0 leftmargin=0>
<div id="uploading1" style="display:none;"><strong>正在上传，请稍候......</strong></div>
<div id="uploading2" style="display:none;">&nbsp;</div>
<div id="uploading3">
<form name="uploadForm" id="uploadForm" action="/upload/file-upload!upload.action" method="post" enctype="multipart/form-data">
<input type='hidden' name='dir1' id="dir1" value='${dir1}'/>
    <input type='hidden' name='dir2' id="dir2" value='${dir2}'/>
    <input type='hidden' name='dir3' id="dir3" value='${dir3}'/>
    <a href="javascript:uploadFile();" class="btn-bar"><span>上传</span></a>
    <input type='file' class='input-def' size='30' name='upload' value='' id='upload'/>
    <s:iterator value="fileUploads">
    &nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/upload/file-upload.action?id=<s:property value='id'/>"><s:property value="fileSaveName"/></a>
    <a href="javascript:deleteFile('<s:property value='id'/>');"><img src="${ctx}/img/delete2.gif" border="0" title="删除附件" /></a>
    </s:iterator>
</form>
</div>
</body>
</html>
<script type="text/javascript">
<!--
if(""!="${message}"){
	alert("${message}");
}

function uploadFile(){
	if(document.getElementById("upload").value==""){
		alert("请选择要上传的文件！");
	}else{
		document.getElementById("uploading1").style.display="";
		document.getElementById("uploading2").style.display="";
		document.getElementById("uploading3").style.display="none";
		document.getElementById("uploadForm").submit();
	}
}

function deleteFile(id){
	if(window.confirm("确定要删除该附件吗？")){
		window.location="/upload/file-upload!delete.action?dir1=${dir1}&dir2=${dir2}&dir3=${dir3}&id="+id;
	}
}

//-->
</script>
