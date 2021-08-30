package com.blz.tictactoe;

import java.util.Scanner;

public class TicTacToe {
	private static char[] board;
	static char userMark, computerMark;
	static Scanner scan = new Scanner(System.in);
	static int userNumber;

	public static void main(String[] args) {
		System.out.println("Welcome to the Tic Tac Toe Game");
		boardCreation();

		choosingXorO();
		currentBoard();
		userCall();
		userMove();
	}

	private static void boardCreation() {
		board = new char[10];
		for (int i = 1; i < 10; i++) {
			board[i] = ' ';
		}

	}

	private static void choosingXorO() {
		System.out.println("Choose 1 for 'X' or Choose 2 for 'O' as your mark");
		int option = scan.nextInt();
		switch (option) {
		case 1:
			userMark = 'X';
			computerMark = 'O';
			break;
		case 2:
			userMark = 'O';
			computerMark = 'X';
			break;
		default:
			System.out.println("Your input is invalid");
			choosingXorO();
		}
	}

	private static void currentBoard() {
		for (int i = 1; i < 10; i++) {
			if (board[i] != 'X' && board[i] != 'O') {
				board[i] = (char) (i + '0');
			}
		}
		showBoard();
	}

	private static void showBoard() {
		System.out.println("\n  " + board[1] + " | " + board[2] + " | " + board[3] + " ");
		System.out.println(" ----------- ");
		System.out.println("  " + board[4] + " | " + board[5] + " | " + board[6] + " ");
		System.out.println(" ----------- ");
		System.out.println("  " + board[7] + " | " + board[8] + " | " + board[9] + " \n");
	}

	private static void userCall() {
		System.out.println("\nEnter a number from board to make the mark:\n");
		userNumber = scan.nextInt();
		if (userNumber < 1 || userNumber > 9) {
			currentBoard();
			System.out.println("Your input is Invalid");
			userCall();
		}
	}

	private static void userMove() {
		if (board[userNumber] == 'X' || board[userNumber] == 'O') {
			currentBoard();
			System.out.println("Number which is selected is not free");
			userCall();
			userMove();
		} else {
			board[userNumber] = userMark;
			System.out.println(userMark + " user is marked " + userNumber);
		}
	}
}
