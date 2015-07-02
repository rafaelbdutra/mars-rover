package br.com.rbdutra.mars.rover.resource;

import java.util.List;

import br.com.rbdutra.mars.rover.domain.Rover;
import br.com.rbdutra.mars.rover.domain.Surface;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class MarsRoverParam {

	private Surface surface;

	private List<Rover> rovers;

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public List<Rover> getRovers() {
		return rovers;
	}

	public void setRovers(List<Rover> rovers) {
		this.rovers = rovers;
	}

}
