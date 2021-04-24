package tictactoe;

public abstract class Player {

    protected char token;

    protected Player(char token) {
        this.token = token;
    }

    public abstract void makeMove(Board game);

    protected abstract String checkMoveValidity(Board game, int i, int j);
}

