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
		                    <span class="font-size-16 btn-color">流水单号查询：</span>
		                     <span class="video fr font-333" >视频帮助
		                            <span class="iconfont icon-shipin" style="margin-top: 1px;"></span>
		                        </span>
		                </div><!--table-toolbar-->
		                <div class="clearfix"></div>
		            </div><!--more-toolbar-->
		            <table class="table">
		              <thead>
		              </thead>
		              <tbody>
		              <tr>
		                  <td>输入流水单号</td>
		                  <td>按特定时间段查询</td>
		                  <td>选择部门</td>
		              </tr>
		              <tr>
		                  <td>
		                      <input type="search" placeholder=""/>
		                      <button class="btn search-button">搜索</button>
		                  </td>
		                  <td>
		                      <button class="btn">昨天</button>
		                      <button class="btn">今天</button>
		                      <input id="startTime" type="text" name="date" placeholder="起始时间" daysOffset="0" class="datetimepicker timepicker width100"/>
		                      <span>-</span>
		                      <input id="endTime" type="text" name="date" placeholder="截止时间"  daysOffset="30" class="datetimepicker timepicker width100"/>
		                  </td>
		                  <td>
		                      <select name="" id="" class="chzn-select" multiple="multiple" data-placeholder="全部门查询">
		                          <c:forEach items="${deptInfoDtoList}" var="deptInfoDto" varStatus="status">
		                             <option value="${deptInfoDto.deptId}">${deptInfoDto.deptName}</option>
		                          </c:forEach>
		                      </select>
		                  </td>
		
		              </tr>
		              </tbody>
		          </table>
		        </div>
		
		        <div class="widgetcontent">
		            <div class="more-toolbar" >
		              <div class="table-toolbar">
		                  <span class="font-size-16 btn-color">流水单号查询：</span>
		                  本次查询结果120张订单，劳动业绩：1000元， 套餐销售业绩：1000元，商品业绩：100元，开卡业绩：1000元，充卡业绩：1000元。
		                  <div class="table-pagination fr">
		                      <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
		                          10 <span class="iconfa-caret-down afont"></span>
		                      </button>
		                      <ul class="dropdown-menu">
		                          <li><a href="#">10</a></li>
		                          <li><a href="#">20</a></li>
		                          <li><a href="#">50</a></li>
		                          <li><a href="#">100</a></li>
		                      </ul>
		                      <div class="left-page page-disable"><i class="FontAwesome iconfa-caret-left afont"></i></div>
		                      <div class="right-page"><i class="FontAwesome iconfa-caret-right afont"></i></div>
		                  </div><!--table-pagination-->
		              </div><!--table-toolbar-->
		              <div class="clearfix"></div>
		          </div><!--more-toolbar-->
		            <table class="table table-bordered table-striped liushui-table">
		              <thead>
		              <tr>
		                  <th>水单号</th>
		                  <th>顾客</th>
		                  <th>消费时间
		                      <i class="FontAwesome iconfa-caret-up afont ml8"></i><i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i></th>
		                  <th style="position: relative" class="wthn100">
		                      <span class="dropdown-toggle" data-toggle="dropdown">
		                          项目名称
		                          <i class="FontAwesome iconfa-caret-down afont ml8 " ></i>
		                      </span>
		                      <ul class="dropdown-menu">
		                          <li><a href="#">美发</a></li>
		                          <li><a href="#">美容</a></li>
		                          <li><a href="#">足浴</a></li>
		                      </ul>
		                  </th>
		                  <th>应收</th>
		                  <th>现金</th>
		                  <th>银联</th>
		                  <th style="position: relative" >
		                      <span class="dropdown-toggle" data-toggle="dropdown">
		                      网络支付 <i class="FontAwesome iconfa-caret-down afont ml8"></i>
		                      </span>
		                      <ul class="dropdown-menu">
		                          <li><a href="#">支付宝</a></li>
		                          <li><a href="#">微信</a></li>
		                          <li><a href="#">团购</a></li>
		                      </ul>
		                  </th>
		                  <th>卡金</th>
		                  <th>优惠券</th>
		                  <th>其它</th>
		                  <!-- <th>挂帐</th> -->
		                  <th>签单</th>
		                  <th>实收</th>
		              </tr>
		              </thead>
		              <tbody>
		              <tr>
		                  <td ><a class="can-click">20150825001</a></td>
		                  <td class="can-click" data-toggle="modal" data-target="#member-data">范冰冰</td>
		                  <td>2015-07-22 10:45</td>
		                  <td class="wthn100 ellipsis-text" data-toggle="tooltip" data-placement="right" title="单剪、单剪、单剪、单剪、单剪、单剪">
		                                                               单剪、单剪、单剪、单剪、单剪、单剪
		                  </td>
		                  <td>1000</td>
		                  <td>1000</td>
		                  <td>1000</td>
		                  
		                  <td>  <i class="iconfont icon-zhifubao fl"></i> <span class="fl ml10">1000</span></td>
		                  <td>1000</td>
		                  <td>1000</td>
		                  <td>1000</td>
		                  <td>1000</td>
		                  <td>1000</td>
		                  <td>1000</td>
		              </tr>
		              <tr>
		                  <td ><a class="can-click">20150825002</a></td>
		                  <td class="can-click" data-toggle="modal" data-target="#member-data">刘一龙</td>
		                  <td>2015-07-22 10:45</td>
		                  <td class="ellipsis-text wthn100" data-toggle="tooltip" data-placement="right" title="梨花烫、梨花烫、梨花烫、梨花烫、梨花烫">
		                      梨花烫、梨花烫、梨花烫、梨花烫、梨花烫
		                  </td>
		                  <td>199</td>
		                  <td>0</td>
		                  <td>99</td>
		                  <td> <i class="iconfont icon-weixin fl"></i> <span class="fl ml10">1000</span></td>
		                  <td>0</td>
		                  <td>0</td>
		                  <td>0</td>
		                  <td>0</td>
		                  <td>0</td>
		                  <td>90</td>
		              </tr>
		              <tr>
		                  <td ><a class="can-click">20150825003</a></td>
		                  <td>散客（男）</td>
		                  <td>2015-07-21 10:45</td>
		                  <td  class="ellipsis-text wthn100" data-placement="right" data-toggle="tooltip" title="梨花烫、梨花烫、梨花烫、梨花烫、梨花烫">
		                      洗剪吹、洗剪吹、洗剪吹、洗剪吹、洗剪吹
		                  </td>
		                  <td>39</td>
		                  <td>10</td>
		                  <td>0</td>
		                  <td><i class="iconfont icon-tuangou fl"></i> <span class="fl ml10">1000</span></td>
		                  <td>0</td>
		                  <td>0</td>
		                  <td>0</td>
		                  <td>0</td>
		                  <td>10</td>
		                  <td>29</td>
		              </tr>
		              <tr>
		                  <td colspan="14" class="text-right">
		                      <button class="btn fr">
		                          <img src="<%=basePath %>images/out_icon.png" alt="" class="vatp"/>
		                          <span class="ml10">导出</span>
		                      </button>
		                  </td>
		              </tr>
		              </tbody>
		          </table>
		        </div>
		    </div>
		</div>
	
	<%@ include file="/template/memberData.jsp" %>
	
	<script type="text/javascript">
	   
	</script>	
	<script type="text/javascript" src="<%=basePath %>js/keepAccounts/queryParticulars.js"></script>
   </div>
     <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
  </div>

</body>

</html>