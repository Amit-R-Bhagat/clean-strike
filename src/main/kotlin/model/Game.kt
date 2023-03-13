package model

import MIN_POINT_FOR_WIN
import MIN_POINT_LEAD_REQUIRED_FOR_WIN

class Game(private val board: Board, private val players: List<Player>) {
    private var status = GameStatusType.INPROGRESS

    private var currPlayerIndex = 0
    fun play(outcome: OutcomeType) {
        if (status != GameStatusType.INPROGRESS) return

        board.update(outcome)
        players[currPlayerIndex].update(outcome)

        changeStatusIfConcluded()

        currPlayerIndex = (currPlayerIndex + 1) % players.size
    }

    private fun changeStatusIfConcluded() {
        val maxPoint = players.maxWith(Comparator.comparingInt { it.getPoints() }).getPoints()
        val minPoint = players.minWith(Comparator.comparingInt { it.getPoints() }).getPoints()

        if (maxPoint >= MIN_POINT_FOR_WIN && maxPoint - minPoint >= MIN_POINT_LEAD_REQUIRED_FOR_WIN) {
            status = GameStatusType.WON
        } else if (board.getNumberOfBlackCoins() == 0 && board.getNumberOfRedCoins() == 0) {
            status = GameStatusType.DRAW
        }
    }


    fun getStatus(): GameStatusType {
        return status
    }
}