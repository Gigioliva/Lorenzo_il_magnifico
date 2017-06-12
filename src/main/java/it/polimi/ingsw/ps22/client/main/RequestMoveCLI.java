package it.polimi.ingsw.ps22.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.move.*;

public class RequestMoveCLI implements RequestMove {
	
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	private String username;

	public Move requestMove(String username) {
		this.username=username;
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

	private Move chiediMossa() {
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

	private Move chiedifamiliare() {
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

	private Move chiediLeader() {
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

	private Move giocaLeader() {
		try {
			System.out.println("Nome del Leader");
			String nome = in.readLine();
			return new LeaderPlaying(username, nome);
		} catch (IOException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private Move scartaLeader() {
		try {
			System.out.println("Nome del Leader");
			String nome = in.readLine();
			return new LeaderDiscarding(username, nome);
		} catch (IOException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private Move chiediMercato(Color color) {
		try {
			System.out.println("in quale spazio? [1,2,3,4]");
			int space = Integer.parseInt(in.readLine());
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			return new MarketMove(username, color, space, servant);
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private Move chiediRaccolto(Color color) {
		try {
			System.out.println("in quale spazio? [1,2]");
			int space = Integer.parseInt(in.readLine());
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			return new HarvestMove(username, color, space, servant);
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private Move chiediProduzione(Color color) {
		try {
			System.out.println("in quale spazio? [1,2]");
			int space = Integer.parseInt(in.readLine());
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			return new ProductionMove(username, color, space, servant);
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}

	private Move chiediConcilio(Color color) {
		try {
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			return new CouncilMove(username, color, servant);
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}
	
	private Move chiediTorre(Color color) {
		try {
			System.out.println("in quale piano? [1,2,3,4]");
			int space = Integer.parseInt(in.readLine());
			System.out.println("Quanti servitori vuoi spendere?");
			int servant = Integer.parseInt(in.readLine());
			System.out.println("In quale torre vuoi posizionare? [building,character,territory,venture]");
			String str = in.readLine();
			if (str.equalsIgnoreCase("building")) {
				return new TowerBuildingMove(username, color, space, servant);
			}
			if (str.equalsIgnoreCase("character")) {
				return new TowerCharacterMove(username, color, space, servant);
			}
			if (str.equalsIgnoreCase("territory")) {
				return new TowerTerritoryMove(username, color, space, servant);
			}
			if (str.equalsIgnoreCase("venture")) {
				return new TowerVentureMove(username, color, space, servant);
			}
			return null;
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error.");
			return null;
		}
	}

}
