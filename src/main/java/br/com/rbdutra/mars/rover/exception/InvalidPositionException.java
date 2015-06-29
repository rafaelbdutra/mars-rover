package br.com.rbdutra.mars.rover.exception;

import br.com.rbdutra.mars.rover.domain.Position;
import br.com.rbdutra.mars.rover.domain.Surface;

public class InvalidPositionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String INVALID_POSITION_PARAMS_MSG = "Illegal position parameters."
			+ " Must be: %d <= X <= %d and %d <= Y <= %d."
			+ " Actual X=%d, Y=%d";

	public InvalidPositionException(Integer surfaceHeight,
			Integer surfaceWidth, Position position) {

		super(String.format(INVALID_POSITION_PARAMS_MSG, Surface.MIN_WIDTH,
				surfaceWidth, Surface.MIN_HEIGHT, surfaceHeight,
				position.getX(), position.getY()));
	}

}
