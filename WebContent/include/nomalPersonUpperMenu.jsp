<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<li class="dropdown">
   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">健康生活 <span class="caret"></span></a>
   <ul class="dropdown-menu">
     <li><a href="#" onClick="main.healthKnowledgeClick()">健康知识</a></li>
     <li><a href="#" onClick="main.healthInfoClick()">健康咨询</a></li>
     <li><a href="#" onClick="main.healthActivityClick()">健康活动</a></li>
     <li role="separator" class="divider"></li>
     <li><a href="#" onClick="main.dayCheckClick()">每日体检</a></li>
     <!-- <li role="separator" class="divider"></li>
     <li><a href="#" onClick="main.seeDoctorClick()">就医通道</a></li> -->
   </ul>
 </li>
 <li class="dropdown">
   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">社区平台 <span class="caret"></span></a>
   <ul class="dropdown-menu">
     <li><a href="#" onClick="main.adviceClick()">我有建议</a></li>
     <li><a href="#" onClick="main.rejectClick()">我要投诉</a></li>
     <li role="separator" class="divider"></li>
     <li><a href="#" onClick="main.leaveMessageClick()">我的留言</a></li>
   </ul>
 </li>
 <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">社区拼车 <span class="caret"></span></a>
    	<ul class="dropdown-menu">
     	<li><a href="#" onClick="main.myCarHugClick()">我的拼车</a></li>
      <li><a href="#" onClick="main.makeCarHugClick()">发起拼车</a></li>
      <li role="separator" class="divider"></li>
      <li><a href="#" onClick="main.carHugListClick()">拼车列表</a></li>
     </ul>
    </li>