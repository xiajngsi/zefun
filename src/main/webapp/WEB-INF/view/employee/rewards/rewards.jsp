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

	<div class="maincontent">
	    <div class="contentinner new-contentinner">
	        <div class="n-tab">
	            <ul>
	                <li class="active n-sub-tab" data-target="#tab1" id="kaoqin">
	                    <span>考勤规则设置</span>
	                    <div class="border-bottom"></div>
	                </li>
	                <li class="n-sub-tab" data-target="#tab2" id="xingwei">
	                    <div class="tab-word">
	                        <span>行为规范</span>
	                    </div>
	                    <div class="border-bottom"></div>
	                </li>
	                <li class="n-sub-tab" data-target="#tab3" id="fuwu">
	                    <div class="tab-word">
	                        <span>服务表现</span>
	                    </div>
	                    <div class="border-bottom"></div>
	                </li>
	            </ul>
	            <div class="clearfix"></div>
	        </div>
		    <div class="n-tab-content ">
		        <div class="tab-padding">
		            <div class="widgetcontent">
		                <table class="table table-bordered ruleinfo aaa" style="display: none">
		                    <thead>
		                        <tr>
		                            <th class="name bold border_underline">考核项</th>
		                            <th class="name bold border_underline">考核标准</th>
		                            <th class="name bold border_underline">考核措施</th>
		                            <th class="name bold border_underline">奖罚金额</th>
		                            <th class="name bold border_underline">操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    <c:forEach items="${kaoqinlist}" var="kaoqinlist">
		                    <tr>
		                        <td>${kaoqinlist.attendanceName}</td>
		                        <c:if test="${kaoqinlist.attendanceName=='迟到'}">
		                        <td>当班日晚于开门时间${kaoqinlist.standard}分钟</td>
		                        <td>扣款</td>
		                        </c:if>
		                        <c:if test="${kaoqinlist.attendanceName=='早退'}">
		                        <td>当班日早于下班时间${kaoqinlist.standard}分钟</td>
		                        <td>扣款</td>
		                        </c:if>
		                        <c:if test="${kaoqinlist.attendanceName=='请假'}">
		                        <td>请假并经过领导审批每${kaoqinlist.standard}小时 </td>
		                        <td>扣款</td>
		                        </c:if>
		                        <c:if test="${kaoqinlist.attendanceName=='旷工'}">
		                        <td>迟到或早退超过${kaoqinlist.standard}小时或者旷工 </td>
		                        <td>扣款</td>
		                        </c:if>
		                        <c:if test="${kaoqinlist.attendanceName=='全勤'}">
		                        <td>${kaoqinlist.standard} </td>
		                        <td>奖励</td>
		                        </c:if>
		                        
		                        <td>${kaoqinlist.abstractMoney}</td>
		                        <td>
		                            <i class="iconfa-pencil project-icon" data-target="#card-discard-setting" onclick="openeditkaoqin('${kaoqinlist.attendanceId}','${kaoqinlist.attendanceName}','${kaoqinlist.standard}','${kaoqinlist.abstractMoney}')"></i>
		                        </td>
		                     </tr>
		                    </c:forEach>
		                    </tbody>
		                </table>
		                <table class="table table-bordered ruleinfo bbb" style="display: none">
		                    <thead>
		                        <tr>
		                            <th class="name bold border_underline">考核项</th>
		                            <th class="name bold border_underline">考核标准</th>
		                            <th class="name bold border_underline">考核措施</th>
		                            <th class="name bold border_underline">奖罚金额</th>
		                            <th class="name bold border_underline">操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    <c:forEach items="${xingweilist}" var="xingweilist">
		                    <tr>
		                        <td>${xingweilist.behaviorName}</td>
		                        <td>${xingweilist.standard} </td>
		                        <td>扣款</td>
		                        <td>${xingweilist.abstractMoney}</td>
		                        <td>
		                            <i class="iconfa-pencil project-icon" data-target="#card-discard-setting" onclick="openeditxingwei('${xingweilist.behaviorId}','${xingweilist.behaviorName}','${xingweilist.standard}','${xingweilist.abstractMoney}')"></i>
		                        </td>
		                     </tr>
		                    </c:forEach>
		                    </tbody>
		                </table>
		                <table class="table table-bordered ruleinfo ccc" style="display: none">
		                    <thead>
		                        <tr>
		                            <th class="name bold border_underline">考核项</th>
		                            <th class="name bold border_underline">考核标准</th>
		                            <th class="name bold border_underline">考核措施</th>
		                            <th class="name bold border_underline">奖罚金额</th>
		                            <th class="name bold border_underline">操作</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    <c:forEach items="${fuwulist}" var="fuwulist">
		                    <tr>
		                        <td>${fuwulist.serviceName}</td>
		                        <c:if test="${fuwulist.serviceName=='好评'}">
		                        <td>当客户评分大于${fuwulist.standard}分</td>
		                        <td>奖励</td>
		                        </c:if>
		                        <c:if test="${fuwulist.serviceName=='差评'}">
		                        <td>当客户评分小于${fuwulist.standard}分</td>
		                        <td>扣款</td>
		                        </c:if>
		                        <c:if test="${fuwulist.serviceName=='投诉'}">
		                        <td>${fuwulist.standard}</td>
		                        <td>扣款</td>
		                        </c:if>
		                        <td>${fuwulist.abstractMoney}</td>
		                        <td>
		                            <i class="iconfa-pencil project-icon" data-target="#card-discard-setting" onclick="openeditfuwu('${fuwulist.serviceId}','${fuwulist.serviceName}','${fuwulist.standard}','${fuwulist.abstractMoney}')"></i>
		                        </td>
		                     </tr>
		                    </c:forEach>
		                    </tbody>
		                </table>
		            </div>
		           
					<!-- 关于考勤 -->
		            <div class="widgetcontent kaoqin" style="display: none">
		                <div class="more-toolbar" >
		                    <div class="table-toolbar">本月员工缺勤记录
		                    <select name="" id="queryyear" class="chzn-select-search input120">
		                            <option value="2015">2015年</option>
		                            <option value="2016">2016年</option>
		                            <option value="2017">2017年</option>
		                        </select>
		                        <select name="" id="querymonth" class="chzn-select-search input120">
		                            <option value="1">1月</option>
		                            <option value="2">2月</option>
		                            <option value="3">3月</option>
		                            <option value="4">4月</option>
		                            <option value="5">5月</option>
		                            <option value="6">6月</option>
		                            <option value="7">7月</option>
		                            <option value="8">8月</option>
		                            <option value="9">9月</option>
		                            <option value="10">10月</option>
		                            <option value="11">11月</option>
		                            <option value="12">12月</option>
		                        </select>
		                        <input type="search" id="search" placeholder="姓名"/>
		                        <button class="button-search btn width100 ml-10" onclick="changePage()">查询</button>
		                        <button class="button-search btn width100">新增记录</button>
		                        <div class="table-pagination fr">
		                        <button data-toggle="dropdown" class="btn dropdown-toggle perpage perpage0">
		                            10 <span class="iconfa-caret-down afont"></span>
		                        </button>
		                        <ul class="dropdown-menu">
		                        <li><a href="javascript:changePageSize(5)">5</a></li>
				                <li><a href="javascript:changePageSize(10)">10</a></li>
				                <li><a href="javascript:changePageSize(20)">20</a></li>
				                <li><a href="javascript:changePageSize(50)">50</a></li>
				                <li><a href="javascript:changePageSize(100)">100</a></li>
		                        </ul>
				            <div class="left-page" id="previousPageButton" onclick="previous()"><i class="FontAwesome iconfa-caret-left afont" ></i></div>
		                    <div class="right-page" id="nextPageButton" onclick="next()"><i class="FontAwesome iconfa-caret-right afont" ></i></div>
		                    </div>
		                    </div>
		                    <!--table-toolbar-->
		                    <div class="clearfix"></div>
		                </div><!--more-toolbar-->
		
		                <table class="table table-bordered member-list-table">
		                    <thead>
		                    <tr>
		                        <th class="nav_td right_border">工号</th>
		                        <th class="nav_td right_border">姓名</th>
		                        <th class="nav_td right_border">迟到次数</th>
		                        <th class="nav_td right_border">早退次数</th>
		                        <th class="nav_td right_border">请假次数</th>
		                        <th class="nav_td right_border">旷工次数</th>
		                        <th class="nav_td right_border">奖励金额</th>
		                        <th class="nav_td right_border">扣款金额</th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    </tbody>
		                </table>
		            </div>
		            <!-- 关于行为 -->
		            <div class="widgetcontent xingwei" style="display: none">
		                <div class="more-toolbar" >
		                    <div class="table-toolbar">本月员工缺勤记录
		                    <select name="" id="queryyear1" class="chzn-select-search input120" >
		                            <option value="2015">2015年</option>
		                            <option value="2016">2016年</option>
		                            <option value="2016">2017年</option>
		                        </select>
		                        <select name="" id="querymonth1" class="chzn-select-search input120">
		                            <option value="1">1月</option>
		                            <option value="2">2月</option>
		                            <option value="3">3月</option>
		                            <option value="4">4月</option>
		                            <option value="5">5月</option>
		                            <option value="6">6月</option>
		                            <option value="7">7月</option>
		                            <option value="8">8月</option>
		                            <option value="9">9月</option>
		                            <option value="10">10月</option>
		                            <option value="11">11月</option>
		                            <option value="12">12月</option>
		                        </select>
		                        <input type="search" id="search1" placeholder="姓名"/>
		                        <button class="button-search btn width100 ml-10" onclick="changePage1()">查询</button>
		                        <button class="button-search btn width100">新增记录</button>
		                        <div class="table-pagination fr">
		                        <button data-toggle="dropdown" class="btn dropdown-toggle perpage perpage1"> 
		                            10 <span class="iconfa-caret-down afont"></span>
		                        </button>
		                        <ul class="dropdown-menu">
		                        <li><a href="javascript:changePageSize1(5)">5</a></li>
				                <li><a href="javascript:changePageSize1(10)">10</a></li>
				                <li><a href="javascript:changePageSize1(20)">20</a></li>
				                <li><a href="javascript:changePageSize1(50)">50</a></li>
				                <li><a href="javascript:changePageSize1(100)">100</a></li>
		                        </ul>
				            <div class="left-page" id="previousPageButton1" onclick="previous1()"><i class="FontAwesome iconfa-caret-left afont" ></i></div>
		                    <div class="right-page" id="nextPageButton1" onclick="next1()"><i class="FontAwesome iconfa-caret-right afont" ></i></div>
		                    </div>
		                    </div>
		                    <!--table-toolbar-->
		                    <div class="clearfix"></div>
		                </div><!--more-toolbar-->
		
		                <table class="table table-bordered member-list-table1">
		                    <thead>
		                    <tr>
		                        <th class="nav_td right_border">工号</th>
		                        <th class="nav_td right_border">姓名</th>
		                        <th class="nav_td right_border">小过次数</th>
		                        <th class="nav_td right_border">大过次数</th>
		                        <th class="nav_td right_border">警告次数</th>
		                        <th class="nav_td right_border">奖励金额</th>
		                        <th class="nav_td right_border">扣款金额</th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    </tbody>
		                </table>
		            </div>
		            <!-- 关于服务 -->
		            <div class="widgetcontent fuwu" style="display: none">
		                <div class="more-toolbar" >
		                    <div class="table-toolbar">本月员工缺勤记录
		                    <select name="" id="queryyear2" class="chzn-select-search input120">
		                            <option value="2015">2015年</option>
		                            <option value="2016">2016年</option>
		                            <option value="2016">2017年</option>
		                        </select>
		                        <select name="" id="querymonth2" class="chzn-select-search input120">
		                            <option value="1">1月</option>
		                            <option value="2">2月</option>
		                            <option value="3">3月</option>
		                            <option value="4">4月</option>
		                            <option value="5">5月</option>
		                            <option value="6">6月</option>
		                            <option value="7">7月</option>
		                            <option value="8">8月</option>
		                            <option value="9">9月</option>
		                            <option value="10">10月</option>
		                            <option value="11">11月</option>
		                            <option value="12">12月</option>
		                        </select>
		                        <input type="search" id="search2" placeholder="姓名"/>
		                        <button class="button-search btn width100 ml-10"onclick="changePage2()">查询</button>
		                        <button class="button-search btn width100">新增记录</button>
		                        <div class="table-pagination fr">
		                        <button data-toggle="dropdown" class="btn dropdown-toggle perpage perpage2">
		                            10 <span class="iconfa-caret-down afont"></span>
		                        </button>
		                        <ul class="dropdown-menu">
		                        <li><a href="javascript:changePageSize2(5)">5</a></li>
				                <li><a href="javascript:changePageSize2(10)">10</a></li>
				                <li><a href="javascript:changePageSize2(20)">20</a></li>
				                <li><a href="javascript:changePageSize2(50)">50</a></li>
				                <li><a href="javascript:changePageSize2(100)">100</a></li>
		                        </ul>
				            <div class="left-page" id="previousPageButton2" onclick="previous2()"><i class="FontAwesome iconfa-caret-left afont" ></i></div>
		                    <div class="right-page" id="nextPageButton2" onclick="next2()"><i class="FontAwesome iconfa-caret-right afont" ></i></div>
		                    </div>
		                    </div>
		                    <!--table-toolbar-->
		                    <div class="clearfix"></div>
		                </div><!--more-toolbar-->
		
		                <table class="table table-bordered member-list-table2">
		                    <thead>
		                    <tr>
		                        <th class="nav_td right_border">工号</th>
		                        <th class="nav_td right_border">姓名</th>
		                        <th class="nav_td right_border">好评次数</th>
		                        <th class="nav_td right_border">差评次数</th>
		                        <th class="nav_td right_border">投诉次数</th>
		                        <th class="nav_td right_border">奖励金额</th>
		                        <th class="nav_td right_border">扣款金额</th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    </tbody>
		                </table>
		            </div>
		             </div>
	            </div>
	            
	        <!-- </div> -->
	        
	</div>
	      <!--模态框-->
	<div class="modal hide" id="update-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content add-zhiwei-modal">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">考核奖惩设置</h4>
	            </div>
	            <div class="modal-body">
	             <input type="hidden" id="updateRuleType">
	                <div  class="editprofileform" >
	                <input type="hidden" id="updateRuleId">
	                </div>
	            </div><!--modal-body-->
	            <div class="modal-footer">
	                <a class="btn cancel-btn modal-cancel" href="#" onclick="canceladd()">取消</a>
	                <a class="btn btn-primary save-btn modal-confirm" href="#" onclick="updatesave()">保存</a>
	            </div>
	        </div>
	    </div>
	</div> 
	<!--查看行为的详细信息-->
	<div class="modal hide" id="xiangqing-chakan-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="width: 500px;">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content xingwei-chakan-modal">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h5 class="modal-title" id="myModalLabel">制度考核明细</h5>
	            </div>
	            <div class="modal-body">
	                <div class="more-toolbar" >
	                    <div class="table-toolbar">
	                        <div class="table-pagination fr">
	                            <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
	                                10 <span class="iconfa-caret-down afont"></span>
	                            </button>
	                            <ul class="dropdown-menu">
	                                <li><a href="javascript:changePageSizee(5)">5</a></li>
					                <li><a href="javascript:changePageSizee(10)">10</a></li>
					                <li><a href="javascript:changePageSizee(20)">20</a></li>
					                <li><a href="javascript:changePageSizee(50)">50</a></li>
					                <li><a href="javascript:changePageSizee(100)">100</a></li>
	                            </ul>
	                            <div class="left-page" id="previousPageButton2" onclick="previouss()"><i class="FontAwesome iconfa-caret-left afont" ></i></div>
	                    <div class="right-page" id="nextPageButton2" onclick="nextt()"><i class="FontAwesome iconfa-caret-right afont" ></i></div>
	                        </div><!--table-pagination-->
	                    </div><!--table-toolbar-->
	                    <div class="clearfix"></div>
	                </div><!--more-toolbar-->
	                <input type="hidden" id="projectName"><input type="hidden" id="employeeId">
	                <table class="table table-bordered derail">
	                    <thead>
	                    <tr>
		                    <th>名称</th>
		                    <th>时间</th>
		                    <th>原因</th>
		                    <th>扣款</th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div><!--modal body-->
	        </div>
	    </div>
	</div>   
	</div>
    <!--RIGHT PANEL结束 -->
</div>

<script>
jQuery(function(){
	
	var id=jQuery(".n-sub-tab").eq(0).attr("id");
	jQuery("#ruletype").val(id);
	//根据不同的规则展示不同的列表
	showRuleList();
})
    //获取加载页面时的页码信息
    var pageNo = "${page.pageNo}";
    var pageSize = "${page.pageSize}";
    var totalPage = "${page.totalPage}"
    
    jQuery(function(){
        jQuery(".page-item").hover(
            function () {
                jQuery(".trangle-css").addClass("hide");
                jQuery(".page-item").removeClass("active");
                jQuery(this).addClass("active")
                jQuery(this).children(".trangle-css").removeClass("hide");
                var tabTarget = jQuery(this).data("target");
                var id=jQuery(this).attr("id");
                jQuery("#ruletype").val(id);
                showRuleList();
                jQuery(".tabtarget").addClass("hide");
                jQuery(tabTarget).removeClass("hide");
            }, function () {
        })
    })
    
jQuery(function(){
    jQuery(".n-sub-tab").on("click", function(){
      jQuery(".n-sub-tab").removeClass("active");
      jQuery(this).addClass("active");
      var targetTab = jQuery(this).data("target");
      if(targetTab == "#tab2"){
        jQuery(".tab-word").css("border","0px");
      }else{
        jQuery(".tab-word").css("border","");
      }
      var id=jQuery(this).attr("id");
      jQuery("#ruletype").val(id);
      showRuleList();
      jQuery(".target-tab").addClass("hide");
      jQuery(targetTab).removeClass("hide");
    })
  })
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/rewards.js"></script>
</div><!--mainwrapper-->
<script type="text/javascript">
var myDate = new Date();
var y=myDate.getFullYear();
var m=myDate.getMonth()+1;
jQuery("#queryyear").val(y);
jQuery("#queryyear").trigger("liszt:updated");
jQuery("#querymonth").val(m);
jQuery("#querymonth").trigger("liszt:updated");
changePage();
jQuery("#queryyear1").val(y);
jQuery("#queryyear1").trigger("liszt:updated");
jQuery("#querymonth1").val(m);
jQuery("#querymonth1").trigger("liszt:updated");
changePage1();
jQuery("#queryyear2").val(y);
jQuery("#queryyear2").trigger("liszt:updated");
jQuery("#querymonth2").val(m);
jQuery("#querymonth2").trigger("liszt:updated");
changePage2();
</script>
</body>
</html>