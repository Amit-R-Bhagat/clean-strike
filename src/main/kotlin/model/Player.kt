package model

class Player(private val id: Int) {
    private var points: Int = 0
    private var fouls: Int = 0

    fun increasePointByOne(): Int {
        return ++points
    }

    fun decreasePointByOne(): Int {
        return --points
    }

    fun increasePoint(amount: Int): Int {
        points += amount
        return points
    }

    fun decreasePoint(amount: Int): Int {
        points -= amount
        return points
    }

    fun getPoints(): Int {
        return points
    }

    fun getFouls(): Int {
        return fouls
    }

    fun increaseFoul(): Int {
        return ++fouls
    }

    fun resetNumberOfFoulToZero(): Int {
        fouls = 0
        return fouls
    }
}