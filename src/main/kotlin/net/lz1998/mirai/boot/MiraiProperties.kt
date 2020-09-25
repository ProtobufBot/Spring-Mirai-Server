package net.lz1998.mirai.boot

import net.lz1998.bot.BotPlugin
import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

@ConfigurationProperties(prefix = "spring.mirai")
object MiraiProperties {
    var url = "/ws/*/"
    var maxTextMessageBufferSize = 512000
    var maxBinaryMessageBufferSize = 512000
    var maxSessionIdleTimeout = 15 * 60000L
    var apiTimeout = 120000L
    var pluginList: List<Class<out BotPlugin>> = ArrayList()
}