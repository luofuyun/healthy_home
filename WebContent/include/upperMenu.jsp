<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<li class="dropdown">
  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">健康生活 <span class="caret"></span></a>
  <ul class="dropdown-menu">
    <li><a href="#" onClick="main.healthInfoClick()">答复咨询</a></li>
    <li><a href="#" onClick="main.healthActivityClick()">健康活动</a></li>
  </ul>
</li>
<li class="dropdown">
  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">社区平台 <span class="caret"></span></a>
  <ul class="dropdown-menu">
    <li><a href="#" onClick="main.adviceClick()">答复建议</a></li>
    <li><a href="#" onClick="main.rejectClick()">处理投诉</a></li>
  </ul>
</li>
<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">系统管理 <span class="caret"></span></a>
   <ul class="dropdown-menu">
     <li><a href="#" onClick="main.userClick()">用户信息</a></li>
     <li><a href="#" onClick="main.outLogin()">退出登录</a></li>
     <!-- <li><a href="#" onClick="main.menuClick()">菜单信息</a></li> -->
   </ul>
</li>