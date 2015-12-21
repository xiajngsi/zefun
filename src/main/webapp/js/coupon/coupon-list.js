    jQuery('.lcs_check_assignType').lc_switch('是','否');
	//全局变量,针对选择类型的不同值
	var ctype = "0";
	jQuery("#baocun").on("click",function(){
		if(ctype=="0"){
			var couponName = jQuery("#couponName").val();
			var couponPrice = jQuery("#couponPrice").val();
			var couponType = ctype;
			var couponVantages = jQuery("#couponVantages").val();
			var couponUseId = "0";
			var couponIsUse = jQuery(".lcs_check_assignType").val();
			var couponStartTime = jQuery("#couponStartTime").val();
			var couponStopTime = jQuery("#couponStopTime").val();
			var ok = checkInput(couponName,couponPrice,couponVantages,couponStartTime,couponStopTime);
			if(ok == false){
				return;
			}
			jQuery.ajax({
				type : "post",
				url : baseUrl + "coupons/add",
				data : "couponName="+couponName+"&couponPrice="+couponPrice+"&couponType="+
				couponType+"&couponUseId="+couponUseId+"&couponVantages="+couponVantages+
				"&couponIsUse="+couponIsUse+"&couponStartTime="+couponStartTime+"&couponStopTime="+couponStopTime,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						initNewHtml(e.msg);
					}else{
						dialog(e.msg);
					}
				}
			});
		}
		if(ctype=="1"){
			var projectId = jQuery("#project").val();
			if(projectId == 0){
				dialog("请先选择一个项目");
				return ;
			}
			var couponName = jQuery("#couponName").val();
			var couponPrice = jQuery("#couponPrice").val();
			var couponType = ctype;
			var couponUseId = projectId;
			var couponVantages = jQuery("#couponVantages").val();
			var couponIsUse = jQuery(".lcs_check_assignType").val();
			var couponStartTime = jQuery("#couponStartTime").val();
			var couponStopTime = jQuery("#couponStopTime").val();
			var ok = checkInput(couponName,couponPrice,couponVantages,couponStartTime,couponStopTime);
			if(ok == false){
				return;
			}
			jQuery.ajax({
				type : "post",
				url : baseUrl + "coupons/add",
				data : "couponName="+couponName+"&couponPrice="+couponPrice+"&couponType="+
				couponType+"&couponUseId="+couponUseId+"&couponVantages="+couponVantages+
				"&couponIsUse="+couponIsUse+"&couponStartTime="+couponStartTime+"&couponStopTime="+couponStopTime,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						initNewHtml(e.msg);
					}else{
						dialog(e.msg);
					}
				}
			});
		}
		if(ctype=="2"){
			var goodsId = jQuery("#goods").val();
			if(goodsId == 0){
				dialog("请先选择一个商品");
				return ;
			}
			var couponName = jQuery("#couponName").val();
			var couponPrice = jQuery("#couponPrice").val();
			var couponType = ctype;
			var couponUseId = goodsId;
			var couponVantages = jQuery("#couponVantages").val();
			var couponIsUse = jQuery(".lcs_check_assignType").val();
			var couponStartTime = jQuery("#couponStartTime").val();
			var couponStopTime = jQuery("#couponStopTime").val();
			var ok = checkInput(couponName,couponPrice,couponVantages,couponStartTime,couponStopTime);
			if(ok == false){
				return;
			}
			jQuery.ajax({
				type : "post",
				url : baseUrl + "coupons/add",
				data : "couponName="+couponName+"&couponPrice="+couponPrice+"&couponType="+
				couponType+"&couponUseId="+couponUseId+"&couponVantages="+couponVantages+
				"&couponIsUse="+couponIsUse+"&couponStartTime="+couponStartTime+"&couponStopTime="+couponStopTime,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						initNewHtml(e.msg);
					}else{
						dialog(e.msg);
					}
				}
			});
		}
		if(ctype=="3"){
			var mealId = jQuery("#meal").val();
			if(mealId == 0){
				dialog("请先选择一个套餐");
				return ;
			}
			var couponName = jQuery("#couponName").val();
			var couponPrice = jQuery("#couponPrice").val();
			var couponType = ctype;
			var couponUseId = mealId;
			var couponVantages = jQuery("#couponVantages").val();
			var couponIsUse = jQuery(".lcs_check_assignType").val();
			var couponStartTime = jQuery("#couponStartTime").val();
			var couponStopTime = jQuery("#couponStopTime").val();
			jQuery.ajax({
				type : "post",
				url : baseUrl + "coupons/add",
				data : "couponName="+couponName+"&couponPrice="+couponPrice+"&couponType="+
				couponType+"&couponUseId="+couponUseId+"&couponVantages="+couponVantages+
				"&couponIsUse="+couponIsUse+"&couponStartTime="+couponStartTime+"&couponStopTime="+couponStopTime,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						initNewHtml(e.msg);
					}else{
						dialog(e.msg);
					}
				}
			});
		}
	});
	//动态生成商品项目套餐信息
	function changeEniy(type){
		ctype = type;
		if(type=="0"){
			jQuery("#select_view").empty();
		}
		if(type=="1"){
			jQuery("#select_view").empty();
			var select = jQuery("<select id='project' data-placeholder='请选择...' class='input-medium hide chzn-select'></select>");
			select.append(jQuery("<option value='0'>请选择...</option>"));
			jQuery.ajax({
				type : "post",
				url : baseUrl + "serch/coupons/use",
				data : "type="+type,
				async : false,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						for (var i = 0; i < e.msg.length; i++) {
							select.append(jQuery("<option value='"+e.msg[i].projectId+"'>"+e.msg[i].projectName+"</option>"));
						}
					}
				}
			});
			jQuery("#select_view").append(select);
			select.chosen();
		}
		if(type=="2"){
			jQuery("#select_view").empty();
			var select = jQuery("<select id='goods' data-placeholder='请选择...' class='input-medium hide chzn-select'></select>");
			select.append(jQuery("<option value='0'>请选择...</option>"));
			jQuery.ajax({
				type : "post",
				url : baseUrl + "serch/coupons/use",
				data : "type="+type,
				async : false,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						for (var i = 0; i < e.msg.length; i++) {
							select.append(jQuery("<option value='"+e.msg[i].goodsId+"'>"+e.msg[i].goodsName+"</option>"));
						}
					}
				}
			});
			jQuery("#select_view").append(select);
			select.chosen();
		}
		if(type=="3"){
			jQuery("#select_view").empty();
			var select = jQuery("<select id='meal' data-placeholder='请选择...' class='input-medium hide chzn-select'></select>");
			select.append(jQuery("<option value='0'>请选择...</option>"));
			jQuery.ajax({
				type : "post",
				url : baseUrl + "serch/coupons/use",
				async : false,
				data : "type="+type,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						for (var i = 0; i < e.msg.length; i++) {
							select.append(jQuery("<option value='"+e.msg[i].comboId+"'>"+e.msg[i].comboName+"</option>"));
						}
					}
				}
			});
			jQuery("#select_view").append(select);
			select.chosen();
		}
	}
	//是否选择框
	jQuery('body').delegate('.lcs_check_assignType', 'lcs-statuschange', function() {
	    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
	    if(status == 'checked'){
	    	jQuery(this).val(1);
	    }else{
	    	jQuery(this).val(0);
	    }
	});

	//预览
	jQuery('table').delegate('.yl', 'click', function() {
		var name = jQuery(this).parents("tr").find("td").eq(0).text();
		var use = jQuery(this).parents("tr").find("td").eq(2).text();
		var stopTime = jQuery(this).parents("tr").find("td").eq(7).text();
		var price = jQuery(this).parents("tr").find("td").eq(3).text();
		jQuery("#yl_group_name").text(name);
		jQuery("#yl_project").text(use);
		jQuery("#yl_end_time").text(stopTime);
		jQuery("#yl_count").text(price);
		jQuery("#time").text(new Date().Format("yyyy-MM-dd"));
		jQuery("#weixin-cousor").modal();
	});
	//发布
	jQuery('table').delegate('.fb', 'click', function() {
	   var clickDoc = jQuery(this);
	   var couponId = jQuery(this).attr("id");
		jQuery.ajax({
			type : "post",
			url : baseUrl + "update/coupons/use",
			async : false,
			data : "couponId="+couponId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					var time = new Date().Format("yyyy-MM-dd");
					clickDoc.parents("tr").find("td").eq(9).text("已发布");
					clickDoc.parents("tr").find("td").eq(8).text(time);
					clickDoc.parent().children(".xj").attr("class","xj");
					clickDoc.parent().children(".fb").attr("class","fb hide");
				}
				else{
					dialog(e.msg);
				}
			}
		});
	});
	//下架
	jQuery('table').delegate('.xj', 'click', function() {
	   var couponId = jQuery(this).attr("id");
	   var clickDoc = jQuery(this);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "update/coupons/no/use",
			async : false,
			data : "couponId="+couponId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					clickDoc.parents("tr").find("td").eq(9).text("未发布");
					clickDoc.parents("tr").find("td").eq(8).text("");
					clickDoc.parent().children(".xj").attr("class","xj hide");
					clickDoc.parent().children(".fb").attr("class","fb");
				}
			}
		});
	});
	//删除
	jQuery('table').delegate('.delete', 'click', function() {
	   var couponId = jQuery(this).attr("id");
	   var clickDoc = jQuery(this);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "action/coupons/delete",
			async : false,
			data : "couponId="+couponId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					jQuery(clickDoc).parents("tr").hide('slow');
				}
				dialog(e.msg);
			}
		});
	});
	//发送优惠券
	var sendToMemberCouponId = 0 ;
	jQuery('table').delegate('.fs', 'click', function() {
	   sendToMemberCouponId = jQuery(this).attr("id");
	   jQuery("#fabu-cousor-modal").modal();
	   jQuery("#quxiao").on("click",function(){
		   jQuery("#fabu-cousor-modal").modal("hide");
	   });
	});
	jQuery("#querenfasong").on("click",function(){
		var couponId = sendToMemberCouponId;
		var ci = jQuery("input:checkbox[name='memberLevel']:checked");
        var memberLevelId = "";
        for(i=0;i<ci.length;i++){
            if(i == ci.length-1 ){
            	memberLevelId = memberLevelId +jQuery(ci[i]).val();
            }else{
            	memberLevelId = memberLevelId +jQuery(ci[i]).val()+ ",";
            }
        }
        var ai = jQuery("input:checkbox[name='memberScreenings']:checked");
        var memberScreening = "";
        for(i=0;i<ai.length;i++){
            if(i == ai.length-1 ){
            	memberScreening = memberScreening +jQuery(ai[i]).val();
            }else{
            	memberScreening = memberScreening +jQuery(ai[i]).val()+ ",";
            }
        }
        if(memberLevelId=="")memberLevelId=0;
        if(memberScreening=="")memberScreening=0;
        if(memberLevelId == ""&&memberScreening ==""){
        	jQuery("#fabu-cousor-modal").modal('hide');
        	dialog("未选择任何会员分组");
        	return;
        }
		jQuery.ajax({   
			type : "post",
			url : baseUrl + "coupon/send/coupons",
			async : false,
			data : "couponId="+couponId+"&memberLevelId="+memberLevelId+"&memberScreening="+memberScreening,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					jQuery("#fabu-cousor-modal").modal('hide');
					dialog("发送成功");
				}else{
					dialog(e.msg);
				}
			}
		});
	});
    //新增成功刷新html
	function initNewHtml(ojb){
		emptyInput();
		var tr = jQuery("<tr></tr>");
		tr.append(jQuery("<td>"+ojb.couponName+"</td>"));
		tr.append(jQuery("<td>"+ojb.couponType+"</td>"));
		tr.append(jQuery("<td>"+ojb.couponUse+"</td>"));
		tr.append(jQuery("<td>"+ojb.couponPrice+"  元"+"</td>"));
		tr.append(jQuery("<td>"+ojb.couponVantages+"  分"+"</td>"));
		tr.append(jQuery("<td>"+ojb.hasExchangeCount+"  次"+"</td>"));
		tr.append(jQuery("<td>"+ojb.hasUseCount+"  次"+"</td>"));
		tr.append(jQuery("<td>"+ojb.couponStopTime+"</td>"));
		tr.append(jQuery("<td>"+ojb.releaseTime+"</td>"));
		tr.append(jQuery("<td>"+ojb.couponIsUse+"</td>"));
		
		var td = jQuery("<td></td>");
		if(ojb.couponIsUse == '已发布'){
			td.append(jQuery("<span class='fb hide' id='"+ojb.couponId+"' style='cursor: pointer'>发布  </span>"));
			td.append(jQuery("<span class='xj' id='"+ojb.couponId+"' style='cursor: pointer'>下架  </span>"));
		}else{
			td.append(jQuery("<span class='fb' id='"+ojb.couponId+"' style='cursor: pointer'>发布  </span>"));
			td.append(jQuery("<span class='xj hide' id='"+ojb.couponId+"' style='cursor: pointer'>下架  </span>"));
		}
		
		td.append(jQuery("<span class='yl' id='"+ojb.couponId+"' style='cursor: pointer'>预览  </span>"));
		td.append(jQuery("<span class='fs' id='"+ojb.couponId+"' style='cursor: pointer'>发送  </span>"));
		td.append(jQuery("<span class='delete' id='"+ojb.couponId+"' style='cursor: pointer'>删除  </span>"));
		tr.append(td);
		jQuery("#showCoupon").append(tr);
		dialog("新增成功");
	}
	//js日期格式化
	Date.prototype.Format = function (fmt) { 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	  /*分页*/
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
	  function changePage(){
			jQuery.ajax({
				type : "post",
				url : baseUrl + "view/coupons/by/page",
				async : false,
				data : "pageNo="+pageNo+"&pageSize="+pageSize,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						jQuery("#showCoupon").empty();
						initTable(e);
					}
				}
			});
	  }
	  function initTable(e){
		  for (var i = 0; i < e.msg.results.length; i++) {
				var tr = jQuery("<tr></tr>");
				tr.append(jQuery("<td>"+e.msg.results[i].couponName+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].couponType+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].couponUse+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].couponPrice+"  元"+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].couponVantages+"  分"+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].hasExchangeCount+"  次"+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].hasUseCount+"  次"+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].couponStopTime+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].releaseTime+"</td>"));
				tr.append(jQuery("<td>"+e.msg.results[i].couponIsUse+"</td>"));
				var td = jQuery("<td></td>");
				if(e.msg.results[i].couponIsUse == '已发布'){
					td.append(jQuery("<span class='fb hide' id='"+e.msg.results[i].couponId+"' style='cursor: pointer'>发布  </span>"));
					td.append(jQuery("<span class='xj' id='"+e.msg.results[i].couponId+"' style='cursor: pointer'>下架  </span>"));
				}else{
					td.append(jQuery("<span class='fb' id='"+e.msg.results[i].couponId+"' style='cursor: pointer'>发布  </span>"));
					td.append(jQuery("<span class='xj hide' id='"+e.msg.results[i].couponId+"' style='cursor: pointer'>下架  </span>"));
				}
				
				td.append(jQuery("<span class='yl' id='"+e.msg.results[i].couponId+"' style='cursor: pointer'>预览  </span>"));
				td.append(jQuery("<span class='fs' id='"+e.msg.results[i].couponId+"' style='cursor: pointer'>发送  </span>"));
				td.append(jQuery("<span class='delete' id='"+e.msg.results[i].couponId+"' style='cursor: pointer'>删除  </span>"));
				tr.append(td);
				jQuery("#showCoupon").append(tr);
		}
	  }
	  
	  //表单验证
	  function checkInput(couponName,couponPrice,couponVantages,couponStartTime,couponStopTime){
		  if(couponName == ""){
			  dialog("名称不能为空");
			  return false;
		  }
		  if(couponPrice == ""){
			  dialog("请输入一个金额");
			  return false;
		  }
		  if(couponVantages == ""){
			  dialog("请输入一个兑换积分");
			  return false;
		  }
		  /*if(couponStartTime == ""){
			  dialog("请输入一个开始时间");
			  return false;
		  }*/
		  if(couponStopTime == ""){
			  dialog("请输入一个结束时间");
			  return false;
		  }
		  if(couponPrice!=""&&(/(^[1-9]\d*$)/.test(couponPrice))!=true){
			  dialog("折扣价格为整数");
			  return false;
		  }
		  if(couponVantages!=""&&(/(^[0-9]\d*$)/.test(couponVantages))!=true){
			  dialog("兑换积分为整数");
			  return false;
		  }
		  return true;
	  }

	  /**手动清空表单数据*/
	  function emptyInput(){
	       jQuery(':input','#couponsForm') 
	  	.not(':button, :submit, :reset, :hidden') 
	  	.val('') 
	  	.removeAttr('checked') 
	  	.removeAttr('selected');
	    jQuery("#select_view").empty();
	   	/*锁定类别*/
	   	jQuery("select[name='couponType']").empty();
	   	var str = '<option selected="selected" value="0">通用</option><option value="1">项目</option><option value="2">商品</option>';
	   	jQuery("select[name='couponType']").append(str);
	   	jQuery("select[name='couponType']").trigger("liszt:updated");
	  }		  
	  
	  /*选择时间*/
	  jQuery("#couponStopTime").bind("click", function(){
	    jQuery(this).datetimepicker({
	      lang:'ch',
	      timepicker:false,
	      format:"Y-m-d"
	    });
	  });