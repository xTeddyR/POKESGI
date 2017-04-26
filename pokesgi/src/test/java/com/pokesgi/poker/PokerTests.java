package com.pokesgi.poker ;

import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

/**
 * Created by Samuel Bijou on 26/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PokerTests
{

    /* TESTS SIMPLES */

    /* TEST 1 */
    // Nombre de cartes
    @Test
    public void shouldGiveNumberOfCards()
    {
        Deck deck= new Deck() ;
        Card C ;

        System.out.println( deck.getTotalCards() ) ;
    }

    /* TEST 2 */
    // Défausse de cartes
    @Test
    public void shouldDrawCardsFromDeck()
    {
        Deck deck= new Deck() ;
        Card C ;

        while (deck.getTotalCards() != 0 )
        {
            C = deck.drawFromDeck() ;
            System.out.println( C.toString() ) ;
        }
    }

    /* TEST 3 */
    // Création d'une main et évaluation
    public void shouldEvaluateHands()
    {
        for (int i = 0 ; i < 100 ; i++)
        {
            Deck deck2 = new Deck() ;

            Hand hand = new Hand(deck2) ;

            hand.displayEvaluation() ;
            hand.displayHand() ;
        }
    }

    /* TEST 4 */
    // Création de deux mains, évaluations, et comparaison
    public void shouldComparateTwoHands()
    {
        for (int i = 0 ; i < 20000 ; i++)
        {
            Deck deck3 = new Deck() ;

            System.out.println("\n[JOUEUR 1]") ;
            Hand hand = new Hand(deck3) ;
            hand.displayEvaluation() ;
            hand.displayHand() ;

            System.out.println("\n[JOUEUR 2]") ;
            Hand hand2 = new Hand(deck3) ;
            hand2.displayEvaluation() ;
            hand2.displayHand() ;


            int result = hand.compareTo(hand2) ;
            String gagnant ;

            System.out.println("\n{GAGNANT}") ;

            if(result == 1)
            {
                gagnant = "Joueur 1" ;
            }

            else if(result == -1)
            {
                gagnant = "joueur 2" ;
            }

            else
            {
                gagnant = "égalité" ;
            }

            System.out.println(gagnant + "\n") ;
        }
    }

}
