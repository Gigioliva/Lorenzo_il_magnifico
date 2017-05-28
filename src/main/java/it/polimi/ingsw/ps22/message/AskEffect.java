package it.polimi.ingsw.ps22.message;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.effect.ActionEffect;

public class AskEffect extends MessageAsk {
	private HashMap<DevelopmentCard,ArrayList<ActionEffect>> listEffect;
	
	public AskEffect(HashMap<DevelopmentCard,ArrayList<ActionEffect>> listEffect){
		this.listEffect=listEffect;
		StringBuilder str=new StringBuilder();
		for(DevelopmentCard el: listEffect.keySet()){
			str.append("Gli effetti della carta " + el.getName() + " sono:\n");
			for(ActionEffect effect: listEffect.get(el)){
				str.append(effect.toString() + "\n");  //fare i toString degli effetti
			}
			str.append("\n");
		}
		str.append("Quale scegli?: "); //chiedi il nome della carta e il numero dell'effetto
		setString(str.toString());
	}
	
	public HashMap<DevelopmentCard,ArrayList<ActionEffect>> getListEffect(){
		return listEffect;
	}

}
