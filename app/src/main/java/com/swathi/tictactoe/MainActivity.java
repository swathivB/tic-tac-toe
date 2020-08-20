package com.swathi.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; // 0 is player1 and 1 is player2

    boolean isGameActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    // Views
    LinearLayout scoreLayout;
    TextView winner;
    Button playAgain;
    TextView Score1,Score2;
    int player1points,player2points;
    EditText etPlayer1,etPlayer2;

    ImageView image0, image1, image2, image3, image4, image5, image6, image7, image8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialising all views
        scoreLayout = findViewById(R.id.score_layout);
        winner = findViewById(R.id.winner);
        Score1=findViewById(R.id.txtScore1);
        Score2=findViewById(R.id.txtScore2);
        playAgain = findViewById(R.id.play_again);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image0 = findViewById(R.id.image0);
        etPlayer1=findViewById(R.id.etPlayer1);
        etPlayer2=findViewById(R.id.etPlayer2);
        Toast.makeText(this,"Set player names",Toast.LENGTH_LONG).show();
    }
    public void onTap(View view) {

        ImageView tappedImage = (ImageView) view;

        int tappedPosition = Integer.parseInt(tappedImage.getTag().toString());
        etPlayer2.setEnabled(false);
        etPlayer1.setEnabled(false);


        if (gameState[tappedPosition] == 2 && isGameActive) {

            gameState[tappedPosition] = activePlayer;

            tappedImage.setTranslationY(-1000f);

            if(activePlayer == 0) {
               tappedImage.setImageResource(R.drawable.x);
            } else {
                tappedImage.setImageResource(R.drawable.o);
            }

            tappedImage.animate().translationYBy(1000f).rotation(360).setDuration(500);

            if(activePlayer == 0) {
                Toast.makeText( this,"player2 turn",Toast.LENGTH_SHORT).show();

                activePlayer = 1;
            } else {
                Toast.makeText( this,"player1 turn",Toast.LENGTH_SHORT).show();
                activePlayer = 0;
            }

            for (int[] winningPosition : winningPositions) {


                if((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                        (gameState[winningPosition[2]] != 2)) {

                    isGameActive = false;
                    String text1=etPlayer1.getText().toString();
                    String text2=etPlayer2.getText().toString();
                    String winnerText = "";
                    Integer score=0;

                    if(gameState[winningPosition[0]] == 0) {
                       winnerText=text1+" has won the game";

                        player1points++;
                        Score1.setText(" "+player1points);

                    } else {
                        winnerText = text2+" has won the game";
                        player2points++;
                        Score2.setText(" " + player2points);
                    }

                    winner.setText(winnerText);
                    scoreLayout.setVisibility(View.VISIBLE);

                } else {

                    boolean isGameADraw = true;

                    for (int state : gameState) {
                        if(state == 2) {
                            isGameADraw = false;
                        }
                    }

                    if(isGameADraw) {
                        winner.setText("This Game is a Draw");
                        scoreLayout.setVisibility(View.VISIBLE);
                    }

                }

            }

        }

    }

    public void playAgain(View view) {

        isGameActive = true;

        activePlayer = 0;


        scoreLayout.setVisibility(View.INVISIBLE);

        for (int i =0; i<9; i++) {
            gameState[i] = 2;
        }

        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
        image7.setImageResource(0);
        image8.setImageResource(0);
        image0.setImageResource(0);


    }
}