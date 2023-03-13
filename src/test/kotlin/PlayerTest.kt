import model.OutcomeType
import model.Player
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayerTest {

    @Test
    fun `should increase points of player by one on strike`() {
        val player = Player(1)

        player.update(OutcomeType.STRIKE)

        assertEquals(1, player.getPoints())
    }

    @Test
    fun `should decrease points of player by one on strikerStrike`() {
        val player = Player(1)

        player.update(OutcomeType.STRIKERSTRIKE)

        assertEquals(-1, player.getPoints())
    }

    @Test
    fun `should increase points of player by two on multiStrike`() {
        val player = Player(1)

        player.update(OutcomeType.MULTISTRIKE)

        assertEquals(2, player.getPoints())
    }


    @Test
    fun `should increase fouls of player by one`() {
        val player = Player(1)

        player.update(OutcomeType.STRIKERSTRIKE)

        assertEquals(1, player.getFouls())
    }
}