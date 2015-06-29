package br.com.rbdutra.mars.rover.exception;

import br.com.rbdutra.mars.rover.domain.Surface;

public class IllegalSurfaceParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String ILLEGAL_SURFACE_PARAMS_MSG = "Illegal surface parameters."
			+ " Must be: %d <= height <= %d and %d <= width <= %d."
			+ " Actual height=%d, width=%d";

	public IllegalSurfaceParameterException(Integer height, Integer width) {

		super(String.format(ILLEGAL_SURFACE_PARAMS_MSG, Surface.MIN_HEIGHT,
				Surface.MAX_HEIGHT, Surface.MIN_WIDTH, Surface.MAX_WIDTH,
				height, width));
	}

}
