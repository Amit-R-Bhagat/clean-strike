package service

import model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `should play with turn of player1`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val game = Game(board, players)

        game.play(OutcomeType.STRIKE)

        assertEquals(1, player1.getPoints())
    }

    @Test
    fun `should play with turn of player2`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val game = Game(board, players)
        game.play(OutcomeType.STRIKE)

        game.play(OutcomeType.STRIKE)

        assertEquals(1, player2.getPoints())
    }

    @Test
    fun `player points should increase by two on multiStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val game = Game(board, players)

        game.play(OutcomeType.MULTISTRIKE)

        assertEquals(2, player1.getPoints())
    }

    @Test
    fun `player points should increase by three on redStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val game = Game(board, players)

        game.play(OutcomeType.REDSTRIKE)

        assertEquals(3, player1.getPoints())
    }

    @Test
    fun `player points should decrease by one on strikerStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val game = Game(board, players)

        game.play(OutcomeType.STRIKERSTRIKE)

        assertEquals(-1, player1.getPoints())
    }

    @Test
    fun `player points should decrease by two on defunctCoin`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val game = Game(board, players)

        game.play(OutcomeType.DEFUNCTCOIN)

        assertEquals(-2, player1.getPoints())
    }

    @Test
    fun `player loses a point after three fouls`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val players = listOf(player1)
        val game = Game(board, players)

        game.play(OutcomeType.STRIKERSTRIKE)
        game.play(OutcomeType.STRIKERSTRIKE)
        game.play(OutcomeType.STRIKE)
        game.play(OutcomeType.STRIKERSTRIKE)

        assertEquals(-4, player1.getPoints())
    }

    @Test
    fun `game is won if a player has atleast five points and lead by atleast three points to any other player`() {
        val board = Board(9,2,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val game = Game(board, players)

        game.play(OutcomeType.REDSTRIKE)
        game.play(OutcomeType.STRIKE)
        game.play(OutcomeType.REDSTRIKE)

        assertEquals(GameStatusType.WON, game.getStatus())
    }

    @Test
    fun `game is draw if no player has atleast five points and lead by atleast three points to any other player and no coins`() {
        val board = Board(1,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val game = Game(board, players)

        game.play(OutcomeType.REDSTRIKE)
        game.play(OutcomeType.STRIKE)

        assertEquals(GameStatusType.DRAW, game.getStatus())
    }
}