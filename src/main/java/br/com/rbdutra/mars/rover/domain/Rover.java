package br.com.rbdutra.mars.rover.domain;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rbdutra.mars.rover.exception.RoverNotInSurfaceException;

public class Rover {

	private static final Logger logger = LoggerFactory.getLogger(Rover.class);

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
			return 0 == this.ordinal() ? W : FaceDirection.values()[this
					.ordinal() - 1];
		}

		private FaceDirection getNext() {
			return FaceDirection.values().length - 1 == this.ordinal() ? N
					: FaceDirection.values()[this.ordinal() + 1];
		}
	}

	private Integer id;

	private Position position;

	private FaceDirection faceDirection;

	private Surface surface;

	private List<Command> commands;

	public Rover(Position initialPosition, FaceDirection initialFaceDirection) {

		logger.info(String.format("Creating rover at [%s] faced to %s",
				initialPosition, initialFaceDirection.getDescription()));

		this.position = initialPosition;
		this.faceDirection = initialFaceDirection;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public void turnLeft() {

		logger.info(String.format("Turning rover [%d] to left", this.id));
		this.faceDirection = faceDirection.getPrevious();
	}

	public void turnRigth() {

		logger.info(String.format("Turning rover [%d] to rigth", this.id));
		this.faceDirection = faceDirection.getNext();
	}

	public void applyCommands() throws RoverNotInSurfaceException {
		this.applyCommands(this.commands);
	}

	public void applyCommands(List<Command> commands)
			throws RoverNotInSurfaceException {

		if (CollectionUtils.isEmpty(commands)) {
			logger.warn(String.format(
					"There is not command to apply to rover [%d]", this.id));
			return;
		}

		for (Command command : commands)
			applyCommand(command);
	}

	public void applyCommand(Command command) throws RoverNotInSurfaceException {

		if (this.surface == null)
			throw new RoverNotInSurfaceException();

		command.execute(this);
	}

	@Override
	public String toString() {

		return new StringBuilder() //
				.append(position) //
				.append(" ") //
				.append(faceDirection.name()) //
				.toString();
	}

}
