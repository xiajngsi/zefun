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
	var deptId=jQuery("#xdeptId").val().substr(6, 11);
	var search=jQuery("#search").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "shift/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize+"&deptId=" + deptId+"&search=" + search,
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
/*function refreshTableData(page){
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	
	var List = page.results;
	var tbody = document.createElement("tbody");
	for (var i = 0; i < List.length; i++) {
		var shift = List[i];
		
		var tr = document.createElement("tr");
		tr.setAttribute("id", shift.employeeId);
		
		var employeeCode = document.createElement("td");
		employeeCode.innerHTML = shift.employeeCode;
		tr.appendChild(employeeCode);
		
		var name = document.createElement("td");
		name.innerHTML = shift.name;
		tr.appendChild(name);
		if(shift.shiftDto==null){
			var shifIda = document.createElement("td");
			
			shifIda.innerHTML = '<select name="shifIda" class="chzn-select-search" style="width: 80px;">'
				+'<option selected="selected" value="">未设置</option>'
				+'<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
				
			tr.appendChild(shifIda);
			
			var shifIdb = document.createElement("td");

			shifIdb.innerHTML = '<select name="shifIdb" class="chzn-select-search" style="width: 80px;">'
				+'<option selected="selected" value="">未设置</option>'
				+'<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			tr.appendChild(shifIdb);
			
			var shifIdc = document.createElement("td");
			shifIdc.innerHTML = '<select name="shifIdc" class="chzn-select-search" style="width: 80px;">'
				+'<option selected="selected" value="">未设置</option>'
				+'<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			tr.appendChild(shifIdc);
			
			var shifIdd = document.createElement("td");

			shifIdd.innerHTML = '<select name="shifIdd" class="chzn-select-search" style="width: 80px;">'
				+'<option selected="selected" value="">未设置</option>'
				+'<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			tr.appendChild(shifIdd);
			
			
			var shifIde = document.createElement("td");
			
			shifIde.innerHTML = '<select name="shifIde" class="chzn-select-search" style="width: 80px;">'
				+'<option selected="selected" value="">未设置</option>'
				+'<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			tr.appendChild(shifIde);
			
			var shifIdf = document.createElement("td");

			shifIdf.innerHTML = '<select name="shifIdf" class="chzn-select-search" style="width: 80px;">'
				+'<option selected="selected" value="">未设置</option>'
				+'<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			tr.appendChild(shifIdf);
			
			var shifIdg = document.createElement("td");
			
			shifIdg.innerHTML = '<select name="shifIdg" class="chzn-select-search" style="width: 80px;">'
				+'<option selected="selected" value="">未设置</option>'
				+'<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			tr.appendChild(shifIdg);
		}else{
			var shifIda = document.createElement("td");
			
			var toua = '<select name="shifIda" class="chzn-select-search" style="width: 80px;">';
				if(shift.shiftDto.shifNamea=='早班'){
					var weia='<option selected="selected" value="早班">早班</option>'
					+'<option value="中班">中班</option>'
					+'<option value="晚班">晚班</option>'
					+'<option value="全班">全班</option>'
					+'<option value="休息日">休息日</option></select>';
				}else if(shift.shiftDto.shifNamea=='中班'){
					var weia='<option value="早班">早班</option>'
					+'<option selected="selected" value="中班">中班</option>'
					+'<option value="晚班">晚班</option>'
					+'<option value="全班">全班</option>'
					+'<option value="休息日">休息日</option></select>';
				}else if(shift.shiftDto.shifNamea=='晚班'){
					var weia='<option value="早班">早班</option>'
					+'<option value="中班">中班</option>'
					+'<option selected="selected" value="晚班">晚班</option>'
					+'<option value="全班">全班</option>'
					+'<option value="休息日">休息日</option></select>';
				}else if(shift.shiftDto.shifNamea=='全班'){
					var weia='<option value="早班">早班</option>'
					+'<option value="中班">中班</option>'
					+'<option value="晚班">晚班</option>'
					+'<option selected="selected" value="全班">全班</option>'
					+'<option value="休息日">休息日</option></select>';
				}else if(shift.shiftDto.shifIda==0){
					var weia='<option value="早班">早班</option>'
					+'<option value="中班">中班</option>'
					+'<option value="晚班">晚班</option>'
					+'<option value="全班">全班</option>'
					+'<option selected="selected" value="休息日">休息日</option></select>';
				}
				shifIda.innerHTML = toua+weia;
			tr.appendChild(shifIda);
			
			
			var shifIdb = document.createElement("td");
			var toub = '<select name="shifIdb" class="chzn-select-search" style="width: 80px;">';
			if(shift.shiftDto.shifNameb=='早班'){
				var weib='<option selected="selected" value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNameb=='中班'){
				var weib='<option value="早班">早班</option>'
				+'<option selected="selected" value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNameb=='晚班'){
				var weib='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option selected="selected" value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNameb=='全班'){
				var weib='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option selected="selected" value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifIdb==0){
				var weib='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option selected="selected" value="休息日">休息日</option></select>';
			}
			shifIdb.innerHTML = toub+weib;
			tr.appendChild(shifIdb);
			
			var shifIdc = document.createElement("td");

			var touc = '<select name="shifIdc" class="chzn-select-search" style="width: 80px;">';
			if(shift.shiftDto.shifNamec=='早班'){
				var weic='<option selected="selected" value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamec=='中班'){
				var weic='<option value="早班">早班</option>'
				+'<option selected="selected" value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamec=='晚班'){
				var weic='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option selected="selected" value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamec=='全班'){
				var weic='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option selected="selected" value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifIdc==0){
				var weic='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option selected="selected" value="休息日">休息日</option></select>';
			}
			shifIdc.innerHTML = touc+weic;
			tr.appendChild(shifIdc);
			
			var shifIdd = document.createElement("td");
			
			var toud = '<select name="shifIdd" class="chzn-select-search" style="width: 80px;">';
			if(shift.shiftDto.shifNamed=='早班'){
				var weid='<option selected="selected" value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamed=='中班'){
				var weid='<option value="早班">早班</option>'
				+'<option selected="selected" value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamed=='晚班'){
				var weid='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option selected="selected" value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamed=='全班'){
				var weid='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option selected="selected" value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifIdd==0){
				var weid='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option selected="selected" value="休息日">休息日</option></select>';
			}
			shifIdd.innerHTML = toud+weid;
			tr.appendChild(shifIdd);
			
			
			var shifIde = document.createElement("td");
			
			var toue = '<select name="shifIde" class="chzn-select-search" style="width: 80px;">';
			if(shift.shiftDto.shifNamee=='早班'){
				var weie='<option selected="selected" value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamee=='中班'){
				var weie='<option value="早班">早班</option>'
				+'<option selected="selected" value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamee=='晚班'){
				var weie='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option selected="selected" value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamee=='全班'){
				var weie='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option selected="selected" value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifIde==0){
				var weie='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option selected="selected" value="休息日">休息日</option></select>';
			}
			shifIde.innerHTML = toue+weie;
			tr.appendChild(shifIde);
			
			var shifIdf = document.createElement("td");
			 
			var touf = '<select name="shifIdf" class="chzn-select-search" style="width: 80px;">';
			if(shift.shiftDto.shifNamef=='早班'){
				var weif='<option selected="selected" value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamef=='中班'){
				var weif='<option value="早班">早班</option>'
				+'<option selected="selected" value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamef=='晚班'){
				var weif='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option selected="selected" value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNamef=='全班'){
				var weif='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option selected="selected" value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifIdf==0){
				var weif='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option selected="selected" value="休息日">休息日</option></select>';
			}
			shifIdf.innerHTML = touf+weif;
			tr.appendChild(shifIdf);
			
			var shifIdg = document.createElement("td");
			
			var toug = '<select name="shifIdg" class="chzn-select-search" style="width: 80px;">';
			if(shift.shiftDto.shifNameg=='早班'){
				var weig='<option selected="selected" value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNameg=='中班'){
				var weig='<option value="早班">早班</option>'
				+'<option selected="selected" value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNameg=='晚班'){
				var weig='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option selected="selected" value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifNameg=='全班'){
				var weig='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option selected="selected" value="全班">全班</option>'
				+'<option value="休息日">休息日</option></select>';
			}else if(shift.shiftDto.shifIdg==0){
				var weig='<option value="早班">早班</option>'
				+'<option value="中班">中班</option>'
				+'<option value="晚班">晚班</option>'
				+'<option value="全班">全班</option>'
				+'<option selected="selected" value="休息日">休息日</option></select>';
			}
			shifIdg.innerHTML = toug+weig;
			tr.appendChild(shifIdg);
		}
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "iconfa-pencil project-icon");
		var name=shift.name;
		editSpan.setAttribute("onclick", "openedit(" + shift.employeeId + ",\'"+name+"\')");
		
		var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "iconfa-trash project-icon");
		removeSpan.setAttribute("onclick", "deleteinfo(" + shift.employeeId + ")");
		operateTd.appendChild(editSpan);
		operateTd.appendChild(removeSpan);
		
		tr.appendChild(operateTd);
		tbody.appendChild(tr);
		
	}
	jQuery(".collect-money-table tbody").remove();
	jQuery(".collect-money-table").append(tbody);
	jQuery("select[name='shifIda']").chosen();
	jQuery("select[name='shifIdb']").chosen();
	jQuery("select[name='shifIdc']").chosen();
	jQuery("select[name='shifIdd']").chosen();
	jQuery("select[name='shifIde']").chosen();
	jQuery("select[name='shifIdf']").chosen();
	jQuery("select[name='shifIdg']").chosen();
	
}*/
function refreshTableData(page){
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	
	var List = page.results;
	var tbody = document.createElement("tbody");
	for (var i = 0; i < List.length; i++) {
		var shift = List[i];
		
		var tr = document.createElement("tr");
		tr.setAttribute("id", shift.employeeId);
		
		var employeeCode = document.createElement("td");
		employeeCode.innerHTML = shift.employeeCode;
		tr.appendChild(employeeCode);
		
		var name = document.createElement("td");
		name.innerHTML = shift.name;
		tr.appendChild(name);
		
		var shifIda = document.createElement("td");
		if(shift.shifIda==null){
			shifIda.innerHTML = "休息日";
		}else{
			shifIda.innerHTML = shift.shifIda;
		}
		tr.appendChild(shifIda);
		
		var shifIdb = document.createElement("td");
		if(shift.shifIdb==null){
			shifIdb.innerHTML = "休息日";
		}else{
			shifIdb.innerHTML = shift.shifIdb;
		}
		tr.appendChild(shifIdb);
		
		var shifIdc = document.createElement("td");
		if(shift.shifIdc==null){
			shifIdc.innerHTML = "休息日";
		}else{
			shifIdc.innerHTML = shift.shifIdc;
		}
		tr.appendChild(shifIdc);
		
		var shifIdd = document.createElement("td");
		if(shift.shifIdd==null){
			shifIdd.innerHTML = "休息日";
		}else{
			shifIdd.innerHTML = shift.shifIdd;
		}
		tr.appendChild(shifIdd);
		
		
		var shifIde = document.createElement("td");
		if(shift.shifIde==null){
			shifIde.innerHTML = "休息日";
		}else{
			shifIde.innerHTML = shift.shifIde;
		}
		tr.appendChild(shifIde);
		
		var shifIdf = document.createElement("td");
		if(shift.shifIdf==null){
			shifIdf.innerHTML = "休息日";
		}else{
			shifIdf.innerHTML = shift.shifIdf;
		}
		tr.appendChild(shifIdf);
		
		var shifIdg = document.createElement("td");
		
		if(shift.shifIdg==null){
			shifIdg.innerHTML = "休息日";
		}else{
			shifIdg.innerHTML = shift.shifIdg;
		}
		tr.appendChild(shifIdg);
		
		var operateTd = document.createElement("td");
		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "iconfa-pencil project-icon");
		var name=shift.name;
		editSpan.setAttribute("onclick", "openedit(" + shift.employeeId + ",\'"+name+"\')");
		
		var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "iconfa-trash project-icon");
		removeSpan.setAttribute("onclick", "deleteinfo(" + shift.employeeId + ")");
		operateTd.appendChild(editSpan);
		operateTd.appendChild(removeSpan);
		
		tr.appendChild(operateTd);
		tbody.appendChild(tr);
	}
	jQuery(".collect-money-table tbody").remove();
	jQuery(".collect-money-table").append(tbody);
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
//修改人员班次信息
function openedit(id,name){
	
	jQuery("#dataId").val(1);
	//getDeptEmployee();
	jQuery("#employeeId2").val(id);
	jQuery("#employeeName2").val(name);
	jQuery("#employeeName2").attr("disabled",true);
	jQuery('#add-emp-yuangong-banci').modal();
	jQuery("#xiugai").show();
	jQuery("#xinzeng").hide();
	
	openshiftinfo();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "shift/action/getemployeeshift",
		data : "employeeId=" + id,
		dataType : "json",
		success : function(e){
			//设置人员为不可输入
			jQuery("#employeeId2").val(id);
			//jQuery("#employeeId1").attr("disabled",true); 
			jQuery("#employeeId1").trigger("liszt:updated");
			var th = jQuery(".check-radio");
			var tr = th.parents("tr");
			tr.find(".check-radio").removeClass("check-after");
			//周一
			//var aa=e.msg.shifIda-1;
			var aaa=e.msg.shifNamea;
			if(aaa=="早班"){
				aaa=0;
			}else if(aaa=="中班"){
				aaa=1;
			}else if(aaa=="晚班"){
				aaa=2;
			}else if(aaa=="全班"){
				aaa=3;
			}else{
				aaa=4;
			}
			//var bb=e.msg.shifIdb-1;
			var bbb=e.msg.shifNameb;
			if(bbb=="早班"){
				bbb=0;
			}else if(bbb=="中班"){
				bbb=1;
			}else if(bbb=="晚班"){
				bbb=2;
			}else if(bbb=="全班"){
				bbb=3;
			}else{
				bbb=4;
			}
			//var cc=e.msg.shifIdc-1;
			var ccc=e.msg.shifNamec;
			if(ccc=="早班"){
				ccc=0;
			}else if(ccc=="中班"){
				ccc=1;
			}else if(ccc=="晚班"){
				ccc=2;
			}else if(ccc=="全班"){
				ccc=3;
			}else{
				ccc=4;
			}
			//var dd=e.msg.shifIdd-1;
			var ddd=e.msg.shifNamed;
			if(ddd=="早班"){
				ddd=0;
			}else if(ddd=="中班"){
				ddd=1;
			}else if(ddd=="晚班"){
				ddd=2;
			}else if(ddd=="全班"){
				ddd=3;
			}else{
				ddd=4;
			}
			//var ee=e.msg.shifIde-1;
			var eee=e.msg.shifNamee;
			if(eee=="早班"){
				eee=0;
			}else if(eee=="中班"){
				eee=1;
			}else if(eee=="晚班"){
				eee=2;
			}else if(eee=="全班"){
				eee=3;
			}else{
				eee=4;
			}
			//var ff=e.msg.shifIdf-1;
			var fff=e.msg.shifNamef;
			if(fff=="早班"){
				fff=0;
			}else if(fff=="中班"){
				fff=1;
			}else if(fff=="晚班"){
				fff=2;
			}else if(fff=="全班"){
				fff=3;
			}else{
				fff=4;
			}
			//var gg=e.msg.shifIdg-1;
			var ggg=e.msg.shifNameg;
			if(ggg=="早班"){
				ggg=0;
			}else if(ggg=="中班"){
				ggg=1;
			}else if(ggg=="晚班"){
				ggg=2;
			}else if(ggg=="全班"){
				ggg=3;
			}else{
				ggg=4;
			}
			//var addData=JSON.stringify(e.msg);
			jQuery('input:radio[name="zhouyi"]').eq(aaa).attr("checked",'checked');
			var a=jQuery('input:radio[name="zhouyi"]').eq(aaa);
		    a.parent().find("span").addClass("check-after");
		    
		    jQuery('input:radio[name="zhouer"]').eq(bbb).attr("checked",'checked');
			var a=jQuery('input:radio[name="zhouer"]').eq(bbb);
		    a.parent().find("span").addClass("check-after");
		    
		    jQuery('input:radio[name="zhousan"]').eq(ccc).attr("checked",'checked');
			var a=jQuery('input:radio[name="zhousan"]').eq(ccc);
		    a.parent().find("span").addClass("check-after");
		    
		    jQuery('input:radio[name="zhousi"]').eq(ddd).attr("checked",'checked');
			var a=jQuery('input:radio[name="zhousi"]').eq(ddd);
		    a.parent().find("span").addClass("check-after");
		    
		    jQuery('input:radio[name="zhouwu"]').eq(eee).attr("checked",'checked');
			var a=jQuery('input:radio[name="zhouwu"]').eq(eee);
		    a.parent().find("span").addClass("check-after");
		    
		    jQuery('input:radio[name="zhouliu"]').eq(fff).attr("checked",'checked');
			var a=jQuery('input:radio[name="zhouliu"]').eq(fff);
		    a.parent().find("span").addClass("check-after");
		    
		    jQuery('input:radio[name="zhouri"]').eq(ggg).attr("checked",'checked');
			var a=jQuery('input:radio[name="zhouri"]').eq(ggg);
		    a.parent().find("span").addClass("check-after");
			
		}
	});
	
	
	
	
}

function changeradio(id){

	var th = jQuery(".check-radio");
	var tr = th.parents("tr");
	tr.find(".check-radio").removeClass("check-after");
	jQuery('input[type="radio"]').attr("checked",false); 
}
//打开新增员工班次信息
function addshift(){
	jQuery('#add-emp-yuangong-banci').modal();
	jQuery("#employeeId1").attr("disabled",false); 
	jQuery("#employeeId1").trigger("liszt:updated");
	openshiftinfo();
	getDeptEmployee();
	var th = jQuery(".check-radio");
	var tr = th.parents("tr");
	tr.find(".check-radio").removeClass("check-after");
	jQuery('input[type="radio"]').attr("checked",false);
	jQuery("#xiugai").hide();
	jQuery("#xinzeng").show();
	jQuery("#dataId").val("");
}
//根据部门标识获取人员信息
function getDeptEmployee(){
	var deptId=jQuery("#xdeptId").val().substr(6, 11);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/getDeptEmployee",
		data : "deptId="+deptId,
		dataType : "json",
		success : function(e){
			var list=e.msg;
			jQuery("#employeeId1").children().remove();
			jQuery("#employeeId1").trigger("liszt:updated");
			for (var i = 0; i < list.length; i++) {
				var lal = jQuery("<option value='"+list[i].employeeId+"'>"+list[i].name+"</option>");
				jQuery("#employeeId1").append(lal);
			}
			
			jQuery("#employeeId1").trigger("liszt:updated");
		}
	});
}
//打开员工新增班次时候获取相关班次信息
function openshiftinfo(){
	
	jQuery('.zaoban').show();
	jQuery('.zhongban').show();
	jQuery('.wanban').show();
	jQuery('.quanban').show();
	var deptId=jQuery("#xdeptId").val().substr(6, 11);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "shift/action/getshift",
		data : "deptId=" +deptId,
		dataType : "json",
		success : function(e){
			var list=e.msg;
			if(list.length>0){
				for (var i = 0; i < list.length; i++) {
					
					if(list[i].shifName=="早班"){
						if(list[i].startTime!=null&&list[i].startTime!=""){
							jQuery(".zaoban").find("input").val(list[i].shifId);
						}else{
							jQuery('.zaoban').hide();
						}
						
					}else if(list[i].shifName=="中班"){
						if(list[i].startTime!=null&&list[i].startTime!=""){
							jQuery(".zhongban").find("input").val(list[i].shifId);
						}else{
							jQuery('.zhongban').hide();
						}
					}else if(list[i].shifName=="晚班"){
						if(list[i].startTime!=null&&list[i].startTime!=""){
							jQuery(".wanban").find("input").val(list[i].shifId);
						}else{
							jQuery('.wanban').hide();
						}
					}else if(list[i].shifName=="全班"){
						if(list[i].startTime!=null&&list[i].startTime!=""){
							jQuery(".quanban").find("input").val(list[i].shifId);
						}else{
							jQuery('.quanban').hide();
						}
					}
				}
			}else{
				jQuery('.zaoban').hide();
				jQuery('.zhongban').hide();
				jQuery('.wanban').hide();
				jQuery('.quanban').hide();
				jQuery('.xiuxi').hide();
				dialog("请先设置班次时间！");
			}
			
		}
	});
}
function save(){
	
	var addData = {};
	var dataId=jQuery("#dataId").val();
	
	
	/*if(dataId!=null &&dataId!=""){
		addData["dataId"] = dataId;
	}*/
	
	var shifIda=jQuery(".check-after").parent().find("input[name='zhouyi']").val();
	//var shifIda=jQuery(':radio[name="zhouyi"]:checked').val();
	if(shifIda==""||shifIda==null){
		dialog("周一班次不能为空！");
		return;
	}
	addData["shifIda"] = shifIda;
	
	var shifIdb=jQuery(".check-after").parent().find("input[name='zhouer']").val();
	//var shifIdb=jQuery(':radio[name="zhouer"]:checked').val();
	if(shifIdb==""||shifIdb==null){
		dialog("周二班次不能为空！");
		return;
	}
	addData["shifIdb"] = shifIdb;
	
	var shifIdc=jQuery(".check-after").parent().find("input[name='zhousan']").val();
	//var shifIdc=jQuery(':radio[name="zhousan"]:checked').val();
	if(shifIdc==""||shifIdc==null){
		dialog("周三班次不能为空！");
		return;
	}
	addData["shifIdc"] = shifIdc;
	
	var shifIdd=jQuery(".check-after").parent().find("input[name='zhousi']").val();
	//var shifIdd=jQuery(':radio[name="zhousi"]:checked').val();
	if(shifIdd==""||shifIdd==null){
		dialog("周四班次不能为空！");
		return;
	}
	addData["shifIdd"] = shifIdd;
	
	var shifIde=jQuery(".check-after").parent().find("input[name='zhouwu']").val();
	//var shifIde=jQuery(':radio[name="zhouwu"]:checked').val();
	if(shifIde==""||shifIde==null){
		dialog("周五班次不能为空！");
		return;
	}
	addData["shifIde"] = shifIde;
	
	var shifIdf=jQuery(".check-after").parent().find("input[name='zhouliu']").val();
	//var shifIdf=jQuery(':radio[name="zhouliu"]:checked').val();
	if(shifIdf==""||shifIdf==null){
		dialog("周六班次不能为空！");
		return;
	}
	addData["shifIdf"] = shifIdf;
	
	var shifIdg=jQuery(".check-after").parent().find("input[name='zhouri']").val();
	//var shifIdg=jQuery(':radio[name="zhouri"]:checked').val();
	if(shifIdg==""||shifIdg==null){
		dialog("周日班次不能为空！");
		return;
	}
	addData["shifIdg"] = shifIdg;
	
	if(dataId==null ||dataId==""){
		var employeeId=jQuery("#employeeId1").val();
		if(employeeId==null ||employeeId==""){
			dialog("人员不能为空！");
			return;
		}
		addData["employeeId"] = employeeId;
		
		var addData=JSON.stringify(addData);
		
		jQuery.ajax({
			type : "post",
			url : baseUrl + "shift/action/addemployeeshift",
			data : "addData="+addData,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					jQuery('#add-emp-yuangong-banci').modal('hide');
					dialog(e.msg);
					return;
				}
				jQuery('#add-emp-yuangong-banci').modal('hide');
				dialog(e.msg);
				changePage();
			}
		});
	}else{
		var employeeId=jQuery("#employeeId2").val();
		if(employeeId==null ||employeeId==""){
			dialog("人员不能为空！");
			return;
		}
		addData["employeeId"] = employeeId;
		var addData=JSON.stringify(addData);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "shift/action/updateemployeeshift",
			data : "addData="+addData,
			dataType : "json",
			success : function(e){
				jQuery('#add-emp-yuangong-banci').modal('hide');
				dialog(e.msg);
				changePage();
			}
		});
	}
}
//删除人员班次信息
function deleteinfo(id){
	if(confirm("确认要删除该条信息吗？")){ 
		jQuery.ajax({
			type : "post",
			url : baseUrl + "shift/action/deleteemployeeshift",
			data : "employeeId=" + id,
			dataType : "json",
			success : function(e){
				
				dialog(e.msg);
				jQuery(".collect-money-table tr[id='"+id+ "']").fadeOut(800).remove();
			}
		});
		}
}
//打开修改班次页面
function openeditshif(){
	//先清空之前的数据
	jQuery("#zaostart").val("");
	jQuery("#zaoend").val("");
	jQuery("#zhongstart").val("");
	jQuery("#zhongend").val("");
	jQuery("#wanstart").val("");
	jQuery("#wanend").val("");
	jQuery("#quanstart").val("");
	jQuery("#quanend").val("");
	jQuery('#fix-banci-modal').modal();
	var deptId=jQuery("#xdeptId").val().substr(6, 11);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "shift/action/getshift",
		data : "deptId=" +deptId,
		dataType : "json",
		success : function(e){
			var list=e.msg;
			
			if(list.length>0){
				for (var i = 0; i < list.length; i++) {
					
					if(list[i].shifName=="早班"){
						jQuery("#zaostart").val(list[i].startTime);
						jQuery("#zaoend").val(list[i].endTime);
					}else if(list[i].shifName=="中班"){
						jQuery("#zhongstart").val(list[i].startTime);
						jQuery("#zhongend").val(list[i].endTime);
					}else if(list[i].shifName=="晚班"){
						jQuery("#wanstart").val(list[i].startTime);
						jQuery("#wanend").val(list[i].endTime);
					}else if(list[i].shifName=="全班"){
						jQuery("#quanstart").val(list[i].startTime);
						jQuery("#quanend").val(list[i].endTime);
					}
				}
				jQuery("#queren").show();
				jQuery("#tijiao").hide();
				
			}else{
				//当没有设计班次信息时候
				if(confirm("由于之前没有设置班次时间，需要默认设置时间吗？")){ 
					jQuery("#zaostart").val("08:00");
					jQuery("#zaoend").val("12:00");
					jQuery("#zhongstart").val("13:00");
					jQuery("#zhongend").val("18:00");
					jQuery("#wanstart").val("18:00");
					jQuery("#wanend").val("22:00");
					jQuery("#quanstart").val("08:00");
					jQuery("#quanend").val("22:00");
					}
				//修改按钮变成提交按钮
				jQuery("#queren").hide();
				jQuery("#tijiao").show();
			}
			
		}
	});
}


//班次时间展示
function shiftinfo(id){
	var deptId=id.substr(6, 11);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "shift/action/getshift",
		data : "deptId=" +deptId,
		dataType : "json",
		success : function(e){
			var list=e.msg;
			if(list.length>0){
				for (var i = 0; i < list.length; i++) {
					
					if(list[i].shifName=="早班"){
						jQuery(".zao").text(list[i].startTime+"-"+list[i].endTime);
					}else if(list[i].shifName=="中班"){
						jQuery(".zhong").text(list[i].startTime+"-"+list[i].endTime);
					}else if(list[i].shifName=="晚班"){
						jQuery(".wan").text(list[i].startTime+"-"+list[i].endTime);
					}else if(list[i].shifName=="全班"){
						jQuery(".quan").text(list[i].startTime+"-"+list[i].endTime);
					}
				}
			}else{
				jQuery(".zao").text("-");
				jQuery(".zhong").text("-");
				jQuery(".wan").text("-");
				jQuery(".quan").text("-");
			}
			
		}
	});
}
//修改班次信息
function saveshif(){
	
	var deptId=jQuery("#xdeptId").val().substr(6, 11);
	//获取班次相关信息
	var zaostart=jQuery("#zaostart").val();
	var zaoend=jQuery("#zaoend").val();
	if(zaostart!=null&&zaostart!=""){
		if(zaoend==""||zaoend==null){
			dialog("早班时间 开始时间设置了，结束时间也必须设置！");
			return;
		}
	}
	if(zaoend!=null&&zaoend!=""){
		if(zaostart==""||zaostart==null){
			dialog("早班时间 结束时间设置了，开始时间也必须设置！");
			return;
		}
	}
	var zhongstart=jQuery("#zhongstart").val();
	var zhongend=jQuery("#zhongend").val();
	if(zhongstart!=null&&zhongstart!=""){
		if(zhongend==""||zhongend==null){
			dialog("中班时间 开始时间设置了，结束时间也必须设置！");
			return;
		}
	}
	if(zhongend!=null&&zhongend!=""){
		if(zhongstart==""||zhongstart==null){
			dialog("中班时间 结束时间设置了，开始时间也必须设置！");
			return;
		}
	}
	var wanstart=jQuery("#wanstart").val();
	var wanend=jQuery("#wanend").val();
	if(wanstart!=null&&wanstart!=""){
		if(wanend==""||wanend==null){
			dialog("晚班时间 开始时间设置了，结束时间也必须设置！");
			return;
		}
	}
	if(wanend!=null&&wanend!=""){
		if(wanstart==""||wanstart==null){
			dialog("晚班时间 结束时间设置了，开始时间也必须设置！");
			return;
		}
	}
	var quanstart=jQuery("#quanstart").val();
	var quanend=jQuery("#quanend").val();
	if(quanstart!=null&&quanstart!=""){
		if(quanend==""||quanend==null){
			dialog("全班时间 开始时间设置了，结束时间也必须设置！");
			return;
		}
	}
	if(quanend!=null&&quanend!=""){
		if(quanstart==""||quanstart==null){
			dialog("全班时间 结束时间设置了，开始时间也必须设置！");
			return;
		}
	}
	if(zaostart==""&&zaoend==""&&zhongstart==""&&zhongend==""&&wanstart==""&&wanend==""&&quanstart==""&&quanend==""){
		dialog("至少要设置一个班次时间！");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "shift/action/updateshift",
		data : "deptId="+deptId+"&zaostart="+zaostart+"&zaoend="+zaoend+"&zhongstart="+zhongstart+"&zhongend="+zhongend+"&wanstart="+wanstart+"&wanend="+wanend+
		"&quanstart="+quanstart+"&quanend="+quanend,
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
//新增
function tijiaoshif(){
	
	var deptId=jQuery("#xdeptId").val().substr(6, 11);
	//获取班次相关信息
	var zaostart=jQuery("#zaostart").val();
	var zaoend=jQuery("#zaoend").val();
	if(zaostart!=null&&zaostart!=""){
		if(zaoend==""||zaoend==null){
			dialog("早班时间 开始时间设置了，结束时间也必须设置！");
			return;
		}
	}
	if(zaoend!=null&&zaoend!=""){
		if(zaostart==""||zaostart==null){
			dialog("早班时间 结束时间设置了，开始时间也必须设置！");
			return;
		}
	}
	var zhongstart=jQuery("#zhongstart").val();
	var zhongend=jQuery("#zhongend").val();
	if(zhongstart!=null&&zhongstart!=""){
		if(zhongend==""||zhongend==null){
			dialog("中班时间 开始时间设置了，结束时间也必须设置！");
			return;
		}
	}
	if(zhongend!=null&&zhongend!=""){
		if(zhongstart==""||zhongstart==null){
			dialog("中班时间 结束时间设置了，开始时间也必须设置！");
			return;
		}
	}
	var wanstart=jQuery("#wanstart").val();
	var wanend=jQuery("#wanend").val();
	if(wanstart!=null&&wanstart!=""){
		if(wanend==""||wanend==null){
			dialog("晚班时间 开始时间设置了，结束时间也必须设置！");
			return;
		}
	}
	if(wanend!=null&&wanend!=""){
		if(wanstart==""||wanstart==null){
			dialog("晚班时间 结束时间设置了，开始时间也必须设置！");
			return;
		}
	}
	var quanstart=jQuery("#quanstart").val();
	var quanend=jQuery("#quanend").val();
	if(quanstart!=null&&quanstart!=""){
		if(quanend==""||quanend==null){
			dialog("全班时间 开始时间设置了，结束时间也必须设置！");
			return;
		}
	}
	if(quanend!=null&&quanend!=""){
		if(quanstart==""||quanstart==null){
			dialog("全班时间 结束时间设置了，开始时间也必须设置！");
			return;
		}
	}
	if(zaostart==""&&zaoend==""&&zhongstart==""&&zhongend==""&&wanstart==""&&wanend==""&&quanstart==""&&quanend==""){
		dialog("至少要设置一个班次时间！");
		return;
	}
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "shift/action/addshift",
		data : "deptId="+deptId+"&zaostart="+zaostart+"&zaoend="+zaoend+"&zhongstart="+zhongstart+"&zhongend="+zhongend+"&wanstart="+wanstart+"&wanend="+wanend+
		"&quanstart="+quanstart+"&quanend="+quanend,
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
//取消班次设置框
function quxiaoshif(){
	jQuery('#fix-banci-modal').modal("hide");
}
//取消班次设置框
function quxiaoemployeeshif(){
	jQuery('#add-emp-yuangong-banci').modal("hide");
}

function UpladFile() {
    var fileObj = document.getElementById("file").files[0]; // 获取文件对象
    var FileController = baseUrl +"shift/action/importExcle";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("filevalue", fileObj);                           // 文件对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (e) {
       
    	/*dialog(xhr.responseText);*/
    	var json = eval("("+xhr.responseText+")");
    	if(json.code!=0){
    		jQuery('#toLeadModal').modal("hide");
    		dialog(json.msg);
    		return;
    	}
    	jQuery('#toLeadModal').modal("hide");
        dialog(json.msg);
        changePage();
    };
    
    xhr.send(form);
    
}

jQuery("#downLondimport").click(function(){
	
	window.open(baseUrl + "model/shift.xls");
});