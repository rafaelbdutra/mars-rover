package br.com.rbdutra.mars.rover.domain.movement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rbdutra.mars.rover.domain.Position;
import br.com.rbdutra.mars.rover.domain.Surface;

public interface Movable {

	Logger logger = LoggerFactory.getLogger(Movable.class);

	public String INVALID_MOVE_MESSAGE = "Cannot move to %s. Position contain another rover.";

	public default Position move(Position position, Surface surface)
			throws IllegalArgumentException {

		Position newPosition = new Position(position);
		changePosition(newPosition);

		logger.info(String.format(
				"Moving rover to %s. fromX=%d, fromY=%d, toX=%d, toY=%d",
				getDirectionName(), position.getX(), position.getY(),
				newPosition.getX(), newPosition.getY()));

		if (surface.isPositionFilled(newPosition))
			throw new IllegalArgumentException(String.format(
					INVALID_MOVE_MESSAGE, getDirectionName()));

		return newPosition;
	}

	public void changePosition(Position position);

	public String getDirectionName();
}
