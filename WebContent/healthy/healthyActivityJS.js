var itemId;
var healthyActivity = {
	action:{
		submitActivity:"/healthy_home/submitActivity",
		takePartIn:"/healthy_home/takePartIn",
		delActive:"/healthy_home/delActive"
	},
	submitActivity:function(){
		$.ajax({
			url:healthyActivity.action.submitActivity,
			type : "post",
			async : false,
			data:$("#publishForm").serialize(),
			success:function(obj){
				alert(obj);
				window.location.href = "healthActivity";
			}
		});
	},
	takePartIn:function(){
		$.ajax({
			url:healthyActivity.action.takePartIn,
			type : "post",
			async : false,
			data:$("#takePartInForm").serialize(),
			success:function(obj){
				alert(obj);
				window.location.href = "healthActivity";
			}
		});
	},
	delActive:function(){
		$.ajax({
			url:healthyActivity.action.delActive,
			type : "post",
			async : false,
			data:$("#confirmForm").serialize(),
			success:function(obj){
				alert(obj);
				window.location.href = "healthActivity";
			}
		});
	}
};
$(function() {
	$("#healthActivityButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	$('#healthyActivityModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var theme = button.data('theme');
		var content = button.data('content');
		var time = button.data('time');
		var addr = button.data('addr');
		var membersStr = button.data('membersstr');
		itemId = button.data('itemid');
		var modal = $(this);
		modal.find('.activityTheme').text(theme);
		modal.find('.activityContent').text(content);
		modal.find('.activityTime').text(time);
		modal.find('.activityAddr').text(addr);
		modal.find('.healthyActivityId').val(itemId);
		modal.find('.dMembersStr').remove();
		modal.find('.membersStr').append("<div class=\"dMembersStr\">"+membersStr+"</div>");
	});
	$('#healthyAsnwerModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var title = button.data('title');
		var question = button.data('question');
		var answer = button.data('answer');
		var modal = $(this);
		modal.find('.answerTtile').text(title);
		modal.find('.answerQuestion').text(question);
		modal.find('.answerAnswer').text(answer);
	});
	
	$('#confirmModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var modal = $(this);
		modal.find('.confirmId').val(itemId);
	});
});