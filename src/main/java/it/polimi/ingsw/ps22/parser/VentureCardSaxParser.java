package it.polimi.ingsw.ps22.parser;

import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import it.polimi.ingsw.ps22.card.CardVenture;
import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.resource.FaithPoint;
import it.polimi.ingsw.ps22.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.resource.Servant;
import it.polimi.ingsw.ps22.resource.Stone;
import it.polimi.ingsw.ps22.resource.VictoryPoint;

// Ottimizzazione possibile: una sola variabile per gestire i valori interi
/*
    <card>
        <name>BASE</name>
        <era>0</era>
        <cost>
            <coincost>0</coincost>
            <woodcost>0</woodcost>
            <stonecost>0</stonecost>
            <servantcost>0</servantcost>
            <militarycost>0</militarycost>
            <militaryrequirement>0</militaryrequirement>
        </cost>
        <gainEffect>
            <coin>0</coin>
            <wood>0</wood>
            <stone>0</stone>
            <servant>0</servant>
            <militarypoint>0</militarypoint>
            <faithpoint>0</faithpoint>
            <councilpoint>0</councilpoint>
        </gainEffect>
        <immextraprod>0</immextraprod>
        <immextraharvest>0</immextraharvest>
        <immactionvalue>0</immactionvalue>
        <immediateactionincrement>0</immediateactionincrement>
        <immactionvaleuassociatedcard>all</immactionvaleuassociatedcard>
        <victorypoint>0</victorypoint>
    </card>

*/

public class VentureCardSaxParser {

	public static void VentureCardParser(String pathname, ArrayList<CardVenture> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardVenture card = new CardVenture();
				HashMap<String, ResourceAbstract> cost = new HashMap<String, ResourceAbstract>();
				HashMap<String, ResourceAbstract> requisite = new HashMap<String, ResourceAbstract>();
				int lastEra = 0;
				int lastCoincost = 0;
				int lastStonecost = 0;
				int lastWoodcost = 0;
				int lastServantcost = 0;
				int lastMilitaryPointcost = 0;
				int lastMilitaryPointrequest = 0;
				boolean boolName = false;
				boolean boolEra = false;
				boolean boolCoincost = false;
				boolean boolStonecost = false;
				boolean boolWoodcost = false;
				boolean boolServantcost = false;
				boolean boolMilitaryPointcost = false;
				boolean boolMilitaryPointrequest = false;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					// System.out.println("Start Element :" + qName);
					if (qName.equalsIgnoreCase("coincost")) {
						boolCoincost = true;
					}
					if (qName.equalsIgnoreCase("stonecost")) {
						boolStonecost = true;
					}
					if (qName.equalsIgnoreCase("woodcost")) {
						boolWoodcost = true;
					}
					if (qName.equalsIgnoreCase("servantcost")) {
						boolServantcost = true;
					}
					if (qName.equalsIgnoreCase("militarycost")) {
						boolMilitaryPointcost = true;
					}
					if (qName.equalsIgnoreCase("militarycost")) {
						boolMilitaryPointcost = true;
					}
					if (qName.equalsIgnoreCase("cost")) {
						cost = new HashMap<String, ResourceAbstract>();
						requisite = new HashMap<String, ResourceAbstract>();
					}

				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (qName.equalsIgnoreCase("cost")) {
						card.addRequisiteCost(cost, requisite);
					}
					if (qName.equalsIgnoreCase("card")) {
						parsedData.add(card);
						card = new CardVenture();
					}
					if (qName.equalsIgnoreCase("gaineffect")) {
						//creazione effetto immediato
					}
					
				}

				public void characters(char ch[], int start, int length) throws SAXException {

					if (boolName) {
						card.setName(new String(ch, start, length));
						boolName = false;
					}
					
					if (boolEra) {
						lastEra = Integer.parseInt(new String(ch, start, length));
						card.setEra(lastEra);
						boolEra = false;
					}
					
					if (boolCoincost) {
						lastCoincost = Integer.parseInt(new String(ch, start, length));
						cost.put("Coin", new Coin(lastCoincost));
						boolCoincost = false;
					}

					if (boolStonecost) {
						lastStonecost = Integer.parseInt(new String(ch, start, length));
						cost.put("Stone", new Stone(lastStonecost));
						boolStonecost = false;
					}

					if (boolWoodcost) {
						lastWoodcost = Integer.parseInt(new String(ch, start, length));
						cost.put("Wood", new Coin(lastWoodcost));
						boolWoodcost = false;
					}

					if (boolServantcost) {
						lastServantcost = Integer.parseInt(new String(ch, start, length));
						cost.put("Servant", new Servant(lastServantcost));
						boolServantcost = false;
					}

					if (boolMilitaryPointcost) {
						lastMilitaryPointcost = Integer.parseInt(new String(ch, start, length));
						cost.put("MilitaryPoint", new MilitaryPoint(lastMilitaryPointcost));
						boolMilitaryPointcost = false;
					}

					if (boolMilitaryPointrequest) {
						lastMilitaryPointrequest = Integer.parseInt(new String(ch, start, length));
						requisite.put("MilitaryPoint", new MilitaryPoint(lastMilitaryPointrequest));
						boolMilitaryPointrequest = false;
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}