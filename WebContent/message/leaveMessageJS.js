var outSizeHugId;
var outSizeUserId;
var leaveMessage = {
		action:{
			deleteMyMessage:"/healthy_home/deleteMyMessage",
			changeMyMessage:"/healthy_home/changeMyMessage"
		},
		deleteMyMessage:function(messageId){
			//alert(messageId);
			$.ajax({
				url:leaveMessage.action.deleteMyMessage+"?messageId="+messageId,
				type : "post",
				async : false,
				success:function(obj){
					alert(obj);
					window.location.href = "myLeaveMessage";
				}
			})
		},
		changeMyMessage:function(){
			$.ajax({
				url:leaveMessage.action.changeMyMessage,
				type : "post",
				async : false,
				data:$("#changeMyMessageForm").serialize(),
				success:function(obj){
					alert(obj);
					window.location.href = "myLeaveMessage";
				}
			})
		}
};
$(function() {
	outSizeUserId = $("#userId").val();
	$("#leaveMessageButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	
	$('#showMessageModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var makeCarHugUserName = button.data('makecarhugusername');
		var content = button.data('content');
		var nickName = $("#nickName").val();
		outSizeHugId = itemId;
		var modal = $(this);
		modal.find('.content').text(content);
	});
});