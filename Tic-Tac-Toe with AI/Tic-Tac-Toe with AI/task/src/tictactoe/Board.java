package tictactoe;
public class Board {

    private int numOfXs = 0;
    private int numOfOs = 0;
    private int numOfEmpty  = 9;
    private char[][] grid = new char[3][3];

    public Board() { }

    public Board(String inputLine) {
        int k = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (inputLine.charAt(k) == 'X') {
                    grid[i][j] = 'X';
                    numOfXs++;
                    numOfEmpty--;
                } else if (inputLine.charAt(k) == 'O') {
                    grid[i][j] = 'O';
                    numOfOs++;
                    numOfEmpty--;
                }
                k++;
            }
        }
    }

    public char[][] getGrid() {
        return this.grid;
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public void updateBoard(int i, int j, char token) {
        grid[i][j] = token;

        if (token == 'X') {
            numOfXs++;
        } else {
            numOfOs++;
        }

        numOfEmpty--;
    }

    public String analyzeGameState() {
        boolean xWin = false;
        boolean oWin = false;
        int rowSum = 0;
        int colSum = 0;
        int leftDiagSum = 0;
        int rightDiagSum = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rowSum += grid[i][j];
                colSum += grid[j][i];

                if (i == j) {
                    leftDiagSum += grid[i][j];
                }

                if (i + j == 2) {
                    rightDiagSum += grid[i][j];
                }
            }

            if (rowSum == 264 || colSum == 264 || leftDiagSum == 264 || rightDiagSum == 264) {
                xWin = true;
                break;
            } else if (rowSum == 237 || colSum == 237 || leftDiagSum == 237 || rightDiagSum == 237) {
                oWin = true;
                break;
            }

            rowSum = 0;
            colSum = 0;
        }

        if (numOfXs - numOfOs >= 2 || numOfOs - numOfXs >= 2 || xWin && oWin) {
            return "Impossible";
        } else if (!xWin && !oWin && numOfEmpty > 0) {
            return "Game not finished";
        } else if (!xWin && !oWin && numOfEmpty == 0) {
            return "Draw";
        } else if (xWin) {
            return "X wins";
        } else {
            return "O wins";
        }
    }
}
