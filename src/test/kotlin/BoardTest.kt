import model.Board
import model.CoinType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun `should create a carrom board with coins and striker`(){
        val numberOfBlackCoins = 9
        val numberOfRedCoins = 1


        val board = Board(numberOfBlackCoins, numberOfRedCoins)

        assertEquals(9, board.getNumberOfBlackCoins())
        assertEquals(1, board.getNumberOfRedCoins())
    }

    @Test
    fun `should decrease black coin count by one on multiStrike`(){
        val board = Board(9, 1)

        board.strike()

        assertEquals(8, board.getNumberOfBlackCoins())
    }

    @Test
    fun `should decrease black coin count by two on multiStrike`(){
        val board = Board(9, 1)

        board.multiStrike()

        assertEquals(7, board.getNumberOfBlackCoins())
    }

    @Test
    fun `should decrease red coin count by one on redStrike`(){
        val board = Board(9, 1)

        board.redStrike()

        assertEquals(0, board.getNumberOfRedCoins())
    }

    @Test
    fun `should remove a coin from play on defunct coin`(){
        val board = Board(9, 1)

        board.defunctCoin(CoinType.BLACK)

        assertEquals(8, board.getNumberOfBlackCoins())
    }
}