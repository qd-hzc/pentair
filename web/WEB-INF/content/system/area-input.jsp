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
                    name: "required"
                },
                messages: {}
            });
        });
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/system/area!save.action" method="post">
    <input type="hidden" name="workingVersion" value="${version}"/>
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="2" nowrap class="TableHeader"></td>
        </tr>
        <tr>
            <td width="10%" align="right" nowrap="nowrap" class="TableContent">ID</td>
            <td width="40%" align="left" class="TableData"><input type="text" class="BigInput" name="id" id="id"
                                                                  size="20" maxlength="50" value="${id}"/></td>
        </tr>
        <tr>
            <td width="10%" align="right" nowrap="nowrap" class="TableContent">地区名称</td>
            <td width="40%" align="left" class="TableData"><input type="text" class="BigInput" name="name" id="name"
                                                                  size="20" maxlength="50" value="${name}"/></td>
        </tr>
        <tr class="TableControl">
            <td nowrap colspan="2" align="center"><input type="submit" class="BigButton"
                                                         value=" <s:text name='common.save'/> "/></td>
        </tr>
    </table>
</form>
</body>
</html>