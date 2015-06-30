package br.com.rbdutra.mars.rover.exception;

import br.com.rbdutra.mars.rover.domain.Position;
import br.com.rbdutra.mars.rover.domain.Rover;

public class PositionAlreadyFilledException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String POSITION_ALREADY_FILLED_MESSAGE = "Cannot move rover [%d] to %s. Position [%d, %d] contain another rover.";

	public PositionAlreadyFilledException(Rover rover, Position position) {

		super(String.format(POSITION_ALREADY_FILLED_MESSAGE, rover.getId(),
				rover.getFaceDirection().getDescription(), position.getX(),
				position.getY()));
	}
}
