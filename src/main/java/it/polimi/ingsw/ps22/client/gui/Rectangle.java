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
		return Math.abs(finalx - initx);
	}

	public int getOffsetY() {
		return Math.abs(finaly - inity);
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
	
	public static Rectangle fillImageRatio(Rectangle rec, float heightWightRatio) { 
		// ratio=altezza/larghezza
		Rectangle temp;
		float aux = (float)rec.getOffsetX()*heightWightRatio;
		if((float)rec.getOffsetY()>aux)
			temp = new Rectangle(
					rec.getInitx(),rec.getFinalx(),rec.getInity(),
					(rec.getInity()+(int)((float)rec.getOffsetX()*heightWightRatio)));
		else
			temp = new Rectangle(
					rec.getInitx(),(rec.getInitx()+(int)((float)rec.getOffsetY()/heightWightRatio)),
					rec.getInity(),rec.getFinaly());
		return temp;
	}

	public static ArrayList<Rectangle> divideRectangle(Rectangle toDivide) {
		ArrayList<Rectangle> toReturn = new ArrayList<>();
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