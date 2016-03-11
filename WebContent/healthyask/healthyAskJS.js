var itemId;
var healthyAsk = {
	action:{
		answer:"/healthy_home/answer",
		submitAsk:"/healthy_home/submitAsk"
	},
	answer:function(){
		$.ajax({
			url:healthyAsk.action.answer,
			type : "post",
			async : false,
			data:$("#answerForm").serialize(),
			success:function(obj){
				alert(obj);
				window.location.href = "healthyAsk";
			}
		});
	},
	submitAsk:function(){
		$.ajax({
			url:healthyAsk.action.submitAsk,
			type : "post",
			async : false,
			data:$("#askForm").serialize(),
			success:function(obj){
				alert(obj);
				window.location.href = "healthyAsk";
			}
		});
	}
};
$(function() {
	$("#healthInfoButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	$('#healthyQuestionModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var title = button.data('title');
		var question = button.data('question');
		itemId = button.data('itemid');
		var modal = $(this);
		modal.find('.questionTitle').text(title);
		modal.find('.questionQuestion').text(question);
		modal.find('.itemId').val(itemId);
	});
	$('#healthyAnswerModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var title = button.data('title');
		var question = button.data('question');
		var answer = button.data('answer');
		var modal = $(this);
		modal.find('.answerTitle').text(title);
		modal.find('.answerQuestion').text(question);
		modal.find('.answerAnswer').text(answer);
		modal.find('.itemId').text(itemId);
	});
});