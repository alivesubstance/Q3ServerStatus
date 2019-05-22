package q3

import com.pengrad.telegrambot.TelegramBot


fun main(args: Array<String>) {
    val telegramBot = TelegramBot.Builder("735603182:AAF_WMAzBOa1vLxELNDt4EilNE_sHF3OG_4")
        .okHttpClient()

}


//import org.telegram.telegrambots.api.objects.Update
//import org.telegram.telegrambots.bots.TelegramLongPollingBot
//
//object Q3ServerStatusBot: TelegramLongPollingBot() {
//
//    override fun getBotToken() = "735603182:AAF_WMAzBOa1vLxELNDt4EilNE_sHF3OG_4"
//
//    override fun getBotUsername() = "q3serverstatus_bot"
//
//    override fun onUpdateReceived(update: Update?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//
//}
