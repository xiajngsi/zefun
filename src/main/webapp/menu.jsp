<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.zefun.web.entity.MemberMenu"%>
<%
String menuBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
String menuPicPath = "7xkv8r.com1.z0.glb.clouddn.com";
%>
<!-- LEFT PANEL 开始-->
<div class="leftpanel">
    <div class="logo-wrap">
        <img src="<%=menuBasePath %>images/logo.png" alt=""/>
    </div>
    <div class="leftmenu">
    ${session_key_system_left_submenu }
    </div><!--leftmenu-->
</div><!--mainleft-->
<!--LEFT PANEL结束 -->