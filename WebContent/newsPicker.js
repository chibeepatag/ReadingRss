$(document).ready(function(){
	$("#getNews").click(getLinks);
});

function getLinks(){
	var topic = $("select[name=topic]").val();
	var source = $("select[name=source]").val();
	$.ajax({
		url: "FetchRssServlet",
		data: {"topic": topic, "source": source},
		type: "GET",
		success: updateNews
	});
}

function updateNews(news){	
	$("#links").html(news).find("a").click(function(e){
		e.preventDefault();
		getNews(this);
	});
}

function getNews(link){
	var url = $(link).attr("href");

	$.ajax({
		url: url,		
		type: "GET",
		success: showNews
	});
}

function showNews(news){
	$("#news").html(news);
}