var register={
		action:{
			
		},
		register:function(){
			
		}
}

/*$(function(){
	$('#showHugModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var modal = $(this);
		$.ajax({
			url : hug.action.loadHug + "?itemId=" + itemId,
			type : "post",
			async : false,
			success : function(obj) {
				var hug = eval(obj);
				modal.find('.hugname').text(hug.name);
				modal.find('.raiseuserid').text(hug.raiseUserId);
				modal.find('.weekday').text(hug.weekDay);
				modal.find('.timelong').text(hug.timeLong);
				modal.find('.type').text(hug.type);
				modal.find('.time').text(hug.time);
				modal.find('.place').text(hug.place);
				modal.find('.descrip').text(hug.descrip);
			}
		})
	})
})*/