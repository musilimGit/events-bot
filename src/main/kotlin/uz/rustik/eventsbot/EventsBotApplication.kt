package uz.rustik.eventsbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventsBotApplication

fun main(args: Array<String>) {
    runApplication<EventsBotApplication>(*args)
}
