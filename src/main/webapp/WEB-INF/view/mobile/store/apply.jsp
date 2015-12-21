<!doctype html>
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
    <title>申请开通智放账号</title>
</head>
<body>
<div class="head">
    <img src="<%=basePath%>images/mobile/logo-head.png" alt="" class="head-img"/>
</div>
<img src="<%=basePath%>images/mobile/kaitong.png" alt="" class="animation-person"/>

<div class="content">
    <div class="title">
        <div class="blue-div"></div>
        <span class="name">申请开通智放账号</span>
    </div>

    <div class="form-group mt80">
        <ul class="register-ul">
            <li class="mb20">
                <label for="" class="left-label register-label">姓名</label>
                <div class="name register-input">
                    <input type="text" name="name" class="normal-input"/>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">手机号</label>
                <div class="name register-input">
                    <input type="tel" name="phone" class="normal-input"/>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label"></label>
                <div class="name register-input">
                    <div class="yzm-wrap">
                        <div class="yzm-input">
                            <input type="tel" class="normal-input" name="verifyCode" placeholder="输入验证码"/>
                        </div>
                        <div class="normal-btn yzm-btn mt-btnh" onclick="send_vc2(this);">获取验证码</div>
                    </div>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">门店类型</label>
                <div class="name register-input showUserPicker" >
                    <div class="s-select"  id="mendian">
                        <input type="text" class="normal-input" placeholder="选择您的门店类型" readonly/>
                        <input type="hidden" name="storeType"/>
                        <img src="<%=basePath%>images/mobile/select-trangle.png" alt="" class="select-trangle" >
                    </div>
                </div>
            </li>
            <li class="mb20" style="display:none;" id="head_li">
                <label for="" class="left-label register-label">总店账号</label>
                <div class="name register-input">
                    <input type="text" name="hqUserName" class="normal-input"/>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">分店名称</label>
                <div class="name register-input">
                    <input type="text" name="storeName" class="normal-input"/>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">所在省</label>
                <div class="name register-input" id="sheng">
                    <div class="s-select">
                        <input type="text" class="normal-input" name="province" placeholder="请选择所在省" value="" readonly/>
                        <img src="<%=basePath%>images/mobile/select-trangle.png" alt="" class="select-trangle"/>
                    </div>
                </div>
            </li>
            <li class="mb20">
                <label for="" class="left-label register-label">所在市</label>
                <div class="name register-input" id="shi">
                    <div class="s-select">
                        <input type="text" class="normal-input" name="city" placeholder="请选择所在市" value="" readonly/>
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

<script src="<%=basePath%>js/mobile/city.data.js"></script>
<script src="<%=basePath%>js/mobile/jquery.min.js"></script>
<script src="<%=basePath%>js/mobile/base.js"></script>
<script src="<%=basePath%>js/mobile/mui.min.js"></script>
<script src="<%=basePath%>js/mobile/mui.picker.js"></script>
<script src="<%=basePath%>js/mobile/mui.poppicker.js"></script>
<script>
    (function($, doc) {
        $.init();
        $.ready(function() {
            var MDPicker = new $.PopPicker();
            MDPicker.setData([{
                value: '2',
                text: '连锁总店'
            }, {
                value: '3',
                text: '连锁分店'
            }, {
                value: '1',
                text: '单店'
            }]);
            var showMDPickerButton = doc.getElementById('mendian');
            showMDPickerButton.addEventListener('tap', function(event) {
              MDPicker.show(function(items) {
                  jQuery(jQuery('#mendian input').get(0)).val(items[0].text);
                  jQuery(jQuery('#mendian input').get(1)).val(items[0].value);
                  if(items[0].value === '3') {
                      jQuery('#head_li').show();
                  } else {
                    jQuery('#head_li').hide();
                  }
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
        send_vc(obj, phone, "注册门店!");
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

            var storeType = $($('#mendian input').get(1)).val();
            if(!storeType || !$.trim(storeType)) {
                show_dialog("请选择门店类型");
                return;
              }
            data['storeType'] = storeType;

            var hqUserName = "";
            if(storeType === '3') {
              hqUserName = $('input[name="hqUserName"]').val();
                if(!hqUserName || !$.trim(hqUserName)) {
                  show_dialog("总店账号不能为空");
                  return;
                }
            }
            hqUserName = $.trim(hqUserName);
            data['hqUserName'] = hqUserName;

            var storeName = $('input[name="storeName"]').val();
            if(!storeName || !$.trim(storeName)) {
                show_dialog("店名不能为空");
                return;
            }
            storeName = $.trim(storeName);
            data['storeName'] = storeName;

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
            $.post("<%=basePath%>storeapply/action/storeApply", data, function(resp){
                resp = eval(resp);
                var code = resp.code;
                if(code != 0) {
                  show_dialog(resp.msg);
                } else {
                  show_dialog("申请成功");
                  window.location.href = "<%=basePath%>storedetail/view/index";
                }
            });
          });
    });
</script>
</body>
</html>