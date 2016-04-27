<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/jscalendar/calendar-win2k-cold-1.css" title="win2k-cold-1" media="all" />
<script language="javascript" type="text/javascript" src="${ctx}/js/jscalendar/calendar.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/jscalendar/calendar-setup.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/jscalendar/lang/cn_utf8.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/greybox/gb_styles.css" media="all" />
<script type="text/javascript">var GB_ROOT_DIR = "${ctx}/js/greybox/";</script>
<script type="text/javascript" src="${ctx}/js/greybox/AJS.js"></script>
<script type="text/javascript" src="${ctx}/js/greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${ctx}/js/greybox/gb_scripts.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/validate/jquery.validate.css"/>
<script language="javascript" type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/validate/jquery.validate.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/validate/messages_cn.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/getfocus.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/table.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/utils.js"></script>
<script language="JavaScript">

function logout(){
	location="/j_spring_security_logout";
}

var view_flag1=1;
function my_menu_view1(id){
	var el=document.getElementById(id);
	if(view_flag1==1) {
		document.getElementById("topbar").style.display="none";
		el.className="call_down";
	} else {
		document.getElementById("topbar").style.display="";
		el.className="call_up";
	}

	view_flag1=1-view_flag1;
}

var view_flag2=1;
function my_menu_view2(id){
	var el=document.getElementById(id);
	if(view_flag2==1) {
		parent.parent.document.getElementById("frame2").cols="0,*";
		el.className="call_right";
	} else {
		parent.parent.document.getElementById("frame2").cols="200,*";
		el.className="call_left";
	}

	view_flag2=1-view_flag2;
}

function maximizer(){
	parent.parent.document.getElementById("frame2").cols="0,*";
	document.getElementById("arrow1").className="call_right";
	view_flag2 = 0;

	document.getElementById("topbar").style.display="none";
	parent.parent.parent.document.getElementById("frame1").rows="0,25,*";
	document.getElementById("arrow2").className="call_down";
	view_flag1 = 0;
}

function maximizerTop(){
	document.getElementById("topbar").style.display="none";
	parent.parent.parent.document.getElementById("frame1").rows="0,25,*";
	document.getElementById("arrow2").className="call_down";
	view_flag1 = 0;
}
//maximizerTop();
</script>