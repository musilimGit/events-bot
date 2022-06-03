package uz.rustik.eventsbot

import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.bots.AbsSender

private fun AbsSender.sendMessage(send: SendMessage) {
    this.executeAsync(send)
}

fun AbsSender.sendMessage(chatId: Long, text: String) {
    sendMessage(SendMessage(chatId.toString(), text))
}

fun AbsSender.sendDocument(sendDocument: SendDocument) {
    executeAsync(sendDocument)
}