﻿<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="<%=basePath%>css/mobile/mui.picker.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/mui.poppicker.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css"/>

    <script src="<%=basePath%>js/mobile/city.data.js"></script>
    <script src="<%=basePath%>js/mobile/jquery.min.js"></script>
    <script src="<%=basePath%>js/mobile/base.js"></script>
    <script src="<%=basePath%>js/mobile/mui.min.js"></script>
    <script src="<%=basePath%>js/mobile/mui.picker.js"></script>
    <script src="<%=basePath%>js/mobile/mui.poppicker.js"></script>
    <title>申请成为渠道商</title>
</head>
<body>
<div class="head">
    <img src="<%=basePath%>images/mobile/logo-head.png" alt="" class="head-img"/>
</div>
<img src="<%=basePath%>images/mobile/shenqing.png" alt="" class="animation-person"/>

<div class="content">
    <div class="title">
        <div class="blue-div"></div>
        <span class="name">申请成为渠道商</span>
    </div>

    <div class="form-group mt80">
        <ul class="register-ul">
            <li class="mb20">
                <label for="" class="left-label register-label">姓名</label>
                <div class="name register-input">
                    <input type="text" name="name" class="normal-input"/>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">手机号</label>
                <div class="name register-input">
                    <input type="text" name="phone" class="normal-input"/>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label"></label>
                <div class="name register-input">
                    <div class="yzm-wrap">
                        <div class="yzm-input">
                          <input type="text" class="normal-input" name="verifyCode" placeholder="输入验证码"/>
                        </div>
                        <div class="normal-btn yzm-btn mt-btnh" onclick="send_vc2(this);">获取验证码</div>
                    </div>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">公司/商号</label>
                <div class="name register-input">
                    <input type="text" name="company" class="normal-input"/>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">业务类型</label>
                <div class="name register-input" id="mendian">
                    <div class="s-select" >
                        <input type="text" class="normal-input" placeholder="请选择业务类型" readonly/>
                        <input type="hidden" name="channelType">
                        <img src="<%=basePath%>images/mobile/select-trangle.png" alt="" class="select-trangle"/>
                    </div>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">所在省</label>
                <div class="name register-input" id="sheng">
                    <div class="s-select">
                        <input type="text" name="province" class="normal-input" placeholder="请选择所在省" readonly />
                        <img src="<%=basePath%>images/mobile/select-trangle.png" alt="" class="select-trangle"/>
                    </div>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">所在市</label>
                <div class="name register-input" id="shi">
                    <div class="s-select">
                        <input type="text" name="city" class="normal-input" placeholder="请选择所在市" readonly/>
                        <img src="<%=basePath%>images/mobile/select-trangle.png" alt="" class="select-trangle"/>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

<div class="submit-wrap">
    <div class="submit" id="sub_apply">提交申请</div>
</div>

<script>
    (function($, doc) {
        $.init();
        $.ready(function() {
            //普通示例
            var userPicker = new $.PopPicker();
            userPicker.setData([{
                value: '1',
                text: '连锁机构'
            }, {
                value: '2',
                text: '发品商'
            }, {
                value: '3',
                text: '培训机构'
            }, {
                value: '0',
                text: '其他'
            }]);
            var showUserPickerButton = doc.getElementById('mendian');
            showUserPickerButton.addEventListener('tap', function(event) {
                userPicker.show(function(items) {
                  jQuery(jQuery('#mendian input').get(0)).val(items[0].text);
                    jQuery(jQuery('#mendian input').get(1)).val(items[0].value);
                });
            }, false);

            var cityPicker = new $.PopPicker({layer: 2});
            cityPicker.setData(cityData);
            var showCityPickerButton = doc.getElementById('sheng');
            showCityPickerButton.addEventListener('tap', function(event) {
              cityPicker.show(function(items) {
                jQuery('#sheng input').val(items[0].text);
                jQuery('#shi input').val(items[1].text);
              });
            });

            var showCityPickerButton2 = doc.getElementById('shi');
            showCityPickerButton2.addEventListener('tap', function(event) {
              cityPicker.show(function(items) {
                jQuery('#sheng input').val(items[0].text);
                jQuery('#shi input').val(items[1].text);
              });
            });
        });
    })(mui, document);

    function send_vc2(obj) {
        var phone = $('input[name="phone"]').val();
          if(!phone || !$.trim(phone)) {
            show_dialog("手机号不能为空");
            return;
          }
          phone = $.trim(phone);
          if(!validate_phone(phone)) {
              show_dialog("请输入正确的手机号");
              return;
          }
          send_vc(obj, phone, "注册渠道!");
      };


    $(function(){
      $('#sub_apply').click(function(){
            var data = {};
              var name = $('input[name="name"]').val();
              if(!name || !$.trim(name)) {
                show_dialog("姓名不能为空");
                return;
              }
              name = $.trim(name);
              data['name'] = name;

              var phone = $('input[name="phone"]').val();
              if(!phone || !$.trim(phone)) {
                show_dialog("手机号不能为空");
                return;
              }
              phone = $.trim(phone);
              if(!validate_phone(phone)) {
                  show_dialog("请输入正确的手机号");
                  return;
              }
              data['phone'] = phone;

              var verifyCode = $('input[name="verifyCode"]').val();
              if(!verifyCode || !$.trim(verifyCode)) {
                show_dialog("验证码不能为空");
                return;
              }
              verifyCode = $.trim(verifyCode);
              data['verifyCode'] = verifyCode;

              var company = $('input[name="company"]').val();
              if(!company || !$.trim(company)) {
                show_dialog("公司/商号不能为空");
                return;
              }
              company = $.trim(company);
              data['company'] = company;

              var agentType = $($('#mendian input').get(1)).val();
              if(!agentType || !$.trim(agentType)) {
                  show_dialog("请选择业务类型");
                  return;
                }
              data['agentType'] = agentType;

              var province = $('input[name="province"]').val();
              if(!province || !$.trim(province)) {
                  show_dialog("请选择所在省份和城市");
                  return;
              }
              province = $.trim(province);
              data['province'] = province;

              var city = $('input[name="city"]').val();
              if(!city || !$.trim(city)) {
                  show_dialog("请选择所在省份和城市");
                  return;
              }
              city = $.trim(city);
              data['city'] = city;
              data['code'] = '${code}';
              $.post("<%=basePath%>agentapply/action/agentApply", data, function(resp){
                  resp = eval(resp);
                  var code = resp.code;
                  if(code != 0) {
                    show_dialog(resp.msg);
                  } else {
                    show_dialog("申请成功");
                    window.location.href = "<%=basePath%>agentdetail/view/index";
                  }
              });
            });
    });
</script>
</body>
</html>
