function dialog(msg){
  var $wrap = $('<div class="alertWrap"></div>');
  var $content = $('<div class="alert"></div>');
  $content.html(msg);
  $wrap.append($content);
  $("body").append($wrap);

  var windowHeight = $(window).height();
  var pt = (windowHeight - 100)/2;

  $wrap.css("padding-top",pt).fadeIn(800);

  $wrap.click(function(){
    $wrap.fadeOut(500).remove();
  })

  setTimeout(function(){
    $wrap.fadeOut(1500).remove();
  },1000);
}

var loadImg = $('<div id="loadingWrap" class="loadWrap" style = "display:block"><div class="loadingImg" style = "margin-top:50%;"><img src="http://7xkv8r.com1.z0.glb.clouddn.com/pc/loadData.gif"/></div></div>');

function findElementByProperty(arr, k, v){
  if (isEmpty(v) || v == 0) {
    return null;
  }
  if(arr == null || arr.length == 0){
    return null;
  }
  for (var j = 0; j < arr.length; j++) {
    var obj = arr[j];
    if (obj[k] == v) {
      return obj;
    }
  }
  return null;
}

function removeElementByProperty(arr, k, v){
  if(arr == null || arr.length == 0){
    return;
  }
  for (var j = 0; j < arr.length; j++) {
    var obj = arr[j];
    if (obj[k] == v) {
      arr.splice(j, 1);
      return;
    }
  }
}

 String.prototype.endWith=function(str){
  if(str==null||str==""||this.length==0||str.length>this.length)
     return false;
  if(this.substring(this.length-str.length)==str)
     return true;
  else
     return false;
  return true;
 }
 String.prototype.startWith=function(str){
  if(str==null||str==""||this.length==0||str.length>this.length)
   return false;
  if(this.substr(0,str.length)==str)
     return true;
  else
     return false;
  return true;
 }

//加载时弹出框效果
$.ajaxSetup({
  timeout: 20000,
  error : function(xhr, textStatus, error) {
    var err_code = xhr.status;
    switch (err_code) {
    case 404:
      dialog('请求路径没有找到.');
      break;
    case 500:
      dialog('服务器发生错误, 请稍后再试.');
      break;
    case 405:
      dialog('错误的请求方式.');
      break;
    case 403: // 需要重新授权
    	window.location.href = xhr.responseText;
    	break;
    default:
      dialog('请求发生错误.');
      break;
    }
  },
  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  beforeSend : function(){
    $("body").append(loadImg);//发起请求前显示加载中...
  },
  complete : function(){
    loadImg.remove();//请求完毕后将加载效果移除
  },
  cache : false,
  headers : {
    'isAjax' : "1"
  }
});


function isEmpty(str) {
  if (str == null || typeof(str) == "undefined" || str.toString().trim() == '') {
    return true;
  }
  return false;
}

function getCurDate(){
	var now = new Date();
	var year = now.getFullYear(); 
	var month = now.getMonth() + 1;
	var day = now.getDate();
	if (day < 10) {
		day = "0" + day;
	}
	return year + "/" + month + "/" + day;
}

function getNowFormatDate() {
  var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();          //秒

    var clock = year + "-";

    if(month < 10)
        clock += "0";

    clock += month + "-";

    if(day < 10)
        clock += "0";

    clock += day + " ";

    if(hh < 10)
        clock += "0";

    clock += hh + ":";


    if (mm < 10)
      clock += '0';

    clock += mm + ":";

    if (ss < 10)
      clock += '0';

    clock += ss;
    return clock;
}

function show_dialog(msg) {
  dialog(msg);
}

function validate_phone(phone) {
  if(!phone || !$.trim(phone)) {
    show_dialog('手机号不能为空');
    return false;
  }
  var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
  if (!reg.test(phone)) {
	  dialog(" 请输入正确的手机号码 ");
      return false;
  }
  return true;
}

function send_vc(obj, phone, business){
  var o_e = $(obj).attr('onclick');
  $(obj).removeAttr('onclick');
  $.post(baseUrl + 'wechat/common/vc?phone=' + phone + "&business=" + business, function(resp){
    resp = eval(resp);
    if(resp.code == 0) {
      var c = 3 * 60;
      var o_c = $(obj).html();
      var i = setInterval(function(){
	      if(c == 0) {
			$(obj).html(o_c);
			$(obj).attr('onclick', o_e);
			window.clearInterval(i);
			return;
	      }
	      $(obj).html(c + '秒后重发');
	      c = c - 1;
      }, 1000);
    } else {
      show_dialog("手机号填写错误");
      $(obj).attr('onclick', o_e);
    }
  });

}
