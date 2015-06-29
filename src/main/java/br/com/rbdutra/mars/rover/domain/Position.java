package br.com.rbdutra.mars.rover.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Position {

	private Integer x;

	private Integer y;

	public Position(Integer x, Integer y) {

		this.x = x;
		this.y = y;
	}

	public Position(Position position) {

		this.x = position.getX();
		this.y = position.getY();
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Position incrementX() {

		Position newPosition = new Position(this);
		newPosition.setX(newPosition.getX() + 1);

		return newPosition;
	}

	public Position incrementY() {

		Position newPosition = new Position(this);
		newPosition.setY(newPosition.getY() + 1);

		return newPosition;
	}

	public Position decrementX() {

		Position newPosition = new Position(this);
		newPosition.setX(newPosition.getX() - 1);

		return newPosition;
	}

	public Position decrementY() {

		Position newPosition = new Position(this);
		newPosition.setY(newPosition.getY() - 1);

		return newPosition;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return new StringBuilder() //
				.append(x)
				.append(" ") //
				.append(y) //
				.toString();
	}

}
