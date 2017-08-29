package com.ckmcknight.android.rltictactoe.Model.util;

import java.util.List;

/**
 * Created by charlie on 5/21/17.
 */

public interface State<I,M> {
    List<M> getMoves();
    int getReward(Player<I, M> p);
}
