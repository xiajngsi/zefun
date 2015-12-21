<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<body>
<script charset="utf-8" src="<%=basePath %>editor/kindeditor-min.js"></script>
<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
      	<!-- 页面代码 -->
      	
		<div class="maincontent">
		    <div class="contentinner">
		        <h4 class="widgettitle">
		            <span class="dingdanzhuantai">发型设置</span>
		            <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span>
		        </h4>
		
		        <div class="project-list">
		            <div class="project-list-head">
		                <input type="search" placeholder="搜索" class="search-input"/>
		                <button type="button" class="btn search-button" id="search-member">搜索</button>
		            </div>
		            <div class="project-list-ul">
		                <div class="project-category">
		                    <div class="project-name">
		                        <i class="afont iconfa-home"></i>
		                        	发型风格
		                        <i onclick="addProjectClass(this);" class="iconfa-plus-sign project-icon fr"></i>
		                    </div>
		                    <c:forEach var="category" items="${categoryList }" varStatus="status">
		                    	<ul class="project-sublist">
			                        <li class="project-sublist-title">${category.hairstyleCategoryName }</li>
			                        <c:forEach var="hairstyleDesign" items="${category.hairstyleDesign }" varStatus="status">
			                        	<li class="project-sublist-content">
											${hairstyleDesign.hairstyleTitle }
				                            <span class="fr">
				                                <i class="iconfa-pencil project-icon"></i>
				                                <i class="iconfa-trash project-icon"></i>
				                            </span>
				                        </li>
			                        </c:forEach>
			                    </ul>
		                    </c:forEach>
		                </div>
		            </div>
		        </div>
		
		        <div class="project-setting">
		            <form id="hairstyleDesignForm">
		                <div class="img-word">
		                	<div class="x-edit-left" id="container">
		                        <div class="file-box" id="hairStyleHeadImg">
		                        	<img alt="" name="headImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
		                        	<input type="hidden" name="hairstyleCover"/>
		                            <input type="file" value="点击上传照片" class="file-inp" style="width:130px;height:100px;"/>
		                        </div>
		                        <div id="pickfiles">点击上传图片</div>
		                        <button type="button" class="btn select-img-btn">选择图库</button>
		                        <div class="img-chicun">图片尺寸200x200像素</div>
		                    </div>
		                    <div class="x-edit-right">
		                        <div class="two-part-30">
		                            <label for="" class="font-bold">发型类别</label>
		                            <select name="hairstyleCategoryId" class="chzn-select">
		                                <option value="1">长发</option>
		                                <option value="2">中发</option>
		                                <option value="3">短发</option>
		                            </select>
		                        </div>
		                        <div class="two-part-70">
		                            <label for="" class="font-bold">发型名称</label>
		                            <input type="text" name="hairstyleTitle"/>
		                        </div>
		                        <div class="clearfix"></div>
		                        <div class="mt10">
		                            <label for="" class="font-bold">发型描述</label>
		                            <textarea name="hairstyleContent" id="" cols="20" rows="5" class="wp80">
		                            </textarea>
		                        </div>
		                    </div>
		                </div>
			            <div class="clearfix"></div>
		
			            <div class="widgetcontent">
			                <table class="table zhibanbiao">
			                    <thead>
			                    <tr>
			                        <th colspan="5">
										发型属性标签（方便顾客预约时搜索）
			                        </th>
			                    </tr>
			                    </thead>
			                    <tbody>
				                    <c:forEach var="codeLibrary" items="${codeLibraries }" varStatus="status">
				                    	<tr>
					                    	<td>${codeLibrary.key }</td>
					                    	<td colspan="2">
						                        <c:forEach var="library" items="${codeLibrary.value }">
						                        	<div class="mr10 fl">
						                    			<span class="check-before check-radio"></span>
						                    			<input type="checkbox" class="beau-select" name="${codeLibrary.key }" value="${library.codeNo }" />
						                    			${library.codeName }
						                    		</div>
						                        </c:forEach>
					                        </td>
				                        </tr>
				                    </c:forEach>
			                    </tbody>
			                </table>
			            </div>
		
			            <div class="widgetcontent">
		                    <table class="table">
		                        <thead>
		                        <tr>
		                            <th>
										附属图片(点击图片可替换)
		                            </th>
		                        </tr>
		                        </thead>
		                        <tbody>
		                        <tr>
		                            <td>
		                                <div class="file-box fl ml30" id="container-affiliatedImg1">
					                        <div class="file-box" id="affiliatedHeadImg1">
					                        	<img alt="" name="affiliatedImg1" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
					                        	<input type="hidden" name="affiliatedImg"/>
					                            <input type="file" value="点击上传照片" class="file-inp" style="width:130px;height:100px;"/>
					                        </div>
					                    </div>
		
		                                <div class="file-box fl ml30" id="container-affiliatedImg2">
					                        <div class="file-box" id="affiliatedHeadImg2">
					                        	<img alt="" name="affiliatedImg2" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
					                        	<input type="hidden" name="affiliatedImg"/>
					                            <input type="file" value="点击上传照片" class="file-inp" style="width:130px;height:100px;"/>
					                        </div>
					                    </div>
					                    
					                    <div class="file-box fl ml30" id="container-affiliatedImg3">
					                        <div class="file-box" id="affiliatedHeadImg3">
					                        	<img alt="" name="affiliatedImg3" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
					                        	<input type="hidden" name="affiliatedImg"/>
					                            <input type="file" value="点击上传照片" class="file-inp" style="width:130px;height:100px;"/>
					                        </div>
					                    </div>
					                    
					                    <div class="file-box fl ml30" id="container-affiliatedImg4">
					                        <div class="file-box" id="affiliatedHeadImg4">
					                        	<img alt="" name="affiliatedImg4" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
					                        	<input type="hidden" name="affiliatedImg"/>
					                            <input type="file" value="点击上传照片" class="file-inp" style="width:130px;height:100px;"/>
					                        </div>
					                    </div>
					                    
		                            </td>
		                        </tr>
		                        </tbody>
		                    </table>
			            </div>
		
			            <div class="widgetcontent">
			                <table class="table">
			                    <thead>
			                    <tr>
			                        <th>
										选择发型相关的产品(点击图片可替换)
			                        </th>
			                    </tr>
			                    </thead>
			                    <tbody>
			                    <tr>
			                        <td>
			                            <div class="file-box fl ml30 file-box-product" data-target="#add-product-modal" data-toggle="modal">
			                            	<input type="hidden" name="click" value="0"/>
			                                <img name="productImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn"/>
			                                <input type="hidden" name="goodsId"/>
			                            </div>
			                            <div class="file-box fl ml30 file-box-product" data-target="#add-product-modal" data-toggle="modal">
			                            	<input type="hidden" name="click" value="0"/>
			                                <img name="productImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn"/>
			                                <input type="hidden" name="goodsId"/>
			                            </div>
			                            <div class="file-box fl ml30 file-box-product" data-target="#add-product-modal" data-toggle="modal" >
			                            	<input type="hidden" name="click" value="0"/>
			                                <img name="productImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn"/>
			                                <input type="hidden" name="goodsId"/>
			                            </div>
			                            <div class="file-box fl ml30 file-box-product" data-target="#add-product-modal" data-toggle="modal">
			                            	<input type="hidden" name="click" value="0"/>
			                                <img name="productImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn"/>
			                                <input type="hidden" name="goodsId"/>
			                            </div>
			                        </td>
			                    </tr>
			                    </tbody>
			                </table>
			            </div>
		
		                <div class="widgetcontent">
		                    <table class="table">
		                        <thead>
		                        <tr>
		                            <th>
										选择发型相关的项目(点击图片可替换)
		                            </th>
		                        </tr>
		                        </thead>
		                        <tbody>
			                        <tr>
			                            <td>
			                                <div class="file-box fl ml30 file-box-project" data-target="#add-project-modal" data-toggle="modal">
			                                	<input type="hidden" name="click" value="0"/>
			                                    <img id="projectImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn"/>
			                                    <input type="hidden" name="projectId"/>
			                                </div>
			                                <div class="file-box fl ml30 file-box-project" data-target="#add-project-modal" data-toggle="modal">
			                                    <input type="hidden" name="click" value="0"/>
			                                    <img id="projectImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn"/>
			                                    <input type="hidden" name="projectId"/>
			                                </div>
			                                <div class="file-box fl ml30 file-box-project" data-target="#add-project-modal" data-toggle="modal">
			                                    <input type="hidden" name="click" value="0"/>
			                                    <img id="projectImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn"/>
			                                    <input type="hidden" name="projectId"/>
			                                </div>
			                                <div class="file-box fl ml30 file-box-project" data-target="#add-project-modal" data-toggle="modal">
			                                    <input type="hidden" name="click" value="0"/>
			                                    <img id="projectImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn"/>
			                                    <input type="hidden" name="projectId"/>
			                                </div>
			                            </td>
			                        </tr>
		                        </tbody>
		                    </table>
		                </div>
			            <div class="table-title" style="float: right;">
			                <button type="reset" class="btn" >清除</button>
			                <button type="button" class="btn" onclick="saveHairstyleDesign();">&nbsp;保&nbsp;&nbsp;存&nbsp;</button>
			            </div>
		            </form>
		        </div>
		    </div>
		</div>
		
		
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<!--选择产品模态框-->
<div class="modal hide" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-sub-project-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择产品</h4>
                <span class="return hide cursor">
                    <span class="icon-chevron-left fl"></span>
                    <span class="fl ml10">返回</span>
                </span>
            </div>
            <div class="modal-body">
                <div class="alternative-img">
                    <div class="alternative-img-select">
                        <h4>产品列表</h4>
                        <div class="imgword-list">
	                        <c:forEach items="${goodsInfoList }" var="goodsInfo">
				            	<div class="photo-item" style="position: relative;">
				                    <div class="photo-content" id="container-product" >
				                    	<input type="hidden" value="${goodsInfo.goodsId }" name="goodsId"/>
				                        <img src="<%=picPath%>${goodsInfo.goodsImage }" alt=""/> ${goodsInfo.goodsName }
				                    </div>
				                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
				                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
				                </div>
				            </c:forEach>
                        </div>
                    </div>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
            	<button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-product" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>

<!--选择项目模态框-->
<div class="modal hide" id="add-project-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-sub-project-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择项目</h4>
                <span class="return hide cursor">
                    <span class="icon-chevron-left fl"></span>
                    <span class="fl ml10">返回</span>
                </span>
            </div>
            <div class="modal-body">
                <div class="alternative-img">
                    <div class="alternative-img-select">
                        <h4>项目列表</h4>
						<div class="imgword-list">
	                        <c:forEach items="${projectInfoList }" var="projectInfo">
				            	<div class="photo-item" style="position: relative;">
				                    <div class="photo-content" id="container-project" >
				                    	<input type="hidden" value="${projectInfo.projectId }" name="projectId"/>
				                        <img src="<%=picPath%>${projectInfo.projectImage }" alt=""/> ${projectInfo.projectName }
				                    </div>
				                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
				                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
				                </div>
				            </c:forEach>
                        </div>
                    </div>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
            	<button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-project" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>

<script src="<%=basePath %>js/commodity/hairstyleDesign.js"></script>

</body>
</html>