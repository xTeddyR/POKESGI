package com.pokesgi.poker ;

import org.junit.Assert ;
import org.junit.Test ;

/**
 * Created by Samuel Bijou on 19/04/2017.
 */
public class HandTests
{

    @Test
    public void should_Create_Hand_With_Cards()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(0, 9) ;
        cards[1] = new Card(1, 7) ;
        cards[2] = new Card(2, 4) ;
        cards[3] = new Card(3, 1) ;
        cards[4] = new Card(0, 12) ;

        Hand hand = new Hand(cards) ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(cards[0], hand.getCards()[0]) ;
        Assert.assertEquals(cards[1], hand.getCards()[1]) ;
        Assert.assertEquals(cards[2], hand.getCards()[2]) ;
        Assert.assertEquals(cards[3], hand.getCards()[3]) ;
        Assert.assertEquals(cards[4], hand.getCards()[4]) ;
    }

    @Test
    public void should_Create_Hand_With_Deck()
    {
        Deck deck = new Deck() ;

        Hand hand = new Hand(deck) ;

        Assert.assertNotNull(hand) ;

        Assert.assertNotNull(hand.getCards()[0]) ;
        Assert.assertNotNull(hand.getCards()[1]) ;
        Assert.assertNotNull(hand.getCards()[2]) ;
        Assert.assertNotNull(hand.getCards()[3]) ;
        Assert.assertNotNull(hand.getCards()[4]) ;
    }

    @Test
    public void should_Create_Hand_With_Deck_And_Cards()
    {
        Deck deck = new Deck() ;

        int size = deck.getCards().size() ;
        Card[] deckCards = new Card[3] ;
        deckCards[0] = deck.drawFromDeck() ;
        deckCards[1] = deck.drawFromDeck() ;
        deckCards[2] = deck.drawFromDeck() ;

        Hand hand = new Hand(deck, deckCards) ;

        Assert.assertNotNull(hand) ;

        Assert.assertNotNull(hand.getCards()[0]) ;
        Assert.assertNotNull(hand.getCards()[1]) ;
        Assert.assertEquals(deckCards[0], hand.getCards()[2]) ;
        Assert.assertEquals(deckCards[1], hand.getCards()[3]) ;
        Assert.assertEquals(deckCards[2], hand.getCards()[4]) ;
    }


    @Test
    public void should_Evaluate_Hand()
    {
        Deck deck = new Deck() ;
        Hand hand = new Hand(deck) ;

        Hand handNotEvaluated = new Hand(hand.getCards()) ;
        hand.evaluateHand() ;

        Assert.assertNotNull(handNotEvaluated) ;
        Assert.assertNotNull(hand) ;

        Assert.assertNotEquals(handNotEvaluated, hand) ;
    }

    @Test
    public void should_Count_Ranks()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(0, 7) ;
        cards[1] = new Card(1, 8) ;
        cards[2] = new Card(2, 9) ;
        cards[3] = new Card(3, 12) ;
        cards[4] = new Card(0, 3) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(1, hand.getRanks()[7]) ;
        Assert.assertEquals(1, hand.getRanks()[8]) ;
        Assert.assertEquals(1, hand.getRanks()[9]) ;
        Assert.assertEquals(1, hand.getRanks()[12]) ;
        Assert.assertEquals(1, hand.getRanks()[3]) ;
    }

    @Test
    public void should_Order_Ranks_For_Single_Cards()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(0, 7) ;
        cards[1] = new Card(1, 8) ;
        cards[2] = new Card(2, 9) ;
        cards[3] = new Card(3, 12) ;
        cards[4] = new Card(0, 3) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(12, hand.getOrderedRanks()[0]) ;
        Assert.assertEquals(9, hand.getOrderedRanks()[1]) ;
        Assert.assertEquals(8, hand.getOrderedRanks()[2]) ;
        Assert.assertEquals(7, hand.getOrderedRanks()[3]) ;
        Assert.assertEquals(3, hand.getOrderedRanks()[4]) ;
    }

    @Test
    public void should_Not_Have_Single_Cards()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(0, 7) ;
        cards[1] = new Card(1, 7) ;
        cards[2] = new Card(2, 7) ;
        cards[3] = new Card(3, 3) ;
        cards[4] = new Card(0, 3) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(0, hand.getOrderedRanks()[0]) ;
    }

    @Test
    public void should_Be_Flush()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(0, 1) ;
        cards[1] = new Card(0, 6) ;
        cards[2] = new Card(0, 3) ;
        cards[3] = new Card(0, 8) ;
        cards[4] = new Card(0, 5) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertTrue(hand.isFlush()) ;
    }

    @Test
    public void should_Not_Be_Flush()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(0, 1) ;
        cards[1] = new Card(1, 2) ;
        cards[2] = new Card(2, 3) ;
        cards[3] = new Card(3, 4) ;
        cards[4] = new Card(0, 5) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertFalse(hand.isFlush()) ;
    }

    @Test
    public void should_Be_Straight()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(0, 1) ;
        cards[1] = new Card(1, 2) ;
        cards[2] = new Card(2, 3) ;
        cards[3] = new Card(3, 4) ;
        cards[4] = new Card(0, 5) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertTrue(hand.isStraight()) ;
        Assert.assertTrue(hand.getTopStraightValue() > -1) ;
    }

    @Test
    public void should_Not_Be_Straight()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 5) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertFalse(hand.isStraight()) ;
        Assert.assertTrue(hand.getTopStraightValue() == -1) ;
    }

    @Test
    public void should_Search_Combinaisons()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 5) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertNotEquals(-1, hand.getSameCards()) ;
        Assert.assertNotEquals(-1, hand.getSameCards2()) ;

        Assert.assertNotEquals(-1, hand.getLargeGroupRank()) ;
        Assert.assertNotEquals(-1, hand.getSmallGroupRank()) ;
    }

    @Test
    public void should_Find_Pair()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 5) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(2, hand.getSameCards()) ;
        Assert.assertNotEquals(3, hand.getSameCards2()) ;
        Assert.assertNotEquals(2, hand.getSameCards2()) ;
    }

    @Test
    public void should_Find_Double_Pair()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 5) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 5) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(2, hand.getSameCards()) ;
        Assert.assertEquals(2, hand.getSameCards2()) ;
    }

    @Test
    public void should_Find_Brelan()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 7) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(3, hand.getSameCards()) ;
        Assert.assertNotEquals(2, hand.getSameCards2()) ;
    }

    @Test
    public void should_Find_Full()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 7) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 3) ;
        cards[4] = new Card(2, 3) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(3, hand.getSameCards()) ;
        Assert.assertEquals(2, hand.getSameCards2()) ;
    }

    @Test
    public void should_Find_Four()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 7) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 7) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertEquals(4, hand.getSameCards()) ;
    }

    @Test
    public void should_Not_Find_Pair()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 7) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertNotEquals(2, hand.getSameCards()) ;
        Assert.assertNotEquals(2, hand.getSameCards2()) ;
    }

    @Test
    public void should_Not_Find_Double_Pair()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 7) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        boolean haveFirstPair = true ;
        boolean haveSecondPair = true ;
        boolean isDoublePair = true ;

        if(hand.getSameCards() != 2)
        {
            haveFirstPair = false ;
        }

        if(hand.getSameCards2() != 2)
        {
            haveSecondPair = false ;
        }

        if(!(haveFirstPair && haveSecondPair))
        {
            isDoublePair = false ;
        }

        Assert.assertFalse(isDoublePair) ;
    }

    @Test
    public void should_Not_Find_Brelan()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 5) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertNotEquals(3, hand.getSameCards()) ;
        Assert.assertNotEquals(3, hand.getSameCards2()) ;
    }

    @Test
    public void should_Not_Find_Full()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 7) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        boolean haveBrelan = true ;
        boolean havePair = true ;
        boolean isFull = true ;

        if(hand.getSameCards() != 3)
        {
            haveBrelan = false ;
        }

        if(hand.getSameCards2() != 2)
        {
            havePair = false ;
        }

        if(!(haveBrelan && havePair))
        {
            isFull = false ;
        }

        Assert.assertFalse(isFull) ;
    }

    @Test
    public void should_Not_Find_Four()
    {
        Card[] cards = new Card[5] ;
        cards[0] = new Card(1, 7) ;
        cards[1] = new Card(2, 7) ;
        cards[2] = new Card(3, 7) ;
        cards[3] = new Card(0, 6) ;
        cards[4] = new Card(2, 2) ;

        Hand hand = new Hand(cards) ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertNotEquals(4, hand.getSameCards()) ;
        Assert.assertNotEquals(4, hand.getSameCards2()) ;
    }

    @Test
    public void should_Evaluate_Score()
    {
        Deck deck = new Deck() ;

        Hand hand = new Hand(deck) ;

        int initializedScore = hand.getScore() ;

        hand.evaluateHand() ;

        Assert.assertNotNull(hand) ;

        Assert.assertNotNull(hand.getScore()) ;
        Assert.assertTrue(hand.getScore() > 0) ;
        Assert.assertNotEquals(initializedScore, hand.getScore()) ;
    }

    @Test
    public void should_Compare_To()
    {
        Deck deck = new Deck() ;

        Hand hand1 = new Hand(deck) ;
        Hand hand2 = new Hand(deck) ;

        hand1.evaluateHand() ;
        hand2.evaluateHand() ;

        Assert.assertNotNull(hand1) ;
        Assert.assertNotNull(hand2) ;

        Assert.assertNotEquals(hand1, hand2) ;

        Assert.assertNotNull(hand1.compareTo(hand2)) ;
        Assert.assertNotNull(hand2.compareTo(hand1)) ;
    }

    @Test
    public void should_Player1_Win()
    {
        Card[] cardsPlayer1 = new Card[5] ;
        cardsPlayer1[0] = new Card(1, 7) ;
        cardsPlayer1[1] = new Card(2, 7) ;
        cardsPlayer1[2] = new Card(3, 7) ;
        cardsPlayer1[3] = new Card(0, 6) ;
        cardsPlayer1[4] = new Card(2, 2) ;

        Hand Player1 = new Hand(cardsPlayer1) ;
        Player1.evaluateHand() ;

        Card[] cardsPlayer2 = new Card[5] ;
        cardsPlayer2[0] = new Card(3, 4) ;
        cardsPlayer2[1] = new Card(1, 5) ;
        cardsPlayer2[2] = new Card(0, 8) ;
        cardsPlayer2[3] = new Card(3, 9) ;
        cardsPlayer2[4] = new Card(0, 7) ;

        Hand Player2 = new Hand(cardsPlayer2) ;
        Player2.evaluateHand() ;

        Assert.assertNotNull(Player1) ;
        Assert.assertNotNull(Player2) ;

        Assert.assertNotEquals(Player1, Player2) ;

        Assert.assertEquals(1, Player1.compareTo(Player2)) ;
    }

    @Test
    public void should_Player2_Win()
    {
        Card[] cardsPlayer1 = new Card[5] ;
        cardsPlayer1[0] = new Card(1, 1) ;
        cardsPlayer1[1] = new Card(2, 2) ;
        cardsPlayer1[2] = new Card(0, 3) ;
        cardsPlayer1[3] = new Card(0, 4) ;
        cardsPlayer1[4] = new Card(2, 5) ;

        Hand Player1 = new Hand(cardsPlayer1) ;
        Player1.evaluateHand() ;

        Card[] cardsPlayer2 = new Card[5] ;
        cardsPlayer2[0] = new Card(3, 8) ;
        cardsPlayer2[1] = new Card(3, 12) ;
        cardsPlayer2[2] = new Card(3, 3) ;
        cardsPlayer2[3] = new Card(3, 4) ;
        cardsPlayer2[4] = new Card(3, 5) ;

        Hand Player2 = new Hand(cardsPlayer2) ;
        Player2.evaluateHand() ;

        Assert.assertNotNull(Player1) ;
        Assert.assertNotNull(Player2) ;

        Assert.assertNotEquals(Player1, Player2) ;

        Assert.assertEquals(-1, Player1.compareTo(Player2)) ;
    }

    @Test
    public void should_Be_Equal()
    {
        Card[] cardsPlayer1 = new Card[5] ;
        cardsPlayer1[0] = new Card(0, 1) ;
        cardsPlayer1[1] = new Card(1, 2) ;
        cardsPlayer1[2] = new Card(2, 5) ;
        cardsPlayer1[3] = new Card(3, 7) ;
        cardsPlayer1[4] = new Card(0, 11) ;

        Hand Player1 = new Hand(cardsPlayer1) ;
        Player1.evaluateHand() ;

        Card[] cardsPlayer2 = new Card[5] ;
        cardsPlayer2[0] = new Card(3, 2) ;
        cardsPlayer2[1] = new Card(2, 7) ;
        cardsPlayer2[2] = new Card(1, 11) ;
        cardsPlayer2[3] = new Card(0, 5) ;
        cardsPlayer2[4] = new Card(3, 1) ;

        Hand Player2 = new Hand(cardsPlayer2) ;
        Player2.evaluateHand() ;

        Assert.assertNotNull(Player1) ;
        Assert.assertNotNull(Player2) ;

        Assert.assertNotEquals(Player1, Player2) ;

        Assert.assertEquals(0, Player1.compareTo(Player2)) ;
    }

}
