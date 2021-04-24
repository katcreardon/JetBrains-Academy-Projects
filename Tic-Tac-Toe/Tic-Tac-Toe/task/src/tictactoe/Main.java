package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] grid = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'0', '0', '9'}
        };
        boolean isContinuing = true;
        int turn = 0;

        while (isContinuing) {
            printGrid(grid);
            grid = getCoordinates(grid, turn);
            turn++;
            isContinuing = analyzeGameState(grid);
        }
    }

    static void printGrid(char[][] grid) {
        System.out.println("---------");
        System.out.println("| " + grid[0][0] + " " + grid[0][1] + " " + grid[0][2] + " |");
        System.out.println("| " + grid[1][0] + " " + grid[1][1] + " " + grid[1][2] + " |");
        System.out.println("| " + grid[2][0] + " " + grid[2][1] + " " + grid[2][2] + " |");
        System.out.println("---------");
    }

    static char[][] getSymbols() {
        char[][] grid = new char[4][3];
        Scanner in = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String str = in.nextLine();
        int numX = 0;
        int numO = 0;
        int numEmpty = 0;

        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = str.charAt(k++);
                if (grid[i][j] == 'X') {
                    numX++;
                } else if (grid[i][j] == 'O') {
                    numO++;
                } else {
                    numEmpty++;
                }
            }
        }

        grid[3][0] = (char) numX;
        grid[3][1] = (char) numO;
        grid[3][2] = (char) numEmpty;

        printGrid(grid);

        return grid;
    }

    static char[][] getCoordinates(char[][] grid, int turn) {
        Scanner in = new Scanner(System.in);
        boolean isValid = false;
        System.out.print("Enter the coordinates: ");

        while (!isValid) {
            try {
                int i = in.nextInt();
                int j = in.nextInt();
                if (i < 1 || i > 3 || j < 1 || j > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    System.out.print("Enter the coordinates: ");
                } else if (grid[i - 1][j - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                    System.out.print("Enter the coordinates: ");
                } else {
                    isValid = true;
                    if (turn % 2 == 0) {
                        grid[i - 1][j - 1] = 'X';
                        grid[3][0] += 1;
                    } else {
                        grid[i - 1][j - 1] = 'O';
                        grid[3][1] += 1;
                    }
                    grid[3][2] -= 1;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                System.out.print("Enter the coordinates: ");
                in.nextLine();
            }
        }
        return grid;
    }

    static boolean analyzeGameState(char[][] grid) {
        int numX = grid[3][0] - '0';
        int numO = grid[3][1] - '0';
        int numEmpty = grid[3][2] - '0';
        boolean flagX = false;
        boolean flagO = false;

        // Sum rows
        int[] rowSums = new int[3];
        for (int i = 0; i < 3; i++) {
            int rowSum = 0;
            for (int j = 0; j < 3; j++) {
                rowSum += grid[i][j];
            }
            rowSums[i] = rowSum;
            if (rowSum == 264) {
                flagX = true;
            } else if (rowSum == 237) {
                flagO = true;
            }
        }

        // Sum columns
        int[] colSums = new int[3];
        for (int j = 0; j < 3; j++) {
            int colSum = 0;
            for (int i = 0; i < 3; i++) {
                colSum += grid[i][j];
            }
            colSums[j] = colSum;
            if (colSum == 264) {
                flagX = true;
            } else if (colSum == 237) {
                flagO = true;
            }
        }

        // Sum diagonals
        int leftDiagSum = 0;
        for (int i = 0; i < 3; i++) {
            leftDiagSum += grid[i][i];
        }

        int rightDiagSum = 0;
        for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
            rightDiagSum += grid[i][j];
        }

        if (leftDiagSum == 264 || rightDiagSum == 264) {
            flagX = true;
        } else if (leftDiagSum == 237 || rightDiagSum == 237) {
            flagO = true;
        }

        if (numX - numO >= 2 || numO - numX >= 2 || flagX && flagO) {
            System.out.println("Impossible");
        } else if (!flagX && !flagO && numEmpty > 0) {
//            System.out.println("Game not finished");
            return true;
        } else if (!flagX && !flagO && numEmpty == 0) {
            printGrid(grid);
            System.out.println("Draw");
        } else if (flagX) {
            printGrid(grid);
            System.out.println("X wins");
        } else {
            printGrid(grid);
            System.out.println("O wins");
        }
        return false;
    }
}
