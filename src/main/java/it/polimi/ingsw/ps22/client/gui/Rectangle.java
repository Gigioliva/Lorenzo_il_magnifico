package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;

public class Rectangle {

	private int initx;
	private int finalx;
	private int inity;
	private int finaly;

	public Rectangle(int ix, int fx, int iy, int fy) {
		initx = ix;
		finalx = fx;
		inity = iy;
		finaly = fy;
	}

	public int getOffsetX() {
		return finalx - initx;
	}

	public int getOffsetY() {
		return finaly - inity;
	}

	public int getInitx() {
		return initx;
	}

	public void setInitx(int initx) {
		this.initx = initx;
	}

	public int getFinalx() {
		return finalx;
	}

	public void setFinalx(int finalx) {
		this.finalx = finalx;
	}

	public int getInity() {
		return inity;
	}

	public void setInity(int inity) {
		this.inity = inity;
	}

	public int getFinaly() {
		return finaly;
	}

	public void setFinaly(int finaly) {
		this.finaly = finaly;
	}

	public Rectangle resize(double factor) {
		Rectangle temp = new Rectangle((int) ((double) this.initx / factor), (int) ((double) this.finalx / factor),
				(int) ((double) this.inity / factor), (int) ((double) this.finaly / factor));
		return temp;
	}

	public static ArrayList<Rectangle> divideRectangle(Rectangle toDivide) {
		ArrayList<Rectangle> toReturn = new ArrayList<Rectangle>();
		toReturn.add(new Rectangle(toDivide.getInitx(),(toDivide.getInitx()+toDivide.getOffsetX()/2),
									toDivide.getInity(),(toDivide.getInity()+toDivide.getOffsetY()/2)));
		
		toReturn.add(new Rectangle((toDivide.getInitx()+toDivide.getOffsetX()/2),toDivide.getFinalx(),
									toDivide.getInity(),(toDivide.getInity()+toDivide.getOffsetY()/2)));
		
		toReturn.add(new Rectangle(toDivide.getInitx(),(toDivide.getInitx()+toDivide.getOffsetX()/2),
									(toDivide.getInity()+toDivide.getOffsetY()/2),toDivide.getFinaly()));
		
		toReturn.add(new Rectangle((toDivide.getInitx()+toDivide.getOffsetX()/2),toDivide.getFinalx(),
									(toDivide.getInity()+toDivide.getOffsetY()/2),toDivide.getFinaly()));
		return toReturn;
	}
}