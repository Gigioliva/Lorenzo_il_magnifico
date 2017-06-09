package it.polimi.ingsw.ps22.server.parser;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.card.CardExcomm;
import it.polimi.ingsw.ps22.server.effect.BonusEffect;
import it.polimi.ingsw.ps22.server.effect.NoPointsCard;
import it.polimi.ingsw.ps22.server.effect.StrangeEffect;
import it.polimi.ingsw.ps22.server.effect.SubVictoryPoint;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.IncrementBuilding;
import it.polimi.ingsw.ps22.server.resource.IncrementCharacter;
import it.polimi.ingsw.ps22.server.resource.IncrementDice;
import it.polimi.ingsw.ps22.server.resource.IncrementHarvest;
import it.polimi.ingsw.ps22.server.resource.IncrementProduction;
import it.polimi.ingsw.ps22.server.resource.IncrementTerritory;
import it.polimi.ingsw.ps22.server.resource.IncrementVenture;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;
import it.polimi.ingsw.ps22.server.resource.Wood;

/*
<leadercard>



 */

public class LeaderCardSaxParser {


	public static void LeaderRead(String pathname, ArrayList<CardExcomm> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardExcomm card = new CardExcomm();
				BonusEffect permResEff =new BonusEffect();
				SubVictoryPoint subEff;
				String lastQName = "";

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					lastQName = "";
					if (qName.equalsIgnoreCase("card")) {
						card.addPermanentEffect(permResEff);
						parsedData.add(card);
						card = new CardExcomm();
					}
				}

				public void characters(char ch[], int start, int length) throws SAXException {

					String str = new String(ch, start, length);

					if (lastQName.equalsIgnoreCase("era")) {
						card.setEra(Integer.parseInt(str));
					}

					if (lastQName.equalsIgnoreCase("militaryincrement")) {
						permResEff.addBonus("MilitaryPoint", new MilitaryPoint(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("coinincrement")) {
						permResEff.addBonus("Coin", new Coin(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("servantincrement")) {
						permResEff.addBonus("Servant", new Servant(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("woodincrement")) {
						permResEff.addBonus("Wood", new Wood(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("stoneincrement")) {
						permResEff.addBonus("Stone", new Stone(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("harvestincrement")) {
						permResEff.addBonus("IncrementHarvest", new IncrementHarvest(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("productionincrement")) {
						permResEff.addBonus("IncrementProduction", new IncrementProduction(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("diceincrement")) {
						permResEff.addBonus("IncrementDice", new IncrementDice(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("territoryactionincrement")) {
						permResEff.addBonus("IncrementTerritory", new IncrementTerritory(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("characteractionincrement")) {
						permResEff.addBonus("IncrementCharacter", new IncrementCharacter(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("buildingactionincrement")) {
						permResEff.addBonus("IncrementBuilding", new IncrementBuilding(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("ventureactionincrement")) {
						permResEff.addBonus("IncrementVenture", new IncrementVenture(Integer.parseInt(str)));
					}

					if (lastQName.equalsIgnoreCase("nomarket")) {
						card.addPermanentEffect(new StrangeEffect("NoMarket"));
					}

					if (lastQName.equalsIgnoreCase("servanthalfvalue")) {
						card.addPermanentEffect(new StrangeEffect("DoubleServant"));
					}

					if (lastQName.equalsIgnoreCase("turnskip")) {
						card.addPermanentEffect(new StrangeEffect("SkipFirstMove"));
					}

					if (lastQName.equalsIgnoreCase("endinvalidcharacter")) {
						card.addEndEffect(new NoPointsCard("Character"));
					}

					if (lastQName.equalsIgnoreCase("endinvalidventure")) {
						card.addEndEffect(new NoPointsCard("Venture"));
					}

					if (lastQName.equalsIgnoreCase("endinvalidterritory")) {
						card.addEndEffect(new NoPointsCard("Territory"));
					}

					if (lastQName.equalsIgnoreCase("endvictorylose")) {
						subEff= new SubVictoryPoint("player");
						subEff.addBonus("VictoryPoint", new VictoryPoint(5));
						card.addEndEffect(subEff);
					}

					if (lastQName.equalsIgnoreCase("militarydecrementvictory")) {
						subEff= new SubVictoryPoint("player");
						subEff.addBonus("MilitaryPoint", new MilitaryPoint(1));
						card.addEndEffect(subEff);
					}

					if (lastQName.equalsIgnoreCase("endlosevictorybuildingstonewood")) {
						subEff= new SubVictoryPoint("Building");
						subEff.addBonus("Wood", new Wood(1));
						subEff.addBonus("Stone", new Stone(1));
						card.addEndEffect(subEff);
					}

					if (lastQName.equalsIgnoreCase("endlosevictoryforallresource")) {
						subEff= new SubVictoryPoint("player");
						subEff.addBonus("Coin", new Coin(1));
						subEff.addBonus("Stone", new Stone(1));
						subEff.addBonus("Wood", new Wood(1));
						subEff.addBonus("Servant", new Servant(1));
						card.addEndEffect(subEff);
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
