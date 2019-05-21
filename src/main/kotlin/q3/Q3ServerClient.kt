package q3

import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class Q3ServerClient {

    companion object {
        val logger = LoggerFactory.getLogger(Q3ServerClient::class.java)
        val MAPNAME_OPTION: String = "mapname"
    }

    fun getServerStatus(host: String, port: Int): Q3ServerStatus {
        val q3ServerResponse = runCommand(host, port)
        println("q3ServerResponse\n $q3ServerResponse")
        return parseResponse(q3ServerResponse)
    }

    fun parseResponse(q3ServerResponse: String): Q3ServerStatus {
        val lines = q3ServerResponse.split("\n")
        val serverStatus = lines[1]
        val startIndex = serverStatus.indexOf(MAPNAME_OPTION) + MAPNAME_OPTION.length + 1
        val map = serverStatus.substring(startIndex, serverStatus.indexOf("\\", startIndex))

        return Q3ServerStatus(map, parsePlayers(lines))
    }

    private fun parsePlayers(lines: List<String>): List<String> {
        if (lines.size <= 2) {
            return listOf()
        }

        return lines.subList(3, lines.size).map { parsePlayer(it) }
    }

    private fun parsePlayer(player: String): String {
        val stats = player.split(" ")
        val name = stats[2]

        return name.replace("\\^\\d.", "")
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

data class Q3ServerStatus(val map: String, val players: List<String>) {
    override fun toString(): String {
        return "Q3ServerStatus(map='$map', players=$players)"
    }
}

fun main(args: Array<String>) {
    val q3ServerClient = Q3ServerClient()
    val serverStatus = q3ServerClient.getServerStatus("q3.playground.ru", 27961)
    print(serverStatus)
}