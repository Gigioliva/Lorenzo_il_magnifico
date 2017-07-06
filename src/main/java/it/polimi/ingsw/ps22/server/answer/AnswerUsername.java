package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.model.Model;

/**
 * 
 * This class implements the answer to the question about
 * the username and password that the player inserts at the very beginning 
 * of the game
 *
 */
public class AnswerUsername extends GenericAnswer {

	private static final long serialVersionUID = 1L;
	private String username;
	private String pass;
	private int numPlayer;
	private boolean reg;
	
	/**
	 * 
	 * @param answer a string representing the answer of the player
	 * @param pass the password inserted
	 * @param numPlayer this int represents whether the player want to play
	 * in a four members lobby or in a 5 members lobby
	 * @param reg this boolean is true if the player is a new user, false otherwise
	 */
	public AnswerUsername(String answer, String pass, int numPlayer, boolean reg){
		super(0);
		this.username=answer;
		this.pass=pass;
		this.numPlayer=numPlayer;
		this.reg=reg;
	}
	
	/**
	 * 
	 * @return the username that the player has inserted
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * 
	 * @return the password that the player has inserted
	 */
	public String getPassword(){
		return pass;
	}
	
	/**
	 * 
	 * @return the number of players (4 or 5 depending on the chosen lobby)
	 */
	public int getNumPlayer(){
		return numPlayer;
	}
	
	/**
	 * 
	 * @return a boolean thats is true if the player is a new user, false otherwise
	 */
	public boolean getReg(){
		return reg;
	}

	@Override
	public void applyAnswer(Model model) {

	}

}
