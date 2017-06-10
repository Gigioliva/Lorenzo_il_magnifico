package it.polimi.ingsw.ps22.client.main;

import java.util.Scanner;

public class Client {

	private static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		System.out.println("Scegli se socket o RMI [1,2]");
		int x=in.nextInt();
		if(x==1){
			ClientSocket.mainSocket();
		}
		if(x==2){
			ClientRMI.mainRMI();
		}
	}

}
