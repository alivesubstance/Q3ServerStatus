package q3

import org.slf4j.LoggerFactory
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


object Q3ServerStatusBot: TelegramLongPollingBot(createBotOptions()) {

    val log = LoggerFactory.getLogger(Q3ServerStatusBot::class.java)!!

    override fun getBotToken() = "735603182:AAF_WMAzBOa1vLxELNDt4EilNE_sHF3OG_4"

    override fun getBotUsername() = "q3serverstatus_bot"

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val message = MessageDispatcher.dispatch(update) ?: return

            try {
                sendApiMethod(message)
            } catch (e: TelegramApiException) {
                throw RuntimeException(e)
            }
        }
    }

}

fun createBotOptions(): DefaultBotOptions {
    val options = DefaultBotOptions()
    options.baseUrl = "https://telega-proxy.appspot.com/bot"
    return options
}