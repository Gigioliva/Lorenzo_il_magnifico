package it.polimi.ingsw.ps22;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.board.Dice;
import it.polimi.ingsw.ps22.server.card.CardCharacter;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.BonusAbstract;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Wood;

public class TestPlayer {
	
	private Player player;
	
	@Before
	public void init(){
		player=new Player("Gigi", ColorPlayer.RED);
	}
	
	@Test
	public void addResource(){
		player.getSpecificResource("Coin").addResource(new Coin(5));
		player.subResources("Wood", new Wood(1));
		assert(player.getSpecificResource("Coin").getQuantity()==5);
		assert(player.getSpecificResource("Wood").getQuantity()==1);
	}
	
	@Test
	public void setPlayer(){
		boolean flag=true;
		if(player.getResources().get("Coin").getQuantity()!=0)
			flag=false;
		if(player.getResources().get("Wood").getQuantity()!=2)
			flag=false;
		if(player.getResources().get("Stone").getQuantity()!=2)
			flag=false;
		if(player.getResources().get("Servant").getQuantity()!=3)
			flag=false;
		if(player.getPoints().get("VictoryPoint").getQuantity()!=0)
			flag=false;
		if(player.getPoints().get("MilitaryPoint").getQuantity()!=0)
			flag=false;
		if(player.getPoints().get("FaithPoint").getQuantity()!=0)
			flag=false;
		if(!player.getUsername().equals("Gigi"))
			flag=false;
		if(player.getPersonalBoard().getBonusCharacter().get(1).getQuantity()!=1)
			flag=false;
		assert(flag);
	}
	
	@Test
	public void setFamily(){
		boolean flag=true;
		HashMap<Color, Family> family=player.getAllFamily();
		for(Color el: Color.values()){
			if(!family.containsKey(el)){
				flag=false;
			}
		}
		assert(flag);
	}
	
	@Test
	public void checkLeader(){
		CardLeader leader=new CardLeader("Carta prova");
		player.addLeader(leader);
		assert(player.getLeaders().contains(leader));
	}
	
	@Test
	public void checkDevelopmentCard(){
		CardTerritory card=new CardTerritory();
		player.addDevelopmentCard("Territory", card);
		assert(player.getSizeCard("Territory")==1);
		assert(player.getDevelopmentCard("Territory").contains(card));
	}
	
	@Test
	public void setSingleFamily(){
		boolean flag=true;
		for(Color el: Color.values()){
			if(player.getFamily(el).getColor()!=el){
				flag=false;
			}
		}
		assert(flag);
	}
	
	@Test
	public void checkMetod(){
		assert(player.isCard("Territory"));
		assert(player.isCard("Building"));
		assert(player.isCard("Venture"));
		assert(player.isResource("Stone"));
		assert(player.isResource("Wood"));
		assert(player.isPoint("VictoryPoint"));
		assert(player.isPoint("MilitaryPoint"));
	}
	
	@Test
	public void testCalcVicPoint(){
		player.getSpecificResource("Coin").addResource(new Coin(5));
		player.getSpecificResource("Wood").addResource(new Coin(3));
		player.getSpecificResource("Stone").addResource(new Coin(3));
		player.addDevelopmentCard("Territory", new CardTerritory());
		player.addDevelopmentCard("Territory", new CardTerritory());
		player.addDevelopmentCard("Territory", new CardTerritory());
		player.addDevelopmentCard("Character", new CardCharacter());
		player.addDevelopmentCard("Character", new CardCharacter());
		player.calcVicPoint();
		assert(player.getGenericValue("VictoryPoint")==7);	
	}
	
	@Test
	public void testFamilyValue(){
		boolean flag=true;
		Dice dice=new Dice();
		dice.setDice();
		player.setFamily(dice);
		for(Color el: Color.values()){
			if(player.getFamily(el).getValue()!=dice.getDice(el))
				flag=false;
		}
		assert(flag);
	}
	
	@Test
	public void testMalus(){
		HashMap<String, BonusAbstract> bonus=new HashMap<>();
		bonus.put("Coin", new Coin(-2));
		player.getBonusAcc().addBonus(bonus);
		player.addSpecificResource("Coin", new Coin(3));
		ArrayList<String> temp=new ArrayList<String>();
		temp.add("Coin");
		player.applyMalusResource(temp);
		assert(player.getSpecificResource("Coin").getQuantity()==1);
	}
	
	@Test
	public void testSpecBonus(){
		player.addSpecBonus("DoubleServant");
		player.addSpecBonus("NoMarket");
		assert(player.getSpecBonus().returnBool("NoMarket"));
		assert(player.getSpecBonus().returnBool("DoubleServant"));
	}
}
