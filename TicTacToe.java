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
		showBoard();
		choosingXorO();

		tossCoin();
		outerloop: while (flag == 0) {
			if ((turn + 1) % 2 == 0) {

				currentBoard();

				userCall();

				userMove();

				currentBoard();
				flag = checkWin();
				if (flag == 1) {
					System.out.println("Excellent! You are the winner");
					break outerloop;
				}

				flag = checkTie();
				if (flag == 1) {
					System.out.println("Nice Play! It's Tie");
					;
					break outerloop;
				}
				turn++;
			} else {

				flag = computerWin();
				if (flag == 1)
					break outerloop;
				flag = computerBlock();
				if (flag == 1) {
					turn++;
					flag = 0;
					return;
				}
				flag = computerCorner();
				if (flag == 1) {
					turn++;
					flag = 0;
					return;
				}
				flag = computerCenterSide();
				if (flag == 1) {
					turn++;
					flag = 0;
					return;
				}
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
		System.out.println("\nUser Mark: '" + userMark + "' and Computer Mark: '" + computerMark + "'");

	}

	// Assignment element wih number if it is not marked
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

	public static int checkWin() {
		for (int i = 1; i < 9; i++) {
			int win[] = winArray(i);
			if (board[win[0]] == board[win[1]] && board[win[1]] == board[win[2]]) {
				flag = 1;
			}
		}
		return flag;
	}

	private static int[] winArray(int number) {
		if (number == 1) {
			int arrayWin[] = { 1, 2, 3 };
			return arrayWin;
		} else if (number == 2) {
			int arrayWin[] = { 4, 5, 6 };
			return arrayWin;
		} else if (number == 3) {
			int arrayWin[] = { 7, 8, 9 };
			return arrayWin;
		} else if (number == 4) {
			int arrayWin[] = { 1, 4, 7 };
			return arrayWin;
		} else if (number == 5) {
			int arrayWin[] = { 2, 5, 8 };
			return arrayWin;
		} else if (number == 6) {
			int arrayWin[] = { 3, 6, 9 };
			return arrayWin;
		} else if (number == 7) {
			int arrayWin[] = { 1, 5, 9 };
			return arrayWin;
		} else {
			int arrayWin[] = { 3, 5, 7 };
			return arrayWin;
		}
	}

	public static int checkTie() {
		for (int i = 1; i < 10; i++) {
			if (board[i] == 'X' || board[i] == 'O') {
				if (i == 9) {
					flag = 1;
				}
			}
		}
		return flag;
	}

	private static int winBlock(char playerMark, char opponentMark) {
		int winBlock[] = new int[3];
		for (int i = 1; i < 9; i++) {
			winBlock = winArray(i);
		}
		if (board[winBlock[0]] == board[winBlock[1]] && board[winBlock[0]] == playerMark
				&& board[winBlock[2]] != opponentMark) {
			flag = winBlock[2];
		} else if (board[winBlock[0]] == board[winBlock[2]] && board[winBlock[2]] == playerMark
				&& board[winBlock[1]] != opponentMark) {
			flag = winBlock[1];
		} else if (board[winBlock[1]] == board[winBlock[2]] && board[winBlock[2]] == playerMark
				&& board[winBlock[0]] != opponentMark) {
			flag = winBlock[0];
		}
		return flag;
	}

	private static int computerWin() {
		int index = winBlock(computerMark, userMark);
		if (index != 0) {
			board[index] = computerMark;
			System.out.println("My choice is '" + index + "'");
			currentBoard();
			System.out.println("I won. Better Luck next time");
			flag = 1;
		}
		return flag;
	}

	private static int computerBlock() {
		int index = winBlock(userMark, computerMark);
		if (index != 0) {
			board[index] = computerMark;
			System.out.println("Computer goes for '" + index + "' to block User");
			flag = 1;
		}
		return flag;
	}

	private static int computerCorner() {
		int corner[] = { 7, 3, 1, 9 };
		for (int i = 0; i < 4; i++) {
			if (board[corner[i]] != 'X' && board[corner[i]] != 'O') {
				board[corner[i]] = computerMark;
				System.out.println("Computer choice is '" + corner[i] + "'");
				flag = 1;
				break;
			}
		}
		return flag;
	}

	private static int computerCenterSide() {
		if (board[5] != 'X' && board[5] != 'O') {
			board[5] = computerMark;
			System.out.println("Computer choice is '5'");
			flag = 1;
		} else {
			int side[] = { 2, 6, 8, 4 };
			for (int j = 0; j < 4; j++) {
				if (board[side[j]] != 'X' && board[side[j]] != 'O') {
					board[side[j]] = computerMark;
					System.out.println("My choice is '" + side[j] + "'");
					flag = 1;
					break;
				}
			}
		}
		return flag;
	}
}
