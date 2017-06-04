package it.polimi.ingsw.ps22.server.parser;

import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.Point;
import it.polimi.ingsw.ps22.server.resource.Resource;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;
import it.polimi.ingsw.ps22.server.resource.Wood;

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

	public static void TerritoryRead(String pathname, ArrayList<CardTerritory> parsedData) {
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

				// ridefinizione del metodo startElement all'interno del
				// DefaultHandler
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					lastQName = qName.toLowerCase();

					if (qName.equalsIgnoreCase("harvesteffect")) {
						boolHarvest = true;
					}

				}

				// ridefinizione del metodo endElement all'interno del
				// DefaultHandler
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (qName.equalsIgnoreCase("gaineffect")) {
						card.addImmediateEffect(gainEffect);
						gainEffect = new GainResource();
					}

					else if (qName.equalsIgnoreCase("harvesteffect")) {
						card.addActionEffect(harvestEffect);
						harvestEffect = new GainResource();
						boolHarvest = false;
					}

					else if (qName.equalsIgnoreCase("card")) {
						parsedData.add(card);
						card = new CardTerritory();
					}

					lastQName = "";

				}

				// ridefinizione del metodo characters all'interno del
				// DefaultHandler
				public void characters(char ch[], int start, int length) throws SAXException {

					String str = new String(ch, start, length);

					if (lastQName.equalsIgnoreCase("name")) {
						card.setName(str);
					}

					if (lastQName.equalsIgnoreCase("era")) {
						lastInt = Integer.parseInt(str);
						card.setEra(lastInt);
					}

					if (lastQName.equalsIgnoreCase("coin")) {
						lastInt = Integer.parseInt(str);
						ResourceTypeCheck(new Coin(lastInt));
					}

					if (lastQName.equalsIgnoreCase("stone")) {
						lastInt = Integer.parseInt(str);
						ResourceTypeCheck(new Stone(lastInt));
					}

					if (lastQName.equalsIgnoreCase("wood")) {
						lastInt = Integer.parseInt(str);
						ResourceTypeCheck(new Wood(lastInt));
					}

					if (lastQName.equalsIgnoreCase("servant")) {
						lastInt = Integer.parseInt(str);
						ResourceTypeCheck(new Servant(lastInt));
					}

					if (lastQName.equalsIgnoreCase("militarypoint")) {
						lastInt = Integer.parseInt(str);
						PointTypeCheck(new MilitaryPoint(lastInt));
					}

					if (lastQName.equalsIgnoreCase("faithpoint")) {
						lastInt = Integer.parseInt(str);
						PointTypeCheck(new FaithPoint(lastInt));
					}

					if (lastQName.equalsIgnoreCase("victorypoint")) {
						lastInt = Integer.parseInt(str);
						PointTypeCheck(new VictoryPoint(lastInt));
					}

					if (lastQName.equalsIgnoreCase("councilpoint")) {
						lastInt = Integer.parseInt(str);
						CouncilPrivilegeTypeCheck(new CouncilPrivilege(lastInt));
					}

					if (lastQName.equalsIgnoreCase("harvestreq")) {
						lastInt = Integer.parseInt(str);
						card.setActionValue(lastInt);
					}

				}

				private void ResourceTypeCheck(Resource res) {
					if (boolHarvest) {
						harvestEffect.addGain(res.getName(), res);
					} else {
						gainEffect.addGain(res.getName(), res);
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
						gainEffect.addGain(res.getName(), res);
					}
				}

			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}