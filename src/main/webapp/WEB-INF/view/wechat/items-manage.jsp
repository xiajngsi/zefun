<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
<div class="mainwrapper" style="background-position: 0px 0px;">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
      			<!-- 页面内容开始 -->
		<div class="maincontent" style="overflow-y: auto">
		    <div class="contentinner">
		        <div class="widgetcontent">
		            <div id="tabs">
		                <ul>
		                    <li><a href="#tabs-1">我的图文库</a></li>
		                    <li><a href="#tabs-2">智放图文库</a></li>
		                    <li><a href="#tabs-3">图片库</a></li>
		                </ul>
		                
		                <div id="tabs-1">
		                    <div class="search-bar">
		                        <input id="myselfItems" type="text" placeholder="标题/作者/摘要"/>
		                        <button id="serchItemsForMyself" class="btn">搜索</button>
		                    </div>
		
		                    <div class="sub-title-bar">
		                        <h6>图文消息(共 <span>${fn:length(slefItems)}</span>个)
		                        <%-- <a href="<%=basePath %>artic/manager" class="fr btn">新建图文</a>
		                        <a href="<%=basePath %>wechat/send/items" class="fr btn mr10">发送图文</a> --%>
		                        </h6>
		                        
		                    </div>
		
		                    <div class="imgword-list" id="mis">
		                    <c:forEach items="${slefItems}" var="slefItem">
			                    <div class="imgword-item">
			                    <input type="hidden" name="yulan" value="${slefItem.mediaId}">
		                            <div class="imgword-top">
		                                <h4 class="imgword-title">
		                                    <a href="">${slefItem.title }</a>    
		                                </h4>
		                                <div class="imgword-info">
		                                    <em class="imgword-data">发布时间:${slefItem.createTime }</em>
		                                </div>
		                                <div class="img-wrap">
		                                    <img src="${slefItem.qiniuImg}" alt=""/>
		                                </div>
		                                <p class="imgword-desc">${fn:substring(slefItem.description,0,14)} ...</p>
		                            </div>
		                            <div class="imgword-opr">
		                                  <ul>
		                                      <li class="appmsg_opr_item grid_item size1of2" >
		                                          <a class="js_tooltip" href="<%=basePath%>wechat/send/update/item?mediaId=${slefItem.mediaId}" data-tooltip="编辑">&nbsp;<i title="编辑" id="${slefItem.replyId}" class="iconfa-pencil project-icon"></i></a>
		                                      </li>
		                                      <li class="appmsg_opr_item grid_item size1of2 no_extra delete" onclick="deleteItems('${slefItem.mediaId}',this)">
		                                          <a class="js_del no_extra js_tooltip" data-id="208999880" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i title="删除" class="iconfa-trash project-icon"> </i></a>
		                                      </li>
		                                  </ul>
	                              </div>
		                        </div>
		                    </c:forEach>
		                   </div><!--imgword-list-->
		
		                </div>
		                <!-- 智放图文库 -->
		                <div id="tabs-2">
		                    <div class="search-bar">
		                        <input id="zhifangItems" type="text" placeholder="标题/作者/摘要"/>
		                        <button id="serchZhifangItems" class="btn">搜索</button>
		                    </div>
		                    <div class="sub-title-bar">
		                        <h6>图文消息(共 <span>${fn:length(items)}</span>个)
		                        </h6>
		                    </div>
		
		                    <div class="imgword-list" id="zmis">
		                    <c:forEach items="${items}" var="items">
			                    <div class="imgword-item">
			                    <input type="hidden" name="yulan" value="${items.mediaId}">
		                            <div class="imgword-top">
		                                <h4 class="imgword-title">
		                                    <a href="">${items.title }</a>    
		                                </h4>
		                                <div class="imgword-info">
		                                    <em class="imgword-data">发布时间:${items.createTime }</em>
		                                </div>
		                                <div class="img-wrap">
		                                    <img src="${items.qiniuImg}" alt=""/>
		                                </div>
		                                <p class="imgword-desc">${fn:substring(items.description,0,14)} ...</p>
		                            </div>
		                            <div class="imgword-opr">
		                            <ul>
						                    <li class="appmsg_opr_item grid_item " style="width: 100px;">
						                        <a class="js_tooltip" onclick="downloadItems('${items.mediaId}',this)">&nbsp;
							                        <i title="下载" id="61" class="iconfont icon-icon46" style="font-size: 12px;">
							                        </i>
							                        <c:if test="${items.downloads == null}">
							                        	<span>0</span>
							                        </c:if>
							                        <c:if test="${items.downloads != null}">
							                        	<span>${items.downloads}</span>
							                        </c:if>
						                        </a>
						                    </li>
						                    <li class="appmsg_opr_item grid_item  no_extra delete" onclick="pariseItems('${items.mediaId}',this)" style="width: 100px;">
						                        <a class="js_del no_extra js_tooltip" data-id="208999880" href="javascript:void(0);" data-tooltip="点赞" title="点赞">
						                            &nbsp;<i title="点赞" class="iconfont icon-zan1"> </i>
						                            <c:if test="${items.praise == null}">
							                        	<span>0</span>
							                        </c:if>
							                        <c:if test="${items.praise != null}">
							                        	<span>${items.praise}</span>
							                        </c:if>
						                        </a>
						                    </li>
						                    <li class="appmsg_opr_item grid_item  no_extra delete" onclick="deleteItems('mAgiqgI8pIrcRSo4HygPq4z98OlF_J9FQF_2i5T5k58',this)" style="width: 100px;">
						                        <a class="js_del no_extra js_tooltip" data-id="208999880" href="javascript:void(0);" data-tooltip="热度" title="图文热度">
						                            &nbsp;<i title="热度" class="iconfont icon-remenhuodong2" style="font-size: 12px;"></i>
						                            <c:if test="${items.mediaCount == null}">
							                        	<span>0</span>
							                        </c:if>
							                        <c:if test="${items.mediaCount != null}">
							                        	<span>${items.mediaCount}</span>
							                        </c:if>
						                        </a>
						                    </li>
						             </ul>
	                              </div>
		                        </div>
		                    </c:forEach>
		                   </div><!--imgword-list-->
		                </div>
<!-- 智放图文库 -->		                
		                <div id="tabs-3">
		                    <div id="custom-toolbar" >
		                        <div class="table-toolbar">
		                            <button id="pickfiles" class="btn">上传图片</button>
		                        </div>
		                    </div>
		                    <div class="widgetcontent">
		                        <div class="photo-list"  id="container" style="border:1px solid #ddd;border-top:0px;">
		                        <c:forEach items="${pictures }" var="picture">
		                        <div class="photo-item">
		                            <input type="hidden" name="pictureId" value="${picture.pictureId }">
	                                <div class="photo-content" >
	                                    <img src="${picture.pictureQiniu }" />
	                                    <!-- <div class="check-content">
	                                        <input type="checkbox"/><span></span>
	                                    </div> -->
	                                </div>
	                                <div class="photo-opr">
	                                    <ul>
	                                        <li style="width: 100%;">
	                                              <span  class="opr-span">
	                                                  <a href="javascript:void(0)">
	                                                      <span class="iconfa-trash project-icon"></span>
	                                                  </a>
	                                              </span>
	                                        </li>
	                                    </ul>
	                                </div>
	                            </div><!--photo-item-->
		                        </c:forEach>
		                        </div>
		                    </div>
		
		                </div>
		            </div>
		        </div>
		    
		    </div>
		<!-- 页面内容开始 -->
		</div>
	</div>

    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<!-- 预览模态框 -->
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
</body>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/wechat/items-manage.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/plupload.full.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<script type="text/javascript">
var qiniu = new QiniuJsSDK();
qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'pickfiles',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
	max_retries : 3, //上传失败最大重试次数
	dragdrop : true, //开启可拖曳上传
	drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
	chunk_size : '2mb', //分块上传时，每片的体积
	auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
	init : {
		'FilesAdded' : function(up, files) {
			console.log("FilesAdded invoked ...");
			var err = false;
			plupload.each(files, function(file) {
				if(!qiniu.isImage(file.name)){
					err = true;
					return false;
				}
			});
			if(err) {
				dialog("只能上传图片文件");
				up.removeFile(files[0]);
				up.stop();
				jQuery('#mask').remove();
			}
		},
		'BeforeUpload' : function(up, file) {
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery('#mask').remove();
			console.log(sourceLink);
			//将七牛的地址传递个微信,然后将该信息全部录入数据库中
			jQuery.ajax({
				type : "post",
				url : baseUrl + "wechat/update/img",
				data : "imgUrl="+sourceLink,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						initNodes(e.msg);
					}
				}
			});
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			dialog("UploadComplete");
		},
		'Key' : function(up, file) {
			var key = "zefun/wechat/" + new Date().getTime();
			return key;
		}
	}
});

/**新增元素,将新增的图片显示在页面上*/
function initNodes (data){
	var photo_item = jQuery("<div class='photo-item'></div>");
	var photo_content = jQuery("<div class='photo-content'></div>");
	var img = jQuery("<img src='"+data.pictureQiniu+"' />");
	photo_content.append(img); 
	var photo_opr = jQuery("<div class='photo-opr'></div>");
	var ul = jQuery("<ul></ul>");
	var li_two = jQuery("<li style='width: 100%;'></li>");
	var s2 = jQuery("<span class='opr-span'><a href='javascript:void(0)'><span class='iconfa-trash project-icon'></span></a></span>");
	li_two.append(s2);
	ul.append(li_two);
	photo_opr.append(ul);
	var pictureId = jQuery("<input type='hidden' name='pictureId' value='"+data.pictureId+"'>");
	photo_item.append(pictureId);
	photo_item.append(photo_content);
	photo_item.append(photo_opr);
	console.log(photo_item.html());
	jQuery("#container").append(photo_item);
}
/**绑定删除图片事件*/
jQuery(function() {
	jQuery("#container").delegate(".iconfa-trash.project-icon","click",function (event){
		var doc = jQuery(this).parents(".photo-item");
		var pictureId = jQuery(this).parents(".photo-item").children("input[name='pictureId']").val();
		console.log(pictureId);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/delete/img",
			data : "pictureId="+pictureId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					doc.hide("show");
				}
			}
		});
	});
});
/*下载选中图文*/
function downloadItems(mediaId,doc){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "wechat/items/copy/zhifang",
		data : "mediaId="+mediaId,
		dataType : "json",
		success : function(e){
			dialog(e.msg);
			window.location.reload();
		}
	}); 
}
/*点赞图文*/
function pariseItems(mediaId,obj){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "wechat/praise/store/wechat",
		data : "mediaId="+mediaId,
		dataType : "json",
		success : function(e){
			if (e.code == 0){
				var str = parseInt(jQuery(obj).find("span").text());
				str = str + 1;
				jQuery(obj).find("span").text(str);
			}else {
				dialog(e.msg);
			}
		}
	}); 
}
/*删除图文消息,该js只能写在该页面中*/
function deleteItems(mediaId,doc){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "wechat/items/delete",
		data : "mediaId="+mediaId,
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				jQuery(doc).parents(".imgword-item").hide("show");
			}
		}
	}); 
}
/*删除智放图文消息*/
function deleteZfItems(mediaId,doc){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "wechat/items/delete",
		data : "mediaId="+mediaId+"&storeId=0",
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				jQuery(doc).parents(".imgword-item").hide("show");
			}
		}
	}); 
}
jQuery("#uploadItems").on("click",function(){
	jQuery.ajax({
		type : "get",
		url : baseUrl + "wechat/upload/items",
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				dialog("更新成功");
			}
			else{
				dialog("更新失败,请重试")
			}
		}
	});
});
</script>
</html>