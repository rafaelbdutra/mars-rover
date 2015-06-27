package br.com.rbdutra.mars.rover.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Command {

	L("Turn Left"), R("Turn Rigth"), M("Move Forward");

	private Logger logger = LoggerFactory.getLogger(Command.class);

	private String INVALID_MOVE_MESSAGE = "Cannot move to %s. Position contain another rover.";

	private String description;

	private Command(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void execute(Rover rover) {

		switch (this) {
		case L:
			rover.turnLeft();
			break;
		case R:
			rover.turnRigth();
			break;
		case M:
			move(rover);
			break;
		default:
			break;
		}
	}

	private void move(Rover rover) {

		Position newPosition = getPositioner(rover).get();
		String directionName = rover.getFaceDirection().getDescription();

		logger.info(String.format(
				"Moving rover to %s. fromX=%d, toX=%d, fromY=%d, toY=%d",
				directionName, rover.getPosition().getX(), newPosition.getX(),
				rover.getPosition().getY(), newPosition.getY()));

		if (rover.getSurface().isPositionFilled(newPosition))
			throw new IllegalArgumentException(String.format(
					INVALID_MOVE_MESSAGE, directionName));

		rover.setPosition(newPosition);
	}

	private PositionSupplier getPositioner(Rover rover) {

		PositionSupplier positionSupplier = null;

		switch (rover.getFaceDirection()) {
		case N:
			positionSupplier = () -> rover.getPosition().incrementY();
			break;
		case S:
			positionSupplier = () -> rover.getPosition().decrementY();
			break;
		case W:
			positionSupplier = () -> rover.getPosition().decrementX();
			break;
		case E:
			positionSupplier = () -> rover.getPosition().incrementX();
			break;
		default:
			break;
		}

		return positionSupplier;
	}

}
