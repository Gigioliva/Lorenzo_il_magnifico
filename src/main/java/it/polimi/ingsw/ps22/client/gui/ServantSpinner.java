package it.polimi.ingsw.ps22.client.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ServantSpinner extends JPanel {

	JSpinner spin;
	
	public ServantSpinner() {
		JLabel spinLab = new JLabel("servant");
		this.add(spinLab);
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);  
		spin = new JSpinner(model);
		spinLab.setLabelFor(spin);
		this.add(spin);
		
		this.setBackground(java.awt.Color.GRAY);
	}
	
	public JSpinner getSpin(){
		return this.spin;
	}

}
