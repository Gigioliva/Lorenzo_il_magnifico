package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.action.ProductionAction;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class ProductionZone extends Zone {
	
	private static final long serialVersionUID = 1L;
	private transient static final int NUM_SPACES = 2;
	private ProductionSpace[] productionSpace;

	public ProductionZone(Model model) {
		super(model);
		productionSpace = new ProductionSpace[NUM_SPACES];
		productionSpace[0] = new ProductionSpace(1, false,1);
		productionSpace[1] = new ProductionSpace(1, true,0);
	}

	public ProductionZone clone(ArrayList<Family> family) {
		ProductionZone temp = new ProductionZone(null);
		temp.productionSpace[0] = this.productionSpace[0].clone(family);
		temp.productionSpace[1] = this.productionSpace[1].clone(family);
		return temp;
	}
	
	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementProduction").getQuantity();
		if (0 <= actionSpace && actionSpace <= NUM_SPACES && !(family.isPlaced()) && productionSpace[actionSpace].isPlayable()
				&& (productionSpace[actionSpace].controlPlacement()
						|| player.getSpecBonus().returnBool("OccupiedSpace"))
				&& (family.getColor()==Color.NEUTRAL || checkAllSpace(player))
				&& checkActionValue(numServant, productionSpace[actionSpace], family, actionValue)) {
			return true;
		}
		return false;
	}

	public void applyMove(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		productionSpace[actionSpace].addFamily(family);
		int actionValue = family.getValue();
		if (productionSpace[actionSpace].getMulti()) {
			actionValue = actionValue - 3;
		}
		ProductionAction productionAction = new ProductionAction(actionValue);
		productionAction.applyAction(player, 0, model);
	}

	private boolean checkAllSpace(Player player) {
		boolean control = true;
		ArrayList<Family> allFamily = new ArrayList<Family>();
		for (int i = 0; i < NUM_SPACES; i++) {
			allFamily.addAll(productionSpace[i].getFamilies());
		}
		for (Color el : Color.values()) {
			if (el != Color.NEUTRAL)
				control = control && !allFamily.contains(player.getFamily(el));
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
	public boolean cantPlaceZone(Player player){
		 HashMap<Color, Family> family = player.getAllFamily();
		 for(int i=0; i<NUM_SPACES; i++){
			 for(Color color: family.keySet()){
				 if(!family.get(color).isPlaced())
					 if(cantPlaceSpace(player.getSpecificResource("Servant").getQuantity(), i, family.get(color)))
						 return false;
			 }
		 }
		 return true;
	}

	@Override
	public void setZone(int num) {
		if (num < 3) {
			productionSpace[1].setNotPlayable();
		}
		if(num==5){
			productionSpace[0].setFivePlayer();
		}
	}
	
	public ProductionSpace[] getProdSpaces(){
		return this.productionSpace;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Production Zone \n");
		for(ProductionSpace prod: productionSpace){
			str.append(prod.toString());
		}
		
		return str.toString();
	}

}
