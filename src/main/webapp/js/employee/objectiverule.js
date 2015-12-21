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

	var search=jQuery("#search").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objectiverule/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize+ "&search=" + search,
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
		var list = List[i];
		var tr = document.createElement("tr");
		tr.setAttribute("id", list.ruleId);
		
		var projectName = document.createElement("td");
		projectName.innerHTML = list.projectName;
		tr.appendChild(projectName);
		
		var contrastTypeName = document.createElement("td");
		contrastTypeName.innerHTML = list.contrastTypeName+list.section+"%";
		tr.appendChild(contrastTypeName);
		
		var rewardsName = document.createElement("td");
		if(list.type==2){
			rewardsName.innerHTML = list.rewardsName+" "+"薪资的"+list.money+"%";
		}else{
			rewardsName.innerHTML = list.rewardsName+" "+list.money;
		}
		tr.appendChild(rewardsName);
		
		
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "iconfa-pencil project-icon");
		editSpan.setAttribute("onclick", "openedit(" + list.ruleId + ")");
		
		var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "iconfa-trash project-icon");
		removeSpan.setAttribute("onclick", "deleteinfo(" + list.ruleId + ")");
		operateTd.appendChild(editSpan);
		operateTd.appendChild(removeSpan);
		tr.appendChild(operateTd);
		tbody.appendChild(tr);
	}
	jQuery(".collect-money-table1 tbody").remove();
	jQuery(".collect-money-table1").append(tbody);
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

function saveinfo(){
	var addData = {};
	var objectiveProject=jQuery("#objectiveProject").val();
	addData["objectiveProject"] = objectiveProject;
	
	var choiceType=jQuery("#choiceType").val();
	addData["choiceType"] = choiceType;
	var contrastType=jQuery("#contrastType").val();
	addData["contrastType"] = contrastType;
	
	var section=jQuery("#section").val();
	if(section==""||section==null){
		dialog("区间比例不能为空！");
		return;
	}
	addData["section"] = section;
	
	var rewards=jQuery("#rewards").val();
	addData["rewards"] = rewards;
	
	var type=jQuery("#type").val();
	addData["type"] = type;
	
	var money=jQuery("#money").val();
	if(money==""||money==null){
		dialog("奖励金额不能为空！");
		return;
	}
	addData["money"] = money;
	
	var addData=JSON.stringify(addData);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objectiverule/action/addObjectiverule",
		data : "addData=" + addData,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog(e.msg);
			location.reload();
		}
	});
}
function openedit(id){
	jQuery("#quxiao").show();
	jQuery("#update").show();
	jQuery("#saveinfo").hide();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objectiverule/action/getruledetail",
		data : "ruleId=" + id,
		dataType : "json",
		success : function(e){
			jQuery("#updateruleId").val(id);
			jQuery("#objectiveProject").val(e.msg.objectiveProject);
			jQuery("#objectiveProject").trigger("liszt:updated");
			jQuery("#choiceType").val(e.msg.choiceType);
			jQuery("#choiceType").trigger("liszt:updated");
			jQuery("#contrastType").val(e.msg.contrastType);
			jQuery("#contrastType").trigger("liszt:updated");
			jQuery("#section").val(e.msg.section);
			
			jQuery("#rewards").val(e.msg.rewards);
			if(e.msg.rewards == 1){
        		jQuery('#rewards').lcs_on();//选中
        	}else{
        		jQuery('#rewards').lcs_off();//关闭
        	}
			jQuery("#type").val(e.msg.type);
			jQuery("#type").trigger("liszt:updated");
			jQuery("#money").val(e.msg.money);
			showtype();
		}
	});
}	
function quxiao(){
	jQuery("#quxiao").hide();
	jQuery("#update").hide();
	jQuery("#saveinfo").show();
	jQuery("#updateruleId").val("");
	jQuery("#objectiveProject").val(1);
	jQuery("#objectiveProject").trigger("liszt:updated");
	jQuery("#contrastType").val(1);
	jQuery("#contrastType").trigger("liszt:updated");
	jQuery("#section").val("");
	jQuery("#rewards").val(1);
	jQuery('#rewards').lcs_on();//选中
	jQuery("#type").val(1);
	jQuery("#type").trigger("liszt:updated");
	jQuery("#money").val("");
}
function updateinfo(){
	var addData = {};
	var updateruleId=jQuery("#updateruleId").val();
	addData["ruleId"] = updateruleId;
	
	var objectiveProject=jQuery("#objectiveProject").val();
	addData["objectiveProject"] = objectiveProject;
	
	var choiceType=jQuery("#choiceType").val();
	addData["choiceType"] = choiceType;
	
	var contrastType=jQuery("#contrastType").val();
	addData["contrastType"] = contrastType;
	
	var section=jQuery("#section").val();
	if(section==""||section==null){
		dialog("区间比例不能为空！");
		return;
	}
	addData["section"] = section;
	
	var rewards=jQuery("#rewards").val();
	addData["rewards"] = rewards;
	
	var type=jQuery("#type").val();
	addData["type"] = type;
	
	var money=jQuery("#money").val();
	if(money==""||money==null){
		dialog("奖励金额不能为空！");
		return;
	}
	addData["money"] = money;
	
	var addData=JSON.stringify(addData);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objectiverule/action/updateObjectiverule",
		data : "addData=" + addData,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog(e.msg);
			location.reload();
		}
	});
}
function deleteinfo(id){
	if(confirm("确认要删除该条信息吗？")){ 
		jQuery.ajax({
			type : "post",
			url : baseUrl + "objectiverule/action/deleteObjectiverule",
			data : "ruleId=" + id,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				dialog(e.msg);
				jQuery(".collect-money-table1 tr[id='" + id + "']").fadeOut(800).remove();
			}
		});
		}
}

function showtype(){
	var choiceType=jQuery("#choiceType").val();
	if(choiceType==1){
		jQuery("#type1").show();
		jQuery("#type2").hide();
	}else{
		jQuery("#type2").show();
		jQuery("#type1").hide();
	}
	
}
