package it.polimi.ingsw.ps22.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import it.polimi.ingsw.ps22.server.answer.*;
import it.polimi.ingsw.ps22.server.message.*;
import it.polimi.ingsw.ps22.server.model.Color;

public class VisitorCLI extends VisitorB {

	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	private ViewClient client;
	
	public VisitorCLI(ViewClient client){
		this.client=client;
	}

	@Override
	public void visit(AskServant mex) {
		System.out.println(mex.getString());
		boolean correct = false;
		int x = 0;
		do {
			try {
				x = Integer.parseInt(stdin.readLine());
				correct = true;
			} catch (IOException | NumberFormatException e) {
				System.out.println("Errato. Scrivi un numero");
			}
		} while (!correct);
		client.send(new AnswerServant(mex.getId(), x));
	}

	@Override
	public void visit(AskCard mex) {
		System.out.println(mex.getString());
		boolean correct = false;
		String type = null;
		String name = null;
		do {
			try {
				System.out.println("Indica il tipo");
				type = stdin.readLine();
				System.out.println("Indica il nome");
				name = stdin.readLine();
				correct = true;
			} catch (IOException e) {
				System.out.println("Errato.");
			}
		} while (!correct);
		client.send(new AnswerCard(mex.getId(), type, name));
	}

	@Override
	public void visit(AskCosts mex) {
		System.out.println(mex.getString());
		boolean correct = false;
		int x = 0;
		do {
			try {
				x = Integer.parseInt(stdin.readLine());
				correct = true;
			} catch (IOException | NumberFormatException e) {
				System.out.println("Errato. Scrivi un numero");
			}
		} while (!correct);
		client.send(new AnswerCosts(mex.getId(), x));
	}

	@Override
	public void visit(AskCouncilPrivilege mex) {
		System.out.println(mex.getString());
		boolean correct = false;
		ArrayList<Integer> x = new ArrayList<Integer>();
		String temp = null;
		do {
			try {
				temp = stdin.readLine();
				x = extractingNumbers(temp);
				correct = true;
			} catch (IOException | NumberFormatException e) {
				System.out.println("Errato. Scrivi un numero");
			}
		} while (!correct);
		client.send(new AnswerCouncilPrivilege(mex.getId(), x));
	}

	@Override
	public void visit(AskEffect mex) {
		System.out.println(mex.getString());
		boolean correct = false;
		ArrayList<Integer> x = new ArrayList<Integer>();
		String temp = null;
		do {
			try {
				temp = stdin.readLine();
				x = extractingNumbers(temp);
				correct = true;
			} catch (IOException | NumberFormatException e) {
				System.out.println("Errato. Scrivi un numero");
			}
		} while (!correct);
		client.send(new AnswerEffect(mex.getId(), x));
	}

	@Override
	public void visit(AskExcomm mex) {
		System.out.println(mex.getString());
		boolean correct = false;
		String answer = null;
		do {
			try {
				answer = stdin.readLine();
				correct = true;
			} catch (IOException e) {
				System.out.println("Errato.");
			}
		} while (!correct);
		client.send(new AnswerExcomm(mex.getId(), answer));
	}

	@Override
	public void visit(ChatMessage mex) {
		System.out.println(mex.getString());
	}

	@Override
	public void visit(ErrorMove mex) {
		System.out.println(mex.getString());
	}

	@Override
	public void visit(GenericMessage mex) {
		System.out.println(mex.getString());
	}
	
	@Override
	public void visit(EndDraft mex) {
		System.out.println(mex.getString());
	}

	@Override
	public void visit(AskLeader mex) {
		System.out.println(mex.getString());
		boolean correct = false;
		String name = null;
		do {
			try {
				name = stdin.readLine();
				correct = true;
			} catch (IOException e) {
				System.out.println("Errato.");
			}
		} while (!correct);
		client.send(new AnswerLeader(mex.getId(), name));
	}

	@Override
	public void visit(AskCopyLeader mex) {
		System.out.println(mex.getString());
		boolean correct = false;
		String name = null;
		do {
			try {
				name = stdin.readLine();
				correct = true;
			} catch (IOException e) {
				System.out.println("Errato.");
			}
		} while (!correct);
		client.send(new AnswerCopyLeader(mex.getId(), name));
	}

	@Override
	public void visit(ChoiceMove mex) {
		System.out.println(mex.getString());
	}
	
	@Override
	public void visit(AskUsername mex){
		System.out.println(mex.getString());
		boolean correct = false;
		String name = null;
		String pass = null;
		int x=0;
		boolean reg=false;
		do {
			try {
				System.out.print("Username: ");
				name = stdin.readLine();
				System.out.print("Password: ");
				pass=stdin.readLine();
				System.out.print("Numero Giocatori: ");
				x=Integer.parseInt(stdin.readLine());
				System.out.print("New User?: ");
				reg=Boolean.parseBoolean(stdin.readLine());
				correct = true;
			} catch (IOException e) {
				System.out.println("Errato.");
			}
		} while (!correct);
		client.send(new AnswerUsername(name, pass, x, reg));
	}
	
	public void visit(AskFamily mex){
		System.out.println(mex.getString());
		boolean correct = false;
		Color color = null;
		do {
			try {
				color = Color.Conversion(stdin.readLine());
				correct = true;
			} catch (IOException | IllegalArgumentException e) {
				System.out.println("Errato.");
			}
		} while (!correct);
		client.send(new AnswerFamily(mex.getId(), color));	
	}
	
	public void visit(EndGame mex){
		System.out.println(mex.getString());
	}

	private static ArrayList<Integer> extractingNumbers(String s) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(s);
		while (m.find()) {
			numbers.add(Integer.parseInt(m.group()));
		}
		if (numbers.size() <= 0) {
			numbers.add(-1);
		}
		return numbers;
	}
}
