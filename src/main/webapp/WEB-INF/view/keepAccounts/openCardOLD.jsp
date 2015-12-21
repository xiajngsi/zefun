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
	        <h4 class="widgettitle">
	            <span class="dingdanzhuantai">充值转账</span>
	            <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span>
	        </h4>
	
	        <div class="widgetcontent">
	            <div class="more-toolbar" >
	                <div class="table-toolbar">
	                    <label for="">新增会员</label>
	                </div>
	                <div class="clearfix"></div>
	            </div><!--more-toolbar-->
	            <table class="table collect-money-table">
	                <thead>
	                <tr>
	                    <th class="dropdown-toggle" data-toggle="dropdown">手机号</th>
	                    <th>姓名</th>
	                    <th>性别</th>
	                    <th class="dropdown-toggle" data-toggle="dropdown">会员级别</th>
	                    <th>开卡方式</th>
	                    <th>开卡要求</th>
	                </tr>
	                </thead>
	                <tbody>
	                <tr>
	                    <td><input type="text" name = "phone" class="input100"/></td>
	                    <td><input type="text" name = "name" class="input80"/></td>
	                    <td><input type="checkbox" name="sex" value="男" class="lcs_check" checked="checked" autocomplete="on" /></td>
	                    <td>
	                        <select data-placeholder="" name = "levelId" class="chzn-select input-small" onchange="changeMemberLevel(this)">
	                           <c:forEach items="${memberLevelList}" var="memberLevel" varStatus="status">
	                                <option value="${memberLevel.levelId}">${memberLevel.levelName}</option>
	                           </c:forEach>
	                        </select>
	                    </td>
	                    <td>
	                        <select data-placeholder=""  name= "openType" class="chzn-select input-small" onchange="changeOpenCard(this);">
	                            <c:if test="${memberLevels.chargeAmount != 0}">
	                                <option value="1">充值开卡</option>
	                            </c:if>
	                            <c:if test="${memberLevels.sellAmount != 0}">
	                                <option value="2">现金购卡</option>
	                            </c:if>
	                        </select>
	                    </td>
	                    <td id="require">
	                        <c:if test="${memberLevels.chargeAmount != 0}">
	                             <span name="1" class="mr10">
		                                                                                  需充值：
		                           <span class='red' >${memberLevels.chargeAmount}</span>
		                                                                                 元
		                        </span>
	                        </c:if>
	                        <c:if test="${memberLevels.sellAmount != 0 && memberLevels.chargeAmount != 0}">
	                            <span name="2" class="mr10 hide">
		                                                                                  需充值：
		                           <span class='red' >${memberLevels.sellAmount}</span>
		                                                                                 元
		                        </span>
	                        </c:if>
	                        <c:if test="${memberLevels.sellAmount != 0 && memberLevels.chargeAmount == 0}">
	                            <span name="2" class="mr10">
		                                                                                  需充值：
		                           <span class='red' >${memberLevels.sellAmount}</span>
		                                                                                 元
		                        </span>
	                        </c:if>
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="6">
	                        <span class="mr10 label12">开卡提成:</span>
	                        <input type="checkbox" name="check-1" value="4" class="lcsStyle lcs_check_ticheng" checked="checked" autocomplete="on" />
	                        <select data-placeholder="" name= "recommendId" class="chzn-select-search input-large input120">
	                            <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
	                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode}  ${employeeInfo.name}</option>
	                            </c:forEach>
	                        </select>
	                        <input type="text" class="input80"/>
	                        <button class="btn btn-primary input30">+</button>
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="6">
	                        <span class="mr10 label12">礼金赠送:</span>
	                        <input type="text" class="input80"/>
	                        <select data-placeholder=""  class="chzn-select input-small">
	                            <c:forEach items="${deptInfoDtoList}" var="deptInfoDto" varStatus="status">
	                               <option value="${deptInfoDto.deptId}">${deptInfoDto.deptCode} ${deptInfoDto.deptName}</option>
	                            </c:forEach>
	                        </select>
	                        <select data-placeholder=""  class="chzn-select input-small">
	                            <option value="1">不分期</option>
	                            <option value="3">3个月</option>
	                            <option value="6">6个月</option>
	                            <option value="9">9个月</option>
	                            <option value="12">12个月</option>
	                        </select>
	                        <button class="btn btn-primary input30">+</button>
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="4">
	                        <span class="mr10 label12">支付密码</span><input class="input80" name = "payPassword" type="password"/>
	                        <span class="mr10 ml10 label12">确认密码</span><input type="password" name = "password" class="input80 mr10"/>
	                    </td>
	                    <td>
	                        <span class="mr10 label12">通知方式:</span>
	                        <input type="checkbox" name="check-2" value="4" class="lcsStyle lcs_check_notify_type" checked="checked" autocomplete="on" />
	                    </td>
	                    <td colspan="2" class="text-right">
	                        <button class="btn btn-primary input100" id= "confirm">&nbsp;保&nbsp;&nbsp;存&nbsp;</button>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
	
	        <div class="row-fluid">
	            <div class="widgetcontent">
	                <div id="tabs" class="recharge-transfer">
	                    <ul>
	                        <li class=""><a href="#tabs-1">充值</a></li>
	                        <li class=""><a href="#tabs-2">转账</a></li>
	                    </ul>
	                    <div id="tabs-1">
	                        <table class="table table-bordered table-invoice member-info-table-right ">
	                            <tbody>
	                            <tr name = "memberTR">
	                                <td class="width15">充值会员</td>
	                                <td class="hide" name="resultTD">
	                                    <span data-toggle="modal" data-target="#member-data" class="can-click"></span>
	                                    <input type="hidden" name = "memberId">
	                                    <span class="ml10"></span>
	                                    <span class="iconsweets-magnifying ml30 mt-5" name="breakName"></span>
	                                </td>
	                                <td class="input-td bg-white" name= "seekTD">
	                                    <input type="search" class="input-large" name = "phoneNumber" placeholder="会员手机号" />
	                                    <button type="button" class="btn search-button" name = "seekName">搜索</button>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td class="width15">充值金额</td>
	                                <td class="input-td">
	                                    <input type="search" class="width97" placeholder="10000"/>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td class="width15">现金支付</td>
	                                <td class="input-td">
	                                    <input type="search" class="width97" placeholder="0"/>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td class="width15">银联支付</td>
	                                <td class="input-td">
	                                    <input type="search" class="width97" placeholder="10000"/>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td class="width15">提成员工</td>
	                                <td class="input-td">
	                                    <select data-placeholder="选择员工" class="chzn-select-search input-large">
	                                        <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
				                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode}  ${employeeInfo.name}</option>
				                            </c:forEach>
	                                    </select>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td class="width15">礼金赠送</td>
	                                <td class="input-td">
	                                    <input type="text" class="input80"/>
	                                    <select data-placeholder=""  class="chzn-select input-small">
	                                        <option value="1">美容部</option>
	                                        <option value="1">美发部</option>
	                                        <option value="1">美甲部</option>
	                                        <option value="1">保健部</option>
	                                    </select>
	                                    <select data-placeholder=""  class="chzn-select input-small">
	                                        <option value="1">不分期</option>
	                                        <option value="1">3个月</option>
	                                        <option value="1">6个月</option>
	                                        <option value="1">9个月</option>
	                                        <option value="1">12个月</option>
	                                    </select>
	                                    <button class="btn btn-primary input30">+</button>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td class="width15">支付密码</td>
	                                <td class="input-td">
	                                    <input type="search" class="width97" placeholder="********"/>
	                                </td>
	                            </tr>
	                            <tr  class="confirm">
	                                <td></td>
	                                <td>
	                                    <button class="btn btn-primary fl">确认充值</button>
	                                </td>
	                            </tr>
	                            </tbody>
	                        </table>
	                    </div><!--tabs-1-->
	
	                    <div id="tabs-2">
	                        <table class="table table-bordered table-invoice member-info-table-right ">
	                            <tbody>
	                              <tr name = "memberTR">
	                                  <td class="width15">转出会员</td>
	                                  <td class="hide" name="resultTD">
	                                      <span data-toggle="modal" data-target="#member-data" class="can-click"></span>
	                                      <span class="ml10"></span>
	                                      <input type="hidden" name = "memberId">
	                                      <span class="cursor iconsweets-magnifying ml30 mt-5" name="breakName"></span>
	                                  </td>
	                                  <td class="input-td bg-white" name= "seekTD">
	                                      <input type="search" class="input-large" name = "phoneNumber" placeholder="会员手机号" />
	                                      <button type="button" class="btn search-button" name = "seekName">搜索</button>
	                                  </td>
	                              </tr>
	                              <tr name = "memberTR">
	                                  <td class="width15">转入会员</td>
	                                  <td class="hide" name="resultTD">
	                                      <span data-toggle="modal" data-target="#member-data" class="can-click"> </span>
	                                      <span class="ml10"></span>
	                                      <input type="hidden" name = "memberId">
	                                      <span class="cursor iconsweets-magnifying ml30 mt-5" name="breakName"></span>
	                                  </td>
	                                  <td class="input-td bg-white" name= "seekTD">
	                                      <input type="search" class="input-large" name = "phoneNumber" placeholder="会员手机号" />
	                                      <button type="button" class="btn search-button" name = "seekName">搜索</button>
	                                  </td>
	                              </tr>
	                              <tr>
	                                  <td>转入金额</td>
	                                  <td class="input-td"><input type="search" class="width97" placeholder="转入金额"/></td>
	                              </tr>
	                              <tr>
	                                  <td>支付密码</td>
	                                  <td class="input-td"><input type="search" class="width97" placeholder="********"/></td>
	                              </tr>
	                              <tr  class="confirm">
	                                  <td></td>
	                                  <td  colspan="" class="input-td">
	                                      <button class="btn btn-primary fl">确认转账</button>
	                                  </td>
	                              </tr>
	                            </tbody>
	                        </table>
	                    </div><!--tabs-2-->
	                </div><!--tabs-->
	            </div><!--widgetcontent-->
	        </div><!--row-fluid-->
	
	    </div><!--contentinner-->
	</div><!--maincontent-->
	
	<%@ include file="/template/memberData.jsp" %>
	
	<script type="text/javascript">
	   var memberLevelListStr = '${memberLevelListStr}';
	   var memberLevelList = eval("(" + memberLevelListStr + ")");
	</script>	
	<script type="text/javascript" src="<%=basePath %>js/keepAccounts/openCard.js"></script>
   </div>
     <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
  </div>

</body>

</html>