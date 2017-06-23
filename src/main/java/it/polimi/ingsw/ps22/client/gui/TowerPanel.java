package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import it.polimi.ingsw.ps22.server.model.Model;

public class TowerPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4631811818202113054L;
	
	private JButton b;
	private transient Rectangle dim;
	private String tower;
	private int space;
	
	private ChangeListener listener;
	

	public TowerPanel(Rectangle dim, String tower, int space){
		JButton b = new JButton();
		
		b.setEnabled(true);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
	
		this.b = b;
		this.tower = tower;
		this.space = space;
		
		
		this.setLayout(new GridLayout(1, 1));
		this.add(b);
		this.setOpaque(false);
		
		this.dim = dim;
		
		setMeasures(dim);
	    
		listener = new BorderEffect(b);
		
	
	}
	
	public void setCard(String path){
		ImageIcon cardIcon = MyImage.createScaledImageIcon(path, dim);
		b.setIcon(cardIcon);
		b.addMouseListener(new MyMouse(BoardPanel.zoomedCard, path));
		b.getModel().addChangeListener(listener);
	}
	
	private void setMeasures(Rectangle dim){
		this.setBounds(dim.getInitx(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
	}
	
	public void update(Model model){
		if(model.getBoard().getTower(tower).getTowerSpaces()[space].getCard() != null){
			String path = CardPath.getDevCardPathname(model.getBoard().getTower(tower).getTowerSpaces()[space].getCard());
			setCard(path);
			repaint();
		}
		else {
			b.setIcon(null);
			b.getModel().removeChangeListener(listener);
		}
	}

}
