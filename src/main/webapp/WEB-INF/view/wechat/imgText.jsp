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
						<div class="desc">
							<span>可创建最多3个一级菜单，每个一级菜单下可创建最多5个二级菜单。</span>
						</div>
						<div class="defined-manage">
							<div class="defined-menu">
								<div class="defined-menu-title">
									<span>菜单管理</span> <span class="icon-plus munu-plus fr" id="menu-add" ></span>
								</div>
								<ul class="wrap-menu">
									<c:forEach items="${menus }" var="menu" >
									<li class="menu" id=${menu.menuId } ><span class="tree-dropdown"><span class="resetMenu">${menu.menuName }</span> <span class="icon-plus fr submenu-add" ></span></span>
										<c:if test="${fn:length(menu.menus) > 0 && menu.menus != null}">
											<ul class="submenu">
												<c:forEach items="${menu.menus }" var="subMenu">
												<li  id="${subMenu.menuId }">${subMenu.menuName }</li>
												</c:forEach>
											</ul>
										</c:if>
									</li> 
									</c:forEach>
									<!-- <li class="menu" id="77">
										<span class="tree-dropdown">
										   <span class="resetMenu">水电费水电费</span>
										      <span class="icon-plus fr submenu-add"></span>
										</span>
										<ul class="submenu">
											<li id="78">水电费</li>
											<li id="79">是的</li>
										</ul>
									</li> -->
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
											<label for="">页面地址</label> <input type="text" />
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
											<!-- <div class="textarea">
												<div class="textarea-content">
													<div class="text-edit"></div>
													<div class="text-toolbar"></div>
												</div>
											</div> -->

											<!-- <div class="imgarea hide">
												<div class="imgarea-content">
													<div class="select-from-depot">
														<div class="inner">
															<span class="icon-plus"></span> <span class="can-click"
																data-target="#photo-list-modal" data-toggle="modal">从素材库中选择</span>
														</div>
													</div>
													<div class="upload-img ">
														<div class="inner">
															<span class="icon-plus"></span> <span class="">上传图片</span>
														</div>
													</div>
												</div>
											</div> -->
											<!--imgarea-->

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
							<button class="btn">保存并预览</button>
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

	<!-- 添加一级菜单的Modal -->
	<div class="modal fade hide" id="add-menu-name-modal-first-menu" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content add-menu-name-modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						添加 一级菜单
					</h4>
				</div>
				<div class="modal-body">
					<div>
						<span class="">还能添加 <span class="sub-num-first-menu"></span> 个 一级菜单，请输入名称（4个汉字或8个字母以内）</span>
					</div>
					<input type="text" style="width: 500px" id="first-menu-name" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">取消</button>
					<button type="button" class="btn " id="confirm-first-menu" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加二级菜单的Modal -->
	<div class="modal fade hide" id="addSubMenu" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content add-menu-name-modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						添加二级菜单
					</h4>
				</div>
				<div class="modal-body">
					<div>
						<span class="">还能添加 <span  class="subMenuAcount"></span>个二级菜单，请输入名称（4个汉字或8个字母以内）</span>
					</div>
					<input type="text" style="width: 500px" id="sub-menu-name" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">取消</button>
					<button type="button" class="btn " id="confirm-sub-menu" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>

	<!--选择图文消息模态框-->
	<!--选择图文消息模态框-->
	<div class="modal fade hide" id="select-wordimg-modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content select-wordimg-modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						选择图文消息 <span class="modal-title-num">一</span>级菜单
					</h4>
				</div>
				<div class="modal-body">
					<div class="search-wrap">
						<input type="search" />
						<button class="btn search-btn">搜索</button>
					</div>
					<div class="slect-list">
						<table class="table">
							<thead>
								<tr>
									<th>标题</th>
									<th class="text-right mr10">发布时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${automaticReplies }" var="replies"> 
								    <tr>
										<td>
										   <input type="hidden" id="${replies.mediaId }"/> 
										   <input type="radio" name="title" /> 
										   <span>${replies.title }</span>
										</td>
										<td class="text-right mr10">${replies.createTime }</td>
									</tr>
								</c:forEach>
								<!-- <tr>
									<td><input type="radio" name="title" /> <span>dfg</span></td>
									<td class="text-right mr10">2015年08月06日</td>
								</tr>
								<tr>
									<td><input type="radio" name="title" /> <span>dfg</span></td>
									<td class="text-right mr10">2015年08月06日</td>
								</tr>
								<tr>
									<td><input type="radio" name="title" /> <span>dfg</span></td>
									<td class="text-right mr10">2015年08月06日</td>
								</tr> -->
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">取消</button>
					<button type="button" class="btn " id="confirm" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<%=basePath%>js/wechat/wechat.js"></script>
</html>