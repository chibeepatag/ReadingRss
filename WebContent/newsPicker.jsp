<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reading Rss</title>
</head>
<body>
	<form action="FetchRssServlet" method="get">
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
		<input type="submit" value="Get News"/>
	</form>
</body>
</html>