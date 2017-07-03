package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * The Zone represents a set of spaces that share common characteristics.
 *  So each concrete zone will extend this class. 
 *
 */
public abstract class Zone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected Model model;
	
	/**
	 * new Zone
	 * @param model
	 */
	public Zone(Model model){
		this.model=model;
	}

	/**
	 * It controls that the {@link Player} has not placed any colorful {@link Family} in this zone
	 * and other specific controls of the specific zone. It will be overridden in each subclass
	 * @param numServant the number of {@link Servant} to increase the action value
	 * @param actionSpace an integer representing the target specific {@link ActionSpace}
	 * @param family the family to be placed.
	 * @return true if the family can be placed, false otherwise
	 */
	public abstract boolean Control(int numServant, int actionSpace, Family family);

	/**
	 * This method reset each {@link ActionSpace} of the given Zone.
	 * for more detailed documentation, see the class {@link ActionSpace}
	 */
	public void reset(){
		
	}

	/**
	 * This method reset each actionSpace of the given Zone. It is thought for
	 * those Zones that require a specific reset depending on the turn of the game
	 * @param turn the current turn of the game
	 */
	public void reset(int turn) {

	}


	/**
	 * this method checks whether the {@link Player} can place the given {@link Family}
	 * in the action space or not just basing on the action value. So it will consider just what
	 * the action value is concerned (familiars'action value, servants added, permanent effect, etc..)
	 * @param family the {@link Family} to be placed
	 * @param actionSpace the specific {@link ActionSpace} in which it's desired to place the familiar
	 * @param numServant the number of servants
	 * @param actionValue the current action value of the dice
	 * @return
	 */
	protected boolean checkActionValue(int numServant, ActionSpace actionSpace, Family family, int actionValue) {
		Player player = family.getPlayer();
		if (numServant >= 0 && numServant <= player.getSpecificResource("Servant").getQuantity()) {
			if ((player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant / 2) >= actionSpace.getActionCost())) {
				return true;
			}
			if (!(player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant) >= actionSpace.getActionCost())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * It applies the effect of the {@link Servant}, 
	 * so it will increment the action value of a quantity of one
	 * for each servant. Remind that this can be different in case of some permanent effects.
	 * Also possible effects are taken into account by this method
	 * 
	 * @param family the {@link Family} to be placed
	 * @param numServant the number of servants
	 */
	protected void applyServant(Family family, int numServant) {
		Player player = family.getPlayer();
		if (player.getSpecBonus().returnBool("DoubleServant")) {
			player.getResources().get("Servant").subResource(new Servant((numServant / 2) * 2));
			family.incrementValue(numServant / 2);
		} else {
			player.getResources().get("Servant").subResource(new Servant(numServant));
			family.incrementValue(numServant);
		}
	}

	/**
	 * This method sets certain spaces of the Zone to 
	 * not playable. This is necessary because for some values of the
	 * number of {@link Player}s, some spaces won't be playable
	 * @param num the number of {@link Player} of the game
	 */
	public void setZone(int num) {

	}
	
	public abstract Zone clone(ArrayList<Family> family);
}
