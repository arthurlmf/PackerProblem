package com.arthurlmf.packer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.arthurlmf.exception.APIException;
import com.arthurlmf.packer.service.GreedyPackingService;

public class GreedyPackingServiceTest {
	
private static final String FILE_PATH = "/Users/arthurfarias/Documents/input.txt";
	
	@Test
	void testExpectedSolution() throws APIException {
		String solution = GreedyPackingService.getInstance().pack(FILE_PATH);
		String expected = "4"  + "\n" + 
						  "-"  + "\n" + 
						  "2,7"+ "\n" + 
						  "8,9";
		Assertions.assertEquals(expected,solution);
	}

}
