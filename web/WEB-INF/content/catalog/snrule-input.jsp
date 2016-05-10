<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <%
        com.opensymphony.xwork2.util.ValueStack vs = (com.opensymphony.xwork2.util.ValueStack) request.getAttribute("struts.valueStack");
        System.out.println(vs.findValue("name"));
    %>
    <script language="JavaScript">
        $(document).ready(function () {
            $("#inputForm").validate({//为inputForm注册validate函数
                rules: {
                    name: "required",
                    s1: "required",
                    s2: "required",
                    s3: "required",
                    s4: "required",
                    s5: "required",
                    s6: "required",
                    s7: "required",
                    s8: "required",
                    s9: "required",
                    s10: "required"
                },
                messages: {}
            });
        });
    </script>
</head>
<body>
<form id="inputForm" action="${ctx}/catalog/snrule!save.action" method="post">
    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="workingVersion" value="${version}"/>
    <table align="center" width="99%" class="TableBlock">
        <tr>
            <td colspan="4" nowrap class="TableHeader"></td>
        </tr>
        <tr>
            <td width="30%" align="right" nowrap="nowrap" class="TableContent">规则名称</td>
            <td align="left" class="TableData" colspan=5><input type="text" class="BigInput" name="name" id="name"
                                                                size="30" maxlength="50" value="${name}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段1</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s1" id="s1" size="30"
                                                      maxlength="50" value="${s1}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值1</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d1" id="d1" size="30"
                                                      maxlength="3" value="${d1}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项1</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i1" id="i1" size="30"
                                                      value="${i1}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段2</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s2" id="s2" size="30"
                                                      maxlength="50" value="${s2}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值2</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d2" id="d2" size="30"
                                                      maxlength="2" value="${d2}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项2</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i2" id="i2" size="30"
                                                      value="${i2}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段3</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s3" id="s3" size="30"
                                                      maxlength="50" value="${s3}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值3</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d3" id="d3" size="30"
                                                      maxlength="2" value="${d3}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项3</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i3" id="i3" size="30"
                                                      value="${i3}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段4</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s4" id="s4" size="30"
                                                      maxlength="50" value="${s4}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值4</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d4" id="d4" size="30"
                                                      maxlength="2" value="${d4}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项4</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i4" id="i4" size="30"
                                                      value="${i4}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段5</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s5" id="s5" size="30"
                                                      maxlength="50" value="${s5}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值5</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d5" id="d5" size="30"
                                                      maxlength="1" value="${d5}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项5</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i5" id="i5" size="30"
                                                      value="${i5}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段6</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s6" id="s6" size="30"
                                                      maxlength="50" value="${s6}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值6</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d6" id="d6" size="30"
                                                      maxlength="1" value="${d6}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项6</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i6" id="i6" size="30"
                                                      value="${i6}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段7</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s7" id="s7" size="30"
                                                      maxlength="50" value="${s7}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值7</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d7" id="d7" size="30"
                                                      maxlength="1" value="${d7}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项7</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i7" id="i7" size="30"
                                                      value="${i7}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段8</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s8" id="s8" size="30"
                                                      maxlength="50" value="${s8}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值8</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d8" id="d8" size="30"
                                                      maxlength="1" value="${d8}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项8</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i8" id="i8" size="30"
                                                      value="${i8}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段9</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s9" id="s9" size="30"
                                                      maxlength="50" value="${s9}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值9</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d9" id="d9" size="30"
                                                      maxlength="1" value="${d9}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项9</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i9" id="i9" size="30"
                                                      value="${i9}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">分段10</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="s10" id="s10" size="30"
                                                      maxlength="50" value="${s10}"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">默认值10</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="d10" id="d10" size="30"
                                                      maxlength="4" value="${d10}"
                                                      onblur="this.value=this.value.toUpperCase();"/></td>
            <td nowrap="nowrap" class="TableContent" align="right">可选项10</td>
            <td align="left" class="TableData"><input type="text" class="BigInput" name="i10" id="i10" size="30"
                                                      value="${i10}"/></td>
        </tr>
        <tr>
            <td nowrap="nowrap" class="TableContent" align="right">说明</td>
            <td colspan="5">
                <textarea rows="6" cols="80" name=note id=note>${note}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="6">说明：可选项中*代表只读，选择项通过半角逗号分隔，值和描述用冒号分隔。</td>
        </tr>
        <tr class="TableControl">
            <td colspan="6" align="center" nowrap><input type="submit" class="BigButton"
                                                         value=" <s:text name='common.save'/> "/></td>
        </tr>
    </table>
</form>
</body>
</html>