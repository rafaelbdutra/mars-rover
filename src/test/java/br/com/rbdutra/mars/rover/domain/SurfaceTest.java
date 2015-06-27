package br.com.rbdutra.mars.rover.domain;


import org.junit.Assert;
import org.junit.Test;

public class SurfaceTest {
	
	private static final Integer INVALID_MIN_HEIGHT = Surface.MIN_HEIGTH - 1;
	private static final Integer INVALID_MIN_WIDTH = Surface.MIN_WIDTH - 1;
	private static final Integer INVALID_MAX_HEIGHT = Surface.MAX_HEIGTH + 1;
	private static final Integer INVALID_MAX_WIDTH = Surface.MAX_WIDTH + 1;

	@Test
	public void testValidSurface() {
		
		Surface surface = new Surface(Surface.MAX_HEIGTH, Surface.MAX_WIDTH);
		Assert.assertNotNull(surface);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMinHeight() {
		
		new Surface(INVALID_MIN_HEIGHT, Surface.MAX_WIDTH);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMinWidth() {
		
		new Surface(Surface.MAX_HEIGTH, INVALID_MIN_WIDTH);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMaxHeight() {
		
		new Surface(INVALID_MAX_HEIGHT, Surface.MAX_WIDTH);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMaxWidth() {
		
		new Surface(Surface.MAX_HEIGTH, INVALID_MAX_WIDTH);
	}
	
}
