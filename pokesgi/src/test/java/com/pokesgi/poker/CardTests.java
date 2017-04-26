package com.pokesgi.poker ;

import org.junit.Assert ;
import org.junit.Test ;

/**
 * Created by Samuel Bijou on 19/04/2017.
 */
public class CardTests
{

    private String[] suits = { "Coeur", "Pique", "Carreau", "Trèfle" } ;
    private String[] ranks = { "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Reine", "Roi" } ;


    @Test
    public void should_Create_7_Of_Hearts()
    {
        Card card = new Card(0, 6) ;

        Assert.assertEquals("7", ranks[card.getRank()]) ;
        Assert.assertEquals("Coeur", suits[card.getSuit()]) ;
    }

    @Test
    public void should_Create_3_Of_Spades()
    {
        Card card = new Card(1, 2) ;

        Assert.assertEquals("3", ranks[card.getRank()]) ;
        Assert.assertEquals("Pique", suits[card.getSuit()]) ;
    }

    @Test
    public void should_Create_4_Of_Clubs()
    {
        Card card = new Card(3, 3) ;

        Assert.assertEquals("4", ranks[card.getRank()]) ;
        Assert.assertEquals("Trèfle", suits[card.getSuit()]) ;
    }

    @Test
    public void should_Create_Ace_Of_Diamond()
    {
        Card card = new Card(2, 0) ;

        Assert.assertEquals("As", ranks[card.getRank()]) ;
        Assert.assertEquals("Carreau", suits[card.getSuit()]) ;
    }


    @Test
    public void should_Give_Rank_As_String()
    {
        Assert.assertEquals("Roi", Card.rankAsString(12)) ;
    }

    @Test
    public void should_Convert_Rank()
    {
        int rank = 14 ;

        Assert.assertEquals(0, Card.convertRank(rank)) ;
    }

    @Test
    public void should_Not_Convert_Rank()
    {
        int rank = 7 ;

        Assert.assertEquals(7, Card.convertRank(rank)) ;
    }

}
