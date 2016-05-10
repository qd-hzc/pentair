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
        <td><input type="button" class="BigButton" onclick="javascript:GB_showCenter('
        <s:text name='common.add'/>
        <s:text name='user.user'/>','${ctx}/system/user!input.action',330, 800);"
                   value="<s:text name='common.add'/><s:text name='user.user'/>"/></td>
    </tr>
</table>
<table class="TableList" width="98%" align="center">
    <tr class="TableHeader">
        <td align="center" nowrap><s:text name='user.loginName'/></td>
        <td align="center" nowrap><s:text name='user.name'/></td>
        <td align="center" nowrap><s:text name='user.englishName'/></td>
        <td align="center" nowrap><s:text name='user.shortName'/></td>
        <td align="center" nowrap><s:text name='user.email'/></td>
        <td align="center" nowrap>所属地区</td>
        <td align="center" nowrap>区域销售经理</td>
        <td align="center" nowrap><s:text name='user.role'/></td>
        <td align="center" nowrap><s:text name='user.status'/></td>
        <td align="center" nowrap><s:text name='common.action'/></td>
    </tr>
    <s:iterator value="allUserList">
        <tr class="TableLine1">
            <td>${loginName}&nbsp;</td>
            <td>${name}&nbsp;</td>
            <td>${englishName}&nbsp;</td>
            <td>${shortName}&nbsp;</td>
            <td><a href="mailto:${email}">${email}</a>&nbsp;</td>
            <td>${area.name}&nbsp;</td>
            <td>${asm.name}&nbsp;</td>
            <td>${roleNames}&nbsp;</td>
            <td>${status}&nbsp;</td>
            <td align="center" nowrap>&nbsp;
                <a href="user!input.action?id=${id}" rel="gb_page_center[800, 330]"
                   title="<s:text name='common.edit'/><s:text name='user.user'/>"><s:text name='common.edit'/></a>
                <a href="javascript:if(confirm('<s:text name='user.confirm_delete'/>'))document.location.href='user!delete.action?id=${id}';"
                   title="<s:text name='common.delete'/><s:text name='user.user'/>"><s:text name='common.delete'/></a>
            </td>
        </tr>
    </s:iterator>
</table>
<%@ include file="/common/footer.jsp" %>
</body>
</html>