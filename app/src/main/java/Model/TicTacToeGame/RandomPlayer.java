package Model.TicTacToeGame;

import java.util.List;
import java.util.Random;

import Model.util.Player;
import Model.util.State;

/**
 * Created by charlie on 5/21/17.
 */

public class RandomPlayer implements Player<Piece, TicTacToeMove> {
    private Piece p;
    private Random random = new Random();

    public RandomPlayer(Piece p) {
       this.p = p;
    }

    @Override
    public TicTacToeMove move(State<Piece, TicTacToeMove> board) {
        List<TicTacToeMove> moves = board.getMoves();
        int m = random.nextInt(moves.size());
        return moves.get(m);
    }

    @Override
    public Piece getIdentifier() {
        return p;
    }
}
