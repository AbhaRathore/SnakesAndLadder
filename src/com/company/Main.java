package com.company;

import java.util.Scanner;         //import Scanner module to get scan or read user input
import java.util.Map;             //import Map module to place snakes and ladders at different locations.
import java.util.HashMap;         //import HashMap module to store map values in hash values format i.e. in key-value pair.

// Main class for with Snakes and ladder game
class SnakeAndLadderGame
{

    final static int WinScore = 100;    //final and static keywords to keep the WinScore value unaltered.

    static Map<Integer, Integer> snake = new HashMap<>();
    static Map<Integer, Integer> ladder = new HashMap<>();

    // Storing snakes and ladders as key-value pairs using hashmap put() function.
    {
        snake.put(98,23);
        snake.put(86,36);
        snake.put(73,54);
        snake.put(51,42);
        snake.put(29,4);

        ladder.put(8,18);
        ladder.put(25,48);
        ladder.put(38,69);
        ladder.put(58,76);
        ladder.put(81,94);
    }

    // Public method to roll a dice
    public int rollDice()
    {
        double random = Math.random();
        random = (random * 6) + 1;
        int randomInt = (int) random;
        return randomInt;
    }

    // Public method to get current players score
    public int playerScore(int playerCurrentScore, int diceValue)
    {
        playerCurrentScore += diceValue;

        if (playerCurrentScore > WinScore)
        {
            playerCurrentScore -= diceValue;
            return playerCurrentScore;
        }

        if (snake.get(playerCurrentScore) != null)
        {
            System.out.println("Oops! Swallowed by Snake on " + playerCurrentScore);
            playerCurrentScore = snake.get(playerCurrentScore);
        }

        if (ladder.get(playerCurrentScore) != null)
        {
            System.out.println("Wow! You climbed up the ladder from " + playerCurrentScore);
            playerCurrentScore = ladder.get(playerCurrentScore);
        }

        return playerCurrentScore;
    }

    public boolean gameResult(int playerCurrentScore)
    {
        return WinScore == playerCurrentScore;
    }

    public void PlayGame()
    {
        Scanner n = new Scanner(System.in);
        System.out.println("Enter number of players: ");
        String player = n.next();
        int playerCount = Integer.parseInt( player );
        int players[] = new int[playerCount+1];

        for (int i=1; i< playerCount+1; i++)
        {
            players[i] = 0;
        }

        int diceValue = 0;
        Scanner s = new Scanner(System.in);
        String str = "";

        do
        {
            for (int i=1; i< playerCount+1; i++){
                System.out.println("\n\nPlayer"+ i +" Turn");
                System.out.println("Press r to Roll Dice");
                str = s.next();
                diceValue = rollDice();

                if (diceValue == 6){
                    System.out.println("Dice Value for Player" + i + " :: " + diceValue);
                    System.out.println("Yay! You got a Six. Do you want to roll dice again? Press y for Yes.");
                    Scanner input = new Scanner(System.in);
                    char userInput = input.next().charAt(0);

                    if (userInput == 'y'){
                        diceValue += rollDice();
                    }
                }
//                System.out.println(diceValue);

                System.out.println("Dice Value for Player" + i + " :: " + diceValue);
                players[i] = playerScore(players[i], diceValue);

                for (int j=1; j< playerCount+1; j++){
                    System.out.println("Player" + j + " Score :: " + players[j]);
                }

                System.out.println("------------------------------");

                if (gameResult(players[i])){
                    System.out.println("Congratulations! Player" + i +" Wins.");
                    return;
                }
            }

        } while ("r".matches(str));
    }

}

public class Main {

    public static void main(String[] args)
    {
        SnakeAndLadderGame S = new SnakeAndLadderGame();
        S.PlayGame();
    }
}
