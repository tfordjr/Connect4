// Terry Ford Jr - Project 2 - CS 2261
// I've playtested extensively, and I have not found any bugs. The program has detected every win condition
// I've thrown at it so far without fail in its current state.
// I also included a draw feature called catscratch that will determine if there is a draw.
// Let me know if I can adjust the program to better adhear to programming standards. Thank you! Have fun.

// FINAL VERSION

package edu.umsl;
import java.util.Scanner;

public class Player {
    public static void main(String[] args) {
        char[][] board = new char[6][7];
        boolean gameOver = false, catscratch = false;
        byte count = 0;
        char disc;
        String color = "lkajsdflkagh";

        System.out.println("Welcome to Connect4 in JAVA! This is a two player game.\n" +
                "To play, simply input the column (0-6) you would like to place a token and press ENTER.\n");

        initialize(board);

        while(!gameOver)
        {
            byte input = 10;
            count++;

            // PLAYER IDENTIFICATION
            if (count%2 == 1) {
                color = "RED";
                disc = 'R';
            }
            else {
                color = "BLUE";
                disc = 'B';
            }

            printBoard(board);
            System.out.println(color + " player, please choose a column (0-6) to drop the " + color + " disc: ");

            // USER INPUT VALIDATION & DRAW GAME TESTING (CATSCRATCH VARIABLE)
            boolean retry;
            do {
                Scanner sc = new Scanner(System.in);
                retry = false;

                try {
                    input = sc.nextByte();
                } catch (Exception ex) {
                    System.out.println("Exceptions handled.");
                    retry = true;
                }

                if (input < 0 || input > 6)
                {
                    System.out.println("Input not accepted, please try again. ");
                    retry = true;
                }
                if (input > -1 && input < 7 && board[0][input] != '-')
                {
                    for (int i = 0; i < 7; i++) {     // DRAW GAME TESTING
                        if(board[0][i] != '-')
                            catscratch = true;
                        else {
                            catscratch = false;
                            break;
                        }
                    }
                    if (catscratch) {            // IF DRAW, BREAK
                        break;
                    } else {
                        System.out.println("Column full! Choose another column");   // ELSE, JUST ONE COLUMN IS FULL
                        retry = true;
                    }
                }
            } while (retry);

            if (catscratch)
                break;

            // DISC PLACEMENT & GAME OVER TESTING
            for (int i = 5; i > -1; i--) {
                if(board[i][input] == '-')
                {
                    board[i][input] = disc;
                    gameOver = isGameOver(board, i, input, disc);
                    break;
                }
            }
        }

        // GAME OVER STATE!!!
        if(gameOver) {
            System.out.println("GAME OVER!!!");
            printBoard(board);
            System.out.println(color + " player wins!!!\nThanks for playing!");
        } else {      // DRAW STATE
            printBoard(board);
            System.out.println("All columns full! DRAW!!!\nPlease play again!");
        }
    }

    public static boolean isGameOver(char[][] grid, int i, int j, char disc)
    {
        // ALL VERTICAL WIN CONDITIONS     SOLID & TESTED, NO ADDITIONAL WORK NEEDED
        if ( i < 3 ) {
            if (grid[i + 1][j] == disc && grid[i + 2][j] == disc && grid[i + 3][j] == disc)
                return true;
        }

        // ALL HORIZONTAL WIN CONDITIONS       SOLID & TESTED, NO ADDITIONAL WORK NEEDED
        for (int index = 0; index < 6; index++) {
            byte count = 0;
            for (int index2 = 0; index2 < 7; index2++) {
                if(grid[index][index2] == disc) {
                    count++;
                    if (count == 4)
                        return true;
                }
                else
                    count = 0;
            }
        }

        // "\" DIAGONAL WIN CONDITIONS        Indexes were chosen carefully to prevent out of bounds checks
        for (int index = 0; index < 3; index++) {
            for (int index2 = 0; index2 < 4; index2++) {
                if (grid[index][index2] == disc && grid[index + 1][index2 + 1] == disc &&
                        grid[index + 2][index2 + 2] == disc && grid[index + 3][index2 + 3] == disc)
                    return true;
            }
        }

        // "/" DIAGONAL WIN CONDITIONS        Indexes were chosen carefully to prevent out of bounds checks
        for (int index = 5; index > 2; index--) {
            for (int index2 = 0; index2 < 4; index2++) {
                if (grid[index][index2] == disc && grid[index - 1][index2 + 1] == disc &&
                        grid[index - 2][index2 + 2] == disc && grid[index - 3][index2 + 3] == disc)
                    return true;
            }
        }
        return false;
    }

    public static void initialize(char[][] arr) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                arr[i][j] = '-';
            }
        }
    }

    public static void printBoard(char[][] arr) {
        System.out.println("\t\t\t0 1 2 3 4 5 6");
        for (int i = 0; i < 6; i++) {
            System.out.print("\t\t\t");
            for (int j = 0; j < 7; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}