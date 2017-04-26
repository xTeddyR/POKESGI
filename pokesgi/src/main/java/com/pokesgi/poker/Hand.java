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


        int[] ranks = countRanks() ;

        int[] orderedRanks = orderRanksForSingleCards(ranks) ;


        int sameCards = 1, sameCards2 = 1 ;
        int largeGroupRank = 0, smallGroupRank = 0 ;

        boolean flush = isFlush() ;

        boolean straight = isStraight(ranks) ;
        int topStraightValue = orderedRanks[0] ;


        // Permet de gérer la hauteur des Paires ou du Brelan de la main
        for (int x = 12 ; x >= 0 ; x--)
        {
            if (ranks[x] > sameCards) // Si la combinaison (Paire ou Brelan) la plus haute est supérieure à l'ancienne plus haute
            {
                if (sameCards == 1) // Si l'ancienne combinaison (Paire ou Brelan) la plus haute n'a pas été initialisée (celle par défaut)
                                    // La met comme combinaison la plus haute
                {
                    largeGroupRank = x ;
                }

                else // Sinon, sauvegarde l'ancienne combinaison (Paire ou Brelan) la plus haute comme étant la moins haute
                {
                    sameCards2 = sameCards ;
                    smallGroupRank = x ;
                }

                sameCards = ranks[x] ; // Sauvegarde la nouvelle combinaison (Paire ou Brelan) la plus haute
            }

            else if (ranks[x] > sameCards2) // Sinon si, la combinaire (Paire ou Brelan) la plus haute est supérieure à l'ancienne moins haute
            {
                sameCards2 = ranks[x] ;
                smallGroupRank = x ;
            }
        }


        // Initialise l'évaluation de la main
        for (int x = 0 ; x <= 5 ; x++)
        {
            value[x] = 0 ;
        }


        // Commence l'évaluation de la main
        if (sameCards == 1) // carte haute
        {
            value[0] = 1 ;
            value[1] = orderedRanks[0] ;
            value[2] = orderedRanks[1] ;
            value[3] = orderedRanks[2] ;
            value[4] = orderedRanks[3] ;
            value[5] = orderedRanks[4] ;
        }

        if (sameCards == 2 && sameCards2 == 1) // Paire
        {
            value[0] = 2 ;
            value[1] = largeGroupRank ;
            value[2] = orderedRanks[0] ;
            value[3] = orderedRanks[1] ;
            value[4] = orderedRanks[2] ;
        }

        if (sameCards == 2 && sameCards2 == 2) // Double Paire
        {
            value[0] = 3 ;
            value[1] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank ;
            value[2] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank ;
            value[3] = orderedRanks[0] ;
        }

        if (sameCards == 3 && sameCards2 != 2) // Brelan
        {
            value[0] = 4 ;
            value[1] = largeGroupRank ;
            value[2] = orderedRanks[0] ;
            value[3] = orderedRanks[1] ;
        }

        if (straight && !flush) // Suite (Quinte)
        {
            value[0] = 5 ;
            value[1] = topStraightValue ;
        }

        if (flush && !straight) // Couleur (Flush)
        {
            value[0] = 6 ;
            value[1] = orderedRanks[0] ;
            value[2] = orderedRanks[1] ;
            value[3] = orderedRanks[2] ;
            value[4] = orderedRanks[3] ;
            value[5] = orderedRanks[4] ;
        }

        if (sameCards == 3 && sameCards2 == 2) // Full
        {
            value[0] = 7 ;
            value[1] = largeGroupRank ;
            value[2] = smallGroupRank ;
        }

        if (sameCards == 4) // Carré
        {
            value[0] = 8 ;
            value[1] = largeGroupRank ;
            value[2] = orderedRanks[0] ;
        }

        if (straight && flush) // Suite et couleur (Quinte Flush)
        {
            value[0] = 9 ;
            value[1] = topStraightValue ;
        }
    }


    public int[] countRanks()
    {
        int[] ranks = new int[13] ;

        // Pour comptabiliser les rangs des cartes
        for (int x = 0 ; x <= 12 ; x++)
        {
            ranks[x] = 0 ;
        }

        for (int x = 0 ; x <= 4 ; x++)
        {
            ranks[ cards[x].getRank() ]++ ;
        }

        return ranks ;
    }

    public int[] orderRanksForSingleCards(int[] ranks)
    {
        int[] orderedRanks = new int[5] ;
        int index = 0 ;

        // Range les rangs des cartes seules, fais l'As en premier vu que c'est la carte la plus forte mais en indice 0
        if (ranks[0] == 1)
        {
            orderedRanks[index] = 14 ;
            index++ ;
        }

        for (int x = 12 ; x >= 1 ; x--)
        {
            if (ranks[x] == 1)
            {
                orderedRanks[index] = x ;
                index++ ;
            }
        }

        return orderedRanks ;
    }

    public boolean isFlush()
    {
        // Pour tester la combinaison de couleur (Flush)
        for (int x = 0 ; x < 4 ; x++)
        {
            if ( cards[x].getSuit() != cards[x+1].getSuit() )
            {
                return false ;
            }
        }

        return true ;
    }

    public boolean isStraight(int[] ranks)
    {
        // Pour les suites
        for (int x = 1 ; x <= 8 ; x++)
        {
            if (ranks[x] == 1 && ranks[x + 1] == 1 && ranks[x + 2] == 1 && ranks[x + 3] == 1 && ranks[x + 4] == 1)
            {
                return true ;
            }
        }

        // Pour la suite royale
        if (ranks[9] == 1 && ranks[10] == 1 && ranks[11] == 1 && ranks[12] == 1 && ranks[0] == 1)
        {
            return true ;
        }

        return false ;
    }

    public int compareTo(Hand that)
    {
        for (int x = 0 ; x < 6 ; x++)
        {
            if (this.value[x] > that.value[x])
                return 1 ;
            else if (this.value[x] != that.value[x])
                return -1 ;
        }

        return 0 ; // Si les mains sont égales
    }

    public void displayEvaluation()
    {
        String result ;

        switch( value[0] )
        {
            case 1 :
                result = "{CARTE} haute de " + Card.rankAsString(value[1]) ;
                break ;
            case 2 :
                result = "{PAIRE} de " + Card.rankAsString(value[1]) ;
                break ;
            case 3 :
                result = "{DOUBLE PAIRE} de " + Card.rankAsString(value[1]) + " sur " + Card.rankAsString(value[2]) ;
                break ;
            case 4 :
                result = "{BRELAN} de " + Card.rankAsString(value[1]) ;
                break ;
            case 5 :
                result = "{QUINTE} à " + Card.rankAsString(value[1]) ;
                break ;
            case 6 :
                result = "{FLUSH} de " + cards[0].getSuit() ;
                break ;
            case 7 :
                result = "{FULL} de " + Card.rankAsString(value[1]) + " sur " + Card.rankAsString(value[2]) ;
                break ;
            case 8 :
                result = "{CARRE} de " + Card.rankAsString(value[1]) ;
                break ;
            case 9 :
                result = "{QUINTE FLUSH} à " + Card.rankAsString(value[1]) + " de " + cards[0].getSuit() ;
                break ;
            default:
                result = "error in Hand.display: value[0] contains invalid value" ;
        }

        System.out.println(result) ;
    }

    public void displayHand()
    {
        for (int x = 0 ; x < 5 ; x++)
            System.out.println(cards[x]) ;
    }

}
