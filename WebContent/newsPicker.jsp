<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="newsPicker.css">
<script language="JavaScript" type="text/javascript" src="jquery-2.1.1.min.js"></script>
<script type="text/javascript" language="javascript" src="newsPicker.js"></script>

<title>Reading Rss</title>
</head>
<body>
	<h1>News One Stop Shop</h1>
	<div id="left">
		<div id="form">
			<form action="FetchRssServlet" method="get" id="newsForm">
				<select name="topic">
					<option value="Business">Business</option>
					<option value="Technology">Technology</option>
					<option value="World">World News</option>
				</select> <select name="source">
					<option value="NYT">New York Times</option>
					<option value="BBC">BBC</option>
					<option value="SMH">Sydney Morning Herald</option>
				</select> 
			</form>
			<button id="getNews">Get News</button>
		</div>
		<div id="links">
			<ul>
				<li>dsd</li>
				<li>sdsds</li>
				<li>gffgf</li>
				<li>wesad</li>
				<li>dsfasd</li>
			</ul>
		</div>
	</div>
	<div id="news">
		Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.
	</div>
</body>
</html>