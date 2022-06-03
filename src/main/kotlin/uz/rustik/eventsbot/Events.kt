package uz.rustik.eventsbot

import org.springframework.context.ApplicationEvent

class CustomEvent(val any: Any) : ApplicationEvent(any) {

}