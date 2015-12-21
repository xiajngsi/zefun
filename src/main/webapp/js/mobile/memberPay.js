var balanceAmount = new Big(memberBalanceAmount);
var totalRealMoney = new Big(0);
var offPickerMap = new Object();
var detailOffMap = new Object();
//初始化优惠项
(function($, jq) {
    $.init();
    $.ready(function() {
    	for (var i = 0; i < detailList.length; i++) {
    		var detail = detailList[i];
    		totalRealMoney = totalRealMoney.plus(detail.discountAmount);
    		
    		var payOffContent = jq("[name='payOffContent'][detailId='" + detail.detailId + "']");
    		
    		var detailOffList = new Array();
    		//套餐/礼金/优惠券抵扣，组装数据
    		var pickerData = new Array();
    		var d = {};
            d["uid"] = 0;
            d["detailId"] = detail.detailId;
            d["offType"] = 0;
            d["offId"] = 0;
            d["count"] = 0;
            d["offAmount"] = 0;
            d["name"] = '不使用优惠';
            d["text"] = '不使用优惠';
            pickerData.push(d);
        	
    		//检查是否存在优惠项
            var paymentOffList = detail.paymentOffList;
            if (paymentOffList == null || paymentOffList.length == 0) {
            	payOffContent.find("[name='offName']:first").html("无可用优惠");
            } else {
            	payOffContent.find("[name='offName']:first").html("有可用优惠<span class='iconfont icon-zhankai'></span>");
                
                //标记是否选择了最佳优惠项
                for (var j = 0; j < paymentOffList.length; j++) {
                    var offObj = paymentOffList[j];
                    
                    var uid = offObj.type + "_" + offObj.id;
                    detailOffList.push(uid);
                    
                    var data = {};
                    data["uid"] = uid;
                    data["detailId"] = detail.detailId;
                    data["offType"] = offObj.type;
                    data["offId"] = offObj.id;
                    data["count"] = offObj.count;
                    data["offAmount"] = offObj.amount;
                    data["name"] = offObj.name;
                    data["text"] = offObj.name + ' -' + offObj.amount + '元 ';
                    
                    pickerData.push(data);
                }
                detailOffMap[detail.detailId] = detailOffList;
            }
            //初始化多选项
            var userPicker = new $.PopPicker();
            userPicker.setData(pickerData);
            jq(payOffContent).on("click", {picker : userPicker, detailId : detail.detailId}, showPicker);
            offPickerMap[detail.detailId] = userPicker;
		}
    	syncRealMoney();
    });
})(mui, $);


function syncRealMoney(){
	totalRealMoney = totalRealMoney.minus(appointOff)
	$("#cardMoney").html("-" + totalRealMoney.toFixed(2));
	
	if (balanceAmount.lt(totalRealMoney)) {
		$("#payBtn").addClass("hide");
		$("#chargeTip").removeClass("hide");
	} 
	else {
		$("#chargeTip").addClass("hide");
		$("#payBtn").removeClass("hide");
	}
}

function showPicker(data){
	$(".mui-poppicker").removeClass("mui-active");
	data.data.picker.show(function(items) {
		var selectOff = items[0];
		var detailId = selectOff.detailId;
		var payOffContent = $("[name='payOffContent'][detailId='" + detailId + "']");
		var projectPrice = payOffContent.attr("projectPrice");
		//检查是否有改变选项
		var sid = payOffContent.attr("uid");
		if (selectOff.uid != sid) {
			var obj = findElementByProperty(this.pickers[0].items, "uid", sid);
			if (obj != null) {
				syncOff(obj, detailId, projectPrice, 2);
			}
			
			//如果选择为不使用优惠
			if (selectOff.uid != 0) {
				syncOff(selectOff, detailId, projectPrice, 1);
			}
			
			//计算优惠之后的实收
			var discountAmount = new Big(payOffContent.attr("discountAmount"));
            if (selectOff.offType == 3 && discountAmount.lt(selectOff.offAmount)) {
            	payOffContent.find("[name='offAmount']:first").html("-" + projectPrice);
            } else {
            	payOffContent.find("[name='offAmount']:first").html("-" + selectOff.offAmount);
            }
            
            //同步实收金额
			var realMoney = new Big(payOffContent.attr("realMoney"));
			totalRealMoney = totalRealMoney.minus(realMoney);
            if (selectOff.offType == 4) {
            	realMoney = new Big(projectPrice - selectOff.offAmount);
            } 
            else {
            	realMoney = discountAmount.minus(selectOff.offAmount);
            }
            if (realMoney.lt(0)) {
            	realMoney = 0;
            }
            totalRealMoney = totalRealMoney.plus(realMoney);
			
            //更新项目详情的当前所有优惠项
            payOffContent.attr("uid", selectOff.uid);
            payOffContent.attr("realMoney", realMoney);
			payOffContent.attr("offType", selectOff.offType);
        	payOffContent.attr("offId", selectOff.offId);
        	payOffContent.attr("offAmount", selectOff.offAmount);
            payOffContent.find("[name='offName']:first").html(selectOff.name + '<span class="iconfont icon-zhankai"></span>');
            syncRealMoney();
		}
	});
}

//type,1:选择，2:取消选择
function syncOff(selectOff, detailId, projectPrice, type){
	var id = selectOff.uid;
	var offAmount = new Big(selectOff.offAmount);
	
	var balance = new Big(allOffMap[id]);
	//检查优惠类型,如果为礼金，需要检查礼金预约，其他检查数量
	var deduction = 1;
	if (selectOff.offType == 4) {
		//维护当前所选部门的礼金余额
		deduction = offAmount;
	}
	if (type == 1) {
		balance = balance.minus(deduction);
	} else {
		balance = balance.plus(deduction);
		
		//如果为礼金，先查看是否有其他项目使用礼金优惠，检查抵扣金额是否需要变更
		if (selectOff.offType == 4) {
			for ( var k in offPickerMap) {
				if (k == detailId) {
					continue;
				}
				var picker = offPickerMap[k].pickers[0];
				var obj = picker.getSelectedItem();
				var payOffContent = $("[name='payOffContent'][detailId='" + obj.detailId + "']");
				var pp = new Big(payOffContent.attr("projectPrice"));
				var pd = new Big(payOffContent.attr("highestDiscount"));
				if(obj.uid == id && obj.offAmount < projectPrice){
					var cof = obj.offAmount;
					obj.offAmount = balance.plus(cof);
					if (obj.offAmount.gt(pd)) {
						obj.offAmount = pd;
					}
					//更新项目详情的当前所有优惠项
					payOffContent.find("[name='offAmount']:first").html("-" + obj.offAmount);
					var r = pp.minus(obj.offAmount);
					
					totalRealMoney = totalRealMoney.plus(r.minus(payOffContent.attr("realMoney")));
					syncRealMoney();
		            payOffContent.attr("realMoney", r);
		        	payOffContent.attr("offAmount", obj.offAmount);
		        	
		        	obj.text = obj.name + ' -' + obj.offAmount + '元 ';
					offPickerMap[k].setData(picker.items);
					balance = balance.plus(cof);
					allOffMap[id] = balance;
					var to =  jQuery.extend(true,{}, obj);
					syncOff(to, to.detailId, projectPrice, 1);
					return;
				}
			}
		}
	}
	allOffMap[id] = balance;
	
	//遍历其他优惠项
	for ( var k in offPickerMap) {
		if (k == detailId) {
			continue;
		}
		//获取此次遍历的项目详情可使用的优惠标识列表
		var detailOffList = detailOffMap[k];
		//如果不能使用该优惠，直接跳过
		if ($.inArray(id, detailOffList) < 0) {
			continue;
		}
		
		var picker = offPickerMap[k].pickers[0];
		//获取此次遍历的实时优惠选项
		var dataItems = picker.items;
		//检查该优惠是否存在其他优惠列表中
		var obj = findElementByProperty(dataItems, "uid", id);
		
		//如果不存在，检查是否有余额，有余额则可加入其内
		if(obj == null && balance.gt(0)) {
			var payOffContent = $("[name='payOffContent'][detailId='" + k + "']");
			var pd = new Big(payOffContent.attr("highestDiscount"));
			
			var to =  jQuery.extend(true,{}, selectOff);
			to.detailId = k;
			to.offAmount = balance;
			
			if(to.offAmount.gt(pd)){
				to.offAmount = pd;
			}
			
			to.text = to.name + ' -' + to.offAmount + '元 ';
            dataItems.push(to);
		} 
		//如果存在，且余额不足，且不是当前遍历选项选择的优惠时从中移除
		else if(obj != null && balance.lt(1) && picker.getSelectedItem().uid != id) {
			removeElementByProperty(dataItems, "uid", id);
		}
		//如果存在，且未礼金，需重新更新礼金抵扣的优惠项
		else if (obj != null && picker.getSelectedItem().uid != id && selectOff.offType == 4) {
			var payOffContent = $("[name='payOffContent'][detailId='" + k + "']");
			var pd = new Big(payOffContent.attr("highestDiscount"));
			obj.offAmount = balance;
			if(obj.offAmount.gt(pd)){
				obj.offAmount = pd;
			}
			obj.text = obj.name + ' -' + obj.offAmount + '元 ';
		}
		
		offPickerMap[k].setData(dataItems);
	}
}

//提交订单
function orderpay(orderId) {
	if (balanceAmount.lt(totalRealMoney)) {
		$("#chargeTip").addClass("hide");
		$("#payBtn").removeClass("hide");
		return;
	}
	
    var details = new Array();
    var detailId = null;
    jQuery("[name='payOffContent']").each(function(){
        var $this = jQuery(this);
        details.push({"detailId" : $this.attr("detailId"), "offType" : $this.attr("offType"), "offId" : $this.attr("offId"), "offAmount" : $this.attr("offAmount")});
    });

    var cardAmount = totalRealMoney;
    
    var data = {'orderId':orderId,'cardAmount':cardAmount,'cashAmount':0,
            'unionpayAmount':0,'wechatAmount':0,'alipayAmount':0,'detailList':details};
    jQuery.ajax({
        type: "POST",
        url: baseUrl + "memberCenter/action/orderPay",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(data) {
            if(data.code != 0) {
                dialog(data.msg);
                return;
            }
            dialog('结账成功');
            window.location.href = baseUrl + "memberCenter/view/orderEvaluate?orderId=" + orderId;
        }
    });
}