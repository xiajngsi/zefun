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
		                <button type="button" class="btn search-button" id="search-member" onclick="searchGodsInfo(this);">搜索</button>
		            </div>
		            <div class="project-list-ul">
		            	<c:forEach var="deptGoods" items="${deptGoodsBaseDto }" varStatus="status">
		            		<div class="project-category">
			                    <div class="project-name" id="deptId${deptGoods.deptId}">
			                        <i class="afont iconfont icon-iconfontxialaeps"></i>
			                       	 	${deptGoods.deptName }
			                        <i class="iconfa-plus-sign project-icon fr" onclick="addBrand(${deptGoods.deptId},this);"></i>
			                    </div>
			                    <ul class="project-sublist dept">
			                    	<li class="project-sublist-title" id="addBrandLi" style="display:none;">
				                    	<input type="text" name="categoryName" id="categoryName" style="width: 73%;"/>
				                    	<input type="hidden" value="${deptGoods.deptId}" name="categoryId">
		            					<i class="iconfa-plus project-icon" onclick="saveBrand(${deptGoods.deptId },this);"></i><i class="iconfa-trash project-icon" onclick="deleteBrand(this);"></i>
		            				</li>
		            				<c:forEach var="goodsCategory" items="${deptGoods.goodsCategoryBaseDto }" varStatus="status">
		            				  <ul class="project-sublist category" style="display: block;">
		            					<li class="project-sublist-title" id="categoryId${goodsCategory.categoryId}">
											<div>
											    <i class="afont iconfont icon-iconfontxialaeps"></i>
												<span class="categoryNameSpan">${goodsCategory.categoryName }</span>
					                            <span class="fr">
					                           		<i class="iconfa-plus project-icon" onclick="addGoodsInfosList(${deptGoods.deptId},${goodsCategory.categoryId},this)"></i>
					                                <i class="iconfa-pencil project-icon" onclick="showBrand(this,'${goodsCategory.categoryName }',${goodsCategory.categoryId});"></i>
					                                <i class="iconfa-trash project-icon" onclick="deleteBrandById(${goodsCategory.categoryId},${deptGoods.deptId},this);"></i>
					                            </span>
				                            </div>
				                            <div style="display:none">
				                            	<input type="text" id="inputCategoryName" value="${goodsCategory.categoryName }" style="width: 70%;"/>
				                            	<i class="iconfa-plus-sign project-icon" onclick="editBrand(${goodsCategory.categoryId},${deptGoods.deptId},this);"></i>
				                            	<i class="iconfa-trash project-icon" onclick="cancelEditBrand(this);"></i>
				                            </div>
				                        </li>
				                        <c:forEach var="goodsInfo" items="${goodsCategory.goodsBaseDtos }" varStatus="status">
					                        <li class="project-sublist-content" id="goodsId${goodsInfo.goodsId}" onclick="queryGoodsInfoById(${goodsInfo.goodsId},this);">${goodsInfo.goodsName }
					                            <span class="fr">
					                            	<i class="icon-fuzhi iconfont" onclick="iconCopyGoodsInfo(${goodsInfo.goodsId},${deptGoods.deptId},this);"></i>
					                                <i class="iconfa-trash project-icon" onclick="deleteGoodsInfo(${goodsInfo.goodsId},${deptGoods.deptId},this);"></i>
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
		              <span class="dingdanzhuantai">正在新增商品</span>
		              <span class="video" title="新增商品" style="float: right; font-weight: 400;color: #333;cursor: pointer"><i class="iconfa-plus" onclick="emptyInput()"></i></span>
		            </h4>
		              <form id="goodsInfoForm">
		              	  <input type="hidden" id="isUpdate" value="0"/>
		                  <div class="img-word">
		                  	  <div class="x-edit-left" >
		                        <div class="file-box" id="goodsInfoHeadImg" onclick="cavsen(this)">
		                        	<img alt="" name="headImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
		                        	<input type="hidden" name="goodsImage" value="zefun/images/pic_none.gif"/>
		                        </div>
		                        <div id="pickfiles">点击上传图片</div>
		                        <!-- <button type="button" class="btn select-img-btn">选择图库</button> -->
		                        <div class="img-chicun">图片尺寸600x600像素</div>
		                      </div>
		                      <div class="x-edit-right">
		                     	<div class="two-part">
			                    	<label for="" class="font-bold">所属部门</label>
			                        <select data-placeholder="选择所属部门"  class="chzn-select input80" name="deptId" id="deptIdSel" onchange="selectCategory(this)">
			                        	<c:forEach var="deptInfo" items="${deptGoodsBaseDto }" varStatus="status">
			                        		<option value="${deptInfo.deptId }">${deptInfo.deptName }</option>
			                        	</c:forEach>
			                        </select>
		                        </div>
		                        <div class="two-part">
			                        <label for="" class="font-bold">商品类别</label>
			                        <input type="hidden" value="" id="defaultCategoryId">
			                        <select data-placeholder="选择商品类别" onchange="changeCopyGoods(this)"  class="chzn-select input80" name="categoryId" id="categoryNameSel" datatype="*"  nullmsg="请选择商品类别！">
			                        	<option value="0">选择一个商品类别</option>
			                        	<c:forEach var="goodsCategory" items="${deptGoodsBaseDto[0].goodsCategoryBaseDto }" varStatus="status">
			                        		<option value="${goodsCategory.categoryId }">${goodsCategory.categoryName }</option>
			                        	</c:forEach>
			                        </select>
		                        </div>
		                        <div class="two-part">
			                        <label for="" class="font-bold">商品品牌</label>
			                        <input name="brandId" type="text" onclick="selectBrand()">
		                        </div> 
		                      	<div class="two-part">
			                        <label for="" class="font-bold">商品名称</label>
			                        <input type="text" name="goodsName" id="goodsName" datatype="s1-10" errormsg="商品名称不能超过十个字"  nullmsg="请填写商品名称！"/>
			                        <input type="hidden" name="goodsId" id="goodsId"/>
			                    </div>
			                    <div class="clearfix"></div>
			                    <label for="" class="font-bold mt5">商品描述</label>
			                    <textarea name="goodsDesc" id="goodsDesc" cols="100" rows="3" datatype="*" errormsg="商品描述至少2个字符,最多18个字符！" nullmsg="请填写商品描述！"></textarea>
		                      </div>
		                  </div>
		                  <div class="clearfix"></div>
		                  
	                      <table class="table">
	                          <thead>
	                          <tr>
	                              <th>附属图片(点击图片可替换)<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="附属图片:为增加您服务项目的吸引力,顾客将看到此处增加的图片将和以上主图片滚动播放">
	                              </th>
	                          </tr>
	                          </thead>
	                          <tbody>
		                          <tr>
		                              <td>
		                              	  <div id="containerAffiliated" class="file-box fl ml30">
					                        <div id="affiliatedHeadImg" onclick="cavsen(this)">
					                        	<img alt="" name="affiliatedHeadImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
					                        	<input type="hidden" name="affiliatedImage" value="zefun/images/pic_none.gif"/>
					                        </div>
						                  </div>
					                      
		                                  <div id="containerAffiliated1" class="file-box fl ml30">
					                        <div id="affiliatedHeadImg1" onclick="cavsen(this)">
					                        	<img alt="" name="affiliatedHeadImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
					                        	<input type="hidden" name="affiliatedImage" value="zefun/images/pic_none.gif"/>
					                        </div>
						                  </div>
		
		                                  <div id="containerAffiliated2" class="file-box fl ml30">
					                        <div id="affiliatedHeadImg2" onclick="cavsen(this)">
					                        	<img alt="" name="affiliatedHeadImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
					                        	<input type="hidden" name="affiliatedImage" value="zefun/images/pic_none.gif"/>
					                        </div>
						                  </div>
		
		                                  <div id="containerAffiliated3" class="file-box fl ml30">
					                        <div id="affiliatedHeadImg3" onclick="cavsen(this)">
					                        	<img alt="" name="affiliatedHeadImg" src="<%=basePath %>images/pic_none.gif" alt="" class="file-btn">
					                        	<input type="hidden" name="affiliatedImage" value="zefun/images/pic_none.gif"/>
					                        </div>
						                  </div>
						                  
		                              </td>
		                          </tr>
	                          </tbody>
	                      </table>
		                <p></p>
                        <div class="clearfix"></div>
                        <div class="more-toolbar" >
                            <div class="table-toolbar">
                                <label for="">门店价格设置</label>
                            </div>
                            <div class="clearfix"></div>
                        </div>
		                  <table class="table">
		                      <thead>
			                      <tr>
			                          <th>门店价格</th>
			                          <th>成本价格</th>
			                          <th>员工销售业绩计算</th>
			                          <th>非卖品?</th>
			                          <th>是否接受礼金</th>
			                          <th>最大礼金抵扣</th>
			                      </tr>
		                      </thead>
		                      <tbody>
			                      <tr>
			                          <td><input type="text" class="input70" name="goodsPrice" id="goodsPrice" datatype="d2-2" errormsg="门店价格：请输入整数,可以保留两位小数" nullmsg="请填写门店价格！" onblur="changeYuYuejiage(this)"/><span class="percent-symbol">元</span></td>
			                          <td><input type="text" class="input70" name="costPrice" id="costPrice" datatype="d2-2" errormsg="成本价格：请输入整数,可以保留两位小数" nullmsg="请填写进成本价格！"/><span class="percent-symbol">元</span></td>
			                          <td><input type="text" class="input70" name="onlineShoppingPrice" id="onlineShoppingPrice" datatype="n" errormsg="业绩计算：请输入整数！" nullmsg="请填写业绩计算！"/><span class="percent-symbol">元</span></td>
			                          <td><input type="checkbox" name="isSellProduct" id="isSellProduct" value="1" class="lcs_check" checked="checked" autocomplete="on" /></td>
			                          <td><input type="checkbox" name="isCashDeduction" id="isCashDeduction" value="1" class="lcs_check" checked="checked" autocomplete="on" /></td>
			                          <td><input type="text" class="input70" name="highestDiscount" id="highestDiscount"  datatype="d2-2" errormsg="最大礼金抵扣：请输入数字,或者保存两位小数！" nullmsg="最大礼金抵扣！"/><span class="percent-symbol">元</span></td>
			                      </tr>
		                      </tbody>
		                  </table>
		                  
		                  <p></p>
	                      <div class="clearfix"></div>
	                        <div class="more-toolbar" >
	                            <div class="table-toolbar">
	                                <label for="">销售提成与库存</label>
	                            </div>
	                            <div class="clearfix"></div>
	                      </div>
		                  <table class="table">
		                      <thead>
		                      <tr>
		                          <th>提成类型</th>
		                          <th>现金提成</th>
		                          <th>卡金提成</th>
		                          <th>当前库存</th>
                                  <th>警告库存</th>
		                          <!-- <th>微信显示</th>
		                          <th>禁用</th> -->
		                      </tr>
		                      </thead>
		                      <tbody>
		                      <tr>
		                          <td><input type="checkbox" name="commissionType" id="commissionType" value="1" class="commtype" checked="checked" autocomplete="on" /></td>
		                          <td><input type="text" name="commissionAmount" id="commissionAmount" class="input70" datatype="n" errormsg="现金提成：请输入数字！" nullmsg="请填写现金提成！"/><span class="percent-symbol">%</span></td>
		                          <td><input type="text" name="cardAmount" id="cardAmount" class="input70" datatype="n" errormsg="卡金提成：请输入数字！" nullmsg="请填写卡金提成！"/><span class="percent-symbol">%</span></td>
		                          <td><input type="number" class="input70" name="goodsStock" id="goodsStock" datatype="n" errormsg="当前库存：请输入整数！" nullmsg="请填写当前库存！"/></td>
                                  <td><input type="number" class="input70" name="warnStock" id="warnStock" datatype="n" errormsg="告警库存：请输入整数！" nullmsg="请填写告警库存！"/></td>
		                          <!-- <td><input type="checkbox" name="isWechatSell" id="isWechatSell" value="1" class="lcs_check" checked="checked" autocomplete="on" /></td>
		                          <td><input type="checkbox" name="isDisable" id="isDisable" value="1" class="lcs_check" checked="checked" autocomplete="on" /></td> -->
		                      </tr>
		                      </tbody>
		                  </table>
						
						<p></p>
			            <div class="clearfix"></div>
                        <div class="more-toolbar" >
                            <div class="table-toolbar">
                                <label for="">为不同会员设置折扣</label>
                            </div>
                            <div class="clearfix"></div>
                        </div>  
		                    <table class="table">
		                      <thead>
			                      <tr>
			                          <th>会员等级</th>
			                          <th>门店价格</th>
			                          <!-- <th>折扣比例</th> -->
			                          <th>会员门店价</th>
			                          <!-- <th>在线预约价</th> -->
			                          <th>
			                              <i class="cursor iconfa-plus" onclick="addDiscount();"></i>
			                          </th>
			                      </tr>
		                      </thead>
		                      <tbody id="discountTbody">
		                      </tbody>
		                    </table>
		                    
		                  <p></p>
		                  <div class="table-title" style="float: right;">
		                      <a type="" class="btn" onclick="emptyInput()">清除</a>
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
                    <p>1.现在您可以一次添加多个商品的类别</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>

<!-- 新增商品模态框 -->
<div class="modal hide in" id="add-goodsinfo-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-bumen-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title"  style="">批量添加商品</h4>
            </div>
            <input type="hidden" name="saveGoodsInfoListDeptId">
            <input type="hidden" name="saveGoodsInfoListCategoryId">
            
            <div class="modal-body" style="overflow:auto">
	           <div>
	     			<div class="desc-title">
	                    	 为系列添加商品,当前系列:<span id="crutentCategoryName"></span>
	                </div>       
	           </div>
               <div>
				   <span>商品名称：</span>
                   <input type="text" placeholder="商品名称" name="goodsInfoListName" class="mr10 ml10 input-medium">
                   <span class="fr font-span minus"><i class="iconfa-plus" onclick="addModalInputGoodsInfo(this)"></i></span>
               </div>
               <div class="modal-btn-group">
                   <a class="btn btn-primary modal-confirm w80" href="javascript:void(0)" onclick="saveGoodsInfoLists(this)">保存</a>
               </div>
                <div class="modal-footer text-left">
                <div class="desc-title">
                    	设置说明
                </div>
                <div class="desc-content">
                   	<p>1.现在您可以一次添加多个商品,实现批量</p>
                    <p>2.添加结束,点击商品进行数据的录入即可</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>
<!-- 选择品牌 -->
<div class="modal hide" id="select-brand" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 580px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择品牌
                </h4>
            </div>
            <div class="modal-body">
                <div class="border-head">
                    <input type="text" class="search-input">
                    <button type="button" class="btn search-button" id="search-brands" onclick="serchBrandByName(this)">搜索</button>
                </div>
                <div class="border-content">
                    <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
                        <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" role="tablist">
                            <li class="ui-state-default ui-corner-top" role="tab" tabindex="-1" aria-controls="tabs-1" aria-labelledby="ui-id-1" aria-selected="false"><a href="#tabs-1" class="ui-tabs-anchor" role="presentation" tabindex="-1" id="ui-id-1">本店品牌</a></li>
                            <li class="ui-state-default ui-corner-top ui-tabs-active ui-state-active" role="tab" tabindex="0" aria-controls="tabs-2" aria-labelledby="ui-id-2" aria-selected="true"><a href="#tabs-2" class="ui-tabs-anchor" role="presentation" tabindex="-1" id="ui-id-2">智放品牌库</a></li>
                        </ul>
                        <!-- 自己的品牌 -->
                        <div id="tabs-1" aria-labelledby="ui-id-1" class="ui-tabs-panel ui-widget-content ui-corner-bottom" role="tabpanel" aria-expanded="false" aria-hidden="true" style="display: none;">
                            <c:forEach items="${brands }" var="brand">
                            <div class="brand" onclick="saveBrand(this)" style="cursor: pointer;">
                                <span>${brand.brandName }</span>
                            </div>
                            </c:forEach>
                            <div class="clearfix"></div>
                        </div>
                        <!-- 自己的品牌 -->

                        <!-- 其它品牌 -->
                        <div id="tabs-2" aria-labelledby="ui-id-2" class="ui-tabs-panel ui-widget-content ui-corner-bottom" role="tabpanel" aria-expanded="true" aria-hidden="false" style="display: block;">
                            <c:forEach items="${goodsBrandList }" var="goodsBrand">
                            <div class="brand" onclick="saveBrand(this)" style="cursor: pointer;">
                                <span>${goodsBrand.codeName }</span>
                            </div>
                            </c:forEach>
                            <div class="clearfix"></div>
                        </div>
                        <!-- 其它品牌 -->
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url" data-dismiss="modal">确认</button>
            </div>
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
                <button type="button" class="btn " id="confirm-menu-url2" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<script id="brandHtml" type="text/html">
<li class="project-sublist-title">
<input type="text" name="categoryName" id="categoryName"/>
<span class="fr">
    <i class="iconfa-plus project-icon"></i>
    <i class="iconfa-pencil project-icon"></i>
    <i class="iconfa-trash project-icon"></i>
</span>
</li>
</script>

<script id="memberLevelSelect" type="text/html">
<tr>
  <td>
	  <select data-placeholder="选择会员等级"  class="chzn-select input-medium" name="levelId">
		<c:forEach var="memberLevel" items="${memberLevels }" varStatus="status">
			<option value="${memberLevel.levelId }">${memberLevel.levelName }</option>
		</c:forEach>
	  </select>
  </td>
  <td><span id="mendianjiage" class="mendianjiage"></span></td>
       <!--<td><input type="text" class="input100" name="discountProportion" datatype="n" errormsg="会员折扣比例：请输入数字！" nullmsg="请填写会员折扣比例！" onblur="calculateMenberPrice(this);"/><span class="percent-symbol">%</span></td>-->
  <td><input type="text" class="input100" name="discountAmount" datatype="n" errormsg="会员门店价：请输入整数！" nullmsg="请填写会员门店价！"/><span class="percent-symbol"></span></td>
       <!--<td><input type="text" class="input100" name="onlineAppointmentPrice"/><span class="percent-symbol"></span></td>-->
  <td><i class="cursor iconfa-minus" onclick="deleteDiscount(this);"></i></td>
</tr>
</script>

<script>
	var memberLevelList = eval("(" + '<%=request.getAttribute("memberLevelList")%>' + ")");
	var deptGoodsList = eval(<%=request.getAttribute("js_deptGoodsBaseDto")%>);
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
<script src="<%=basePath %>js/commodity/goodsInfo.js"></script>
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
		       	objImage.find("img[name='headImg']").attr("src",imageUrl);
		       	objImage.find("input[name='goodsImage']").val(key); 
		       	
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
	    jQuery("#confirm-menu-url2").one("click",function(){
	    	jQuery(objDoc).children("img").attr("src",src); 
	    	jQuery(objDoc).children("input[name='goodsImage']").val(key);
	    	
	    	jQuery(objDoc).children("img").attr("src",src);
	    	jQuery(objDoc).children("input[name='affiliatedImage']").val(key);
	    });
	});
}
</script>
<script type="text/javascript">
function selectBrand(){
	jQuery("#select-brand").modal();
}
function saveBrand(obj){
	var brandName = jQuery(obj).find("span").text();
	jQuery("input[name='brandId']").val(brandName);
	jQuery("#select-brand").modal('hide');
}
function serchBrandByName(obj){
	var brandName = jQuery(obj).prev().val();
	var brand1 = jQuery("#tabs-1").children("div[class='brand']");
	var brand2 = jQuery("#tabs-2").children("div[class='brand']");
	for (var i = 0; i < brand1.length; i++) {
		brand1.eq(i).hide();
		var name = brand1.eq(i).find("span").text();
		if(name.indexOf(brandName)!=-1){
			brand1.eq(i).show();
		}
	}
	for (var i = 0; i < brand2.length; i++) {
		brand2.eq(i).hide();
		var name = brand2.eq(i).find("span").text();
		if(name.indexOf(brandName)!=-1){
			brand2.eq(i).show();
		}
	}
}
</script>
</body>
</html>