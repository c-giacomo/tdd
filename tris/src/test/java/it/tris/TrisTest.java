package it.tris;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tris.core.Tris;

class TrisTest {
	
	private Tris tris;
	
	@BeforeEach
	void setUp() {
		tris = new Tris();
	}
	
	@Test
	void whenGameStartsCleanGrid() {
		boolean isClean = tris.checkCleanGrid();
		
		assertTrue(isClean);
	}
	
	@Test
	void whenYOutOfBorderThenException() {
		assertThrows(RuntimeException.class, () -> tris.play(2,4));
	}
	
	@Test
	void whenXOutOfBorderThenException() {
		assertThrows(RuntimeException.class, () -> tris.play(4,2));
	}
	
	@Test
	void whenValidMoveThenWriteMove() {
		assertTrue(tris.checkCleanGrid());
		tris.play(1, 2);
		assertFalse(tris.checkCleanGrid());
	}
	
	@Test
	void whenValidMoveThenWriteOnThatPlace() {
		tris.play(1, 2);
		assertNotEquals('\0', tris.getCell(1,2));
	}
	
	@Test
	void whenMoveOnOccupiedCellThenException() {
		tris.play(1, 2);
		assertThrows(RuntimeException.class, () -> tris.play(1,2));
	}
	
	@Test
	void whenFirstMoveThenPlayerX() {
		tris.play(1, 2);
		assertEquals('X', tris.lastPlayer);
	}
	
	@Test
	void whenXplaysThenX() {
		tris.play(1, 2);
		assertEquals(tris.lastPlayer, tris.getCell(1, 2));
	}
	
	@Test
	void whenXplaysThenOSturn() {
		tris.play(1, 2);
		tris.play(2, 2);
		assertEquals('O', tris.lastPlayer);
	}
	
	@Test
	void whenOplaysThenOSigned() {
		tris.play(1, 2);
		tris.play(2, 2);
		assertEquals(tris.lastPlayer, tris.getCell(2, 2));
	}
	
	@Test
	void whenFirstRoundThenNoWinner() {
		String result = tris.play(1, 2);
		assertEquals("no winner", result);
	}
	
	@Test
	void whenHorizontalWinnerExistThenCheck() {
		tris.play(0, 0);
		tris.play(1, 1);
		tris.play(0, 1);
		tris.play(1, 2);
		String result = tris.play(0, 2);
		assertEquals("X is the winner", result);
	}
	
	@Test
	void whenVerticalWinnerExistThenCheck() {
		tris.play(0, 0);
		tris.play(0, 1);
		tris.play(1, 0);
		tris.play(0, 2);
		String result = tris.play(2, 0);
		assertEquals("X is the winner", result);
	}

}
