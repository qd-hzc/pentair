<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><s:text name="menu.user"/> - <s:text name="website.title"/></title>
    <%@ include file="/common/meta.jsp" %>
</head>
<body topmargin="0" leftmargin="0" STYLE="margin:0;padding:0">
<jsp:include page="/common/header.jsp">
    <jsp:param name="menu" value="0"/>
</jsp:include>
<br/>
<table width="98%" align="center">
    <tr>
        <td><input type="button" class="BigButton"
                   onclick="javascript:GB_showCenter('新增地区品牌责任人','${ctx}/system/area-app!input.action',300, 800);"
                   value="新增地区品牌责任人"/></td>
    </tr>
</table>
<table class="TableList" width="98%" align="center">
    <tr class="TableHeader">
        <td align="center" nowrap>地区名称</td>
        <td align="center" nowrap>品牌</td>
        <td align="center" nowrap>应用工程师</td>
        <td align="center" nowrap>客户服务专员</td>
        <td align="center" nowrap><s:text name='common.action'/></td>
    </tr>
    <s:iterator value="allAreaAppList">
        <tr class="TableLine1">
            <td>${area.name}&nbsp;</td>
            <td>${brand.name}&nbsp;</td>
            <td>${ownerApp.name}&nbsp;</td>
            <td>${ownerCs.name}&nbsp;</td>
            <td align="center" nowrap>&nbsp;
                <a href="area-app!input.action?id=${id}" rel="gb_page_center[500, 300]" title="编辑地区">编辑</a>
                <a href="javascript:if(confirm('确认删除此责任人么？'))document.location.href='area-app!delete.action?id=${id}';"
                   title="删除地区"><s:text name='common.delete'/></a></td>
        </tr>
    </s:iterator>
</table>
<%@ include file="/common/footer.jsp" %>
</body>
</html>