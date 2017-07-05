package it.polimi.ingsw.ps22.server.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.action.HarvestAction;
import it.polimi.ingsw.ps22.server.action.ProductionAction;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.effect.ExtraAction;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.ReduceCostEffect;
import it.polimi.ingsw.ps22.server.effect.StrangeEffect;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
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
            <Territory>0</Territory>
            <Venture>0</Venture>
            <Building>0</Building>
            <Character>0</Character>
            <Coin>0</Coin>
            <Stone>0</Stone>
            <Wood>0</Wood>
            <Servant>0</Servant>
            <MilitaryPoint>0</MilitaryPoint>
            <FaithPoint>0</FaithPoint>
            <VictoryPoint>0</VictoryPoint>
        </requisite>
        <eachturnharvestaction>0</eachturnharvestaction>
        <!-- sta a indicare il valore della azione -->
        <eachturnprodaction>0</eachturnprodaction>
        <!-- sta a indicare il valore della azione -->
        <gainresource>
            <Coin>0</Coin>
            <!-- sta a indicare il valore del gain -->
            <Stone>0</Stone>
            <!-- sta a indicare il valore del gain -->
            <Wood>0</Wood>
            <!-- sta a indicare il valore del gain -->
            <Servant>0</Servant>
            <!-- sta a indicare il valore del gain -->
            <MilitaryPoint>0</MilitaryPoint>
            <!-- sta a indicare il valore del gain-->
            <FaithPoint>0</FaithPoint>
            <!-- sta a indicare il valore del gain -->
            <VictoryPoint>0</VictoryPoint>
            <!-- sta a indicare il valore del gain -->
            <CouncilPrivilege>0</CouncilPrivilege>
            <!-- sta a indicare il valore del gain -->
        </gainresource>
        <eachturnonefamdefvalue6>1</eachturnonefamdefvalue6>
        <!-- sta a indicare il true -->
        <canplaceinocupatedspace>1</canplaceinocupatedspace>
        <!-- sta a indicare il true -->
        <nothreeadditivecoin>1</nothreeadditivecoin>
        <!-- sta a indicare il true -->
        <neutralmaggioration3>1</neutralmaggioration3>
        <!-- sta a indicare il true -->
        <familiardefinitevalue5>1</familiardefinitevalue5>
        <!-- sta a indicare il true -->
        <familiarbonus2>1</familiarbonus2>
        <!-- sta a indicare il true -->
        <leadercopy>1</leadercopy>
        <!-- sta a indicare il true -->
        <vaticansubstainvictorypointgain5>1</vaticansubstainvictorypointgain5>
        <!-- sta a indicare il true -->
        <resourcebonusdouble>1</resourcebonusdouble>
        <!-- sta a indicare il true -->
        <developmentcardcoindiscount3>1</developmentcardcoindiscount3>
        <!-- sta a indicare il true -->
        <nomilitarypointrequisiteterritory>1</nomilitarypointrequisiteterritory>
        <!-- sta a indicare il true -->
    </card>
</leadercard>
 */

public class LeaderCardSaxParser {

	/**
	 * This method parse a XML file to read the {@link ArrayList} of
	 * {@link CardLeader}
	 * 
	 * @param pathname
	 *            is the path of the file to read to load the card
	 * @param parsedData
	 *            is the {@link ArrayList} of {@link CardLeader} you want to
	 *            fill with XML data
	 */
	public static void LeaderRead(String pathname, ArrayList<CardLeader> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardLeader card;
				HashMap<String, Integer> req = new HashMap<String, Integer>();
				GainResource gainTurnEffect = new GainResource();
				String lastQName = "";
				private boolean boolRequisite = false;
				private boolean boolGainResource = false;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = new String(qName);
					if (qName.equalsIgnoreCase("requisite")) {
						boolRequisite = true;
					}

					if (qName.equalsIgnoreCase("gainresource")) {
						boolGainResource = true;
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
						boolRequisite = false;
					}

					if (qName.equalsIgnoreCase("gainresource")) {
						card.addImmediateEffect(gainTurnEffect);
						gainTurnEffect = new GainResource();
						boolGainResource = false;
					}

				}

				public void characters(char ch[], int start, int length) throws SAXException {
					String str = new String(ch, start, length);

					if (lastQName.equalsIgnoreCase("name")) {
						card = new CardLeader(str);
					}

					if (boolRequisite) {
						if (lastQName.equalsIgnoreCase("territory") || lastQName.equalsIgnoreCase("venture")
								|| lastQName.equalsIgnoreCase("building") || lastQName.equalsIgnoreCase("character")
								|| lastQName.equalsIgnoreCase("coin") || lastQName.equalsIgnoreCase("stone")
								|| lastQName.equalsIgnoreCase("wood") || lastQName.equalsIgnoreCase("servant")
								|| lastQName.equalsIgnoreCase("militarypoint")
								|| lastQName.equalsIgnoreCase("faithpoint")
								|| lastQName.equalsIgnoreCase("councilprivilege")
								|| lastQName.equalsIgnoreCase("victorypoint")) {
							req.put(lastQName, Integer.parseInt(str));
						}
					}

					if (boolGainResource) {
						if (lastQName.equalsIgnoreCase("coin")) {
							gainTurnEffect.addGain("Coin", new Coin(Integer.parseInt(str)));
						}
						if (lastQName.equalsIgnoreCase("stone")) {
							gainTurnEffect.addGain("Stone", new Stone(Integer.parseInt(str)));
						}
						if (lastQName.equalsIgnoreCase("wood")) {
							gainTurnEffect.addGain("Wood", new Wood(Integer.parseInt(str)));
						}
						if (lastQName.equalsIgnoreCase("servant")) {
							gainTurnEffect.addGain("Servant", new Servant(Integer.parseInt(str)));
						}
						if (lastQName.equalsIgnoreCase("militarypoint")) {
							gainTurnEffect.addGain("MilitaryPoint", new MilitaryPoint(Integer.parseInt(str)));
						}
						if (lastQName.equalsIgnoreCase("faithpoint")) {
							gainTurnEffect.addGain("FaithPoint", new FaithPoint(Integer.parseInt(str)));
						}
						if (lastQName.equalsIgnoreCase("victorypoint")) {
							gainTurnEffect.addGain("VictoryPoint", new VictoryPoint(Integer.parseInt(str)));
						}
						if (lastQName.equalsIgnoreCase("councilprivilege")) {
							gainTurnEffect.addGain("CouncilPrivilege", new CouncilPrivilege(Integer.parseInt(str)));
						}
					}

					if (lastQName.equalsIgnoreCase("leadercopy")) {
						card.setCopy();
					}

					if (lastQName.equalsIgnoreCase("eachturnonefamdefvalue6")) {
						card.addPermanentEffect(new StrangeEffect("OneFamilyCol6"));
					}

					if (lastQName.equalsIgnoreCase("canplaceinocupatedspace")) {
						card.addPermanentEffect(new StrangeEffect("OccupiedSpace"));
					}

					if (lastQName.equalsIgnoreCase("nothreeadditivecoin")) {
						card.addPermanentEffect(new StrangeEffect("NoCostTower"));
					}

					if (lastQName.equalsIgnoreCase("neutralmaggioration3")) {
						card.addPermanentEffect(new StrangeEffect("Neutral+3"));
					}

					if (lastQName.equalsIgnoreCase("familiardefinitevalue5")) {
						card.addPermanentEffect(new StrangeEffect("AllFamilyCol5"));
					}

					if (lastQName.equalsIgnoreCase("familiarbonus2")) {
						card.addPermanentEffect(new StrangeEffect("FamilyCol+2"));
					}

					if (lastQName.equalsIgnoreCase("vaticansubstainvictorypointgain5")) {
						card.addPermanentEffect(new StrangeEffect("PointVicChurch+5"));
					}

					if (lastQName.equalsIgnoreCase("resourcebonusdouble")) {
						card.addPermanentEffect(new StrangeEffect("DoubleGain"));
					}

					if (lastQName.equalsIgnoreCase("nomilitarypointrequisiteterritory")) {
						card.addPermanentEffect(new StrangeEffect("NoTerritoryReq"));
					}

					if (lastQName.equalsIgnoreCase("developmentcardcoindiscount3")) {
						final int coinDiscount = 3;
						ReduceCostEffect reduce;
						reduce = new ReduceCostEffect("Venture");
						reduce.addBonus("Coin", new Coin(coinDiscount));
						card.addPermanentEffect(reduce);
						reduce = new ReduceCostEffect("Character");
						reduce.addBonus("Coin", new Coin(coinDiscount));
						card.addPermanentEffect(reduce);
						reduce = new ReduceCostEffect("Building");
						reduce.addBonus("Coin", new Coin(coinDiscount));
						card.addPermanentEffect(reduce);
					}

					if (lastQName.equalsIgnoreCase("eachturnharvestaction")) {
						card.addImmediateEffect(new ExtraAction(new HarvestAction(Integer.parseInt(str))));
					}

					if (lastQName.equalsIgnoreCase("eachturnprodaction")) {
						card.addImmediateEffect(new ExtraAction(new ProductionAction(Integer.parseInt(str))));
					}
				}

			};

			saxParser.parse(pathname, handler);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(LeaderCardSaxParser.class.getName());
			logger.info(e.getMessage());
		}
	}
}
