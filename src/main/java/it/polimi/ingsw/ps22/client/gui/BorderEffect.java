package it.polimi.ingsw.ps22.client.gui;

import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BorderEffect implements ChangeListener {
	
	JButton b;

	public BorderEffect(JButton b) {
		this.b = b;
	    
	    
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		b.setBorderPainted(true);
		final Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
		final Insets insets = raisedBevelBorder.getBorderInsets(b);
	    final EmptyBorder emptyBorder = new EmptyBorder(insets);
		ButtonModel model = (ButtonModel) e.getSource();
        if (model.isRollover()) {
            b.setBorder(raisedBevelBorder);
        } else {
            b.setBorder(emptyBorder);
        }
		
	}

}
