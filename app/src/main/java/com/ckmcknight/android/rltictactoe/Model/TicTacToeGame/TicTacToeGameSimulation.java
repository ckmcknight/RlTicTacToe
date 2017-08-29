package com.ckmcknight.android.rltictactoe.Model.TicTacToeGame;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ckmcknight.android.rltictactoe.Model.util.Player;

/**
 * Created by charlie on 5/19/17.
 */

public class TicTacToeGameSimulation {
    private Player<Piece, TicTacToeMove> p1;
    private Player<Piece, TicTacToeMove> p2;
    private TicTacToeBoard board;

    public TicTacToeGameSimulation(Player<Piece, TicTacToeMove> p1, Player<Piece, TicTacToeMove> p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new TicTacToeBoard();
    }

    public void playComplete() {
        boolean p1Turn = true;
        while (board.getStatus().equals(TicTacToeBoard.GameStatus.ON_GOING)) {
            Player<Piece, TicTacToeMove> currentPlayer = p1Turn ? p1 : p2;
            TicTacToeMove move = currentPlayer.move(board.copy());
            board.placePiece(move, currentPlayer.getIdentifier());
            p1Turn = !p1Turn;
        }

        if (board.getStatus().equals(TicTacToeBoard.GameStatus.DRAW)) {
            System.out.println("Game was a draw");
        } else if (board.getStatus().equals(TicTacToeBoard.GameStatus.Winner_X)) {
            System.out.println(Piece.X + " Wins!");
        } else if (board.getStatus().equals(TicTacToeBoard.GameStatus.Winner_O)) {
            System.out.println(Piece.O + " Wins!");
        } else {
            Logger.getAnonymousLogger().log(Level.WARNING, "The end of game status is wrong");
        }
    }

    public static void main(String[] args) {
        Player<Piece, TicTacToeMove> p1 = new RandomPlayer(Piece.X);
        Player<Piece, TicTacToeMove> p2 = new HumanPlayer(Piece.O);
        TicTacToeGameSimulation game = new TicTacToeGameSimulation(p1, p2);
        game.playComplete();
    }

}
