package com.pokesgi.poker ;

/**
 * Created by Samuel Bijou on 11/04/2017.
 */
public class Hand
{

    private Card[] cards ;
    private int[] value ;

    Hand(Deck d)
    {
        value = new int[6] ;
        cards = new Card[5] ;
        
        for (int x = 0 ; x < 5 ; x++)
        {
            cards[x] = d.drawFromDeck() ;
        }

        // Do Something
    }


    void displayAll()
    {
        for (int x = 0 ; x < 5 ; x++)
            System.out.println(cards[x]) ;
    }

    int compareTo(Hand that)
    {
        for(int x = 0 ; x < 6 ; x++)
        {
            if(this.value[x] > that.value[x])
                return 1 ;

            else if(this.value[x] < that.value[x])
                return -1 ;
        }

        return 0 ;
    }

}
