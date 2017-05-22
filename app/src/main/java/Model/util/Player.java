package Model.util;

import Model.TicTacToeGame.TicTacToeMove;
import Model.util.State;

/**
 * Created by charlie on 5/21/17.
 */

public interface Player<I, M> {

    M move(State<I, M> board);
    I getIdentifier();
}
