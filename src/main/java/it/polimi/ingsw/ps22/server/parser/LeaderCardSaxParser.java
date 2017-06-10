package it.polimi.ingsw.ps22.server.parser;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.effect.BonusEffect;
import it.polimi.ingsw.ps22.server.effect.NoPointsCard;
import it.polimi.ingsw.ps22.server.effect.StrangeEffect;
import it.polimi.ingsw.ps22.server.effect.SubVictoryPoint;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
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
        
        <eachturnharvestaction>0</eachturnharvestaction>
        <!-- sta a indicare il valore della azione -->
        <eachturnprodaction>0</eachturnprodaction>
        <!-- sta a indicare il valore della azione -->
        <gainresource>
            <eachturngaincoin>0</eachturngaincoin>
            <!-- sta a indicare il valore del gain -->
            <eachturngainstone>0</eachturngainstone>
            <!-- sta a indicare il valore del gain -->
            <eachturngainwood>0</eachturngainwood>
            <!-- sta a indicare il valore del gain -->
            <eachturngainservant>0</eachturngainservant>
            <!-- sta a indicare il valore del gain -->
            <eachturngainmilitarypoint>0</eachturngainmilitarypoint>
            <!-- sta a indicare il valore del gain-->
            <eachturngainfaithpoint>0</eachturngainfaithpoint>
            <!-- sta a indicare il valore del gain -->
            <eachturngainvictorypoint>0</eachturngainvictorypoint>
            <!-- sta a indicare il valore del gain -->
            <eachturngaincouncilpoint>0</eachturngaincouncilpoint>
            <!-- sta a indicare il valore del gain -->
        </gainresource>
        <eachturnonefamdefvalue>6</eachturnonefamdefvalue>
        <!-- sta a indicare il valore del azione fam -->
        <canplaceinocupatedspace>0</canplaceinocupatedspace>
        <!-- sta a indicare il true -->
        <nothreeadditivecoin>1</nothreeadditivecoin>
        <!-- sta a indicare il true -->
        <neutralmaggioration3>1</neutralmaggioration3>
        <!-- sta a indicare il true -->
        <familiardefinitevalue>1</familiardefinitevalue>
        <!-- sta a indicare il true -->
        <familiarbonus+2>1</familiarbonus+2>
        <!-- sta a indicare il true -->
        <leadercopy>1</leadercopy>
        <!-- sta a indicare il true -->
        <vaticansubstainvictorypointgain5>1</vaticansubstainvictorypointgain5>
        <!-- sta a indicare il true -->
        <resourcebonusdouble>1</resourcebonusdouble>
        <!-- sta a indicare il true -->
        <developmentcardcoindiscount>0</developmentcardcoindiscount>
        <!-- indica il val del discount -->
    </card>
</leadercard>
 */

public class LeaderCardSaxParser {

	public static void LeaderRead(String pathname, ArrayList<CardLeader> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardLeader card;
				HashMap<String, Integer> req = new HashMap<String, Integer>();
				String lastQName = "";
				private boolean boolRequisite = false;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();
					if (qName.equalsIgnoreCase("requisite")) {
						boolRequisite = true;

					}

				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					lastQName = "";

					if (qName.equalsIgnoreCase("card")) {
						parsedData.add(card);
					}

					if (qName.equalsIgnoreCase("requisite")) {
						card.addRequisite(req);
						req = new HashMap<String, Integer>();
					}

				}

				public void characters(char ch[], int start, int length) throws SAXException {
					String str = new String(ch, start, length);

					if (boolRequisite) {
						if (lastQName.equalsIgnoreCase("coin")) {
							//req.put("Coin",new Coin(Integer.parseInt(str)));
						}

						if (lastQName.equalsIgnoreCase("stone")) {
							
						}

						if (lastQName.equalsIgnoreCase("wood")) {
							
						}

						if (lastQName.equalsIgnoreCase("servant")) {
							
						}

						if (lastQName.equalsIgnoreCase("militarypoint")) {
							
						}

						if (lastQName.equalsIgnoreCase("faithpoint")) {
							
						}

						if (lastQName.equalsIgnoreCase("victorypoint")) {
							
						}

						if (lastQName.equalsIgnoreCase("councilpoint")) {
							
						}
					}

				}

			};

			saxParser.parse(pathname, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
