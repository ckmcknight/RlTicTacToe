package com.ckmcknight.android.rltictactoe.Model.TicTacToeGame;

import com.ckmcknight.android.rltictactoe.Model.util.Player;

/**
 * Created by charlie on 5/21/17.
 */

public class TicTacToeGame {
    private Player<Piece, TicTacToeMove> otherPlayer;
    private TicTacToeBoard board = new TicTacToeBoard();
    private boolean userFirst;
    private Piece playerPiece;

    private TicTacToeGame(boolean userFirst, Player<Piece, TicTacToeMove> otherPlayer) {
        this.otherPlayer = otherPlayer;
        this.userFirst = userFirst;
        this.playerPiece = otherPlayer.getIdentifier().equals(Piece.X) ? Piece.O : Piece.X;
    }

    public TicTacToeBoard execute(TicTacToeMove move) {
        if (move == null) {
            if (!userFirst) {
                TicTacToeMove aiMove = otherPlayer.move(board.copy());
                board.placePiece(aiMove, otherPlayer.getIdentifier());
            }
            return board;
        } else {
            if (board.getMoves().contains(move)) {
                board.placePiece(move, playerPiece);
                if (board.getStatus().equals(TicTacToeBoard.GameStatus.ON_GOING)) {
                    TicTacToeMove aiMove = otherPlayer.move(board.copy());
                    board.placePiece(aiMove, otherPlayer.getIdentifier());
                }
                return board.copy();
            } else {
                return board.copy();
            }
        }

    }

    public static TicTacToeGame createRandomGame(boolean userFirst) {
        Piece otherPiece = userFirst ? Piece.X : Piece.O;
        Player<Piece, TicTacToeMove> other = new RandomPlayer(otherPiece);
        return new TicTacToeGame(userFirst, other);
    }

    public TicTacToeBoard.GameStatus getGameStatus() {
        return board.getStatus();
    }
}
