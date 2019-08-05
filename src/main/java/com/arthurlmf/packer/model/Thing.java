package com.arthurlmf.packer.model;
/**
 * A class that represents a thing in the problem. It implements a Packable interface 
 * so it can be stored in Packing Service.
 * @author arthurfarias
 *
 */
public class Thing implements Packable {
	
	private final Integer number;
	private final Double weight;
	private final Double cost;
	/**
	 * Creates a new Thing with given parameters
	 * @param number the thing number 
	 * @param weight the thing weight
	 * @param cost the thing cost
	 */
	public Thing(final Integer number, final Double weight, final Double cost) {
		this.number = number;
		this.weight = weight;
		this.cost = cost;
	}
	/**
	 * Returns a number of this thing
	 * @return an Integer with the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Returns a weight of this thing
	 * @return an Double with the weight
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * Returns a cost of this thing
	 * @return an Double with the cost
	 */
	public Double getCost() {
		return cost;
	}
	
	/**
	 * Provides an implementation to an strategy  which compares 
	 * two Packable objects.
	 * 
	 * @param the other Packable to be compared with this
	 * @return negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
 
	 */
	@Override
	public int compareTo(Packable packable) {
		//This solutions uses a i.e Strategy pattern and it is a base for the greedy 
		//solution
		//First compares the cost. It must return the biggest cost 
		int costComparation = cost.compareTo(packable.getCost())  * (-1);
		//if both have the same cost return the smallest weight
		if(costComparation == 0)
			return weight.compareTo(packable.getWeight());
		return costComparation;
	}
	
	/**
	 * Provides a string representation for a Thing
	 * @return String with a given representation
	 */
	@Override
	public String toString() {
		return String.format("[%s , %s, %s]", number, weight, cost); 
	}
}
