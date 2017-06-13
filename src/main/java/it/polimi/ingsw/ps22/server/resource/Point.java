package it.polimi.ingsw.ps22.server.resource;

public class Point extends ResourceAbstract {
	
	private static final long serialVersionUID = 1L;
	
	public Point(int points){
		super(points);
	}
	
	@Override
	public Point clone() {
		return new Point(this.getQuantity());
	}

}
