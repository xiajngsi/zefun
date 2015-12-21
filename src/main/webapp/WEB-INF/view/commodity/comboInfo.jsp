<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<%
   String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
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
		        <div class="project-list">
		            <div class="project-list-head">
		                <input type="search" placeholder="搜索" class="search-input"/>
		                <button type="button" class="btn search-button" id="search-member" onclick="searchComboInfo(this);">搜索</button>
		            </div>
		            <div class="project-list-ul">
		            	<c:forEach var="deptInfoDto" items="${deptInfoCmboInfoList }" varStatus="status">
		            		<div class="project-category">
			                    <div class="project-name" id="deptId${deptInfoDto.deptId}">
			                        <i class="afont iconfont icon-iconfontxialaeps"></i>
			                       	 ${deptInfoDto.deptName }
			                       	 <i class="iconfa-plus project-icon fr" onclick="addComboList(${deptInfoDto.deptId},this);"></i>
<!-- 			                        <i class="iconfa-plus-sign project-icon fr" onclick="addCombo(this);"></i> -->
			                    </div>
			                    <ul class="project-sublist">
			                    	<c:forEach var="comboInfo" items="${deptInfoDto.comboInfoList }" varStatus="status">
			                    	<li class="project-sublist-content" id="comboId${comboInfo.comboId }" onclick="queryComboInfoById(${comboInfo.comboId },this);">${comboInfo.comboName }
				                            <span class="fr">
				                                <i class="iconfa-trash project-icon" onclick="deleteComboInfo(${comboInfo.comboId },this);"></i>
				                            </span>
					                </li>
			                    	</c:forEach>
			                    </ul>
			                </div>
			                <div class="gap"></div>
		            	</c:forEach>
		            </div>
		        </div>
		        <div class="project-setting">
		       		<h4 class="widgettitle">
		              <span class="dingdanzhuantai">正在新增套餐</span>
		              <span class="video" title="新增新增套餐" style="float: right; font-weight: 400;color: #333;cursor: pointer">
		              <i class="iconfa-plus" onclick="clearInput()"></i></span>
		            </h4>
		            <form id="comboInfoForm">
		            	<input type="hidden" id="isUpdate" value="0"/>
		                <div class="img-word">
		                	<div class="x-edit-left">
		                        <div class="file-box" id="comboHeadImg" onclick="cavsen(this)">
		                        	<img alt="" name="headImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
		                        	<input type="hidden" name="comboImage" value="zefun/images/pic_none.gif"/>
		                        </div>
		                        <div>点击上传图片</div>
		                        <div class="img-chicun">图片尺寸600x600像素</div>
		                    </div>
		                    <div class="x-edit-right">
		                        <div class="two-part">
			                    	<label for="" class="font-bold">套餐所属部门</label>
			                        <select data-placeholder="选择项目类别"  class="chzn-select input80" name="deptId" id="deptIdSel" onchange="getShiftMah();" datatype="*" nullmsg="请选择项目所属部门！" errormsg="请选择项目所属部门！">
			                        	<c:forEach var="deptInfo" items="${deptInfoList }" varStatus="status">
			                        		<option value="${deptInfo.deptId }">${deptInfo.deptName }</option>
			                        	</c:forEach>
			                        </select>
		                        </div>
		                        <div class="two-part">
			                    	<label for="" class="font-bold">套餐名称</label>
			                        <input type="text" name="comboName" id="comboName" datatype="*"  nullmsg="请填写套餐名称！"/>
		                        	<input type="hidden" name="comboId" id="comboId"/>
		                        </div>
								<div class="clearfix"></div>		                        
		                        <label for="" class="font-bold mt5">套餐描述</label>
		                        <textarea name="comboDesc" id="comboDesc" cols="100" rows="5" datatype="*" errormsg="请填写套餐描述！" nullmsg="请填写套餐描述！"></textarea>
		                    </div>
		                </div>
		                <div class="clearfix"></div>
		                <div class="widgetcontent">
			                <div class="more-toolbar">
			                	<div class="table-title">设置套餐活动期限</div>
			                </div>
			                <table class="table ">
			                    <thead>
				                    <tr>
				                        <th>是否有时间限制<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="月卡,季卡,年卡需要设置时间期限"></th>
				                        <th>有效期?</th>
				                        <th>是否进行身份认证
				                        <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="仅限购买该套餐的顾客本人使用时,再次需要选择'是',购买完成后,要求客户当场自行拍照上传到会员资料中,下次顾客消费套餐服务前,员工可核对是否是顾客本人">
				                        </th>
				                    </tr>
			                    </thead>
			                    <tbody>
				                    <tr>
				                        <td>
				                        <input type="checkbox" class="standard" id="standard" value="1" checked="checked" autocomplete="on">
				                        <input type="hidden" name="standard" value="1">
				                        </td>
				                        <td><input type="text" class="input70" name="validDate" id="validDate" datatype="n" errormsg="有效期：请输入大于1的整数！" nullmsg="请填写有效期！"/><span class="percent-symbol">天</span></td>
				                        <td>
				                        <input type="checkbox" class="isAttestation" id="isAttestation" value="1" checked="checked" autocomplete="on">
				                        <input type="hidden" name="isAttestation" value="1">
				                        </td>
				                    </tr>
			                    </tbody>
			                </table>
		                </div>
		                <div class="widgetcontent">
		                <div class="more-toolbar">
		               		 <div class="table-title">为套餐选择项目</div>
		                </div>
			                <table class="table  outtable">
			                    <thead>
				                    <tr>
				                        <th>项目名称<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="此处的项目必须是您在项目设置中已经设置过的项目"></th>
				                        <th>门店价格</th>
				                        <th>套餐内项目数量</th>
				                        <th>单次服务业绩计算
				                        <img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="当员工的业绩/提成为比例时，比例乘以此处设置的值就是自己的业绩与提成">
				                        </th>
				                        <!-- <th>提成比例</th> -->
				                        <th><i class="cursor iconfa-plus" onclick="addProjectList();"></i></th>
				                    </tr>
			                    </thead>
			                    <tbody id="projectSetTbody">
				                    <tr>
				                        <td>
				                            <select data-placeholder="选择项目"  class="chzn-select input-medium" name="projectIds" onchange="changeProjectSel(this);">
				                            	<option value="-1">请选择项目</option>
				                            	<c:forEach var="projectInfoDto" items="${projectInfoDtoList }" varStatus="status">
				                            		<option value="${projectInfoDto.projectId }" price="${projectInfoDto.projectPrice }">
				                            			${projectInfoDto.projectName }
				                            		</option>
				                            	</c:forEach>
				                            </select>
				                        </td>
				                       <!--  <td>
				                        	<i class="cursor iconfont icon-shouqi" onclick="showProjectSet(this);"></i>
				                        </td> -->
				                        <td>
				                        	<span name="projectPriceSpan">0.00</span>
				                        </td>
				                        <td>
				                        	<input type="hidden" name="projectPrice" value="${projectInfoDtoList[0].projectPrice }"/>
				                        	<input type="hidden" name="projectNames"/>
				                        	<input type="number" class="input70" name="projectCounts" onblur="countProjectNum();" datatype="n" errormsg="项目数量：请输入大于1的整数！" nullmsg="请填写项目数量！"/>
				                        </td>
				                        <td>
				                        	<input type="text" class="input70" name="comboPerformanceCal" datatype="n" errormsg="单次服务业绩计算：请输入数字！" nullmsg="请输入单次服务业绩计算！"/><span class="percent-symbol">元</span>
				                        </td>
				                        <!-- <td>
				                        	<input type="text" class="input70" name="royaltyRate" datatype="n" errormsg="提成比例：请输入数字！" nullmsg="请输入提成比例！"/><span class="percent-symbol">%</span>
				                        </td> -->
				                        <td><i class="cursor iconfa-minus" onclick="deleteProjectList(this);"></i></td>
				                    </tr>
				                    <tr class="innertable hide ">
										<td colspan="8" >
											<table class="table  tr-innertable">
												<thead>
													<tr>
														<td>职位名称</td>
														<td>提成方式</td>
														<td>指定提成</td>
														<td>非指定提成</td>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</td>
									</tr>
			                    </tbody>
			                </table>
		                </div>
		                
		                <div class="widgetcontent">
			                <div class="more-toolbar">
			               		 <div class="table-title">为套餐选择商品</div>
			                </div>
			                <table class="table ">
			                    <thead>
				                    <tr>
				                        <th>商品名称</th>
				                        <th>商品原价</th>
				                        <th>套餐内商品数量</th>
				                        <!-- <th>商品内业绩计算</th>
				                        <th>提成类型</th>
										<th>提成金额</th> -->
				                        <th><i class="cursor iconfa-plus" onclick="addGoodsList(this);"></i></th>
				                    </tr>
			                    </thead>
			                    <input type="hidden" name="goodsName">
			                    <tbody id="goodsSetTbody">
				                    <tr>
				                        <td>
				                            <select data-placeholder="选择商品"  class="chzn-select input-medium" name="goodsId" onchange="changeGoodsSel(this);">
				                            	<option value="-1">请选择商品</option>
				                            	<c:forEach var="goodsinfo" items="${goodsinfos }" varStatus="status">
				                            		<option value="${goodsinfo.goodsId }" price="${goodsinfo.goodsPrice }">
				                            			${goodsinfo.goodsName }
				                            		</option>
				                            	</c:forEach>
				                            </select>
				                        </td>
				                        <td>
				                        	<span name="goodsPriceSpan">0.00</span>
				                        </td>
				                        <td>
				                        	<input type="hidden" name="goodsNames"/>
				                        	<input type="hidden" name="goodsPrice" value="${goodsinfos[0].goodsPrice }"/>
				                        	<input type="number" class="input70" name="goodsCounts" onblur="countGoodNum();" datatype="n" errormsg="商品数量：请输入大于1的整数！" nullmsg="请填写商品数量！"/>
				                        </td>
				                        <!-- <td>
				                        	<input type="text" class="input70" name="comboGoodsPerformanceCal" datatype="d2-2" errormsg="单次服务业绩计算：请输入数字，可以保留2位小数！" nullmsg="请填写单次服务业绩计算！"/><span class="percent-symbol">元</span>
				                        </td>
				                        <td>
				                        	<input type="checkbox" name="goodsCommissionTypeDate" value="2" class="lcs_check_type" checked="checked" autocomplete="on">
				                        </td>
				                        <td>
				                        	<input type="text" datatype="n" errormsg="指定提成：请输入大于1的整数！" nullmsg="请填写指定提成！" name="commissionAmount" value="" class="input30"><span class="percent-symbol">元</span>
				                        </td> -->
				                        <td><i class="cursor iconfa-minus" onclick="deleteGoodsList(this);"></i></td>
				                    </tr>
			                    </tbody>
			                </table>
		                </div>
		                
		                <div class="widgetcontent">
			                <div class="more-toolbar">
			                	<div class="table-title">设置套餐价格及销售提成</div>
			                </div>
			                <table class="table ">
		                    <thead>
			                    <tr>
			                        <th>项目原总价</th>
			                        <th>套餐销售价</th>
			                        <th>提成方式</th>
			                        <th>现金提成</th>
			                        <th>卡金提成</th>
			                        <th>员工销售业绩计算</th>
			                    </tr>
		                    </thead>
		                    <tbody>
			                    <tr>
			                        <td><input type="hidden" class="input70" name="projectAmount" id="projectAmount" readonly="readonly"/><span></span></td>
			                        <td><input type="text" class="input70" name="comboSalePrice" id="comboSalePrice" datatype="n" nullmsg="请输入套餐销售价！" errormsg="套餐销售价:请输入大于1以上的数字！"/><span class="percent-symbol">元</span></td>
			                        <td><input type="checkbox" class="lcs_check_type" name="commissionType" id="commissionType" value="1" checked="checked" autocomplete="on" /></td>
			                        <td><input type="text" class="input70" name="cashCommission" id="cashCommission" datatype="n" nullmsg="请输入现金提成！" errormsg="现金提成:请输入大于1以上的数字！"/><span class="percent-symbol">%</span></td>
			                        <td><input type="text" class="input70" name="cardCommission" id="cardCommission" datatype="n" nullmsg="请输入卡金提成！" errormsg="卡金提成:请输入大于1以上的数字！"/><span class="percent-symbol">%</span></td>
			                        <td><input type="text" class="input70" name="comboPerformance" id="comboPerformance" datatype="n" nullmsg="请输入员工销售业绩计算！" errormsg="员工销售业绩计算:请输入大于1以上的数字！"/><span class="percent-symbol">元</span></td>
			                    </tr>
		                    </tbody>
		                </table>
		                </div>
		                <p></p>
		                <div class="table-title" style="float: right;">
		                    <button type="button" class="btn btn_reset" onclick="clearInput()">清除</button>
		                    <button type="button" class="btn" id="submitBtn" >&nbsp;保&nbsp;&nbsp;存&nbsp;</button>
		                </div>
		            </form>
		        </div>
		    </div>
		</div>
      	
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->
<!-- 新增类别模态框 -->
<div class="modal hide in" id="add-combo-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-bumen-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title"  style="">批量添加套餐</h4>
            </div>
            <input type="hidden" name="saveComboListDeptId">
            <div class="modal-body" style="overflow:auto">
            		<div>
		     			<div class="desc-title">
		                    	为项目添加套餐,当前部门:<span id="bumenxinzeng"></span>
		                </div>       
		           	</div>
                    <div>
						<span>套餐名称：</span>
                        <input type="text" placeholder="套餐名称" name="comboListName" class="mr10 ml10 input-medium">
                        <span class="fr font-span minus"><i class="iconfa-plus" onclick="addModalInputCombo(this)"></i></span>
                    </div>
                    <div class="modal-btn-group">
                        <a class="btn btn-primary modal-confirm w80" href="javascript:saveComboList()" id="saveComboList">保存</a>
                    </div>
                <div class="modal-footer text-left">
                <div class="desc-title">
                    	设置说明
                </div>
                <div class="desc-content">
                   	<p>1.现在您可以一次添加多个套餐,实现批量</p>
                    <p>2.添加结束,点击套餐进行数据的录入即可</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>
<div class="modal hide" id="jietu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content jietu ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">截图</h4>
            </div>
            <div class="modal-body nopadding">
              <div class="crop-container">
                <img src="<%=basePath %>images/pic_none.gif" id="cropbox" />
              </div>

              <div class="jietu-control">
                <input type="file" class="inputfile" accept="image/*" />
                <div class="btn dblock">选择文件</div>
				<div class="btn dblock" onclick="selectImagesFromPictures(this)">选择图库</div>
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
	                    <div class="imgword-list" style="width: 600px;height: 400px;">
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
<script id="projectSelect" type="text/html">
<tr>
	<td>
		<select data-placeholder="选择项目"  class="chzn-select input-medium" name="projectIds" onchange="changeProjectSel(this);">
			<option value="-1">请选择项目</option>
			<c:forEach var="projectInfoDto" items="${projectInfoDtoList }" varStatus="status">
				<option value="${projectInfoDto.projectId }" price="${projectInfoDto.projectPrice }">
					${projectInfoDto.projectName }
				</option>
			</c:forEach>
		</select>
		
	</td>
	<td>
		<span name="projectPriceSpan"> 0.00 元 </span>
	</td>
	<td>
		<input type="hidden" name="projectPrice" value="${projectInfoDtoList[0].projectPrice }"/>
		<input type="hidden" name="projectNames"/>
		<input type="number" class="input70" name="projectCounts" onblur="countProjectNum();" datatype="n" errormsg="项目数量：请输入大于1的整数！" nullmsg="请填写项目数量！"/>
	</td>
	<td>
		<input type="text" class="input70" name="comboPerformanceCal" datatype="d2-2" errormsg="单次服务业绩计算：请数字，可以保留2位小数！" nullmsg="请填写单次服务业绩计算！"/><span class="percent-symbol">元</span>
	</td>
	<!--<td>
		<input type="text" class="input70" name="royaltyRate" datatype="d2-2" errormsg="提成比例：请数字，可以保留2位小数！" nullmsg="请填写提成比例！"/><span class="percent-symbol">%</span>
	</td>-->
	<td><i class="cursor iconfa-minus" onclick="deleteProjectList(this);"></i></td>
</tr>
<tr class="innertable hide">
	<td colspan="8">
		<table class="table">
			<thead>
			<tr>
				<td>职位名称</td>
				<td>提成方式</td>
				<td>指定提成</td>
				<td>非指定提成</td>
			</tr>
			</thead>
			<tbody></tbody>
		</table>
	</td>
</tr>
</script>
<!-- 商品列表 -->
<script id="goodsSelect" type="text/html">
<tr>
			                        <td>
			                            <select data-placeholder="选择商品"  class="chzn-select input-medium" name="goodsId" onchange="changeGoodsSel(this);">
			                            	<option value="-1">请选择商品</option>
			                            	<c:forEach var="goodsinfo" items="${goodsinfos }" varStatus="status">
			                            		<option value="${goodsinfo.goodsId }" price="${goodsinfo.goodsPrice }">
			                            			${goodsinfo.goodsName }
			                            		</option>
			                            	</c:forEach>
			                            </select>
			                        </td>
			                        <td>
			                        	<span name="goodsPriceSpan">0.00</span>
			                        </td>
			                        <td>
										<input type="hidden" name="goodsNames"/>
			                        	<input type="hidden" name="goodsPrice" value="${goodsinfos[0].goodsPrice }"/>
			                        	<input type="number" class="input70" onblur="countGoodNum();" name="goodsCounts" onblur="countGoodNum();" datatype="n" errormsg="商品数量：请输入大于1的整数！" nullmsg="请填写商品数量！"/>
			                        </td>
			                        <td><i class="cursor iconfa-minus" onclick="deleteGoodsList(this);"></i></td>
			                    </tr>
</script>
<script id="projectCommission" type="text/html">
</script>

<script>
	var projectInfoList = eval(<%=request.getAttribute("projectInfoList")%>);
	var deptInfoListDate = eval(<%=request.getAttribute("js_deptInfoList")%>);
	var goodsinfos = eval(<%=request.getAttribute("goodsinfos_js")%>);
</script>
<script src="<%=basePath %>js/commodity/comboInfo.js"></script>
<script type="text/javascript">
var  objDoc, $image, cropBoxData, canvasData;
var options = {
		  aspectRatio: 1 / 1,
	      minContainerWidth: 1000,
	      minContainerHeight: 580,
	      minCanvasHeight:580,
	      minCropBoxWidth: 580,
	      minCropBoxHeight: 580,
	      width:580,
	      height:580,
	      movable: true,
	      responsive:false,
	      dragCrop: false,
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
	  objImage.find("input[name='goodsImage']").val(key);
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
	        	/* objImage.find("img[name='headImg']").attr("src",imageUrl); */
	        	objImage.find("input[name='comboImage']").val(key); 
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
    	jQuery(objDoc).children("input[name='comboImage']").val(key);
    });
});
}
</script>
</body>
</html>