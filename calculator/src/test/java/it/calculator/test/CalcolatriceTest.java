package it.calculator.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.calculator.Calcolatrice;

class CalcolatriceTest {
	
	Calcolatrice c;

	@BeforeAll
	void setUp() {
		c = new Calcolatrice();
	}

	@Test
	void testSum() {
		assertEquals(3, c.sum(1,2));
	}
	
	@Test
	void testSub() {
		assertEquals(1, c.sub(2,1));
	}
	
	@Test
	void testMult() {
		assertEquals(2, c.multiply(1,2));
	}
	
	@Test
	void testDivision() {
		assertEquals(1, c.division(2,2));
	}

}
