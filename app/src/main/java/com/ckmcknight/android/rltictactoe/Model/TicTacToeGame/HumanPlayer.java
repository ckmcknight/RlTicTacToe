package com.ckmcknight.android.rltictactoe.Model.TicTacToeGame;

import java.util.Scanner;

import com.ckmcknight.android.rltictactoe.Model.util.Player;
import com.ckmcknight.android.rltictactoe.Model.util.State;

/**
 * Human player for use in game simulations. Not for use with the UI.
 */
public class HumanPlayer implements Player<Piece, TicTacToeMove> {
    private Piece p;
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(Piece p) {
        this.p = p;
    }

    @Override
    public TicTacToeMove move(State board) {
        boolean legalMove = false;
        TicTacToeMove move = null;
        System.out.println("\n");
        while (!legalMove) {
            System.out.println("Current Board");
            System.out.println(board);
            System.out.println(p + "'s Turn");
            System.out.print("Row?: ");
            int r = scanner.nextInt();
            System.out.print("Col?: ");
            int c = scanner.nextInt();
            move = new TicTacToeMove(r,c);
            if (board.getMoves().contains(move)) {
                legalMove = true;
            } else {
                System.out.println("Illegal Move! Try again.");
            }
        }
        return move;
    }

    @Override
    public Piece getIdentifier() {
        return p;
    }
}
