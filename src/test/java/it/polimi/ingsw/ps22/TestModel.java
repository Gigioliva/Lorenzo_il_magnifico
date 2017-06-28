package it.polimi.ingsw.ps22;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.model.Model;

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

}
