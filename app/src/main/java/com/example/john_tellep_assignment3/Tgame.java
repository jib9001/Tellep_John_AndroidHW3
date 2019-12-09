package com.example.john_tellep_assignment3;

import java.util.Random;

public class Tgame
{
    public char[] gameBoard = new char[9];
    boolean playGame = false;
    Random random = new Random();

    public Tgame()
    {
        newGame();
    }

    public void newGame()
    {
        for (int i = 0; i < gameBoard.length; i++)
        {
            gameBoard[i] = '-';
        }
        playGame = true;
    }

    public void setPlayerMove(int index)
    {
        gameBoard[index] = 'X';
    }

    public int getComputerMove()
    {
        boolean madeMove = false;
        int move = -1;
        while (!madeMove)
        {
            move = random.nextInt(9);
            if (gameBoard[move] == '-')
            {
                gameBoard[move] = 'O';
                madeMove = true;
            }
        }
        return move;
    }

    public int checkForWinner()
    {

        if (checkWins('X') || checkWins('O'))
        {
            return 1;
        }

        boolean stillMoves = false;
        for (char space : gameBoard)
        {
            if (space == '-')
            {
                stillMoves = true;
                break;
            }
        }

        if (!stillMoves)
        {
            return 0;
        }

        return -1;
    }

    private boolean checkWins(char player)
    {
        if (gameBoard[0] == player && gameBoard[1] == player && gameBoard[2] == player)
        {
            return true;
        }
        else if (gameBoard[3] == player && gameBoard[4] == player && gameBoard[5] == player)
        {
            return true;
        }
        else if (gameBoard[6] == player && gameBoard[7] == player && gameBoard[8] == player)
        {
            return true;
        }
        else if (gameBoard[0] == player && gameBoard[3] == player && gameBoard[6] == player)
        {
            return true;
        }
        else if (gameBoard[1] == player && gameBoard[4] == player && gameBoard[7] == player)
        {
            return true;
        }
        else if (gameBoard[2] == player && gameBoard[5] == player && gameBoard[8] == player)
        {
            return true;
        }
        else if (gameBoard[0] == player && gameBoard[4] == player && gameBoard[8] == player)
        {
            return true;
        }
        else if (gameBoard[2] == player && gameBoard[4] == player && gameBoard[6] == player)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}