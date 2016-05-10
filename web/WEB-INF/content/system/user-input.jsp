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
                    loginName: {
                        required: true,
                        remote: "user!checkLoginName.action?oldLoginName=" + encodeURIComponent('${loginName}')
                    },
                    name: "required",
                    plainPassword: {
                        required: true,
                        minlength: 6
                    },
                    passwordConfirm: {
                        equalTo: "#plainPassword"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    area_id: "required",
                    status: "required",
                    checkedRoles: "required"
                },
                messages: {
                    loginName: {
                        remote: "用户名已存在"
                    },
                    passwordConfirm: {
                        equalTo: "两次输入的密码不一致"
                    }
                }
            });
        });
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/system/user!save.action" method="post">
    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="workingVersion" value="${version}"/>
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="4" nowrap class="TableHeader"></td>
        </tr>
        <tr>
            <td width="10%" align="right" nowrap="nowrap" class="TableContent"><s:text name='user.loginName'/></td>
            <td width="40%" align="left" class="TableData"><input type="text" class="BigInput" name="loginName"
                                                                  id="loginName" size="20" maxlength="50"
                                                                  value="${loginName}"
                                                                  <s:if test="id != null">readonly='readonly'</s:if> />
            </td>
            <td width="10%" align="right" nowrap="nowrap" class="TableContent"><s:text name='user.shortName'/></td>
            <td width="40%" align="left" class="TableData"><input name="shortName" type="text" class="BigInput"
                                                                  id="shortName" value="${shortName}" size="20"
                                                                  maxlength="50"/></td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent"><s:text name='user.plainPassword'/></td>
            <td align="left" class="TableData"><input type="password" class="BigInput" name="plainPassword"
                                                      id="plainPassword" size="20" maxlength="50"
                                                      value="${plainPassword}"/></td>
            <td align="right" nowrap="nowrap" class="TableContent"><s:text name='user.passwordConfirm'/></td>
            <td align="left" class="TableData"><input name="passwordConfirm" type="password" class="BigInput"
                                                      id="passwordConfirm" value="${plainPassword}" size="20"
                                                      maxlength="50"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right"><s:text name='user.name'/></td>
            <td align="left" class="TableData"><input name="name" type="text" class="BigInput" id="name" value="${name}"
                                                      size="20" maxlength="50"/></td>
            <td nowrap="nowrap" class="TableContent" align="right"><s:text name='user.englishName'/></td>
            <td align="left" class="TableData"><input name="englishName" type="text" class="BigInput" id="englishName"
                                                      value="${englishName}" size="20" maxlength="50"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">所属区域</td>
            <td align="left" class="TableData"><s:select name="area_id" headerKey="" headerValue="请选择" list="areasAll"
                                                         listKey="id" listValue="name"/></td>
            <td align="right" nowrap="nowrap" class="TableContent"><s:text name='user.email'/></td>
            <td align="left" class="TableData"><input name="email" type="text" class="BigInput" id="email"
                                                      value="${email}" size="20" maxlength="50"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">区域销售经理</td>
            <td align="left" class="TableData"><s:select name="asm_id" headerKey="" headerValue="请选择" list="asmsAll"
                                                         listKey="id" listValue="name"/></td>
            <td align="right" nowrap="nowrap" class="TableContent">&nbsp;</td>
            <td align="left" class="TableData">&nbsp;</td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right"><s:text name='user.status'/></td>
            <td align="left" class="TableData"><s:radio name="status" list="#{'启用':'启用','禁用':'禁用'}"/></td>
            <td align="right" nowrap="nowrap" class="TableContent">&nbsp;</td>
            <td align="left" class="TableData">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="4" nowrap class="TableHeader"><s:text name='user.role'/></td>
        </tr>
        <tr>
            <td colspan="4" class="TableData"><s:radio name="checkedRoles" list="rolesAll" listKey="id"
                                                       listValue="name"/></td>
        </tr>
        <tr class="TableControl">
            <td nowrap colspan="4" align="center"><input type="submit" class="BigButton"
                                                         value=" <s:text name='common.save'/> "/></td>
        </tr>
    </table>
</form>
</body>
</html>