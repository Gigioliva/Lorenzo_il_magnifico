package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

import it.polimi.ingsw.ps22.server.player.Family;

public class CouncilLabel extends JLabel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1595555729187234344L;
	ArrayList<Family> familiars = new ArrayList<>();
	double resizeFactor;
	private AdaptiveLayout layout = AdaptiveLayout.instance();
	
	public CouncilLabel(double resizeFact) {
		super();
		this.resizeFactor = resizeFact;
		this.setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<familiars.size(); i++){
			Rectangle dim = layout.getCouncilPalaceFamSpace(resizeFactor, i);
			Rectangle dim0 = layout.getCouncilPalaceFamSpace(resizeFactor, 0);
			String path = FamilyPath.getFamilyPathname(familiars.get(i).getPlayer().getColor().getColor(), familiars.get(i).getColor());
			g.drawImage(MyImage.createScaledImageIcon(path, dim).getImage(), dim.getInitx() - dim0.getInitx(), 
					dim.getInity() - dim0.getInity(), null);
		}
		
	}
	
	public void updateCouncilLabel(ArrayList<Family> fam){
		familiars = fam;
		this.repaint();
	}

}
