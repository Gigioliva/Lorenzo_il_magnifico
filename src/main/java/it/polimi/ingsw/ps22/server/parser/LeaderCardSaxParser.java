package it.polimi.ingsw.ps22.server.parser;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.card.CardExcomm;
import it.polimi.ingsw.ps22.server.card.CardLeader;
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
XML Structure
<leadercard>
    <card>
        <name>BaseCard</name>
        <requisite>
            <samedevelopmentcard>0</samedevelopmentcard>
            <cardterritory>0</cardterritory>
            <cardventure>0</cardventure>
            <cardbuilding>0</cardbuilding>
            <cardcharacter>0</cardcharacter>
            <coin>0</coin>
            <stone>0</stone>
            <wood>0</wood>
            <servant>0</servant>
            <militarypoint>0</militarypoint>
            <faithpoint>0</faithpoint>
            <victorypoint>0</victorypoint>
        </requisite>
        <eachturnharvestaction>0</eachturnharvestaction> <!-- sta a indicare il valore della azione -->
        <eachturnprodaction>0</eachturnprodaction> <!-- sta a indicare il valore della azione -->
        <eachturngaincoin>0</eachturngaincoin> <!-- sta a indicare il valore del gain -->
        <eachturngainstone>0</eachturngainstone> <!-- sta a indicare il valore del gain -->
        <eachturngainwood>0</eachturngainwood> <!-- sta a indicare il valore del gain -->
        <eachturngainservant>0</eachturngainservant> <!-- sta a indicare il valore del gain -->
        <eachturngainmilitarypoint>0</eachturngainmilitarypoint> <!-- sta a indicare il valore del gain-->
        <eachturngainfaithpoint>0</eachturngainfaithpoint> <!-- sta a indicare il valore del gain -->
        <eachturngainvictorypoint>0</eachturngainvictorypoint> <!-- sta a indicare il valore del gain -->
        <eachturngaincouncilpoint>0</eachturngaincouncilpoint> <!-- sta a indicare il valore del gain -->
        <eachturnonefamdefvalue>6</eachturnonefamdefvalue> <!-- sta a indicare il valore del azione fam -->
        <canplaceinocupatedspace>0</canplaceinocupatedspace> <!-- sta a indicare il true -->
        <nothreeadditivecoin>1</nothreeadditivecoin> <!-- sta a indicare il true -->
        <neutralmaggioration>1</neutralmaggioration> <!-- sta a indicare il valore dell'incremento -->
        <familiardefinitevalue>0</familiardefinitevalue> <!-- sta a indicare il valore dei fam colorati -->
        <familiarbonus>0</familiarbonus> <!-- sta a indicare il valore dell'inc dei fam colorati -->
        <leadercopy>1</leadercopy> <!-- sta a indicare il true -->
        <vaticansubstainvictorypointgain>1</vaticansubstainvictorypointgain> <!-- indica il gain -->
        <resourcebonusdouble>1</resourcebonusdouble> <!-- sta a indicare il true -->
        <developmentcardcoindiscount>0</developmentcardcoindiscount> <!-- indica il val del discount -->
    </card>
</leadercard>
 */

public class LeaderCardSaxParser {

	public static void LeaderRead(String pathname, ArrayList<CardLeader> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				
				CardLeader card = new CardLeader("",true);
				String lastQName = "";

				
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();
				}

				
				
				public void endElement(String uri, String localName, String qName) throws SAXException {
					lastQName = "";
					if (qName.equalsIgnoreCase("card")) {
						//azioni aggiuntive
						parsedData.add(card);
						card = new CardLeader("",true);
					}
				}

				
				
				public void characters(char ch[], int start, int length) throws SAXException {
					String str = new String(ch, start, length);
					
				}
				
				
					
				
			};

			saxParser.parse(pathname, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
