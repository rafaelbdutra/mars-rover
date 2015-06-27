package br.com.rbdutra.mars.rover.domain.movement;

import br.com.rbdutra.mars.rover.domain.Position;
import br.com.rbdutra.mars.rover.domain.Rover.FaceDirection;

public class MoveToNorth implements Movable {
	
	private FaceDirection faceDirection;
	
	public MoveToNorth(FaceDirection faceDirection) {
		this.faceDirection = faceDirection;
	}

	@Override
	public void changePosition(Position position) {
		position.incrementY();
	}

	@Override
	public String getDirectionName() {
		return faceDirection.getDescription();
	}

}
