package it.polimi.ingsw.ps22.server.view;

public class UserData {
	private String password;
	private int numPlayedGame;
	private int numVictory;
	
	public UserData(String password) {
		this.password = password;
		this.numPlayedGame = 0;
		this.numVictory = 0;
	}

	public int getNumPlayedGame() {
		return this.numPlayedGame;
	}

	public void setNumPlayedGame(int numPlayedGame) {
		this.numPlayedGame = numPlayedGame;
	}

	public String getPassword() {
		return this.password;
	}
	
	public int getNumVictory() {
		return this.numVictory;
	}

	public void setNumVictory(int numVictory) {
		this.numVictory = numVictory;
	}

	@Override
	public String toString() {
		StringBuilder temp = new StringBuilder();
		temp.append("Password: " + this.password + "\nNumero vittorie: " 
		+ this.numVictory + "\nNumero partite giocate: " + this.numPlayedGame);
		return temp.toString();
	}
	
}
