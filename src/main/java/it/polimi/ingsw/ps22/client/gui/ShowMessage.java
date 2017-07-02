package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.message.CloseGame;
import it.polimi.ingsw.ps22.server.message.GenericMessage;

public class ShowMessage extends MessageDialog {

	private static final long serialVersionUID = 8937673487954589420L;
	GenericMessage mex;
	Gui gui;

	public ShowMessage(ViewClient view, GenericMessage mex, Gui gui) {
		super(view);
		this.gui=gui;
		mainPanel.setLayout(new GridLayout(0, 1));
		this.mex = mex;
		this.setTitle("Message");
		JLabel message = new JLabel(mex.getString());
		message.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(message);
		mainPanel.add(confirmButton);
		this.confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowMessage.this.dispose();
				if(mex instanceof CloseGame){
					gui.close();
				}
			}
		});
		this.setMinimumSize(new Dimension(30, 100));
	}

}
