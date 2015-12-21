jQuery(function(){
    jQuery('.lcs_check').lc_switch('是', '否');
    //套餐是否时间限制
    jQuery('.standard').lc_switch('是', '否');
    //是否进行身份认证
    jQuery('.isAttestation').lc_switch('是', '否');
    jQuery('.lcs_check_type').lc_switch('比例','固定');
    jQuery('body').delegate('.lcs_check_type', 'lcs-off', function() {
        jQuery(this).siblings().css("background","#fbd04d").children().css("color","#123134");
    });
    jQuery('body').delegate('.standard', 'lcs-statuschange', function() {
	    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
	    if(status == 'checked'){
	    	jQuery(this).val(1);
	    	jQuery("input[name='standard']").val(1);
	    	jQuery("input[name='validDate']").show();
	    	jQuery("input[name='validDate']").next().show();
	    }else{
	    	jQuery(this).val(0);
	    	jQuery("input[name='standard']").val(0);
	    	jQuery("input[name='validDate']").hide();
	    	jQuery("input[name='validDate']").next().hide();
	    }
	});
    jQuery('body').delegate('.isAttestation', 'lcs-statuschange', function() {
	    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
	    if(status == 'checked'){
	    	jQuery(this).val(1);
	    	jQuery("input[name='isAttestation']").val(1);
	    }else{
	    	jQuery(this).val(0);
	    	jQuery("input[name='isAttestation']").val(0);
	    }
	});
    
    
    jQuery('body').delegate('.lcs_check_type', 'lcs-statuschange', function() {
	    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
	    if(status == 'checked'){
	    	jQuery(this).val(1);
	    	jQuery(this).parent().parent().nextAll().find("input").next().text("%");
	    }else{
	    	jQuery(this).val(2);
	    	jQuery(this).parent().parent().nextAll().find("input").next().text("元");
	    }
	});
    
    //起始时间,截止时间
    jQuery('#startTime, #endTime').datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y/m/d'
    });
    
    /*套餐的展示与隐藏*/
    jQuery('.project-name').on("click", function(){
        var th = jQuery(this)
        var target = th.siblings();
        target.toggle();
        for (var i = 0; i < jQuery(this).children("i").length; i++) {
       	 if(jQuery(this).children("i").eq(i).attr("class") == "afont iconfont icon-iconfontxialaeps"){
            	jQuery(this).children("i").eq(i).attr("class","afont iconfont icon-iconfontxialaeps active");
            	return;
            } 
            if(jQuery(this).children("i").eq(i).attr("class") == "afont iconfont icon-iconfontxialaeps active"){
            	jQuery(this).children("i").eq(i).attr("class","afont iconfont icon-iconfontxialaeps");
            	return;
            }
		}
    });
    
    //表单校验
    jQuery("#comboInfoForm").Validform({
		tiptype: 1,
		btnSubmit: "#submitBtn",//指定表单提交按钮
		btnReset: ".btn_reset",//指定表单重置按钮
		ignoreHidden: true,//当为true时对:hidden的表单元素将不做验证
		postonce: true,//开启二次提交防御,为true时，在数据成功提交后，表单将不能再继续提交
		datatype: {
			"d1-2":/^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/, //校验小数，保留2位
		},
		beforeSubmit: function(curform){
			saveComboInfo();
			return false;
		}
	});
    
});

/**搜索套餐*/
function searchComboInfo(obj){
	var inputVal = jQuery(obj).prev().val();
	var comboNameObj = jQuery(".project-sublist-content");
	for(var i=0; i<comboNameObj.length; i++){
		var comboName = jQuery(comboNameObj[i]).text();
		if(inputVal != ""){
			if(comboName.indexOf(inputVal) == -1 ){
				jQuery(comboNameObj[i]).hide();
			}else{
				jQuery(comboNameObj[i]).parents(".project-sublist").show();
				jQuery(comboNameObj[i]).show();
			}
		}else{
			jQuery(comboNameObj[i]).parents(".project-sublist").show();
			jQuery(comboNameObj[i]).show();
		}
	}
}

function addCombo(obj){
	jQuery("#comboInfoForm")[0].reset();
}

/**根据id查询套餐信息*/
function queryComboInfoById(comboId,obj){
	stopBubble(obj);
	jQuery(".project-sublist-content").removeClass("active");
    jQuery(obj).addClass("active");
	jQuery.ajax({
        cache: true,
        type: "GET",
        async: false,
        url: baseUrl+"comboInfo/queryComboInfoById?comboId="+comboId,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	//锁定滚动条
            	window.scrollTo(0,0);
            	jQuery("#isUpdate").val(1);
            	var map = data.msg;
            	var comboInfo = map["comboInfo"];
            	jQuery(".dingdanzhuantai").text("正在修改套餐 : "+comboInfo.comboName);
            	
            	if(comboInfo.comboImage != null && comboInfo.comboImage != ""){
            		jQuery("img[name='headImg']").attr("src", picUrl + comboInfo.comboImage);
                	jQuery("input[name='comboImage']").val(comboInfo.comboImage);
            	}else{
            		jQuery("img[name='headImg']").attr("src", baseUrl + "images/pic_none.gif");
                	jQuery("input[name='comboImage']").val("zefun/idCard/1445060767155");
            	}
            	
            	jQuery("#comboName").val(comboInfo.comboName);
            	jQuery("#comboId").val(comboInfo.comboId);
            	jQuery("#comboDesc").val(comboInfo.comboDesc);
            	
            	//是否有时间设置
            	var standard = comboInfo.standard;
            	if(standard == 1){
            		jQuery('#standard').lcs_on();//选中
            		jQuery("input[name='standard']").val(1);
            	}else{
            		jQuery('#standard').lcs_off();//关闭
            		jQuery("input[name='standard']").val(0);
            	}
            	//是否进行身份认证
            	var isAttestation = comboInfo.isAttestation;
            	if(isAttestation == 1){
            		jQuery('#isAttestation').lcs_on();//选中
            		jQuery("input[name='isAttestation']").val(1);
            	}else{
            		jQuery('#isAttestation').lcs_off();//关闭
            		jQuery("input[name='isAttestation']").val(0);
            	}
            	
            	
            	/*jQuery("#startTime").val(comboInfo.startDate);
            	jQuery("#endTime").val(comboInfo.endDate);*/
            	jQuery("#validDate").val(comboInfo.validDate);
            	
            	jQuery("#projectAmount").val(comboInfo.projectAmount);
            	jQuery("#projectAmount").next().text(comboInfo.projectAmount+"  元");
            	jQuery("#comboSalePrice").val(comboInfo.comboSalePrice);
            	jQuery("#cashCommission").val(comboInfo.cashCommission);
            	jQuery("#cardCommission").val(comboInfo.cardCommission);
            	
            	/*将部门选定*/
            	jQuery("#deptIdSel").empty();
            	for (var i = 0; i < deptInfoListDate.length; i++) {
            		if(comboInfo.deptId == deptInfoListDate[i].deptId){
            			jQuery("#deptIdSel").append("<option selected='selected' value='"+comboInfo.deptId+"'>"+deptInfoListDate[i].deptName+"</option>");
            		}
            		else{
            			jQuery("#deptIdSel").append("<option value='"+deptInfoListDate[i].deptId+"'>"+deptInfoListDate[i].deptName+"</option>");
            		}
				}
            	jQuery("#deptIdSel").trigger("liszt:updated");
            	jQuery("#deptIdSel").chosen();
            	var isWechatSell = comboInfo.isWechatSell;
            	if(isWechatSell == 1){
            		jQuery('#isWechatSell').lcs_on();//选中
            	}else{
            		jQuery('#isWechatSell').lcs_off();//关闭
            	}
            	
            	var isDisable = comboInfo.isDisable;
            	if(isDisable == 1){
            		jQuery('#isDisable').lcs_on();//选中
            	}else{
            		jQuery('#isDisable').lcs_off();//关闭
            	}
            	
            	var commissionType = comboInfo.commissionType;
            	if(commissionType == 1){
            		jQuery('#commissionType').lcs_on();//选中
            	}else{
            		jQuery('#commissionType').lcs_off();//关闭
            	}
            	
            	jQuery("#commissionDiscount").val(comboInfo.commissionDiscount);
            	jQuery("#salesCommiission").val(comboInfo.salesCommiission);
            	jQuery("#startTime").val(comboInfo.startDate);
            	jQuery("#endTime").val(comboInfo.endDate);
            	jQuery("#comboPerformance").val(comboInfo.comboPerformance);
            	
            	jQuery("#projectSetTbody").empty();
            	//套餐关联的项目列表
            	var comboProjectList = map["comboProjectList"];
            	console.log(comboProjectList);
            	var status = 0;
            	for(var i=0; i<comboProjectList.length; i++){
            		var comboProject = comboProjectList[i];
            		var projectId = comboProject.projectId;
            		var projectPrice = comboProject.projectPrice;
            		var projectCount = comboProject.projectCount;
            		var comboPerformanceCal = comboProject.comboPerformanceCal;
            		var royaltyRate = comboProject.royaltyRate;
            		var trEl = jQuery("<tr></tr>");
            		var selectEl = addProjectSelect(projectId);
            		var td = jQuery("<td></td>");
            		td.append(selectEl);
            		trEl.append(td);
            		/*var tdSelEl = jQuery("<td><i class='iconfont icon-shouqi' onclick='showProjectSet(this);'></i></td>");
            		trEl.append(tdSelEl);*/
            		trEl.append("<td><span name='projectPriceSpan'>"+projectPrice+"</span></td>");
            		trEl.append("<td><input type='hidden' name='projectPrice' value='"+projectPrice+"'/>"+
            					"<input type='hidden' name='projectNames'/>"+
            					"<input type='text' class='input70' name='projectCounts' value='"+projectCount+"' onblur='countProjectNum();'/>"+
            					"<span class='percent-symbol'>次</span></td>");
            		trEl.append("<td><input type='text' class='input70' name='comboPerformanceCal' value='"+comboPerformanceCal+"' datatype='n' errormsg='单次服务业绩计算：请输入数字！' nullmsg='请输入单次服务业绩计算！''/><span class='percent-symbol'>元</span></td>");
            		trEl.append("<td><i class='cursor iconfa-minus' onclick='deleteProjectList(this)'></i></td>");
            		jQuery("#projectSetTbody").append(trEl);
            		jQuery("#projectSetTbody").find("select").chosen();
            	}
            	//套餐商品信息
            	var comboGoods = map["comboGoods"];
            	var tbody = jQuery("#goodsSetTbody");
            	tbody.empty();
            	for (var i = 0; i < comboGoods.length; i++) {
            		var objHtml = jQuery("#goodsSelect").html();
            		jQuery("#goodsSetTbody").append(objHtml);
            		jQuery("#goodsSetTbody").find("select").chosen();
            		jQuery("#goodsSetTbody").find('.lcs_check_type').lc_switch('比例','固定');
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("select").empty();
            		for (var j = 0; j < goodsinfos.length; j++) {
            			if(goodsinfos[j].goodsId == comboGoods[i].goodsId){
            				jQuery("#goodsSetTbody").children("tr").eq(i).find("select").append("<option selected='selected' value='"+goodsinfos[j].goodsId+"'  price='"+goodsinfos[j].goodsPrice+"'>"+ goodsinfos[j].goodsName +"</option>");
            			}else{
            				jQuery("#goodsSetTbody").children("tr").eq(i).find("select").append("<option value='"+goodsinfos[j].goodsId+"'  price='"+goodsinfos[j].goodsPrice+"'>"+ goodsinfos[j].goodsName +"</option>");
            			}
    				}
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("select").trigger("liszt:updated");
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("span[name='goodsPriceSpan']").text(comboGoods[i].goodsPrice);
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("input[name='goodsNames']").val(comboGoods[i].goodsName);
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("input[name='goodsPrice']").val(comboGoods[i].goodsPrice);
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("input[name='goodsCounts']").val(comboGoods[i].goodsCounts);
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("input[name='comboGoodsPerformanceCal']").val(comboGoods[i].comboPerformanceCal);
            		if(comboGoods[i].commissionType == 2){
            			jQuery("#goodsSetTbody").children("tr").eq(i).find("input[name='goodsCommissionTypeDate']").lcs_off();
            		}
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("input[name='goodsCommissionTypeDate']").val(comboGoods[i].commissionType);
            		jQuery("#goodsSetTbody").children("tr").eq(i).find("input[name='commissionAmount']").val(comboGoods[i].commissionAmount);
				}
            	
            	//套餐会员等级信息
            	var comboMemberLevel = map["comboMemberLevel"];
//            	jQuery("#levelId").val(comboMemberLevel.levelId);
//            	jQuery("#levelId").trigger("liszt:updated");
//            	jQuery("#validDateVip").val(comboMemberLevel.validDate);
            	
            }else{
            	dialog("error");
            }
        }
    });
}

/**项目列表下拉框*/
function addProjectSelect(projectId){
	var selectEl = jQuery('<select name="projectIds" data-placeholder="Choose a Country..." class="input-medium" onchange="changeProjectSel(this);"></select>');
	for (var i = 0; i < projectInfoList.length; i++) {
		var projectInfo = projectInfoList[i];
		if(projectId == projectInfo.projectId){
			selectEl.append("<option value='"+projectInfo.projectId+"' selected='selected' price='"+projectInfo.projectPrice+"'>"+ projectInfo.projectName +"</option>");
		}else{
			selectEl.append("<option value='"+projectInfo.projectId+"' price='"+projectInfo.projectPrice+"' >"+projectInfo.projectName+"</option>");
		}
	}
	return selectEl;
}

/**添加项目列表*/
function addProjectList(){
	var objHtml = jQuery("#projectSelect").html();
	jQuery("#projectSetTbody").append(objHtml);
	jQuery("#projectSetTbody").find("select").chosen();
	
}
/**添加商品列表*/
function addGoodsList(obj){
	var objHtml = jQuery("#goodsSelect").html();
	jQuery("#goodsSetTbody").append(objHtml);
	jQuery("#goodsSetTbody").find("select").chosen();
	jQuery("#goodsSetTbody").find('.lcs_check_type').lc_switch('比例','固定');
}

/**删除项目列表*/
function deleteProjectList(obj){
	//重新计算-项目原总价
	var projectPrice = jQuery(obj).parent().siblings().find("span[name='projectPriceSpan']").text();
	var projectCount = jQuery(obj).parent().siblings().find("input[name='projectCounts']").val();
	var amount = projectPrice * projectCount;
	var projectAmount = jQuery("#projectAmount").val();
	jQuery("#projectAmount").val(projectAmount - amount);
	jQuery(obj).parent().parent().next().remove();
	jQuery(obj).parent().parent().remove();
}
function deleteGoodsList(obj){
	jQuery(obj).parents("tr").remove();
}
/**保存套餐*/
function saveComboInfo(){
	var projectNameObj = jQuery("select[name='projectIds']").siblings().find("span");
	var projectName = "";
	for(var i=0; i<projectNameObj.length; i++){
		projectName += jQuery(projectNameObj[i]).text() + ",";
	}
	jQuery("input[name='projectNames']").val(projectName);
	var goodsNameObj = jQuery("input[name='goodsNames']");
	var goodsName = "";
	for(var i=0; i<goodsNameObj.length; i++){
		goodsName += jQuery(goodsNameObj[i]).val() + ",";
	}
	jQuery("input[name='goodsName']").val(goodsName);
	//提成方式,数据传送失败,重新获取
	var commissionTypeDate = jQuery("input[name='commissionType']").val();
	var comboIdDate = jQuery("#comboId").val();
	//assignType在修改时候,在后台接受到的值比较
	var assignTypeDate = "";
	for (var i = 0; i < jQuery("input[name='assignType']").length; i++) {
		assignTypeDate = assignTypeDate + "&assignTypeDate="+jQuery("input[name='assignType']").eq(i).val();
	}
	//商品
	var goodsCommissionType = "";
	for (var i = 0; i < jQuery("input[name='goodsCommissionTypeDate']").length; i++) {
		goodsCommissionType = goodsCommissionType + "&goodsCommissionTypeDate="+jQuery("input[name='goodsCommissionTypeDate']").eq(i).val();
	}
	// 商品折扣checkbox处理
	var data = jQuery("#comboInfoForm").serialize()+assignTypeDate+"&commissionTypeDate="+commissionTypeDate+goodsCommissionType;
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"comboInfo/saveComboInfo",
        data: data,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	//window.location.href = baseUrl+"comboInfo/view/comboInfoList";
            	dialog("保存成功");
            	var deptId = jQuery("#deptIdSel").val();
            	var comboName = jQuery("#comboName").val();
            	if(jQuery("#isUpdate").val() == 1){//修改
            		var comboId = jQuery("input[name='comboId']").val();
            		jQuery("#comboId"+comboId).remove();
            		var str = '<li class="project-sublist-content active" id="comboId'+comboId+'" onclick="queryComboInfoById('+comboId+',this);">'+comboName+
                    				'<span class="fr">'+
                    					'<i class="iconfa-trash project-icon" onclick="deleteComboInfo('+comboId+',this);"></i>'+
			                    	' </span>'+
			                  '</li>';
            		jQuery("#deptId"+deptId).nextAll().append(str);
            	}else{
            		//新增
            		var comboId = data.msg;
            		jQuery("#comboId").val(comboId);
            		jQuery("#isUpdate").val(1);
            		var str = '<li class="project-sublist-content active" id="comboId'+comboId+'" onclick="queryComboInfoById('+comboId+',this);">'+comboName+
				    				'<span class="fr">'+
				    					'<i class="iconfa-trash project-icon" onclick="deleteComboInfo('+comboId+',this);"></i>'+
				                	' </span>'+
				              '</li>';
            		jQuery("#deptId"+deptId).nextAll().append(str);
            	}
            }else{
            	dialog(data.msg);
            }
            clearInput();
        }
    });
}

/**删除套餐*/
function deleteComboInfo(comboId,obj){
	stopBubble(obj);
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"comboInfo/deleteComboInfo",
        data: "comboId="+comboId,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	//window.location.href = baseUrl+"comboInfo/view/comboInfoList";
            	dialog("删除成功");
            	jQuery("#comboId"+comboId).remove();
            	clearInput()
            	//jQuery(obj).parent().parent().parent().hide();
            }else{
            	dialog("error");
            }
            clearInput();
        }
    });
}


/**项目下拉框change事件*/
function changeProjectSel(obj){
	var projectId = jQuery(obj).val();
	if(projectId != -1){
		var price = jQuery(obj).find("option:selected").attr("price");
		jQuery(obj).parent().parent().find("input[name='projectPrice']").val(price);
		jQuery(obj).parent().parent().find("span[name='projectPriceSpan']").text(price);
	}else{
		jQuery(obj).parent().parent().find("input[name='projectPrice']").val(0.00);
		jQuery(obj).parent().parent().find("span[name='projectPriceSpan']").text("0.00");
	}
	return;
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"comboInfo/queryCommissionByProjectId",
        data: "projectId="+projectId,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	var projectCommissionTbody = jQuery(obj).parent().parent().next().find("tbody");
            	projectCommissionTbody.empty();
            	var projectCommissionList = data.msg;
            	//显示套餐所选项目提成
            	showProjectCommission(projectCommissionList,projectCommissionTbody);
            	jQuery(projectCommissionTbody).find("select").chosen();
            	jQuery(obj).parent().parent().next().find(".lcs_check_type").lc_switch('比例','固定');
            	jQuery(obj).parent().parent().next().find(".lcs_check_type").lcs_off();
        		/*jQuery(".lcs_check_type").lc_switch('比例','固定');
        		jQuery('.lcs_check_type').lcs_off();*///关闭
        		for (var i = 0; i < projectCommissionList.length; i++) {
        			if(projectCommissionList[i].assignCardType == 2){
        				jQuery(projectCommissionTbody).find(".lcs_check_type").eq(i).lcs_off();
        			}else{
        				jQuery(projectCommissionTbody).find(".lcs_check_type").eq(i).lcs_on();
        			}
				}
            }else{
            	dialog("error");
            }
        }
    });
	jQuery(obj).parent().parent().next().removeClass("hide");
}
/**商品下拉框change事件*/
function changeGoodsSel(obj){
	var goodsId = jQuery(obj).val();
	if(goodsId != -1){
		var price = jQuery(obj).find("option:selected").attr("price");
		var goodsName = jQuery(obj).find("option:selected").text();
		jQuery(obj).parent().siblings().find("input[name='goodsNames']").val(trim(goodsName));
		jQuery(obj).parent().siblings().find("input[name='goodsPrice']").val(price);
		jQuery(obj).parent().next().find("span[name='goodsPriceSpan']").text(price);
	}else{
		jQuery(obj).parent().siblings().find("input[name='goodsPrice']").val(0.00);
		jQuery(obj).parent().next().find("span[name='goodsPriceSpan']").text("0.00");
	}
	for (var i = 0; i < goodsinfos.length; i++) {
		if(goodsinfos[i].goodsId == goodsId){
			jQuery(obj).parent().parent().find("input[name='commissionAmount']").eq(0).val(goodsinfos[i].cardAmount);
			if(goodsinfos[i].commissionType == 2){
				jQuery(obj).parent().parent().find(".lcs_check_type").lcs_off();//关闭
			}else{
				jQuery(obj).parent().parent().find(".lcs_check_type").lcs_on();//关闭
			}
		}
	}
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
/**显示套餐所选项目提成*/
function showProjectCommission(projectCommissionList,projectCommissionTbody){
	for(var i=0; i<projectCommissionList.length; i++){
		var projectCommission = projectCommissionList[i];
		var trEl = jQuery("<tr></tr>");
		var tdEL = jQuery("<td><input type='hidden' name='empLevelId' value='"+projectCommission.levelId+"'/>"+projectCommission.levelName+"</td>");
//		var selectEl = jQuery("<select class='chzn-select input-medium' name='empLevelId'><select>");
//		var optionEl = jQuery("<option value='"+projectCommission.levelId+"'>"+projectCommission.levelName+"</option>");
//		selectEl.append(optionEl);
//		tdEL.append(selectEl);
		trEl.append(tdEL);
		var assignTypeEl = jQuery('<td><input type="checkbox" name="assignType" value="'+projectCommission.assignCashType+'" class="lcs_check_type" checked="checked" autocomplete="on" /></td>');
		trEl.append(assignTypeEl);
		var assignCashEL = jQuery('<td><input type="text" datatype="n" errormsg="指定提成：请输入大于1的整数！" nullmsg="请填写指定提成！" name="assignCash" value="'+projectCommission.assignCash+'" class="input30"><span class="percent-symbol">%</span></td>');
		trEl.append(assignCashEL);
		var assignCardEl = jQuery('<td><input type="text" datatype="n" errormsg="非指定提成：请输入大于1的整数！" nullmsg="请填写非指定提成！" name="assignCard" value="'+projectCommission.assignCard+'" class="input30"><span class="percent-symbol">%</span></td>');
		trEl.append(assignCardEl);
		/*var nonAssignCashEL = jQuery('<td><input type="text" datatype="n" errormsg="非指定客提成：请输入大于1的整数！" nullmsg="请填写非指定客提成！" name="nonAssignCash" value="'+projectCommission.nonAssignCash+'" class="input30"><span class="percent-symbol">%</span></td>');
		trEl.append(nonAssignCashEL);
		var nonAssignCardEl = jQuery('<td><input type="text" datatype="n" errormsg="非指定客刷卡：请输入大于1的整数！" nullmsg="请填写非指定客刷卡！" name="nonAssignCard" value="'+projectCommission.nonAssignCard+'" class="input30"><span class="percent-symbol">%</span></td>');
		trEl.append(nonAssignCardEl);*/
		projectCommissionTbody.append(trEl);
	}
	
}

/**展开或收起项目设置*/
function showProjectSet(obj){
	
	if(jQuery(obj).hasClass("icon-shouqi")){
		jQuery(obj).removeClass("icon-shouqi");
		jQuery(obj).addClass("icon-zhankai");
	}else{
		jQuery(obj).removeClass("icon-zhankai");
		jQuery(obj).addClass("icon-shouqi");
	}
	
	if (jQuery(obj).parent().parent().next().hasClass("hide")) {
		jQuery(obj).parent().parent().next().removeClass("hide");
    }else{
    	jQuery(obj).parent().parent().next().addClass("hide");
    }
}

/**会员等级下拉框*/
function changeMemberLevel(obj){
	var goodsDiscount = jQuery(obj).find("option:selected").attr("goodsDiscount");
	var projectDiscount = jQuery(obj).find("option:selected").attr("projectDiscount");
	jQuery("#goodsDiscount").text(goodsDiscount + "折");
	jQuery("#projectDiscount").text(projectDiscount  + "折");
}

/**计算项目总价*/
function countProjectNum(){
	var projectCountObj = jQuery("input[name='projectCounts']");
	var projectPriceObj = jQuery("input[name='projectPrice']");
	var projectAmount = 0;
	for(var i=0;i<projectCountObj.length;i++){
		var projectCount = jQuery(projectCountObj[i]).val();
		var projectPrice = jQuery(projectPriceObj[i]).val();
		var amount = projectCount * projectPrice;
		projectAmount += amount;
	}
	var goodsCountObj = jQuery("input[name='goodsCounts']");
	var goodsPriceObj = jQuery("input[name='goodsPrice']");
	for(var i=0;i<goodsCountObj.length;i++){
		var goodsCount = jQuery(goodsCountObj[i]).val();
		var goodsPrice = jQuery(goodsPriceObj[i]).val();
		var amount = goodsCount * goodsPrice;
		projectAmount += amount;
	}
	jQuery("#projectAmount").val(projectAmount);
	jQuery("#projectAmount").next().text(projectAmount+"  元");
}
/**计算项目总价*/
function countGoodNum(){
	var projectCountObj = jQuery("input[name='projectCounts']");
	var projectPriceObj = jQuery("input[name='projectPrice']");
	var projectAmount = 0;
	for(var i=0;i<projectCountObj.length;i++){
		var projectCount = jQuery(projectCountObj[i]).val();
		var projectPrice = jQuery(projectPriceObj[i]).val();
		var amount = projectCount * projectPrice;
		projectAmount += amount;
	}
	var goodsCountObj = jQuery("input[name='goodsCounts']");
	var goodsPriceObj = jQuery("input[name='goodsPrice']");
	for(var i=0;i<goodsCountObj.length;i++){
		var goodsCount = jQuery(goodsCountObj[i]).val();
		var goodsPrice = jQuery(goodsPriceObj[i]).val();
		var amount = goodsCount * goodsPrice;
		projectAmount += amount;
	}
	jQuery("#projectAmount").val(projectAmount);
	jQuery("#projectAmount").next().text(projectAmount+"  元");
}
/**批量新增套餐*/
function addComboList(deptId,obj){
	stopBubble(obj);
	clearInput();
	/*jQuery("#bumenxinzeng").text(jQuery(obj).parent().text());
	jQuery("input[name='saveComboListDeptId']").val(deptId);
	jQuery("#add-combo-modal").modal();*/
	
}
function addModalInputCombo(obj){
	var str = '<div><br><span>套餐名称：</span> <input type="text" placeholder="套餐名称" name="comboListName" class="mr10 ml10 input-medium"><span class="fr font-span minus"><i class="iconfa-minus" onclick="deleteModalInputCombo(this)"></i></span></div>';
	jQuery(obj).parents(".modal-body").children(".modal-btn-group").before(str);
}
function deleteModalInputCombo(obj){
	jQuery(obj).parent().parent().remove();
}
function saveComboList(){
	var deptId = jQuery("input[name='saveComboListDeptId']").val();
	var data = "deptId="+deptId;
	for (var i = 0; i < jQuery("input[name='comboListName']").length; i++) {
		if(jQuery("input[name='comboListName']").eq(i).val().length>8){
			dialog("名称不可超过8个字");
			return;
		}
	}
	for (var i = 0; i < jQuery("input[name='comboListName']").length; i++) {
		data = data+"&comboName="+jQuery("input[name='comboListName']").eq(i).val();
	}
	console.log(data);
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"comboInfo/saveComboInfos",
        data: data,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
        	if(data.code == 0){
        		for (var i = 0; i < data.msg.length; i++) {
					var comboId = data.msg[i].comboId;
					var comboName = data.msg[i].comboName;
					var str = '<li class="project-sublist-content" id="comboId'+comboId+'" onclick="queryComboInfoById('+comboId+',this);">'+comboName+
				    				'<span class="fr">'+
				    					'<i class="iconfa-trash project-icon" onclick="deleteComboInfo('+comboId+',this);"></i>'+
				                	' </span>'+
				              '</li>';
					jQuery("#deptId"+deptId).nextAll().append(str);
				}
        		jQuery("#add-combo-modal").modal('hide');
        	}
        }
    });
}
/** *************************图片上传************************* */
var qiniu = new QiniuJsSDK();

qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'comboHeadImg',       //上传选择的点选按钮，**必需**
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
				jQuery('#mask').remove();
			}
		},
		'BeforeUpload' : function(up, file) {
			jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='headImg']").attr("src",sourceLink);
			jQuery("input[name='comboImage']").val(res.key);
			jQuery('#mask').remove();
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			//dialog("UploadComplete");
			jQuery('#mask').remove();
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});

qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'editComboHeadImg',       //上传选择的点选按钮，**必需**
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
				jQuery('#mask').remove();
			}
		},
		'BeforeUpload' : function(up, file) {
			jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
		},
		'UploadProgress' : function(up, file) {
		},
		'FileUploaded' : function(up, file, info) {
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery("img[name='headImg']").attr("src",sourceLink);
			jQuery("input[name='comboImage']").val(res.key);
			jQuery('#mask').remove();
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			dialog("UploadComplete");
			jQuery('#mask').remove();
		},
		'Key' : function(up, file) {
			var key = "zefun/idCard/" + new Date().getTime();
			return key;
		}
	}
});

/*清除表单选项*/
function clearInput(){
    jQuery(':input','#projectform') 
	.not(':button, :submit, :reset, :hidden') 
	.val('') 
	.removeAttr('checked') 
	.removeAttr('selected');
    jQuery("#positionTbody").find("input[type='text']").val('');
    jQuery("#positionTbody").find("input").val("");
    /*将选中的项目解开*/
    jQuery(".project-sublist-content.nopadding.project-selected").attr("class","project-sublist-content nopadding");
    jQuery("#comboId").val('');
    jQuery("#isUpdate").val('');
    jQuery("img[name='headImg']").attr("src", baseUrl + "images/pic_none.gif");
	jQuery("input[name='comboImage']").val("zefun/idCard/1445060767155");
	
	jQuery("#projectSetTbody").empty();
	var objHtml = jQuery("#projectSelect").html();
	jQuery("#projectSetTbody").append(objHtml);
	jQuery("#projectSetTbody").find("select").chosen();
	jQuery(".project-sublist-content").removeClass("active");
	jQuery("#comboDesc").val('');    
	jQuery("#validDate").val('');
	jQuery("#cashCommission").val('');
	jQuery("#comboSalePrice").val('');
	jQuery("#cardCommission").val('');
	jQuery("#projectAmount").val('');
	jQuery("#projectAmount").next().text('');
	jQuery("#goodsSetTbody").empty();
	jQuery(".dingdanzhuantai").text("正在新增套餐");
    
}
