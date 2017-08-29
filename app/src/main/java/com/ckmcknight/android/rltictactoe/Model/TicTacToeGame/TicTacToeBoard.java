package com.ckmcknight.android.rltictactoe.Model.TicTacToeGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ckmcknight.android.rltictactoe.Model.util.InvalidMoveException;
import com.ckmcknight.android.rltictactoe.Model.util.Player;
import com.ckmcknight.android.rltictactoe.Model.util.State;

/**
 * Created by charlie on 5/19/17.
 */

public final class TicTacToeBoard implements State<Piece, TicTacToeMove> {
    private Piece[][] board;
    private boolean immutable;

    public enum GameStatus {
        Winner_X,
        Winner_O,
        ON_GOING,
        DRAW,
    }

    public TicTacToeBoard() {
        board = new Piece[][] {
                {Piece.Empty, Piece.Empty, Piece.Empty},
                {Piece.Empty, Piece.Empty, Piece.Empty},
                {Piece.Empty, Piece.Empty, Piece.Empty}};
        this.immutable = false;
    }

    private TicTacToeBoard(TicTacToeBoard old) {
        this.board = old.board.clone();
        this.immutable = true;
    }

    public GameStatus getStatus() {
        if (checkWinner(Piece.X)) {
            return GameStatus.Winner_X;
        } else if (checkWinner(Piece.O)) {
            return GameStatus.Winner_O;
        } else if (checkFullBoard()) {
            return GameStatus.DRAW;
        } else {
            return GameStatus.ON_GOING;
        }
    }

    private boolean checkFullBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(Piece.Empty)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWinner(Piece p) {
        for (int i = 0; i < 3; i++) {
            boolean diagComp1, diagComp2, rowComp, colComp;
            diagComp1 = diagComp2 = rowComp = colComp = true;
            for (int j = 0; j < 3; j++) {
                diagComp1 = diagComp1 && board[j][j].equals(p);
                diagComp2 = diagComp2 && board[2-j][j].equals(p);
                rowComp = rowComp && board[i][j].equals(p);
                colComp = colComp && board[j][i].equals(p);
            }
            if (diagComp1 || diagComp2 || rowComp || colComp) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TicTacToeMove> getMoves() {
        List<TicTacToeMove> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(Piece.Empty)) {
                    moves.add(new TicTacToeMove(i,j));
                }
            }
        }
        return moves;
    }

    @Override
    public int getReward(Player<Piece, TicTacToeMove> p) {
        int reward = 1000;
        if (getStatus().equals(GameStatus.Winner_X)) {
            if (p.getIdentifier().equals(Piece.X)){
                return reward;
            } else {
                return -1 * reward;
            }
        } else if (getStatus().equals(GameStatus.Winner_O)) {
            if (p.getIdentifier().equals(Piece.O)){
                return reward;
            } else {
                return -1 * reward;
            }
        } else {
            return 0;
        }
    }

    public TicTacToeBoard copy() {
        return new TicTacToeBoard(this);
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void placePiece(TicTacToeMove move, Piece p) {
        if (!immutable) {
            if (board[move.getRow()][move.getCol()].equals(Piece.Empty)) {
                board[move.getRow()][move.getCol()] = p;
            } else {
                throw new InvalidMoveException();
            }
        } else {
            throw new UnsupportedOperationException("Can't change immutable TicTacToeBoard");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TicTacToeBoard)) {
            return false;
        }
        TicTacToeBoard b = (TicTacToeBoard) o;
        return Arrays.equals(b.board, this.board);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    @Override
    public String toString() {
        String val = "_____________\n";
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                val += board[i][j] + " ";
            }
            val += "\n";
        }
        return val;
    }

}
