package com.pokesgi;

import com.pokesgi.poker.Card;
import com.pokesgi.poker.Deck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokesgiApplication {

	public static void main(String[] args)
	{
		// Pour tester
		//SpringApplication.run(PokesgiApplication.class, args) ;

		Deck deck= new Deck() ;
		Card C ;

		System.out.println( deck.getTotalCards() ) ;

		while (deck.getTotalCards() != 0 )
		{
			C = deck.drawFromDeck() ;
			System.out.println( C.toString() ) ;
		}

		/*
		for (int i = 0 ; i < 100 ; i++)
		{
			Deck deck = new Deck() ;
			Hand hand = new Hand(deck) ;
			hand.display() ;
			hand.displayAll() ;
		}
		 */

		/*
		for (int i = 0 ; i < 20000 ; i++)
		{
			Deck deck = new Deck() ;
			Hand hand = new Hand(deck) ;
			Hand hand2 = new Hand(deck) ;
			hand.display() ;
			hand.displayAll() ;
			hand2.display() ;
			hand2.displayAll() ;

			System.out.println(hand.compareTo(hand2)) ;
		}
		 */
	}
}
