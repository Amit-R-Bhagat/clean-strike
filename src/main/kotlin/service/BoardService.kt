package service

import model.Board
import model.CoinType
import model.Player

class BoardService(private val board: Board, private val players: List<Player>) {

    private var currPlayerIndex = 0
    fun play(outcome: Int) {
        val currPlayer = players[currPlayerIndex]
        when(outcome) {
            1 -> {
                board.strike()
                currPlayer.increasePointByOne()
            }
            2 -> {
                board.multiStrike()
                currPlayer.increasePoint(2)
            }
            3 -> {
                board.redStrike()
                currPlayer.increasePoint(3)
            }
            4 -> {
                currPlayer.decreasePointByOne()
                currPlayer.increaseFoul()
            }
            5 -> {
                board.defunctCoin(CoinType.BLACK)
                currPlayer.decreasePoint(2)
                currPlayer.increaseFoul()
            }
        }

        if(currPlayer.getFouls() == 3){
            currPlayer.decreasePointByOne()
            currPlayer.setFoul(0)
        }

        currPlayerIndex = (currPlayerIndex + 1)%players.size
    }
}