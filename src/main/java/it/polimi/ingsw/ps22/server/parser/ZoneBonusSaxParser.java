package it.polimi.ingsw.ps22.server.parser;

/*

XML Structure:
<spaces>   
    <space>
        <Coin>1</Coin>
        <Stone>1</Stone>
        <Wood>1</Wood>
        <Servant>1</Servant>
        <MilitaryPoint>1</MilitaryPoint>
        <FaithPoint>1</FaithPoint>
        <VictoryPoint>1</VictoryPoint>
        <CouncilPrivilege>1</CouncilPrivilege>
    </space>    
</spaces>

 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class ZoneBonusSaxParser {
	/**
	 * This method read data to create board's bonus zone from XML file
	 * 
	 * @param pathname
	 *            is the pathname of the file to read
	 * @param parsedData
	 *            is the {@link ArrayList} of {@link HashMap} to fill with XML
	 *            data
	 */
	public static void BonusRead(String pathname, ArrayList<HashMap<String, ResourceAbstract>> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				HashMap<String, ResourceAbstract> toAdd = new HashMap<String, ResourceAbstract>();
				String lastQName = "";

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {

					lastQName = "";

					if (qName.equalsIgnoreCase("space")) {
						parsedData.add(toAdd);
						toAdd = new HashMap<String, ResourceAbstract>();
					}
				}

				public void characters(char ch[], int start, int length) throws SAXException {

					String str = new String(ch, start, length);

					if (lastQName.equalsIgnoreCase("coin")) {
						toAdd.put("Coin", new Coin(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("stone")) {
						toAdd.put("Stone", new Stone(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("wood")) {
						toAdd.put("Wood", new Coin(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("servant")) {
						toAdd.put("Servant", new Servant(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("militarypoint")) {
						toAdd.put("MilitaryPoint", new MilitaryPoint(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("faithpoint")) {
						toAdd.put("FaithPoint", new FaithPoint(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("victorypoint")) {
						toAdd.put("VictoryPoint", new VictoryPoint(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("councilprivilege")) {
						toAdd.put("CouncilPrivilege", new CouncilPrivilege(Integer.parseInt(str)));
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			Logger logger = Logger.getLogger(ZoneBonusSaxParser.class.getName());
			logger.info(e.getMessage());
		}

	}

}