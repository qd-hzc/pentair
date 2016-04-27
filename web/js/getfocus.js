//用方向键和回车键控制表单输入框的焦点

var curCtlIndex = 0;
var arrCtl = new Array();
var oldEvent = new Array();
function SetFocusToFirstControl() {
    var i = 0,j = -1;
    for(i=0;i<document.forms[0].elements.length;i++) {
        if(document.forms[0].elements[i].tagName == 'INPUT' || document.forms[0].elements[i].tagName == 'SELECT' || document.forms[0].elements[i].tagName == 'TEXTAREA') {
            if(document.forms[0].elements[i].type != 'submit' && document.forms[0].elements[i].type != 'reset' && document.forms[0].elements[i].type != 'hidden' && document.forms[0].elements[i].type != 'button') {
                if(document.forms[0].elements[i].disabled == 'disabled' || document.forms[0].elements[i].disabled == true) 
                    continue;
                if(document.forms[0].elements[i].readOnly) 
                    continue;
                if(document.forms[0].elements[i].style.display =="none") 
                    continue;
                if(document.forms[0].elements[i].style.width == "0px")
                    continue;
                try {
                    j++;
                    arrCtl[j] = document.forms[0].elements[i];
                    arrCtl[j].blur();
                } catch(el) { }
            }
            document.forms[0].elements[i].onblur = onblur_handler;
            document.forms[0].elements[i].onfocus = onfocus_handler;
        }
    }
    for(i=0;i<arrCtl.length;i++) {
        try {
            arrCtl[i].focus();
            break;
        } catch(el) {}
    }
}

function keyEnter(objSubmit) {
    var i = 0;
    if(event.keyCode == 13) {
        for(i=curCtlIndex+1;i<arrCtl.length;i++) {
            if(curCtlIndex < arrCtl.length - 1) {
                try {
                    curCtlIndex++;
                    arrCtl[curCtlIndex].focus();
                    return false;
                }
                catch(el) {}
            }
            else {
                //break;
            }
        }   
        if(objSubmit != undefined && objSubmit != '' )
            document.getElementById(objSubmit).click();
        return false;
    } else if(event.keyCode == 37) {
        for(i=curCtlIndex-1;i>=0;i--) {
            try {
                curCtlIndex--;
                arrCtl[curCtlIndex].focus();
                break;
            } catch(el) {}
        }
        return false;
    }
}


function onblur_handler() {
    this.style.backgroundColor="White";
}

function onfocus_handler() {
    for(i=0;i<arrCtl.length;i++) {
        if(this.id == '') {
            if(this.name == arrCtl[i].name) {
                curCtlIndex = i;
                break;
            }
        } else {
            if(this.id == arrCtl[i].id)  {
                curCtlIndex = i;
                break;
            }
        }
    }
    this.style.backgroundColor="yellow";
    return true;
}