jQuery(function(){
		if(pageNo == totalPage){
			jQuery("#previousPageButton").attr("disabled",true);
			jQuery("#nextPageButton").attr("disabled",true); 
		}
	    if(pageNo == 1){
	    	jQuery("#previousPageButton").attr("disabled",true);
		}
});

jQuery('.lcs_check').lc_switch('是', '否');

/*选择时间*/
jQuery(".datetimepickerBegin").each(function(){
  jQuery(this).datetimepicker({
    value:beginDate,
    lang:'ch',
    timepicker:false,
    format:"Y-m-d"
  });
});

/*选择时间*/
jQuery(".datetimepickerEnd").each(function(){
  jQuery(this).datetimepicker({
    value:endDate,
    lang:'ch',
    timepicker:false,
    format:"Y-m-d"
  });
});

/*新增会员模态框左右居中*/
jQuery('#addStoreflow').click(function (e) {
	jQuery("#addForm")[0].reset();
	jQuery("input[name='flowId']").val("");
	addChooseValue("businessType");
	addChooseValue("businessProject");
	addChooseValue("flowSource");
	addChooseValue("flowType");
    jQuery('#add-account').modal({show:true, backdrop:"static"});
});

jQuery("#chooseExcle").click(function(){
	jQuery("input[name='filevalue']").click();
});

jQuery("#confirm").click(function(){
	jQuery("#excleForm").submit(); 
});

jQuery("#save").click(function(){
	/*var data = jQuery("#addForm").serialize();
	if(jQuery("input[name='flowId']").val() == ""){*/
	var flowAmount = jQuery("input[name='flowAmount']").val();
	if (flowAmount == "") {
		dialog("记账金额不能为空！");
		return;
	}
	var deptId = jQuery("select[name='deptId']").val();
	if (deptId == "") {
		dialog("记账部门不能为空！");
		return;
	}
	var businessType = jQuery("select[name='businessType']").val();
	if (businessType == "") {
		dialog("记账类别不能为空！");
		return;
	}
	var businessProject = jQuery("select[name='businessProject']").val();
	if (businessProject == "") {
		dialog("记账项目不能为空！");
		return;
	}
	var flowSource = jQuery("select[name='flowSource']").val();
	if (flowSource == "") {
		dialog("收支来源及去向不能为空！");
		return;
	}
	var principalId = jQuery("select[name='principalId']").val();
	if (principalId == "") {
		dialog("经手人不能为空！");
		return;
	}
	var flowType = jQuery("select[name='flowType']").val();
	if (flowType == "") {
		dialog("流水类型不能为空！");
		return;
	}
	var flowNum = jQuery("input[name='flowNum']").val();
	if (flowNum < 0) {
		dialog("分摊月数不能小于0！");
		return;
	}
	if (flowNum == "") {
		flowNum = 0;
	}
	var businessDesc = jQuery("input[name='businessDesc']").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/addStoreFlow",
		data : "flowAmount="+flowAmount+"&deptId="+deptId+"&businessType="+businessType+"&businessProject="+businessProject+"&flowSource="+flowSource+"&principalId="+principalId+"&flowType="+flowType+"&flowNum="+flowNum+"&businessDesc="+businessDesc,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				return;
			}
			dialog("提交成功,即将刷新页面...");
			window.location.href = baseUrl + "KeepAccounts/initializeStoreFlow";
		}
	});
	/*}else{
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/updateStoreFlow",
			data : data,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				dialog("修改成功,即将刷新页面...");
				jQuery('#add-account').modal("hide");
				changePage();
			}
		});
	}*/
});

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
	changePage();
}


//分页处理
function changePage(){
	var beginTimes = jQuery("#begin_time").val();
	var endTimes = jQuery("#end_time").val();
	var beginTime = parseInt(beginTimes.replace(/-/g,""));
	var endTime = parseInt(endTimes.replace(/-/g,""));
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/storeFlowList",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize + "&beginTime=" + beginTime + "&endTime=" + endTime,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var obj = e.msg;
			refreshTableData(obj.page);
			updateCome(obj.inComeAll,obj.outComeAll);
			checkPageButton();
		}
	});
}

function refreshTableData(page){
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	jQuery("#storeflowTbody").empty();
	var storeFlowList = page.results;
	for (var i = 0; i < storeFlowList.length; i++) {
		var storeflow = storeFlowList[i];
		var income ="";
		var outcome = "";
		if(storeflow.flowType == 2){
			income = storeflow.flowAmount;
		}
		if(storeflow.flowType == 1){
			outcome = storeflow.flowAmount;
		}
		jQuery("#storeflowTbody").append("<tr id='"+storeflow.flowId+"'>"
	            +"<td>"+storeflow.businessType+"</td>"
                +"<td>"+storeflow.businessProject+"</td>"
                +"<td>"+storeflow.flowSource+"</td>"
                +"<td>"+income+"</td>"
                +"<td class='expend'>"+outcome+"</td>"
                +"<td>"+storeflow.principal.name+"</td>"
                +"<td>"+storeflow.operator.name+"</td>"
                +"<td>"+storeflow.flowTime+"</td>"
                +"<td>"+storeflow.flowNum+"</td>"
                +"<td>"+storeflow.businessDesc+"</td>"
		        +"<td>"
		          +"<i class='iconfa-trash project-icon' onclick='deleteStoreflow("+storeflow.flowId+")'></i>"
		        +"</td>"
            +"</tr>");
	}
	
}

function updateCome(inComeAll,outComeAll){
	jQuery("#inComeAllID").text(inComeAll);
	jQuery("#outComeAllID").text(outComeAll);
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

jQuery("#downLond").click(function(){
	window.open(baseUrl + "KeepAccounts/downloadExcle");
});


//修改会员等级信息
function editStoreflow(obj,flowId){
	var tdobj = jQuery(obj).parents("#"+flowId).find("td");
	for(var i = 0;i < tdobj.length; i++){
		console.log(jQuery(tdobj[i]).text());
		if(i  == 0){
			jQuery("input[name='flowId']").val(flowId);
			updateChooseValue("businessType",jQuery(tdobj[i]).text());
			ajaxValue(jQuery(tdobj[i]).text());
		}
		if(i == 1){
			updateChooseValue("businessProject",jQuery(tdobj[i]).text());
		}
		if(i == 2){
			updateChooseValue("flowSource",jQuery(tdobj[i]).text());
		}
		if(i == 3){
			if(jQuery(tdobj[i]).text() != ""){
				updateChooseValue("flowType","收入");
				jQuery("input[name='flowAmount']").val(jQuery(tdobj[i]).text());
			}
		}
		if(i == 4){
			if(jQuery(tdobj[i]).text() != ""){
				updateChooseValue("flowType","支出");
				jQuery("input[name='flowAmount']").val(jQuery(tdobj[i]).text());
			}
		}
		if(i == 5){
			jQuery("input[name='principalId']").val(jQuery(tdobj[i]).text());
		}
		if(i == 6){
			jQuery("input[name='operatorId']").val(jQuery(tdobj[i]).text());
		}
		if(i == 8){
			jQuery("textarea[name='businessDesc']").val(jQuery(tdobj[i]).text());
		}
	}
	jQuery('#add-account').modal({show:true, backdrop:"static"});
}

//修改时初始化select框
function updateChooseValue(name,values){
	jQuery("select[name='"+name+"']").val(values);
	jQuery("select[name='"+name+"']").trigger("liszt:updated");
}

//新增时初始化select框
function addChooseValue(name){
	var liobj = jQuery("select[name='"+name+"']").parent().find("li");
	jQuery("select[name='"+name+"']").val(jQuery(liobj[0]).text());
	jQuery("select[name='"+name+"']").trigger("liszt:updated");
}

//动态生成项目类型
jQuery("select[name='businessType']").parent().delegate("li","click",function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	var values = jQuery(obj).text();
	if(values != ""){
		ajaxValue(values);
	}
});

//记账类别与记账项目级联
function ajaxValue(values){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/trendCodeLibrary",
		data : "codeName=" + values,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
			}else{
				jQuery("select[name='businessProject']").empty();
				jQuery("select[name='businessProject']").append("<option value=''>选择记账项目</option>");
				jQuery.each(e.msg,function(i,codeLibrary){
					jQuery("select[name='businessProject']").append("<option value='"+codeLibrary.codeName+"'>"+codeLibrary.codeName+"</option>");
				});
				jQuery("select[name='businessProject']").trigger("liszt:updated");
			}
		}
	});
}

//部门与员工级联
function employeeValue(obj){
	var values = jQuery(obj).val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/getDeptEmployee",
		data : "deptId=" + values,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
			}else{
				jQuery("select[name='principalId']").empty();
				jQuery("select[name='principalId']").append("<option value=''>选择经手人</option>");
				jQuery.each(e.msg,function(i,employees){
					jQuery("select[name='principalId']").append("<option value='"+employees.employeeId+"'>"+employees.name+"</option>");
				});
				jQuery("select[name='principalId']").trigger("liszt:updated");
			}
		}
	});
}

//删除会员等级信息
function deleteStoreflow(flowId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/deleteStoreFlow",
		data : "flowId=" + flowId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			changePage();
		}
	});
}


//查询时间区间内开支记账
jQuery("#findDate").click(function(){
	var beginTimes = jQuery("#begin_time").val();
	var endTimes = jQuery("#end_time").val();
	var beginTime = Number(beginTimes.replace("-","").replace("-",""));
	var endTime = Number(endTimes.replace("-","").replace("-",""));
	if(beginTime > endTime){
		dialog("开始时间不能大余结束时间");
	}else{
		pageNo = 1;
		changePage();
	}
});

function checkNum(obj) {  
    //检查是否是非数字值  
    if (isNaN(obj.value)) {  
        obj.value = "";  
    }   
} 