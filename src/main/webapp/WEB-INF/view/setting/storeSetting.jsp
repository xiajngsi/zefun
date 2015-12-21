<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
 <style>
    .hide{
        display: none;
    }
</style>
<body>
<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp"%>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp"%>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
        <%@ include file="/top.jsp"%>
        <div class="maincontent">
            <div class="contentinner">
                <div class="shop-iphone">
                    <div class="iphone-inner">
                        <div class="shop-info">
                            <div class="iconfont-wrap shopImage">
                                <span class="iconfont icon-iconfonttianjiaeps"></span>
                            </div>
                            <div class="img-word cursor setting_option"  name="edit-img">添加店铺轮播图片</div>
                            <div class="shop-tel cursor setting_option" name="edit-shop-tel">设置店铺地址和电话</div>
                        </div>
                        <ul class="shop-func">
                            <li class="setting_option" name="edit-description">
                                <span class="word" >门店介绍</span> 
                                <span  class="fr iconfont icon-xiugai4"></span>
                            </li>
                            <li class="setting_option" name="edit-technical">
                                <span class="word" >技术展示</span> 
                                <span  class="fr iconfont icon-xiugai4"></span>
                            </li>
                            <li class="setting_option" name="edit-characteristic">
                                <span class="word">特色服务</span> 
                                <span  class="fr iconfont icon-xiugai4"></span>
                            </li>
                            <!-- <li class="setting_option" name="edit-mingshi">
                                <span class="word">名师介绍</span> 
                                <span  class="fr iconfont icon-xiugai4"></span>
                            </li> -->
                        </ul>
                        <!-- <img src="assets/images/1.jpg" alt="" class="shop-qrcode"/>
                        <div class="text-center qrcode-word" >扫码预览</div> -->
                    </div>
                </div>
                
                <div class="shop-edit-area edit-shop-tel hide">
                    <div class="shop-edit">
                        <div class="inner">
                            <p id="storeLogoContent">
		                        <label for="" class="shop-logo-label ">设置店铺LOGO:</label>
		                        <input type="file" id="storeLogoButton" class="shop-logo-input"/>
		                        <c:choose>
		                          <c:when test="${empty storeInfo.storeLogo }">
		                              <img src="<%=basePath %>images/shop-logo.png" id="storeLogoImg" class="shop-logo"/>
		                          </c:when>
		                          <c:otherwise>
		                              <img src="<%=picPath %>${storeInfo.storeLogo }?imageView2/1/w/120/h/120"" id="storeLogoImg" class="shop-logo"/>
		                          </c:otherwise>
		                        </c:choose>
		                        <span class="ml30">*此logo用于移动端店铺介绍页面。</span>
		                        <input type="hidden" id="storeLogo" value="${storeInfo.storeLogo }" />
		                    </p>
		                    <p>
                                <label for="">设置店铺名称</label>
                                <input type="text" id="storeName" value="${storeInfo.storeName }" class="wp97"/>
                            </p>
                            <p>
                                <label for="">设置店铺地址</label>
                                <input type="text" id="storeAddress" value="${storeInfo.storeAddress }" class="wp97"/>
                            </p>
                            <p>
                                <label for="">设置店铺电话<span class="font-999" style="font-size: 12px">(多个以,号分割)</span></label>
                                <input type="text" id="storeTel" value="${storeInfo.storeTel }" class="wp97"/>
                            </p>
                            <p>
                                <label for="">门店负责人姓名</label>
                                <input type="text" id="storeLinkname" value="${storeInfo.storeLinkname }" class="wp97"/>
                            </p>
                            <p>
                                <label for="">门店负责人电话</label>
                                <input type="text" id="storeLinkphone" value="${storeInfo.storeLinkphone }" class="wp97"/>
                            </p>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveStoreInfo()">提交</div>
                </div>
        
                <div class="shop-edit-area edit-img">
                    <div class="shop-edit">
                        <div class="inner">
                            <div class="flex-img">
                                <c:if test="${ps!=null }">
                                    <c:forEach items="${ps}" var="picture">
                                       <div class="img-wrap">
	                                        <span class="iconfont icon-guanbi"></span>
	                                        <img src="<%=picPath %>${ picture }?imageView2/1/w/400/h/400" alt="">
	                                        <input type="hidden" name="carouselPicture" value="${ picture }">
	                                    </div>
                                    </c:forEach>
                                </c:if>
                                <div class="img-wrap" id="carousel-father">
                                    <img id="carousel" src="<%=basePath %>img/img-none.png" alt=""  onclick="cavsen(this)"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveCarousel()">提交</div>
                </div>
                
                <div class="shop-edit-area edit-description hide">
                    <div class="shop-edit">
                        <div class="inner" id="descriptionImgContent" >
                            <span class="font-size-16">门店介绍</span>
                            <span class="btn" id="descriptionImg" style="margin-top: -14px; margin-left: 5px;">插入图片</span>
	                        <script id="editorDescription" type="text/plain" style="width:470px;height:562px;">${storeInfo.storeDesc}</script>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveDescription()">提交</div>
                </div>
                
                <div class="shop-edit-area edit-technical hide">
                    <div class="shop-edit">
                        <div class="inner" id="technicalImgContent">
                            <span class="font-size-16">技术展示</span>
                            <span class="btn" id="technicalImg" style="margin-top: -14px; margin-left: 5px;">插入图片</span>
                            <script id="editorTechnical" type="text/plain" style="width:470px;height:562px;">${storeInfo.technical}</script>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveTechnical()">提交</div>
                </div>
                
                <div class="shop-edit-area edit-characteristic hide">
                    <div class="shop-edit">
                        <div class="inner" id="characteristicImgContent">
                            <span class="font-size-16">特色服务</span>
                            <span class="btn" id="characteristicImg" style="margin-top: -14px; margin-left: 5px;">插入图片</span>
                            <script id="editorCharacteristic" type="text/plain" style="width:470px;height:562px;">${storeInfo.characteristic}</script>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveCharacteristic()">提交</div>
                </div>
                
                <%-- <div class="shop-edit-area edit-mingshi hide">
                    <div class="shop-edit">
                        <div class="inner">
                              <div class="search-mingshi">
                                    <div class="designer-list">
                                        <div class="designer-item-title">
                                            <div class="project-list-head">
                                                <input type="search" placeholder="搜索" class="search-input">
                                                <button type="button" class="btn search-button" id="search-member">搜索</button>
                                            </div>
                                        </div>
                                        <ul class="employeeAll">
                                             <c:forEach items="${storeEmployeeList }" var="employee">
                                                 <li class="designer-item-content" employeeId="${employee.employeeId }">
                                                    <img src="<%=picPath%>${employee.headImage}?imageView2/1/w/116/h/116"/>
                                                    <div class="info">
                                                        <div class="fs30 font-666">${employee.employeeCode } ${employee.name }</div>
                                                        <div class="fs30 font-666"> ${employee.levelName }</div>
                                                        <div><span class="normoal-word">服务 <span class="n-blue">${employee.serviceCount }</span>人次</span></div>
                                                    </div>
                                                </li>
                                             </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="yixuanze">
                                    <div class="designer-list">
                                        <div class="designer-item-title">
                                            <span class="font-size-14 font-666">已选择</span>
                                        </div>
                                        <ul class="employeeSelected">
                                             <c:forEach items="${showEmployeeList }" var="employee">
                                                 <li class="designer-item-content" employeeId="${employee.employeeId }">
                                                    <div class="shanchu-icon"><span class="iconfont icon-shanchujilu"></span></div>
                                                    <img src="<%=picPath%>${employee.headImage}?imageView2/1/w/116/h/116"/>
                                                    <div class="info">
                                                        <div class="fs30 font-666">${employee.employeeCode } ${employee.name }</div>
                                                        <div class="fs30 font-666"> ${employee.levelName }</div>
                                                        <div><span class="normoal-word">服务 <span class="n-blue">${employee.serviceCount }</span>人次</span></div>
                                                    </div>
                                                </li>
                                             </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveEmployee()">提交</div>
                </div> --%>
            </div>
        </div>
        <!--RIGHT PANEL结束 -->
        <div class="clearfix"></div>

        <div id="star"></div>
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
                <div class="btn dblock mt10 save">保存</div>
                <div class="btn dblock mt10 zoomin">放大</div>
                <div class="btn dblock mt10 zoomout">缩小</div>
              </div>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>js/setting/storeSetting.js"></script>
</body>
</html>