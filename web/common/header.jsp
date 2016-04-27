<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<table id="topbar" class="topbar" height=50 width="100%" border=0 cellspacing=0 cellpadding=0>
  <tr height=40>
    <td nowrap>
      <span id="banner_text" style="font:黑体">&nbsp;<img src="${ctx}/img/pentair_logo.png" align=absmiddle height=40 style='Filter:Glow(Color=white,Strength=2)'>&nbsp;销售管理系统</span></td>
    <td align="center">
    <security:authorize ifAnyGranted="ROLE_ADMIN">
	<a href="${ctx}/rfq/rfq.action" class="menu_link"><s:text name="menu.rfq"/></a>&nbsp;|&nbsp;
	<a href="${ctx}/catalog/brand.action" class="menu_link"><s:text name="menu.brand"/></a>&nbsp;|&nbsp;
	<a href="${ctx}/catalog/series.action" class="menu_link"><s:text name="menu.series"/></a>&nbsp;|&nbsp;
	<a href="${ctx}/catalog/snrule.action" class="menu_link"><s:text name="menu.snrule"/></a>&nbsp;|&nbsp;
<!--	<a href="${ctx}/catalog/product.action" class="menu_link"><s:text name="menu.product"/></a>&nbsp;|&nbsp;
 -->	
 	<a href="${ctx}/system/area.action" class="menu_link">地区管理</a>&nbsp;|&nbsp;
 	<a href="${ctx}/system/area-app.action" class="menu_link">地区品牌责任人管理</a>&nbsp;|&nbsp;
 	<a href="${ctx}/system/user.action" class="menu_link"><s:text name="menu.user"/></a>&nbsp;
    </security:authorize>
     </td>
    <td align="right" valign="top">
      <div id="time"><span class="time_left"><span class="time_right">
 <span id='date' >当前用户:<%=((LoginUser)SpringSecurityUtils.getCurrentUser()).getName()%></span>&nbsp;
      </span></span>
      </div>
      <div style="font-size:6px">&nbsp;</div>
      <div>
      <a href="javascript:logout();" class="menu_link"><img src="${ctx}/img/menu/mytable.gif" width="16" height="16" border="0" align="absmiddle">退出系统 </a>&nbsp;
	  <a href="${ctx}/system/user!changePasswordInput.action" class="menu_link" rel="gb_page_center[500, 250]" title="<s:text name='security.changepassword' />"><img src="${ctx}/img/login.gif" width="16" height="16" border="0" align="absmiddle">修改密码</a>&nbsp;&nbsp;
      </div>
      </td>
    </tr>
</table>
<!--
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="${ctx}/img/shortcut_bg.png" class="small" id="NAV" height="26">
<tr>
	<td width="20" nowrap>&nbsp;</td>
	<td nowrap>
    <security:authorize ifAnyGranted="ROLE_ADMIN">
	<a href="${ctx}/rfq/rfq.action" class="menu_link"><s:text name="menu.rfq"/></a>&nbsp;&nbsp;
	<a href="${ctx}/catalog/brand.action" class="menu_link"><s:text name="menu.brand"/></a>&nbsp;&nbsp;
	<a href="${ctx}/catalog/series.action" class="menu_link"><s:text name="menu.series"/></a>&nbsp;&nbsp;
	<a href="${ctx}/catalog/product.action" class="menu_link"><s:text name="menu.product"/></a>&nbsp;&nbsp;
	<a href="${ctx}/system/user.action" class="menu_link"><s:text name="menu.user"/></a>&nbsp;
    </security:authorize>
	</td>
	<td align="right" nowrap>
	  <a href="javascript:logout();" class="menu_link"><img src="${ctx}/img/menu/mytable.gif" width="16" height="16" border="0" align="absmiddle"> <s:text name="security.logout"/></a>&nbsp;
	  <a href="${ctx}/system/user!changePasswordInput.action" class="menu_link" rel="gb_page_center[500, 250]" title="<s:text name='security.changepassword' />"><img src="${ctx}/img/login.gif" width="16" height="16" border="0" align="absmiddle"> <s:text name="security.changepassword"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
	<td align="right" nowrap><a href="javascript:my_menu_view1('arrow1')" id="arrow1" class="call_up" alt="<s:text name='tooltip.updown'/>"></a></td>
</tr>
</table>
 -->