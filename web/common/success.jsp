<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>操作成功</title>
<%@ include file="/common/meta.jsp" %>
</head>
<body>
<br />
<br />
<br />
<h3 align="center"><s:actionmessage /></h3>
<br />
<br />
<br />
<p align="center">
<s:if test="popup">
<a href="javascript:closeme();">关闭</a>
</s:if>
<s:else>
<a href="javascript:history.back();">返回</a>
</s:else>
</p>
</body>
</html>
<script language=javascript>
function closeme(){
	parent.parent.location.reload();
	parent.parent.GB_hide();
}
</script>
