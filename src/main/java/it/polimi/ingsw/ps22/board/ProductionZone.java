package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.action.ProductionAction;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class ProductionZone extends Zone {

	private static final int NUM_SPACES = 2;

	private ProductionSpace[] productionSpace;

	public ProductionZone() {
		productionSpace = new ProductionSpace[NUM_SPACES];
		productionSpace[0] = new ProductionSpace(1, false);
		productionSpace[1] = new ProductionSpace(1, true);
	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player=family.getPlayer();
		int actionValue=family.getValue() + player.getBonusAcc().getBonus("IncrementProduction").getQuantity();
		if (!(family.isPlaced()) && productionSpace[actionSpace].isPlayable() && (productionSpace[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& checkAllSpace(player) && checkActionValue(numServant, productionSpace[actionSpace], family, actionValue)) {
			productionSpace[actionSpace].addFamily(family);
			actionValue=family.getValue();
			if (productionSpace[actionSpace].getMulti()) {
				actionValue=actionValue-3;
			}
			ProductionAction productionAction = new ProductionAction(actionValue);
			productionAction.applyAction(player);
			return true;
		}
		return false;
		/*
		 * Verificare che in questa zona non ci siano famigliari colorati dello
		 * stesso giocatore e che ci sia posto nella zona indicata. Chiedere
		 * all'utente se vuole spendere dei servitori, a quel punto confrontare
		 * se il valore del dado del famigliare + incremento dei servitori è
		 * maggiore del valore dell'azione richiesto, se sufficiente scalare i
		 * servitori. quindi aggiungere il famigliare nella zona (bonus e presa
		 * carte verrà fatto dallo spazio azione)
		 */
	}

	private boolean checkAllSpace(Player player) {
		boolean control = true;
		ArrayList<Family> allFamily = new ArrayList<Family>();
		for (int i = 0; i < NUM_SPACES; i++) {
			allFamily.addAll(productionSpace[i].getFamilies());
		}
		for (Color el : Color.values()) {
			if (el != Color.NEUTRAL)
				control = control && allFamily.contains(player.getFamily(el));
		}
		return control;
	}
	
	@Override
	public void reset() {
		for (int i = 0; i < NUM_SPACES; i++) {
			productionSpace[i].resetFamily();
		}
	}
	
	@Override
	public void setZone(int num){
		if(num<3){
			productionSpace[1].setNotPlayable();
		}
	}

}
