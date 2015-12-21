/*二级菜单添加位置*/
var subMenuClass = {};
jQuery(function() {
	/*点击添加一级菜单*/
	jQuery("#menu-add").on("click",createFirstMenuModal);
	/*为新增的二级菜单添加jquery.delegate事件*/
	jQuery(".wrap-menu").delegate("span[class='icon-plus fr submenu-add']","click",function (event){
		createSubMenuModal(this);
	});
	 /*当确定输入一级菜单的名字后*/
	jQuery("#confirm-first-menu").on("click", function(){
	     jQuery('#add-menu-name-modal-first-menu').modal('hide');
	     var firstMenuName = jQuery("#first-menu-name").val();
	     if(firstMenuName==""){alert("名称不能为空");return;}
	     /*在这里进行后台访问,添加菜单,再将数据取出来,放在页面上使用*/
	     jQuery.ajax({
	 		 url: baseUrl+"add/menu",
	         data: "menuName="+firstMenuName+"&menuRefId=0",
	         type:"POST",
	         success: function(data) {
	             if(data.code == 0){
	            	 jQuery(".wrap-menu").append("<li class=\"menu\" id=\""+data.msg.menuId+"\">" +
	            	 		"<span class=\"tree-dropdown\"><span  class=\"resetMenu\">"+firstMenuName+"</span>" +
	            	 		"<span class=\"icon-plus fr submenu-add\"></span></span></li>");
	             }else{
	            	 dialog('添加失败', '错误信息');
	             }
	         }
	 	});
	     jQuery("#first-menu-name").val("");
	 });

	/*确定二级菜单的名字后的操作*/
	jQuery("#confirm-sub-menu").on("click",
		function() {
			jQuery('#addSubMenu').modal('hide');
			var subMenuName = jQuery("#sub-menu-name").val();
			var menuRefId = subMenuClass.parent().attr("id");
			jQuery.ajax({
	     		 url: baseUrl+"add/menu",
	             data: "menuName="+subMenuName+"&menuRefId="+menuRefId,
	             type:"POST",
	             success: function(data) {
	                 if(data.code == 0){
	                	 jQuery(subMenuClass).append("<li id=\""+data.msg.menuId+"\">" + subMenuName + "</li>");
	                 }else{
	                	 dialog('添加失败', '错误信息');
	                 }
	             }
	     	});
			jQuery("#sub-menu-name").val("");
	});

	jQuery(".reset-content").on("click", function() {
		jConfirm('重设会导致当前菜单内容被清空确定重设？', 'Dialog', function(r) {
			/*dialog('Confirmed: ' + r, 'Confirmation Results');*/
		});
	});
	/*文字消息显示*/
	jQuery("#word").on("click", function() {
		jQuery(".imgarea").hide();
		jQuery(".imgwordarea").hide();
		jQuery(".textarea").show();
	
	});
	/*图片消息显示*/
	jQuery("#img").on("click", function() {
		jQuery(".textarea").hide();
		jQuery(".imgwordarea").hide();
		jQuery(".imgarea").show();
	});
	/*图文消息显示*/
	jQuery("#imgword").on("click", function() {
		jQuery(".textarea").hide();
		jQuery(".imgarea").hide();
		jQuery(".imgwordarea").show();
		});
});

/*为一级菜单设置delegate点击事件,配置一级菜单回复信息,一级删除*/
jQuery(function (){
	jQuery(".wrap-menu").delegate("span[class='resetMenu']","click",function (event){
		var docEvent = jQuery(this);
		var menuId = docEvent.parent().parent().attr("id");
		var menuName = docEvent.text();
		var changeMenuInfo = jQuery("#showMenuName");
		changeMenuInfo.text("一级菜单:"+menuName);
		changeMenuInfo.next(".fr.can-click").attr("id",menuId);
		/*为其删除按钮添加事件*/
		changeMenuInfo.next(".fr.can-click").click(function (){
			deleteFirstMenu(menuId);
		});
		/*配置'一级菜单回复信息'页面形式*/
		setFirMenuModal(docEvent,menuId,menuName);
	});
});
/*配置'一级菜单回复信息'页面形式*/
function setFirMenuModal(docEvent,menuId,menuName){
	/*现在全部隐藏掉*/
	hideMessageDoc();
	/*此处有一个ajax请求,查看是否已经设置过*/
	var messageDiage = jQuery(".init.p");
	var messageSet = jQuery(".inner.hide");
	messageDiage.hide();
	messageSet.children("div[class='desc p']").text("请配置  "+menuName+" 菜单的内容");
	/*如果一级菜单有了二级菜单,将配置一级菜单的功能删掉*/
	if(docEvent.parent().next("ul").children().length>0){
		jQuery("#goPage").parent().hide();
		jQuery("#send").parent().hide();
	}else{
		jQuery("#goPage").parent().show();
		jQuery("#send").parent().show();
	}
	jQuery("#initialCreate").parent().show();
	/*设置新增二级菜单功能*/
	jQuery("#initialCreate").parent().on("click",function (){
		createSubMenuModal(docEvent);
	});
	messageSet.show();
}
/*为二级菜单设置delegate点击事件,配置二级菜单回复信息,二级删除*/
jQuery(function (){
	jQuery(".wrap-menu").delegate("li","click",function (event){
		var docEvent = jQuery(this);
		if(docEvent.children().length>0)return;
		var menuId = docEvent.attr("id");
		var menuName = docEvent.text();
		var changeMenuInfo = jQuery("#showMenuName");
		changeMenuInfo.text("二级菜单:"+menuName);
		changeMenuInfo.next(".fr.can-click").attr("id",menuId);
		/*为其删除按钮添加事件*/
		changeMenuInfo.next(".fr.can-click").click(function (){
			deleteSubMenu(menuId,docEvent);
		});
		/*设置'配置二级菜单消息的页面'形式*/
		setSubMenuModal(menuId,menuName);
	});
});
/*设置'配置二级菜单消息的页面'形式*/
function setSubMenuModal(menuId,menuName){
	/*现在全部隐藏掉*/
	hideMessageDoc();
	/*此处有一个ajax请求,查看是否已经设置过*/
	var messageDiage = jQuery(".init.p");
	var messageSet = jQuery(".inner.hide");
	messageDiage.hide();
	messageSet.children("div[class='desc p']").text("请配置  "+menuName+" 菜单的内容");
	jQuery("#goPage").parent().show();
	jQuery("#send").parent().show();
	jQuery("#initialCreate").parent().hide();
	messageSet.show();
}
/*设置菜单回复信息*/
jQuery(function (){
	jQuery("#send").parent().on("click",function(){
		var changeMenuInfo = jQuery("#showMenuName");
		var menuId = changeMenuInfo.next(".fr.can-click").attr("id");
		sendMessages(menuId);
	});
	jQuery("#goPage").parent().on("click",function(){
		var changeMenuInfo = jQuery("#showMenuName");
		var menuId = changeMenuInfo.next(".fr.can-click").attr("id");
		sendUrlMessages(menuId);
	});
});
/*发送图文消息*/
function sendMessages(menuId){
	jQuery(".inner.hide").hide();
	jQuery(".send-message.hide").show();
	jQuery(".added-reply.hide").show();
	jQuery(".imgwordarea.hide").show();
}
/*设置跳转链接*/
function sendUrlMessages(menuId){
	jQuery.ajax({
		url: baseUrl+"get/one/menu",
        data: "menuId="+menuId,
        type:"POST",
        success: function(data) {
        	jQuery("#menuUrl").val(data.menuUrl);
        }
	});
	hideMessageDoc();
	jQuery(".go-url.hide").show();
	jQuery("#confirm-menu-url").on("click",function(){
		var radio = jQuery("input[name='title']").filter(":checked").eq(0);
		var radioParent = radio.parents("td");
		var url = radioParent.children("input[name='menuUrl']").val();
		console.log(url);
		jQuery.ajax({
    		url: baseUrl+"set/menu/url",
            data: "menuId="+menuId+"&menuUrl="+escape(url),
            type:"POST",
            success: function(data) {
                if(data.code == 0){
                	jQuery("#menuUrl").val(url);
                }else{
               	 dialog('添加失败', '错误信息');
                }
            }
		
    	});
	});
	
}
/*创建一级菜单*/
function createFirstMenuModal(){
	var th = jQuery(this);
	var thChildNum = jQuery(".wrap-menu").children("li").length;
	var subNum = 3 - thChildNum;
	if (thChildNum < 3) {
		jQuery('#add-menu-name-modal-first-menu').modal('show');
		jQuery('#add-menu-name-modal-first-menu').on('shown.bs.modal',
		function(e) {
			jQuery(".sub-num-first-menu").text(subNum);
		});
	} else {
		dialog('最多只能添加3个一级菜单，当前已达设置上限', '错误信息');
	}
}
/*创建二级菜单*/
function createSubMenuModal(docEventDoc){
	var th = jQuery(docEventDoc);
	/*第一次新增了一级菜单,加一个二级菜单的包裹项*/
	if(th.parent().next(".submenu").attr("class")==undefined){
		th.parent().parent().append("<ul class=\"submenu\"></ul>");
	}
	var nodeParent = th.parent().next(".submenu");
	/*定位插入元素的位置*/
	subMenuClass = nodeParent;
	var modalTitleNum = '二';
	var thChildNum = th.parent().siblings(".submenu").children("li").length;
	var subNum = 5 - thChildNum;
	if (thChildNum < 5) {
		jQuery('#addSubMenu').modal('show');
		jQuery('#addSubMenu').on('shown.bs.modal',
		function(e) {
			jQuery(".subMenuAcount").text(subNum);
		});
	}else{
		dialog('最多只能添加3个一级菜单，当前已达设置上限', '错误信息');
	}
}
/*删除一级菜单*/
function deleteFirstMenu(menuId){
	jQuery.ajax({
		url: baseUrl+"delete/menu",
        data: "menuId="+menuId,
        type:"POST",
        success: function(data) {
            if(data.code == 0){
            	var wrapMenu = jQuery(".wrap-menu").children("#"+menuId);
            	wrapMenu.remove();
            }
        }
	});
}
/*删除二级菜单*/
function deleteSubMenu(menuId,docEvent){
	jQuery.ajax({
		url: baseUrl+"delete/menu",
        data: "menuId="+menuId,
        type:"POST",
        success: function(data) {
            if(data.code == 0){
            	docEvent.remove();
            }
        }
	});	
}
/*模态框居中*/
jQuery(function() {
	/*会员资料模态框左右居中*/
jQuery('#select-wordimg-modal').on(
		'show.bs.modal',
		function(e) {
			var left = (jQuery(window).width() - jQuery(
					"#select-wordimg-modal").width()) / 2 + 300;
			jQuery("#select-wordimg-modal").css("left", left);
			});
});
/*发送图文消息的时候隐藏点过的元素*/
function hideMessageDoc(){
	jQuery(".inner.hide").hide();
	jQuery(".send-message.hide").hide();
	jQuery(".added-reply.hide").hide();
	jQuery(".imgwordarea.hide").hide();
	jQuery(".go-url.hide").hide();
}
