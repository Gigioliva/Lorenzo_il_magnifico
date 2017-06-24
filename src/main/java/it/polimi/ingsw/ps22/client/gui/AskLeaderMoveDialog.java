package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.move.LeaderDiscarding;
import it.polimi.ingsw.ps22.server.move.LeaderPlaying;

public class AskLeaderMoveDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6480972698424761333L;
	private ButtonGroup group = new ButtonGroup();
	private JRadioButton b1;
	private JRadioButton b2;
	private JButton confirmButton = new JButton("Confirm");
	private JPanel mainPanel = new JPanel();
	private transient ViewClient view;
	private String username;
	private LeaderButton leaderB;
	
	public AskLeaderMoveDialog(ViewClient view, String username, LeaderButton leaderB){
		super();
		
		mainPanel.setLayout(new GridLayout(0, 1));
		
		this.setTitle("Which move do you wanna do? ");
		this.username = username;
		this.view = view;
		this.leaderB = leaderB;
		
		b1 = new JRadioButton("Discard Leader");
		group.add(b1);
		mainPanel.add(b1);
		
		b2 = new JRadioButton("Play Leader");
		group.add(b2);
		mainPanel.add(b2);

	    mainPanel.add(confirmButton);
	    
	    confirmButton.addActionListener(new ConfirmListener());
		
	    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                //frame.dispose();
            }
        });
        this.setVisible(true);
        this.add(mainPanel);
        pack();
        setMinimumSize(new Dimension(300, 30));
        setVisible(true);
	}
	
	
	private class ConfirmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (b1.isSelected()){
				view.send(new LeaderDiscarding(username, leaderB.getCardName()));
				System.out.println(leaderB.getCardName());
				leaderB.setEnabled(false);
				leaderB.setVisible(false);
				AskLeaderMoveDialog.this.dispose();
			}
			else if (b2.isSelected()){
				view.send(new LeaderPlaying(username, leaderB.getCardName()));
				System.out.println("gioco " + leaderB.getCardName() );
				AskLeaderMoveDialog.this.dispose();
			}
			
		}		
	}


}
