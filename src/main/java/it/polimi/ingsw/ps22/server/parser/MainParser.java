package it.polimi.ingsw.ps22.server.parser;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import it.polimi.ingsw.ps22.client.gui.CardPath;
import it.polimi.ingsw.ps22.server.board.FaithPointTrack;
import it.polimi.ingsw.ps22.server.card.CardBuilding;
import it.polimi.ingsw.ps22.server.card.CardCharacter;
import it.polimi.ingsw.ps22.server.card.CardExcomm;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.player.PersonalBoard;
import it.polimi.ingsw.ps22.server.player.UserData;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class MainParser {

	public static void mainParser(String[] args) {
		
		ArrayList<CardBuilding> building = new ArrayList<CardBuilding>();
		String pathnameBuilding = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Building.xml";
		BuildingCardSaxParser.BuildingRead(pathnameBuilding, building);
		System.out.println("CARD BUILDING");
		for (CardBuilding card : building)
			System.out.println("\n" + card.toString());

		ArrayList<CardTerritory> territory = new ArrayList<CardTerritory>();
		String pathnameTerritory = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Territory.xml";
		TerritoryCardSaxParser.TerritoryRead(pathnameTerritory, territory);
		System.out.println("\n\n\nCARD TERRITORY");
		for (CardTerritory card : territory)
			System.out.println("\n" + card.toString());

		ArrayList<CardVenture> venture = new ArrayList<CardVenture>();
		String pathnameVenture = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Venture.xml";
		VentureCardSaxParser.VentureRead(pathnameVenture, venture);
		System.out.println("\n\n\nCARD VENTURE");
		for (CardVenture card : venture)
			System.out.println("\n" + card.toString());
		
		ArrayList<CardCharacter> character = new ArrayList<CardCharacter>();
		String pathnameCharacter = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Character.xml";
		CharacterCardSaxParser.CharacterRead(pathnameCharacter, character);
		System.out.println("\n\n\nCARD CHARACTER");
		for (CardCharacter card : character)
			System.out.println("\n" + card.toString());

		ArrayList<CardExcomm> excomm = new ArrayList<CardExcomm>();
		String pathnameExcomm = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Excomm.xml";
		ExcommCardSaxParser.ExcommRead(pathnameExcomm, excomm);
		System.out.println("\n\n\nCARD EXCOMM");
		for (CardExcomm card : excomm)
			System.out.println("\n" + card.toString());
		
		ArrayList<CardLeader> leader = new ArrayList<CardLeader>();
		String pathnameLeader = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Leader.xml";
		LeaderCardSaxParser.LeaderRead(pathnameLeader, leader);
		System.out.println("\n\n\nCARD LEADER");
		for (CardLeader card : leader)
			System.out.println("\n" + card.toString());

		ArrayList<HashMap<String, ResourceAbstract>> spaces = new ArrayList<HashMap<String, ResourceAbstract>>();
		String pathnameSpace = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/MarketSpace.xml";
		ZoneBonusSaxParser.BonusRead(pathnameSpace, spaces);
		System.out.println("\n\n\nSPACE BONUS");
		StringBuilder temp = new StringBuilder();
		int i = 1;
		for (HashMap<String, ResourceAbstract> x : spaces) {
			temp.append("\nSPACE "+i+"\n");
			i++;
			for (String el : x.keySet()) {	
				temp.append(el + " " + x.get(el).getQuantity() + "\n"); }
			temp.append("\n"); }
		System.out.println(temp);

		ArrayList<PersonalBoard> persBoard = new ArrayList<PersonalBoard>();
		String pathnamePersonalBoard = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/PersonalBoard.xml";
		PersonalBoardSaxParser.PersonalBoardRead(pathnamePersonalBoard, persBoard);
		System.out.println("\n\n\nPERSONAL BOARD");
		for (PersonalBoard pers : persBoard)
			System.out.println("\n" + pers.toString());

		HashMap<Integer,VictoryPoint> faithPoint = new HashMap<Integer,VictoryPoint>();
		String pathnameFaithPoint = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/FaithPointTrack.xml";
		FaithPointSaxParser.FaithRead(pathnameFaithPoint, faithPoint);
		FaithPointTrack faithPointTrack = new FaithPointTrack(faithPoint);
		System.out.println("\n\n\n FAITH POINT TRACK");
		System.out.println(faithPointTrack.toString());
		
		System.out.println("\n\n\n PLAYERS DATA");
		HashMap<String, UserData> playerDataToWrite = new HashMap<String, UserData>();
		UserData x;
		x = new UserData("marco");
		x.setNumPlayedGame(1);
		x.setNumVictory(1);
		playerDataToWrite.put("Marco",x);
		x = new UserData("lore");
		x.setNumPlayedGame(2);
		x.setNumVictory(2);
		playerDataToWrite.put("Lore",x);
		x = new UserData("gigi");
		x.setNumPlayedGame(3);
		x.setNumVictory(3);
		playerDataToWrite.put("Gigi",x);
		String playerPathname = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/UserData.xml";
		OutputPlayerDataDomParser.PlayerDataWrite(playerPathname, playerDataToWrite);
		System.out.println("Now already written file is ready to be read");
		HashMap<String, UserData> playerDataReaded = new HashMap<String, UserData>();
		InputPlayerDataSaxParser.PlayerRead(playerPathname, playerDataReaded);
		playerDataReaded.get("Gigi").setNumVictory(playerDataReaded.get("Gigi").getNumVictory()+1);
		for(String el: playerDataReaded.keySet()) {
			System.out.println("\nUsername: " + el);
			System.out.println(playerDataReaded.get(el).toString());
		}
		
		System.out.println("\nWrite new players' data on file");
		OutputPlayerDataDomParser.PlayerDataWrite(playerPathname, playerDataReaded);
		
		System.out.println("\n\nPARSER EXECUTION TERMINATED CORRECTLY");
		
		System.out.println("\n\nDevelopment card pathname check\n");
		ImageIcon card;
		int count = 0;
		for (CardTerritory t: territory) {
			card = new ImageIcon(new String("src/main/java/it/polimi/ingsw/ps22/client/gui/"+CardPath.getDevCardPathname(t)));
			count++;
			System.out.println("Card number: " + count + "\tVerifica l'altezza di 436: " + card.getIconHeight());
		}
		for (CardBuilding b: building) {
			card = new ImageIcon(new String("src/main/java/it/polimi/ingsw/ps22/client/gui/"+CardPath.getDevCardPathname(b)));
			count++;
			System.out.println("Card number: " + count + "\tVerifica l'altezza di 436: " + card.getIconHeight());
		}
		for (CardCharacter c: character) {
			card = new ImageIcon(new String("src/main/java/it/polimi/ingsw/ps22/client/gui/"+CardPath.getDevCardPathname(c)));
			count++;
			System.out.println("Card number: " + count + "\tVerifica l'altezza di 436: " + card.getIconHeight());
		}
		for (CardVenture v: venture) {
			card = new ImageIcon(new String("src/main/java/it/polimi/ingsw/ps22/client/gui/"+CardPath.getDevCardPathname(v)));
			count++;
			System.out.println("Card number: " + count + "\tVerifica l'altezza di 436: " + card.getIconHeight());
		}
		
		System.out.println("\nDevelopment card pathname check finished");
	}

}
