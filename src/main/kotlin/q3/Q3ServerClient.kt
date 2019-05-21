package q3

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class Q3ServerClient {

    fun getPlayers(host: String, port: Int): List<String> {
        val q3ServerStatus = runBashScript("q3_server_getstatus.sh", host, port)
        println("q3ServerStatus = $q3ServerStatus")

        return arrayListOf();
    }

    private fun runBashScript(script: String, host: String, port: Int): String {
        try {
            val proc = ProcessBuilder("bash", script, host, port.toString())
                .directory(File("/home/mirian/code/3rdparty/q3serverstatus/src/main/resources"))
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()

            val procText = proc.inputStream.bufferedReader().readText()
            if (!proc.waitFor(1, TimeUnit.SECONDS) || proc.exitValue() != 0) {
                throw RuntimeException("Error while executing $this \n ${proc.errorStream.bufferedReader().readText()}")
            }

            return procText
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }

}

fun main(args: Array<String>) {
    val q3ServerClient = Q3ServerClient()
    q3ServerClient.getPlayers("q3.playground.ru", 27960)
}