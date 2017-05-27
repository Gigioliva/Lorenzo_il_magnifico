package it.polimi.ingsw.ps22.parser;

import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.action.CardAction;
import it.polimi.ingsw.ps22.action.HarvestAction;
import it.polimi.ingsw.ps22.action.ProductionAction;
import it.polimi.ingsw.ps22.card.CardCharacter;
import it.polimi.ingsw.ps22.effect.BonusEffect;

import it.polimi.ingsw.ps22.effect.ExtraAction;
import it.polimi.ingsw.ps22.effect.GainResource;
import it.polimi.ingsw.ps22.effect.MultiplyEffect;
import it.polimi.ingsw.ps22.effect.ReduceCostEffect;
import it.polimi.ingsw.ps22.effect.StrangeEffect;

import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.resource.FaithPoint;
import it.polimi.ingsw.ps22.resource.IncrementBuilding;
import it.polimi.ingsw.ps22.resource.IncrementCharacter;
import it.polimi.ingsw.ps22.resource.IncrementHarvest;
import it.polimi.ingsw.ps22.resource.IncrementProduction;
import it.polimi.ingsw.ps22.resource.IncrementTerritory;
import it.polimi.ingsw.ps22.resource.IncrementVenture;
import it.polimi.ingsw.ps22.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.resource.Servant;
import it.polimi.ingsw.ps22.resource.Stone;
import it.polimi.ingsw.ps22.resource.VictoryPoint;
import it.polimi.ingsw.ps22.resource.Wood;

/*
<card>											
    <name>BASE</name>							
    <era>0</era>									
    <coincost></coincost>															
    <istgain>									
        <coin>0</coin>									
        <wood>0</wood>						
        <stone>0</stone>								
        <servant>0</servant>					
        <militarypoint>0</militarypoint>
        <faithpoint>0</faithpoint>				
        <councilpoint>0</councilpoint>			
    </istgain>									
    <immextraprod>0</immextraprod>						
    <immextraharvest>0</immextraharvest>		
    <immextraaction>							
        <value></value>						
        <type></type>					
        <coin>0</coin>							
        <wood>0</wood>					
        <stone>0</stone>			
        <servant>0</servant>		
    </immextraaction>				
    <moltiplication>						
        <factor></factor>
        <factor1>
            <coin>0</coin>								
            <wood>0</wood>									
            <stone>0</stone>								
            <servant>0</servant>			
            <militarypoint>0</militarypoint>				
            <faithpoint>0</faithpoint>			
            <councilpoint>0</councilpoint>
            <victorypoint>0</victorypoint>
        </factor1>
        <factor2>
            <cardtype></cardtype>	// dovrà essere scritto come Territory, la maiuscola è importante
            <factorcard>0</factorcard>
            <coin>0</coin>								
            <wood>0</wood>									
            <stone>0</stone>								
            <servant>0</servant>			
            <militarypoint>0</militarypoint>				
            <faithpoint>0</faithpoint>			
            <councilpoint>0</councilpoint>
        </factor2>       
    </moltiplication>						
    <harvestincrement></harvestincrement>		
    <productionincrement></productionincrement>	
    <notowerbonus></notowerbonus>   
    <placementincrement>
        <value>0</value>
        <type></type>				// dovrà essere scritto come Territory, la maiuscola è importante
        <reduce>
        	<coin>0</coin>								
        	<wood>0</wood>									
        	<stone>0</stone>
        </reduce>								
    </placementincrement>
</card>											

*/

public class CharacterCardSaxParser {

	public static void CharacterCardParser(String pathname, ArrayList<CardCharacter> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				CardCharacter card = new CardCharacter();
				GainResource gainImm = new GainResource();
				MultiplyEffect mult = new MultiplyEffect();
				BonusEffect bonus = new BonusEffect();
				ReduceCostEffect reduce;
				CardAction cardAct;
				String lastQName = "";
				String stringType = "";
				//int lastMult = 0;
				int lastIncrCardValue = 0;
				boolean boolGainIstEffect = false;
				//boolean boolEffect = false;
				boolean boolImmExtraAction = false;
				boolean boolMult = false;
				boolean boolFactor1 = false;
				boolean boolPlaceIncr = false;
				
				
				// ridefinizione del metodo startElement all'interno del
				// DefaultHandler
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					lastQName = qName;
					
					if(qName.equals("istgain")) 
						boolGainIstEffect = true;
					
					if(qName.equals("immextraaction")) 
						boolImmExtraAction = true;

					if(qName.equals("moltiplication")) 
						boolMult=true;
					
					if(qName.equals("factor1")) 
						boolFactor1=true;
					
					if(qName.equals("placementincrement")) 
						boolPlaceIncr=true;
					
				}

				
				
				// ridefinizione del metodo endElement all'interno del
				// DefaultHandler
				public void endElement(String uri, String localName, String qName) throws SAXException {

					if(qName.equals("card")) {
						card.addPermanentEffect(bonus);
						parsedData.add(card);
						bonus = new BonusEffect();
						card= new CardCharacter();
					}
					
					if(qName.equals("istgain")) {
						boolGainIstEffect = false;
						card.addImmediateEffect(gainImm);
						gainImm = new GainResource();
					}
					
					if(qName.equals("immextraaction")) {
						boolImmExtraAction = false;
						card.addImmediateEffect(new ExtraAction(cardAct));
					}
					
					if(qName.equals("moltiplication")) {
						boolMult=false;
						card.addImmediateEffect(mult);
						mult = new MultiplyEffect();
					}
					
					if(qName.equals("factor1")) 
						boolFactor1 = false;
					
					if(qName.equals("placementincrement")) {
						boolPlaceIncr = false;
					}
					
					if(qName.equals("reduce")) {
						card.addPermanentEffect(reduce);
					}

					
				}

				
				
				// ridefinizione del metodo characters all'interno del
				// DefaultHandler
				public void characters(char ch[], int start, int length) throws SAXException {

					String str = new String(ch, start, length);

					// aggiunto nome alla carta
					if (lastQName.equalsIgnoreCase("name"))
						card.setName(str);

					
					if (lastQName.equalsIgnoreCase("era"))
						card.setEra(Integer.parseInt(str));
					
					
					if (lastQName.equalsIgnoreCase("coincost"))
						card.addCost(new Coin(Integer.parseInt(str)));
					
					
					if(boolGainIstEffect) {
						if(lastQName.equalsIgnoreCase("coin"))
							gainImm.addGain("Coin", new Coin(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("stone"))
							gainImm.addGain("Stone", new Stone(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("wood"))
							gainImm.addGain("Wood", new Wood(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("servant"))
							gainImm.addGain("Servant", new Servant(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("militarypoint"))
							gainImm.addGain("MilitaryPoint", new MilitaryPoint(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("faithpoint"))
							gainImm.addGain("FaithPoint", new FaithPoint(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("councilpoint"))
							gainImm.addGain("CouncilPrivilege", new CouncilPrivilege(Integer.parseInt(str)));
					}
					
					
					if (lastQName.equalsIgnoreCase("immextraprod")) 
						card.addImmediateEffect(new ExtraAction(new ProductionAction(Integer.parseInt(str))));
					
					
					if (lastQName.equalsIgnoreCase("immextraharvest")) 
						card.addImmediateEffect(new ExtraAction(new HarvestAction(Integer.parseInt(str))));
					
					
					if (boolImmExtraAction){
						if (lastQName.equalsIgnoreCase("value")) 
							cardAct = new CardAction(Integer.parseInt(str));
						if (lastQName.equalsIgnoreCase("type"))
							cardAct.addType(str);
						if(lastQName.equalsIgnoreCase("coin"))
							cardAct.addDiscount("Coin", new Coin(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("stone"))
							cardAct.addDiscount("Stone", new Stone(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("wood"))
							cardAct.addDiscount("Wood", new Wood(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("servant"))
							cardAct.addDiscount("Servant", new Servant(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("militarypoint"))
							gainImm.addGain("MilitaryPoint", new MilitaryPoint(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("faithpoint"))
							gainImm.addGain("FaithPoint", new FaithPoint(Integer.parseInt(str)));
						if(lastQName.equalsIgnoreCase("councilpoint"))
							gainImm.addGain("CouncilPrivilege", new CouncilPrivilege(Integer.parseInt(str)));
					}
					
					
					if(boolMult) {
						if(lastQName.equalsIgnoreCase("cardtype")) 
							mult.setMultiplier(str);
						
						if(lastQName.equalsIgnoreCase("cardtype")) 
							mult.setMultiplierQty(Integer.parseInt(str));
						
						if(lastQName.equalsIgnoreCase("coin")) {
							if (boolFactor1) { 
								mult.setMultiplicand(new Coin(Integer.parseInt(str)));
								mult.setMultiplicandType("Coin");
							}
							else 
								{ mult.setMultiplier("Coin");
								mult.setMultiplierQty(Integer.parseInt(str));
								}
						}
						
						if(lastQName.equalsIgnoreCase("stone")) {
							if (boolFactor1) { 
								mult.setMultiplicand(new Stone(Integer.parseInt(str)));
								mult.setMultiplicandType("Stone");
							}
							else 
								{ mult.setMultiplier("Stone");
								mult.setMultiplierQty(Integer.parseInt(str));
								}
						}
						
						if(lastQName.equalsIgnoreCase("wood")) {
							if (boolFactor1) { 
								mult.setMultiplicand(new Wood(Integer.parseInt(str)));
								mult.setMultiplicandType("Wood");
							}
							else 
								{ mult.setMultiplier("Wood");
								mult.setMultiplierQty(Integer.parseInt(str));
								}
						}
						
						if(lastQName.equalsIgnoreCase("servant")) {
							if (boolFactor1) { 
								mult.setMultiplicand(new Servant(Integer.parseInt(str)));
								mult.setMultiplicandType("Servant");
							}
							else 
								{ mult.setMultiplier("Servant");
								mult.setMultiplierQty(Integer.parseInt(str));
								}
						}
						
						if(lastQName.equalsIgnoreCase("militarypoint")) {
							if (boolFactor1) { 
								mult.setMultiplicand(new MilitaryPoint(Integer.parseInt(str)));
								mult.setMultiplicandType("MilitaryPoint");
							}
							else 
								{ mult.setMultiplier("MilitaryPoint");
								mult.setMultiplierQty(Integer.parseInt(str));
								}
								
						}
						
						if(lastQName.equalsIgnoreCase("councilpoint")) {
							if (boolFactor1) { 
								mult.setMultiplicand(new CouncilPrivilege(Integer.parseInt(str)));
								mult.setMultiplicandType("CouncilPrivilege");
							}
							else 
								{ mult.setMultiplier("CouncilPrivilege");
								mult.setMultiplierQty(Integer.parseInt(str));
								}
						}
						
						if(lastQName.equalsIgnoreCase("faithpoint")) {
							if (boolFactor1) { 
								mult.setMultiplicand(new FaithPoint(Integer.parseInt(str)));
								mult.setMultiplicandType("FaithPoint");
							}
							else 
								{ mult.setMultiplier("FaithPoint");
								mult.setMultiplierQty(Integer.parseInt(str));
								}
						}
						
						if(lastQName.equalsIgnoreCase("victorypoint")) {
							mult.setMultiplicand(new VictoryPoint(Integer.parseInt(str)));
							mult.setMultiplicandType("VictoryPoint");
						}
						
						/*if(lastQName.equalsIgnoreCase("cardtype")) {
								if(str.equalsIgnoreCase("cardventure")) {
									//mult.setMultiplicand();
									mult.setMultiplicandType("FaithPoint");
								}
						}*/
						
					}
					
					if(lastQName.equalsIgnoreCase("harvestincrement")) {
						bonus.addBonus("IncrementHarvest", new IncrementHarvest(Integer.parseInt(str)));	
					}
						
					if(lastQName.equalsIgnoreCase("productionincrement")) {
						bonus.addBonus("IncrementProduction", new IncrementProduction(Integer.parseInt(str)));	
					}
					
					if(lastQName.equalsIgnoreCase("notowerbonus")) {
						card.addPermanentEffect(new StrangeEffect("GainTower"));
					}
						
					
					if(boolPlaceIncr) {
						
						if(lastQName.equalsIgnoreCase("value")) 
							lastIncrCardValue = Integer.parseInt(str);
						
						if(lastQName.equalsIgnoreCase("type")) {
							stringType=str;
							if(str.equalsIgnoreCase("territory"))
								bonus.addBonus("IncrementTerritory", new IncrementTerritory(lastIncrCardValue));
							
							if(str.equalsIgnoreCase("character"))
								bonus.addBonus("IncrementCharacter", new IncrementCharacter(lastIncrCardValue));
							
							if(str.equalsIgnoreCase("building"))
								bonus.addBonus("IncrementBuilding", new IncrementBuilding(lastIncrCardValue));
							
							if(str.equalsIgnoreCase("venture"))
								bonus.addBonus("IncrementVenture", new IncrementVenture(lastIncrCardValue));
						}
						
						if(lastQName.equalsIgnoreCase("reduce"))
							reduce = new ReduceCostEffect(stringType);
						
						if(lastQName.equalsIgnoreCase("coin"))
							reduce.addBonus("Coin", new Coin(Integer.parseInt(str)));
						
						if(lastQName.equalsIgnoreCase("stone"))
							reduce.addBonus("Stone", new Stone(Integer.parseInt(str)));
						
						if(lastQName.equalsIgnoreCase("wood"))
							reduce.addBonus("Wood", new Wood(Integer.parseInt(str)));						
					}	
							
					
						
				}

			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
