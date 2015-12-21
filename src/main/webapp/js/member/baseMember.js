  var sex = "全部";
  var levelId = 0;	
  var serchType = 0;
  jQuery("#show_more").on("click",function(){
	    var detailInfo = jQuery(".more-condition-table");
	    //将会员手机姓名条件清空
	    jQuery("#serchMemberByNameOrPhone").val("");
	    if(detailInfo.is(':visible')){
	    	serchType = 0;
	        detailInfo.hide();
	        jQuery(".show-more").removeClass("open");
	    }else{
	    	serchType = 1;
	        detailInfo.show();
	        jQuery(".show-more").addClass("open");
	    }
 });
    //查看筛选器搜索结果
    jQuery("#serch").on("click",function(){
    	var ok = checkInput();
    	if(ok==false){
    		return;
    	}
    	sex = "全部";
    	levelId = 0;
    	pageNo = 1;
    	var data = "pageNo="+pageNo+"&pageSize="+pageSize+"&sex="+sex+"&levelId="+levelId;
    	data = initDate(data);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/serch/by/screen",
			data : data,
			dataType : "json",
			success : function(e){
				jQuery("#serchMemberByNameOrPhone").val('');
				if(e.code == 0){
					jQuery("#member_serch_count").text("当前满足条件 "+e.msg.totalRecord+" 人");
					jQuery(".perpage").text(e.msg.pageSize);
					totalPage = e.msg.totalPage;
					pageNo = e.msg.pageNo;
					pageSize = e.msg.pageSize;
					jQuery("#init_member").empty();
					initTable(e);
				}else{
					jQuery("#member_serch_count").text("当前满足条件 "+0+" 人");
					jQuery("#init_member").empty();
				}
			}
		});
		jQuery("#tishi").show();
    });
    jQuery("#baocun2").on("click",function(){
        jQuery("#add-member-group").modal();
        jQuery(".btn.modal-cancel").on("click",function(){
        	jQuery("#add-member-group").modal('hide');
        });
    });
    jQuery("#baocun").on("click",function(){
    	var groupName = jQuery("#group_name").val();
    	var data = "screeningName="+groupName;
    	data = initDate(data);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/add/memberScreening",
			data : data,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					dialog("新增成功");
					jQuery("#add-member-group").modal('hide');
				}
			}
		});
    });
    /*放弃保存*/
    jQuery("#fangqi").on("click",function(){
        jQuery("#tishi").hide();
    });
    //根据会员名称和电话查询
    jQuery("#serchMemberByNameOrPhoneDoc").on("click",function(){
  	  var content = jQuery("#serchMemberByNameOrPhone").val();
  	  if(content == ""){
  		  location.reload();
  		  return;
  	  }
  	  jQuery(".table.table-bordered.table-striped.member-list-table").find("th").eq(3).find("span[class='dropdown-toggle']").text("会员等级");
	  jQuery(".table.table-bordered.table-striped.member-list-table").find("th").eq(2).find("span[class='dropdown-toggle']").text("性别");
	  sex = "全部";
	  pageNo = 1;
  	  levelId = 0;
  	  serchType = 0;
  	  //隐藏多条件
  	  jQuery(".table.more-condition-table").hide();
  	  jQuery("#tishi").hide();
  	  serchPhoneName(content);
    });
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
//分页处理
  function changePage(){
	  console.log(serchType);
	  if (serchType == 1){
		var data = "pageNo="+pageNo+"&pageSize="+pageSize+"&sex="+sex+"&levelId="+levelId;
		data = initDate(data);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/serch/by/screen",
			data : data,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					jQuery(".perpage").text(e.msg.pageSize);
					totalPage = e.msg.totalPage;
					jQuery("#init_member").empty();
					initTable(e);
				}else{
					jQuery("#member_serch_count").text("当前满足条件 0 人");
					jQuery("#init_member").empty();
				}
			}
		});
	  }else{
		  var content = jQuery("#serchMemberByNameOrPhone").val();
		  serchPhoneName(content);
	  }
 }

  //封装查询date数据
  function initDate(data){
	  	var chuzhis = jQuery("#chuzhi1").val();
		if(chuzhis!="")data=data+"&unusedBalanceStart="+chuzhis;
	  	var chuzhie = jQuery("#chuzhi2").val();
	  	if(chuzhie!="")data=data+"&unusedBalanceEnd="+chuzhie;
	  	var jifenyes = jQuery("#jifenye1").val();
	  	if(jifenyes!="")data=data+"&integralBalanceStart="+jifenyes;
	  	var jifenyee = jQuery("#jifenye2").val();
	  	if(jifenyee!="")data=data+"&integralBalanceEnd="+jifenyee;
	  	var birthdays = jQuery("#birthday-start").val();
	  	if(birthdays!="")data=data+"&birthdayStart="+birthdays;
	  	var birthdaye = jQuery("#birthday-end").val();
	  	if(birthdaye!="")data=data+"&birthdayEnd="+birthdaye;
	  	var registers = jQuery("#register-start").val();
	  	if(registers!="")data=data+"&registrationDateStart="+registers;
	  	var registere = jQuery("#register-end").val();
	  	if(registere!="")data=data+"&registrationDateEnd="+registere;
	  	var xiaofeis = jQuery("#xiaofje1").val();
	  	if(xiaofeis!="")data=data+"&consumptionAverageStart="+xiaofeis;
	  	var xiaofeie = jQuery("#xiaofje2").val();
	  	if(xiaofeie!="")data=data+"&consumptionAverageEnd="+xiaofeie;
	   	var leijixfs = jQuery("#leijixf1").val();
	  	if(leijixfs!="")data=data+"&cumulativeConsumptionStart="+leijixfs;
	  	var leijixfe = jQuery("#leijixf2").val();
	  	if(leijixfe!="")data=data+"&cumulativeConsumptionEnd="+leijixfe;
	  	var scxf1 = jQuery("#scxf1").val();
	  	if(scxf1!="")data=data+"&lastConsumeTimeStart="+scxf1;
	  	var scxf2 = jQuery("#scxf2").val();
	  	if(scxf2!="")data=data+"&lastConsumeTimeEnd="+scxf2;
	  	//分店数据
	  	var branchOffice = jQuery("#branchOffice").val();
	  	data = data + "&branchOffice=" + branchOffice;
	  	return data;
  }

  function serchPhoneName(content){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/serch/by/nameOrPhone",
			data : "pageNo="+ pageNo + "&pageSize=" + pageSize + "&content="+content+"&sex="+sex+"&levelId="+levelId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					jQuery(".perpage").text(e.msg.pageSize);
					totalPage = e.msg.totalPage;
					jQuery("#init_member").empty();
					initTable(e);
				}else{
					jQuery("#init_member").empty();
				}
			}
		});
  }
  
  //根据性别或者会员等级进行筛选
  function serchMemberBySex(ssex,obj){
	  jQuery(".table.table-bordered.table-striped.member-list-table").find("th").eq(3).find("span[class='dropdown-toggle']").text("会员等级");
	  jQuery(obj).parent().parent().prev(".dropdown-toggle").text('性别'+'('+ssex+')');
	  jQuery(obj).parent().parent().prev(".dropdown-toggle").append('<span class="caret"></span>');
	  //在点击了按此条件查询的时候一定要全部还原,包括分页的信息
	  levelId = 0;
	  pageNo = 1;
	  pageSize = 50;
	  if(ssex == "全部"){
	      sex = "全部";
		  changePage();
	  }else{
		  sex = ssex;
		  changePage();
	  }
  }
  function serchMemberByLevel(slevelId,levelName,obj){
	  jQuery(".table.table-bordered.table-striped.member-list-table").find("th").eq(2).find("span[class='dropdown-toggle']").text("性别");
	  jQuery(obj).parent().parent().prev(".dropdown-toggle").text('会员等级'+'('+levelName+')');
	  jQuery(obj).parent().parent().prev(".dropdown-toggle").append('<span class="caret"></span>');
	  //在点击了按此条件查询的时候一定要全部还原,包括分页的信息
	  sex = "全部";
	  pageNo = 1;
	  pageSize = 50;
	  if(slevelId=="0"){
		  levelId = 0;
		  changePage();
	  }else{
		  levelId = slevelId;
		  changePage();
	  }
  }
  //拼装table
  function initTable(e){
		for (var i = 0; i < e.msg.results.length; i++) {
			var tr = jQuery("<tr></tr>");
			var td0 = jQuery("<td>"+e.msg.results[i].storeName+"</td>");
			var td1 = jQuery("<td>"+e.msg.results[i].phone+"</td>");
			var td2 = jQuery("<td class='can-click' data-target='#member-data' onclick='selectMemberInfo("+e.msg.results[i].memberId+")' data-toggle='modal' id="+e.msg.results[i].memberId+">"+e.msg.results[i].name+"</td>");
			var td3 = jQuery("<td>"+e.msg.results[i].sex+"</td>");
			var td4 = jQuery("<td>"+e.msg.results[i].levelName+"</td>");
			var td5;
			if (e.msg.results[i].birthday==null||e.msg.results[i].birthday==""){
				td5 = jQuery("<td>"+"</td>");
			}else{
				td5 = jQuery("<td>"+e.msg.results[i].birthday+"</td>");
			}
			var td6 = jQuery("<td>"+e.msg.results[i].balanceAmount+"</td>");
			var td7 = jQuery("<td>"+e.msg.results[i].balanceIntegral+"</td>");
			var td8 = jQuery("<td>"+e.msg.results[i].totalConsumeAmount+"</td>");
			var td9 = jQuery("<td>"+e.msg.results[i].avgConsumeAmount+"</td>");
			var td10 = jQuery("<td>"+e.msg.results[i].createTime+"</td>");
			tr.append(td0);
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			tr.append(td5);
			tr.append(td6);
			tr.append(td7);
			tr.append(td8);
			tr.append(td9);
			tr.append(td10);
			jQuery("#init_member").append(tr);
		}
  }
  
  //校验数据
  function checkInput(){
		var chuzhis = jQuery("#chuzhi1").val();
	  	var chuzhie = jQuery("#chuzhi2").val();
	  	var jifenyes = jQuery("#jifenye1").val();
	  	var jifenyee = jQuery("#jifenye2").val();
	  	var birthdays = jQuery("#birthday-start").val();
	  	var birthdaye = jQuery("#birthday-end").val();
	  	var registers = jQuery("#register-start").val();
	  	var registere = jQuery("#register-end").val();
	  	var xiaofeis = jQuery("#xiaofje1").val();
	  	var xiaofeie = jQuery("#xiaofje2").val();
	   	var leijixfs = jQuery("#leijixf1").val();
	  	var leijixfe = jQuery("#leijixf2").val();
	  	var scxf1 = jQuery("#scxf1").val();
	  	var scxf2 = jQuery("#scxf2").val();
	  	
	  if(chuzhis!=""){
		  if((/(^[0-9]\d*$)/.test(chuzhis))!=true){
			  dialog("储值余额请输入整数");
			  return false;
		  }
	  }
	  if(chuzhie!=""){
		  if((/(^[0-9]\d*$)/.test(chuzhie))!=true){
			  dialog("储值余额请输入整数");
			  return false;
		  }
	  }
	  if(jifenyes!=""){
		  if((/(^[0-9]\d*$)/.test(jifenyes))!=true){
			  dialog("积分余额请输入整数");
			  return false;
		  }
	  }
	  if(jifenyee!=""){
		  if((/(^[0-9]\d*$)/.test(jifenyee))!=true){
			  dialog("积分余额请输入整数");
			  return false;
		  }
	  }
	  if(xiaofeis!=""){
		  if((/(^[0-9]\d*$)/.test(xiaofeis))!=true){
			  dialog("消费均额请输入整数");
			  return false;
		  }
	  }
	  if(xiaofeie!=""){
		  if((/(^[0-9]\d*$)/.test(xiaofeie))!=true){
			  dialog("消费均额请输入整数");
			  return false;
		  }
	  }
	  if(leijixfs!=""){
		  if((/(^[0-9]\d*$)/.test(leijixfs))!=true){
			  dialog("累计消费请输入整数");
			  return false;
		  }
	  }
	  if(leijixfe!=""){
		  if((/(^[0-9]\d*$)/.test(leijixfe))!=true){
			  dialog("累计消费请输入整数");
			  return false;
		  }
	  }
	  if(chuzhis!=""&&chuzhie!=""){
		  if((chuzhis-chuzhie)>=0){
			  dialog("储值余额请正确输入区间");
			  return false;
		  }
	  }
	  if(jifenyes!=""&&jifenyee!=""){
		  if((jifenyes-jifenyee)>=0){
			  dialog("积分余额请正确输入区间");
			  return false;
		  }
	  }
	  if(xiaofeis!=""&&xiaofeie!=""){
		  if((xiaofeis-xiaofeie)>=0){
			  dialog("消费均额请正确输入区间");
			  return false;
		  }
	  }
	  if(leijixfs!=""&&leijixfe!=""){
		  if((leijixfs-leijixfe)>=0){
			  dialog("累计消费请正确输入区间");
			  return false;
		  }
	  }
	  if(birthdays!=""&&birthdaye!=""){
		  var birstart = new Date(("2015-"+birthdays).replace(/-/g,"/"));
		  var birend = new Date(("2015-"+birthdaye).replace(/-/g,"/"));
		  if((birstart-birend)>=0){
			  dialog("开始时间大于结束时间");
			  return false;
		  }
	  }
	  if(registers!=""&&registere!=""){
		  var resstart = new Date(registers.replace(/-/g,"/"));
		  var resend= new Date(registere.replace(/-/g,"/"));
		  if((resstart-resend)>=0){
			  dialog("开始时间大于结束时间");
			  return false;
		  }
	  }
	  return true;
  }