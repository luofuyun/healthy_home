var message={
		action:{
			submitMessage:"/healthy_home/submitMessage"
		},
		submitMessage:function(){
			$.ajax({
				url:message.action.submitMessage,
				type : "post",
				async : false,
				data:$("#messageForm").serialize(),
				success:function(obj){
					alert(obj);
					window.location.href = "myLeaveMessage";
				}
			});
		}
}