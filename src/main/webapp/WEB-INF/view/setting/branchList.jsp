<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
 <style>
    .hide{
        display: none!important;
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
		       <div class="zf-logn clearfix">
		           <img src="<%=picPath %>${storeInfo.storeLogo}?imageView2/1/w/107/h/107" class="fl"/>
		           <span>${storeInfo.storeName }</span>
		         <!-- <div class="md-search fr">
		             <input type="search" placeholder="搜索">
		             <button class="btn search-button">搜索</button>
		         </div> -->
		       </div>
		        <div class="mendian">
		            <ul>
		              <c:forEach items="${storeList }" var="store">
		                  <li class="md1">
                            <dl>
                                <dt class="fl"><img src="<%=picPath %>${store.storeLogo}?imageView2/1/w/107/h/107"/></dt>
                                <dd class="fl">
                                    <h3>${store.storeName }</h3>
                                    <p>${store.storeAddress }</p>
                                    <p>电话：${store.storeTel }</p>
                                    <p>负责人：${store.storeLinkname }</p>
                                    <p>负责人电话：${store.storeLinkphone }</p>
                                </dd>
                            </dl>
                          </li>
		              </c:forEach>
		            </ul>
		        </div>
		    </div>
		</div>
        <!--RIGHT PANEL结束 -->
        <div class="clearfix"></div>

        <div id="star"></div>
    </div>
</div>

<script type="text/javascript">
var isCost = "${storeSetting.costCommissionType}";
var couponStr = "${storeSetting.firstFollowCoupon}";
jQuery(function(){
	jQuery("[name='costCommissionType'][value='" + isCost + "']").attr('checked',true);
	
	if (!isEmpty(couponStr)) {
		var couponArr = couponStr.split(",");
		for (var i = 0; i < couponArr.length; i++) {
			jQuery("#couponSelect option[value='" + couponArr[i] + "']").attr("selected", "selected");
		}
		jQuery("#couponSelect").trigger("liszt:updated");
	}
	
});

function save(){
    var data = jQuery("#baseSettingForm").serialize();
    var coupon = jQuery("#couponSelect").val();
    if (!isEmpty(coupon) && coupon.length > 0) {
    	data += "&firstFollowCoupon=" + coupon.toString();
    }
    jQuery.ajax({
        url : baseUrl + "system/action/baseSetting",
        type : "POST",
        data : data,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("更新成功");
        }
    });
}
</script>
</body>
</html>