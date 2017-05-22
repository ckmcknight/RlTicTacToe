package Model.TicTacToeGame;

import java.util.Arrays;

/**
 * Created by charlie on 5/21/17.
 */

public class TicTacToeMove {
    private int[] loc;

    public TicTacToeMove(int row, int col) {
       loc = new int[]{row, col};
    }

    public int getRow() {
        return loc[0];
    }

    public int getCol() {
        return loc[1];
    }

    public int[] getMove() {
        return loc;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(loc);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TicTacToeMove)) {
            return false;
        }
        TicTacToeMove other = (TicTacToeMove) o;
        return Arrays.equals(this.loc, other.loc);
    }
}
