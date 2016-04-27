/************各种滚动或跑马灯特效****************/
/************以下是文字上下翻屏滚动特效****************/
/*调用方法
new Dron_ScrollBox("id").init();
*/
function Dron_ScrollBox(uid){
	this.scrollBox = document.getElementById(uid);
	this.scrollBoxHeight = this.scrollBox.clientHeight;
	this.scrollBoxInner = this.scrollBox.innerHTML;
	this.scrollCol = this.scrolln = 0;
	this.setScroll = function (){
		this.scrollBox.scrollTop = this.scrollCol + this.scrolln;
		if(this.scrolln==this.scrollBoxHeight){
		   return this.addScroll()
		}else{
			this.scrolln ++;
		}
		var o = this;
		function m(){o.setScroll();}
		setTimeout(m,20);
	}
	this.addScroll = function (){
		this.scrollBox.innerHTML += this.scrollBoxInner;
		this.scrollCol = this.scrollBox.scrollTop;
		this.scrolln = 0;
		var o = this;
		function m(){o.setScroll();}
		setTimeout(m,3000);
	}
	this.init = this.addScroll;
}

/***********图片或文字滚动特效***************/
/*调用方法
Marquee.newInstance("Left", "partners").Init();
*/
String.prototype.format = function(){
var tmpStr = this;
var iLen = arguments.length;
for(var i=0;i<iLen;i++){
  tmpStr = tmpStr.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
}
return tmpStr;
}
function $() {
  var elements = new Array();
  for (var i = 0; i < arguments.length; i++) {
    var element = arguments[i];
    if (typeof element == 'string')
      element = document.getElementById(element);

    if (arguments.length == 1)
      return element;

    elements.push(element);
  }
  return elements;
}
function IMarquee(){
function throwError(){alert("接口未实现：" + arguments[0]);}
this.Scroll = function(){throwError("Scroll");}
this.Clone = function(){throwError("Clone");}
}
function AbstractMarquee(){
IMarquee.apply(this);
var ref = this;
var timer = null;
var container = null;
var indexs = ["ContainerID", "Delay", "Amount", "Width", "Height"];
this.Amount = 1;
this.Delay = 30;
this.Width = 0;
this.Height = 0;
this.ContainerID = "";
this.Start = function(){
  clearTimer();
  timer = setInterval(ref.Scroll, ref.Delay);
}
this.Stop = function(){
  clearTimer();
}
this.Pause = function(){
  clearTimer();
}
this.Init = function(){
  container = $(this.ContainerID);
  if(container == null) {alert("无法找到id为{0}的对象,初始化失败。".format(this.ContainerID));return;};
  container.style.overflow = "hidden";
  if(this.Width > 0) container.style.width = this.Width + "px";
  if(this.Height > 0) container.style.height = this.Height + "px";
  this.Clone();
  this.AttachEvent();
  this.Start();
}
this.AttachEvent = function(){
  container.onmouseover = ref.Pause;
  container.onmouseout = ref.Start;
}
function clearTimer(){
  if(timer != null)clearInterval(timer);
}
function _Marquee(){
  var max = Math.min(indexs.length, arguments.length);
  for(var i=0;i<max;i++)
   this[indexs[i]] = arguments[i];
}
_Marquee.apply(this, arguments);
}
function MarqueeUp(){
AbstractMarquee.apply(this, arguments);
var ref = this;
var container = $(this.ContainerID);
this.Clone = function(){
  container.innerHTML = '<div>{0}</div><div>{0}</div>'.format(container.innerHTML);
}
this.Scroll = function(){
  with(container){
   if(scrollTop >= lastChild.offsetTop) scrollTop -= firstChild.offsetHeight;
   else scrollTop += ref.Amount;
  }
}
}
function MarqueeDown(){
AbstractMarquee.apply(this, arguments);
var ref = this;
var container = $(this.ContainerID);
this.Clone = function(){
  container.innerHTML = '<div>{0}</div><div>{0}</div>'.format(container.innerHTML);
  container.scrollTop = container.scrollHeight;
}
this.Scroll = function(){
  with(container){
   if(scrollTop <= firstChild.offsetTop) scrollTop += lastChild.offsetHeight;
   else scrollTop -= ref.Amount;
  }
}
}
function MarqueeLeft(){
AbstractMarquee.apply(this, arguments);
var ref = this;
var container = $(this.ContainerID);
this.Clone = function(){
  container.innerHTML = '<table cellspacing="0" cellpadding="0" border="0"><tr><td>{0}</td><td>{0}</td></tr></table>'.format(container.innerHTML);
}
this.Scroll = function(){
  with(container){
   if(scrollLeft >= firstChild.rows[0].cells[1].offsetLeft) scrollLeft -= firstChild.rows[0].cells[0].offsetWidth;
   else scrollLeft += ref.Amount;
  }
}
}
function MarqueeRight(){
AbstractMarquee.apply(this, arguments);
var ref = this;
var container = $(this.ContainerID);
this.Clone = function(){
  container.innerHTML = '<table cellspacing="0" cellpadding="0" border="0"><tr><td>{0}</td><td>{0}</td></tr></table>'.format(container.innerHTML);
  container.scrollLeft = container.scrollWidth;
}
this.Scroll = function(){
  with(container){
   if(scrollLeft <= firstChild.rows[0].cells[0].offsetLeft) scrollLeft += firstChild.rows[0].cells[1].offsetWidth;
   else scrollLeft -= ref.Amount;
  }
}
}
var Marquee = {
Type : {
  UP : function(){return new MarqueeUp(arguments[0]);},
  DOWN : function(){return new MarqueeDown(arguments[0]);},
  LEFT : function(){return new MarqueeLeft(arguments[0]);},
  RIGHT : function(){return new MarqueeRight(arguments[0]);}
},
newInstance : function(type, container){
  return this.Type[type.toUpperCase()].call(this, container);
}
}

