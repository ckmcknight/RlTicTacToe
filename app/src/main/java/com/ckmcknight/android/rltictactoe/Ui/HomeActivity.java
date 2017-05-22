package com.ckmcknight.android.rltictactoe.Ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ckmcknight.android.rltictactoe.R;

public class HomeActivity extends AppCompatActivity {

    Button newGameButton;
    Button viewStatsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        newGameButton = (Button) findViewById(R.id.newGameButton);
        viewStatsButton = (Button) findViewById(R.id.viewStatsButton);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        viewStatsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
    }


}
