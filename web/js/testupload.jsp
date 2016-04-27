<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>测试附件上传</title>
</head>
<body>
<br />
<br />
<br />
<br />
<%if(request.getAttribute("id")!=null){%>
<a href="/testupload/file-upload.action?id=<%=request.getAttribute("id")%>">文件上传成功，点击下载 <%=request.getAttribute("uploadFileName")%></a>
<br />
<br />
<%}%>
<form name="uploadForm" action="/testupload/file-upload!upload.action" method="post" enctype="multipart/form-data">
    <input type="file" size="50" name="upload" id="upload" />
	<input type="submit" value="上传" />
</form>
</body>
</html>
