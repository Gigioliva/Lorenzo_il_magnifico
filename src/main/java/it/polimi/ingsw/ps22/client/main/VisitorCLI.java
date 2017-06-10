package it.polimi.ingsw.ps22.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import it.polimi.ingsw.ps22.server.answer.*;
import it.polimi.ingsw.ps22.server.message.*;

public class VisitorCLI extends VisitorB {

	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public AnswerServant visit(AskServant mex) {
		System.out.println(mex.getString() + "prova");
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
		return new AnswerServant(mex.getId(), x);
	}

	@Override
	public AnswerCard visit(AskCard mex) {
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
		return new AnswerCard(mex.getId(), type, name);
	}

	@Override
	public AnswerCosts visit(AskCosts mex) {
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
		return new AnswerCosts(mex.getId(), x);
	}

	@Override
	public AnswerCouncilPrivilege visit(AskCouncilPrivilege mex) {
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
		return new AnswerCouncilPrivilege(mex.getId(), x);
	}

	@Override
	public AnswerEffect visit(AskEffect mex) {
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
		return new AnswerEffect(mex.getId(), x);
	}

	@Override
	public AnswerExcomm visit(AskExcomm mex) {
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
		return new AnswerExcomm(mex.getId(), answer);
	}

	@Override
	public GenericAnswer visit(ChatMessage mex) {
		System.out.println(mex.getString());
		return null;
	}

	@Override
	public GenericAnswer visit(ErrorMove mex) {
		System.out.println(mex.getString());
		return null;
	}

	@Override
	public GenericAnswer visit(GenericMessage mex) {
		System.out.println(mex.getString());
		return null;
	}

	@Override
	public AnswerLeader visit(AskLeader mex) {
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
		return new AnswerLeader(mex.getId(), name);
	}

	@Override
	public AnswerCopyLeader visit(AskCopyLeader mex) {
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
		return new AnswerCopyLeader(mex.getId(), name);
	}

	@Override
	public GenericAnswer visit(ChoiceMove mex) {
		System.out.println(mex.getString());
		return null;
	}
	
	@Override
	public AnswerUsername visit(AskUsername mex){
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
		return new AnswerUsername(name);
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
