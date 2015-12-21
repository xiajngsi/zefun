//上一页
function previous() {
	if (pageNo <= 1) {
		return;
	}
	pageNo--;
	changePage();
}
// 下一页
function next() {
	if (pageNo >= totalPage) {
		return;
	}
	pageNo++;
	changePage();
}
// 更改每页显示数量
function changePageSize(size) {
	pageNo = 1;
	pageSize = size;
	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePage();
}
// 分页处理
function changePage() {
	var content = jQuery("#serchMemberByNameOrPhone").val();
	var data;
	if (content!=null&&content!=""){
		var data = "pageNo=" + pageNo + "&pageSize=" + pageSize + "&content="+content;
	}else {
		var data = "pageNo=" + pageNo + "&pageSize=" + pageSize;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/error/member/info",
		data : data,
		dataType : "json",
		success : function(e) {
			if (e.code == 0) {
				jQuery(".perpage").text(e.msg.pageSize);
				totalPage = e.msg.page.totalPage;
				jQuery("#init_member").empty();
				initTable(e.msg.page.results, lastFacilitator);
			}
		}
	});
}

// 拼装table
function initTable(members, lastFacilitator) {
	if (lastFacilitator == "盛传"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			var str = '<tr id="err_'+member.id+'">'+
                '<td>'+member.phone+'</td>'+
                '<td>'+member.name+' </td>'+
                '<td>'+member.sex+' </td>'+
                '<td>'+member.levelNum+' </td>'+
                '<td>'+member.levelName+' </td>'+
                '<td>'+member.levelType+' </td>'+
                '<td>'+member.discount+' </td>'+
                '<td>'+member.totalAmount+' </td>'+
                '<td>'+member.totalConsumeAmount+' </td>'+
                '<td>'+member.balanceAmount+' </td>'+
                '<td>'+member.sendAmount+' </td>'+
                '<td>'+member.aeadTime+' </td>'+
                '<td>'+member.consumeAmount+' </td>'+
                '<td>'+member.balanceIntegral+' </td>'+
                '<td>'+member.lastConsumeTime+' </td>'+
                '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(1, '+member.id+' ,this);"></i></td>'+
                '<td class="can-click m-btn" onclick="balanceAmountMove(1, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
                '<td class="can-click m-btn" onclick="comboMove(1, '+member.id+', this)" style="text-decoration: none; text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
            '</tr>';
			jQuery("#init_member").append(str);
		}
	}
	else if (lastFacilitator == "左右"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			var str = '<tr id="err_'+member.id+'">'+
                '<td>'+member.phone+'</td>'+
                '<td>'+member.name+' </td>'+
                '<td>'+member.sex+' </td>'+
                '<td>'+member.levelNum+' </td>'+
                '<td>'+member.levelName+' </td>'+
                '<td>'+member.balanceAmount+' </td>'+
                '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(2, '+member.id+' ,this);"></i></td>'+
                '<td class="can-click m-btn" onclick="balanceAmountMove(2, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
            '</tr>';
			jQuery("#init_member").append(str);
		}
	}
	else if (lastFacilitator == "云浩企汇通"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			var str = '<tr id="err_'+member.id+'">'+
                '<td>'+member.phone+'</td>'+
                '<td>'+member.name+' </td>'+
                '<td>'+member.sex+' </td>'+
                '<td>'+member.levelNum+' </td>'+
                '<td>'+member.levelName+' </td>'+
                '<td>'+member.balanceAmount+' </td>'+
                '<td>'+member.balanceGiftmoneyAmount+' </td>'+
                '<td>'+member.consumeCount+' </td>'+
                '<td>'+member.balanceIntegral+' </td>'+
                '<td>'+member.lastConsumeTime+' </td>'+
                '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(3, '+member.id+' ,this);"></i></td>'+
                '<td class="can-click m-btn" onclick="balanceAmountMove(3, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
            '</tr>';
			jQuery("#init_member").append(str);
		}
	}
	else if (lastFacilitator == "博卡"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			var str = '<tr id="err_'+member.id+'">'+
                '<td>'+member.phone+'</td>'+
                '<td>'+member.name+' </td>'+
                '<td>'+member.sex+' </td>'+
                '<td>'+member.levelNum+' </td>'+
                '<td>'+member.totalAmount+' </td>'+
                '<td>'+member.balanceAmount+' </td>'+
                '<td>'+member.totalConsumeAmount+' </td>'+
                '<td>'+member.consumeCount+' </td>'+
                '<td>'+member.avgConsumeAmount+' </td>'+
                '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(4, '+member.id+' ,this);"></i></td>'+
                '<td class="can-click m-btn" onclick="balanceAmountMove(4, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
            '</tr>';
			jQuery("#init_member").append(str);
		}
	}
	else {
		
	}
}

//根据姓名或者电话进行查询
function serchMemberByNameOrPhoneDoc(){
	var content = jQuery("#serchMemberByNameOrPhone").val();
	pageNo = 1;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/error/member/info",
		data : "pageNo=" +pageNo+ "&pageSize=" + pageSize+"&content="+content,
		dataType : "json",
		success : function(e) {
			if (e.code == 0) {
				jQuery(".perpage").text(e.msg.pageSize);
				totalPage = e.msg.page.totalPage;
				jQuery("#init_member").empty();
				initTable(e.msg.page.results, lastFacilitator);
			}
		}
	});
}

//删除会员数据
var errMemberType = null;
var errMemberId = null;
function deleteErrMemberTip(type, id){
	errMemberType = type;
	errMemberId = id;
	jQuery("#deleteErrorMemberModel").modal('show');
}

//删除错误会员数据
function deleteErrMember(){
	jQuery("#deleteErrorMemberModel").modal('hide');
	if(isEmpty(errMemberType) || isEmpty(errMemberId)){
		dialog("请刷新重试！");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/error/member/delete",
		data : "type=" + errMemberType + "&id=" +errMemberId,
		dataType : "json",
		success : function(e) {
			if (e.code != 0) {
				dialog(e.msg);
				return;
			}
			dialog("删除成功！");
			jQuery("#err_" + errMemberId).fadeOut(500).remove();
		}
	});
}
