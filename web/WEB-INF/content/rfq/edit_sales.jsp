<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<script language="JavaScript">
$(document).ready(function() {
	$("#inputForm").validate({//为inputForm注册validate函数
		rules: {
		},
		messages: {

		}
	});	
});
</script>
</head>
<body>
<form id="inputForm" action="${ctx}/rfq/rfq!save.action" method="post">
<input type="hidden" name="id" id="id" value="${id}"/>
<input type="hidden" name="rfqStatus" id="rfqStatus" value=""/>
<table align="center" width="99%" class="TableBlock">
  <tr>
      <td colspan="4" nowrap class="TableHeader">信息必须填写完整，该信息将作为报价的依据</td>
  </tr>
  <tr>
      <td width="15%" align="right" nowrap="nowrap" class="TableContent">RFQ编号</td>
      <td width="35%" align="left" class="TableData">${sn}</td>
      <td width="15%" align="right" nowrap="nowrap" class="TableContent">需要完成时间</td>
      <td align="left" class="TableData">${tofinishDate}</td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">客户名称</td>
    <td colspan="3" align="left" class="TableData">${customerName}</td>
    </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">商务联系人</td>
    <td align="left" class="TableData">${customerLinkman1}</td>
    <td align="right" class="TableData">商务联系人电话</td>
    <td align="left" class="TableData">${customerPhone1}</td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">技术部联系人</td>
    <td align="left" class="TableData">${customerLinkman2}</td>
    <td align="right" class="TableData">技术部联系人电话</td>
    <td align="left" class="TableData">${customerPhone2}</td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">代理商名称</td>
    <td colspan="3" align="left" class="TableData">${agentName}</td>
    </tr>
  <tr>
    <td align="right" nowrap="nowrap" class="TableContent">代理商联系人</td>
    <td align="left" class="TableData">${agentLinkman}</td>
    <td align="right" class="TableData">代理商联系电话</td>
    <td align="left" class="TableData">${agentPhone}</td>
  </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">产品系列</td>
    <td align="left" class="TableData">${series.name}</td>
    <td align="right" class="TableData">所属行业</td>
    <td align="left" class="TableData">${trade}</td>
  </tr>
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right">非标产品简述</td>
    <td colspan="3" align="left" class="TableData">${note}</td>
    </tr>
  <tr>
    <td colspan="4" nowrap="nowrap" class="TableControl"><strong>产品信息</strong></td>
    </tr>
  <tr>
    <td colspan="4" align="right" class="TableData"><table width="100%" border="0">
      <tr>
        <th align="center">四班号</th>
        <th align="center">产品编码</th>
        <th align="center">产品名称</th>
        <%--th align="center">目标价位</th--%>
        <th align="center">青岛公开价<br>(不含税不含运费)</th>
        <s:if test="status.id=='ce2salesyes'||status.id=='asm2salesyes'||status.id=='asm2salesno'||status.id=='pm2salesyes2'||status.id=='pm2salesno2'||status.id=='fd2salesyes'||status.id=='fd2salesno'||status.id=='md2salesyes'||status.id=='md2salesno'">
        <th align="center">销售申请的特价</th>
        <th align="center">最终特价批准部门</th>
        </s:if>
        <th align="center">交货周期<br>(weeks)</th>
        <th align="center">工装模具费</th>
        <th align="center">批量</th>
        <th align="center">操作</th>
      </tr>
      <s:iterator value="rfqDetails">
      <tr class="${(specialPrice!=null && specialPrice!='') ? ((specialApprover=='区域销售经理批准' || specialApprover=='产品经理批准' || specialApprover=='财务部经理批准' || specialApprover=='销售总监批准') ? 'specialApprovedTr' : ((specialApprover=='区域销售经理否决' || specialApprover=='产品经理否决' || specialApprover=='财务部经理否决' || specialApprover=='销售总监否决') ? 'specialRejectedTr' : 'specialTr')) : ''}">
        <td align="left">${sn}&nbsp;</td>
        <td align="left">${productNo}&nbsp;</td>
        <td align="left">${productName}&nbsp;</td>
        <%--td>${expectedPrice}&nbsp;</td--%>
        <td><fmt:formatNumber value="${(materialCost + loh)/(1-series.targetProfit/100)/(1-series.discountRate/100)}" pattern="#.##"  groupingUsed="true"/></td>
        <s:if test="status.id=='ce2salesyes'||status.id=='asm2salesno'||status.id=='pm2salesno2'||status.id=='fd2salesno'||status.id=='md2salesno'">
        <td><input type=text size=10 name="specialPrice" class="specialPrice" value="${specialPrice}"></td>
        <td>${specialApprover}</td>
        </s:if>
        <s:if test="status.id=='asm2salesyes'||status.id=='pm2salesyes2'||status.id=='fd2salesyes'||status.id=='md2salesyes'">
        <td>${specialPrice}</td>
        <td>${specialApprover}</td>
        </s:if>
        <td>${deliveryWeeks}</td>
        <td><fmt:formatNumber value="${toolingCharge}" pattern="#.##"  groupingUsed="true"/></td>
        <td><fmt:formatNumber value="${batch}" pattern="#.##"  groupingUsed="true"/></td>
        <td align="center"><a href="rfq-detail!view.action?id=${id}"><s:text name='common.view'/></a>
        </td>
      </tr>
      </s:iterator>
    </table></td>
    </tr>
    <s:if test="appCheck4!=null && appCheck4!=''">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>应用工程部意见</strong></td>
    <td colspan="3" align="left" class="TableData"><s:if test="noteApp!=null && noteApp!=''">${noteApp}<br /></s:if>    
    <s:if test="appCheck1==1">项目基本信息填写完整/</s:if>
    <s:if test="appCheck2==1">技术说明完善/</s:if>
    <s:if test="appCheck3==1">配置清单完善</s:if>，与标准产品的契合度：${appCheck4}
	</td>
  </tr>
  </s:if>
  <s:if test="(fileUploadsCePublic!=null&&fileUploadsCePublic.size!=0)||(noteCePublic!=null && noteCePublic!='')">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>报价工程部意见(公开价)</strong></td>
    <td colspan="3" align="left" class="TableData"><s:if test="noteCePublic!=null && noteCePublic!=''">${noteCePublic}<br /></s:if>
    <s:iterator value="fileUploadsCePublic"><a href="${ctx}/upload/file-upload.action?id=${id}">${fileSaveName}</a>&nbsp;&nbsp;</s:iterator></td>
  </tr>
  </s:if>
  <s:if test="status.id=='ce2salesyes'||status.id=='asm2salesno'||status.id=='pm2salesno2'||status.id=='fd2salesno'||status.id=='md2salesno'">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>特价申请原因</strong></td>
    <td colspan="3" align="left" class="TableData"><textarea name="noteSpecialPrice" id="noteSpecialPrice" cols="70" rows="4">${noteSpecialPrice}</textarea>
    <iframe id="upload3" src="${ctx}/upload/file-upload!init.action?dir1=${sn}&dir2=specialPrice&dir3=" frameborder="0" scrolling="no" width="600" height="50"></iframe></td>
  </tr>
  </s:if>
  <s:if test="status.id=='asm2salesyes'||status.id=='pm2salesyes2'||status.id=='fd2salesyes'||status.id=='md2salesyes'">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>特价申请原因</strong></td>
    <td colspan="3" align="left" class="TableData"><s:if test="noteSpecialPrice!=null && noteSpecialPrice!=''">${noteSpecialPrice}<br /></s:if>
    <s:iterator value="fileUploadsSpecialPrice"><a href="${ctx}/upload/file-upload.action?id=${id}">${fileSaveName}</a>&nbsp;&nbsp;</s:iterator></td>
  </tr>
  </s:if>
  <s:if test="(fileUploadsPm!=null&&fileUploadsPm.size!=0)||(notePm!=null && notePm!='')">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>产品经理意见</strong></td>
    <td colspan="3" align="left" class="TableData"><s:if test="notePm!=null && notePm!=''">${notePm}<br /></s:if>
    <s:iterator value="fileUploadsPm"><a href="${ctx}/upload/file-upload.action?id=${id}">${fileSaveName}</a>&nbsp;&nbsp;</s:iterator></td>
  </tr>
  </s:if>
  <s:if test="noteMm!=null && noteMm!=''">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>市场部经理意见</strong></td>
    <td colspan="3" align="left" class="TableData">${noteMm}</td>
  </tr>
  </s:if>
  <s:if test="noteFd!=null && noteFd!=''">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>财务部经理意见</strong></td>
    <td colspan="3" align="left" class="TableData">${noteFd}</td>
  </tr>
  </s:if>
  <s:if test="noteMd!=null && noteMd!=''">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>销售总监意见</strong></td>
    <td colspan="3" align="left" class="TableData">${noteMd}</td>
  </tr>
  </s:if>
  <s:if test="noteGm!=null && noteGm!=''">
  <tr>
    <td nowrap="nowrap" class="TableContent" align="right"><strong>总经理意见</strong></td>
    <td colspan="3" align="left" class="TableData">${noteGm}</td>
  </tr>
  </s:if>
    <tr>
    <td class="TableData" height="50" colspan="4" align="center" nowrap>
    	<s:iterator value="rfqWorkflows">
    		<s:if test="nextStatus.id == 'specialprice2asm'">
    			<input type="submit" class="BigButton" value="${title}" onClick="javascript:document.getElementById('rfqStatus').value='${nextStatus.id}';return checkSpecialPrice();" />&nbsp;&nbsp;&nbsp;&nbsp;
    		</s:if>
    		<s:if test="nextStatus.id != 'specialprice2asm'">
    			<input type="submit" class="BigButton" value="${title}" onClick="javascript:document.getElementById('rfqStatus').value='${nextStatus.id}';return window.confirm('确认要提交吗？');" />&nbsp;&nbsp;&nbsp;&nbsp;
    		</s:if>
    	</s:iterator>
    </td>
  </tr>
</table>
</form>
</body>
</html>
<script language=javascript>
function checkSpecialPrice() {
	var inputs = $(".specialPrice");
	var hasData = false;
	for(var i = 0; i < inputs.size(); i ++) {
		var specialValue = inputs.eq(i).val();
		if(specialValue) {
			hasData = true;
		}
	}
	if(!hasData) {
		alert("您没有填写任何特价，请填写后提交。");
		return false;
	}
	return window.confirm('确认要提交吗？');
}
</script>