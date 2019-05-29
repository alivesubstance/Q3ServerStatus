package q3

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import q3.commands.Command
import q3.commands.Q3ServerStatusCommand

object MessageDispatcher {

    private const val CMD_PREFIX = "/"
    private val commands = mapOf<String, Command>(
        Pair("status", Q3ServerStatusCommand)
    )

    fun dispatch(update: Update): SendMessage? {
        val text = update.message.text
        if (text == null || text.isEmpty()) {
            return null
        }

        val chatId = update.message.chatId
        if (!text.startsWith(CMD_PREFIX)) {
            return SendMessage(chatId, "Not a command '$text'")
        }

        val (cmd, args) = parseCommandText(text)
        if (cmd !in commands) {
            return SendMessage(chatId, "Unknown command '$text'")
        }

        val response = commands[cmd]?.execute(args)
        return SendMessage(update.message.chatId, response)
    }

    private fun parseCommandText(text: String): List<String> {
        val firstSpaceCharIdx = text.indexOf(" ")
        return listOf(
            text.substring(1, firstSpaceCharIdx),
            text.substring(firstSpaceCharIdx + 1, text.length)
        )
    }

}