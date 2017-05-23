package it.polimi.ingsw.ps22.parser;

import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.card.CardTerritory;
import it.polimi.ingsw.ps22.effect.GainResource;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.resource.FaithPoint;
import it.polimi.ingsw.ps22.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.resource.Point;
import it.polimi.ingsw.ps22.resource.Resource;
import it.polimi.ingsw.ps22.resource.Servant;
import it.polimi.ingsw.ps22.resource.Stone;
import it.polimi.ingsw.ps22.resource.VictoryPoint;
import it.polimi.ingsw.ps22.resource.Wood;

/*
    <card>
        <name>BASE</name>													
        <era>0</era>														
        <gainEffect>														
            <coin>0</coin>													
            <wood>0</wood>													
            <stone>0</stone>												
            <servant>0</servant>											
            <militarypoint>0</militarypoint>											
            <faithpoint>0</faithpoint>										
            <councilpoint>0</councilpoint>
            <victorypoint>0</victorypoint>									
        </gainEffect>	
        <harvestEffect>														
            <coin>0</coin>													
            <wood>0</wood>													
            <stone>0</stone>												
            <servant>0</servant>											
            <militarypoint>0</militarypoint>											
            <faithpoint>0</faithpoint>										
            <councilpoint>0</councilpoint>
            <victorypoint>0</victorypoint>									
        </harvestEffect>														
        <harvestreq>0</harvestreq>										
    </card>	

*/

public class TerritoryCardSaxParser {

	public static void TerritoryCardParser(String pathname, ArrayList<CardTerritory> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardTerritory card = new CardTerritory();
				GainResource gainEffect = new GainResource();
				GainResource harvestEffect = new GainResource();
				int lastInt = 0;
				String lastQName;
				boolean boolHarvest = false;
				/*boolean boolName = false;
				boolean boolEra = false;
				boolean boolCoin = false;
				boolean boolStone = false;
				boolean boolWood = false;
				boolean boolServant = false;
				boolean boolMilitaryPoint = false;
				boolean boolCouncilPoint = false;
				boolean boolFaithPoint = false;
				boolean boolVictoryPoint = false;
				boolean boolHarvestReq = false; */

				// ridefinizione del metodo startElement all'interno del
				// DefaultHandler
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
	
					lastQName = qName.toLowerCase();
					
					if (qName.equalsIgnoreCase("harvesteffect")) {
						boolHarvest = true;
					}
					
					/*
					if (qName.equalsIgnoreCase("name")) {
						boolName = true;
					}

					if (qName.equalsIgnoreCase("era")) {
						boolEra = true;
					}

					if (qName.equalsIgnoreCase("coin")) {
						boolCoin = true;
					}

					if (qName.equalsIgnoreCase("stone")) {
						boolStone = true;
					}

					if (qName.equalsIgnoreCase("wood")) {
						boolWood = true;
					}

					if (qName.equalsIgnoreCase("servant")) {
						boolServant = true;
					}

					if (qName.equalsIgnoreCase("military")) {
						boolMilitaryPoint = true;
					}

					if (qName.equalsIgnoreCase("councilpoint")) {
						boolCouncilPoint = true;
					}

					if (qName.equalsIgnoreCase("faithpoint")) {
						boolFaithPoint = true;
					}

					if (qName.equalsIgnoreCase("victorypoint")) {
						boolVictoryPoint = true;
					}

					if (qName.equalsIgnoreCase("harvestreq")) {
						boolHarvestReq = true;
					}
					*/
				}

				// ridefinizione del metodo endElement all'interno del
				// DefaultHandler
				public void endElement(String uri, String localName, String qName) throws SAXException {

					if (qName.equalsIgnoreCase("gaineffect")) {
						card.addImmediateEffect(gainEffect);
						gainEffect = new GainResource();
					}

					if (qName.equalsIgnoreCase("harvesteffect")) {
						card.addActionEffect(harvestEffect);
						harvestEffect = new GainResource();
						boolHarvest=false;
					}

					if (qName.equalsIgnoreCase("card")) {
						parsedData.add(card);
						card = new CardTerritory();
					}
				}

				// ridefinizione del metodo characters all'interno del
				// DefaultHandler
				public void characters(char ch[], int start, int length) throws SAXException {
					
				String str = new String(ch, start, length);
					if(lastQName.equalsIgnoreCase("name")) {
					card.setName(str);	
					}
					else {
						
						lastInt=Integer.parseInt(str);
						
						if(lastQName.equalsIgnoreCase("coin")) {
							ResourceTypeCheck(new Coin(lastInt));
						}
						
						if(lastQName.equalsIgnoreCase("stone")) {
							ResourceTypeCheck(new Stone(lastInt));
						}
						
						if(lastQName.equalsIgnoreCase("wood")) {
							ResourceTypeCheck(new Wood(lastInt));
						}
						
						if(lastQName.equalsIgnoreCase("servant")) {
							ResourceTypeCheck(new Servant(lastInt));
						}
						
						if(lastQName.equalsIgnoreCase("militarypoint")) {
							PointTypeCheck(new MilitaryPoint(lastInt));
						}
						
						if(lastQName.equalsIgnoreCase("faithpoint")) {
							PointTypeCheck(new FaithPoint(lastInt));
						}
						
						if(lastQName.equalsIgnoreCase("victorypoint")) {
							PointTypeCheck(new VictoryPoint(lastInt));
						}
						
						if(lastQName.equalsIgnoreCase("militarypoint")) {
							CouncilPrivilegeTypeCheck(new CouncilPrivilege(lastInt));
						}
						
					}
					
					/*
					// aggiunto nome alla carta
					if (boolName) {
						card.setName(new String(ch, start, length));
						boolName = false;
					}

					// aggiunta era alla carta
					if (boolEra) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						card.setEra(lastInt);
						boolEra = false;
					}

					// monete
					if (boolCoin) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolHarvest) {
							harvestEffect.addGain("Coin", new Coin(lastInt));
						} else {
							gainEffect.addGain("Coin", new Coin(lastInt));
						}
						boolCoin = false;
					}

					// pietre
					if (boolStone) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolHarvest) {
							harvestEffect.addGain("Stone", new Stone(lastInt));
						} else {
							gainEffect.addGain("Stone", new Stone(lastInt));
						}
						boolStone = false;
					}

					// legni
					if (boolWood) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolHarvest) {
							harvestEffect.addGain("Wood", new Wood(lastInt));
						} else {
							gainEffect.addGain("Wood", new Wood(lastInt));
						}
						boolWood = false;
					}

					// servitori
					if (boolServant) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolHarvest) {
							harvestEffect.addGain("Servant", new Servant(lastInt));
						} else {
							gainEffect.addGain("Servant", new Servant(lastInt));
						}
						boolServant = false;
					}

					// punti militari
					if (boolMilitaryPoint) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolHarvest) {
							harvestEffect.addGain("MilitaryPoint", new MilitaryPoint(lastInt));
						} else {
							gainEffect.addGain("MilitaryPoint", new MilitaryPoint(lastInt));
						}
						boolMilitaryPoint = false;
					}

					// privilegio del consiglio
					if (boolCouncilPoint) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolHarvest) {
							harvestEffect.addGain("CouncilPrivilege", new CouncilPrivilege(lastInt));
						} else {
							gainEffect.addGain("CouncilPrivilege", new CouncilPrivilege(lastInt));
						}
						boolCouncilPoint = false;
					}

					// punti fede
					if (boolFaithPoint) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolHarvest) {
							harvestEffect.addGain("FaithPoint", new FaithPoint(lastInt));
						} else {
							gainEffect.addGain("FaithPoint", new FaithPoint(lastInt));
						}
						boolFaithPoint = false;
					}

					// punti vittoria
					if (boolVictoryPoint) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolHarvest) {
							harvestEffect.addGain("VictoryPoint", new VictoryPoint(lastInt));
						} else {
							gainEffect.addGain("VictoryPoint", new VictoryPoint(lastInt));
						}
						boolVictoryPoint = false;
					}

					// requisito per azione raccolto
					if (boolHarvestReq) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						card.setActionValue(lastInt);
						boolHarvestReq = false;
					}
					*/
				}

				private void ResourceTypeCheck(Resource res) {
					if (boolHarvest) {
						harvestEffect.addGain(res.getName(), res);
					} else {
						gainEffect.addGain("Coin", res);
					}
				}

				private void PointTypeCheck(Point point) {
					if (boolHarvest) {
						harvestEffect.addGain(point.getName(), point);
					} else {
						gainEffect.addGain(point.getName(), point);
					}
				}
				
				private void CouncilPrivilegeTypeCheck(CouncilPrivilege res) {
					if (boolHarvest) {
						harvestEffect.addGain(res.getName(), res);
					} else {
						gainEffect.addGain("Coin", res);
					}
				}
				
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}