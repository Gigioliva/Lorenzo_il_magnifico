package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ServantSpinner extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4913189729334223962L;
	JSpinner spin;
	 ArrayList<ActionButton> actionSpaces;
	
	public ServantSpinner() {
		JLabel spinLab = new JLabel("servant");
		this.setOpaque(false);
		this.add(spinLab);
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);  
		spin = new JSpinner(model);
		spinLab.setLabelFor(spin);
		this.add(spin);
		
	}
	
	public ServantSpinner( ArrayList<ActionButton> actionSpaces) {
		JLabel spinLab = new JLabel("Add servant:");
		this.add(spinLab);
		this.setOpaque(false);
		this.actionSpaces = actionSpaces;
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);  
		spin = new JSpinner(model);
		spinLab.setLabelFor(spin);
		this.add(spin);
		
	}
	
	public JSpinner getSpin(){
		return this.spin;
	}
	
	public void updateSpin(){
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1); 
		spin.setModel(model);
		for(int i = 0; i<actionSpaces.size(); i++){
			actionSpaces.get(i).numServants = 0;
		}
	}

}
