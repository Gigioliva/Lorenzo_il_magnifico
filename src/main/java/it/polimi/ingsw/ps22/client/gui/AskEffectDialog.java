package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerEffect;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.message.AskEffect;

public class AskEffectDialog extends MessageDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8937673487954589420L;
	AskEffect mex;
	DevelopmentCard card;
	static ArrayList<Integer> choices = new ArrayList<>();
	ButtonGroup group = new ButtonGroup();
	ArrayList<Button> buttons = new ArrayList<>();

	public AskEffectDialog(ViewClient view, AskEffect mex) {
		super(view);

		mainPanel.setLayout(new GridLayout(0, 1));

		this.mex = mex;

		this.setTitle("Scegli tra i seguenti effetti");

		this.card = mex.getListEffect().keySet().iterator().next();

		for (int i = 0; i < mex.getListEffect().get(card).size(); i++) {
			Button b = new Button(i+1, mex.getListEffect().get(card).get(i).toString());
			buttons.add(b);
			group.add(b);
			mainPanel.add(b);
		}
		
		Button b = new Button(0, "Don't appply any effect");
		buttons.add(b);
		group.add(b);
		mainPanel.add(b);

		mainPanel.add(confirmButton);

		confirmButton.addActionListener(new ConfirmListener());

		this.setMinimumSize(new Dimension(150, 200));

	}

	private class Button extends JRadioButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = -74226909844700947L;
		private int answer;

		public Button(int answer, String text) {
			super(text);
			this.answer = answer;

		}

		public int getAnswer() {
			return this.answer;
		}
	}

	private class ConfirmListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean close = false;
			for (Button b : buttons) {
				if (b.isSelected()) {
					choices.add(b.getAnswer());
					close = true;
				}
			}
			if (close) {
				mex.getListEffect().remove(card);
				if (!mex.getListEffect().isEmpty()) {
					new AskEffectDialog(view, mex);
				} else {
					view.send(new AnswerEffect(mex.getId(), choices));
					choices.clear();
				}
				AskEffectDialog.this.dispose();
			}

		}
	}
}
