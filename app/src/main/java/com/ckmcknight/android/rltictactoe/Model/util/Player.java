package com.ckmcknight.android.rltictactoe.Model.util;

/**
 * Created by charlie on 5/21/17.
 */

public interface Player<I, M> {

    M move(State<I, M> board);
    I getIdentifier();
}
