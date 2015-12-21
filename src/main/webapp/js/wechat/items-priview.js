/*保存*/
jQuery(document).ready(function(){
	jQuery("#baocun").on("click",function(){
		//表单提交，记得做校验
		 var ok = checkInput();
		 if(ok == false){
			 return;
		 }
  		 var data = serializeData();
  		 data = data + "&itemNum=" + itemNum;
  		 console.log(data);
  			jQuery.ajax({
  				type : "post",
  				url : baseUrl + "uploadnews",
  				data : data,
  				dataType : "json",
  				success : function(e){
  					if(e.code == 0){
  						window.location.href=baseUrl+"wechat/items/manage"; 
  					}else{
  						dialog("出错了,请稍后重试");
  					}
  					
  				}
  			});
	}); 
});
jQuery(document).ready(function(){
    	  //保存预览
    	  jQuery("#baocunyulan").on("click",function(){
    		  	var ok = checkInput();
    			 if(ok == false){
    				 return;
    			 }
    		  	var data = serializeData();
    	  		data = data + "&itemNum=" + itemNum;
    			jQuery.ajax({
    				type : "post",
    				url : baseUrl + "uploadnews",
    				data : data,
    				dataType : "json",
    				success : function(e){
    					if(e.code == 0){
    						for (var int = 0; int < 8; int++) {
    							jQuery("#item_yl"+int).hide();
    							jQuery("#ytitile"+int).text("");
    							jQuery("#yimg"+int).attr("src","");
    						}
    						setUrl(e.msg);
    						for (var i = 0; i < e.msg.length; i++) {
    							var array_element = e.msg[i];
    							var num = i + 1;
    								jQuery("#item_yl"+num).show();
    								jQuery("#ytitile"+num).text(array_element.title);
    								jQuery("#yimg"+num).attr("src",array_element.qiniuImg);
    						}
    						jQuery("#viewShow").show();
    						jQuery("#img-content").hide();
    						jQuery("#win").attr("class","mobile_preview_bd");
    						jQuery("#weixin-preview").modal("show");
    						//退出预览要跳转至图文消息展示页面
    						jQuery("#viewClose").on("click",function(){
    							window.location.href=baseUrl+"wechat/items/manage";
    						});
    					}
    					dialog("提交成功,即将刷新页面...");
    				}
    			});
          }); 
    });
    function setUrl(data){
  		var array_element1 = data[0];
  		var array_element2 = data[1];
  		var array_element3 = data[2];
  		var array_element4 = data[3];
  		var array_element5 = data[4];
  		var array_element6 = data[5];
  		var array_element7 = data[6];
  		var array_element8 = data[7];
  	    jQuery("#item_yl1").on("click",function(){
  	    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
  			jQuery("#viewShow").hide();
  			jQuery("#activity-name").text(array_element1.title);
  			jQuery("#ylauthor").text(array_element1.author);
  			jQuery("#js_content").html(array_element1.content);
  			jQuery("#img-content").show();
  	    });
  	    jQuery("#item_yl2").on("click",function(){
  	    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
  			jQuery("#viewShow").hide();
  			jQuery("#activity-name").text(array_element2.title);
  			jQuery("#ylauthor").text(array_element2.author);
  			jQuery("#js_content").html(array_element2.content);
  			jQuery("#img-content").show();
  	    });
  	    jQuery("#item_yl3").on("click",function(){
  	    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
  			jQuery("#viewShow").hide();
  			jQuery("#activity-name").text(array_element3.title);
  			jQuery("#ylauthor").text(array_element3.author);
  			jQuery("#js_content").html(array_element3.content);
  			jQuery("#img-content").show();
  	    });
  	    jQuery("#item_yl4").on("click",function(){
  	    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
  			jQuery("#viewShow").hide();
  			jQuery("#activity-name").text(array_element4.title);
  			jQuery("#ylauthor").text(array_element4.author);
  			jQuery("#js_content").html(array_element4.content);
  			jQuery("#img-content").show();
  	    });
  	    jQuery("#item_yl5").on("click",function(){
  	    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
  			jQuery("#viewShow").hide();
  			jQuery("#activity-name").text(array_element5.title);
  			jQuery("#ylauthor").text(array_element5.author);
  			jQuery("#js_content").html(array_element5.content);
  			jQuery("#img-content").show();
  	    });
  	    jQuery("#item_yl6").on("click",function(){
  	    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
  			jQuery("#viewShow").hide();
  			jQuery("#activity-name").text(array_element6.title);
  			jQuery("#ylauthor").text(array_element6.author);
  			jQuery("#js_content").html(array_element6.content);
  			jQuery("#img-content").show();
  	    });
  	    jQuery("#item_yl7").on("click",function(){
  	    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
  			jQuery("#viewShow").hide();
  			jQuery("#activity-name").text(array_element7.title);
  			jQuery("#ylauthor").text(array_element7.author);
  			jQuery("#js_content").html(array_element7.content);
  			jQuery("#img-content").show();
  	    });
  	    jQuery("#item_yl8").on("click",function(){
  	    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
  			jQuery("#viewShow").hide();
  			jQuery("#activity-name").text(array_element8.title);
  			jQuery("#ylauthor").text(array_element8.author);
  			jQuery("#js_content").html(array_element8.content);
  			jQuery("#img-content").show();
  	    });
  	    //返回
  	    jQuery(".mobile_preview_hd").on("click",function(){
  	        /**将网页全屏*/
  	        jQuery(".mobile_preview_bd-other-page").attr("class","mobile_preview_bd");
  	        jQuery("#viewShow").show();
  	        jQuery("#img-content").hide();
  	    });
  	}
    
//表单验证    
function checkInput(){
  for (var i = 1; i <= itemNum; i++) {
	  if(jQuery("#title"+i).val()==""){
		  dialog("标题不可或缺");
		  return false;
	  }
	  if(jQuery("#author"+i).val()==""){
		  dialog("作者不可或缺");
		  return false;
	  }
	  if(jQuery("#imgUrl"+i).val()==""){
		  dialog("为你的图文添加一张图片吧");
		  return false;
	  }
	  if(jQuery("#Description"+i).val()==""){
		  dialog("来段描述吧");
		  return false;
	  }
	}
  //判定content的内容
  return checkTextarea();
}
function checkTextarea(){
	if(itemNum == 1){
		var c1 = UE.getEditor('editor1').getContentTxt();
		if(c1==""){
			dialog("正文可不能为空哦");
			return false;
		}
	}
	if(itemNum == 2){
		var c1 = UE.getEditor('editor1').getContentTxt();
		var c2 = UE.getEditor('editor2').getContentTxt();
		if(c1==""||c2==""){
			dialog("正文可不能为空哦");
			return false;
		}
	}
	if(itemNum == 3){
		var c1 = UE.getEditor('editor1').getContentTxt();
		var c2 = UE.getEditor('editor2').getContentTxt();
		var c3 = UE.getEditor('editor3').getContentTxt();
		if(c1==""||c2==""||c3==""){
			dialog("正文可不能为空哦");
			return false;
		}
	}
	if(itemNum == 4){
		var c1 = UE.getEditor('editor1').getContentTxt();
		var c2 = UE.getEditor('editor2').getContentTxt();
		var c3 = UE.getEditor('editor3').getContentTxt();
		var c4 = UE.getEditor('editor4').getContentTxt();
		if(c1==""||c2==""||c3==""||c4==""){
			dialog("正文可不能为空哦");
			return false;
		}
	}
	if(itemNum == 5){
		var c1 = UE.getEditor('editor1').getContentTxt();
		var c2 = UE.getEditor('editor2').getContentTxt();
		var c3 = UE.getEditor('editor3').getContentTxt();
		var c4 = UE.getEditor('editor4').getContentTxt();
		var c5 = UE.getEditor('editor5').getContentTxt();
		if(c1==""||c2==""||c3==""||c4==""||c5==""){
			dialog("正文可不能为空哦");
			return false;
		}
	}
	if(itemNum == 6){
		var c1 = UE.getEditor('editor1').getContentTxt();
		var c2 = UE.getEditor('editor2').getContentTxt();
		var c3 = UE.getEditor('editor3').getContentTxt();
		var c4 = UE.getEditor('editor4').getContentTxt();
		var c5 = UE.getEditor('editor5').getContentTxt();
		var c6 = UE.getEditor('editor6').getContentTxt();
		if(c1==""||c2==""||c3==""||c4==""||c5==""||c6==""){
			dialog("正文可不能为空哦");
			return false;
		}
	}
	if(itemNum == 7){
		var c1 = UE.getEditor('editor1').getContentTxt();
		var c2 = UE.getEditor('editor2').getContentTxt();
		var c3 = UE.getEditor('editor3').getContentTxt();
		var c4 = UE.getEditor('editor4').getContentTxt();
		var c5 = UE.getEditor('editor5').getContentTxt();
		var c6 = UE.getEditor('editor6').getContentTxt();
		var c7 = UE.getEditor('editor7').getContentTxt();
		if(c1==""||c2==""||c3==""||c4==""||c5==""||c6==""||c7==""){
			dialog("正文可不能为空哦");
			return false;
		}
	}
	if(itemNum == 8){
		var c1 = UE.getEditor('editor1').getContentTxt();
		var c2 = UE.getEditor('editor2').getContentTxt();
		var c3 = UE.getEditor('editor3').getContentTxt();
		var c4 = UE.getEditor('editor4').getContentTxt();
		var c5 = UE.getEditor('editor5').getContentTxt();
		var c6 = UE.getEditor('editor6').getContentTxt();
		var c7 = UE.getEditor('editor7').getContentTxt();
		var c8 = UE.getEditor('editor8').getContentTxt();
		if(c1==""||c2==""||c3==""||c4==""||c5==""||c6==""||c7==""||c8==""){
			dialog("正文可不能为空哦");
			return false;
		}
	}
	return true;
}
var dataList = new Array();
//封装请求数据
function serializeData(){
	//给个默认值
	var data = "data=data";
	for (var i = 0; i < jQuery("input[name='title']").length; i++) {
		if(i == jQuery("input[name='title']").length-1){
			data = data+"&title="+jQuery("input[name='title']").eq(i).val();
		}else{
			data = data+"&title="+jQuery("input[name='title']").eq(i).val()+"@!";
		}
	}
	for (var i = 0; i < jQuery("input[name='author']").length; i++) {
		if(i == jQuery("input[name='author']").length-1){
			data = data+"&author="+jQuery("input[name='author']").eq(i).val();
		}else{
			data = data+"&author="+jQuery("input[name='author']").eq(i).val()+"@!";;
		}
	}
	for (var i = 0; i < jQuery("input[name='imgUrl']").length; i++) {
		if(i == jQuery("input[name='imgUrl']").length-1){
			data = data+"&imgUrl="+jQuery("input[name='imgUrl']").eq(i).val();
		}else{
			data = data+"&imgUrl="+jQuery("input[name='imgUrl']").eq(i).val()+"@!";
		}
	}
	for (var i = 0; i < jQuery("textarea[name='Description']").length; i++) {
		if(i == jQuery("textarea[name='Description']").length-1){
			data = data+"&Description="+jQuery("textarea[name='Description']").eq(i).val();
		}else{
			data = data+"&Description="+jQuery("textarea[name='Description']").eq(i).val()+"@!";
		}
	}
	for (var i = 0; i < jQuery("input[name='content_source_url']").length; i++) {
		if(i == jQuery("input[name='content_source_url']").length-1){
			data = data+"&content_source_url="+jQuery("input[name='content_source_url']").eq(i).val();
		}else{
			data = data+"&content_source_url="+jQuery("input[name='content_source_url']").eq(i).val()+"@!";
		}
	}
	 var c1 = UE.getEditor('editor1').getContent();
	 var c2 = UE.getEditor('editor2').getContent();
	 var c3 = UE.getEditor('editor3').getContent();
	 var c4 = UE.getEditor('editor4').getContent();
	 var c5 = UE.getEditor('editor5').getContent();
	 var c6 = UE.getEditor('editor6').getContent();
	 var c7 = UE.getEditor('editor7').getContent();
	 var c8 = UE.getEditor('editor8').getContent();
	 
	 c1 = c1.replace(/%/g, "%25");  
	 c1 = c1.replace(/\&/g, "%26");  
	 c1 = c1.replace(/\+/g, "%2B"); 
	 c2 = c2.replace(/%/g, "%25");  
	 c2 = c2.replace(/\&/g, "%26");  
	 c2 = c2.replace(/\+/g, "%2B");
	 c3 = c3.replace(/%/g, "%25");  
	 c3 = c3.replace(/\&/g, "%26");  
	 c3 = c3.replace(/\+/g, "%2B");
	 c4 = c4.replace(/%/g, "%25");  
	 c4 = c4.replace(/\&/g, "%26");  
	 c4 = c4.replace(/\+/g, "%2B");
	 c5 = c5.replace(/%/g, "%25");  
	 c5 = c5.replace(/\&/g, "%26");  
	 c5 = c5.replace(/\+/g, "%2B");
	 c6 = c6.replace(/%/g, "%25");  
	 c6 = c6.replace(/\&/g, "%26");  
	 c6 = c6.replace(/\+/g, "%2B");
	 c7 = c7.replace(/%/g, "%25");  
	 c7 = c7.replace(/\&/g, "%26");  
	 c7 = c7.replace(/\+/g, "%2B");
	 c8 = c8.replace(/%/g, "%25");  
	 c8 = c8.replace(/\&/g, "%26");  
	 c8 = c8.replace(/\+/g, "%2B");
	data = data + "&content="+c1+"@!"+"&content="+c2+"@!"+"&content="+c3+"@!"+"&content="+c4+"@!"+"&content="+c5+"@!"+"&content="+c6+"@!"+"&content="+c7+"@!"+"&content="+c8+"&status="+"1";
	return data;
}

