jQuery(function(){
   
	//进货时间
    jQuery('#recodrsTime').datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y/m/d'
    });
	
});


/**保存进货记录*/
function savePurchaseRecords(){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"goodsPurchaseRecord/savePurchaseRecords",
        data: jQuery("#purchaseRecodesForm").serialize(),
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	window.location.href = baseUrl+"goodsPurchaseRecord/view/purchaseRecords";
            }else{
            	dialog("error");
            }
        }
    });
}


/** *************************分页js代码******************************* */
