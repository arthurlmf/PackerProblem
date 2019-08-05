package com.arthurlmf.packer;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.arthurlmf.exception.APIException;

class PackerTest {

	private static final String FILE_NAME = "case001.txt";

	private static String absolutePath;
	
	@BeforeAll
	static void setPath() {
		Path resourceDirectory = Paths.get("src","test","resources");
		absolutePath = resourceDirectory.toFile().getAbsolutePath() + "/" ;
	}
	
	@Test
	void testInvalidParameter() {
		try {
			Packer.pack(FILE_NAME);
		} catch (APIException e) {
			Assertions.assertEquals("case001.txt is not a valid parameter or file not exists. Provide absolute path to test file.", e.getMessage());
		}
	}
	
	@Test
	void testExpectedSolution() throws APIException {
		String solution = Packer.pack(absolutePath + "/" + FILE_NAME);
		String expected = "4"  + "\n" + 
						  "-"  + "\n" + 
						  "2,7"+ "\n" + 
						  "8,9";
		Assertions.assertEquals(expected,solution);
	}

}
