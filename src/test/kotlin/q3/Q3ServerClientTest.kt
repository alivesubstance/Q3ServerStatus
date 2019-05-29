package q3

import org.junit.Assert.assertEquals
import org.junit.Test

internal class Q3ServerClientTest {

    @Test
    fun parseResponse_Full() {
        val serverResponse = readFileFromResources("q3server_response_full.txt")
        val q3ServerStatus = Q3ServerClient().parseResponse(serverResponse)
        assertEquals("pro-q3tourney7", q3ServerStatus.map)

        assertEquals(3, q3ServerStatus.players.size)

        assertEquals(0, q3ServerStatus.players[0].score)
        assertEquals(26, q3ServerStatus.players[0].ping)
        assertEquals("jericho", q3ServerStatus.players[0].name)

        assertEquals(10, q3ServerStatus.players[1].score)
        assertEquals(16, q3ServerStatus.players[1].ping)
        assertEquals("6JlATHOu DED", q3ServerStatus.players[1].name)

        assertEquals(0, q3ServerStatus.players[2].score)
        assertEquals(32, q3ServerStatus.players[2].ping)
        assertEquals("the beast skill", q3ServerStatus.players[2].name)
    }

    @Test
    fun parseResponse_EmptyPlayers() {
        val serverResponse = readFileFromResources("q3server_response_empty_players.txt")
        val q3ServerStatus = Q3ServerClient().parseResponse(serverResponse)
        assertEquals("pro-q3tourney7", q3ServerStatus.map)

        assertEquals(0, q3ServerStatus.players.size)
   }

    private fun readFileFromResources(fileName: String) =
        Q3ServerClientTest::class.java.classLoader.getResource(fileName).readText()

}