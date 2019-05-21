package q3

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.io.File

internal class Q3ServerClientTest {

    @Test
    fun parseResponse() {
        val serverResponse = File(javaClass.getResource("classpath:/q3server_response.txt").toURI()).readText()
        val q3ServerStatus = Q3ServerClient().parseResponse(serverResponse)
        assertEquals("pro-q3tourney7", q3ServerStatus.map)

        assertEquals(2, q3ServerStatus.players.size)
        assertEquals("jericho", q3ServerStatus.players[0])
        assertEquals("6JlATHOu DED", q3ServerStatus.players[1])
    }


}