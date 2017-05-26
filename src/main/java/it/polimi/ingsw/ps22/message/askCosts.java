package it.polimi.ingsw.ps22.message;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class askCosts extends messageAsk {
	private int numChoice;
	
	public askCosts(ArrayList<HashMap<String, ResourceAbstract>> cost){
		numChoice=cost.size();
		StringBuilder str=new StringBuilder();
		str.append("Quale costo scegli? \n");
		for (HashMap<String, ResourceAbstract> x : cost) {
			for (String el : x.keySet()) {
				str.append(el + ": ");
				str.append(x.get(el).getQuantity() + " ");
			}
			str.append("\n");
		}
		setString(str.toString());
	}
	
	public void applyAsk(){
		model.notifyAsk(this);
	}
	
	public int getNumChoice(){
		return numChoice;
	}

}
