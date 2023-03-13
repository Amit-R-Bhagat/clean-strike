package service

import model.Board
import model.CoinType
import model.OutcomeType
import model.Player

class BoardService(private val board: Board, private val players: List<Player>) {

    private var currPlayerIndex = 0
    fun play(outcome: OutcomeType) {
        val currPlayer = players[currPlayerIndex]
        when(outcome) {
            OutcomeType.STRIKE -> {
                board.strike()
                currPlayer.increasePointByOne()
            }
            OutcomeType.MULTISTRIKE -> {
                board.multiStrike()
                currPlayer.increasePoint(2)
            }
            OutcomeType.REDSTRIKE -> {
                board.redStrike()
                currPlayer.increasePoint(3)
            }
            OutcomeType.STRIKERSTRIKE -> {
                currPlayer.decreasePointByOne()
                currPlayer.increaseFoul()
            }
            OutcomeType.DEFUNCTCOIN -> {
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