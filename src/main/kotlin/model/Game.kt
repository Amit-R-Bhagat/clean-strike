package service

import model.Board
import model.CoinType
import model.OutcomeType
import model.Player

class Game(private val board: Board, private val players: List<Player>) {

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

        foulLimitCheck(currPlayer)
        gameConclusionCheck()

        currPlayerIndex = (currPlayerIndex + 1)%players.size
    }

    private fun gameConclusionCheck() {
        var maxPointPlayer = players.maxWith(Comparator.comparingInt{it.getPoints()})
        var maxPoint = maxPointPlayer.getPoints()
        var minPoint = players.minWith(Comparator.comparingInt{it.getPoints()}).getPoints()

        if(maxPoint >= 5 && maxPoint-minPoint >= 3){
            println("Game won by player ${maxPointPlayer.getId()}")
        }else if(board.getNumberOfBlackCoins() == 0 && board.getNumberOfRedCoins() == 0){
            println("Game is draw")
        }
    }

    private fun foulLimitCheck(currPlayer: Player) {
        if (currPlayer.getFouls() == 3) {
            currPlayer.decreasePointByOne()
            currPlayer.setFoul(0)
        }
    }
}