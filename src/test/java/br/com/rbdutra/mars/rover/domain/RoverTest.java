package br.com.rbdutra.mars.rover.domain;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rbdutra.mars.rover.domain.Rover.FaceDirection;
import br.com.rbdutra.mars.rover.exception.InvalidPositionException;
import br.com.rbdutra.mars.rover.exception.PositionAlreadyFilledException;
import br.com.rbdutra.mars.rover.exception.RoverNotInSurfaceException;

public class RoverTest {

	private Surface surface;
	private Rover centralizedFaceToNorthRover;
	private Rover centralizedFaceToWestRover;

	@Before
	public void setUp() {

		this.surface = new Surface(10, 10);
		this.centralizedFaceToNorthRover = new Rover(new Position(6, 5),
				FaceDirection.N);
		this.centralizedFaceToWestRover = new Rover(new Position(5, 6),
				FaceDirection.W);

		this.surface.addRovers(Arrays.asList(centralizedFaceToNorthRover,
				centralizedFaceToWestRover));
	}

	@Test
	public void testTurnRigth() {

		centralizedFaceToNorthRover.applyCommand(Command.R);
		centralizedFaceToWestRover.applyCommand(Command.R);

		Assert.assertEquals(FaceDirection.E,
				centralizedFaceToNorthRover.getFaceDirection());
		Assert.assertEquals(FaceDirection.N,
				centralizedFaceToWestRover.getFaceDirection());
	}

	@Test
	public void testTurnLeft() {

		centralizedFaceToNorthRover.applyCommand(Command.L);
		centralizedFaceToWestRover.applyCommand(Command.L);

		Assert.assertEquals(FaceDirection.W,
				centralizedFaceToNorthRover.getFaceDirection());
		Assert.assertEquals(FaceDirection.S,
				centralizedFaceToWestRover.getFaceDirection());
	}

	@Test
	public void testMove() {

		centralizedFaceToNorthRover.applyCommand(Command.M);
		centralizedFaceToWestRover.applyCommand(Command.M);

		Position expectedNorthRoverPosition = new Position(6, 6);
		Position expectedWestRoverPosition = new Position(4, 6);

		Assert.assertEquals(expectedNorthRoverPosition,
				centralizedFaceToNorthRover.getPosition());
		Assert.assertEquals(expectedWestRoverPosition,
				centralizedFaceToWestRover.getPosition());

		Assert.assertEquals(FaceDirection.N,
				centralizedFaceToNorthRover.getFaceDirection());
		Assert.assertEquals(FaceDirection.W,
				centralizedFaceToWestRover.getFaceDirection());
	}

	@Test
	public void testChainOfCommands() {

		List<Command> chainOfCommands = Arrays.asList(Command.M, Command.M,
				Command.M, Command.R, Command.M, Command.R, Command.M,
				Command.R, Command.M, Command.L);

		centralizedFaceToNorthRover.applyCommands(chainOfCommands);
		centralizedFaceToWestRover.applyCommands(chainOfCommands);

		Position expectedInitialFaceToNorthRover = new Position(6, 7);
		Position expectedInitialFaceToWestRover = new Position(3, 6);

		Assert.assertEquals(expectedInitialFaceToNorthRover,
				centralizedFaceToNorthRover.getPosition());
		Assert.assertEquals(expectedInitialFaceToWestRover,
				centralizedFaceToWestRover.getPosition());

		Assert.assertEquals(FaceDirection.S,
				centralizedFaceToNorthRover.getFaceDirection());
		Assert.assertEquals(FaceDirection.E,
				centralizedFaceToWestRover.getFaceDirection());
	}

	@Test(expected = RoverNotInSurfaceException.class)
	public void testNotInSurface() {

		centralizedFaceToNorthRover.setSurface(null);
		centralizedFaceToNorthRover.applyCommand(Command.M);
	}

	@Test(expected = InvalidPositionException.class)
	public void testOutOfSurface() {

		List<Command> chainOfCommands = Arrays.asList(Command.M, Command.M,
				Command.M, Command.M, Command.M);

		centralizedFaceToNorthRover.applyCommands(chainOfCommands);

		Assert.assertEquals(new Position(6, 10),
				centralizedFaceToNorthRover.getPosition());

		centralizedFaceToNorthRover.applyCommand(Command.M);
	}

	@Test(expected = PositionAlreadyFilledException.class)
	public void testColision() {

		List<Command> chainOfCommands = Arrays.asList(Command.M, Command.L);

		centralizedFaceToNorthRover.applyCommands(chainOfCommands);

		Assert.assertEquals(new Position(6, 6),
				centralizedFaceToNorthRover.getPosition());
		Assert.assertEquals(FaceDirection.W,
				centralizedFaceToNorthRover.getFaceDirection());

		centralizedFaceToNorthRover.applyCommand(Command.M);
	}

	@Test
	public void testNoCommandsPassed() {

		Assert.assertEquals(new Position(6, 5),
				centralizedFaceToNorthRover.getPosition());
		Assert.assertEquals(FaceDirection.N,
				centralizedFaceToNorthRover.getFaceDirection());
		
		centralizedFaceToNorthRover.applyCommands();
		centralizedFaceToNorthRover.applyCommands(null);
		
		Assert.assertEquals(new Position(6, 5),
				centralizedFaceToNorthRover.getPosition());
		Assert.assertEquals(FaceDirection.N,
				centralizedFaceToNorthRover.getFaceDirection());
	}
}
