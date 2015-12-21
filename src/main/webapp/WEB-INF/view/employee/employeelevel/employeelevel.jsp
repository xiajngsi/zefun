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

      <div class="breadcrumbwidget">
    <ul class="skins">
        <li><a href="default" class="skin-color default"></a></li>
        <li><a href="orange" class="skin-color orange"></a></li>
        <li><a href="dark" class="skin-color dark"></a></li>
        <li>&nbsp;</li>
        <li class="fixed"><a href="" class="skin-layout fixed"></a></li>
        <li class="wide"><a href="" class="skin-layout wide"></a></li>
    </ul><!--skins-->
    <ul class="breadcrumb">
        <li><a href="javascript：void(0);">员工设置</a> <span class="divider">/</span></li>
        <li class="active">职位设置</li>
    </ul>
</div><!--breadcrumbwidget-->
<div class="maincontent">
    <div class="contentinner">
        <h4 class="widgettitle">
            <span class="dingdanzhuantai blue">岗位列表</span>
            <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span>
        </h4>

        <div class="more-toolbar" >
            <div class="table-toolbar">
                <button class="btn" data-target="#add-zhiwei-modal" data-toggle="modal">添加职位</button>
                <!-- <input type="search" placeholder="职位"/>
                <button class="button-search btn width100 ml-10">查询</button> -->
                <div class="table-pagination fr">
                    <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                        10 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:changePageSize(5)">5</a></li>
		                <li><a href="javascript:changePageSize(10)">10</a></li>
		                <li><a href="javascript:changePageSize(20)">20</a></li>
		                <li><a href="javascript:changePageSize(50)">50</a></li>
		                <li><a href="javascript:changePageSize(100)">100</a></li>
                    </ul>
                    <div class="btn" id="previousPageButton" onclick="previous()"><span class="icon-chevron-left"></span></div>
		            <div class="btn" id="nextPageButton" onclick="next()"><span class="icon-chevron-right"></span></div>
                </div><!--table-pagination-->
            </div><!--table-toolbar-->
            <div class="clearfix"></div>
        </div><!--more-toolbar-->

        <table class="table table-bordered table-striped member-list-table">
            <thead>
            <tr>
                <th>岗位编号</th>
                <th>岗位名称</th>
                <th>职位</th>
                <th class="drop-th">指定提成 </th>
                <th class="drop-th">非指定提成 </th>
                <th>非指定客单价</th>
                <th>指定客单价</th>
                <th>业绩总额</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list.results }" var="employeelevel">
            <tr id="${employeelevel.levelId}">
                <td>${employeelevel.positionCode }</td>
                <td >${employeelevel.positionName }</td>
                <td>${employeelevel.levelName }</td>
                <td >${employeelevel.assignCommission }</td>
                <td>${employeelevel.nonAssignCommission }</td>
                <td>${employeelevel.nonCustomercost }</td>
                <td>${employeelevel.customercost }</td>
                <td>${employeelevel.performancecount }</td>
                <td>
                    <span class="icon-edit" onclick="openedit(${employeelevel.levelId})" ></span>
				    <span class="icon-remove-sign ml30" onclick="deleteinfo(${employeelevel.levelId})"></span>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!--添加职位模态框-->
<div class="modal fade hide" id="add-zhiwei-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-zhiwei-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加职位</h4>
            </div>
            <div class="modal-body">
                <div  class="editprofileform" >
                    <p>
                        <label>岗位名称:</label>
                        <select class="chzn-select-search input-xlarge" id="addpositionId">
                        <c:forEach items="${positionlist }" var="list">
                            <option value="${list.positionId }">${list.positionName }</option>
                         </c:forEach>
                        </select>
                    </p>
                    <p>
                        <label>职位名称:</label>
                        <input type="text" name="name" id="addlevelName" class="input-xlarge" value="" placeholder="">
                    </p>
                    <p>
                        <label>指定提成:</label>
                        <select  id="addassignType" class="chzn-select-search input-small" >
                            <option value="1">固定金额</option>
                            <option value="2">业绩比例</option>
                        </select>
                        <input type="text" name="name" class="input-medium ml13 two-part-input" id="addassignCommission" >
                    </p>
                    <p>
                        <label>非指定提成:</label>
                        <select id="addnonAssignType" class="chzn-select-search input-small" >
                            <option value="1">固定金额</option>
                            <option value="2">业绩比例</option>
                        </select>
                        <input type="text" name="name" id="addnonAssignCommission"class="input-medium ml13 two-part-input" ><!-- <span class="input-word">%</span> -->
                    </p>
                    <hr/>
                    <p>
                        <label style="width: 280px">职位自动升级/降级，tips：每月底自动处理一次</label>
                        <input class="btn" id="addis_upgrade" type="button" value="启用" onclick="changeis()"/>
                    <p>
                        <label>非指定客单价:</label>
                        <input type="text" name="name" id="addnonCustomercost" class="input-small" value=""  style="width: 40px">
                    </p>
                    <p>
                        <label>指定客单价:</label>
                        <input type="text" name="name" id="addcustomercost" class="input-small" value=""  style="width: 40px">
                    </p>
                    <p>
                        <label>业绩总额:</label>
                        <input type="text" name="name" id="addperformancecount" class="input-small" value=""  style="width: 40px">
                    </p>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
                <a class="btn cancel-btn modal-cancel" href="#" onclick="canceladd()">取消</a>
                <a class="btn btn-primary save-btn modal-confirm" href="#" onclick="addsave()">保存</a>
            </div>
        </div>
    </div>
</div>


<!--修改职位模态框-->
<div class="modal fade hide" id="update-zhiwei-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-zhiwei-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改职位</h4>
            </div>
            <div class="modal-body">
                <div class="editprofileform" >
                    <p>
                        <label>岗位名称:</label>
                        <input type="hidden" id="updatelevelId">
                        <select  data-placeholder="Choose a country..." id="updatepositionId" class="chzn-select-search input-xlarge" >
                            <c:forEach items="${positionlist }" var="list">
                            <option value="${list.positionId }">${list.positionName }</option>
                         </c:forEach>
                        </select>
                    </p>
                    <p>
                        <label>职位名称:</label>
                        <input type="text" name="name" id="updatelevelName" class="input-xlarge" value="" placeholder="三星发型师">
                    </p>
                    <p>
                        <label>指定提成:</label>
                        <select  class="chzn-select-search input-small" id="updateassignType">
                            <option value="1">固定金额</option>
                            <option value="2">业绩比例</option>
                        </select>
                        <input type="text" name="name" class="input-medium ml13 two-part-input" value="50" id="updateassignCommission">
                    </p>
                    <p>
                        <label>非指定提成:</label>
                        <select  class="chzn-select-search input-small" id="updatenonAssignType">
                            <option value="1">固定金额</option>
                            <option value="2">业绩比例</option>
                        </select>
                        <input type="text" name="name" class="input-medium ml13 two-part-input" value="10" id="updatenonAssignCommission"><!-- <span class="input-word">%</span> -->
                    </p>
                    <hr/>
                    <p>
                        <label style="width: 280px">职位自动升级/降级，tips：每月底自动处理一次</label>
                        <input class="btn" id="updateis_upgrade" type="button" value="启用" onclick="updatechangeis()"/>
                    <p>
                        <label>非指定客单价:</label>
                        <input type="text" name="name" id="updatenonCustomercost"class="input-small" value=""  style="width: 40px">
                    </p>
                    <p>
                        <label>指定客单价:</label>
                        <input type="text" name="name" id="updatecustomercost"class="input-small" value="" style="width: 40px">
                    </p>
                    <p>
                        <label>业绩总额:</label>
                        <input type="text" name="name" id="updateperformancecount"class="input-small" value=""  style="width: 40px">
                    </p>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
                <a class="btn cancel-btn modal-cancel" href="#" onclick="cancelupdate()">取消</a>
                <a class="btn btn-primary save-btn modal-confirm" href="#" onclick="editsave()">保存</a>
            </div>
        </div>
    </div>
</div>

<script>

    jQuery('#account-time').datetimepicker({
        yearOffset:0,
        lang:'ch',
        timepicker:true,
        format:'d/m/Y',
        formatDate:'Y/m/d',
        minDate:'-1970/01/02', // yesterday is minimum date
        maxDate:'+1970/01/02' // and tommorow is maximum date calendar
    });

    jQuery(".show-more").on("click",function(){
        var detailInfo = jQuery(".more-condition-table");
        if(detailInfo.is(':visible')){
            detailInfo.hide();
        }else{
            detailInfo.show();
        }
    });

  //获取加载页面时的页码信息
    var pageNo = "${list.pageNo}";
    var pageSize = "${list.pageSize}";
    var totalPage = "${list.totalPage}"
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/employeelevel.js"></script>
<script type="text/javascript">
	checkPageButton();
</script>
    </div>
    <!--RIGHT PANEL结束 -->

</div>

</body>
</html>