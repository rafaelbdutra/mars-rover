package br.com.rbdutra.mars.rover.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Surface {
	
	private Logger logger = LoggerFactory.getLogger(Surface.class);

	public static final Integer MIN_HEIGTH = Integer.valueOf(1);
	public static final Integer MIN_WIDTH = Integer.valueOf(1);
	public static final Integer MAX_HEIGTH = Integer.valueOf(50);
	public static final Integer MAX_WIDTH = Integer.valueOf(50);

	private static final String INVALID_SURFACE_PARAMS_MSG = "Illegal surface parameters."
			+ " Must be: %d <= height <= %d and %d <= width <= %d."
			+ " Actual height=%d, width=%d";
	private static final String INVALID_POSITION_PARAMS_MSG = "Illegal position parameters."
			+ " Must be: %d <= X <= %d and %d <= Y <= %d."
			+ " Actual X=%d, Y=%d";

	private Integer height;

	private Integer width;

	private List<Rover> rovers;

	public Surface(Integer height, Integer width)
			throws IllegalArgumentException {
		
		logger.info(String.format("Creating surface with height=%d, witdh=%d", height, width));

		if (!isValidCoordinates(height, width))
			throw new IllegalArgumentException(String.format(
					INVALID_SURFACE_PARAMS_MSG, MIN_HEIGTH, MAX_HEIGTH,
					MIN_WIDTH, MAX_WIDTH, height, width));

		this.height = height;
		this.width = width;
		this.rovers = new ArrayList<Rover>();
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	private Boolean isValidCoordinates(Integer height, Integer width)
			throws IllegalArgumentException {

		if (height == null || width == null)
			return Boolean.FALSE;

		if (height >= MIN_HEIGTH && width >= MIN_WIDTH && height <= MAX_HEIGTH
				&& width <= MAX_WIDTH)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	private Boolean isValidPositionInSurface(Position position) {

		if (position == null || position.getX() == null
				|| position.getY() == null)
			return Boolean.FALSE;

		if (position.getX() >= MIN_WIDTH && position.getX() <= this.width
				&& position.getY() >= MIN_HEIGTH
				&& position.getY() <= this.height)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	// Rover operations
	public List<Rover> getRovers() {
		return rovers;
	}

	public void addRovers(List<Rover> rovers) throws IllegalArgumentException {

		if (CollectionUtils.isEmpty(rovers))
			return;

		for (Rover rover : rovers)
			addRover(rover);
	}

	public void addRover(Rover rover) throws IllegalArgumentException {

		if (rover == null)
			return;

		if (!isPositionFilled(rover.getPosition())) {
		
			this.rovers.add(rover);
			rover.setSurface(this);
		}
	}

	public Boolean isPositionFilled(Position position)
			throws IllegalArgumentException {

		if (!isValidPositionInSurface(position))
			throw new IllegalArgumentException(String.format(INVALID_POSITION_PARAMS_MSG, MIN_WIDTH, position.getX(), this.width, MIN_HEIGTH, position.getY(), this.height));

		return rovers.stream().anyMatch(r -> position.equals(r.getPosition()));
	}
}
