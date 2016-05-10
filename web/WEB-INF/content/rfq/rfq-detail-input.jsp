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
                    productName: "required",
                    quantityYear: "required",
                    saleAmountYear: "required"
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
    <input type="hidden" name="specialPrice" id="specialPrice" value=""/>
    <input type="hidden" name="specialApprover" id="specialApprover" value=""/>
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="4" nowrap class="TableHeader">信息必须填写完整，该信息将作为报价的依据</td>
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
            <td align="right" nowrap="nowrap" class="TableContent">竞争对手名称</td>
            <td width="35%" align="left" class="TableData"><input name="competitorName" type="text" class="BigInput"
                                                                  id="competitorName" value="${competitorName}"
                                                                  size="30" maxlength="50"/></td>
            <td width="15%" align="right" class="TableContent">竞争对手价格</td>
            <td align="left" class="TableData"><input name="competitorPrice" type="text" class="BigInput"
                                                      id="competitorPrice" value="${competitorPrice}" size="30"
                                                      maxlength="50"/></td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">目标价位</td>
            <td align="left" class="TableData"><input name="expectedPrice" type="text" class="BigInput"
                                                      id="expectedPrice" value="${expectedPrice}" size="30"
                                                      maxlength="50"/></td>
            <td align="right" class="TableContent">代理商预留利润</td>
            <td align="left" class="TableData"><input name="agentProfit" type="text" class="BigInput" id="agentProfit"
                                                      value="${agentProfit}" size="30" maxlength="50"/></td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">客户要求交货周期</td>
            <td align="left" class="TableData"><input name="deliveryCycle" type="text" class="BigInput"
                                                      id="deliveryCycle" value="${deliveryCycle}" size="30"
                                                      maxlength="50"/></td>
            <td align="right" class="TableContent">销售机会</td>
            <td align="left" class="TableData"><input name="opportunity" type="text" class="BigInput" id="opportunity"
                                                      value="${opportunity}" size="30" maxlength="50"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">年需求量(台/套)</td>
            <td align="left" class="TableData"><input name="quantityYear" type="text" class="BigInput" id="quantityYear"
                                                      value="${quantityYear}" size="30" maxlength="50"/></td>
            <td align="right" class="TableContent">每单最小批量(台/套)</td>
            <td align="left" class="TableData"><input name="quantityMin" type="text" class="BigInput" id="quantityMin"
                                                      value="${quantityMin}" size="30" maxlength="50"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">年销售额(万元)</td>
            <td align="left" class="TableData" colspan=3><input name="saleAmountYear" type="text" class="BigInput"
                                                                id="saleAmountYear" value="${saleAmountYear}" size="30"
                                                                maxlength="50"/></td>
        </tr>
        <security:authorize ifAnyGranted="ROLE_CM,ROLE_CE,ROLE_ADMIN">
            <tr>
                <td nowrap="nowrap" class="TableContent" align="right">材料费</td>
                <td align="left" class="TableData"><input name="materialCost" type="text" class="BigInput"
                                                          id="materialCost" value="${materialCost}" size="30"
                                                          maxlength="50"/></td>
                <td align="right" class="TableContent">工时费</td>
                <td align="left" class="TableData"><input name="loh" type="text" class="BigInput" id="loh"
                                                          value="${loh}" size="30" maxlength="50"/></td>
            </tr>
        </security:authorize>
        <security:authorize ifAnyGranted="ROLE_CM,ROLE_CE,ROLE_ADMIN">
            <tr>
                <td nowrap="nowrap" class="TableContent" align="right">工装模具费</td>
                <td align="left" class="TableData"><input name="toolingCharge" type="text" class="BigInput"
                                                          id="toolingCharge" value="${toolingCharge}" size="30"
                                                          maxlength="50"/></td>
                <td align="right" class="TableContent">批量</td>
                <td align="left" class="TableData"><input name="batch" type="text" class="BigInput" id="batch"
                                                          value="${batch}" size="30" maxlength="50"/></td>
            </tr>
            <tr>
                <td nowrap="nowrap" class="TableContent" align="right">交货周期<br>(weeks)</td>
                <td align="left" class="TableData"><input name="deliveryWeeks" type="text" class="BigInput"
                                                          id="deliveryWeeks" value="${deliveryWeeks}" size="30"
                                                          maxlength="45"/></td>
                <td align="right" class="TableContent"></td>
                <td align="left" class="TableData"></td>
            </tr>
        </security:authorize>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">备注</td>
            <td colspan="3" align="left" class="TableData"><label>
                <textarea name="note" id="note" cols="70" rows="5">${note}</textarea>
            </label></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">技术说明</td>
            <td colspan="3" align="left" class="TableData">
                <iframe id="upload1"
                        src="${ctx}/upload/file-upload!init.action?dir1=${rfq_sn}&dir2=${sn.replaceAll('&', '_')}&dir3=tech"
                        frameborder="0" scrolling="no" width="600" height="50"></iframe>
            </td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">配置清单</td>
            <td colspan="3" align="left" class="TableData">
                <iframe id="upload2"
                        src="${ctx}/upload/file-upload!init.action?dir1=${rfq_sn}&dir2=${sn.replaceAll('&', '_')}&dir3=list"
                        frameborder="0" scrolling="no" width="600" height="50"></iframe>
            </td>
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
<script type="text/javascript">
    window.setInterval("reinitIframe('upload1')", 200);
    window.setInterval("reinitIframe('upload2')", 200);
</script>