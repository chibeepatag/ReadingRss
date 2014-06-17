/**This initializes the page by 
 * 1. displaying the current date and time
 * 2. Adding an event handler to the getNews button.*/
$(document).ready(function(){
	$("#date").text(new Date());
	$("#getNews").click(getLinks);
});

/**This function sends the get request using Ajax where
 * it passes on the chosen topic and source as request parameters*/
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

/**This is the callback function that receives the response of the
 * servlet. It places the news links in the container links.
 * It also disables the default of links so that when
 * the link is clicked the page is not redirected to that page.
 * Instead the news should be placed on the iFrame to the right.*/
function updateNews(news){	
	$("#links").html(news).find("a").click(function(e){
		e.preventDefault();
		getNews(this);
	});
}

/**This function replaces the default function of clicking
 * a link. It displays the link on the iFrame instead of
 * redirecting the page.*/
function getNews(link){
	var url = $(link).attr("href");
	$("#news").find("iframe").attr("src", url);
}
