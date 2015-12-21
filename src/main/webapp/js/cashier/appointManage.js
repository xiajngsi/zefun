checkPageButton();

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
function deleteAppoint(appointId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "appoint/action/cancel",
		data : "appointmentId=" + appointId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery(".appoint-table tr[id='" + appointId + "']").fadeOut(500).remove();
		}
	});
}

//分页处理
function changePage(){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "appoint/action/list",
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
	
	var appointList = page.results;
	var tbody = document.createElement("tbody");
	for (var i = 0; i < appointList.length; i++) {
		var appoint = appointList[i];
		var tr = document.createElement("tr");
		tr.setAttribute("id", appoint.appointmentId);
		
		var employeeTd = document.createElement("td");
		employeeTd.innerHTML = appoint.employeeInfo.employeeCode + " " + appoint.employeeInfo.name;
		tr.appendChild(employeeTd);
		
		var nameTd = document.createElement("td");
		nameTd.innerHTML = appoint.name;
		tr.appendChild(nameTd);
		
		var phoneTd = document.createElement("td");
		phoneTd.innerHTML = appoint.phone;
		tr.appendChild(phoneTd);
		
		var projectTd = document.createElement("td");
		projectTd.innerHTML = appoint.projectInfo.projectName;
		tr.appendChild(projectTd);
		
		var dateTd = document.createElement("td");
		dateTd.innerHTML = appoint.appointmentDate;
		tr.appendChild(dateTd);
		
		var timeTd = document.createElement("td");
		timeTd.innerHTML = appoint.appointmentTime;
		tr.appendChild(timeTd);
        
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "iconfont icon-dizhixiugai");
		editSpan.setAttribute("onclick", "editAppoint(" + appoint.appointmentId + ")");
		operateTd.appendChild(editSpan);
		
		var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "iconfont icon-xx ml10");
		removeSpan.setAttribute("onclick", "deleteAppoint(" + appoint.appointmentId + ")");
		operateTd.appendChild(removeSpan);
		
		tr.appendChild(operateTd);
		tbody.appendChild(tr);
	}
	jQuery(".appoint-table tbody").remove();
	jQuery(".appoint-table").append(tbody);
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