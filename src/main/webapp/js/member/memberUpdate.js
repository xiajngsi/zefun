/** 生日日历插件 */
jQuery('.timePickerClean').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'Y-m'
});

/** 修改滑动样式 */
jQuery(".can-click.m-btn.update").hover(function() {
	jQuery(this).find("span").show();
}, function() {
	jQuery(this).find("span").eq(1).hide();
});
var objTr;
/** 弹出修改信息模态框 */
jQuery("body").delegate(".can-click.m-btn.update","click",function(){
	var tr = jQuery(this).parents("tr");
	objTr = tr;
	var memberId = tr.children("td").eq(1).attr("id");
	var phone = tr.children("td").eq(0).text();
	var name = tr.children("td").eq(1).text();
	var sex = tr.children("td").eq(2).text();
	var birthday = tr.children("td").eq(4).text();
	var levelName = tr.children("td").eq(3).text();
	var balanceAmount = tr.children("td").eq(5).text();
	var balanceIntegral = tr.children("td").eq(6).text();
	var totalConsumeAmount = tr.children("td").eq(7).text();
	var avgConsumeAmount = tr.children("td").eq(8).text();
	var createTime = tr.children("td").eq(9).text();
	
	jQuery("#member-xiugai").find("tbody").children("tr").eq(0).children("td").eq(1).text(levelName);
	jQuery("#member-xiugai").find("tbody").children("tr").eq(0).children("td").eq(3).text(balanceAmount);
	jQuery("#member-xiugai").find("tbody").children("tr").eq(1).children("td").eq(1).text(balanceIntegral);
	jQuery("#member-xiugai").find("tbody").children("tr").eq(1).children("td").eq(3).text(totalConsumeAmount);
	jQuery("#member-xiugai").find("tbody").children("tr").eq(2).children("td").eq(1).text(avgConsumeAmount);
	jQuery("#member-xiugai").find("tbody").children("tr").eq(2).children("td").eq(3).text(createTime);
	
	jQuery("input[name='memberId']").val(memberId);
	jQuery("input[name='name']").val(name);
	jQuery("input[name='phone']").val(phone);
	jQuery("input[name='sex']").val(sex);
	jQuery("input[name='birthday']").val(birthday);
	
	jQuery("#member-xiugai").find(".xg-title").find("li").eq(0).find("input").val(name);
	
	if (sex == "男"){
		jQuery("#member-xiugai").find(".xg-title").find("li").eq(1).find("input[type='radio']").eq(0).prop("checked",true);
		jQuery("#member-xiugai").find(".xg-title").find("li").eq(1).find("input[type='radio']").eq(1).removeAttr("checked");
	}else {
		jQuery("#member-xiugai").find(".xg-title").find("li").eq(1).find("input[type='radio']").eq(0).removeAttr("checked");
		jQuery("#member-xiugai").find(".xg-title").find("li").eq(1).find("input[type='radio']").eq(1).prop("checked",true);
	}
	
	jQuery("#member-xiugai").find(".xg-title").find("li").eq(2).find("input").val(birthday);
	jQuery("#member-xiugai").find(".xg-title").find("li").eq(3).find("input").val(phone);
	
	jQuery("#member-xiugai").modal();
});
/**修改姓名*/
function changeName(obj){
	jQuery("input[name='name']").val(jQuery(obj).val());
}
/**修改性别*/
function changeSex(sex){
	if (sex == "男"){
		jQuery("#member-xiugai").find(".xg-title").find("li").eq(1).find("input[type='radio']").eq(0).attr("checked","checked");
		jQuery("#member-xiugai").find(".xg-title").find("li").eq(1).find("input[type='radio']").eq(1).removeAttr("checked");
	}else {
		jQuery("#member-xiugai").find(".xg-title").find("li").eq(1).find("input[type='radio']").eq(0).removeAttr("checked");
		jQuery("#member-xiugai").find(".xg-title").find("li").eq(1).find("input[type='radio']").eq(1).attr("checked","checked");
	}
	jQuery("input[name='sex']").val(sex);
}
/**修改生日*/
function changeBirthday(obj){
	jQuery("input[name='birthday']").val(jQuery(obj).val());
}
/**修改手机号码*/
function changePhone(obj){
	jQuery("input[name='phone']").val(jQuery(obj).val());
}

/** 提交 */
function updateMember(){
	var data = jQuery("#updateMemberInfo").serialize();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/update/member/info",
		data : data,
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				dialog(e.msg);
				var memberId = jQuery("input[name='memberId']").val();
				
				var name = jQuery("input[name='name']").val();
				var phone = jQuery("input[name='phone']").val();
				var sex = jQuery("input[name='sex']").val();
				var birthday = jQuery("input[name='birthday']").val();
				
				jQuery(objTr).children("td").eq(0).text(phone);
				jQuery(objTr).children("td").eq(1).text(name);
				jQuery(objTr).children("td").eq(2).text(sex);
				jQuery(objTr).children("td").eq(4).text(birthday);
				
				jQuery("#member-xiugai").modal('hide');
			}else{
				dialog(e.msg);
				jQuery("#member-xiugai").modal('hide');
			}
		}
	});
}