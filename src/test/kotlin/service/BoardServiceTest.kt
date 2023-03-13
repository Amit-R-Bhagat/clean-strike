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
}