package com.pokesgi.poker ;

import lombok.AllArgsConstructor ;
import lombok.Getter ;
import lombok.Setter ;

/**
 * Created by Samuel Bijou.
 */
@Getter @Setter
public class Card
{

    private int rank, suit ;

    private static String[] suits = { "coeur", "pique", "carreau", "trèfle" } ;
    private static String[] ranks  = { "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Reine", "Roi" } ;


    public Card(short suit, short rank)
    {
        this.rank = rank ;
        this.suit = suit ;
    }


    public static String rankAsString( int rank )
    {
        rank = convertToModulo(rank) ;

        return ranks[rank] ;
    }

    public @Override String toString()
    {
        rank = convertToModulo(rank) ;

        return ranks[rank] + " de " + suits[suit] ;
    }

    public static int convertToModulo(int rank)
    {
        if(rank == 14)
        {
            return 0 ;
        }

        return rank ;
    }

}
