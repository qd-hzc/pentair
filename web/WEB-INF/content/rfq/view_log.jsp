<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
</head>
<body>
<form id="mainForm" action="" method="get">
<table class="TableList"  width="98%" align="center">
  <tr class="TableHeader">
	  <td align="center" nowrap>用户</td>
      <td align="center" nowrap="nowrap">用户角色</td>
      <td align="center" nowrap="nowrap">RFQ状态</td>
      <td align="center" nowrap>时间</td>
    </tr>
  <s:iterator value="rfqLogs">
  <tr class="TableLine1">
	<td>${fromUser}&nbsp;</td>
	<td>${fromRole}&nbsp;</td>
	<td>${info}&nbsp;</td>
	<td align="center">${theDate}&nbsp;</td>
	</tr>
</s:iterator>  
</table>
</form>
<%@ include file="/common/footer.jsp" %>
</body>
</html>