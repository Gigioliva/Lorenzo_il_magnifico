package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.action.ProductionAction;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.message.AskCopyLeader;
import it.polimi.ingsw.ps22.server.message.AskCouncilPrivilege;
import it.polimi.ingsw.ps22.server.message.AskEffect;
import it.polimi.ingsw.ps22.server.message.AskFamily;
import it.polimi.ingsw.ps22.server.message.AskServant;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public class Main {
	public static void mainGui(String[] args) {
	
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
				new LoginFrame(view);
				Gui b = new Gui();
				Model model = new Model();
				b.initGui(model, view);
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
				
				b.askPrivilege(new AskCouncilPrivilege(2, model.getPlayers().get("Tizio")) );
				
				HashMap<DevelopmentCard,ArrayList<ActionEffect>> listEffect = new HashMap<DevelopmentCard,ArrayList<ActionEffect>>();
				listEffect.put(model.getBoard().getTower("Building").getTowerSpaces()[0].getCard(),
						model.getBoard().getTower("Building").getTowerSpaces()[0].getCard().getActionEffects() );
				listEffect.put(model.getBoard().getTower("Building").getTowerSpaces()[1].getCard(),
						model.getBoard().getTower("Building").getTowerSpaces()[1].getCard().getActionEffects() );
				AskEffect mex = new AskEffect(listEffect , new ProductionAction(3), model.getPlayers().get("Tizio"));
				b.askEffect(mex);
				b.askFamily(new AskFamily(model.getPlayers().get("Tizio")));
				b.askServants(new AskServant(new ProductionAction(4)));
				ArrayList<CardLeader> arr = new ArrayList<CardLeader>();
				arr.add(new CardLeader("Botticelli"));
				arr.add(new CardLeader("Borgia"));
				b.askCopyLeader(new AskCopyLeader(arr,new CardLeader("ciao"),model.getPlayers().get("Tizio")));
				b.errorMove();
				
			}
		});
	}
}
