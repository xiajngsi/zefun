function dialog(msg){
  jQuery("#alertToast").html(msg);
  jQuery("#alertWrap").fadeIn();
  setTimeout(function(){
	  jQuery("#alertWrap").fadeOut(500);
  }, 1000);
}

//加载时弹出框效果
jQuery.ajaxSetup({
	timeout: 60000,
	error : function(xhr, textStatus, error) {
		var err_code = xhr.status;
		switch (err_code) {
		case 404:
			dialog('对不起，系统未找到您访问的地址');
			break;
		case 500:
			dialog('出错啦，刷新页面再试试看');
			break;
		case 405:
			dialog('错误的请求方式.');
			setTimeout(function(){
				window.location.href = baseUrl;
			}, 500)
			break;
		case 403:
			dialog('操作权限不足.');
			setTimeout(function(){
				window.location.href = baseUrl;
			}, 500)
			break;
		case 999:
			dialog('会话失效，请重新登录');
			setTimeout(function(){
				window.location.href = baseUrl;
			}, 500)
			break;
		default:
			dialog(xhr.responseText);
			break;
		}
	},
	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	beforeSend : function(){
//		if (!(document.cookie || navigator.cookieEnabled)) {
//			dialog('您的浏览器关闭了cookie功能, 这样可能会影响您在本站的体验.');
//		}
		jQuery("#loadingWrap").fadeIn();//发起请求前显示加载中...
	},
	complete : function(){
		jQuery("#loadingWrap").fadeOut();//请求完毕后将加载效果移除
	},
	// crossDomain: true,
	cache : false,
	headers : {
		'isAjax' : "1"
	}
});


var audio = null;
function textToVoice(per, text){
	jQuery.ajax({
	    url : baseUrl + "qiniu/textToVoice",
	    type : "POST",
	    data : "text=" + text + "&per=" + per,
	    beforeSend : function(){
	    },
	    complete : function(){
	    },
	    success : function(e){
	    	if(e.code != 0){
	    		//dialog("出了点小问题，要不重试一次?");
	    		return;
	    	}
	    	if(audio != null) audio.pause();
	    	
            audio = new Audio();
            audio.src = picUrl + e.msg;
            audio.play();
	    }
	});
}