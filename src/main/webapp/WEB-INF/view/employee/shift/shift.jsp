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
            <c:forEach items="${deptlist}" var="deptlist">
                <li class="n-sub-tab" data-target="#tab1" id="deptId${deptlist.deptId}">
                    <span>${deptlist.deptName}</span>
                    <div class="border-bottom"></div>
                </li>
                </c:forEach>
                <input type="hidden" id="xdeptId">
            </ul>
            <div class="clearfix"></div>
        </div>
		<%-- <ul class="page-tab">
            <!--data-target的值和对应内容的id对应-->
            <c:forEach items="${deptlist}" var="deptlist">
            <li class="page-item" data-target="#tab1" id="deptId${deptlist.deptId}">
                ${deptlist.deptName}
                <span class="trangle-css hide"></span>
            </li>
		</c:forEach>
		<!-- 选择后部门标识在这边 -->
		<input type="hidden" id="xdeptId">
        </ul> --%>
        
        <div class="n-tab-content ">
            <div class="target-tab" id="tab1">
                <div class="tab-padding">
                    <div class="widgetcontent">
                    <table class="table collect-money-table1">
                        <tbody>
                        <tr>
                            <td>早班：<span class="zao"></span> </td>
                            <td>中班：<span class="zhong"></span> </td>
                            <td>晚班：<span class="wan"></span>  </td>
                            <td>全班：<span class="quan"></span> </td>
                            <td> <i class="iconfa-pencil project-icon ml10" data-toggle="modal"  onclick="openeditshif()"></i>班次时间设置</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                    <div class="widgetcontent">
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <button class="btn" data-target="#add-emp-yuangong-banci" onclick="addshift()">新增员工排班</button>
                            <input type="search" id="search" placeholder="员工工号/姓名"/>
                            <button class="button-search btn width100 ml-10" onclick="changePage()">查询</button>
                            <button class="btn" data-toggle="modal" data-target="#toLeadModal">导入</button>
                            <button class="btn" id="downLondimport">导入模板下载</button>
                            <div class="table-pagination fr">
                                <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                                    10 <span class="iconfa-caret-down afont"></span>
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
                    <table class="table table-bordered table-striped collect-money-table">
                        <thead>
                        <tr>
		                    <th>工号</th>
		                    <th>姓名</th>
		                    <th>周一班次</th>
		                    <th>周二班次</th>
		                    <th>周三班次</th>
		                    <th>周四班次</th>
		                    <th>周五班次</th>
		                    <th>周六班次</th>
		                    <th>周日班次</th>
		                    <th></th>
		                </tr>
                        </thead>
                        <tbody>
                        
                        </tbody>
                       
                    </table>

                </div>
            </div>
            </div>
            </div>
                
        <!-- <div class="widgetcontent">
            <table class="table collect-money-table1">
                <thead>
                <tr>
                    <th>早班</th>
                    <th>中班</th>
                    <th>晚班</th>
                    <th>全班</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="zao"></td>
                    <td class="zhong"></td>
                    <td class="wan"></td>
                    <td class="quan"></td>
                    <td>
                        <span class="fr">
                            <i class="iconfa-pencil project-icon" data-toggle="modal" onclick="openeditshif()"></i>
                        </span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div> -->
        

       

<!--添加员工班次模态框-->
<div class="modal hide" id="add-emp-yuangong-banci" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-emp-yuangong-banci">
            <div class="modal-header ">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">员工班次设置</h5>
            </div>
            <div class="modal-body">
                <div class="editprofileform" method="post">
                    <table class="table table-bordered zhibanbiao">
                        <tbody>
                        <tr>
                        	<input type="hidden" id="dataId">
                        	<td colspan="6" id="xiugai" style="display: none">
                                <label>员工名称</label>
                                <input type="hidden" id="employeeId2">
                                <input type="text" id="employeeName2">
                            </td>
                            <td colspan="6" id="xinzeng" style="display: none">
                                <label>员工名称</label>
                                <select data-placeholder="输入员工名字" name="yuangong" class="chzn-select-search" id="employeeId1" onchange="changeradio(this)" >
                                <c:forEach items="${employeeList}" var="employeeList">
                                    <option value="${employeeList.employeeId}">${employeeList.name}</option>
                                </c:forEach>
                                </select>
                            </td>
                        </tr>
							<tr>
								<td>周一</td>
								<td class="zaoban"><span
									class="check-before check-radio"></span> <input
									class="zaoban1" type="radio" name="zhouyi" value="" />早班</td>
								<td class="zhongban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouyi" value="" />中班</td>
								<td class="wanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouyi" value="" />晚班</td>
								<td class="quanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouyi" value="" />全班</td>
								<td class="xiuxi"><span class="check-before check-radio"></span>
									<input type="radio" name="zhouyi" value="0" />休息日</td>
							</tr>
							<tr>
								<td>周二</td>
								<td class="zaoban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouer" value=""  /> 早班</td>
								<td class="zhongban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouer" value=""  />中班</td>
								<td class="wanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouer" value=""  />晚班</td>
								<td class="quanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouer" value=""  />全班</td>
								<td class="xiuxi"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouer" value="0"  />休息日</td>
							</tr>
							<tr>
								<td>周三</td>
								<td class="zaoban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousan" value=""  />早班</td>
								<td class="zhongban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousan" value=""  />中班</td>
								<td class="wanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousan" value=""  />晚班</td>
								<td class="quanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousan" value=""  />全班</td>
								<td class="xiuxi"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousan" value="0" />休息日</td>
							</tr>
							<tr>
								<td>周四</td>
								<td class="zaoban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousi" value="" />早班</td>
								<td class="zhongban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousi" value=""  />中班</td>
								<td class="wanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousi" value=""  />晚班</td>
								<td class="quanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousi" value=""  />全班</td>
								<td class="xiuxi"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhousi" value="0"  />休息日</td>
							</tr>
                            <tr>
								<td>周五</td>
								<td class="zaoban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouwu" value=""  />早班</td>
								<td class="zhongban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouwu" value=""  />中班</td>
								<td class="wanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouwu" value=""  />晚班</td>
								<td class="quanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouwu" value="" />全班</td>
								<td class="xiuxi"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouwu" value="0"  />休息日</td>
							</tr>
							<tr>
								<td>周六</td>
								<td class="zaoban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouliu" value=""  />早班</td>
								<td class="zhongban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouliu" value=""  />中班</td>
								<td class="wanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouliu" value="" />晚班</td>
								<td class="quanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouliu" value=""  />全班</td>
								<td class="xiuxi" ><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouliu" value="0"  />休息日</td>
							</tr>
							<tr>
								<td>周日</td>
								<td class="zaoban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouri" value="" />早班</td>
								<td class="zhongban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouri" value="" />中班</td>
								<td class="wanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouri" value=""/>晚班</td>
								<td class="quanban"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouri" value=""/>全班</td>
								<td class="xiuxi"><span
									class="check-before check-radio"></span> <input
									type="radio" name="zhouri" value="0" />休息日</td>
							</tr>

					</tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn modal-cancel" href="javascript:quxiaoemployeeshif()">取消</a>
                <a class="btn btn-primary modal-confirm" href="javascript:save()">确认</a>
            </div>
        </div>
    </div>
</div>
<!--修改班次-->
<!--修改班次-->
<div class="modal hide" id="fix-banci-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content fix-banci-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改班次</h4>
            </div>

            <div class="modal-body">
                <!--<div>
                    <label>班次名称: <span>早班</span></label>
                </div>-->
                <!--<br>-->
              <table class="table">
                  <thead>
                  <tr>
                      <th>班次</th>
                      <th>开始时间</th>
                      <th>结束时间</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                      <td>早班</td>
                      <td>
                          <input type="text" class="datetime input80" id="zaostart"/>
                      </td>
                      <td>
                          <input type="text" class="datetime input80" id="zaoend"/>
                      </td>
                  </tr>
                  <tr>
                      <td>中班</td>
                      <td>
                          <input type="text" class="datetime input80" id="zhongstart"/>
                      </td>
                      <td>
                          <input type="text" class="datetime input80" id="zhongend"/>
                      </td>
                  </tr>
                  <tr>
                      <td>晚班</td>
                      <td>
                          <input type="text" class="datetime input80" id="wanstart"/>
                      </td>
                      <td>
                          <input type="text" class="datetime input80" id="wanend"/>
                      </td>
                  </tr>
                  <tr>
                      <td>全班</td>
                      <td>
                          <input type="text" class="datetime input80" id="quanstart"/>
                      </td>
                      <td>
                          <input type="text" class="datetime input80" id="quanend"/>
                      </td>
                  </tr>
                  </tbody>
              </table>
            </div><!--modal-body-->

            <div class="modal-footer">
                <a id="quxiao" class="btn cancel-btn modal-cancel" href="#" onclick="quxiaoshif()">取消</a>
                <a id="queren" class="btn btn-primary save-btn modal-confirm" href="javascript:saveshif()" >保存</a>
                <a id="tijiao" class="btn btn-primary save-btn modal-confirm" href="javascript:tijiaoshif()" style="display: none" >保存</a>
            </div>
        </div>
    </div>
</div>
<div class="modal hide" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <!-- <div>
                    	<progress  id="progressBar" value="0" max="100"></progress>
						<span id="percentage"></span>
                    </div> -->
            <div class="modal-body">
                <form action="<%=basePath %>objective/action/importExcle" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                    <p>
                        <label>文件位置选择:</label>
                        <input type="file" name="filename" id="file" class="input-large" value="" readonly="readonly">
                       </p>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="javascript:void()" id="confirm"  onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>
    </div>
</div>


<script>
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}";
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
      jQuery("#xdeptId").val(id);
  	shiftinfo(id);
  	changePage();
      jQuery(".target-tab").addClass("hide");
      jQuery(targetTab).removeClass("hide");
    })
  })
jQuery(function(){
	jQuery(".n-sub-tab").eq(0).addClass("active");
//jQuery(".page-item").eq(0).children(".trangle-css").removeClass("hide");
//dialog("==========="+jQuery(".n-sub-tab").eq(0).attr("id"));
var id=jQuery(".n-sub-tab").eq(0).attr("id");
//dialog(id);
jQuery("#xdeptId").val(id);
shiftinfo(id);
changePage();
//时间
 jQuery('.datetime').datetimepicker({
           datepicker:false,
           format:'H:i',
           step:5
       });

  })
    


jQuery("input[type='radio']").on("click", function(){
            var th = jQuery(this);
             var tr = th.parents("tr");
            tr.find(".check-radio").removeClass("check-after");
            if(th.is(":checked")){
                th.siblings(".check-radio").addClass("check-after");
            }
        });
</script>
<script type="text/javascript"> 



</script> 
<script type="text/javascript" src="<%=basePath %>js/employee/shift.js"></script>

</div>
    
    
    <!--RIGHT PANEL结束 -->


</div><!--mainwrapper-->

</body>
</html>