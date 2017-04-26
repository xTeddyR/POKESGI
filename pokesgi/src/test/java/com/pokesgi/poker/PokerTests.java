package com.pokesgi.poker ;

import org.junit.Test ;

/**
 * Created by Samuel Bijou on 19/04/2017.
 */
public class PokerTests
{

    /* TESTS SIMPLES */

    /* TEST 1 */
    // Nombre de cartes
    @Test
    public void should_Give_Number_Of_Cards()
    {
        System.out.println("\n[TEST DE TAILLE DU PAQUET]") ;

        Deck deck= new Deck() ;
        Card C ;

        System.out.println( deck.getTotalCards() + "\n") ;
    }

    /* TEST 2 */
    // Défausse de cartes
    @Test
    public void should_Draw_Cards_From_Deck()
    {
        System.out.println("\n[TEST DE DEFAUSSE DU PAQUET]") ;

        Deck deck= new Deck() ;
        Card C ;

        while (deck.getTotalCards() != 0 )
        {
            C = deck.drawFromDeck() ;
            System.out.println( C.toString() ) ;
        }

        System.out.println("\n") ;
    }

    /* TEST 3 */
    // Création d'une main et évaluation
    @Test
    public void should_Evaluate_Hand()
    {
        System.out.println("\n[TEST D'EVALUATION D'UNE MAIN]") ;

        for (int i = 0 ; i < 100 ; i++)
        {
            Deck deck2 = new Deck() ;

            Hand hand = new Hand(deck2) ;
            hand.evaluateHand() ;

            hand.displayEvaluation() ;
            hand.displayHand() ;

            System.out.println("\n") ;
        }
    }

    /* TEST 4 */
    // Création de deux mains, évaluations, et comparaison
    @Test
    public void should_Comparate_Two_Hands()
    {
        System.out.println("\n[TEST DE COMPARAISON DE DEUX MAINS]") ;

        for (int i = 0 ; i < 1000 ; i++)
        {
            Deck deck3 = new Deck() ;

            System.out.println("\n[JOUEUR 1]") ;

            Hand hand = new Hand(deck3) ;

            hand.evaluateHand() ;

            hand.displayEvaluation() ;
            hand.displayHand() ;


            System.out.println("\n[JOUEUR 2]") ;

            Hand hand2 = new Hand(deck3) ;

            hand2.evaluateHand() ;

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
