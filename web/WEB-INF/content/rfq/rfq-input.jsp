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
			customerName:"required",
			series_id:"required"
		},
		messages: {

		}
	});	
});
</script>
</head>
<body>
<form id="inputForm" action="${ctx}/rfq/rfq!save.action" method="post">
<table align="center" width="99%" class="TableBlock">
  <tr>
      <td colspan="4" nowrap class="TableHeader">信息必须填写完整，该信息将作为报价的依据</td>
  </tr>
  <tr>
      <td width="15%" align="right" nowrap="nowrap" class="TableContent">RFQ编号</td>
      <td width="35%" align="left" class="TableData">系统自动生成</td>
      <td width="15%" align="right" nowrap="nowrap" class="TableContent">需要完成时间</td>
      <td align="left" class="TableData"><input type="text" class="BigInput" name="tofinishDate" id="tofinishDate" size="30" maxlength="50" value="${tofinishDate}" readonly="readonly" /></td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">客户名称</td>
    <td colspan="3" align="left" class="TableData"><input type="text" class="BigInput" name="customerName" id="customerName" size="30" maxlength="50" value="${customerName}" /></td>
    </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">商务联系人</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="customerLinkman1" id="customerLinkman1" size="30" maxlength="50" value="${customerLinkman1}" /></td>
    <td align="right" class="TableData">商务联系人电话</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="customerPhone1" id="customerPhone1" size="30" maxlength="50" value="${customerPhone1}" /></td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">技术部联系人</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="customerLinkman2" id="customerLinkman2" size="30" maxlength="50" value="${customerLinkman2}" /></td>
    <td align="right" class="TableData">技术部联系人电话</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="customerPhone2" id="customerPhone2" size="30" maxlength="50" value="${customerPhone2}" /></td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">代理商名称</td>
    <td colspan="3" align="left" class="TableData"><input type="text" class="BigInput" name="agentName" id="agentName" size="30" maxlength="50" value="${agentName}" /></td>
    </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">代理商联系人</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="agentLinkman" id="agentLinkman" size="30" maxlength="50" value="${agentLinkman}" /></td>
    <td align="right" class="TableData">代理商联系电话</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="agentPhone" id="agentPhone" size="30" maxlength="50" value="${agentPhone}" /></td>
  </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">产品系列</td>
    <td align="left" class="TableData"><s:select name="series_id" headerKey="" headerValue="请选择" list="seriesAll" listKey="id" listValue="name" /></td>
    <td align="right" class="TableData">所属行业</td>
    <td align="left" class="TableData"><input type="text" class="BigInput" name="trade" id="trade" size="30" maxlength="50" value="${trade}" /></td>
  </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">非标产品简述</td>
    <td colspan="3" align="left" class="TableData"><label>
      <textarea name="note" id="note" cols="70" rows="10">${note}</textarea>
    </label></td>
    </tr>
  <tr>
    <td class="TableData" height="50" colspan="4" align="center" nowrap>
    <input type="submit" class="BigButton" value="下一步：填写产品信息" /></td>
  </tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
Calendar.setup({
	inputField     :    "tofinishDate",   // id of the input field
	ifFormat       :    "%Y-%m-%d",       // format of the input field
	showsTime      :    false
});
</script>
