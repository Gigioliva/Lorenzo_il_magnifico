package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

import it.polimi.ingsw.ps22.server.player.Family;

public class HarvestLabel extends JLabel {

	private static final long serialVersionUID = -221005325565851136L;
	ArrayList<Family> familiars = new ArrayList<Family>();
	double resizeFactor;
	
	public HarvestLabel(double resizeFact) {
		super();
		this.resizeFactor = resizeFact;
		this.setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<familiars.size(); i++){
			Rectangle dim = AdaptiveLayout.getProdRightFamSpace(resizeFactor, i);
			Rectangle dim0 = AdaptiveLayout.getProdRightFamSpace(resizeFactor, 0);
			String path = FamilyPath.getFamilyPathname(familiars.get(i).getPlayer().getColor().getColor(), familiars.get(i).getColor());
			g.drawImage(MyImage.createScaledImageIcon(path, dim).getImage(), dim.getInitx() - dim0.getInitx(), 
					dim.getInity() - dim0.getInity(), null);
		}
		
	}
	
	public void updateHarvLabel(ArrayList<Family> fam){
		familiars = fam;
		this.repaint();
	}

}
