<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <%@ include file="/template/employeedetail.jsp" %>
 <script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/wenben.js"></script>

  
   
    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>
    
    <style>
    #edui_fixedlayer{
    	z-index:1059 !important;
    }
    	#edui79{
    		z-index:1059 !important;
    	}
    	#edui79_content{
    		z-index:1099 !important;
    	}
    	#edui1_iframeholder{
    		width:920px !important;
    	}
    	#edui1{
    	width: 920px !important;
    	}
    </style>
   
<div class="maincontent">
    <div class="contentinner">
<!--  <input type="button" value="详情" onclick="employeedetail(1)">  -->
        <div class="more-toolbar" >
            <div class="table-toolbar">
                <button class="btn"  onclick="openadd()">添加员工</button>
                <button class="btn" data-toggle="modal" data-target="#toLeadModal">导入</button>
                <button class="btn" id="downLondimport">导入模板下载</button> 
                <button class="btn" id="downLond">导出</button>
                <input type="search" id="search" placeholder="员工姓名/工号/手机号"/>
                <input type="hidden" id="querygangwei">
                <button class="button-search btn width100 ml-10" onclick="changePage()">查询</button>
                <div class="table-pagination fr">
                    <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                        100 <span class="caret"></span>
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
                <th style="text-align: center">工号</th>
                <th style="text-align: center">姓名</th>
                <th style="text-align: center">性别</th>
                <th style="text-align: center">部门</th>
                <th style="text-align: center" class="drop-th">
                	<span class="dropdown-toggle" data-toggle="dropdown">岗位 <span class="caret"></span></span>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                      <li><a tabindex="-1" href="javascript:gangwei('')">全部</a></li>
                      <c:forEach items="${positionlist }" var="position">
                           <li><a tabindex="-1" href="javascript:gangwei(${position.positionId })">${position.positionName }</a></li>
                      </c:forEach>
                      </ul>
				</th>
                <th style="text-align: center">职位 </th>
                <th style="text-align: center">员工账号</th>
                <th style="text-align: center">手机号</th>
                <!-- <th style="text-align: center">入职时间</th> -->
               <!--  <th style="text-align: center">状态</th> -->
                <th style="text-align: center">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.results }" var="employee">
            <tr id="${employee.employeeId}">
                <td style="text-align: center">${employee.employeeCode }</td>
                <td style="text-align: center">${employee.name }</td>
                <td style="text-align: center">${employee.sex }</td>
                <td style="text-align: center">${employee.deptName }</td>
                <td style="text-align: center">${employee.positionName }</td>
                <td style="text-align: center">${employee.levelName }</td>
                <td style="text-align: center">${employee.userName }</td>
                <td style="text-align: center">${employee.phone }</td>
                <%-- <td style="text-align: center">${employee.entryDate }</td> --%>
                <%-- <td style="text-align: center">${employee.employeeStatus }</td> --%>
                <td>
                    <span class="iconfa-pencil project-icon" onclick="openedit(${employee.employeeId})" ></span>
				    <span class="iconfa-trash project-icon" onclick="deleteinfo(${employee.employeeId})"></span>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!--添加员工模态框-->
<div class="modal hide" id="employee-add-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content employee-data-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">员工资料</h5>
            </div>
            <div class="modal-body">
                <div id="tabs">
                    <ul>
                        <li><a href="#tabs-1">基本资料</a></li>
                    </ul>
                    <!-- 人员新增后返回这个人员的id -->
                    <input type="hidden" id="addemployeeid">
                    <div id="tabs-1">
                        <div class="base-info-img" id="container">
                            <img name='headImg' src="" alt="" id="headImage"/> 
                            <input type="hidden" name="hiddenheadImg"/>
                            <button class="btn select-btn" id="pickHeadImg">上传图片</button>
                        </div>
                        <table class="table table-bordered base-info-table">
                            <tbody>
                                <tr>
                                   <td class="width15">部门</td>
                                    <td class="input-td">
                                    <select name="deptId"  class="chzn-select-search input-xlarge" id="deptId" onchange="changedept()">
                                            <option value="">-选择部门-</option>
                                        </select>
                                    </td>
                                    <td class="width15">岗位</td>
                                    <td class="input-td">
                                    <select name="positionId" id="positionId" class="chzn-select-search input-xlarge" onchange="changeposition()">
                                            <option value="">-请先选择岗位-</option>
                                            
                                        </select></td>
                                </tr>
                                <tr>
                                <td class="width15">职位</td>
                                    <td class="input-td">
                                    <select name="levelId"  class="chzn-select-search input-xlarge" id="levelId">
                                            <option value="">-请先选择岗位-</option>
                                        </select>
                                    </td>
                                    <td class="">工号</td>
                                    <td class="input-td">
                                    <input type="text" class="input-xlarge" id="employeeCode"/>
                                    <input type="hidden" class="input-xlarge" id="employeeCode1"/>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td class="">姓名</td>
                                    <td class="input-td">
                                        <input type="text" class="input-xlarge" id="name"/>
                                    </td>
                                    <td class="">性别</td>
                                    <td class="input-td">
                                    <select name="sex" id="sex" class="chzn-select input-xlarge">
                                            <option value="">-选择性别-</option>
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="">身份证</td>
                                    <td class="input-td"><input type="text" class="input-xlarge " id="identityCard"/></td>
                                    <td class="">出生日期</td>
                                    <td class="input-td"><input type="text" class="input-xlarge timePickerClean datetimepicker"  id="birthday"/></td>
                                </tr>
                                <tr>
                                    <td class="">当前状态</td>
                                    <td class="input-td">
                                        <select data-placeholder="在职"  class="chzn-select-search input-xlarge" id="employeeStatus" >
                                            <option value="1">在职</option>
                                            <option value="2">离职</option>
                                        </select>
                                    </td>
                                    <td class="">手机号</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="phone"  onkeyup="setuser()"/></td>
                                </tr>
                                <tr>
                                    <td class="">到职日期</td>
                                    <td class="input-td">
                                        <input type="text" class="input-xlarge timePickerClean datetimepicker" daysoffset="0" id="entryDate"/>
                                    </td>
                                    <td class="">住址</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="address"/></td>
                                </tr>
                                <tr>
                                    <td class="">离职日期</td>
                                    <td class="input-td">
                                        <input type="text" class="width97 timePickerClean datetimepicker"  id="leaveDate"/>
                                    </td>
                                    <td class="">健康证</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="healthCard"/></td>
                                </tr>
                                <tr>
                                    <td class="">介绍人</td>
                                    <td class="input-td">
                                       
                                        <!-- <input type="text" class="input-xlarge" id="recommendId"/> -->
                                        <select  class="chzn-select-search input-xlarge" id="recommendId" >
                                        	<option value="">选择介绍人</option>
                                          <c:forEach items="${recommendList }" var="List">
                                            <option value="${List.employeeId }">${List.name }</option>
                                            </c:forEach>
                                        </select>
                                        
                                    </td>
                                    <td class="">紧急联系人电话</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="emergencyPhone"/></td>
                                </tr>
                                <tr>
                                    <td class="">基本工资</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="baseSalaries"/><span class="word">元/月</span></td>
                                    <td class="">岗位津贴</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="positionSalaries"/><span class="word-right">元/月</span></td>
                                </tr>
                                <tr>
                                    <td class="">账号</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="userName" disabled="true"/></td>
                                    <td class="">选择角色</td>
                                    <td class="input-td word-parent">
                                    <select  class="chzn-select-search input-xlarge" id="roleId" >
                                        	<option value="">选择角色</option>
                                          <c:forEach items="${rolelist }" var="rolelist">
                                            <option value="${rolelist.roleId }">${rolelist.roleName }</option>
                                            </c:forEach>
                                     </select>
									</td>
                                </tr>

                            </tbody>
                        </table>
                        <div class="clearfix"></div>
                        <div class="self-btn">
                            <div class="btn btn-primary ml10 fr" onclick="addsave()">确定保存</div>
                            <button class="btn fr" onclick="addcanse()">取消</button>
                        </div>
                    </div><!--tabs-1-->
                </div>
            </div><!--modal-body-->
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->

<!--修改员工资料模态框-->
<div class="modal hide" id="employee-update-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content employee-data-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">员工资料</h5>
            </div>
            <div class="modal-body">
                <div id="tabs">
                    <!-- <ul>
                        <li><a href="#tabs-11">基本资料</a></li>
                        <li><a href="#tabs-22" onclick="querypx()">个人简介</a></li>
                        <li><a href="#tabs-33" onclick="querygz()">工作经历</a></li>
                        <li><a href="#tabs-44" onclick="querysc()">擅长项目</a></li>
                        <li><a href="#tabs-55" onclick="queryjcjl()">奖惩记录</a></li>
                        <li><a href="#tabs-66" onclick="querytjgx()">推介关系图</a></li>
                        <li><a href="#tabs-77">派遣调动</a></li>
                    </ul> -->
                    <!-- 人员的id -->
                    <input type="hidden" id="updateemployeeId">
                    <div id="tabs-11">
                        <div class="base-info-img" id="container">
                            <img name='updateheadImg' src="" alt="" id="updateheadImage"/> 
                            <input type="hidden" name="hiddenupdateheadImage"/>
                            <button class="btn select-btn" id="updatepickHeadImg">上传图片</button>
                        </div>
                        <table class="table table-bordered base-info-table" >
                            <tbody>
                                <tr>
                                <td class="width15">部门</td>
                                    <td class="input-td">
                                    <select name="updatedeptId"  class="chzn-select-search input-xlarge" id="updatedeptId" onchange="changedept1()">
                                    		<c:forEach items="${deptlist}" var="deptlist">
                                            <option value="${deptlist.deptId}">${deptlist.deptName}</option>
                                            </c:forEach>
                                     </select>
                                    </td>
                                    <td class="width15">岗位</td>
                                    <td class="input-td">
                                    <select name="updatepositionId" id="updatepositionId" class="chzn-select-search input-xlarge" onchange="changeposition1()">
                                            <option value="">-选择岗位-</option>
                                            <c:forEach items="${positionlist }" var="position">
                                            <option value="${position.positionId }">${position.positionName }</option>
                                            </c:forEach>
                                        </select></td>
                                </tr>
                                <tr>
                                <td class="width15">职位</td>
                                    <td class="input-td">
                                    <select name="updatelevelId"  class="chzn-select-search input-xlarge" id="updatelevelId">
                                            <option value="">-请先选择岗位-</option>
                                        </select>
                                    </td>
                                    <td class="">工号</td>
                                    <td class="input-td">
                                    <input type="text" class="input-xlarge" id="updateemployeeCode"/>
                                    <input type="hidden" class="input-xlarge" id="updateemployeeCode1"/>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td class="">姓名</td>
                                    <td class="input-td">
                                        <input type="text" class="input-xlarge" id="updatename"/>
                                    </td>
                                    <td class="">性别</td>
                                    <td class="input-td">
                                    <select name="updatesex" id="updatesex" class="chzn-select input-xlarge">
                                            <option value="">-选择性别-</option>
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="">身份证</td>
                                    <td class="input-td"><input type="text" class="input-xlarge timePickerClean" id="updateidentityCard"/></td>
                                    <td class="">出生日期</td>
                                    <td class="input-td"><input type="text" class="input-xlarge timePickerClean datetimepicker"  id="updatebirthday"/></td>
                                </tr>
                                <tr>
                                    <td class="">当前状态</td>
                                    <td class="input-td">
                                        <select data-placeholder="在职"  class="chzn-select-search input-xlarge" id="updateemployeeStatus" >
                                            <option value="1">在职</option>
                                            <option value="2">离职</option>
                                        </select>
                                    </td>
                                    <td class="">手机号</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updatephone"/></td>
                                </tr>
                                <tr>
                                    <td class="">到职日期</td>
                                    <td class="input-td">
                                        <input type="text" class="input-xlarge timePickerClean datetimepicker"  id="updateentryDate"/>
                                    </td>
                                    <td class="">住址</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updateaddress"/></td>
                                </tr>
                                <tr>
                                    <td class="">离职日期</td>
                                    <td class="input-td">
                                        <input type="text" class="width97 timePickerClean datetimepicker"  id="updateleaveDate"/>
                                    </td>
                                    <td class="">健康证</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updatehealthCard"/></td>
                                </tr>
                                <tr>
                                    <td class="">介绍人</td>
                                    <td class="input-td">
                                       
                                        <!-- <input type="text" class="input-xlarge" id="recommendId"/> -->
                                        <select  class="chzn-select-search input-xlarge" id="updaterecommendId" >
                                        	<option value="">选择介绍人</option>
                                          <c:forEach items="${recommendList }" var="List1">
                                            <option value="${List1.employeeId }">${List1.name }</option>
                                            </c:forEach>
                                        </select>
                                        
                                    </td>
                                    <td class="">紧急联系人电话</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updateemergencyPhone"/></td>
                                </tr>
                                <tr>
                                    <td class="">基本工资</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="updatebaseSalaries"/><span class="word">元/月</span></td>
                                    <td class="">岗位津贴</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="updatepositionSalaries"/><span class="word-right">元/月</span></td>
                                </tr>
                                <tr>
                                    <td class="">选择角色</td>
                                    <td class="input-td word-parent">
                                    <select  class="chzn-select-search input-xlarge" id="updateroleId" >
                                        	<option value="">选择角色</option>
                                          <c:forEach items="${rolelist }" var="rolelist">
                                            <option value="${rolelist.roleId }">${rolelist.roleName }</option>
                                            </c:forEach>
                                     </select>
                                    </td>
                                    <td class=""></td>
                                    <td class="input-td word-parent">
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                        <div class="clearfix"></div>
                        <div class="self-btn">
                            <div class="btn btn-primary ml10 fr" onclick="updatesave()">确定保存</div>
                            <button class="btn fr" onclick="updatecanse()">取消</button>
                        </div>
                    </div><!--tabs-1-->

                    <!--tabs-1-->
                    <!-- <div id="tabs-22">
	                    <script id="editor1" type="text/plain" style="width:960px;height:300px;"></script>
	                    <button class="btn fr" onclick="savedesc()">保存</button> 
                    </div> -->
                    <!--tabs-1-->
                    
                    <!--tabs-5-->
                    <!-- <div id="tabs-55" style="height: 500px">
                        <div id="custom-toolbar" class="clearfix">
                            <select name="" id="querytype" class="chzn-select-search input80" data-placeholder="选择目标年" onchange="queryjcjl()">
			                    <option value="1">处罚</option>
			                    <option value="2">奖励</option>
			                </select>
                        </div>

                        <table class="table table-bordered self-table-striped table55" >
                            <thead>
                            <tr>
                                <th>时间 </th>
                                <th>原因</th>
                            </tr>
                            </thead>
                            <tbody>
                            
                        </table>
                    </div> -->
                    <!--tabs-5-->

                    <!-- <div id="tabs-66">
                    <table class="table tuijie-table table661">
                            <tbody>
                            </tbody>
                       </table>
                       <table class="table tuijie-table table662">
                            <tbody>
                            </tbody>
                       </table>
                       <table class="table tuijie-table table663">
                            <tbody>
                            </tbody>
                       </table>
                    </div> --><!--tabs-6-->

                    <%-- <div id="tabs-77">
                        <div class="more-toolbar" >
                            <div class="table-toolbar">
                                <select name="" id="storeId" class="chzn-select-search">
                                   <c:forEach items="${storelist }" var="storelist">
                                    <option value="${storelist.storeId }">${storelist.storeName }</option>
                                    </c:forEach>
                                </select>
                                <input type="text" class="" id="dispatch-start1"/>
                                <div class="btn" onclick="pqsave()">确定调出</div>
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
                                    <div class="left-page"><i class="FontAwesome iconfa-caret-left afont"></i></div>
                                    <div class="right-page"><i class="FontAwesome iconfa-caret-right afont"></i></div>
                                </div>
                            </div><!--table-toolbar-->
                            <div class="clearfix"></div>
                        </div><!--more-toolbar-->

                        <table class="table table-bordered table-striped jifen-record-table table77">
                            <thead>
                            <tr>
                                <th>调往门店 </th>
                                <th>派遣时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="single">
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div><!--tabs-7--> --%>
                </div>
            </div><!--modal-body-->
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->
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
                   </p>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="#" id="confirm" data-dismiss="modal" onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>


<div class="modal hide in" id="add-content-image1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage1" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>

<script type="text/javascript">
//获取加载页面时的页码信息
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}"
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/employee.js"></script>
</div>
<!--RIGHT PANEL结束 -->
</div>
</body>
</html>