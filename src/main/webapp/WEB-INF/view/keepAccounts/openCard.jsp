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
	          <div class="n-tab">
	            <ul>
	                <li class="active n-sub-tab" data-target="#tab1">
	                    <span>开卡</span>
	                    <div class="border-bottom"></div>
	                </li>
	                <li class="n-sub-tab" data-target="#tab2">
	                    <div class="tab-word">
	                        <span>充值</span>
	                    </div>
	                    <div class="border-bottom"></div>
	                </li>
	                <li class="n-sub-tab" data-target="#tab3">
	                    <div class="tab-word">
	                        <span>转账</span>
	                    </div>
	                    <div class="border-bottom"></div>
	                </li>
	                <li class="n-sub-tab" data-target="#tab4">
	                    <div class="tab-word">
                            <span>会员升级</span>
                        </div>
                        <div class="border-bottom"></div>
                    </li>
                    <li class="n-sub-tab" data-target="#tab5">
	                    <div class="tab-word">
                            <span>优惠赠送</span>
                        </div>
                        <div class="border-bottom"></div>
                    </li>
	            </ul>
	            <div class="clearfix"></div>
	        </div>
	
	        <div class="n-tab-content">
	            <div id="tab1" class="target-tab ">
	                <div class="tab-form">
	                <p>
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">手机号:</label>
	                        <input type="text" name = "phone" class="w185"/>
	                    </div>
	                    <div class="p-part">
	                        <label class="ml10" for="">姓名:</label>
	                        <input type="text" name = "name" class="w185"/>
	                    </div>
	                    <div class="p-part">
	                        <label class="ml10" for="">性别:</label>
	                        <input type="radio" name="sex" class="ml10" value="男" checked="checked"/><span class="ml5">男</span>&nbsp;&nbsp;
	                        <input type="radio" name="sex" class="ml10" value="女" /><span class="ml5">女</span>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">会员级别:</label>
	                        <select id="" name = "kkLevelId" class="chzn-select w100" onchange="changeMemberLevel(this)">
	                            <c:forEach items="${memberLevelList}" var="memberLevel" varStatus="status">
	                                <option value="${memberLevel.levelId}">${memberLevel.levelName}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="p-part">
	                          <label class="w60 ml10" for="">开卡方式:</label>
	                          <select name= "openType" id="" class="chzn-select w100" onchange="changeOpenCard(this);">
	                              <c:if test="${memberLevels.chargeAmount != 0}">
	                                <option value="1">充值开卡</option>
	                              </c:if>
	                              <c:if test="${memberLevels.sellAmount != 0}">
	                                <option value="2">现金购卡</option>
	                              </c:if>
	                              
	                              <c:if test="${memberLevels.chargeAmount == 0 && memberLevels.sellAmount == 0}">
	                                <option value="1">充值开卡</option>
	                                <option value="2">现金购卡</option>
	                              </c:if>
	                          </select>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                 <div name = "commissionDIV">
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">提成业绩:</label>
	                        <select name="totaiCommissionType" id="" class="chzn-select w70" onchange="totaiUpdateVL(this)">
	                            <option value="1">固定</option>
	                            <option value="2">比例</option>
	                        </select>
	                    </div>
	                    <div class="p-part">
	                        <select name="totaiRecommendId" id="" class="chzn-select w165" onchange="totaiUpdateEmployeeInfo(this)">
	                            <option value="">选择员工</option>
	                            <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
	                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode} ${employeeInfo.name}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                  </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                  <div id = "deptInfoDIV">
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">礼金赠送:</label>
	                        <input type="text" name = "giftmoneyAmount" class="w70"/><span class='percent-symbol'>元</span>
	                    </div>
	                    <div class="p-part">
	                        <%-- <select name="deptInfoId" id="" class="chzn-select w165" onchange="updateDeptInfoId(this)">
	                            <option value="">选择部门</option>
	                            <c:forEach items="${deptInfoDtoList}" var="deptInfoDto" varStatus="status">
	                               <option value="${deptInfoDto.deptId}">${deptInfoDto.deptName}</option>
	                            </c:forEach>
	                        </select> --%>
	                        <select name="partType" id="" class="chzn-select input100">
	                            <option value="1">不分期</option>
	                            <option value="3">3个月</option>
	                            <option value="6">6个月</option>
	                            <option value="9">9个月</option>
	                            <option value="12">12个月</option>
	                            <option value="24">24个月</option>
	                        </select>
	                    </div>
                        <div class="p-part">
                            <select name="pastDate" id="" class="chzn-select input100">
                                <option value="0">不过期</option>
                                <option value="3">3个月</option>
                                <option value="6">6个月</option>
                                <option value="9">9个月</option>
                                <option value="12">12个月</option>
                                <option value="24">24个月</option>
                            </select>
                        </div>
	                  </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">卡金赠送:</label>
	                        <input type="text" name = "rewardAmount" class="w70"/><span class='percent-symbol'>元</span>
	                    </div>
	                    <div class="p-part">
                            <label class="ml10" for="">是否接收短信:</label>
	                        <input type="radio" name="messageType" class="ml10" value="1" /><span class="ml5">是</span>&nbsp;&nbsp;
	                        <input type="radio" name="messageType" class="ml10" value="0" checked="checked"/><span class="ml5">否</span>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	                
	                <p>
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">支付密码:</label>
	                        <input type="password" name = "payPassword" class="w185"/>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">确认密码:</label>
	                        <input type="password" name = "password" class="w185"/>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">现金支付:</label>
	                        <input type="text" name="kkCashAmount" class="w185" onkeyup="checkNum(this)"/>
	                    </div>
	                    <div class="p-part">
	                        <label class="ml10" for="">银联支付:</label>
	                        <input type="text" name= "kkUnionpayAmount" class="w185" onkeyup="checkNum(this)"/>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	                <div class="confirm-part mt50 ml80">
	                    <div id = "require">
	                        <c:if test="${memberLevels.chargeAmount != 0}">
	                             <span name="1" class="mr10">
		                                                                                  需充值：
		                           <span class='red' >${memberLevels.chargeAmount}</span>
		                                                                                 元
		                        </span>
	                        </c:if>
	                        <c:if test="${memberLevels.sellAmount != 0 && memberLevels.chargeAmount != 0}">
	                            <span name="2" class="mr10 hide">
		                                                                                  需支付：
		                           <span class='red' >${memberLevels.sellAmount}</span>
		                                                                                 元
		                        </span>
	                        </c:if>
	                        <c:if test="${memberLevels.sellAmount != 0 && memberLevels.chargeAmount == 0}">
	                            <span name="2" class="mr10">
		                                                                                  需支付：
		                           <span class='red' >${memberLevels.sellAmount}</span>
		                                                                                 元
		                        </span>
	                        </c:if>
	                        <c:if test="${memberLevels.sellAmount == 0 && memberLevels.chargeAmount == 0}">
	                            <span name="1" class="mr10">
		                                                                                  需充值：
		                           <span class='red' >0</span>
		                                                                                 元
		                        </span>
		                        <span name="2" class="mr10 hide">
		                                                                                  需支付：
		                           <span class='red' >0</span>
		                                                                                 元
		                        </span>
	                        </c:if>
	                    </div>
	                    <button class="btn w80" id="confirm">保存</button>
	                </div>
	                </form>
	                </div>
	            </div><!--tab1-->
	            
	            <div id="tab2" class="target-tab hide">
	                <div class="tab-form">
	                <p>
	                    <div class="p-part-first" id = "partDIV" name = "memberTR">
	                        <label class="w60 ml10" for="">充值会员:</label>
	                        <div style="display: inline-block;" name= "seekTD">
	                           <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号"/><span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
	                        </div>
	                        <div  name="resultTD" style="display: none;">
                                <span data-toggle="modal" data-target="#member-data" class="can-click"></span>
                                <input type="hidden" name = "memberId">
                                <span class="ml10" name = "balance"></span>
                                <span class="iconsweets-magnifying ml10 mt-5" name="breakName"></span>
                            </div>
	                    </div>
	                    <div class="p-part">
	                        <label class="ml10" for="">充值金额:</label>
	                        <input type="text" name="chargeAmount" class="w185" onkeyup="checkNum(this)"/>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">现金支付:</label>
	                        <input type="text" name="cashAmount" class="w185" onkeyup="checkNum(this)"/>
	                    </div>
	                    <div class="p-part">
	                        <label class="ml10" for="">银联支付:</label>
	                        <input type="text" name= "unionpayAmount" class="w185" onkeyup="checkNum(this)"/>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                 <div name = "commissionDIV">
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">提成业绩:</label>
	                        <select name="totaiCommissionType" id="" class="chzn-select w70" onchange="totaiUpdateVL(this)">
	                            <option value="1">固定</option>
	                            <option value="2">比例</option>
	                        </select>
	                    </div>
	                    <div class="p-part">
	                        <select name="totaiRecommendId" id="" class="chzn-select w165" onchange="totaiUpdateEmployeeInfo(this)">
	                            <option value="">选择员工</option>
	                            <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
	                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode} ${employeeInfo.name}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                  </div>
	                </p>
	                <div class="clearfix"></div>
	                
	                <p>
	                  <div id = "czdeptInfoDIV">
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">礼金赠送:</label>
	                        <input type="text" name = "czGiftmoneyAmount" class="w70"/><span class='percent-symbol'>元</span>
	                    </div>
	                    <div class="p-part">
	                        <select name="czPartType" id="" class="chzn-select input100">
	                            <option value="1">不分期</option>
	                            <option value="3">3个月</option>
	                            <option value="6">6个月</option>
	                            <option value="9">9个月</option>
	                            <option value="12">12个月</option>
	                            <option value="24">24个月</option>
	                        </select>
	                    </div>
	                    <div class="p-part">
                            <select name="czPastDate" id="" class="chzn-select input100">
                                <option value="0">不过期</option>
                                <option value="3">3个月</option>
                                <option value="6">6个月</option>
                                <option value="9">9个月</option>
                                <option value="12">12个月</option>
                                <option value="24">24个月</option>
                            </select>
                        </div>
	                  </div>
	                </p>
	                <div class="clearfix"></div>
	
	                <p>
	                    <div class="p-part-first">
	                        <label class="w60 ml10" for="">卡金赠送:</label>
	                        <input type="text" name = "czRewardAmount" class="w70"/><span class='percent-symbol'>元</span>
	                    </div>
	                </p>
	                <div class="clearfix"></div>
	                
	                <div class="confirm-part mt50 ml80">
	                    <button class="btn w100" onclick="czConfirm(1)">确认充值</button>
	                </div>
	                </div>
	
	            <div class="more-detail-table">
	                <div class="tab-table">
	
	                <table class="table">
	                    <thead>
	                    <tr>
	                        <th>充值时间</th>
	                        <th>会员账号</th>
	                        <th>充值门店</th>
	                        <th>充值金额</th>
	                    </tr>
	                    </thead>
	                    <tbody id = "czTbody">
	                    </tbody>
	                </table>
	                </div>
	            </div>
	            </div><!--tab2-->
	
	            <div id="tab3" class="target-tab hide">
	                <div class="tab-form">
	                    <p>
	                        <div class="p-part-first" id = "outDIV" name = "memberTR">
		                        <label class="w60 ml10" for="">转出会员:</label>
		                        <div style="display: inline-block;" name= "seekTD">
		                           <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号"/><span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        </div>
		                        <div  name="resultTD" style="display: none;">
	                                <span data-toggle="modal"  data-target="#member-data" class="can-click"></span>
	                                <input type="hidden" name = "memberId">
	                                <span class="ml10" name = "balance"></span>
	                                <span class="iconsweets-magnifying ml10 mt-5" name="breakName"></span>
	                            </div>
		                    </div>
		                    <div class="p-part" id = "inDIV" name = "memberTR">
		                        <label class="ml10" for="">转入会员:</label>
		                        <div style="display: inline-block;" name= "seekTD">
		                           <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号"/><span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        </div>
		                        <div  name="resultTD" style="display: none;">
	                                <span data-toggle="modal" data-target="#member-data" class="can-click"></span>
	                                <input type="hidden" name = "memberId">
	                                <span class="ml10" name = "balance"></span>
	                                <span class="iconsweets-magnifying ml10 mt-5" name="breakName"></span>
	                            </div>
		                    </div>
	                    </p>
	                    <div class="clearfix"></div>
	
	                    <p>
	                        <div class="p-part-first">
	                            <label class="w60 ml10" for="">转入金额:</label>
	                            <input type="text" name = "zzChargeAmount" class="w185" onkeyup="checkNum(this)"/>
	                        </div>
	                    </p>
	                    <div class="clearfix"></div>
	
	                    <p>
	                        <div class="p-part-first">
	                            <label class="w60 ml10" for="">支付密码:</label>
	                            <input type="password" name="zzPassword" class="w185"/>
	                        </div>
	                    </p>
	                    <div class="clearfix"></div>
	
	                    <div class="confirm-part mt50 ml80">
	                        <button class="btn w100" onclick="zzConfirm()">确认转账</button>
	                    </div>
	                </div>
	
	                <div class="more-detail-table">
	
	                    <div class="tab-table">
	
	                    <table class="table">
	                        <thead>
	                        <tr>
	                            <th>转账时间</th>
	                            <th>转出会员</th>
	                            <th>转入会员</th>
	                            <th>转出金额</th>
	                        </tr>
	                        </thead>
	                        <tbody id = "zzTbody">
	                        </tbody>
	                    </table>
	                    </div>
	                </div>
	
	            </div><!--tab3-->
	            
	            <div id="tab4" class="target-tab hide">
                    <div class="tab-form">
	                    <p>
	                        <div class="p-part-first" name = "memberTR">
	                            <label class="w60 ml10" for="">充值会员:</label>
	                            <div style="display: inline-block;" name= "seekTD">
	                               <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号"/><span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
	                            </div>
	                            <div  name="resultTD" style="display: none;">
	                                <span data-toggle="modal" data-target="#member-data" class="can-click"></span>
	                                <input type="hidden" name = "memberId">
	                                <input type="hidden" name = "levelId">
	                                <span class="ml10" name = "levelName"></span>
	                                <span class="ml10" name = "balance"></span>
	                                <span class="iconsweets-magnifying ml10 mt-5" name="breakName"></span>
	                            </div>
	                        </div>
	                    </p>
	                    <div class="clearfix"></div>
	                    
	                    <p>
	                        <div class="p-part-first">
	                            <label class="w60 ml10" for="">升级级别:</label>
	                            <select id="" name = "sjLevelId" class="chzn-select w100">
	                                <c:forEach items="${memberLevelList}" var="memberLevel" varStatus="status">
	                                    <option value="${memberLevel.levelId}">${memberLevel.levelName}</option>
	                                </c:forEach>
	                            </select>
	                        </div>
	                        <div class="p-part">
	                              <label class="w60 ml10" for="">升级方式:</label>
	                              <select name= "sjOpenType" id="" class="chzn-select w100">
	                                    <option value="1">充值升级</option>
	                                    <option value="2">现金升级</option>
	                              </select>
	                        </div>
	                    </p>
	                    <div class="clearfix"></div>
	    
	                    <p>
		                 <div name = "commissionDIV">
		                    <div class="p-part-first">
		                        <label class="w60 ml10" for="">提成业绩:</label>
		                        <select name="totaiCommissionType" id="" class="chzn-select w70" onchange="totaiUpdateVL(this)">
		                            <option value="1">固定</option>
		                            <option value="2">比例</option>
		                        </select>
		                    </div>
		                    <div class="p-part">
		                        <select name="totaiRecommendId" id="" class="chzn-select w165" onchange="totaiUpdateEmployeeInfo(this)">
		                            <option value="">选择员工</option>
		                            <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
		                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode} ${employeeInfo.name}</option>
		                            </c:forEach>
		                        </select>
		                    </div>
		                  </div>
		                </p>
		                <div class="clearfix"></div>
	                    
	                    <p>
	                      <div id = "sjDeptInfoDIV">
	                        <div class="p-part-first">
	                            <label class="w60 ml10" for="">礼金赠送:</label>
	                            <input type="text" name = "sjGiftmoneyAmount" class="w70"/><span class='percent-symbol'>元</span>
	                        </div>
	                        <div class="p-part">
                                <select name="sjPartType" class="chzn-select input100">
                                    <option value="1">不分期</option>
                                    <option value="3">3个月</option>
                                    <option value="6">6个月</option>
                                    <option value="9">9个月</option>
                                    <option value="12">12个月</option>
                                    <option value="24">24个月</option>
                                </select>
                            </div>
	                        <div class="p-part">
		                        <select name="sjPastDate" class="chzn-select input100">
                                    <option value="0">不过期</option>
                                    <option value="3">3个月</option>
                                    <option value="6">6个月</option>
                                    <option value="9">9个月</option>
                                    <option value="12">12个月</option>
                                    <option value="24">24个月</option>
                                </select>
		                    </div>
	                      </div>
	                    </p>
	                    <div class="clearfix"></div>
	                    
	                    <p>
		                    <div class="p-part-first">
		                        <label class="w60 ml10" for="">卡金赠送:</label>
		                        <input type="text" name = "sjRewardAmount" class="w70"/><span class='percent-symbol'>元</span>
		                    </div>
		                </p>
		                <div class="clearfix"></div>
	                    
	                    <p>
	                        <div class="p-part-first">
	                            <label class="w60 ml10" for="">现金支付:</label>
	                            <input type="text" name="sjCashAmount" class="w185" onkeyup="checkNum(this)"/>
	                        </div>
	                        <div class="p-part">
	                            <label class="ml10" for="">银联支付:</label>
	                            <input type="text" name= "sjUnionpayAmount" class="w185" onkeyup="checkNum(this)"/>
	                        </div>
	                    </p>
	                    <div class="clearfix"></div>
	    
	                    <div class="confirm-part mt50 ml80">
	                        <button class="btn w100" onclick="sjConfirm()">确认升级</button>
	                    </div>
                    </div>
 
                </div><!--tab4-->
                
                <div id="tab5" class="target-tab hide">
	                <div class="tab-form">
		                <p>
		                    <div class="p-part-first" id = "partDIV" name = "memberTR">
		                        <label class="w60 ml10" for="">赠送会员:</label>
		                        <div style="display: inline-block;" name= "seekTD">
		                           <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号"/><span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        </div>
		                        <div  name="resultTD" style="display: none;">
	                                <span data-toggle="modal" data-target="#member-data" class="can-click"></span>
	                                <input type="hidden" name = "memberId">
	                                <span class="ml10" name = "balance"></span>
	                                <span class="iconsweets-magnifying ml10 mt-5" name="breakName"></span>
	                            </div>
		                    </div>
		                </p>
		                <div class="clearfix"></div>
		                
		                <p>
                            <div class="p-part-first">
                                <label class="w60 ml10" for="">积分赠送:</label>
                                <input type="text" name = "zsIntegralAmount" class="w70"/><span class='percent-symbol'>分</span>
                            </div>
                        </p>
                        <div class="clearfix"></div>
		
		                <p>
		                 <div name = "commissionDIV">
		                    <div class="p-part-first">
		                        <label class="w60 ml10" for="">赠优惠卷:</label>
		                        <select class="chzn-select wthn300" id="couponSelect" multiple="true" data-placeholder="请选择要赠送的优惠券，可多选">
                                   <c:forEach items="${couponList }" var="coupon">
                                       <option value="${coupon.couponId }">${coupon.couponName }</option>
                                   </c:forEach>
                                </select>
		                    </div>
		                  </div>
		                </p>
		                <div class="clearfix"></div>
		                
		                <p>
		                  <div id = "czdeptInfoDIV">
		                    <div class="p-part-first">
		                        <label class="w60 ml10" for="">礼金赠送:</label>
		                        <input type="text" name="zsGiftmoneyAmount" class="w70"/><span class='percent-symbol'>元</span>
		                    </div>
		                    <div class="p-part">
		                        <select name="zsPartType" class="chzn-select input100">
		                            <option value="1">不分期</option>
		                            <option value="3">3个月</option>
		                            <option value="6">6个月</option>
		                            <option value="9">9个月</option>
		                            <option value="12">12个月</option>
		                            <option value="24">24个月</option>
		                        </select>
		                    </div>
		                    <div class="p-part">
                                <select name="zsPastDate" class="chzn-select input100">
                                    <option value="0">不过期</option>
                                    <option value="3">3个月</option>
                                    <option value="6">6个月</option>
                                    <option value="9">9个月</option>
                                    <option value="12">12个月</option>
                                    <option value="24">24个月</option>
                                </select>
                            </div>
		                  </div>
		                </p>
		                <div class="clearfix"></div>
		                
		                <p>
                          <div id = "czdeptInfoDIV">
                            <div class="p-part-first">
                                <label class="w60 ml10" for="">赠送原因:</label>
                                <input type="text" name="zsReason" class="wthn300"/>
                            </div>
                          </div>
                        </p>
                        <div class="clearfix"></div>
		
		                <div class="confirm-part mt50 ml80">
		                    <button class="btn w100" onclick="presentGift()">立即赠送</button>
		                </div>
	                </div>
	            </div><!--tab5-->
                
	        </div>
	
	    </div><!--contentinner-->
	</div><!--maincontent-->
	
	<!--充值提示-->
	<div class="modal hide" id="czModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content confirm">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" onclick="czCancel()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">提示</h4>
	            </div>
	
	            <div class="modal-body confirm-body">
	                                            会员充值没有达到最低充值金额呦！是否确定本次操作？
	            </div><!--modal-body-->
	
	            <div class="modal-footer">
	                <a class="btn cancel-btn modal-cancel" data-dismiss="modal" onclick="czCancel()" href="#">取消</a>
	                <a onclick="rechargeCard(2)" class="btn btn-primary save-btn modal-confirm" data-dismiss="modal" href="#">保存</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!--提示-->
	<div class="modal hide" id="sjModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content confirm">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">提示</h4>
	            </div>
	
	            <div class="modal-body confirm-body">
	                                            会员升级没有输入金额呦！是否确定本次操作？
	            </div><!--modal-body-->
	
	            <div class="modal-footer">
	                <a class="btn cancel-btn modal-cancel" data-dismiss="modal"  href="#">取消</a>
	                <a onclick="pastDateConfirm(2)" class="btn btn-primary save-btn modal-confirm" data-dismiss="modal" href="#">保存</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<%@ include file="/template/memberData.jsp" %>
	<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
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