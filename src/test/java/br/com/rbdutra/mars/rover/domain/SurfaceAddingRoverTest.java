package br.com.rbdutra.mars.rover.domain;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rbdutra.mars.rover.domain.Rover.FaceDirection;
import br.com.rbdutra.mars.rover.exception.InvalidPositionException;

public class SurfaceAddingRoverTest {

	private Surface surface;
	private Rover validRover1;
	private Rover validRover2;
	private Rover invalidRover1;
	private Rover invalidRover2;
	private Rover samePositionRover1;
	private Rover samePositionRover2;

	@Before
	public void setUp() {

		this.surface = new Surface(10, 10);
		this.validRover1 = new Rover(new Position(1, 1), FaceDirection.N);
		this.validRover2 = new Rover(new Position(2, 2), FaceDirection.E);
		this.invalidRover1 = new Rover(new Position(-1, 15), FaceDirection.S);
		this.invalidRover2 = new Rover(new Position(0, 0), FaceDirection.W);
		this.samePositionRover1 = new Rover(new Position(6, 6), FaceDirection.N);
		this.samePositionRover2 = new Rover(new Position(6, 6), FaceDirection.S);
	}

	@Test
	public void testAddValidRovers() {

		surface.addRovers(Arrays.asList(validRover1, validRover2));
		Assert.assertSame(validRover1, surface.getRovers().get(0));
		Assert.assertSame(validRover2, surface.getRovers().get(1));
	}

	@Test(expected = InvalidPositionException.class)
	public void testAddInvalidPositionRovers() {

		surface.addRovers(Arrays.asList(invalidRover1, invalidRover2));
	}
	
	@Test
	public void testAddSamePositionRovers() {
		
		surface.addRovers(Arrays.asList(samePositionRover1));
		Assert.assertSame(samePositionRover1, surface.getRovers().get(0));
		
		surface.addRovers(Arrays.asList(samePositionRover2));
		Assert.assertTrue(surface.getRovers().size() == 1);
	}
	
	@Test
	public void testAddNullRover() {
		
		surface.addRovers(null);
		Assert.assertTrue(surface.getRovers().isEmpty());
	}
}
