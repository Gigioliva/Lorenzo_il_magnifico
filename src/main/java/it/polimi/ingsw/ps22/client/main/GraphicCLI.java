package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.model.Model;

public class GraphicCLI extends Graphic {
	
	private boolean init=true;
	
	public GraphicCLI(ViewClient view){
		super(new VisitorCLI(view),new RequestMoveCLI(view));
	}

	@Override
	public void printModel(Model model) {
		/*System.out.print("\033[H\033[2J");*/
		if(init){
			System.out.println(" #       ###   ####   #####  #   #  #####   ###           ###   #             #   #    #     ####  #   #   ###   #####   ###    ###    ###  \n #      #   #  #   #  #      ##  #     #   #   #           #    #             ## ##   # #   #      ##  #    #    #        #    #   #  #   # \n #      #   #  ####   ####   # # #    #    #   #           #    #             # # #  #   #  #  ##  # # #    #    ####     #    #      #   # \n #      #   #  # #    #      #  ##   #     #   #           #    #             # # #  #####  #   #  #  ##    #    #        #    #   #  #   # \n #####   ###   #  ##  #####  #   #  #####   ###           ###   #####         #   #  #   #   ####  #   #   ###   #       ###    ###    ### ");
			init=false;
		}
		StringBuilder temp=new StringBuilder();
		for(String el: model.getPlayers().keySet()){
			temp.append(model.getPlayers().get(el).toString());
		}
		temp.append(model.getBoard().toString());
		System.out.println(temp.toString());
	}

	/*@Override
	public String getChat() {
		
	}*/
}
