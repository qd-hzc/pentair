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
			am_id:"required",
			cm_id:"required",
			pm_id:"required",
			mm_id:"required",
			md_id:"required",
			gm_id:"required"
		},
		messages: {

		}
	});	
});
</script>
</head>
<body>
<form id="inputForm" action="${ctx}/catalog/brand!save.action" method="post">
<input type="hidden" name="id" value="${id}"/>
<input type="hidden" name="workingVersion" value="${version}"/>
<table align="center" width="99%" class="TableBlock">
  <tr>
      <td colspan="4" nowrap class="TableHeader"></td>
  </tr>
  <tr>
      <td width="10%" align="right" nowrap="nowrap" class="TableContent">编号</td>
      <td width="40%" align="left" class="TableData"><input type="text" class="BigInput" name="code" id="code" size="30" maxlength="50" value="${code}" /></td>
      <td width="10%" align="right" nowrap="nowrap" class="TableContent">名称</td>
      <td width="40%" align="left" class="TableData">
      <input type="text" class="BigInput" name="name" id="name" size="30" maxlength="50" value="${name}" /></td>
  </tr>
  <tr>
      <td nowrap="nowrap" class="TableContent" align="right">应用工程部经理</td>
      <td align="left" class="TableData"><s:select name="am_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
      <td nowrap="nowrap" class="TableContent" align="right">报价工程部经理</td>
      <td align="left" class="TableData"><s:select name="cm_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">客户服务部经理</td>
    <td align="left" class="TableData"><s:select name="csm_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
    <td align="right" nowrap="nowrap" class="TableContent">市场部经理</td>
    <td align="left" class="TableData"><s:select name="mm_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
    </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">销售总监</td>
    <td align="left" class="TableData"><s:select name="md_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
    <td align="right" nowrap="nowrap" class="TableContent">总经理</td>
    <td align="left" class="TableData"><s:select name="gm_id" headerKey="" headerValue="请选择" list="usersAll" listKey="id" listValue="name" /></td>
    </tr>
  <tr class="TableControl">
    <td nowrap colspan="4" align="center"><input type="submit" class="BigButton" value=" <s:text name='common.save'/> " /></td>
  </tr>
</table>
</form>
</body>
</html>