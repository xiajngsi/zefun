var isMember = false;
var orderId = null;
var useOffObject = new Object();
var totalRealMoney = new Big(0);
var totalReceivableMoney = 0;
var allOffMap = null;
var detailOffMap = new Object();
var selectOffList = new Array();
var balanceAmount = 0;
var appointOff = 0;

/**
 * 弹框搜索用户
 * @param orderId 订单标识
 * @param $obj 选中的行
 */
function openMemberInfoDialog(orderId, $obj) {
	jQuery("#btn-search-member").bind("click", function(){
		searchMemberInfo(orderId, $obj);
	});
	jQuery("#find-member").modal({show:true, backdrop:"static"});
}
/**
 * 搜索会员
 * @param orderId 订单标识
 * @param $obj 选中的行
 */
function searchMemberInfo(orderId, $obj) {
	var memberId = null;
	var memberName = jQuery("#ipt-search-mamber").val();
	if(memberName == '') {
		dialog("请输入用户信息");
		return ;
	}
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"selfcashier/action/memberinfo",
        data: {'orderId' : orderId, 'phone' : memberName},
        async: false,
		success: function(data) {
			if(data.code == 0) {
				var memberinfo = data.msg;
				memberId = memberinfo.memberId;
				jQuery("#search-membername").text(memberinfo.name);
				jQuery("#search-memberlevel").text(memberinfo.levelName);
				jQuery("#search-memberamount").text(memberinfo.balanceAmount);
				jQuery("#search-memberstore").text(memberinfo.storeName);
				jQuery("#btn-order-member-submit").bind("click", function(){
					changeMemberInfo(orderId, memberId, memberinfo.name, $obj);
				});
			} else {
            	dialog("搜索不到会员，请重试");
            }
		}
	});
}
/**
 * 修改订单，绑定会员信息
 * @param orderId 订单标识
 * @param memberId 会员标识
 * @param memberName 会员名称
 * @param $obj 选中的行
 */
function changeMemberInfo(orderId, memberId, memberName, $obj) {
	var passwd = jQuery.trim(jQuery("#ipt-member-passwd").val());
	if(passwd == '') {
		dialog("请输入会员的支付密码！");
		return;
	}
	passwd = CryptoJS.MD5(CryptoJS.MD5(passwd).toString().toUpperCase()).toString().toUpperCase();
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"selfcashier/action/changemember",
        data: {'orderId' : orderId, 'memberId' : memberId, 'passwd' : passwd},
        async: false,
		success: function(data) {
			if(data.code == 0) {
				var memberTd = jQuery($obj).parent();
				var memberinfo = data.msg;
				memberTd.text(memberName);
				memberTd.addClass("can-click");
				jQuery("#btn-order-member-submit").unbind("click");
				jQuery('#cashier').modal('hide');
			} else if(data.msg != null){
            	dialog(data.msg);
            } else {
            	dialog("指定的会员不存在！");
            }
			jQuery('#find-member').modal('hide');
			cancleSearchMember();
		}
	});
}
/**
 * 取消搜索会员
 * @param orderId 订单标识
 */
function cancleSearchMember() {
	jQuery("#ipt-search-mamber").val('');
	jQuery("#ipt-member-passwd").val('');
	jQuery("#search-membername").text('');
	jQuery("#search-memberlevel").text('');
	jQuery("#search-memberamount").text('');
	jQuery("#search-memberstore").text('');
	jQuery("#btn-order-member-submit").unbind("click");
	jQuery("#btn-search-member").unbind("click");
}

function showCashierDetail(orderId, orderStatus) {
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "selfcashier/action/orderinfo",
        data: {"orderId" : orderId},
		success: function(data) {
			if(data.code != 0) {
				dialog(data.msg);
				return;
			}
			jQuery(".submitButton").addClass("hide");
			addCashierDetail(data.msg);
			console.log(data.msg.orderStatus)
			if (data.msg.orderStatus == 2 || data.msg.orderStatus == 5) {
				jQuery(".submitButton").removeClass("hide");
			}
		}
	});
}

/**
 * 提交订单
 */
function submitOrderInfo() {
	var details = new Array();
	var detailId = null;
	var couponValue = null;
	jQuery("#tbody-datainfo tr").each(function(){
		var $this = jQuery(this);
		detailId = $this.attr("detailId");
		var off = jQuery($this.find("[name='selectOff'] option:selected"));
		if(isEmpty(off) || isEmpty(jQuery(off).attr("offType"))) {
			details.push({"detailId" : detailId, "offType" : 0, "offId" : 0, "offAmount" : 0});
		} else {
			details.push({"detailId" : detailId, "offType" : jQuery(off).attr("offType"), "offId" : jQuery(off).attr("offId"), "offAmount" : jQuery(off).attr("offAmount")});
		}
	});

	var cardAmount = new Big(jQuery("#cardpayAmount").val());
	var cashAmount = new Big(jQuery("#cashAmount").val());
	var unionpayAmount = new Big(jQuery("#unionpayAmount").val());
	var wechatAmount = new Big(jQuery("#wechatAmount").val());
	var alipayAmount = new Big(jQuery("#alipayAmount").val());
	
	if(cardAmount.gt(balanceAmount)){
		dialog("余额不足，请充值");
		return;
	}

	var tempAmount = cardAmount.plus(cashAmount).plus(unionpayAmount).plus(wechatAmount).plus(alipayAmount);
//	if (tempAmount.lt(totalRealMoney)) {
//		dialog("还差" + totalRealMoney.minus(tempAmount).toFixed(2) + "元才能买单哦");
//		return;
//	} 
//	else if (tempAmount.gt(totalRealMoney)) {
//		dialog("您多付了" + tempAmount.minus(totalRealMoney).toFixed(2) + "元，请更正");
//		return;
//	}
	
	var data = {'orderId':orderId,'cardAmount':cardAmount,'cashAmount':cashAmount,
        	'unionpayAmount':unionpayAmount,'wechatAmount':wechatAmount,'alipayAmount':alipayAmount,'detailList':details};
	jQuery.ajax({
        type: "POST",
        url: baseUrl + "selfcashier/action/submitorder",
        contentType: "application/json",
        data: JSON.stringify(data),
		success: function(data) {
			if(data.code != 0) {
				dialog(data.msg);
			} else {
				dialog('结账成功');
            }
			jQuery('#cashier').modal('hide');
			//
			window.location.href = baseUrl + 'selfcashier/view/list';
		}
	});
}

function addCashierDetail(orderInfo){
	totalRealMoney = new Big(0);
	totalReceivableMoney = 0;
	appointOff = 0;
	jQuery("#appointOff").html("");
	
	initHead(orderInfo);
	
	orderId = orderInfo.orderId;
	var tbody = document.getElementById("tbody-datainfo");
	tbody.innerHTML = "";
	var orderDetails = orderInfo.orderDetails;
	allOffMap = orderInfo.allOffMap;
	
	for (var i = 0; i < orderDetails.length; i++){
		var detail = orderDetails[i];
		var realMoney = new Big(detail.projectPrice);
		
		var tr = document.createElement("tr");
		tr.setAttribute("detailId", detail.detailId)
		
		tdName = document.createElement("td");
		tdName.innerHTML = detail.projectName;
		tr.appendChild(tdName);
		
		tdPrice = document.createElement("td");
		tdPrice.innerHTML = detail.projectPrice;
		tr.appendChild(tdPrice);
		
		totalReceivableMoney += detail.projectPrice;
		appointOff += detail.appointOff;
		if (isMember) {
			
			tr.setAttribute("detailId", detail.detailId);
			tr.setAttribute("uid", 0);
			tr.setAttribute("offId", 0);
			tr.setAttribute("offName", "");
			tr.setAttribute("offType", 0);
			tr.setAttribute("offAmount", 0);
			tr.setAttribute("projectPrice", detail.projectPrice);
			tr.setAttribute("discountAmount", detail.discountAmount);
			tr.setAttribute("highestDiscount", detail.highestDiscount);
			
			var tdDiscount = document.createElement("td");
			tdDiscount.innerHTML = detail.discountAmount;
			tr.appendChild(tdDiscount);
			
			realMoney = new Big(detail.discountAmount);
			
			//套餐/礼金/优惠券抵扣，组装数据
			var selectOff = document.createElement("select");
			selectOff.setAttribute("detailid", detail.detailId);
			selectOff.setAttribute("class", "chzn-select");
			selectOff.setAttribute("name", "selectOff");
			selectOff.setAttribute("data-placeholder", "暂无优惠选项");
			
			//检查是否存在优惠项
	        var paymentOffList = detail.paymentOffList;
	        if (paymentOffList != null && paymentOffList.length > 0) {
	            var detailOffList = new Array();
	            //标记是否选择了最佳优惠项
	            var isSelected = false;
	            for (var j = 0; j < paymentOffList.length; j++) {
	                var offObj = paymentOffList[j];
	                
	                var uid = offObj.type + "_" + offObj.id;
	                detailOffList.push(uid);
	                //检查该优惠的可用次数/余额
	                var balance = new Big(allOffMap[uid]);
	                if (balance < 1) {
	                	continue;
	                }
	                
	                var optionOff = document.createElement("option");
	                optionOff.setAttribute("uid", uid);
	                optionOff.setAttribute("detailId", detail.detailId);
	                optionOff.setAttribute("offType", offObj.type);
	                optionOff.setAttribute("offId", offObj.id);
	                optionOff.setAttribute("offAmount", offObj.amount);
	                optionOff.setAttribute("offName", offObj.name);
	                optionOff.value = uid;
	                optionOff.innerHTML = offObj.name + ' -' + offObj.amount + '元 ';
	                
	                //检查当前优惠是否被其他消费项目使用，如未使用，直接用为最佳优惠项，否则继续检查下一个
	                if (isSelected == false) {
	                    //如果为礼金，此次使用数量为项目价格
	                    if (offObj.type == 4) {
	                    	//先检查礼金余额是否够抵用
	                    	if (balance.lt(detail.highestDiscount)) {
	                    		offObj.amount = balance;
	                    		optionOff.setAttribute("offAmount", balance);
	                    		optionOff.innerHTML = offObj.name + ' -' + offObj.amount + '元 ';
	                    	}
	                    	
	                    	realMoney = new Big(detail.projectPrice - offObj.amount);
	                    	balance = balance.minus(offObj.amount);
	                    } 
	                    else {
	                    	balance = balance.minus(1);
	                    	realMoney = realMoney.minus(offObj.amount);
	                    }
	                    isSelected = true;
	                    
	                    allOffMap[uid] = balance;
	                    tr.setAttribute("uid", uid);
	                    tr.setAttribute("offId", offObj.id);
	        			tr.setAttribute("offName",offObj.name);
	                    tr.setAttribute("offType", offObj.type);
	                    tr.setAttribute("offAmount", offObj.amount);
	                	optionOff.setAttribute("selected", "selected");
	                }
	                selectOff.appendChild(optionOff);
	            }
	            
	        }
	        
	        var tdOff = document.createElement("td");
			tdOff.appendChild(selectOff);
			tr.appendChild(tdOff);
			
			selectOffList.push(jQuery(selectOff));
            detailOffMap[detail.detailId] = detailOffList;
            
            if (realMoney.lt(0)) {
            	realMoney = 0;
            }
		}
		
		if(appointOff > 0){
			jQuery("#appointOff").html("预约优惠金额：" + appointOff + "元");
		}
		
		//实收金额
		var tdReal = document.createElement("td");
		tdReal.innerHTML = realMoney;
		tr.appendChild(tdReal);
		
		totalRealMoney = totalRealMoney.plus(realMoney);
		syncRealMoney();
		tbody.appendChild(tr);
		jQuery("[name='selectOff']").chosen({disable_search_threshold: 10, width:"95%"});
	} 
	
	if (isMember) {
		balanceAmount = new Big(orderInfo.memberInfo.balanceAmount);
		syncCardAmount();
	}
	else {
		jQuery("#cashAmount").val(totalRealMoney.toFixed(2));
	}
	
	jQuery("#totalReceivableMoney").html(totalReceivableMoney);
	jQuery("#cashier").modal({show:true, backdrop:"static"});
}

function syncRealMoney(){
	totalRealMoney = totalRealMoney.minus(appointOff);
	jQuery("#totalRealMoney").html(totalRealMoney.toFixed(2));
}

function syncCardAmount(){
	if(balanceAmount.lt(totalRealMoney)){
		jQuery("#cardpayAmount").val(balanceAmount.toFixed(2));
	} else {
		jQuery("#cardpayAmount").val(totalRealMoney.toFixed(2));
	}
}

//优惠选项改变时的处理
jQuery("#cashier").delegate("[name='selectOff']", "change", function(event){
	var selectOff = jQuery(this).children('option:selected');
	var id = selectOff.val();
	
	var detailId = selectOff.attr("detailId") ;
	var tr = jQuery("tr[detailId='" + detailId + "']");
	var oldUid = tr.attr("uid");
	var oldType = tr.attr("offType");
	var oldId = tr.attr("offId");
	var oldName = tr.attr("offName");
	var oldAmount = tr.attr("offAmount");
	
	get(selectOff, id);
	
	put(detailId, oldUid, oldType, oldId, oldName, oldAmount);
	console.log("change...");
});

//选择新的优惠项
function get(selectOff, id){
	var detailId = selectOff.attr("detailId") ;
	var tr = jQuery("tr[detailId='" + detailId + "']");
	var uid = selectOff.attr("uid");
	var offType = selectOff.attr("offType");
	var offId = selectOff.attr("offId");
	var offName = selectOff.attr("offName");
	var offAmount = selectOff.attr("offAmount");
	
	var projectPrice = new Big(tr.attr("projectPrice"));
	var discountAmount = new Big(tr.attr("discountAmount"));
	
	var banlance = new Big(allOffMap[uid]);
	//计算使用余额
	var useAmount = 0;
	var tealMoneyTd = tr.find("td:last");
	var curRealMoney = new Big(tealMoneyTd.html());
	var realMoney = new Big(0);
	if (offType == 4) {
		useAmount = banlance;
		if(projectPrice.lt(banlance)){
			useAmount = projectPrice;
		}
		realMoney = projectPrice.minus(useAmount);
		banlance = banlance.minus(useAmount);
		allOffMap[uid] = banlance;
		
		//礼金还需要检查其他的待选项，同步其可用金额
		for (var j = 0; j < selectOffList.length; j++) {
			var tempSelect = selectOffList[j];
			var tempDetailId = tempSelect.attr("detailId");
			//如果为当前选项，直接跳过
			if (tempDetailId == detailId) {
				continue;
			}
			
			//检查遍历项是否可以使用该项优惠，如不可以直接跳过
			var useOffList = detailOffMap[tempDetailId];
			if(jQuery.inArray(uid, useOffList) < 0){
				continue;
			}
			
			var option = tempSelect.children("[uid='" + uid + "']");
			var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']");
			var option = tempSelect.children("[uid='" + uid + "']");
			if (option != null && tempDetail.attr("uid") != option.attr("uid")) {
				var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']")
				var highestDiscount = new Big(tempDetail.attr("highestDiscount"));
				var tempOffAmount = new Big(option.attr("offAmount"));
				tempOffAmount = banlance;
				if (tempOffAmount.gt(highestDiscount)) {
					tempOffAmount = highestDiscount;
				}
				var tempOffset = tempOffAmount.minus(option.attr("offAmount"));
				option.attr("offAmount", tempOffAmount);
				option.html(offName + ' -' + tempOffAmount + "元");
				tempSelect.trigger("liszt:updated");
			}
		}
	} else {
		useAmount = offAmount;
		realMoney = discountAmount.minus(useAmount);
		banlance = banlance.minus(1);
		allOffMap[uid] = banlance;
	}
	if (realMoney.lt(0)) {
		realMoney = 0;
	}
	//重新计算实收
	tealMoneyTd.html(realMoney.toFixed(2));
	totalRealMoney = totalRealMoney.minus(curRealMoney).plus(realMoney);
	syncRealMoney();
	syncCardAmount();
	
	tr.attr("uid", id);
	tr.attr("offId", offId);
	tr.attr("offName", offName);
	tr.attr("offType", offType);
	tr.attr("offAmount", useAmount);
	
	//如果余额为0，需要检查其他选项表，将其删除
	if(banlance.lte(0)){
		for (var j = 0; j < selectOffList.length; j++) {
			var tempSelect = selectOffList[j];
			var tempDetailId = tempSelect.attr("detailId");
			//如果为当前选项，直接跳过
			if (tempDetailId == detailId) {
				continue;
			}
			
			//检查遍历项是否可以使用该项优惠，如不可以直接跳过
			var useOffList = detailOffMap[tempDetailId];
			if(jQuery.inArray(uid, useOffList) < 0){
				continue;
			}

			var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']");
			var option = tempSelect.children("[uid='" + uid + "']");
			if (option != null && tempDetail.attr("uid") != option.attr("uid")) {
				option.remove();
				tempSelect.trigger("liszt:updated");
			}
		}
	}
}

function put(detailId, oldUid, oldType, oldId, oldName, oldAmount){
	var oldBanlance = new Big(allOffMap[oldUid]);
	//1.归还使用金额/数量
	if (oldType == 4) {
		oldBanlance = oldBanlance.plus(oldAmount);
	} else {
		oldBanlance = oldBanlance.plus(1);
	}
	allOffMap[oldUid] = oldBanlance;
	
	//2.检查是否有需要该资源的消费项
	for (var j = 0; j < selectOffList.length; j++) {
		var tempSelect = selectOffList[j];
		var tempDetailId = tempSelect.attr("detailId");
		//如果为当前选项，直接跳过
		
		//检查遍历项是否可以使用该项优惠，如不可以直接跳过
		var useOffList = detailOffMap[tempDetailId];
		if(jQuery.inArray(oldUid, useOffList) < 0){
			continue;
		}
		
		var option = tempSelect.children("[uid='" + oldUid + "']");
		var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']")
		var tempProjectPrice = new Big(tempDetail.attr("projectPrice"));
		var tempDiscountAmount = new Big(tempDetail.attr("discountAmount"));
		var highestDiscount = new Big(tempDetail.attr("highestDiscount"));
		//如果未使用，加入其中
		if (option == null || option.length == 0) {
			var optionOff = document.createElement("option");
            optionOff.setAttribute("uid", oldUid);
            optionOff.setAttribute("detailId", tempDetailId);
            optionOff.setAttribute("offType", oldType);
            optionOff.setAttribute("offId", oldId);
            optionOff.setAttribute("offName", oldName);
            optionOff.setAttribute("offAmount", oldAmount);
            optionOff.value = oldUid;
            optionOff.innerHTML = oldName + ' -' + oldAmount + '元 ';
            //如果是礼金，需要检查抵扣金额
            if (oldType == 4) {
            	oldAmount = oldBanlance;
            	if (highestDiscount.lt(oldAmount)) {
            		oldAmount = highestDiscount;
            	}
            }
            tempSelect.append(optionOff);
            
            tempSelect.trigger("liszt:updated");
		} 
	}
	
	//如果是礼金
	if (oldType == 4) {
		//1.先检查所有在使用礼金的优惠列表
		var giftOptionList = jQuery("option:selected[uid='" + oldUid + "']");
		for (var i = 0; i < giftOptionList.length; i++) {
			var giftOption = jQuery(giftOptionList[i]);
			
			var tempDetailId = giftOption.attr("detailId");
			var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']");
			var tempProjectPrice = new Big(tempDetail.attr("projectPrice"));
			var highestDiscount = new Big(tempDetail.attr("highestDiscount"));
			
			var tempOffAmount = new Big(giftOption.attr("offAmount"));
			oldBanlance = oldBanlance.plus(tempOffAmount);
			tempOffAmount = oldBanlance;
			if (tempOffAmount.gt(highestDiscount)) {
				tempOffAmount = highestDiscount;
			}
			giftOption.attr("offAmount", tempOffAmount);
			giftOption.html(oldName + ' -' + tempOffAmount + "元");
			oldBanlance = oldBanlance.minus(tempOffAmount);
			allOffMap[oldUid] = oldBanlance;
			
			//重新计算实收金额
			var curRealMoney = new Big(tempDetail.find("td:last").html());
			var tempRealMoney = tempProjectPrice.minus(tempOffAmount).toFixed(2);
			totalRealMoney = totalRealMoney.minus(curRealMoney).plus(tempRealMoney);
			tempDetail.find("td:last").html(tempRealMoney);
			tempDetail.attr("offAmount", tempOffAmount);
			
			syncRealMoney();
			syncCardAmount();
			jQuery("select[detailId='" + tempDetailId +"'").trigger("liszt:updated");
		}
		
		//2.再检查所有存在礼金优惠项的优惠列表，同步其余额
		giftOptionList = jQuery("option[uid='" + oldUid + "']");
		for (var j = 0; j < giftOptionList.length; j++) {
			var giftOption = jQuery(giftOptionList[j]);
			var tempDetailId = giftOption.attr("detailId");
			var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']")
			if(giftOption.attr("uid") == tempDetail.attr("uid")){
				continue;
			}
			if(oldBanlance.lte(0)){
				giftOption.remove();
			} 
			else {
				var highestDiscount = new Big(tempDetail.attr("highestDiscount"));
				
				var tempOffAmount = new Big(giftOption.attr("offAmount"));
				tempOffAmount = oldBanlance;
				if (tempOffAmount.gt(highestDiscount)) {
					tempOffAmount = highestDiscount;
				}
				var tempOffset = tempOffAmount.minus(giftOption.attr("offAmount"));
				giftOption.attr("offAmount", tempOffAmount);
				giftOption.html(oldName + ' -' + tempOffAmount + "元");
			}
			
			jQuery("select[detailId='" + tempDetailId +"'").trigger("liszt:updated");
		}
	}
	
}

//初始化订单支付头部信息
function initHead(orderInfo){
	var orderCode = orderInfo.orderCode;
	var memberId = orderInfo.memberId;
	var memberName = "";
	var memberLevel = "";
	var memberImg = "";
	if (!isEmpty(memberId)) {
		memberName = orderInfo.memberInfo.name;
		memberLevel = orderInfo.memberInfo.levelName;
		memberImg = orderInfo.memberInfo.headUrl;
		isMember = true;
	} else {
		memberName = "散客";
		memberLevel = orderInfo.sex;
		if (memberLevel == "男") {
			memberImg = "pc/userhead/default/male.png";
		} else {
			memberImg = "pc/userhead/default/female.png";
		}
		isMember = false;
	}
	memberImg = picUrl + memberImg;
	
	jQuery("[name='payImg']").attr("src", memberImg);
	jQuery("[name='payMemberName']").html(memberName);
	jQuery("[name='payMemberLevel']").html(memberLevel);
	jQuery("[name='payOrderCode']").html(orderCode);
	
	var cashierOrderTheadBody = "<th>项目名称</th><th>项目价格</th>";
	if(!isEmpty(memberId)) {
		cashierOrderTheadBody += "<th>会员价格</th><th>套餐/礼金/优惠券抵扣</th>";
		jQuery("#memberCardBalance").html(orderInfo.memberInfo.balanceAmount);
//		jQuery("#orderAccount tr").addClass("hide");
		jQuery("#memberAccount").removeClass("hide");
	} else {
//		jQuery("#orderAccount tr").removeClass("hide");
		jQuery("#memberAccount").addClass("hide");
	}
	cashierOrderTheadBody += "<th>实收金额</th>";
	jQuery("#detail-thead-body").html(cashierOrderTheadBody);
}