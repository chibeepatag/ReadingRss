package edu.cmu.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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
 * Servlet implementation class FetchRssServlet
 */
public class FetchRssServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String BUSINESS = "Business";
	private static final String TECHNOLOGY = "Technology";
	private static final String WORLD = "World";

	private static final String NYT_BUSINESS_URL = "http://rss.nytimes.com/services/xml/rss/nyt/Business.xml";
	private static final String NYT_TECHNOLOGY_URL = "http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml";
	private static final String NYT_WORLD_URL = "http://rss.nytimes.com/services/xml/rss/nyt/World.xml";

	private static final String BBC_BUSINESS_URL = "http://feeds.bbci.co.uk/news/business/rss.xml";
	private static final String BBC_TECHNOLOGY_URL = "http://feeds.bbci.co.uk/news/technology/rss.xml";
	private static final String BBC_WORLD_URL = "http://feeds.bbci.co.uk/news/world/rss.xml";

	private static final String SMH_BUSINESS_URL = "http://www.smh.com.au/rssheadlines/business.xml";
	private static final String SMH_TECHNOLOGY_URL = "http://feeds.smh.com.au/rssheadlines/technology.xml";
	private static final String SMH_WORLD_URL = "http://feeds.smh.com.au/rssheadlines/world.xml";

	private static final String BBC = "BBC";
	private static final String NYT = "NYT";
	private static final String SMH = "SMH";

	private static final String XSL_LOCATION = "/XSLTransformerCode.xsl";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchRssServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
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

	private String getUrl(String topic, String source) throws Exception {
		if (topic.equals(BUSINESS)) {
			if (source.equals(BBC)) {
				return BBC_BUSINESS_URL;
			} else if (source.equals(NYT)) {
				return NYT_BUSINESS_URL;
			} else if (source.equals(SMH)) {
				return SMH_BUSINESS_URL;
			}
		} else if (topic.equals(TECHNOLOGY)) {
			if (source.equals(BBC)) {
				return BBC_TECHNOLOGY_URL;
			} else if (source.equals(NYT)) {
				return NYT_TECHNOLOGY_URL;
			} else if (source.equals(SMH)) {
				return SMH_TECHNOLOGY_URL;
			}
		} else if (topic.equals(WORLD)) {
			if (source.equals(BBC)) {
				return BBC_WORLD_URL;
			} else if (source.equals(NYT)) {
				return NYT_WORLD_URL;
			} else if (source.equals(SMH)) {
				return SMH_WORLD_URL;
			}
		}
		throw new Exception("Invalid topic or source");
	}

}
