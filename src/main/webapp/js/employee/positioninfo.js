//
////修改岗位保存
//function editsave(positionId,positionCode,positionName,deptId){
//	
//	var positionCode=jQuery("#updatepositionCode"+positionId+"").val();
//	if(positionCode==""||positionCode==null){
//		dialog("岗位编码不能为空！");
//		return;
//	}
//	var positionName=jQuery("#"+positionId+"").val();
//	
//	if(positionName==""||positionName==null){
//		dialog("岗位名称不能为空！");
//		return;
//	}
//	if(positionName.length>10){
//		dialog("岗位名称不能超过10个字符！");
//		return;
//	}
//	jQuery.ajax({
//		type : "post",
//		url : baseUrl + "position/action/update",
//		data : "positionId="+positionId+"&positionCode="+positionCode+"&positionName="+positionName+"&deptId="+deptId,
//		dataType : "json",
//		success : function(e){
//			if(e.code != 0){
//				dialog(e.msg);
//				return;
//			}
//			jQuery('#update-gangwei-modal').modal('hide');
//			dialog(e.msg);
//			location.reload();
//		}
//	});
//}
//

////点击修改职位触发
//function editlevel(levelId,obj){
//	jQuery(".bumendiv").hide();
//	jQuery(".zhiweidiv").show();
//	jQuery(".project-selected").removeClass("project-selected");
//	jQuery(obj).addClass("project-selected");
//	jQuery.ajax({
//		type : "post",
//		url : baseUrl + "employeelevel/action/leveldetail",
//		data : "levelId=" + levelId,
//		dataType : "json",
//		success : function(e){
//			if(e.code != 0){
//				dialog("系统繁忙！");
//			}
//		//修改页面数据赋值
//			jQuery("#levelpositionId").val(e.msg.positionId);
//			jQuery("#levelpositionId").trigger("liszt:updated");
//			
//			jQuery("#updatelevelId").val(levelId);
//			//jQuery("#updatepositionId").val(e.msg.positionId);
//			jQuery("#levelName").val(e.msg.levelName);
//			
//		}
//	});
//	//职位底下的员工
//	listshow(levelId);
//}
//
////职位底下员工展示列表
//function listshow(levelId){
//	jQuery.ajax({
//		type : "post",
//		url : baseUrl + "employeelevel/action/getlevelemployee",
//		data : "levelId=" + levelId,
//		dataType : "json",
//		success : function(e){
//			var List = e.msg;
//			var tbody = document.createElement("tbody");
//			if(List.length<1){
//				
//				jQuery(".member-list-table tbody").remove();
//				var tr = document.createElement("tr");
//				var td = document.createElement("td");
//				td.innerHTML = "无";
//				tr.appendChild(td);
//				tbody.appendChild(tr);
//				jQuery(".member-list-table").append(tbody);
//				return;
//			}
//			
//			for (var i = 0; i < List.length; i++) {
//				var list = List[i];
//				
//				var tr = document.createElement("tr");
//				//tr.setAttribute("id", list.objectiveId);
//				var employeeCode = document.createElement("td");
//				employeeCode.innerHTML = list.employeeCode;
//				tr.appendChild(employeeCode);
//				
//				var name = document.createElement("td");
//				name.innerHTML = list.name;
//				tr.appendChild(name);
//				
//				var phone = document.createElement("td");
//				phone.innerHTML = list.phone;
//				tr.appendChild(phone);
//				
//				var sex = document.createElement("td");
//				sex.innerHTML = list.sex;
//				tr.appendChild(sex);
//				
//				var birthday = document.createElement("td");
//				birthday.innerHTML = list.birthday;
//				tr.appendChild(birthday);
//				
//				var entryDate = document.createElement("td");
//				entryDate.innerHTML = list.entryDate;
//				tr.appendChild(entryDate);
//				
//				var employeeStatus = document.createElement("td");
//				employeeStatus.innerHTML = list.employeeStatus;
//				tr.appendChild(employeeStatus);
//				
//				tbody.appendChild(tr);
//			}
//			jQuery(".member-list-table tbody").remove();
//			jQuery(".member-list-table").append(tbody);
//			
//		}
//	});
//}


//
//
//function updateposition(name,code,id){
//jQuery("#positionli"+id+"").hide();
//jQuery("#positionli1"+id+"").show();
//jQuery("#updatepositionCode"+id+"").val(code);
//jQuery("#updatepositionCode"+id+"").trigger("liszt:updated");
//
//} 
//function quxiao(name,code,id){
// jQuery("#positionli"+id+"").show();
// jQuery("#positionli1"+id+"").hide();
//}
//




function addDept(){
	/*jQuery("#deptinfo").show();*/
	jQuery("#bumenxinzeng").show();
	jQuery("#bumenxiugai").hide();
	jQuery("#deptCode").val("");
	jQuery("#deptCode").trigger("liszt:updated");
	jQuery("#deptName").val("");
	jQuery('#isResults').lcs_on();//选中
	
	jQuery("#bumen1").show();
	jQuery("#bumen2").hide();
	jQuery('#add-bumen-modal').modal();
	
	
}
function addsavedept(){
	var deptCode=jQuery("#deptCode").val();
	if(deptCode==""||deptCode==null){
		dialog("部门编码不能为空！");
		return;
	}
	var deptName=jQuery("#deptName").val();
	if(deptName.length>10){
		dialog("部门名称长达不能超过10个字符！");
		return;
	}
	if(deptName==""||deptName==null){
		dialog("部门名称不能为空！");
		return;
	}
	var isResults=jQuery("#isResults").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "dept/action/adddept",
		data : "deptCode="+deptCode+"&deptName="+deptName+"&isResults="+isResults,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				/*dialog(e.msg);*/
				dialog(e.msg);
				return;
			}
			dialog(e.msg);
			location.reload();
		}
	});
}
function quxiaoadddept(){
	jQuery('#add-bumen-modal').modal("hide");
}

function updatedept(id,name,code,isResults,obj){
	stopBubble(obj);
	
	jQuery("#bumenxinzeng").hide();
	jQuery("#bumenxiugai").show();
	jQuery("#bumen1").hide();
	jQuery("#bumen2").show();
	
	jQuery("#deptCode").val(code);
	jQuery("#deptCode").trigger("liszt:updated");
	jQuery("#deptName").val(name);
	jQuery("#isResults").val(isResults);
	jQuery("#updatedeptId").val(id);
	
	if(isResults == 1){
		jQuery('#isResults').lcs_on();//选中
	}else{
		jQuery('#isResults').lcs_off();//关闭
	}

	jQuery('#add-bumen-modal').modal();
	
}

function editsavedept(){
	var id=jQuery("#updatedeptId").val();
	var deptCode=jQuery("#deptCode").val();
	if(deptCode==""||deptCode==null){
		dialog("编码不能为空！");
		return;
	}
	var deptName=jQuery("#deptName").val();
	if(deptName==""||deptName==null){
		dialog("部门名称不能为空！");
		return;
	}
	if(deptName.length>10){
		dialog("部门名称长达不能超过10个字符！");
		return;
	}
	var isResults=jQuery("#isResults").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "dept/action/updatedept",
		data : "deptId="+id+"&deptCode="+deptCode+"&deptName="+deptName+"&isResults="+isResults,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				/*dialog(e.msg);*/
				dialog(e.msg);
				return;
			}
			dialog(e.msg);
			location.reload();
		}
	});
}



function deletedept(id,obj){
	stopBubble(obj);
	if(confirm("确认要删除该条部门信息吗？")){ 
		jQuery.ajax({
			type : "post",
			url : baseUrl + "dept/action/deletedept",
			data : "deptId=" + id,
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
	
}


function addPosition(id,obj){
stopBubble(obj);
jQuery("#position_deptId").val(id);
jQuery("#positionCode").val("");
jQuery("#positionCode").trigger("liszt:updated");
jQuery("#positionName").val("");
jQuery("#gangwei1").show();
jQuery("#gangwei2").hide();
jQuery("#gangwei11").show();
jQuery("#gangwei22").hide();
jQuery('#add-gangwei-modal').modal();


}


function positionsave(){

var deptId=jQuery("#position_deptId").val();

var positionCode =jQuery("#positionCode").val();
if(positionCode==""||positionCode==null){
	dialog("岗位编码不能为空！");
	return;
}
var positionName =jQuery("#positionName").val();
if(positionName==""||positionName==null){
	dialog("岗位名称不能为空！");
	return;
}
if(positionName.length>10){
	dialog("岗位名称不能超过10个字符！");
	return;
}
var isDept =jQuery("#isDept").val();

jQuery.ajax({
	type : "post",
	url : baseUrl + "position/action/add",
	data : "positionCode=" + positionCode+"&positionName="+positionName+"&deptId="+deptId+"&isDept="+isDept,
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

function quxiaoposition(){
	
	jQuery('#add-gangwei-modal').modal("hide");
}

function updateposition(deptId,name,code,id,isDept,obj){
	stopBubble(obj);
	jQuery("#gangwei1").hide();
	jQuery("#gangwei2").show();
	jQuery("#positionCode").val(code);
	jQuery("#positionCode").trigger("liszt:updated");
	jQuery("#positionName").val(name);
	jQuery("#position_deptId").val(deptId);
	jQuery("#positionId").val(id);
	jQuery("#gangwei11").hide();
	jQuery("#gangwei22").show();
	jQuery("#isDept").val(isDept);
	
	if(isDept == 1){
		jQuery('#isDept').lcs_on();//选中
	}else{
		jQuery('#isDept').lcs_off();//关闭
	}
	
	jQuery('#add-gangwei-modal').modal();

	} 
//修改岗位保存
function positioneditsave(){
	var positionId=jQuery("#positionId").val();
	var deptId=jQuery("#position_deptId").val();
	var positionCode=jQuery("#positionCode").val();
	if(positionCode==""||positionCode==null){
		dialog("岗位编码不能为空！");
		return;
	}
	
	var positionName=jQuery("#positionName").val();
	
	if(positionName==""||positionName==null){
		dialog("岗位名称不能为空！");
		return;
	}
	if(positionName.length>10){
		dialog("岗位名称不能超过10个字符！");
		return;
	}
	var isDept =jQuery("#isDept").val();
	var data = "positionCode="+positionCode+"&positionName="+positionName+"&positionId="+positionId+"&deptId="+deptId+"&isDept="+isDept;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/update",
		data : data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery('#update-gangwei-modal').modal('hide');
			dialog(e.msg);
			location.reload();
		}
	});
}
function deleteposition(positionId){
if(confirm("确认要删除该条岗位信息吗？")){ 
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/delete",
		data : "positionId=" + positionId,
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
}


//新增职位
function addlevel(id){
	jQuery('#zhiwei1').show();
	jQuery('#zhiwei2').hide();
	jQuery('#zhiwei11').show();
	jQuery('#zhiwei22').hide();
	jQuery('#zhiwei_positionid').val(id);
	jQuery("#zhiwei_positionid").trigger("liszt:updated");
	jQuery('#levelName').val("");
	jQuery('#add-zhiwei-modal').modal();
}





//新增职位信息
function levelsave(){
	//获取相关参数 然后再把它封装在addData这个对象里面
	var addData = {};
	var positionId =jQuery("#zhiwei_positionid").val();
	addData["positionId"] = positionId;
	var levelName =jQuery("#levelName").val();
	if(levelName==""||levelName==null){
		dialog("职位名称不能为空！");
		 return;
	}
	if(levelName.length>10){
		dialog("职位名称不能超过10个字符！");
		return;
	}
	addData["levelName"] = levelName;
	
	
	var addData=JSON.stringify(addData);
	//判断是否修改 当职位id为空新增   否则修改
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
				dialog(e.msg);
				location.reload();
			}
		});
	
}
function openupdatelevel(id,positionId,name){
	
	jQuery('#zhiwei1').hide();
	jQuery('#zhiwei2').show();
	jQuery('#zhiwei11').hide();
	jQuery('#zhiwei22').show();
	jQuery('#levelId').val(id);
	jQuery('#levelName').val(name);
	jQuery('#zhiwei_positionid').val(positionId);
	jQuery("#zhiwei_positionid").trigger("liszt:updated");
	jQuery('#add-zhiwei-modal').modal();
}


function updatelevel(){
	//获取相关参数 然后再把它封装在addData这个对象里面
	var addData = {};
	var positionId =jQuery("#zhiwei_positionid").val();
	addData["positionId"] = positionId;
	var levelName =jQuery("#levelName").val();
	if(levelName==""||levelName==null){
		dialog("职位名称不能为空！");
		 return;
	}
	if(levelName.length>10){
		dialog("职位名称不能超过10个字符！");
		return;
	}
	addData["levelName"] = levelName;
	var levelId =jQuery("#levelId").val();
	addData["levelId"] = levelId;
	
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
		location.reload();
	}
});	
}

function deletelevel(levelId){
if(confirm("确认要删除该条职位信息吗？")){ 
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/delete",
		data : "levelId=" + levelId,
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
}
function quxiaozhiwei(){
	jQuery('#add-zhiwei-modal').modal("hide");
}

function getlevelemployee(id){
	jQuery("#div1").hide();
	jQuery("#div2").show();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/getlevelemployee",
		data : "levelId=" + id,
		dataType : "json",
		success : function(e){
			
			var tbody = document.createElement("tbody");
			if(e.msg.length>0){
				for (var i = 0; i < e.msg.length; i++) {
					var employee = e.msg[i];
					var tr = document.createElement("tr");
					
					var employeeCode = document.createElement("td");
					employeeCode.innerHTML = employee.employeeCode;
					tr.appendChild(employeeCode);
					
					var name = document.createElement("td");
					name.innerHTML = employee.name;
					tr.appendChild(name);
					
					
					var sex = document.createElement("td");
					sex.innerHTML = employee.sex;
					tr.appendChild(sex);
					
					var deptName = document.createElement("td");
					deptName.innerHTML = employee.deptName;
					tr.appendChild(deptName);
					
					var positionName = document.createElement("td");
					positionName.innerHTML = employee.positionName;
					tr.appendChild(positionName);
					
					var levelName = document.createElement("td");
					levelName.innerHTML = employee.levelName;
					tr.appendChild(levelName);
					
					/*var baseSalaries = document.createElement("td");
					baseSalaries.innerHTML = employee.baseSalaries;
					tr.appendChild(baseSalaries);*/
					
					
					var phone = document.createElement("td");
					phone.innerHTML = employee.phone;
					tr.appendChild(phone);
					
					var userName = document.createElement("td");
					userName.innerHTML = employee.userName;
					tr.appendChild(userName);
					
					/*var entryDate = document.createElement("td");
					entryDate.innerHTML = employee.entryDate;
					tr.appendChild(entryDate);*/
					
					tbody.appendChild(tr);
				}
				jQuery(".member-list-table tbody").remove();
				jQuery(".member-list-table").append(tbody);
			}else{
				jQuery(".member-list-table tbody").remove();
				
				
			}
			
			
		}
	});
}





function UpladFile() {
    var fileObj = document.getElementById("file").files[0]; // 获取文件对象
    var FileController = baseUrl +"dept/action/importexcle";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("filevalue", fileObj);                         // 文件对象
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
        setTimeout(function(){
        	location.reload();
    	},300);
       
    };
    xhr.send(form);
}
jQuery("#downLondimport").click(function(){
	
	window.open(baseUrl + "model/dept.xls");
});