package com.pokesgi.poker ;

import lombok.Getter ;
import lombok.Setter ;

import java.util.Random ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 11/04/2017.
 */
@Getter @Setter
public class Deck
{

    private ArrayList<Card> cards ;

    public Deck()
    {
        cards = new ArrayList<Card>() ;

        for(short suitCount = 0 ; suitCount <= 3 ; suitCount++)
        {
            for(short rankCount = 0 ; rankCount <= 12 ; rankCount++)
            {
                cards.add( new Card(suitCount, rankCount) ) ;
            }
        }

        Shuffle() ;
    }

    public void Shuffle()
    {
        int size = cards.size() - 1 ;

        int index_1, index_2 ;
        Random generator = new Random() ;

        Card temp ;

        for(short i = 0 ; i < 100 ; i++)
        {
            index_1 = generator.nextInt( size ) ;
            index_2 = generator.nextInt( size ) ;

            temp = cards.get( index_2 ) ;
            cards.set( index_2 , cards.get( index_1 ) ) ;
            cards.set( index_1, temp ) ;
        }
    }

    public Card drawFromDeck()
    {
        return cards.remove( cards.size() - 1 ) ;
    }

    public int getTotalCards()
    {
        return cards.size() ;
    }

}
