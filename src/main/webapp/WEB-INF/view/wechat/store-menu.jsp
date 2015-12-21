<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<div class="mainwrapper">
		<!--loading start-->
		<%@ include file="/loading.jsp"%>
		<!--loading end-->

		<!--left-panel start-->
		<%@ include file="/menu.jsp"%>
		<!--left-panel end-->

		<!--RIGHT PANEL开始 -->
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>

			<!-- 页面内容开始 -->
			<div class="maincontent" style="overflow-y: auto">
				<div class="contentinner">
					<h4 class="widgettitle">
						<span class="dingdanzhuantai blue">用户自定义菜单</span> <span
							class="video"
							style="float: right; font-weight: 400; color: #333;">视频帮助
							<span class="iconfont icon-video" style="margin-top: 1px;"></span>
						</span>
					</h4>

					<div class="widgetcontent">
						
						<!-- 已经开启了菜单 -->
						<div class="highlight_box_wrp" id="menu_setting">
						<c:if test="${menus == null}">
						 	<div class="opr">
				                <a href="javascript:;" class="btn btn_primary" id="btn_start"> 开启 </a>
				            </div>
						</c:if>
						<c:if test="${menus != null}">
						 	<div class="opr">
				                <a href="javascript:;" class="btn btn_primary" id="btn_stop"> 关闭 </a>
				            </div>
						</c:if>
					    </div>
						<!-- 已经开启了菜单 -->
						
						<div class="defined-manage">
							<div class="defined-menu">
				                    <div class="sub_title_bar light" >
				                        <div style="position: absolute;margin-top: 10px">
				                            <h4 style="margin-left: 10px"> 菜单管理 </h4>
				                        </div>
				                        <div class="opr_wrp" style="height: 45px;">
				                        </div>
         							</div>
         							<c:if test="${menus == null}">
									 	<ul class="wrap-menu hide">
									</c:if>
									<c:if test="${menus != null}">
									 	<ul class="wrap-menu">
									</c:if>
									<c:forEach items="${menus }" var="menu" >
									<li class="menu" id=${menu.menuId } ><span class="tree-dropdown"><span class="resetMenu">${menu.menuName }</span></span>
										<c:if test="${fn:length(menu.menus) > 0 && menu.menus != null}">
											<ul class="submenu">
												<c:forEach items="${menu.menus }" var="subMenu">
												<li  id="${subMenu.menuId }">${subMenu.menuName }</li>
												</c:forEach>
											</ul>
										</c:if>
									</li> 
									</c:forEach>
								</ul>
							</div>
							<div class="menu-wrap">
								<div class="menu-content-title">
									<span id="showMenuName" >当前菜单: 暂无选中</span> <span class="fr  can-click" id="">删除</span> <span class="fr mr30 can-click">重命名</span>
								</div>
								<div class="menu-content">
									<div class="load hide p">
										<img src="<%=basePath %>img/loaders/loader1.gif" alt="" /> <span>菜单重置中，请稍后</span>
									</div>
									<div class="init  p">
										<div class="desc">你可以点击左侧菜单或添加一个新菜单，然后设置菜单内容</div>
									</div>
									<div class="inner hide">
										<div class="desc p">请设置“积分商场”菜单的内容</div>
										<div class="block">
											<div class="item hide">
												<span class="select-block" id="initialCreate"> </span> 
												   <span class="word">新增二级菜单</span>
											</div>
											<div class="item">
												<span class="select-block" id="send"> </span> 
												   <span class="word">发送图文消息</span>
											</div>

											<div class="item">
												<span class="select-block" id="goPage"> </span>
												   <span class="word">跳转到网页</span>
											</div>

											<!-- <div class="item">
												<span class="select-block" id="autoReply"> </span> <span
													class="word">自动回复</span>
											</div> -->
										</div>
									</div>
									<div class="go-url hide">
										<p class="desc">
											订阅者点击该子菜单会跳到以下链接 <span class="can-click reset-content">重设菜单内容</span>
										</p>
										<p>
											<label for="">页面地址</label> <input type="text" id="menuUrl"/>
										</p>
										<p>
											<span class="red validate-info hide">请输入正确的URL</span>
										</p>
										<p>
											<span class="can-click modal-lunch" data-target="#select-wordimg-modal" data-toggle="modal">从公众号图文消息中选择</span>
										</p>
									</div>
									<div class="send-message hide">
										<p class="desc">订阅者点击该子菜单会收到以下消息，重设菜单内容</p>

										<div class="added-reply hide">
											<ul>
												<!-- <li class="added-nav" id="word"><span
													class="icon-pencil"></span><span class="ml10">文字</span></li> -->
												<li class="added-nav" id="imgword"><span
													class="icon-picture"></span><span class="ml10">图文消息</span>
												</li>
											</ul>

											<div class="imgwordarea hide">
												<div class="imgarea-content">
													<div class="select-from-depot">
														<div class="inner">
															<span class="icon-plus"></span> <span class="can-click"
																data-target="#photo-list-modal" data-toggle="modal">从素材库中选择</span>
														</div>
													</div>
													<div class="upload-img ">
														<div class="inner">
															<span class="icon-plus"></span> <span class="can-click">新建图文消息</span>
														</div>
													</div>
												</div>
											</div>
											<!--imgwordarea-->
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="menu-opr">
							<button class="btn">预览</button>
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

</body>
<script>
jQuery("#btn_start").on("click",function(){
	jQuery.ajax({
		type : "get",
		url : baseUrl + "wechat/copy/menus",
		dataType : "json",
		success : function(e){
			if(e.code==0){
				location.reload(true);
			}
		}
	});
});
jQuery("#btn_stop").on("click",function(){
	jQuery.ajax({
		type : "get",
		url : baseUrl + "wechat/delete/copy/menus",
		dataType : "json",
		success : function(e){
			if(e.code==0){
				location.reload(true);   
			}
		}
	});
});
</script>
</html>