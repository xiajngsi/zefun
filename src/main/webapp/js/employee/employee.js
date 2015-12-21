

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
	var gangwei=jQuery("#querygangwei").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize+ "&search=" + search+ "&gangwei=" + gangwei,
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
		tr.setAttribute("id", employee.employeeId);
		
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
		
		var userName = document.createElement("td");
		userName.innerHTML = employee.userName;
		tr.appendChild(userName); 
		
		var phone = document.createElement("td");
		phone.innerHTML = employee.phone;
		tr.appendChild(phone);
		
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "iconfa-pencil project-icon");
		editSpan.setAttribute("onclick", "openedit(" + employee.employeeId + ")");
		
		var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "iconfa-trash project-icon");
		removeSpan.setAttribute("onclick", "deleteinfo(" + employee.employeeId + ")");
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



//点击新增时候触发
function openadd(){
	qingkong();
	//获取岗位下拉框信息
	jQuery.ajax({
		type : "post",
		url : baseUrl + "dept/action/getDeptInfo",
		data : "",
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var list=e.msg;
			var position = jQuery("select[name='deptId']");
			jQuery("#deptId").find("option").remove();
			option = jQuery("<option>").text("-选择部门-").val("");
			position.append(option);
//			var last=JSON.stringify(position); 
//            console.log("======="+last);
			for (var i = 0; i < list.length; i++) {
				//dialog(list[i].positionName);
				 option = jQuery("<option>").text(list[i].deptName).val(list[i].deptId);
				position.append(option);
				jQuery("#deptId").trigger("liszt:updated");
			}
			jQuery('#employee-add-modal').modal();
		}
		
	});
	
}
function qingkong(){
//	UE.getEditor('editor1').setContent("");
	jQuery("#headImage").val("");
	jQuery("input[name='hiddenheadImg']").val("");
	jQuery("img[name='headImg']").attr("src","");
	jQuery("#deptId").val("");
	jQuery("#deptId").trigger("liszt:updated");
	jQuery("#positionId").val("");
	jQuery("#positionId").trigger("liszt:updated");
	jQuery("#levelId").val("");
	jQuery("#levelId").trigger("liszt:updated");
	jQuery("#employeeCode").val("");
	jQuery("#employeeCode1").val("");
	jQuery("#name").val("");
	jQuery("#identityCard").val("");
	jQuery("#birthday").val("");
	jQuery("#employeeStatus").val("");
	jQuery("#employeeStatus").trigger("liszt:updated");
	jQuery("#phone").val("");
	jQuery("#entryDate").val("");
	jQuery("#address").val("");
	jQuery("#leaveDate").val("");
	jQuery("#healthCard").val("");
	jQuery("#recommendId").val("");
	jQuery("#recommendId").trigger("liszt:updated");
	jQuery("#emergencyPhone").val("");
	jQuery("#baseSalaries").val("");
	jQuery("#positionSalaries").val("");
	jQuery("#userName").val("");
	jQuery("#roleId").val("");
	jQuery("#roleId").trigger("liszt:updated");
	changedept();
	
}
//改变部门处罚岗位改变
function changedept(){
	var deptId=jQuery("#deptId").val();
	//获取岗位下拉框信息
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/queryposition",
		data : "deptId="+deptId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var list=e.msg;
			//dialog(list);
			var positionId = jQuery("select[name='positionId']");
			var level = jQuery("select[name='levelId']");
			//console.log("======="+position);
			if(deptId!=null&&deptId!=""){
				if(list.length>0){
					jQuery(positionId).children().remove();
					for (var i = 0; i < list.length; i++) {
						//dialog(list[i].levelName);
						var option = jQuery("<option>").text(list[i].positionName).val(list[i].positionId);
						positionId.append(option);
						jQuery("#positionId").trigger("liszt:updated");
						changeposition();
					}	
				}else{
					jQuery(positionId).children().remove();
					var option = jQuery("<option>").text("-无-").val("");
					positionId.append(option);
					jQuery("#positionId").trigger("liszt:updated");
					
					jQuery(levelId).children().remove();
					var option = jQuery("<option>").text("-无-").val("");
					level.append(option);
					jQuery("#levelId").trigger("liszt:updated");
				}
			}else{
				jQuery(positionId).children().remove();
				var option = jQuery("<option>").text("-请先选择部门-").val("");
				positionId.append(option);
				jQuery("#positionId").trigger("liszt:updated");
				
				
				jQuery(levelId).children().remove();
				var option = jQuery("<option>").text("-请先选择岗位-").val("");
				level.append(option);
				jQuery("#levelId").trigger("liszt:updated");
			}
		}
	});
}
//改变岗位触发职位信息改变
function changeposition(){
	var positionId=jQuery("#positionId").val();
	//获取岗位下拉框信息
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/querylevelInfo",
		data : "positionId="+positionId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var list=e.msg;
			//dialog(list);
			var level = jQuery("select[name='levelId']");
			//console.log("======="+position);
			if(positionId!=null&&positionId!=""){
				if(list.length>0){
					jQuery(levelId).children().remove();
					for (var i = 0; i < list.length; i++) {
						//dialog(list[i].levelName);
						var option = jQuery("<option>").text(list[i].levelName).val(list[i].levelId);
						level.append(option);
						jQuery("#levelId").trigger("liszt:updated");
					}	
				}else{
					jQuery(levelId).children().remove();
					var option = jQuery("<option>").text("-无-").val("");
					level.append(option);
					jQuery("#levelId").trigger("liszt:updated");
				}
			}else{
				jQuery(levelId).children().remove();
				var option = jQuery("<option>").text("-请先选择岗位-").val("");
				level.append(option);
				jQuery("#levelId").trigger("liszt:updated");
			}
		}
	});
	
	//根据选择的岗位获取岗位编码然给人员工号
	var positionId=jQuery("#positionId").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/positiondetail",
		data : "positionId="+positionId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var info=e.msg;
			if(positionId!=null && positionId!=""){
				
				//jQuery("#employeeCode").val(info.deptCode+""+info.positionCode);
				//旁边赋值个隐藏项  新增时候可以对比
				jQuery("#employeeCode1").val(info.deptCode+""+info.positionCode);
			}else{
				//jQuery("#employeeCode").val("");
				//旁边赋值个隐藏项  新增时候可以对比
				jQuery("#employeeCode1").val("");
			}
			
			
		}
	});
	
}
//人员基本资料新增
function addsave(){
	
	//获取相关参数 然后再把它封装在addData这个对象里面
	var addData = {};
	//图片
	var headImage=jQuery("input[name='hiddenheadImg']").val(); 
	
	addData["headImage"] = headImage;
	
	 //岗位id
	var deptId=jQuery("#deptId").val();
	if(deptId==null||deptId==""){
		dialog("部门不能为空！");
		return;
	}
	
	addData["deptId"] = deptId;
	 //岗位id
	var positionId=jQuery("#positionId").val();
	if(positionId==null||positionId==""){
		dialog("岗位不能为空！");
		return;
	}
		addData["positionId"] = positionId;
	//职位id
	var levelId=jQuery("#levelId").val();
	if(levelId==null||levelId==""){
		dialog("职位不能为空！")
		return;
	}
		addData["levelId"] = levelId;
	//员工编码
	var employeeCode=jQuery("#employeeCode").val();
	if(employeeCode.length!=2){
		dialog("员工编码只能是2位数字");
		return;
	}
	if(isNaN(employeeCode)){
        dialog("员工编码只能是2位数字");
        return;
     }
	var employeeCode1=jQuery("#employeeCode1").val();
	//var copyemployeeCode = employeeCode.substr(0, 2);
	addData["employeeCode"] = employeeCode1+""+employeeCode;
	//性别
	var sex=jQuery("#sex").val();
	
		addData["sex"] = sex;
	//名称
	var name=jQuery("#name").val();
	if(name==""||name==null){
		dialog("姓名不能为空！");
		return;
	}
		addData["name"] = name;
	//员工身份证
	var identityCard=jQuery("#identityCard").val();
		addData["identityCard"] = identityCard;
	//员工生日
	var birthday=jQuery("#birthday").val();
		addData["birthday"] = birthday;
	//员工状态
	var employeeStatus=jQuery("#employeeStatus").val();
		addData["employeeStatus"] = employeeStatus;
	//员工手机
	var phone=jQuery("#phone").val();
	if (isNaN(phone)){
        dialog("手机号码只能为数字！");
        return;
     }
	if(phone.length!=11){
		dialog("手机号码为11位数！");
		return;
	}
		addData["phone"] = phone;
	//员工到职日期
	var entryDate=jQuery("#entryDate").val();
		addData["entryDate"] = entryDate;
	//员工地址
	var address=jQuery("#address").val();
		addData["address"] = address;
	//员工离职日期
	var leaveDate=jQuery("#leaveDate").val();
		addData["leaveDate"] = leaveDate;
	//员工健康证
	var healthCard=jQuery("#healthCard").val();
		addData["healthCard"] = healthCard;
	//员工介绍人
	var recommendId=jQuery("#recommendId").val();
		addData["recommendId"] = recommendId;
		
	//紧急人联系电话
	var emergencyPhone=jQuery("#emergencyPhone").val();
		addData["emergencyPhone"] = emergencyPhone;
	//基本工资
	var baseSalaries=jQuery("#baseSalaries").val();
	if(baseSalaries!=null&&baseSalaries!=""){
		addData["baseSalaries"] = baseSalaries;
	}else{
		addData["baseSalaries"] = 0;
	}
	if (isNaN(baseSalaries)){
        dialog("基本工资只能为数字！");
        return;
     }
	//岗位工资
	var positionSalaries=jQuery("#positionSalaries").val();
		if(positionSalaries!=null&&positionSalaries!=""){
			addData["positionSalaries"] = positionSalaries;
		}else{
			addData["positionSalaries"] = 0;
		}
		if (isNaN(positionSalaries)){
	        dialog("岗位工资只能为数字！");
	        return;
	     }
	//员工账号
	var userName=jQuery("#userName").val();
		addData["userName"] = userName;
	//角色	
	var roleId=jQuery("#roleId").val();
	if(roleId==""||roleId==null){
		dialog("角色不能为空！");
		return;
	}
		addData["roleId"] = roleId;
	var addData=JSON.stringify(addData);
	/*	var addData=JSON.stringify(addData);
		dialog(addData);
		return;*/
		jQuery.ajax({
			type : "post",
			url : baseUrl + "employee/action/add",
			data : "addData="+addData,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				jQuery("#addemployeeid").val(e.msg);
				jQuery('#employee-add-modal').modal("hide");
				changePage();
				dialog("新增成功！");
				
			}
		});
}
var qiniu = new QiniuJsSDK();
qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'pickHeadImg',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
	max_retries : 3, //上传失败最大重试次数
	dragdrop : true, //开启可拖曳上传
	drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
	chunk_size : '2mb', //分块上传时，每片的体积
	auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
	init : {
		'FilesAdded' : function(up, files) {
			var err = false;
			plupload.each(files, function(file) {
				if(!qiniu.isImage(file.name)){
					err = true;
					return false;
				}
			});
			if(err) {
				dialog("只能上传图片文件");
				up.removeFile(files[0]);
				up.stop();
			}
		},
		'BeforeUpload' : function(up, file) {
			
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='headImg']").attr("src",sourceLink);
			jQuery("input[name='hiddenheadImg']").val(res.key);
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
		},
		'UploadComplete' : function() {
			dialog("上传成功");
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});
var qiniu1 = new QiniuJsSDK();
qiniu1.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'updatepickHeadImg',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
	max_retries : 3, //上传失败最大重试次数
	dragdrop : true, //开启可拖曳上传
	drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
	chunk_size : '2mb', //分块上传时，每片的体积
	auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
	init : {
		'FilesAdded' : function(up, files) {
			var err = false;
			plupload.each(files, function(file) {
				if(!qiniu.isImage(file.name)){
					err = true;
					return false;
				}
			});
			if(err) {
				dialog("只能上传图片文件");
				up.removeFile(files[0]);
				up.stop();
			}
		},
		'BeforeUpload' : function(up, file) {
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='updateheadImg']").attr("src",sourceLink);
			jQuery("input[name='hiddenupdateheadImage']").val(res.key);
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
		},
		'UploadComplete' : function() {
			dialog("上传成功");
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});
//选择性别时候触发的方法
function selectsex(sex){
	if(sex==1){
		jQuery('#sex').val(1);
	}else{
		jQuery('#sex').val(2);
	}
}
//增添培训行
function copyeducation(){
	  var str = jQuery("<tr>"+
      "<td>"+
      "<input name='peixunstart' type='text' class='input-small datetimepicker' id='train-start'/><span class='ml10 mr10'>至</span>"+
      "<input name='peixunend' type='text' class='input-small datetimepicker' id='train-end'/></td>"+
      "<td><input name='educationInstitution' type='text' class='input-small' /></td>"+
      "<td><input name='educationContent' type='text' class='input-medium' /></td>"+
      "<td><input name='educationPerformance' type='text' class='input-medium'/></td>"+
      "<td class='operation'>"+
      "<span class='icon-edit'></span>"+
      "<span class='iconfa-trash project-icon' onclick='deletepx(this)'></span>"+
      "</td>"+
      "</tr>");
	  jQuery(".table2 tbody").append(str);
	  /*培训开始时间*/
      jQuery("input[name='peixunstart']").datetimepicker({
          lange: "ch",
          timepicker:false,
          format:'Y-m-d'
      });
      /*培训结束时间*/
      jQuery("input[name='peixunend']").datetimepicker({
          lange: "ch",
          timepicker:false,
          format:'Y-m-d'
      });
	}

//删除培训经历
function deletepx(obj){
	jQuery(obj).parent().parent().remove();
}
//新增培训信息
function peixunsave(){
	var employeeid=jQuery('#addemployeeid').val();
	if(employeeid==""||employeeid==null){
		dialog("请先新增员工基本资料再进行此操作！");
		return;
	}
	//获取所有培训经历
	var STARTDATE = jQuery("input[name='peixunstart']");
	var ENDDATE = jQuery("input[name='peixunend']");
	var EDUCATIONINSTITUTION = jQuery("input[name='educationInstitution']");
	var EDUCATIONCONTENT = jQuery("input[name='educationContent']");
	var EDUCATIONPERFORMANCE = jQuery("input[name='educationPerformance']");
	//声明各个集合
	var startDateList = new Array();
	var endDateList = new Array();
	var educationInstitutionList = new Array();
	var educationContentList = new Array();
	var educationPerformanceList = new Array();
	var object = new Object();
	for(var i = 0; i < STARTDATE.length; i++){
		var startDate=jQuery(STARTDATE[i]).val();
		if(startDate==""||startDate==null){
			dialog("开始时间不能有为空！");
			return;
		}
		var endDate=jQuery(ENDDATE[i]).val();
		if(endDate==""||endDate==null){
			dialog("结束时间不能有为空！");
			return;
		}
		var educationInstitution=jQuery(EDUCATIONINSTITUTION[i]).val();
		if(educationInstitution==""||educationInstitution==null){
			dialog("培训机构不能有为空！");
			return;
		}
		var educationContent=jQuery(EDUCATIONCONTENT[i]).val();
		if(educationContent==""||educationContent==null){
			dialog("培训内容不能有为空！");
			return;
		}
		var educationPerformance=jQuery(EDUCATIONPERFORMANCE[i]).val();
		startDateList.push(startDate);
		endDateList.push(endDate);
		educationInstitutionList.push(educationInstitution);
		educationContentList.push(educationContent);
		educationPerformanceList.push(educationPerformance);
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/pxadd",
		data : 'employeeId='+employeeid+'&startDateList='+startDateList+'&endDateList='+endDateList+'&educationInstitutionList='+educationInstitutionList
        +'&educationContentList='+educationContentList+'&educationPerformanceList='+educationPerformanceList,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("新增成功！");
		}
	});
}
//增添工作经验行
function copyexperience(){
	  var str = jQuery("<tr>"+
            "<td><input name='gzstartDate' type='text' class='input-small datetimepicker' id='work-start'/><span class='ml10 mr10'>至</span>"+
                "<input name='gzendDate' type='text' class='input-small datetimepicker' id='work-end'/></td>"+
            "<td><input name='companyName' type='text' class='input-small'/></td>"+
            "<td><input name='positionName' type='text' class='input-small' /></td>"+
            "<td><input name='experienceDesc' type='text' class='input-xlarge' /></td>"+
            "<td class='operation'>"+
                "<span class='icon-edit'></span>"+
                "<span class='iconfa-trash project-icon' onclick='deletegz(this)'></span></td>"+
        "</tr>");
	  jQuery(".table3 tbody").append(str);
	  /*培训开始时间*/
      jQuery("input[name='gzstartDate']").datetimepicker({
          lange: "ch",
          timepicker:false,
          format:'Y-m-d'
      });
      /*培训结束时间*/
      jQuery("input[name='gzendDate']").datetimepicker({
          lange: "ch",
          timepicker:false,
          format:'Y-m-d'
      });
	}
//删除工作经历
function deletegz(obj){
	jQuery(obj).parent().parent().remove();
}

//新增工作信息
function gzjysave(){
	var employeeid=jQuery('#addemployeeid').val();
	if(employeeid==""||employeeid==null){
		dialog("请先新增员工基本资料再进行此操作！");
		return;
	}
	//获取所有工作经历
	var STARTDATE = jQuery("input[name='gzstartDate']");
	var ENDDATE = jQuery("input[name='gzendDate']");
	var COMPANYNAME = jQuery("input[name='companyName']");
	var POSITIONNAME = jQuery("input[name='positionName']");
	var EXPERIENCEDESC = jQuery("input[name='experienceDesc']");
	//声明各个集合
	var startDateList = new Array();
	var endDateList = new Array();
	var companyNameList = new Array();
	var positionNameList = new Array();
	var experienceDescList = new Array();
	var object = new Object();
	for(var i = 0; i < STARTDATE.length; i++){
		var startDate=jQuery(STARTDATE[i]).val();
		if(startDate==""||startDate==null){
			dialog("开始时间不能有为空！");
			return;
		}
		var endDate=jQuery(ENDDATE[i]).val();
		if(endDate==""||endDate==null){
			dialog("结束时间不能有为空！");
			return;
		}
		var companyName=jQuery(COMPANYNAME[i]).val();
		if(companyName==""||companyName==null){
			dialog("公司名称不能为空！");
			return;
		}
		var positionName=jQuery(POSITIONNAME[i]).val();
		if(positionName==""||positionName==null){
			dialog("岗位名称不能有为空！");
			return;
		}
		var experienceDesc=jQuery(EXPERIENCEDESC[i]).val();
		startDateList.push(startDate);
		endDateList.push(endDate);
		companyNameList.push(companyName);
		positionNameList.push(positionName);
		experienceDescList.push(experienceDesc);
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/gzjyadd",
		data : 'employeeId='+employeeid+'&startDateList='+startDateList+'&endDateList='+endDateList+'&companyNameList='+companyNameList
        +'&positionNameList='+positionNameList+'&experienceDescList='+experienceDescList,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("新增成功！");
		}
	});
}
//增添擅长行
function copyskill(){
	  var str = jQuery("<tr>"+
                        "<td><input name='categoryName' type='text' class='input-small' /></td>"+
                        "<td><input name='skillName' type='text' class='input-medium' /></td>"+
                        "<td><input name='skillDesc' type='text' class='input-xxlarge' /></td>"+
                        "<td class='operation'>"+
                            "<span class='icon-edit'></span>"+
                            "<span class='iconfa-trash project-icon' onclick='deletesc(this)'></span>"+
                        "</td>"+
                    "</tr>");
	  jQuery(".table4 tbody").append(str);
	}
//删除工作经历
function deletesc(obj){
	jQuery(obj).parent().parent().remove();
}
//新增擅长信息
function scsave(){
	var employeeid=jQuery('#addemployeeid').val();
	if(employeeid==""||employeeid==null){
		dialog("请先新增员工基本资料再进行此操作！");
		return;
	}
	//获取所有培训经历
	var CATEGORYNAME = jQuery("input[name='categoryName']");
	var SKILLNAME = jQuery("input[name='skillName']");
	var SKILLDESC = jQuery("input[name='skillDesc']");
	//声明各个集合
	var categoryNameList = new Array();
	var skillNameList = new Array();
	var skillDescList = new Array();
	var object = new Object();
	for(var i = 0; i < CATEGORYNAME.length; i++){
		var categoryName=jQuery(CATEGORYNAME[i]).val();
		if(categoryName==""||categoryName==null){
			dialog("擅长类别不能为空！");
			return;
		}
		var skillName=jQuery(SKILLNAME[i]).val();
		if(skillName==""||skillName==null){
			dialog("擅长项目不能有为空！");
			return;
		}
		var skillDesc=jQuery(SKILLDESC[i]).val();
		
		
		categoryNameList.push(categoryName);
		skillNameList.push(skillName);
		skillDescList.push(skillDesc);
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/scadd",
		data : 'employeeId='+employeeid+'&categoryNameList='+categoryNameList+'&skillNameList='+skillNameList+'&skillDescList='+skillDescList,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("新增成功！");
		}
	});
}
//打开修改页面
var employeeCode;
function openedit(id){
	
	//获取人员详情赋值在上面
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/getdetail",
		data : "employeeId="+id,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var last=JSON.stringify(e.msg); 
            //console.log("======="+last);
			
			
			changedept2(e.msg.deptId,e.msg.positionId,e.msg.levelId);
			
            //changeposition1();
            jQuery("#updatephone").val(e.msg.phone);
            jQuery("#updatesex").val(e.msg.sex);
            jQuery("#updatesex").trigger("liszt:updated");
            jQuery("#updatename").val(e.msg.name);
            jQuery("#updateidentityCard").val(e.msg.identityCard);
            jQuery("#updateemployeeStatus").val(e.msg.employeeStatus);
            jQuery("#updateemployeeStatus").trigger("liszt:updated");
            jQuery("#updatebirthday").val(e.msg.birthday);
            jQuery("#updateentryDate").val(e.msg.entryDate);
            jQuery("#updateleaveDate").val(e.msg.leaveDate);
            jQuery("#updateaddress").val(e.msg.address);
            jQuery("#updatehealthCard").val(e.msg.healthCard);
            jQuery("#updateemergencyPhone").val(e.msg.emergencyPhone);
            jQuery("#updatebaseSalaries").val(e.msg.baseSalaries);
            jQuery("#updatepositionSalaries").val(e.msg.positionSalaries);
            jQuery("#updaterecommendId").val(e.msg.recommendId);
            jQuery("#updaterecommendId").trigger("liszt:updated");
            jQuery("img[name='updateheadImg']").attr("src",picUrl+e.msg.headImage);
            jQuery("input[name='hiddenupdateheadImage']").val(e.msg.headImage)
            
            jQuery("#updateemployeeId").val(e.msg.employeeId);
            jQuery("#updateroleId").val(e.msg.roleId);
            jQuery("#updateroleId").trigger("liszt:updated");
            employeeCode=e.msg.employeeCode;
            
          //擅长信息查询初始化
        	//querysc();
        	//工作经验信息查询初始化
        	//querygz();
        	//培训经验信息查询初始化
        	//querypx();
        	//奖惩信息查询初始化
//        	queryjcjl();
        	//关系查询初始化
//        	querytjgx();
        	//派遣
//        	querypq();
        	
       }
	});
	
}

//修改是赋值岗位后会选择职位
function changelevel(levelId){
	var positionId=jQuery("#updatepositionId").val();
	//获取岗位下拉框信息
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/querylevelInfo",
		data : "positionId="+positionId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var list=e.msg;
			var level = jQuery("select[name='updatelevelId']");
			if(positionId!=null&&positionId!=""){
				if(list.length>0){
					jQuery(updatelevelId).children().remove();
					var option = jQuery("<option>").text("-无-").val("");
					level.append(option);
					for (var i = 0; i < list.length; i++) {
						var option = jQuery("<option>").text(list[i].levelName).val(list[i].levelId);
						level.append(option);
						jQuery("#updatelevelId").trigger("liszt:updated");
					}	
				}else{
					jQuery(updatelevelId).children().remove();
					var option = jQuery("<option>").text("-无-").val("");
					level.append(option);
					jQuery("#updatelevelId").trigger("liszt:updated");
				}
			}else{
				jQuery(updatelevelId).children().remove();
				var option = jQuery("<option>").text("-请先选择岗位-").val("");
				level.append(option);
				jQuery("#updatelevelId").trigger("liszt:updated");
			}
			jQuery("#updatelevelId").val(levelId)
            jQuery("#updatelevelId").trigger("liszt:updated");
		}
	});
	//根据选择的岗位获取岗位编码然给人员工号
	var positionId=jQuery("#updatepositionId").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/positiondetail",
		data : "positionId="+positionId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var info=e.msg;
			
			if(positionId!=null && positionId!=""){
				
				//jQuery("#updateemployeeCode").val(info.positionCode);
				//旁边赋值个隐藏项  新增时候可以对比
				jQuery("#updateemployeeCode1").val(info.deptCode+""+info.positionCode);
			}else{
				//jQuery("#updateemployeeCode").val("");
				//旁边赋值个隐藏项  新增时候可以对比
				jQuery("#updateemployeeCode1").val("");
			}
			
			var aa=employeeCode+"";
			jQuery("#updateemployeeCode").val(aa.substring(2,4));
		}
	});
}
//改变部门处罚岗位改变
function changedept1(){
	var deptId=jQuery("#updatedeptId").val();
	//获取岗位下拉框信息
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/queryposition",
		data : "deptId="+deptId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var list=e.msg;
			//dialog(list);
			var positionId = jQuery("select[name='updatepositionId']");
			var level = jQuery("select[name='updatelevelId']");
			//console.log("======="+positionId);
			if(deptId!=null&&deptId!=""){
				if(list.length>0){
					jQuery(positionId).children().remove();
					for (var i = 0; i < list.length; i++) {
						
						var option = jQuery("<option>").text(list[i].positionName).val(list[i].positionId);
						positionId.append(option);
						jQuery("#updatepositionId").trigger("liszt:updated");
						changeposition1();
					}	
				}else{
					jQuery(positionId).children().remove();
					var option = jQuery("<option>").text("-无-").val("");
					positionId.append(option);
					jQuery("#updatepositionId").trigger("liszt:updated");
					
					jQuery(levelId).children().remove();
					var option = jQuery("<option>").text("-无-").val("");
					level.append(option);
					jQuery("#updatelevelId").trigger("liszt:updated");
				}
			}else{
				jQuery(positionId).children().remove();
				var option = jQuery("<option>").text("-请先选择部门-").val("");
				positionId.append(option);
				jQuery("#updatepositionId").trigger("liszt:updated");
				
				
				jQuery(levelId).children().remove();
				var option = jQuery("<option>").text("-请先选择岗位-").val("");
				level.append(option);
				jQuery("#updatelevelId").trigger("liszt:updated");
			}
		}
	});
}
//改变岗位触发职位信息改变
function changeposition1(){
	var positionId=jQuery("#updatepositionId").val();
	
	//获取岗位下拉框信息
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/querylevelInfo",
		data : "positionId="+positionId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var list=e.msg;
			var level = jQuery("select[name='updatelevelId']");
			if(positionId!=null&&positionId!=""){
				if(list.length>0){
					jQuery(updatelevelId).children().remove();
					//var option = jQuery("<option>").text("-无-").val("");
					level.append(option);
					for (var i = 0; i < list.length; i++) {
						//dialog(list[i].levelName);
						var option = jQuery("<option>").text(list[i].levelName).val(list[i].levelId);
						level.append(option);
						jQuery("#updatelevelId").trigger("liszt:updated");
					}	
				}else{
					jQuery(updatelevelId).children().remove();
					var option = jQuery("<option>").text("-无-").val("");
					level.append(option);
					jQuery("#updatelevelId").trigger("liszt:updated");
				}
			}else{
				jQuery(updatelevelId).children().remove();
				var option = jQuery("<option>").text("-请先选择岗位-").val("");
				level.append(option);
				jQuery("#updatelevelId").trigger("liszt:updated");
			}
		}
	});
	//根据选择的岗位获取岗位编码然给人员工号
	var positionId=jQuery("#updatepositionId").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/positiondetail",
		data : "positionId="+positionId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			var info=e.msg;
			if(positionId!=null && positionId!=""){
				//jQuery("#updateemployeeCode").val(info.deptCode+""+info.positionCode);
				//旁边赋值个隐藏项  新增时候可以对比
				jQuery("#updateemployeeCode1").val(info.deptCode+""+info.positionCode);
			}else{
				//jQuery("#updateemployeeCode").val("");
				//旁边赋值个隐藏项  新增时候可以对比
				jQuery("#updateemployeeCode1").val("");
			}
		}
	});
}
//选择性别时候触发的方法
function uselectsex(sex){
	if(sex==1){
		jQuery('#updatesex').val(1);
	}else{
		jQuery('#updatesex').val(2);
	}
}
//人员基本资料新增
function updatesave(){
	//获取相关参数 然后再把它封装在addData这个对象里面
	var addData = {};
	var employeeId=jQuery("#updateemployeeId").val();
	addData["employeeId"] = employeeId;
	//图片
	var headImage=jQuery("input[name='hiddenupdateheadImage']").val();
	addData["headImage"] = headImage;
	
	var deptId=jQuery("#updatedeptId").val();
	if(deptId==null||deptId==""){
		dialog("部门不能为空！")
		return;
	}
	addData["deptId"] = deptId;
	 //岗位id
	var positionId=jQuery("#updatepositionId").val();
	if(positionId==null||positionId==""){
		dialog("岗位不能为空！")
		return;
	}
		addData["positionId"] = positionId;
	//职位id
	var levelId=jQuery("#updatelevelId").val();
	if(levelId==null||levelId==""){
		dialog("职位不能为空！")
		return;
	}
		addData["levelId"] = levelId;
	//员工编码
	var employeeCode=jQuery("#updateemployeeCode").val();
	
	if(employeeCode.length!=2){
		dialog("员工编码只能是2位数字");
		return;
	}
	if(isNaN(employeeCode)){
        dialog("员工编码只能是2位数字");
        return;
     }
	var employeeCode1=jQuery("#updateemployeeCode1").val();
	
	//var copyemployeeCode = employeeCode.substr(0,2);
	
	/*if(copyemployeeCode!=employeeCode1){
		dialog("员工编码前两位不能修改！");
		return;
	}*/
	addData["employeeCode"] = employeeCode1+""+employeeCode;
	//性别
	var sex=jQuery("#updatesex").val();
	
    addData["sex"] = sex;
	//名称
	var name=jQuery("#updatename").val();
	if(name==""||name==null){
		dialog("姓名不能为空！");
		return;
	}
		addData["name"] = name;
	//员工身份证
	var identityCard=jQuery("#updateidentityCard").val();
		addData["identityCard"] = identityCard;
	//员工生日
	var birthday=jQuery("#updatebirthday").val();
		addData["birthday"] = birthday;
	//员工状态
	var employeeStatus=jQuery("#updateemployeeStatus").val();
		addData["employeeStatus"] = employeeStatus;
	//员工手机
	var phone=jQuery("#updatephone").val();
	if (isNaN(phone)){
        dialog("手机号码只能为数字！");
        return;
     }
	if(phone.length!=11){
		dialog("手机号码为11位数！");
		return;
	}
		addData["phone"] = phone;
	//员工到职日期
	var entryDate=jQuery("#updateentryDate").val();
		addData["entryDate"] = entryDate;
	//员工地址
	var address=jQuery("#updateaddress").val();
		addData["address"] = address;
	//员工离职日期
	var leaveDate=jQuery("#updateleaveDate").val();
		addData["leaveDate"] = leaveDate;
	//员工健康证
	var healthCard=jQuery("#updatehealthCard").val();
		addData["healthCard"] = healthCard;
	//员工介绍人
	var recommendId=jQuery("#updaterecommendId").val();
		addData["recommendId"] = recommendId;
	//紧急人联系电话
	var emergencyPhone=jQuery("#updateemergencyPhone").val();
		addData["emergencyPhone"] = emergencyPhone;
	//基本工资
	var baseSalaries=jQuery("#updatebaseSalaries").val();
	if(baseSalaries!=null&&baseSalaries!=""){
		addData["baseSalaries"] = baseSalaries;
	}else{
		addData["baseSalaries"] = 0;
	}
	if (isNaN(baseSalaries)){
        dialog("基本工资只能为数字！");
        return;
     }
	//岗位工资
	var positionSalaries=jQuery("#updatepositionSalaries").val();
	if(positionSalaries!=null&&positionSalaries!=""){
		addData["positionSalaries"] = positionSalaries;
	}else{
		addData["positionSalaries"] = 0;
	}
	if (isNaN(positionSalaries)){
        dialog("基本工资只能为数字！");
        return;
     }
	var roleId=jQuery("#updateroleId").val();
	if(roleId==""||roleId==null){
		dialog("角色不能为空！");
		return;
	}
	addData["roleId"] = roleId;
	
	var addData=JSON.stringify(addData);
		//dialog(addData);
		//return;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "employee/action/update",
			data : "addData="+addData,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				changePage();
				dialog("修改成功！");
				jQuery('#employee-update-modal').modal("hide");
			}
		});
}
//输入手机号码就等于输入账号
function setuser(){
	var a=jQuery("#phone").val();
	jQuery("#userName").val(a);
}
//增添培训行
function ucopyeducation(){
	  var str = jQuery("<tr>"+
      "<td>"+
      "<input name='u_peixunstart' type='text' class='input-small' id='train-start'/><span class='ml10 mr10'>至</span>"+
      "<input name='u_peixunend' type='text' class='input-small' id='train-end'/></td>"+
      "<td><input name='u_educationInstitution' type='text' class='input-small' /></td>"+
      "<td><input name='u_educationContent' type='text' class='input-medium' /></td>"+
      "<td><input name='u_educationPerformance' type='text' class='input-medium'/></td>"+
      "<td class='operation'>"+
      "<span class='icon-edit'></span>"+
      "<span class='iconfa-trash project-icon' onclick='deletepx(this)'></span>"+
      "</td>"+
      "</tr>");
	  jQuery(".table22 tbody").append(str);
	  /*培训开始时间*/
      jQuery("input[name='u_peixunstart']").datetimepicker({
          lange: "ch",
          timepicker:false,
          format:'Y-m-d'
      });
      /*培训结束时间*/
      jQuery("input[name='u_peixunend']").datetimepicker({
          lange: "ch",
          timepicker:false,
          format:'Y-m-d'
      });
	}
//修改时培训信息查询
function querypx(){
	var employeeId =jQuery("#updateemployeeId").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/pxquery",
		data : "employeeId="+employeeId,
		dataType : "json",
		success : function(e){
			jQuery(".table22 tbody tr").remove();
			if(e.msg.length>0){
				for (var i = 0; i < e.msg.length; i++) {
					 var str = jQuery("<tr>"+
						      "<td>"+
						      "<input name='u_peixunstart' type='text' class='input-small' id='train-start'value='"+e.msg[i].startDate+"'/><span class='ml10 mr10'>至</span>"+
						      "<input name='u_peixunend' type='text' class='input-small' id='train-end' value='"+e.msg[i].endDate+"'/></td>"+
						      "<td><input name='u_educationInstitution' type='text' class='input-small' value='"+e.msg[i].educationInstitution+"'/></td>"+
						      "<td><input name='u_educationContent' type='text' class='input-medium' value='"+e.msg[i].educationContent+"'/></td>"+
						      "<td><input name='u_educationPerformance' type='text' class='input-medium' value='"+e.msg[i].educationPerformance+"'/></td>"+
						      "<td class='operation'>"+
						      "<span class='icon-edit'></span>"+
						      "<span class='iconfa-trash project-icon' onclick='deletepx(this)'></span>"+
						      "</td>"+
						      "</tr>");
							  jQuery(".table22 tbody").append(str);
							  /*培训开始时间*/
							  jQuery("input[name='u_peixunstart']").datetimepicker({
							      lange: "ch",
							      timepicker:false,
							      format:'Y-m-d'
							  });
							  /*培训结束时间*/
							  jQuery("input[name='u_peixunend']").datetimepicker({
							      lange: "ch",
							      timepicker:false,
							      format:'Y-m-d'
							  });
				}
			}
			
		}
	});
}
//修改培训信息
function updatepxsave(){
	var employeeid=jQuery('#updateemployeeId').val();
	//获取所有培训经历
	var STARTDATE = jQuery("input[name='u_peixunstart']");
	var ENDDATE = jQuery("input[name='u_peixunend']");
	var EDUCATIONINSTITUTION = jQuery("input[name='u_educationInstitution']");
	var EDUCATIONCONTENT = jQuery("input[name='u_educationContent']");
	var EDUCATIONPERFORMANCE = jQuery("input[name='u_educationPerformance']");
	//声明各个集合
	var startDateList = new Array();
	var endDateList = new Array();
	var educationInstitutionList = new Array();
	var educationContentList = new Array();
	var educationPerformanceList = new Array();
	
	var object = new Object();
	for(var i = 0; i < STARTDATE.length; i++){
		var startDate=jQuery(STARTDATE[i]).val();
		if(startDate==""||startDate==null){
			dialog("开始时间不能有为空！");
			return;
		}
		var endDate=jQuery(ENDDATE[i]).val();
		if(endDate==""||endDate==null){
			dialog("结束时间不能有为空！");
			return;
		}
		var educationInstitution=jQuery(EDUCATIONINSTITUTION[i]).val();
		if(educationInstitution==""||educationInstitution==null){
			dialog("培训机构不能有为空！");
			return;
		}
		var educationContent=jQuery(EDUCATIONCONTENT[i]).val();
		if(educationContent==""||educationContent==null){
			dialog("培训内容不能有为空！");
			return;
		}
		var educationPerformance=jQuery(EDUCATIONPERFORMANCE[i]).val();
		startDateList.push(startDate);
		endDateList.push(endDate);
		educationInstitutionList.push(educationInstitution);
		educationContentList.push(educationContent);
		educationPerformanceList.push(educationPerformance);
	}
	//console.log(startDateList);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/pxadd",
		data : 'employeeId='+employeeid+'&startDateList='+startDateList+'&endDateList='+endDateList+'&educationInstitutionList='+educationInstitutionList
        +'&educationContentList='+educationContentList+'&educationPerformanceList='+educationPerformanceList,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("修改成功！");
		}
	});
}
//修改时工作信息查询
function querygz(){
	var employeeId =jQuery("#updateemployeeId").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/gzjyquery",
		data : "employeeId="+employeeId,
		dataType : "json",
		success : function(e){
			jQuery(".table33 tbody tr").remove();
			if(e.msg.length>0){
				for (var i = 0; i < e.msg.length; i++) {
					var str = jQuery("<tr>"+
				            "<td><input name='u_gzstartDate' type='text' class='input-small' id='work-start' value='"+e.msg[i].startDate+"'/><span class='ml10 mr10'>至</span>"+
				                "<input name='u_gzendDate' type='text' class='input-small' id='work-end' value='"+e.msg[i].endDate+"'/></td>"+
				            "<td><input name='u_companyName' type='text' class='input-small' value='"+e.msg[i].companyName+"'/></td>"+
				            "<td><input name='u_positionName' type='text' class='input-small' value='"+e.msg[i].positionName+"'/></td>"+
				            "<td><input name='u_experienceDesc' type='text' class='input-xlarge' value='"+e.msg[i].experienceDesc+"'/></td>"+
				            "<td class='operation'>"+
				                "<span class='icon-edit'></span>"+
				                "<span class='iconfa-trash project-icon' onclick='deletegz(this)'></span></td>"+
				        "</tr>");
					  jQuery(".table33 tbody").append(str);
					  /*培训开始时间*/
				      jQuery("input[name='u_gzstartDate']").datetimepicker({
				          lange: "ch",
				          timepicker:false,
				          format:'Y-m-d'
				      });
				      /*培训结束时间*/
				      jQuery("input[name='u_gzendDate']").datetimepicker({
				          lange: "ch",
				          timepicker:false,
				          format:'Y-m-d'
				      });
				}
			}
			
		}
	});
}
//增添工作经验行
function ucopyexperience(){
	  var str = jQuery("<tr>"+
            "<td><input name='u_gzstartDate' type='text' class='input-small' id='work-start'/><span class='ml10 mr10'>至</span>"+
                "<input name='u_gzendDate' type='text' class='input-small' id='work-end'/></td>"+
            "<td><input name='u_companyName' type='text' class='input-small'/></td>"+
            "<td><input name='u_positionName' type='text' class='input-small' /></td>"+
            "<td><input name='u_experienceDesc' type='text' class='input-xlarge' /></td>"+
            "<td class='operation'>"+
                "<span class='icon-edit'></span>"+
                "<span class='iconfa-trash project-icon' onclick='deletegz(this)'></span></td>"+
        "</tr>");
	  jQuery(".table33 tbody").append(str);
	  /*培训开始时间*/
      jQuery("input[name='u_gzstartDate']").datetimepicker({
          lange: "ch",
          timepicker:false,
          format:'Y-m-d'
      });
      /*培训结束时间*/
      jQuery("input[name='u_gzendDate']").datetimepicker({
          lange: "ch",
          timepicker:false,
          format:'Y-m-d'
      });
	}
//修改工作信息
function updategzjysave(){
	var employeeid=jQuery('#updateemployeeId').val();
	//获取所有工作经历
	var STARTDATE = jQuery("input[name='u_gzstartDate']");
	var ENDDATE = jQuery("input[name='u_gzendDate']");
	var COMPANYNAME = jQuery("input[name='u_companyName']");
	var POSITIONNAME = jQuery("input[name='u_positionName']");
	var EXPERIENCEDESC = jQuery("input[name='u_experienceDesc']");
	//声明各个集合
	var startDateList = new Array();
	var endDateList = new Array();
	var companyNameList = new Array();
	var positionNameList = new Array();
	var experienceDescList = new Array();
	var object = new Object();
	for(var i = 0; i < STARTDATE.length; i++){
		var startDate=jQuery(STARTDATE[i]).val();
		if(startDate==""||startDate==null){
			dialog("开始时间不能有为空！");
			return;
		}
		var endDate=jQuery(ENDDATE[i]).val();
		if(endDate==""||endDate==null){
			dialog("结束时间不能有为空！");
			return;
		}
		var companyName=jQuery(COMPANYNAME[i]).val();
		if(companyName==""||companyName==null){
			dialog("公司名称不能为空！");
			return;
		}
		var positionName=jQuery(POSITIONNAME[i]).val();
		if(positionName==""||positionName==null){
			dialog("岗位名称不能有为空！");
			return;
		}
		var experienceDesc=jQuery(EXPERIENCEDESC[i]).val();
		startDateList.push(startDate);
		endDateList.push(endDate);
		companyNameList.push(companyName);
		positionNameList.push(positionName);
		experienceDescList.push(experienceDesc);
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/gzjyadd",
		data : 'employeeId='+employeeid+'&startDateList='+startDateList+'&endDateList='+endDateList+'&companyNameList='+companyNameList
        +'&positionNameList='+positionNameList+'&experienceDescList='+experienceDescList,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("修改成功！");
		}
	});
}
//修改时擅长信息查询
function querysc(){
	var employeeId =jQuery("#updateemployeeId").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/scquery",
		data : "employeeId="+employeeId,
		dataType : "json",
		success : function(e){
			jQuery(".table44 tbody tr").remove();
			if(e.msg.length>0){
				for (var i = 0; i < e.msg.length; i++) {
					var str = jQuery("<tr>"+
	                        "<td><input name='u_categoryName' type='text' class='input-small' value='"+e.msg[i].categoryName+"'/></td>"+
	                        "<td><input name='u_skillName' type='text' class='input-medium' value='"+e.msg[i].skillName+"'/></td>"+
	                        "<td><input name='u_skillDesc' type='text' class='input-xxlarge' value='"+e.msg[i].skillDesc+"'/></td>"+
	                        "<td class='operation'>"+
	                            "<span class='icon-edit'></span>"+
	                            "<span class='iconfa-trash project-icon' onclick='deletesc(this)'></span>"+
	                        "</td>"+
	                    "</tr>");
					jQuery(".table44 tbody").append(str);
				}
			}
			
		}
	});
}
//增添擅长行
function ucopyskill(){
	  var str = jQuery("<tr>"+
                        "<td><input name='u_categoryName' type='text' class='input-small' /></td>"+
                        "<td><input name='u_skillName' type='text' class='input-medium' /></td>"+
                        "<td><input name='u_skillDesc' type='text' class='input-xxlarge' /></td>"+
                        "<td class='operation'>"+
                            "<span class='icon-edit'></span>"+
                            "<span class='iconfa-trash project-icon' onclick='deletesc(this)'></span>"+
                        "</td>"+
                    "</tr>");
	  jQuery(".table44 tbody").append(str);
	}
//新增擅长信息
function updatescsave(){
	var employeeid=jQuery('#updateemployeeId').val();
	//获取所有培训经历
	var CATEGORYNAME = jQuery("input[name='u_categoryName']");
	var SKILLNAME = jQuery("input[name='u_skillName']");
	var SKILLDESC = jQuery("input[name='u_skillDesc']");
	//声明各个集合
	var categoryNameList = new Array();
	var skillNameList = new Array();
	var skillDescList = new Array();
	var object = new Object();
	for(var i = 0; i < CATEGORYNAME.length; i++){
		var categoryName=jQuery(CATEGORYNAME[i]).val();
		if(categoryName==""||categoryName==null){
			dialog("擅长类别不能为空！");
			return;
		}
		var skillName=jQuery(SKILLNAME[i]).val();
		if(skillName==""||skillName==null){
			dialog("擅长项目不能有为空！");
			return;
		}
		var skillDesc=jQuery(SKILLDESC[i]).val();
		
		
		categoryNameList.push(categoryName);
		skillNameList.push(skillName);
		skillDescList.push(skillDesc);
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/scadd",
		data : 'employeeId='+employeeid+'&categoryNameList='+categoryNameList+'&skillNameList='+skillNameList+'&skillDescList='+skillDescList,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("新增成功！");
		}
	});
}
//奖惩记录查询
function queryjcjl(){
	var addData = {};
	var employeeid=jQuery('#updateemployeeId').val();
	addData["employeeId"] = employeeid;
	var querytype=jQuery('#querytype').val();
	if(querytype!=null&&querytype!=""){
		addData["type"] = querytype;
	}
	
	var addData=JSON.stringify(addData);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/action/getrewardsrecord",
		data : 'addData='+addData,
		dataType : "json",
		success : function(e){
			jQuery(".table55 tbody").empty();
			for (var i = 0; i < e.msg.length; i++) {
				var str = jQuery("<tr>"+
			            "<td>"+e.msg[i].operateTime+"</td>"+
			            "<td>"+e.msg[i].des+"</td>"+
			        "</tr>");
			        jQuery(".table55 tbody").append(str);
			}
		}
	});
}
//推荐关系查询
function querytjgx(){
	var employeeid=jQuery('#updateemployeeId').val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/queryrygx",
		data : 'employeeId='+employeeid,
		dataType : "json",
		success : function(e){
			var selfinfo=e.msg.selfinfo;
			var referrerinfo=e.msg.referrerinfo;
			var recommendedlist=e.msg.recommendedlist;
			jQuery(".table661 tbody").empty();
			if(selfinfo!=null){
				var str = jQuery("<tr>"+
	            "<td>本人</td>"+
	            "<td><div class='detail-img'>"+
	                    "<img src='"+picUrl+selfinfo.headImage+"' alt=''/></div>"+
	                "<div class='detail-content'>"+
	                    "<span>姓名："+selfinfo.name+"</span>"+
	                    "<span>工号："+selfinfo.employeeCode+"</span>"+
	                    "<span>门店：南山店</span></div></td>"+
	            "<td></td><td></td>"+
	            "</tr>");
			        jQuery(".table661 tbody").append(str);
			}
			jQuery(".table662 tbody").empty();
			if(referrerinfo!=null){
				var str = jQuery("<tr>"+
	            "<td>推荐人</td>"+
	            "<td><div class='detail-img'>"+
	                    "<img src='"+picUrl+referrerinfo.headImage+"' alt=''/></div>"+
	                "<div class='detail-content'>"+
	                    "<span>姓名："+referrerinfo.name+"</span>"+
	                    "<span>工号："+referrerinfo.employeeCode+"</span>"+
	                    "<span>门店：南山店</span></div></td>"+
	            "<td></td><td></td>"+
	            "</tr>");
			        jQuery(".table662 tbody").append(str);
			}
			jQuery(".table663 tbody").empty();
			if(recommendedlist.length>0){
				var trTD = jQuery("<tr></tr>");
				trTD.append("<td>被他推荐的人</td>");
				for (var i = 0; i < recommendedlist.length; i++) {
					trTD.append("<td><div class='detail-img'>"+
				                    "<img src='"+picUrl+recommendedlist[i].headImage+"' alt=''/></div>"+
				                    "<div class='detail-content'>"+
				                    "<span>姓名："+recommendedlist[i].name+"</span>"+
				                    "<span>工号："+recommendedlist[i].employeeCode+"</span>"+
				                    "<span>门店：南山店</span></div></td>");
						}
				jQuery(".table663 tbody").append(trTD);
				}
		}
	});
}
//查询派遣
function querypq(){
	
	var employeeId=jQuery('#updateemployeeId').val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/getpqlist",
		data : 'employeeId='+employeeId,
		dataType : "json",
		success : function(e){
			jQuery(".table77 tbody").empty();
			for (var i = 0; i < e.msg.length; i++) {
				var str = jQuery("<tr>"+
			            "<td>"+e.msg[i].pStoreName+"</td>"+
			            "<td>"+e.msg[i].dispatchTime+"</td>"+
			        "</tr>");
			        jQuery(".table77 tbody").append(str);
			}
		}
	});
}
function addcanse(){
	jQuery('#employee-add-modal').modal('hide');
}
function updatecanse(){
	jQuery('#employee-update-modal').modal('hide');
}

//改变部门处罚岗位改变
function changedept2(id,id2,id3){
	
	//获取岗位下拉框信息
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/queryposition",
		data : "deptId="+id,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			jQuery("#updatedeptId").val(id);
			jQuery("#updatedeptId").trigger("liszt:updated");
			var list=e.msg;
			var positionId = jQuery("select[name='updatepositionId']");
				if(list.length>0){
					jQuery(positionId).children().remove();
					for (var i = 0; i < list.length; i++) {
						
						var option = jQuery("<option>").text(list[i].positionName).val(list[i].positionId);
						positionId.append(option);
						jQuery("#updatepositionId").trigger("liszt:updated");
						
					}	
					
				}
				jQuery("#updatepositionId").val(id2);
				jQuery("#updatepositionId").trigger("liszt:updated");
				changelevel(id3);
				setTimeout(function(){
	        		jQuery('#employee-update-modal').modal();
	        	},100);
		}
	});
}

function pqsave(){
	var pStoreId=jQuery("#storeId").val();
	var dispatchTime=jQuery("#dispatch-start1").val();
	var employeeId=jQuery('#updateemployeeId').val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/adddispatch",
		data : "pStoreId="+pStoreId+"&dispatchTime="+dispatchTime+"&employeeId="+employeeId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			dialog(e.msg);
			
		}
	});
}

jQuery("#downLond").click(function(){
	window.open(baseUrl + "employee/action/downloadExcle");
});


function deleteinfo(id){
	if(confirm("确认要删除该条信息吗？")){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "employee/action/delete",
			data : "employeeId=" + id,
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

jQuery("#downLondimport").click(function(){
	
	//window.open(baseUrl + "employee/action/downloadImport");
	window.open(baseUrl + "model/employee.xls");
});


function UpladFile() {
    var fileObj = document.getElementById("file").files[0]; // 获取文件对象
    var FileController = baseUrl +"employee/action/importExcle"; 
    // 接收上传文件的后台地址
    
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
        changePage();
    };

    xhr.send(form);
    
}

function gangwei(id){
	
	jQuery("#querygangwei").val(id);
	changePage();
}

/**
 * 新增个人简介
 */
function savedesc(){
	var c1 = UE.getEditor('editor1').getContent();
	c1 = c1.replace(/%/g, "%25");  
	c1 = c1.replace(/\&/g, "%26");  
	c1 = c1.replace(/\+/g, "%2B");
	var employeeId =jQuery("#updateemployeeId").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/savedesc",
		data : 'employeeId='+employeeId+'&desc='+c1,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery('#employee-update-modal').modal("hide");
			dialog("操作成功！");
		}
	});
}
