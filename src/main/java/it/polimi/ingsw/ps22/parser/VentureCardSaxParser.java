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
import it.polimi.ingsw.ps22.card.CardVenture;
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

public class VentureCardSaxParser {

	public static void VentureCardParser(String pathname, ArrayList<CardVenture> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardVenture card = new CardVenture();
				HashMap<String, ResourceAbstract> cost = new HashMap<String, ResourceAbstract>();
				HashMap<String, ResourceAbstract> requisite = new HashMap<String, ResourceAbstract>();
				GainResource gainEffect = new GainResource();
				CardAction cardAct;
				String lastQName = "";
				int lastInt = 0;
				boolean boolCost = false;

				// ridefinizione del metodo startElement all'interno del
				// DefaultHandler
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					lastQName = qName;

					if (qName.equalsIgnoreCase("cost")) {
						boolCost = true;
					}

				}
				

				// ridefinizione del metodo endElement all'interno del
				// DefaultHandler
				public void endElement(String uri, String localName, String qName) throws SAXException {

					lastQName = "";
					
					if (qName.equalsIgnoreCase("cost")) {
						card.addRequisiteCost(cost, requisite);
						cost = new HashMap<String, ResourceAbstract>();
						requisite = new HashMap<String, ResourceAbstract>();
						boolCost = false;
					}

					if (qName.equalsIgnoreCase("gaineffect")) {
						card.addImmediateEffect(gainEffect);
						gainEffect = new GainResource();
					}

					if (qName.equalsIgnoreCase("card")) {
						parsedData.add(card);
						card = new CardVenture();
					}

					// riconoscitore tipo carta associata a azione immediata
					if (qName.equalsIgnoreCase("immactionvaleuassociatedcard")) {
						// attenzione a scrivere correttamente i tipi con le
						// maiuscole
						card.addImmediateEffect(new ExtraAction(cardAct));

					}
				}

				// ridefinizione del metodo characters all'interno del
				// DefaultHandler
				public void characters(char ch[], int start, int length) throws SAXException {

					String str = new String(ch, start, length);

					// aggiunto nome alla carta
					if (lastQName.equalsIgnoreCase("name")) {
						card.setName(str);
					} else {
						// aggiunge i tipi
						if (lastQName.equalsIgnoreCase("type")) {
							cardAct.addType(str);
						} else {
							lastInt = Integer.parseInt(str);

							// aggiunta era alla carta
							if (lastQName.equalsIgnoreCase("era")) {
								card.setEra(lastInt);
							}

							// gestione monete
							if (lastQName.equalsIgnoreCase("coin")) {
								if (boolCost) {
									cost.put("Coin", new Coin(lastInt));
								} else {
									gainEffect.addGain("Coin", new Coin(lastInt));
								}
							}

							// gestione pietre
							if (lastQName.equalsIgnoreCase("stone")) {
								if (boolCost) {
									cost.put("Stone", new Stone(lastInt));
								} else {
									gainEffect.addGain("Stone", new Stone(lastInt));
								}
							}

							// gestione legni
							if (lastQName.equalsIgnoreCase("wood")) {
								if (boolCost) {
									cost.put("Wood", new Wood(lastInt));
								} else {
									gainEffect.addGain("Wood", new Wood(lastInt));
								}
							}

							// gestione servitori
							if (lastQName.equalsIgnoreCase("servant")) {
								if (boolCost) {
									cost.put("Servant", new Servant(lastInt));
								} else {
									gainEffect.addGain("Servant", new Servant(lastInt));
								}
							}

							// gestione punti militari
							if (lastQName.equalsIgnoreCase("militarypoint")) {
								if (boolCost) {
									cost.put("MilitaryPoint", new MilitaryPoint(lastInt));
								} else {
									gainEffect.addGain("MilitaryPoint", new MilitaryPoint(lastInt));
								}
							}

							// requisito punti militari (non serve il contr che
							// sia un
							// cost, può essere solo quello)
							if (lastQName.equalsIgnoreCase("militaryrequirement")) {
								requisite.put("MilitaryPoint", new MilitaryPoint(lastInt));
							}

							// effetto immediato privilegio del consiglio
							if (lastQName.equalsIgnoreCase("councilpoint")) {
								gainEffect.addGain("CouncilPrivilege", new CouncilPrivilege(lastInt));
							}

							// effetto immediato punti fede
							if (lastQName.equalsIgnoreCase("faithpoint")) {
								gainEffect.addGain("FaithPoint", new FaithPoint(lastInt));
							}

							// effetto punti vittoria a fine partita
							if (lastQName.equalsIgnoreCase("victorypoint")) {
								card.addEndEffect(new EndVictoryEffect(lastInt));
							}

							// effetto nuova azione produzione aggiuntiva
							if (lastQName.equalsIgnoreCase("immextraprod")) {
								card.addImmediateEffect(new ExtraAction(new ProductionAction(lastInt)));

							}

							// effetto nuova azione raccolto aggiuntiva
							if (lastQName.equalsIgnoreCase("immextraharvest")) {
								card.addImmediateEffect(new ExtraAction(new HarvestAction(lastInt)));
							}

							if (lastQName.equalsIgnoreCase("immactionvalue")) {
								cardAct = new CardAction(lastInt);
							}
						}
					}

					/*
					 * // aggiunto nome alla carta if (boolName) {
					 * card.setName(new String(ch, start, length)); boolName =
					 * false; }
					 * 
					 * // aggiunta era alla carta if (boolEra) { lastInt =
					 * Integer.parseInt(new String(ch, start, length));
					 * card.setEra(lastInt); boolEra = false; }
					 * 
					 * // gestione monete if (boolCoin) { lastInt =
					 * Integer.parseInt(new String(ch, start, length)); if
					 * (boolCost) { cost.put("Coin", new Coin(lastInt)); } else
					 * { gainEffect.addGain("Coin", new Coin(lastInt)); }
					 * boolCoin = false; }
					 * 
					 * // gestione pietre if (boolStone) { lastInt =
					 * Integer.parseInt(new String(ch, start, length)); if
					 * (boolCost) { cost.put("Stone", new Stone(lastInt)); }
					 * else { gainEffect.addGain("Stone", new Stone(lastInt)); }
					 * boolStone = false; }
					 * 
					 * // gestione legni if (boolWood) { lastInt =
					 * Integer.parseInt(new String(ch, start, length)); if
					 * (boolCost) { cost.put("Wood", new Wood(lastInt)); } else
					 * { gainEffect.addGain("Wood", new Wood(lastInt)); }
					 * boolWood = false; }
					 * 
					 * // gestione servitori if (boolServant) { lastInt =
					 * Integer.parseInt(new String(ch, start, length)); if
					 * (boolCost) { cost.put("Servant", new Servant(lastInt)); }
					 * else { gainEffect.addGain("Servant", new
					 * Servant(lastInt)); } boolServant = false; }
					 * 
					 * // gestione punti militari if (boolMilitaryPoint) {
					 * lastInt = Integer.parseInt(new String(ch, start,
					 * length)); if (boolCost) { cost.put("MilitaryPoint", new
					 * MilitaryPoint(lastInt)); } else {
					 * gainEffect.addGain("MilitaryPoint", new
					 * MilitaryPoint(lastInt)); } boolMilitaryPoint = false; }
					 * 
					 * // requisito punti militari (non serve il contr che sia
					 * un // cost, può essere solo quello) if
					 * (boolMilitaryPointrequest) { lastInt =
					 * Integer.parseInt(new String(ch, start, length));
					 * requisite.put("MilitaryPoint", new
					 * MilitaryPoint(lastInt)); boolMilitaryPointrequest =
					 * false; }
					 * 
					 * // effetto immediato privilegio del consiglio if
					 * (boolCouncilPoint) { lastInt = Integer.parseInt(new
					 * String(ch, start, length));
					 * gainEffect.addGain("CouncilPrivilege", new
					 * CouncilPrivilege(lastInt)); boolCouncilPoint = false; }
					 * 
					 * // effetto immediato punti fede if (boolFaithPoint) {
					 * lastInt = Integer.parseInt(new String(ch, start,
					 * length)); gainEffect.addGain("FaithPoint", new
					 * FaithPoint(lastInt)); boolFaithPoint = false; }
					 * 
					 * // effetto punti vittoria a fine partita if
					 * (boolVictoryPoint) { lastInt = Integer.parseInt(new
					 * String(ch, start, length)); card.addEndEffect(new
					 * EndVictoryEffect(lastInt)); boolVictoryPoint = false; }
					 * 
					 * // effetto nuova azione produzione aggiuntiva if
					 * (boolImmProd) { lastInt = Integer.parseInt(new String(ch,
					 * start, length)); card.addImmediateEffect(new
					 * ExtraAction(new ProductionAction(lastInt))); boolImmProd
					 * = false; }
					 * 
					 * // effetto nuova azione raccolto aggiuntiva if
					 * (boolImmHarvest) { lastInt = Integer.parseInt(new
					 * String(ch, start, length)); card.addImmediateEffect(new
					 * ExtraAction(new HarvestAction(lastInt))); boolImmHarvest
					 * = false; }
					 * 
					 * // effetto azione immediata if (boolImmActionValue) {
					 * lastInt = Integer.parseInt(new String(ch, start,
					 * length)); cardAct = new CardAction(lastInt);
					 * boolImmActionValue = false; }
					 * 
					 * // aggiunge i tipi if (boolType) { String str = new
					 * String(ch, start, length); cardAct.addType(str); boolType
					 * = false; }
					 */
				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}