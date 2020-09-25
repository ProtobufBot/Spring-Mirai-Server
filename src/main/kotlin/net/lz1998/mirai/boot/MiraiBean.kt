package net.lz1998.mirai.boot

import net.lz1998.mirai.bot.ApiSender
import net.lz1998.mirai.bot.BotFactory
import net.lz1998.mirai.handler.EventHandler
import net.lz1998.mirai.handler.WebSocketHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MiraiBean {
    @Autowired
    lateinit var cqProperties: MiraiProperties

    @Autowired
    lateinit var eventProperties: EventProperties

    @Autowired
    lateinit var botFactory: BotFactory

    @Bean
    @ConditionalOnMissingBean // 如果用户自定义eventHandler则不创建
    fun createEventHandler(): EventHandler {
        return EventHandler()
    }

    @Bean
    @ConditionalOnMissingBean // 如果用户自定义apiSender则不创建
    fun createApiSender(): ApiSender {
        return ApiSender()
    }

    @Bean
    @ConditionalOnMissingBean // 如果用户自定义webSocketHandler则不创建
    fun createWebSocketHandler(): WebSocketHandler {
        return WebSocketHandler(eventProperties, botFactory)
    }
}