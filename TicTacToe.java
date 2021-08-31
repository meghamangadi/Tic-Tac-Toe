package com.blz.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private static char[] board;
	static char userMark, computerMark;
	static Scanner scan = new Scanner(System.in);
	private static final Random random = new Random();
	static int userNumber, computerNumber;
	static int turn = 1, flag = 0;

	public static void main(String[] args) {
		System.out.println("Welcome to the Tic Tac Toe Game");
		boardCreation();

		choosingXorO();
		currentBoard();
		userCall();
		userMove();
		tossCoin();

		while (flag == 0) {
			if ((turn + 1) % 2 == 0) {
				currentBoard();
				userCall();
				userMove();
				turn++;
			}
		}
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

	private static void tossCoin() {
		System.out.println("\nMaking a toss to check who plays first\nChoose 1 for Head or 2 for Tail");
		int option = scan.nextInt();
		if (option == 1 || option == 2) {
			int flip = random.nextInt(2) + 1;
			if (flip == 1) {
				System.out.println("\nBy tossing Coin it shows HEAD\n");
			} else {
				System.out.println("\nBy tossing Coin it shows TAIL\n");
			}
			if (flip == option) {
				System.out.println("User will start the game\n");
			} else {
				System.out.println("Computer will start the game\n");
				computerFirstTurn();
			}
		} else {
			System.out.println("\nInvalid input Again");
			tossCoin();
		}
	}

	public static void computerFirstTurn() {
		computerNumber = random.nextInt(9) + 1;
		board[computerNumber] = computerMark;
		System.out.println("Computer choses '" + computerNumber + "' now user turn");
	}
}
