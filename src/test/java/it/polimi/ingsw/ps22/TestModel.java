package it.polimi.ingsw.ps22;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class TestModel {
	
	private Model model;
	
	@Before
	public void init(){
		model=new Model();
		model.addPlayers("Sigismondo");
		model.addPlayers("Pastafizia");
		model.addPlayers("Dr.Culocane");
		model.addPlayers("Giovacchino");
	}
	
	@Test
	public void testStartGame(){
		model.startGame();
		assert(model.getTurn()==1);
		ArrayList<String> players=new ArrayList<>();
		players.add("Sigismondo");
		players.add("Pastafizia");
		players.add("Dr.Culocane");
		players.add("Giovacchino");
		ArrayList<String> players2=new ArrayList<>(model.getPlayers().keySet());
		assert(players.equals(players2));
		int i=0;
		for(String el: players){
			assert(model.getPlayers().get(el).getSpecificResource("Coin").getQuantity()==(5+i));
			assert(model.getPlayers().get(el).getSpecificResource("Stone").getQuantity()==2);
			assert(model.getPlayers().get(el).getSpecificResource("Wood").getQuantity()==2);
			assert(model.getPlayers().get(el).getSpecificResource("Servant").getQuantity()==3);
			i++;
		}
	}
	
	@Test
	public void testClone(){
		model.startGame();
		Model temp=model.clone("Sigismondo");
		assert(model.getBoard().toString().equals(temp.getBoard().toString()));
		for(String el: model.getPlayers().keySet()){
			assert(model.getPlayers().get(el).toString().equals(temp.getPlayers().get(el).toString()));
		}
	}
	
	@Test
	public void testNextPlayer(){
		model.startGame();
		for(Player el: model.getPlayers().values()){
			while(!model.getCardLeaderStart(el).isEmpty()){
				model.getCardLeaderStart(el).remove(0);
			}
		}
		model.draftStart();
		assert(model.getPlayerGame().equals("Sigismondo"));
		model.nextPlayer();
		assert(model.getPlayerGame().equals("Pastafizia"));
	}
	
	@Test
	public void testEndGame(){
		assert(model.getWaitAnswer().isEmpty());
		model.startGame();
		for(Player el: model.getPlayers().values()){
			while(!model.getCardLeaderStart(el).isEmpty()){
				model.getCardLeaderStart(el).remove(0);
			}
		}
		model.draftStart();
		assert(model.getCanFamilyMove());
		model.getPlayers().get("Sigismondo").addSpecificResource("VictoryPoint", new VictoryPoint(150));
		model.getPlayers().get("Sigismondo").addSpecificResource("MilitaryPoint", new MilitaryPoint(10));
		model.getPlayers().get("Pastafizia").addSpecificResource("VictoryPoint", new VictoryPoint(3));
		model.getPlayers().get("Pastafizia").addSpecificResource("MilitaryPoint", new MilitaryPoint(8));
		model.getPlayers().get("Dr.Culocane").addSpecificResource("VictoryPoint", new VictoryPoint(2));
		model.getPlayers().get("Dr.Culocane").addSpecificResource("MilitaryPoint", new MilitaryPoint(8));
		model.getPlayers().get("Giovacchino").addSpecificResource("VictoryPoint", new VictoryPoint(0));
		model.getPlayers().get("Giovacchino").addSpecificResource("MilitaryPoint", new MilitaryPoint(4));
		while(model.getTurn()!=7){
			model.nextPlayer();
		}
		assert(model.getPlayerGame().equals("Sigismondo"));
		assert(!model.getIsActive());
	}
	
	@Test
	public void testSet(){
		model.startGame();
		for(Player el: model.getPlayers().values()){
			while(!model.getCardLeaderStart(el).isEmpty()){
				model.getCardLeaderStart(el).remove(0);
			}
		}
		model.draftStart();
		assert(model.getCurrentPlayer().getUsername().equals("Sigismondo"));
		assert(model.getOrderedPlayers(2).equals("Dr.Culocane"));
	}

}
