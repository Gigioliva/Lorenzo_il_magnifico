package it.polimi.ingsw.ps22.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class Ask extends Observable {

	public int askServant(Player player) {
		boolean correct = true;
		StringBuilder str = new StringBuilder("quanti servitori vuoi spendere?:");
		do {
			int size = str.length();
			setChanged();
			notifyObservers(str);
			isChan(str);
			str.delete(0, size);
			try {
				int result = Integer.parseInt(str.toString());
				if (result > 0 && result <= player.getSpecificResource("Servant").getQuantity()) {
					return result;
				} else {
					str.delete(0, str.capacity());
					str.append("Non correct. quanti servitori vuoi spendere?:");
					correct = false;
				}
			} catch (NumberFormatException e) {
				str.delete(0, str.capacity());
				str.append("Non correct. quanti servitori vuoi spendere?:");
				correct = false;
			}
		} while (!correct);
		return 0;
	}

	public int askCostVenture(ArrayList<RequisiteCost> possibleCost) {
		boolean correct = true;
		StringBuilder str = new StringBuilder("quale costo scegli? \n");
		do {
			for (RequisiteCost x : possibleCost) {
				HashMap<String, ResourceAbstract> cost = x.getCost();
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
					str.append("Non correct. quale costo scegli? \n");
					correct = false;
				}
			} catch (NumberFormatException e) {
				str.delete(0, str.capacity());
				str.append("Non correct. quale costo scegli? \n");
				correct = false;
			}
		} while (!correct);
		return -1;
	}

	public boolean askExcomm() {
		boolean correct = true;
		StringBuilder str = new StringBuilder("vuoi sostenere la Chiesa? [Si;No] ");
		do {
			int size = str.length();
			setChanged();
			notifyObservers(str);
			isChan(str);
			str.delete(0, size);
			if (str.toString().toUpperCase().equals("SI")) {
				return true;
			}
			if (str.toString().toUpperCase().equals("NO")) {
				return false;
			}
			str.delete(0, str.capacity());
			str.append("Non correct. vuoi sostenere la Chiesa? [Si;No] ");
			correct = false;
		} while (!correct);
		return false;
	}

	public void isChan(StringBuilder str) {
		StringBuilder temp = new StringBuilder(str);
		while (str.equals(temp)) {

		}
	}

}
