package model

import CONSECUTIVE_TURN_NOT_POCKETED_LIMIT
import FOUL_LIMIT

class Player(private val id: Int) {
    private var points: Int = 0
    private var fouls: Int = 0
    private var consecutiveTurnsNotPocketed: Int = 0

    private fun increasePointByOne(): Int {
        return ++points
    }

    private fun decreasePointByOne(): Int {
        return --points
    }

    private fun increasePoint(amount: Int): Int {
        points += amount
        return points
    }

    private fun decreasePoint(amount: Int): Int {
        points -= amount
        return points
    }

    fun getPoints(): Int {
        return points
    }

    fun getFouls(): Int {
        return fouls
    }

    private fun increaseFoul(): Int {
        fouls++
        if(fouls >= FOUL_LIMIT){
            decreasePointByOne()
            fouls = 0
        }
        return fouls
    }

    private fun increaseConsecutiveTurnNotPocketed(): Int {
        consecutiveTurnsNotPocketed++
        if(consecutiveTurnsNotPocketed >= CONSECUTIVE_TURN_NOT_POCKETED_LIMIT){
            decreasePointByOne()
            consecutiveTurnsNotPocketed = 0
        }
        return consecutiveTurnsNotPocketed
    }

    fun update(outcome: OutcomeType){
        when(outcome){
            OutcomeType.STRIKE -> increasePointByOne()
            OutcomeType.MULTISTRIKE -> increasePoint(2)
            OutcomeType.REDSTRIKE -> increasePoint(3)
            OutcomeType.DEFUNCTCOIN -> {
                decreasePoint(2)
                increaseFoul()
                increaseConsecutiveTurnNotPocketed()
            }
            OutcomeType.STRIKERSTRIKE -> {
                decreasePointByOne()
                increaseFoul()
                increaseConsecutiveTurnNotPocketed()
            }
            OutcomeType.NONE -> {
                increaseConsecutiveTurnNotPocketed()
            }
        }
    }
}