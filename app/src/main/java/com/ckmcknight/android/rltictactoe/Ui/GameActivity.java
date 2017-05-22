package com.ckmcknight.android.rltictactoe.Ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.ckmcknight.android.rltictactoe.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Model.TicTacToeGame.HumanPlayer;
import Model.TicTacToeGame.Piece;
import Model.TicTacToeGame.TicTacToeBoard;
import Model.TicTacToeGame.TicTacToeGame;
import Model.TicTacToeGame.TicTacToeMove;
import Model.util.Player;

import static android.R.attr.handle;

public class GameActivity extends AppCompatActivity {

    TextView[][] gameLocationTextViews;
    Button playAgainButton, homeScreenButton;
    Map<TextView, int[]> viewToLocMap = new HashMap<>();
    TicTacToeGame ticTacToeGame;
    TicTacToeBoard.GameStatus gameStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpGame();
                playAgainButton.setVisibility(View.INVISIBLE);
                homeScreenButton.setVisibility(View.INVISIBLE);
            }
        });
        homeScreenButton = (Button) findViewById(R.id.homeScreenButton);
        homeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GameActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Find view based on board location
        gameLocationTextViews = new TextView[][]
                        {{(TextView) findViewById(R.id.s0_0), (TextView) findViewById(R.id.s0_1), (TextView) findViewById(R.id.s0_2)},
                        {(TextView) findViewById(R.id.s1_0), (TextView) findViewById(R.id.s1_1), (TextView) findViewById(R.id.s1_2)},
                        {(TextView) findViewById(R.id.s2_0), (TextView) findViewById(R.id.s2_1), (TextView) findViewById(R.id.s2_2)}};

        //Set up Game
        setUpGame();

        // Add board press handlers
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                gameLocationTextViews[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (gameStatus.equals(TicTacToeBoard.GameStatus.ON_GOING)) {
                            TicTacToeMove move = new TicTacToeMove(row,col);
                            TicTacToeBoard currentBoard = ticTacToeGame.execute(move);
                            updateBoard(currentBoard);
                            if (!currentBoard.getStatus().equals(TicTacToeBoard.GameStatus.ON_GOING)) {
                                gameStatus = currentBoard.getStatus();
                                handleEndGame();
                            }
                        }
                    }
                });
            }
        }

    }

    private void setUpGame() {
        ticTacToeGame = TicTacToeGame.createRandomGame((new Random()).nextBoolean());
        updateBoard(ticTacToeGame.execute(null));
        gameStatus = TicTacToeBoard.GameStatus.ON_GOING;

    }

    private void updateBoard(TicTacToeBoard newBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                changeBox(i, j, newBoard.getPiece(i,j).toString());
            }
        }
    }

    private void handleEndGame() {
        System.out.println("Game Over!");
        Toast.makeText(getApplicationContext(), gameStatus.toString(), Toast.LENGTH_SHORT).show();
        playAgainButton.setVisibility(View.VISIBLE);
        homeScreenButton.setVisibility(View.VISIBLE);
    }

    private void changeBox(int row, int col, String mark) {
        gameLocationTextViews[row][col].setText(mark);
    }

}
