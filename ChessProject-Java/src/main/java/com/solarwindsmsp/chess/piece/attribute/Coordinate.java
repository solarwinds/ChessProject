package com.solarwindsmsp.chess.piece.attribute;

/**
 * Class to represent a coordinate (X,Y).
 *
 * @author jr185235
 *
 */
public class Coordinate {

	/** Position X. */
	private int xCoordinate = -1;
	/** Position Y. */
	private int yCoordinate = -1;

	/**
	 * Constructor.
	 *
	 * @param xCoordinate - The position X.
	 * @param yCoordinate - The position Y.
	 */
	public Coordinate(final int xCoordinate, final int yCoordinate) {
		super();
		setxCoordinate(xCoordinate);
		setyCoordinate(yCoordinate);
	}

	/** @return {@link xCoordinate}. */
	public int getxCoordinate() {
		return xCoordinate;
	}

	/** @return {@link yCoordinate}. */
	public int getyCoordinate() {
		return yCoordinate;
	}

	/** @param {@link xCoordinate} to be set. */
	public void setxCoordinate(final int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	/** @param {@link yCoordinate} to be set. */
	public void setyCoordinate(final int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

}
