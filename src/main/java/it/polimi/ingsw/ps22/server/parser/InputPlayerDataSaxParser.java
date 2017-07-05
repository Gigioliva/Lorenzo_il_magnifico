package it.polimi.ingsw.ps22.server.parser;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.view.UserData;

/* 
 XML Structure
 
	<userdata>
    	<user>
        	<username></username>
        	<password></password>
        	<numvict></numvict>
        	<numgame></numgame>
    	</user>
	</userdata>
	
 */

public class InputPlayerDataSaxParser {

	/**
	 * This method load players' data from XML file
	 * 
	 * @param pathname
	 *            is the pathname of the file you want to read
	 * @param parsedData
	 *            is the {@link HashMap} to fill with XML data
	 */
	public static void PlayerRead(String pathname, HashMap<String, UserData> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				String lastQName = "";
				String user;
				UserData toAdd;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {

					lastQName = "";

					if (qName.equalsIgnoreCase("user")) {
						parsedData.put(user, toAdd);
					}
				}

				public void characters(char ch[], int start, int length) throws SAXException {

					String str = new String(ch, start, length);

					if (lastQName.equalsIgnoreCase("username")) {
						user = str;
					}

					if (lastQName.equalsIgnoreCase("password")) {
						toAdd = new UserData(str);
					}

					if (lastQName.equalsIgnoreCase("numvict")) {
						toAdd.setNumVictory(Integer.parseInt(str));
					}

					if (lastQName.equalsIgnoreCase("numgame")) {
						toAdd.setNumPlayedGame(Integer.parseInt(str));
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			Logger logger = Logger.getLogger(InputPlayerDataSaxParser.class.getName());
			logger.info(e.getMessage());
		}

	}

}
