package it.polimi.ingsw.ps22.parser;

/*

XML Structure:
<spaces>   
    <space>
        <Coin>1</Coin>
        <Stone>1</Stone>
        <Wood>1</Wood>
        <Servant>1</Servant>
        <MilitaryPoint>1</MilitaryPoint>
        <FaithPoint>1</FaithPoint>
        <VictoryPoint>1</VictoryPoint>
        <CouncilPrivilege>1</CouncilPrivilege>
    </space>    
</spaces>

 */
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.resource.FaithPoint;
import it.polimi.ingsw.ps22.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.resource.Servant;
import it.polimi.ingsw.ps22.resource.Stone;
import it.polimi.ingsw.ps22.resource.VictoryPoint;

public class ZoneBonusSaxParser {

	public static void BonusParser(String pathname, ArrayList<HashMap<String, ResourceAbstract>> parsedData) 
	{
		try {
		   SAXParserFactory factory = SAXParserFactory.newInstance();
		   SAXParser saxParser = factory.newSAXParser();
		   DefaultHandler handler = new DefaultHandler() {
			   HashMap<String, ResourceAbstract> toAdd = new HashMap<String, ResourceAbstract>();
			   int lastInt=0;
			   /*int lastCoin = 0; 
			   int lastStone = 0; 
			   int lastWood = 0; 
			   int lastServant = 0; 
			   int lastMilitaryPoint = 0; 
			   int lastFaithPoint = 0; 
			   int lastVictoryPoint = 0;
			   int lastCouncilPrivilege = 0;*/
			   boolean boolCoin = false;
			   boolean boolStone = false;
			   boolean boolWood = false; 
			   boolean boolServant = false;
			   boolean boolMilitaryPoint = false;
			   boolean boolFaithPoint = false;
			   boolean boolVictoryPoint = false;
			   boolean boolCouncilPrivilege = false;
			   
			   
			   public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
				   //System.out.println("Start Element :" + qName);
				   if (qName.equalsIgnoreCase("Coin")) {boolCoin = true;}
				   if (qName.equalsIgnoreCase("Stone")) {boolStone = true;}
				   if (qName.equalsIgnoreCase("Wood")) {boolWood = true;}
				   if (qName.equalsIgnoreCase("Servant")) {boolServant = true;}
				   if (qName.equalsIgnoreCase("MilitaryPoint")) {boolMilitaryPoint = true;}
				   if (qName.equalsIgnoreCase("FaithPoint")) {boolFaithPoint = true;}
				   if (qName.equalsIgnoreCase("VictoryPoint")) {boolVictoryPoint = true;} 
				   if (qName.equalsIgnoreCase("CouncilPrivilege")) {boolCouncilPrivilege = true;}
				   if (qName.equalsIgnoreCase("space")) {
					   toAdd = new HashMap<String, ResourceAbstract>();
				   }
				  
			   }

			   public void endElement(String uri, String localName, String qName) throws SAXException {
				   //System.out.println("End Element :" + qName);
				   if (qName.equalsIgnoreCase("space"))
				   		{
					   	parsedData.add(toAdd);
				   		//lastEra=new ArrayList<Integer>();
				   		//lastName=new StringBuffer();
				   		}
			   		}

			   public void characters(char ch[], int start, int length) throws SAXException {
				   	/*if (boolCoin) 
			   			{lastCoin= Integer.parseInt(new String(ch, start, length));
			   			toAdd.put("Coin", new Coin(lastCoin));
			   			boolCoin = false;}
				   
				   	if (boolStone) 
			   			{lastStone= Integer.parseInt(new String(ch, start, length));
			   			toAdd.put("Stone", new Stone(lastStone));
			   			boolStone = false;}
				   
				   	if (boolWood) 
			   			{lastWood= Integer.parseInt(new String(ch, start, length));
			   			toAdd.put("Wood", new Coin(lastWood));
			   			boolWood = false;}
				   
				   	if (boolServant) 
			   			{lastServant= Integer.parseInt(new String(ch, start, length));
			   			toAdd.put("Servant", new Servant(lastServant));
			   			boolServant = false;}
				   
				   	if (boolMilitaryPoint) 
				   		{lastMilitaryPoint= Integer.parseInt(new String(ch, start, length));
				   		toAdd.put("MilitaryPoint", new MilitaryPoint(lastMilitaryPoint));
				   		boolMilitaryPoint = false;}
				   
			   		if (boolFaithPoint) 
			   			{lastFaithPoint= Integer.parseInt(new String(ch, start, length));
			   			toAdd.put("FaithPoint", new FaithPoint(lastFaithPoint));
			   			boolFaithPoint = false;}
			   		
			   		if (boolVictoryPoint) 
		   				{lastVictoryPoint= Integer.parseInt(new String(ch, start, length));
		   				toAdd.put("VictoryPoint", new VictoryPoint(lastVictoryPoint));
		   				boolVictoryPoint = false;}
			   		
			   		if (boolCouncilPrivilege) 
		   				{lastCouncilPrivilege= Integer.parseInt(new String(ch, start, length));
		   				toAdd.put("CouncilPrivilege", new CouncilPrivilege(lastCouncilPrivilege));
		   				boolCouncilPrivilege = false;}
				   */
				   
				   if (boolCoin) 
		   			{lastInt= Integer.parseInt(new String(ch, start, length));
		   			toAdd.put("Coin", new Coin(lastInt));
		   			boolCoin = false;}
			   
			   	if (boolStone) 
		   			{lastInt= Integer.parseInt(new String(ch, start, length));
		   			toAdd.put("Stone", new Stone(lastInt));
		   			boolStone = false;}
			   
			   	if (boolWood) 
		   			{lastInt= Integer.parseInt(new String(ch, start, length));
		   			toAdd.put("Wood", new Coin(lastInt));
		   			boolWood = false;}
			   
			   	if (boolServant) 
		   			{lastInt= Integer.parseInt(new String(ch, start, length));
		   			toAdd.put("Servant", new Servant(lastInt));
		   			boolServant = false;}
			   
			   	if (boolMilitaryPoint) 
			   		{lastInt= Integer.parseInt(new String(ch, start, length));
			   		toAdd.put("MilitaryPoint", new MilitaryPoint(lastInt));
			   		boolMilitaryPoint = false;}
			   
		   		if (boolFaithPoint) 
		   			{lastInt= Integer.parseInt(new String(ch, start, length));
		   			toAdd.put("FaithPoint", new FaithPoint(lastInt));
		   			boolFaithPoint = false;}
		   		
		   		if (boolVictoryPoint) 
	   				{lastInt= Integer.parseInt(new String(ch, start, length));
	   				toAdd.put("VictoryPoint", new VictoryPoint(lastInt));
	   				boolVictoryPoint = false;}
		   		
		   		if (boolCouncilPrivilege) 
	   				{lastInt= Integer.parseInt(new String(ch, start, length));
	   				toAdd.put("CouncilPrivilege", new CouncilPrivilege(lastInt));
	   				boolCouncilPrivilege = false;}
				   
			   		}
		   		};

		   	saxParser.parse(pathname, handler);

	} catch (Exception e) 
	   	{e.printStackTrace();}

	}
	
}