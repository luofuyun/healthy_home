var main = {
	action : {
		loadDataAction : "/hugtogether/loadDataAction",
		sMessageAction : "/hugtogether/sMessageAction",
		tMessageAction : "/hugtogether/tMessageAction",
		oMessageAction : "/hugtogether/oMessageAction",
		loadHugAction : "/hugtogether/loadHugAction",
		loadStudentAction : "/hugtogether/loadStudentAction"
	},
	setDivCanSee : function() {
		$("#hideDiv").attr("style", "position: fixed;");
	},
	setDivHide : function() {
		$("#hideDiv").attr("style", "display:none;position: fixed;");
	},
	personal:function(userId){
		$.ajax({
			url:main.action.personalAction+"?userId="+userId,
			type:"post",
			async:false
		});
	},
	loadData : function() {
		$.ajax({
			url : main.action.loadDataAction,
			type : "post",
			async : false,
			data : $("#pageForm").serialize()
		});
		window.location.href = 'hug.jsp';
	},
	sMessage : function() {
		$.ajax({
			url : main.action.sMessageAction,
			type : "post",
			async : false,
			data : $("#pageForm").serialize()
		})
		window.location.href = 'mainwsm.jsp';
	},
	tMessage : function() {
		$.ajax({
			url : main.action.tMessageAction,
			type : "post",
			async : false,
			data : $("#pageForm").serialize()
		})
		window.location.href = 'mainwtm.jsp';
	},
	oMessage : function() {
		$.ajax({
			url : main.action.oMessageAction,
			type : "post",
			async : false,
			data : $("#pageForm").serialize()
		})
		window.location.href = 'mainwom.jsp';
	},
	cancelSelected:function(){
		$(".list-group-item").attr("class","list-group-item");
	},
	userClick:function(){
		this.cancelSelected();
		$("#userManButton").attr("class","list-group-item active");
		window.location.href = "loadDataAction?nowPage=1&pageSize=5";
	},
	roleClick:function(){
		this.cancelSelected();
		$("#roleManButton").attr("class","list-group-item active");
		window.location.href = "loadRoleDataAction?nowPage=1&pageSize=5";
	},
	menuClick:function(){
		this.cancelSelected();
		$("#menuManButton").attr("class","list-group-item active");
		window.location.href = "loadMenuDataAction?nowPage=1&pageSize=5";
	},
	healthKnowledgeClick:function(){
		this.cancelSelected();
		$("#healthKnowledgeButton").attr("class","list-group-item active");
		window.location.href = "healthKnowledge";
	},
	healthInfoClick:function(){
		this.cancelSelected();
		$("#healthInfoButton").attr("class","list-group-item active");
		window.location.href = "healthyAsk";
	},
	healthActivityClick:function(){
		this.cancelSelected();
		$("#healthActivityButton").attr("class","list-group-item active");
		window.location.href = "healthActivity";
	},
	dayCheckClick:function(){
		this.cancelSelected();
		$("#dayCheckButton").attr("class","list-group-item active");
		window.location.href = "dayCheck";
	},
	seeDoctorClick:function(){
		this.cancelSelected();
		$("#seeDoctorButton").attr("class","list-group-item active");
		window.location.href = "seeDoctor";
	},
	societyCarHugClick:function(menuId){
		this.cancelSelected();
		$("#societyCarHugButton").attr("class","list-group-item active");
		window.location.href = "societyCarHug?menuId="+menuId;
	},
	myCarHugClick:function(){
		this.cancelSelected();
		$("#myCarHugButton").attr("class","list-group-item active");
		var userId = $("#userId").val();
		window.location.href = "myCarHug?userId="+userId;
	},
	carHugListClick:function(){
		this.cancelSelected();
		$("#carHugListButton").attr("class","list-group-item active");
		window.location.href = "carHugList";
	},
	makeCarHugClick:function(){
		this.cancelSelected();
		$("#makeCarHugButton").attr("class","list-group-item active");
		window.location.href = "makeCarHug";
	},
	adviceClick:function(){
		this.cancelSelected();
		$("#adviceButton").attr("class","list-group-item active");
		window.location.href = "advice";
	},
	rejectClick:function(){
		this.cancelSelected();
		$("#rejectButton").attr("class","list-group-item active");
		window.location.href = "reject";
	},
	leaveMessageClick:function(){
		this.cancelSelected();
		$("#leaveMessageButton").attr("class","list-group-item active");
		window.location.href = "leaveMessage";
	},
	adviceClick:function(){
		this.cancelSelected();
		$("#adviceButton").attr("class","list-group-item active");
		window.location.href = "advice";
	},
	rejectClick:function(){
		this.cancelSelected();
		$("#rejectButton").attr("class","list-group-item active");
		window.location.href = "reject";
	},
	leaveMessageClick:function(){
		this.cancelSelected();
		$("#leaveMessageButton").attr("class","list-group-item active");
		window.location.href = "myLeaveMessage";
	},
	personalMessage:function(){
		this.cancelSelected();
		$("#personalMessage").attr("class","list-group-item active");
		window.location.href = "personalMessage";
	},
	outLogin:function(){
		this.cancelSelected();
		$("#outLogin").attr("class","list-group-item active");
		window.location.href = "login.jsp";
	},
	goPersonal:function(){
		window.location.href = "personalAction";
	}
}
$(function() {
	$('#myModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget); // Button that triggered the
												// modal
		var itemid = button.data('itemid'); // Extract info from data-*
											// attributes
		var modal = $(this);
		$.ajax({
			url : main.action.loadHugAction + "?itemid=" + itemid,
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
				$("#hug-id").val(hug.id);
			}
		})
	})
	$('#wsmModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget); // Button that triggered the
												// modal
		var itemid = button.data('itemid'); // Extract info from data-*
											// attributes
		var modal = $(this);
		$.ajax({
			url : main.action.loadStudentAction + "?itemid=" + itemid,
			type : "post",
			async : false,
			success : function(obj) {
				var student = eval(obj);
				modal.find('.realname').text(student.realName);
				modal.find('.studynumber').text(student.studyNumber);
				modal.find('.university').text(student.university);
				modal.find('.grade').text(student.grade);
				modal.find('.sex').text(student.sex);
				modal.find('.nickname').text(student.nickName);
				modal.find('.city').text(student.city);
				modal.find('.age').text(student.age);
				modal.find('.classname').text(student.className);
				$("#student-huged-id").val(student.id);
			}
		})
	})
	$('#personalModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget); // Button that triggered the
												// modal
		var itemid = button.data('itemid'); // Extract info from data-*
											// attributes
		var modal = $(this);
		$.ajax({
			url : main.action.loadHugAction + "?itemid=" + itemid,
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
				$("#hug-id").val(hug.id);
			}
		})
	})
})
