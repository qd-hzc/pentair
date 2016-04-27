<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:text name="menu.product"/> - <s:text name="website.title"/></title>
<%@ include file="/common/meta.jsp" %>
</head>
<body topmargin="0" leftmargin="0" STYLE="margin:0;padding:0">
<jsp:include page="/common/header.jsp">
	<jsp:param name="menu" value="0" />
</jsp:include>
<br />
<table width="98%" align="center">
<tr><td><input type="button" class="BigButton" onClick="javascript:GB_showCenter('<s:text name='common.add'/>产品','${ctx}/catalog/product!input.action',250, 500);" value="<s:text name='common.add'/>产品" /></td></tr>
</table>
<form id="mainForm" action="product.action" method="get">
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}"/>
<table class="TableList"  width="98%" align="center">
  <tr class="TableHeader">
	  <td align="center" nowrap><a href="javascript:sort('code','asc')">编号</a></td>
      <td align="center" nowrap><a href="javascript:sort('name','asc')">名称</a></td>
      <td align="center" nowrap>品牌</td>
      <td align="center" nowrap><a href="javascript:sort('series.id','asc')">产品系列</a></td>
      <td align="center" nowrap><s:text name='common.action'/></td>
  </tr>
  <s:iterator value="page.result">
  <tr class="TableLine1">
	<td>${code}&nbsp;</td>
	<td>${name}&nbsp;</td>
	<td>${series.brand.name}&nbsp;</td>
	<td>${series.name}&nbsp;</td>
	<td align="center" nowrap>
    <a href="product!input.action?id=${id}" rel="gb_page_center[500, 250]" title="<s:text name='common.edit'/>产品"><s:text name='common.edit'/></a>
    <a href="javascript:if(confirm('确定要删除该产品吗？'))document.location.href='product!delete.action?id=${id}';" title="<s:text name='common.delete'/>产品"><s:text name='common.delete'/></a></td>
  </tr>
</s:iterator>
  <tr class="TableLine1">
    <td colspan="5" align="center" class="TableControl"><%@ include file="/common/pager.jsp" %></td>
    </tr>
</table>
</form>
<%@ include file="/common/footer.jsp" %>
</body>
</html>