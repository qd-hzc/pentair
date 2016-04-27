<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<script language="JavaScript">
$(document).ready(function() {
	$("#inputForm").validate({//为inputForm注册validate函数
		rules: {
			code: "required",
			name:"required",
			shortName:"required",
			brand_id:"required"
		},
		messages: {

		}
	});	
});
</script>
</head>
<body>
<form id="inputForm" action="${ctx}/catalog/series!save.action" method="post">
<input type="hidden" name="id" value="${id}"/>
<input type="hidden" name="workingVersion" value="${version}"/>
<table align="center" width="99%" class="TableBlock">
  <tr>
      <td colspan="2" nowrap class="TableHeader"></td>
  </tr>
  <tr>
      <td width="30%" align="right" nowrap="nowrap" class="TableContent">四班号段</td>
      <td align="left" class="TableData"><input type="text" class="BigInput" name="code" id="code" size="30" maxlength="50" value="${code}" /></td>
  </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">所属品牌</td>
    <td align="left" class="TableData"><s:select name="brand_id" headerKey="" headerValue="请选择" list="brandsAll" listKey="id" listValue="name" /></td>
  </tr>
  <tr>
      <td nowrap="nowrap" class="TableContent" align="right">产品系列名称</td>
      <td align="left" class="TableData"><input type="text" class="BigInput" name="name" id="name" size="30" maxlength="50" value="${name}" /></td>
  </tr>
  <tr>
  	<td nowrap="nowrap" class="TableContent" align="right">产品名称缩写</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="shortName" id="shortName" size="30" maxlength="3" value="${shortName}" onchange='this.value=this.value.toUpperCase();'/></td>
  </tr>
  <tr>
  	<td nowrap="nowrap" class="TableContent" align="right">目标标准利润(%)</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="targetProfit" id="targetProfit" size="5" maxlength="4" value="${targetProfit}" /></td>
  </tr>
  <tr>
  	<td nowrap="nowrap" class="TableContent" align="right">标准折扣率(%)</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="discountRate" id="discountRate" size="5" maxlength="4" value="${discountRate}" /></td>
  </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">产品经理</td>
    <td align="left" class="TableData"><s:select name="pm_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
  </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">设计工程部经理</td>
    <td align="left" class="TableData"><s:select name="dm_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
  </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">报价工程师</td>
    <td align="left" class="TableData"><s:select name="ce_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
  </tr>
  <tr class="TableControl">
    <td colspan="2" align="center" nowrap><input type="submit" class="BigButton" value=" <s:text name='common.save'/> " /></td>
    </tr>
</table>
</form>
</body>
</html>