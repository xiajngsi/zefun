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

//分页处理
function changePage(){

	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/list",
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
	
	var List = page.results;
	var tbody = document.createElement("tbody");
	
	for (var i = 0; i < List.length; i++) {
		var employeeLevel = List[i];
		
		var tr = document.createElement("tr");
		tr.setAttribute("id", employeeLevel.levelId);
		
		var positionCode = document.createElement("td");
		positionCode.innerHTML = employeeLevel.positionCode;
		tr.appendChild(positionCode);
		
		var positionName = document.createElement("td");
		positionName.innerHTML = employeeLevel.positionName;
		tr.appendChild(positionName);
		
		var levelName = document.createElement("td");
		levelName.innerHTML = employeeLevel.levelName;
		tr.appendChild(levelName);
		
		var assignCommission = document.createElement("td");
		assignCommission.innerHTML = employeeLevel.assignCommission;
		tr.appendChild(assignCommission);
		
		var nonAssignCommission = document.createElement("td");
		nonAssignCommission.innerHTML = employeeLevel.nonAssignCommission;
		tr.appendChild(nonAssignCommission);
		
		var nonCustomercost = document.createElement("td");
		nonCustomercost.innerHTML = employeeLevel.nonCustomercost;
		tr.appendChild(nonCustomercost);
		
		
		var customercost = document.createElement("td");
		customercost.innerHTML = employeeLevel.customercost;
		tr.appendChild(customercost);
		
		var performancecount = document.createElement("td");
		performancecount.innerHTML = employeeLevel.performancecount;
		tr.appendChild(performancecount);
		
		
		
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "icon-edit");
		editSpan.setAttribute("onclick", "openedit(" + employeeLevel.levelId + ")");
		
		var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "icon-remove-sign ml30");
		removeSpan.setAttribute("onclick", "deleteinfo(" + employeeLevel.levelId + ")");
		operateTd.appendChild(editSpan);
		operateTd.appendChild(removeSpan);
		tr.appendChild(operateTd);
		tbody.appendChild(tr);
	}
	jQuery(".member-list-table tbody").remove();
	jQuery(".member-list-table").append(tbody);
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

//删除职位信息
function deleteinfo(levelId){
	if(confirm("确认要删除该条信息吗？")){ 
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/delete",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				dialog(e.msg);
				return;
			}
			dialog(e.msg);
			jQuery(".member-list-table tr[id='" + levelId + "']").fadeOut(800).remove();
		}
	});
	}
}

//打开修改页面
function openedit(levelId){
	
	
	jQuery('#update-zhiwei-modal').modal();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/leveldetail",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
		//修改页面数据赋值
			jQuery("#updatepositionId").val(e.msg.positionId);
			jQuery("#updatepositionId").trigger("liszt:updated");
			
			jQuery("#updatelevelId").val(e.msg.levelId);
			//jQuery("#updatepositionId").val(e.msg.positionId);
			jQuery("#updatelevelName").val(e.msg.levelName);
			jQuery("#updateassignType").val(e.msg.assignType);
			jQuery("#updateassignType").trigger("liszt:updated");
			
			if(e.msg.isUpgrade=="0"){
				jQuery("#updateis_upgrade").val("启用");
			}else{
				jQuery("#updateis_upgrade").val("禁用");
			}
			
			jQuery("#updateassignCommission").val(e.msg.assignCommission);
			jQuery("#updatenonAssignType").val(e.msg.nonAssignType);
			jQuery("#updatenonAssignType").trigger("liszt:updated");
			
			jQuery("#updatenonAssignCommission").val(e.msg.nonAssignCommission);
			jQuery("#updatenonCustomercost").val(e.msg.nonCustomercost);
			jQuery("#updatecustomercost").val(e.msg.customercost);
			jQuery("#updateperformancecount").val(e.msg.performancecount);
			
			
		}
	});
	
}

//修改职位信息
function editsave(){
	//获取相关参数 然后再把它封装在addData这个对象里面
	var addData = {};
	
	//判断只能输入数字或者保留2个的小数
	var re=/^\d*\.{0,2}\d{0,2}$/;
	var levelId =jQuery("#updatelevelId").val();
	addData["levelId"] = levelId;
	
    var positionId =jQuery("#updatepositionId").val();
    addData["positionId"] = positionId;
    
	var levelName =jQuery("#updatelevelName").val();
	if(levelName==""||levelName==null){
		dialog("职位名称不能为空！");
		 return;
	}
	addData["levelName"] = levelName;
	
	var assignType =jQuery("#updateassignType").val();
	addData["assignType"] = assignType;
	
	var assignCommission =jQuery("#updateassignCommission").val();
	if(assignCommission==""||assignCommission==null){
		dialog("指定提成不能为空");
		 return;
	}
	 if(!re.test(assignCommission)){
		 dialog("指定提成只能输入数字或者保留2位的小数！");
		 return;
	 }
	 addData["assignCommission"] = assignCommission;
	 
	var nonAssignType =jQuery("#updatenonAssignType").val();
	addData["nonAssignType"] = nonAssignType;
	
	var nonAssignCommission =jQuery("#updatenonAssignCommission").val();
	if(nonAssignCommission==""||nonAssignCommission==null){
		dialog("非指定提成不能为空");
		 return;
	}
	if(!re.test(nonAssignCommission)){
		 dialog("非指定提成只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["nonAssignCommission"] = nonAssignCommission;
	
	var nonCustomercost =jQuery("#updatenonCustomercost").val();
	if(!re.test(nonCustomercost)){
		 dialog("非指定客单价只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["nonCustomercost"] = nonCustomercost;
	
	var customercost =jQuery("#updatecustomercost").val();
	if(!re.test(customercost)){
		 dialog("指定客单价只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["customercost"] = customercost;
	
	var performancecount =jQuery("#updateperformancecount").val();
	if(!re.test(performancecount)){
		 dialog("业绩总额只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["performancecount"] = performancecount;
	
	var is_upgrade =jQuery("#updateis_upgrade").val();
	if(is_upgrade=="启用"){
		is_upgrade=0;
	}else{
		is_upgrade=1;
	}
	addData["isUpgrade"] = is_upgrade;
	var addData=JSON.stringify(addData);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/update",
		data :"addData="+addData,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				
				return;
			}
			dialog(e.msg);
			jQuery('#update-zhiwei-modal').modal('hide');
			location.reload();
		}
	});
}
//新增职位信息
function addsave(){
	//获取相关参数 然后再把它封装在addData这个对象里面
	var addData = {};
	
	var re=/^\d*\.{0,2}\d{0,2}$/;
	var positionId =jQuery("#addpositionId").val();
	addData["positionId"] = positionId;
	var levelName =jQuery("#addlevelName").val();
	if(levelName==""||levelName==null){
		dialog("职位名称不能为空！");
		 return;
	}
	addData["levelName"] = levelName;
	
	var assignType =jQuery("#addassignType").val();
	addData["assignType"] = assignType;
	
	var assignCommission =jQuery("#addassignCommission").val();
	if(assignCommission==""||assignCommission==null){
		dialog("指定提成不能为空");
		 return;
	}
	if(!re.test(assignCommission)){
		 dialog("指定提成只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["assignCommission"] = assignCommission;
	
	var nonAssignType =jQuery("#addnonAssignType").val();
	addData["nonAssignType"] = nonAssignType;
	
	var nonAssignCommission =jQuery("#addnonAssignCommission").val();
	if(nonAssignCommission==""||nonAssignCommission==null){
		dialog("非指定提成不能为空");
		 return;
	}
	if(!re.test(nonAssignCommission)){
		 dialog("非指定提成只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["nonAssignCommission"] = nonAssignCommission;
	
	var nonCustomercost =jQuery("#addnonCustomercost").val();
	if(!re.test(nonCustomercost)){
		 dialog("非指定客单价只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["nonCustomercost"] = nonCustomercost;
	
	var customercost =jQuery("#addcustomercost").val();
	if(!re.test(customercost)){
		 dialog("指定客单价只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["customercost"] = customercost;
	
	var performancecount =jQuery("#addperformancecount").val();
	if(!re.test(performancecount)){
		 dialog("业绩总额只能输入数字或者保留2位的小数！");
		 return;
	 }
	addData["performancecount"] = performancecount;
	
	var is_upgrade =jQuery("#addis_upgrade").val();
	if(is_upgrade=="启用"){
		is_upgrade=0;
	}else{
		is_upgrade=1;
	}
	addData["isUpgrade"] = is_upgrade;
	
	var addData=JSON.stringify(addData);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/add",
		data :  "addData="+addData,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				dialog(e.msg);
				return;
			}
			console.log(e.msg);
			dialog(e.msg);
			jQuery('#add-zhiwei-modal').modal('hide');
			location.reload();
//			jQuery("#add-member-card").addClass("in").attr("aria-hidden", "false").show();
		}
	});
}
function canceladd(){
	jQuery('#add-zhiwei-modal').modal('hide');
}

function cancelupdate(){
	jQuery('#update-zhiwei-modal').modal('hide');
}

//新增时候的启用禁用
function changeis(){
	
	var is_upgrade =jQuery("#addis_upgrade").val();
	if(is_upgrade=="启用"){
		jQuery("#addis_upgrade").val("禁用");
	}else{
		jQuery("#addis_upgrade").val("启用");
	}
}

//修该的启用禁用

function updatechangeis(){
	
	var is_upgrade =jQuery("#updateis_upgrade").val();
	if(is_upgrade=="启用"){
		jQuery("#updateis_upgrade").val("禁用");
	}else{
		jQuery("#updateis_upgrade").val("启用");
	}
}

