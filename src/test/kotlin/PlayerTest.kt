import model.Player
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayerTest {

    @Test
    fun `should increase points of player by one`(){
        val player = Player(1)

        player.increasePointByOne()

        assertEquals(1, player.getPoints())
    }

    @Test
    fun `should decrease points of player by one`(){
        val player = Player(1)

        player.decreasePointByOne()

        assertEquals(-1, player.getPoints())
    }
}