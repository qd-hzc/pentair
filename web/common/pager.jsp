<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<c:set var="_pageNo" value="${page.pageNo}" />
<c:set var="_totalPages" value="${page.totalPages}" />
<table width="100%">
	<tr>
		<td nowrap width="30%" class="txdeepgreen" align="center">共<span class="red">${page.totalCount}</span>条/每页<span class="red">${page.pageSize}</span>条  当前第<span class="red">${page.pageNo}</span>页/共<span class="red">${page.totalPages}</span>页</td>
		<td nowrap align="center">
<s:if test="page.hasPre">
	<input type="button" value="首页" class="SmallButton" onclick="javascript:jumpPage(1);"> &nbsp;&nbsp;
	<input type="button" value="上一页" class="SmallButton" onclick="javascript:jumpPage(${page.prePage});"> &nbsp;&nbsp;
</s:if>
<s:if test="page.hasNext">
	<input type="button" value="下一页" class="SmallButton" onclick="javascript:jumpPage(${page.nextPage});"> &nbsp;&nbsp;
	<input type="button" value="末页" class="SmallButton" onclick="javascript:jumpPage(${page.totalPages});"> &nbsp;&nbsp;
</s:if>
		</td>
        <td nowrap align="center" class="txdeepgreen">
        转到第<select name="pageno" onchange="javascript:jumpPage(pageno.value);">
        <%for(int i=1; i<=((Long)(pageContext.getAttribute("_totalPages"))).intValue(); i++){%>
        <option value="<%=i%>" <%if(i == ((Integer)(pageContext.getAttribute("_pageNo"))).intValue()){%>selected="selected" <%}%>><%=i%></option>
        <%}%>
        </select>页
		</td>

	</tr>
</table>
