var healthKnowledge = {
	action:{
		loadHealth:"/healthy_home/loadHealth"
	}
};
$(function() {
	
	$("#healthKnowledgeButton").attr("class","list-group-item active");
	//弹出模态框事件主注册
	
	$('#healthModal').on('show.bs.modal', function(event) {
		
		/*var modal = $(this);
		modal.find('.title').text("桃子这么吃有助预防乳腺癌");
		modal.find('.description').text("桃子这么吃防乳腺癌 5类人不适合吃 美国得克萨斯农工大学农业生命研究中心食品科学家扎瓦洛斯博士完成的最新动物试验发现，桃子提取液可以抑制恶性乳腺癌细胞的进一步扩散");
		modal.find('.message').append("<p>俗话说，“<a target=\"_blank\" href=\"http://www.tngou.net/food/show/1128\">桃</a>养人，<a target=\"_blank\" href=\"http://www.tngou.net/food/show/1296\">杏</a>伤人”，桃子中富含糖、钙、磷、铁和<a target=\"_blank\" href=\"http://www.tngou.net/check/show/1530\">维生素</a>B、维生素C及大量的水分，其中铁的含量，更是居水果之冠。传统医学认为，桃有补益气血、养阴生津的作用。</p><p><strong>桃子这么吃有助预防乳腺癌</strong></p><p>研究指出，每天吃3个桃子，有助于预防乳腺癌。因为桃子中的酚类复合物具有抗击乳腺癌和防止癌扩散的功效，可以抑制癌细胞而不会殃及正常细胞。</p><p>美国一项新研究发现，每天吃3个桃子，有助于预防乳腺癌。美国得克萨斯农工大学<a target=\"_blank\" href=\"http://www.tngou.net\">农业</a>生命研究中心食品科学家扎瓦洛斯博士完成的最新动物试验发现，桃子提取液可以抑制恶性乳腺癌细胞的进一步扩散。经过测算，试验中使用的桃子提取液剂量相当于每天吃2~3个桃子。</p><p>扎瓦洛斯博士表示，桃子中的酚类复合物具有抗击乳腺癌和防止癌扩散的功效，可以抑制癌细胞而不会殃及正常细胞。新研究的最大亮点是，这类物质可以防止癌细胞的转移。新研究表明，在日常饮食中，每天吃两三个桃子有助于预防和应对致命乳腺癌。</p><p>美国营养学家伊恩·马尔伯表示，吃水果能比喝果汁获得更多的纤维素等营养物质，<a target=\"_blank\" href=\"http://www.tngou.net/disease/show/6816\">保健</a>功效更全面。</p><p><strong>桃子并非人人皆宜</strong></p><p><a target=\"_blank\" href=\"http://www.tngou.net/symptom/show/295\">糖尿</a>病患者：桃子的含糖量高，每百克桃含糖7克，糖尿病患者如果不加节制过量进食，就会引起<a target=\"_blank\" href=\"http://www.tngou.net/check/show/1593\">血糖</a>和<a target=\"_blank\" href=\"http://www.tngou.net/check/show/2465\">尿糖</a>迅速上升，加重病情。</p><p>胃肠功能弱者：桃子中含有大量的大分子物质，吃桃会增加肠胃负担，造成<a target=\"_blank\" href=\"http://www.tngou.net/symptom/show/21\">腹痛</a>、<a target=\"_blank\" href=\"http://www.tngou.net/disease/show/116\">腹泻</a>，所以不宜食用。</p><p>平时内热偏盛易生疮<a target=\"_blank\" href=\"http://www.tngou.net/disease/show/6963\">疖</a>者：桃味甘而性温，过食则生热。对于已经上火的人来说，多吃桃子无异于“火上浇油”。</p><p><br/></p><br/>");
		modal.find('.keywords').text("桃子 乳腺癌 癌细胞 有助于 每天 ");*/
		var button = $(event.relatedTarget);
		var itemId = button.data('itemid');
		var modal = $(this);
		$.ajax({
			url : healthKnowledge.action.loadHealth + "?itemId=" + itemId,
			type : "post",
			async : false,
			success : function(obj) {
				var health = eval(obj);
				modal.find('.title').text(health.title);
				modal.find('.description').text(health.descripe);
				modal.find('.dMessage').remove();
				modal.find('.message').append("<div class=\"dMessage\">"+health.message+"</div>");
				modal.find('.keywords').text(health.keywords);
			}
		});
	});
});