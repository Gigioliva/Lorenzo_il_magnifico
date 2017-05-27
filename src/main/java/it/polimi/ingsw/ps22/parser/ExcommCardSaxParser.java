/*
XML Structure:
<cards>   
    <card>
        <era></era>
        <militaryincrement>-1</militaryincrement>
        <coinincrement>-1</coinincrement>
        <servantincrement>-1</servantincrement>
        <woodincrement>-1</woodincrement>
        <stoneincrement>-1</stoneincrement>
        <harvestincrement>-3</harvestincrement>
        <productionincrement>-3<productionincrement>
        <diceincrement>-1</diceincrement>
        <territoryactionincrement>-4</territoryactionincrement>
        <characteractionincrement>-4</characteractionincrement>
        <buildingactionincrement>-4</buildingactionincrement>
        <ventureactionincrement>-4</ventureactionincrement>
        <nomarket>1</nomarket> 								//sta solo a rappresentare il true
        <servanthalfvalue>1</servanthalfvalue>				//sta solo a rappresentare il true
        <turnskip>1<turnskip>								//sta solo a rappresentare il true
        <endinvalidcharacter>1</endinvalidcharacter>		//sta solo a rappresentare il true
        <endinvalidventure>1</endinvalidventure>			//sta solo a rappresentare il true
        <endinvalidterritory>1</endinvalidterritory>		//sta solo a rappresentare il true
        <
    </card>    
</cards>
 */

package it.polimi.ingsw.ps22.parser;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import it.polimi.ingsw.ps22.resource.ResourceAbstract;


public class ExcommCardSaxParser {


	public static void BonusParser(String pathname, ArrayList<HashMap<String, ResourceAbstract>> parsedData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				int lastInt = 0;
				String lastQName = "";

				
				
				
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					lastQName = qName.toLowerCase();

				}

				
				
				
				public void endElement(String uri, String localName, String qName) throws SAXException {

					if (qName.equalsIgnoreCase("space")) {
						
					}
				}

				
				
				
				public void characters(char ch[], int start, int length) throws SAXException {

					lastInt = Integer.parseInt(new String(ch, start, length));

					if (lastQName.equalsIgnoreCase("coin")) {
						
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

					if (lastQName.equalsIgnoreCase("councilprivilege")) {
			
					}

				}
			};

			saxParser.parse(pathname, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
