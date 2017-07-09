package it.polimi.ingsw.ps22.client.gui;

import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.message.AskCard;
import it.polimi.ingsw.ps22.server.message.AskCopyLeader;
import it.polimi.ingsw.ps22.server.message.AskCosts;
import it.polimi.ingsw.ps22.server.message.AskCouncilPrivilege;
import it.polimi.ingsw.ps22.server.message.AskEffect;
import it.polimi.ingsw.ps22.server.message.AskExcomm;
import it.polimi.ingsw.ps22.server.message.AskFamily;
import it.polimi.ingsw.ps22.server.message.AskLeader;
import it.polimi.ingsw.ps22.server.message.AskServant;
import it.polimi.ingsw.ps22.server.message.ChatMessage;
import it.polimi.ingsw.ps22.server.message.ChoiceMove;
import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoardPanel board;
	private transient ViewClient view;
	private Model model;

	public Gui(ViewClient view) {
		this.view = view;
		this.setUndecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			Logger logger = Logger.getLogger(Gui.class.getName());
			logger.info(e.getMessage());
		}
	}

	public void initGui(Model model, LoginFrame login) {
		login.closeLoginWindow();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.pack();
		this.setVisible(true);

		this.model = model;
		board = new BoardPanel(this.getWidth(), this.getHeight(), view.getUsername(), view, model, this.getGui());

		// personalBoard = new PersonalBoardPanel(this.getWidth(),
		// this.getHeight(), username);

		this.add(board);
		// this.add(personalBoard);

		if (System.getProperty("os.name").contains("Windows")) {
			Music.playMP3("src/main/java/it/polimi/ingsw/ps22/client/gui/music/sound.mp3");
		} else {
			Thread temp = new Thread(new Runnable() {
				@Override
				public void run() {
					Music.playMP3("src/main/java/it/polimi/ingsw/ps22/client/gui/music/sound.mp3");
				}
			});
			temp.start();
		}
	}

	private Gui getGui() {
		return this;
	}

	public void updateGui(Model model) {
		board.updateBoard(model);
	}

	public void askPrivilege(AskCouncilPrivilege mex) {
		(new PrivilegeDialog(view, mex)).setVisible(true);
		return;
	}

	public void getCard(AskCard mex) {
		(new AskCardDialog(view, mex)).setVisible(true);
	}

	public void askCosts(AskCosts mex) {
		(new AskCostDialog(view, mex)).setVisible(true);
	}

	public void askEffect(AskEffect mex) {
		(new AskEffectDialog(view, mex)).setVisible(true);
	}

	public void askFamily(AskFamily mex) {
		(new AskFamilyDialog(view, mex)).setVisible(true);
	}

	public void askServants(AskServant mex) {
		(new AskServantDialog(view, mex)).setVisible(true);
	}

	public void askCopyLeader(AskCopyLeader mex) {
		(new AskCopyLeaderDialog(view, mex)).setVisible(true);
	}

	public void askLeader(AskLeader mex) {
		(new AskLeaderDialog(view, mex)).setVisible(true);
	}

	public void setLeaders() {
		board.setLeaders(model);
	}

	public void errorMove(ErrorMove mex) {
		(new ShowMessage(view, mex, this)).setVisible(true);
	}

	public void askExcomm(AskExcomm mex) {
		(new AskExcommDialog(view, mex)).b1.setVisible(true);
	}

	public void yourTurn(ChoiceMove mex) {
		board.setIsYourTurn();
		(new ShowMessage(view, mex, this)).setVisible(true);
	}

	public void genericMessage(GenericMessage mex) {
		(new ShowMessage(view, mex, this)).setVisible(true);
	}
	
	public void showChatMessage(ChatMessage mex){
		board.addChatMessage(mex.getString());
	}

	public void close() {
		this.dispose();
		Music.stopMP3();
		view.close();
	}

}
