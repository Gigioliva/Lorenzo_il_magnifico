package it.polimi.ingsw.ps22.server.parser;

/*
XML Structure

<all>
<personalboard>
    <harvestpersonalbonus>
        <coin>0</coin>
        <wood>0</wood>
        <stone>0</stone>
        <servant>0</servant>
        <militarypoint>0</militarypoint>
        <faithpoint>0</faithpoint>
        <councilpoint>0</councilpoint>
        <victorypoint>0</victorypoint>
    </harvestpersonalbonus>
    <prodpersonalbonus>
        <coin>0</coin>
        <wood>0</wood>
        <stone>0</stone>
        <servant>0</servant>
        <militarypoint>0</militarypoint>
        <faithpoint>0</faithpoint>
        <councilpoint>0</councilpoint>
        <victorypoint>0</victorypoint>
    </prodpersonalbonus>
    <territorybonus>
        <value>0</value>
        <victorypoint>0</victorypoint>
        <require>0</require>
    </territorybonus>
    <characterbonus>
        <value>0</value>
        <victorypoint>0</victorypoint>
    </characterbonus>
</personalboard>
</all>
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.player.PersonalBoard;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;
import it.polimi.ingsw.ps22.server.resource.Wood;

public class PersonalBoardSaxParser {

	/**
	 * This method read data to create personal boards of the player from XML
	 * file
	 * 
	 * @param pathname
	 *            is the pathname of the file you want to read
	 * @param parsedData
	 *            is the {@link ArrayList} of {@link PersonalBoard} to fill with
	 *            XML data
	 */
	public static void PersonalBoardRead(String pathname, ArrayList<PersonalBoard> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				PersonalBoard toAdd = new PersonalBoard();
				HashMap<String, GainResource> personalBonus = new HashMap<String, GainResource>();
				HashMap<String, ResourceAbstract> harvestGain = new HashMap<String, ResourceAbstract>();
				HashMap<String, ResourceAbstract> prodGain = new HashMap<String, ResourceAbstract>();
				HashMap<Integer, VictoryPoint> bonusCharacter = new HashMap<Integer, VictoryPoint>();
				HashMap<Integer, MilitaryPoint> requirementHarvest = new HashMap<Integer, MilitaryPoint>();
				HashMap<Integer, VictoryPoint> bonusHarvest = new HashMap<Integer, VictoryPoint>();
				GainResource gain;
				String lastQName = "";
				int lastInt = 0;
				int lastValue = 0;
				boolean boolHarvestBonus = false;
				boolean boolProdBonus = false;
				boolean boolTerritory = false;
				boolean boolCharacter = false;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName;
					if (qName.equalsIgnoreCase("harvestpersonalbonus")) {
						boolHarvestBonus = true;
					}

					if (qName.equalsIgnoreCase("prodpersonalbonus")) {
						boolProdBonus = true;
					}

					if (qName.equalsIgnoreCase("territorybonus")) {
						boolTerritory = true;
					}

					if (qName.equalsIgnoreCase("characterbonus")) {
						boolCharacter = true;
					}
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					lastQName = "";

					if (qName.equalsIgnoreCase("harvestpersonalbonus")) {
						boolHarvestBonus = false;
					}

					if (qName.equalsIgnoreCase("prodpersonalbonus")) {
						boolProdBonus = false;
					}

					if (qName.equalsIgnoreCase("personalbonus")) {
						gain = new GainResource();
						for (String el : harvestGain.keySet()) {
							gain.addGain(el, harvestGain.get(el));
						}
						personalBonus.put("Harvest", gain);
						gain = new GainResource();
						for (String el : prodGain.keySet()) {
							gain.addGain(el, prodGain.get(el));
						}
						personalBonus.put("Production", gain);
						toAdd.setPersonalBonus(personalBonus);
						personalBonus = new HashMap<String, GainResource>();
						harvestGain = new HashMap<String, ResourceAbstract>();
						prodGain = new HashMap<String, ResourceAbstract>();
					}

					if (qName.equalsIgnoreCase("territorybonus")) {
						boolTerritory = false;
						toAdd.setBonusHarvest(bonusHarvest);
						toAdd.setRequirementHarvest(requirementHarvest);
						requirementHarvest = new HashMap<Integer, MilitaryPoint>();
						bonusHarvest = new HashMap<Integer, VictoryPoint>();
					}

					if (qName.equalsIgnoreCase("characterbonus")) {
						boolCharacter = false;
						toAdd.setBonusCharacter(bonusCharacter);
						bonusCharacter = new HashMap<Integer, VictoryPoint>();
					}

					if (qName.equalsIgnoreCase("personalboard")) {
						parsedData.add(toAdd);
						toAdd = new PersonalBoard();
						personalBonus = new HashMap<String, GainResource>();
					}

				}

				public void characters(char ch[], int start, int length) throws SAXException {

					String str = new String(ch, start, length);

					if (lastQName.equalsIgnoreCase("pathname")) {
						toAdd.setPathname(str);
					}

					if (boolHarvestBonus) {
						if (lastQName.equalsIgnoreCase("coin")) {
							lastInt = Integer.parseInt(str);
							harvestGain.put("Coin", new Coin(lastInt));
						}
						if (lastQName.equalsIgnoreCase("stone")) {
							lastInt = Integer.parseInt(str);
							harvestGain.put("Stone", new Stone(lastInt));
						}
						if (lastQName.equalsIgnoreCase("wood")) {
							lastInt = Integer.parseInt(str);
							harvestGain.put("Wood", new Wood(lastInt));
						}
						if (lastQName.equalsIgnoreCase("servant")) {
							lastInt = Integer.parseInt(str);
							harvestGain.put("Servant", new Servant(lastInt));
						}
						if (lastQName.equalsIgnoreCase("militarypoint")) {
							lastInt = Integer.parseInt(str);
							harvestGain.put("MilitaryPoint", new MilitaryPoint(lastInt));
						}
						if (lastQName.equalsIgnoreCase("faithpoint")) {
							lastInt = Integer.parseInt(str);
							harvestGain.put("FaithPoint", new FaithPoint(lastInt));
						}
						if (lastQName.equalsIgnoreCase("victorypoint")) {
							lastInt = Integer.parseInt(str);
							harvestGain.put("VictoryPoint", new VictoryPoint(lastInt));
						}
						if (lastQName.equalsIgnoreCase("councilpoint")) {
							lastInt = Integer.parseInt(str);
							harvestGain.put("CouncilPrivilege", new CouncilPrivilege(lastInt));
						}

					}

					if (boolProdBonus) {
						if (lastQName.equalsIgnoreCase("coin")) {
							lastInt = Integer.parseInt(str);
							prodGain.put("Coin", new Coin(lastInt));
						}
						if (lastQName.equalsIgnoreCase("stone")) {
							lastInt = Integer.parseInt(str);
							prodGain.put("Stone", new Stone(lastInt));
						}
						if (lastQName.equalsIgnoreCase("wood")) {
							lastInt = Integer.parseInt(str);
							prodGain.put("Wood", new Wood(lastInt));
						}
						if (lastQName.equalsIgnoreCase("servant")) {
							lastInt = Integer.parseInt(str);
							prodGain.put("Servant", new Servant(lastInt));
						}
						if (lastQName.equalsIgnoreCase("militarypoint")) {
							lastInt = Integer.parseInt(str);
							prodGain.put("MilitaryPoint", new MilitaryPoint(lastInt));
						}
						if (lastQName.equalsIgnoreCase("faithpoint")) {
							lastInt = Integer.parseInt(str);
							prodGain.put("FaithPoint", new FaithPoint(lastInt));
						}
						if (lastQName.equalsIgnoreCase("victorypoint")) {
							lastInt = Integer.parseInt(str);
							prodGain.put("VictoryPoint", new VictoryPoint(lastInt));
						}
						if (lastQName.equalsIgnoreCase("councilpoint")) {
							lastInt = Integer.parseInt(str);
							prodGain.put("CouncilPrivilege", new CouncilPrivilege(lastInt));
						}

					}

					if (boolTerritory) {
						if (lastQName.equalsIgnoreCase("value")) {
							lastValue = Integer.parseInt(str);
						}
						if (lastQName.equalsIgnoreCase("victorypoint")) {
							lastInt = Integer.parseInt(str);
							bonusHarvest.put(lastValue, new VictoryPoint(lastInt));
						}
						if (lastQName.equalsIgnoreCase("require")) {
							lastInt = Integer.parseInt(str);
							requirementHarvest.put(lastValue, new MilitaryPoint(lastInt));
						}
					}

					if (boolCharacter) {
						if (lastQName.equalsIgnoreCase("value")) {
							lastValue = Integer.parseInt(str);
						}
						if (lastQName.equalsIgnoreCase("victorypoint")) {
							lastInt = Integer.parseInt(str);
							bonusCharacter.put(lastValue, new VictoryPoint(lastInt));
						}
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			Logger logger = Logger.getLogger(PersonalBoardSaxParser.class.getName());
			logger.info(e.getMessage());
		}

	}

}
