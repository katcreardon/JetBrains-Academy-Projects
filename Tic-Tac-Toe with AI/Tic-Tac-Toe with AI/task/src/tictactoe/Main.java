package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        getInput();

        // Test
//        Board game = new Board("OXX_X__O_");
//        Computer.checkWinCondition(game, 'O');
//        testGame();
    }

    public static void getInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input command: ");
        String command = in.nextLine();
        String[] commands = command.split("\\s");
        Player p1 = new User('X');
        Player p2 = new User('O');

        if ("exit".equals(command)) {
            System.exit(0);
        } else if (commands.length == 3) {
            if ("start".equals(commands[0])) {
                if (!"user".equals(commands[1])) {
                    p1 = new Computer('X', commands[1]);
                }
                if (!"user".equals(commands[2])) {
                    p2 = new Computer('O', commands[2]);
                }
            }
        } else {
            System.out.println("Bad parameters!");
            getInput();
        }

        playGame(p1, p2);
    }

    public static void playGame(Player p1, Player p2) {
        Board newGame = new Board();
        boolean isContinuing = true;
        int turn = 1;
        String gameState = "Game not finished";

        while (isContinuing) {
            newGame.printBoard();

            if (turn % 2 != 0) {
                p1.makeMove(newGame);
            } else {
                p2.makeMove(newGame);
            }

            gameState = newGame.analyzeGameState();

            if (!"Game not finished".equals(gameState)) {
                newGame.printBoard();
                System.out.println(gameState);
                System.out.println();
                isContinuing = false;
            } else {
                turn++;
            }
        }

        getInput();
    }

    public static void testGame() {
        Board game = new Board("OOXOXXXXO");
        System.out.println(game.analyzeGameState());
    }
}
