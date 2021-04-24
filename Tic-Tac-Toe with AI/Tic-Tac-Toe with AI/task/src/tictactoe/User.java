package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Player {

    public User(char token) {
        super(token);
    }

    @Override
    public void makeMove(Board game) {
        Scanner in = new Scanner(System.in);
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Enter the coordinates: ");
            try {
                int i = in.nextInt();
                int j = in.nextInt();
                String moveResult = checkMoveValidity(game, i - 1, j - 1);

                if ("range".equals(moveResult)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if ("occupied".equals(moveResult)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else if ("valid".equals(moveResult)) {
                    game.updateBoard(i - 1, j - 1, token);
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                in.nextLine();
            }
        }
    }

    @Override
    protected String checkMoveValidity(Board game, int i, int j) {
        if (i < 0 || i > 2 || j < 0 || j > 2) {
            return "range";
        } else if (game.getGrid()[i][j] != 0) {
            return "occupied";
        } else {
            return "valid";
        }
    }
}
