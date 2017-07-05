package it.polimi.ingsw.ps22.server.parser;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.polimi.ingsw.ps22.server.view.UserData;

/* 
XML Structure

	<userdata>
   	<user>
       	<username></username>
       	<password></password>
       	<numvict></numvict>
       	<numgame></numgame>
   	</user>
	</userdata>
	
*/

public class OutputPlayerDataDomParser {

	/**
	 * This method write on persistent memory the data of the player with their
	 * statistics
	 * 
	 * @param pathname
	 *            is the pathname of the file in which you want to write
	 * @param toWrite
	 *            is the {@link HashMap} which contains the usersData to write
	 *            on XML file
	 */
	public static void PlayerDataWrite(String pathname, HashMap<String, UserData> toWrite) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			String str;
			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("userdata");
			doc.appendChild(rootElement);

			for (String userNm : toWrite.keySet()) {

				Element user = doc.createElement("user");
				rootElement.appendChild(user);

				Element username = doc.createElement("username");
				username.appendChild(doc.createTextNode(userNm));
				user.appendChild(username);

				Element password = doc.createElement("password");
				password.appendChild(doc.createTextNode(toWrite.get(userNm).getPassword()));
				user.appendChild(password);

				Element numvict = doc.createElement("numvict");
				str = Integer.toString(toWrite.get(userNm).getNumVictory());
				numvict.appendChild(doc.createTextNode(str));
				user.appendChild(numvict);

				Element numgame = doc.createElement("numgame");
				str = Integer.toString(toWrite.get(userNm).getNumPlayedGame());
				numgame.appendChild(doc.createTextNode(str));
				user.appendChild(numgame);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(pathname));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("Player data saved on Server!");

		} catch (ParserConfigurationException | TransformerException pce) {
			Logger logger = Logger.getLogger(OutputPlayerDataDomParser.class.getName());
			logger.info(pce.getMessage());
		}
	}
}
