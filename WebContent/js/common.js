$(function(){
	// 모든 input 자동완성 off
	$('input').each(function(){
		$(this).prop('autocomplete', 'off');
	});
	
	// input 타입1
	$('div.input-container > input').focus(function(){
		$(this).parent().addClass('focus on');
	}).focusout(function(){
		if($(this).val() == ""){
			$(this).parent().removeClass('focus');
		} else{
			$(this).parent().removeClass('on');
		}
	});

	// input radio
	$('label.radioLabel').click(function(){
		$(this).prev().prop('checked', true);
	});
});