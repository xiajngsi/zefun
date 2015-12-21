<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />

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
            <span class="dingdanzhuantai">图文消息</span>
            <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span>
        </h4>

        <div class="widgetcontent">
            <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
                <div id="tabs-1">
                    <!--<ol class="breadcrumb">
                        <li><a href="#">图文消息</a></li>
                        <li class="active">新建图文消息</li>
                    </ol>-->

								<div class="news-area">
									<div class="multi edit news">
										<div class="news-content">
											<div class="news-item1 first-item" id="news-item1">
												<div class="cover-news-item">
													<h4 class="news-title">
														<a onclick="return false;" href="javascript:void(0);"
														target="_blank" id="t1">请设置标题</a>
													</h4>
													<div class="news-img-wrap">
														<img style="width: 318px;height: 160px" src="<%=basePath %>images/pic_none.gif" id="img-one" alt="" />
													</div>
												</div>
												<div class="news-edit-mask">
													<a class="js-edit" href="javascript:void(0);"><span class="icon-pencil"></span></a>
												</div>
											</div>
											<!--news-item2-->
											<div id="news-item2"
												class="news-item2 news-item editing hide">
												<img class="js_appmsg_thumb news-thumb news-img" src="">
												<i class="news-thumb default news-word">缩略图</i>
												<h4 class="news-title">
													<a  href="javascript:void(0);"  id="t2"></a>
												</h4>
			                                    <div class="news-edit-mask" >
			                                        <a class="icon18_common edit_gray js_edit"
			                                           onclick="return false;" href="javascript:void(0);">编辑</a>
			                                        <a class="icon18_common del_gray js_del"
			                                           onclick="return false;" href="javascript:void(0);">删除</a>
			                                    </div>
											</div>
											<!--news-item3-->
											<div id="news-item3"
												class="news-item2 news-item editing hide">
												<img class="js_appmsg_thumb news-thumb news-img" src="">
												<i class="news-thumb default news-word">缩略图</i>
												<h4 class="news-title">
													<a onclick="return false;" href="javascript:void(0);"
														target="_blank" id="t3">标题</a>
												</h4>
												<div class="news-edit-mask">
													<a class="icon18_common edit_gray js_edit"
														onclick="return false;" href="javascript:void(0);">编辑</a>
													<a class="icon18_common del_gray js_del"
														onclick="return false;" href="javascript:void(0);">删除</a>
												</div>
											</div>
											<!--news-item4-->
											<div id="news-item4"
												class="news-item2 news-item editing hide">
												<img class="js_appmsg_thumb news-thumb news-img" src="">
												<i class="news-thumb default news-word">缩略图</i>
												<h4 class="news-title">
													<a onclick="return false;" href="javascript:void(0);"
														target="_blank" id="t4">标题</a>
												</h4>
												<div class="news-edit-mask">
													<a class="icon18_common edit_gray js_edit"
														onclick="return false;" href="javascript:void(0);">编辑</a>
													<a class="icon18_common del_gray js_del"
														onclick="return false;" href="javascript:void(0);">删除</a>
												</div>
											</div>
											<!--news-item5-->
											<div id="news-item5"
												class="news-item2 news-item editing hide">
												<img class="js_appmsg_thumb news-thumb news-img" src="">
												<i class="news-thumb default news-word">缩略图</i>
												<h4 class="news-title">
													<a onclick="return false;" href="javascript:void(0);"
														target="_blank" id="t5">标题</a>
												</h4>
												<div class="news-edit-mask">
													<a class="icon18_common edit_gray js_edit"
														onclick="return false;" href="javascript:void(0);">编辑</a>
													<a class="icon18_common del_gray js_del"
														onclick="return false;" href="javascript:void(0);">删除</a>
												</div>
											</div>
											<!--news-item6-->
											<div id="news-item6"
												class="news-item2 news-item editing hide">
												<img class="js_appmsg_thumb news-thumb news-img" src="">
												<i class="news-thumb default news-word">缩略图</i>
												<h4 class="news-title">
													<a onclick="return false;" href="javascript:void(0);"
														target="_blank" id="t6">标题</a>
												</h4>
												<div class="news-edit-mask">
													<a class="icon18_common edit_gray js_edit"
														onclick="return false;" href="javascript:void(0);">编辑</a>
													<a class="icon18_common del_gray js_del"
														onclick="return false;" href="javascript:void(0);">删除</a>
												</div>
											</div>
											<!--news-item7-->
											<div id="news-item7"
												class="news-item2 news-item editing hide">
												<img class="js_appmsg_thumb news-thumb news-img" src="">
												<i class="news-thumb default news-word">缩略图</i>
												<h4 class="news-title">
													<a onclick="return false;" href="javascript:void(0);"
														target="_blank" id="t7">标题</a>
												</h4>
												<div class="news-edit-mask">
													<a class="icon18_common edit_gray js_edit"
														onclick="return false;" href="javascript:void(0);">编辑</a>
													<a class="icon18_common del_gray js_del"
														onclick="return false;" href="javascript:void(0);">删除</a>
												</div>
											</div>
											<!--news-item8-->
											<div id="news-item8"
												class="news-item2 news-item editing hide">
												<img class="js_appmsg_thumb news-thumb news-img" src="">
												<i class="news-thumb default news-word">缩略图</i>
												<h4 class="news-title">
													<a onclick="return false;" href="javascript:void(0);"
														target="_blank" id="t8">标题</a>
												</h4>
												<div class="news-edit-mask">
													<a class="icon18_common edit_gray js_edit"
														onclick="return false;" href="javascript:void(0);">编辑</a>
													<a class="icon18_common del_gray js_del"
														onclick="return false;" href="javascript:void(0);">删除</a>
												</div>
											</div>

											<a onclick="addArtile()"
												class="create_access_primary appmsg_add" id="js_add_appmsg"
												href="javascript:void(0);"> <i
												class="icon20_common add_gray icon-plus">增加一条</i>
											</a>
										</div>
									</div>
									<!--news-content-->
								</div>
								<!--news-area-->
								
                              <form action="<%=basePath %>uploadnews" method="POST"  class="am-form" >
                              
								<div class="news-edit-area">
								<!-- 第一个item -->
									<div class="news-edit" id="item1">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> <span
													class="e-input-box "> <input name="title" type="text" id="title1" class="e-input js_title js_counter" max-length="64"> <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> <span class="e-input-box "> <input id="author1" name="author" type="text" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container">
													    <input type="hidden" id="imgUrl1" name="imgUrl" value="">
														<img id="showImg1" src="" style="width: 100px; height: 100px;display:none" />
													</div>
									                <button type="button" class="select-file btn btn" id="pickfiles">选择文件</button>
									           		 <button type="button" class="select-file btn from-picture btn" id="from-picture1">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
											<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description1" name="Description" cols="30" rows="3"></textarea>
											</div>
											
											<div class="edit-item">
												<label for="" class="e-label">原文链接</label>
												<input type="text"  name="content_source_url" value="">
											</div>
											
											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<script id="editor1" type="text/plain" style="width:540px;height:400px;"></script>
											<!-- <!-- edit -->
											<!-- <textarea id="editor1" name="content" style="width:600px;height:600px;"></textarea> -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
							  <!-- 第一个item -->	
							  <!-- 第二个item -->	
								<div class="news-edit hide" id="item2">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> <span
													class="e-input-box "> <input name="title" type="text" id="title2" class="e-input js_title js_counter" max-length="64"> <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> <span class="e-input-box "> 
												    <input id="author2" name="author" type="text" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- <input type="file" onchange="previewImage(this)" value="上传" /> -->
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container2">
													    <input type="hidden" id="imgUrl2" name="imgUrl" value="">
														<img id="showImg2" src="" style="width: 100px; height: 100px;display:none" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles2">选择文件</button>
									            <!-- 七牛上传图片 -->
												<button type="button" class="select-file btn from-picture" id="from-picture2">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description2" name="Description"  cols="30" rows="3"></textarea>
											</div>
											
											<div class="edit-item">
												<label for="" class="e-label">原文链接</label>
												<input type="text"  name="content_source_url" value="">
											</div>

											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<script id="editor2"  type="text/plain" style="width:540px;height:400px;"></script>
											
											<!-- edit -->
											<!-- <textarea id="editor2" name="content" style="width:600px;height:300px;"></textarea> -->
											<!-- edit -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
								<!-- 第二个item -->	
								<!-- 第三个item -->	
								<div class="news-edit hide" id="item3">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> <span
													class="e-input-box "> <input name="title" type="text" id="title3" class="e-input js_title js_counter" max-length="64"> <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> <span class="e-input-box "> 
												    <input id="author3" name="author" type="text" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- <input type="file" onchange="previewImage(this)" value="上传" /> -->
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container3">
													    <input type="hidden" id="imgUrl3" name="imgUrl" value="">
														<img id="showImg3" src="" style="width: 100px; height: 100px;display:none" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles3">选择文件</button>
									            <!-- 七牛上传图片 -->
												<button type="button" class="select-file btn from-picture" id="from-picture3">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description3" name="Description"  cols="30" rows="3"></textarea>
											</div>

											<div class="edit-item">
												<label for="" class="e-label">原文链接</label>
												<input type="text"  name="content_source_url" value="">
											</div>
											
											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<!-- edit -->
											<script id="editor3"  type="text/plain" style="width:540px;height:400px;"></script>
											<!-- edit -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
								<!-- 第三个item -->	
								<!-- 第四个item -->	
								<div class="news-edit hide" id="item4">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> <span
													class="e-input-box "> <input name="title" type="text" id="title4" class="e-input js_title js_counter" max-length="64"> <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> <span class="e-input-box "> 
												    <input id="author4" name="author" type="text" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- <input type="file" onchange="previewImage(this)" value="上传" /> -->
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container4">
													    <input type="hidden" id="imgUrl4" name="imgUrl" value="">
														<img id="showImg4" src="" style="width: 100px; height: 100px;display:none" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles4">选择文件</button>
									            <!-- 七牛上传图片 -->
												<button type="button" class="select-file btn from-picture" id="from-picture4">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description4" name="Description"  cols="30" rows="3"></textarea>
											</div>

											<div class="edit-item">
												<label for="" class="e-label">原文链接</label>
												<input type="text"  name="content_source_url" value="">
											</div>

											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<!-- edit -->
											<script id="editor4"  type="text/plain" style="width:540px;height:400px;"></script>
											<!-- edit -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
								<!-- 第四个item -->	
								<!-- 第五个item -->	
								<div class="news-edit hide" id="item5">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> <span
													class="e-input-box "> <input name="title" type="text" id="title5" class="e-input js_title js_counter" max-length="64"> <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> <span class="e-input-box "> 
												    <input id="author5" name="author" type="text" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- <input type="file" onchange="previewImage(this)" value="上传" /> -->
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container5">
													    <input type="hidden" id="imgUrl5" name="imgUrl" value="">
														<img id="showImg5" src="" style="width: 100px; height: 100px;display:none" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles5">选择文件</button>
									            <!-- 七牛上传图片 -->
												<button type="button" class="select-file btn from-picture" id="from-picture5">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description5" name="Description"  cols="30" rows="3"></textarea>
											</div>

											<div class="edit-item">
												<label for="" class="e-label">原文链接</label>
												<input type="text"  name="content_source_url" value="">
											</div>

											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<!-- edit -->
											<script id="editor5"  type="text/plain" style="width:540px;height:400px;"></script>
											<!-- edit -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
								<!-- 第五个item -->	
								<!-- 第六个item -->	
								<div class="news-edit hide" id="item6">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> <span
													class="e-input-box "> <input name="title" type="text" id="title6" class="e-input js_title js_counter" max-length="64"> <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> <span class="e-input-box "> 
												    <input id="author6" name="author" type="text" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- <input type="file" onchange="previewImage(this)" value="上传" /> -->
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container6">
													    <input type="hidden" id="imgUrl6" name="imgUrl" value="">
														<img id="showImg6" src="" style="width: 100px; height: 100px;display:none" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles6">选择文件</button>
									            <!-- 七牛上传图片 -->
												<button type="button" class="select-file btn from-picture" id="from-picture6">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description6" name="Description"  cols="30" rows="3"></textarea>
											</div>

											<div class="edit-item">
												<label for="" class="e-label">原文链接</label>
												<input type="text"  name="content_source_url" value="">
											</div>
											
											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<!-- edit -->
											<script id="editor6"  type="text/plain" style="width:540px;height:400px;"></script>
											<!-- edit -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
								<!-- 第六个item -->	
								<!-- 第七个item -->	
								<div class="news-edit hide" id="item7">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> <span
													class="e-input-box "> <input name="title" type="text" id="title7" class="e-input js_title js_counter" max-length="64"> <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> <span class="e-input-box "> 
												    <input id="author7" name="author" type="text" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- <input type="file" onchange="previewImage(this)" value="上传" /> -->
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container7">
													    <input type="hidden" id="imgUrl7" name="imgUrl" value="">
														<img id="showImg7" src="" style="width: 100px; height: 100px;display:none" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles7">选择文件</button>
									            <!-- 七牛上传图片 -->
												<button type="button" class="select-file btn from-picture" id="from-picture7">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description7" name="Description"  cols="30" rows="3"></textarea>
											</div>

											<div class="edit-item">
												<label for="" class="e-label">原文链接</label>
												<input type="text"  name="content_source_url" value="">
											</div>

											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<!-- edit -->
											<script id="editor7"  type="text/plain" style="width:540px;height:400px;"></script>
											<!-- edit -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
								<!-- 第七个item -->	
								<!-- 第八个item -->	
								<div class="news-edit hide" id="item8">
										<div class="inner">
											<div class="edit-item">
												<label for="" class="e-label">标题</label> <span
													class="e-input-box "> <input name="title" type="text" id="title8" class="e-input js_title js_counter" max-length="64"> <em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item"> 
												<label for="" class="e-label">作者</label> <span class="e-input-box "> 
												    <input id="author8" name="author" type="text" class="e-input js_title js_counter" max-length="64">
													<em class="e-input-append frm_counter">61/64</em>
												</span>
											</div>
											<div class="edit-item">
												<label for="" class="e-label">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											<div class="upload-btn">
												<!-- <input type="file" onchange="previewImage(this)" value="上传" /> -->
												<!-- 七牛上传图片 -->
													<div class="display-frame" id="container8">
													    <input type="hidden" id="imgUrl8" name="imgUrl" value="">
														<img id="showImg8" src="" style="width: 100px; height: 100px;display:none" />
													</div>
									                <button type="button" class="select-file btn" id="pickfiles8">选择文件</button>
									            <!-- 七牛上传图片 -->
												<button type="button" class="select-file btn from-picture" id="from-picture8">从图片库中选择</button>
											</div>
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
<br>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description8" name="Description"  cols="30" rows="3"></textarea>
											</div>

											<div class="edit-item">
												<label for="" class="e-label">原文链接</label>
												<input type="text"  name="content_source_url" value="">
											</div>
											
											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<!-- edit -->
											<script id="editor8"  type="text/plain" style="width:540px;height:400px;"></script>
											<!-- edit -->
											
										</div>
										<i class="arrow arrow_out" style="margin-top: 0px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 0px;"></i>
									</div>
									</div>
									<input type="hidden" value="1" name="itemNum" id="itemNum">
								<!-- 第八个item -->	
								</form>
								<div class = "clearfix"></div>
									<!--微信确认按钮-->
									<div
										style="margin-left: 0; margin-right: 0; border-top: 1px solid #e7e7eb; -webkit-box-shadow: none; text-align: center; padding-top: 20px; padding-bottom: 50px; margin-top: 40px;">
										<span id="baocun"style="min-width: 104px; padding: 0; background-color: #44b549;"><button class="btn">保存</button></span>
										<span style="min-width: 104px; padding: 0; background-color: #44b549;"><button id="baocunyulan" class="btn">保存并预览</button></span>
										<span style="min-width: 104px; padding: 0; background-color: #44b549;display: none"><button class="btn">保存并群发</button></span>
									</div>
								</div>
								<!--news-edit-area-->
								
							</div>
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
	<!--mainwrapper-->
	
<!--微信预览模态框-->
<div class="modal hide weixin-preview-modal" data-backdrop="static" id="weixin-preview" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
            <c:forEach items="${pictureLibraries }" var="pictures">
            	<div class="photo-item"  style="position: relative;">
                    <div class="photo-content" id="container" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url1" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
            <c:forEach items="${pictureLibraries }" var="pictures">
            	<div class="photo-item"  style="position: relative;">
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url2" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
            <c:forEach items="${pictureLibraries }" var="pictures">
            	<div class="photo-item"  style="position: relative;">
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url3" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
            <c:forEach items="${pictureLibraries }" var="pictures">
            	<div class="photo-item"  style="position: relative;">
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url4" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
            <c:forEach items="${pictureLibraries }" var="pictures">
            	<div class="photo-item"  style="position: relative;">
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url5" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
            <c:forEach items="${pictureLibraries }" var="pictures">
            	<div class="photo-item"  style="position: relative;">
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url6" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal7" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
            <c:forEach items="${pictureLibraries }" var="pictures">
            	<div class="photo-item"  style="position: relative;">
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url7" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal8" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 600px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
            <c:forEach items="${pictureLibraries }" var="pictures">
            	<div class="photo-item"  style="position: relative;">
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url8" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>

<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage1" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage2" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage3" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage4" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage5" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage6" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image7" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage7" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image8" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage8" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
</body>
<script src="<%=basePath%>js/wechat/article-manage.js"></script>
<script src="<%=basePath%>js/wechat/chose-picture.js"></script>
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
      //新增图文消息
      var itemNum = 1;
      function addArtile(){
    	  if(itemNum >= 8){
    		  return ;
    	  }
    	  itemNum = itemNum + 1;
    	  jQuery("#item"+(itemNum-1)).hide();
    	  jQuery("#item"+itemNum).show();
    	  /**左侧显示缩略图信息*/
    	  jQuery("#news-item"+itemNum).show();
    	  jQuery("#itemNum").val(itemNum);
      }
      
      //设置标题
      jQuery(document).ready(function(){
    	  jQuery("#title1").blur(function(e){
	   	     jQuery("#t1").text(jQuery(this).val());
		 });
    	  jQuery("#title2").blur(function(e){
         	 jQuery("#t2").text(jQuery(this).val());
    	 });
    	  jQuery("#title3").blur(function(e){
             jQuery("#t3").text(jQuery(this).val());
       	 }); 
    	  jQuery("#title4").blur(function(e){
             jQuery("#t4").text(jQuery(this).val());
       	 }); 
    	  jQuery("#title5").blur(function(e){
             jQuery("#t5").text(jQuery(this).val());
       	 }); 
    	  jQuery("#title6").blur(function(e){
             jQuery("#t6").text(jQuery(this).val());
       	 }); 
    	  jQuery("#title7").blur(function(e){
             jQuery("#t7").text(jQuery(this).val());
       	 });
    	  jQuery("#title8").blur(function(e){
             jQuery("#t8").text(jQuery(this).val());
       	 });  
      });

      //点击编辑
      jQuery(document).ready(function(){
    	  jQuery("#news-item1").on("click",function(){
    	  hideItems();
    	  jQuery("#item1").show();
    	  });
    	  jQuery("#news-item2").on("click",function(){
        	  hideItems();
        	  jQuery("#item2").show();
         });
    	  jQuery("#news-item3").on("click",function(){
        	  hideItems();
        	  jQuery("#item3").show();
         });
    	  jQuery("#news-item4").on("click",function(){
        	  hideItems();
        	  jQuery("#item4").show();
         });
    	  jQuery("#news-item5").on("click",function(){
        	  hideItems();
        	  jQuery("#item5").show();
         });
    	  jQuery("#news-item6").on("click",function(){
        	  hideItems();
        	  jQuery("#item6").show();
         });
    	  jQuery("#news-item7").on("click",function(){
        	  hideItems();
        	  jQuery("#item7").show();
         });
    	  jQuery("#news-item8").on("click",function(){
        	  hideItems();
        	  jQuery("#item8").show();
         });
      });
      //隐藏所有的图文消息
      function hideItems(){
    	  for (var i = 0; i <= 8; i++) {
    		  jQuery("#item"+i).hide();
		}
      }
</script>
<script type="text/javascript" src="<%=basePath%>js/wechat/items-priview.js"></script>
<script type="text/javascript">
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
</script>
</html>