<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <script language="JavaScript">
        var snruleId;
        var oldProductNo;

        $(document).ready(function () {
            snruleId = parent.$("#snruleId").val();
            oldProductNo = parent.$("#productNo").val();
            if (snruleId) {
                $("#snRules").val(snruleId);
                $("#snRules").change();
            }
        });

        function snRuleChange(select) {
            var id = $(select).val();
            if (id) {
                var url = "${ctx}/catalog/snrule!input.action?id=" + id + "&time=" + new Date().getTime();
                $.get(url, null, function (responsexml) {
                    var root = $(responsexml.documentElement);
                    $("#s1").html(root.attr("s1"));
                    $("#s2").html(root.attr("s2"));
                    $("#s3").html(root.attr("s3"));
                    $("#s4").html(root.attr("s4"));
                    $("#s5").html(root.attr("s5"));
                    $("#s6").html(root.attr("s6"));
                    $("#s7").html(root.attr("s7"));
                    $("#s8").html(root.attr("s8"));
                    $("#s9").html(root.attr("s9"));

                    //取用户之前设置的值进行填充
                    if (oldProductNo) {
                        <%
                        String prefix = request.getParameter("prefix");
                        if(prefix.length() == 2) {
                        %>
                        root.attr("d1", oldProductNo.substring(0, 2));
                        root.attr("d2", oldProductNo.substring(2, 4));
                        root.attr("d3", oldProductNo.substring(4, 6));
                        root.attr("d4", oldProductNo.substring(6, 8));
                        root.attr("d5", oldProductNo.substring(8, 9));
                        root.attr("d6", oldProductNo.substring(9, 10));
                        root.attr("d7", oldProductNo.substring(10, 11));
                        root.attr("d8", oldProductNo.substring(11, 12));
                        root.attr("d9", oldProductNo.substring(12, 13));
                        oldProductNo = null;
                        <%
                        }
                        else if(prefix.length() == 3) {
                        %>
                        root.attr("d1", oldProductNo.substring(0, 3));
                        root.attr("d2", oldProductNo.substring(3, 5));
                        root.attr("d3", oldProductNo.substring(5, 7));
                        root.attr("d4", oldProductNo.substring(7, 9));
                        root.attr("d5", oldProductNo.substring(9, 10));
                        root.attr("d6", oldProductNo.substring(10, 11));
                        root.attr("d7", oldProductNo.substring(11, 12));
                        root.attr("d8", oldProductNo.substring(12, 13));
                        root.attr("d9", oldProductNo.substring(13, 14));
                        oldProductNo = null;
                        <%
                        }
                        %>
                    }

                    for (var i = 1; i <= 9; i++) {
                        var s = $("#s" + i);
                        var label = root.attr("s" + i);
                        s.html(label);

                        var d = $("#d" + i);
                        var dValue = root.attr("d" + i);
                        if (label == '-') {
                            dValue = "";
                        }
                        var prop = root.attr("i" + i);

                        if (label == "颜色" || label == "表面处理") {
                            prop = "0:无,1:RAL7021,2:RAL7032,3:镀锌,4:特殊颜色,5:RAL7035,8:S078,9:RAL9005";
                        }
                        if ((label == "材料" || label == "材质") && !prop) {
                            prop = "C:冷、热轧板,Z:覆铝锌板,G:镀锌板,A:铝板,S:不锈钢板,P:塑料,E:铝型材,V:钢化玻璃,Y:有机玻璃/亚克力";
                        }
                        if (i == 9) {
                            if (prop != "*") {
                                if (dValue == "0") {
                                    dValue = "G";
                                }
                                prop = "G:configured (ATO),P:Paint Only,F:Fast Track (Cut out，New size，Change color),M:Modified (New part，New material),A:&lt;30% 部件定制的customize,B:&gt;30% &lt;60%部件定制的customize,C:&gt;60% 部件定制的customize";
                            }
                            else {
                                dValue = "";
                            }
                        }

                        d.val(dValue);
                        d.next().remove();
                        if (prop == "*" || label == '无意义' || label == '补足') {
                            d.css("display", "");
                            d.css("border-width", "0px");
                            d.attr("readonly", true);
                            //d.parent().css("width", "");
                        }
                        else if (prop == "") {
                            d.css("display", "");
                            d.css("border-width", "1px");
                            d.attr("readonly", false);
                            //d.parent().css("width", "");
                        }
                        else {
                            var options = prop.split(",");
                            var html = "<div align=left>";
                            for (var j = 0; j < options.length; j++) {
                                var option = options[j].split(":");
                                html += "<input type=radio id=o" + i + "_" + j + " name=o" + i + " value='" + option[0] + "' onclick='if(this.checked){$(\"#d" + i + "\").val(this.value);";
                                if (i == 9) {
                                    html += "checkStandard(this);";
                                }
                                html += "}'";
                                if (d.val() == option[0]) {
                                    html += " checked";
                                }
                                html += ">";
                                html += "<label for=o" + i + "_" + j + ">" + option[0] + " - " + option[1] + "</label><br>";
                            }
                            html += "</div>";
                            d.parent().append($(html));
                            //d.parent().css("width", "150px");
                            d.css("display", "none");
                        }
                    }

                    $("#s10").html(root.attr("s10"));
                    var d10 = $("#d10");
                    d10.css("border-width", "0px");

                    var note = root.text();
                    $("#note").html(note.replace(/\n/g, "<br>"));

                    var notes = note.split("\n");
                    for (var i = 0; i < notes.length; i++) {
                        note = notes[i];
                        if (note.indexOf(")") != -1) {
                            var index = note.substring(0, note.indexOf(")"));
                            $("#d" + index).parent().attr("title", note);
                        }
                    }
                }, "xml");
            }
            else {

            }
        }

        function generateProductNo() {
            var productNo = "";
            for (var i = 1; i < 10; i++) {
                productNo += $("#d" + i).val();
            }
            if (productNo.length < 13) {
                alert("产品编码填写不完整！");
                return;
            }
            parent.$("#productNo").val(productNo);
            parent.$("#snruleId").val($("#snRules").val());
            parent.closeProductNoSelect();
        }

        function checkStandard(radio) {
            var input = $("#d9");
            if (input.val() == 'S') {
                var choise = confirm("请确认你的产品是标准产品还是非标产品?");
                if (choise) {
                    alert("您选择的是标准产品，建议退出该系统。");
                }
                else {
                    input.val('');
                    radio.checked = false;
                }
            }
        }
    </script>
</head>
<body>
<table align="center" width="99%" class="TableBlock">
    <tr>
        <td width="15%" align="right" nowrap="nowrap" class="TableContent">编码规则</td>
        <td class="TableContent"><s:select style="width:400px;" id="snRules" name="snRules" headerKey=""
                                           headerValue="请选择" list="snRulesAll" listKey="id" listValue="name"
                                           onchange="snRuleChange(this);"/></td>
    </tr>
</table>
<table align="center" width="99%" class="TableBlock" style='table-layout:auto'>
    <tr height=30>
        <td id=s1 class="TableContent"></td>
        <td id=s2 class="TableContent"></td>
        <td id=s3 class="TableContent"></td>
        <td id=s4 class="TableContent"></td>
        <td id=s5 class="TableContent"></td>
        <td id=s6 class="TableContent"></td>
        <td id=s7 class="TableContent"></td>
        <td id=s8 class="TableContent"></td>
        <td id=s9 class="TableContent"></td>
        <td id=s10 class="TableContent"></td>
    </tr>
    <tr>
        <td class="TableData" align=center valign=top><input type=text id=d1 maxlength=2 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d2 maxlength=2 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d3 maxlength=2 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d4 maxlength=2 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d5 maxlength=1 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d6 maxlength=1 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d7 maxlength=1 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d8 maxlength=1 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d9 maxlength=1 style='width:40px'/></td>
        <td class="TableData" align=center valign=top><input type=text id=d10 maxlength=4 style='width:40px'
                                                             onblur='this.value=this.value.toUpperCase();' readonly/>
        </td>
    </tr>
    <tr>
        <td colspan=10 class="TableData" id=note></td>
    </tr>
    <tr>
        <td colspan=10 align=center class="TableData">
            <input type=button class="BigButton" value=" 确定 " onclick="generateProductNo();"/>
            <input type=button class="BigButton" value=" 返回 " onclick="parent.closeProductNoSelect();"/>
        </td>
    </tr>
</table>
</body>
</html>
