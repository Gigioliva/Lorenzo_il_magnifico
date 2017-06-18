package it.polimi.ingsw.ps22.client.main;

import javax.swing.SwingUtilities;
import it.polimi.ingsw.ps22.client.gui.Gui;
import it.polimi.ingsw.ps22.server.model.Model;

public class GraphicGUI extends Graphic {

	private Gui gui;
	public boolean flag = true;

	public GraphicGUI(ViewClient view) {
		this.gui=new Gui(view);
		requestMove = new RequestMoveGUI();
		visitor = new VisitorGUI(gui, view);
	}

	@Override
	public void printModel(Model model) {
		if (flag) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					gui.initGui(model);
					gui.updateGui(model);
					flag = false;
				}
			});
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run(){
					gui.updateGui(model);
				}
			});
		}

	}

}
