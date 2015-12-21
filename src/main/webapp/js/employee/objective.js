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
	
	querysum();
	var year=jQuery("#queryyear").val();
	var month=jQuery("#querymonth").val();
	if(month<10){
		month="0"+month
	}
	var objectiveMonth=year+"-"+month;
	var search=jQuery("#search").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objective/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize+ "&objectiveMonth=" + objectiveMonth+ "&search=" + search,
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
		var Objective = List[i];
		
		var tr = document.createElement("tr");
		tr.setAttribute("id", Objective.employeeId);
		
		var employeeCode = document.createElement("td");
		employeeCode.innerHTML = Objective.employeeCode;
		tr.appendChild(employeeCode);
		
		var name = document.createElement("td");
		name.innerHTML = Objective.name;
		tr.appendChild(name);
		
		var deptName = document.createElement("td");
		deptName.innerHTML = Objective.deptName;
		tr.appendChild(deptName);
		
		var positionName = document.createElement("td");
		positionName.innerHTML = Objective.positionName;
		tr.appendChild(positionName);
		var addData=JSON.stringify(Objective.employeeObjective);
		
		//判断是否有设置目标如果没有设置目标的话就都初始化为0
		if(Objective.employeeObjective==null){
			var totalProjectSales = document.createElement("td");
			totalProjectSales.innerHTML = "<input type='text' class='input80' name='totalProjectSales' value='"+0+"'>";
			tr.appendChild(totalProjectSales);
			
			var assignProjectSales = document.createElement("td");
			assignProjectSales.innerHTML = "<input type='text' class='input80' name='assignProjectSales' value='"+0+"'>";
			tr.appendChild(assignProjectSales);
			
			var comboSales = document.createElement("td");
			comboSales.innerHTML = "<input type='text' class='input80' name='comboSales' value='"+0+"'>";
			tr.appendChild(comboSales);
			
			var goodsSales = document.createElement("td");
			goodsSales.innerHTML = "<input type='text' class='input80' name='goodsSales' value='"+0+"'>";
			tr.appendChild(goodsSales);
			
			var chargeSales = document.createElement("td");
			chargeSales.innerHTML = "<input type='text' class='input80' name='chargeSales' value='"+0+"'>";
			tr.appendChild(chargeSales);
			
			/*var cardSales = document.createElement("td");
			cardSales.innerHTML = "<input type='text' class='input80' name='cardSales' value='"+0+"'>";
			tr.appendChild(cardSales);*/
		}else{
			var totalProjectSales = document.createElement("td");
			if(Objective.employeeObjective.totalProjectSales==null){
				totalProjectSales.innerHTML = "<input type='text' name='totalProjectSales'class='input80' value='"+0+"'>";
			}else{
				totalProjectSales.innerHTML = "<input type='text' class='input80' name='totalProjectSales' value='"+Objective.employeeObjective.totalProjectSales+"'>";
			}
			tr.appendChild(totalProjectSales);
			
			var assignProjectSales = document.createElement("td");
			if(Objective.employeeObjective.assignProjectSales==null){
				assignProjectSales.innerHTML = "<input type='text' name='assignProjectSales' class='input80' value='"+0+"'>";
			}else{
				assignProjectSales.innerHTML = "<input type='text' name='assignProjectSales' class='input80' value='"+Objective.employeeObjective.assignProjectSales+"'>";
			}
			tr.appendChild(assignProjectSales);
			
			var comboSales = document.createElement("td");
			if(Objective.employeeObjective.comboSales==null){
				comboSales.innerHTML = "<input type='text' name='comboSales' class='input80' value='"+0+"'>";
			}else{
				comboSales.innerHTML ="<input type='text' name='comboSales' class='input80' value='"+Objective.employeeObjective.comboSales+"'>";
			}
			tr.appendChild(comboSales);
			
			var goodsSales = document.createElement("td");
			if(Objective.employeeObjective.goodsSales==null){
				goodsSales.innerHTML = "<input type='text' name='goodsSales' class='input80' value='"+0+"'>";
			}else{
				goodsSales.innerHTML = "<input type='text' name='goodsSales' class='input80' value='"+Objective.employeeObjective.goodsSales+"'>";
			}
			tr.appendChild(goodsSales);
			
			var chargeSales = document.createElement("td");
			if(Objective.employeeObjective.chargeSales==null){
				chargeSales.innerHTML = "<input type='text' name='chargeSales' class='input80' value='"+0+"'>";
			}else{
				chargeSales.innerHTML = "<input type='text' name='chargeSales' class='input80' value='"+Objective.employeeObjective.chargeSales+"'>";
			}
			tr.appendChild(chargeSales);
			
			/*var cardSales = document.createElement("td");
			if(Objective.employeeObjective.cardSales==null){
				cardSales.innerHTML = "<input type='text' name='cardSales' class='input80' value='"+0+"'>";
			}else{
				cardSales.innerHTML = "<input type='text' name='cardSales' class='input80' value='"+Objective.employeeObjective.cardSales+"'>";
			}
			tr.appendChild(cardSales);*/
		}
		
		
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "iconfont icon-duihao");
		editSpan.setAttribute("onclick", "openedit(" + Objective.employeeId + ")");
		
		/*var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "iconfa-trash project-icon");
		removeSpan.setAttribute("onclick", "deleteinfo(" + Objective.objectiveId + ")");*/
		operateTd.appendChild(editSpan);
		/*operateTd.appendChild(removeSpan);*/
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
//新增员工目标
function addsave(){
	//获取相关参数 然后再把它封装在addData这个对象里面
	var addData = {};
	var employeeId=jQuery("#employeeId").val();
	if(employeeId==null||employeeId==""){
		dialog("请选择员工!");
		return;
	}
	addData["employeeId"] = employeeId;
	var year=jQuery("#year").val();
	var month=jQuery("#month").val();
	if(month<10){
		month="0"+month;
	}
	var objectiveMonth=year+"-"+month;
	addData["objectiveMonth"] = objectiveMonth;
	var totalProjectSales=jQuery("#totalProjectSales").val();
	if (isNaN(totalProjectSales)){
        dialog("劳动业绩总体目标只能为数字！");
        return;
     }
	addData["totalProjectSales"] = totalProjectSales;
	var goodsSales=jQuery("#goodsSales").val();
	if (isNaN(goodsSales)){
        dialog("商品销售目标只能为数字！");
        return;
     }
	addData["goodsSales"] = goodsSales;
	var Data=JSON.stringify(addData);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objective/action/add",
		data : "addData=" + Data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog(e.msg);
			changePage();
		}
	});
}
//修改
function openedit(id){
	var addData = {};
	addData["employeeId"] = id;
	var year=jQuery("#queryyear").val();
	var month=jQuery("#querymonth").val();
	if(month<10){
		month="0"+month
	}
	var objectiveMonth=year+"-"+month;
	addData["objectiveMonth"] = objectiveMonth;
	var totalProjectSales=jQuery("#"+id+"").children().find("input[name='totalProjectSales']").val();
	if (isNaN(totalProjectSales)){
        dialog("劳动业绩总体目标只能为数字或者小数！");
        return;
     }
	if(totalProjectSales==null||totalProjectSales==""){
		totalProjectSales=0;
	}
	addData["totalProjectSales"] = totalProjectSales;
	var assignProjectSales=jQuery("#"+id+"").children().find("input[name='assignProjectSales']").val();
	if (isNaN(totalProjectSales)){
        dialog("指定业绩目标只能为数字或者小数！");
        return;
     }
	if(assignProjectSales==null||assignProjectSales==""){
		assignProjectSales=0;
	}
	addData["assignProjectSales"] = assignProjectSales;
	var comboSales=jQuery("#"+id+"").children().find("input[name='comboSales']").val();
	if (isNaN(totalProjectSales)){
        dialog("套餐销售目标只能为数字或者小数！");
        return;
     }
	if(comboSales==null||comboSales==""){
		comboSales=0;
	}
	addData["comboSales"] = comboSales;
	var goodsSales=jQuery("#"+id+"").children().find("input[name='goodsSales']").val();
	if (isNaN(totalProjectSales)){
        dialog("商品销售目标只能为数字或者小数！");
        return;
     }
	if(goodsSales==null||goodsSales==""){
		goodsSales=0;
	}
	addData["goodsSales"] = goodsSales;
	var chargeSales=jQuery("#"+id+"").children().find("input[name='chargeSales']").val();
	if (isNaN(totalProjectSales)){
        dialog("开卡/充值目标只能为数字或者小数！");
        return;
     }
	if(chargeSales==null||chargeSales==""){
		chargeSales=0;
	}
	addData["chargeSales"] = chargeSales;
	/*var cardSales=jQuery("#"+id+"").children().find("input[name='cardSales']").val();
	if (isNaN(totalProjectSales)){
        dialog("优惠券销售目标只能为数字或者小数！");
        return;
     }*/
	var Data=JSON.stringify(addData);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objective/action/add",
		data : "addData=" + Data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog(e.msg);
			changePage();
		}
	});
}
//修改员工目标
function updatesave(){
	//获取相关参数 然后再把它封装在addData这个对象里面
	var addData = {};
	var objectiveId=jQuery('#objectiveId').val();
	addData["objectiveId"] = objectiveId;
	var employeeId=jQuery("#employeeId").val();
	if(employeeId==null||employeeId==""){
		dialog("请选择员工!");
		return;
	}
	addData["employeeId"] = employeeId;
	var year=jQuery("#year").val();
	var month=jQuery("#month").val();
	if(month<10){
		month="0"+month;
	}
	var objectiveMonth=year+"-"+month;
	addData["objectiveMonth"] = objectiveMonth;
	var totalProjectSales=jQuery("#totalProjectSales").val();
	 if (isNaN(totalProjectSales)){
	        dialog("劳动业绩总体目标只能为数字！");
	        return;
	     }
	addData["totalProjectSales"] = totalProjectSales;
	var goodsSales=jQuery("#goodsSales").val();
	if (isNaN(goodsSales)){
        dialog("商品销售目标只能为数字！");
        return;
     }
	addData["goodsSales"] = goodsSales;
	var Data=JSON.stringify(addData);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objective/action/update",
		data : "addData=" + Data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog(e.msg);
			changePage();
		}
	});
}

function quxiao(){
	jQuery("#update").hide();
	jQuery("#quxiao").hide();
	jQuery("#baocun").show();
	
	jQuery('#objectiveId').val("");
	jQuery('#employeeId').val("");
	jQuery("#employeeId").trigger("liszt:updated");
	
	var myDate = new Date();
    var y=myDate.getFullYear();
    var m=myDate.getMonth();
	jQuery('#year').val(y);
	jQuery("#year").trigger("liszt:updated");
		jQuery('#month').val(m);
		jQuery("#month").trigger("liszt:updated");
	jQuery('#totalProjectSales').val("");
	jQuery('#goodsSales').val("");
	
}

//删除目标功能
function deleteinfo(id){
	if(confirm("确认要删除该条信息吗？")){ 
		jQuery.ajax({
			type : "post",
			url : baseUrl + "objective/action/delete",
			data : "objectiveId=" + id,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					dialog(e.msg);
					return;
				}
				dialog(e.msg);
				jQuery(".member-list-table tr[id='" + id + "']").fadeOut(800).remove();
			}
		});
		}
}
//统计
function querysum(){
	var year=jQuery("#queryyear").val();
	var month=jQuery("#querymonth").val();
	if(month<10){
		month="0"+month
	}
	var objectiveMonth=year+"-"+month;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "objective/action/querysum",
		data : "objectiveMonth=" + objectiveMonth,
		dataType : "json",
		success : function(e){
			if(e.msg==null){
				jQuery(".a").text(0);
				jQuery(".b").text(0);
				jQuery(".c").text(0);
				jQuery(".d").text(0);
				jQuery(".e").text(0);
				/*jQuery(".f").text(0);*/
				
			}else{
				jQuery(".a").text(e.msg.totalsum);
				jQuery(".b").text(e.msg.assignsun);
				jQuery(".c").text(e.msg.combosum);
				jQuery(".d").text(e.msg.goodssum);
				jQuery(".e").text(e.msg.chargesum);
				/*jQuery(".f").text(e.msg.cardsum);*/
				
			}
		}
	});
}

jQuery("#downLond").click(function(){
	var year=jQuery("#queryyear").val();
	var month=jQuery("#querymonth").val();
	if(month<10){
		month="0"+month
	}
	var objectiveMonth=year+"-"+month;
	
	window.open(baseUrl + "objective/action/downloadExcle?objectiveMonth="+objectiveMonth);
});

jQuery("#downLondimport").click(function(){
	
	window.open(baseUrl + "objective/action/downloadImport");
});



jQuery("#chooseExcle").click(function(){
	jQuery("input[name='filevalue']").click();
});
jQuery("input[name='filevalue']").live("change",function(){
	var excel_file = jQuery("input[name='filevalue']").val();
	var lists = excel_file.split("\\");
	var filename = lists[lists.length - 1];
	jQuery("input[name='filename']").val(filename)
});
/*jQuery("#confirm").click(function(){
	jQuery("#excleForm").submit(); 
});*/

function UpladFile() {
    var fileObj = document.getElementById("file").files[0]; // 获取文件对象
    var FileController = baseUrl +"objective/action/importExcle";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("filevalue", fileObj);                           // 文件对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (e) {
    	/*dialog(xhr.responseText);*/
    	var json = eval("("+xhr.responseText+")");
    	if(json.code!=0){
    		dialog(json.msg);
    		return;
    	}
        dialog(json.msg);
        location.reload();
    };

    xhr.send(form);
    
}
