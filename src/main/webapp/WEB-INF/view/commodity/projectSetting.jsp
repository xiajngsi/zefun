<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> 
<%@ include file="/head.jsp" %>
<%
   String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<body>
<style>
	.crop-container{
		width: 1000px;
		height: 640px;
	}
</style>

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
				
				<div class="project-list">
		            <div class="project-list-head">
		                <input type="search" placeholder="搜索" class="search-input"/>
		                <button type="button" class="btn search-button" id="search-member" onclick="searchProject(this);">搜索</button>
		            </div>
		            <div class="project-list-ul">
		            	<c:forEach var="deptInfoDto" items="${deptProjectList }" varStatus="status">
           					<div class="project-category">
		            			<div class="project-name" id="deptId${deptInfoDto.deptId}">
		            				<i class="afont iconfont icon-iconfontxialaeps"></i> ${deptInfoDto.deptName }
		            				<i class="iconfa-plus project-icon fr" onclick="addCategory(${deptInfoDto.deptId},this);"></i>
		            			</div>
		            			<ul class="project-sublist dept ">
		            				<li class="project-sublist-title" id="addCategoryLi" style="display:none;"><input type="text" name="categoryName" id="categoryName" style="width: 73%;"/>
		            					<i class="iconfa-plus project-icon" onclick="saveCategory(${deptInfoDto.deptId},this);"></i><i class="iconfa-trash project-icon" onclick="deleteCategory(this);"></i>
		            				</li>
		            				
				            		<c:forEach var="projectCategory" items="${deptInfoDto.projectCategoryList }" varStatus="status">
				            		<ul class="project-sublist category" style="display: block;">
				            			<li class="project-sublist-title" id="categoryId${projectCategory.categoryId}">
		            						<div>
		            						    <i class="afont iconfont icon-iconfontxialaeps"></i>
		            							<span class="categoryNameSpan">${projectCategory.categoryName }</span>
			            						<span class="fr">
			            							<i class="iconfa-plus project-icon" onclick="addProjectsList(${deptInfoDto.deptId},${projectCategory.categoryId},this)"></i>
					                                <i class="iconfa-pencil project-icon" onclick="showEditCategory(this);"></i>
					                                <i class="iconfa-trash project-icon" onclick="deleteProjectCategory(${projectCategory.categoryId},${deptInfoDto.deptId},this);"></i>
					                            </span>
				                            </div>
				                            <div style="display:none">
				                            	<input type="text" id="editCategoryName" value="${projectCategory.categoryName }" style="width: 70%;"/>
				                            	<i class="icon-duihao iconfont" onclick="editCategory(${projectCategory.categoryId},${deptInfoDto.deptId},this);"></i>
				                            	<i class="icon-xx iconfont" onclick="cancelEditCategory(this);"></i>
				                            </div>
		            					</li>
	            						<c:forEach var="projectInfoDto" items="${projectCategory.projectList }" varStatus="status">
	            							<li class="project-sublist-content" id="projectId${projectInfoDto.projectId}" onclick="queryProjectById(${projectInfoDto.projectId},this);">${projectInfoDto.projectName }
					                            <span class="fr">
					                            	<i class="icon-fuzhi iconfont" onclick="iconCopyProject(${projectInfoDto.projectId},${deptInfoDto.deptId},this);" title="复制"></i>
					                                <i class="iconfa-trash project-icon" onclick="deleteProject(${projectInfoDto.projectId},${deptInfoDto.deptId},this);" title="删除"></i>
					                            </span>
					                        </li>
				            			</c:forEach>
				            			</ul>
			            			</c:forEach>
		            			</ul>
		            		</div>
		            		<div class="gap"></div>
           				</c:forEach>
		            </div>
		        </div>
				
				<div class="project-setting">
					<h4 class="widgettitle">
		              <span class="dingdanzhuantai">正在新增项目</span>
		              <span class="video" title="新增项目" style="float: right; font-weight: 400;color: #333;cursor: pointer"><i class="iconfa-plus" onclick="emptyInput()"></i></span>
		            </h4>
		            <form id="projectform">
		            	<!-- <input type="hidden" id="isUpdateData" value="0"/> -->
		                <div class="img-word">
		                    <div class="x-edit-left" id="container">
		                        <div class="file-box" onclick="cavsen(this)" >
		                        	<img alt="" name="headImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
		                        	<input type="hidden" name="projectImage" value="zefun/images/pic_none.gif" />
		                        </div>
		                        <div id="pickfiles">点击上传图片</div>
		                        <!-- <button type="button" class="btn select-img-btn">选择图库</button> -->
		                        <div class="img-chicun">图片尺寸600x600像素</div>
		                    </div>
		                    <div class="x-edit-right" style="margin-bottom: 10px;">
		                        <div class="two-part">
			                    	<label for="" class="font-bold">项目所属部门</label>
			                        <select data-placeholder="选择所属部门"  class="chzn-select input80" name="deptId" id="deptIdSel" onchange="changeDept(this)" datatype="*" nullmsg="请选择项目所属部门！" errormsg="请选择项目所属部门！">
			                        	<c:forEach var="deptInfo" items="${deptProjectList }" varStatus="status">
			                        		<option value="${deptInfo.deptId }">${deptInfo.deptName }</option>
			                        	</c:forEach>
			                        </select>
		                        </div>
		                        <div class="two-part">
			                        <label for="" class="font-bold">项目类别</label>
			                        <input type="hidden" value="" id="defaultCategoryId">
			                        <select data-placeholder="选择项目类别"  class="chzn-select input80" name="categoryId" id="categoryNameSel" onchange="changeCategory(this)" datatype="*" nullmsg="请选择项目类别！" errormsg="请选择项目类别！">
			                       	 	<option value="0">选择项目类别</option>
			                             <c:if test="${!empty deptProjectList }">
			                                 <c:forEach var="projectCategory" items="${deptProjectList[0].projectCategoryList }" varStatus="status">
	                                            <option value="${projectCategory.categoryId }">${projectCategory.categoryName }</option>
	                                        </c:forEach>
			                             </c:if>
			                        </select>
		                        </div>
		                        <div class="two-part">
			                    	<label for="" class="font-bold">项目名称</label>
			                        <input type="text" class="input80 inputxt Validform_error" name="projectName" id="projectName" datatype="s1-8" errormsg="为了顾客的完美体验，名称最长8个字" nullmsg="请填写项目名称！"/>
		                        </div>
		                        <div class="clearfix"></div>
		                        <input type="hidden" name="projectId" id="projectId"/>
		                        <input type="hidden" name="isUpdateData" id="isUpdateData"/>
		                        <label for="" class="font-bold mt5">项目描述</label>
		                        <textarea name="projectDesc" id="projectDesc" cols="100" rows="3" datatype="*" nullmsg="请输入项目描述！" errormsg="请输入项目描述！"></textarea>
		                    </div>
		                </div>
		                <div class="clearfix"></div>
		                <table class="table">
	                          <thead>
	                          <tr>
	                              <th>附属图片(点击图片可替换)
	                              <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="附属图片:为增加您服务项目的吸引力,顾客将看到此处增加的图片将和以上主图片滚动播放">
	                              </th>
	                          </tr>
	                          </thead>
	                          <tbody>
		                          <tr>
		                              <td>
		                              	  <div id="containerAffiliated" class="file-box fl ml30">
					                        <div id="affiliatedHeadImg" style="position: relative; z-index: 1;" onclick="cavsen(this)">
					                        	<img alt="" name="affiliatedHeadImg" src="<%=picPath %>zefun/images/pic_none.gif" class="file-btn">
					                        	<input type="hidden" name="affiliatedImage" value="zefun/images/pic_none.gif">
					                        </div>
						                  <div id="html5_1a1qtprml18ikeu96bl1vlo43u6_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 132px; height: 107px; overflow: hidden; z-index: 0;"><input id="html5_1a1qtprml18ikeu96bl1vlo43u6" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,.jpg,.jpeg,image/png,.png,image/bmp,.bmp"></div></div>
					                      
		                                  <div id="containerAffiliated1" class="file-box fl ml30">
					                        <div id="affiliatedHeadImg1" style="position: relative; z-index: 1;" onclick="cavsen(this)">
					                        	<img alt="" name="affiliatedHeadImg" src="<%=picPath %>zefun/images/pic_none.gif" class="file-btn">
					                        	<input type="hidden" name="affiliatedImage" value="zefun/images/pic_none.gif">
					                        </div>
						                  <div id="html5_1a1qtprmo8bm1fnshva5onidb9_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 132px; height: 107px; overflow: hidden; z-index: 0;"><input id="html5_1a1qtprmo8bm1fnshva5onidb9" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,.jpg,.jpeg,image/png,.png,image/bmp,.bmp"></div></div>
		
		                                  <div id="containerAffiliated2" class="file-box fl ml30">
					                        <div id="affiliatedHeadImg2" style="position: relative; z-index: 1;" onclick="cavsen(this)">
					                        	<img alt="" name="affiliatedHeadImg" src="<%=picPath %>zefun/images/pic_none.gif" class="file-btn">
					                        	<input type="hidden" name="affiliatedImage" value="zefun/images/pic_none.gif">
					                        </div>
						                  <div id="html5_1a1qtprmr1dsv1pi86v21jnm6ec_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 132px; height: 107px; overflow: hidden; z-index: 0;"><input id="html5_1a1qtprmr1dsv1pi86v21jnm6ec" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,.jpg,.jpeg,image/png,.png,image/bmp,.bmp"></div></div>
		
		                                  <div id="containerAffiliated3" class="file-box fl ml30">
					                        <div id="affiliatedHeadImg3" style="position: relative; z-index: 1;" onclick="cavsen(this)">
					                        	<img alt="" name="affiliatedHeadImg" src="<%=picPath %>zefun/images/pic_none.gif" class="file-btn">
					                        	<input type="hidden" name="affiliatedImage" value="zefun/images/pic_none.gif">
					                        </div>
						                  <div id="html5_1a1qtprmufu46fr1nb41hka13l2f_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 132px; height: 107px; overflow: hidden; z-index: 0;"><input id="html5_1a1qtprmufu46fr1nb41hka13l2f" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,.jpg,.jpeg,image/png,.png,image/bmp,.bmp"></div></div>
		                              </td>
		                          </tr>
	                          </tbody>
	                      </table>
	                    <p></p>
		                <div class="more-toolbar" >
			                <div class="table-toolbar">
			                    <label for="">门店价格设置</label>
			                </div>
			                <div class="clearfix"></div>
			            </div>
		                <!-- <div class="table-title ml8">门店价格设置</div> -->
		                <table class="table">
		                    <thead>
			                    <tr>
			                        <th>门店价格</th>
			                        <th>成本价格
			                        <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="为了更准确的计算门店的盈利，强烈建议您为每一个项目设置一个基础成本，不包括员工提成。">
			                        </th>
			                        <th>接受预约
			                        <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="选择'是'则项目会在您的微信门店预约中显示,'否'则不会显示">
			                        </th>
			                        <th>预约优惠金额
			                        <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="为鼓励更多顾客通过预约进行消费,您可以设置优惠金额吸引顾客预约">
			                        </th>
			                        <th>是否抵扣礼金</th>
			                        <th>最大抵扣礼金</th>
			                    </tr>
		                    </thead>
		                    <tbody>
			                    <tr>
			                        <td>
			                            <input type="text" class="input70" onblur="changeYuYuejiage(this)" name="projectPrice" id="projectPrice" datatype="d2-2" nullmsg="请输入门店价格！" errormsg="门店价格:请输入数字,可以保留两位小数" /><span class="percent-symbol">元</span>
			                        </td>
			                        <td>
			                        	<input type="text" class="input70" name="costPrice" id="costPrice" datatype="d2-2" nullmsg="请输入成本价格！" errormsg="成本价格:请输入数字,可以保留两位小数" /><span class="percent-symbol">元</span>
			                        </td>
			                        <td>
			                            <input type="checkbox" name="isAppointment" id="isAppointment" value="1" class="lcs_check" checked="checked" autocomplete="on" />
			                        </td>
			                        <td>
			                        	<input type="text" class="input70" name="appointmentPrice" value="0" id="appointmentPrice" datatype="n" nullmsg="请输入预约优惠价格！" errormsg="预约优惠价格:请输入数字！"/><span class="percent-symbol">元</span>
			                        </td>
			                        <td>
			                            <input type="checkbox" name="isGiftCash" id="isGiftCash" value="0" class="lcs_check" checked="checked" autocomplete="on" />
			                        </td>
			                        <td>
			                            <input type="text" class="input70" name="highestDiscount" value="0" id="highestDiscount"  datatype="n" nullmsg="请输入最大礼金抵扣！" errormsg="最大礼金抵扣:请输入数字！"/><span class="percent-symbol">元</span>
			                        </td>
			                    </tr>
		                    </tbody>
		                </table>
		                
		                <p></p>
		                <div class="clearfix"></div>
		                <div class="more-toolbar" >
			                <div class="table-toolbar">
			                    <label for="">为项目设置服务步骤</label>
			                    <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="一个步骤计算一次业绩与提成，不能设置相同牌位的步骤">
			                </div>
			                <div class="clearfix"></div>
			            </div>
		                <input type="hidden" id="shiftMahjongId" />
                        <table class="table isOrder">
                            <thead>
                                <tr>
                                    <th>步骤顺序</th>
                                    <th>轮牌名称
                                    <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="按照项目提供服务的顺序增加流水牌,流水牌需要在'员工轮牌'中提前设置好">
                                    </th>
                                    <th>步骤名称
                                    <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="此处的步骤名称顾客不会看到,仅供员工手机开单时选择步骤下的服务人员">
                                    </th>
                                    <th>业绩计算方式<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="如果为固定，不管实收金额，员工业绩为填写的金额；如果为比例，业绩＝(项目实收－所有固定的业绩金额)＊比例"></th>
                                    <th>员工业绩<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="如果为固定，不管实收金额，员工业绩为填写的金额；如果为比例，业绩＝(项目实收－所有固定的业绩金额)＊比例"></th>
                                    <th>是否可预约<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="不同项目可能有多个服务步骤,但是只有一个步骤可被预约"></th>
                                    <th><span class="fr font-span minus"><i class="iconfa-plus" onclick="addProjectStep();"></i></span></th>
                                </tr>
                            </thead>
                            <tbody id="projectStepTbody">
                                <tr name="projectStepTr">
                                    <td><span name="projectStepNumber" class="input60">1</span></td>
                                    <td>
                                        <select data-placeholder="选择牌位名称" class="chzn-select wthn100" name="shiftMahjongId" onchange="changeMahjongStep(this);">
				                          <c:if test="${!empty deptMahjongList }">
				                            <c:forEach var="shiftMahjongDto" items="${deptMahjongList[0].mahjongLevelList }" varStatus="status">
				                                <option value="${shiftMahjongDto.shiftMahjongId }">${shiftMahjongDto.shiftMahjongName }</option>
				                            </c:forEach>
				                          </c:if>
				                        </select>
                                    </td>
                                    <td><input type="text" class="input100" name="shiftStepName" datatype="s1-8" nullmsg="请输入步骤名称！" errormsg="步骤名称:最多可输入8个汉字"/></td>
                                    <td>
			                        	<select name="stepPerformanceType" class="chzn-select w70">
			                                <option value="2">固定</option>
			                                <option value="1">比例</option>
			                            </select>
			                        </td>
                                    <td>
			                        	<input type="text" class="input70" name="stepPerformance" id="stepPerformance" datatype="n" nullmsg="请输入员工业绩计算！" errormsg="员工业绩计算:请输入数字！"><span class="percent-symbol">元</span>
			                        </td>
                                    <td><input type="checkbox" value="1" class="lcs_check isDisableApp" checked="checked" autocomplete="on" /></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
		                
		                <p></p>
		                <div class="clearfix"></div>
		                <div id="xinxiugaishezhibuzhou">
		                 <div class="more-toolbar" >
			                <div class="table-toolbar">
			                    <label for="">请为<span id="num">1</span>号步骤<span id="name"></span>设置职位提成</label>
			                    <input type="hidden" value="${fn:length(deptMahjongList[0].mahjongLevelList[0].employeeLevelList) }" name="zhiweinum">
			                </div>
			                <div class="clearfix"></div>
			            </div>
		                <table class="table">
		                    <thead>
			                    <tr>
			                        <th>职位名称
			                        <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="此处的职位名称为所有可以参与该步骤服务并进行轮牌的员工">
			                        </th>
			                        <th>提成方式</th>
			                        <th>指定提成</th>
			                        <th>非指定提成</th>
			                        <th>预约奖励<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="通过设置给员工的奖励，可让员工鼓励顾客通过微信进行预约。"></th>
			                        <th rowspan="2"><i class="cursor iconfa-plus" onclick="addEmployeeLevel(this);"></i></th>
			                    </tr>
		                    </thead>
		                    <tbody id="positionTbody">
		                    <c:forEach var="employeeLevel1" items="${deptMahjongList[0].mahjongLevelList[0].employeeLevelList }" varStatus="status1">
			                    <tr>
                                    <td>
                                        <select data-placeholder="选择职位"  class="chzn-select input100" name="empLevelId">
                                              <c:if test="${!empty deptMahjongList && !empty deptMahjongList[0].mahjongLevelList[0].employeeLevelList }">
	                                            <c:forEach var="employeeLevel2" items="${deptMahjongList[0].mahjongLevelList[0].employeeLevelList }" varStatus="status2">
	                                            <c:if test="${employeeLevel1.levelId == employeeLevel2.levelId }">
	                                            	<option selected="selected" value="${employeeLevel2.levelId }">${employeeLevel2.levelName }</option>
	                                            </c:if>
	                                            <c:if test="${employeeLevel1.levelId != employeeLevel2.levelId }">
	                                            	<option value="${employeeLevel2.levelId }">${employeeLevel2.levelName }</option>
	                                            </c:if>
	                                            </c:forEach>
	                                          </c:if>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="assignType" id="" class="chzn-select w70">
			                                <option value="2">固定</option>
			                                <option value="1">比例</option>
			                            </select>
			                         </td>
                                    <td><input type="text" name="assignCash" class="input30" datatype="n" nullmsg="请输入指定提成！" errormsg="指定提成:请输入数字"/><span class="percent-symbol">元</span></td>
                                    <td><input type="text" name="assignCard" class="input30" datatype="n" nullmsg="请输入非指定提成！" errormsg="非指定提成:请输入数字"/><span class="percent-symbol">元</span></td>
                                    <td><input type="text" name="appointmentReward" class="input30" value="0" datatype="n" nullmsg="请输入预约奖励！" errormsg="预约奖励:请输入数字"/><span class="percent-symbol">元</span>
                                    </td>
                                    <td><i class="cursor iconfa-minus" onclick="deleteEmpLevel(this);"></i></td>
                                </tr>
                            </c:forEach>
		                    </tbody>
		                </table>
		                <p></p>
		                </div>
		                <p></p>
		                <div class="clearfix"></div>
		                <div class="more-toolbar" >
			                <div class="table-toolbar">
			                    <label for="">为不同会员设置折扣
			                    <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="有些会员卡对门店各种服务享受统一折扣，则不需要在此再进行单独设置。如果您为某种会员在此设置了特殊折扣,系统则会对该类会员应用此处设置的折扣">
			                    </label>
			                    <label for="">
			                    会员低于
			                    <input type="text" id="memberLastAccount" class="input30" onkeyup="setMemberLastAccount(this)"/><span class="percent-symbol">%</span>
			                    设置固定价格
			                    <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="有些会员卡比该项目的最低折扣还低,再次输入一个最低折扣,将会对所有小于该折扣的会员进行设置">
			                    </label>
			                    
			                </div>
			                <div class="clearfix"></div>
			            </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>会员种类</th>
                                    <th>门店价格</th>
                                    <th>会员价格</th>
                                    <th><span class="fr font-span minus"><i class="iconfa-plus" onclick="addMemberLevel();"></i></span></th>
                                </tr>
                            </thead>
                            <tbody id="discountTbody">
                            </tbody>
                        </table>
		                
		                <p></p>
		                <div class="table-title" style="float: right;">
		                    <a type="reset" class="btn btn_reset" onclick="emptyInput()" >清除</a>
		                    <button type="button" class="btn" id="submitBtn">&nbsp;保&nbsp;&nbsp;存&nbsp;</button>
		                </div>
		            </form>
		        </div>
		        
		    </div>
		</div>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div>

<!-- 新增类别模态框 -->
<div class="modal hide in" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-bumen-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title"  style="">批量添加系列</h4>
            </div>
            <input type="hidden" name="saveCategoryListDeptId">
            <div class="modal-body" style="overflow:auto">
            		<div>
		     			<div class="desc-title">
		                    	为项目添加系列,当前部门:<span id="bumenxinzeng"></span>
		                </div>       
		           	</div>
                    <div>
						<span>系列名称：</span>
                        <input type="text" placeholder="系列名称" name="categoryListName" class="mr10 ml10 input-medium">
                        <span class="fr font-span minus"><i class="iconfa-plus" onclick="addModalInputCategory(this)"></i></span>
                    </div>
                    <div class="modal-btn-group">
                        <a class="btn btn-primary modal-confirm w80" href="javascript:addsavedept()" id="saveCategoryList">保存</a>
                    </div>
                <div class="modal-footer text-left">
                <div class="desc-title">
                    	设置说明
                </div>
                <div class="desc-content">
                    <p>1.现在您可以一次添加多个项目的类别</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>

<!-- 新增项目模态框 -->
<div class="modal hide in" id="add-project-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-bumen-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title"  style="">批量添加项目</h4>
            </div>
            <input type="hidden" name="saveProjectListDeptId">
            <input type="hidden" name="saveProjectListCategoryId">
            <div class="modal-body" style="overflow:auto">
           			<div>
		     			<div class="desc-title">
		                    	 为系列添加项目,当前系列:<span id="crutentCategoryName"></span>
		                </div>       
		           	</div>
                    <div>
						<span>项目名称：</span>
                        <input type="text" placeholder="项目名称" name="projectListName" class="mr10 ml10 input-medium">
                        <span class="fr font-span minus"><i class="iconfa-plus" onclick="addModalInputProject(this)"></i></span>
                    </div>
                    <div class="modal-btn-group">
                        <a class="btn btn-primary modal-confirm w80" href="javascript:void(0)" onclick="saveProjectsLists(this)">保存</a>
                    </div>
                <div class="modal-footer text-left">
                <div class="desc-title">
                    	设置说明
                </div>
                <div class="desc-content">
                    <p>1.现在您可以一次添加多个项目,实现批量</p>
                    <p>2.添加结束,点击项目进行数据的录入即可</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>

<!-- 导入项目模态框 -->
<div class="modal hide in" id="upload-project-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-bumen-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title"  style="">导入项目</h4>
            </div>
            <div class="modal-body" style="overflow:auto">
                    <div>
						<span>项目名称：</span>
                        <input type="file" id="file" class="mr10 ml10 input-medium">
                    </div>
                    <!-- <div>
                    	<progress  id="progressBar" value="0" max="100"></progress>
						<span id="percentage"></span>
                    </div> -->
                    <div class="modal-btn-group">
                        <a class="btn btn-primary modal-confirm w80" href="javascript:void(0)" onclick="uploadSaveProjects(this)">保存</a>
                    </div>
                <div class="modal-footer text-left">
                <div class="desc-title">
                    	设置说明
                </div>
                <div class="desc-content">
                    <p>1.对于拥有多部门的门店或连锁机构，设置部门编码可以方便您管理不同的业务部门，如果您的门店是专业店没有多个部门，请将部门编号设置为1.</p>
                    <p>2.某些部门属于非营业部门，请将部门设置为“不产生业绩”这样系统在生成营业报表的时候，将忽略这些部门.</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>


<div class="modal hide" id="jietu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content jietu ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">截图</h4>
            </div>
            <div class="modal-body nopadding">
              <div class="crop-container">
                <img src="<%=basePath %>images/test.jpg" id="cropbox" />
              </div>

              <div class="jietu-control">
                <input type="file" class="inputfile" accept="image/*" />
                <div class="btn dblock">选择文件</div>
				<div class="btn dblock" id="selectImagesFromPictures" onclick="selectImagesFromPictures(this)">选择图库</div>
                <div class="btn dblock mt10 save">保存</div>
                <div class="btn dblock mt10 zoomin">放大</div>
                <div class="btn dblock mt10 zoomout">缩小</div>
              </div>

            </div>
        </div>
    </div>
</div>

<!-- 选择图片库模态框 -->
<div class="modal hide" id="photo-list-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            <div id="tabs" style="border-bottom:1px solid #fff;overflow: auto;" >
                    <ul>
                    <c:forEach items="${images }" var="codeDtos" varStatus="status">
                    	<li><a href="#tabs-${status.index+1 }">${codeDtos.typeName }</a></li>
                    </c:forEach>
                    </ul>
                    
                    <c:forEach items="${images }" var="codeDtos" varStatus="status">
                    	<div id="tabs-${status.index+1 }">
	                    <div class="imgword-list" style="width:600px;height: 400px;">
				            <c:forEach items="${codeDtos.codeLibraryDtos }" var="pictures">
				            	<div class="photo-item"  style="position: relative;">
				                    <div class="photo-content" >
				                        <img src="<%=qiniu %>${pictures.codeName }" id="${pictures.codeName }">
				                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
				                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
				                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
				                </div>
				            </c:forEach>
				        </div>
	                </div>
                    </c:forEach>	
	        </div>
            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<script id="memberLevelSelect" type="text/html">
<tr>
	<td>
		<select data-placeholder="选择会员种类"  class="chzn-select input100" name="levelId">
			<c:forEach var="memberLevel" items="${memberLevels }" varStatus="status">
				<option value="${memberLevel.levelId }">${memberLevel.levelName }</option>
			</c:forEach>
		</select>
	</td>
    <!--<td><input type="text" class="input70" name="discountProportion" onblur="calculateMenberPrice(this);" datatype="n" nullmsg="请输入折扣比例！" errormsg="折扣比例:请输入数字"/><span class="percent-symbol">%</span></td>-->
	<td>
		<span id="mendianjiage" class="mendianjiage"></span>
	<td>
        <input type="text" class="input70" name="discountAmount" datatype="n" nullmsg="请输入会员价格！" errormsg="会员价格:请输入数字"/></td>
	<!--<td><input type="text" class="input70" name="onlineAppointmentPrice" datatype="n" nullmsg="请输入预约优惠价格！" errormsg="预约优惠价格:请输入数字"/><span class="percent-symbol"></span></td>-->
	<td><span class="fr font-span minus"><i class="iconfa-minus" onclick="deleteDiscount(this);"></i></span></td>
</tr>
</script>
<script id="employeeLevelSelect" type="text/html">
<tr>
    <td>
        <select data-placeholder="选择职位"  class="chzn-select input100" name="empLevelId">
            <c:if test="${!empty deptMahjongList && !empty deptMahjongList[0].mahjongLevelList[0].employeeLevelList }">
	             <c:forEach var="employeeLevel" items="${deptMahjongList[0].mahjongLevelList[0].employeeLevelList }" varStatus="status">
	                 <option value="${employeeLevel.levelId }">${employeeLevel.levelName }</option>
	             </c:forEach>
	        </c:if>
        </select>
    </td>
    <td>
        <select name="assignType" id="" class="chzn-select w70">
			 <option value="2">固定</option>
			 <option value="1">比例</option>
		</select>
	</td>
    <td><input type="text" name="assignCash" class="input30" datatype="n" nullmsg="请输入价格！" errormsg="指定提成:请输入数字"/><span class="percent-symbol">%</span></td>
    <td><input type="text" name="assignCard" class="input30" datatype="n" nullmsg="请输入价格！" errormsg="非指定提成:请输入数字"/><span class="percent-symbol">%</span></td>
    <td><input type="text" name="appointmentReward" value="0" class="input30" datatype="n" nullmsg="请输入价格！" errormsg="预约奖励:请输入数字"/><span class="percent-symbol">%</span></td>
    <td><i class="iconfa-minus" onclick="deleteEmpLevel(this);"></i></td>
</tr>
</script>
<script id="shezhigangweiticheng" type="text/html">
<div class="more-toolbar" >
<div class="table-toolbar">
    <label for="">请为<span id="num">1</span>号步骤<span id="name"></span>设置职位提成</label>
	<input type="hidden" value="1" name="zhiweinum">
</div>
<div class="clearfix"></div>
</div>
<table class="table">
<thead>
    <tr>
        <th>职位名称<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="此处的职位名称为所有可以参与该步骤服务并进行轮牌的员工"></th>
        <th>提成方式</th>
        <th>指定提成</th>
        <th>非指定提成</th>
        <th>预约奖励<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="通过设置给员工的奖励，可让员工鼓励顾客通过微信进行预约。"></th>
        <th rowspan="2"><i class="cursor iconfa-plus" onclick="addEmployeeLevel(this);"></i></th>
    </tr>
</thead>
<tbody id="positionTbody">
    <tr>
        <td>
            <select data-placeholder="选择职位"  class="chzn-select input100" name="empLevelId">
                  <c:if test="${!empty deptMahjongList && !empty deptMahjongList[0].mahjongLevelList[0].employeeLevelList }">
                    <c:forEach var="employeeLevel" items="${deptMahjongList[0].mahjongLevelList[0].employeeLevelList }" varStatus="status">
                        <option value="${employeeLevel.levelId }">${employeeLevel.levelName }</option>
                    </c:forEach>
                  </c:if>
            </select>
        </td>
        <td>
            <select name="assignType" id="" class="chzn-select w70">
                <option value="2">固定</option>
                <option value="1">比例</option>
            </select>
         </td>
        <td><input type="text" name="assignCash" class="input30" datatype="n" nullmsg="请输入指定提成！" errormsg="指定提成:请输入数字"/><span class="percent-symbol">元</span></td>
        <td><input type="text" name="assignCard" class="input30" datatype="n" nullmsg="请输入非指定提成！" errormsg="非指定提成:请输入数字"/><span class="percent-symbol">元</span></td>
        <td><input type="text" name="appointmentReward" class="input30" datatype="n" nullmsg="请输入预约奖励！" errormsg="预约奖励:请输入数字"/><span class="percent-symbol">元</span>
		</td>
        <td><i class="cursor iconfa-minus" onclick="deleteEmpLevel(this);"></i></td>
    </tr>
</tbody>
</table>
<p></p>
</script>
<script>
    var memberLevelList = eval("(" + '<%=request.getAttribute("memberLevelList")%>' + ")");
    var deptProjectList = eval(<%=request.getAttribute("js_deptProjectList")%>);
    var deptMahjongList = eval("(" + '<%=request.getAttribute("mahjongList")%>' + ")");
	jQuery('.project-sublist.category').delegate('.project-sublist-title', 'click', function() {
		stopBubble(this);
		for (var i = 0; i < jQuery(this).find("i").length; i++) {
			if(jQuery(this).find("i").eq(i).attr("class") == "afont iconfont icon-iconfontxialaeps"){
				jQuery(this).parent().children(".project-sublist-content").hide();
				jQuery(this).find("i").eq(i).attr("class","afont iconfont icon-iconfontxialaeps active");
				return;
			}
			if(jQuery(this).find("i").eq(i).attr("class") == "afont iconfont icon-iconfontxialaeps active"){
				jQuery(this).parent().children(".project-sublist-content").show();
				jQuery(this).find("i").eq(i).attr("class","afont iconfont icon-iconfontxialaeps");
				return;
			}
		}
	})
</script>
<script src="<%=basePath %>js/commodity/projectSetting.js"></script>
</body>
<script type="text/javascript">
var  objDoc, $image, cropBoxData, canvasData;
var options = {
			aspectRatio: 1,
	      minCropBoxWidth: 580,
	      minCropBoxHeight: 580,
	      width:580,
	      height:580,
	      responsive:false,
	      cropBoxMovable: false,
	      cropBoxResizable: false,
	      viewMode: 1,
	      dragMode: 'move'
		};

var $image = jQuery('.crop-container > img');
$image.cropper(options);
function cavsen(obj){
  objDoc = jQuery(obj);	
  var otherUrl = jQuery(obj).children("img").attr("src");
  jQuery("#cropbox").attr("src", otherUrl);
  $image.cropper('reset').cropper('replace', otherUrl);
  jQuery(".cropper-view-box img").attr("src", otherUrl);
  jQuery("#jietu").modal(); 
  
  /*启用截图*/
  var $inputImage = jQuery('.inputfile');
  var URL = window.URL || window.webkitURL;
  var blobURL;

  if (URL) {
    $inputImage.change(function () {
      var files = this.files;
      var file;

      if (!$image.data('cropper')) {
        return;
      }

      if (files && files.length) {
        file = files[0];

        if (/^image\/\w+$/.test(file.type)) {
            blobURL = URL.createObjectURL(file);
            $image.one('built.cropper', function () {
              // Revoke when load complete
              URL.revokeObjectURL(blobURL);
            }).cropper('reset').cropper('replace', blobURL);
            $inputImage.val('');
          } else {
            window.alert('请选择一张图片');
          }
      }
    });
  } else {
    $inputImage.prop('disabled', true).parent().addClass('disabled');
  }
  var objImage = jQuery(obj);
  jQuery(".btn.save").one("click", function () {
	  var result = $image.cropper('getCroppedCanvas', options);
	  var base64Str = result.toDataURL("image/png");
	  var key = "zefun/project/" + new Date().getTime();
	  objImage.find("img[name='headImg']").attr("src", base64Str);
	  objImage.find("input[name='projectImage']").val(key);
	  objImage.find("img[name='affiliatedHeadImg']").attr("src", base64Str);
	  objImage.find("input[name='affiliatedImage']").val(key);
	  var data = "stringBase64=" + encodeURIComponent(base64Str) + "&key=" + key;
	  jQuery("#jietu").modal('hide');
	  jQuery.ajax({
			type: "POST",
			url: baseUrl+"qiniu/base64",
		       data: data,
		       contentType: "application/x-www-form-urlencoded",
		       dataType: "json",
		       async:true,  
		       beforeSend:function(){
		       	console.log("beforeSend upload image");
		       },
		       error:function (){
		       	console.log("upload image error");
		       },
		       complete :function (){
		       	console.log("complete upload image");
		       },
		       success: function(data) {
		       	var imageUrl = data.msg.imageUrl;
		       	var key = data.msg.key;
		       	objImage.find("img[name='headImg']").attr("src",imageUrl);
		       	objImage.find("input[name='projectImage']").val(key); 
		       	
		       	objImage.find("img[name='affiliatedHeadImg']").attr("src",imageUrl);
		       	objImage.find("input[name='affiliatedImage']").val(key);
		       }
     });
});
}

jQuery('#jietu').on('hidden.bs.modal', function () {
      cropBoxData = $image.cropper('getCropBoxData');
      canvasData = $image.cropper('getCanvasData');
      $image.cropper('clear').cropper('reset');
      jQuery(".btn.save").unbind("click");
});


//选择图文库
function selectImagesFromPictures(obj){
	stopBubble(obj);
	jQuery(".btn.save").unbind("click");
	jQuery("#jietu").modal('hide');
	jQuery("#photo-list-modal").modal();
	jQuery(".photo-item").on("click",function(){
	    jQuery(".photo-item").children(".appmsg_mask").hide();
	    jQuery(".photo-item").children(".icon_card_selected").hide();
	    var picItem = jQuery(this);
	    picItem.children(".appmsg_mask").show();
	    picItem.children(".icon_card_selected").show();
	    var src = picItem.find("img").eq(0).attr("src");
	    var key = picItem.find("img").eq(0).attr("id");
	    jQuery("#confirm-menu-url").one("click",function(){
	    	jQuery(objDoc).children("img").attr("src",src); 
	    	jQuery(objDoc).children("input[name='projectImage']").val(key);
	    	
	    	jQuery(objDoc).children("img").attr("src",src);
	    	jQuery(objDoc).children("input[name='affiliatedImage']").val(key);
	    });
	});
}
</script>
</html>