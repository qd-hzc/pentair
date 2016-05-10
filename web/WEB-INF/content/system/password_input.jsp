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
                    oldPassword: "required",
                    plainPassword: {
                        required: true,
                        minlength: 6
                    },
                    passwordConfirm: {
                        equalTo: "#plainPassword"
                    }
                },
                messages: {
                    passwordConfirm: {
                        equalTo: "两次输入的密码不一致"
                    }
                }
            });
        });
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/system/user!changePasswordSave.action" method="post">
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="2" nowrap class="TableHeader"></td>
        </tr>
        <tr>
            <td width="30%" align="right" nowrap="nowrap" class="TableContent">请输入原密码：</td>
            <td align="left" class="TableData"><input name="oldPassword" type="password" class="BigInput"
                                                      id="oldPassword" size="20" maxlength="50"/></td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">请输入新密码：</td>
            <td align="left" class="TableData"><input type="password" class="BigInput" name="plainPassword"
                                                      id="plainPassword" size="20" maxlength="50"/></td>
        </tr>
        <tr>
            <td align="right" nowrap="nowrap" class="TableContent">再次确认输入新密码：</td>
            <td align="left" class="TableData"><input name="passwordConfirm" type="password" class="BigInput"
                                                      id="passwordConfirm" size="20" maxlength="50"/></td>
        </tr>
        <tr class="TableControl">
            <td nowrap colspan="2" align="center"><input type="submit" class="BigButton"
                                                         value=" <s:text name='common.save'/> "/></td>
        </tr>
    </table>
</form>
</body>
</html>