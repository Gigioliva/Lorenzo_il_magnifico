package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.model.Model;

public class GraphicCLI extends Graphic {
	
	public GraphicCLI(){
		super(new VisitorCLI(),new RequestMoveCLI());
	}

	@Override
	public void printModel(Model model) {
		StringBuilder temp=new StringBuilder();
		temp.append("DISEGNO MODEL");
		for(String el: model.getPlayers().keySet()){
			temp.append("GIOCATORE: " + el + model.getPlayers().get(el).toString());
		}
		temp.append(model.getBoard().toString());
		System.out.println(temp.toString());
	}

	/*@Override
	public String getChat() {
		
	}*/
}
