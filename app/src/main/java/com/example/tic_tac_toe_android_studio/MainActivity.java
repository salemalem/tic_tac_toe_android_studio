package com.example.tic_tac_toe_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red
    int activePlayer = 0;
    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // 2 means unplayed

    int[][] winningPositions = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // horizontal
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // vertical
        {0, 4, 8}, {2, 4, 6}              // cross
    };

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        //  System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(1000).setDuration(900);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2
                ) {
                    // Someone has won!
                    gameIsActive = false;
                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText((winner + " has won!"));

                    LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgainLayout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;


                    for (int counterState : gameState) {
                        if (counterState == 2) {
                            gameIsOver = false;
                            gameIsActive = true;
                        }
                    }

                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("DRAW");

                        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        playAgainLayout.setVisibility(View.VISIBLE);
                        gameIsActive = false;
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        // resetting
        // 0 = yellow, 1 = red
        activePlayer = 0;

        // 2 means unplayed
        //        gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        //        it's not allowed to gamestate =
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "MAKE WITH <3 \nFROM SHYNGYS",
                Toast.LENGTH_LONG).show();
    }
}
