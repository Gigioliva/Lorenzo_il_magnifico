package it.polimi.ingsw.ps22.client.gui;

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

	public Rectangle resize(float factor) {
		Rectangle temp = new Rectangle((int) ((float) this.initx / factor), (int) ((float) this.finalx / factor),
				(int) ((float) this.inity / factor), (int) ((float) this.finaly / factor));
		return temp;
	}

}