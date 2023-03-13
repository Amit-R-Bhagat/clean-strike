package service

import model.Board
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

        boardService.play(1)

        assertEquals(1, player1.getPoints())
    }

    @Test
    fun `should play with turn of player2`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)
        boardService.play(1)

        boardService.play(1)

        assertEquals(1, player2.getPoints())
    }

    @Test
    fun `player points should increase by two on multiStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(2)

        assertEquals(2, player1.getPoints())
    }

    @Test
    fun `player points should increase by three on redStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(3)

        assertEquals(3, player1.getPoints())
    }

    @Test
    fun `player points should decrease by one on strikerStrike`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(4)

        assertEquals(-1, player1.getPoints())
    }

    @Test
    fun `player points should decrease by two on defunctCoin`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val player2 = Player(2)
        val players = listOf(player1, player2)
        val boardService = BoardService(board, players)

        boardService.play(5)

        assertEquals(-2, player1.getPoints())
    }

    @Test
    fun `player loses a point after three fouls`() {
        val board = Board(9,1,1)
        val player1 = Player(1)
        val players = listOf(player1)
        val boardService = BoardService(board, players)

        boardService.play(4)
        boardService.play(4)
        boardService.play(4)

        assertEquals(-4, player1.getPoints())
    }
}