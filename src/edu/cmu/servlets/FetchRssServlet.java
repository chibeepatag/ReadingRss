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
	
	private static final String BUSINESS_URL = "http://rss.nytimes.com/services/xml/rss/nyt/Business.xml";
	private static final String TECHNOLOGY_URL = "http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml";
	private static final String WORLD_URL = "http://rss.nytimes.com/services/xml/rss/nyt/World.xml";
		
	private static final String XSL_LOCATION = "/XSLTransformerCode.xsl";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchRssServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String topic = request.getParameter("topic");
		PrintWriter out = response.getWriter();
		Source xmlDoc;
		switch (topic) {
		case BUSINESS:
			xmlDoc = new StreamSource(BUSINESS_URL);			
			break;
			
		case TECHNOLOGY:
			xmlDoc = new StreamSource(TECHNOLOGY_URL);
			break;
			
		case WORLD:
			xmlDoc = new StreamSource(WORLD_URL);
			break;
			
		default:
			throw new ServletException("Invalid Topic");
		}
		
		applyStyleSheet(xmlDoc, out);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void applyStyleSheet(Source xmlDoc, PrintWriter out){
		Result result = new StreamResult(out);
		ServletContext context = getServletContext();
		InputStream xsl = (InputStream)(context.getResourceAsStream(XSL_LOCATION));
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
	
	

}
