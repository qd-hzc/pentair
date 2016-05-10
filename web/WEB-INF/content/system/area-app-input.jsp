<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <script language="JavaScript">
        $(document).ready(function () {
            $("#inputForm").validate({//为inputForm注册validate函数
                rules: {
                    area_id: "required",
                    brand_id: "required",
                    app_id: "required",
                    cs_id: "required"
                },
                messages: {}
            });
        });
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/system/area-app!save.action" method="post">
    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="workingVersion" value="${version}"/>
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="2" nowrap class="TableHeader"></td>
        </tr>
        <tr>
            <td width="10%" align="right" nowrap="nowrap" class="TableContent">地区</td>
            <td width="40%" align="left" class="TableData"><s:select name="area_id" headerKey="" headerValue="请选择"
                                                                     list="allAreaList" listKey="id"
                                                                     listValue="name"/></td>
        </tr>
        <tr>
            <td width="10%" align="right" nowrap="nowrap" class="TableContent">品牌</td>
            <td width="40%" align="left" class="TableData"><s:select name="brand_id" headerKey="" headerValue="请选择"
                                                                     list="allBrandList" listKey="id"
                                                                     listValue="name"/></td>
        </tr>
        <tr>
            <td width="10%" align="right" nowrap="nowrap" class="TableContent">应用工程师</td>
            <td width="40%" align="left" class="TableData"><s:select name="app_id" headerKey="" headerValue="请选择"
                                                                     list="allUserList" listKey="id"
                                                                     listValue="name"/></td>
        </tr>
        <tr>
            <td width="10%" align="right" nowrap="nowrap" class="TableContent">客户服务专员</td>
            <td width="40%" align="left" class="TableData"><s:select name="cs_id" headerKey="" headerValue="请选择"
                                                                     list="allUserList" listKey="id"
                                                                     listValue="name"/></td>
        </tr>
        <tr class="TableControl">
            <td nowrap colspan="2" align="center"><input type="submit" class="BigButton"
                                                         value=" <s:text name='common.save'/> "/></td>
        </tr>
    </table>
</form>
</body>
</html>