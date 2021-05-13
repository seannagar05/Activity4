package com.example.activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView text_player_one_score, text_player_two_score, roundshow, drawcounter;
    private Button[] button = new Button[9];
    private Button button_reset;
    private int playeronescorecount,playertwoscorecount,roundcount, round, draw;
    boolean activePlayer;

    //p1 = 0 p2 = 1 empty = 2

    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningStates = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        text_player_one_score = (TextView) findViewById(R.id.txt_player_one_score);
        text_player_two_score = (TextView) findViewById(R.id.txt_player_two_score);
        button_reset = (Button) findViewById(R.id.btn_reset);
        roundshow = (TextView)findViewById(R.id.textView4);
                drawcounter = (TextView)findViewById(R.id.textView2);

        for(int i=0; i < button.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            button[i] = (Button) findViewById(resourceID);
            button[i].setOnClickListener(this);

        }

        roundcount = 0;
        playeronescorecount = 0;
        playertwoscorecount = 0;
        round = 1;
        draw = 0;
        activePlayer = true;
    }

    @Override
    public void onClick(View view) {
        //Log.i("test","button is clicked");
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if(activePlayer){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#010101"));
            gameState[gameStatePointer] = 0;
        }else{
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#010101"));
            gameState[gameStatePointer] = 1;
        }
        roundcount++;

        if(checkWinner()){
            if(activePlayer){
                Toast.makeText(this, "Player One Won", Toast.LENGTH_SHORT).show();
                round++;
                playeronescorecount++;
                updateround();
                updatePlayerScore();
                playAgain();
            }else{
                Toast.makeText(this, "Player Two Won!", Toast.LENGTH_SHORT).show();
                round++;
                playertwoscorecount++;
                updatePlayerScore();
                updateround();
                playAgain();
            }
        }else if(roundcount == 9){


            round++;
            draw++;
            updateround();
            updatedraw();
            playAgain();
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();

        }else{
            activePlayer = !activePlayer;
        }

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playeronescorecount = 0;
                playertwoscorecount = 0;
                updatePlayerScore();
            }
        });
    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winningState:winningStates){
            if(gameState[winningState[0]]==gameState[winningState[1]] &&
                    gameState[winningState[1]]==gameState[winningState[2]] &&
                        gameState[winningState[0]] !=2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        text_player_one_score.setText(Integer.toString(playeronescorecount));
        text_player_two_score.setText(Integer.toString(playertwoscorecount));
    }

    public void playAgain(){
        roundcount = 0;
        activePlayer = true;

        for(int i = 0; i < button.length; i++){
            gameState[i] = 2;
            button[i].setText("");
        }
    }


    public void updateround() { roundshow.setText(Integer.toString(round));}
    public void updatedraw() { drawcounter.setText(Integer.toString(draw));}
    }


