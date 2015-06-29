package br.com.rbdutra.mars.rover.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rbdutra.mars.rover.exception.IllegalSurfaceParameterException;
import br.com.rbdutra.mars.rover.exception.InvalidPositionException;

public class Surface {

	private Logger logger = LoggerFactory.getLogger(Surface.class);

	public static final Integer MIN_HEIGHT = Integer.valueOf(1);
	public static final Integer MIN_WIDTH = Integer.valueOf(1);
	public static final Integer MAX_HEIGHT = Integer.valueOf(50);
	public static final Integer MAX_WIDTH = Integer.valueOf(50);

	private Integer height;

	private Integer width;

	private List<Rover> rovers;

	public Surface(Integer height, Integer width)
			throws IllegalSurfaceParameterException {

		logger.info(String.format("Creating surface with height=%d, witdh=%d",
				height, width));

		if (!isValidCoordinates(height, width))
			throw new IllegalSurfaceParameterException(height, width);

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

	private Boolean isValidCoordinates(Integer height, Integer width) {

		if (height == null || width == null)
			return Boolean.FALSE;

		if (height >= MIN_HEIGHT && width >= MIN_WIDTH && height <= MAX_HEIGHT
				&& width <= MAX_WIDTH)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	private Boolean isValidPositionInSurface(Position position) {

		if (position == null || position.getX() == null
				|| position.getY() == null)
			return Boolean.FALSE;

		if (position.getX() >= MIN_WIDTH - 1 && position.getX() <= this.width
				&& position.getY() >= MIN_HEIGHT - 1
				&& position.getY() <= this.height)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	// Rover operations
	public List<Rover> getRovers() {
		return rovers;
	}

	public void addRovers(List<Rover> rovers) throws InvalidPositionException {

		if (CollectionUtils.isEmpty(rovers))
			return;

		for (Rover rover : rovers)
			addRover(rover);
	}

	public void addRover(Rover rover) throws InvalidPositionException {

		if (rover == null)
			return;

		if (!isPositionFilled(rover.getPosition())) {

			this.rovers.add(rover);
			rover.setSurface(this);
		}
	}

	public Boolean isPositionFilled(Position position)
			throws InvalidPositionException {

		if (!isValidPositionInSurface(position))
			throw new InvalidPositionException(this.height, this.width,
					position);

		return rovers.stream().anyMatch(
				rover -> position.equals(rover.getPosition()));
	}
}
