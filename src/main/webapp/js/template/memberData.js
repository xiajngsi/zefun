var memberDataId = "";

var memberDatePageNo = {"moneyFlowPageNo":1, "orderComboPageNo":1, "integralFlowPageNo":1, "orderProjectPageNo":1, "orderGoodsPageNo":1};

var memberDatePageSize = {"moneyFlowPageSize":5, "orderComboPageSize":5, "integralFlowPageSize":5, "orderProjectPageSize":5, "orderGoodsPageSize":5};

var memberDateTotalPage = {"moneyFlowTotalPage":0, "orderComboTotalPage":0, "integralFlowTotalPage":0, "orderProjectTotalPage":0, "orderGoodsTotalPage":0};

var businessTypeArray =new Array("","消费","充值","转账","开卡","升级", "卡金");

var flowTypeArray =new Array("","-","+");

jQuery("#orderComboTBODY").delegate(".project-toggle", "click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
    var thisElement = jQuery(obj);
    var thisElementParent = thisElement.parents("tr")
    var projectPart = thisElementParent.nextUntil(".single");
    if(!projectPart.is(":visible")){
        projectPart.show();
    }else{
        projectPart.hide();
    }
});

jQuery("div[name = 'memberTR']").delegate("span[name='breakName']","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	jQuery(obj).parents("div[name='memberTR']").find("input[name='memberId']").val("").change();
	jQuery(obj).parents("div[name='memberTR']").find("div[name='resultTD']").css("display","none");
    jQuery(obj).parents("div[name='memberTR']").find("div[name='seekTD']").css("display", "inline-block");
});

jQuery(document).keyup(function(event){ 
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	if (jQuery(obj).attr("name") == "phoneNumber") {
		//输入11位自动提交
		if (jQuery(obj).val().length == 11) {
			submitPhone (obj);
		}
	}
}); 


//通过手机号码查询会员
jQuery("div[name = 'memberTR']").delegate("span[name='seekName']","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	submitPhone (obj);
});

function submitPhone (obj) {
	var phoneNumber = jQuery(obj).parents("div[name='memberTR']").find("input[name='phoneNumber']").val();
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if (!reg.test(phoneNumber)) {
		dialog("号码格式有误~");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMemberByPhone",
		data : "phone="+phoneNumber,
		//async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var memberBaseDto = e.msg;
			jQuery(obj).parents("div[name='memberTR']").find(".can-click").text(memberBaseDto.name+"  "+memberBaseDto.sex+"  "+memberBaseDto.phone);
			jQuery(obj).parents("div[name='memberTR']").find("span[name='levelName']").text("会员等级："+memberBaseDto.levelName);
			jQuery(obj).parents("div[name='memberTR']").find("span[name='balance']").text("余额："+memberBaseDto.balanceAmount);
			jQuery(obj).parents("div[name='memberTR']").find("input[name='memberId']").val(memberBaseDto.memberId).change();
			jQuery(obj).parents("div[name='memberTR']").find("input[name='levelId']").val(memberBaseDto.levelId);
			
			
		    jQuery(obj).parents("div[name='memberTR']").find("div[name='resultTD']").css("display", "inline-block");
		    jQuery(obj).parents("div[name='memberTR']").find("div[name='seekTD']").css("display","none");
		}
	});
}

//查询会员信息
jQuery("div[name = 'memberTR']").delegate(".can-click","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	jQuery("#tabsLI-1").click();
	
	memberDataId = jQuery(obj).parents("div[name='memberTR']").find("input[name='memberId']").val();
	
	selectMemberInfo(memberDataId);
});

function selectMemberInfo(memberId) {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectByMemberDto",
		data : "memberId="+memberId,
		//async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var dto = e.msg;
			memberDataId = memberId;
			var memberDto = dto.memberDto;
			jQuery("span[name='memberBase']").text(memberDto.name +" "+memberDto.sex);
			jQuery("td[name='phone']").text(memberDto.phone);
			jQuery("td[name='totalConsumeAmount']").text(memberDto.memberAccount.totalConsumeAmount);
			jQuery("td[name='levelName']").text(memberDto.memberLevel.levelName);
			jQuery("td[name='totalAmount']").text(memberDto.memberAccount.totalAmount);
			jQuery("td[name='birthday']").text(memberDto.birthday);
			jQuery("td[name='totalIntegral']").text(memberDto.memberAccount.totalIntegral);
			jQuery("td[name='balanceAmount']").text(memberDto.memberAccount.balanceAmount);
			jQuery("td[name='avgConsumeAmount']").text(memberDto.memberAccount.avgConsumeAmount);
			jQuery("td[name='balanceIntegral']").text(memberDto.memberAccount.balanceIntegral);
			jQuery("td[name='avgConsumeDays']").text(memberDto.memberAccount.avgConsumeDays+"天/次");
			jQuery("td[name='storeName']").text(memberDto.storeInfo.storeName);
			jQuery("td[name='lastConsumeTime']").text(memberDto.memberAccount.lastConsumeTime);
//			jQuery("td[name='problem']").empty();
//			jQuery("td[name='problem']").append(memberDto.memberAccount.problem+"<span class='fr gray cursor' onclick='answerFunction()'>查看答案</span>");
//			jQuery("td[name='answer']").empty();
//			jQuery("td[name='answer']").append(memberDto.memberAccount.answer+"<span class='fr gray cursor' onclick='problemFunction()'>查看问题</span>");
			moneyFlow(dto.pageMoneyFlowDto);
			orderProject(dto.pageOrderProjectPageDto);
			orderGoods(dto.pageOrderGoodsPageDto);
			orderCombo(dto.pageOrderComboPageDto);
			integralFlow(dto.pageIntegralFlowDto);
		}
	});
}

function answerFunction(){
	jQuery("td[name='problem']").addClass("hide");
	jQuery("td[name='answer']").removeClass("hide");
}

function problemFunction(){
	jQuery("td[name='answer']").addClass("hide");
	jQuery("td[name='problem']").removeClass("hide");
}


function page(moneyFlowPageNo, moneyFlowPageSize, type){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMoneyFlow",
		data : "memberId="+memberDataId+"&pageNo="+moneyFlowPageNo+"&pageSize="+moneyFlowPageSize+"&type="+type,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var page = e.msg;
			if (type == 4) {
				moneyFlow(page);
			}
			//套餐
	        else if (type == 2) {
	        	orderCombo(page);
	        }
	        //积分流水
	        else if (type == 5) {
	        	integralFlow(page);
	        }
	        //项目
	        else if (type == 1) {
	        	orderProject(page);
	        }
	        //商品
	        else if (type == 3) {
	        	orderGoods(page);
	        }
		}
	});
}

function moneyFlow(page) {
	memberDateTotalPage.moneyFlowTotalPage = page.totalPage;
	addDisable("previousMoneyFlow");
	if(memberDateTotalPage.moneyFlowTotalPage == 1){
		addDisable("nextMoneyFlow");
	}
	var results = page.results;
	jQuery("#moneyFlowTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var moneyFlowDto = results[i];
		var a = i + 1;
		jQuery("#moneyFlowTBODY").append("<tr class='single'>"+
				                            "<td>"+a+"</td>"+
			                                "<td>"+businessTypeArray[moneyFlowDto.businessType]+"</td>"+
			                               /* "<td class='can-click'>"+moneyFlowDto.orderName+"</td>"+*/
			                                /*"<td class='blue'>"+moneyFlowDto.balanceAmount+"</td>"+*/
			                                "<td class='blue'>"+flowTypeArray[moneyFlowDto.flowType] + moneyFlowDto.flowAmount+"</td>"+
			                                "<td>"+moneyFlowDto.flowTime+"</td>"+
			                                "<td>"+moneyFlowDto.lastOperatorName+"</td>"+
			                                "<td>"+moneyFlowDto.storeName+"</td>"+
			                            "</tr>");
	}
}

function orderProject(page) {
	memberDateTotalPage.orderProjectTotalPage = page.totalPage;
	addDisable("previousOrderProject");
	if(memberDateTotalPage.orderProjectTotalPage == 1){
		addDisable("nextOrderProject");
	}
	var results = page.results;
	jQuery("#orderProjectTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderProjectDto = results[i];
		jQuery("#orderProjectTBODY").append("<tr class='single'>"+
					                              "<td>"+orderProjectDto.orderCode+"</td>"+
					                              "<td>"+orderProjectDto.projectName+"</td>"+
					                              "<td class='blue'>"+orderProjectDto.discountAmount+"</td>"+
					                              "<td><span class='cursor'>"+orderProjectDto.privilegeInfo+" </span><span class='red'>-"+orderProjectDto.privilegeMoney+"</span></td>"+
					                              "<td class='red'>"+orderProjectDto.realPrice+"</td>"+
					                              "<td>"+orderProjectDto.createTime+"</td>"+
					                              "<td>"+orderProjectDto.storeName+"</td>"+
					                         "</tr>");
	}
}

function integralFlow(page) {
	memberDateTotalPage.integralFlowTotalPage = page.totalPage;
	addDisable("previousIntegralFlow");
	if(memberDateTotalPage.integralFlowTotalPage == 1){
		addDisable("nextIntegralFlow");
	}
	var results = page.results;
	jQuery("#integralFlowTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var integralFlow = results[i];
		jQuery("#integralFlowTBODY").append("<tr class='single'>"+
				                                  "<td>"+integralFlow.businessType+"</td>"+
				                                  "<td class='red'>"+flowTypeArray[integralFlow.flowType] + integralFlow.flowAmount +"</td>"+
				                                  "<td>"+integralFlow.businessDesc+"</td>"+
				                                  "<td>"+integralFlow.flowTime+"</td>"+
				                              "</tr>");
	}
}

function orderGoods(page) {
	memberDateTotalPage.orderGoodsTotalPage = page.totalPage;
	addDisable("previousOrderGoods");
	if(memberDateTotalPage.orderGoodsTotalPage == 1){
		addDisable("nextOrderGoods");
	}
	var results = page.results;
	jQuery("#orderGoodsTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderGoodsDto = results[i];
		jQuery("#orderGoodsTBODY").append("<tr class='single'>"+
				                              "<td>"+orderGoodsDto.orderCode+"</td>"+
				                              "<td>"+orderGoodsDto.projectName+"</td>"+
				                              "<td>"+orderGoodsDto.lastOperatorName+"</td>"+
				                              "<td class='blue'>"+orderGoodsDto.discountAmount+"</td>"+
				                              "<td><span class='cursor'>"+orderGoodsDto.privilegeInfo+" </span><span class='red'>-"+orderGoodsDto.privilegeMoney+"</span></td>"+
				                              "<td class='red'>"+orderGoodsDto.realPrice+"</td>"+
				                              "<td>"+orderGoodsDto.createTime+"</td>"+
				                              "<td>"+orderGoodsDto.storeName+"</td>"+
				                             "</tr>");
				}
}

function orderCombo(page) {
	memberDateTotalPage.orderComboTotalPage = page.totalPage;
	addDisable("previousOrderCombo");
	if(memberDateTotalPage.orderComboTotalPage == 1){
		addDisable("nextOrderCombo");
	}
	var results = page.results;
	jQuery("#orderComboTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderComboDto = results[i];
		jQuery("#orderComboTBODY").append("<tr class='single'>"+
				                              "<td class='cursor mr10 project-toggle'>"+orderComboDto.comboName+"<span class='icon-chevron-down'></span></td>"+
				                              "<td>"+orderComboDto.projectCount+"</td>"+
				                              "<td class='red'>"+orderComboDto.remainingCount+"</td>"+
				                              "<td class='blue'>"+orderComboDto.projectAmount+"</td>"+
				                              "<td class='red'>"+orderComboDto.comboPrice+"</td>"+
				                              "<td>"+orderComboDto.lastOperatorName+"</td>"+
				                              "<td>"+orderComboDto.createTime+"</td>"+
				                              "<th>"+orderComboDto.overdueTime+"</th>"+
				                              "<td>"+orderComboDto.storeName+"</td>"+
				                          "</tr>");
		var projectList = orderComboDto.projectList;
		for (var j = 0; j < projectList.length; j++) {
			var memberComboProject = projectList[j];
			jQuery("#orderComboTBODY").append("<tr class='project-part hide'>"+
				                                  "<td>"+memberComboProject.projectName+"</td>"+
				                                  "<td>"+memberComboProject.projectCount+"</td>"+
				                                  "<td class='red'>"+memberComboProject.remainingCount+"</td>"+
				                                  "<td class='blue'>"+memberComboProject.projectPrice+"</td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                              "</tr>");
		}
	}
}

//上一页
function previousPageButton(type){
	if (type == 4) {
		if(memberDatePageNo.moneyFlowPageNo == 1){
			return;
		}
		memberDatePageNo.moneyFlowPageNo = memberDatePageNo.moneyFlowPageNo - 1;
		page(memberDatePageNo.moneyFlowPageNo, memberDatePageSize.moneyFlowPageSize, 4);
		
		if(memberDatePageNo.moneyFlowPageNo == 1){
			addDisable("previousMoneyFlow");
		}
		else {
			removeDisable("nextMoneyFlow");
			removeDisable("previousMoneyFlow");
		}
	}
	//套餐
    else if (type == 2) {
    	if(memberDatePageNo.orderComboPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderComboPageNo = memberDatePageNo.orderComboPageNo - 1;
		page(memberDatePageNo.orderComboPageNo, memberDatePageSize.orderComboPageSize, 2);
		
		if(memberDatePageNo.orderComboPageNo == 1){
			addDisable("previousOrderCombo");
		}
		else {
			removeDisable("nextOrderCombo");
			removeDisable("previousOrderCombo");
		}
    }
    //积分流水
    else if (type == 5) {
    	if(memberDatePageNo.integralFlowPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.integralFlowPageNo = memberDatePageNo.integralFlowPageNo - 1;
		page(memberDatePageNo.integralFlowPageNo, memberDatePageSize.integralFlowPageSize, 5);
		
		if(memberDatePageNo.integralFlowPageNo == 1){
			addDisable("previousIntegralFlow");
		}
		else {
			removeDisable("nextIntegralFlow");
			removeDisable("previousIntegralFlow");
		}
    }
    //项目
    else if (type == 1) {
    	if(memberDatePageNo.orderProjectPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderProjectPageNo = memberDatePageNo.orderProjectPageNo - 1;
		page(memberDatePageNo.orderProjectPageNo, memberDatePageSize.orderProjectPageSize, 1);
		
		if(memberDatePageNo.orderProjectPageNo == 1){
			addDisable("previousOrderProject");
		}
		else {
			removeDisable("nextOrderProject");
			removeDisable("previousOrderProject");
		}
    }
    //商品
    else if (type == 3) {
    	if(memberDatePageNo.orderGoodsPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderGoodsPageNo = memberDatePageNo.orderGoodsPageNo - 1;
		page(memberDatePageNo.orderGoodsPageNo, memberDatePageSize.orderGoodsPageSize, 3);
		
		if(memberDatePageNo.orderGoodsPageNo == 1){
			addDisable("previousOrderGoods");
		}
		else {
			removeDisable("nextOrderGoods");
			removeDisable("previousOrderGoods");
		}
    }
}
//下一页
function nextPageButton (type){
	if (type == 4) {
		if(memberDatePageNo.moneyFlowPageNo == memberDateTotalPage.moneyFlowTotalPage){
			return;
		}
		memberDatePageNo.moneyFlowPageNo = memberDatePageNo.moneyFlowPageNo + 1;
		page(memberDatePageNo.moneyFlowPageNo, memberDatePageSize.moneyFlowPageSize, 4);
		
		if(memberDatePageNo.moneyFlowPageNo == memberDateTotalPage.moneyFlowTotalPage){
			addDisable("nextMoneyFlow");
		}
		else {
			removeDisable("nextMoneyFlow");
		}
		removeDisable("previousMoneyFlow");
	}
	//套餐
    else if (type == 2) {
    	if(memberDatePageNo.orderComboPageNo == memberDateTotalPage.orderComboTotalPage){
			return;
		}
    	memberDatePageNo.orderComboPageNo = memberDatePageNo.orderComboPageNo + 1;
		page(memberDatePageNo.orderComboPageNo, memberDatePageSize.orderComboPageSize, 2);
		
		if(memberDatePageNo.orderComboPageNo == memberDateTotalPage.orderComboTotalPage){
			addDisable("nextOrderCombo");
		}
		else {
			removeDisable("nextOrderCombo");
		}
		removeDisable("previousOrderCombo");
    }
    //积分流水
    else if (type == 5) {
    	if(memberDatePageNo.integralFlowPageNo == memberDateTotalPage.integralFlowTotalPage){
			return;
		}
    	memberDatePageNo.integralFlowPageNo = memberDatePageNo.integralFlowPageNo + 1;
		page(memberDatePageNo.integralFlowPageNo, memberDatePageSize.integralFlowPageSize, 5);
		
		if(memberDatePageNo.integralFlowPageNo == memberDateTotalPage.integralFlowTotalPage){
			addDisable("nextIntegralFlow");
		}
		else {
			removeDisable("nextIntegralFlow");
		}
		removeDisable("previousIntegralFlow");
    }
    //项目
    else if (type == 1) {
    	if(memberDatePageNo.orderProjectPageNo == memberDateTotalPage.orderProjectTotalPage){
			return;
		}
    	memberDatePageNo.orderProjectPageNo = memberDatePageNo.orderProjectPageNo + 1;
		page(memberDatePageNo.orderProjectPageNo, memberDatePageSize.orderProjectPageSize, 1);
		
		if(memberDatePageNo.orderProjectPageNo == memberDateTotalPage.orderProjectTotalPage){
			addDisable("nextOrderProject");
		}
		else {
			removeDisable("nextOrderProject");
		}
		removeDisable("previousOrderProject");
    }
    //商品
    else if (type == 3) {
    	if(memberDatePageNo.orderGoodsPageNo == memberDateTotalPage.orderGoodsTotalPage){
			return;
		}
    	memberDatePageNo.orderGoodsPageNo = pageNo.orderGoodsPageNo + 1;
		page(memberDatePageNo.orderGoodsPageNo, memberDatePageSize.orderGoodsPageSize, 3);
		
		if(memberDatePageNo.orderGoodsPageNo == memberDateTotalPage.orderGoodsTotalPage){
			addDisable("nextOrderGoods");
		}
		else {
			removeDisable("nextOrderGoods");
		}
		removeDisable("previousOrderGoods");
    }
}

function addDisable(name) {
	jQuery("#" + name).addClass("page-disable");
}

function removeDisable(name) {
	jQuery("#" + name).removeClass("page-disable");
}