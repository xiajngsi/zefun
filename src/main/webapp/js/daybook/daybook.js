var isAssignArray =new Array("未指定", "指定", "");
var isAppointArray =new Array("未预约","预约", "");

jQuery(function(){
		if(pageNo == totalPage){
			jQuery("#previousPageButton").attr("disabled",true);
			jQuery("#nextPageButton").attr("disabled",true); 
			jQuery("#previousPageButton").addClass("page-disable");
			jQuery("#nextPageButton").addClass("page-disable");
		}
	    if(pageNo == 1){
	    	jQuery("#previousPageButton").attr("disabled",true);
	    	jQuery("#previousPageButton").addClass("page-disable");
		}
});

var curDate = getCurDate();
jQuery('#startDate').datetimepicker({value : curDate + ' 00:00'});
jQuery('#endDate').datetimepicker({value : curDate + ' 23:00'});

var directType = 1;
var payType = null;
//查询类型 1：时间 2：订单
var selectType = 1;

//上一页
jQuery("#previousPageButton").click(function(){
	if(pageNo <= 1){
		return;
	}
	pageNo--;
	changePage();
});

//下一页
jQuery("#nextPageButton").click(function(){
	if(pageNo >= totalPage){
		return;
	}
	pageNo++;
	changePage();
});

//更改每页显示数量
function changePageSize(size){
	pageNo = 1;
	pageSize = size;
	jQuery(".perpage").html(size + "<span class='iconfa-caret-down afont'></span>");
	if (selectType == 2) {
		btnSearchPhone();
	}
	else {
		btnSearchTime();
	}
}

function btnSearchPhone() {
	var queryCode = jQuery("#ipt-search-phone").val();
	var beginTime = '';
	var endTime = '';
	changePage(queryCode, beginTime, endTime);
	selectType = 2;
}

function btnSearchTime () {
	var queryCode = '';
	var beginTimes = jQuery("#startDate").val();
	var endTimes = jQuery("#endDate").val();
	var d1 = new Date(beginTimes);
	var d2 = new Date(endTimes);
	if (d1 > d2) {
		dialog("开始时间不能大于结束时间");
		return;
	}
	beginTime = beginTimes.replace(/\//g,"-");
	endTime = endTimes.replace(/\//g,"-");
	
	changePage(queryCode, beginTime, endTime);
	selectType = 1;
}



//分页处理
function changePage(queryCode, beginTime, endTime){
	var $deptSelect = jQuery("#dept-select");
	var deptSelect = null;
	if($deptSelect != null) {
		deptSelect = $deptSelect.val();
		if(deptSelect != null && deptSelect.length >= 1) {
			deptSelect = deptSelect.join(",");
		}
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/action/list",
		data : {"queryCode":queryCode, "pageNo":pageNo, "pageSize":pageSize, "beginTime":beginTime,
			"endTime":endTime, "deptIdStr":deptSelect, "direct":directType, "payType":payType},
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var obj = e.msg;
			jQuery("#searchDate").html(obj.searchDate);
			jQuery("#statStr").html(obj.statStr);
			refreshTableData(obj.pageInfo);
			refreshStatData(obj.pageInfo.totalRecord, obj);
			checkPageButton();
		}
	});
}

function refreshTableData(page){
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	var $tbody = jQuery("#tbody-data");
	$tbody.empty();
	var daybookDatas = page.results;
	for (var i = 0; i < daybookDatas.length; i++) {
		$tbody.append(spellTableData(daybookDatas[i]));
	}
}

function refreshStatData(totalCount, obj) {
	var statInfo = obj.countBook;
	jQuery("#span-totalcount").text(totalCount);
	jQuery("#searchDate").text(obj.searchDate);
	
	jQuery("#receivableAmount").text(statInfo.receivableAmount);
	jQuery("#giftAmount").text(statInfo.giftAmount);
	jQuery("#couponAmount").text(statInfo.couponAmount);
	jQuery("#comboAmount").text(statInfo.comboAmount);
	jQuery("#realPrice").text(statInfo.realPrice);
	
	jQuery("#cashAmount").text(statInfo.cashAmount);
	jQuery("#unionpayAmount").text(statInfo.unionpayAmount);
	jQuery("#wechatAmount").text(statInfo.wechatAmount);
	jQuery("#alipayAmount").text(statInfo.alipayAmount);
	jQuery("#cardAmount").text(statInfo.cardAmount);
	
	jQuery("#projectSalesAmount").text(obj.projectSalesAmount);
	jQuery("#goodsSalesAmount").text(obj.goodsSalesAmount);
	jQuery("#comboSalesAmount").text(obj.comboSalesAmount);
	jQuery("#cardSalesAmount").text(obj.cardSalesAmount);
}

function spellTableData(data) {
	var str = '"<tr><td data-toggle="modal" data-target="#liushui-search" onclick="updateSelectOrder('+ data.orderId +')"><a class="can-click">'+ data.orderCode +'</a></td>';
	if(data.memberId == null || data.memberId == '') {
		str += '<td>散（' + data.sex + '）</td>';
	} else {
		str += '<td class="can-click" data-toggle="modal" data-target="#member-data" onclick="selectMemberInfo('+data.memberId+')">' + data.memberName + '</td>'
	}
	var projectName = data.projectName ;
	if (isEmpty(projectName) || projectName == 'null') {
		projectName = "充值开卡";
	}
	str += '<td>' + data.createTime + '</td>'
		+ '<td class="wthn100 ellipsis-text" data-toggle="tooltip" data-placement="right" title="' + projectName + '">'
	 	+ projectName + '</td>'
	 	+ '<td>' + data.realPrice + '</td>'
	 	+ '<td>' + data.cashAmount + '</td>'
	    + '<td>' + data.unionpayAmount + '</td>'
	    + '<td>';
	if(data.wechatAmount == 0 && data.alipayAmount == 0) {
		str += 0;
	} else if (data.wechatAmount > data.alipayAmount) {
		var waAmount = data.wechatAmount + data.alipayAmount;
		str += '<i class="iconfont icon-weixin fl"></i><span class="fl ml10">' + waAmount + '</span>';
	} else {
		var waAmount = data.wechatAmount + data.alipayAmount;
		str += '<i class="iconfont icon-zhifubao fl"></i><span class="fl ml10">' + waAmount + '</span>';
	}
	str += '</td>'
		+ '<td>' + data.cardAmount + '</td>'
		+ '<td>' + data.comboAmount + '</td>'
		+ '<td>' + data.giftAmount + '</td>'
		+ '<td>' + data.couponAmount + '</td>'
//		+ '<td>' + data.debtAmount + '</td>'
//		+ '<td>' + data.freeAmount + '</td>'
		+ '<td>' + data.realPay + '</td>'
		+ '<td><span class="iconfa-trash project-icon" onclick="deleteOrder('+ data.orderId + ', this)"></span></td>'
		+ '</tr>';
	return str;
}

function checkPageButton(){
	//处理上一页
	if(pageNo <= 1){
		jQuery("#previousPageButton").attr("disabled",true);  
		jQuery("#previousPageButton").addClass("page-disable");
	} else {
		jQuery("#previousPageButton").attr("disabled",false);  
		jQuery("#previousPageButton").removeClass("page-disable");
	}
	
	//处理下一页
	if(pageNo >= totalPage){
		jQuery("#nextPageButton").attr("disabled",true); 
		jQuery("#nextPageButton").addClass("page-disable");
	} else {
		jQuery("#nextPageButton").attr("disabled",false);  
		jQuery("#nextPageButton").removeClass("page-disable");
	}
}

function changeQueryDirect(direct) {
	if(directType == direct) {
		return ;
	}
	directType = direct;
	changePage();
}

function changePayType(type) {
	if(payType == type) {
		return;
	} else if(type == 0) {
		payType = null;
	} else {
		payType = type;
	}
	changePage();
}

var updateOrderId = "";

var commissionArray = new Array();

function updateSelectOrder(orderId) {
	jQuery("#liushui-search").removeClass("hide");
	updateOrderId = orderId;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/selectOrderByUpdate",
		data : "orderId="+orderId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var obj = e.msg;
			jQuery("#orderCodeModel").text("流水单号：" + obj.orderCode);
			jQuery("#discountAmountModel").text(obj.discountAmount);
			jQuery("input[name='cashAmountModel']").val(obj.cashAmount);
			jQuery("input[name='unionpayAmountModel']").val(obj.unionpayAmount);
			jQuery("input[name='wechatAmountModel']").val(obj.wechatAmount);
			jQuery("input[name='alipayAmountModel']").val(obj.alipayAmount);
			jQuery("input[name='cardAmountModel']").val(obj.cardAmount);
			jQuery("input[name='debtAmountModel']").val(obj.debtAmount);
			jQuery("#realAmountModel").text(obj.realAmount);
			var privilege = parseFloat(obj.appointOff) + parseFloat(obj.comboAmount) + parseFloat(obj.couponAmount) + parseFloat(obj.giftAmount);
			jQuery("#privilegeModel").text(privilege);
			
			jQuery("#detailExpense").empty();
			jQuery("#detailExpense").append("<div class='ls-title'>"+
                                            "</div>");
			var orderDetailList = obj.orderDetailList;
			for (var i = 0; i < orderDetailList.length; i++) {
				var orderDetailDto = orderDetailList[i];
				var tables = jQuery("<table class='table xfdetail' orderDetail = '"+orderDetailDto.orderDetail+"'></table>");
				var oldDiscountAmount =  parseFloat(orderDetailDto.discountAmount) + parseFloat(obj.freeAmount);
				var proStr = "<thead>"+
					            "<tr>"+
					                "<td>"+orderDetailDto.projectName+"</td>"+
					                "<td colspan='2' name = ''>折扣价格：¥"+oldDiscountAmount+"</td>";
				if (orderDetailDto.privilegeMoney <= 0) {
					proStr += "<td></td>";
				} else {
					proStr += "<td>"+orderDetailDto.privilegeInfo+" -"+orderDetailDto.privilegeMoney+"</td>"
				}
				if (orderDetailDto.freeAmount > 0) {
					proStr += "<td>改单金额: "+orderDetailDto.freeAmount+" </td>"+
                    "<td colspan='2'>改单备注: "+orderDetailDto.orderRemark+"</td>";
				} else {
					proStr += "<td></td><td colspan='2'></td>";
				}
				
				proStr += "</tr>"+
					          "</thead>";
				tables.append(proStr);
				var stepList = orderDetailDto.stepList;
				var tbodys = jQuery("<tbody></tbody>");
				for (var j = 0; j < stepList.length; j++) {
					var orderDetailStepDto = stepList[j];
					var tbodysStr = "<tr commissionId = '"+orderDetailStepDto.commissionId+"'>"+
						                "<td>"+orderDetailStepDto.projectStepName+"</td>"+
						                "<td>"+orderDetailStepDto.shiftMahjongName+"</td>";
					if (isEmpty(orderDetailStepDto.employeeInfo) || orderDetailStepDto.employeeInfo == "null") {
						tbodysStr = tbodysStr + "<td>未设置人员</td>";
					}
					else {
						tbodysStr = tbodysStr + "<td>"+orderDetailStepDto.employeeInfo.employeeCode+" "+orderDetailStepDto.employeeInfo.name+"</td>";
					}
					tbodysStr = tbodysStr + "<td>"+isAssignArray[orderDetailStepDto.isAssign]+"</td>"+
							                "<td>"+isAppointArray[orderDetailStepDto.isAppoint]+"</td>"+
							                "<td>提成： <input class='w85' type='text' name = 'commissionAmount' value = '"+orderDetailStepDto.commissionAmount+"' placeholder='0'/></td>"+
							                "<td>业绩： <input class='w85' type='text' name = 'commissionCalculate' value = '"+orderDetailStepDto.commissionCalculate+"' placeholder='0'/></td>"+
							            "</tr>";
					tbodys.append(tbodysStr);
					var commissionObj = {"commissionId": orderDetailStepDto.commissionId,"commissionAmount":orderDetailStepDto.commissionAmount,"commissionCalculate":orderDetailStepDto.commissionCalculate};
	                commissionArray.push(commissionObj);
				}
				tables.append(tbodys);
				jQuery("#detailExpense").append(tables);
			}
		}
	});
}

function checkNum(obj) {  
    //检查是否是非数字值  
    if (isNaN(obj.value)) {  
        obj.value = "";  
    }   
}

//取消model
function cancelModel() {
	jQuery("#liushui-search").addClass("hide");
}

//确定model
function confirmModel() {
	var cashAmount = jQuery("input[name='cashAmountModel']").val();
	var unionpayAmount = jQuery("input[name='unionpayAmountModel']").val();
	var wechatAmount = jQuery("input[name='wechatAmountModel']").val();
	var alipayAmount = jQuery("input[name='alipayAmountModel']").val();
	var cardAmount = jQuery("input[name='cardAmountModel']").val();
	var debtAmount = jQuery("input[name='debtAmountModel']").val();
	if (isEmpty(cashAmount)) {
		cashAmount = 0;
	}
	if (isEmpty(unionpayAmount)) {
		unionpayAmount = 0;
	}
	if (isEmpty(alipayAmount)) {
		alipayAmount = 0;
	}
	if (isEmpty(wechatAmount)) {
		wechatAmount = 0;
	}
	if (isEmpty(cardAmount)) {
		cardAmount = 0;
	}
	if (isEmpty(debtAmount)) {
		debtAmount = 0;
	}
	var realAmount = jQuery("#realAmountModel").text();
	var totailMoney = parseFloat(cashAmount) + parseFloat(unionpayAmount) + parseFloat(wechatAmount) 
	+ parseFloat(alipayAmount) + parseFloat(cardAmount) + parseFloat(debtAmount);
	
	if (parseFloat(realAmount) != totailMoney) {
		dialog("支付金额与实收不相等，请重新填写！");
		return;
	}
	
	var commissionAmountInput = jQuery("input[name='commissionAmount']");
	var commissionCalculateInput = jQuery("input[name='commissionCalculate']");
	
	var newCommissionArray = new Array();
	
	for (var i = 0; i < commissionAmountInput.length; i++) {
		var commissionAmount = jQuery(commissionAmountInput[i]).val();
		var commissionCalculate = jQuery(commissionCalculateInput[i]).val();
		var commissionId = jQuery(commissionAmountInput[i]).parent().parent().attr("commissionId");
		
		for (var j = 0; j < commissionArray.length; j++) {
			if (commissionArray[j].commissionId == commissionId) {
				if (commissionArray[j].commissionAmount != commissionAmount || commissionArray[j].commissionCalculate != commissionCalculate) {
					var newObj = {"commissionId": commissionId, "commissionAmount":commissionAmount, "commissionCalculate":commissionCalculate};
					newCommissionArray.push(newObj);
				}
			}
		}
	}
	
	var commissionArrayStr = JSON.stringify(newCommissionArray);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/orderByUpdate",
		data : {"cashAmount":cashAmount, "unionpayAmount":unionpayAmount, "wechatAmount":wechatAmount, "alipayAmount":alipayAmount,
			"cardAmount":cardAmount, "debtAmount":debtAmount, "realAmount":realAmount, "orderId":updateOrderId, "commissionArrayStr":commissionArrayStr},
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("保存成功！");
			jQuery("#cancelModel").click();
		}
	});
}

var memberId = null;

function deleteOrder (orderId, obj) {
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/elementDeleteOrderId",
		data : "orderId="+orderId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				if (e.code == 888) {
					var date = e.msg;
					var memberLevelList = date.memberLevelList;
					
					memberId = date.memberId;
					
					var levelId = date.levelId;
					
					jQuery("#topTR").siblings().remove();
					
					var trVar = null;
					for (var i = 0; i < memberLevelList.length; i++) {
						if (i % 2 == 0) {
							trVar = jQuery("<tr></tr>");
							var element = packageElement(memberLevelList[i].levelId, memberLevelList[i].levelName, levelId);
							trVar.append(element);
							if (i + 1 == memberLevelList.length) {
								trVar.append("<td></td>")
								jQuery("#modelTbody").append(trVar);
							}
						}
						else {
							var element = packageElement(memberLevelList[i].levelId, memberLevelList[i].levelName, levelId);
							trVar.append(element);
							jQuery("#modelTbody").append(trVar);
						}
					}
					dialog("删除成功！");
					jQuery("#member-level-modal").modal("show");
					jQuery(obj).parents("tr").remove();
					return;
				}
				dialog(e.msg);
				return;
			}
			dialog("删除成功！");
			jQuery(obj).parents("tr").remove();
		}
	});
}

function packageElement(levelId, levelName, oldLevelId) {
	var str =  "<td >"+
				    "<div class='ch-checker fl'>";
	if (oldLevelId == levelId) {
		str = str + "<div class='beau-checker active'>"+
			           "<span class='iconfont icon-gou'></span>"+
			        "</div>" +
		            "<input type='radio' class='yellow-checker' name='levelId' checked='checked' value='"+ levelId +"'>";
	}
	else {
		str = str + "<div class='beau-checker'>"+
			           "<span class='iconfont icon-gou'></span>"+
			        "</div>" +
			        "<input type='radio' class='yellow-checker' name='levelId' value='"+ levelId +"'>";
	}    
	str = str + "</div>"+
				"<span class='ml30'>"+ levelName +"</span>"+
		     "</td>";
	return str;
}

function updateMemberLevel() {
	var levelId = jQuery("input[name='levelId']:checked").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/changeLevelId",
		data : "memberId=" + memberId + "&levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("修改会员等级成功！");
			jQuery("#member-level-modal").modal("hide");
		}
	});
}