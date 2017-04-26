package com.pokesgi.poker ;

/**
 * Created by Samuel Bijou on 20/04/2017.
 */

import com.pokesgi.user.UserDTO ;
import lombok.AllArgsConstructor ;
import lombok.Getter ;
import lombok.NoArgsConstructor ;
import lombok.Setter ;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Game
{

    private UserDTO[] users = new UserDTO[10] ;
    private Boolean[] usersReady = new Boolean[10] ;
    private Hand[] usersHand = new Hand[10] ;
    private int[] usersScore = new int[10] ;


    public void startGame()
    {
        Deck deck = new Deck() ;

        if(checkPlayersReady())
        {
            giveCardsAndEvaluate(deck) ;


            String winners = searchWinner() ;


            System.out.println("\nGagnant(s) : " + winners + "\n") ;
        }

        else
        {
            System.out.println("\nTous les joueurs ne sont pas prêts\n") ;
        }
    }

    public int addPlayer(UserDTO newUser)
    {
        for(int x = 0 ; x < users.length ; x++)
        {
            if(users[x] == null)
            {
                users[x] = newUser ;

                return x ;
            }
        }

        return -1 ;
    }

    public UserDTO removePlayer(int userIndex)
    {
        UserDTO result = null ;

        if(userIndex >= 0 && userIndex < 10)
        {
            result = users[userIndex] ;

            users[userIndex] = null ;
            usersReady[userIndex] = false ;
            usersHand[userIndex] = null ;
            usersScore[userIndex] = 0 ;
        }

        return result ;
    }

    public boolean checkPlayersReady()
    {
        boolean playerExist = false ;

        for(int x = 0 ; x < usersReady.length ; x++)
        {
            if(users[x] != null)
            {
                playerExist = true ;

                if(usersReady[x] == null)
                {
                    return false ;
                }
            }
        }

        if(playerExist)
        {
            return true ;
        }

        else
        {
            return false ;
        }
    }

    public void giveCardsAndEvaluate(Deck deck)
    {
        for(int x = 0 ; x < users.length ; x++)
        {
            if(users[x] != null)
            {
                Card[] userCards = new Card[5] ;
                userCards[0] = deck.drawFromDeck() ;
                userCards[1] = deck.drawFromDeck() ;
                userCards[2] = deck.drawFromDeck() ;
                userCards[3] = deck.drawFromDeck() ;
                userCards[4] = deck.drawFromDeck() ;

                usersHand[x] = new Hand(userCards) ;

                usersHand[x].evaluateHand() ;

                usersScore[x] = usersHand[x].getScore() ;
            }
        }
    }

    public String searchWinner()
    {
        int maxScore = -1 ;
        int winnerIndex = -1 ;
        String winnersNames = "" ;

        for(int x = 0 ; x < usersScore.length ; x++)
        {
            if(usersScore[x] > maxScore)
            {
                maxScore = usersScore[x] ;
                winnerIndex = x ;

                winnersNames = users[x].getUsername() ;
            }

            else if(usersScore[x] == maxScore)
            {
                int result = usersHand[x].compareTo(usersHand[winnerIndex]) ;

                if(result == 1)
                {
                    winnerIndex = x ;

                    winnersNames = users[x].getUsername() ;
                }

                else if(result == 0)
                {
                    winnersNames = winnersNames + " " + users[x].getUsername() ;
                }
            }
        }

        return winnersNames ;
    }

}
