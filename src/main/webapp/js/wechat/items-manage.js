jQuery(function() {
	/*预览*/
	jQuery(".appmsg_opr_item.grid_item.size1of2.no_extra.yulan").on("click",function(){
		var mediaId = jQuery(this).parents(".imgword-item").children("input[name='yulan']").val();
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/get/item/by/mediaId",
			data : "mediaId="+mediaId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					for (var int = 0; int < 8; int++) {
						jQuery("#item_yl"+int).hide();
						jQuery("#ytitile"+int).text("");
						jQuery("#yimg"+int).attr("src","");
					}
					setUrl(e.msg);
					/**显示图文消息*/
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
				}
			}
		});
	});
});

/**将数据村放入全局中*/
var array_element1 = null;
var array_element2 = null;
var array_element3 = null;
var array_element4 = null;
var array_element5 = null;
var array_element6 = null;
var array_element7 = null;
var array_element8 = null;
/**将数据村放入全局中*/
function setUrl(data){
	 array_element1 = data[0];
	 array_element2 = data[1];
	 array_element3 = data[2];
	 array_element4 = data[3];
	 array_element5 = data[4];
	 array_element6 = data[5];
	 array_element7 = data[6];
	 array_element8 = data[7];
    jQuery("#item_yl1").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element1.title);
		jQuery("#ylauthor").text(array_element1.author);
		jQuery("#js_content").html(array_element1.content);
		jQuery("#img-content").show();
    });
    jQuery("#item_yl2").on("click",function(){
    	console.log("item_yl2");
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
//我的图文库进行搜索图文消息
jQuery("#serchItemsForMyself").on("click",function(){
	var content = jQuery("#myselfItems").val();
	if(content == ""){
		window.location.reload();
	}
	console.log(content.indexOf("_"));
	if(content.indexOf("_")>=0||content.indexOf("&")>=0){
		dialog("请不要输入特殊字符");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "wechat/serch/items",
		data : "content="+content,
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				jQuery("#mis").empty();
				for ( var s = 0; s < e.msg.length; s++) {
					var div = jQuery("<div class='imgword-item'></div>");
					var input = jQuery("<input type='hidden' name='yulan' value='"+e.msg[s].mediaId+"'>");
					div.append(input);
					
                    var innerDiv1 = jQuery("<div class='imgword-top'></div>");
                    var h4 = jQuery("<h4 class='imgword-title'></h4>");
                    var a = jQuery("<a href=''>"+e.msg[s].title+"</a>");
                    h4.append(a);
                    innerDiv1.append(h4);
                    var infoDiv = jQuery("<div class='imgword-info'></div>");
                    var em = jQuery("<em class='imgword-data'>"+e.msg[s].createTime+"</em>");
                    infoDiv.append(em);
                    innerDiv1.append(infoDiv);
                    var wrapDiv = jQuery("<div class='img-wrap'></div>");
                    var img = jQuery("<img src='"+e.msg[s].qiniuImg+"' alt=''/>");
                    wrapDiv.append(img);
                    innerDiv1.append(wrapDiv);
                    var p = jQuery("<p class='imgword-desc'>"+e.msg[s].description+"</p>");
                    innerDiv1.append(p);
                    div.append(innerDiv1);
                    
                     var innerDiv2 = jQuery("<div class='imgword-opr'></div>");
                     var ul = jQuery("<ul></ul>");
                     var li1 = jQuery("<li class='appmsg_opr_item grid_item size1of2' ></li>");
                     var a1 = jQuery("<a class='js_tooltip' href='"+baseUrl+"wechat/send/update/item?mediaId="+e.msg[s].mediaId+"' data-tooltip='编辑'></a>");
                     var i1 = jQuery("<i title='编辑' id='"+e.msg[s].replyId+"' class='iconfa-pencil project-icon'></i>");
                     a1.append(i1);
                     li1.append(a1);
                     ul.append(li1);
                     var li2 = jQuery("<li class='appmsg_opr_item grid_item size1of2 no_extra delete' onclick='deleteItems('"+e.msg[s].mediaId+"',this)'></li>");
                     var a2 = jQuery("<a class='js_del no_extra js_tooltip' data-id='208999880' href='javascript:void(0);' data-tooltip='删除'></a>");
                     var i2 = jQuery("<i title='删除' class='iconfa-trash project-icon'></i>");
                     a2.append(i2);
                     li2.append(a2);
                     ul.append(li2);
                     innerDiv2.append(ul);
                     div.append(innerDiv2);
                     
                     jQuery("#mis").append(div);
                     
				}
			}
		}
	});
});
//智放图文库中搜索图文消息
  
jQuery("#serchZhifangItems").on("click",function(){
	var content = jQuery("#zhifangItems").val();
	if(content == ""){
		return ;
	}
	if(content.indexOf("_")>=0||content.indexOf("&")>=0){
		dialog("请不要输入特殊字符");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "wechat/serch/items",
		data : "content="+content+"&storeId=0",
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				jQuery("#zmis").empty();
				for ( var s = 0; s < e.msg.length; s++) {
					var div = jQuery("<div class='imgword-item'></div>");
					var input = jQuery("<input type='hidden' name='yulan' value='"+e.msg[s].mediaId+"'>");
					div.append(input);
					
                    var innerDiv1 = jQuery("<div class='imgword-top'></div>");
                    var h4 = jQuery("<h4 class='imgword-title'></h4>");
                    var a = jQuery("<a href=''>"+e.msg[s].title+"</a>");
                    h4.append(a);
                    innerDiv1.append(h4);
                    var infoDiv = jQuery("<div class='imgword-info'></div>");
                    var em = jQuery("<em class='imgword-data'>"+e.msg[s].createTime+"</em>");
                    infoDiv.append(em);
                    innerDiv1.append(infoDiv);
                    var wrapDiv = jQuery("<div class='img-wrap'></div>");
                    var img = jQuery("<img src='"+e.msg[s].qiniuImg+"' alt=''/>");
                    wrapDiv.append(img);
                    innerDiv1.append(wrapDiv);
                    var p = jQuery("<p class='imgword-desc'>"+e.msg[s].description+"</p>");
                    innerDiv1.append(p);
                    div.append(innerDiv1);
                    
                     var innerDiv2 = jQuery("<div class='imgword-opr'></div>");
                     var ul = jQuery("<ul></ul>");
                     var li1 =jQuery("<li class='appmsg_opr_item grid_item size1of2 no_extra delete' onclick='deleteItems('"+e.msg[s].mediaId+"',this)'></li>");
                     var a1 = jQuery("<a class='js_del no_extra js_tooltip' data-id='208999880' href='javascript:void(0);' data-tooltip='删除'></a>");
                     var i1 = jQuery("<i title='删除' class='iconfa-trash project-icon'></i>");
                     a1.append(i1);
                     li1.append(a1);
                     ul.append(li1);
                     var li2 = jQuery("<li title='下载' class='appmsg_opr_item grid_item size1of2 no_extra delete' onclick='downloadItems('${items.mediaId}',this)'></li>");
                     var a2 = jQuery("<a class='js_del no_extra js_tooltip' data-id='208999880' href='javascript:void(0);' data-tooltip='删除'></a>");
                     var i2 = jQuery("<i title='下载' class='iconfont icon-icon46'></i>");
                     a2.append(i2);
                     li2.append(a2);
                     ul.append(li2);
                     innerDiv2.append(ul);
                     div.append(innerDiv2);
                     
                     jQuery("#zmis").append(div);
                     
				}
			}
		}
	});
});