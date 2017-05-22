package it.polimi.ingsw.ps22.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class Ask extends Observable {

	public int askServant(Player player) {
		boolean correct = true;
		StringBuilder str = new StringBuilder();
		do {
			str.append("quanti servitori vuoi spendere?: ");
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
					str.append("Non corretto. ");
					correct = false;
				}
			} catch (NumberFormatException e) {
				str.delete(0, str.capacity());
				str.append("Non corretto. ");
				correct = false;
			}
		} while (!correct);
		return 0;
	}

	public int askCostVenture(ArrayList<RequisiteCost> possibleCost) {
		boolean correct = true;
		StringBuilder str = new StringBuilder();
		do {
			str.append("quale costo scegli? \n");
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
					str.append("Non corretto. ");
					correct = false;
				}
			} catch (NumberFormatException e) {
				str.delete(0, str.capacity());
				str.append("Non corretto. ");
				correct = false;
			}
		} while (!correct);
		return -1;
	}

	public boolean askExcomm() {
		boolean correct = true;
		StringBuilder str = new StringBuilder();
		do {
			str.append("vuoi sostenere la Chiesa? [Si;No] ");
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
			str.append("Non corretto. ");
			correct = false;
		} while (!correct);
		return false;
	}
	
	public ArrayList<Integer> askPrivilegeChange(int num) {
		boolean corretto;
		ArrayList<Integer> result;
		StringBuilder str = new StringBuilder();
		do {
			corretto=true;
			str.append("cosa vuoi in cambio?: " + num + " scelte [separati da spazio]\n");
			str.append("1: 1 legno e una pietra\n");
			str.append("2: 2 servitori\n");
			str.append("3: 2 monete\n");
			str.append("4: 2 punti militari\n");
			str.append("5: 1 punto fede\n");
			int size = str.length();
			setChanged();
			notifyObservers(str);
			isChan(str);
			str.delete(0, size);
			result = parseToArray(str.toString());
			for (int el = 0; el < result.size(); el++) {
				for (int el2 = el + 1; el2 < result.size(); el2++) {
					if (result.get(el) == result.get(el2)) {
						str.delete(0, str.capacity());
						str.append("Non corretto. ");
						corretto = false;
					}
				}
			}
			if (result.size() != num) {
				str.delete(0, str.capacity());
				str.append("Non corretto. ");
				corretto = false;
			}
			for(int el: result){
				if(el<1 || el>5){
					str.delete(0, str.capacity());
					str.append("Non corretto. ");
					corretto=false;
				}
			}
		} while (!corretto);
		return result;
	}

	private static ArrayList<Integer> parseToArray(String s) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(s);
		while (m.find()) {
			numbers.add(Integer.parseInt(m.group()));
		}
		if (numbers.size() <= 0) {
			numbers.add(-1);
		}
		return numbers;
	}

	private void isChan(StringBuilder str) {
		StringBuilder temp = new StringBuilder(str);
		while (str.equals(temp)) {

		}
	}

}