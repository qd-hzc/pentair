<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:text name="menu.rfq"/> - <s:text name="website.title"/></title>
<%@ include file="/common/meta.jsp" %>
</head>
<body topmargin="0" leftmargin="0" STYLE="margin:0;padding:0">
<jsp:include page="/common/header.jsp">
	<jsp:param name="menu" value="0" />
</jsp:include>
<br />
<security:authorize ifAnyGranted="ROLE_SALES">
<table width="98%" align="center">
<tr><td>
<s:iterator value="brandsAll">
<input type="button" class="BigButton" onClick="javascript:GB_showCenter('<s:text name='common.add'/>RFQ','${ctx}/rfq/rfq!input.action?brand_id=${id}',500, 1000);" value="新建 | New RFQ - ${name}" />
</s:iterator>
</td>
</tr>
</table>
</security:authorize>
<security:authorize ifAnyGranted="ROLE_CS,ROLE_CSM">
<table width="98%" align="center">
<tr><td>
<s:iterator value="brandsAll">
<input type="button" class="BigButton" onClick="javascript:GB_showCenter('<s:text name='common.add'/>RFQ','${ctx}/rfq/rfq!input.action?brand_id=${id}',500, 1000);" value="新增货号 - ${name}" />
</s:iterator>
</td>
</tr>
</table>
</security:authorize>

<table width="98%" align="center">
<tr><td>

<security:authorize ifAnyGranted="ROLE_CSM">
<input type="button" class="BigButton" onClick="window.location.replace('${ctx}/rfq/excel.action');" value="Excel 导出产品编码"/>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_MM,ROLE_CM">
<input type="button" class="BigButton" onClick="window.location.replace('${ctx}/rfq/excelrfq.action');" value="Excel 导出 RFQ"/>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_CM,ROLE_AM">
<input type="button" class="BigButton" onClick="window.location.replace('${ctx}/rfq/excelrfqdetail.action');" value="Excel 导出 订单行"/>
</security:authorize>

</td></tr>
</table>

<form id="mainForm" action="rfq.action" method="get">
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}"/>
<table class="TableList"  width="98%" align="center">
  <tr class="TableLine1">
	  <td>编号 ：<input type="text" class="BigInput" name="filter_LIKES_sn" value="${param['filter_LIKES_sn']}" size="10"/>&nbsp;&nbsp;
      客户 ：<input type="text" class="BigInput" name="filter_LIKES_customerName" value="${param['filter_LIKES_customerName']}" size="10"/>&nbsp;&nbsp;
      品牌 ：<s:select style="width:100px;" name="filter_EQS_series.brand.id" headerKey="" headerValue="请选择 " list="brandsAll" listKey="id" listValue="name" />&nbsp;&nbsp;
      产品 ：<s:select style="width:100px;" name="filter_EQS_series.id" headerKey="" headerValue="请选择 " list="seriesAll" listKey="id" listValue="name" /><security:authorize ifNotGranted="ROLE_SALES">&nbsp;&nbsp;
      地区 ：<s:select name="filter_EQS_ownerSales.area.id" headerKey="" headerValue="请选择" list="areasAll" listKey="id" listValue="name" />&nbsp;&nbsp;
      销售 ：<s:select style="width:100px;" name="filter_EQS_ownerSales.id" headerKey="" headerValue="请选择 " list="salesUsersAll" listKey="id" listValue="name" /></security:authorize>&nbsp;&nbsp;
      状态 ：<s:select style="width:250px;" name="filter_EQS_status.id" headerKey="" headerValue="请选择 " list="rfqStatusAll" listKey="id" listValue="name" />
      </td>
      <td align="center" nowrap="nowrap"><input name="提交" type="submit" class="BigButton" value="查询 " /></td>
  </tr>
</table>
<br />
<table class="TableList"  width="98%" align="center">
  <tr class="TableHeader">
	  <td align="center" nowrap><a href="javascript:sort('sn','asc')" style="line-height:16px">编号</a></td>
      <td align="center" nowrap="nowrap"><a href="javascript:sort('customerName','asc')" style="line-height:16px">客户名称</a></td>
      <td align="center" nowrap="nowrap" style="line-height:16px">所属地区</td>
      <td align="center" nowrap style="line-height:16px">品牌</td>
      <td align="center" nowrap="nowrap" style="line-height:16px"><a href="javascript:sort('series.id','asc')">产品</a></td>
      <td align="center" nowrap style="line-height:16px">区域销售经理</td>
      <td align="center" nowrap style="line-height:16px">产品经理</td>
      <td align="center" nowrap><a href="javascript:sort('ownerSales.id','asc')" style="line-height:16px">销售工程师</a></td>
      <td align="center" nowrap><a href="javascript:sort('ownerAPP.id','asc')" style="line-height:16px">应用工程师</a></td>
      <td align="center" nowrap><a href="javascript:sort('ownerCE.id','asc')" style="line-height:16px">报价工程师</a></td>
      <td align="center" nowrap><a href="javascript:sort('ownerCS.id','asc')" style="line-height:16px">客户服务专员</a></td>
      <td align="center" nowrap><a href="javascript:sort('inputTime','asc')" style="line-height:16px">录入时间</a></td>
      <td align="center" nowrap><a href="javascript:sort('status','asc')" style="line-height:16px">状态</a></td>
      <td align="center" nowrap style="line-height:16px">操作</td>
  </tr>
  <s:iterator value="page.result">
  <tr class="TableLine1">
	<td>${sn}&nbsp;</td>
	<td>${customerName}&nbsp;</td>
	<td>${ownerSales.area.name}&nbsp;</td>
	<td>${series.brand.name}&nbsp;</td>
	<td>${series.name}&nbsp;</td>
	<td>${ownerSales.asm.name}&nbsp;</td>
	<td>${series.ownerPM.name}&nbsp;</td>
	<td>${ownerSales.name}&nbsp;</td>
	<td>${ownerAPP.name}&nbsp;</td>
	<td>${ownerCE.name}&nbsp;</td>
	<td>${ownerCS.name}&nbsp;</td>
	<td>${inputTime}&nbsp;</td>
	<td>${status.name}&nbsp;</td>
    <td align="center" nowrap style="line-height:16px">
    <c:choose>
	<c:when test="${fn:contains(editStatus, status.id)}">
    <a href="rfq!input.action?id=${id}" rel="gb_page_center[1100, 600]" title="<s:text name='common.edit'/>RFQ">编辑</a><br>
    
	</c:when>
	<c:otherwise>
    <a href="rfq!view.action?id=${id}" rel="gb_page_center[1100, 600]" title="<s:text name='common.view'/>RFQ">查看</a><br>
	</c:otherwise>
	</c:choose>
    <a href="rfq!viewLog.action?id=${id}" rel="gb_page_center[1000, 350]" title="跟踪RFQ工作流记录">跟踪</a>
    </td>
  </tr>
</s:iterator>
  <tr class="TableLine1">
    <td colspan="14" align="center" class="TableControl"><%@ include file="/common/pager.jsp" %></td>
    </tr>
</table>
</form>
<%@ include file="/common/footer.jsp" %>
</body>
</html>