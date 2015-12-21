jQuery(document).ready(function(){
    jQuery('#tabs').tabs();

    jQuery("#search-member").on("click", function(){
        jQuery("#search-td").hide();
        jQuery("#member-info").show();
    });

    jQuery("#icon-search").on("click", function(){
        jQuery("#member-info").hide();
        jQuery("#search-td").show();
    });

    jQuery("#search-member-chongzhi").on("click", function(){
        jQuery("#search-td-chongzhi").hide();
        jQuery("#member-info-chongzhi").show();
    });

    jQuery("#icon-search-chongzhi").on("click", function(){
        jQuery("#member-info-chongzhi").hide();
        jQuery("#search-td-chongzhi").show();
    });


    jQuery("#search-member-chongzhi2").on("click", function(){
        jQuery("#search-td-chongzhi2").hide();
        jQuery("#member-info-chongzhi2").show();
    });

    jQuery("#icon-search-chongzhi2").on("click", function(){
        jQuery("#member-info-chongzhi2").hide();
        jQuery("#search-td-chongzhi2").show();
    });
});

//是否存在
var isExist = 0;

function changeOpenCard(obj){
    var valMoney = jQuery(obj).val();
    console.log(valMoney);
    if(valMoney == 1){
    	jQuery("span[name='1']").removeClass("hide");
        jQuery("span[name='2']").addClass("hide");
    }else{
    	jQuery("span[name='1']").addClass("hide");
        jQuery("span[name='2']").removeClass("hide");
    }
}

//汇总
function totaiUpdateEmployeeInfo(obj){
	//找到父级
	var parentObj = jQuery(obj).parents("div[name='commissionDIV']");
	
	var employeeId = jQuery(obj).val();
	var name = jQuery(obj).find("option[value='"+employeeId+"']").text();
	var vl = "";
	var commissionType = parentObj.find("select[name='totaiCommissionType']").val();
	if (commissionType == 1) {
		vl = "元";
	}
	else {
		vl = "%";
	}
	parentObj.append("<div class='p-part'>"+
                        "<div class='select-result'>"+
                            "<span name='employeeId' value='"+employeeId+"'>"+name+"：</span>"+
                            "提成--<input type='text' name = 'commission' onkeyup='checkNum(this)'/><span class='percent-symbol'>"+vl+"</span>"+
                            "业绩--<input type='text' name = 'calculate' onkeyup='checkNum(this)'/><span class='percent-symbol'>"+vl+"</span>"+
                            "<span onclick = 'totaiDeleteEmployee(this)' class = 'ml10 cursor'>X</span>"+
                        "</div>"+
                    "</div>");
	jQuery(obj).find("option[value='"+employeeId+"']").remove();
	jQuery(obj).trigger("liszt:updated");
}

function totaiUpdateVL(obj) {
	//找到父级
	var parentObj = jQuery(obj).parents("div[name='commissionDIV']");
	
	var employeeIds = parentObj.find("span[name='employeeId']");
	for (var i = 0; i < employeeIds.length; i++) {
		var commissionType = parentObj.find("select[name='totaiCommissionType']").val();
		if (commissionType == 1) {
			vl = "元";
		}
		else {
			vl = "%";
		}
		jQuery(employeeIds[i]).parent().find(".percent-symbol").text(vl);
	}
}

function totaiDeleteEmployee(obj){
	//找到父级
	var parentObj = jQuery(obj).parents("div[name='commissionDIV']");

	var employeeId = jQuery(obj).parents(".p-part").find("span[name='employeeId']").attr("value");
	var name = jQuery.trim((jQuery(obj).parents(".p-part").find("span[name='employeeId']").text()).replace("：",""));
	
	jQuery(obj).parents(".p-part").remove();
	parentObj.find("select[name='totaiRecommendId']").append("<option value='"+employeeId+"'>"+name+"</option>");
	parentObj.find("select[name='totaiRecommendId']").trigger("liszt:updated");
}


jQuery("input[name='phone']").blur(function(event) { 
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 var phone = jQuery(obj).val();
	 if (phone == "") {
	     return;
     }
	 var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	 if (!reg.test(phone)) {
		dialog("号码格式有误~");
		return;
	 }
	 jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/isCheckAccount",
			data : "phone="+phone,
			async:false,//使用同步的Ajax请求 
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					isExist = 0;
					return;
				}
				isExist = 1;
			}
		});
})

function changeMemberLevel(obj){
	var levelId = jQuery(obj).val();
	for(var i = 0; i < memberLevelList.length; i++){
		var a = 0;
		if (memberLevelList[i].levelId == levelId) {
			jQuery("select[name='openType']").empty();
			jQuery("#require").empty();
			if (memberLevelList[i].chargeAmount != "") {
				jQuery("select[name='openType']").append("<option value='1'>充值开卡</option>");
				jQuery("#require").append("<span name='1' class='mr10'>需充值："+
						                        "<span class='red' >"+memberLevelList[i].chargeAmount+"</span>元"+
						                   "</span>");
				a++;
			}
            if (memberLevelList[i].sellAmount != "") {
            	jQuery("select[name='openType']").append("<option value='2'>现金购卡</option>");
            	if(a != 0){
            		jQuery("#require").append("<span name='2' class='mr10 hide'>需支付："+
					                            "<span class='red' >"+memberLevelList[i].sellAmount+"</span>元"+
					                         "</span>");
            	}
            	else {
            		jQuery("#require").append("<span name='2' class='mr10'>需支付："+
					                            "<span class='red' >"+memberLevelList[i].sellAmount+"</span>元"+
					                         "</span>");
            	}
			}
            
            if (memberLevelList[i].chargeAmount == "" && memberLevelList[i].sellAmount == "") {
            	jQuery("select[name='openType']").append("<option value='1'>充值开卡</option>");
            	jQuery("select[name='openType']").append("<option value='2'>现金购卡</option>");
				jQuery("#require").append("<span name='1' class='mr10'>需充值："+
						                        "<span class='red' >0</span>元"+
						                   "</span>");
				jQuery("#require").append("<span name='2' class='mr10 hide'>需支付："+
                        "<span class='red' >0</span>元"+
                       "</span>");
            }
		}
	}
	jQuery("select[name='openType']").trigger("liszt:updated");
}


jQuery('body').delegate('.lcs_check_assignType', 'lcs-statuschange', function() {
    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
    if(status == 'checked'){
    	jQuery(this).val(1);
    	jQuery(this).parent().parent().siblings().find("input").next().text("%");
    }else{
    	jQuery(this).val(2);
    	jQuery(this).parent().parent().siblings().find("input").next().text("元");
    }
});

var modelType = -1;
var dataAjax = {};
jQuery("#confirm").click(function(){
	var phone = jQuery("input[name = 'phone']").val();
	
	if (phone == "") {
		dialog("号码不能为空！");
		return;
	}
	
	if (isExist == 0) {
		dialog("号码有误请修改");
		return;
	}
	
	var name = jQuery("input[name = 'name']").val();
	if (name == "") {
		dialog("名称不能为空！");
		return;
	}
	var payPassword = jQuery("input[name = 'payPassword']").val();
	var password = jQuery("input[name = 'password']").val();
	if (payPassword == "") {
		dialog("密码为空！");
		return;
	}
	if (payPassword != password) {
		dialog("密码不一致，请重新输入！");
		return;
	}
	payPassword = CryptoJS.MD5(CryptoJS.MD5(payPassword).toString().toUpperCase()).toString().toUpperCase();
	
	var openType = jQuery("select[name= 'openType']").val();
	var balanceAmount = 0;
	/*if(openType == 1){
		balanceAmount = jQuery("span[name='1']").find(".red").text();
	}
	else {
		balanceAmount = jQuery("span[name='2']").find(".red").text();
	}*/
	var cashAmount = jQuery("input[name = 'kkCashAmount']").val();
	var unionpayAmount = jQuery("input[name = 'kkUnionpayAmount']").val();
	if (cashAmount == "" && unionpayAmount == "") {
		dialog("请填写现金支付或银联支付！");
		return;
	}
	cashAmount = valueZero(cashAmount);
	unionpayAmount = valueZero(unionpayAmount);
	
	var realPrice = parseInt(cashAmount) + parseInt(unionpayAmount);
	
	balanceAmount = realPrice;
	
	var sex = jQuery("input:radio[name='sex']:checked").val();
	
	var levelId = jQuery("select[name='kkLevelId']").val();
	
	var recommend = arrayObj("tab1", realPrice);
	if (recommend == -1) {
		return;
	}
	
	//礼金
	var giftmoneyAmount = jQuery("input[name='giftmoneyAmount']").val();

	giftmoneyAmount = valueZero(giftmoneyAmount);
	var partType = jQuery("select[name='partType']").val();
	var pastDate = 0;
	
	if (parseInt(giftmoneyAmount) != 0) {
		pastDate = jQuery("select[name='pastDate']").val();
		pastDate = valueZero(pastDate);
	}
	
	//卡金
	var rewardAmount = jQuery("input[name='rewardAmount']").val();

	rewardAmount = valueZero(rewardAmount);
	//是否接收短信
	var messageType = jQuery("input:radio[name='messageType']:checked").val();
	
	dataAjax = {"phone":phone, "name":name, "sex": sex, "levelId": levelId, "openType" : openType, "recommend" : recommend, "giftmoneyAmount" : giftmoneyAmount, "pastDate" : pastDate,
	   "partType" : partType, "rewardAmount" : rewardAmount,"messageType" : messageType, "balanceAmount" : balanceAmount, "cashAmount" : cashAmount, 
	   "unionpayAmount" : unionpayAmount, "payPassword" : payPassword}
	
	openCard();
	
})

function confirmModel() {
	if (modelType == 1) {
		openCard();
	}
	else if (modelType == 2) {
		rechargeCard(1);
	}
	else {
		queren();
	}
}

function openCard() {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/addMemberInfo",
		data : dataAjax,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

//tabType 那个tab ID， type 1：提成 2： 业绩, realPrice 支付金额
function arrayObj (tabType, realPrice) {
	var tab = jQuery("#" + tabType);
	
	var	commissionParentObj = jQuery(tab).find("div[name='commissionDIV']");
	
	//选中员工标识集合
	var commissionEmployeeIds = commissionParentObj.find("span[name='employeeId']");
	
	var recommend = "";
	//类型
	var commissionType = commissionParentObj.find("select[name='totaiCommissionType']").val();
	
	for (var i = 0; i < commissionEmployeeIds.length; i++) {
		var commissionNum = jQuery(commissionEmployeeIds[i]).parent().find("input[name='commission']").val();
		
		var calculateNum = jQuery(commissionEmployeeIds[i]).parent().find("input[name='calculate']").val();
		
		var employeeId = jQuery(commissionEmployeeIds[i]).attr("value");
		
		if (commissionNum == "" && calculateNum == "") {
			var name = jQuery.trim((jQuery(commissionEmployeeIds[i]).text()).replace("：",""));
			dialog(name+"的提成,业绩不能为同时空！");
			return -1;
		}
		
		commissionNum = valueZero(commissionNum);
		
		calculateNum = valueZero(calculateNum);
		
		if (commissionType != 1) {
			commissionNum = commissionNum * realPrice/100;
			calculateNum = calculateNum * realPrice/100;
		}
		
		if (recommend == "") {
			recommend = employeeId + ":" + commissionNum + ":" + calculateNum;
		}
		else {
			recommend = recommend + "," + employeeId + ":" + commissionNum + ":" + calculateNum;
		}
	}
	
	return recommend;
}

function czConfirm(type){
	jQuery("#czModel").modal("hide");
	var memberId = jQuery("#tab2").find("input[name = 'memberId']").val();
	if (memberId == "") {
		dialog("未正确查找出充值会员！");
		return;
	}
	var chargeAmount = jQuery("input[name = 'chargeAmount']").val();
	if (chargeAmount == "") {
		dialog("充值金额不能为空！");
		return;
	}
	var cashAmount = jQuery("input[name = 'cashAmount']").val();
	var unionpayAmount = jQuery("input[name = 'unionpayAmount']").val();
	/*if (cashAmount == "" && unionpayAmount == "") {
		dialog("请填写现金支付或银联支付！");
		return;
	}*/
	cashAmount = valueZero(cashAmount);
	unionpayAmount = valueZero(unionpayAmount);
	var realPrice = parseInt(cashAmount) + parseInt(unionpayAmount);
	/*if (parseInt(chargeAmount) > realPrice) {
		dialog("充值金额不能小于支付金额！");
		return;
	}*/
	var openType = jQuery("select[name= 'openType']").val();

	var recommend = arrayObj("tab2", realPrice);
	if (recommend == -1) {
		return;
	}

	var partType = jQuery("select[name='czPartType']").val();
	
	//礼金
	var giftmoneyAmount = jQuery("input[name='czGiftmoneyAmount']").val();
	giftmoneyAmount = valueZero(giftmoneyAmount);
    var pastDate = 0;
	
	if (parseInt(giftmoneyAmount) != 0) {
		pastDate = jQuery("select[name='czPastDate']").val();
		pastDate = valueZero(pastDate);
	}
	
	//卡金
	var rewardAmount = jQuery("input[name='czRewardAmount']").val();
	rewardAmount = valueZero(rewardAmount);
	
	dataAjax = {"memberId" : memberId, "chargeAmount" : chargeAmount, "cashAmount" : cashAmount, "unionpayAmount" : unionpayAmount, "recommend" : recommend, "giftmoneyAmount" : giftmoneyAmount, 
			"pastDate": pastDate, "partType" : partType, "rewardAmount" : rewardAmount, "type" : type}
	
	rechargeCard(type);
}


//优惠赠送
function presentGift(){
	var memberId = jQuery("#tab5").find("input[name = 'memberId']").val();
	if (memberId == "") {
		dialog("未正确查找出赠送会员！");
		return;
	}
	
	//礼金
	var giftmoneyAmount = jQuery("input[name='zsGiftmoneyAmount']").val();
	if(!isEmpty(giftmoneyAmount) && isNaN(giftmoneyAmount)){
		dialog("积分只能赠送整数！");
		return;
	} else if(isEmpty(giftmoneyAmount)) {
		giftmoneyAmount = 0;
	}
	
	//积分
	var integralAmount = jQuery("input[name='zsIntegralAmount']").val();
	if(!isEmpty(integralAmount) && isNaN(integralAmount)){
		dialog("积分只能赠送整数！");
		return;
	} else if(isEmpty(integralAmount)) {
		integralAmount = 0;
	}
	
	//优惠券
	var coupon = jQuery("#couponSelect").val();
	
	if(giftmoneyAmount == 0 && integralAmount == 0 && isEmpty(coupon)) {
		dialog("暂无选择任何优惠项！");
		return;
	}
	
	if (coupon == null) {
		coupon = '';
	}
	
	var comment = jQuery("input[name='zsReason']").val();
	if (isEmpty(comment)) {
		dialog("请填写赠送原因，以备核查！");
		return;
	}
	
	var part = jQuery("[name='zsPartType']").val();
	var overdueMonth = jQuery("[name='zsPastDate']").val();
	
	var data = "memberId=" + memberId + "&giftmoneyAmount=" + giftmoneyAmount + "&part=" + part 
		+ "&overdueMonth=" + overdueMonth + "&integralAmount=" + integralAmount + "&coupon=" + coupon + "&comment=" + comment;
	console.log("presentGift data : " + data);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/presentGift",
		data : data,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("赠送成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

function rechargeCard(type) {
	dataAjax.type = type;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/rechargeMemberInfo",
		data : dataAjax,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				if (e.code == 41007) {
					jQuery("#czModel").modal("show");
				}
				else {
					dialog(e.msg);
				}
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

function zzConfirm(){
	var outMemberId = jQuery("#outDIV").find("input[name = 'memberId']").val();
	if (outMemberId == "") {
		dialog("未正确查找出转出会员！");
		return;
	}
	var inMemberId = jQuery("#inDIV").find("input[name = 'memberId']").val();
	if (inMemberId == "") {
		dialog("未正确查找出转入会员！");
		return;
	}
	var chargeAmount = jQuery("input[name = 'zzChargeAmount']").val();
	if (chargeAmount == "") {
		dialog("转入金额不能为空！");
		return;
	}
	var password = jQuery("input[name = 'zzPassword']").val();
	if (password == "") {
		dialog("转出会员密码不能为空！");
		return;
	}
	password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
    var val = (jQuery("#outDIV").find("span[name='balance']").text()).split("：");
	var num = jQuery.trim(val[1]);
	
	if (parseInt(num) < parseInt(chargeAmount)) {
		dialog("转账金额大于储值余额");
		return;
	}
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/checkoutAccount",
		data : "outMemberId="+outMemberId+"&inMemberId="+inMemberId+"&chargeAmount="+chargeAmount+"&password="+password,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

function czCancel() {
	jQuery('#czModel').addClass('hide');
}

var amountIsNull = 1;

function sjConfirm() {
	if (amountIsNull == 1) {
		var cashAmount = jQuery("input[name = 'sjCashAmount']").val();
		var unionpayAmount = jQuery("input[name = 'sjUnionpayAmount']").val();
		if (cashAmount == "" && unionpayAmount == "") {
			jQuery('#sjModel').modal('show');
			return;
		}
	}
	pastDateConfirm(1);
}

function pastDateConfirm(type) {
	amountIsNull = type;
	var giftmoneyAmount = jQuery("input[name='sjGiftmoneyAmount']").val();
	giftmoneyAmount = valueZero(giftmoneyAmount);
	
    var pastDate = 0;
	
	if (parseInt(giftmoneyAmount) != 0) {
		pastDate = jQuery("select[name='sjPastDate']").val();
		pastDate = valueZero(pastDate);
	}
	queren();
}

function queren(){
	var memberId = jQuery("#tab4").find("input[name = 'memberId']").val();
	if (memberId == "") {
		dialog("请输入正确的升级会员！");
		return;
	}
	var levelId = jQuery("select[name='sjLevelId']").val();
	var oldLevelId = jQuery("#tab4").find("input[name = 'levelId']").val();
	if (levelId == oldLevelId) {
		dialog("升级会员等级相同，不能执行该操作！");
		return;
	}
	var openType = jQuery("select[name='sjOpenType']").val();

	var cashAmount = jQuery("input[name = 'sjCashAmount']").val();
	var unionpayAmount = jQuery("input[name = 'sjUnionpayAmount']").val();

	cashAmount = valueZero(cashAmount);
	
	unionpayAmount = valueZero(unionpayAmount);
	
	var realPrice = parseInt(cashAmount) + parseInt(unionpayAmount);
	
	var recommend = arrayObj("tab4", realPrice);
	if (recommend == -1) {
		return;
	}
	
	var partType = jQuery("select[name='sjPartType']").val();
	
	//礼金
	var giftmoneyAmount = jQuery("input[name='sjGiftmoneyAmount']").val();
	giftmoneyAmount = valueZero(giftmoneyAmount);
	
	var pastDate = jQuery("select[name='sjPastDate']").val();
	
	//卡金
	var rewardAmount = jQuery("input[name='sjRewardAmount']").val();
	rewardAmount = valueZero(rewardAmount);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/upgradeMemberInfo",
		data : "memberId="+memberId+"&levelId="+levelId+"&openType="+openType+"&recommend="+recommend+"&giftmoneyAmount="+giftmoneyAmount+"&pastDate="+pastDate+"&partType="+partType+"&rewardAmount="+rewardAmount+"&cashAmount="+cashAmount+"&unionpayAmount="+unionpayAmount,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}


jQuery("#outDIV").delegate("input[name='memberId']","change",function(){
	var memberId = jQuery("#outDIV").find("input[name='memberId']").val();
	if (memberId == "") {
		jQuery("#zzTbody").empty();
	}
	else{
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/selectZcDetail",
			data : "memberId="+memberId,
			async:false,//使用同步的Ajax请求  
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					return;
				}
				var data = e.msg;
				for (var i = 0 ; i < data.length; i++) {
					jQuery("#zzTbody").append("<tr>"+
					                            "<td>"+data[i].flowTime+"</td>"+
					                            "<td>"+data[i].name+"</td>"+
					                            "<td>"+data[i].orderName+"</td>"+
					                            "<td>"+data[i].flowAmount+"</td>"+
					                           "</tr>");
				}
			}
		});
	}
});

jQuery("#partDIV").delegate("input[name='memberId']","change",function(){
	var memberId = jQuery("#partDIV").find("input[name='memberId']").val();
	if (memberId == "") {
		jQuery("#czTbody").empty();
	}
	else{
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/selectCzDetail",
			data : "memberId="+memberId,
			async:false,//使用同步的Ajax请求  
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					return;
				}
				var data = e.msg;
				for (var i = 0 ; i < data.length; i++) {
					jQuery("#czTbody").append("<tr>"+
					                            "<td>"+data[i].flowTime+"</td>"+
					                            "<td>"+data[i].name+"</td>"+
					                            "<td>"+data[i].storeName+"</td>"+
					                            "<td>"+data[i].flowAmount+"</td>"+
					                           "</tr>");
				}
			}
		});
	}
});

function valueZero(value) {
	if (isEmpty(value)) {
		value = 0;
	}
	return value;
}

function checkNum(obj) {  
    //检查是否是非数字值  
    if (isNaN(obj.value)) {  
        obj.value = "";  
    }   
} 
