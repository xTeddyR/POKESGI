package com.pokesgi.poker ;

import com.pokesgi.user.UserDTO ;
import org.junit.Assert ;
import org.junit.Test ;

/**
 * Created by Samuel Bijou on 20/04/2017.
 */
public class GameTests
{

    @Test
    public void should_Start_Game()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        game.addPlayer(user1) ;
        game.addPlayer(user2) ;

        game.getUsersReady()[0] = true ;
        game.getUsersReady()[1] = true ;

        game.startGame() ;

        Assert.assertNotNull(game.getUsers()[0]) ;
        Assert.assertNotNull(game.getUsers()[1]) ;

        Assert.assertTrue(game.getUsersScore()[0] > 0) ;
    }

    @Test
    public void should_Not_Start_Game()
    {
        Game game = new Game() ;

        game.startGame() ;

        Assert.assertNull(game.getUsers()[0]) ;

        Assert.assertTrue(game.getUsersScore()[0] == 0) ;
    }

    @Test
    public void should_Not_Have_Started_Game()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        game.addPlayer(user1) ;
        game.addPlayer(user2) ;

        game.startGame() ;

        Assert.assertNotNull(game.getUsers()[0]) ;
        Assert.assertNotNull(game.getUsers()[1]) ;

        Assert.assertTrue(game.getUsersScore()[0] == 0) ;
    }

    @Test
    public void should_Add_Player()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;

        game.addPlayer(user1) ;

        Assert.assertNotNull(game.getUsers()[0]) ;

        Assert.assertEquals(user1, game.getUsers()[0]) ;
    }

    @Test
    public void should_Not_Add_Player()
    {
        Game game = new Game() ;

        UserDTO[] users = new UserDTO[10] ;

        users[0] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[1] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[2] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[3] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[4] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[5] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[6] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[7] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[8] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        users[9] = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;

        game.setUsers(users) ;

        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        int result = game.addPlayer(user2) ;

        Assert.assertEquals(-1, result) ;

        Assert.assertNotNull(game.getUsers()[0]) ;

        for(int i = 0 ; i < 10 ; i++)
        {
            Assert.assertNotEquals(game.getUsers()[i], user2) ;
        }
    }

    @Test
    public void should_Remove_Player()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        game.addPlayer(user1) ;
        game.addPlayer(user2) ;

        UserDTO result = game.removePlayer(1) ;

        Assert.assertNull(game.getUsers()[1]) ;
        Assert.assertFalse(game.getUsersReady()[1]) ;
        Assert.assertNull(game.getUsersHand()[1]) ;
        Assert.assertTrue(game.getUsersScore()[1] == 0) ;

        Assert.assertEquals(user2, result) ;

        Assert.assertNotEquals(user2, game.getUsers()[1]) ;
    }

    @Test
    public void should_Not_Remove_Player()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        game.addPlayer(user1) ;
        game.addPlayer(user2) ;

        UserDTO result = game.removePlayer(10) ;

        Assert.assertNull(result) ;

        Assert.assertNotNull(game.getUsers()[0]) ;
        Assert.assertNotNull(game.getUsers()[1]) ;
    }

    @Test
    public void should_Get_Ready()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        game.addPlayer(user1) ;
        game.addPlayer(user2) ;

        game.getUsersReady()[0] = true ;
        game.getUsersReady()[1] = true ;

        Assert.assertNotNull(game.getUsers()[0]) ;
        Assert.assertNotNull(game.getUsers()[1]) ;

        Assert.assertTrue(game.checkPlayersReady()) ;
    }

    @Test
    public void should_Not_Get_Ready()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        game.addPlayer(user1) ;
        game.addPlayer(user2) ;

        game.getUsersReady()[0] = true ;

        Assert.assertNotNull(game.getUsers()[0]) ;
        Assert.assertNotNull(game.getUsers()[1]) ;

        Assert.assertFalse(game.checkPlayersReady()) ;
    }

    @Test
    public void should_Give_Cards_And_Evaluate()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        game.addPlayer(user1) ;
        game.addPlayer(user2) ;

        Deck deck = new Deck() ;

        game.giveCardsAndEvaluate(deck) ;

        Assert.assertNotNull(game.getUsers()[0]) ;
        Assert.assertNotNull(game.getUsers()[1]) ;

        Assert.assertNotNull(game.getUsersHand()[0]) ;
        Assert.assertNotNull(game.getUsersHand()[1]) ;

        Assert.assertTrue(game.getUsersScore()[0] > 0) ;
        Assert.assertTrue(game.getUsersScore()[1] > 0) ;
    }

    @Test
    public void should_Search_Winner()
    {
        Game game = new Game() ;

        UserDTO user1 = new UserDTO(1, "toto", "mdp", "flambeur", "Jean", "Michel", "jean.michel@email.com", "", "") ;
        UserDTO user2 = new UserDTO(2, "tata", "mdp", "joueuse", "Michelle", "Jean", "michelle.jean@email.com", "", "") ;

        game.addPlayer(user1) ;
        game.addPlayer(user2) ;

        Deck deck = new Deck() ;

        game.giveCardsAndEvaluate(deck) ;

        Assert.assertNotNull(game.getUsers()[0]) ;
        Assert.assertNotNull(game.getUsers()[1]) ;

        String winners = game.searchWinner() ;

        Assert.assertNotEquals("", winners) ;
    }

}
