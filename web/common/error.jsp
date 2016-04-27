<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%@ include file="/common/taglibs.jsp" %>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0); 
%>
<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("error.jsp");
	logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统错误提示</title>
<%@ include file="/common/meta.jsp" %>
</head>
<body>
<br />
<br />
<br />
<br />
<h2 align="center">操作失败！</h2>
<p align="center">
<%
out.println(ex.getMessage());
%>
</p>
<p align="center"><a href="javascript:history.back();">返回</a></p>
</body>
</html>
