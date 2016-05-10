<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
</head>
<body>
<form id="inputForm" action="${ctx}/rfq/rfq-detail!save.action" method="post">
    <s:if test="salesCanModify">
        <input type="hidden" name="id" id="id" value="${id}"/>
        <input type="hidden" name="sn" id="sn" value="${sn}"/>
        <input type="hidden" name="rfq_id" id="rfq_id" value="${rfq_id}"/>
        <input type="hidden" name="status" id="status" value="salesModifyBeforeDone"/>
    </s:if>
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="4" nowrap class="TableHeader">产品信息</td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">四班号</td>
            <td colspan="3" align="left" class="TableData">${sn}</td>
        </tr>
        <tr>
            <td width="15%" align="right" nowrap="nowrap" class="TableContent">产品编码</td>
            <td colspan="3" align="left" class="TableData">${productNo}</td>
        </tr>
        <tr>
            <td width="15%" align="right" nowrap="nowrap" class="TableContent">产品名称</td>
            <td colspan="3" align="left" class="TableData">${productName}</td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">竞争对手名称</td>
            <td width="35%" align="left" class="TableData">${competitorName}</td>
            <td width="15%" align="right" class="TableContent">竞争对手价格</td>
            <td align="left" class="TableData">${competitorPrice}</td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">目标价位</td>
            <td align="left" class="TableData">${expectedPrice}</td>
            <td align="right" class="TableContent">代理商预留利润</td>
            <td align="left" class="TableData">${agentProfit}</td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">客户要求交货周期</td>
            <td align="left" class="TableData">${deliveryCycle}</td>
            <td align="right" class="TableContent">销售机会</td>
            <td align="left" class="TableData">${opportunity}</td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">年需求量(台/套)</td>
            <s:if test="salesCanModify">
                <td align="left" class="TableData"><input name="quantityYear" type="text" class="BigInput"
                                                          id="quantityYear" value="${quantityYear}" size="30"
                                                          maxlength="50"/></td>
            </s:if>
            <s:if test="!salesCanModify">
                <td align="left" class="TableData">${quantityYear}</td>
            </s:if>
            <td align="right" class="TableContent">每单最小批量(台/套)</td>
            <s:if test="salesCanModify">
                <td align="left" class="TableData"><input name="quantityMin" type="text" class="BigInput"
                                                          id="quantityMin" value="${quantityMin}" size="30"
                                                          maxlength="50"/></td>
            </s:if>
            <s:if test="!salesCanModify">
                <td align="left" class="TableData">${quantityMin}</td>
            </s:if>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">年销售额(万元)</td>
            <s:if test="salesCanModify">
                <td align="left" class="TableData" colspan=3><input name="saleAmountYear" type="text" class="BigInput"
                                                                    id="saleAmountYear" value="${saleAmountYear}"
                                                                    size="30" maxlength="50"/></td>
            </s:if>
            <s:if test="!salesCanModify">
                <td align="left" class="TableData" colspan=3>${saleAmountYear}</td>
            </s:if>
        </tr>

        <security:authorize
                ifAnyGranted="ROLE_PM,ROLE_CM,ROLE_CE,ROLE_DE,ROLE_DM,ROLE_MM,ROLE_MD,ROLE_GM,ROLE_FD,ROLE_CS,ROLE_CC">
            <tr>
                <td nowrap="nowrap" class="TableContent" align="right">材料费</td>
                <td align="left" class="TableData">${materialCost}</td>
                <td align="right" class="TableContent">工时费</td>
                <td align="left" class="TableData">${loh}</td>
            </tr>
        </security:authorize>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">工装模具费</td>
            <td align="left" class="TableData">${toolingCharge}</td>
            <td align="right" class="TableContent">批量</td>
            <td align="left" class="TableData">${batch}</td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">交货周期<br>(weeks)</td>
            <td align="left" class="TableData">${deliveryWeeks}</td>
            <td align="right" class="TableContent"></td>
            <td align="left" class="TableData"></td>
        </tr>

        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">备注</td>
            <td colspan="3" align="left" class="TableData">${note}</td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">技术说明</td>
            <td colspan="3" align="left" class="TableData"><s:iterator value="fileUploadsTech"><a
                    href="${ctx}/upload/file-upload.action?id=${id}">${fileSaveName}</a>&nbsp;&nbsp;</s:iterator></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">配置清单</td>
            <td colspan="3" align="left" class="TableData"><s:iterator value="fileUploadsList"><a
                    href="${ctx}/upload/file-upload.action?id=${id}">${fileSaveName}</a>&nbsp;&nbsp;</s:iterator></td>
        </tr>
        <tr>
            <td class="TableData" height="50" colspan="4" align="center" nowrap>
                <s:if test="salesCanModify">
                    <input type="submit" class="BigButton" value=" 保 存 "/>
                </s:if>
                <input name="按钮" type="button" class="BigButton" onclick="javascript:history.back();" value=" 返 回 "/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>