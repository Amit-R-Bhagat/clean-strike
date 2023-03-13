import exception.NotEnoughCoinsException
import model.Board
import model.OutcomeType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BoardTest {

    @Test
    fun `should create a carrom board with coins and striker`() {
        val numberOfBlackCoins = 9
        val numberOfRedCoins = 1
        val numberOfStrikers = 1

        val board = Board(numberOfBlackCoins, numberOfRedCoins, numberOfStrikers)

        assertEquals(9, board.getNumberOfBlackCoins())
        assertEquals(1, board.getNumberOfRedCoins())
    }

    @Test
    fun `should decrease black coin count by one on strike`() {
        val board = Board(9, 1, 1)

        board.update(OutcomeType.STRIKE)

        assertEquals(8, board.getNumberOfBlackCoins())
    }

    @Test
    fun `should decrease black coin count by two on multiStrike`() {
        val board = Board(9, 1, 1)

        board.update(OutcomeType.MULTISTRIKE)

        assertEquals(7, board.getNumberOfBlackCoins())
    }

    @Test
    fun `should decrease red coin count by one on redStrike`() {
        val board = Board(9, 1, 1)

        board.update(OutcomeType.REDSTRIKE)

        assertEquals(0, board.getNumberOfRedCoins())
    }

    @Test
    fun `should remove a coin from play on defunct coin`() {
        val board = Board(9, 1, 1)

        board.update(OutcomeType.DEFUNCTCOIN)

        assertEquals(8, board.getNumberOfBlackCoins())
    }

    @Test
    fun `should throw error if outcome cannot be played`() {
        val board = Board(9, 1, 1)
        board.update(OutcomeType.REDSTRIKE)

        assertFailsWith<NotEnoughCoinsException> { board.update(OutcomeType.REDSTRIKE) }
    }

}