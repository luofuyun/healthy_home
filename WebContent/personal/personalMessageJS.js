var personalMessage = {
	action:{
		answerRecieveMyMessage:"/healthy_home/ansRecMessage"
	},
	answerRecieveMyMessage:function(){
		$.ajax({
			url:personalMessage.action.answerRecieveMyMessage,
			type : "post",
			async : false,
			data:$("#answerRecieveForm").serialize(),
			success:function(obj){
				alert(obj);
				window.location.href = "personalMessage";
			}
		});
	}
};
$(function() {
	
	$("#personalMessageButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	
	$('#ansRecMessageModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var modal = $(this);
		modal.find('.itemId').val(itemId);
	});
});