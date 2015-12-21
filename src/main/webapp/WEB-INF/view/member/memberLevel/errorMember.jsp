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
		    
		    <div class="widgetcontent">
			    <div class="more-toolbar">
		            <div class="table-toolbar">
		                <span class="font-size-16 btn-color mr10">异常会员数据</span>
		            </div>
		            <div class="clearfix"></div>
		        </div><!--more-toolbar-->
		        <div class="more-toolbar">
		            <div class="table-toolbar">
		            	<a class="btn" href="<%=basePath%>member/download/error/member">导出</a>
		                <input type="search" placeholder="手机号码/姓名/卡号" id="serchMemberByNameOrPhone">
		                <button class="button-search btn width100 ml-10" onclick="serchMemberByNameOrPhoneDoc()">查询</button>
		            </div>
		            <div class="clearfix"></div>
		        </div>
	            </div>
		        
		        <div class="more-toolbar" >
			        <div class="table-toolbar">
					    <div class="table-pagination">
						    <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
	                        ${page.pageSize} <span class="iconfa-caret-down afont"></span>
	                        </button>
		                    <ul class="dropdown-menu">
		                        <li><a href="javascript:changePageSize(5)">5</a></li>
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
				<div class="more-toolbar">
	                <div class="table-toolbar">
	                    <span class="font-size-16 btn-color" id="searchDate">会员信息统计</span>
	
	                    <div class="table-pagination">
	                        <table class="ls-detail fr">
	                            <tbody><tr>
	                                <td>会员总人数：</td>
	                                <td id="receivableAmount">${page.totalRecord }</td>
	                                <td>当前储值余额：</td>
	                                <td id="couponAmount">${balanceAmounts.balanceAmounts }</td>
	                            </tr>
	                        </tbody></table>
	                    </div><!--table-pagination-->
	                </div><!--table-toolbar-->
	                <div class="clearfix"></div>
	             </div>
	            <c:if test="${lastFacilitator == '盛传' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>卡类型</th>
			                <th>折扣</th>
			                <th>储值总额</th>
			                <th>消费总额</th>
			                <th>卡内总余额</th>
			                <th>赠送总余额</th>
			                <th>失效日期</th>
			                <th>消费次数</th>
			                <th>当前积分</th>
			                <th>最后消费日</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			                <th>套餐迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.levelName }</td>
			                <td>${member.levelType }</td>
			                <td>${member.discount }</td>
			                <td>${member.totalAmount }</td>
			                <td>${member.totalConsumeAmount }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.sendAmount }</td>
			                <td>${member.aeadTime }</td>
			                <td>${member.consumeAmount }</td>
			                <td>${member.balanceIntegral }</td>
			                <td>${member.lastConsumeTime }</td>
			                <td>
			                <i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(1, ${member.id },this);"></i>
			                </td>
			                <td class="can-click m-btn" onclick="balanceAmountMove(1, ${member.id }, this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                <td class="can-click m-btn" onclick="comboMove(1, ${member.id }, this)" style="text-decoration: none; text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if>    
	            
	            <c:if test="${lastFacilitator == '左右' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>卡内总余额</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.levelName }</td>
			                <td>${member.balanceAmount }</td>
			                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(2, ${member.id },this);"></i></td>
		                    <td class="can-click m-btn" onclick="balanceAmountMove(2, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			            </tr>
		                </c:forEach>
			            </tbody>
			        </table>
	            </c:if>  
	            
	            <c:if test="${lastFacilitator=='云浩企汇通' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>储值余额 </th>
			                <th>礼金储值余额</th>
			                <th>累计消费次数</th>
			                <th>积分余额</th>
			                <th>最后消费日期</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.levelName }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.balanceGiftmoneyAmount }</td>
			                <td>${member.consumeCount }</td>
			                <td>${member.balanceIntegral }</td>
	                        <td>${member.lastConsumeTime }</td>
			                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(3, ${member.id },this);"></i></td>
		                    <td class="can-click m-btn" onclick="balanceAmountMove(3, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if>   
	            <c:if test="${lastFacilitator=='博卡' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>储值总额 </th>
			                <th>储值余额</th>
			                <th>累计消费总额</th>
			                <th>累计消费次数</th>
			                <th>平均消费额度</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.totalAmount }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.totalConsumeAmount }</td>
			                <td>${member.consumeCount }</td>
			                <td>${member.avgConsumeAmount }</td>
			                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(4, ${member.id },this);"></i></td>
		                    <td class="can-click m-btn" onclick="balanceAmountMove(4, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	       </div><!-- contentinner -->
	       <!--删除提示-->
           <div class="modal hide" id="deleteErrorMemberModel" tabindex="-1" role="dialog" aria-labelledby="deleteErrorMemberModel">
               <div class="modal-dialog" role="document">
                   <div class="modal-content confirm">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                           <h4 class="modal-title">删除提示</h4>
                       </div>
                       <div class="modal-body confirm-body">
                        	 会员资料删除后将不可恢复，您确认要删除吗？
                       </div>
                       <div class="modal-footer">
                           <a class="btn cancel-btn modal-cancel" data-dismiss="modal" href="javascript:void();">我点错了</a>
                           <a class="btn btn-primary save-btn modal-confirm" href="javascript:deleteErrMember();">确认删除</a>
                       </div>
                   </div>
               </div>
           </div>     
       </div><!-- maincontent -->
    </div><!-- rightpanel -->
    <!--RIGHT PANEL结束 -->
    <div class="clearfix"></div>
    <div id="star"></div>
</div><!--mainwrapper-->

<!--余额迁移模态框-->
<div class="modal hide" id="yueqianyi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-yichang">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"> 余额迁移</h4>
            </div>
            <div class="modal-body">
                <div class="modal-wrap">
                    <!--原系统-->
                    <div class="xitong fl">
                        <div class="xitong-top">
                           	       原系统
                        </div>
                        <div class="xitong-main">
                            <ul>
                                <li><span>会员姓名：</span> 暂无</li>
                                <li><span>电话号码：</span> 暂无</li>
                                <li><span>会员性别：</span> 暂无</li>
                                <li><span>会员卡号：</span> 暂无</li>
                            </ul>
                        </div>
                    </div>
                    <!--箭头-->
                    <span><img src="<%=basePath %>images/arr.png" alt=""/></span>
                    <!--智放系统-->
                    <div class="xitong fr" style="height: 306px">
                        <div class="xitong-top">
                            	智放系统
                        </div>
                        <div class="xitong-main">
                        	<div class="xt-search">
                                <span>搜索会员</span>
                                <input type="text" placeholder="会员手机号码"/>
                                <span class="iconfont icon-sousuo" onclick="changeToMemberInfo(this)"></span>
                            </div>
                            <ul>
                                <li><span>会员姓名：</span> 暂无</li>
                                <li><span>电话号码：</span> 暂无</li>
                                <li><span>会员性别：</span> 暂无</li>
                                <li><span>会员卡名：</span> 暂无</li>
                                <li><span>当前余额：</span> 暂无</li>
                                <input type="hidden" name="memberId">
                            </ul>
                        </div>
                    </div>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" id="balandMove">确定迁移</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->


<!--套餐迁移模态框-->
<div class="modal hide" id="taocanqianyi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-yichang">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"> 套餐迁移</h4>
            </div>
            <div class="modal-body">
                <div class="modal-wrap">
                    <!--原系统-->
                    <div class="xitong fl">
                        <div class="xitong-top">
                            	原系统
                        </div>
                        <div class="xitong-main">
                            <ul>
                                <li><span>会员姓名：</span> 暂无</li>
                                <li><span>电话号码：</span> 暂无</li>
                                <li><span>会员性别：</span> 暂无</li>
                                <li><span>会员卡号：</span> 暂无</li>
                            </ul>
                        </div>
                    </div>
                    <!--箭头-->
                    <span><img src="<%=basePath %>images/arr.png" alt=""/></span>
                    <!--智放系统-->
                    <div class="xitong fr" style="height: 340px;">
                        <div class="xitong-top">
                           		 智放系统
                        </div>
                        <div class="xitong-main">
                        	<div class="xt-search">
                                <span>搜索会员</span>
                                <input type="text" placeholder="会员手机号码"/>
                                <span class="iconfont icon-sousuo" onclick="comboPhone(this)"></span>
                            </div>
                            <ul>
                            	<li><span>会员姓名：</span>暂无</li>
                                <li><span>套餐名称：</span><input type="text" name="comboName"/></li>
                                <li><span>选择项目：</span>
	                                <select data-placeholder="选择项目" class="chzn-select input-medium" style="width:176px !important;" name="projectId">
	                                	<option value="0" >请选择一个项目</option>
	                                	<c:forEach items="${projectInfos }" var="projectInfo" >
	                                		<option value="${projectInfo.projectId }">${projectInfo.projectName }</option>
	                           			</c:forEach>
	                                </select>
                                </li>
                                <li><span>项目次数：</span><input type="text" name="projectCount"/></li>
                                <li><span>过期时间：</span><input type="text" name="overdueTime" placeholder="截止时间"  class="timePickerClean width100"></li>
                                <input type="hidden" name="memberId">
                            </ul>
                        </div>
                    </div>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" id="comboMove">确定迁移</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->


</body>
<script type="text/javascript">
//获取加载页面时的页码信息
var pageNo = '${page.pageNo}';	
var pageSize = '${page.pageSize}';	
var totalPage = '${page.totalPage}';	
var lastFacilitator = '${lastFacilitator}';	
</script>
<script type="text/javascript" src="<%=basePath%>js/member/errorMember.js"></script>
<script type="text/javascript" src="<%=basePath%>js/member/errorMemberMove.js"></script>
</html>