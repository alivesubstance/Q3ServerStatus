package q3.commands

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import q3.Q3ServerClient
import q3.VelocityUtil

object Q3ServerStatusCommand : Command {

    override fun execute(update: Update, args: String): BotApiMethod<Message> {
        val serverStatus = Q3ServerClient().getServerStatus(args)
        val response = VelocityUtil.renderServerStatus(serverStatus)
        val sendMessage = SendMessage(update.message.chatId, response)
        sendMessage.enableMarkdown(true)
        return sendMessage
    }

}