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
		        <div class="border-head">
		            <span>基础配置</span>
		        </div>
		        <form id="baseSettingForm">
			        <div class="border-content">
			            <table class="table nobordered-table">
			                <tr>
                                <td class="text-right fb">提成是扣除成本:</td>
                                <td>
                                     <input type="radio" name="costCommissionType" value="1" />&nbsp;是&nbsp;&nbsp;
                                     <input type="radio" name="costCommissionType" value="0" />&nbsp;否&nbsp;&nbsp;
                                     <span class="">计算员工业绩是否扣除项目/商品设置的成本价格</span>
                                </td>
                            </tr>
			                <tr>
			                    <td class="text-right fb">业绩折扣-优惠券:</td>
			                    <td>
			                         <input type="text" name="couponCommissionRate" value="${storeSetting.couponCommissionRate }" class="name w60"/>
			                         <span class='percent-symbol'>%</span>
			                         <span>客户使用优惠券抵扣的金额乘以此比例为优惠券的业绩值，0-100</span>
			                    </td>
			                </tr>
			                <tr>
                                <td class="text-right fb">业绩折扣-礼金:</td>
                                <td>
                                     <input type="text" name="giftCommissionRate" value="${storeSetting.giftCommissionRate }" class="name w60"/>
                                     <span class='percent-symbol'>%</span>
                                     <span>客户使用礼金抵扣的金额乘以此比例为礼金的业绩值，0-100</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">会员短信服务费:</td>
                                <td>
                                     <input type="text" name="smsFee" value="${storeSetting.smsFee }" class="name w60"/>
                                     <span class='percent-symbol'>元</span>
                                     <span>每月1号从会员卡金中扣除</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">预约到时提醒:</td>
                                <td>
                                     <input type="text" name="appointRemindHour" value="${storeSetting.appointRemindHour }" class="name w60"/>
                                     <span class='percent-symbol'>H</span>
                                     <span>快到预约时间时发出预约提醒通知(单位:小时)</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">优惠券过期提醒:</td>
                                <td>
                                     <input type="text" value="3" class="name w60"/>
                                     <span class='percent-symbol'>天</span>
                                     <span>在会员优惠券快过期时发送提醒</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">新会员奖励:</td>
                                <td>
                                     <span>首次关注公众号的会员自动发送此奖励(多选择)</span><br/>
                                     优惠券赠送：
                                     <select class="chzn-select wth100" id="couponSelect" multiple="true" data-placeholder="请选择要赠送的优惠券，可多选">
                                        <c:forEach items="${couponList }" var="coupon">
                                            <option value="${coupon.couponId }">${coupon.couponName }</option>
                                        </c:forEach>
                                     </select>
                                     礼金赠送：
                                     <input type="text" name="firstFollowGift" class="name w60" value="${storeSetting.firstFollowGift }" />
                                     <span class='percent-symbol'>元</span>
                                </td>
                            </tr>
			                <tr>
	                            <td class="text-right fb"><a class="btn btn-primary" href="javascript:save();">保存</a></td>
	                            <td></td>
	                        </tr>
			            </table>
			        </div>
		        </form>
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