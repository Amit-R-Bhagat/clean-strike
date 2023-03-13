package model

class Player(val id: Int) {
    private var points: Int = 0

    fun increasePointByOne(): Int {
        return ++points
    }

    fun decreasePointByOne(): Int{
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

    fun getPoints(): Int{
        return points
    }
}