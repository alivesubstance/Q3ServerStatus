package q3

import java.io.IOException
import java.util.concurrent.TimeUnit

class Q3ServerClient {

    fun getPlayers(host: String, port: Int): List<String> {
        val q3ServerResponse = runCommand(host, port)
        println("${q3ServerResponse}")

        return arrayListOf();
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

fun main(args: Array<String>) {
    val q3ServerClient = Q3ServerClient()
    q3ServerClient.getPlayers("q3.playground.ru", 27961)
}