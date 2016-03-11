var index = {
	action : {
		submitConfigAction : ctx + "/login"
	},
	validateForm : function() {
		var score = $("#userName").val();
		return score != null && score != "";
	},
	submit : function() {
		if (this.validateForm()) {
			$("#subBtn").attr("disabled", "disabled");
			$.ajax({
						url : this.action.submitConfigAction,
						type : "post",
						async : false,
						data : $("#from").serialize(),
						success : function(obj) {
							var result = eval(obj);
							if (result.code == 0) {

							} else {
								$("#subBtn").removeAttr("disabled", "disabled");
							}
							$.messager.alert("提示", result.desc);
						}
					});
		} else {
			$.messager.alert("警告", "用户名不能为空");
		}
	}
}