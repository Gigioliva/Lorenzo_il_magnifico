package it.polimi.ingsw.ps22.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		boolean flag=false;
		do{
			try{
				System.out.println("Scegli se socket o RMI [1,2]");
				int x=Integer.parseInt(stdin.readLine());
				System.out.println(x);
				if(x==1){
					ClientSocket.mainSocket();
					flag=true;
				}
				if(x==2){
					ClientRMI.mainRMI();
					flag=true;
				}
			}catch(NumberFormatException | IOException e){
				System.out.println("Scelta errata.");
			}
		}while(!flag);
	}

}
