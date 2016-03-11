<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<li role="presentation" class="dropdown">
	<a class="dropdown-toggle" role="button" onClick="main.goPersonal()" onmouseover="main.setDivCanSee()" onmouseout="main.setDivHide()"  
		href="#" aria-expanded="false"> ${user.nickName} <span class="caret"></span>
	</a>
	<div class="row">
		<div class="panel panel-default col-md-3 col-sm-4" id="hideDiv" style="display: none; position: fixed;"
			onmouseover="main.setDivCanSee()" onmouseout="main.setDivHide()">
			<!-- Default panel contents -->
			<div class="row">
				<div class="panel-body col-md-6">
					<a href="#" class=""><img class="img-responsive" src="image/head.jpg" alt="用户头像"></a>
				</div>
				<div class="col-md-6" style="padding-top: 16px">
					<table class="table">
						<tr><td>姓名:</td><td>${user.realName}</td></tr>
						<tr><td>昵称:</td><td>${user.nickName}</td></tr>
						<tr><td>身份:</td><td>${role.roleName}</td></tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</li>