package q3

import org.telegram.telegrambots.ApiContextInitializer

import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.exceptions.TelegramApiException
import com.oracle.util.Checksums.update
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.bots.DefaultBotOptions


object Q3ServerStatusBot: TelegramLongPollingBot(createBotOptions()) {



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
                e.printStackTrace()
            }

        }
    }

}

fun createBotOptions(): DefaultBotOptions {
    val options = DefaultBotOptions()
    options.baseUrl = "http://idi-na-xyz.ru"
    return options
}


fun main(args: Array<String>) {
    ApiContextInitializer.init();
    val botsApi = TelegramBotsApi()

    try {
        botsApi.registerBot(Q3ServerStatusBot)
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }

}