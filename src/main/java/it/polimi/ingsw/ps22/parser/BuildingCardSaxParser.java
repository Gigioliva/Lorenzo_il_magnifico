package it.polimi.ingsw.ps22.parser;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.card.CardBuilding;
import it.polimi.ingsw.ps22.effect.ExchangeResource;
import it.polimi.ingsw.ps22.effect.GainResource;
import it.polimi.ingsw.ps22.effect.MultiplyEffect;
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

	public static void BuildingCardParser(String pathname, ArrayList<CardBuilding> parsedData) {
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
					if (lastQName.equalsIgnoreCase("name"))
						card.setName(str);
					else {
						if (lastQName.equalsIgnoreCase("type"))
							multProdEffect.setMultiplier(str);
						else {
							lastInt = Integer.parseInt(str);

							if (lastQName.equalsIgnoreCase("era"))
								card.setEra(lastInt);

							if (lastQName.equalsIgnoreCase("prodreq"))
								card.setActionValue(lastInt);

							if (lastQName.equalsIgnoreCase("coin"))
								processElementResource(new Coin(lastInt));

							if (lastQName.equalsIgnoreCase("stone"))
								processElementResource(new Stone(lastInt));

							if (lastQName.equalsIgnoreCase("wood"))
								processElementResource(new Wood(lastInt));

							if (lastQName.equalsIgnoreCase("servant"))
								processElementResource(new Servant(lastInt));

							if (lastQName.equalsIgnoreCase("militarypoint"))
								processElementPoint(new MilitaryPoint(lastInt));

							if (lastQName.equalsIgnoreCase("faithpoint"))
								processElementPoint(new FaithPoint(lastInt));

							if (lastQName.equalsIgnoreCase("victorypoint"))
								processElementPoint(new VictoryPoint(lastInt));

							if (lastQName.equalsIgnoreCase("coucilpoint"))
								processElementCouncilPrivilege(lastInt);

						}
					}

				}

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

				private void processElementCouncilPrivilege(int value) {

					if (boolGainImm) {
						gainImmEffect.addGain("CouncilPoint", new CouncilPrivilege(value));
					} else {
						if (boolGainProd) {
							gainProdEffect.addGain("CouncilPoint", new CouncilPrivilege(value));
						} else {
							if (boolExchangeProd) {
								if (lastInt < 0) {
									exchangeProdEffect.addCost("CouncilPoint", new CouncilPrivilege(value));
								} else {
									exchangeProdEffect.addGain("CouncilPoint", new CouncilPrivilege(value));
								}
							} else {
								if (boolCardMoltProd) {
									multProdEffect.setMultiplicandType("CouncilPoint");
									multProdEffect.setMultiplicand(new CouncilPrivilege(value));
								}
							}
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