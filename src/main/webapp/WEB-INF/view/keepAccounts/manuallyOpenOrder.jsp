<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<body>

  <div class="mainwrapper">
   <!--loading start-->
   <%@ include file="/loading.jsp" %>
    <!--loading end-->
   <%@ include file="/menu.jsp" %>
   <div class="rightpanel" style="margin-left: 200px;">
      <%@ include file="/top.jsp" %>
      <div class="maincontent">
	    <div class="contentinner">
	
	        <div class="widgetcontent">
	            <div class="more-toolbar" >
	                <div class="table-toolbar">
	                    <!-- <span class="mr10">会员:</span><input type="search" placeholder="手机号" id="account-time" name="date"/>
	                    <button class="button-search btn" style="margin-left: -10px;">查询</button> -->
	                    <div class="p-part-first" name = "memberTR">
	                        <span class="ml10">会员:</span>
	                        <div style="display: inline-block;" name= "seekTD">
	                           <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号"/><span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
	                        </div>
	                        <div  name="resultTD" style="display: none;">
                                <span data-toggle="modal"  data-target="#member-data" class="can-click"></span>
                                <input type="hidden" name = "memberId" onchange="changeMember(this)">
                                <span class="ml10" name = "balance"></span>
                                <span class="iconsweets-magnifying ml10 mt-5" name="breakName"></span>
                            </div>
	                    </div>
	                    <div class="sex-select ml30" name='sexDIV'>
	                        <span class="ml10">散客:</span>
	                        <label class="radio ml30"  for="">
	                            <input type="radio" name = "sex" value="男" checked/> <span class="ml5">男</span>
	                        </label>
	                        <label class="radio ml30" for="">
	                            <input type="radio" name = "sex" value="女"/> <span class="ml5">女</span>
	                        </label>
	                    </div>
	                </div>
	                <div class="clearfix"></div>
	            </div><!--more-toolbar-->
	            <!--kd-select-content start-->
	            <div class="kd-select-content">
	
	                <div class="kai-bumen kd-bmlist">
	                    <ul class="flex-box trangle">
	                        <c:forEach items="${list}" var="dept" varStatus="status">
	                           <li <c:if test="${status.index == 0}">class="flex-item bm active"</c:if> <c:if test="${status.index != 0}">class="flex-item bm"</c:if> style="cursor:pointer;" onclick="changeDept(${dept.deptId})">
		                            <span>${dept.deptName}</span>
		                            <em></em>
		                        </li>
	                        </c:forEach>
	                    </ul>
	                </div>
	                
	                <c:forEach items="${list}" var="dept" varStatus="status">
	                    <div <c:if test="${status.index == 0}">class="select-target"</c:if> <c:if test="${status.index != 0}">class="select-target hide"</c:if> name= "${dept.deptId}">
		                    <div class="select-style-wrap">
		
		                        <!--选择类型-->
		                        <div class="select-list">
		                            <ul>
		                                <li class="select-item active" style="cursor:pointer;" onclick="changeType(this, 'project')">项目</li>
		                                <li class="select-item" style="cursor:pointer;" onclick="changeType(this, 'combo')">套餐</li>
		                                <li class="select-item" style="cursor:pointer;" onclick="changeType(this, 'goods')">商品</li>
		                            </ul>
		                        </div>
		
		                        <!--选择类型的系列-->
		                        <div class="selected-child-select" name = "projectUL">
		                            <ul>
		                                <c:forEach items="${dept.projectCategoryList}" var="projectCategory" varStatus="projectStatus">
		                                    <li <c:if test="${projectStatus.index == 0}">class="selected-item active"</c:if> <c:if test="${projectStatus.index != 0}">class="selected-item"</c:if> style="cursor:pointer;" categoryid = "${projectCategory.categoryId}" onclick="changeCategory(this, ${projectCategory.categoryId}, 'project')">${projectCategory.categoryName}</li>
		                                </c:forEach>
		                            </ul>
		                        </div>
		                        
		                        <div class="selected-child-select hide" name = "comboUL">
		                            <ul>
		                                <li class="selected-item active" style="cursor:pointer;">套餐</li>
		                            </ul>
		                        </div>
		                        
		                        <div class="selected-child-select hide" name = "goodsUL">
		                            <ul>
		                                <c:forEach items="${dept.goodsCategoryList}" var="goodsCategory" varStatus="goodsStatus">
		                                    <li <c:if test="${goodsStatus.index == 0}">class="selected-item active"</c:if> <c:if test="${goodsStatus.index != 0}">class="selected-item"</c:if> style="cursor:pointer;" categoryid = "${goodsCategory.categoryId}" onclick="changeCategory(this, ${goodsCategory.categoryId}, 'goods')">${goodsCategory.categoryName}</li>
		                                </c:forEach>
		                            </ul>
		                        </div>
		
		                    </div>
		                    
		                    
		                    <!--选择具体的项目-->
		                    <c:forEach items="${dept.projectCategoryList}" var="projectCategory" varStatus="projectStatus">
		                        <div <c:if test="${projectStatus.index == 0}">class="all-kind-wrap"</c:if> <c:if test="${projectStatus.index != 0}">class="all-kind-wrap hide"</c:if> name= "project" categoryid = "${projectCategory.categoryId}">
			                        <ul>
			                            <c:forEach items="${projectCategory.projectList}" var="project">
			                                <li class="detail-item" style="cursor:pointer;" onclick="chooceProject(${project.projectId}, '${project.projectName}', ${project.projectPrice}, 1)">
				                                <div><span class="name">${project.projectName}</span></div>
				                                <div>项目价格:<span class="item-price">￥${project.projectPrice}</span></div>
				                            </li>
			                            </c:forEach>
			                        </ul>
			                    </div>
		                    </c:forEach>
		                    <!--选择具体的项目-->
		                    
		                    <div class="all-kind-wrap hide" name= "combo">
		                        <ul>
		                            <c:forEach items="${dept.comboInfoList}" var="comboInfo">
		                                <li class="detail-item" style="cursor:pointer;" onclick="chooceProject(${comboInfo.comboId}, '${comboInfo.comboName}', ${comboInfo.comboSalePrice}, 3)">
			                                <div><span class="name">${comboInfo.comboName}</span></div>
			                                <div>套餐价格:<span class="item-price">￥${comboInfo.comboSalePrice}</span></div>
			                            </li>
		                            </c:forEach>
		                        </ul>
		                    </div>
		                    
		                    <c:forEach items="${dept.goodsCategoryList}" var="goodsCategory" varStatus="goodsStatus">
		                        <div class="all-kind-wrap hide" name= "goods" categoryid = "${goodsCategory.categoryId}">
			                        <ul>
			                            <c:forEach items="${goodsCategory.goodsBaseDtos}" var="goods">
			                                <li class="detail-item" style="cursor:pointer;" onclick="chooceProject(${goods.goodsId}, '${goods.goodsName}', ${goods.goodsPrice}, 2)">
				                                <div><span class="name">${goods.goodsName}</span></div>
				                                <div>商品价格:<span class="item-price">￥${goods.goodsPrice}</span></div>
				                            </li>
			                            </c:forEach>
			                        </ul>
			                    </div>
		                    </c:forEach>
		                    
		                </div>
	                </c:forEach>
	            </div>
	            <!--kd-select-content end-->
	        </div>
	        <!--widgetcontent end-->
	
	        <div class="widgetcontent">
	            <div class="more-toolbar" >
	                <div class="table-toolbar">
	                    <span class="font-size-16 btn-color">消费明细</span>
	
	                    <div class="fr">
	                        <span class="mr10">手工单号:</span>
	                        <input class="input-medium" type="text"/>
	                    </div>
	                </div>
	                <div class="clearfix"></div>
	            </div>
	
	            <div class="show-content">
	                <ul id = "showUL">
	                    <li class="xiaofei-item" name= "goodsNameLI">
	                        <div class="xiaofei-name">
	                            <span class="name mr20">商品销售</span>
	                        </div>
	
	                    </li>
	                    <li class="xiaofei-item" name= "comboNameLI">
	                        <div class="xiaofei-name">
	                            <span class="name mr20">套餐销售</span>
	                        </div>
	
	                    </li>
	                </ul>
	            </div>
	            <div class="btn fr w80 mr20 mt20" onclick="save()">开单</div>
	        </div>
	        <!--widgetcontent-->
	
	    </div>
	</div>
	
	<!--提示-->
	<div class="modal hide" id="saveModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content confirm">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="cancelModel();"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">提示</h4>
	            </div>
	
	            <div class="modal-body confirm-body">
	                                            未使用会员开单！是否继续执行散客开单？
	            </div>
	
	            <div class="modal-footer">
	                <a class="btn cancel-btn modal-cancel" onclick="cancelModel();">取消</a>
	                <a onclick="queren()" class="btn btn-primary save-btn modal-confirm" data-dismiss="modal" href="#">继续</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<%@ include file="/template/memberData.jsp" %>
	<script type="text/javascript">
	   var employeeInfoListStr = '${employeeInfoList}';
	   var employeeInfoList = eval("(" + employeeInfoListStr + ")");
	</script>	
	<script type="text/javascript" src="<%=basePath %>js/keepAccounts/manuallyOpenOrder.js"></script>
   </div>
     <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
  </div>

</body>
</html>