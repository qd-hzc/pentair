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

        var prefix = '${series.shortName}';

        function seriesChange(select) {
            var id = $(select).val();
            if (id) {
                var url = "${ctx}/catalog/series!input.action?id=" + id + "&time=" + new Date().getTime();
                $.get(url, null, function (responsexml) {
                    var root = $(responsexml.documentElement);
                    prefix = root.attr("shortName");
                }, "xml");
            }
        }
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/rfq/rfq!save.action" method="post">
    <input type="hidden" name="id" id="id" value="${id}"/>
    <input type="hidden" name="rfqStatus" id="rfqStatus" value=""/>
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="4" nowrap class="TableHeader">信息必须填写完整</td>
        </tr>
        <tr>
            <td width="15%" align="right" nowrap="nowrap" class="TableContent">RFQ编号</td>
            <td width="85%" align="left" class="TableData">${sn}</td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">客户名称</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="customerName" id="customerName"
                                                      size="30" maxlength="50" value="${customerName}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">产品系列</td>
            <td align="left" class="TableData"><s:select name="series_id" headerKey="" onchange='seriesChange(this);'
                                                         headerValue="请选择" list="seriesAll" listKey="id"
                                                         listValue="name"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">非标产品简述</td>
            <td align="left" class="TableData"><label>
                <textarea name="note" id="note" cols="70" rows="5">${note}</textarea>
            </label></td>
        </tr>
        <tr>
            <td colspan="4" nowrap="nowrap" class="TableControl"><strong>产品信息</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
                    type="button" class="BigButton"
                    onClick="javascript:location='rfq-detail!input.action?rfq_id=${id}&prefix=' + window.prefix;"
                    value="新增产品信息"/></td>
        </tr>
        <tr>
            <td colspan="4" align="right" class="TableData">
                <table width="100%" border="0">
                    <tr>
                        <th width="20%" align="center">产品编码</th>
                        <th align="center">产品名称</th>
                        <th width="20%" align="center">操作</th>
                    </tr>
                    <s:iterator value="rfqDetails">
                        <tr>
                            <td align="left">${productNo}&nbsp;</td>
                            <td align="left">${productName}&nbsp;</td>
                            <td align="center"><a
                                    href="rfq-detail!input.action?id=${id}&prefix=${series.shortName}"><s:text
                                    name='common.edit'/></a>
                                <a href="javascript:if(confirm('确定要删除该产品信息吗？'))document.location.href='rfq-detail!delete.action?id=${id}';"><s:text
                                        name='common.delete'/></a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </td>
        </tr>
        <tr>
            <td class="TableData" height="50" colspan="4" align="center" nowrap>
                <input type="submit" class="BigButton" value="保存"
                       onclick="javascript:document.getElementById('rfqStatus').value='csinput';"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
