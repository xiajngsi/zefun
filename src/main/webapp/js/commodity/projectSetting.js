jQuery(function(){
    jQuery('#account-time').datetimepicker({
        yearOffset:0,
        lang:'ch',
        timepicker:true,
        format:'d/m/Y',
        formatDate:'Y/m/d',
        minDate:'-1970/01/02', // yesterday is minimum date
        maxDate:'+1970/01/02' // and tommorow is maximum date calendar
    });

    jQuery(".show-more").on("click",function(){
        var detailInfo = jQuery(".more-condition-table");
        if(detailInfo.is(':visible')){
            detailInfo.hide();
        }else{
            detailInfo.show();
        }
    });

    /*项目的展示与隐藏*/
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

    /*去掉选中元素的顶部的线条*/
    var deleteLine = function(){
        var selectedElement = jQuery(".project-category-selected");
         var elementIndex = selectedElement.index() - 1;
        var parent  = selectedElement.parent(".project-sublist");
        parent.find("li:eq(" + elementIndex + ")").css("border-bottom", "0px");

    }
    deleteLine();
    
    jQuery('.lcs_check').lc_switch('是', '否');
    jQuery('#isGiftCash').lcs_off();
    
    jQuery('body').delegate('.lcs_check', 'lcs-statuschange', function() {
	    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
	    if(status == 'checked'){
	    	jQuery(this).val(1);
	    }else{
	    	jQuery(this).val(0);
	    }
	});
    //职位中提成方式
    jQuery('body').delegate('[name="assignType"]', 'change', function() {
	    var value = jQuery(this).val();
	    if(value == '1'){
	    	jQuery(this).closest('tr').find('.percent-symbol').text("%");
	    }else{
	    	jQuery(this).closest('tr').find('.percent-symbol').text("元");
	    }
	    jQuery(this).closest('tr').find('.percent-symbol').eq(2).text("元");
	});
    //方式
    jQuery('body').delegate('[name="stepPerformanceType"]', 'change', function() {
	    var value = jQuery(this).val();
	    if(value == '1'){
	    	jQuery(this).closest('tr').find('.percent-symbol').text("%");
	    }else{
	    	jQuery(this).closest('tr').find('.percent-symbol').text("元");
	    }
	});
    
    //表单校验
    jQuery("#projectform").Validform({
		tiptype: 1,
		btnSubmit: "#submitBtn",//指定表单提交按钮
		btnReset: ".btn_reset",//指定表单重置按钮
		ignoreHidden: true,//当为true时对:hidden的表单元素将不做验证
		postonce: true,//开启二次提交防御,为true时，在数据成功提交后，表单将不能再继续提交
		ajaxPost:false,
		beforeSubmit: function(curform){
			saveProjectInfo();
			return false;
		}
	});
    
    /**初始化轮牌数据*/
    for (var i = 0; i < deptMahjongList.length; i++) {
    	var dept = deptMahjongList[i];
    	var mahjongList = dept.mahjongLevelList
    	deptMahjong[dept.deptId] = mahjongList;
    	
    	for (var j = 0; j < mahjongList.length; j++) {
			var mahjong = mahjongList[j];
    		mahjongLevel[mahjong.shiftMahjongId] = mahjong.employeeLevelList;
		}
    	try {
    		if(i == 0){
        		currentDeptId = dept.deptId;
        		currentMahjongList = mahjongList;
        		currentLevelList = mahjongList[0].employeeLevelList;
        	}
		} catch (e) {
			
		}
	}
});

/**
 * 预约步骤,控制
 */
jQuery('#projectStepTbody').delegate('.isDisableApp', 'lcs-on', function() {
	jQuery(".isDisableApp[value='1']").lcs_off();
});

var currentDeptId = null;
var currentMahjongList = null;
var currentLevelList = new Array();
var deptMahjong = new Object();
var mahjongLevel = new Object();


/**搜索项目*/
function searchProject(obj){
	var inputVal = jQuery(obj).prev().val();
	var projectNameObj = jQuery(".project-sublist-content");
	for(var i=0; i<projectNameObj.length; i++){
		var projectName = jQuery(projectNameObj[i]).text();
		if(inputVal != ""){
			if(projectName.indexOf(inputVal) == -1 ){
				jQuery(projectNameObj[i]).hide();
			}else{
				jQuery(projectNameObj[i]).parents(".project-sublist.dept").show();
				jQuery(projectNameObj[i]).show();
			}
		}else{
			jQuery(projectNameObj[i]).parents(".project-sublist.dept").show();
			jQuery(projectNameObj[i]).show();
		}
	}
}
/**添加多类别modal*/
function addModalInputCategory(obj){
	var str = '<div><br><input type="hidden"><span>类别名称：</span> <input type="text" placeholder="类别名称" name="categoryListName" class="mr10 ml10 input-medium"><span class="fr font-span minus"><i class="iconfa-minus" onclick="deleteModalInputCategory(this)"></i></span></div>';
	jQuery(obj).parents(".modal-body").children(".modal-btn-group").before(str);
}
/**删除多类别添加中的一列*/
function deleteModalInputCategory(obj){
	jQuery(obj).parent().parent().remove();
}
/**保存类别集合*/
jQuery("#saveCategoryList").on("click",function(){
	var data = "data = data";
	for (var i = 0; i < jQuery("input[name='categoryListName']").length; i++) {
		if(jQuery("input[name='categoryListName']").val().length>8){
			dialog("字数请少于8个");
			return;
		}else{
			data = data + "&categoryName=" + jQuery("input[name='categoryListName']").eq(i).val();
		}
	}
	var deptId = jQuery("input[name='saveCategoryListDeptId']").val();
	data = data + "&deptId=" + deptId;
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"project/saveProjectCategorys",
        data: data,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function(data) {
            if(data.code == 0){
            	dialog("保存成功");
            	var categorys = data.msg;
            	for (var i = 0; i < categorys.length; i++) {
            		var str = '<ul class="project-sublist category" style="display: block;">'+
			        			'<li class="project-sublist-title" id="categoryId'+categorys[i].categoryId+'">'+
									'<div>'+
										'<i class="afont iconfont icon-iconfontxialaeps"></i>'+
										'<span class="categoryNameSpan">&nbsp;'+ categorys[i].categoryName +'</span>'+
										'<span class="fr">'+
										  '<i class="iconfa-plus project-icon" onclick="addProjectsList('+deptId+','+categorys[i].categoryId+',this)"></i>&nbsp;&nbsp;'+
					                      '<i class="iconfa-pencil project-icon" onclick="showEditCategory(this);"></i>'+
					                      '<i class="iconfa-trash project-icon" onclick="deleteProjectCategory('+categorys[i].categoryId+','+deptId+',this);"></i>'+
					                   '</span>'+
					               '</div>'+
					               '<div style="display:none">'+
					                	'<input type="text" id="editCategoryName" value="'+categorys[i].categoryName+'" style="width: 70%;">'+
					                	'<i class="iconfa-plus-sign project-icon" onclick="editCategory('+categorys[i].categoryId+','+deptId+',this);"></i>'+
					                	'<i class="iconfa-trash project-icon" onclick="cancelEditCategory(this);"></i>'+
					                '</div>'+
								'</li>'+
							  '</ul>';
            		jQuery("#deptId"+deptId).next().append(str);
            		//维护js中的属性值
                	maintenance(deptId,categorys[i].categoryName,categorys[i].categoryId,1);
				}
            	jQuery("#add-category-modal").modal('hide');
            }else{
            	dialog("error");
            }
        }
	});
});

/**添加类别*/
function addCategory(deptId,obj){
	jQuery("#add-category-modal .iconfa-minus").closest("div").remove();
	jQuery("[name='categoryListName']").val('');
	jQuery("#bumenxinzeng").text(jQuery(obj).parent().text());
	jQuery("input[name='saveCategoryListDeptId']").val(deptId);
	jQuery("#add-category-modal").modal();
	//维护上下箭头的方向
    for (var i = 0; i < jQuery(obj).parents('.project-name').children("i").length; i++) {
   	 if(jQuery(obj).parents('.project-name').children("i").eq(i).attr("class") == "afont iconfont icon-iconfontxialaeps"){
   		jQuery(obj).parents('.project-name').children("i").eq(i).attr("class","afont iconfont icon-iconfontxialaeps active");
     }
   	 if(jQuery(obj).parents('.project-name').children("i").eq(i).attr("class") == "afont iconfont icon-iconfontxialaeps active"){
   		jQuery(obj).parents('.project-name').children("i").eq(i).attr("class","afont iconfont icon-iconfontxialaeps");
     }
	}
    stopBubble(obj);
    return;
    //控制下层元素的显示与隐藏
	jQuery(obj).parents(".project-name").next().show();
	stopBubble(obj);
	jQuery("input[name='categoryName']").val("");
	jQuery(obj).parent().parent().find("ul").find("#addCategoryLi").show();
}

/**保存类别*/
function saveCategory(deptId,obj){
	var categoryName = jQuery(obj).prev().val();
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"project/saveProjectCategory",
        data: "categoryName="+ categoryName + "&deptId="+deptId,
        success: function(data) {
            if(data.code == 0){
            	//window.location.href = baseUrl+"project/view/projectList";
            	dialog("保存成功");
            	var categoryId = data.msg;
            	var str = '<ul class="project-sublist category" style="display: block;">'+
			        			'<li class="project-sublist-title" id="categoryId'+categoryId+'">'+
							'<div>'+'<i class="afont iconfont icon-iconfontxialaeps"></i>'+
								'<span class="categoryNameSpan">'+ categoryName +'</span>'+
								'<span class="fr">'+   
			                      '<i class="iconfa-pencil project-icon" onclick="showEditCategory(this);"></i>'+
			                      '<i class="iconfa-trash project-icon" onclick="deleteProjectCategory('+categoryId+','+deptId+',this);"></i>'+
			                   '</span>'+
			               '</div>'+
			               '<div style="display:none">'+
			                	'<input type="text" id="editCategoryName" value="'+categoryName+'" style="width: 70%;">'+
			                	'<i class="iconfa-plus-sign project-icon" onclick="editCategory('+categoryId+','+deptId+',this);"></i>'+
			                	'<i class="iconfa-trash project-icon" onclick="cancelEditCategory(this);"></i>'+
			                '</div>'+
						'</li>'+
						'</ul>';
            	jQuery(obj).parent().parent().append(str);
            	jQuery(obj).parent().hide();
            	//维护js中的属性值
            	maintenance(deptId,categoryName,categoryId,1);
            }else{
            	dialog("error");
            }
        }
	});
}

/**更改部门,换取数据*/
function changeDept(select,deptIds){
	var deptId;
	if(deptIds!=null){
		deptId = deptIds;
	}else{
		deptId = jQuery(select).val();
	}
	/*for (var i = 0; i < deptMahjongList.length; i++) {
		if(deptMahjongList[i].deptId == deptId){
			currentMahjongList = deptMahjongList[i].mahjongLevelList;
			jQuery("select[name='shiftMahjongId']").empty();
			for (var j = 0; j < deptMahjongList[i].mahjongLevelList.length; j++) {
				jQuery("select[name='shiftMahjongId']").append("<option value="+deptMahjongList[i].mahjongLevelList[j].shiftMahjongId+">"+deptMahjongList[i].mahjongLevelList[j].shiftMahjongName+"</option>");
			}
        	jQuery("select[name='shiftMahjongId']").trigger("liszt:updated");
		}
	}*/
	//更新类别
	reloadSelectCategory(deptId);
	//更新轮牌信息
	changeCurretShifStep(deptId);
}
//更改部门,更新轮牌
function changeCurretShifStep(deptId){
	
	//清空轮牌信息,只剩下一个
	var table = jQuery("#projectStepTbody");
	for (var i = 0; i < table.length; i++) {
		var tr = jQuery(table.eq(i).children("tr[name='projectStepTr']"));
		if(tr.length > 1){
			for (var j = 1; j < tr.length; j++) {
				tr.eq(j).remove();
			}
		}
	}
	//更新轮牌下的职位信息
	for (var j = 0; j < jQuery("#xinxiugaishezhibuzhou").children("table").eq(0).children("tbody").find("tr").length; j++) {
		jQuery("#xinxiugaishezhibuzhou").children("table").eq(0).children("tbody").find("tr").eq(j).remove();
	}
	jQuery("#xinxiugaishezhibuzhou").children("table").eq(0).find(".cursor.iconfa-plus").click();
	jQuery("#xinxiugaishezhibuzhou").children("p").eq(0).nextAll().remove();
	jQuery("input[name='zhiweinum']").val(1);
	
	var stept = jQuery("select[name='shiftMahjongId']");
	var mahjongLevelList;
	for (var i = 0; i < deptMahjongList.length; i++) {
		if (deptMahjongList[i].deptId == deptId){
			mahjongLevelList = deptMahjongList[i].mahjongLevelList;
		}
	}
	for (var i = 0; i < stept.length; i++) {
		var select = stept.eq(i);
		jQuery(select).empty();
		for (var j = 0; j < mahjongLevelList.length; j++) {
			jQuery(select).append("<option value="+mahjongLevelList[j].shiftMahjongId+">"+mahjongLevelList[j].shiftMahjongName+"</option>");
		}
		jQuery(select).trigger("liszt:updated");
		
		var number = jQuery(select).parents("tr").children("td").eq(0).find("span[name='projectStepNumber']").text();
		var tempList = mahjongLevel[jQuery(select).val()];
		if(tempList==null){
			//如果该部门下没有任何轮牌信息,将职位中的内容全部清空
			jQuery("#xinxiugaishezhibuzhou").children("table").eq(0).children("tbody").empty();
			jQuery("input[name='zhiweinum']").val(0);
			return;
		}
		var s = jQuery("#xinxiugaishezhibuzhou").children("table").eq(number-1).find("select[name='empLevelId']");
		jQuery(s).empty();
		for (var j = 0; j < tempList.length; j++) {
			var level = tempList[j];
			jQuery(s).append("<option value='" + level.levelId + "'>" + level.levelName + "</option>");
		}
		jQuery(s).trigger("liszt:updated");
	}
	
	//触发一次更新轮牌步骤的操作
	changeMahjongStep(jQuery("#projectStepTbody").children("tr").eq(jQuery("#projectStepTbody").children("tr").length-1).find("select[name='shiftMahjongId']"));
}
/**更改类别,换取复制项目下拉框的数据*/
function changeCategory(select){
	var deptId = jQuery("select[name='deptId']").val();
	var categoryId = jQuery(select).val();
	if(categoryId == null){
		jQuery("select[name='copyProject']").empty();
		jQuery("select[name='copyProject']").trigger("liszt:updated");
		return;
	}
	for (var i = 0; i < deptProjectList.length; i++) {
		if(deptProjectList[i].deptId == deptId){
			for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
				if(deptProjectList[i].projectCategoryList[j].categoryId == categoryId){
					jQuery("select[name='copyProject']").empty();
					jQuery("select[name='copyProject']").append("<option value='0'>"+"请选择一个项目复制"+"</option>");
					for (var m = 0; m < deptProjectList[i].projectCategoryList[j].projectList.length; m++) {
						jQuery("select[name='copyProject']").append("<option value="+deptProjectList[i].projectCategoryList[j].projectList[m].projectId+">"+deptProjectList[i].projectCategoryList[j].projectList[m].projectName+"</option>");
					}
					jQuery("select[name='copyProject']").trigger("liszt:updated");
				}
			}
		}
	}
}

/**静态删除类别*/
function deleteCategory(obj){
	jQuery(obj).parent().hide();
}

/**动态删除类别*/
function deleteProjectCategory(categoryId,deptId,obj){
	var data = "categoryId="+categoryId + "&deptId=" + deptId;
	for (var i = 0; i < deptProjectList.length; i++) {
		if(deptProjectList[i].deptId == deptId){
			for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
				if(deptProjectList[i].projectCategoryList[j].categoryId == categoryId){
					for (var s = 0; s < deptProjectList[i].projectCategoryList[j].projectList.length; s++) {
						data = data + "&projectId=" + deptProjectList[i].projectCategoryList[j].projectList[s].projectId;
					}
				}
			}
		}
	}
	stopBubble(obj);
	if(confirm("删除该类别,将会删除类别下的所有商品,确定么?")){
		jQuery.ajax({
			url: baseUrl+"project/deleteProjectCategory",
	        data: data,
	        success: function(data) {
	            if(data.code == 0){
	            	dialog("删除成功");
	            	jQuery(obj).parents(".project-sublist.category").hide();
	            	maintenance(null,null,categoryId,2);
	            	emptyInput();
	            }else{
	            	dialog(data.msg);
	            }
	        }
		});
	}else{
		return;
	}
	
}

/**显示编辑类别*/
function showEditCategory(obj){
	stopBubble(obj);
	jQuery(obj).parent().parent().hide();
	jQuery(obj).parent().parent().siblings().show();
}

/**取消编辑*/
function cancelEditCategory(obj){
	stopBubble(obj);
	jQuery(obj).parent().hide();
	jQuery(obj).parent().prev().show();
}

/**编辑类别*/
function editCategory(categoryId,deptId,obj){
	stopBubble(obj);
	var categoryName = jQuery(obj).prev().val();
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"project/editProjectCategory",
        data: "categoryId="+categoryId + "&categoryName="+categoryName+"&deptId="+deptId,
        success: function(data) {
            if(data.code == 0){
            	dialog("修改成功");
            	jQuery(obj).parent().prev().find(".categoryNameSpan").text(categoryName);
            	jQuery(obj).parent().prev().show();
            	jQuery(obj).parent().hide();
            	maintenance(null,categoryName,categoryId,3);
            }else{
            	dialog("error");
            }
        }
	});
}
/**批量新增项目信息*/
function addProjectsList(deptId,categoryId,obj){
	stopBubble(obj);
	emptyInput();
	//清除之前得内容 iconfa-minus
	/*jQuery("#add-project-modal .iconfa-minus").closest("div").remove();
	jQuery("[name='projectListName']").val('');
	jQuery("input[name='saveProjectListDeptId']").val(deptId);
	jQuery("input[name='saveProjectListCategoryId']").val(categoryId);
	var categoryName = jQuery(obj).parent().prev().text();
	jQuery("#crutentCategoryName").text(categoryName);
	jQuery("#add-project-modal").modal();*/
	
}
function addModalInputProject(obj){
	var str = '<div><br><input type="hidden"><span>项目名称：</span> <input type="text" placeholder="项目名称" name="projectListName" class="mr10 ml10 input-medium"><span class="fr font-span minus"><i class="iconfa-minus" onclick="deleteModalInputProject(this)"></i></span></div>';
	jQuery(obj).parents(".modal-body").children(".modal-btn-group").before(str);
}
function deleteModalInputProject(obj){
	jQuery(obj).parent().parent().remove();
}
function saveProjectsLists(obj){
	var data = "data=data";
	for (var i = 0; i < jQuery("input[name='projectListName']").length; i++) {
		if(jQuery("input[name='projectListName']").eq(i).val()==""){
			dialog("名称不能为空");
			return;
		}
		if(jQuery("input[name='projectListName']").eq(i).val().length>8){
			dialog("字数不能超过8个");
			return;
		}
		data = data + "&projectName=" + jQuery("input[name='projectListName']").eq(i).val();
	}
	var deptId = jQuery("input[name='saveProjectListDeptId']").val();
	var categoryId = jQuery("input[name='saveProjectListCategoryId']").val();
	data = data + "&deptId="+deptId + "&categoryId="+categoryId;
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"project/saveProjects",
        data: data,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function(data) {
            if(data.code == 0){
            	dialog("保存成功");
            	var projects = data.msg;
            	for (var i = 0; i < projects.length; i++) {
            		var str = '<li class="project-sublist-content" id="projectId'+projects[i].projectId+'" onclick="queryProjectById('+projects[i].projectId+',this);">'+projects[i].projectName+
		                    	   '<span class="fr">'+
		                    	        '<i class="icon-fuzhi iconfont" onclick="iconCopyProject('+projects[i].projectId+','+deptId+',this);" title="复制"></i>&nbsp;'+
					                    '<i class="iconfa-trash project-icon" onclick="deleteProject('+projects[i].projectId+','+deptId+',this);" title="删除"></i>'+
					               ' </span>'+
					           '</li>';
	                jQuery("#categoryId"+categoryId).after(str);
	                //维护js数据
	                servicdeData(deptId,categoryId,projects[i].projectId,projects[i].projectName);
	                //crutDeptIdChange(projects[i].projectId);
				}
            	jQuery("#add-project-modal").modal('hide');
            }else{
            	dialog("error");
            }
            emptyInput();
        }
	});
}

function addProject(){
	resetForm(jQuery("#projectform"));
	jQuery("img[name='headImg']").attr("src", picUrl + "zefun/images/pic_none.gif");
	jQuery("input[name='projectImage']").val("");
	jQuery("#positionTbody").empty();
	jQuery("#discountTbody").empty();
	jQuery("#shiftMahjongId").find("option").removeAttr("selected");
	jQuery("#shiftMahjongId").trigger("liszt:updated");
	jQuery(".project-selected").removeClass("project-selected");
}

/**根据id查询项目*/
function queryProjectById(projectId,obj){
	emptyInput();
	if(obj!=null){
		stopBubble(obj);
		//维护js数据
	    /*crutDeptIdChange(projectId);*/
		jQuery(".project-sublist-content").removeClass("active");
	    jQuery(obj).addClass("active");
	}
	jQuery.ajax({
        cache: true,
        type: "GET",
        async: false,
        url: baseUrl+"project/queryProjectInfoById?projectId="+projectId,
        success: function(data) {
            if(data.code == 0){
            	//锁定滚动条
                jQuery('html,body').animate({scrollTop:0}, 800);
            	jQuery("#isUpdateData").val(1);
            	var map = data.msg;
            	var projectInfo = map["projectInfo"];
            	//下面的这个判断是为了识别批量新增的项目进行展示数据的
            	if(map["projectStepList"].length == 0){
            		serchSaveProjectsListInit(projectInfo);
            		return;
            	}
            	
            	if(projectInfo.projectImage != null && projectInfo.projectImage != ""){
            		jQuery("img[name='headImg']").attr("src", picUrl + projectInfo.projectImage);
                	jQuery("input[name='projectImage']").val(projectInfo.projectImage);
            	}else{
            		jQuery("img[name='headImg']").attr("src", picUrl + "zefun/images/pic_none.gif");
                	jQuery("input[name='projectImage']").val("zefun/images/pic_none.gif");
            	}
            	
            	jQuery("#deptIdSel").val(projectInfo.deptId);
            	jQuery("#deptIdSel").trigger("liszt:updated");
            	
            	jQuery("#categoryNameSel").val(projectInfo.categoryId);
            	jQuery("#categoryNameSel").trigger("liszt:updated");
            	
            	jQuery("#hairstyleIdSel").val(projectInfo.hairstyleId);
            	jQuery("#hairstyleIdSel").trigger("liszt:updated");
            	
            	/*附属图片*/
            	var affiliatedImage = projectInfo.affiliatedImage;
            	var headImg = jQuery("img[name='affiliatedHeadImg']");
            	var affiliatedImageInput = jQuery("input[name='affiliatedImage']");
            	if(affiliatedImage != null && affiliatedImage != ""){
            		var img = affiliatedImage.split(",");
            		for(var i=0;i<affiliatedImageInput.length;i++){
            			jQuery(headImg[i]).attr("src",picUrl + img[i]);
            			jQuery(affiliatedImageInput[i]).val(img[i]);
            		}
            	}else{
            		for(var i=0;i<affiliatedImageInput.length;i++){
            			jQuery(headImg[i]).attr("src", picUrl + "zefun/images/pic_none.gif");
                    	jQuery(affiliatedImageInput[i]).val("zefun/images/pic_none.gif");
            		}
            	}
            	if(obj!=null){
            		jQuery(".dingdanzhuantai").text("正在修改项目 : "+projectInfo.projectName);
            	}else{
            		jQuery(".dingdanzhuantai").text("正在复制项目 : "+projectInfo.projectName);
            	}
            	
            	jQuery("#projectName").val(projectInfo.projectName);
            	jQuery("#highestDiscount").val(projectInfo.highestDiscount);
            	jQuery("#projectId").val(projectInfo.projectId);
            	jQuery("#isUpdate").val(projectInfo.isUpdate);
            	jQuery("#projectDesc").val(projectInfo.projectDesc);
            	jQuery("#projectPrice").val(projectInfo.projectPrice);
            	jQuery("#costPrice").val(projectInfo.costPrice);
            	jQuery("#performanceCalculate").val(projectInfo.performanceCalculate);
            	jQuery("#appointmentPrice").val(projectInfo.appointmentPrice);
            	
            	jQuery("#isGroupbuy").val(projectInfo.isGroupbuy);
            	jQuery("#groupbuyPrice").val(projectInfo.groupbuyPrice);
            	jQuery("#groupbuyResults").val(projectInfo.groupbuyResults);
            	jQuery("#groupbuyPlatform").val(projectInfo.groupbuyPlatform);
            	/*锁定部门*/
            	for (var i = 0; i < deptProjectList.length; i++) {
            		if(deptProjectList[i].deptId == projectInfo.deptId){
            			jQuery("select[name='deptId']").empty();
                    	jQuery("select[name='deptId']").append("<option value="+projectInfo.deptId+">"+deptProjectList[i].deptName+"</option>");
                    	jQuery("select[name='deptId']").trigger("liszt:updated");
                    	//锁定类别
                    	jQuery("#categoryNameSel").empty();
                    	for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
                    		if(deptProjectList[i].projectCategoryList[j].categoryId == projectInfo.categoryId){
                    			jQuery("#categoryNameSel").append("<option value='"+deptProjectList[i].projectCategoryList[j].categoryId+"' selected='selected' >"+deptProjectList[i].projectCategoryList[j].categoryName+"</option>");
                    		}else{
                    			jQuery("#categoryNameSel").append("<option value='"+deptProjectList[i].projectCategoryList[j].categoryId+"'  >"+deptProjectList[i].projectCategoryList[j].categoryName+"</option>");
                    		}
						}
                    	jQuery("#categoryNameSel").trigger("liszt:updated");
            		}
            	}
            	//更新当前轮牌设置
            	var deptId = jQuery("select[name='deptId']").val();
            	for (var i = 0; i < deptMahjongList.length; i++) {
            		if(deptMahjongList[i].deptId == deptId){
            			currentMahjongList = deptMahjongList[i].mahjongLevelList;
            		}
            	}
            	
            	/*如果是点击了查询的话,就把下面的类别ID锁定,修改商品的时候用于比较*/
            	jQuery("#defaultCategoryId").val(projectInfo.categoryId);
            	//reloadSelectCategory(projectInfo.deptId);
            	
            	var isAppointment = projectInfo.isAppointment;
            	if(isAppointment == 1){
            		jQuery('#isAppointment').lcs_on();//选中
            	}else{
            		jQuery('#isAppointment').lcs_off();//关闭
            	}
            	
            	var isWechatSell = projectInfo.isWechatSell;
            	if(isWechatSell == 1){
            		jQuery('#isWechatSell').lcs_on();//选中
            	}else{
            		jQuery('#isWechatSell').lcs_off();//关闭
            	}
            	
            	var isDisable = projectInfo.isDisable;
            	if(isDisable == 1){
            		jQuery('#isDisable').lcs_on();//选中
            	}else{
            		jQuery('#isDisable').lcs_off();//关闭
            	}
            	
            	var isGiftCash = projectInfo.isGiftCash;
            	if(isGiftCash == 1){
            		jQuery('#isGiftCash').lcs_on();//选中
            	}else{
            		jQuery('#isGiftCash').lcs_off();//关闭
            	}
            	
            	jQuery("#discountTbody").empty();
            	//会员折扣
            	var projectDiscountList = map["projectDiscountList"];
            	for(var i=0; i<projectDiscountList.length; i++){
            		var projectDiscount = projectDiscountList[i];
            		var levelId = projectDiscount.levelId;
            		var discountProportion = projectDiscount.discountProportion;
            		var discountAmount = projectDiscount.discountAmount;
            		var appointmentDiscount = projectDiscount.appointmentDiscount;
            		var onlineAppointmentPrice = projectDiscount.onlineAppointmentPrice;
            		
            		var trEl = jQuery("<tr></tr>");
            		var selectEl = addMemberLevelSelect(levelId);
            		var tdSelEl = jQuery("<td></td>");
            		tdSelEl.append(selectEl);
            		trEl.append(tdSelEl);
            		trEl.append("<td><span class='mendianjiage'>"+projectInfo.projectPrice+"  元</span></td>");
            		trEl.append("<td><input type='text' class='input70' name='discountAmount' value='"+discountAmount+"'/><span class='percent-symbol'>元</span></td>");
            		/*trEl.append("<td><input type='text' class='input70' name='onlineAppointmentPrice' value='"+onlineAppointmentPrice+"'/><span class='percent-symbol'>元</span></td>");*/
            		trEl.append("<td><span class='fr font-span minus'><i class='iconfa-minus' onclick='deleteDiscount(this);'></i></span></td>");
            		jQuery("#discountTbody").append(trEl);
            		jQuery(selectEl).chosen();
            	}
            	
            	//按项目流程设置轮牌
            	var projectStepList = map["projectStepList"];
            	for (var i = 0; i < projectStepList.length; i++) {
            		if(i>0){
            			addProjectStep();
            		}
				}
            	jQuery("#projectStepTbody").empty();
            	for(var i=0; i<projectStepList.length; i++){
            		var trEl = jQuery("<tr name='projectStepTr'></tr>");
            		trEl.append('<td><span name="projectStepNumber" class="input60">'+(i+1)+'</span></td>');
            		
            		var tdSelEl = jQuery("<td></td>");
            		var selectEl = jQuery('<select data-placeholder="选择牌位名称" class="chzn-select wthn100" name="shiftMahjongId" onchange="changeMahjongStep(this);">');
            		for (var j = 0; j < currentMahjongList.length; j++) {
            			if(projectStepList[i].shiftMahjongId == currentMahjongList[j].shiftMahjongId){
            				selectEl.append('<option selected="selected" value="'+currentMahjongList[j].shiftMahjongId+'">'+currentMahjongList[j].shiftMahjongName+'</option>');
            			}else{
            				selectEl.append('<option value="'+currentMahjongList[j].shiftMahjongId+'">'+currentMahjongList[j].shiftMahjongName+'</option>');
            			}
					}
            		tdSelEl.append(selectEl);
            		trEl.append(tdSelEl);
            		jQuery(selectEl).val(projectStepList[i].shiftMahjongId);
            		
            		trEl.append('<td><input type="text" class="input100" name="shiftStepName" value="'+projectStepList[i].projectStepName+'" ></td>');
            		
            		trEl.append('<td><select name="stepPerformanceType" class="chzn-select w70"><option value="2">固定</option><option value="1">比例</option></select></td>');
            		
            		trEl.append('<td><input type="text" class="input70" name="stepPerformance" value="'+projectStepList[i].stepPerformance+'" id="stepPerformance" datatype="n" nullmsg="请输入员工业绩计算！" errormsg="员工业绩计算:请输入数字！"><span class="percent-symbol">元</span></td>');
            		
            		var isDefaultTd = jQuery('<td></td>');
            		var isDefaultRadio = jQuery('<input type="checkbox" value="0" class="lcs_check isDisableApp" autocomplete="on" />');
            		isDefaultTd.append(isDefaultRadio);
            		trEl.append(isDefaultTd);
            		
            		if (i == 0) {
            			trEl.append('<td></td>');
            		} else {
            			trEl.append("<td><span class='fr font-span minus'><i class='iconfa-minus' onclick='deleteProjectStep(this);'></i></span></td>");
            		}
            		
            		jQuery("#projectStepTbody").append(trEl);
            		
            		jQuery(isDefaultRadio).lc_switch('是', '否');
            		
            		var isDefault = projectStepList[i].isDisable;
                	if(isDefault == 1){
                		jQuery(isDefaultRadio).lcs_on();//选中
                	}else{
                		jQuery(isDefaultRadio).lcs_off();//关闭
                	}
                	//步骤业绩方式
                	jQuery("select[name='stepPerformanceType']").eq(i).val(projectStepList[i].stepPerformanceType);
                	if(projectStepList[i].stepPerformanceType == 1){
                		jQuery("select[name='stepPerformanceType']").eq(i).find("option[value='1']").attr("selected","selected");
                		jQuery("input[name='stepPerformance']").eq(i).next().text("%");
                	}else{
                		jQuery("select[name='stepPerformanceType']").eq(i).find("option[value='2']").attr("selected","selected");
                	}
            	}
            	jQuery("#projectStepTbody select").chosen();
            	//changeMahjongStep();
            	//职位提成
            	for (var i = 0; i < jQuery("#xinxiugaishezhibuzhou").children("table").length; i++) {
            		jQuery("#xinxiugaishezhibuzhou").children("table").eq(i).find("tbody").empty();
				}
            	var projectCommissionList = map["projectCommissionList"];
            	
            	//修改
            	for (var i = 0; i < jQuery("select[name='shiftMahjongId']").length; i++){
            		for(var j=0; j<projectCommissionList.length; j++){
            			var projectCommission = projectCommissionList[j];
	            		var shiftMahjongId = projectCommission.shiftMahjongId;
	            		var levelId = projectCommission.levelId;
	            		var assignType = projectCommission.assignCashType;
	            		var assignCash = projectCommission.assignCash;
	            		var assignCard = projectCommission.assignCard;
	            		var nonAssignCash = projectCommission.nonAssignCash;
	            		var nonAssignCard = projectCommission.nonAssignCard;
	            		var appointmentReward = projectCommission.appointmentReward;
        				if (jQuery("select[name='shiftMahjongId']").eq(i).val() == shiftMahjongId){
							var num = jQuery("select[name='shiftMahjongId']").eq(i).parents("tr").find("span[name='projectStepNumber']").text();
							var trEl = jQuery("<tr></tr>");
		            		var tdSelEl = jQuery("<td></td>");
		            		var selectEl = jQuery('<select name="empLevelId" data-placeholder="选择项目" class="input100" ></select>');
		            		currentLevelList = mahjongLevel[shiftMahjongId];
		            		for (var k = 0; k < currentLevelList.length; k++) {
		            			var level = currentLevelList[k];
		            			if (levelId == level.levelId){
		            				selectEl.append("<option value='" + level.levelId + "' selected='selected'>" + level.levelName + "</option>");
		            			}else {
		            				selectEl.append("<option value='" + level.levelId + "'>" + level.levelName + "</option>");
		            			}
		            		}
		            		tdSelEl.append(selectEl);
		            		jQuery(selectEl).val(levelId);
		            		trEl.append(tdSelEl);
		            		
		            		var commissionTd = jQuery('<td></td>');
		            		var commissionSel = jQuery('<select name="assignType" class="chzn-select w70"><option value="1">比例</option><option value="2">固定</option></select>');
		            		commissionTd.append(commissionSel);
		            		jQuery(commissionSel).val(assignType);
		            		trEl.append(commissionTd);
		            		
		            		var assignTypeStr = "元";
		            		if (assignType==1) {
		            			assignTypeStr = "%";
		            		}
		            		trEl.append("<td><input type='text' class='input30' name='assignCash' value='"+assignCash+"'/><span class='percent-symbol'>"+assignTypeStr+"</span></td>");
		            		trEl.append("<td><input type='text' class='input30' name='assignCard' value='"+assignCard+"'/><span class='percent-symbol'>"+assignTypeStr+"</span></td>");
		            		trEl.append("<td><input type='text' class='input30' name='appointmentReward' value='"+appointmentReward+"'/><span class='percent-symbol'>元</span></td>");
		            		trEl.append("<td><i class='cursor iconfa-minus' onclick='deleteEmpLevel(this);'></i></td>");
							jQuery("#xinxiugaishezhibuzhou").children("table").eq(parseInt(num)-1).find("#positionTbody").append(trEl);
        				}
            		}
            	}
            	//将轮牌中的步骤后的删除按钮,只有最后一个步骤可以拥有删除按钮
            	for (var i = 0; i < jQuery("tr[name='projectStepTr']").length; i++) {
            		if (i!=(jQuery("tr[name='projectStepTr']").length-1)){
            			jQuery("tr[name='projectStepTr']").eq(i).children("td").eq(6).empty();
            		}
				}
            	
            	for (var i = 0; i < jQuery("#xinxiugaishezhibuzhou").children("table").length; i++) {
            		jQuery("#xinxiugaishezhibuzhou").children("table").eq(i).prev().find("input[name='zhiweinum']").val(jQuery("#xinxiugaishezhibuzhou").children("table").eq(i).children("tbody").find("tr").length);
				}
            	jQuery("#positionTbody select").chosen();
            }else{
            	dialog("error");
            }
        }
    });
}

/** 根据Id查询批量新增项目的信息*/
function serchSaveProjectsListInit(projectInfo){
	//下面的可以初始化轮牌
	changeDept(null,projectInfo.deptId);
	jQuery("#projectName").val(projectInfo.projectName);
	jQuery("#projectId").val(projectInfo.projectId);
	//更新当前轮牌设置
	var deptId = jQuery("select[name='deptId']").val();
	for (var i = 0; i < deptMahjongList.length; i++) {
		if(deptMahjongList[i].deptId == deptId){
			currentMahjongList = deptMahjongList[i].mahjongLevelList;
		}
	}
	/*锁定部门*/
	for (var i = 0; i < deptProjectList.length; i++) {
		if(deptProjectList[i].deptId == projectInfo.deptId){
			jQuery("select[name='deptId']").empty();
        	jQuery("select[name='deptId']").append("<option value="+projectInfo.deptId+">"+deptProjectList[i].deptName+"</option>");
        	jQuery("select[name='deptId']").trigger("liszt:updated");
        	//锁定类别
        	jQuery("#categoryNameSel").empty();
        	for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
        		if(deptProjectList[i].projectCategoryList[j].categoryId == projectInfo.categoryId){
        			jQuery("#categoryNameSel").append("<option value='"+deptProjectList[i].projectCategoryList[j].categoryId+"' selected='selected' >"+deptProjectList[i].projectCategoryList[j].categoryName+"</option>");
        		}else{
        			jQuery("#categoryNameSel").append("<option value='"+deptProjectList[i].projectCategoryList[j].categoryId+"'  >"+deptProjectList[i].projectCategoryList[j].categoryName+"</option>");
        		}
			}
        	jQuery("#categoryNameSel").trigger("liszt:updated");
		}
	}
	//触发一次切换轮牌的操作
	changeMahjongStep(jQuery("#projectStepTbody").children("tr").eq(jQuery("#projectStepTbody").children("tr").length-1).find("select[name='shiftMahjongId']"));
}

/**会员等级下拉框*/
function addMemberLevelSelect(levelId){
	var selectEl = jQuery('<select name="levelId" data-placeholder="Choose a Country..." class="input100" ></select>');
	for (var i = 0; i < memberLevelList.length; i++) {
		var memberLevel = memberLevelList[i];
		if(levelId == memberLevel.levelId){
			selectEl.append("<option value='"+memberLevel.levelId+"' selected='selected' >"+memberLevel.levelName+"</option>");
		}else{
			selectEl.append("<option value='"+memberLevel.levelId+"' >"+memberLevel.levelName+"</option>");
		}
	}
	return selectEl;
}


/**添加项目步骤*/
function addProjectStep(){
	//获取当前最后一个步骤顺序
	var number = parseInt(jQuery("[name='projectStepNumber']:last").html()) + 1;
	var str = "<tr name='projectStepTr'>"
		+ "<td><span name='projectStepNumber' class='input60'>" + number + "</span></td>"
		+ "<td>"
		+ "<select data-placeholder='选择牌位名称' class='chzn-select wthn100' name='shiftMahjongId' onchange='changeMahjongStep(this);'>";
	var deptId = jQuery("select[name='deptId']").val();
	currentMahjongList = deptMahjong[deptId];
	if (currentMahjongList.length == 0){
		dialog("该部门下不存在任何轮牌信息");
		return;
	}
	for (var i = 0; i < currentMahjongList.length; i++) {
		var mahjong = currentMahjongList[i];
		str += "<option value='" + mahjong.shiftMahjongId + "'>" + mahjong.shiftMahjongName + "</option>"
	}
	str += "</select>"
		+ "</td>"
		+ "<td><input type='text' class='input100' name='shiftStepName' datatype='*' nullmsg='请填写步骤名称!'/></td>"
		+ '<td><select name="stepPerformanceType" class="chzn-select w70"><option value="2">固定</option><option value="1">比例</option></select></td>'
		+ '<td><input type="text" class="input70" name="stepPerformance" id="stepPerformance" datatype="n" nullmsg="请输入员工业绩计算！" errormsg="员工业绩计算:请输入数字！"><span class="percent-symbol">元</span></td>'
		+ '<td><input type="checkbox" value="0" class="lcs_check isDisableApp" autocomplete="on" /></td>'
		+ "<td><span class='fr font-span minus'><i class='iconfa-minus' onclick='deleteProjectStep(this);'></i></span></td>"
		+ "</tr>";
	jQuery("#projectStepTbody").append(str);
	jQuery("#projectStepTbody").find("select").chosen();
	jQuery('.lcs_check').lc_switch('是', '否');
	
	//将新加的轮牌的上一个轮牌后面的删除按钮去掉,保证只能从最后一个开始删除
	jQuery("tr[name='projectStepTr']").eq((jQuery("tr[name='projectStepTr']").length-2)).children("td").eq(6).empty();
	
	jQuery("#xinxiugaishezhibuzhou").append(jQuery("#shezhigangweiticheng").html());
	jQuery("#xinxiugaishezhibuzhou").children(".more-toolbar").eq(number-1).find("#num").text(number);
	jQuery("#xinxiugaishezhibuzhou").children("table").eq(number-1).find("select").chosen();
	//触发一次切换轮牌的操作
	changeMahjongStep(jQuery("#projectStepTbody").children("tr").eq(jQuery("#projectStepTbody").children("tr").length-1).find("select[name='shiftMahjongId']"));
}
/**删除项目步骤*/
function deleteProjectStep(obj){
	//删除下面的设置岗位的元素
	var number = jQuery(obj).parents("tr").children("td").eq(0).find("span[name='projectStepNumber']").text();
	jQuery("#xinxiugaishezhibuzhou").children(".more-toolbar").eq(number-1).remove();
	jQuery("#xinxiugaishezhibuzhou").children("table").eq(number-1).remove();
	jQuery("#xinxiugaishezhibuzhou").children("p").eq(number-1).remove();
	jQuery(obj).parents("tr").remove();
	
	//将本次步骤的上一个轮牌,增加删除按钮
	jQuery("tr[name='projectStepTr']").eq((jQuery("tr[name='projectStepTr']").length-1)).children("td").eq(6).append('<span class="fr font-span minus"><i class="iconfa-minus" onclick="deleteProjectStep(this);"></i></span>');
}

/**切换轮牌操作*/
var changeMahjongStep = function(select){
	var number = jQuery(select).parents("tr").children("td").eq(0).find("span[name='projectStepNumber']").text();
	if(jQuery(select).val() == null){
		return;
	}
	var tempList = mahjongLevel[jQuery(select).val()];
	var positionTbody = jQuery("#xinxiugaishezhibuzhou").children("table").eq(number-1).find("#positionTbody");
	positionTbody.empty();
	for (var j = 0; j < tempList.length; j++) {
		var str = "<tr>"
			+ "<td>"
			+ "<select data-placeholder='选择职位'  class='chzn-select input100' name='empLevelId'>";
		for (var i = 0; i < tempList.length; i++) {
			var level = tempList[i];
			str += "<option value='" + level.levelId + "'>" + level.levelName + "</option>";
		}
		str += "</select>"
			+ "</td>"
			+ '<td><select name="assignType" class="chzn-select w70"><option value="2">固定</option><option value="1">比例</option></select></td>'
			+ "<td><input type='text' name='assignCash' class='input30' datatype='n'  nullmsg='请输入指定提成' errormsg='指定提成:请输数字！'/><span class='percent-symbol'>元</span></td>"
			+ "<td><input type='text' name='assignCard' class='input30' datatype='n'  nullmsg='请输入非指定提成' errormsg='非指定提成:请输数字！'/><span class='percent-symbol'>元</span></td>"
			+ "<td><input type='text' name='appointmentReward' class='input30' datatype='n' value='0' nullmsg='请输入预约奖励' errormsg='预约奖励:请输数字！'/><span class='percent-symbol'>元</span>" +
				"</td>"
			+ "<td><i class='cursor iconfa-minus' onclick='deleteEmpLevel(this);'></i></td>"
			+ "</tr>";
		positionTbody.append(str);
		positionTbody.find("select").chosen();
	}
	for (var j = 0; j < tempList.length; j++) {
		positionTbody.find("select[name='empLevelId']").eq(j).find("option").eq(j).attr('selected','selected');
		positionTbody.find("select[name='empLevelId']").eq(j).val(tempList[j].levelId);
		positionTbody.find("select[name='empLevelId']").trigger("liszt:updated");
	}
	jQuery("#xinxiugaishezhibuzhou").children("table").eq(number-1).prev().find("input[name='zhiweinum']").val(tempList.length);
}

/**复制项目更换下拉框选项*/
function changeCopyProject(select){
	var projectId = jQuery(select).val();
	if(projectId == 0){emptyInput();return;}
	queryProjectById(projectId,null);
	jQuery("#projectName").val(jQuery("#projectName").val()+"复制");
	jQuery("#projectId").val("");
	jQuery("#isUpdateData").val("");
	jQuery(".project-sublist-content").removeClass("active");
}
/**复制项目改变了*/
function iconCopyProject(projectId,deptId,obj){
	stopBubble(obj);
	jQuery(".dingdanzhuantai").text("正在复制项目");
	queryProjectById(projectId,null);
	jQuery("#projectName").val(jQuery("#projectName").val()+"复制");
	jQuery("#projectId").val("");
	jQuery("#isUpdateData").val("");
	jQuery(".project-sublist-content").removeClass("active");
}

/**添加会员折扣*/
function addMemberLevel(){
	var objHtml = jQuery("#memberLevelSelect").html();
	jQuery("#discountTbody").append(objHtml);
	jQuery("#discountTbody").find("select").chosen();
	if (jQuery("#projectPrice").val() == ""){
		jQuery(".mendianjiage").text(0+"  元");
	}else{
		jQuery(".mendianjiage").text(jQuery("#projectPrice").val()+"  元");
	}
}

/**删除会员折扣*/
function deleteDiscount(obj){
	jQuery(obj).parent().parent().parent().remove();
}

/**添加员工提成*/
function addEmployeeLevel(obj){
	var num = jQuery(obj).parents("table").prev(".more-toolbar").find("input[name='zhiweinum']").val();
	jQuery(obj).parents("table").prev(".more-toolbar").find("input[name='zhiweinum']").val(parseInt(num)+1);
	var eqNum = jQuery(obj).parents("table").prev().find("#num").text()-1;
	var levelId = jQuery("select[name='shiftMahjongId']").eq(eqNum).val()
	
	currentLevelList = mahjongLevel[levelId];
	if(currentLevelList == null)return;
	var str = "<tr>"
		+ "<td>"
		+ "<select data-placeholder='选择职位'  class='chzn-select input100' name='empLevelId'>";
	for (var i = 0; i < currentLevelList.length; i++) {
		var level = currentLevelList[i];
		str += "<option value='" + level.levelId + "'>" + level.levelName + "</option>";
	}
	str += "</select>"
		+ "</td>"
		+ '<td><select name="assignType" class="chzn-select w70"><option value="2">固定</option><option value="1">比例</option></select></td>'
		+ "<td><input type='text' name='assignCash' class='input30' datatype='n' nullmsg='请输入指定提成' errormsg='指定提成:请输数字！'/><span class='percent-symbol'>元</span></td>"
		+ "<td><input type='text' name='assignCard' class='input30' datatype='n' nullmsg='请输入非指定提成' errormsg='非指定提成:请输数字！'/><span class='percent-symbol'>元</span></td>"
		+ "<td><input type='text' name='appointmentReward' class='input30' value='0' datatype='n' nullmsg='请输入预约奖励' errormsg='预约奖励:请输数字！'/><span class='percent-symbol'>元</span>" +
			"</td>"
		+ "<td><i class='cursor iconfa-minus' onclick='deleteEmpLevel(this);'></i></td>"
		+ "</tr>";
	jQuery(obj).parents("table").find("#positionTbody").append(str);
	jQuery(obj).parents("table").find("#positionTbody").find("select").chosen();
//	jQuery('.lcs_check_assignType').lc_switch('比例','固定');
}

/**删除员工提成*/
function deleteEmpLevel(obj){
	var num = jQuery(obj).parents("table").prev(".more-toolbar").find("input[name='zhiweinum']").val();
	jQuery(obj).parents("table").prev(".more-toolbar").find("input[name='zhiweinum']").val(parseInt(num)-1)
	jQuery(obj).parent().parent().remove();
}

/**保存项目*/
function saveProjectInfo(){
	//对预约奖励进行判断
	if((jQuery("#projectPrice").val()/2-jQuery("#appointmentPrice").val())<0){
		dialog("预约奖励超过了项目价格的一半");
		return ;
	}
	//对预约进行判断
	if (jQuery("#isAppointment").val() == 1 && jQuery(".isDisableApp[value='1']").length == 0){
		dialog("该项目设置为可预约,在轮牌步骤中为没有可预约项");
		return;
	}
	//对会员折扣进行判断
	var levelIdArray = new Array();
	for (var i = 0; i < jQuery("select[name='levelId']").length; i++) {
		if(levelIdArray.contains(jQuery("select[name='levelId']").eq(i).val())){
			dialog("对会员的折扣设置不可重复");
			return;
		}else{
			levelIdArray.push(jQuery("select[name='levelId']").eq(i).val());
		}
		
	}
	var data = jQuery("#projectform").serialize();
	var str = "";
	var isDisableArray = jQuery(".isDisableApp");
    for (var i = 0; i < isDisableArray.length; i++) {
    	str = str + "&isDisableApp=" + isDisableArray.eq(i).val();
	}
    data = data + str;
    for (var i = 0; i < jQuery("input[name='zhiweinum']").length; i++) {
		if(jQuery("input[name='zhiweinum']").eq(i).val() == "0"){
			dialog("请为第"+(i+1)+"轮牌设置职位信息");
			return;
		}
	}
    var shiftMahjongIdArray = new Array();
    //轮牌不可重复
    for (var i = 0; i < jQuery("select[name='shiftMahjongId']").length; i++) {
    	if(jQuery("select[name='shiftMahjongId']").eq(i).val() == null){
    		dialog("请设置轮牌");
    		return;
    	}
    	if (shiftMahjongIdArray.contains(jQuery("select[name='shiftMahjongId']").eq(i).val())){
    		dialog("项目设置的轮牌不可重复");
    		return;
    	}else{
    		shiftMahjongIdArray.push(jQuery("select[name='shiftMahjongId']").eq(i).val());
    	}
	}
    //职位设置中不可重复
    for (var i = 0; i < jQuery("#xinxiugaishezhibuzhou").children("table").length; i++) {
    	var arrayObj = new Array();//创建一个数组
    	for (var j = 0; j < jQuery("#xinxiugaishezhibuzhou").children("table").eq(i).find("select[name='empLevelId']").length; j++) {
    		var empLevelId = jQuery("#xinxiugaishezhibuzhou").children("table").eq(i).find("select[name='empLevelId']").eq(j).val();
			if(arrayObj.contains(empLevelId)){
				dialog("第"+(i+1)+"个职位设置中重复");
				return;
			}else{
				arrayObj.push(empLevelId);
			}
		}
	}
    var categoryId = jQuery("select[name='categoryId']").val();
    if(categoryId == 0){
    	dialog("请选择一个类别");
    	return;
    }
    if(data.indexOf("isAppointment")<0){
    	data = data + "&isAppointment="+ jQuery("#isAppointment").val();
    }
    if (data.indexOf("isGiftCash") < 0) {
    	data += "&isGiftCash=" + jQuery("#isGiftCash").val();
    }
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"project/saveProject",
        data: data,
        async: false,
        success: function(data) {
            if(data.code == 0){
            	dialog("保存成功");
            	var deptId = jQuery("#deptIdSel").val();
            	var categoryId = jQuery("#categoryNameSel").val();
            	var projectName = jQuery("#projectName").val();
            	
            	if(jQuery("#isUpdateData").val() == 1){//修改
            		var projectId = jQuery("#projectId").val();
            		jQuery("#projectId"+projectId + " .project-content").html(projectName);
            		/*删除该项目后,将其新增到心的类别下面*/
            		jQuery("#projectId"+projectId).remove();
            		var str = '<li class="project-sublist-content active" id="projectId'+projectId+'" onclick="queryProjectById('+projectId+',this);">'+projectName+
	                    		  '<span class="fr">'+
	                    		    '<i class="icon-fuzhi iconfont" onclick="iconCopyProject('+projectId+','+deptId+',this);" title="复制"></i>&nbsp;'+
				                    '<i class="iconfa-trash project-icon" onclick="deleteProject('+projectId+','+deptId+',this);"></i>'+
				                 ' </span>'+
				              '</li>';
                    jQuery("#categoryId"+categoryId).after(str);
                    //维护js数据
	                //crutDeptIdChange(projectId);
	                servicdeData(deptId,categoryId,projectId,projectName);
            	}else{
            		//新增
            		var deptId = jQuery("#deptIdSel").val();
            		var projectId = data.msg;
            		var str = '<li class="project-sublist-content active" id="projectId'+projectId+'" onclick="queryProjectById('+projectId+',this);">'+projectName+
			                    '<span class="fr">'+
				                    '<i class="icon-fuzhi iconfont" onclick="iconCopyProject('+projectId+','+deptId+',this);" title="复制"></i>&nbsp;'+
				                    '<i class="iconfa-trash project-icon" onclick="deleteProject('+projectId+','+deptId+',this);"></i>'+
				               ' </span>'+
				            '</li>';
	                jQuery("#categoryId"+categoryId).after(str);
	                //将其设置为是修改操作
	                jQuery("#isUpdateData").val(1);
	                //在隐藏框中将项目id加入
	                jQuery("#projectId").val(projectId);
	                //维护js数据
	                servicdeData(deptId,categoryId,projectId,projectName);
	                //crutDeptIdChange(projectId);
            	}
            	emptyInput();
            }else{
            	dialog(data.msg);
            	emptyInput();
            }
        }
    });
	
}

/**删除项目*/
function deleteProject(projectId,deptId,obj){
	stopBubble(obj); 
	crutDeptIdChange(projectId);
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"project/deleteProject",
        data: "projectId="+ projectId + "&deptId=" + deptId,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	dialog("删除成功");
            	jQuery("#projectId"+projectId).remove();
            	emptyInput();
            }else{
            	dialog(data.msg);
            }
        }
    });
}

//修改门店价格的时候,将预约价格也改变
function changeYuYuejiage(obj){
	//jQuery("#appointmentPrice").val(jQuery(obj).val());
	jQuery(".mendianjiage").text(jQuery(obj).val()+"  元");
	jQuery("#performanceCalculate").val(jQuery(obj).val());
}

//计算会员门店价
function calculateMenberPrice(obj){
	var discountProportion = jQuery(obj).val();
	var projectPrice = jQuery("#projectPrice").val();
	if(projectPrice == ""){
		dialog("标准价格不能为空!");
	}else{
		jQuery(obj).parent().siblings().find("#yuyuejiage").text(Number(Number(discountProportion)*Number(projectPrice)/100).toFixed(2)+" 元");
		jQuery(obj).parent().siblings().find("input[name='discountAmount']").val(Number(Number(discountProportion)*Number(projectPrice)/100).toFixed(2));
		jQuery(obj).parent().siblings().find("input[name='onlineAppointmentPrice']").val(Number(Number(discountProportion)*Number(projectPrice)/100).toFixed(2));
	}
}

/**
 * 扩展Array方法,满足删除
 */
Array.prototype.remove=function(dx) 
{ 
    if(isNaN(dx)||dx>this.length){return false;} 
    for(var i=0,n=0;i<this.length;i++) 
    { 
        if(this[i]!=this[dx]) 
        { 
            this[n++]=this[i];
        } 
    } 
    this.length-=1;
} 
/**
 * 维护js中项目类别对象
 * type:1.新增维护,2.删除维护,3.修改维护
 */
function maintenance(deptId,categoryName,categoryId,type){
	if(type == 1){
		for (var i = 0; i < deptProjectList.length; i++) {
			if(deptProjectList[i].deptId == deptId){
				var category = new Object();
				category["categoryName"] = categoryName;
				category["categoryId"] = categoryId;
				deptProjectList[i].projectCategoryList.push(category);
				for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
					if(deptProjectList[i].projectCategoryList[j].categoryId == categoryId){
						var projectList = new Array();
						deptProjectList[i].projectCategoryList[j]["projectList"] = projectList;
					}
				}
			}
		}
	}
	if(type == 2){
		for (var i = 0; i < deptProjectList.length; i++) {
			for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
				if(categoryId == deptProjectList[i].projectCategoryList[j].categoryId){
					deptProjectList[i].projectCategoryList.remove(j);
				}
			}
		}
	}
	if(type == 3){
		for (var i = 0; i < deptProjectList.length; i++) {
			for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
				if(categoryId == deptProjectList[i].projectCategoryList[j].categoryId){
					deptProjectList[i].projectCategoryList[j]["categoryName"] = categoryName;
				}
			}
		}
	}
	/**
	 * 刷新右侧类别下拉框
	 * 如果当前右侧选中的部门deptId和参数相等,那么久更新下列的项目类别下拉框
	 */
	var curtDeptId = jQuery("select[name='deptId']").val();
	reloadSelectCategory(curtDeptId);
}

/**新增修改删除要维护js中的数据*/
function servicdeData(deptId,categoryId,projectId,projectName){
	for (var i = 0; i < deptProjectList.length; i++) {
		for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
			//删除obj
			for (var s = 0; s < deptProjectList[i].projectCategoryList[j].projectList.length; s++) {
				if(deptProjectList[i].projectCategoryList[j].projectList[s].projectId == projectId){
					deptProjectList[i].projectCategoryList[j].projectList.remove(s);
				}
			}
		}
	}
	//在新加入obj
	for (var i = 0; i < deptProjectList.length; i++) {
		if(deptProjectList[i].deptId == deptId){
			for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
				if(deptProjectList[i].projectCategoryList[j].categoryId == categoryId){
					var project = new Object();
					project["projectName"] = projectName;
					project["projectId"] = projectId;
					deptProjectList[i].projectCategoryList[j].projectList.push(project);
				}
			}
		}
	}
}
/**维护当前部门ID*/
function crutDeptIdChange(projectId){
	for (var i = 0; i < deptProjectList.length; i++) {
		for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
			for (var s = 0; s < deptProjectList[i].projectCategoryList[j].projectList.length; s++) {
				if(deptProjectList[i].projectCategoryList[j].projectList[s].projectId == projectId){
					currentDeptId = deptProjectList[i].deptId;
				}
			}
		}
	}
}

/**
 * 刷新项目类别中的下拉框数据 
*/
function reloadSelectCategory(deptId){
	for (var i = 0; i < deptProjectList.length; i++) {
		if(deptProjectList[i].deptId == deptId){
			jQuery("select[name='categoryId']").empty();
			jQuery("select[name='categoryId']").append("<option value='0'>选择项目类别</option>");
			for (var j = 0; j < deptProjectList[i].projectCategoryList.length; j++) {
				jQuery("select[name='categoryId']").append("<option value="+deptProjectList[i].projectCategoryList[j].categoryId+">"+deptProjectList[i].projectCategoryList[j].categoryName+"</option>");
			}
    	    jQuery("select[name='categoryId']").trigger("liszt:updated");
		}
	}
	changeCategory(jQuery("select[name='categoryId']"));
}

//校验轮牌设置信息
function checkMahjongLevelList(){
	var status = 0;
	var deptId = jQuery("select[name='deptId']").val();
	var shiftMahjongId = jQuery("select[name='shiftMahjongId']");
	a:for (var i = 0; i < shiftMahjongId.length; i++) {
		var smId = shiftMahjongId.eq(i).val();
		for (var j = 0; j < deptMahjongList.length; j++) {
			if(deptId == deptMahjongList[j].deptId){
				for (var m = 0; m < deptMahjongList[j].mahjongLevelList.length; m++) {
				if(deptMahjongList[j].mahjongLevelList[m].shiftMahjongId == smId){
					for (var s = 0; s < deptMahjongList[j].mahjongLevelList[m].employeeLevelList.length; s++) {
						for (var w = 0; w < jQuery("select[name='empLevelId']").length; w++) {
							if(deptMahjongList[j].mahjongLevelList[m].employeeLevelList[s].levelId == jQuery("select[name='empLevelId']").eq(w).val()){
								status++;
								continue a;
							}
						}
						
					}
				}
			}
			}
		}
	}
	if(status == shiftMahjongId.length){
		return true;
	}else{
		return false;
	}
}
/**手动清空表单数据*/
function emptyInput(){
     jQuery(':input','#projectform') 
	.not(':button, :submit, :reset, :hidden') 
	.val('') 
	.removeAttr('checked') 
	.removeAttr('selected');
    jQuery("#positionTbody").find("input[type='text']").val('');
    jQuery("#positionTbody").find("input").val("");
    /*将选中的项目解开*/
    jQuery(".project-sublist-content.nopadding.project-selected").attr("class","project-sublist-content nopadding");
    jQuery("#defaultCategoryId").val('');
    jQuery("#positionTbody").empty();
    jQuery("#positionTbody").append(jQuery("#employeeLevelSelect").html());
    jQuery("#positionTbody").find("select").chosen();
    jQuery("input[name='projectImage']").val("zefun/images/pic_none.gif");
    jQuery("img[name='headImg']").attr("src",picUrl+ "zefun/images/pic_none.gif"); 
    
    jQuery("input[name='affiliatedImage']").val("zefun/images/pic_none.gif");
    jQuery("img[name='affiliatedHeadImg']").attr("src",picUrl+"zefun/images/pic_none.gif");
    
    
    jQuery("#discountTbody").empty();
    jQuery("#isUpdateData").val(0);
    jQuery("#projectId").val("");
    /*解锁部门*/
    jQuery("select[name='deptId']").empty();
	for (var i = 0; i < deptProjectList.length; i++) {
        jQuery("select[name='deptId']").append("<option value="+deptProjectList[i].deptId+">"+deptProjectList[i].deptName+"</option>");
	}
	jQuery("select[name='deptId']").trigger("liszt:updated");
	//解锁类别,根据当前部门的信息,确定类别
	jQuery("select[name='categoryId']").empty();
	for (var i = 0; i < deptProjectList[0].projectCategoryList.length; i++) {
		jQuery("select[name='categoryId']").append("<option value="+deptProjectList[0].projectCategoryList[i].categoryId+">"+deptProjectList[0].projectCategoryList[i].categoryName+"</option>");
    }
	jQuery("select[name='categoryId']").trigger("liszt:updated");
	jQuery(".project-sublist-content").removeClass("active");
	changeDept(null,jQuery("select[name='deptId']").val());
	var table = jQuery("#projectStepTbody");
	for (var i = 0; i < table.length; i++) {
		var tr = jQuery(table.eq(i).children("tr[name='projectStepTr']"));
		if(tr.length > 1){
			for (var j = 1; j < tr.length; j++) {
				tr.eq(j).remove();
			}
		}
	}
	jQuery(".dingdanzhuantai").text("正在新增项目");
	jQuery("input[name='shiftStepName']").val("");

	//更新轮牌
	for (var j = 0; j < jQuery("#xinxiugaishezhibuzhou").children("table").eq(0).children("tbody").find("tr").length; j++) {
		jQuery("#xinxiugaishezhibuzhou").children("table").eq(0).children("tbody").find("tr").eq(j).remove();
	}
	jQuery("#xinxiugaishezhibuzhou").children("table").eq(0).find(".cursor.iconfa-plus").click();
	jQuery("#xinxiugaishezhibuzhou").children("p").eq(0).nextAll().remove();
	jQuery("input[name='zhiweinum']").val(1);
	//触发一次切换轮牌的操作
	changeMahjongStep(jQuery("#projectStepTbody").children("tr").eq(jQuery("#projectStepTbody").children("tr").length-1).find("select[name='shiftMahjongId']"));
}

//对会员卡最低项目折扣的设置
function setMemberLastAccount(obj){
	var discount = jQuery(obj).val();
	if(discount == ""){
		return;
	}else{
		jQuery("#discountTbody").empty();
		for (var i = 0; i < memberLevelList.length; i++) {
			if(memberLevelList[i].projectDiscount<parseInt(discount)){
				addMemberLevel();
				jQuery("select[name='levelId']").eq((jQuery("select[name='levelId']").length-1)).val(memberLevelList[i].levelId);
				jQuery("select[name='levelId']").eq((jQuery("select[name='levelId']").length-1)).find("option[value='"+memberLevelList[i].levelId+"']").attr("selected","selected");
				jQuery("select[name='levelId']").eq((jQuery("select[name='levelId']").length-1)).trigger("liszt:updated");
			}
		}
	}
}

function modalUpload(){
	jQuery("#upload-project-modal").modal();
}
function uploadSaveProjects(obj){
    var fileObj = document.getElementById("file").files[0]; // 获取文件对象
    var FileController = baseUrl + "project/upload";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("author", "hooyes");                        // 可以增加表单数据
    form.append("file", fileObj);                           // 文件对象
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function () {
        dialog("上传完成!");
    };
    // 实现进度条功能
    // xhr.upload.addEventListener("progress", progressFunction, false);
    xhr.send(form);
}
function progressFunction(evt) {
    var progressBar = document.getElementById("progressBar");
    var percentageDiv = document.getElementById("percentage");
    if (evt.lengthComputable) {
        progressBar.max = evt.total;
        progressBar.value = evt.loaded;
        percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
    }
}

//图片上传预览    IE是用了滤镜。
function previewImage(file)
{
    var MAXWIDTH  = 260;
    var MAXHEIGHT = 180;
    if (file.files && file.files[0])
    {
        var reader = new FileReader();
		reader.readAsDataURL(file.files[0]);
        reader.onload = function(evt){
		//获得上传的base64数据
        var src = evt.target.result;
		//获得上传的base64数据
        document.getElementById('imgPre').src = this.result;
        var data = "stringBase64="+src;
    	jQuery.ajax({
            cache: true,
            type: "POST",
            url: baseUrl+"qiniu/base64",
            data: data,
            async: false,
            success: function(data) {
                if(data.code == 0){
                }
            }
        });
    }   
    }
}
Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}
