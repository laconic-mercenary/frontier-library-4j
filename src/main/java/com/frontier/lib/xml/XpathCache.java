
package com.frontier.lib.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XpathCache {

	private static final String CHARSET = "UTF-8";

	private String xmlCache = null;
	private Document doc = null;

	/**
	 * Gets the string version of the xml specified in the setXmlCache() call
	 * 
	 * @return
	 */
	public String getXmlCache() {
		return xmlCache;
	}

	/**
	 * Sets the xml string and also creates the document which will be used in
	 * find() calls
	 * 
	 * @param xmlCache
	 *           The string version of the xml
	 * @throws Exception
	 *            If errors occur in attempting to build the xml document
	 */
	public void setXmlCache(String xmlCache) throws Exception {
		setXmlCache(xmlCache, false);
	}

	public void setXmlCache(String xmlCache, boolean ignoreDtd) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		if (ignoreDtd) {
			builder.setEntityResolver(new EntityResolver() {

				@Override
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
						IOException {
					return new InputSource(new StringReader(""));
				}
			});
		}

		this.xmlCache = xmlCache;
		InputStream stream = new ByteArrayInputStream(getXmlCache().getBytes(CHARSET));
		this.doc = builder.parse(stream);
	}

	/**
	 * Attempts to find a node in the xml document, given the xpath expression
	 * 
	 * @param xpathExpression
	 *           An xpath expression
	 * @return An XpathQueryResult object, check hasSucceeded()
	 * @throws XPathExpressionException
	 */
	public XpathQueryResult find(String xpathExpression) throws XPathExpressionException {
		if (this.doc == null)
			throw new IllegalStateException("Please call setXmlCache() before calling find()");

		String result = null;
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile(xpathExpression);

		result = (String) expr.evaluate(doc, XPathConstants.STRING);

		return new XpathQueryResult(xpathExpression, result, !result.trim().isEmpty());
	}
}
