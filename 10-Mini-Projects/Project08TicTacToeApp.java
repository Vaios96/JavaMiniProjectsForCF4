package gr.aueb.cf.projects;

import org.w3c.dom.ls.LSOutput;

import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is a Tic Tac Toe app. 2 players take turns picking a spot to place their mark (X or O).
 * The winner is the one that will connect 3 of his/hers marks (horizontally, vertically or diagonally).
 * In case all 9 spots are filled and both of the players failed to connect their marks, the end game end in a draw.
 * The app checks for correct input (if the spot that the player picks is available and if the input is valid), if there
 * is a winner (in case there is, the game stops and announces the winner) and if there is a tie (in case there is, the game stops and announces the tied result).
 */
public class Project08TicTacToeApp {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean gameOn = true;

        while(gameOn){
            rulesOfTheGame();
            char[][] ticTacToe = new char[5][11];
            boolean[] positionsCovered = new boolean[9];
            int turns = 0;
            int player = 1;
            int choice;
            String startNewGame;

            initializeBoard(ticTacToe);
            printBoard(ticTacToe);

            while (true) {
                turns++;
                System.out.println("Where would you like to place your sign?");
                choice = getChoice(positionsCovered);
                placeChoice(choice, ticTacToe, player);
                printBoard(ticTacToe);

                if (turns >= 5) {
                    if (hasWon(ticTacToe)) {
                        System.out.println("Player " + player + " has won!");
                        break;
                    } else if (turns == 9) {
                        System.out.println("It is a tie!");
                        break;
                    }
                }

                player = changePlayer(player);
            }

            System.out.println("Would you like to play again? Press 'y' or 'Y' to play again (or anything else to quit)!");
            in.nextLine();
            startNewGame = in.nextLine();
            if (startNewGame.matches("[yY]")) {
                continue;
            } else {
                gameOn = false;
            }
        }

        System.out.println("Thanks for playing!");
    }

    public static void rulesOfTheGame() {
        System.out.println("Welcome to Tic-Tac-Toe! This a game for 2 players. You will have to decide who goes first. The first player is assigned the O mark and the second player the X");
        System.out.println("Both players take turns placing their marks on one of the 9 positions that are showed in the following graph: ");
        System.out.println(" 7 | 8 | 9 ");
        System.out.println("___|___|___");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("___|___|___");
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("Connect 3 to win!");
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 11; j++) {
                board[i][j] = ' ';
            }
            board[i][3] = '|';
            board[i][7] = '|';
        }

        for (int k = 1; k < 5; k += 2) {
            for (int a = 0; a < 11; a++) {
                board[k][a] = '_';
            }
            board[k][3] = '|';
            board[k][7] = '|';
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static int getChoice(boolean[] placed) {
        int choice;

        while (true) {
            try {
                choice = in.nextInt();
                if (isValid(choice, placed)) {
                    break;
                }
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.println("Invalid input! Please insert a number from 1 to 9 that has not been already used!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return choice;
    }

    public static boolean isValid(int choice, boolean[] placed) {
        if (choice >= 1 && choice <= 9) {
            if (placed[choice - 1]){
                throw new IllegalArgumentException("There is already a sign placed in this position!");
            } else {
                placed[choice - 1] = true;
                return true;
            }
        } else {
            throw new IllegalArgumentException("Invalid input! Please insert a number from 1 to 9 that has not been already used!");
        }
    }

    public static void placeChoice(int choice, char[][] board, int player) {
        switch (choice) {
            case 1:
                if (player == 1) {
                    board[4][1] = 'O';
                } else {
                    board[4][1] = 'X';
                }
                break;
            case 2:
                if (player == 1) {
                    board[4][5] = 'O';
                } else {
                    board[4][5] = 'X';
                }
                break;
            case 3:
                if (player == 1) {
                    board[4][9] = 'O';
                } else {
                    board[4][9] = 'X';
                }
                break;
            case 4:
                if (player == 1) {
                    board[2][1] = 'O';
                } else {
                    board[2][1] = 'X';
                }
                break;
            case 5:
                if (player == 1) {
                    board[2][5] = 'O';
                } else {
                    board[2][5] = 'X';
                }
                break;
            case 6:
                if (player == 1) {
                    board[2][9] = 'O';
                } else {
                    board[2][9] = 'X';
                }
                break;
            case 7:
                if (player == 1) {
                    board[0][1] = 'O';
                } else {
                    board[0][1] = 'X';
                }
                break;
            case 8:
                if (player == 1) {
                    board[0][5] = 'O';
                } else {
                    board[0][5] = 'X';
                }
                break;
            case 9:
                if (player == 1) {
                    board[0][9] = 'O';
                } else {
                    board[0][9] = 'X';
                }
                break;
        }
    }

    public static boolean hasWon(char[][] board) {

        // 3 rows
        if (board[0][9] != ' ' && board[0][9] == board[0][5] && board[0][5] == board[0][1]) {
            return true;
        } else if (board[2][9] != ' ' && board[2][9] == board[2][5] && board[2][5] == board[2][1]) {
            return true;
        } else if (board[4][9] != ' ' && board[4][9] == board[4][5] && board[4][5] == board[4][1]) {
            return true;
        }

        // 3 columns
        if (board[0][1] != ' ' && board[0][1] == board[2][1] && board[2][1] == board[4][1]) {
            return true;
        } else if (board[0][5] != ' ' && board[0][5] == board[2][5] && board[2][5] == board[4][5]) {
            return true;
        } else if (board[0][9] != ' ' && board[0][9] == board[2][9] && board[2][9] == board[4][9]) {
            return true;
        }

        // 2 diagonals
        if (board[0][9] != ' ' && board[0][9] == board[2][5] && board[2][5] == board[4][1]) {
            return true;
        } else if (board[0][1] != ' ' && board[0][1] == board[2][5] && board[2][5] == board[4][9]) {
            return true;
        }

        return false;
    }

    public static int changePlayer(int player) {
        if (player == 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
