//上一页
function previous(){
	if(pageNo <= 1){
		return;
	}
	pageNo--;
	changePage();
}

//下一页
function next(){
	if(pageNo >= totalPage){
		return;
	}
	pageNo++;
	changePage();
}

//更改每页显示数量
function changePageSize(size){
	pageNo = 1;
	pageSize = size;
	jQuery(".perpage").html(size + "<span class='caret'></span>");
	changePage();
}

//点击新增时的处理
function showAddView(){
	jQuery(".editMemberLevelForm")[0].reset();
	jQuery('#add-member-card').modal({show:true, backdrop:"static"});
}

//提交会员等级信息，存在等级标识则修改，不存在则新增
function addOrEditMemberLevel(){
	var data = jQuery(".editMemberLevelForm").serialize();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/add",
		data : data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("提交成功,即将刷新页面...");
			resetForm(".editMemberLevelForm");
			pageNo = 1;
			changePage();
		}
	});
}

function czupdateDeptInfoId(obj){
	var deptId = jQuery(obj).val();
	var deptName = jQuery(obj).find("option[value='"+deptId+"']").text();
	jQuery(obj).closest('td').append("<div class='p-part'>"+
				                        "<div class='select-result'>"+
				                            "<span name='czDeptId' value='"+deptId+"'>"+deptName+"：</span>"+
				                            "<input type='text'/><span class='percent-symbol'>元</span>"+
				                            "<span onclick='czdeleteDept(this)'>&nbsp;&nbsp;X</span>"+
				                        "</div>"+
				                   "</div>");
	jQuery(obj).find("option[value='"+deptId+"']").remove();
	jQuery(obj).trigger("liszt:updated");
}

function czdeleteDept(obj){
	var deptId = jQuery(obj).parents(".p-part").find("span[name='czDeptId']").attr("value");
	var deptName = jQuery.trim((jQuery(obj).parents(".p-part").find("span[name='czDeptId']").text()).replace("：",""));
	
	jQuery(obj).parents(".p-part").remove();
	var select = jQuery(obj).closest("td").find('select');
	
	select.append("<option value='"+deptId+"'>"+deptName+"</option>");
	select.trigger("liszt:updated");
}

//修改会员等级信息
function editMemberLevel(levelId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/info",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery(".editMemberLevelForm [type=reset]").trigger("click");
			dataToFormByName(e.msg);
		}
	});
}

//删除会员等级信息
function deleteMemberLevel(levelId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/delete",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery(".member-card-table tr[id='" + levelId + "']").fadeOut(500).remove();
		}
	});
}


//设置默认会员等级
function setDefaultLevel(levelId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/default",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("提交成功,即将刷新页面...");
			pageNo = 1;
			changePage();
		}
	});
}

//分页处理
function changePage(){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			refreshTableData(e.msg);
			checkPageButton();
		}
	});
}

//刷新表格数据
function refreshTableData(page){
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	
	var memberLevelList = page.results;
	var tbody = document.createElement("tbody");
	for (var i = 0; i < memberLevelList.length; i++) {
		var memberLevel = memberLevelList[i];
		
		var tr = document.createElement("tr");
		tr.setAttribute("id", memberLevel.levelId);
		
		var levelNameTd = document.createElement("td");
		levelNameTd.innerHTML = memberLevel.levelName;
		if (memberLevel.isDefault == 1) {
			levelNameTd.innerHTML = memberLevel.levelName + ' <span class="font-999">默认等级</span>';
		}
		tr.appendChild(levelNameTd);
		
		var projectDiscountTd = document.createElement("td");
		projectDiscountTd.innerHTML = memberLevel.projectDiscount + "%";
		tr.appendChild(projectDiscountTd);
		
		var goodsDiscountTd = document.createElement("td");
		goodsDiscountTd.innerHTML = memberLevel.goodsDiscount + "%";
		tr.appendChild(goodsDiscountTd);
		
		var getWayTd = document.createElement("td");
		var getWayContent = ""; 
		var isMultiple = false;
		if(memberLevel.sellAmount > 0){
			getWayContent = memberLevel.sellAmount + "元购买";
			isMultiple = true;
		}
		if(memberLevel.chargeAmount > 0){
			if(isMultiple){
				getWayContent += "&nbsp;或&nbsp;";
			}
			getWayContent += "充值" + memberLevel.chargeAmount + "元";
		}
		getWayTd.innerHTML = getWayContent;
		tr.appendChild(getWayTd);
		
		var chargeMinMoneyTd = document.createElement("td");
		chargeMinMoneyTd.innerHTML = memberLevel.chargeMinMoney + "元";
		tr.appendChild(chargeMinMoneyTd);
		
		var chargeBelonTypeTd = document.createElement("td");
		var chargeBelonTypeStr = "开卡门店";
		if(memberLevel.chargeBelongType == 2){
			chargeBelonTypeStr = "充值门店";
		}
		chargeBelonTypeTd.innerHTML = chargeBelonTypeStr;
		tr.appendChild(chargeBelonTypeTd);
		
//		var registerCommissionTd = document.createElement("td");
//		var registerCommissionContent = "";
//		if(memberLevel.registerCommissionType == 1){
//			registerCommissionContent = "按业绩比例 " + memberLevel.registerCommissionAmount + " %";
//		} else {
//			registerCommissionContent = "按固定金额 " + memberLevel.registerCommissionAmount + " 元";
//		}
//		registerCommissionTd.innerHTML = registerCommissionContent;
//		tr.appendChild(registerCommissionTd);
//		
//		var chargeCommissionTd = document.createElement("td");
//		var chargeCommissionContent = "";
//		if(memberLevel.chargeCommissionType == 1){
//			chargeCommissionContent = "按业绩比例 " + memberLevel.chargeCommissionAmount + " %";
//		} else {
//			chargeCommissionContent = "按固定金额 " + memberLevel.chargeCommissionAmount + " 元";
//		}
//		chargeCommissionTd.innerHTML = chargeCommissionContent;
//		tr.appendChild(chargeCommissionTd);
		
		var integralNumberTd = document.createElement("td");
		integralNumberTd.innerHTML = memberLevel.integralUnit + "元 = " + memberLevel.integralNumber + " 积分";
		tr.appendChild(integralNumberTd);
		
//		var integralUnitTd = document.createElement("td");
//		integralUnitTd.innerHTML = "累计满" + memberLevel.upgradeIntegral + "积分";
//		tr.appendChild(integralUnitTd);
        
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "iconfont icon-dizhixiugai");
		editSpan.setAttribute("onclick", "editMemberLevel(" + memberLevel.levelId + ")");
		operateTd.appendChild(editSpan);
		
		if (memberLevel.isDefault == 0) {
			var removeSpan = document.createElement("span");
			removeSpan.setAttribute("class", "iconfont icon-xx ml10");
			removeSpan.setAttribute("onclick", "deleteMemberLevel(" + memberLevel.levelId + ")");
			operateTd.appendChild(removeSpan);
		}
		
//		var defaultSpan = document.createElement("span");
//		if(memberLevel.isDefault == 0){
//			tr.setAttribute("class", "default-non");
//			defaultSpan.setAttribute("class", "cursor memberlevel-default-setting hide");
//			defaultSpan.setAttribute("onclick", "setDefaultLevel(" + memberLevel.levelId + ")");
//			defaultSpan.innerHTML = "设为默认";
//		} else {
//			defaultSpan.setAttribute("class", "memberlevel-default-show");
//			defaultSpan.innerHTML = "默认等级";
//		}
//		operateTd.appendChild(defaultSpan);
		
		tr.appendChild(operateTd);
		tbody.appendChild(tr);
	}
	jQuery(".member-card-table tbody").remove();
	jQuery(".member-card-table").append(tbody);
}

//检查当前页码是否可以进行上一页或者下一页，并对其翻页按钮进行对应的处理
function checkPageButton(){
	//处理上一页
	if(pageNo <= 1){
		jQuery("#previousPageButton").attr("disabled",true);  
	} else {
		jQuery("#previousPageButton").attr("disabled",false);  
	}
	
	//处理下一页
	if(pageNo >= totalPage){
		jQuery("#nextPageButton").attr("disabled",true);  
	} else {
		jQuery("#nextPageButton").attr("disabled",false);  
	}
}