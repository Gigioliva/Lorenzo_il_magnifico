package it.polimi.ingsw.ps22.parser;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.action.CardAction;
import it.polimi.ingsw.ps22.action.HarvestAction;
import it.polimi.ingsw.ps22.action.ProductionAction;
import it.polimi.ingsw.ps22.card.CardBuilding;
import it.polimi.ingsw.ps22.effect.EndVictoryEffect;
import it.polimi.ingsw.ps22.effect.ExchangeResource;
import it.polimi.ingsw.ps22.effect.ExtraAction;
import it.polimi.ingsw.ps22.effect.GainResource;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.resource.FaithPoint;
import it.polimi.ingsw.ps22.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.resource.Servant;
import it.polimi.ingsw.ps22.resource.Stone;
import it.polimi.ingsw.ps22.resource.Wood;

/*
	<card>
	
		<name>BASE</name>													
		<era>0</era>
		<prodreq>0</prodreq>
		
		<cost>
            <coin>0</coin>	
            <wood>0</wood>		
            <stone>0</stone>
            <servant>0</servant>
            <military>0</military>
        </cost>
							
		<gainEffect>														
    		<coin>0</coin>													
    		<wood>0</wood>													
    		<stone>0</stone>												
    		<servant>0</servant>											
    		<military>0</military>											
    		<faithpoint>0</faithpoint>										
    		<councilpoint>0</councilpoint>
    		<victorypoint>0</victorypoint>									
		</gainEffect>

		<gainprodeffect>														
    		<coin>0</coin>													
    		<wood>0</wood>													
    		<stone>0</stone>
    		<servant>0</servant>
    		<military>0</military>
    		<faithpoint>0</faithpoint>	
    		<councilpoint>0</councilpoint>
    		<victorypoint>0</victorypoint>	
		</gainprodeffect>

		<exchangeprodeffect>														
    		<coin>0</coin>													
    		<wood>0</wood>													
    		<stone>0</stone>
    		<servant>0</servant>
    		<military>0</military>
    		<faithpoint>0</faithpoint>	
    		<councilpoint>0</councilpoint>
    		<victorypoint>0</victorypoint>	
		</exchangeprodeffect>

		<cardmoltprodeffect>														
    		<coin>0</coin>													
    		<wood>0</wood>													
    		<stone>0</stone>
    		<servant>0</servant>
    		<military>0</military>
    		<faithpoint>0</faithpoint>	
    		<councilpoint>0</councilpoint>
    		<victorypoint>0</victorypoint>
    		<type>CardBuilding</type>
		</cardmoltprodeffect>

    </card>	
*/

public class BuildingCardSaxParser {

	public static void VentureCardParser(String pathname, ArrayList<CardBuilding> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardBuilding card = new CardBuilding();
				HashMap<String, ResourceAbstract> cost = new HashMap<String, ResourceAbstract>();
				GainResource gainEffect = new GainResource();
				ExchangeResource exchange = new ExchangeResource();
				CardAction cardAct;
				int lastInt = 0;
				int lastImmActionValue = 0;
				boolean boolName = false;
				boolean boolEra = false;
				boolean boolCost = false;
				boolean boolGainImm = false;
				boolean boolGainProd = false;
				boolean boolExchangeProd = false;
				boolean boolCardMoltProd = false;
				boolean boolCoin = false;
				boolean boolStone = false;
				boolean boolWood = false;
				boolean boolServant = false;
				boolean boolMilitaryPoint = false;
				boolean boolCouncilPoint = false;
				boolean boolFaithPoint = false;
				boolean boolVictoryPoint = false;
				boolean boolType = false;

				// ridefinizione del metodo startElement all'interno del
				// DefaultHandler
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					// Boolean basilari della carta
					if (qName.equalsIgnoreCase("name")) {
						boolName = true;
					}

					if (qName.equalsIgnoreCase("era")) {
						boolEra = true;
					}

					// Boolean per capire in quale ambito stiamo parlando della
					// risorsa
					if (qName.equalsIgnoreCase("cost")) {
						boolCost = true;
					}

					if (qName.equalsIgnoreCase("gaineffect")) {
						boolGainImm = true;
					}

					if (qName.equalsIgnoreCase("gainprodeffect")) {
						boolGainProd = true;
					}

					if (qName.equalsIgnoreCase("exchangeprodeffect")) {
						boolExchangeProd = true;
					}

					if (qName.equalsIgnoreCase("cardmoltprodeffect")) {
						boolCardMoltProd = true;
					}

					// Boolean Risorse
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

					// Boolean tipo di carta da modificare
					if (qName.equalsIgnoreCase("type")) {
						boolType = true;
					}

				}

				// ridefinizione del metodo endElement all'interno del
				// DefaultHandler
				public void endElement(String uri, String localName, String qName) throws SAXException {

				}

				// ridefinizione del metodo characters all'interno del
				// DefaultHandler
				public void characters(char ch[], int start, int length) throws SAXException {

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}