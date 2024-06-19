package it.tris.core;

import java.util.Objects;

public class Tris {

	private Character[][] board = {
			{'\0','\0','\0'},
			{'\0','\0','\0'},
			{'\0','\0','\0'}
	};
	
	public Character lastPlayer = '\0';
	
	public boolean checkCleanGrid() {
		for (int row = 0; row < 3; row ++) {
			for (int col = 0; col < 3; col ++) {
				if (board[row][col] != '\0') return false;
			}
		}
		return true;
	}

	public String play(int x, int y) {
		changeTurn();
		
		checkAxis("Y", y);
		checkAxis("X", x);
		
		if (board[x][y] != '\0') {
			throw new RuntimeException("Cella già occupata");
		} else { 
			board[x][y] = this.lastPlayer;
			if (isWin())
				return this.lastPlayer + " is the winner";
			return "no winner";
		}
		
	}

	private void checkAxis(String name, int axis) {
		if (axis < 0 || axis > 2) throw new RuntimeException(name + "out of bounds"); 
		
	}

	public Character getCell(int x, int y) {
		return board[x][y];
	}
	
	public void changeTurn() {
		if (this.lastPlayer == 'X')
			this.lastPlayer = 'O';
		else
			this.lastPlayer = 'X';
	}
	
	public boolean isWin() {
		int count = 0;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (Objects.equals(board[y][x], this.lastPlayer)) {
					count++;
				} else {
					count = 0;
				}
			}
			if (count == 3) break;
		}
		
		if (count == 3) return true;
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (Objects.equals(board[y][x], this.lastPlayer)) {
					count++;
				} else {
					count = 0;
				}
			}
			if (count == 3) break;
		}
		
		if (count == 3) return true;
		
		boolean result = false;
		if (Objects.equals(board[0][0], this.lastPlayer) && Objects.equals(board[1][1], this.lastPlayer) && Objects.equals(board[2][2], this.lastPlayer))
			result = true;
		
		if (result) return result;
		
		if (Objects.equals(board[2][2], this.lastPlayer) && Objects.equals(board[1][1], this.lastPlayer) && Objects.equals(board[0][0], this.lastPlayer))
			result = true;
		
		return result;
		
	}
}
