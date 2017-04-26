package com.pokesgi.poker ;

import org.junit.Assert ;
import org.junit.Test ;

/**
 * Created by Samuel Bijou on 19/04/2017.
 */
public class DeckTests
{

    @Test
    public void should_Create_Deck()
    {
        Deck deck = new Deck() ;

        Assert.assertNotNull(deck.getCards().get(0)) ;
    }

    @Test
    public void should_Deck_Have_52_Cards()
    {
        Deck deck = new Deck() ;

        Assert.assertEquals(52, deck.getCards().size()) ;
    }


    @Test
    public void should_Shuffle_Deck()
    {
        Deck deck = new Deck() ;

        Card card1 = deck.getCards().get(0) ;

        deck.shuffle() ;

        Card card2 = deck.getCards().get(0) ;

        Assert.assertNotEquals(card1, card2) ;
    }

    @Test
    public void should_Draw_One_Card()
    {
        Deck deck = new Deck() ;

        Card card = deck.drawFromDeck() ;

        Assert.assertNotNull(card) ;
        Assert.assertEquals(51, deck.getTotalCards()) ;
    }

    @Test
    public void should_Draw_Two_Cards()
    {
        Deck deck = new Deck() ;

        Card card1 = deck.drawFromDeck() ;
        Card card2 = deck.drawFromDeck() ;

        Assert.assertNotNull(card1) ;
        Assert.assertNotNull(card2) ;
        Assert.assertNotEquals(card1, card2) ;
        Assert.assertEquals(50, deck.getTotalCards()) ;
    }

    @Test
    public void should_Get_Number_Of_Cards()
    {
        Deck deck = new Deck() ;

        Assert.assertEquals(52, deck.getTotalCards()) ;
    }

}
