<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><s:text name="menu.series"/> - <s:text name="website.title"/></title>
    <%@ include file="/common/meta.jsp" %>
</head>
<body topmargin="0" leftmargin="0" STYLE="margin:0;padding:0">
<jsp:include page="/common/header.jsp">
    <jsp:param name="menu" value="0"/>
</jsp:include>
<br/>
<table width="98%" align="center">
    <tr>
        <td><input type="button" class="BigButton" onClick="javascript:GB_showCenter('<s:text
                name='common.add'/>产品系列','${ctx}/catalog/series!input.action',250, 500);"
                   value="<s:text name='common.add'/>产品系列"/></td>
    </tr>
</table>
<form id="mainForm" action="series.action" method="get">
    <input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
    <input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
    <input type="hidden" name="page.order" id="order" value="${page.order}"/>
    <table class="TableList" width="98%" align="center">
        <tr class="TableHeader">
            <td align="center" nowrap><a href="javascript:sort('code','asc')">四班号段</a></td>
            <td align="center" nowrap><a href="javascript:sort('brand.id','asc')">所属品牌</a></td>
            <td align="center" nowrap><a href="javascript:sort('name','asc')">产品系列名称</a></td>
            <td align="center" nowrap><a href="javascript:sort('targetProfit','asc')">目标标准利润(%)</a></td>
            <td align="center" nowrap><a href="javascript:sort('discountRate','asc')">标准折扣率(%)</a></td>
            <td align="center" nowrap><a href="javascript:sort('ownerPM.id','asc')">产品经理</a></td>
            <td align="center" nowrap><a href="javascript:sort('ownerDM.id','asc')">设计工程部经理</a></td>
            <td align="center" nowrap><a href="javascript:sort('ownerCE.id','asc')">报价工程师</a></td>
            <td align="center" nowrap><s:text name='common.action'/></td>
        </tr>
        <s:iterator value="page.result">
            <tr class="TableLine1">
                <td>${code}&nbsp;</td>
                <td>${brand.name}&nbsp;</td>
                <td>${name}&nbsp;</td>
                <td>${targetProfit}&nbsp;</td>
                <td>${discountRate}&nbsp;</td>
                <td>${ownerPM.name}&nbsp;</td>
                <td>${ownerDM.name}&nbsp;</td>
                <td>${ownerCE.name}&nbsp;</td>
                <td align="center" nowrap>
                    <a href="series!input.action?id=${id}" rel="gb_page_center[550, 350]"
                       title="<s:text name='common.edit'/>产品系列"><s:text name='common.edit'/></a>
                    <a href="javascript:if(confirm('确定要删除该产品系列吗？'))document.location.href='series!delete.action?id=${id}';"
                       title="<s:text name='common.delete'/>产品系列"><s:text name='common.delete'/></a>&nbsp;</td>
            </tr>
        </s:iterator>
        <tr class="TableLine1">
            <td colspan="9" align="center" class="TableControl">
                <%@ include file="/common/pager.jsp" %>
            </td>
        </tr>
    </table>
</form>
<%@ include file="/common/footer.jsp" %>
</body>
</html>