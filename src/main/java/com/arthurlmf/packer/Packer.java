package com.arthurlmf.packer;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.arthurlmf.exception.APIException;
import com.arthurlmf.packer.service.GreedyPackingService;
/**
 * A class that provides a facade of Packing Challenge
 * @author arthurfarias
 *
 */
public class Packer {
	
	/**
	 * This method returns the solution of Packer Challenge.
	 * @param filePath the absolute path to a test file as a String
	 * @return the problems solution as string 
	 * @throws APIException if parameter is no valid
	 */
	public static String pack(String filePath) throws APIException {
		Path myPath = Paths.get(filePath);
		final boolean exists = myPath.toFile().exists();
		if (!exists)
			throw new APIException(filePath + " is not a valid parameter or file not exists. Provide absolute path to test file.");
		
		return GreedyPackingService.getInstance().pack(filePath);
	}
}
