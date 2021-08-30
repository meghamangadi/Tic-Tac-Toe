package com.blz.tictactoe;

public class TicTacToe {
	private static char[] board;

	public static void main(String[] args) {
		System.out.println("Welcome to the Tic Tac Toe");
		boardCreation();
	}

	private static void boardCreation() {
		board = new char[10];
		for (int i = 1; i < 10; i++) {
			board[i] = ' ';
			System.out.println(board[i]);
		}

	}
}