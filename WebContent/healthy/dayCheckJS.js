var dayCheck = {
	action:{
		todayCheck:"/healthy_home/todayCheck"
	},
	submitTodayCheck:function(){
		$.ajax({
			url:dayCheck.action.todayCheck,
			type:"post",
			async:false,
			data:$("#todayCheckForm").serialize(),
			success:function(obj){
				alert("您的健康状况为： "+obj);
				window.location.href = "dayCheck";
			}
		});
	}
};
$(function() {
	$("#dayCheckButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	$('#healthyActivityModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var theme = button.data('theme');
		var content = button.data('content');
		var time = button.data('time');
		var addr = button.data('addr');
		var membersStr = button.data('membersstr');
		var modal = $(this);
		modal.find('.activityTheme').text(theme);
		modal.find('.activityContent').text(content);
		modal.find('.activityTime').text(time);
		modal.find('.activityAddr').text(addr);
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
});