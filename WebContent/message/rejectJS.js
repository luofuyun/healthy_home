var reject = {
	action:{
		answerReject:"/healthy_home/answerReject"
	},
	answerReject:function(){
		$.ajax({
			url:reject.action.answerReject,
			type : "post",
			async : false,
			data:$("#adminRejectForm").serialize(),
			success:function(obj){
				alert(obj);
				window.location.href = "reject";
			}
		});
	}
};
$(function() {
	outSizeUserId = $("#userId").val();
	$("#rejectButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	$('#adminRejectModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var modalContent = button.data('content');
		var modal = $(this);
		modal.find('.modalContent').text(modalContent);
		modal.find('.itemId').val(itemId);
	});
});