package com.pokesgi.poker ;

import lombok.Getter ;
import lombok.Setter ;

/**
 * Created by Samuel Bijou on 11/04/2017.
 */
@Getter @Setter
public class Hand
{

    private Card[] cards = new Card[5] ;
    private int[] values = new int[5] ;

    private int score = 0 ;

    private int[] ranks = new int[13] ;

    private int[] orderedRanks = new int[5] ;

    private int sameCards = -1, sameCards2 = -1 ;
    private int largeGroupRank = -1, smallGroupRank = -1 ;

    private boolean flush ;

    private boolean straight ;
    private int topStraightValue = -1 ;


    public Hand(Card[] newCards)
    {
        for (int x = 0 ; x < newCards.length ; x++)
        {
            cards[x] = newCards[x] ;
        }
    }

    public Hand(Deck deck)
    {
        for (int x = 0 ; x < 5 ; x++)
        {
            cards[x] = deck.drawFromDeck() ;
        }
    }


    public void evaluateHand()
    {
        countRanks() ;

        orderRanksForSingleCards() ;


        flush = isFlush() ;

        straight = isStraight() ;

        if(straight)
        {
            topStraightValue = orderedRanks[0] ;
        }

        searchCombinaisons() ;

        evaluateScore() ;
    }

    public void countRanks()
    {
        // Pour comptabiliser les rangs des cartes
        for (int x = 0 ; x <= 12 ; x++)
        {
            ranks[x] = 0 ;
        }

        for (int x = 0 ; x <= 4 ; x++)
        {
            ranks[ cards[x].getRank() ]++ ;
        }
    }

    public void orderRanksForSingleCards()
    {
        int index = 0 ;

        // Range les rangs des cartes seules, fait l'As en premier vu que c'est la carte la plus forte mais en indice 0
        if (ranks[0] == 1)
        {
            orderedRanks[index] = 13 ;
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

    public boolean isStraight()
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

    public void searchCombinaisons()
    {
        // Permet de gérer la hauteur des Paires ou du Brelan de la main
        for (int x = 12 ; x >= 0 ; x--)
        {
            if (ranks[x] > sameCards) // Si la combinaison (Paire ou Brelan) la plus haute est supérieure à l'ancienne plus haute
            {
                if (sameCards != -1) // Si l'ancienne combinaison (Paire ou Brelan) la plus haute n'a pas été initialisée (celle par défaut)
                {
                    sameCards2 = sameCards ;
                    smallGroupRank = largeGroupRank ;
                }

                sameCards = ranks[x] ; // Sauvegarde la nouvelle combinaison (Paire ou Brelan) la plus haute
                largeGroupRank = x ;
            }

            else if (ranks[x] > sameCards2) // Sinon si, la combinaire (Paire ou Brelan) la plus haute est supérieure à l'ancienne moins haute
            {
                sameCards2 = ranks[x] ;
                smallGroupRank = x ;
            }
        }
    }

    public void evaluateScore()
    {
        // Initialise l'évaluation de la main
        for (int x = 0 ; x < 5 ; x++)
        {
            values[x] = 0 ;
        }


        // Commence l'évaluation de la main
        if (straight && flush) // Suite et couleur (Quinte Flush)
        {
            score = 9 ;

            values[0] = orderedRanks[0] ;
            values[1] = orderedRanks[1] ;
            values[2] = orderedRanks[2] ;
            values[3] = orderedRanks[3] ;
            values[4] = orderedRanks[4] ;
        }

        else if (sameCards == 4) // Carré
        {
            score = 8 ;

            values[0] = largeGroupRank ;
            values[1] = largeGroupRank ;
            values[2] = largeGroupRank ;
            values[3] = largeGroupRank ;
            values[4] = smallGroupRank ;
        }

        else if (sameCards == 3 && sameCards2 == 2) // Full
        {
            score = 7 ;

            values[0] = largeGroupRank ;
            values[1] = largeGroupRank ;
            values[2] = largeGroupRank ;
            values[3] = smallGroupRank ;
            values[4] = smallGroupRank ;
        }

        else if (flush && !straight) // Couleur (Flush)
        {
            score = 6 ;

            values[0] = orderedRanks[0] ;
            values[1] = orderedRanks[1] ;
            values[2] = orderedRanks[2] ;
            values[3] = orderedRanks[3] ;
            values[4] = orderedRanks[4] ;
        }

        else if (straight && !flush) // Suite (Quinte)
        {
            score = 5 ;

            values[0] = orderedRanks[0] ;
            values[1] = orderedRanks[1] ;
            values[2] = orderedRanks[2] ;
            values[3] = orderedRanks[3] ;
            values[4] = orderedRanks[4] ;
        }

        else if (sameCards == 3 && sameCards2 != 2) // Brelan
        {
            score = 4 ;

            values[0] = largeGroupRank ;
            values[1] = largeGroupRank ;
            values[2] = largeGroupRank ;
            values[3] = orderedRanks[0] ;
            values[4] = orderedRanks[1] ;
        }

        else if (sameCards == 2 && sameCards2 == 2) // Double Paire
        {
            score = 3 ;

            values[0] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank ;
            values[1] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank ;
            values[2] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank ;
            values[3] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank ;
            values[4] = orderedRanks[0] ;
        }

        else if (sameCards == 2 && sameCards2 == 1) // Paire
        {
            score = 2 ;

            values[0] = largeGroupRank ;
            values[1] = largeGroupRank ;
            values[2] = orderedRanks[0] ;
            values[3] = orderedRanks[1] ;
            values[4] = orderedRanks[2] ;
        }

        if (sameCards == 1) // carte haute
        {
            score = 1 ;

            values[0] = orderedRanks[0] ;
            values[1] = orderedRanks[1] ;
            values[2] = orderedRanks[2] ;
            values[3] = orderedRanks[3] ;
            values[4] = orderedRanks[4] ;
        }
    }

    public int compareTo(Hand that)
    {
        int result = 0 ;

        if(this.getScore() > that.getScore())
        {
            result = 1 ;
        }

        else if(this.getScore() > that.getScore())
        {
            result = -1 ;
        }

        else
        {
            for (int x = 0 ; x < 5 ; x++)
            {
                if (this.values[x] > that.values[x])
                    result = 1 ;
                else if (this.values[x] != that.values[x])
                    result = -1 ;
            }
        }

        return result ;
    }

    public void displayEvaluation()
    {
        String result ;

        switch( score )
        {
            case 1 :
                result = "{CARTE} haute de " + Card.rankAsString(values[0]) ;
                break ;
            case 2 :
                result = "{PAIRE} de " + Card.rankAsString(values[0]) ;
                break ;
            case 3 :
                result = "{DOUBLE PAIRE} de " + Card.rankAsString(largeGroupRank) + " sur " + Card.rankAsString(smallGroupRank) ;
                break ;
            case 4 :
                result = "{BRELAN} de " + Card.rankAsString(values[0]) ;
                break ;
            case 5 :
                result = "{QUINTE} à " + Card.rankAsString(values[0]) ;
                break ;
            case 6 :
                result = "{FLUSH} de " + cards[0].getSuit() ;
                break ;
            case 7 :
                result = "{FULL} de " + Card.rankAsString(largeGroupRank) + " sur " + Card.rankAsString(smallGroupRank) ;
                break ;
            case 8 :
                result = "{CARRE} de " + Card.rankAsString(values[0]) ;
                break ;
            case 9 :
                result = "{QUINTE FLUSH} à " + Card.rankAsString(values[0]) + " de " + cards[0].getSuit() ;
                break ;
            default :
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
