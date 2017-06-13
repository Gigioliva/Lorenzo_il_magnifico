package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public class Main {
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				ArrayList<String> avver = new ArrayList<String>();
				avver.add("Lore");
				avver.add("Gigi");
				avver.add("Marco");
				ArrayList<String> paths = new ArrayList<String>();
				paths.add("./image/personalBonus/personalbonus1.png");
				paths.add("./image/personalBonus/personalbonus2.png");
				paths.add("./image/personalBonus/personalbonus3.png");
				ViewClient view = new ViewClient();
				Gui b = new Gui();
				b.initGui("Tizio", "./image/personalBonus/personalbonus4.png", avver, paths, view);
				Model model = new Model();
				model.addPlayers("Lore");
				model.addPlayers("Gigi");
				model.addPlayers("Marco");
				model.addPlayers("Tizio");
				ArrayList<Player> players = new ArrayList<Player>();
				for(String user: model.getPlayers().keySet()){
					players.add((model.getPlayers().get(user)));
				}
				model.getBoard().reset(1, players);
				b.updateGui(model);
			}
		});
	}
}
