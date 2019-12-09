package com.example.john_tellep_assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    TextView gameStatus;
    Button[] gameButtons;
    Button newGame;
    Tgame game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameButtons = new Button[9];
        gameButtons[0] = findViewById(R.id.button0);
        gameButtons[1] = findViewById(R.id.button1);
        gameButtons[2] = findViewById(R.id.button2);
        gameButtons[3] = findViewById(R.id.button3);
        gameButtons[4] = findViewById(R.id.button4);
        gameButtons[5] = findViewById(R.id.button5);
        gameButtons[6] = findViewById(R.id.button6);
        gameButtons[7] = findViewById(R.id.button7);
        gameButtons[8] = findViewById(R.id.button8);

        gameStatus = findViewById(R.id.gameStatus);

        for(Button button : gameButtons)
        {
            button.setOnClickListener(onPlayButtonClicked);
        }

        newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener(onNewGameButtonClicked);

        game = new Tgame();
    }

    private View.OnClickListener onNewGameButtonClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            game = new Tgame();
            for (Button square : gameButtons)
            {
                square.setText("");
            }

            gameStatus.setText("Keep Playing");

        }
    };

    private View.OnClickListener onPlayButtonClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            Button gameButton = (Button) v;

            int index = -1;
            for (int i = 0; i < gameButtons.length; i++)
            {
                if (gameButton.getId() == gameButtons[i].getId())
                {
                    index = i;
                }

            }

            boolean stillPlaying = true;
            if (game.playGame && game.gameBoard[index] == '-' && stillPlaying) {
                game.setPlayerMove(index);
                gameButton.setTextColor(Color.rgb(102,255,102));
                stillPlaying = !isGameOver(1);

                if (stillPlaying) {
                    int move = game.getComputerMove();
                    gameButtons[move].setTextColor(Color.rgb(51,204,255));
                    stillPlaying = !isGameOver(0);
                }
            }
        }

    };

    private boolean isGameOver(int player)
    {
        for (int i = 0; i < gameButtons.length; i++)
        {
            if (game.gameBoard[i] != '-')
            {
                gameButtons[i].setText(String.valueOf(game.gameBoard[i]));
            }
        }

        if (game.checkForWinner() == 1)
        {
            game.playGame = false;
            if (player == 0)
            {
                gameStatus.setText("Lose");
            }
            else
            {
                gameStatus.setText("Win");
            }
            return true;
        }
        else if (game.checkForWinner() == 0)
        {
            game.playGame = false;
            gameStatus.setText("Tie");
            return true;
        }
        else
        {
            gameStatus.setText("Keep Playing");
            return false;
        }
    }

}
