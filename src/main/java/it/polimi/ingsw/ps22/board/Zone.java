package board;

import player.Player;

public abstract class Zone {

	public boolean Control(Player player, int actionSpace, int actionValue) {
		return false;
	}	 // Controlla che in quella zona il giocatore non abbia
		// piazzato altri famigliari colorati o che abbia
		// sufficienti risorse per prende la carta nella torre (Override)
	
	public void reset(){
		
	}
}
