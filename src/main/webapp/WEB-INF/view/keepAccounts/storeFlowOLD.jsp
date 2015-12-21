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
	        <li><a href="dashboard.hbs">收银记账</a> <span class="divider">/</span></li>
	        <li class="active">开支记账</li>
	    </ul>
	  </div><!--breadcrumbwidget-->
		<div class="maincontent">
		    <div class="contentinner">
		        <h4 class="widgettitle">
		            <span class="dingdanzhuantai blue">收支表单</span>
		            <span class="dengdaiqueren">区间收入汇总: <span class="num" id = "inComeAllID">${inComeAll}</span></span>
		            <span class="fuwuzhong">区间支出汇总: <span class="blue num" id = "outComeAllID">${outComeAll}</span></span>
		            <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span>
		        </h4>
		
		        <div id="custom-toolbar" >
		            <div class="table-toolbar" id="container">
		                <button class="btn" id="addStoreflow">新增账单</button>
		                <button class="btn" data-toggle="modal" data-target="#toLeadModal">导入</button>
		                <button class="btn" id = "downLond">导出</button>
		                <input type="search" class="input80" id="begin_time" name="beginDate" value="${beginDate}" onfocus="this.blur()"/>
		                <span>--</span>
		                <input type="search" class="input80" id="end_time" name="endDate" value="${endDate}"  onfocus="this.blur()"/>
		                <button class="btn" id="findDate">查询时间区间</button>
		            </div>
		            <div class="table-pagination">
		                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
		                    10 <span class="caret"></span>
		                </button>
		                <ul class="dropdown-menu">
		                    <li><a href="javascript:changePageSize(10)">10</a></li>
		                    <li><a href="javascript:changePageSize(20)">20</a></li>
		                    <li><a href="javascript:changePageSize(50)">50</a></li>
		                    <li><a href="javascript:changePageSize(100)">100</a></li>
		                </ul>
		                <button class="btn" id="previousPageButton"><span class="icon-chevron-left"></span></button>
		                <button class="btn" id="nextPageButton"><span class="icon-chevron-right"></span></button>
		            </div>
		        </div>
		
		        <table class="table table-bordered table-striped collect-money-table">
		            <thead>
		            <tr>
		                <th class="dropdown-toggle" data-toggle="dropdown"> 收支类别</th>
		                <th class="dropdown-toggle" data-toggle="dropdown">收支项目</th>
		                <th>收支来源/去向</th>
		                <th>收入金额</th>
		                <th>支出金额</th>
		                <th>经手人</th>
		                <th>记账人</th>
		                <th>记账时间</th>
		                <th>备注说明</th>
		                <th></th>
		            </tr>
		            </thead>
		            <tbody id = "storeflowTbody">
		              <c:forEach items="${page.results }" var="storeflow">
		                <tr id="${storeflow.flowId}">
		                    <td>${storeflow.businessType}</td>
		                    <td>${storeflow.businessProject}</td>
		                    <td>${storeflow.flowSource}</td>
		                    <td><c:if test="${storeflow.flowType == 2}">${storeflow.flowAmount}</c:if></td>
		                    <td class="expend"><c:if test="${storeflow.flowType == 1}">${storeflow.flowAmount}</c:if></td>
		                    <td>${storeflow.principalId}</td>
		                    <td>${storeflow.operatorId}</td>
		                    <td>${storeflow.flowTime}</td>
		                    <td>${storeflow.businessDesc}</td>
		                    <td>
		                       <span class="icon-edit" onclick="editStoreflow(this,${storeflow.flowId})" ></span>
				               <span class="icon-remove-sign ml30" onclick="deleteStoreflow(${storeflow.flowId})"></span>
		                    </td>
		                </tr>
		              </c:forEach>
		            </tbody>
		        </table>
		    </div>
		</div>

<!--新增账单模态框-->
<div class="modal fade hide" id="add-account" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">新增账单</h5>
            </div>
            <div class="modal-body">
                <form action="<%=basePath %>KeepAccounts/addStoreFlow" class="editprofileform" method="post" id="addForm">
                    <input type="hidden" name="flowId" value="">
                    <p>
                        <label>收支类别:</label>
                        <span class="formwrapper">
                            <select data-placeholder="Choose a Country..." name="businessType" class="chzn-select input-large" >
                              <c:forEach items="${businessTypeList}" var="businessType">
                                 <option value="${businessType.codeName}">${businessType.codeName}</option>
                              </c:forEach>
                            </select>
                        </span>
                    </p>
                    <p>
                        <label>收支项目:</label>
                        <span class="formwrapper">
                            <select data-placeholder="Choose a Country..." name="businessProject" class="chzn-select input-large" >
                              <c:forEach items="${businessProjectList}" var="businessProject">
                                 <option value="${businessProject.codeName}">${businessProject.codeName}</option>
                              </c:forEach>
                            </select>
                        </span>
                    </p>
                    <p class="big-select">
                        <label>收支来源/去向:</label>
                         <span class="formwrapper">
                              <select data-placeholder="Choose a Country..." name="flowSource" class="chzn-select input-large" tabindex="2">
                                 <c:forEach items="${flowSourceList}" var="flowSource">
                                    <option value="${flowSource.codeName}">${flowSource.codeName}</option>
                                 </c:forEach>
                              </select>
                          </span>
                    </p>
                    <p>
                        <label>流水类型:</label>
                         <span class="formwrapper">
                              <select data-placeholder="Choose a Country..." name="flowType" class="chzn-select input-large">
                                  <option value="2">收入</option>
                                  <option value="1">支出</option>
                              </select>
                          </span>
                    </p>
                    <p>
                        <label>收入金额:</label>
                        <input type="text" name="flowAmount" class="input-large" value="">
                    </p>
                    <p>
                        <label>经手人:</label>
                        <input type="text" name="principalId" class="input-large" value="">
                    </p>
                    <p>
                        <label>记账人:</label>
                        <input type="text" name="operatorId" class="input-large" value="">
                    </p>
                    <p>
                        <label>备注:</label>
                        <textarea name="businessDesc" id="" cols="30" rows="3" class="input-large">
                        </textarea>
                    </p>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn modal-cancel" href="#" data-dismiss="modal">取消</a>
                <a class="btn btn-primary modal-confirm" href="#" id="storeFlowConfirm" data-dismiss="modal">确定</a>
            </div>
        </div>
    </div>
</div>

<!--导入模态框-->
<div class="modal fade hide" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <div class="modal-body">
                <form action="<%=basePath %>KeepAccounts/readExcle" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                    <p>
                        <label>文件位置选择:</label>
                        <input type="text" name="filename" class="input-large" value="" readonly="readonly">
                        <a class="btn modal-cancel" href="#" id="chooseExcle">浏览</a>
                        <input type="file" name="filevalue" class="input-large" value="" style="display: none" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                    </p>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn modal-cancel" href="#" data-dismiss="modal">取消</a>
                <a class="btn btn-primary modal-confirm" href="#" id="confirm" data-dismiss="modal">确定</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	//获取加载页面时的页码信息
	var pageNo = "${page.pageNo}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.totalPage}";
</script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/storeFlow.js"></script>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

</body>
</html>