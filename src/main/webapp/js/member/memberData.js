var memberId = "";

//资金流水
var moneyFlowPageNo = 1;
var moneyFlowPageSize = 5;
var moneyFlowTotalPage = 0;

var businessTypeArray =new Array("","消费","充值","转账","开卡");
var flowTypeArray =new Array("","-","+");

jQuery(document).ready(function(){
    jQuery(".project-toggle").on("click", function(){
        var thisElement = jQuery(this);
        var thisElementParent = thisElement.parents("tr")
        var projectPart = thisElementParent.nextUntil(".single");
        if(!projectPart.is(":visible")){
            projectPart.show();
        }else{
            projectPart.hide();
        }
    });
});

jQuery("div[name = 'memberTR']").delegate("span[name='breakName']","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	jQuery(obj).parents("div[name='memberTR']").find("input[name='memberId']").val("").change();
	jQuery(obj).parents("div[name='memberTR']").find("div[name='resultTD']").css("display","none");
    jQuery(obj).parents("div[name='memberTR']").find("div[name='seekTD']").css("display", "inline-block");
});
/*
//通过手机号码查询会员
jQuery("div[name = 'memberTR']").delegate("span[name='seekName']","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
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
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var memberBaseDto = e.msg;
			jQuery(obj).parents("div[name='memberTR']").find(".can-click").text(memberBaseDto.name+"  "+memberBaseDto.sex+"  "+memberBaseDto.phone);
			jQuery(obj).parents("div[name='memberTR']").find("span[name='balance']").text("余额："+memberBaseDto.balanceAmount);
			jQuery(obj).parents("div[name='memberTR']").find("input[name='memberId']").val(memberBaseDto.memberId).change();
			
		    jQuery(obj).parents("div[name='memberTR']").find("div[name='resultTD']").css("display", "inline-block");
		    jQuery(obj).parents("div[name='memberTR']").find("div[name='seekTD']").css("display","none");
		}
	});
});*/

//查询会员信息
jQuery("body").delegate(".can-click","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	jQuery("#tabsLI-1").click();
	
	memberId = jQuery(this).attr("id");
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectByMemberDto",
		data : "memberId="+memberId,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var memberDto = e.msg;
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
			jQuery("td[name='problem']").empty();
			jQuery("td[name='problem']").append(memberDto.memberAccount.problem+"<span class='fr gray cursor' onclick='answerFunction()'>查看答案</span>");
			jQuery("td[name='answer']").empty();
			jQuery("td[name='answer']").append(memberDto.memberAccount.answer+"<span class='fr gray cursor' onclick='problemFunction()'>查看问题</span>");
		}
	});
});

function answerFunction(){
	jQuery("td[name='problem']").addClass("hide");
	jQuery("td[name='answer']").removeClass("hide");
}

function problemFunction(){
	jQuery("td[name='answer']").addClass("hide");
	jQuery("td[name='problem']").removeClass("hide");
}

jQuery("#tabsLI-5").click(function(){
	moneyFlowPage();
});

function moneyFlowPage(){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMoneyFlow",
		data : "memberId="+memberId+"&pageNo="+moneyFlowPageNo+"&pageSize="+moneyFlowPageSize,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var page = e.msg;
			
			moneyFlowTotalPage = page.totalPage;
			if(moneyFlowTotalPage == 1){
				
			}
			var results = page.results;
			jQuery("#moneyFlowTBODY").empty();
			for (var i = 0; i < results.length; i++) {
				var moneyFlowDto = results[i];
				var a = i + 1;
				jQuery("#moneyFlowTBODY").append("<tr class='single'>"+
						                            "<td>"+a+"</td>"+
					                                "<td>"+businessTypeArray[moneyFlowDto.businessType]+"</td>"+
					                                "<td class='can-click'>"+moneyFlowDto.orderName+"</td>"+
					                                "<td class='blue'>"+moneyFlowDto.balanceAmount+"</td>"+
					                                "<td class='blue'>"+flowTypeArray[moneyFlowDto.flowType] + moneyFlowDto.flowAmount+"</td>"+
					                                "<td>"+moneyFlowDto.flowTime+"</td>"+
					                                "<td>"+moneyFlowDto.lastOperatorName+"</td>"+
					                                "<td>"+moneyFlowDto.storeName+"</td>"+
					                            "</tr>");
			}
		}
	});
}