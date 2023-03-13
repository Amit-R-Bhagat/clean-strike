package model

class Player(private val id: Int) {
    private var points: Int = 0
    private var fouls: Int = 0

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
        if(fouls > 2){
            decreasePointByOne()
            fouls = 0
        }
        return fouls
    }

    fun update(outcome: OutcomeType){
        when(outcome){
            OutcomeType.STRIKE -> increasePointByOne()
            OutcomeType.MULTISTRIKE -> increasePoint(2)
            OutcomeType.REDSTRIKE -> increasePoint(3)
            OutcomeType.DEFUNCTCOIN -> {
                decreasePoint(2)
                increaseFoul()
            }
            OutcomeType.STRIKERSTRIKE -> {
                decreasePointByOne()
                increaseFoul()
            }
            OutcomeType.NONE -> increaseFoul()
        }
    }
}