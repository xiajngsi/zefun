<%@page import="com.zefun.web.entity.MemberMenu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String topBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
%>
<div class="headerpanel">
    <div class="headerlist">
        ${session_key_system_head_menu }
        <div class="user-info">
            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/${session_key_user_info.headImage }?imageView2/1/w/80/h/80" alt=""/>
            <div class="info-desc">
                <div class="name">HI,<span>${session_key_user_info.name }</span></div>
                <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="/page.html">${session_key_user_info.storeName } <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="<%=topBasePath %>user/logout">退出</a></li>
                    <em></em>
                    <span></span>
                </ul>
            </div>
        </div>
    </div>
</div><!--headerpanel-->
<a href="" class="showmenu"></a>
<script type="text/javascript">
jQuery(document).ready(function(){
	initMenu();
});

function initMenu(){
	var url = window.location.href;
	
	if (url.endWith("#")) {
		url = url.substring(0, url.indexOf("#"));
	}
	
	if (url.indexOf("?") > 0) {
		url = url.substring(0, url.indexOf("?"));
	}
	
	jQuery('.left-active-img').addClass('hide');//隐藏其他菜单
	
	var curLi = jQuery("a[href='" + url + "']").parent();//父元素li
	curLi.addClass('active').siblings('li').removeClass('active');
	
	var curImg = curLi.prev();//找到当前选中元素对应的选中转态图片
	curImg.removeClass('hide').siblings('img').addClass('hide');//隐藏其它选中转态的图片
	var curUl = curImg.parent();//找到左边菜单对应的ul
	curUl.removeClass('hide').siblings('ul').addClass('hide');//显示当前地址的菜单
	
	jQuery('.headerlist li').removeClass('active');
	jQuery("li[role='" + curUl.attr('role') + "']").addClass('active');
	 if(url.indexOf("wechat/send/update/item")>0){
		    jQuery("ul[role='membersMenu']").removeClass('hide').siblings('ul').addClass('hide');;
		    jQuery("li[name='user-defined-menu']").addClass('active').siblings('li').removeClass('active');
		    jQuery("li[name='user-defined-menu']").prev().removeClass('hide').siblings('img').addClass('hide');
		    jQuery("li[role='membersMenu']").addClass('active');
		  }
}

</script>
<%@include file="chat.jsp" %>
