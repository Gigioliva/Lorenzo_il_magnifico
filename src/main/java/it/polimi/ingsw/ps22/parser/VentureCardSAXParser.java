
package it.polimi.ingsw.ps22.parser;

import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.card.CardVenture;

/*
	<card>
		<name>BASE</name>
		<era>0</era>
		<cost>
    		<coincost>0</coincost>
    		<woodcost>0</woodcost>
    		<stonecost>0</stonecost>
    		<servantcost>0</servantcost>
    		<militarycost>0</militarycost>
    		<militaryrequirement>0</militaryrequirement>
		</cost>	
		<immeffectstone>0</immeffectstone>
		<immeffectwood>0</immeffectwood>
		<immeffectcoin>0</immeffectcoin>
		<immeffectservant>0</immeffectservant>
		<immeffectfaith>0</immeffectfaith>
		<immeffectmilitary>0</immeffectmilitary>
		<immeffectcouncilprivilege>0</immeffectcouncilprivilege>
		<immextraprod>0</immextraprod>
		<immextraharvest>0</immextraharvest>
		<immactionvalue>0</immactionvalue>
		<immediateactionincrement>0</immediateactionincrement>
		<immactionvaleuassociatedcard>all</immactionvaleuassociatedcard>
		<victorypoint>0</victorypoint>
	</card>
*/

public class VentureCardSAXParser {

		public static void CardSAX(String pathname, ArrayList<CardVenture> parsedData) 
		{
			try {
			   SAXParserFactory factory = SAXParserFactory.newInstance();
			   SAXParser saxParser = factory.newSAXParser();
			   DefaultHandler handler = new DefaultHandler() {
				   StringBuffer lastName = new StringBuffer();
				   int lastEra=0;
				   
				   boolean boolName = false;
				   boolean boolEra = false;
				   
				   
				   public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {

					   System.out.println("Start Element :" + qName);

					   if (qName.equalsIgnoreCase("id")) {boolId = true;}
					   if (qName.equalsIgnoreCase("name")) {boolName = true;}
					   if (qName.equalsIgnoreCase("era")) {boolEra = true;}
				   		}

				   public void endElement(String uri, String localName, String qName) throws SAXException {
					   //System.out.println("End Element :" + qName);
					   if (qName.equalsIgnoreCase("card"))
					   		{/*CardVenture toAdd = new CardVenture(lastId,lastName,lastEra);
					   		parsedData.add(toAdd);*/
					   		lastEra=new ArrayList<Integer>();
					   		lastName=new StringBuffer();}
				   		}

				   public void characters(char ch[], int start, int length) throws SAXException {
					   if (boolId) 
					   		{lastId= Integer.parseInt(new String(ch, start, length));
					   		boolId = false;}
					   if (boolName) 
					   		{lastName.append(new String(ch, start, length));
					   		boolName = false;}
					   if (boolEra)
					   		{lastEra.add(Integer.parseInt(new String(ch, start, length)));
					   		boolEra = false;}
				   		}
			   		};
			   		
	    saxParser.parse(pathname, handler);

	  } catch (Exception e) 
		   	{e.printStackTrace();}

	
	}
	
}
