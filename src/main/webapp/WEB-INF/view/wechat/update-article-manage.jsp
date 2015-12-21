<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
<style>
	.edui-for-preview {
		top:0px;
	}
</style>
<body>
	<div class="mainwrapper">
		<!--loading start-->
		<%@ include file="/loading.jsp" %>
		<!--loading end-->

		<!--left-panel start-->
		<%@ include file="/menu.jsp"%>
		<!--left-panel end-->

		<!--RIGHT PANEL开始 -->
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/articleImagesButton.js"></script>
			<!-- 页面内容开始 -->

<div class="maincontent" style="overflow-y: auto">
    <div class="contentinner">
        <h4 class="widgettitle">
            <span class="dingdanzhuantai">修改图文</span>
            <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span>
        </h4>

        <div class="widgetcontent">
            <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all " style="padding-bottom:10px;">
                <div id="tabs-1" style="height: 900px;">
                    <!--<ol class="breadcrumb">
                        <li><a href="#">图文消息</a></li>
                        <li class="active">新建图文消息</li>
                    </ol>-->
								<div class="news-area">
									<div class="multi edit news">
										<div class="news-content">
										<!--news-item1-->
										<c:forEach items="${items }"  var="auto" varStatus="status">
										    <c:if test="${status.first == 'true' }">
										    
										    <div class="news-item1 first-item" id="news-item1">
													<div class="cover-news-item">
														<h4 class="news-title">
															<a onclick="return false;" href="javascript:void(0);" target="_blank" id="t1">${auto.title }</a>
														</h4>
														<div class="news-img-wrap">
															<img style="width: 318px;height: 160px" src="${auto.qiniuImg }" id="img-one" />
														</div>
													</div>
													<div class="news-edit-mask">
														<a class="js-edit" href="javascript:void(0);"><span class="icon-pencil"></span></a>
													</div>
											</div>
										    </c:if>
										    
										    <c:if test="${status.first == 'false' }">
										    <div id="news-item${status.index+1 }" class="news-item2 news-item editing">
												<img class="news-thumb default news-word" src="${auto.qiniuImg }">
												<h4 class="news-title">
													<a  href="javascript:void(0);"  id="t2">${auto.title }</a>
												</h4>
			                                    <div class="news-edit-mask" >
			                                        <a class="icon18_common edit_gray js_edit"
			                                           onclick="return false;" href="javascript:void(0);">编辑</a>
			                                        <a class="icon18_common del_gray js_del"
			                                           onclick="return false;" href="javascript:void(0);">删除</a>
			                                    </div>
											</div>
										    </c:if>
										</c:forEach>
										
										</div>
									</div>
									<!--news-content-->
								</div>
								<!--news-area-->
								
                              <form action="<%=basePath %>wechat/items/update" method="POST"  class="am-form" >
                              
								<div class="news-edit-area">
								<c:forEach items="${items }"  var="auto" varStatus="status">
								    <c:if test="${status.first == 'true' }">
								    <!-- 第一个item -->
									<div class="news-edit" id="item1">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> 
												  <span class="e-input-box "> 
												     <input type="hidden" name="index" value="${status.index }">
												     <input type="hidden" name="media_id" value="${auto.mediaId }">
												     <input type="hidden" name="replyId" value="${auto.replyId }">
												     <input type="hidden" name="content_source_url" value="${auto.contentSourceUrl }">
													 <input name="title" type="text" id="title1"  value="${auto.title }" class="e-input js_title js_counter" max-length="64"> 
													 <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> 
												<span class="e-input-box "> 
												    <input id="author1" name="author" type="text" value="${auto.author }" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn" style="margin-bottom:10px;">
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container">
													    <input type="hidden" id="thumb1" class="thumb" name="thumb_media_id"  value="${auto.thumbMediaId }">
													    <input type="hidden" id="qiniuImg1" name="qiniuImg" value="${auto.qiniuImg }">
													    <input type="hidden" id="picUrl1" name="picUrl" value="${auto.picUrl }">
														<img id="showImg1" src="${auto.qiniuImg }" style="width: 100px; height: 100px;" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles">选择文件</button>
									            <!-- 七牛上传图片 -->
												<!-- <button class="btn">上传</button> -->
													<button type="button" class="select-file from-picture btn" id="from-picture1">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>
<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea name="digest" cols="30" rows="3" >${auto.description }</textarea>
											</div>
											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											<!-- edit -->
											<script id="editor1" type="text/plain" style="width:540px;height:400px;">${auto.content }</script>
											<%-- <textarea id="editor1" name="content" style="width:600px;height:300px;">
											${auto.content }
											</textarea> --%>
											<!-- edit -->
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
							  <!-- 第一个item -->
								    </c:if>
								    <c:if test="${status.first == 'false' }">
								    <!-- 第二个item -->	
								<div class="news-edit hide" id="item${status.index+1 }">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> 
												 <span class="e-input-box ">
												     <input type="hidden" name="index" value="${status.index }">
												     <input type="hidden" name="media_id" value="${auto.mediaId }">
												     <input type="hidden" name="replyId" value="${auto.replyId }">
												     <input type="hidden" name="content_source_url" value="${auto.contentSourceUrl }">
												    <input name="title" type="text" id="title${status.index+1 }"  value="${auto.title }"  class="e-input js_title js_counter" max-length="64"> 
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> 
												<span class="e-input-box "> 
												    <input id="author${status.index+1 }" name="author" type="text"  value="${auto.author }"  class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container2">
													    <input type="hidden" id="thumb${status.index+1 }" class="thumb" name="thumb_media_id"  value="${auto.thumbMediaId }">
													    <input type="hidden" id="qiniuImg${status.index+1 }" name="qiniuImg" value="${auto.qiniuImg }">
													    <input type="hidden" id="picUrl${status.index+1 }" name="picUrl" value="${auto.picUrl }">
														<img id="showImg2" src="${auto.qiniuImg }" style="width: 100px; height: 100px;" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles${status.index+1 }">选择文件</button>
									            <!-- 七牛上传图片 -->
												<button type="button" class="select-file from-picture btn" id="from-picture${status.index+1 }">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>
											<p></p>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea name="digest"  cols="30" rows="3" >${auto.description }</textarea>
											</div>

											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<!-- edit -->
											<script id="editor${status.index+1 }" type="text/plain" style="width:540px;height:400px;">${auto.content }</script>
											<%-- <textarea id="editor${status.index+1 }" name="content" style="width:600px;height:300px;">
											${auto.content }
											</textarea> --%>
											<!-- edit -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
								<!-- 第二个item -->	
								    </c:if>
								</c:forEach>
							    <input type="hidden" value="1" name="itemNum" id="itemNum">
								</div>
								</form>
								<div class = "clearfix"></div>
								<!--微信确认按钮-->
								<div style="margin-left: 0; margin-right: 0; -webkit-box-shadow: none; text-align: center; padding-top: 20px; padding-bottom: 50px; ">
									<span id="baocun" style="min-width: 104px; padding: 0; background-color: #44b549;"><button class="btn">确认修改</button></span>
									<span style="min-width: 104px; padding: 0; background-color: #44b549;display: none"><button class="btn">预览</button></span>
									<span style="min-width: 104px; padding: 0; background-color: #44b549;display: none"><button class="btn">保存并群发</button></span>
								</div>
							</div>
								<!--news-edit-area-->
								
							<!--tabs-1-->
						</div>
					</div>
				</div>
			</div>
			<!-- 页面内容结束 -->

		</div>
		<!--RIGHT PANEL结束 -->

		<div class="clearfix"></div>

		<div id="star"></div>
	</div>
	<c:forEach items="${items }"  var="itemPicture" varStatus="statusPicture">
	<!-- 选择图片模态框 -->
	<div class="modal hide" id="photo-list-modal${statusPicture.index+1 }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content select-wordimg-modal" style="width: 580px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	                <h4 class="modal-title" id="myModalLabel">
	                    	选择图片
	                </h4>
	            </div>
	            <div class="imgword-list">
	            <c:forEach items="${pictureLibraries }" var="pictures">
	            	<div class="photo-item"  style="position: relative;">
	                    <div class="photo-content" id="container" >
	                        <input type="hidden" name="pictureWechat" value="${pictures.pictureWechat }" >
	                        <input type="hidden" name="thumbMediaId" value="${pictures.thumbMediaId }" >
	                        <img src="${pictures.pictureQiniu }">
	                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
	                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
	                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
	                </div>
	            </c:forEach>
	            </div>
	
	            <div class="modal-footer">
	                <button type="button" class="btn" data-dismiss="modal">取消</button>
	                <button type="button" class="btn " id="confirm-menu-url${statusPicture.index+1 }" data-dismiss="modal">确认</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- 选择图片模态框 -->	
	</c:forEach>
<%@include file="qiniuChoseImage.jsp" %>
</body>
</html>
<script src="<%=basePath%>js/wechat/update-article-manage.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/plupload.full.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<script charset="utf-8" src="<%=basePath %>editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath %>editor/lang/zh_CN.js"></script>
<script type="text/javascript">
jQuery(".news-item").hover(
        function(){
           jQuery(this).children(".news-edit-mask").show();
        },
        function(){
            jQuery(this).children(".news-edit-mask").hide();
        }
);
</script>
<script>
var toolbars = {
		toolbars: [
		   		['fullscreen', 'source', '|', 'undo', 'redo', '|',
		           'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript','|', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
		           'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
		           'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
		           'directionalityltr', 'directionalityrtl', 'indent', '|',
		           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase','preview']
		   	]
		   };
var u1 = UE.getEditor('editor1', toolbars);
var u2 = UE.getEditor('editor2', toolbars);
var u3 = UE.getEditor('editor3', toolbars);
var u4 = UE.getEditor('editor4', toolbars);
var u5 = UE.getEditor('editor5', toolbars);
var u6 = UE.getEditor('editor6', toolbars);
var u7 = UE.getEditor('editor7', toolbars);
var u8 = UE.getEditor('editor8', toolbars);

var qiniu1 = new QiniuJsSDK();
var qiniu2 = new QiniuJsSDK();
var qiniu3 = new QiniuJsSDK();
var qiniu4 = new QiniuJsSDK();
var qiniu5 = new QiniuJsSDK();
var qiniu6 = new QiniuJsSDK();
var qiniu7 = new QiniuJsSDK();
var qiniu8 = new QiniuJsSDK();
/* 
     //富文本按钮
     var items = [
                  'source', '|', 'undo', 'redo', '|', 'cut', 'copy', 'paste',
                  'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','|','picture',
                  'insertorderedlist','insertunorderedlist','fontsize','forecolor','hilitecolor','bold'
     ];
     //提示名称
     KindEditor.lang({
         picture : '图片'
     });
     //创建自定义的图片富文本按钮
    var editor1 ;
    var editor2 ;
    var editor3 ;
    var editor4 ;
    var editor5 ;
    var editor6 ;
    var editor7 ;
    var editor8 ;
    KindEditor.ready(function(K) {
        editor1 = K.create('#editor1', {
            width:'430px',
            minHeight:300,
            minWidth:540,
            items:items
        });
    });
    KindEditor.ready(function(K) {
        editor2 = K.create('#editor2', {
            width:'430px',
            minHeight:300,
            minWidth:540,
            items:items
        });
    });
    KindEditor.ready(function(K) {
        editor3 = K.create('#editor3', {
            width:'430px',
            minHeight:300,
            minWidth:540,
            items:items
        });
    });
    KindEditor.ready(function(K) {
        editor4 = K.create('#editor4', {
            width:'430px',
            minHeight:300,
            minWidth:540,
            items:items
        });
    });
    KindEditor.ready(function(K) {
        editor5 = K.create('#editor5', {
            width:'430px',
            minHeight:300,
            minWidth:540,
            items:items
        });
    });
    KindEditor.ready(function(K) {
        editor6 = K.create('#editor6', {
            width:'430px',
            minHeight:300,
            minWidth:540,
            items:items
        });
    });
    KindEditor.ready(function(K) {
        editor7 = K.create('#editor7', {
            width:'430px',
            minHeight:300,
            minWidth:540,
            items:items
        });
    });
    KindEditor.ready(function(K) {
        editor8 = K.create('#editor8', {
            width:'430px',
            minHeight:300,
            minWidth:540,
            items:items
        });
    });
 */
      

</script>
</html>