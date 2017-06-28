package it.polimi.ingsw.ps22.server.parser;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TimerSaxParser {

	private static void TimerRead(String pathname, HashMap<String, Integer> timers) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				String lastQName = "";

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					lastQName = "";
				}

				public void characters(char ch[], int start, int length) throws SAXException {
					String str = new String(ch, start, length);

					if (lastQName.equalsIgnoreCase("server")) {
						timers.put("Server", Integer.parseInt(str));
					}
					
					if (lastQName.equalsIgnoreCase("controller")) {
						timers.put("Controller", Integer.parseInt(str));
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			Logger logger = Logger.getLogger(TimerSaxParser.class.getName());
			logger.info(e.getMessage());
		}
	}
	
	public static int ServerTimer() {
		String pathname="src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Timers.xml";
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		TimerRead(pathname, temp);
		return temp.get("Server");
	}
	
	public static int ControllerTimer() {
		String pathname="src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Timers.xml";
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		TimerRead(pathname, temp);
		return temp.get("Controller");
	}
	
}
