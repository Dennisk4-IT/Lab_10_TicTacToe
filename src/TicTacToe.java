import java.util.Scanner;

public class TicTacToe {
    private static final int totalRows = 3;
    private static final int totalColumns = 3;
    private static final String[][] ttBoard = new String[totalRows][totalColumns];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        boolean playAgain;
        do {
            clearBoard();
            int moveCount = 0;
            boolean isGameOver = false;
            while (moveCount < totalRows * totalColumns && !isGameOver) {
                display();
                int row, column;
                do {
                    row = safeInput.getRangedInt(userInputScanner, "Enter row (1-3)", 1, 3) - 1;
                    column = safeInput.getRangedInt(userInputScanner, "Enter column (1-3)", 1, 3) - 1;
                } while (!isValidMove(row, column));

                ttBoard[row][column] = currentPlayer;
                moveCount++;

                if (moveCount >= 5) {
                    isGameOver = isWin(currentPlayer);
                }

                if (isGameOver) {
                    display();
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (moveCount == totalRows * totalColumns) {
                    display();
                    System.out.println("It's a tie!");
                } else {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            }
            playAgain = safeInput.getYNConfirm(userInputScanner, "Do you want to play again?");
        } while (playAgain);
    }

    private static void clearBoard() {
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                ttBoard[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("\n  1 2 3");
        for (int i = 0; i < totalRows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < totalColumns; j++) {
                System.out.print(ttBoard[i][j]);
                if (j < totalColumns - 1) System.out.print("|");
            }
            System.out.println();
            if (i < totalRows - 1) System.out.println("------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return ttBoard[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < totalRows; i++) {
            if (ttBoard[i][0].equals(player) && ttBoard[i][1].equals(player) && ttBoard[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < totalColumns; j++) {
            if (ttBoard[0][j].equals(player) && ttBoard[1][j].equals(player) && ttBoard[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (ttBoard[0][0].equals(player) && ttBoard[1][1].equals(player) && ttBoard[2][2].equals(player)) ||
                (ttBoard[0][2].equals(player) && ttBoard[1][1].equals(player) && ttBoard[2][0].equals(player));
    }
}
