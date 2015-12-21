<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		         <c:if test="${session_key_user_info.storeType != 3 }">
			        <div class="widgetcontent">
			            <div class="more-toolbar" >
			                <div class="table-toolbar">
			                    <span class="font-size-16 btn-color mr10">添加会员卡种类</span>
			                    <button class="btn" data-toggle="modal" data-target="#toLeadModal">导入会员卡</button>
			                </div>
			                <div class="clearfix"></div>
			            </div>
			            <form class="editMemberLevelForm">
				            <table class="table collect-money-table">
				                <thead>
				                <tr>
				                    <th>会员卡名称</th>
				                    <th>项目折扣<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="折后价为原价乘以设置的百分比，不打折请填写100"></th>
				                    <th>商品折扣<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="折后价为原价乘以设置的百分比，不打折请填写100"></th>
				                    <th>现金售卡<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="类似资格卡,通过现金购买的方式得到，所付现金不存入卡中"></th>
				                    <th>充值开卡</th>
				                    <th>最低充值</th>
				                    <th>充值金额归属</th>
				                </tr>
				                </thead>
				                <tbody>
				                <tr>
				                    <td><input type="text" class="input80" name="levelName" /></td>
				                    <td><input type="text" class="input80" name="projectDiscount" /><span class="percent-symbol">%</span></td>
				                    <td><input type="text" class="input80" name="goodsDiscount" /><span class="percent-symbol">%</span></td>
				                    <td><input type="text" class="input80" name="sellAmount" /><span class="percent-symbol">元</span></td>
				                    <td><input type="text" class="input80" name="chargeAmount" /><span class="percent-symbol">元</span></td>
				                    <td><input type="text" class="input80" name="chargeMinMoney" /><span class="percent-symbol">元</span></td>
				                    <td><select name="chargeBelongType" class="chzn-select wthn100">
				                        <option value="1">开卡门店</option>
				                        <option value="2">充值门店</option>
				                    </select></td>
				                </tr>
				                <tr>
				                    <td colspan="7">
				                        <div class="p-part-first">
				                            <span class="mr10 label12 font-bold">积分计算方式：</span>
				                            每消费<input type="number" name="integralUnit" class="input30" value="1"><span class="percent-symbol">元</span>
				                            <span class="ml10">获得</span>
				                            <input type="number" name="integralNumber" class="input30" value="1"><span class="percent-symbol">分</span>
				                        </div>
				                        <a class="btn btn-primary fr" onclick="addOrEditMemberLevel()">&nbsp;保&nbsp;&nbsp;存&nbsp;</a>
				                        <a class="btn btn-primary fr mr10" onclick="resetForm('.editMemberLevelForm');">&nbsp;清&nbsp;&nbsp;空&nbsp;</a>
				                    </td>
				                </tr>
				                </tbody>
				            </table>
				            <input type="hidden" name="levelId" style="width: 0px; height: 0px;" >
				        </form>
			        </div>
		        </c:if>
		
		        <div class="more-toolbar" >
		            <div class="table-toolbar">
		                <span class="font-size-16 btn-color mr10">当前会员卡种类</span>
		                <div class="table-pagination">
		                    <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
		                        ${page.pageSize } <span class="iconfa-caret-down afont"></span>
		                    </button>
		                    <ul class="dropdown-menu">
	                            <li><a href="javascript:changePageSize(10)">10</a></li>
	                            <li><a href="javascript:changePageSize(20)">20</a></li>
	                            <li><a href="javascript:changePageSize(50)">50</a></li>
	                            <li><a href="javascript:changePageSize(100)">100</a></li>
			                </ul>
		                    <div class="left-page" id="previousPageButton" onclick="previous()"><i class="FontAwesome iconfa-caret-left afont"></i></div>
		                    <div class="right-page" id="nextPageButton" onclick="next()"><i class="FontAwesome iconfa-caret-right afont"></i></div>
		                </div>
		            </div>
		            <div class="clearfix"></div>
		        </div>
		
		        <table class="table table-bordered table-striped member-card-table">
		            <thead>
		              <tr>
		                  <th>会员卡名称</th>
		                  <th>项目折扣</th>
		                  <th>商品折扣</th>
		                  <th>开卡条件</th>
		                  <th>最低充值</th>
		                  <th>充值金额归属</th>
		                  <th>积分计算方式</th>
		                  <c:if test="${session_key_user_info.storeType != 3 }">
		                      <th>操作</th>
		                  </c:if>
		              </tr>
		            </thead>
		            <tbody>
			            <c:forEach items="${page.results }" var="memberLevel">
			                 <tr id="${memberLevel.levelId }">
                               <td>${memberLevel.levelName }
                                <c:if test="${memberLevel.isDefault == 1 }"><span class="font-999">默认等级</span></c:if>
                               </td>
                               <td>${memberLevel.projectDiscount }%</td>
                               <td>${memberLevel.goodsDiscount }%</td>
                               <td>
                                    <c:set var="isMultiple" value="false" />
                                    <c:if test="${memberLevel.sellAmount > 0 }">
                                        ${memberLevel.sellAmount }元购买
                                        <c:set var="isMultiple" value="true" />
                                    </c:if>
                                    <c:if test="${memberLevel.chargeAmount > 0 }">
                                        <c:if test="${isMultiple == 'true' }">
                                            &nbsp;或&nbsp;
                                        </c:if>
                                        充值${memberLevel.chargeAmount }元
                                    </c:if>
                               </td>
                               <td>${memberLevel.chargeMinMoney }元</td>
                               <td>
                                   <c:choose>
                                      <c:when test="${memberLevel.chargeBelongType == 1 }">
                                          开卡门店
                                      </c:when>
                                      <c:otherwise>
                                          充值门店
                                      </c:otherwise>
                                   </c:choose>
                               </td>
                               <td>${memberLevel.integralUnit }元 = ${memberLevel.integralNumber }积分</td>
                               <c:if test="${session_key_user_info.storeType != 3 }">
	                               <td>
	                                   <span class="iconfont icon-dizhixiugai" onclick="editMemberLevel(${memberLevel.levelId})"></span>
	                                   <c:if test="${memberLevel.isDefault == 0 }">
		                                   <span class="iconfont icon-xx ml10" onclick="deleteMemberLevel(${memberLevel.levelId})"></span>
	                                   </c:if>
	                               </td>
                               </c:if>
                             </tr>
			            </c:forEach>
		            </tbody>
		        </table>
		    </div>
		</div>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->
<div class="modal hide in" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <div class="modal-body" style="height: 150px;">
                <form action="" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                    <p>
                        <label>文件位置选择:</label>
                        <input type="file" name="filename" id="file" class="input-large">
                        <label>之前的服务商:</label>
                        <select data-placeholder="服务商名称" class="chzn-select" name="storeName">
			            		<option value="盛传">盛传服务商</option>
			            </select>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="#" id="confirm" data-dismiss="modal" onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>
<script>
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
	
	
function UpladFile() {
    var fileObj = document.getElementById("file").files[0]; // 获取文件对象
    var FileController = baseUrl +"memberLevel/action/importexcle";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("storeName", jQuery("select[name='storeName']").val());       
    form.append("file", fileObj);    // 文件对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (e) {
    	/*dialog(xhr.responseText);*/
    	var json = eval("("+xhr.responseText+")");
    	if(json.code!=0){
    		dialog(json.msg);
    		return;
    	}
        dialog(json.msg);
        setTimeout(function(){
        	location.reload();
    	},300);
       
    };
    xhr.send(form);
}
</script>
</body>
</html>