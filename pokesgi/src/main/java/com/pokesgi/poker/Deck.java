package com.pokesgi.poker ;

import java.util.Random ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 11/04/2017.
 */
public class Deck
{

    private ArrayList<Card> cards ;

    Deck()
    {
        cards = new ArrayList<Card>() ;
        int index_1, index_2 ;
        Random generator = new Random() ;
        Card temp ;

        for(short suitCount = 1 ; suitCount <= 4 ; suitCount++)
        {
            for(short rankCount = 1 ; rankCount <= 13 ; rankCount++)
            {
                cards.add( new Card(suitCount, rankCount) ) ;
            }
        }

        for(int i = 0 ; i < 100 ; i++)
        {
            index_1 = generator.nextInt( cards.size() - 1 );
            index_2 = generator.nextInt( cards.size() - 1 );

            temp = cards.get( index_2 );
            cards.set( index_2 , cards.get( index_1 ) );
            cards.set( index_1, temp );
        }
    }

    public Card drawFromDeck()
    {
        return cards.remove( 0 ) ;
    }

    public int getTotalCards()
    {
        return cards.size() ;
    }

}
