package com.arthurlmf.packer.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.arthurlmf.packer.model.Packable;
import com.arthurlmf.packer.model.Thing;

public class ThingTest {

	@Test
	void test() {
		Packable thing = new Thing(1, 80d, 90d);
		Assertions.assertEquals(new Integer(1), thing.getNumber());
		Assertions.assertEquals(80d, thing.getWeight().doubleValue());
		Assertions.assertEquals(90d, thing.getCost().doubleValue());
	}
	
	@Test
	void testEquals() {
		Packable oneThing = new Thing(1, 80d, 90d);
		Packable secondThing = new Thing(2, 50d, 37d);
		Packable thirdThing = new Thing(2, 70d, 90d);
		Assertions.assertEquals(-1, oneThing.compareTo(secondThing));
		Assertions.assertEquals(1,secondThing.compareTo(oneThing));
		Assertions.assertEquals(1, oneThing.compareTo(thirdThing));
	}
	
	@Test
	void testToString() {
		Packable thing = new Thing(1, 80d, 90d);
		Assertions.assertEquals("[1 , 80.0, 90.0]", thing.toString());
	}

}
