package it.polimi.ingsw.ps22.view;

import it.polimi.ingsw.ps22.message.*;

public class Visitor {

	private String user;
	private String user_mex;

	public Visitor() {

	}

	public Visitor(String user, String user_mex) {
		this.user = user;
		this.user_mex = user_mex;
	}

	public AskServant visit(AskServant mex) {
		if (user.equals(user_mex)) {
			return new AskServant(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public AskCard visit(AskCard mex) {
		if (user.equals(user_mex)) {
			return new AskCard(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public AskCosts visit(AskCosts mex) {
		if (user.equals(user_mex)) {
			return new AskCosts(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public AskCouncilPrivilege visit(AskCouncilPrivilege mex) {
		if (user.equals(user_mex)) {
			return new AskCouncilPrivilege(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public AskEffect visit(AskEffect mex) {
		if (user.equals(user_mex)) {
			return new AskEffect(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public AskExcomm visit(AskExcomm mex) {
		if (user.equals(mex.getPlayer().getUsername())) {
			return new AskExcomm(mex.getString(),mex.getId());
		} else{
			return null;
		}

	}

	public GenericMessage visit(ChatMessage mex) {
		return mex;
	}

	public GenericMessage visit(ErrorMove mex) {
		if (user.equals(user_mex)) {
			return mex;
		} else{
			return null;
		}
	}
	
	public GenericMessage visit(GenericMessage mex) {
		if (user.equals(user_mex)) {
			return mex;
		} else{
			return null;
		}
	}
	
	public AskLeader visit(AskLeader mex) {
		if (user.equals(mex.getPlayer().getUsername())) {
			return new AskLeader(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

}
