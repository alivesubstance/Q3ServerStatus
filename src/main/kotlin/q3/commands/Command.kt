package q3.commands

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

interface Command {

    fun execute(update: Update, args: String): BotApiMethod<Message>

}