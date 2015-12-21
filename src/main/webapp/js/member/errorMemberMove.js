/** 余额迁移 */
function balanceAmountMove(type, id, obj) {
	jQuery("input[name='memberId']").eq(0).val("0");
	/** 展示数据-原系统 */
	var phone = jQuery(obj).parents("tr").children("td").eq(0).text();
	var name = jQuery(obj).parents("tr").children("td").eq(1).text();
	var sex = jQuery(obj).parents("tr").children("td").eq(2).text();
	var levelNum = jQuery(obj).parents("tr").children("td").eq(3).text();
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(0).html('<span>会员姓名：</span>'+name);
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(1).html('<span>电话号码：</span>'+phone);
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(2).html('<span>会员性别：</span>'+sex);
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(3).html('<span>会员卡号：</span>'+levelNum);
	jQuery("#yueqianyi").modal();
	var data = "id="+id+"&type="+type;
	jQuery("#balandMove").on("click", function(){
		var memberId = jQuery("input[name='memberId']").eq(0).val();
		if (memberId == 0){
			dialog("请查询一个会员进行迁移");
			return;
		}
		data = data+"&memberId="+memberId;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/move/baland/info",
			data : data,
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					dialog(e.msg);
					jQuery("#err_"+id).hide();
				}else{
					
				}
				jQuery("#yueqianyi").modal('hide');
			}
		});
	});
}

/** 取消绑定事件 */
jQuery('#yueqianyi').on('hidden.bs.modal', function (){
	jQuery("#yueqianyi").find(".xitong-main").eq(1).find("li").eq(0).html('<span>会员姓名：</span> 暂无');
	jQuery("#yueqianyi").find(".xitong-main").eq(1).find("li").eq(1).html('<span>电话号码：</span> 暂无');
	jQuery("#yueqianyi").find(".xitong-main").eq(1).find("li").eq(2).html('<span>会员性别：</span> 暂无');
	jQuery("#yueqianyi").find(".xitong-main").eq(1).find("li").eq(3).html('<span>会员卡号：</span> 暂无');
	jQuery("#balandMove").unbind("click");
});
/** 取消绑定事件 */
jQuery('#taocanqianyi').on('hidden.bs.modal', function (){
	jQuery("#taocanqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(0).html('<span>会员姓名：</span> 暂无');
	jQuery("#comboMove").unbind("click");
});

function changeToMemberInfo(obj){
	/** 展示数据-智放系统 */
	var phone = jQuery(obj).prev().val();
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if (!reg.test(phone)) {
		dialog("号码格式有误~");
		return;
	}
	var data = "phone="+phone;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMemberByPhone",
		data : data,
		dataType : "json",
		success : function(e) {
			if (e.code != 0) {
				dialog(e.msg);
				return;
			}else{
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(0).html('<span>会员姓名：</span>'+e.msg.name);
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(1).html('<span>电话号码：</span>'+e.msg.phone);
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(2).html('<span>电话性别：</span>'+e.msg.sex);
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(3).html('<span>会员等级：</span>'+e.msg.levelName);
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(4).html('<span>当前余额：</span>'+e.msg.balanceAmount);
				jQuery("input[name='memberId']").eq(0).val(e.msg.memberId);
			}
		}
	});
}


/** 套餐迁移 */
function comboMove(type, id, obj){
	jQuery("input[name='memberId']").eq(1).val("0");
	/** 展示数据-原系统 */
	var phone = jQuery(obj).parents("tr").children("td").eq(0).text();
	var name = jQuery(obj).parents("tr").children("td").eq(1).text();
	var sex = jQuery(obj).parents("tr").children("td").eq(2).text();
	var levelNum = jQuery(obj).parents("tr").children("td").eq(3).text();
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(0).html('<span>会员姓名：</span>'+name);
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(1).html('<span>电话号码：</span>'+phone);
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(2).html('<span>会员性别：</span>'+sex);
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(3).html('<span>会员卡号：</span>'+levelNum);
	jQuery("#taocanqianyi").modal();
	var data = "id="+id+"&type="+type;
	jQuery("#comboMove").on("click", function(){
		var memberId = jQuery("input[name='memberId']").eq(1).val();
		if (memberId == 0){
			dialog("请查询一个会员进行迁移");
			return;
		}
		data = data+"&memberId="+memberId;
		var comboName = jQuery("input[name='comboName']").val();
		var projectId = jQuery("select[name='projectId']").val();
		var projectCount = jQuery("input[name='projectCount']").val();
		var overdueTime = jQuery("input[name='overdueTime']").val();
		data = data + "&comboName="+comboName+"&projectId="+projectId+"&projectCount="+projectCount+"&overdueTime="+overdueTime;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/move/combo/info",
			data : data,
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					dialog(e.msg);
					jQuery("#err_"+id).hide();
				}else{
					dialog(e.msg);
					return;
				}
				jQuery("#taocanqianyi").modal('hide');
			}
		});
	});
}

function comboPhone(obj){
	/** 展示数据-智放系统 */
	var phone = jQuery(obj).prev().val();
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if (!reg.test(phone)) {
		dialog("号码格式有误~");
		return;
	}
	var data = "phone="+phone;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMemberByPhone",
		data : data,
		dataType : "json",
		success : function(e) {
			if (e.code != 0) {
				dialog(e.msg);
				return;
			}else{
				jQuery("#taocanqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(0).html('<span>会员姓名：</span>'+e.msg.name);
				jQuery("input[name='memberId']").eq(1).val(e.msg.memberId);
			}
		}
	});
}

jQuery('.timePickerClean').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'Y-m-d'
});
