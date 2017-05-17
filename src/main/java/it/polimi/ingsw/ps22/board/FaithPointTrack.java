package it.polimi.ingsw.ps22.board;
import java.util.HashMap;

import it.polimi.ingsw.ps22.resource.VictoryPoint;

public class FaithPointTrack {
	private HashMap<Integer,VictoryPoint> track;
	
	public FaithPointTrack(HashMap<Integer,VictoryPoint> track){
		this.track=track;
	}
	
	public VictoryPoint getVictoryBonus(int faithPoint){
		return track.get(faithPoint);
	}
	

}
