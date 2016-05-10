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
            var iframe = $("<iframe width=100% height=100% src='${ctx}/rfq/productno.action?prefix=${series.shortName}'></iframe>");
            div.append(iframe);
        }

        function closeProductNoSelect() {
            $("#productNoSelectDiv").remove();
        }
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/rfq/rfq-detail!save.action" method="post">
    <input type="hidden" name="sn" id="sn" value="${rfqDetail_sn}"/>
    <input type="hidden" name="rfq_id" id="rfq_id" value="${rfq_id}"/>
    <input type="hidden" name="rfq_sn" id="rfq_sn" value="${rfq_sn}"/>
    <input type="hidden" name="status" id="status" value="again"/>
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
                                                                  id="productName" size="30" maxlength="50"/></td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">竞争对手名称</td>
            <td width="35%" align="left" class="TableData"><input type="text" class="BigInput" name="competitorName"
                                                                  id="competitorName" size="30" maxlength="50"/></td>
            <td width="15%" align="right" class="TableContent">竞争对手价格</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="competitorPrice"
                                                      id="competitorPrice" size="30" maxlength="50"/></td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">目标价位</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="expectedPrice"
                                                      id="expectedPrice" size="30" maxlength="50"/></td>
            <td align="right" class="TableContent">代理商预留利润</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="agentProfit" id="agentProfit"
                                                      size="30" maxlength="50"/></td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">客户要求交货周期</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="deliveryCycle"
                                                      id="deliveryCycle" size="30" maxlength="50"/></td>
            <td align="right" class="TableContent">销售机会</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="opportunity" id="opportunity"
                                                      size="30" maxlength="50"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">年需求量(台/套)</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="quantityYear" id="quantityYear"
                                                      size="30" maxlength="50"/></td>
            <td align="right" class="TableContent">每单最小批量(台/套)</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="quantityMin" id="quantityMin"
                                                      size="30" maxlength="50"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">年销售额(万元)</td>
            <td align="left" class="TableData" colspan=3><input name="saleAmountYear" type="text" class="BigInput"
                                                                id="saleAmountYear" value="${saleAmountYear}" size="30"
                                                                maxlength="50"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">备注</td>
            <td colspan="3" align="left" class="TableData"><label>
                <textarea name="note" id="note" cols="70" rows="5"></textarea>
            </label></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">技术说明</td>
            <td colspan="3" align="left" class="TableData">
                <iframe id="upload1"
                        src="${ctx}/upload/file-upload!init.action?dir1=${rfq_sn}&dir2=${rfqDetail_sn.replaceAll('&', '_')}&dir3=tech"
                        frameborder="0" scrolling="no" width="600" height="50"></iframe>
            </td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">配置清单</td>
            <td colspan="3" align="left" class="TableData">
                <iframe id="upload2"
                        src="${ctx}/upload/file-upload!init.action?dir1=${rfq_sn}&dir2=${rfqDetail_sn.replaceAll('&', '_')}&dir3=list"
                        frameborder="0" scrolling="no" width="600" height="50"></iframe>
                </iframe></td>
        </tr>
        <tr>
            <td class="TableData" height="50" colspan="4" align="center" nowrap>
                <input name="提交" type="submit" class="BigButton" value="保存，填写其它产品信息"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" class="BigButton" value="保存，提交至应用工程部"
                       onclick="javascript:document.getElementById('status').value='sales2am';"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" class="BigButton" value="保存，暂不提交"
                       onclick="javascript:document.getElementById('status').value='input';"/></td>
        </tr>
    </table>
</form>
</body>
</html>
<script type="text/javascript">
    window.setInterval("reinitIframe('upload1')", 200);
    window.setInterval("reinitIframe('upload2')", 200);
</script>