package br.com.rbdutra.mars.rover.domain;

public enum Command {

	L("Turn Left"), R("Turn Rigth"), M("Move Forward");

	private String description;

	private Command(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
