//-----------------------考勤规则部分------------------------------------
//上一页
function previous(rule){
	if(pageNoOfAttendance <= 1){
		return;
	}
	pageNoOfAttendance --;
	changePage(rule);
}

//下一页
function next(rule){
	if(pageNoOfAttendance >= totalPageOfAttendance){
		return;
	}
	pageNoOfAttendance ++;
	changePage(rule);
}

//更改每页显示数量
function changePageSize(size, rule){
	pageNoOfAttendance = 1;
	pageSizeOfAttendance = size;
	jQuery(".perpage0").html(size + "<span class='caret'></span>");
	changePage(rule);
}
//分页处理
function changePage(rule){
	
	var year=jQuery("#queryyear").val();
	var month=jQuery("#querymonth").val();
	if(month<10){
		month="0"+month;
	}
	var objectiveMonth=year+"-"+month;
	var search=jQuery("#search").val();
	var data = "pageNo=" + pageNoOfAttendance + "&pageSize=" + pageSizeOfAttendance+ "&time=" 
		+ objectiveMonth+ "&employeeName=" + search + "&rule=" + rule;
	//console.log(data);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "attendance/action/list",
		data : data,
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
	pageNoOfAttendance = page.pageNo;
	pageSizeOfAttendance = page.pageSize;
	totalPageOfAttendance = page.totalPage;
	
	var List = page.results;
	var tbody = document.createElement("tbody");
	
	for (var i = 0; i < List.length; i++) {
		var countrewards = List[i];
		
		var tr = document.createElement("tr");
		//tr.setAttribute("id", countrewards.employeeId);
		
		var employeeCode = document.createElement("td");
		employeeCode.innerHTML = countrewards.employeeCode;
		tr.appendChild(employeeCode);
		
		var name = document.createElement("td");
		name.innerHTML = countrewards.employeeName;
		tr.appendChild(name);
		//迟到
		var chidao = document.createElement("td");
		var chidaoA="<a href='javascript:void(0)' onclick='opendetail(\"1\","+countrewards.employeeId+")'>"+countrewards.countOfLate+"</a>"
		chidao.innerHTML = chidaoA;
		tr.appendChild(chidao);
		//早退
		var zaotui = document.createElement("td");
		var zaotuiA="<a href='javascript:void(0)' onclick='opendetail(\"2\","+countrewards.employeeId+")'>"+countrewards.countOfEarlyLeave+"</a>"
		zaotui.innerHTML = zaotuiA;
		tr.appendChild(zaotui);
		//请假
		var qingjia = document.createElement("td");
		var qingjiaA="<a href='javascript:void(0)' onclick='opendetail(\"3\","+countrewards.employeeId+")'>"+countrewards.countOfHoliday+"</a>"
		qingjia.innerHTML = qingjiaA;
		tr.appendChild(qingjia);
		//旷工
		var kuanggong = document.createElement("td");
		var kuanggongA="<a href='javascript:void(0)' onclick='opendetail(\"4\","+countrewards.employeeId+")'>"+countrewards.countOfAbsenteeism+"</a>"
		kuanggong.innerHTML = kuanggongA;
		tr.appendChild(kuanggong);
		
		var jiang = document.createElement("td");
		jiang.innerHTML = countrewards.moneyOfReward;
		tr.appendChild(jiang);
		
		var kou = document.createElement("td");
		kou.innerHTML = countrewards.moneyOfPunishment;
		tr.appendChild(kou);
		
		tbody.appendChild(tr);
	}
	jQuery(".member-list-table tbody").remove();
	jQuery(".member-list-table").append(tbody);
}

//检查当前页码是否可以进行上一页或者下一页，并对其翻页按钮进行对应的处理
function checkPageButton(){
	//处理上一页
	if(pageNoOfAttendance <= 1){
		jQuery("#previousPageButton").attr("disabled",true);  
	} else {
		jQuery("#previousPageButton").attr("disabled",false);  
	}
	
	//处理下一页
	if(pageNoOfAttendance >= totalPageOfAttendance){
		jQuery("#nextPageButton").attr("disabled",true);  
	} else {
		jQuery("#nextPageButton").attr("disabled",false); 
	}
}
//-------------------------行为规范部分-----------------------------------------------------------------------------------------
//上一页
function previous1(rule){
	if(pageNoOfBehaviour <= 1){
		return;
	}
	pageNoOfBehaviour --;
	changePage1(rule);
}

//下一页
function next1(rule){
	if(pageNoOfBehaviour >= totalPageOfBehaviour){
		return;
	}
	pageNoOfBehaviour ++;
	changePage1(rule);
}

//更改每页显示数量
function changePageSize1(size,rule){
	pageNoOfBehaviour = 1;
	pageSizeOfBehaviour = size;
	jQuery(".perpage1").html(size + "<span class='caret'></span>");
	changePage1(rule);
}
//分页处理
function changePage1(rule){
	
	var year=jQuery("#queryyear1").val();
	var month=jQuery("#querymonth1").val();
	if(month<10){
		month="0"+month
	}
	var objectiveMonth=year+"-"+month;
	var search=jQuery("#search1").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "attendance/action/list",
		data : "pageNo=" + pageNoOfBehaviour + "&pageSize=" + pageSizeOfBehaviour + "&time=" 
				+ objectiveMonth+ "&employeeName=" + search + "&rule=" + rule,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			refreshTableData1(e.msg);
			checkPageButton1();
		}
	});
}

//刷新表格数据
function refreshTableData1(page){
	pageNoOfBehaviour = page.pageNo;
	pageSizeOfBehaviour = page.pageSize;
	totalPageOfBehaviour = page.totalPage;
	
	var List = page.results;
	var tbody = document.createElement("tbody");
	
	for (var i = 0; i < List.length; i++) {
		var countrewards = List[i];
		
		var tr = document.createElement("tr");
		//tr.setAttribute("id", countrewards.employeeId);
		
		var employeeCode = document.createElement("td");
		employeeCode.innerHTML = countrewards.employeeCode;
		tr.appendChild(employeeCode);
		
		var name = document.createElement("td");
		name.innerHTML = countrewards.employeeName;
		tr.appendChild(name);
		//小过
		var xiaoguo = document.createElement("td");
		var xiaoguoA="<a href='javascript:void(0)' onclick='opendetail(\"6\","+countrewards.employeeId+")'>"+countrewards.countOfSmallMistake+"</a>"
		xiaoguo.innerHTML = xiaoguoA;
		tr.appendChild(xiaoguo);
		//大过
		var daguo = document.createElement("td");
		var daguoA="<a href='javascript:void(0)' onclick='opendetail(\"7\","+countrewards.employeeId+")'>"+countrewards.countOfSeriousMistake+"</a>"
		daguo.innerHTML = daguoA;
		tr.appendChild(daguo);
		//警告
		var jinggao = document.createElement("td");
		var jinggaoA="<a href='javascript:void(0)' onclick='opendetail(\"8\","+countrewards.employeeId+")'>"+countrewards.countOfWarning+"</a>"
		jinggao.innerHTML = jinggaoA;
		tr.appendChild(jinggao);
		
		var jiang = document.createElement("td");
		jiang.innerHTML = countrewards.moneyOfReward;
		tr.appendChild(jiang);
		
		var kou = document.createElement("td");
		kou.innerHTML = countrewards.moneyOfPunishment;
		tr.appendChild(kou);
		
		tbody.appendChild(tr);
	}
	jQuery(".member-list-table1 tbody").remove();
	jQuery(".member-list-table1").append(tbody);
}

//检查当前页码是否可以进行上一页或者下一页，并对其翻页按钮进行对应的处理
function checkPageButton1(){
	//处理上一页
	if(pageNoOfBehaviour <= 1){
		jQuery("#previousPageButton").attr("disabled",true);  
	} else {
		jQuery("#previousPageButton").attr("disabled",false);  
	}
	
	//处理下一页
	if(pageNoOfBehaviour >= totalPageOfBehaviour){
		jQuery("#nextPageButton").attr("disabled",true);  
	} else {
		jQuery("#nextPageButton").attr("disabled",false); 
	}
}
//--------------------------服务表现部分-------------------------------------------------------------------------------------
//上一页
function previous2(rule){
	if(pageNoOfService <= 1){
		return;
	}
	pageNoOfService --;
	changePage2(rule);
}

//下一页
function next2(rule){
	if(pageNoOfService >= totalPageOfService){
		return;
	}
	pageNoOfService ++;
	changePage2(rule);
}

//更改每页显示数量
function changePageSize2(size,rule){
	pageNoOfService = 1;
	pageSizeOfService = size;
	jQuery(".perpage2").html(size + "<span class='caret'></span>");
	changePage2(rule);
}
//分页处理
function changePage2(rule){
	
	var year=jQuery("#queryyear2").val();
	var month=jQuery("#querymonth2").val();
	if(month<10){
		month="0"+month
	}
	var objectiveMonth=year+"-"+month;
	var search=jQuery("#search2").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/action/list2",
		data : "pageNo=" + pageNoOfService + "&pageSize=" + pageSizeOfService + "&time=" 
				+ objectiveMonth+ "&employeeName=" + search + "&rule=" + rule,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			refreshTableData2(e.msg);
			checkPageButton2();
		}
	});
}

//刷新表格数据
function refreshTableData2(page){
	pageNoOfService = page.pageNo;
	pageSizeOfService = page.pageSize;
	totalPageOfService = page.totalPage;
	
	var List = page.results;
	var tbody = document.createElement("tbody");
	
	for (var i = 0; i < List.length; i++) {
		var countrewards = List[i];
		
		var tr = document.createElement("tr");
		//tr.setAttribute("id", countrewards.employeeId);
		
		var employeeCode = document.createElement("td");
		employeeCode.innerHTML = countrewards.employeeCode;
		tr.appendChild(employeeCode);
		
		var name = document.createElement("td");
		name.innerHTML = countrewards.employeeName;
		tr.appendChild(name);
		//好评
		var haoping = document.createElement("td");
		var haopingA="<a href='javascript:void(0)' onclick='opendetail(\"9\","+countrewards.employeeId+")'>"+countrewards.countOfFavourable+"</a>"
		haoping.innerHTML = haopingA;
		tr.appendChild(haoping);
		//差评
		var chaping = document.createElement("td");
		var chapingA="<a href='javascript:void(0)' onclick='opendetail(\"10\","+countrewards.employeeId+")'>"+countrewards.countOfBadpost+"</a>"
		chaping.innerHTML = chapingA;
		tr.appendChild(chaping);
		//投诉
		var tousu = document.createElement("td");
		var tousuA="<a href='javascript:void(0)' onclick='opendetail(\"11\","+countrewards.employeeId+")'>"+countrewards.countOfComplaint+"</a>"
		tousu.innerHTML = tousuA;
		tr.appendChild(tousu);
		
		var jiang = document.createElement("td");
		jiang.innerHTML = countrewards.moneyOfReward;
		tr.appendChild(jiang);
		
		var kou = document.createElement("td");
		kou.innerHTML = countrewards.moneyOfPunishment;
		tr.appendChild(kou);
		
		tbody.appendChild(tr);
	}
	jQuery(".member-list-table2 tbody").remove();
	jQuery(".member-list-table2").append(tbody);
}

//检查当前页码是否可以进行上一页或者下一页，并对其翻页按钮进行对应的处理
function checkPageButton2(){
	//处理上一页
	if(pageNoOfService <= 1){
		jQuery("#previousPageButton").attr("disabled",true);  
	} else {
		jQuery("#previousPageButton").attr("disabled",false);  
	}
	
	//处理下一页
	if(pageNoOfService >= totalPageOfService){
		jQuery("#nextPageButton").attr("disabled",true);  
	} else {
		jQuery("#nextPageButton").attr("disabled",false); 
	}
}

//--------------------------------------------------------------------------------------
//打开修改框框
function openeditkaoqin(ruleId,projectName,standard,abstractMoney){
	jQuery(".editprofileform").empty();
	var str = jQuery("<p>"+
	        "<label>考核名称:</label>"+
	        "<input type='hidden' id='updateRuleId'>"+
	        "<input type='text' name='name' id='updateprojectName' style='width: 95px;' class='input-xlarge' disabled></p>");
	if(projectName=="迟到"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "迟到超过<input type='text' id='updatestandard' style='width: 15px;'>分钟</p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "罚款<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}else if(projectName=="早退"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "早退超过<input type='text' id='updatestandard' style='width: 15px;'>分钟</p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "罚款<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}else if(projectName=="旷工"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "迟到或早退超过<input type='text' id='updatestandard' style='width: 15px;'>小时或者旷工</p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "每次扣日薪<input type='text' id='updateabstractMoney' style='width: 35px;'>%</p>");
	}else if(projectName=="请假"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "请假并且经过主管批准</p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "每小时扣日薪<input type='text' id='updateabstractMoney' style='width: 35px;'>%</p>");
	}else if(projectName=="全勤"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "<input type='text' id='updatestandard' style='width: 95px;'></p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "奖励<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}
	
	
		jQuery(".editprofileform").append(str);
		jQuery(".editprofileform").append(str1);
	
	jQuery('#updateRuleId').val(ruleId);
	jQuery('#updateRuleType').val("kaoqin");
	jQuery('#updateprojectName').val(projectName);
	jQuery('#updatestandard').val(standard);
	jQuery('#updateabstractMoney').val(abstractMoney);
	
	jQuery('#update-modal').modal();
}
//打开修改框框
function openeditxingwei(ruleId,projectName,standard,abstractMoney){
	jQuery(".editprofileform").empty();
	var str = jQuery("<p>"+
	        "<label>考核名称:</label>"+
	        "<input type='hidden' id='updateRuleId'>"+
	        "<input type='text' name='name' id='updateprojectName' style='width: 95px;' class='input-xlarge' disabled></p>");
	if(projectName=="小过"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "<input type='text' id='updatestandard' style='width: 95px;'></p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "罚款<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}else if(projectName=="大过"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "<input type='text' id='updatestandard' style='width: 95px;'></p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "奖励<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}else if(projectName=="警告"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "<input type='text' id='updatestandard' style='width: 95px;'></p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "奖励<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}
	
	
		jQuery(".editprofileform").append(str);
		jQuery(".editprofileform").append(str1);
	
	
	
	
	jQuery('#updateRuleId').val(ruleId);
	jQuery('#updateRuleType').val("xingwei");
	jQuery('#updateprojectName').val(projectName);
	jQuery('#updatestandard').val(standard);
	jQuery('#updateabstractMoney').val(abstractMoney);
	
	jQuery('#update-modal').modal();
}
//打开修改框框
function openeditfuwu(ruleId,projectName,standard,abstractMoney){
	jQuery(".editprofileform").empty();
	var str = jQuery("<p>"+
	        "<label>考核名称:</label>"+
	        "<input type='hidden' id='updateRuleId'>"+
	        "<input type='text' name='name' id='updateprojectName' style='width: 95px;' class='input-xlarge' disabled></p>");
	if(projectName=="差评"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "服务评分低于<input type='text' id='updatestandard' style='width: 15px;'>分</p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "罚款<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}else if(projectName=="好评"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "服务评分高于<input type='text' id='updatestandard' style='width: 15px;'>分</p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "奖励<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}else if(projectName=="投诉"){
		var str1 = jQuery("<p>"+
		        "<label>考核标准:</label>"+
		        "<input type='text' id='updatestandard' style='width: 95px;'></p>"+
		        "<p>"+
		        "<label>奖惩措施:</label>"+
		        "奖励<input type='text' id='updateabstractMoney' style='width: 35px;'>元</p>");
	}
	
	
		jQuery(".editprofileform").append(str);
		jQuery(".editprofileform").append(str1);
	
	jQuery('#updateRuleId').val(ruleId);
	jQuery('#updateRuleType').val("fuwu");
	jQuery('#updateprojectName').val(projectName);
	jQuery('#updatestandard').val(standard);
	jQuery('#updateabstractMoney').val(abstractMoney);
	
	jQuery('#update-modal').modal();
}
//取消按钮
function canceladd(){
	jQuery('#update-modal').modal('hide');
}

//修改规则
function updatesave(){
	var addData = {};
	var ruleId=jQuery('#updateRuleId').val();
	addData["ruleId"] = ruleId;
	var ruleType=jQuery('#updateRuleType').val();
	addData["ruleType"] = ruleType;
	var projectName=jQuery('#updateprojectName').val();
	addData["projectName"] = projectName;
	var standard=jQuery('#updatestandard').val();
	addData["standard"] = standard;
	var abstractMoney=jQuery('#updateabstractMoney').val();
	addData["abstractMoney"] = abstractMoney;
	var Data=JSON.stringify(addData);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/action/update",
		data : "addData="+Data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			jQuery('#update-modal').modal('hide');
			dialog(e.msg);
			if(ruleType=="kaoqin"){
				showkaoqin();
			}else if(ruleType=="xingwei"){
				showxingwei();
			}else if(ruleType=="fuwu"){
				showfuwu();
			}
			
		}
	});
}

//-----------------------------------------------------------------------------------------------------------------------------------
function showRuleList(){
	var ruletype=jQuery("#ruletype").val();
	
	if(ruletype=="kaoqin"){
		//showkaoqin();
		jQuery(".aaa").show();
		jQuery(".bbb").hide();
		jQuery(".ccc").hide();
		jQuery(".kaoqin").show();
		jQuery(".xingwei").hide();
		jQuery(".fuwu").hide();
	}else if(ruletype=="xingwei"){
		//showxingwei();
		jQuery(".aaa").hide();
		jQuery(".bbb").show();
		jQuery(".ccc").hide();
		jQuery(".kaoqin").hide();
		jQuery(".xingwei").show();
		jQuery(".fuwu").hide();
	}else if(ruletype=="fuwu"){
		//showfuwu();
		jQuery(".aaa").hide();
		jQuery(".bbb").hide();
		jQuery(".ccc").show();
		jQuery(".kaoqin").hide();
		jQuery(".xingwei").hide();
		jQuery(".fuwu").show();
	}
}
//展示kaoqin
function showkaoqin(){
	jQuery(".aaa tbody").remove();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/action/kaoqinlist",
		data : '',
		dataType : "json",
		success : function(e){
			var list=e.msg;
			
			for (var i = 0; i < list.length; i++) {
				
				if(list[i].attendanceName=="迟到"){
					var name=list[i].attendanceName;
					var str1 = jQuery("<tr><td>"+list[i].attendanceName+"</td>"+
                            "<td>当班日晚于开门时间"+list[i].standard+"分钟</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditkaoqin("+list[i].attendanceId+",'迟到',"+list[i].standard+","+list[i].abstractMoney+")\"></i>");
				jQuery(".aaa").append(str1);
				}else if(list[i].attendanceName=="早退"){
					var str1 = jQuery("<tr><td>"+list[i].attendanceName+"</td>"+
                            "<td>当班日早于下班时间"+list[i].standard+"分钟</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditkaoqin("+list[i].attendanceId+",'早退',"+list[i].standard+","+list[i].abstractMoney+")\"></i>");
				jQuery(".aaa").append(str1);
				}else if(list[i].attendanceName=="请假"){
					var str1 = jQuery("<tr><td>"+list[i].attendanceName+"</td>"+
                            "<td>请假并经过领导审批每"+list[i].standard+"小时</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditkaoqin("+list[i].attendanceId+",'请假',"+list[i].standard+","+list[i].abstractMoney+")\"></i>");
				jQuery(".aaa").append(str1);
				}else if(list[i].attendanceName=="旷工"){
					var str1 = jQuery("<tr><td>"+list[i].attendanceName+"</td>"+
                            "<td>旷工"+list[i].standard+"小时</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditkaoqin("+list[i].attendanceId+",'旷工',"+list[i].standard+","+list[i].abstractMoney+")\"></i>");
				jQuery(".aaa").append(str1);
				}else if(list[i].attendanceName=="全勤"){
					var str1 = jQuery("<tr><td>"+list[i].attendanceName+"</td>"+
                            "<td>"+list[i].standard+"</td>"+
                            "<td>奖励</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditkaoqin("+list[i].attendanceId+",'全勤',"+"'"+list[i].standard+"'"+","+list[i].abstractMoney+")\"></i>");
				jQuery(".aaa").append(str1);
				}
				
				
			}
			
		}
	});
}


//展示xingwei
function showxingwei(){
	jQuery(".bbb tbody").remove();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/action/xingweilist",
		data : '',
		dataType : "json",
		success : function(e){
			var list=e.msg;
			for (var i = 0; i < list.length; i++) {
				if(list[i].behaviorName=="小过"){
					var str1 = jQuery("<tr><td>"+list[i].behaviorName+"</td>"+
                            "<td>"+list[i].standard+"</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditxingwei("+list[i].behaviorId+",'小过',"+"'"+list[i].standard+"'"+","+list[i].abstractMoney+")\"></i>");
				jQuery(".bbb").append(str1);	
				}else if(list[i].behaviorName=="大过"){
					var str1 = jQuery("<tr><td>"+list[i].behaviorName+"</td>"+
                            "<td>"+list[i].standard+"</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditxingwei("+list[i].behaviorId+",'大过',"+"'"+list[i].standard+"'"+","+list[i].abstractMoney+")\"></i>");
				jQuery(".bbb").append(str1);
				}else if(list[i].behaviorName=="警告"){
					var str1 = jQuery("<tr><td>"+list[i].behaviorName+"</td>"+
                            "<td>"+list[i].standard+"</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditxingwei("+list[i].behaviorId+",'警告',"+"'"+list[i].standard+"'"+","+list[i].abstractMoney+")\"></i>");
				jQuery(".bbb").append(str1);
				}
				
			}
			
		}
	});
}
//展fuwu
function showfuwu(){
	jQuery(".ccc tbody").remove();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/action/fuwulist",
		data : '',
		dataType : "json",
		success : function(e){
			var list=e.msg;
			for (var i = 0; i < list.length; i++) {
				if(list[i].serviceName=="好评"){
					var str1 = jQuery("<tr><td>"+list[i].serviceName+"</td>"+
                            "<td>当客户评分大于"+list[i].standard+"</td>"+
                            "<td>奖励</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditfuwu("+list[i].serviceId+",'好评',"+"'"+list[i].standard+"'"+","+list[i].abstractMoney+")\"></i>");
				jQuery(".ccc").append(str1);
				}else if(list[i].serviceName=="差评"){
					var str1 = jQuery("<tr><td>"+list[i].serviceName+"</td>"+
                            "<td>当客户评分小于"+list[i].standard+"</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditfuwu("+list[i].serviceId+",'差评',"+"'"+list[i].standard+"'"+","+list[i].abstractMoney+")\"></i>");
				jQuery(".ccc").append(str1);
				}else if(list[i].serviceName=="投诉"){
					var str1 = jQuery("<tr><td>"+list[i].serviceName+"</td>"+
                            "<td>"+list[i].standard+"</td>"+
                            "<td>扣款</td>"+
                            "<td>"+list[i].abstractMoney+"元</td>"+
                            "<td><i class='iconfa-pencil project-icon' data-target='#card-discard-setting' onclick=\"openeditfuwu("+list[i].serviceId+",'投诉',"+"'"+list[i].standard+"'"+","+list[i].abstractMoney+")\"></i>");
				jQuery(".ccc").append(str1);
				}
				
			}
			
		}
	});
}
//-----------------------------------------奖惩详情部分---------------------------------------------------------------------------------

/*function opendetail(projectName,employeeId){
	pageNo = 1;
	jQuery(".perpage").html(10 + "<span class='caret'></span>");
	jQuery('#xiangqing-chakan-modal').modal();
	xiangxi(projectName,employeeId);
}*/
/*function xiangxi(projectName,employeeId){
	jQuery("#projectName").val(projectName);
	jQuery("#employeeId").val(employeeId);
	var ruletype=jQuery("#ruletype").val();
	var objectiveMonth;
	if(ruletype=="kaoqin"){
		var year=jQuery("#queryyear").val();
		var month=jQuery("#querymonth").val();
		if(month<10){
			month="0"+month
		}
		objectiveMonth=year+"-"+month;
	}else if(ruletype=="xingwei"){
		var year=jQuery("#queryyear1").val();
		var month=jQuery("#querymonth1").val();
		if(month<10){
			month="0"+month
		}
		objectiveMonth=year+"-"+month;
		
	}else if(ruletype=="fuwu"){
		var year=jQuery("#queryyear2").val();
		var month=jQuery("#querymonth2").val();
		if(month<10){
			month="0"+month
		}
		objectiveMonth=year+"-"+month;
	}
	var projectName=projectName;
	var employeeId=employeeId;
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewardsDetail/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize+ "&time=" + objectiveMonth+ "&type=" + projectName+ "&employeeId=" + employeeId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			refreshTableDataa(e.msg);
			checkPageButtonn();
		}
	});
	
}*/
function opendetail(type, employeeId){
	detailPageNoOfAttendance = 1;
	jQuery(".perpage").html(10 + "<span class='caret'></span>");
	jQuery('#xiangqing-chakan-modal').modal();
	rewardDetail(type, employeeId);
}
function rewardDetail(type, employeeId){
	jQuery("#type").val(type);
	jQuery("#employeeId").val(employeeId);
//	var detailPageSizeOfAttendance = 10;
	/*if(detailPageSizeOfAttendance === null)
		detailPageSizeOfAttendance = 10;*/
	var time;
	if(type==1 || type==2 || type==3 || type==4) {
		var year = jQuery("#queryyear").val();
		var month = jQuery("#querymonth").val();
		if(month < 10)
			month = "0" + month;
		time = year + "-" + month;
	}
	if(type==6 || type==7 || type==8) {
		var year = $("#queryyear1").val();
		var month = $("#querymonth1").val();
		if(month < 10)
			month = "0" + month;
		time = year + "-" + month;
	}
	if(type==9 || type==10 || type==11) {
		var year = $("#queryyear2").val();
		var month = $("#querymonth2").val();
		if(month < 10)
			month = "0" + month;
		time = year + "-" + month;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewardsDetail/action/list",
		data : "pageNo=" + detailPageNoOfAttendance + "&pageSize=" + detailPageSizeOfAttendance 
			+ "&time=" + time + "&type=" + type+ "&employeeId=" + employeeId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			detailPageNoOfAttendance = e.msg.pageNo;
			detailPageSizeOfAttendance = e.msg.pageSize;
			detailTotalPageOfAttendance = e.msg.totalPage;
			refreshTableDataOfDetail(e.msg);
			checkPageButtonn();
		}
	});
}
function refreshTableDataOfDetail(page){
	detailPageNoOfAttendance = page.pageNo;
	detailPageSizeOfAttendance = page.pageSize;
	detailTotalPageOfAttendance = page.totalPage;
	
	var List = page.results;
	var tbody = document.createElement("tbody");
	
	for (var i = 0; i < List.length; i++) {
		var countrewards = List[i];
		
		var tr = document.createElement("tr");
		//tr.setAttribute("id", countrewards.employeeId);
		
		var operateTime = document.createElement("td");
		operateTime.innerHTML = countrewards.modifytime;
		tr.appendChild(operateTime);
		
		var kou = document.createElement("td");
		kou.innerHTML = countrewards.number;
		tr.appendChild(kou);
		
		var des = document.createElement("td");
		des.innerHTML = countrewards.reasons;
		tr.appendChild(des);
		
		tbody.appendChild(tr);
	}
	jQuery(".derail tbody").remove();
	jQuery(".derail").append(tbody);
}
//检查当前页码是否可以进行上一页或者下一页，并对其翻页按钮进行对应的处理
function checkPageButtonn(){
	//处理上一页
	if(detailPageNoOfAttendance <= 1){
		jQuery("#previousPageButton").attr("disabled",true);  
	} else {
		jQuery("#previousPageButton").attr("disabled",false);  
	}
	
	//处理下一页
	if(detailPageNoOfAttendance >= detailTotalPageOfAttendance){
		jQuery("#nextPageButton").attr("disabled",true);  
	} else {
		jQuery("#nextPageButton").attr("disabled",false); 
	}
}
//上一页
function previouss(){
	if(detailPageNoOfAttendance <= 1){
		return;
	}
	detailPageNoOfAttendance --;
	var a=jQuery("#projectName").val();
	var b=jQuery("#employeeId").val();
	rewardDetail(a,b);
}

//下一页
function nextt(){
	if(detailPageNoOfAttendance >= detailTotalPageOfAttendance){
		return;
	}
	detailPageNoOfAttendance ++;
	var a=jQuery("#projectName").val();
	var b=jQuery("#employeeId").val();
	rewardDetail(a,b);
}

//更改每页显示数量
function changePageSizee(size){
	detailPageNoOfAttendance = 1;
	detailPageSizeOfAttendance = size;
	jQuery(".perpage").html(size + "<span class='caret'></span>");
	var a = jQuery("#type").val();
	var b = jQuery("#employeeId").val();
	rewardDetail(a,b);
}

//--------------------------------------添加奖惩----------------------------------------------
//打开新增员工班次信息
function addshift(){
	jQuery('#add-emp-yuangong-reward').modal();
	jQuery("#employeeId1").attr("disabled",false); 
	jQuery("#employeeId1").trigger("liszt:updated");
	openshiftinfo();
	getDeptEmployee();
	var th = jQuery(".check-radio");
	var tr = th.parents("tr");
	tr.find(".check-radio").removeClass("check-after");
	jQuery('input[type="radio"]').attr("checked",false);
	jQuery("#xiugai").hide();
	jQuery("#xinzeng").show();
	jQuery("#dataId").val("");
}