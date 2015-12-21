jQuery(function(){
    
	//表单校验
    jQuery("#supplierInfoForm").Validform({
		tiptype: 1,
		btnSubmit: "#submitBtn",//指定表单提交按钮
		btnReset: ".btn_reset",//指定表单重置按钮
		ignoreHidden: true,//当为true时对:hidden的表单元素将不做验证
		postonce: true,//开启二次提交防御,为true时，在数据成功提交后，表单将不能再继续提交
		ajaxPost:false,
		datatype: {
			"d1-2":/^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/, //校验小数，保留2位
		},
		beforeSubmit: function(curform){
			saveSupplierInfo();
			return false;
		}
	});
	
});

/**查询供应商信息*/
function querySupplierInfo(){
	var supplierInfoName = jQuery("#inputSupplierInfoName").val();
	changePage(supplierInfoName);
}

/**保存供应商信息*/
function saveSupplierInfo(){
	var supplyBrandObj = jQuery("#supplyBrand").find("option:selected");
	var supplyBrandStr = "";
	for(var i=0; i<supplyBrandObj.length; i++){
		var supplyBrand = jQuery(supplyBrandObj[i]).text();
		supplyBrandStr += supplyBrand + ",";
	}
	
	var supplyCategoryObj = jQuery("#supplyCategory").find("option:selected");
	var supplyCategoryStr = "";
	for(var i=0; i<supplyCategoryObj.length; i++){
		var supplyCategory = jQuery(supplyCategoryObj[i]).text();
		supplyCategoryStr += supplyCategory + ",";
	}
	
	supplyBrandStr = supplyBrandStr.substring(0,supplyBrandStr.length-1);
	supplyCategoryStr = supplyCategoryStr.substring(0,supplyCategoryStr.length-1);
	
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"supplierInfo/saveSupplierInfo",
        data: jQuery("#supplierInfoForm").serialize() + "&supplyBrandStr="+supplyBrandStr + "&supplyCategoryStr="+supplyCategoryStr,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	window.location.href = baseUrl+"supplierInfo/view/supplierInfoList";
            }else{
            	dialog("error");
            }
        }
    });
}

/**显示编辑供应商模态框*/
function showEditSupplierInfo(supplierId){
	jQuery.ajax({
        cache: true,
        type: "GET",
        async: false,
        url: baseUrl+"supplierInfo/querySupplierInfoById?supplierId="+supplierId,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	var supplierInfo = data.msg;
            	jQuery("#supplierName").val(supplierInfo.supplierName);
            	jQuery("#supplierId").val(supplierInfo.supplierId);
            	
            	var ls = supplierInfo.supplyBrand.split(",");
            	var listBrand = new Array();
            	for (var i = 0; i < ls.length; i++) {
            		listBrand.push(ls[i]);
				}
            	jQuery("#supplyBrand").val(listBrand);
            	jQuery("#supplyBrand").trigger("liszt:updated");
            	
            	ls = supplierInfo.supplyCategory.split(",");
            	var listCategory = new Array();
            	for (var i = 0; i < ls.length; i++) {
            		listCategory.push(ls[i]);
				}
            	jQuery("#supplyCategory").val(listCategory);
            	jQuery("#supplyCategory").trigger("liszt:updated");
            	
            	jQuery("#linkName").val(supplierInfo.linkName);
            	jQuery("#linkPhone").val(supplierInfo.linkPhone);
            	
            	jQuery("#supplierInfoForm").find(".cancelBtn").remove();
            	var lastTrDiv = jQuery("#supplierInfoForm").find("tbody tr").last().find("div");
            	/*lastTrDiv.append("<button class='btn btn-primary input100 cancelBtn' onclick='cancelUpdate(this);'>取消修改</button>");*/
            	
            }else{
            	dialog("error");
            }
        }
    });
}

function cancelUpdate(obj){
	jQuery("#supplierInfoForm")[0].reset();
	jQuery("#supplierId").val("");
	jQuery(obj).remove();
}


/**删除供应商*/
function deleteSupplierInfo(supplierId){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"supplierInfo/deleteSupplierInfo",
        data: "supplierId="+ supplierId,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	window.location.href = baseUrl+"supplierInfo/view/supplierInfoList";
            }else{
            	dialog("error");
            }
        }
    });
}

/** *************************分页js代码******************************* */
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
	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePage();
}

//分页处理
function changePage(supplierName){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "supplierInfo/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize + "&supplierName="+supplierName,
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
	
	var supplierInfoList = page.results;
	var tbody = document.createElement("tbody");
	for (var i = 0; i < supplierInfoList.length; i++) {
		var supplierInfo = supplierInfoList[i];
		
		var tr = document.createElement("tr");
		tr.setAttribute("id", supplierInfo.supplierId);
		
		var supplierName = document.createElement("td");
		supplierName.innerHTML = supplierInfo.supplierName;
		tr.appendChild(supplierName);
		
		var supplyBrand = document.createElement("td");
		supplyBrand.innerHTML = supplierInfo.supplyBrandStr;
		tr.appendChild(supplyBrand);
		
		var supplyCategory = document.createElement("td");
		supplyCategory.innerHTML = supplierInfo.supplyCategoryStr;
		tr.appendChild(supplyCategory);
		
		var linkName = document.createElement("td");
		linkName.innerHTML = supplierInfo.linkName;
		tr.appendChild(linkName);
		
		var linkPhone = document.createElement("td");
		linkPhone.innerHTML = supplierInfo.linkPhone;
		tr.appendChild(linkPhone);
		
		/*var isIncludeCost = document.createElement("td");
		isIncludeCost.setAttribute("class", "red");
		isIncludeCost.innerHTML = supplierInfo.isIncludeCost;
		tr.appendChild(isIncludeCost);*/
		
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("i");
		editSpan.setAttribute("class", "iconfa-pencil project-icon");
		editSpan.setAttribute("onclick", "showEditSupplierInfo(" + supplierInfo.supplierId + ")");
		
		var removeSpan = document.createElement("i");
		removeSpan.setAttribute("class", "iconfa-trash project-icon");
		removeSpan.setAttribute("onclick", "deleteSupplierInfo(" + supplierInfo.supplierId + ")");
		operateTd.appendChild(editSpan);
		operateTd.appendChild(removeSpan);
		tr.appendChild(operateTd);
		tbody.appendChild(tr);
	}
	jQuery(".table-striped tbody").remove();
	jQuery(".table-striped").append(tbody);
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