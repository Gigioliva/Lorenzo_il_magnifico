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

	public GenericMessage visit(AskServant mex) {
		if (user.equals(user_mex)) {
			return new MessageAsk(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public GenericMessage visit(AskCard mex) {
		if (user.equals(user_mex)) {
			return new MessageAsk(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public GenericMessage visit(AskCosts mex) {
		if (user.equals(user_mex)) {
			return new MessageAsk(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public GenericMessage visit(AskCouncilPrivilege mex) {
		if (user.equals(user_mex)) {
			return new MessageAsk(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public GenericMessage visit(AskEffect mex) {
		if (user.equals(user_mex)) {
			return new MessageAsk(mex.getString(),mex.getId());
		} else{
			return null;
		}
	}

	public GenericMessage visit(AskExcomm mex) {
		if (user.equals(mex.getPlayer().getUsername())) {
			return new MessageAsk(mex.getString(),mex.getId());
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

}
