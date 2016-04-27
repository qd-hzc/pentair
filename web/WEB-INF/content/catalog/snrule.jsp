<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:text name="menu.snrule"/> - <s:text name="website.title"/></title>
<%@ include file="/common/meta.jsp" %>
</head>
<body topmargin="0" leftmargin="0" STYLE="margin:0;padding:0">
<jsp:include page="/common/header.jsp">
	<jsp:param name="menu" value="0" />
</jsp:include>
<br />
<table width="98%" align="center">
<tr><td><input type="button" class="BigButton" onClick="javascript:GB_showCenter('<s:text name='common.add'/>编码规则','${ctx}/catalog/snrule!input.action',500, 900);" value="<s:text name='common.add'/>编码规则" /></td></tr>
</table>
<form id="mainForm" action="snrule.action" method="get">
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}"/>
<table class="TableList"  width="98%" align="center">
  <tr class="TableHeader">
	  <td align="center" nowrap><a href="javascript:sort('name','asc')">名称</a></td>
      <td align="center" nowrap><a href="javascript:sort('s1','asc')">分段1</a></td>
      <td align="center" nowrap><a href="javascript:sort('s2','asc')">分段2</a></td>
      <td align="center" nowrap><a href="javascript:sort('s3','asc')">分段3</a></td>
      <td align="center" nowrap><a href="javascript:sort('s4','asc')">分段4</a></td>
      <td align="center" nowrap><a href="javascript:sort('s5','asc')">分段5</a></td>
      <td align="center" nowrap><a href="javascript:sort('s6','asc')">分段6</a></td>
      <td align="center" nowrap><a href="javascript:sort('s7','asc')">分段7</a></td>
      <td align="center" nowrap><a href="javascript:sort('s8','asc')">分段8</a></td>
      <td align="center" nowrap><a href="javascript:sort('s9','asc')">分段9</a></td>
      <td align="center" nowrap><a href="javascript:sort('s10','asc')">分段10</a></td>
      <td align="center" nowrap><s:text name='common.action'/></td>
  </tr>
  <s:iterator value="page.result">
  <tr class="TableLine1">
	<td>${name}&nbsp;</td>
	<td>${s1}&nbsp;</td>
	<td>${s2}&nbsp;</td>
	<td>${s3}&nbsp;</td>
	<td>${s4}&nbsp;</td>
	<td>${s5}&nbsp;</td>
	<td>${s6}&nbsp;</td>
	<td>${s7}&nbsp;</td>
	<td>${s8}&nbsp;</td>
	<td>${s9}&nbsp;</td>
	<td>${s10}&nbsp;</td>
    <td align="center" nowrap>
    <a href="snrule!input.action?id=${id}" rel="gb_page_center[900, 500]" title="<s:text name='common.edit'/>编码规则"><s:text name='common.edit'/></a>
    <a href="javascript:if(confirm('确定要删除该编码规则吗？'))document.location.href='snrule!delete.action?id=${id}';" title="<s:text name='common.delete'/>编码规则"><s:text name='common.delete'/></a>&nbsp;</td>
  </tr>
</s:iterator>
  <tr class="TableLine1">
    <td colspan="12" align="center" class="TableControl"><%@ include file="/common/pager.jsp" %></td>
    </tr>
</table>
</form>
<%@ include file="/common/footer.jsp" %>
</body>
</html>