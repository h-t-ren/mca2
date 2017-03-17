jQuery.extend(jQuery.validator.messages, {
    required:"此项必填。",
    remote:"改名称已经存在，请更改其他名称",
    email:"请输入一个合法的电子邮件地址。",
    url:"请输入合法网址。",
    date:"请输入合法日期。",
    dateISO:"请输入合法日期（ISO格式）。",
    number:"请输入合法数字。",
    digits:"请只输入数字。",
    creditcard:"请输入一个有效的信用卡号。",
    equalTo:"请再次输入相同的值。",
    maxlength:jQuery.validator.format("请输入不超过{0}个字符。"),
    minlength:jQuery.validator.format("请输入至少{0}个字符。"),
    rangelength:jQuery.validator.format("请输入介于{0}和{1}个长的字符值。"),
    range:jQuery.validator.format("请输入介于{0}和{1}的值。"),
    max:jQuery.validator.format("请输入一个小于或等于{0}的值。"),
    min:jQuery.validator.format("请输入一个大于或等于{0}的值。")
});


var CompanyForm = function () {
    return {
        init: function () {
   
            var wizform = $('#companyForm');
			/*-----------------------------------------------------------------------------------*/
			/*	Validate the form elements
			/*-----------------------------------------------------------------------------------*/
            wizform.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
					companyName: {
                        required: true,
                        minlength: 4,
                        maxlength: 64,
                        remote:{       
                            type:"GET",
                            url: $clientURL+"verifyCompanyName",
                            data:{
                              id:function(){return $("#companyId").val();}
                            } 
                           } 
                        
                    },
					address: {
                        required: true
                    },
                    'usersByCreator.mobile':{
                    	required: true
                    }
                },

                invalidHandler: function (event, validator) { 
                   // alert_success.hide();
                  //  alert_error.show();
                },

                highlight: function (element) { 
                    $(element)
                        .closest('.form-group').removeClass('has-success').addClass('has-error'); 
                },

                unhighlight: function (element) { 
                    $(element)
                        .closest('.form-group').removeClass('has-error'); 
                },

                success: function (label) {
                    if (label.attr("for") == "gender") { 
                        label.closest('.form-group').removeClass('has-error').addClass('has-success');
                        label.remove(); 
                    } else { 
                        label.addClass('valid') 
                        .closest('.form-group').removeClass('has-error').addClass('has-success'); 
                    }
                }
            });

           
        }
    };
}();