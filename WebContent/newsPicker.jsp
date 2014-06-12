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

	<div id="header"><img src="images/PatagTimes.png"/>
	<br/>
	<span id="subhead">Your consolidated source of latest news.</span>
	<span id="date">Today is </span>
	<hr/>
	</div>
	<div id="left">
		<div id="form">
			<form action="FetchRssServlet" method="get" id="newsForm">
				<select name="topic">
					<option value="Business">Business</option>
					<option value="Technology">Technology</option>
					<option value="World">World News</option>
				</select> 
				<select name="source">
					<option value="NYT">New York Times</option>
					<option value="BBC">BBC</option>
					<option value="SMH">Sydney Morning Herald</option>
				</select> 
			</form>
			<button id="getNews">Get News</button>
		</div>
		<div id="links">
			
		</div>
	</div>
	<div id="news">
		<iframe src="" id="newsFrame"></iframe>
	</div>
</body>
</html>