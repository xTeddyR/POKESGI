package com.pokesgi.poker ;

import lombok.Getter ;
import lombok.Setter ;

/**
 * Created by Samuel Bijou on 11/04/2017.
 */
@Getter @Setter
public class Card
{

    private int suit, rank ;

    private static final String[] suits = { "Coeur", "Pique", "Carreau", "Trèfle" } ;
    private static final String[] ranks = { "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Reine", "Roi" } ;


    public Card(int newSuit, int newRank)
    {
        this.rank = newRank ;
        this.suit = newSuit ;
    }


    public static String rankAsString( int newRank )
    {
        newRank = convertRank(newRank) ;

        return ranks[newRank] ;
    }

    public @Override String toString()
    {
        rank = convertRank(rank) ;

        return ranks[rank] + " de " + suits[suit] ;
    }

    public static int convertRank(int newRank)
    {
        if(newRank == 13)
        {
            return 0 ;
        }

        return newRank ;
    }

}
