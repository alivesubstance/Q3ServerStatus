package q3

import com.google.common.annotations.VisibleForTesting
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class Q3ServerClient {

    companion object {
        val log = LoggerFactory.getLogger(Q3ServerClient::class.java)!!
        const val MAPNAME_OPTION: String = "mapname"
    }

    fun getServerStatus(host: String, port: Int): Q3ServerStatus {
        val q3ServerResponse = runCommand(host, port)

        log.info("Response for {}:{}:\n{}", host, port, q3ServerResponse)

        return parseResponse(q3ServerResponse)
    }

    @VisibleForTesting
    fun parseResponse(q3ServerResponse: String): Q3ServerStatus {
        val lines = q3ServerResponse.split("\n")
        // first string is a server response marker, ignore it
        // second is a server status reach string
        val serverStatus = lines[1]
        val startIndex = serverStatus.indexOf(MAPNAME_OPTION) + MAPNAME_OPTION.length + 1
        val map = serverStatus.substring(startIndex, serverStatus.indexOf("\\", startIndex))

        return Q3ServerStatus(map, parsePlayers(lines))
    }

    private fun parsePlayers(lines: List<String>): List<Player> {
        if (lines.size <= 2) {
            return listOf()
        }

        // players list starts from the third string
        return lines.subList(2, lines.size).map { parsePlayer(it) }
    }

    private fun parsePlayer(player: String): Player {
        // player string consist of score, ping and name separated by space
        val stats = player.split(" ")

        // some name can be separated by space too. join all parts except score and ping
        val name = stats.subList(2, stats.size)
            .joinToString(separator = " ")
            .replace(Regex("\\^\\d"), "")
            .replace("\"", "")

        return Player(stats[0].toInt(), stats[1].toInt(), name)
    }

    private fun runCommand(host: String, port: Int): String {
        try {
            val cmd = arrayOf(
                "/bin/bash",
                "-c",
                "echo -ne '\\xff\\xff\\xff\\xffgetstatus' | nc -4 -u -w 1 $host $port"
            )
            val proc = Runtime.getRuntime().exec(cmd)
            val text = proc.inputStream.bufferedReader().readText()
            if (!proc.waitFor(1, TimeUnit.SECONDS) || proc.exitValue() != 0) {
                throw RuntimeException("Error while executing $this \n ${proc.errorStream.bufferedReader().readText()}")
            }

            return text
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

}

data class Q3ServerStatus(val map: String, val players: List<Player>) {
    override fun toString(): String {
        return "Q3ServerStatus(map='$map', players=$players)"
    }
}

data class Player(val score: Int, val ping: Int, val name: String)

fun main(args: Array<String>) {
    val q3ServerClient = Q3ServerClient()
    val serverStatus = q3ServerClient.getServerStatus("q3.playground.ru", 27961)
    print(serverStatus)
}