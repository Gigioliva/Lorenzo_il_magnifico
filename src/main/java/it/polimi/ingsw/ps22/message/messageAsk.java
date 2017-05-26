package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.model.Model;

public class messageAsk {
	
	private String ask;
	
	protected static Model model;
	
	public String getString(){
		return ask;
	}
	
	public void setString(String str){
		this.ask=str;
	}
	
	public static void setModel(Model mod){
		model=mod;
	}

}
