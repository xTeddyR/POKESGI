package com.pokesgi.poker ;

import lombok.AllArgsConstructor ;
import lombok.Getter ;
import lombok.Setter ;

/**
 * Created by Samuel Bijou.
 */
@AllArgsConstructor
@Getter @Setter
public class Card
{

    private short rank, suit ;

    private static String[] suits = { "coeur", "trèfle", "carreau", "pique" } ;
    private static String[] ranks  = { "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Reine", "Roi" } ;


    public static String rankAsString( int __rank )
    {
        return ranks[__rank] ;
    }

    public @Override String toString()
    {
        return ranks[rank] + " de " + suits[suit] ;
    }

}
