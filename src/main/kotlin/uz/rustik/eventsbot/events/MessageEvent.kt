package uz.rustik.eventsbot.events

import org.springframework.context.ApplicationEvent

class MessageEvent(source: Any, val message: String) : ApplicationEvent(source) {

}