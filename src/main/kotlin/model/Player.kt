package model

class Player(val id: Int) {
    private var points: Int = 0

    fun increasePointByOne(): Int {
        return ++points
    }

    fun decreasePointByOne(): Int{
        return --points
    }

    fun getPoints(): Int{
        return points
    }
}