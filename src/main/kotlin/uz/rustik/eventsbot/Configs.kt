package uz.rustik.eventsbot

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "telegram")
class BotProperties {
    lateinit var bots: List<Bot>

    class Bot {
        lateinit var token: String
        lateinit var chats: Set<Long>
    }
}