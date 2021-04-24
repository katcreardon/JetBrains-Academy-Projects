package tictactoe;

import java.util.Random;

public class Computer extends Player {

    private String level;

    public Computer(char token, String level) {
        super(token);
        this.level = level;
    }

    @Override
    public void makeMove(Board game) {
        boolean isValid = false;
        Random rand = new Random();

        System.out.printf("Making move level \"%s\"%n", level);
        if ("easy".equals(level)) {
            easyMove(game, isValid, rand);
        } else if ("medium".equals(level)) {
            mediumMove(game, isValid, rand);
        } else {
            hardMove();
        }
    }

    @Override
    protected String checkMoveValidity(Board game, int i, int j) {
        if (game.getGrid()[i][j] == 0) {
            return "valid";
        } else {
            return "invalid";
        }
    }

    // For instance testing
//    public static boolean checkWinCondition(Board game, char token) {
//        int rowSum = 0;
//        int colSum = 0;
//        int leftDiagSum = 0;
//        int rightDiagSum = 0;
//
//        // win for Computer
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                rowSum += game.getGrid()[i][j];
//                colSum += game.getGrid()[j][i];
//
//                if (i == j) {
//                    leftDiagSum += game.getGrid()[i][j];
//                }
//
//                if (i + j == 2) {
//                    rightDiagSum += game.getGrid()[i][j];
//                }
//            }
//
//            if (rowSum == 176 && token == 'X' || rowSum == 158 && token == 'O') {
//                for (int k = 0; k < 3; k++) {
//                    if (game.getGrid()[i][k] == 0) {
//                        game.updateBoard(i, k, token);
//                        return true;
//                    }
//                }
//            } else if (colSum == 176 && token == 'X' || colSum == 158 && token == 'O') {
//                for (int k = 0; k < 3; k++) {
//                    if (game.getGrid()[k][i] == 0) {
//                        game.updateBoard(k, i, token);
//                        return true;
//                    }
//                }
//            } else if (leftDiagSum == 176 && token == 'X' || leftDiagSum == 158 && token == 'O') {
//                for (int k = 0; k < 3; k++) {
//                    if (game.getGrid()[k][k] == 0) {
//                        game.updateBoard(k, k, token);
//                        return true;
//                    }
//                }
//            } else if (rightDiagSum == 176 && token == 'X' || rightDiagSum == 158 && token == 'O') {
//                for (int k = 0, m = 2; k < 3 && m >= 0; k++, m--) {
//                    if (game.getGrid()[k][m] == 0) {
//                        game.updateBoard(k, m, token);
//                        return true;
//                    }
//                }
//            }
//            rowSum = 0;
//            colSum = 0;
//        }
//
//        leftDiagSum = 0;
//        rightDiagSum = 0;
//
//        // Block win for User
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                rowSum += game.getGrid()[i][j];
//                colSum += game.getGrid()[j][i];
//
//                if (i == j) {
//                    leftDiagSum += game.getGrid()[i][j];
//                }
//
//                if (i + j == 2) {
//                    rightDiagSum += game.getGrid()[i][j];
//                }
//            }
//
//            if (rowSum == 176 && token == 'O' || rowSum == 158 && token == 'X') {
//                for (int k = 0; k < 3; k++) {
//                    if (game.getGrid()[i][k] == 0) {
//                        game.updateBoard(i, k, token);
//                        return true;
//                    }
//                }
//            } else if (colSum == 176 && token == 'O' || colSum == 158 && token == 'X') {
//                for (int k = 0; k < 3; k++) {
//                    if (game.getGrid()[k][i] == 0) {
//                        game.updateBoard(k, i, token);
//                        return true;
//                    }
//                }
//            } else if (leftDiagSum == 176 && token == 'O' || leftDiagSum == 158 && token == 'X') {
//                for (int k = 0; k < 3; k++) {
//                    if (game.getGrid()[k][k] == 0) {
//                        game.updateBoard(k, k, token);
//                        return true;
//                    }
//                }
//            } else if (rightDiagSum == 176 && token == 'O' || rightDiagSum == 158 && token == 'X') {
//                for (int k = 0, m = 2; k < 3 && m >= 0; k++, m--) {
//                    if (game.getGrid()[k][m] == 0) {
//                        game.updateBoard(k, m, token);
//                        return true;
//                    }
//                }
//            }
//            rowSum = 0;
//            colSum = 0;
//        }
//
//        return false;
//    }

    private boolean checkWinCondition(Board game, char token) {
        int rowSum = 0;
        int colSum = 0;
        int leftDiagSum = 0;
        int rightDiagSum = 0;

        // win for Computer
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rowSum += game.getGrid()[i][j];
                colSum += game.getGrid()[j][i];

                if (i == j) {
                    leftDiagSum += game.getGrid()[i][j];
                }

                if (i + j == 2) {
                    rightDiagSum += game.getGrid()[i][j];
                }
            }

            if (rowSum == 176 && token == 'X' || rowSum == 158 && token == 'O') {
                for (int k = 0; k < 3; k++) {
                    if (game.getGrid()[i][k] == 0) {
                        game.updateBoard(i, k, token);
                        return true;
                    }
                }
            } else if (colSum == 176 && token == 'X' || colSum == 158 && token == 'O') {
                for (int k = 0; k < 3; k++) {
                    if (game.getGrid()[k][i] == 0) {
                        game.updateBoard(k, i, token);
                        return true;
                    }
                }
            } else if (leftDiagSum == 176 && token == 'X' || leftDiagSum == 158 && token == 'O') {
                for (int k = 0; k < 3; k++) {
                    if (game.getGrid()[k][k] == 0) {
                        game.updateBoard(k, k, token);
                        return true;
                    }
                }
            } else if (rightDiagSum == 176 && token == 'X' || rightDiagSum == 158 && token == 'O') {
                for (int k = 0, m = 2; k < 3 && m >= 0; k++, m--) {
                    if (game.getGrid()[k][m] == 0) {
                        game.updateBoard(k, m, token);
                        return true;
                    }
                }
            }
            rowSum = 0;
            colSum = 0;
        }

        leftDiagSum = 0;
        rightDiagSum = 0;

        // Block win for User
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rowSum += game.getGrid()[i][j];
                colSum += game.getGrid()[j][i];

                if (i == j) {
                    leftDiagSum += game.getGrid()[i][j];
                }

                if (i + j == 2) {
                    rightDiagSum += game.getGrid()[i][j];
                }
            }

            if (rowSum == 176 && token == 'O' || rowSum == 158 && token == 'X') {
                for (int k = 0; k < 3; k++) {
                    if (game.getGrid()[i][k] == 0) {
                        game.updateBoard(i, k, token);
                        return true;
                    }
                }
            } else if (colSum == 176 && token == 'O' || colSum == 158 && token == 'X') {
                for (int k = 0; k < 3; k++) {
                    if (game.getGrid()[k][i] == 0) {
                        game.updateBoard(k, i, token);
                        return true;
                    }
                }
            } else if (leftDiagSum == 176 && token == 'O' || leftDiagSum == 158 && token == 'X') {
                for (int k = 0; k < 3; k++) {
                    if (game.getGrid()[k][k] == 0) {
                        game.updateBoard(k, k, token);
                        return true;
                    }
                }
            } else if (rightDiagSum == 176 && token == 'O' || rightDiagSum == 158 && token == 'X') {
                for (int k = 0, m = 2; k < 3 && m >= 0; k++, m--) {
                    if (game.getGrid()[k][m] == 0) {
                        game.updateBoard(k, m, token);
                        return true;
                    }
                }
            }
            rowSum = 0;
            colSum = 0;
        }

        return false;
    }

    private void easyMove(Board game, boolean isValid, Random rand) {
        while (!isValid) {
            int i = rand.nextInt(3);
            int j = rand.nextInt(3);
            if ("valid".equals(checkMoveValidity(game, i, j))) {
                game.updateBoard(i, j, token);
                isValid = true;
            }
        }
    }

    private void mediumMove(Board game, boolean isValid, Random rand) {
        if (!checkWinCondition(game, token)) {
            easyMove(game, isValid, rand);
        }
    }

    private void hardMove() {

    }
}
