/* DAlert 1.1.0 
* Author: Dilusha Gonagala 
* License : MIT
*/
!function(a,b){"use strict";dalert={originalAlert:window.alert,originalConfirm:window.confirm,alert:function(a,c,d,e){var f,g="DAlert !",h="Hi.. I am A DAlert !",i="#275F98",j="#ffffff",k="#ffffff",l="#000000";void 0!==e&&(void 0!==e.messageFontColor&&""!==e.messageFontColor&&(l=e.messageFontColor),void 0!==e.messageBgColor&&""!==e.messageBgColor&&(k=e.messageBgColor),void 0!==e.tittleBgColor&&""!==e.tittleBgColor&&(i=e.tittleBgColor),void 0!==e.tittleFontColor&&""!==e.tittleFontColor&&(j=e.tittleFontColor)),void 0!==d&&"[object Function]"===Object.prototype.toString.call(d)&&(f=d),c||(c=g),a||(a=h);try{b("<div></div>").html(a).dialog({title:c,resizable:!1,modal:!0,beforeClose:function(){b(this).remove()},buttons:{Ok:function(){b(this).dialog("close")}},close:function(){void 0!==f&&f()}}),b(".ui-widget-content").css("color",l),b(".ui-widget-content").css("background",k),b(".ui-widget-header").css("background",i),b(".ui-dialog-title").css("color",j),dalert.byPassjQueryUI()}catch(m){window.console.log(m)}},confirm:function(a,c,d,e){var f,g="DAlert !",h="Hi.. I am A DAlert Confirm !",i="#275F98",j="#ffffff",k="#ffffff",l="#000000";void 0!==e&&(void 0!==e.messageFontColor&&""!==e.messageFontColor&&(l=e.messageFontColor),void 0!==e.messageBgColor&&""!==e.messageBgColor&&(k=e.messageBgColor),void 0!==e.tittleBgColor&&""!==e.tittleBgColor&&(i=e.tittleBgColor),void 0!==e.tittleFontColor&&""!==e.tittleFontColor&&(j=e.tittleFontColor)),c||(c=g),a||(a=h);try{b("<div></div>").html(a).dialog({title:c,resizable:!1,modal:!0,beforeClose:function(){b(this).remove()},buttons:{Yes:function(){f=!0,void 0!==d&&""!==d&&("function"==typeof d?(d(f),b(this).dialog("close")):dalert.alert("Not a function !")),(""===d||void 0===d)&&dalert.alert("Please define a callback function")},No:function(){f=!1,void 0!==d&&""!==d&&("function"==typeof d?(d(f),b(this).dialog("close")):dalert.alert("Not a function !")),(""===d||void 0===d)&&dalert.alert("Please define a callback function")}}}),b(".ui-widget-content").css("color",l),b(".ui-widget-content").css("background",k),b(".ui-widget-header").css("background",i),b(".ui-dialog-title").css("color",j),dalert.byPassjQueryUI()}catch(m){window.console.log(m)}},byPassjQueryUI:function(){b(".ui-button-text-only .ui-button-text").css("padding-left","20px"),b(".ui-button-text-only .ui-button-text").css("padding-right","20px"),b(".ui-button-text-only .ui-button-text").css("padding-top","5px"),b(".ui-button-text-only .ui-button-text").css("padding-bottom","5px"),b(".ui-button-text-only .ui-button-text").css("font-size","13px"),b(".ui-dialog").css("border-width","1px"),b(".ui-dialog").css("border-style","solid"),b(".ui-dialog").css("border-color","#76A0F8")},ReplaceAlert:function(){window.alert=dalert.alert},ReplaceConfirm:function(){window.confirm=dalert.confirm},RestoreAlert:function(){window.alert=dalert.originalAlert},RestoreConfirm:function(){window.confirm=dalert.originalConfirm}}}(window.dalert=window.dalert||{},jQuery);