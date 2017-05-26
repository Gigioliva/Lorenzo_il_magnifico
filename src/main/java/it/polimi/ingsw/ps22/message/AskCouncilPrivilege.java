package it.polimi.ingsw.ps22.message;

public class AskCouncilPrivilege extends MessageAsk {
	private int numChoice;
	
	public AskCouncilPrivilege(int numChoice){
		this.numChoice=numChoice;
		StringBuilder str = new StringBuilder();
		str.append("cosa vuoi in cambio?: " + numChoice + " scelte [separati da spazio]\n");
		str.append("1: 1 legno e una pietra\n");
		str.append("2: 2 servitori\n");
		str.append("3: 2 monete\n");
		str.append("4: 2 punti militari\n");
		str.append("5: 1 punto fede\n");
		setString(str.toString());
	}
	
	public void applyAsk(){
		model.notifyAsk(this);
	}
	
	public int getNumChoice(){
		return numChoice;
	}
	
}
