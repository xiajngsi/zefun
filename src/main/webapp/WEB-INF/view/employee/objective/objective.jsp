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
    

   <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
    	<%@ include file="/top.jsp" %>
<div class="maincontent">
    <div class="contentinner">
        <h4 class="widgettitle">
            <span class="dingdanzhuantai blue">目标统计</span>
            <span class="ml10">劳动业绩总体目标<span class="red a"></span></span>
            <span class="ml10">指定业绩目标<span class="red b"></span></span>
            <span class="ml10">套餐销售目标<span class="red c"></span></span>
            <span class="ml10">商品销售目标<span class="red d"></span></span>
            <span class="ml10">开卡充值目标<span class="red e"></span></span>
            <!-- <span class="ml10">优惠卡销售目标<span class="blue f"></span></span> -->
        </h4>
			<div class="widgetcontent">
        </div>
        <div class="more-toolbar" >
            <div class="table-toolbar">
                <button class="btn" data-toggle="modal" data-target="#toLeadModal">导入</button>
                <button class="btn" id="downLondimport">导入模板下载</button>
                <button class="btn" id="downLond">导出</button>

                <span>目标年月:</span>

                <select name="" id="queryyear" class="chzn-select-search input80" data-placeholder="选择目标年" >
                    <option value="2015">2015</option>
                    <option value="2016">2016</option>
                </select>
                <select name="" id="querymonth" class="chzn-select-search input80" data-placeholder="选择目标月">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>

                <input type="search" id="search" placeholder="员工姓名/岗位/职位/工号"/>
                <button class="button-search btn width100 ml-10" onclick="changePage()">查询</button>
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
                    <div class="left-page" id="previousPageButton" onclick="previous()"><span class="FontAwesome iconfa-caret-left afont"></span></div>
		            <div class="right-page" id="nextPageButton" onclick="next()"><span class="FontAwesome iconfa-caret-right afont"></span></div>
                </div><!--table-pagination-->
            </div><!--table-toolbar-->
            <div class="clearfix"></div>
        </div><!--more-toolbar-->

        <table class="table table-bordered table-striped member-list-table">
            <thead>
            <tr>
                <th>工号</th>
                <th>姓名  </th>
                <th>部门</th>
                <th>岗位 </th>
                <th>劳动业绩总体目标</th>
                <th>指定业绩目标</th>
                <th>套餐销售目标</th>
                <th>商品销售目标</th>
                <th>开卡/充值目标</th>
                <!-- <th>优惠卡销售目标</th> -->
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            
            </tbody>
        </table>
    </div>
</div>

<div class="modal hide" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <div class="modal-body">
                <form action="<%=basePath %>objective/action/importExcle" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                    <p>
                        <label>文件位置选择:</label>
                        <input type="file" name="filename" id="file" class="input-large" value="" readonly="readonly">
                        <a class="btn modal-cancel" href="#" id="chooseExcle">浏览</a>
<!--                         <input type="file" name="filevalue" class="input-large" value="" style="display: none" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
 -->                    </p>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn modal-cancel" href="#" data-dismiss="modal">取消</a>
                <a class="btn btn-primary modal-confirm" href="#" id="confirm" data-dismiss="modal" onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>

 </div>
    <!--RIGHT PANEL结束 -->
<script type="text/javascript">
  //获取加载页面时的页码信息
    var pageNo = "${page.pageNo}";
    var pageSize = "${page.pageSize}";
    var totalPage = "${page.totalPage}"
    var myDate = new Date();
    var y=myDate.getFullYear();
    var m=myDate.getMonth();
    
    jQuery("#queryyear").val(y);
    jQuery("#queryyear").trigger("liszt:updated");
    jQuery("#querymonth").val(m+1);
    jQuery("#querymonth").trigger("liszt:updated");
    
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/objective.js"></script>
<script type="text/javascript">
	checkPageButton();
	changePage();
	//查询上面的目标统计
	querysum();
</script>

</div>

</body>
</html>