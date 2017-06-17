package it.polimi.ingsw.ps22.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.server.message.ChoiceMove;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public class ViewClient extends Observable implements Observer, Runnable {
	
	private String username;
	private Graphic graphic;
	private BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	private boolean myTurn=true;
	
	public ViewClient(){
		boolean flag=false;
		do{
			try{
				System.out.println("GUI o CLI [1,2]");
				int x=Integer.parseInt(stdin.readLine());
				if(x==1){
					graphic=new GraphicGUI(this);
					flag=true;
				}
				if(x==2){
					graphic=new GraphicCLI(this);
					flag=true;
				}
			}catch(NumberFormatException | IOException e){
				System.out.println("Scelta errata.");
			}
		}while(!flag);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			username=(String)arg;
			System.out.println((String)arg);
		}
		if(arg instanceof GenericMessage){
			graphic.getAnswer((GenericMessage)arg);
		}
		if(arg instanceof ChoiceMove){
			graphic.getMove();
		}
		if(arg instanceof Model){
			graphic.printModel((Model)arg);
		}
	}

	@Override
	public void run() {
		
	}
	
	public String getUsername(){
		return username;
	}
	
	public void send(Object obj){
		if(obj!=null){
			setChanged();
			notifyObservers(obj);
		}
	}
	
	public boolean isMyTurn(){
		return myTurn;
	}
	
	public void setFlag(boolean flag){
		this.myTurn=flag;
	}

}
