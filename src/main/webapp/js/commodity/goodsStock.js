jQuery(function(){
	//进货时间
    jQuery('#recodrsTime').datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y/m/d'
    });
    
  //表单校验
    jQuery("#purchaseRecodesForm").Validform({
		tiptype: 1,
		btnSubmit: "#submitBtn",//指定表单提交按钮
		btnReset: ".btn_reset",//指定表单重置按钮
		ignoreHidden: true,//当为true时对:hidden的表单元素将不做验证
		postonce: true,//开启二次提交防御,为true时，在数据成功提交后，表单将不能再继续提交
		beforeSubmit: function(curform){
			savePurchaseRecords();
			return false;
		}
	});
});

function queryByGoodsSale(goodsId){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"goodsInfo/queryByGoodsSale",
        data: "goodsId="+goodsId,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	var orderDetailDtoList = data.msg;
            	for(var i=0;i<orderDetailDtoList.length;i++){
            		var orderDetailDto = orderDetailDtoList[i];
            		var createTime = orderDetailDto.createTime;//销售日期
            		var projectPrice = orderDetailDto.projectPrice;//销售价格
            		var projectCount = orderDetailDto.projectCount;//销售数量
            		var employeeName = orderDetailDto.employeeName;//销售人员
            	}
            }else{
            	dialog("error");
            }
        }
    });
}


/**保存进货记录*/
function savePurchaseRecords(){
	var purchaseCount = jQuery("input[name='purchaseCount']").val();
	if(purchaseCount <= 0){
		dialog("进货数量必须是整数");
		return;
	}
	if(purchaseCount == ""){
		dialog("请输入进货数量");
		return;
	}
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"goodsPurchaseRecord/savePurchaseRecords",
        data: jQuery("#purchaseRecodesForm").serialize(),
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	window.location.href = baseUrl+"goodsInfo/purchase/records";
            }else{
            	dialog("error");
            }
        }
    });
}


/* ** *************************分页js代码******************************* */

/** 商品库存分页 */
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
	jQuery(".perpageL").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePage();
}

//分页处理
function changePage(brandId,categoryId){
	
	var goodsName = jQuery("input[name='goodsStockName']").val();
	var isSellProduct = jQuery("#isSellProduct").val();
	var goodsStock = jQuery("#goodsStock:checked").val();
	var warnStock = jQuery("#warnStock:checked").val();
	var data = "pageNo=" + pageNo + "&pageSize=" + pageSize;
	if(goodsName != "" && typeof(goodsName)!="undefined"){
		data = data + "&goodsName=" + goodsName;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "goodsInfo/action/list",
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
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	jQuery(".goodsStock").text(pageSize);
	jQuery(".goodsStock").append('&nbsp'+'<span class="iconfa-caret-down afont"></span>');
	var goodsInfoDtoList = page.results;
	console.log("length:"+goodsInfoDtoList.length);
	var tbody = document.createElement("tbody");
	for (var i = 0; i < goodsInfoDtoList.length; i++) {
		var goodsInfoDto = goodsInfoDtoList[i];
		console.log(goodsInfoDto);
		var tr = document.createElement("tr");
		tr.setAttribute("id", goodsInfoDto.goodsId);
		
		
		var categoryName = document.createElement("td");
		categoryName.innerHTML = goodsInfoDto.categoryName;
		tr.appendChild(categoryName);
		
		var brandName = document.createElement("td");
		brandName.innerHTML = goodsInfoDto.brandId;
		tr.appendChild(brandName);
		
		var goodsName = document.createElement("td");
		goodsName.innerHTML = goodsInfoDto.goodsName;
		tr.appendChild(goodsName);
		
		var avgCost = document.createElement("td");
		avgCost.innerHTML = goodsInfoDto.goodsPurchaseRecordDto.avgCost;
		tr.appendChild(avgCost);
		
		var prevCost = document.createElement("td");
		prevCost.innerHTML = goodsInfoDto.goodsPurchaseRecordDto.prevCost;
		tr.appendChild(prevCost);
		
		var goodsPrice = document.createElement("td");
		goodsPrice.innerHTML = goodsInfoDto.goodsPrice;
		tr.appendChild(goodsPrice);
		
		var goodsStock = document.createElement("td");
		goodsStock.innerHTML = goodsInfoDto.goodsStock;
		tr.appendChild(goodsStock);
		
		/*var thirtySales = document.createElement("td");
		thirtySales.innerHTML = goodsInfoDto.thirtySales;
		tr.appendChild(thirtySales);*/
		
		var warnStock = document.createElement("td");
		warnStock.innerHTML = goodsInfoDto.warnStock;
		tr.appendChild(warnStock);
		/*
		var supplierName = document.createElement("td");
		supplierName.innerHTML = goodsInfoDto.supplierName;
		tr.appendChild(supplierName);*/
		
		tbody.appendChild(tr);
	}
	jQuery(".goodsStock-table tbody").remove();
	jQuery(".goodsStock-table").append(tbody);
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

/** 进货记录分页 */
//上一页
function previousR(){
	if(pageNoR <= 1){
		return;
	}
	pageNoR--;
	changePageR();
}

//下一页
function nextR(){
	if(pageNoR >= totalPageR){
		return;
	}
	pageNoR++;
	changePageR();
}

//更改每页显示数量
function changePageSizeR(size){
	pageNoR = 1;
	pageSizeR = size;
	jQuery(".perpageR").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePageR();
}

//分页处理
function changePageR(){
	var data = "pageNo=" + pageNoR + "&pageSize=" + pageSizeR;
	var goodsName = jQuery("input[name='goodsName']").val();
	if(goodsName!=""&&typeof(goodsName)!="undefined")
		data = data + "&goodsName="+goodsName;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "goodsPurchaseRecord/action/list",
		data : data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			refreshTableDataR(e.msg);
			checkPageButtonR();
		}
	});
}

//刷新表格数据
function refreshTableDataR(page){
	pageNoR = page.pageNo;
	pageSizeR = page.pageSize;
	totalPageR = page.totalPage;
	jQuery(".goodspPurchase").text(pageSizeR);
	jQuery(".goodspPurchase").append('&nbsp'+'<span class="iconfa-caret-down afont"></span>');
	var goodsPurchaseRecordDtoList = page.results;
	var tbody = document.createElement("tbody");
	for (var i = 0; i < goodsPurchaseRecordDtoList.length; i++) {
		var goodsPurchaseRecordDto = goodsPurchaseRecordDtoList[i];
		
		var tr = document.createElement("tr");
		tr.setAttribute("id", goodsPurchaseRecordDto.purchaseId);
		
		var supplierName = document.createElement("td");
		supplierName.innerHTML = goodsPurchaseRecordDto.supplierName;
		tr.appendChild(supplierName);
		
		var goodsName = document.createElement("td");
		goodsName.innerHTML = goodsPurchaseRecordDto.goodsName;
		tr.appendChild(goodsName);
		
		var purchasePrice = document.createElement("td");
		purchasePrice.innerHTML = goodsPurchaseRecordDto.purchasePrice;
		tr.appendChild(purchasePrice);
		
		var purchaseCount = document.createElement("td");
		purchaseCount.innerHTML = goodsPurchaseRecordDto.purchaseCount;
		tr.appendChild(purchaseCount);
		
		var purchaseTime = document.createElement("td");
		purchaseTime.innerHTML = goodsPurchaseRecordDto.purchaseTime;
		tr.appendChild(purchaseTime);
		
		var employeeName = document.createElement("td");
		employeeName.innerHTML = goodsPurchaseRecordDto.employeeName;
		tr.appendChild(employeeName);
		
		tbody.appendChild(tr);
		
	}
	jQuery(".goodsPurchaseRecord-table tbody").remove();
	jQuery(".goodsPurchaseRecord-table").append(tbody);
}

//检查当前页码是否可以进行上一页或者下一页，并对其翻页按钮进行对应的处理
function checkPageButtonR(){
	//处理上一页
	if(pageNoR <= 1){
		jQuery("#previousPageButtonR").attr("disabled",true);  
	} else {
		jQuery("#previousPageButtonR").attr("disabled",false);  
	}
	
	//处理下一页
	if(pageNoR >= totalPageR){
		jQuery("#nextPageButtonR").attr("disabled",true);  
	} else {
		jQuery("#nextPageButtonR").attr("disabled",false);  
	}
}