package it.polimi.ingsw.ps22.parser;

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
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.resource.FaithPoint;
import it.polimi.ingsw.ps22.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.resource.Servant;
import it.polimi.ingsw.ps22.resource.Stone;
import it.polimi.ingsw.ps22.resource.VictoryPoint;

public class ZoneBonusSaxParser {

	public static void BonusParser(String pathname, ArrayList<HashMap<String, ResourceAbstract>> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				HashMap<String, ResourceAbstract> toAdd = new HashMap<String, ResourceAbstract>();
				int lastInt = 0;
				String lastQName = "";
				/*
				boolean boolCoin = false;
				boolean boolStone = false;
				boolean boolWood = false;
				boolean boolServant = false;
				boolean boolMilitaryPoint = false;
				boolean boolFaithPoint = false;
				boolean boolVictoryPoint = false;
				boolean boolCouncilPrivilege = false;
				*/

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();
					/*if (qName.equalsIgnoreCase("Coin")) {
						boolCoin = true;
					}
					if (qName.equalsIgnoreCase("Stone")) {
						boolStone = true;
					}
					if (qName.equalsIgnoreCase("Wood")) {
						boolWood = true;
					}
					if (qName.equalsIgnoreCase("Servant")) {
						boolServant = true;
					}
					if (qName.equalsIgnoreCase("MilitaryPoint")) {
						boolMilitaryPoint = true;
					}
					if (qName.equalsIgnoreCase("FaithPoint")) {
						boolFaithPoint = true;
					}
					if (qName.equalsIgnoreCase("VictoryPoint")) {
						boolVictoryPoint = true;
					}
					if (qName.equalsIgnoreCase("CouncilPrivilege")) {
						boolCouncilPrivilege = true;
					}
					*/

				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					
					if (qName.equalsIgnoreCase("space")) {
						parsedData.add(toAdd);
						toAdd = new HashMap<String, ResourceAbstract>();
					}
				}

				public void characters(char ch[], int start, int length) throws SAXException {

					lastInt = Integer.parseInt(new String(ch, start, length));
					
					if (lastQName.equalsIgnoreCase("coin")) {
						toAdd.put("Coin", new Coin(lastInt));
					}

					if (lastQName.equalsIgnoreCase("stone")) {
						toAdd.put("Stone", new Stone(lastInt));
					}

					if (lastQName.equalsIgnoreCase("wood")) {
						toAdd.put("Wood", new Coin(lastInt));
					}

					if (lastQName.equalsIgnoreCase("servant")) {
						toAdd.put("Servant", new Servant(lastInt));
					}

					if (lastQName.equalsIgnoreCase("militarypoint")) {
						toAdd.put("MilitaryPoint", new MilitaryPoint(lastInt));
					}

					if (lastQName.equalsIgnoreCase("faithpoint")) {
						toAdd.put("FaithPoint", new FaithPoint(lastInt));
					}

					if (lastQName.equalsIgnoreCase("victorypoint")) {
						toAdd.put("VictoryPoint", new VictoryPoint(lastInt));
					}

					if (lastQName.equalsIgnoreCase("councilprivilege")) {
						toAdd.put("CouncilPrivilege", new CouncilPrivilege(lastInt));
					}
					
					/*
					if (boolCoin) {
						toAdd.put("Coin", new Coin(lastInt));
						boolCoin = false;
					}

					if (boolStone) {
						toAdd.put("Stone", new Stone(lastInt));
						boolStone = false;
					}

					if (boolWood) {
						toAdd.put("Wood", new Coin(lastInt));
						boolWood = false;
					}

					if (boolServant) {
						toAdd.put("Servant", new Servant(lastInt));
						boolServant = false;
					}

					if (boolMilitaryPoint) {
						toAdd.put("MilitaryPoint", new MilitaryPoint(lastInt));
						boolMilitaryPoint = false;
					}

					if (boolFaithPoint) {
						toAdd.put("FaithPoint", new FaithPoint(lastInt));
						boolFaithPoint = false;
					}

					if (boolVictoryPoint) {
						toAdd.put("VictoryPoint", new VictoryPoint(lastInt));
						boolVictoryPoint = false;
					}

					if (boolCouncilPrivilege) {
						toAdd.put("CouncilPrivilege", new CouncilPrivilege(lastInt));
						boolCouncilPrivilege = false;
					}
					*/
					
				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}