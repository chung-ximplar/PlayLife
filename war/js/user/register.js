$(document).ready(function(e){
	$('#form_register').ajaxForm({
		dataType : 'json',
		beforeSerialize: function($form, options) { 
			$('#tb_password').val(MD5($('#tb_password').val()));
		},
		success : function(data){
			if (data.status == 'ok'){
				$('#div_register_success').addClass('div_center');
				$('#form_register').flipContent($('#div_register_success'));
				setTimeout('window.location = "/"', 3000);
			} else {
				var div_error = $('#div_error').removeClass('hide').empty();;
				var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
				var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
				$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
				$('<span />').attr({id : 'span_error'}).html(data.error.displayMessage).appendTo(div);
				$('#tb_password, #tb_confirmPassword').val('');
			}
		},
		error : function(data){
			var div_error = $('#div_error').removeClass('hide').empty();;
			var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
			var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
			$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
			$('<span />').attr({id : 'span_error'}).html(SERVER_ERROR).appendTo(div);
			$('#tb_password, #tb_confirmPassword').val('');
		}
	});
});
