var personal = {
		action:{
			answerAdvice:"/healthy_home/answerAdvice",
			changePersonalMessage:"/healthy_home/changePersonalMessage"
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
		},
		change:function(){
			$.ajax({
				url:personal.action.changePersonalMessage,
				type : "post",
				async : false,
				data:$("#changePersonalMessageForm").serialize(),
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
	
	$('#showHugModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var makeCarHugUserName = button.data('makecarhugusername');
		var nickName = $("#nickName").val();
		outSizeHugId = itemId;
		var modal = $(this);
		modal.find('.makeCarHugUser').text(makeCarHugUserName);
		$.ajax({
			url : carHugList.action.carHugListDetail + "?hugId=" + itemId,
			type : "post",
			async : false,
			success : function(obj) {
				var togetherList = eval(obj);
				var togetherListStr = '';
				for(var i in togetherList){
					togetherListStr = togetherListStr + togetherList[i].nickName+'  ';
				}
				modal.find('.carHugMembers').text(togetherListStr);
			}
		});
	});
});