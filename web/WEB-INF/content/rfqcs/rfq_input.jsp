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
                    customerName: "required",
                    series_id: "required"
                },
                messages: {}
            });
        });
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/rfq/rfq!save.action" method="post">
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="4" nowrap class="TableHeader">信息必须填写完整</td>
        </tr>
        <tr>
            <td width="15%" align="right" nowrap="nowrap" class="TableContent">RFQ编号</td>
            <td width="85%" align="left" class="TableData">系统自动生成</td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">客户名称</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="customerName" id="customerName"
                                                      size="30" maxlength="50" value="${customerName}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">产品系列</td>
            <td align="left" class="TableData"><s:select name="series_id" headerKey="" headerValue="请选择"
                                                         list="seriesAll" listKey="id" listValue="name"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">非标产品简述</td>
            <td align="left" class="TableData"><label>
                <textarea name="note" id="note" cols="70" rows="10">${note}</textarea>
            </label></td>
        </tr>
        <tr>
            <td class="TableData" height="50" colspan="4" align="center" nowrap>
                <input type="submit" class="BigButton" value="下一步：填写产品信息"/></td>
        </tr>
    </table>
</form>
</body>
</html>