package q3

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


fun main(args: Array<String>) {
    ApiContextInitializer.init();
    val botsApi = TelegramBotsApi()

    try {
        botsApi.registerBot(Q3ServerStatusBot)
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }

}