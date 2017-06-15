package it.polimi.ingsw.ps22.server.board;
import java.io.Serializable;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class FaithPointTrack implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<Integer,VictoryPoint> track;
	
	public FaithPointTrack(HashMap<Integer,VictoryPoint> track){
		this.track=track;
	}
	
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
