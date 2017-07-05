package it.polimi.ingsw.ps22.server.parser;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.server.card.CardBuilding;
import it.polimi.ingsw.ps22.server.effect.ExchangeResource;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.MultiplyEffect;
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
		<prodreq>0</prodreq>
		<cost>
            <coin>0</coin>	
            <wood>0</wood>		
            <stone>0</stone>
            <servant>0</servant>
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

	/**
	 * This method parse a XML file to read the {@link ArrayList} of
	 * {@link CardBuilding}
	 * 
	 * @param pathname
	 *            is the path of the file to read to load the card
	 * @param parsedData
	 *            is the {@link ArrayList} of {@link CardBuilding} you want to
	 *            fill with XML data
	 */

	public static void BuildingRead(String pathname, ArrayList<CardBuilding> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardBuilding card = new CardBuilding();
				GainResource gainImmEffect = new GainResource();
				GainResource gainProdEffect = new GainResource();
				ExchangeResource exchangeProdEffect = new ExchangeResource();
				MultiplyEffect multProdEffect = new MultiplyEffect();
				String lastQName;
				int lastInt = 0;
				boolean boolCost = false;
				boolean boolGainImm = false;
				boolean boolGainProd = false;
				boolean boolExchangeProd = false;
				boolean boolCardMoltProd = false;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					lastQName = qName.toLowerCase();

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

				}

				public void endElement(String uri, String localName, String qName) throws SAXException {

					lastQName = "";

					if (qName.equalsIgnoreCase("cost")) {
						boolCost = false;
					}

					if (qName.equalsIgnoreCase("gaineffect")) {
						card.addImmediateEffect(gainImmEffect);
						gainImmEffect = new GainResource();
						boolGainImm = false;
					}

					if (qName.equalsIgnoreCase("gainprodeffect")) {
						card.addActionEffect(gainProdEffect);
						gainProdEffect = new GainResource();
						boolGainProd = false;
					}

					if (qName.equalsIgnoreCase("exchangeprodeffect")) {
						card.addActionEffect(exchangeProdEffect);
						exchangeProdEffect = new ExchangeResource();
						boolExchangeProd = false;
					}

					if (qName.equalsIgnoreCase("cardmoltprodeffect")) {
						card.addActionEffect(multProdEffect);
						multProdEffect = new MultiplyEffect();
						boolCardMoltProd = false;
					}

					if (qName.equalsIgnoreCase("card")) {
						parsedData.add(card);
						card = new CardBuilding();
					}

				}

				public void characters(char ch[], int start, int length) throws SAXException {
					String str = new String(ch, start, length);
					if (lastQName.equalsIgnoreCase("name")) {
						card.setName(str);
					}

					if (lastQName.equalsIgnoreCase("type")) {
						multProdEffect.setMultiplier(str);
					}

					if (lastQName.equalsIgnoreCase("era")) {
						lastInt = Integer.parseInt(str);
						card.setEra(lastInt);
					}

					if (lastQName.equalsIgnoreCase("prodreq")) {
						lastInt = Integer.parseInt(str);
						card.setActionValue(lastInt);
					}

					if (lastQName.equalsIgnoreCase("coin")) {
						lastInt = Integer.parseInt(str);
						processElementResource(new Coin(Math.abs(lastInt)));
					}

					if (lastQName.equalsIgnoreCase("stone")) {
						lastInt = Integer.parseInt(str);
						processElementResource(new Stone(Math.abs(lastInt)));
					}

					if (lastQName.equalsIgnoreCase("wood")) {
						lastInt = Integer.parseInt(str);
						processElementResource(new Wood(Math.abs(lastInt)));
					}

					if (lastQName.equalsIgnoreCase("servant")) {
						lastInt = Integer.parseInt(str);
						processElementResource(new Servant(Math.abs(lastInt)));
					}

					if (lastQName.equalsIgnoreCase("militarypoint")) {
						lastInt = Integer.parseInt(str);
						processElementPoint(new MilitaryPoint(Math.abs(lastInt)));
					}

					if (lastQName.equalsIgnoreCase("faithpoint")) {
						lastInt = Integer.parseInt(str);
						processElementPoint(new FaithPoint(Math.abs(lastInt)));
					}

					if (lastQName.equalsIgnoreCase("victorypoint")) {
						lastInt = Integer.parseInt(str);
						processElementPoint(new VictoryPoint(Math.abs(lastInt)));
					}

					if (lastQName.equalsIgnoreCase("councilpoint")) {
						lastInt = Integer.parseInt(str);
						processElementCouncilPrivilege(Math.abs(lastInt));
					}

				}

				/**
				 * 
				 * @param res
				 *            is a resource (Coin, Stone, Wood, Servant) to
				 *            process and add in the correct place in the card
				 *            (as a cost, as an immediate gain or as a
				 *            production effect)
				 */

				private void processElementResource(Resource res) {
					if (boolCost) {
						card.addCost(res.getName(), res);
					} else {
						if (boolGainImm) {
							gainImmEffect.addGain(res.getName(), res);
						} else {
							if (boolGainProd) {
								gainProdEffect.addGain(res.getName(), res);
							} else {
								if (boolExchangeProd) {
									if (lastInt < 0) {
										exchangeProdEffect.addCost(res.getName(), res);
									} else {
										exchangeProdEffect.addGain(res.getName(), res);
									}
								} else {
									if (boolCardMoltProd) {
										multProdEffect.setMultiplicandType(res.getName());
										multProdEffect.setMultiplicand(res);
									}
								}
							}
						}
					}
				}

				/**
				 * 
				 * @param point
				 *            is a generic point (FaithPoint, VictoryPoint,
				 *            MilitaryPoint) to process and add in the correct
				 *            place in the card (as a cost, as an immediate gain
				 *            or as a production effect)
				 */
				private void processElementPoint(Point point) {

					if (boolGainImm) {
						gainImmEffect.addGain(point.getName(), point);
					} else {
						if (boolGainProd) {
							gainProdEffect.addGain(point.getName(), point);
						} else {
							if (boolExchangeProd) {
								if (lastInt < 0) {
									exchangeProdEffect.addCost(point.getName(), point);
								} else {
									exchangeProdEffect.addGain(point.getName(), point);
								}
							} else {
								if (boolCardMoltProd) {
									multProdEffect.setMultiplicandType(point.getName());
									multProdEffect.setMultiplicand(point);
								}
							}
						}
					}
				}

				/**
				 * 
				 * @param value
				 *            is the number of CouncilPriviledge to add in the
				 *            correct place in the card (as a cost, as an
				 *            immediate gain or as a production effect)
				 */

				private void processElementCouncilPrivilege(int value) {

					if (boolGainImm) {
						gainImmEffect.addGain("CouncilPrivilege", new CouncilPrivilege(value));
					} else {
						if (boolGainProd) {
							gainProdEffect.addGain("CouncilPrivilege", new CouncilPrivilege(value));
						} else {
							if (boolExchangeProd) {
								if (lastInt < 0) {
									exchangeProdEffect.addCost("CouncilPrivilege", new CouncilPrivilege(value));
								} else {
									exchangeProdEffect.addGain("CouncilPrivilege", new CouncilPrivilege(value));
								}
							} else {
								if (boolCardMoltProd) {
									multProdEffect.setMultiplicandType("CouncilPrivilege");
									multProdEffect.setMultiplicand(new CouncilPrivilege(value));
								}
							}
						}
					}
				}

			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			Logger logger = Logger.getLogger(BuildingCardSaxParser.class.getName());
			logger.info(e.getMessage());
		}

	}

}