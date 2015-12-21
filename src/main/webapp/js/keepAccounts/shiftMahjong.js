var  shiftMahjongId = "";

var stateList = new Array();
var a1 = {"state":"0","name":"工作中","styles":"gongzuozhong","imgs":baseUrl+"img/lunpai/cut.png"};
var a2 = {"state":"1","name":"空闲中","styles":"kongxianzhong","imgs":baseUrl+"img/lunpai/coffee.png"};
var a3 = {"state":"2","name":"暂时离开","styles":"zanshilikai","imgs":baseUrl+"img/lunpai/alarm.png"};
var a4 = {"state":"3","name":"离开","styles":"likai","imgs":baseUrl+"img/lunpai/clock.png"};
var a5 = {"state":"4","name":"指定服务","styles":"zhidingfuwu","imgs":baseUrl+"img/lunpai/heart.png"};

stateList[0] = a1;
stateList[1] = a2;
stateList[2] = a3;
stateList[3] = a4;
stateList[4] = a5;

//全局修改状态点击对象
var overallObj = "";

//全局修改状态轮牌员工标识
var shiftMahjongEmployeeId = "";

jQuery(document).ready(function(){
	
	renewalShift();
    
    updatePage();
});

jQuery(document).ready(function () {
	jQuery("*").click(function (event) {
		event = event ? event : window.event; 
		var obj = event.srcElement ? event.srcElement : event.target;
		var state = jQuery(obj).parents(".slider-foot").find(".zhuangtai-word").text();
		if (state == "工作中" || state == "指定服务") {
			return;
		}
        if (jQuery(obj).parents(".slider-foot").hasClass("slider-foot")){
        	var parentObj = jQuery(obj).parents(".slider-foot");
        	var left = jQuery(parentObj).offset().left + 10;
            var top = jQuery(parentObj).offset().top + 20;
            overallObj = jQuery(parentObj);
            shiftMahjongEmployeeId = jQuery(parentObj).attr("shiftMahjongEmployeeId");
            jQuery(".select-zhuangtai").removeClass("hide").css({"left":left, "top":top})
        }
        else {
        	jQuery(".select-zhuangtai").addClass("hide");
        }
        /*event.stopPropagation(); //阻止事件冒泡    
*/    });
});

function updateState(num){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "staff/action/updateState",
		data : "shiftMahjongEmployeeId="+shiftMahjongEmployeeId+"&state="+num,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
            var date = e.msg;
			var employeeObj = infoDIV(date.shiftMahjongId,date.shiftMahjongEmployeeList);
			var parents = jQuery(overallObj).parents(".s-slider-wrap");
			parents.empty();
			parents.append(employeeObj);
			renewalShift();
			jQuery(".select-zhuangtai").addClass("hide");
		}
	});
}

function renewalShift() {
	jQuery('.slider4').bxSlider({
        slideWidth: 108,
        minSlides: 1,
        maxSlides: 15,
        moveSlides: 1,
        slideMargin: 0,
        pager: false
    });
}

//初始化轮牌新增model
jQuery("#shiftModel").click(function(){
	shiftMahjongId = "";
	
	valuation("shiftMahjongRule", 1);
	valuation("shiftMahjongUp", 1);
	valuation("nature", 1);
	jQuery("input[name='shiftMahjongName']").val("");
	
	updatePosition();
});

function setShiftMahjong(shiftMahjongIdS,shiftMahjongName,nature,shiftMahjongUp,shiftMahjongRule){
	shiftMahjongId = shiftMahjongIdS;
	
	valuation("shiftMahjongRule", shiftMahjongRule);
	valuation("shiftMahjongUp", shiftMahjongUp);
	valuation("nature", nature);
    
	jQuery("input[name='shiftMahjongName']").val(shiftMahjongName);
	
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/initializeModel",
		data : "shiftMahjongId="+shiftMahjongId,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var data = e.msg;
			updatePosition();
			if(data.length > 0){
				var obj = jQuery("input[name='positionId']");
				for (var j = 0; j < obj.length; j++) {
					for(var i = 0; i < data.length;i++){
						if (jQuery(obj[j]).val() == data[i].positionId) {
							jQuery(obj[j]).parent().find(".beau-checker").addClass("active");
							jQuery(obj[j]).prop("checked", true);
							jQuery(obj[j]).parent().parent().next().find("select[name='upShiftType']").val(data[i].isPunchCard);
							jQuery(obj[j]).parent().parent().next().find("select[name='upShiftType']").trigger("liszt:updated");
						}
					}
				}
			}
		}
	});
}

//切换部门时更新model中岗位下拉
function updatePosition(){
	jQuery("tr[name = 'positionMessage']").remove();
	
	for (var i = 0; i < deptDtoList.length; i++) {
		if (deptId == deptDtoList[i].deptId) {
			var positionList = deptDtoList[i].positionInfo;
			var positionMessage = null;
			for (var j = 0; j < positionList.length; j++) {
				if (j % 2 == 0) {
					positionMessage = jQuery("<tr name = 'positionMessage'></tr>");
					positionMessage.append("<td>"+
					                            "<div class='ch-checker fl'>"+
					                              "<div class='beau-checker'>"+
					                                "<span class='iconfont icon-gou'></span>"+
					                              "</div>"+
					                              "<input type='checkbox' class='yellow-checker' name='positionId' value='"+positionList[j].positionId+"'/>"+
					                            "</div>"+
					                            "<span class='ml30'>"+positionList[j].positionName+"</span>"+
					                        "</td>"+
					                        "<td>"+
					                            "<span class='ml8 mr5'>上牌方式:</span>"+
					                            "<select name='upShiftType' class='chzn-select input-small'>"+
					                              "<option value='1'>打卡上牌</option>"+
					                              "<option value='0'>手动上牌</option>"+
					                            "</select>"+
					                         "</td>");
					if (j + 1 == positionList.length) {
						positionMessage.append("<td></td><td></td>")
						jQuery("#modelTbody").append(positionMessage);
					}
				}
				else {
					positionMessage.append("<td>"+
					                            "<div class='ch-checker fl'>"+
					                              "<div class='beau-checker'>"+
					                                "<span class='iconfont icon-gou'></span>"+
					                              "</div>"+
					                              "<input type='checkbox' class='yellow-checker' name='positionId' value='"+positionList[j].positionId+"'/>"+
					                            "</div>"+
					                            "<span class='ml30'>"+positionList[j].positionName+"</span>"+
				                           "</td>"+
					                       "<td>"+
					                            "<span class='ml8 mr5'>上牌方式:</span>"+
					                            "<select name='upShiftType' class='chzn-select input-small'>"+
					                              "<option value='1'>打卡上牌</option>"+
					                              "<option value='0'>手动上牌</option>"+
					                            "</select>"+
					                         "</td>");
					jQuery("#modelTbody").append(positionMessage);
				}
			}
			jQuery(".chzn-select").chosen({disable_search_threshold: 10, no_results_text: ""});
			return;
		}
	}
}

function updateDept(obj, deptIds){
	var tabObj = jQuery(obj).parent().find(".tab");
	for (var i = 0; i < tabObj.length; i++) {
		jQuery(tabObj[i]).removeClass("active");
	}
	jQuery(obj).addClass("active");
	deptId = deptIds;
	updatePage();
}

//新增或设置轮牌model
jQuery("#confirm").click(function(){
	
	var shiftMahjongUp = radioValue("shiftMahjongUp");
	var shiftMahjongRule = radioValue("shiftMahjongRule");
	var nature = radioValue("nature");
	var positionIdList = jQuery("input[name='positionId']:checked");
	var positionIdListStr = "";
	for (var i = 0; i < positionIdList.length; i++) {
		if (positionIdListStr == "") {
			positionIdListStr = jQuery(positionIdList[i]).val() + ":" + jQuery(positionIdList[i]).parent().parent().next().find("select[name='upShiftType']").val();
		}
		else {
			positionIdListStr = positionIdListStr + "," + jQuery(positionIdList[i]).val() + ":" + jQuery(positionIdList[i]).parent().parent().next().find("select[name='upShiftType']").val();
		}
		console.log(positionIdListStr);
	}
	
	var shiftMahjongName = jQuery("input[name='shiftMahjongName']").val();
	if (shiftMahjongName == "") {
		dialog("轮牌名称不能为空！");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/addUpdateShiftMahjong",
		data : "shiftMahjongId="+shiftMahjongId+"&shiftMahjongName="+shiftMahjongName+"&shiftMahjongUp="+shiftMahjongUp+"&shiftMahjongRule="+shiftMahjongRule+"&deptId="+deptId+"&nature="+nature+"&positionIdListStr="+positionIdListStr,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			updatePage();
			jQuery('#rotating-setting-modal').modal("hide");
		}
	});
});

//赋值model中redio的值
function valuation(names, values) {
	var nameObj = jQuery("input[name='"+names+"']");
	for (var i = 0; i < nameObj.length; i++) {
		var obj = nameObj[i];
		jQuery(obj).parent().find(".beau-checker").removeClass("active");
		var value = (jQuery(obj).val()).replace(/[^0-9]/ig,""); 
		if (value == values) {
			jQuery(obj).parent().find(".beau-checker").addClass("active");
			jQuery(obj).prop("checked", true);
		}
	}
}

//取model中redio的值
function radioValue(names) {
	var nameObj = jQuery("input[name='"+names+"']:checked").val();
	return nameObj;
}

//更新轮牌页面（按部门）
function updatePage(){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "staff/action/selectshiftMahjong",
		data : "deptId="+deptId,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var shiftMahjongDtoList = e.msg;
			jQuery("#lunpaiDIV").empty();
			for (var i = 0; i < shiftMahjongDtoList.length; i++) {
				var shiftInfo = shiftMahjongDtoList[i];
                var widgetcontent = jQuery("<div class='widgetcontent'></div>")
				var zhiwei = jQuery("<div class='zhiwei-lunpai'></div>");
				zhiwei.append("<div class='zhiwei-name'>"+
						                        "<span class='ml10 name'>"+shiftInfo.shiftMahjongName+"</span>"+
						                        "<div class='lunpai-shezhi fr'>"+
						                            "<i class='iconfont icon-shezhi' data-target='#rotating-setting-modal' data-toggle='modal' onclick=\"setShiftMahjong("+shiftInfo.shiftMahjongId+",'"+shiftInfo.shiftMahjongName+"',"+shiftInfo.nature+","+shiftInfo.shiftMahjongUp+","+shiftInfo.shiftMahjongRule+")\"></i>"+
						                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='iconfont iconfa-trash' onclick=\"deleteShiftMahjong(this, "+shiftInfo.shiftMahjongId+")\"></i>"+
						                        "</div>"+
						                    "</div>");
				var shiftObj = jQuery("<div class='s-whole-wrap'></div>");
				shiftObj.append("<div class='s-left-control'>"+
	                             "<span class='iconfont icon-zuobianfangxiang'></span>"+
	                          "</div>");
				var wrapObj = jQuery("<div class='s-slider-wrap'></div>");
				
				var shiftMahjongEmployeeList = shiftMahjongDtoList[i].shiftMahjongEmployeeList;
				
				var employeeObj = infoDIV(shiftInfo.shiftMahjongId,shiftMahjongEmployeeList);
				
				wrapObj.append(employeeObj);
				shiftObj.append(wrapObj);
				shiftObj.append("<div class='s-right-control'>"+
	                             "<span class='iconfont icon-youbianfangxiang'></span>"+
	                          "</div>");
				zhiwei.append(shiftObj);
				widgetcontent.append(zhiwei);
				jQuery("#lunpaiDIV").append(widgetcontent);
				renewalShift();
			}
		}
	});
}

//员工排位
function infoDIV(shiftMahjongIdDiv,shiftMahjongEmployeeList){
	
	var employeeObj = jQuery("<div class='slider4'></div>");
	
	for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
		var shiftMahjongEmployee = shiftMahjongEmployeeList[j];
		var slideObj = jQuery("<div class='slide slider-part' name='101'></div>");
		var imgs = picUrl+shiftMahjongEmployee.headImage;
		var sliderTitle = jQuery("<div class='slider-title'></div>");
		if (shiftMahjongEmployee.shiftMahjongOrder == 999) {
			sliderTitle.append("<span class=''>"+shiftMahjongEmployee.shiftMahjongOrder+"</span>");
		}
		else {
			sliderTitle.append("<img src='"+baseUrl+"img/common/left-arraw.png' alt='' onclick= 'upwardIMG(this,"+shiftMahjongEmployee.shiftMahjongEmployeeId+","+shiftMahjongIdDiv+","+shiftMahjongEmployee.shiftMahjongOrder+")'/>"+
			                   "<span class=''>"+shiftMahjongEmployee.shiftMahjongOrder+"</span>"+
			                   "<img src='"+baseUrl+"img/common/right-arraw.png' alt='' onclick= 'nextIMG(this,"+shiftMahjongEmployee.shiftMahjongEmployeeId+","+shiftMahjongIdDiv+","+shiftMahjongEmployee.shiftMahjongOrder+")'/>");
		}
		slideObj.append(sliderTitle);
		slideObj.append("<div class='slider-content'>"+
		                    "<div class='center'>"+
		                        "<img src='"+imgs+"' alt=''/>"+
		                        "<div class='name'>"+shiftMahjongEmployee.employeeCode+" "+ shiftMahjongEmployee.name+"</div>"+
		                    "</div>"+
		                "</div>");
		for (var k = 0; k < stateList.length; k++) {
			if(stateList[k].state == shiftMahjongEmployee.state){
				slideObj.append("<div class='slider-foot' shiftMahjongEmployeeId = '"+shiftMahjongEmployee.shiftMahjongEmployeeId+"'>"+
					               "<div class='center "+stateList[k].styles+"'>"+
				                        "<div class='zhuangtai fl'>"+
				                            "<img src='"+stateList[k].imgs+"' alt='"+stateList[k].name+"'/>"+
				                        "</div>"+
				                        "<div class='zhuangtai-word fl' names = '"+stateList[k].state+"'>"+
				                            ""+stateList[k].name+""+
				                        "</div>"+
				                    "</div>"+
				                "</div>");
			}
		}
		employeeObj.append(slideObj);
	}
	return employeeObj;
}

//上移
function upwardIMG(obj,shiftMahjongEmployeeId,shiftMahjongIdDIV,shiftMahjongOrder){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/upwardIMG",
		data : "shiftMahjongEmployeeId="+shiftMahjongEmployeeId+"&shiftMahjongId="+shiftMahjongIdDIV+"&shiftMahjongOrder="+shiftMahjongOrder,
		async:false,//使用同步的Ajax请求 
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var employeeObj = infoDIV(shiftMahjongIdDIV,e.msg);
			var parents = jQuery(obj).parents(".s-slider-wrap");
			parents.empty();
			parents.append(employeeObj);
			renewalShift();
		}
	});
}

//下移
function nextIMG(obj,shiftMahjongEmployeeId,shiftMahjongIdDIV,shiftMahjongOrder){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/nextIMG",
		data : "shiftMahjongEmployeeId="+shiftMahjongEmployeeId+"&shiftMahjongId="+shiftMahjongIdDIV+"&shiftMahjongOrder="+shiftMahjongOrder,
		async:false,//使用同步的Ajax请求 
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var employeeObj = infoDIV(shiftMahjongIdDIV,e.msg);
			var parents = jQuery(obj).parents(".s-slider-wrap");
			parents.empty();
			parents.append(employeeObj);
			renewalShift();
		}
	});
}

function deleteShiftMahjong(obj, shiftMahjongId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/deleteShiftMahjong",
		data : "shiftMahjongId="+shiftMahjongId,
		async:false,//使用同步的Ajax请求 
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery(obj).parents(".widgetcontent").remove();
		}
	});
}

