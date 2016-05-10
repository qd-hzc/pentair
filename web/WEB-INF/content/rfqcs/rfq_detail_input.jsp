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
                    productNo: "required",
                    productName: "required"
                },
                messages: {}
            });
        });

        function openProductNoSelect() {
            var div = $("<div id=productNoSelectDiv style='position:absolute;background-color:grey;top:0px;left:0px;width:1000px;height:1000px;'></div>");
            $(document.body).append(div);
            var iframe = $("<iframe width=100% height=100% src='${ctx}/rfq/productno.action?prefix=<%=request.getParameter("prefix")%>'></iframe>");
            div.append(iframe);
        }

        function closeProductNoSelect() {
            $("#productNoSelectDiv").remove();
        }
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/rfq/rfq-detail!save.action" method="post">
    <input type="hidden" name="id" id="id" value="${id}"/>
    <input type="hidden" name="sn" id="sn" value="${sn}"/>
    <input type="hidden" name="rfq_id" id="rfq_id" value="${rfq_id}"/>
    <input type="hidden" name="status" id="status" value=""/>
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="4" nowrap class="TableHeader">信息必须填写完整</td>
        </tr>
        <tr>
            <td width="15%" align="right" nowrap="nowrap" class="TableContent">产品编码</td>
            <td colspan="3" align="left" class="TableData">
                <input type="text" class="BigInput" name="productNo" id="productNo" size="30" maxlength="20"
                       value="${productNo}" readonly/>
                <input type="hidden" name="snruleId" id="snruleId" value="${snruleId}"/>
                <input type="button" class="BigButton" value=" 选择 " onclick="openProductNoSelect();">
            </td>
        </tr>
        <tr>
            <td width="15%" align="right" nowrap="nowrap" class="TableContent">产品名称</td>
            <td colspan="3" align="left" class="TableData"><input type="text" class="BigInput" name="productName"
                                                                  id="productName" size="30" maxlength="50"
                                                                  value="${productName}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">备注</td>
            <td colspan="3" align="left" class="TableData"><label>
                <textarea name="note" id="note" cols="70" rows="5">${note}</textarea>
            </label></td>
        </tr>
        <tr>
            <td class="TableData" height="50" colspan="4" align="center" nowrap>
                <input name="提交" type="submit" class="BigButton" value=" 保 存 "/>
                <input name="提交" type="button" class="BigButton" value=" 取消 " onclick="window.history.back();"/></td>
        </tr>
    </table>
</form>
</body>
</html>