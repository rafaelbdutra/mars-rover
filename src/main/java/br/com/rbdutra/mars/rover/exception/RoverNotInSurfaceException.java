package br.com.rbdutra.mars.rover.exception;

public class RoverNotInSurfaceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {

		return "The rover is not in a surface";
	}

}
