package it.polimi.ingsw.ps22.client.main;

import javax.swing.SwingUtilities;
import it.polimi.ingsw.ps22.client.gui.Gui;
import it.polimi.ingsw.ps22.server.model.Model;

public class GraphicGUI extends Graphic {

	private Gui gui;
	public boolean flag = true;
	private ViewClient view;

	public GraphicGUI(ViewClient view) {
		this.gui=new Gui();
		this.view = view;
		requestMove = new RequestMoveGUI();
		visitor = new VisitorGUI(gui, view);
	}

	@Override
	public void printModel(Model model) {
		if (flag) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					gui.initGui(model, view);
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
