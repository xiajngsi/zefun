<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>

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
		        <div id="custom-toolbar" >
		            <div class="table-toolbar">
		                <button class="btn" onclick="showAddView()">添加会员卡</button>
		            </div>
		            <div class="table-pagination">
		                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
		                    ${page.pageSize } <span class="caret"></span>
		                </button>
		                <ul class="dropdown-menu">
		                    <li><a href="javascript:changePageSize(5)">5</a></li>
		                    <li><a href="javascript:changePageSize(10)">10</a></li>
		                    <li><a href="javascript:changePageSize(20)">20</a></li>
		                    <li><a href="javascript:changePageSize(50)">50</a></li>
		                    <li><a href="javascript:changePageSize(100)">100</a></li>
		                </ul>
		                <button class="btn" id="previousPageButton" onclick="previous()"><span class="icon-chevron-left"></span></button>
		                <button class="btn" id="nextPageButton" onclick="next()"><span class="icon-chevron-right"></span></button>
		            </div>
		        </div>
		
		        <table class="table table-bordered table-striped member-card-table">
		            <thead>
			            <tr>
			                <th>等级名称</th>
			                <th>项目折扣</th>
			                <th>商品折扣</th>
			                <th>获取方式</th>
			                <th>开卡提成</th>
			                <th>充值提成</th>
			                <th>积分规则</th>
			                <th>升级条件</th>
			                <th></th>
			            </tr>
		            </thead>
		            <tbody>
			            <c:forEach items="${page.results }" var="memberLevel">
			            	<tr id="${memberLevel.levelId}">
				                <td>${memberLevel.levelName }</td>
				                <td>${memberLevel.projectDiscount }</td>
				                <td>${memberLevel.goodsDiscount }</td>
				                <td>
				                	<c:set var="isMultiple" value="false" />
				                	<c:if test="${memberLevel.sellAmount > 0 }">
			                			现金${memberLevel.sellAmount }元购买
			                			<c:set var="isMultiple" value="true" />
			                		</c:if>
				                	<c:if test="${memberLevel.chargeAmount > 0 }">
				                		<c:if test="${isMultiple == 'true' }">
				                			&nbsp;或者&nbsp;
				                		</c:if>
			                			充值${memberLevel.chargeAmount }元获得
			                		</c:if>
				                </td>
				                <td>
				                	<c:choose>
				                		<c:when test="${memberLevel.registerCommissionType == 1 }">
				                			按业绩比例 ${memberLevel.registerCommissionAmount } %
				                		</c:when>
				                		<c:otherwise>
				                			按固定金额 ${memberLevel.registerCommissionAmount } 元
				                		</c:otherwise>
				                	</c:choose>
				                </td>
				                <td>
				                	<c:choose>
				                		<c:when test="${memberLevel.chargeCommissionType == 1 }">
				                			按业绩比例 ${memberLevel.chargeCommissionAmount } %
				                		</c:when>
				                		<c:otherwise>
				                			按固定金额 ${memberLevel.chargeCommissionAmount } 元
				                		</c:otherwise>
				                	</c:choose>
				                </td>
				                <td>消费${memberLevel.integralUnit }元可获得${memberLevel.integralNumber }积分</td>
				                <td>累计满${memberLevel.upgradeIntegral }积分</td>
				                <td>
				                    <span class="icon-edit" onclick="editMemberLevel(${memberLevel.levelId})" ></span>
				                    <span class="icon-remove-sign ml30" onclick="deleteMemberLevel(${memberLevel.levelId})"></span>
				                </td>
				            </tr>
			            </c:forEach>
		            </tbody>
		        </table>
		    </div>
		</div>
		<!-- 添加会员等级开始 -->
		<div class="modal fade hide" id="add-member-card" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content add-member-card">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title" id="myModalLabel">新增会员卡</h4>
		            </div>
		            <div class="modal-body">
		                <form action="<%=basePath %>memberLevel/action/add" class="editprofileform editMemberLevelForm" method="post">
		                	<input type="hidden" name="levelId" value="">
		                    <p>
		                        <label>等级名称:</label>
		                        <input type="text" name="levelName" class="input-xlarge" value="">
		                    </p>
		                    <p>
		                        <label>项目折扣:</label>
		                        <input type="text" name="projectDiscount" class="input-xlarge" value=""><span class="input-word">折</span>
		                    </p>
		                    <p>
		                        <label>商品折扣:</label>
		                        <input type="text" name="goodsDiscount" class="input-xlarge" value=""><span class="input-word">折</span>
		                    </p>
		                    <p>
		                        <label>现金售卡:</label>
		                        <input type="text" name="sellAmount" class="input-xlarge" value=""><span class="input-word">元</span>
		                    </p>
		                    <p>
		                        <label>充值开卡:</label>
		                        <input type="text" name="chargeAmount" class="input-xlarge" value=""><span class="input-word">元</span>
		                    </p>
		                    <p>
		                        <label>积分规则:</label>
		                        <span>每消费</span>
		                        <input type="text" name="integralUnit" class="input60 ml10" value="1.5">元
		                        <span class="ml10">获得</span>
		                        <input type="text" name="integralNumber" class="input60 ml10" value="1">分
		                        <span class="ml10"></span>
		                    </p>
		                    <p>
		                        <label>升级条件:</label>
		                        <span class="input-small second-word">累计积分达到</span>
		                        <input type="text" name="upgradeIntegral" class="input80 mr10" value="">分自动升级
		                    </p>
		                    <p class="switch-p">
		                        <label>开卡提成:</label>
		                        <!-- <input id="switch-onText" type="checkbox" checked  data-size="small" data-handle-width="55" data-on-text="业绩金额" data-off-text="固定金额" data-off-color="primary"> -->
		                        <input type="text" name="registerCommissionAmount" class="input-small ml30 two-part-input switch-after-input" value=""><span class="switch-input-word">元</span>
		                        <!--<input id="switch-onText" type="checkbox" name='my-checkbox' checked data-on-text="Yes" data-off-text="固定金额" data-on-text="业绩金额">-->
		                        <span class="formwrapper">
		                                <select id="registerCommissionType"  class="chzn-select input-small" >
		                                    <option value="1">固定金额</option>
		                                    <option value="2">业绩比例</option>
		                                </select>
		                        </span>
		                    </p>
		                    <p class="switch-p">
		                        <label>充值提成:</label>
		                        <input id="switch-onText" type="checkbox" checked  data-size="small" data-handle-width="55" data-on-text="业绩金额" data-off-text="固定金额" data-off-color="primary">
		                        <input type="text" name="chargeCommissionAmount" class="input-small ml30 two-part-input switch-after-input" value=""><span class="switch-input-word">元</span>
		                        <!--<input id="switch-onText" type="checkbox" name='my-checkbox' checked data-on-text="Yes" data-off-text="固定金额" data-on-text="业绩金额">-->
		                        <!--<span class="formwrapper">
		                                <select data-placeholder="Choose a Country..."  class="chzn-select input-small" >
		                                    <option value="United States">固定金额</option>
		                                    <option value="United Kingdom">业绩比例</option>
		                                </select>
		                        </span>-->
		                    </p>
		                </form>
		            </div>
		            <div class="modal-footer">
		                <a class="btn modal-cancel" href="#">取消</a>
		                <a class="btn btn-primary modal-confirm" href="javascript:addOrEditMemberLevel();">确定</a>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 添加会员等级结束 -->
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->
<script>
    jQuery(function(){
        jQuery("input[type=\"checkbox\"], input[type=\"radio\"]").not("[data-switch-no-init]").bootstrapSwitch();
    });
    
    //获取加载页面时的页码信息
    var pageNo = "${page.pageNo}";
    var pageSize = "${page.pageSize}";
    var totalPage = "${page.totalPage}"
</script>
<script type="text/javascript" src="<%=basePath %>js/member/memberLevel.js"></script>
<script type="text/javascript">
	checkPageButton();
	//手动更新选择项，两步顺序执行,
	jQuery("#registerCommissionType").val(1);
	jQuery("#registerCommissionType").trigger("liszt:updated");
	//获取选择项的值
	jQuery("#registerCommissionType :selected").val();
	//参考api：http://harvesthq.github.io/chosen/#change-update-events
</script>
</body>
</html>