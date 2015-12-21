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
			    <div class="more-toolbar" >
		            <div class="table-toolbar">
		                <span class="font-size-16 btn-color mr10">会员数据</span>
		            </div>
		            <div class="clearfix"></div>
		        </div><!--more-toolbar-->
		        <div class="more-toolbar" >
		            <div class="table-toolbar">
		                <input type="search" placeholder="手机号码/姓名" id="serchMemberByNameOrPhone"/>
		                <button class="button-search btn width100 ml-10" id="serchMemberByNameOrPhoneDoc">查询</button>
		                <div class="show-more" id="show_more">
		                    <img src="<%=basePath %>/img/common/d-triangle.png" alt="" class="ml10 mr10 more-img"/><span>更多筛选信息</span>
		                </div>
		            </div>
		            <div class="clearfix"></div>
		        </div>
	            <table class="table more-condition-table hide">
	                <tbody>
	                    <tr>
	                        <td class="width8">储值余额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="chuzhi1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="chuzhi2"/>
	                        </td>
	                        <td class="width8">积分余额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="jifenye1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="jifenye2"/>
	                        </td>
	                    </tr>
	
	                    <tr>
	                        <td class="width8">生　　日</td>
	                        <td class="">
	                            <input type="text" class="input80 timePickerClean" id="birthday-start"/>
	                            <span>-</span>
	                            <input type="text" class="input80 timePickerClean" id="birthday-end"/>
	                        </td>
	                        <td class="width8">注册日期</td>
	                        <td class="">
	                            <input type="text" class="input80 timePickerClean" id="register-start"/>
	                            <span>-</span>
	                            <input type="text" class="input80 timePickerClean" id="register-end"/>
	                        </td>
	                    </tr>
	
	                    <tr>
	                        <td class="width8">消费均额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="xiaofje1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="xiaofje2"/>
	                        </td>
	                        <td class="width8">累计消费</td>
	                        <td class="">
	                            <input type="number" class="input80" id="leijixf1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="leijixf2"/>
	                        </td>
	                    </tr>
	                    
	                    <tr>
	                        <td class="width8">距上次消费</td>
	                        <td class="">
	                            <input type="number" class="input80" id="scxf1"/>
	                            <span>-</span>
	                            <input type="number" class="input80" id="scxf2"/>
	                        </td>
	                        <td class="width8">选择分店</td>
	                        <td>
		                        <select style="width: 186px" data-placeholder="选择分店,默认为全部分店" class="chzn-select input-xlarge" name="branchOffice" id="branchOffice" multiple="multiple">
		                            <c:forEach var="storeList" items="${storeList }" varStatus="status">
		                        		<option value="${storeList.storeId }">${storeList.storeName }</option>
		                        	</c:forEach>
		                        </select>
		                    </td>
	                    </tr>
	                    
	                    <tr>
	                        <td>
	
	                        </td>
	                        <td class="">
	                            <input type="hidden" class="input80" />
	                        </td>
	                        <td class="width8"></td>
	                        <td class="">
	                            <input style="float: right;margin-right: 20px;" type="button" class="btn" id="serch" value="搜索"/><span>&nbsp;&nbsp;&nbsp;</span>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
   	            <!--保存筛选信息-->
	            <table style="border: 1px solid #ddd;margin-top: 10px;margin-bottom: 0;width: 100%;display: none;"  id="tishi">
	                <tbody>
	                <tr>
	                    <td style="padding-left: 10px;"><label>您要保存筛选信息么?</label></td>
	                    <td></td>
	                    <td></td>
	                    <td style="padding-top: 5px;"><input type="button" class="btn" id="fangqi" style="float: right;margin-right: 20px;" value="放弃"/></td>
	                </tr>
	                <tr>
	                    <td style="padding-left: 10px;" id="member_serch_count">当前满足条件 0 人</td>
	                    <td></td>
	                    <td></td>
	                    <td>
	                        <input type="button" class="btn" id="baocun2" style="float: right;margin-right: 20px;" value="保存"/>
	                    </td>
	                </tr>
	                </tbody>
	            </table>
	            </div>
	            <!--保存筛选信息-->
		        <!-- </div> -->
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
	                
		        <table class="table table-bordered table-striped member-list-table">
		            <thead>
		            <tr>
		            	<th>所属门店</th>
		                <th>手机号</th>
		                <th>姓名</th>
		                <th class="drop-th">
		                    <span class="dropdown-toggle" data-toggle="dropdown">性别 <span class="caret"></span></span>
		                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
		                    	<li><a tabindex="-1" onclick="serchMemberBySex('全部',this)" href="javascript:void(0)">全部</a></li>
		                        <li><a tabindex="-1" onclick="serchMemberBySex('男',this)" href="javascript:void(0)">男</a></li>
		                        <li><a tabindex="-1" onclick="serchMemberBySex('女',this)" href="javascript:void(0)">女</a></li>
		                    </ul>
		                </th>
		                <th class="drop-th">
		                    <span class="dropdown-toggle" data-toggle="dropdown">会员等级 <span class="caret"></span></span>
		                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
		                        <li><a tabindex="-1" onclick="serchMemberByLevel('0','全部',this)" href="javascript:void(0)">全部</a></li>
		                        <c:forEach items="${levels }" var="level">
		                        	<li><a tabindex="-1" onclick="serchMemberByLevel('${level.levelId}','${level.levelName }',this)" href="javascript:void(0)">${level.levelName }</a></li>
		                        </c:forEach>
		                    </ul>
		                </th>
		                <th>生日</th>
		                <th>储值余额</th>
		                <th>积分余额</th>
		                <th>累计消费</th>
		                <th>消费均额</th>
		                <th>注册日期</th>
		            </tr>
		            </thead>
		            <tbody id="init_member">
		            
		            <c:forEach items="${page.results }" var="member">
		            <tr>
		            	<td>${member.storeName }</td>
		                <td>${member.phone }</td>
		                <td class="can-click" data-target="#member-data" data-toggle="modal" onclick="selectMemberInfo(${member.memberId })" id="${member.memberId }">${member.name }</td>
		                <td>${member.sex }</td>
		                <td>${member.levelName }</td>
		                <td>${member.birthday }</td>
		                <td class="red">${member.balanceAmount }</td>
		                <td class="blue">${member.balanceIntegral }</td>
		                <td>${member.totalConsumeAmount }</td>
		                <td>${member.avgConsumeAmount }</td>
		                <td>${member.createTime }</td>
		            </tr>
		            </c:forEach>
		            </tbody>
		        </table>
	       </div><!-- contentinner -->
       </div><!-- maincontent -->
    </div><!-- rightpanel -->

    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->
<!--会员筛选器模态框-->
<div class="modal hide" id="add-member-group" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-member-card">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增会员分组</h4>
            </div>
            <div class="modal-body">
                <form action="" class="editprofileform" method="post">
                    <p>
                        <label>筛选器名称:</label>
                        <input type="text" name="cart-name" id="group_name" class="input-xlarge" value="">
                    </p>
                </form>
            </div>
            <div class="modal-footer">
                <a class="btn modal-cancel" href="#">取消</a>
                <a id="baocun" class="btn btn-primary modal-confirm" href="#">确定</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="/template/memberData.jsp" %>
</body>

<script src="<%=basePath %>js/member/baseMember.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery("select").chosen();
//日期
jQuery('#register-start, #register-end').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'Y-m-d'
});
jQuery('#birthday-start, #birthday-end').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'m-d'
	});
	
//获取加载页面时的页码信息
var pageNo = '${page.pageNo}';	
var pageSize = '${page.pageSize}';	
var totalPage = '${page.totalPage}';	

</script>

</html>