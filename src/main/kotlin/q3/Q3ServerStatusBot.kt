package q3

import com.pengrad.telegrambot.TelegramBot
import okhttp3.OkHttpClient
import org.telegram.telegrambots.ApiContextInitializer


//fun main(args: Array<String>) {
//    val telegramBot = TelegramBot.Builder("735603182:AAF_WMAzBOa1vLxELNDt4EilNE_sHF3OG_4")
//        .okHttpClient(createOkHttpClient())
//        .build()
//
//    telegramBot.
//}
//
//private fun createOkHttpClient(): OkHttpClient {
//    //TODO configure timeouts
//    return OkHttpClient()
//}


import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.exceptions.TelegramApiException





object Q3ServerStatusBot: TelegramLongPollingBot() {

    override fun getBotToken() = "735603182:AAF_WMAzBOa1vLxELNDt4EilNE_sHF3OG_4"

    override fun getBotUsername() = "q3serverstatus_bot"

    override fun onUpdateReceived(update: Update?) {

    }

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