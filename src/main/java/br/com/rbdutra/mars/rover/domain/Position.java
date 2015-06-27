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
	
	public void incrementX() {
		this.x++;
	}
	
	public void incrementY() {
		this.y++;
	}
	
	public void decrementX() {
		this.x--;
	}
	
	public void decrementY() {
		this.y--;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
