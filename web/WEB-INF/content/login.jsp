<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="com.pentair.utils.tld.SessionTld" %>
<%@ taglib prefix="fmt" uri="http://thankful.site/language" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><s:text name="security.login"/> - <s:text name="website.title"/></title>
    <%@ include file="/common/meta.jsp" %>
    <style type="text/css">
        body {
            font-size: 9pt;
            background: #5A7080;
        }

        table {
            border: 1px #000000 solid;
            border-collapse: collapse;
        }

        td {
            background-color: #266192;
            border: 1px #000000 solid;
        }

        .login_field {
            font-size: 10pt;
            background: url("${ctx}/img/login_field_bg.png");
            repeat-x;
        }

        input.text {
            border: 1px #404040 solid;
            color: #212121;
            font-size: 9pt;
            background: url("${ctx}/img/input_bg.png") repeat-x;
            padding: 2px 0px 2px 5px;
        }

        input.submit {
            width: 74px;
            height: 21px;
            color: #FFFFFF;
            background: url("${ctx}/img/login_btn.png") no-repeat;
            border: 0px;
            cursor: hand;
        }

        input.submit:hover {
            background-position: 0 -21px;
        }
    </style>
</head>
<%
    String contextPath = request.getContextPath();

    Object language = request.getParameter("language");
    String langStr = "china";
    if (null != language) {
        langStr = (String) language;
    }
    request.getSession().setAttribute("language", langStr);
    SessionTld.setSession(request.getSession());
%>
<body onload="javascript:loginForm.j_username.focus();">
<br>
<br>
<br>
<br>

<fmt:language key="login.login"/>
<div style="height: 40px;">
    语言/Language：
    <select id="language">
        <option value="china">中文</option>
        <option value="english">English</option>
    </select>
</div>
<script>
    $(function () {
        var contextPath = '<%=contextPath%>';
        var language = '<%=langStr%>';
        $('#language').val(language).change(function () {
            var lang = $('#language').val();
            window.location.href = contextPath + '/login.jsp?language=' + lang;
        });
    })
</script>
<div align="center">
    <form name="loginForm" method="post" action="${ctx}/j_spring_security_check"
          onsubmit="javascript:return checkForm(this);">
        <input type="hidden" name="j_captcha" id="j_captcha" value="54321">
        <table id=loginTable border=0 cellspacing="0" cellpadding="0" align="center">
            <tr>
                <td align="center" colspan=4>
                    <div id=pentairLogo style='position:absolute;Filter:Glow(Color=white,Strength=2)'><img
                            src="${ctx}/img/pentair_logo.png?version=121129" height=50></div>
                    <img src="${ctx}/img/login.jpg?version=121212" width="609" height="205"></td>
            </tr>
            <tr height="37">
                <td align="center" class="login_field" nowrap="nowrap">
                    <b>用户名：</b>
                </td>
                <td align="center" class="login_field" nowrap="nowrap">
                    <input type="text" class="text" name="j_username" size="15" maxlength="30"
                           onmouseover="this.focus()" onfocus="this.select()" id="j_username"
                           value="<s:if test="not empty param.error"><%=session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%></s:if>"/>
                </td>
                <td align="center" class="login_field" nowrap="nowrap">
                    <b>密码：</b>
                </td>
                <td align="center" class="login_field" nowrap="nowrap">
                    <input type="password" class="text" name="j_password" onmouseover="this.focus()"
                           onfocus="this.select()" size="15" maxlength="30" value="" id="j_password">
                    &nbsp;&nbsp;
                    <input type="submit" name="submit" class="submit" value="登录 ">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
<script language="JavaScript">
    <!--
    function checkForm(f) {
        if (f.j_username.value == "") {
            alert("<s:text name="alert.input"/><s:text name="user.loginName"/><s:text name="mark.exclamation"/>");
            f.j_username.focus();
            return false;
        }
        if (f.j_password.value == "") {
            alert("<s:text name="alert.input"/><s:text name="user.plainPassword"/><s:text name="mark.exclamation"/>");
            f.j_password.focus();
            return false;
        }

        return true;
    }

    <%if ("1".equals(request.getParameter("error"))) {%>
    alert("<s:text name="security.error1"/>");
    <%}%>

    var loginTable = $("#loginTable");
    var pentairLogo = $('#pentairLogo');
    pentairLogo.css("left", (loginTable.offset().left + 80) + "px");
    pentairLogo.css("top", (loginTable.offset().top + 20) + "px");
    //-->
</script>   
