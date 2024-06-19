package it.polish;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.polish.core.PolishNotion;
import it.polish.exception.PNException;

class PolishTest {
	
	private static PolishNotion pn;

	@BeforeAll
	static void setUp() {
		pn = new PolishNotion();
	}
	
	@Test
	void inputNullThenException() {
		assertThrows(PNException.class, () -> {
			pn.calcola(null);
		});
	}
	
	@Test
	void inputEmptyThenException() {
		assertThrows(PNException.class, () -> {
			pn.calcola("");
		});
	}
	
	@Test
	void inputIsNotNumberThenException() {
		assertThrows(PNException.class, () -> {
			pn.calcola("A B +");
		});
	}
	
	@Test
	void inputHasNotSignThenException() {
		assertThrows(PNException.class, () -> {
			pn.calcola("A B");
		});
	}
	
	@Test
	void input1Plus2Then3() {
		assertEquals(3, pn.calcola("1 2 +"));
	}
	
	@Test
	void input1Minu2Then3() {
		assertEquals(-1, pn.calcola("1 2 -"));
	}
	
	@Test
	void inputOneNumberThenItSelf() {
		assertEquals(4, pn.calcola("4"));
	}

}
