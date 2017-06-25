package it.polimi.ingsw.ps22;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.board.Dice;
import it.polimi.ingsw.ps22.server.card.CardCharacter;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.EndVictoryEffect;
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
		player.addSpecBonus("FamilyCol+2");
		player.setFamily(dice);
		for(Color el: Color.values()){
			if(player.getFamily(el).getValue()!=dice.getDice(el)+2 && el!=Color.NEUTRAL)
				flag=false;
		}
		assert(flag);
		player.addSpecBonus("Neutral+3");
		player.setFamily(dice);
		assert(player.getFamily(Color.NEUTRAL).getValue()==3);
		player.getFamily(Color.NEUTRAL).incrementValue(2);
		assert(player.getFamily(Color.NEUTRAL).getValue()==5);
	}
	
	@Test
	public void testMalus(){
		HashMap<String, BonusAbstract> bonus=new HashMap<>();
		bonus.put("Coin", new Coin(-2));
		player.addBonusAcc(bonus);
		player.addSpecificResource("Coin", new Coin(3));
		ArrayList<String> temp=new ArrayList<String>();
		temp.add("Coin");
		player.applyMalusResource(temp);
		assert(player.getSpecificResource("Coin").getQuantity()==1);
		assert(player.getBonusAcc().getBonus("Coin").getQuantity()==-2);
	}
	
	@Test
	public void testSpecBonus(){
		player.addSpecBonus("DoubleServant");
		player.addSpecBonus("NoMarket");
		assert(player.getSpecBonus().returnBool("NoMarket"));
		assert(player.getSpecBonus().returnBool("DoubleServant"));
	}
	
	@Test
	public void testClone(){
		CardTerritory temp= new CardTerritory();
		CardLeader leader=new CardLeader("Carta leader");
		temp.setName("Carta prova");
		player.addDevelopmentCard("Territory", temp);
		player.addLeader(leader);
		EndVictoryEffect eff=new EndVictoryEffect(2);
		player.getEndEffects().add(eff);
		Player clone=player.clone(player.getUsername());
		assert(clone.getUsername().equals(player.getUsername()));
		for(String el: player.getResources().keySet()){
			assert(clone.getSpecificResource(el).getQuantity()==player.getSpecificResource(el).getQuantity());
		}
		for(String el: player.getPoints().keySet()){
			assert(clone.getSpecificResource(el).getQuantity()==player.getSpecificResource(el).getQuantity());
		}
		assert(player.getColor()==clone.getColor());
		for(DevelopmentCard el: player.getDevelopmentCard("Territory")){
			assert(searchCard(el.getName(),clone.getDevelopmentCard("Territory")));
		}
		assert(clone.toString().equals(player.toString()));
	}
	
	@Test
	public void checkEndEffect(){
		EndVictoryEffect eff=new EndVictoryEffect(2);
		player.getEndEffects().add(eff);
		player.applyEndEffects(null);
		assert(player.getSpecificResource("VictoryPoint").getQuantity()==2);
	}
	
	@Test
	public void checkResetLeader(){
		CardLeader leader=new CardLeader("Carta leader");
		player.addLeader(leader);
		leader.playLeader(player, null);
		player.resetLeader();
		for(CardLeader el: player.getLeaders()){
			assert(el.isPlay()==false);
		}
	}
	
	private boolean searchCard(String name, ArrayList<DevelopmentCard> cards){
		for(DevelopmentCard el: cards){
			if(el.getName().equals(name))
				return true;
		}
		return false;
	}
}
