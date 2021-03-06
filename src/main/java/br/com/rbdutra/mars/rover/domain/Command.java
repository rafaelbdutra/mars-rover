package br.com.rbdutra.mars.rover.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rbdutra.mars.rover.domain.Rover.FaceDirection;
import br.com.rbdutra.mars.rover.exception.PositionAlreadyFilledException;

public enum Command {

	L, R, M;

	private Logger logger = LoggerFactory.getLogger(Command.class);

	public void execute(Rover rover) throws PositionAlreadyFilledException {

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

	private void move(Rover rover) throws PositionAlreadyFilledException {

		Position newPosition = getPositioner(rover).get();
		FaceDirection faceDirection = rover.getFaceDirection();

		logger.info(String.format(
				"Moving rover [%d] to %s. fromX=%d, toX=%d, fromY=%d, toY=%d",
				rover.getId(), faceDirection.getDescription(), rover
						.getPosition().getX(), newPosition.getX(), rover
						.getPosition().getY(), newPosition.getY()));

		if (rover.getSurface().isPositionFilled(newPosition))
			throw new PositionAlreadyFilledException(rover, newPosition);

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
