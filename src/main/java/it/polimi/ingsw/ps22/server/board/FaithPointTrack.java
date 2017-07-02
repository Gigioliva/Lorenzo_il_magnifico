package it.polimi.ingsw.ps22.server.board;
import java.io.Serializable;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

/**
 * 
 * The faith point track implements the concept of the track the the players follow when they gain 
 * {@link FaithPoint}. Each cell of the track may have a bonus in {@link VictoryPoint}
 *
 */
public class FaithPointTrack implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<Integer,VictoryPoint> track;
	
	/**
	 * It instantiate the class
	 * @param track an {@link HashMap} containing for each key (integer) the corresponding gain in {@link VictoryPoint}
	 */
	public FaithPointTrack(HashMap<Integer,VictoryPoint> track){
		this.track=track;
	}
	
	/**
	 * It returns the bonus in {@link VictoryPoint} associated to the integer passed as parameter
	 * @param faithPoint an int representing the number of faith points
	 * @return
	 */
	public VictoryPoint getVictoryBonus(int faithPoint){
		return track.get(faithPoint);
	}
	
	@Override
	public FaithPointTrack clone() {
		HashMap<Integer,VictoryPoint> temp = new HashMap<Integer,VictoryPoint>();
		for(Integer i: track.keySet()) {
			temp.put(i, track.get(i).clone());
		}
		FaithPointTrack x = new FaithPointTrack(temp);
		return x;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("Faith Point track: \n");
		
		for(Integer pos: track.keySet()){
			str.append("  Position: " + pos + " ");
			str.append("Victory Points " + track.get(pos).getQuantity() + "\n");
		}
		
		return str.toString();
	}

}
