

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
	//自定义查询条件
	//
	jQuery.ajax({
		type : "post",
		url : baseUrl + "attendance/view/attendance",
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
		var employee = List[i];
		var tr = document.createElement("tr");
		//tr.setAttribute("id", employee.employeeId);
		
		var employeeCode = document.createElement("td");
		employeeCode.innerHTML = employee.employeeCode;
		tr.appendChild(employeeCode);
		
		var name = document.createElement("td");
		name.innerHTML = employee.name;
		tr.appendChild(name);
		
		var qiandao = document.createElement("td");
		qiandao.innerHTML = "签到";
		tr.appendChild(qiandao);
		
		var signinTime = document.createElement("td");
		signinTime.innerHTML = employee.signinTime;
		tr.appendChild(signinTime);
		
		var lateTime = document.createElement("td");
		lateTime.innerHTML = employee.lateTime;
		tr.appendChild(lateTime);
		
		var qiantui = document.createElement("td");
		qiantui.innerHTML = "签退";
		tr.appendChild(qiantui);
		
		
		var signoutTime = document.createElement("td");
		signoutTime.innerHTML = employee.signoutTime;
		tr.appendChild(signoutTime);
		
		var earlyTime = document.createElement("td");
		earlyTime.innerHTML = employee.earlyTime;
		tr.appendChild(earlyTime);
		
		var date = document.createElement("td");
		date.innerHTML = employee.date;
		tr.appendChild(date);
		
		
		
		var operateTd = document.createElement("td");
/*		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "icon-edit");
		editSpan.setAttribute("onclick", "openedit(" + employee.employeeId + ")");
		
		var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "icon-remove-sign ml30");
		removeSpan.setAttribute("onclick", "deleteinfo(" + employee.employeeId + ")");
		operateTd.appendChild(editSpan);
		operateTd.appendChild(removeSpan);*/
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


