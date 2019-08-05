package com.arthurlmf.packer.service;

/**
 * A base interface with the behavior of a Packing Service.
 * @author arthurfarias
 *
 */

public interface PackingService {
	
	/**
	 * Provides the behavior for an packing service endpoint
	 * @param filePath the path to a test file as a String
	 * @return the solution of a packing
	 */
	
	public String pack(String filePath);

}
