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

// Ottimizzazione possibile: una sola variabile per gestire i valori interi
/*
    <card>
        <name>BASE</name>													ok
        <era>0</era>														ok
        <cost>																ok
            <coin>0</coin>													ok
            <wood>0</wood>													ok
            <stone>0</stone>												ok
            <servant>0</servant>											ok
            <military>0</military>											ok
            <militaryrequirement>0</militaryrequirement>					ok
        </cost>																ok
        <gainEffect>														ok
            <coin>0</coin>													ok
            <wood>0</wood>													ok
            <stone>0</stone>												ok
            <servant>0</servant>											ok
            <military>0</military>											ok
            <faithpoint>0</faithpoint>										ok
            <councilpoint>0</councilpoint>									ok
        </gainEffect>														ok
        <immextraprod>0</immextraprod>
        <immextraharvest>0</immextraharvest>
        <immactionvalue>0</immactionvalue>
        <immactionvaleuassociatedcard>all</immactionvaleuassociatedcard>
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
				int lastInt = 0;
				int lastImmActionValue = 0;
				boolean boolName = false;
				boolean boolEra = false;
				boolean boolCost = false;
				boolean boolCoin = false;
				boolean boolStone = false;
				boolean boolWood = false;
				boolean boolServant = false;
				boolean boolMilitaryPoint = false;
				boolean boolMilitaryPointrequest = false;
				boolean boolCouncilPoint = false;
				boolean boolFaithPoint = false;
				boolean boolVictoryPoint = false;
				boolean boolImmHarvest = false;
				boolean boolImmProd = false;
				boolean boolImmActionValue = false;
				boolean boolAssCard = false;

				// ridefinizione del metodo startElement all'interno del
				// DefaultHandler
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					if (qName.equalsIgnoreCase("cost")) {
						boolCost = true;
					}

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

					if (qName.equalsIgnoreCase("militaryrequirement")) {
						boolMilitaryPointrequest = true;
					}

					if (qName.equalsIgnoreCase("victorypoint")) {
						boolVictoryPoint = true;
					}

					if (qName.equalsIgnoreCase("immextraprod")) {
						boolImmProd = true;
					}

					if (qName.equalsIgnoreCase("immextraharvest")) {
						boolImmHarvest = true;
					}

					if (qName.equalsIgnoreCase("immactionvalue")) {
						boolImmActionValue = true;
					}

					if (qName.equalsIgnoreCase("immactionvaleuassociatedcard")) {
						boolAssCard = true;
					}

				}

				// ridefinizione del metodo endElement all'interno del
				// DefaultHandler
				public void endElement(String uri, String localName, String qName) throws SAXException {

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
				}

				// ridefinizione del metodo characters all'interno del
				// DefaultHandler
				public void characters(char ch[], int start, int length) throws SAXException {

					// aggiunto nome alla carta
					if (boolName) {
						card.setName(new String(ch, start, length));
						boolName = false;
					}

					// aggiunta era alla carta
					if (boolEra) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						card.setEra(lastInt);
						boolEra = false;
					}

					// gestione monete
					if (boolCoin) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolCost) {
							cost.put("Coin", new Coin(lastInt));
						} else {
							gainEffect.addGain("Coin", new Coin(lastInt));
						}
						boolCoin = false;
					}

					// gestione pietre
					if (boolStone) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolCost) {
							cost.put("Stone", new Stone(lastInt));
						} else {
							gainEffect.addGain("Stone", new Stone(lastInt));
						}
						boolStone = false;
					}

					// gestione legni
					if (boolWood) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolCost) {
							cost.put("Wood", new Wood(lastInt));
						} else {
							gainEffect.addGain("Wood", new Wood(lastInt));
						}
						boolWood = false;
					}

					// gestione servitori
					if (boolServant) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolCost) {
							cost.put("Servant", new Servant(lastInt));
						} else {
							gainEffect.addGain("Servant", new Servant(lastInt));
						}
						boolServant = false;
					}

					// gestione punti militari
					if (boolMilitaryPoint) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						if (boolCost) {
							cost.put("MilitaryPoint", new MilitaryPoint(lastInt));
						} else {
							gainEffect.addGain("MilitaryPoint", new MilitaryPoint(lastInt));
						}
						boolMilitaryPoint = false;
					}

					// requisito punti militari (non serve il contr che sia un
					// cost, può essere solo quello)
					if (boolMilitaryPointrequest) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						requisite.put("MilitaryPoint", new MilitaryPoint(lastInt));
						boolMilitaryPointrequest = false;
					}

					// effetto immediato privilegio del consiglio
					if (boolCouncilPoint) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						gainEffect.addGain("CouncilPrivilege", new CouncilPrivilege(lastInt));
						boolCouncilPoint = false;
					}

					// effetto immediato punti fede
					if (boolFaithPoint) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						gainEffect.addGain("FaithPoint", new FaithPoint(lastInt));
						boolFaithPoint = false;
					}

					// effetto punti vittoria a fine partita
					if (boolVictoryPoint) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						card.addEndEffect(new EndVictoryEffect(lastInt));
						boolVictoryPoint = false;
					}

					// effetto nuova azione produzione aggiuntiva
					if (boolImmProd) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						card.addImmediateEffect(new ExtraAction(new ProductionAction(lastInt)));
						boolImmProd = false;
					}

					// effetto nuova azione raccolto aggiuntiva
					if (boolImmHarvest) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						card.addImmediateEffect(new ExtraAction(new HarvestAction(lastInt)));
						boolImmHarvest = false;
					}

					// effetto azione immediata
					if (boolImmActionValue) {
						lastImmActionValue = Integer.parseInt(new String(ch, start, length));
						boolImmActionValue = false;
					}

					// carta associata a azione immediata
					if (boolAssCard) {
						lastInt = Integer.parseInt(new String(ch, start, length));
						card.addImmediateEffect(new ExtraAction(new CardAction(lastInt)));
						// manca azione carta!
						boolAssCard = false;
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}