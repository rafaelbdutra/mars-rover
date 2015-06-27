package br.com.rbdutra.mars.rover.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rbdutra.mars.rover.domain.movement.Movable;
import br.com.rbdutra.mars.rover.domain.movement.MoveToEast;
import br.com.rbdutra.mars.rover.domain.movement.MoveToNorth;
import br.com.rbdutra.mars.rover.domain.movement.MoveToSouth;
import br.com.rbdutra.mars.rover.domain.movement.MoveToWest;

public class Rover {
	
	private static final Logger logger = LoggerFactory.getLogger(Rover.class);
	
	private static final String NOT_ON_A_SURFACE_MSG = "Rover is not on a surface";

	public enum FaceDirection {

		N("North"), E("East"), S("South"), W("West");

		private String description;

		FaceDirection(String description) {

			this.description = description;
		}

		public String getDescription() {
			return description;
		}
		
		FaceDirection getFaceDirection(Command command) {
			
			switch (command) {
			case L:
				return getPrevious();
			case R:
				return getNext();
			default:
				return this;
			}
		}
		
		private FaceDirection getPrevious() {
			return 0 == this.ordinal() ? W : FaceDirection.values()[this.ordinal() - 1];
		}
		
		private FaceDirection getNext() {
			return FaceDirection.values().length - 1 == this.ordinal() ? N : FaceDirection.values()[this.ordinal() + 1];
		}
	}

	private Position position;

	private FaceDirection faceDirection;
	
	private Surface surface;

	public Rover(Position initialPosition, FaceDirection initialFaceDirection) {

		this.position = initialPosition;
		this.faceDirection = initialFaceDirection;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public FaceDirection getFaceDirection() {
		return faceDirection;
	}

	public void setFaceDirection(FaceDirection faceDirection) {
		this.faceDirection = faceDirection;
	}

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}
	
	public void applyCommand(List<Command> commands) throws IllegalArgumentException {
		
		for (Command command : commands)
			applyCommand(command);
	}

	public void applyCommand(Command command) throws IllegalArgumentException {
		
		if (this.surface == null)
			throw new IllegalArgumentException(NOT_ON_A_SURFACE_MSG);

		if (Command.M.equals(command))
			move();
		else {
			
			logger.info(String.format("Executing command [%s]", command.getDescription()));
			setFaceDirection(faceDirection.getFaceDirection(command));
		}
	}
	
	private void move() {
		
		Movable movable = null;
		
		switch (faceDirection) {
		case N:
			movable = new MoveToNorth(FaceDirection.N);
			break;
		case E:
			movable = new MoveToEast(FaceDirection.E);
			break;
		case S:
			movable = new MoveToSouth(FaceDirection.S);
			break;
		case W:
			movable = new MoveToWest(FaceDirection.W);
			break;
		default:
			return;
		}
		
		setPosition(movable.move(position, surface));
	}
}
