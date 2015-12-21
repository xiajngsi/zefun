<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
            String picPath = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增门店</title>
</head>
<body>
    <form action="<%=basePath %>storeinfo/action/addstore" method="post">
        姓名：<input type="text" name="name" /><br/>
        账号：<input type="text" name="phone" /><br/>
        店名：<input type="text" name="storeName" /><br/>
        类型：<select name="storeType">
                <option value="1">单店</option>
                <option value="2">连锁总店</option>
                <option value="3">连锁分店</option>
            </select><br/>
        总店：<input type="text" name="parentId" value="" /><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>