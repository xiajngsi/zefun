jQuery(document).ready(function(){
  /*loading*/
  jQuery('.loading').fadeOut(800);
  /*tooltip*/
  jQuery('[data-toggle="tooltip"]').tooltip();

  /*模态框中点击选择图片跳到选择图片的页面*/
  jQuery(".select-img-btn").on("click", function(){
    jQuery(".add-project-main").hide();
    jQuery(".return").show();
    jQuery(".alternative-img").fadeIn();

  });

  /*在选择图片页面点击返回按钮返回到前一页*/
  jQuery(".return").on("click", function(){
    jQuery(".alternative-img").hide();
    jQuery(".return").hide();
    jQuery(".add-project-main").fadeIn();

  });

  // Select
  jQuery(".chzn-select").chosen({disable_search_threshold: 10, no_results_text: "",width:"95%"});
  jQuery(".chzn-select2").chosen({disable_search_threshold: 12, no_results_text: ""});

  // Select with Search
  jQuery(".chzn-select-search").chosen({no_results_text: "没找到匹配项"});

  //select with search input
  jQuery(".chzn-select-search-input").chosen({no_results_text: "没找到匹配项"},{max_selected_options: 5});

  /*tabs*/
  jQuery('#tabs').tabs();

  var getCurrentDate = function(format){
    var d = new Date();
    return d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate();
  }

  /*选择时间*/
  jQuery(".datetimepicker").each(function(){
    var timepicker = jQuery(this).attr("timepicker") ? jQuery(this).attr("timepicker") : false;
    var format = jQuery(this).attr("format") ? jQuery(this).attr("format") : "Y/m/d";
    var step = jQuery(this).attr("step") ? jQuery(this).attr("step") : 30;
    var value = '';
    if (jQuery(this).attr("daysOffset")) {
      var d = new Date();
      var days = parseInt(jQuery(this).attr("daysOffset"));
      days = days + d.getDate();
      d.setDate(days);
      var date = d.getDate();
      if (date < 10) {
    	  date = "0" + date;
      }
      value = d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + date;
    }
    console.log("value : " + value);
    jQuery(this).datetimepicker({
      value:value,
      lang:'ch',
      timepicker:timepicker,
      format:format,
      step:step
    });
  });

  /*radio的样式替换*/
  //.beau-select这个加在input上
  //.beau-check-comp加在一个组合上
  jQuery(".beau-select").on("click", function(){
    var th = jQuery(this);
    var parent = th.parents(".beau-check-comp");
    parent.find(".check-radio").removeClass("check-after");
    if(th.is(":checked")){
      th.siblings(".check-radio").addClass("check-after");
    }else{
      th.siblings(".check-radio").removeClass("check-after");
    }
  });

  /* *//*radio的样式替换*//*
   jQuery("input[type='checkbox']").on("click", function(){
   var th = jQuery(this);
   var tr = th.parents("tr");
   *//*tr.find(".check-radio").removeClass("check-after");*//*
   if(th.is(":checked")){
   th.siblings(".check-radio").addClass("check-after");
   }else{
   th.siblings(".check-radio").removeClass("check-after");
   }
   });*/

  /*最新的tab*/
  jQuery(function(){
    jQuery(".n-sub-tab").on("click", function(){
      jQuery(".n-sub-tab").removeClass("active");
      jQuery(this).addClass("active");
      var targetTab = jQuery(this).data("target");
      if(targetTab == "#tab2"){
        jQuery(".tab-word").css("border","0px");
      }else{
        jQuery(".tab-word").css("border","");
      }
      jQuery(".target-tab").addClass("hide");
      jQuery(targetTab).removeClass("hide");
    })
  })
//时间按钮清除数据
  jQuery(".timePickerClean").focus(function(){
  	  var timer = jQuery(this);
  	  window.document.onkeydown = function(evt){
  		  evt = (evt) ? evt : window.event
  				  if (evt.keyCode) {
  				     if(evt.keyCode == 8){
  				    	 jQuery(timer).datetimepicker('hide');
  				    	 jQuery(timer).blur(function(){
  				    		 jQuery(timer).val("");
  				    	 });
  				     }
  				  }
  	  }
  });
  
  /*最新的漂亮的单选和多选*/
  jQuery(document).delegate(".yellow-checker", "click", function () {
    var radio = jQuery(".yellow-checker");
    for(var i=0;i<radio.length;i++)
    {
      if(radio[i].checked)
      {
        jQuery(radio[i]).siblings(".beau-checker").addClass("active");
      }else{
        jQuery(radio[i]).siblings(".beau-checker").removeClass("active");
      }
    }
  });

});

/*模态框居中*/
//jQuery('.modal').on('show.bs.modal', function (e) {
//  var left = ( jQuery(this).width())/2;
//  jQuery(this).css("margin-left", -left);
//});
/*判断模态框高度*/
jQuery(document).ready(function () {
  var WH=jQuery(window).height()
  var modal=jQuery(".modal").height()
  if(WH>modal){
    jQuery(".modal").css({top:50+"%"})
    jQuery('.modal').on('show.bs.modal', function (e) {
    	 var left = ( jQuery(this).width())/2;
    	  jQuery(this).css("margin-left", -left);
      var height = ( jQuery(this).height())/2;
      jQuery(this).css("margin-top", -height);
    });
  }
    else{
      jQuery(".modal").css({top:0})
    }
})

Date.parseFunctions={count:0};Date.parseRegexes=[];Date.formatFunctions={count:0};Date.prototype.dateFormat=function(format) {if(Date.formatFunctions[format]==null) {Date.createNewFormat(format)}var func=Date.formatFunctions[format];return this[func]()};Date.createNewFormat=function(format) {var funcName="format"+Date.formatFunctions.count++;Date.formatFunctions[format]=funcName;var code="Date.prototype."+funcName+" = function() {return ";var special=false;var ch='';for(var i=0;i<format.length;++i) {ch=format.charAt(i);if(!special&&ch=="\\") {special=true}else if(special) {special=false;code+="'"+String.escape(ch)+"' + "}else{code+=Date.getFormatCode(ch)}}eval(code.substring(0,code.length-3)+";}")};Date.getFormatCode=function(character) {switch(character) {case"d":return"String.leftPad(this.getDate(), 2, '0') + ";case"D":return"Date.dayNames[this.getDay()].substring(0, 3) + ";case"j":return"this.getDate() + ";case"l":return"Date.dayNames[this.getDay()] + ";case"S":return"this.getSuffix() + ";case"w":return"this.getDay() + ";case"z":return"this.getDayOfYear() + ";case"W":return"this.getWeekOfYear() + ";case"F":return"Date.monthNames[this.getMonth()] + ";case"m":return"String.leftPad(this.getMonth() + 1, 2, '0') + ";case"M":return"Date.monthNames[this.getMonth()].substring(0, 3) + ";case"n":return"(this.getMonth() + 1) + ";case"t":return"this.getDaysInMonth() + ";case"L":return"(this.isLeapYear() ? 1 : 0) + ";case"Y":return"this.getFullYear() + ";case"y":return"('' + this.getFullYear()).substring(2, 4) + ";case"a":return"(this.getHours() < 12 ? 'am' : 'pm') + ";case"A":return"(this.getHours() < 12 ? 'AM' : 'PM') + ";case"g":return"((this.getHours() %12) ? this.getHours() % 12 : 12) + ";case"G":return"this.getHours() + ";case"h":return"String.leftPad((this.getHours() %12) ? this.getHours() % 12 : 12, 2, '0') + ";case"H":return"String.leftPad(this.getHours(), 2, '0') + ";case"i":return"String.leftPad(this.getMinutes(), 2, '0') + ";case"s":return"String.leftPad(this.getSeconds(), 2, '0') + ";case"O":return"this.getGMTOffset() + ";case"T":return"this.getTimezone() + ";case"Z":return"(this.getTimezoneOffset() * -60) + ";default:return"'"+String.escape(character)+"' + "}};Date.parseDate=function(input,format) {if(Date.parseFunctions[format]==null) {Date.createParser(format)}var func=Date.parseFunctions[format];return Date[func](input)};Date.createParser=function(format) {var funcName="parse"+Date.parseFunctions.count++;var regexNum=Date.parseRegexes.length;var currentGroup=1;Date.parseFunctions[format]=funcName;var code="Date."+funcName+" = function(input) {\n"+"var y = -1, m = -1, d = -1, h = -1, i = -1, s = -1;\n"+"var d = new Date();\n"+"y = d.getFullYear();\n"+"m = d.getMonth();\n"+"d = d.getDate();\n"+"var results = input.match(Date.parseRegexes["+regexNum+"]);\n"+"if (results && results.length > 0) {";var regex="";var special=false;var ch='';for(var i=0;i<format.length;++i) {ch=format.charAt(i);if(!special&&ch=="\\") {special=true}else if(special) {special=false;regex+=String.escape(ch)}else{obj=Date.formatCodeToRegex(ch,currentGroup);currentGroup+=obj.g;regex+=obj.s;if(obj.g&&obj.c) {code+=obj.c}}}code+="if (y > 0 && m >= 0 && d > 0 && h >= 0 && i >= 0 && s >= 0)\n"+"{return new Date(y, m, d, h, i, s);}\n"+"else if (y > 0 && m >= 0 && d > 0 && h >= 0 && i >= 0)\n"+"{return new Date(y, m, d, h, i);}\n"+"else if (y > 0 && m >= 0 && d > 0 && h >= 0)\n"+"{return new Date(y, m, d, h);}\n"+"else if (y > 0 && m >= 0 && d > 0)\n"+"{return new Date(y, m, d);}\n"+"else if (y > 0 && m >= 0)\n"+"{return new Date(y, m);}\n"+"else if (y > 0)\n"+"{return new Date(y);}\n"+"}return null;}";Date.parseRegexes[regexNum]=new RegExp("^"+regex+"$");eval(code)};Date.formatCodeToRegex=function(character,currentGroup) {switch(character) {case"D":return{g:0,c:null,s:"(?:Sun|Mon|Tue|Wed|Thu|Fri|Sat)"};case"j":case"d":return{g:1,c:"d = parseInt(results["+currentGroup+"], 10);\n",s:"(\\d{1,2})"};case"l":return{g:0,c:null,s:"(?:"+Date.dayNames.join("|")+")"};case"S":return{g:0,c:null,s:"(?:st|nd|rd|th)"};case"w":return{g:0,c:null,s:"\\d"};case"z":return{g:0,c:null,s:"(?:\\d{1,3})"};case"W":return{g:0,c:null,s:"(?:\\d{2})"};case"F":return{g:1,c:"m = parseInt(Date.monthNumbers[results["+currentGroup+"].substring(0, 3)], 10);\n",s:"("+Date.monthNames.join("|")+")"};case"M":return{g:1,c:"m = parseInt(Date.monthNumbers[results["+currentGroup+"]], 10);\n",s:"(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)"};case"n":case"m":return{g:1,c:"m = parseInt(results["+currentGroup+"], 10) - 1;\n",s:"(\\d{1,2})"};case"t":return{g:0,c:null,s:"\\d{1,2}"};case"L":return{g:0,c:null,s:"(?:1|0)"};case"Y":return{g:1,c:"y = parseInt(results["+currentGroup+"], 10);\n",s:"(\\d{4})"};case"y":return{g:1,c:"var ty = parseInt(results["+currentGroup+"], 10);\n"+"y = ty > Date.y2kYear ? 1900 + ty : 2000 + ty;\n",s:"(\\d{1,2})"};case"a":return{g:1,c:"if (results["+currentGroup+"] == 'am') {\n"+"if (h == 12) { h = 0; }\n"+"} else { if (h < 12) { h += 12; }}",s:"(am|pm)"};case"A":return{g:1,c:"if (results["+currentGroup+"] == 'AM') {\n"+"if (h == 12) { h = 0; }\n"+"} else { if (h < 12) { h += 12; }}",s:"(AM|PM)"};case"g":case"G":case"h":case"H":return{g:1,c:"h = parseInt(results["+currentGroup+"], 10);\n",s:"(\\d{1,2})"};case"i":return{g:1,c:"i = parseInt(results["+currentGroup+"], 10);\n",s:"(\\d{2})"};case"s":return{g:1,c:"s = parseInt(results["+currentGroup+"], 10);\n",s:"(\\d{2})"};case"O":return{g:0,c:null,s:"[+-]\\d{4}"};case"T":return{g:0,c:null,s:"[A-Z]{3}"};case"Z":return{g:0,c:null,s:"[+-]\\d{1,5}"};default:return{g:0,c:null,s:String.escape(character)}}};Date.prototype.getTimezone=function() {return this.toString().replace(/^.*? ([A-Z]{3}) [0-9]{4}.*$/,"$1").replace(/^.*?\(([A-Z])[a-z]+ ([A-Z])[a-z]+ ([A-Z])[a-z]+\)$/,"$1$2$3")};Date.prototype.getGMTOffset=function() {return(this.getTimezoneOffset()>0?"-":"+")+String.leftPad(Math.floor(Math.abs(this.getTimezoneOffset())/60),2,"0")+String.leftPad(Math.abs(this.getTimezoneOffset())%60,2,"0")};Date.prototype.getDayOfYear=function() {var num=0;Date.daysInMonth[1]=this.isLeapYear()?29:28;for(var i=0;i<this.getMonth();++i) {num+=Date.daysInMonth[i]}return num+this.getDate()-1};Date.prototype.getWeekOfYear=function() {var now=this.getDayOfYear()+(4-this.getDay());var jan1=new Date(this.getFullYear(),0,1);var then=(7-jan1.getDay()+4);document.write(then);return String.leftPad(((now-then)/7)+1,2,"0")};Date.prototype.isLeapYear=function() {var year=this.getFullYear();return((year&3)==0&&(year%100||(year%400==0&&year)))};Date.prototype.getFirstDayOfMonth=function() {var day=(this.getDay()-(this.getDate()-1))%7;return(day<0)?(day+7):day};Date.prototype.getLastDayOfMonth=function() {var day=(this.getDay()+(Date.daysInMonth[this.getMonth()]-this.getDate()))%7;return(day<0)?(day+7):day};Date.prototype.getDaysInMonth=function() {Date.daysInMonth[1]=this.isLeapYear()?29:28;return Date.daysInMonth[this.getMonth()]};Date.prototype.getSuffix=function() {switch(this.getDate()) {case 1:case 21:case 31:return"st";case 2:case 22:return"nd";case 3:case 23:return"rd";default:return"th"}};String.escape=function(string) {return string.replace(/('|\\)/g,"\\$1")};String.leftPad=function(val,size,ch) {var result=new String(val);if(ch==null) {ch=" "}while(result.length<size) {result=ch+result}return result};Date.daysInMonth=[31,28,31,30,31,30,31,31,30,31,30,31];Date.monthNames=["January","February","March","April","May","June","July","August","September","October","November","December"];Date.dayNames=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];Date.y2kYear=50;Date.monthNumbers={Jan:0,Feb:1,Mar:2,Apr:3,May:4,Jun:5,Jul:6,Aug:7,Sep:8,Oct:9,Nov:10,Dec:11};Date.patterns={ISO8601LongPattern:"Y-m-d H:i:s",ISO8601ShortPattern:"Y-m-d",ShortDatePattern:"n/j/Y",LongDatePattern:"l, F d, Y",FullDateTimePattern:"l, F d, Y g:i:s A",MonthDayPattern:"F d",ShortTimePattern:"g:i A",LongTimePattern:"g:i:s A",SortableDateTimePattern:"Y-m-d\\TH:i:s",UniversalSortableDateTimePattern:"Y-m-d H:i:sO",YearMonthPattern:"F, Y"};

String.prototype.replaceAll = function(s1,s2){ 
  return this.replace(new RegExp(s1,"gm"),s2); 
}
String.prototype.startWith=function(str){
  var reg=new RegExp("^"+str);
  return reg.test(this);
};

String.prototype.endWith=function(str){
  var reg=new RegExp(str+"$");
  return reg.test(this);
};

Array.prototype.removeByIndex=function(dx) 
{ 
    if(isNaN(dx)||dx>this.length){return false;} 
    for(var i=0,n=0;i<this.length;i++) 
    { 
        if(this[i]!=this[dx]) 
        { 
            this[n++]=this[i] 
        } 
    } 
    this.length-=1 
}

Array.prototype.uniqueByKey = function(key)
{
	var n = {},r=[], v = ''; //n为hash表，r为临时数组
	for(var i = 0; i < this.length; i++) //遍历当前数组
	{
		if (!this[i]) {
			continue;
		}
		v = this[i][key];
		if (!n[v]) //如果hash表中没有当前项
		{
			n[v] = true; //存入hash表
			r.push(this[i]); //把当前数组的当前项push到临时数组里面
		}
	}
	return r;
}

function formDataToObj(data){
	if(data == null || data == undefined || data.length == 0){
		return {};
	}
	var array = data.split("&");
var obj = {};
for (var i = 0; i < array.length; i++) {
	var kv = array[i].split("=");
		obj[kv[0]] = kv[1];
	}
	return obj;
}

function isEmpty(str) {
	if (str == null || typeof(str) == "undefined" || str.toString().trim() == '') {
		return true;
	}
	return false;
}

function getCurDate(){
	var now = new Date();
	var year = now.getFullYear(); 
	var month = now.getMonth() + 1;
	var day = now.getDate();
	if (day < 10) {
		day = "0" + day;
	}
	return year + "/" + month + "/" + day;
}

function dataToFormByName(obj){
    var key,value,tagName,type,arr;
    for(x in obj){
        key = x;
        value = obj[x];
         
        jQuery("[name='"+key+"'],[name='"+key+"[]']").each(function(){
        tagName = jQuery(this)[0].tagName;
        type = jQuery(this).attr('type');
        if(tagName=='INPUT' || tagName=='TEXTAREA'){
            if(type=='radio' || type=='checkbox'){
            	jQuery(this).attr('checked',jQuery(this).val()==value);
            }else{
            	jQuery(this).val(value);
            }
        }else if(tagName=='SELECT'){
        	jQuery(this).val(value);
        	jQuery(this).trigger("liszt:updated");
            }
        });
    }
}

function resetForm(form){
	jQuery(form)[0].reset();
	jQuery(form).find("[type='hidden']").val('');
	jQuery(form).find("[type='text']").val('');
	jQuery(form).find("[type='checkbox']").removeAttr("checked");
	return false;
}

//阻止事件冒泡的通用函数
function stopBubble(e){
    // 如果传入了事件对象，那么就是非ie浏览器
    if(e&&e.stopPropagation){
        //因此它支持W3C的stopPropagation()方法
        e.stopPropagation();
    }else{
        //否则我们使用ie的方法来取消事件冒泡
        window.event.cancelBubble = true;
    }
}

