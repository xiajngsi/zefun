<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/head.jsp" %>
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
      	<div class="maincontent">
			<div class="contentinner">
				<div class="widgetcontent">
		            <div class="more-toolbar" >
		                <div class="table-toolbar">
		                    <table class="table">
		                        <tr>
		                            <td>
		                                订单搜索
		                                <input id="ipt-search-phone" type="search" placeholder="输入流水单号/会员手机号"/>
                                        <button id="btn-search-phone" class="btn search-button" onclick="btnSearchPhone();">搜索</button>
		                            </td>
		                            <td>
		                                按特定时间段查询
		                                <input id="startDate" type="text" name="date" placeholder="起始时间" daysOffset="0" class="input120"/>
					                    <input id="endDate" type="text" name="date" placeholder="截止时间"  daysOffset="0" class="input120"/>
					                    <button id="btn-search-time" class="btn" onclick="btnSearchTime();">查询</button>
		                            </td>
		
		                        </tr>
		                    </table>
		                </div><!--table-toolbar-->
		                <div class="clearfix"></div>
		            </div><!--more-toolbar-->
	           </div>
	
	           <div class="widgetcontent">
	             <div class="more-toolbar" >
	                <div class="table-toolbar">
	                    <span class="font-size-16 btn-color" id="searchDate">${searchDate }</span>
	
	                    <div class="table-pagination">
	                        <table class="ls-detail fr">
	                            <tr>
	                                <td>应收款：</td>
	                                <td id="receivableAmount">${countBook.receivableAmount }</td>
	                                <td>礼金抵扣：</td>
	                                <td id="giftAmount">${countBook.giftAmount }</td>
	                                <td>优惠券抵扣：</td>
	                                <td id="couponAmount">${countBook.couponAmount }</td>
	                                <td>套餐抵扣：</td>
                                    <td id="comboAmount">${countBook.comboAmount }</td>
	                                <td>实收款：</td>
	                                <td id="realPrice">${countBook.realPrice }</td>
	                            </tr>
	                        </table>
	                    </div><!--table-pagination-->
	                </div><!--table-toolbar-->
	                <div class="clearfix"></div>
	             </div><!--more-toolbar-->
	             <table class="table table-bordered ls-detai-table">
	              <thead>
		              <tr>
		                  <td colspan="5">现金营收付款方式</td>
		                  <td colspan="4">门店实收业绩分布</td>
		              </tr>
	              </thead>
	              <tbody>
		              <tr>
		                  <th>现金</th>
		                  <th>银联</th>
		                  <th>微信</th>
		                  <th>支付宝</th>
		                  <th>划卡</th>
		                  <th>劳动成绩</th>
		                  <th>商品销售</th>
		                  <th>套餐销售</th>
		                  <th>开卡充值</th>
		              </tr>
		              <tr>
		                  <td><span id="cashAmount">${countBook.cashAmount }</span></td>
		                  <td><span id="unionpayAmount">${countBook.unionpayAmount }</span></td>
		                  <td><span id="wechatAmount">${countBook.wechatAmount }</span></td>
		                  <td><span id="alipayAmount">${countBook.alipayAmount }</span></td>
		                  <td><span id="cardAmount">${countBook.cardAmount }</span></td>
		                  <td><span id="projectSalesAmount">${projectSalesAmount}</span></td>
		                  <td><span id="goodsSalesAmount">${goodsSalesAmount}</span></td>
		                  <td><span id="comboSalesAmount">${comboSalesAmount}</span></td>
		                  <td><span id="cardSalesAmount">${cardSalesAmount}</span></td>
		              </tr>
	              </tbody>
	            </table>
	            <div class="more-toolbar" >
	              <div class="table-toolbar">
	                  <span class="font-size-16 btn-color">流水详情</span>
	
	                  <div class="table-pagination fr">
	                      <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
	                          10 <span class="iconfa-caret-down afont"></span>
	                      </button>
	                      <ul class="dropdown-menu">
	                          <li><a href="javascript:changePageSize(10);" onclick="changePageSize(10);">10</a></li>
	                          <li><a href="javascript:changePageSize(20);" onclick="changePageSize(20);">20</a></li>
	                          <li><a href="javascript:changePageSize(50);" onclick="changePageSize(50);">50</a></li>
	                          <li><a href="javascript:changePageSize(100);" onclick="changePageSize(100);">100</a></li>
	                      </ul>
	                      <div id="previousPageButton" class="left-page"><i class="FontAwesome iconfa-caret-left afont"></i></div>
	                      <div id="nextPageButton" class="right-page"><i class="FontAwesome iconfa-caret-right afont"></i></div>
	                  </div><!--table-pagination-->
	              </div><!--table-toolbar-->
	              <div class="clearfix"></div>
	            </div><!--more-toolbar-->
	            <table class="table table-bordered table-striped liushui-table">
	              <thead>
	              <tr>
	                  <th>流水单号</th>
	                  <th>顾客</th>
	                  <th>消费时间
	                    <div class="paixu">
	                      <i onclick="changeQueryDirect(2);" class="FontAwesome iconfa-caret-up afont ml8"></i>
	                      <i onclick="changeQueryDirect(1);" class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th>服务内容</th>
	                  <th>应收</th>
	                  <th>现金</th>
	                  <th>银联</th>
	                  <th>网络支付</th>
	                  <th>卡金</th>
	                  <th>套餐</th>
	                  <th>礼金</th>
	                  <th>优惠券</th>
	                  <th>实收</th>
					  <th>操作</th>
	              </tr>
	              </thead>
	              <tbody id="tbody-data">
	              <c:forEach var="daybook" items="${pageInfo.results}" varStatus="status">
	              	<tr>
	                  <td data-toggle="modal" data-target="#liushui-search" onclick="updateSelectOrder(${daybook.orderId})"><a class="can-click">${daybook.orderCode}</a></td>
	                  
	                  	<c:choose>
		                  <c:when test="${daybook.memberId == null}">
		                  <td>
		                  	散客（${daybook.sex}）
		                  </td>
		                  </c:when>
		                  <c:otherwise>
		                  <td class="can-click" data-toggle="modal" data-target="#member-data" onclick="selectMemberInfo(${daybook.memberId})">
		                  ${daybook.memberName}
		                  </td>
		                  </c:otherwise>
		                 </c:choose>
	                  
	                  <td>${daybook.createTime}</td>
	                  <td class="wthn100 ellipsis-text" data-toggle="tooltip" data-placement="right" title="${daybook.projectName}">
	                     ${daybook.projectName}
	                  </td>
	                  <td>${daybook.realPrice}</td>
	                  <td>${daybook.cashAmount}</td>
	                  <td>${daybook.unionpayAmount}</td>
	                  <td>
		                  <c:choose>
		                  	<c:when test="${daybook.wechatAmount == 0 and daybook.alipayAmount == 0}">
		                  		0
		                  	</c:when>
		                  	<c:when test="${daybook.wechatAmount > daybook.alipayAmount}">
		                  		<i class="iconfont icon-weixin fl"></i>
				                  <span class="fl ml10">
				                  	${daybook.wechatAmount + daybook.alipayAmount}
				                  </span>
		                  	</c:when>
		                  	<c:when test="${daybook.alipayAmount > daybook.wechatAmount}">
		                  		<i class="iconfont icon-zhifubao fl"></i>
				                  <span class="fl ml10">
				                  	${daybook.wechatAmount + daybook.alipayAmount}
				                  </span>
		                  	</c:when>
		                  	<c:otherwise>
		                  		0
		                  	</c:otherwise>
		                  </c:choose>
	                  </td>
	                  <td>${daybook.cardAmount}</td>
	                  <td>${daybook.comboAmount}</td>
	                  <td>${daybook.giftAmount}</td>
	                  <td>${daybook.couponAmount}</td>
	                  <td>${daybook.realPay}</td>
					  <td><span class="iconfa-trash project-icon" onclick="deleteOrder(${daybook.orderId}, this)"></span></td>
	              </tr>
	              </c:forEach>
	              </tbody>
	          </table>
			</div>
		</div>
   	</div>
    <!--RIGHT PANEL结束 -->
	
	<!--流水查询模态框-->
	<div class="modal hide" id="liushui-search" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content liushui-data">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" onclick="cancelModel()">&times;</span></button>
	                <span id="orderCodeModel"></span>
	                &nbsp;&nbsp;&nbsp;&nbsp;
	                <span></span>
	            </div>
	            <div class="modal-body">
	                    <table class="table price">
	                        <thead>
	                            <tr>
	                                <td>折扣</td>
	                                <td>现金</td>
	                                <td>银联</td>
	                                <td>微信</td>
	                                <td>支付宝</td>
	                                <td>卡金</td>
	                                <!-- <td>挂账</td> -->
	                                <td>优惠抵扣</td>
	                                <td>实收</td>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <tr>
	                                <td><span class="red" id = "discountAmountModel"></span></td>
	                                <td><input type="text" name = "cashAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "unionpayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "wechatAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "alipayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "cardAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <!-- <td><input type="text" name = "debtAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td> -->
	                                <td><span class="red" id = "privilegeModel"></span></td>
	                                <td><span class="red" id = "realAmountModel"></span></td>
	                            </tr>
	                        </tbody>
	                    </table>
	                    <!-- 基本信息开始 -->
	                    <div class="expense" id = "detailExpense">
	                            
                        </div>
	                </div>
	            </div><!--modal-body-->
	            <!-- 基本信息结束 -->
                <div class="ls-footer clearfix">
                    <div class="fr">
                        <button class="btn" aria-hidden="true" onclick="cancelModel()" id = "cancelModel" data-dismiss="modal" aria-label="Close">取消</button>
                        <button class="btn" onclick="confirmModel()">确定</button>
                    </div>
                </div>
	        </div><!--modal-content-->
	    </div><!--modal-dialog-->   
	    
	    <!-- 会员等级 -->
	    <div class="modal hide" id="member-level-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content rotating-setting-modal" style="width: 450px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h5 class="modal-title" id="myModalLabel">调整会员等级</h5>
	            </div>
	            <div class="modal-body">
	                <form action="" class="editprofileform" method="post" style="padding-bottom: 42px;">
	                    <table class="table">
	                      <tbody id = "modelTbody">
	                        <tr id = "topTR">
	                            <td colspan="2">
	                                                                                               请选择下列会员等级
	                            </td>
	                        </tr>
	                        
	                        </tbody>
	                    </table>
	                </form>
	            </div><!--modal body-->
	
	            <div class="modal-footer">
	                <a class="btn modal-cancel" href="#" data-dismiss="modal" aria-label="Close">取消</a>
	                <a class="btn btn-primary modal-confirm" href="#" onclick="updateMemberLevel()">确定</a>
	            </div>
	        </div>
	        
	        
	    </div>
	</div> 

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<%@ include file="/template/memberData.jsp" %>
<script type="text/javascript">
//获取加载页面时的页码信息
var pageNo = "${pageInfo.pageNo}";
var pageSize = "${pageInfo.pageSize}";
var totalPage = "${pageInfo.totalPage}";
</script>
<script type="text/javascript" src="<%=basePath %>js/daybook/daybook.js"></script>
</body>
</html>