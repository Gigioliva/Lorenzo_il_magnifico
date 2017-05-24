package it.polimi.ingsw.ps22.parser;

import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.HashMap;

import it.polimi.ingsw.ps22.action.CardAction;
import it.polimi.ingsw.ps22.action.HarvestAction;
import it.polimi.ingsw.ps22.action.ProductionAction;
import it.polimi.ingsw.ps22.card.CardCharacter;
import it.polimi.ingsw.ps22.effect.EndVictoryEffect;
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
        <name>BASE</name>													ok
        <era>0</era>														ok
        <cost>																ok
            <coin>0</coin>													ok
            <wood>0</wood>													ok
            <stone>0</stone>												ok
            <servant>0</servant>											ok
            <militarypoint>0</militarypoint>								ok
            <militaryrequirement>0</militaryrequirement>					ok
        </cost>																ok
        <gainEffect>														ok
            <coin>0</coin>													ok
            <wood>0</wood>													ok
            <stone>0</stone>												ok
            <servant>0</servant>											ok
            <militarypoint>0</militarypoint>								ok
            <faithpoint>0</faithpoint>										ok
            <councilpoint>0</councilpoint>									ok
        </gainEffect>														ok
        <immextraprod>0</immextraprod>										ok
        <immextraharvest>0</immextraharvest>								ok
        <immactionvalue>0</immactionvalue>									ok
        <immactionvaleuassociatedcard>										ok
        	<type></type>													ok
        </immactionvaleuassociatedcard>										ok
        <victorypoint>0</victorypoint>										ok
    </card>																	ok

*/

public class CharacterCardSaxParser {

	public static void CharacterCardParser(String pathname, ArrayList<CardCharacter> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardCharacter card = new CardCharacter();
				String lastQName = "";
				int lastInt = 0;

				// ridefinizione del metodo startElement all'interno del
				// DefaultHandler
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					lastQName = qName;


				}

				// ridefinizione del metodo endElement all'interno del
				// DefaultHandler
				public void endElement(String uri, String localName, String qName) throws SAXException {

					
				}

				// ridefinizione del metodo characters all'interno del
				// DefaultHandler
				public void characters(char ch[], int start, int length) throws SAXException {

					String str = new String(ch, start, length);

					// aggiunto nome alla carta
					if (lastQName.equalsIgnoreCase("name")) {
						card.setName(str);
					} else {
						lastInt = Integer.parseInt(str);

						
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
