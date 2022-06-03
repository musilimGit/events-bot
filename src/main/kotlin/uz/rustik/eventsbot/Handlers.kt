package uz.rustik.eventsbot

import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.objects.InputFile
import java.io.ByteArrayInputStream
import java.io.File

@Component
class CustomEventHandler(botProperties: BotProperties) : ApplicationListener<CustomEvent> {
    private val bots: List<BotProperties.Bot>

    init {
        bots = botProperties.bots
    }

    override fun onApplicationEvent(event: CustomEvent) {
        when (val any = event.any) {
            is Throwable -> {
                bots.forEach { bot ->
                    val sender = object : DefaultAbsSender(DefaultBotOptions()) {
                        override fun getBotToken() = bot.token
                    }
                    bot.chats.forEach { chat ->
                        sender.sendDocument(
                            SendDocument(
                                chat.toString(),
                                InputFile(ByteArrayInputStream(any.stackTraceToString().toByteArray()), "test.txt")
                            )
                        )
                    }
                }
            }
            is String -> {
                val take = any.take(4096)
                bots.forEach { bot ->
                    val sender = object : DefaultAbsSender(DefaultBotOptions()) {
                        override fun getBotToken() = bot.token
                    }
                    bot.chats.forEach { chat ->
                        sender.sendMessage(chat, take)
                    }
                }
            }
            is File -> {
                bots.forEach { bot ->
                    val sender = object : DefaultAbsSender(DefaultBotOptions()) {
                        override fun getBotToken() = bot.token
                    }
                    bot.chats.forEach { chat ->
                        sender.sendDocument(SendDocument(chat.toString(), InputFile(any)))
                    }
                }
            }
            else -> {
                throw TypeCastException()
            }
        }
    }
}