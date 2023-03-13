package model

import exception.NotEnoughCoinsException

class Board(private var numberOfBlackCoins: Int, private var numberOfRedCoins: Int, private val numberOfStrikers: Int) {

    fun getNumberOfBlackCoins(): Int {
        return numberOfBlackCoins
    }

    fun getNumberOfRedCoins(): Int {
        return numberOfRedCoins
    }

    private fun strike() {
        if (numberOfBlackCoins == 0) throw NotEnoughCoinsException()
        numberOfBlackCoins -= 1
    }

    private fun multiStrike() {
        if (numberOfBlackCoins == 0) throw NotEnoughCoinsException()
        numberOfBlackCoins -= 2
    }

    private fun redStrike() {
        if (numberOfRedCoins == 0) throw NotEnoughCoinsException()
        numberOfRedCoins -= 1
    }

    private fun defunctCoin() {
        if (numberOfBlackCoins == 0) throw NotEnoughCoinsException()
        numberOfBlackCoins -= 1
    }

    fun update(outcome: OutcomeType) {
        when (outcome) {
            OutcomeType.STRIKE -> strike()
            OutcomeType.MULTISTRIKE -> multiStrike()
            OutcomeType.REDSTRIKE -> redStrike()
            OutcomeType.DEFUNCTCOIN -> defunctCoin()
            else -> {}
        }
    }
}