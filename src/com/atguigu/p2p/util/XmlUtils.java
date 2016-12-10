/**
 * agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.atguigu.p2p.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Common utilities for easily parsing XML without duplicating logic.
 * 
 * @author Scott Battaglia
 * @version $Revision: 11729 $ $Date: 2007-09-26 14:22:30 -0400 (Tue, 26 Sep 2007) $
 * @since 3.0
 */
public final class XmlUtils {

	private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

	@SuppressWarnings("unchecked")
	public static Map<String, String> xml2Map(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(xml);
			Element rootElement = document.getRootElement();
			for (Iterator<Element> it = rootElement.elementIterator(); it.hasNext();) {
				Element element = (Element) it.next();
				map.put(element.getName(), element.getText());
			}
		} catch (Exception e) {
			logger.error("xml2Map error:", e);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> xml2MapList(String xml, String xPath) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		try {
			Document document = DocumentHelper.parseText(xml);
			List<Element> selectNodes = document.selectNodes(xPath);
			for (Element e : selectNodes) {
				Map<String, String> map = new HashMap<String, String>();
				for (Iterator<Element> it = e.elementIterator(); it.hasNext();) {
					Element element = (Element) it.next();
					map.put(element.getName(), element.getText());
				}
				mapList.add(map);
			}
		} catch (Exception e) {
			logger.error("xml2MapList error:", e);
		}
		return mapList;
	}

	/**
	 * Get an instance of an XML reader from the XMLReaderFactory.
	 * 
	 * @return the XMLReader.
	 */
	public static XMLReader getXmlReader() {
		try {
			return XMLReaderFactory.createXMLReader();
		} catch (final SAXException e) {
			throw new RuntimeException("Unable to create XMLReader", e);
		}
	}

	/**
	 * Retrieve the text for a group of elements. Each text element is an entry in a list.
	 * <p>
	 * This method is currently optimized for the use case of two elements in a list.
	 * 
	 * @param xmlAsString the xml response
	 * @param element the element to look for
	 * @return the list of text from the elements.
	 */
	public static List<String> getTextForElements(final String xmlAsString, final String element) {
		final List<String> elements = new ArrayList<String>(2);
		final XMLReader reader = getXmlReader();

		final DefaultHandler handler = new DefaultHandler() {

			private boolean foundElement = false;

			private StringBuilder buffer = new StringBuilder();

			public void startElement(final String uri, final String localName, final String qName,
					final Attributes attributes) throws SAXException {
				if (localName.equals(element)) {
					this.foundElement = true;
				}
			}

			public void endElement(final String uri, final String localName, final String qName) throws SAXException {
				if (localName.equals(element)) {
					this.foundElement = false;
					elements.add(this.buffer.toString());
					this.buffer = new StringBuilder();
				}
			}

			public void characters(char[] ch, int start, int length) throws SAXException {
				if (this.foundElement) {
					this.buffer.append(ch, start, length);
				}
			}
		};

		reader.setContentHandler(handler);
		reader.setErrorHandler(handler);

		try {
			reader.parse(new InputSource(new StringReader(xmlAsString)));
		} catch (final Exception e) {
			logger.error("error", e);
			return null;
		}

		return elements;
	}

	/**
	 * Retrieve the text for a specific element (when we know there is only one).
	 * 
	 * @param xmlAsString the xml response
	 * @param element the element to look for
	 * @return the text value of the element.
	 */
	public static String getTextForElement(final String xmlAsString, final String element) {
		final XMLReader reader = getXmlReader();
		final StringBuffer buffer = new StringBuffer();

		final DefaultHandler handler = new DefaultHandler() {

			private boolean foundElement = false;

			public void startElement(final String uri, final String localName, final String qName,
					final Attributes attributes) throws SAXException {
				if (localName.equals(element)) {
					this.foundElement = true;
				}
			}

			public void endElement(final String uri, final String localName, final String qName) throws SAXException {
				if (localName.equals(element)) {
					this.foundElement = false;
				}
			}

			public void characters(char[] ch, int start, int length) throws SAXException {
				if (this.foundElement) {
					buffer.append(ch, start, length);
				}
			}
		};

		reader.setContentHandler(handler);
		reader.setErrorHandler(handler);

		try {
			reader.parse(new InputSource(new StringReader(xmlAsString)));
		} catch (final Exception e) {
			logger.error("error", e);
			return null;
		}

		return buffer.toString();
	}
}
