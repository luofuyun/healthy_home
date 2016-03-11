var outSizeHugId;
var outSizeUserId;
var carHugList = {
		action:{
			carHugListDetail:"/healthy_home/carHugListDetail",
			joinCarHug:"/healthy_home/joinCarHug",
			leaveHugMess:"/healthy_home/submitMessage"
		},
		joinCarHug:function(){
			$.ajax({
				url:carHugList.action.joinCarHug+"?userId="+outSizeUserId+"&hugId="+outSizeHugId,
				type : "post",
				async : false,
				success:function(obj){
					alert(obj);
					window.location.href = "myCarHug?userId="+outSizeUserId+"&message="+obj;
				}
			});
		},
		leaveHugMess:function(){
			$.ajax({
				url:carHugList.action.leaveHugMess,
				type : "post",
				async : false,
				data:$("#leaveHugMessForm").serialize(),
				success:function(obj){
					alert(obj);
					window.location.href = "carHugList";
				}
			});
		}
};
$(function() {
	outSizeUserId = $("#userId").val();
	$("#carHugListButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	
	$('#showHugModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var makeCarHugUserName = button.data('makecarhugusername');
		var membersStr = button.data('membersstr');
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
				modal.find('.dMembersStr').remove();
				modal.find('.carHugMembers').append("<div class=\"dMembersStr\">"+membersStr+"</div>");
			}
		});
	});
	
	$('#leaveHugMessModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var userId = button.data('userid');
		var modal = $(this);
		modal.find('.recieveUserId').val(userId);
	});
});