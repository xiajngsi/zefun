<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<div class="mainwrapper">
		<!--loading start-->
		<%@ include file="/loading.jsp" %>
		<!--loading end-->
		<%@ include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>

		<div class="maincontent" style="overflow-y: auto">
		    <div class="contentinner">
		        <div class="widgetcontent">
		            <div id="tabs">
		                <ul>
		                    <li><a class="tabs" href="#tabs-1">关注自动回复</a></li>
		                    <li><a class="tabs" href="#tabs-2">消息自动回复</a></li>
		                </ul>
		                <div id="tabs-1">
		                    <!-- 已经设置过 -->
		                    <c:if test="${autoReply!=null  }">
		                    <div>
		                         <c:if test="${autoReply.msgType == 'text' }">
		                         <label>您设置的为回复文本:内容如下</label>
		                         <p>${autoReply.msgText }</p>
		                         </c:if>
		                    	 <c:if test="${autoReply.msgType == 'news' }">
		                    	 <label>您设置的为回复图文:内容如下</label>
		                         <table class="table table-bordered table-striped member-list-table">
						            <thead>
						            <tr>
						                <th>图文名称</th>
						                <th>创建时间</th>
						                <th>作者</th>
						                <th>查看详情</th>
						            </tr>
						            </thead>
						            <tbody id="init_member">
						            <tr>
						                <td>${autoReply.automaticReply.title }</td>
						                <td>${autoReply.automaticReply.createTime }</td>
						                <td>${autoReply.automaticReply.author }</td>
						                <td>
						                <button id="${autoReply.automaticReply.mediaId }" class="yl">预览</button>
						                </td>
						            </tr>
						            </tbody>
						       	  </table>
		                         </c:if>
		                         <button class="btn mt10" id="deleteFollowAuto">清除内容</button>
		                    </div>
		                    </c:if>
		                    <!-- 没有设置过 -->
		                    <c:if test="${autoReply==null  }">
		                    <div class="added-reply one">
		                        <ul>
		                            <li class="added-nav" id="word-one">
		                                <span class="iconfa-pencil project-icon"></span><span style="cursor: pointer" class="ml10">文字</span>
		                            </li>
		                            <li class="added-nav" id="img-one">
		                                <span class="icon-picture"></span><span style="cursor: pointer" class="ml10">图文</span>
		                            </li>
		                        </ul>
		                        <div class="textarea" id="textarea-one">
		                            <textarea id="text-one" style="width: 1004px;margin-top: 20px;overflow:auto;"></textarea>
		                        </div>
		
		                        <br>
		                        <div class="imgarea hide" id="imgarea-one">  
		                          
		                          <table class="table table-bordered table-striped member-list-table">
						            <thead>
						            <tr>
						                <th>选择</th>
						                <th>图文名称</th>
						                <th>创建时间</th>
						                <th>作者</th>
						                <th>查看详情</th>
						            </tr>
						            </thead>
						            <tbody id="init_member">
						            <c:forEach items="${automaticReplys }" var="automaticReply">
						            <tr>
						                <td>
						                <input type="radio" name="media_id" value="${automaticReply.mediaId }">
						                </td>
						                <td>${automaticReply.title }</td>
						                <td>${automaticReply.createTime }</td>
						                <td>${automaticReply.author }</td>
						                <td>
						                <button id="${automaticReply.mediaId }" class="yl">预览</button>
						                </td>
						            </tr>
						            </c:forEach>
						            </tbody>
						       	  </table>
		                          
		                          
		                      </div><!--imgarea-->
		
		                        <div class="opr one">
		                            <button class="btn" id="follow">保存</button>
		                        </div><!--opr-->
		                    </div>
		                    </c:if>
		                </div><!--tabs-1-->
		                <div id="tabs-2">
		                    <!-- 已经设置过 -->
		                    <c:if test="${textReply!=null  }">
		                    <div>
		                         <c:if test="${textReply.msgType == 'text' }">
		                         <label>您设置的为回复文本:内容如下</label>
		                         <p>${textReply.msgText }</p>
		                         </c:if>
		                    	 <c:if test="${textReply.msgType == 'news' }">
		                    	 <label>您设置的为回复图文:内容如下</label>
		                         <table class="table table-bordered table-striped member-list-table">
						            <thead>
						            <tr>
						                <th>图文名称</th>
						                <th>创建时间</th>
						                <th>作者</th>
						                <th>查看详情</th>
						            </tr>
						            </thead>
						            <tbody id="init_member">
						            <tr>
						                <td>${textReply.automaticReply.title }</td>
						                <td>${textReply.automaticReply.createTime }</td>
						                <td>${textReply.automaticReply.author }</td>
						                <td>
						                <button id="${textReply.automaticReply.mediaId }" class="yl">预览</button>
						                </td>
						            </tr>
						            </tbody>
						       	  </table>
		                         </c:if>
		                         <button class="btn mt10" id="deleteTextAuto">清除内容</button>
		                    </div>
		                    </c:if>
		                    <!-- 没有设置过 -->
		                    <c:if test="${textReply==null  }">
		                    <div class="added-reply two">
		                        <ul>
		                            <li class="added-nav" id="word-two">
		                                <span class="iconfa-pencil project-icon"></span><span style="cursor: pointer" class="ml10">文字</span>
		                            </li>
		                            <li class="added-nav" id="img-two">
		                                <span class="icon-picture"></span><span style="cursor: pointer" class="ml10">图文</span>
		                            </li>
		                        </ul>
		                        <div class="textarea" id="textarea-two">
		                            <textarea style="width: 1004px;margin-top: 20px;overflow:auto;"></textarea>
		                        </div>
		
		                        <div class="imgarea hide" id="imgarea-two">  
		                        <br>
		                          
		                          <table class="table table-bordered table-striped member-list-table">
						            <thead>
						            <tr>
						                <th>选择</th>
						                <th>图文名称</th>
						                <th>创建时间</th>
						                <th>作者</th>
						                <th>查看详情</th>
						            </tr>
						            </thead>
						            <tbody id="init_member">
						            <c:forEach items="${automaticReplys }" var="automaticReply">
						            <tr>
						                <td>
						                <input type="radio" name="media_id" value="${automaticReply.mediaId }">
						                </td>
						                <td>${automaticReply.title }</td>
						                <td>${automaticReply.createTime }</td>
						                <td>${automaticReply.author }</td>
						                <td>
						                <button id="${automaticReply.mediaId }" class="yl">预览</button>
						                </td>
						            </tr>
						            </c:forEach>
						            </tbody>
						       	  </table>
		                          
		                          
		                      </div><!--imgarea-->
		
		                        <div class="opr two">
		                            <button class="btn" id="message_reply">保存</button>
		                        </div><!--opr-->
		                    </div>
		                    </c:if>
		                </div>
		            </div><!--tabs-->
		            <div>
		                <!--解释-->
		                <br>
		                <span>**关注自动回复:当用户关注该公众号时,将自动回复该设置消息**</span><br>
		                <span>**事件自动回复:当用户向该公众号发送消息时,将自动回复该设置消息**</span>
		            </div>
		        </div><!--widgetcontent-->
		    </div>
		</div>
		<!--关注自动回复图文消息-->
   		<div class="clearfix"></div>
		<div id="star"></div>
		</div>
	</div>

<!--微信预览模态框-->
<div class="modal hide weixin-preview-modal" id="weixin-preview" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content weixin-preview">
            <div class="mobile_preview" id="mobileDiv" style="display: block;">
                <div class="mobile_preview_hd">
                    <strong class="nickname">智放</strong>
                </div>
                <div class="mobile_preview_bd" id="win">
                    <ul id="viewShow" class="show_list">
                        <li>
                            <div class="img-link" id="item_yl1">
                                <a href="javascript:void(0)">
                                    <img style="width: 218px;height: 135.9px;margin-bottom: 5px;" id="yimg1" src="" alt="微信测试"/>
                                    <div  class="word" id="ytitile1" style="width: 218px">
                                       			 太酷了！文艺程序员给女票做了个智能小风扇...
                                    </div>
                                </a>

                            </div>
                            <div class="word-link hide" id="item_yl2">
                                <a href="javascript:void(0)">
                                    <div class="word">
                                        <p id="ytitile2">轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere</p>
                                    </div>
                                    <div class="img" style="width: 50px; height: 50px;margin-bottom: 5px;">
                                        <img id="yimg2" src="" alt=""/>
                                    </div>
                                </a>
                            </div>
                            <div class="word-link hide" id="item_yl3">
                                <a href="javascript:void(0)">
                                    <div class="word">
                                        <p id="ytitile3">轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere</p>
                                    </div>
                                    <div class="img" style="width: 50px; height: 50px;margin-bottom: 5px;">
                                        <img id="yimg3" src="" alt=""/>
                                    </div>
                                </a>
                            </div>
                            <div class="word-link hide" id="item_yl4">
                                <a href="javascript:void(0)">
                                    <div class="word">
                                        <p id="ytitile4">轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere</p>
                                    </div>
                                    <div class="img" style="width: 50px; height: 50px;margin-bottom: 5px;">
                                        <img id="yimg4" src="" alt=""/>
                                    </div>
                                </a>
                            </div>
                            <div class="word-link hide" id="item_yl5">
                                <a href="javascript:void(0)">
                                    <div class="word" >
                                        <p id="ytitile5">轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere</p>
                                    </div>
                                    <div class="img" style="width: 50px; height: 50px;margin-bottom: 5px;">
                                        <img id="yimg5" src="" alt=""/>
                                    </div>
                                </a>
                            </div>
                            <div class="word-link hide" id="item_yl6">
                                <a href="javascript:void(0)">
                                    <div class="word">
                                        <p id="ytitile6">轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere</p>
                                    </div>
                                    <div class="img" style="width: 50px; height: 50px;margin-bottom: 5px;">
                                        <img id="yimg6" src="" alt=""/>
                                    </div>
                                </a>
                            </div>
                            <div class="word-link hide" id="item_yl7">
                                <a href="javascript:void(0)">
                                    <div class="word">
                                        <p id="ytitile7">轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere</p>
                                    </div>
                                    <div class="img" style="width: 50px; height: 50px;margin-bottom: 5px;">
                                        <img id="yimg7" src="" alt=""/>
                                    </div>
                                </a>
                            </div>
                            <div class="word-link hide" id="item_yl8">
                                <a href="javascript:void(0)">
                                    <div class="word">
                                        <p id="ytitile8">轻松玩转后期剪辑Premiere轻松玩转后期剪辑Premiere</p>
                                    </div>
                                    <div class="img" style="width: 50px; height: 50px;margin-bottom: 5px;">
                                        <img id="yimg8" src="" alt=""/>
                                    </div>
                                </a>
                            </div>
                        </li>
                    </ul>
                    <!--点击图片后进入content-->
                    <div id="img-content" class="rich_media_area_primary hide" style="">
                        <h2 class="rich_media_title" id="activity-name">
                            正规测试用例
                        </h2>
                        <hr/>
                        <div class="rich_media_meta_list">
                            <span id="post-date" class="rich_media_meta rich_media_meta_text">2015-08-17</span>

                            <span class="rich_media_meta rich_media_meta_link rich_media_meta_nickname" id="ylauthor">高国藩</span>
                            <a class="rich_media_meta rich_media_meta_link rich_media_meta_nickname" href="javascript:void(0);" id="post-user">智放公众号</a>
                        </div>
                        <div class="rich_media_content" id="js_content">
                            <!--存放内容content-->

                        </div>

                        <div class="rich_media_tool" id="js_toobar2">
                            <div id="js_read_area2" style="float: left" class="media_tool_meta tips_global meta_primary">阅读&nbsp;<span id="readNum2">100</span></div>
                            <a id="js_report_article2" style="float: right"  class="media_tool_meta tips_global meta_extra" href="javascript:void(0);">举报</a>
                        </div>
                    </div>
                    <!--点击图片后进入content-->
                </div>
                <div class="mobile_preview_ft">
                    <ul class="pre_menu_list grid_line" id="viewList">
                        
                    </ul>
                </div>
                <a href="javascript:void(0);" class="mobile_preview_closed btn btn_default" id="viewClose" data-dismiss="modal">退出预览</a>
            </div>
        </div>
    </div>
</div>
<!-- 预览模态框 -->

</body>
<script>
    //关注自动回复信息设置
    var auto_type = "text";
    jQuery(function(){
        jQuery("#word-one").on("click",function(){
        jQuery("#imgarea-one").hide();
        jQuery("#textarea-one").show();
        auto_type = "text";
    });
    jQuery("#img-one").on("click",function(){
        jQuery("#textarea-one").hide();
        jQuery("#imgarea-one").show();
        auto_type = "item";
    	});
    });
    jQuery("#follow").one("click",function(){
    	var text_one = jQuery("#textarea-one").children("textarea").val();
    	var mediaId_one = jQuery("#imgarea-one").find("input:radio:checked").val();
    	if(auto_type == "item"){
    		followAuto(mediaId_one);
    	}
    	if(auto_type == "text"){
    		followAuto(text_one);
    	}
    });
	//消息自动回复信息设置
	var msg_type = "text";
    jQuery(function(){
        jQuery("#word-two").on("click",function(){
            jQuery("#imgarea-two").hide();
            jQuery("#textarea-two").show();
            msg_type = "text";
        });
        jQuery("#img-two").on("click",function(){
            jQuery("#textarea-two").hide();
            jQuery("#imgarea-two").show();
            msg_type = "item";
        });
    });
    jQuery("#message_reply").one("click",function(){
    	var text_two = jQuery("#textarea-two").children("textarea").val();
    	var mediaId_two = jQuery("#imgarea-two").find("input:radio:checked").val();
    	if(msg_type == "item"){
    		textAuto(mediaId_two);
    	}
    	if(msg_type == "text"){
    		textAuto(text_two);
    	}
    });
    //关注回复
    function followAuto(message){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/set/follow/repy",
			data : "autoType="+auto_type+"&message="+message,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					location.reload(true);
				}
			}
		});
    }
    //消息回复
    function textAuto(message){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/set/text/repy",
			data : "autoType="+msg_type+"&message="+message,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					location.reload(true);
				}
			}
		});
    }
    /**删除设置*/
    jQuery("#deleteFollowAuto").on("click",function(){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/delete/auto/repy",
			data : "msgStatus=1",
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					location.reload(true);
				}
			}
		});
    });
    jQuery("#deleteTextAuto").on("click",function(){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/delete/auto/repy",
			data : "msgStatus=2",
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					location.reload(true);
				}
			}
		});
    });
    /**预览*/
    jQuery( ".widgetcontent" ).delegate( ".yl", "click", function() {
    	var mediaId = jQuery(this).attr("id");
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
</script>
</html>