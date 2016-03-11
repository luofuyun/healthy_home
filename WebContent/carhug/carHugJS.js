var outSizeHugId;
var outSizeUserId;
var carHug = {
	action:{
		myCarHugDetail:"/healthy_home/myCarHugDetail",
		cancelCarHug:"/healthy_home/cancelCarHug"
	},
	cancelCarHug:function(){
		$.ajax({
			url:carHug.action.cancelCarHug+"?userId="+outSizeUserId+"&hugId="+outSizeHugId,
			type : "post",
			async : false,
			success:function(obj){
				alert(obj);
				window.location.href = "myCarHug?userId="+outSizeUserId+"&message="+obj;
			}
		});
	}
};
$(function() {
	outSizeUserId = $("#userId").val();
	$("#myCarHugButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	var userNickName = $("#userNickName").val()
	$('#showHugModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var membersStr = button.data('membersstr');
		outSizeHugId = itemId;
		var modal = $(this);
		$.ajax({
			url : carHug.action.myCarHugDetail + "?hugId=" + itemId,
			type : "post",
			async : false,
			success : function(obj) {
				var togetherList = eval(obj);
				var togetherListStr = '';
				for(var i in togetherList){
					togetherListStr = togetherListStr + i;
				}
				modal.find('.dMembersStr').remove();
				modal.find('.carHugMembers').append("<div class=\"dMembersStr\">"+membersStr+"</div>");
				modal.find('.makeCarHugUser').text(userNickName);
			}
		});
	});
});