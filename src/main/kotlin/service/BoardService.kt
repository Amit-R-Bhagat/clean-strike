package service

import model.Board
import model.Player

class BoardService(private val board: Board, private val players: List<Player>) {

    private var currPlayerIndex = 0
    fun play(outcome: Int) {
        when(outcome) {
            1 -> {
                board.strike()
                players[currPlayerIndex].increasePointByOne()
            }
            2 -> {
                board.multiStrike()
                players[currPlayerIndex].increasePoint(2)
            }
            3 -> {
                board.redStrike()
                players[currPlayerIndex].increasePoint(3)
            }
        }

        currPlayerIndex = (currPlayerIndex + 1)%players.size
    }
}