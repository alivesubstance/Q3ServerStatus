package q3

import org.telegram.telegrambots.ApiContextInitializer

import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.exceptions.TelegramApiException
import com.oracle.util.Checksums.update
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.bots.DefaultBotOptions

fun main(args: Array<String>) {
    ApiContextInitializer.init();
    val botsApi = TelegramBotsApi()

    try {
        botsApi.registerBot(Q3ServerStatusBot)
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }

}