package com.pokesgi;

import com.pokesgi.poker.Card;
import com.pokesgi.poker.Deck;
import com.pokesgi.poker.Hand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokesgiApplication
{
	public static void main(String[] args)
	{
		// Pour tester
		//SpringApplication.run(PokesgiApplication.class, args) ;

		/* TEST 1 */
		// Nombre de cartes et défausse
		Deck deck= new Deck() ;
		Card C ;

		System.out.println( deck.getTotalCards() ) ;

		while (deck.getTotalCards() != 0 )
		{
			C = deck.drawFromDeck() ;
			System.out.println( C.toString() ) ;
		}

		/* TEST 2 */
		// Création d'une main et évaluation
		for (int i = 0 ; i < 100 ; i++)
		{
			Deck deck2 = new Deck() ;

			Hand hand = new Hand(deck2) ;

			hand.displayEvaluation() ;
			hand.displayHand() ;
		}

		/* TEST 3 */
		// Création de deux mains, évaluations, et comparaison
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
