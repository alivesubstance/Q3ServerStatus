package q3

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

object MessageProcessor {

    const val CMD_START = "/"

    fun process(update: Update): SendMessage? {
        val text = update.message.text
        val cmd = getCommand(text) ?: return null

        var response = ""
        if ("/status" == cmd) {
            val hostPort = text.split(" ")[1]
            response = Q3ServerClient().getServerStatus(hostPort).toString()
        }

        return SendMessage(update.message.chatId, response)
    }

    private fun getCommand(text: String): String? {
        if (!text.startsWith(CMD_START)) {
            return null
        }

        return text.substring(0, text.indexOf(" "));
    }

}