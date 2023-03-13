package model

import exception.NotEnoughCoinsException

class Board(private var numberOfBlackCoins: Int, private var numberOfRedCoins: Int, private val numberOfStrikers: Int) {

    fun getNumberOfBlackCoins(): Int{
        return numberOfBlackCoins
    }

    fun getNumberOfRedCoins(): Int {
        return numberOfRedCoins
    }

    fun strike() {
        if(numberOfBlackCoins==0) throw NotEnoughCoinsException()
        numberOfBlackCoins -= 1
    }

    fun multiStrike() {
        if(numberOfBlackCoins==0) throw NotEnoughCoinsException()
        numberOfBlackCoins -= 2
    }

    fun redStrike() {
        if(numberOfRedCoins==0) throw NotEnoughCoinsException()
        numberOfRedCoins -= 1
    }

    fun defunctCoin(coinType: CoinType) {
        if(coinType == CoinType.BLACK){
            if(numberOfBlackCoins==0) throw NotEnoughCoinsException()
            numberOfBlackCoins -= 1
        }else {
            if(numberOfRedCoins==0) throw NotEnoughCoinsException()
            numberOfRedCoins -= 1
        }
    }
}