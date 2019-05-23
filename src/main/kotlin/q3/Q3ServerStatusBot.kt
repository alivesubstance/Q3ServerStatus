package q3

import com.google.common.base.Throwables
import org.telegram.telegrambots.ApiContextInitializer

import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.exceptions.TelegramApiException
import com.oracle.util.Checksums.update
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.bots.DefaultBotOptions
import java.lang.RuntimeException


object Q3ServerStatusBot: TelegramLongPollingBot(createBotOptions()) {

    val log = LoggerFactory.getLogger(Q3ServerStatusBot::class.java)!!

    override fun getBotToken() = "735603182:AAF_WMAzBOa1vLxELNDt4EilNE_sHF3OG_4"

    override fun getBotUsername() = "q3serverstatus_bot"

    override fun onUpdateReceived(update: Update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.message.hasText()) {
            val message = SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.message.chatId)
                .setText(update.message.text)

            try {
                execute(message) // Call method to send the message
            } catch (e: TelegramApiException) {
                log.error("Failed to send message", e)
                Throwables.propagate(e)
            }
        }
    }

}

fun createBotOptions(): DefaultBotOptions {
    val options = DefaultBotOptions()
    options.baseUrl = "http://idi-na-xyz.ru"
    return options
}