package Model.TicTacToeGame;

/**
 * Created by charlie on 5/19/17.
 */

public enum Piece {
    X("X"),
    O("O"),
    Empty(" ");

    private final String value;

    Piece(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
