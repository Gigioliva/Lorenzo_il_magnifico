package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.model.Model;

public class GraphicGUI extends Graphic {

	public GraphicGUI() {
		super(new VisitorGUI(), new RequestMoveGUI());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printModel(Model model) {
	}

}
