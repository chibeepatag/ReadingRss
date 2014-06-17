package edu.cmu.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * This servlet fetches the links to news articles given the selected source and
 * topic. Servlet implementation class FetchRssServlet
 * 
 * @author ChibeePatag
 */
public class FetchRssServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * XSL_LOCATION is the location in the classpath of the xsl code to
	 * transform the rss xml of news links.
	 */
	private static final String XSL_LOCATION = "/XSLTransformerCode.xsl";

	/**
	 * This map contains the urls of the news feeds. The key is the source of
	 * news (BBC, SMH or NYT); The value is another map where the key is the
	 * topic (Business, Technology or World) and the value is the url of the RSS
	 * depending on the two keys previously mentioned.
	 * 
	 */
	private static final Map<String, Map<String, String>> urlMap = new HashMap<String, Map<String, String>>();

	static {
		Map<String, String> bbcUrlMap = new HashMap<String, String>();
		bbcUrlMap.put("Business",
				"http://feeds.bbci.co.uk/news/business/rss.xml");
		bbcUrlMap.put("Technology",
				"http://feeds.bbci.co.uk/news/technology/rss.xml");
		bbcUrlMap.put("World", "http://feeds.bbci.co.uk/news/world/rss.xml");
		urlMap.put("BBC", bbcUrlMap);

		Map<String, String> smhUrlMap = new HashMap<String, String>();
		smhUrlMap.put("Business",
				"http://www.smh.com.au/rssheadlines/business.xml");
		smhUrlMap.put("Technology",
				"http://feeds.smh.com.au/rssheadlines/technology.xml");
		smhUrlMap
				.put("World", "http://feeds.smh.com.au/rssheadlines/world.xml");
		urlMap.put("SMH", smhUrlMap);

		Map<String, String> nytUrlMap = new HashMap<String, String>();
		nytUrlMap.put("Business",
				"http://rss.nytimes.com/services/xml/rss/nyt/Business.xml");
		nytUrlMap.put("Technology",
				"http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml");
		nytUrlMap.put("World",
				"http://rss.nytimes.com/services/xml/rss/nyt/World.xml");
		urlMap.put("NYT", nytUrlMap);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchRssServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * This is the method that receives get http requests. It retrieves two
	 * parameters, topic and source which is used to retrieve the corresponding
	 * URL from the urlMap.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("topic");
		String source = request.getParameter("source");
		PrintWriter out = response.getWriter();
		try {
			String url = getUrl(topic, source);
			Source xmlDoc = new StreamSource(url);
			applyStyleSheet(xmlDoc, out);
		} catch (Exception e) {
			out.write(e.getMessage());
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private void applyStyleSheet(Source xmlDoc, PrintWriter out) {
		Result result = new StreamResult(out);
		ServletContext context = getServletContext();
		InputStream xsl = (InputStream) (context
				.getResourceAsStream(XSL_LOCATION));
		Source xslDoc = new StreamSource(xsl);
		TransformerFactory factory = TransformerFactory.newInstance();

		try {
			Transformer trans = factory.newTransformer(xslDoc);
			trans.transform(xmlDoc, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method retrieves the url from the urlMap
	 * given the topic and source.
	 * @param topic - The news topic (Business, Technology or World)
	 * @param source - The source of news (NYT, BBC or SMH)
	 * @return the url of the rss
	 * @throws Exception when the topic or source is not supported.
	 */
	private String getUrl(String topic, String source) throws Exception {
		String url = urlMap.get(source).get(topic);
		if (null == url) {
			throw new Exception("Invalid topic or source");
		}
		return url;
	}

}
