package it.polimi.ingsw.ps22.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class Ask extends Observable{
	
	public int askServant() {
		StringBuilder str = new StringBuilder("quanti servitori vuoi spendere? ");
		int size = str.length();
		setChanged();
		notifyObservers(str);
		isChan(str);
		str.delete(0, size);
		try{
			int result= Integer.parseInt(str.toString());
			return result;
		}
		catch(NumberFormatException e){
			return 0;
		}
	}
	
	public int askCostVenture(ArrayList<RequisiteCost> possibleCost) {
		boolean corretto = true;
		StringBuilder str = new StringBuilder("quale costo scegli? \n");
		do {
			for (RequisiteCost x : possibleCost) {
				HashMap<String, ResourceAbstract> cost=x.getCost();
				for (String el : cost.keySet()) {
					str.append(el + ": ");
					str.append(cost.get(el).getQuantity() + " ");
				}
				str.append("\n");
			}
			int size = str.length();
			setChanged();
			notifyObservers(str);
			isChan(str);
			str.delete(0, size);
			int result;
			try {
				result = Integer.parseInt(str.toString());
				if (result <= possibleCost.size() && result > 0) {
					return result - 1;
				} else {
					str.delete(0, str.capacity());
					str.append("Non corretto. quale costo scegli? \n");
					corretto = false;
				}
			} catch (NumberFormatException e) {
				str.delete(0, str.capacity());
				str.append("Non corretto. quale costo scegli? \n");
				corretto = false;
			}
		} while (!corretto);
		return -1;
	}

	public void isChan(StringBuilder str) {
		StringBuilder temp = new StringBuilder(str);
		while (str.equals(temp)) {

		}
	}

}
