package it.polimi.ingsw.ps22.server.parser;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

/*
XML Structure
<faithpointtrack>
	<slot>
		<number></number>
		<victory></victory>
	</slot>
</faithpointtrack> 
 */

public class FaithPointSaxParser {
	
	public static void FaithRead(String pathname, HashMap<Integer,VictoryPoint> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				String lastQName = "";
				Integer number;
				VictoryPoint vic;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();
				}
				
				public void endElement(String uri, String localName, String qName) throws SAXException {
					lastQName = "";
					if (qName.equalsIgnoreCase("slot")) {
						parsedData.put(number, vic);
					}
				}

				public void characters(char ch[], int start, int length) throws SAXException {
					String str = new String(ch, start, length);
					if (lastQName.equalsIgnoreCase("number")) {
						number = new Integer(Integer.parseInt(str));
					}
					if (lastQName.equalsIgnoreCase("victory")) {
						vic = new VictoryPoint(Integer.parseInt(str));
					}
				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			Logger logger = Logger.getLogger(FaithPointSaxParser.class.getName());
			logger.info(e.getMessage());
		}
	}
}
