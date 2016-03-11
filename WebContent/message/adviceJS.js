var outSizeHugId;
var outSizeUserId;
var advice = {
	action:{
		answerAdvice:"/healthy_home/answerAdvice"
	},
	answerAdvice:function(){
		$.ajax({
			url:advice.action.answerAdvice,
			type : "post",
			async : false,
			data:$("#adminAdviceForm").serialize(),
			success:function(obj){
				alert(obj);
				window.location.href = "advice";
			}
		});
	}
};
$(function() {
	outSizeUserId = $("#userId").val();
	$("#adviceButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	$('#adminAdviceModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var modalContent = button.data('content');
		var modal = $(this);
		modal.find('.modalContent').text(modalContent);
		modal.find('.itemId').val(itemId);
	});
});