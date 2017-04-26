package com.pokesgi.poker ;

import lombok.Getter ;
import lombok.Setter ;

/**
 * Created by Samuel Bijou on 11/04/2017.
 */
@Getter @Setter
public class Hand
{

    private Card[] cards ;
    private int[] value ;

    public Hand(Deck d)
    {
        value = new int[6] ;
        cards = new Card[5] ;

        for (int x = 0 ; x < 5 ; x++)
        {
            cards[x] = d.drawFromDeck() ;
        }

        int[] ranks = new int[14] ;
        int[] orderedRanks = new int[5] ;	 //miscellaneous cards that are not otherwise significant
        boolean flush = true, straight = false ;
        int sameCards = 1, sameCards2 = 1 ;
        int largeGroupRank = 0, smallGroupRank = 0 ;
        int index = 0 ;
        int topStraightValue = 0 ;

        for (int x = 0 ; x <= 13 ; x++)
        {
            ranks[x] = 0 ;
        }

        for (int x = 0 ; x <= 4 ; x++)
        {
            ranks[ cards[x].getRank() ]++ ;
        }

        for (int x = 0 ; x < 4 ; x++)
        {
            if (x == 3)
            {
                if (cards[x].getSuit() != cards[x + 1].getSuit())
                {
                    flush = false ;
                }

                break ;
            }

            if (cards[x].getSuit() != cards[x + 1].getSuit())
            {
                flush = false ;

                break ;
            }
        }


        for (int x = 13 ; x >= 1 ; x--)
        {
            if (ranks[x] > sameCards)
            {
                if (sameCards != 1)
                {
                    largeGroupRank = x ;
                }

                else
                {
                    sameCards2 = sameCards ;
                    smallGroupRank = x ;
                }

                sameCards = ranks[x] ;
            }

            else if (ranks[x] > sameCards2)
            {
                sameCards2 = ranks[x] ;
                smallGroupRank = x ;
            }
        }


        if (ranks[1] == 1)
        {
            orderedRanks[index] = 14 ;
            index++ ;
        }

        for (int x = 13 ; x >= 2 ; x--)
        {
            if (ranks[x] == 1)
            {
                orderedRanks[index] = x ;
                index++ ;
            }
        }


        for (int x = 1 ; x <= 9 ; x++)
        {
            if (ranks[x] == 1 && ranks[x + 1] == 1 && ranks[x + 2] == 1 && ranks[x + 3] == 1 && ranks[x + 4] == 1)
            {
                straight = true ;
                topStraightValue = x + 4 ;
                break ;
            }
        }

        if (ranks[10] == 1 && ranks[11] == 1 && ranks[12] == 1 && ranks[13] == 1 && ranks[1] == 1)
        {
            straight = true ;
            topStraightValue = 14 ;
        }

        for (int x = 0 ; x <= 5 ; x++)
        {
            value[x] = 0 ;
        }


        // commence l'évaluation de la main
        if (sameCards == 1) // carte haute
        {
            value[0] = 1 ;
            value[1] = orderedRanks[0] ;
            value[2] = orderedRanks[1] ;
            value[3] = orderedRanks[2] ;
            value[4] = orderedRanks[3] ;
            value[5] = orderedRanks[4] ;
        }

        if (sameCards == 2 && sameCards2 == 1) // paire
        {
            value[0] = 2 ;
            value[1] = largeGroupRank ;
            value[2] = orderedRanks[0] ;
            value[3] = orderedRanks[1] ;
            value[4] = orderedRanks[2] ;
        }

        if (sameCards == 2 && sameCards2 == 2) //double paire
        {
            value[0] = 3 ;
            value[1] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank ;
            value[2] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank ;
            value[3] = orderedRanks[0] ;
        }

        if (sameCards == 3 && sameCards2 != 2) // brelan
        {
            value[0] = 4 ;
            value[1] = largeGroupRank ;
            value[2] = orderedRanks[0] ;
            value[3] = orderedRanks[1] ;
        }

        if (straight && !flush) // suite (quinte)
        {
            value[0] = 5 ;
            value[1] = topStraightValue ;
        }

        if (flush && !straight) // couleur (flush)
        {
            value[0] = 6 ;
            value[1] = orderedRanks[0] ;
            value[2] = orderedRanks[1] ;
            value[3] = orderedRanks[2] ;
            value[4] = orderedRanks[3] ;
            value[5] = orderedRanks[4] ;
        }

        if (sameCards == 3 && sameCards2 == 2) // full
        {
            value[0] = 7 ;
            value[1] = largeGroupRank ;
            value[2] = smallGroupRank ;
        }

        if (sameCards == 4) // carré
        {
            value[0] = 8 ;
            value[1] = largeGroupRank ;
            value[2] = orderedRanks[0] ;
        }

        if (straight && flush) // suite et couleur (quinte flush)
        {
            value[0] = 9 ;
            value[1] = topStraightValue ;
        }
    }

    public int compareTo(Hand that)
    {
        for (int x = 0 ; x < 6 ; x++)
        {
            if (this.value[x]>that.value[x])
                return 1 ;
            else if (this.value[x] != that.value[x])
                return -1 ;
        }

        return 0 ; // si les mains sont égales
    }

    public void display()
    {
        String s ;

        switch( value[0] )
        {

            case 1 :
                s = "carte haute" ;
                break ;
            case 2 :
                s = "paire de " + Card.rankAsString(value[1]) + "\'s" ;
                break ;
            case 3 :
                s = "double paire " + Card.rankAsString(value[1]) + " " + Card.rankAsString(value[2]) ;
                break ;
            case 4 :
                s = "brelan (triple paire) " + Card.rankAsString(value[1]) + "\'s" ;
                break ;
            case 5 :
                s = "quinte (suite) " + Card.rankAsString(value[1]) ;
                break ;
            case 6 :
                s = "flush (couleur)";
                break ;
            case 7 :
                s = "full " + Card.rankAsString(value[1]) + " sur " + Card.rankAsString(value[2]) ;
                break ;
            case 8 :
                s = "carré " + Card.rankAsString(value[1]) ;
                break ;
            case 9 :
                s = "quinte flush (suite et couleur) " + Card.rankAsString(value[1]) + " high" ;
                break ;
            default:
                s = "error in Hand.display: value[0] contains invalid value" ;
        }

        s = "				" + s ;

        System.out.println(s) ;
    }

    public void displayAll()
    {
        for (int x = 0 ; x < 5 ; x++)
            System.out.println(cards[x]) ;
    }

}
