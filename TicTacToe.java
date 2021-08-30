package com.blz.tictactoe;

public class TicTacToe {
	private static char[] element;

	public static void main(String[] args) {
		System.out.println("Welcome to the Tic Tac Toe");
		boardCreation();
	}

	private static void boardCreation() {
		element = new char[10];
		for (int i = 1; i < 10; i++) {
			element[i] = ' ';
			System.out.println(element[i]);
		}

	}
}