package service

import model.Board
import model.OutcomeType
import model.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardServiceTest {

    @Test
    fun `should play with turn of player1`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(OutcomeType.STRIKE)

        assertEquals(1, player1.getPoints())
    }

    @Test
    fun `should play with turn of player2`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)
        boardService.play(OutcomeType.STRIKE)

        boardService.play(OutcomeType.STRIKE)

        assertEquals(1, player2.getPoints())
    }

    @Test
    fun `player points should increase by two on multiStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(OutcomeType.MULTISTRIKE)

        assertEquals(2, player1.getPoints())
    }

    @Test
    fun `player points should increase by three on redStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(OutcomeType.REDSTRIKE)

        assertEquals(3, player1.getPoints())
    }

    @Test
    fun `player points should decrease by one on strikerStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(OutcomeType.STRIKERSTRIKE)

        assertEquals(-1, player1.getPoints())
    }

    @Test
    fun `player points should decrease by two on defunctCoin`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(OutcomeType.DEFUNCTCOIN)

        assertEquals(-2, player1.getPoints())
    }

    @Test
    fun `player loses a point after three fouls`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val players = listOf(player1)
        val boardService = BoardService(board, players)

        boardService.play(OutcomeType.STRIKERSTRIKE)
        boardService.play(OutcomeType.STRIKERSTRIKE)
        boardService.play(OutcomeType.STRIKERSTRIKE)

        assertEquals(-4, player1.getPoints())
    }
}