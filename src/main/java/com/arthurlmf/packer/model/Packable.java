package com.arthurlmf.packer.model;

/**
 * A base interface that all packable shall derive from.
 * @author arthurfarias
 *
 */

public interface Packable extends Comparable<Packable> {
	/**
	 * Return a number of a given packable.
	 * @return the value of this number which is Integer
	 */
	
	public Integer getNumber();
	
	/**
	 * Return a weight of a given packable.
	 * @return the value of this weight which is Double
	 */
	public Double getWeight();
	
	/**
	 * Return a cost of a given packable.
	 * @return the value of this cost which is Double
	 */
	public Double getCost();
	
}
