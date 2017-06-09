package it.polimi.ingsw.ps22.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.move.*;

public class RequestMoveCLI implements RequestMove {
	
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public Move requestMove() {
		boolean corretto=false;
		do {
			try {
				Move mossa = chiediMossa();
				if(mossa!=null){
					corretto = true;
					return mossa;
				}
			} catch (Exception e) {
				System.out.println("Non corretto, riprova.");
				corretto = false;
			}
		} while (!corretto);
		return null;
	}

	private static Move chiediMossa() {
		System.out.println("che mossa vuoi fare? [familiare, leader]");
		String str;
		try {
			str = in.readLine();
			if (str.equalsIgnoreCase("familiare")) {
				return chiedifamiliare();
			}
			if (str.equalsIgnoreCase("leader")) {
				return chiediLeader();
			}
			return null;
		} catch (IOException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private static Move chiedifamiliare() {
		try {
			System.out.println("Che familiare vuoi piazzare? [white,black,orange,neutral]");
			String str = in.readLine();
			Color color = Enum.valueOf(Color.class, str.toUpperCase());
			System.out.println("dove vuoi piazzarlo? [mercato, produzione, raccolto, torre, concilio]");
			str = in.readLine();
			if (str.equalsIgnoreCase("mercato")) {
				return chiediMercato(color);
			}
			if (str.equalsIgnoreCase("produzione")) {
				return chiediProduzione(color);
			}
			if (str.equalsIgnoreCase("raccolto")) {
				return chiediRaccolto(color);
			}
			if (str.equalsIgnoreCase("concilio")) {
				return chiediConcilio(color);
			}
			if (str.equalsIgnoreCase("torre")) {
				return chiediTorre(color);
			}
			return null;
		} catch (IOException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private static Move chiediLeader() {
		try {
			System.out.println("Vuoi Giocare un Leader o Scartarlo? [gioca,scarta]");
			String str = in.readLine();
			if (str.equalsIgnoreCase("gioca")) {
				return giocaLeader();
			}
			if (str.equalsIgnoreCase("scarta")) {
				return scartaLeader();
			}
			return null;
		} catch (IOException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private static Move giocaLeader() {
		try {
			System.out.println("Nome del Leader");
			String nome = in.readLine();
			return new LeaderPlaying(ViewClient.getUsername(), nome);
		} catch (IOException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private static Move scartaLeader() {
		try {
			System.out.println("Nome del Leader");
			String nome = in.readLine();
			return new LeaderDiscarding(ViewClient.getUsername(), nome);
		} catch (IOException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private static Move chiediMercato(Color color) {
		try {
			System.out.println("in quale spazio? [1,2,3,4]");
			int space = Integer.parseInt(in.readLine());
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			return new MarketMove(ViewClient.getUsername(), color, space, servant);
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private static Move chiediRaccolto(Color color) {
		try {
			System.out.println("in quale spazio? [1,2]");
			int space = Integer.parseInt(in.readLine());
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			return new HarvestMove(ViewClient.getUsername(), color, space, servant);
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private static Move chiediProduzione(Color color) {
		try {
			System.out.println("in quale spazio? [1,2]");
			int space = Integer.parseInt(in.readLine());
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			return new ProductionMove(ViewClient.getUsername(), color, space, servant);
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private static Move chiediConcilio(Color color) {
		try {
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			return new CouncilMove(ViewClient.getUsername(), color, servant);
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}
	
	private static Move chiediTorre(Color color) {
		try {
			System.out.println("in quale piano? [1,2,3,4]");
			int space = Integer.parseInt(in.readLine());
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			System.out.println("In quale torre vuoi posizionare? [building,character,territory,venture]");
			String str = in.readLine();
			if (str.equalsIgnoreCase("building")) {
				return new TowerBuildingMove(ViewClient.getUsername(), color, space, servant);
			}
			if (str.equalsIgnoreCase("character")) {
				return new TowerCharacterMove(ViewClient.getUsername(), color, space, servant);
			}
			if (str.equalsIgnoreCase("territory")) {
				return new TowerTerritoryMove(ViewClient.getUsername(), color, space, servant);
			}
			if (str.equalsIgnoreCase("venture")) {
				return new TowerVentureMove(ViewClient.getUsername(), color, space, servant);
			}
			return null;
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}

}
