package model

class Game(private val board: Board, private val players: List<Player>) {
    private var status = GameStatusType.INPROGRESS

    private var currPlayerIndex = 0
    fun play(outcome: OutcomeType) {
        if (status != GameStatusType.INPROGRESS) return

        val currPlayer = players[currPlayerIndex]
        when (outcome) {
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

        checkIfFoulLimitExceeded(currPlayer)
        changeStatusIfConcluded()

        currPlayerIndex = (currPlayerIndex + 1) % players.size
    }

    private fun changeStatusIfConcluded() {
        val maxPoint = players.maxWith(Comparator.comparingInt { it.getPoints() }).getPoints()
        val minPoint = players.minWith(Comparator.comparingInt { it.getPoints() }).getPoints()

        if (maxPoint >= 5 && maxPoint - minPoint >= 3) {
            status = GameStatusType.WON
        } else if (board.getNumberOfBlackCoins() == 0 && board.getNumberOfRedCoins() == 0) {
            status = GameStatusType.DRAW
        }
    }

    private fun checkIfFoulLimitExceeded(currPlayer: Player) {
        if (currPlayer.getFouls() == 3) {
            currPlayer.decreasePointByOne()
            currPlayer.resetNumberOfFoulToZero()
        }
    }

    fun getStatus(): GameStatusType {
        return status
    }
}